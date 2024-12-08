package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;

import java.sql.*;
import java.util.ArrayList;

public class MySQLServiceProtocol implements DBServiceProtocol
{
    Connection connection;

    public MySQLServiceProtocol(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public ArrayList<Book> findByText(String query)
    {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            Statement request = connection.createStatement();
            ResultSet response = request.executeQuery("SELECT * FROM Book WHERE Title LIKE '%" + query + "%'");
            while (response.next())
            {
                String isbn = response.getString("ISBN");
                String title = response.getString("Title");
                String genre = response.getString("Genre");
                String grade = response.getString("Grade");
                books.add(new Book(title, genre, isbn, grade));
            }
            request.close();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public void insertBook(Book book)
    {
        String insertStatement = "INSERT INTO Book VALUES (?, ?, ?, ?)";
        //Book book = new Book(title,genre,isbn,grade); behöver ej skapa ny?

        try (PreparedStatement pstm = connection.prepareStatement(insertStatement))
        {
            pstm.setString(1, book.getTitle());
            pstm.setString(2, book.getGenre());
            pstm.setString(3, book.getIsbn());
            pstm.setString(4, book.getGrade());

            pstm.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e);
        }

    }

    @Override
    public void insertAuthor(String firstName, String lastName, String ssn, Connection connection)
    {
        String insertStatement = "INSERT INTO Author VALUES ( ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(insertStatement))
        {
            pstm.setString(1, firstName);
            pstm.setString(2, lastName);
            pstm.setString(3, ssn);

            pstm.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void insertBookByAuthor(String firstName, String lastName, String ssn,
                                   Connection connection, Book book) throws SQLException
    {
        insertBook(book);
        insertAuthor(firstName, lastName, ssn, connection);

        String insertStatement = "INSERT INTO WrittenBy VALUES (?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(insertStatement))
        {
            pstm.setString(1, book.getIsbn());
            pstm.setString(2, ssn);

            pstm.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}
