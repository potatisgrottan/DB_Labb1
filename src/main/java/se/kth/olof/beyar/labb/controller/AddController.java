package se.kth.olof.beyar.labb.controller;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.model.Author;
import se.kth.olof.beyar.labb.model.Book;
import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;
import se.kth.olof.beyar.labb.view.AddView;

import java.sql.SQLException;

public class AddController
{
    AddView view;
    DBServiceProtocol databaseService;

    public AddController(AddView view, DBServiceProtocol databaseService)
    {
        this.view = view;
        this.databaseService = databaseService;
    }

    public void initializeListeners(Stage stage)
    {
        view.getAddBothDialog().setOnAction(_ -> {
            view.createAndShowDialog(stage, view.createAddBothBox());

            view.getBookDialogSaveButton().setOnAction(_-> {
                new Thread(() -> {
                    readValuesFromBothDialog();
                    Platform.runLater(() -> {
                        view.getBookDialogPopup().close();
                    });
                }).start();
            });

            view.getBookDialogCancelButton().setOnAction(_->view.getBookDialogPopup().close());
        });

        view.getAddBookDialog().setOnAction(_ -> {
            view.createAndShowDialog(stage, view.createAddBookBox());

            view.getBookDialogSaveButton().setOnAction(_ -> {
                new Thread(() -> {
                    readValuesFromBookDialog();
                    Platform.runLater(() -> {
                        view.getBookDialogPopup().close();
                    });
                }).start();
            });

            view.getBookDialogCancelButton().setOnAction(_ -> view.getBookDialogPopup().close());
        });

        view.getAddAuthorDialog().setOnAction(_ -> {
            view.createAndShowDialog(stage, view.createAuthorBox());

            view.getAuthorDialogSaveButton().setOnAction(_ -> {
                new Thread(() -> {
                    readValuesFromAuthorDialog(0);
                    Platform.runLater(() -> {
                        view.getBookDialogPopup().close();
                    });
                }).start();
            });

            view.getAuthorDialogCancelButton().setOnAction(_ -> view.getBookDialogPopup().close());
        });
    }

    public void readValuesFromBookDialog()
    {
        String title = view.getTitleInput().getText();
        String genre = view.getGenreInput().getText();
        String isbn = view.getISBNInput().getText();
        String grade = view.getGradeInput().getText();
        String authorSSN = view.getBookAuthorSSNInput().getText();

        Book book = new Book(title, genre, isbn, grade, authorSSN);
        if(book.getIsbn().isEmpty())
        {
            book.setIsbn(null);
        }

        try {
            databaseService.insertBook(book);
            //TODO Lägg också till i sambandstabellen?
            // Det är lite knasigt hur vi gjort det
            databaseService.insertWrittenBy(book.getIsbn(), book.getAuthors().getFirst().getSSN());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(isbn + ", " + title  + ", " + genre  + ", " + grade + ", " + authorSSN);
    }

    public void readValuesFromAuthorDialog(int authorORboth) {
        Author author;
        String firstname = view.getAuthorFirstname().getText();
        String lastname = view.getAuthorLastname().getText();
        String ssn = view.getAuthorSSN().getText();
        String bookISBN = view.getAuthorBookISBN().getText();

        if(authorORboth == 0)
        {
            author = new Author(firstname, lastname, ssn, bookISBN);
            System.out.println(firstname + ", " + lastname  + ", " + ssn  + ", " + bookISBN);
        }
        else
        {
            author = new Author(firstname,lastname,ssn);
        }

        if(author.getSSN().isEmpty())
        {
            author.setSsn(null);
        }

        try {
            databaseService.insertAuthor(author);
            //TODO Lägg också till i sambandstabellen?
            // Det är lite knasigt hur vi gjort det
            databaseService.insertWrittenBy(bookISBN, author.getSSN());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void readValuesFromBothDialog (){
        String firstname = view.getAuthorFirstname().getText();
        String lastname = view.getAuthorLastname().getText();
        String ssn = view.getAuthorSSN().getText();

        String isbn = view.getISBNInput().getText();
        String title = view.getTitleInput().getText();
        String genre = view.getGenreInput().getText();
        String grade = view.getGradeInput().getText();

        Author a = new Author(firstname,lastname,ssn);
        Book b = new Book(title,genre,isbn,grade);
        b.addAuthor(a);

        if(b.getIsbn().isEmpty())
        {
            b.setIsbn(null);
        }

        if(a.getSSN().isEmpty())
        {
            a.setSsn(null);
        }

        try
        {
            databaseService.insertBookByAuthor(a, b);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public VBox createAddView()
    {
        return view.createAddView();
    }
}
