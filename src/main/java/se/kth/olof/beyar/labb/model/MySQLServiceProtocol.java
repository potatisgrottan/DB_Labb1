package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.common.BooksDBException;
import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides the implementation of the DBServiceProtocol for MySQL.
 */
public class MySQLServiceProtocol implements DBServiceProtocol
{
    Connection connection;

    /**
     * Constructs a MySQLServiceProtocol with the specified database connection.
     *
     * @param connection the database connection
     */
    public MySQLServiceProtocol(Connection connection)
    {
        this.connection = connection;
    }

    /**
     * Searches for books by author name using partial matching.
     * The search is case-insensitive and matches any part of the author's name.
     *
     * @param authorNameQuery The author name or partial name to search for
     * @return ArrayList of Book objects written by authors matching the query
     * @throws BooksDBException if there's an error executing the database query
     */
    public ArrayList<Book> findByAuthor(String authorNameQuery) {
        return generalSearchFunction(authorNameQuery,"Author.Name LIKE ?");
    }

    /**
     * Searches for books by ISBN using partial matching.
     * Finds books whose ISBN contains the query string.
     *
     * @param query The ISBN or partial ISBN to search for
     * @return ArrayList of Book objects matching the ISBN query
     * @throws BooksDBException if there's an error executing the database query
     */
    public ArrayList<Book> findByISBN(String query) {
        return generalSearchFunction(query, "Book.ISBN LIKE ?");
    }

    /**
     * Searches for books by title using partial matching.
     * The search is case-insensitive and matches any part of the book title.
     *
     * @param query The title or partial title to search for
     * @return ArrayList of Book objects with titles matching the query
     * @throws BooksDBException if there's an error executing the database query
     */
    public ArrayList<Book> findByTitle(String query) {
        return generalSearchFunction(query,"Book.Title LIKE ?");
    }

    /**
     * Searches for books by genre using partial matching.
     * The search is case-insensitive and matches any part of the genre name.
     *
     * @param query The genre or partial genre to search for
     * @return ArrayList of Book objects in genres matching the query
     * @throws BooksDBException if there's an error executing the database query
     */
    public ArrayList<Book> findByGenre(String query) {
        return generalSearchFunction(query, "Book.Genre LIKE ?");
    }

    /**
     * Searches for books by their exact rating/grade.
     * This requires an exact match.
     *
     * @param query The exact grade/rating to search for
     * @return ArrayList of Book objects with the specified grade
     * @throws BooksDBException if there's an error executing the database query
     */
    public ArrayList<Book> findByRating(String query) {
       return generalSearchFunction(query, "Book.Grade = ?");
    }

    /**
     * Inserts a new book into the database.
     *
     * @param book the book to insert
     */
    @Override
    public void insertBook(Book book) throws SQLException
    {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Book VALUES (?, ?, ?, ?)"))
        {
            pstm.setString(1, book.getIsbn());
            pstm.setString(2, book.getTitle());
            pstm.setString(3, book.serializeGenres());
            pstm.setString(4, book.getGrade());
            pstm.executeUpdate();
        }
    }

    /**
     * Inserts a new author into the database.
     *
     * @param author the author to insert
     */
    @Override
    public void insertAuthor(Author author) throws SQLException
    {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Author VALUES (?, ?)"))
        {
            pstm.setString(1, author.getSSN());
            pstm.setString(2, author.getName());
            pstm.executeUpdate();
        }
    }

    /**
     * Inserts a new entry into the WrittenBy table, linking a book and an author.
     *
     * @param bookISBN  the ISBN of the book
     * @param authorSSN the SSN of the author
     */
    @Override
    public void insertWrittenBy(String bookISBN, String authorSSN) throws SQLException
    {
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO WrittenBy VALUES (?, ?)"))
        {
            pstm.setString(1, bookISBN);
            pstm.setString(2, authorSSN);
            pstm.executeUpdate();
        }
    }

