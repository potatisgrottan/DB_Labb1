package se.kth.olof.beyar.labb.model;

import java.util.ArrayList;

public class Book
{
    private final String isbn;
    private final String title;
    private final String genre;
    private final String grade;
    private final ArrayList<Author> authors;

    public Book(String title, String genre, String isbn, String grade)
    {
        this.title = title;
        this.grade = grade;
        this.genre = genre;
        this.isbn = isbn;
        // the bok with most amout of authors of all time has 26
        authors = new ArrayList<>();
    }

    public Book(String title, String genre, String isbn, String grade, String authorSSN)
    {
        this(title, genre, isbn, grade);
        Author author = new Author(null, null, authorSSN, isbn);
        authors.add(author);
    }

    public void addAuthor(Author author)
    {
        authors.add(author);
    }

    public String getGenre()
    {
        return genre;
    }

    public String getTitle()
    {
        return title;
    }

    public String getGrade()
    {
        return grade;
    }

    public String getIsbn() {
        return isbn;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
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
