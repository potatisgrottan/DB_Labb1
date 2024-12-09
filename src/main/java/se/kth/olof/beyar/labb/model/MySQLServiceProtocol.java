package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.common.Grades;
import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;

import java.sql.*;
import java.util.ArrayList;

public class MySQLServiceProtocol implements DBServiceProtocol {
    Connection connection;

    public MySQLServiceProtocol(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ArrayList<Book> findByText(String query, int chosenGrade) {
        simulateDBDelay();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public void insertBook(Book book) {
        simulateDBDelay();
        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Book VALUES (?, ?, ?, ?, ?)")) {
            pstm.setString(1, book.getIsbn());
            pstm.setString(2, book.getTitle());
            pstm.setString(3, book.getGenre());
            pstm.setString(4, book.getGrade());
            pstm.setString(5, book.getAuthors().getFirst().getSSN());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void insertAuthor(Author author) {
        simulateDBDelay();

        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO Author VALUES (?, ?, ?)")) {
            pstm.setString(1, author.getSSN());
            pstm.setString(2, author.getFirstName());
            pstm.setString(3, author.getLastName());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void insertWrittenBy(String bookISBN, String authorSSN) {
        simulateDBDelay();

        try (PreparedStatement pstm = connection.prepareStatement("INSERT INTO WrittenBy VALUES (?, ?)")) {
            pstm.setString(1, bookISBN);
            pstm.setString(2, authorSSN);

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void insertBookByAuthor(Author author, Book book) throws SQLException
    {
        simulateDBDelay();
        try {
            connection.setAutoCommit(false);
            insertAuthor(author);
            insertBook(book);
            insertWrittenBy(book.getIsbn(), author.getSSN());
            connection.commit();
        } catch (Exception e) {
            if (connection != null)
                connection.rollback();
            throw e;
        } finally {
            if (connection != null)
                connection.setAutoCommit(true);
        }
    }

    private void simulateDBDelay()
    {
        try
        {
            System.out.println("[DB] simulating delay");
            Thread.sleep(0);
            System.out.println("[DB] done");
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
