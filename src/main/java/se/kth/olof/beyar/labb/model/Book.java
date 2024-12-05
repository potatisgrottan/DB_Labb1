package se.kth.olof.beyar.labb.model;

import java.util.ArrayList;

public class Book
{
    private String isbn;
    private String title;
    private String genre;
    private int grade;
    private final ArrayList<Author> authors;

    public Book(String title, String genre, String isbn ,int grade)
    {
        this.title = title;
        this.grade = grade;
        this.genre = genre;
        this.isbn = isbn;
        // the bok with most amout of authors of all time has 26
        authors = new ArrayList<>();
    }

    public void addAuthor(Author author)
    {
        authors.add(author);
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getGrade()
    {
        return grade;
    }

    public void setGrade(int grade)
    {
        this.grade = grade;
    }



    @Override
    public String toString()
    {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", grade=" + grade +
                '}';
    }
}
