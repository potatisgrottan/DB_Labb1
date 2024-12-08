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
        String insertStatement = "INSERT INTO Book VALUES (?, ?, ?, ?, ?)";
        //Book book = new Book(title,genre,isbn,grade); behöver ej skapa ny?

        try (PreparedStatement pstm = connection.prepareStatement(insertStatement))
        {
            pstm.setString(1, book.getIsbn());
            pstm.setString(2, book.getTitle());
            pstm.setString(3, book.getGenre());
            pstm.setString(4, book.getGrade());
            pstm.setString(5,book.getAuthors().get(0).getSSN());

            pstm.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }

    }

    @Override
    public void insertAuthor(Author author)
    {
        String insertStatement = "INSERT INTO Author VALUES ( ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(insertStatement))
        {
            pstm.setString(1, author.getSSN());
            pstm.setString(2, author.getFirstName());
            pstm.setString(3, author.getLastName());

            pstm.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void insertBookByAuthor(Author author, Book book) throws SQLException
    {
        insertAuthor(author);
        insertBook(book);
        String insertStatement = "INSERT INTO WrittenBy VALUES (?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(insertStatement))
        {
            pstm.setString(1, book.getIsbn());
            pstm.setString(2, author.getSSN());

            pstm.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
            System.out.println();
            throw new SQLException(e);
        }
    }
}