    /**
     * Inserts a book and an author into the database and links them in the WrittenBy table.
     *
     * @param author the author to insert
     * @param book   the book to insert
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void insertBookByAuthor(Author author, Book book) throws SQLException
    {
        try
        {
            connection.setAutoCommit(false);
            insertAuthor(author);
            insertBook(book);
            insertWrittenBy(book.getIsbn(), author.getSSN());
            connection.commit();

        } catch (SQLException e)
        {
            if (connection != null)
                connection.rollback();

            throw new BooksDBException(e);

        } finally
        {
            if (connection != null)
                connection.setAutoCommit(true);
        }
    }

    /**
     * Inserts a book and creates a written-by relationship within a single transaction.
     *
     * @param book the book to be inserted
     * @param authorSSN the ssn of the author who wrote the book
     * @throws SQLException if a database access error occurs or the transaction fails
     */
    @Override
    public void insertBookUpdateAuthor(Book book, String authorSSN) throws SQLException
    {
        try
        {
            connection.setAutoCommit(false);
            insertBook(book);
            insertWrittenBy(book.getIsbn(), authorSSN);
            connection.commit();
        } catch (SQLException e)
        {
            if (connection != null)
                connection.rollback();
            throw new BooksDBException(e);
        } finally
        {
            if (connection != null)
                connection.setAutoCommit(true);
        }
    }

    /**
     * Inserts an author and creates a written-by relationship within a single transaction.
     *
     * @param author the author to be inserted
     * @param bookISBN the book isbn to be inserted written by the author
     * @throws SQLException if a database access error occurs or the transaction fails
     */
    @Override
    public void insertAuthorUpdateBook(Author author, String bookISBN) throws SQLException
    {
        try
        {
            connection.setAutoCommit(false);

            insertAuthor(author);
            insertWrittenBy(author.getBooks().getLast(), author.getSSN());

            connection.commit();
        } catch (SQLException e)
        {
            if (connection != null)
                connection.rollback();

            throw new BooksDBException(e);
        } finally
        {
            if (connection != null)
                connection.setAutoCommit(true);
        }
    }

    /**
     * Checks if a book with the given ISBN exists in the array list and adds the author to it if found.
     * This helper method is used to prevent duplicate book entries when the same book has multiple authors.
     *
     * @param arrayList The list of books to search through
     * @param isbn The ISBN to look for
     * @param author The author to add to the book if found
     * @return true if the book was found and author added, false if the book was not found
     */
    private boolean bookInArray(ArrayList<Book> arrayList, String isbn, Author author)
    {
        for (Book book : arrayList)
        {
            if (book.getIsbn().equals(isbn))
            {
                book.addAuthor(author);
                return true;
            }
        }
        return false;
    }

    /**
     * Performs a general database search using the provided query and search condition.
     * This method executes a SQL query that joins the Book, WrittenBy, and Author tables.
     *
     * @param query The search value to look for
     * @param addToSearchQuery The WHERE clause condition to add to the SQL query
     * @return ArrayList of Book objects matching the search criteria, with their associated authors
     * @throws BooksDBException if there's an error executing the SQL query
     */
    private ArrayList<Book> generalSearchFunction(String query, String addToSearchQuery){
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT Book.*, Author.* " +
                    "FROM Book JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN " +
                    "JOIN Author ON WrittenBy.Author_SSN = Author.SSN " +
                    "WHERE " + addToSearchQuery;

            PreparedStatement request = connection.prepareStatement(searchQuery);
            request.setString(1, query);
            ResultSet response = request.executeQuery();

            while (response.next())
            {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                String ssn = response.getString("SSN");
                String name = response.getString("Name");

                Author author = new Author(name, ssn);
                if (!(bookInArray(books, isbn, author)))
                {
                    Book book = new Book(title, genre, isbn, grade);
                    book.addAuthor(author);
                    books.add(book);
                }
            }
            request.close();
        } catch (SQLException e)
        {
            throw new BooksDBException(e);
        }

        return books;
    }
}
