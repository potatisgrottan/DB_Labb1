package se.kth.olof.beyar.labb.controller;

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
        view.getAddBothDialog().setOnAction(_ -> view.createAndShowDialog(stage, view.createAddBothBox()));

        view.getAddBookDialog().setOnAction(_ -> {
            view.createAndShowDialog(stage, view.createAddBookBox());

            view.getBookDialogSaveButton().setOnAction(_ -> {
                readValuesFromBookDialog();
                view.getDialogPopup().close();
            });

            view.getBookDialogCancelButton().setOnAction(_ -> view.getDialogPopup().close());
        });

        view.getAddAuthorDialog().setOnAction(_ -> {
            view.createAndShowDialog(stage, view.createAuthorBox());

            view.getAuthorDialogSaveButton().setOnAction(_ -> {
                readValuesFromAuthorDialog();
                view.getDialogPopup().close();
            });

            view.getAuthorDialogCancelButton().setOnAction(_ -> view.getDialogPopup().close());
        });
    }

    public void readValuesFromBookDialog() {
        String isbn = view.getISBNInput().getText();
        String title = view.getTitleInput().getText();
        String genre = view.getGenreInput().getText();
        String grade = view.getGradeInput().getText();

        Book book = new Book(isbn,title,genre,grade);

        try {
            databaseService.insertBook(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(isbn + ", " + title  + ", " + genre  + ", " + grade);
    }

    public void readValuesFromAuthorDialog() {
        String firstname = view.getAuthorFirstname().getText();
        String lastname = view.getAuthorLastname().getText();
        String ssn = view.getAuthorSSN().getText();
        String bookISBN = view.getAuthorBookISBN().getText();

        Author author = new Author(firstname, lastname, ssn, bookISBN);

        try {
            databaseService.insertAuthor(author);
            //TODO Lägg också till i sambandstabellen?
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(firstname + ", " + lastname  + ", " + ssn  + ", " + bookISBN);
    }

    public VBox createAddView()
    {
        return view.createAddView();
    }
}
