package se.kth.olof.beyar.labb.model;

import java.util.ArrayList;

/**
 * Represents an author with a first name, last name,
 * social security number (SSN), and the books they have written.
 * */
public class Author
{
    private final String firstName;
    private final String lastName;
    private String ssn;
    private final ArrayList<String> written;

    /** * Constructs an Author with the specified first name, last name,and SSN.
     * @param firstName the first name of the author
     * @param lastName the last name of the author
     * @param ssn the social security number of the author
     * */
    public Author(String firstName, String lastName, String ssn)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        written = new ArrayList<>();
    }

    /**
     * Adds a book to the list of books written by the author.
     * @param isbn the book to be added
     *  */
    public void addBook(String isbn)
    {
        written.add(isbn);
    }

    /**
     * @return the first name of the author
     * */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @return the last name of the author
     * */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @return the social security number of the author
     * */
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
