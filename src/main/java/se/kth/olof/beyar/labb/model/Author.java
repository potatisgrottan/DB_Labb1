package se.kth.olof.beyar.labb.model;

import java.util.ArrayList;

public class Author
{
    private String firstName;
    private String lastName;
    private String SSN;
    private final ArrayList<Book> written;

    public Author(String firstName, String lastName, String SSN)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        // Author that has written the most amount of books has written over 1000
        written = new ArrayList<>();
    }

    public void addBook(Book book)
    {
        written.add(book);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getSSN()
    {
        return SSN;
    }

    public void setSSN(String SSN)
    {
        this.SSN = SSN;
    }

    @Override
    public String toString()
    {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", SSN=" + SSN +
                '}';
    }

    public String findByText(String searchValue)
    {
        // MySQLServiceProtocol < SQLServiceProtocol
        // NoSQLServiceProtocol < SQLServiceProtocol

        // Detaljer uppgifter = "hostname, username, password, databasename";

        // Service Provider A
        // MySQLServiceProtocol mySQLServiceProtocol = new MySQLInstance(uppgifter);

        // Service Provider B
        // NoSQLServiceProtocol noSQLServiceProtocol = new NoSQLInstance(uppgifter);

        // Service Provider C
        // PåhittadDatabas påhittadDatabas = new PåhittadDatabas(uppgifter)

        // Service Protocol
        // DatabaseProvider databaseProvider = new DatabaseProvider(mySQLInstance)
        // DatabaseProvider databaseProvider = new DatabaseProvider(noSQLInstance)
        // DatabaseProvider databaseProvider = new DatabaseProvider(PåhittadDatabas)

        // return databaseProvider.findByText(searchValue);

        return "";
    }
}
