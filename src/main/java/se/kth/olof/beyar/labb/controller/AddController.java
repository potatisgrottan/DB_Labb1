package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

                view.getBookDialogPopup().close();
            });

            view.getBookDialogCancelButton().setOnAction(_ -> {
                view.getBookDialogPopup().close();
            });
        });

        view.getAddAuthorDialog().setOnAction(_ -> view.createAndShowDialog(stage, view.createAuthorBox()));
    }

    public void readValuesFromBookDialog() {
        String isbn = view.getISBNInput().getText();
        String title = view.getTitleInput().getText();
        String genre = view.getGenreInput().getText();
        String grade = view.getGradeInput().getText();

        try {
            databaseService.insertBook(isbn,title,genre,grade);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(isbn + ", " + title  + ", " + genre  + ", " + grade);
    }

    public VBox createAddView()
    {
        return view.createAddView();
    }
}
