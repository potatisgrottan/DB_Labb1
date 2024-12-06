package se.kth.olof.beyar.labb.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddButtonViews
{

    public AddButtonViews()
    {
    }

    public void createAndShowDialog(Stage stage, VBox addBox)
    {
        Stage dialogBook = new Stage();
        dialogBook.initModality(Modality.WINDOW_MODAL);
        dialogBook.initOwner(stage);
        Scene dialogScene = new Scene(addBox, 300, 250);
        dialogBook.setScene(dialogScene);
        dialogBook.showAndWait();
    }

    public VBox createAddBothBox()
    {
        HBox bothBox = new HBox();

        VBox bookBox = createAddBookBox();
        VBox authorBox = createAuthorBox();

        authorBox.getChildren().remove(7);

        bothBox.getChildren().addAll(bookBox, authorBox);

        VBox bothVBox = new VBox();
        bothVBox.getChildren().addAll(bothBox);
        return bothVBox;
    }

    public VBox createAddBookBox()
    {
        Label addBookLabel = new Label("Add Book");
        Label isbnLabel = new Label("ISBN:");
        Label titleLabel = new Label("Title:");
        Label genreLabel = new Label("Genre:");
        Label gradeLabel = new Label("Grade:");

        TextField titleBar = new TextField();
        titleBar.setPromptText("Add title here!");

        TextField isbnBar = new TextField();
        isbnBar.setPromptText("Add isbn here!");

        TextField gradeBar = new TextField();
        gradeBar.setPromptText("Add grade here!");

        TextField genreBar = new TextField();
        genreBar.setPromptText("Add genre here!");

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        HBox hBookViewBox = new HBox();
        hBookViewBox.getChildren().addAll(saveButton, cancelButton);

        VBox bookVBox = new VBox();
        bookVBox.getChildren().addAll(addBookLabel, isbnLabel, isbnBar, titleLabel, titleBar,
                genreLabel, genreBar, gradeLabel, gradeBar, hBookViewBox);

        return bookVBox;
    }

    public VBox createAuthorBox()
    {
        Label addBookLabel = new Label("Add author");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label ssnLabel = new Label("Social Security Number:");

        TextField firstNameBar = new TextField();
        firstNameBar.setPromptText("write the first name here!");

        TextField lastNameBar = new TextField();
        lastNameBar.setPromptText("write the last name here!");

        TextField ssnBar = new TextField();
        ssnBar.setPromptText("write social security number here!");

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        HBox hBookViewBox = new HBox();
        hBookViewBox.getChildren().addAll(saveButton, cancelButton);

        VBox vBVBox = new VBox();
        vBVBox.getChildren().addAll(addBookLabel, firstNameLabel, firstNameBar, lastNameLabel, lastNameBar,
                ssnLabel, ssnBar, hBookViewBox);

        return vBVBox;
    }
}
