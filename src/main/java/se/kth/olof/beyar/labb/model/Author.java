package se.kth.olof.beyar.labb.model;

import java.util.ArrayList;

public class Author
{
    private final String firstName;
    private final String lastName;
    private String ssn;
    private final ArrayList<String> written;

    public Author(String firstName, String lastName, String ssn)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        written = new ArrayList<>();
    }

    public Author(String firstName, String lastName, String ssn, String bookISBN)
    {
        this(firstName, lastName, ssn);
        this.bookISBN = bookISBN;
    }

    public void addBook(Book book)
    {
        written.add(book);
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getSSN()
    {
        return ssn;
    }

    /**
     * Method used to replace authors with empty string as ssn to null instead
     * @param ssn sets the new ssn of the author
     */
    public void setSsn(String ssn)
    {
        this.ssn = ssn;
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
