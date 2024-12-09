package se.kth.olof.beyar.labb.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;

    public Database(String schema, String host, int port)
    {
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + schema + "?UseClientEnc=UTF8";
        this.username = System.getenv("username");
        this.password = System.getenv("password");
    }

    public Database(String schema, int port)
    {
        this(schema, "localhost", port);
    }

    public Database(String schema, String host)
    {
        this(schema, host, 3306);
    }

    public Database(String schema)
    {
        this(schema, "localhost", 3306);
    }

    public Connection connect() throws SQLException, ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("DB user " + username + " connected to: " + url);
            return connection;
        } catch (SQLException e)
        {
            throw new SQLException(e);
        } catch (ClassNotFoundException e)
        {
            throw new ClassNotFoundException();
        }
    }

    public void disconnect() throws SQLException
    {
        try
        {
            if (connection != null)
            {
                connection.close();
                System.out.println("Connection closed on " + username);
            }
        } catch (SQLException e)
        {
            throw new SQLException(e);
        }
    }
}
