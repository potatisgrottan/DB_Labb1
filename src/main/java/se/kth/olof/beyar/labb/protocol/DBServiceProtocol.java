package se.kth.olof.beyar.labb.protocol;

// MySQLServiceProtocol < DBServiceProtocol
// NoSQLServiceProtocol < DBServiceProtocol
//
// String uppgifter = "hostname, username, password, databasename";
//
// Service Provider A
// MySQLServiceProtocol mySQLServiceProtocol = new MySQLInstance(uppgifter);
//
// Service Provider B
// NoSQLServiceProtocol noSQLServiceProtocol = new NoSQLInstance(uppgifter);
//
// Service Protocols
// DatabaseProvider databaseProvider = new DatabaseProvider(mySQLInstance)
// DatabaseProvider databaseProvider = new DatabaseProvider(noSQLInstance)
//
// Service Provider C
// PåhittadDatabas < DBServiceProtocol
// PåhittadDatabas påhittadDatabas = new PåhittadDatabas(uppgifter)
// DatabaseProvider databaseProvider = new DatabaseProvider(PåhittadDatabas)
//
// return databaseProvider.findByText(searchValue);

import se.kth.olof.beyar.labb.model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DBServiceProtocol
{
    ArrayList<Book> findByText(String query) throws SQLException;

    public void insertBook(String isbn, String title,String genre, int grade, Connection connection) throws SQLException;

    public void insertAuthor(String firstName, String lastName, String ssn, Connection connection)  throws SQLException;

    public void insertBookByAuthor(String firstName, String lastName, String ssn,
                                   Connection connection, String isbn, String title, String genre, int grade) throws SQLException;

}
