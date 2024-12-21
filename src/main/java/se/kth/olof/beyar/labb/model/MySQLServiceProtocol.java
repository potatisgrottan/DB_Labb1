package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.common.BooksDBException;
import se.kth.olof.beyar.labb.common.Grades;
import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;

import java.sql.*;
import java.util.ArrayList;

/**
 * Provides the implementation of the DBServiceProtocol for MySQL.
 */
public class MySQLServiceProtocol implements DBServiceProtocol
{
    /**
     * Finds books by a text query and an optional grade filter.
     *
     * @param query       the text query to search for
     * @param chosenGrade the rating to filter by, 0 is for "no preference"
     * @return a list of books that match the query and grade filter
     */
    // TODO
    // Break down findByText to the implementations below before Labb 2
    @Override
    public ArrayList<Book> findByText(String query, int chosenGrade) throws BooksDBException
    {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT Book.*, Author.* " +
                    "FROM Book JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN " +
                    "JOIN Author ON WrittenBy.Author_SSN = Author.SSN " +
                    "WHERE (" +
                    "Book.Title LIKE ? " +
                    "OR Book.ISBN LIKE ? " +
                    "OR Author.FirstName LIKE ? " +
                    "OR Author.LastName LIKE ? " +
                    "OR Book.Genre LIKE ?" +
                    ")";

            if (chosenGrade != Grades.NO_PREFERENCE.ordinal())
            {
                searchQuery += " AND Book.Grade = ?";
            }

            PreparedStatement request = connection.prepareStatement(searchQuery);

            String wildcardQuery = "%" + query + "%";
            request.setString(1, wildcardQuery);
            request.setString(2, wildcardQuery);
            request.setString(3, wildcardQuery);
            request.setString(4, wildcardQuery);
            request.setString(5, wildcardQuery);

            if (chosenGrade != Grades.NO_PREFERENCE.ordinal())
            {
                request.setInt(6, chosenGrade);
            }

            ResultSet response = request.executeQuery();

            while (response.next())
            {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                String ssn = response.getString("SSN");
                String firstName = response.getString("FirstName");
                String lastName = response.getString("LastName");

                Author author = new Author(firstName, lastName, ssn);
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

   public ArrayList<Book>  findByAuthor(String query) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT Book.*, Author.* " +
                    "FROM Book JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN " +
                    "JOIN Author ON WrittenBy.Author_SSN = Author.SSN " +
                    "WHERE (" +
                    "Author.FirstName LIKE ? " +
                    "OR Author.LastName LIKE ? " +
                    ")";

            PreparedStatement request = connection.prepareStatement(searchQuery);

            String wildcardQuery = "%" + query + "%";
            request.setString(1, wildcardQuery);
            request.setString(2, wildcardQuery);


            ResultSet response = request.executeQuery();

            while (response.next())
            {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                String ssn = response.getString("SSN");
                String firstName = response.getString("FirstName");
                String lastName = response.getString("LastName");

                Author author = new Author(firstName, lastName, ssn);
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

   public ArrayList<Book>  findByISBN(String query) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT Book.*, Author.* " +
                    "FROM Book JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN " +
                    "JOIN Author ON WrittenBy.Author_SSN = Author.SSN " +
                    "WHERE (" +
                    " Book.ISBN LIKE ? " +
                    ")";

            PreparedStatement request = connection.prepareStatement(searchQuery);

            String wildcardQuery = "%" + query + "%";
            request.setString(1, wildcardQuery);

            ResultSet response = request.executeQuery();

            while (response.next())
            {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                String ssn = response.getString("SSN");
                String firstName = response.getString("FirstName");
                String lastName = response.getString("LastName");

                Author author = new Author(firstName, lastName, ssn);
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

   public ArrayList<Book>  findByTitle(String query) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT Book.*, Author.* " +
                    "FROM Book JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN " +
                    "JOIN Author ON WrittenBy.Author_SSN = Author.SSN " +
                    "WHERE (" +
                    "Book.Title LIKE ? " +
                    ")";

            PreparedStatement request = connection.prepareStatement(searchQuery);

            String wildcardQuery = "%" + query + "%";
            request.setString(1, wildcardQuery);


            ResultSet response = request.executeQuery();

            while (response.next())
            {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                String ssn = response.getString("SSN");
                String firstName = response.getString("FirstName");
                String lastName = response.getString("LastName");

                Author author = new Author(firstName, lastName, ssn);
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

   public ArrayList<Book>  findByGenre(String query) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT Book.*, Author.* " +
                    "FROM Book JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN " +
                    "JOIN Author ON WrittenBy.Author_SSN = Author.SSN " +
                    "WHERE (" +
                    "Book.Genre LIKE ?" +
                    ")";

            PreparedStatement request = connection.prepareStatement(searchQuery);

            String wildcardQuery = "%" + query + "%";
            request.setString(1, wildcardQuery);

            ResultSet response = request.executeQuery();

            while (response.next())
            {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                String ssn = response.getString("SSN");
                String firstName = response.getString("FirstName");
                String lastName = response.getString("LastName");

                Author author = new Author(firstName, lastName, ssn);
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

   public ArrayList<Book>  findByRating(String query, int chosenGrade) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            String searchQuery = "SELECT Book.*, Author.* " +
                    "FROM Book JOIN WrittenBy ON WrittenBy.Book_ISBN = Book.ISBN " +
                    "JOIN Author ON WrittenBy.Author_SSN = Author.SSN " +
                    "WHERE (" +
                    "Book.Grade = ?" +
                    ")";

            PreparedStatement request = connection.prepareStatement(searchQuery);
            request.setInt(1, chosenGrade);
            ResultSet response = request.executeQuery();

            while (response.next())
            {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                String ssn = response.getString("SSN");
                String firstName = response.getString("FirstName");
                String lastName = response.getString("LastName");

                Author author = new Author(firstName, lastName, ssn);
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
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Author VALUES (?, ?, ?)"))
        {
            pstm.setString(1, author.getSSN());
            pstm.setString(2, author.getFirstName());
            pstm.setString(3, author.getLastName());
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
     * @throws SQLException if a database access error occurs or the transaction fails
     */
    @Override
    public void insertBookTransaktion(Book book, String authorSSN) throws SQLException
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
     * @throws SQLException if a database access error occurs or the transaction fails
     */
    @Override
    public void insertAuthorTransaktion(Author author) throws SQLException
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
}
