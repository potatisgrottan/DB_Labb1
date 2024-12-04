package se.kth.olof.beyar.labb.Model;

import java.util.ArrayList;

public class Author {
    private String firstName;
    private String lastName;
    private String SSN;
    private ArrayList<Book> written;

    public Author(String firstName, String lastName, String SSN){
        this.firstName=firstName;
        this.lastName=lastName;
        this.SSN=SSN;
        written = new ArrayList<>(); // author that has written the most amount of books has written over 1000
    }

    public void addBook(Book book){
        written.add(book);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", SSN=" + SSN +
                '}';
    }
}
