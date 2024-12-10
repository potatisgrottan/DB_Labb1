package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.common.BooksDBException;
import se.kth.olof.beyar.labb.common.Grades;
import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides the implementation of the DBServiceProtocol for MySQL.
 */
public class MySQLServiceProtocol implements DBServiceProtocol {
    Connection connection;

    /**
     * Constructs a MySQLServiceProtocol with the specified database connection.
     * @param connection the database connection
     */
    public MySQLServiceProtocol(Connection connection) {
        this.connection = connection;
    }

    /**
     * Finds books by a text query and an optional grade filter.
     * @param query the text query to search for
     * @param chosenGrade the rating to filter by, 0 is for "no preference"
     * @return a list of books that match the query and grade filter
     */
    @Override
    public ArrayList<Book> findByText(String query, int chosenGrade) {

        ArrayList<Book> books = new ArrayList<>();
        try {
            Statement request = connection.createStatement();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder
                    .append("SELECT Book.*, Author.* ")
                    .append("FROM Book JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN ")
                    .append("JOIN Author ON WrittenBy.Author_SSN = Author.SSN ")
                    .append("WHERE (")
                    .append("Book.Title LIKE '%").append(query).append("%' ")
                    .append("OR Book.ISBN LIKE '%").append(query).append("%' ")
                    .append("OR Author.FirstName LIKE '%").append(query).append("%' ")
                    .append("OR Author.LastName LIKE '%").append(query).append("%' ")
                    .append("OR Book.Genre LIKE '%").append(query).append("%' ")
                    .append(")");

            if (chosenGrade != Grades.NO_PREFERENCE.ordinal()) {
                queryBuilder.append(" AND Book.Grade = ").append(chosenGrade);
            }

            ResultSet response = request.executeQuery(queryBuilder.toString());

            while (response.next()) {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                books.add(new Book(title, genre, isbn, grade));
            }
            request.close();
        }
        catch (SQLException e) {
            throw new BooksDBException(e);
        }

        return books;
    }


    /**
     * Inserts a new book into the database.
     * @param book the book to insert
     */
    @Override
    public void insertBook(Book book) throws SQLException {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Book VALUES (?, ?, ?, ?)")) {
            pstm.setString(1, book.getIsbn());
            pstm.setString(2, book.getTitle());
            pstm.setString(3, book.getGenre());
            pstm.setString(4, book.getGrade());

            pstm.executeUpdate();
        }
       /* catch (SQLException e)
        {
            throw new BooksDBException(e);
        }*/
    }

    /**
     * Inserts a new author into the database.
     * @param author the author to insert
     */
    @Override
    public void insertAuthor(Author author) throws SQLException {


        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Author VALUES (?, ?, ?)")) {
            pstm.setString(1, author.getSSN());
            pstm.setString(2, author.getFirstName());
            pstm.setString(3, author.getLastName());

            pstm.executeUpdate();
        }
        /*catch (SQLException e)
        {
            throw new BooksDBException(e);
        }*/
    }

    /**
     * Inserts a new entry into the WrittenBy table, linking a book and an author.
     * @param bookISBN the ISBN of the book
     * @param authorSSN the SSN of the author
     */
    @Override
    public void insertWrittenBy(String bookISBN, String authorSSN) throws SQLException{
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO WrittenBy VALUES (?, ?)")) {
            pstm.setString(1, bookISBN);
            pstm.setString(2, authorSSN);

            pstm.executeUpdate();
        }
        /*catch (SQLException e) {
            System.out.println(e);
        }*/
    }

    /**
     * Inserts a book and an author into the database and links them in the WrittenBy table.
     * @param author the author to insert
     * @param book the book to insert
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void insertBookByAuthor(Author author, Book book) throws SQLException
    {
        try {
            connection.setAutoCommit(false);
            insertAuthor(author);
            insertBook(book);
            insertWrittenBy(book.getIsbn(), author.getSSN());
            connection.commit();

        } catch (SQLException e) {
            if (connection != null)
                connection.rollback();

            throw new BooksDBException(e);

        } finally {
            if (connection != null)
                connection.setAutoCommit(true);
        }
    }


    /**
     * Inserts a book and creates a written-by relationship within a single transaction.
     * @param book the book to be inserted
     * @throws SQLException if a database access error occurs or the transaction fails
     * */
    @Override
    public void insertBookTransaktion(Book book) throws SQLException {

        try {
            connection.setAutoCommit(false);

            insertBook(book);
            insertWrittenBy(book.getIsbn(), book.getAuthors().getLast());

            connection.commit();
        } catch (SQLException e) {
            if (connection != null)
                connection.rollback();
            throw new BooksDBException(e);
        } finally {
            if (connection != null)
                connection.setAutoCommit(true);
        }
    }

    /** * Inserts an author and creates a written-by relationship within a single transaction.
     * @param author the author to be inserted
     * @throws SQLException if a database access error occurs or the transaction fails
     * */
    @Override
    public void insertAuthorTransaktion(Author author) throws SQLException {

        try {
            connection.setAutoCommit(false);

            insertAuthor(author);
            insertWrittenBy(author.getBooks().getLast(), author.getSSN());

            connection.commit();
        } catch (SQLException e) {
            if (connection != null)
                connection.rollback();
            throw new BooksDBException(e);
        } finally {
            if (connection != null)
                connection.setAutoCommit(true);
        }
    }
}
