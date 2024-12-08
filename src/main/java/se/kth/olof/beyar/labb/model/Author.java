package se.kth.olof.beyar.labb.model;

import java.util.ArrayList;

public class Author
{
    private String firstName;
    private String lastName;
    private String ssn;
    private String bookISBN;
    private final ArrayList<Book> written;

    public Author(String firstName, String lastName, String ssn, String bookISBN)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.bookISBN = bookISBN;
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
        return ssn;
    }

    public void setSSN(String ssn)
    {
        this.ssn = ssn;
    }

    public String getBookISBN()
    {
        return bookISBN;
    }

    @Override
    public String toString()
    {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", SSN=" + ssn +
                '}';
    }
}
