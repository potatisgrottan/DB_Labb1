package se.kth.olof.beyar.labb.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddView
{
    HBox dialogOption;
    VBox bookDialog;
    Stage bookDialogPopup;

    public AddView()
    {
    }

    public VBox createAddView()
    {
        Label addLabel = new Label("Add");
        Button addBook = new Button("Book");
        Button addAuthor = new Button("Author");
        Button addBoth = new Button("Both");

        dialogOption = new HBox();
        dialogOption.getChildren().addAll(addBoth, addBook, addAuthor);
        return new VBox(addLabel, dialogOption);
    }

    public Button getAddBothDialog()
    {
        return (Button) dialogOption.getChildren().getFirst();
    }

    public Button getAddBookDialog()
    {
        return (Button) dialogOption.getChildren().get(1);
    }

    public Button getAddAuthorDialog()
    {
        return (Button) dialogOption.getChildren().get(2);
    }

    public void createAndShowDialog(Stage stage, VBox addBox)
    {
        bookDialogPopup = new Stage();
        bookDialogPopup.initModality(Modality.WINDOW_MODAL);
        bookDialogPopup.initOwner(stage);
        Scene dialogScene = new Scene(addBox, 300, 250);
        bookDialogPopup.setScene(dialogScene);
        bookDialogPopup.show();
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

        HBox bookDialogAction = new HBox();
        bookDialogAction.getChildren().addAll(saveButton, cancelButton);

        bookDialog = new VBox();
        bookDialog.getChildren().addAll(addBookLabel, isbnLabel, isbnBar, titleLabel, titleBar,
                genreLabel, genreBar, gradeLabel, gradeBar, bookDialogAction);

        return bookDialog;
    }

    public Stage getBookDialogPopup()
    {
        return bookDialogPopup;
    }

    public TextField getISBNInput()
    {
        return (TextField) bookDialog.getChildren().get(2);
    }

    public TextField getTitleInput()
    {
        return (TextField) bookDialog.getChildren().get(4);
    }

    public TextField getGenreInput()
    {
        return (TextField) bookDialog.getChildren().get(6);
    }

    public TextField getGradeInput()
    {
        return (TextField) bookDialog.getChildren().get(8);
    }

    public Button getBookDialogSaveButton()
    {
        return (Button) ((HBox) bookDialog.getChildren().get(9)).getChildren().getFirst();
    }

    public Button getBookDialogCancelButton()
    {
        return (Button) ((HBox) bookDialog.getChildren().get(9)).getChildren().get(1);
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
