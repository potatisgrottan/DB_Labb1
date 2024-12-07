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
    public ArrayList<Book> findByText(String query) throws SQLException
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
                int grade = response.getInt("Grade");
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
    public void insertBook(String isbn, String title, String genre, int grade, Connection connection) throws SQLException
    {
        String insertStatement = "INSERT INTO Book VALUES (?, ?, ?, ?)";
        //Book book = new Book(title,genre,isbn,grade); behöver ej skapa ny?

        try (PreparedStatement pstm = connection.prepareStatement(insertStatement))
        {
            pstm.setString(1, isbn);
            pstm.setString(2, title);
            pstm.setString(3, genre);
            pstm.setInt(4, grade);

            pstm.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e);
        }

    }

    @Override
    public void insertAuthor(String firstName, String lastName, String ssn, Connection connection) throws SQLException
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
                                   Connection connection, String isbn, String title, String genre, int grade) throws SQLException
    {
        insertBook(isbn, title, genre, grade, connection);
        insertAuthor(firstName, lastName, ssn, connection);

        String insertStatement = "INSERT INTO WrittenBy VALUES (?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(insertStatement))
        {
            pstm.setString(1, isbn);
            pstm.setString(2, ssn);

            pstm.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}
