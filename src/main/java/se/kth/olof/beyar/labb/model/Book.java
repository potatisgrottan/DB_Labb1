package se.kth.olof.beyar.labb.model;

import java.util.ArrayList;

/**
 * Represents a book with an ISBN,
 * title, genre, grade, and a list of authors.
 *  */
public class Book
{
    private String isbn;
    private final String title;
    private final String genre;
    private final String grade;
    private final ArrayList<String> authors;

    /**
     * Constructs a Book with the specified title, genre, ISBN, and grade.
     * @param title the title of the book
     * @param genre the genre of the book
     * @param isbn the ISBN of the book
     * @param grade the grade of the book
     * */
    public Book(String title, String genre, String isbn, String grade)
    {
        this.title = title;
        this.grade = grade;
        this.genre = genre;
        this.isbn = isbn;
        authors = new ArrayList<>();
    }

    /** * Constructs a Book with the specified title, genre, ISBN, grade, and author SSN.
     * @param title the title of the book
     * @param genre the genre of the book
     * @param isbn the ISBN of the book
     * @param grade the grade of the book
     * @param authorSSN the social security number of the author who wrote the book
     * */
    public Book(String title, String genre, String isbn, String grade, String authorSSN)
    {
        this(title, genre, isbn, grade);
        authors.add(authorSSN);
    }

    /**
     * Adds an author to the list of authors who have written the book.
     * @param ssn the author to be added
     */
    public void addAuthor(String ssn)
    {
        authors.add(ssn);
    }

    /**
     * Returns the genre of the book.
     * @return the genre of the book
     */
    public String getGenre()
    {
        return genre;
    }

    /**
     * Returns the title of the book.
     * @return the title of the book
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Returns the grade of the book.
     * @return the grade of the book
     */
    public String getGrade()
    {
        return grade;
    }

    /**
     * Returns the ISBN of the book.
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Returns the list of authors who have written the book.
     * @return the list of authors
     */
    public ArrayList<String> getAuthors() {
        return authors;
    }

     /**
     * Used to replace books with empty string as isbn to null
     * @param isbn the new ISBN of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", grade=" + grade +
                ", authorSSN='" + authors + '\'' +
                '}';
    }
}
