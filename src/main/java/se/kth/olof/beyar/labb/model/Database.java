package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.common.BooksDBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents a database connection with methods to connect and disconnect from the database.
 */
public class Database
{
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;

    /**
     * Constructs a Database object with the specified schema, host, and port.
     * @param schema the name of the database schema
     * @param host the database host
     * @param port the port number
     */
    public Database(String schema, String host, int port)
    {
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + schema + "?UseClientEnc=UTF8";
        this.username = System.getenv("username");
        this.password = System.getenv("password");
    }

    /**
     * Constructs a Database object with the specified schema and port, using localhost as the host.
     * @param schema the name of the database schema
     * @param port the port number */
    public Database(String schema, int port)
    {
        this(schema, "localhost", port);
    }

    public Database(String schema, String host)
    {
        this(schema, host, 3306);
    }

    /**
     * Constructs a Database object with the specified schema,
     * using localhost as the host and the default port 3306.
     * @param schema the name of the database schema */
    public Database(String schema)
    {
        this(schema, "localhost", 3306);
    }

    /**
     * Connects to the database using the specified URL, username, and password.
     * @return a Connection object representing the database connection
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the MySQL JDBC Driver class is not found */
    public Connection connect() throws SQLException, ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("DB user " + username + " connected to: " + url);
            return connection;
        }
        catch (SQLException e)
        {
            throw new BooksDBException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new ClassNotFoundException();
        }
    }

    /**
     * Disconnects from the database, closing the connection.
     * @throws SQLException if a database access error occurs
     */
    public void disconnect() throws SQLException
    {
        try
        {
            if (connection != null)
            {
                connection.close();
                System.out.println("Connection closed on " + username);
            }
        }
        catch (SQLException e)
        {
            throw new BooksDBException(e);
        }
    }
}
