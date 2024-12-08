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
    Stage dialogPopup;
    VBox bookDialog;
    VBox authorDialog;

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
        dialogPopup = new Stage();
        dialogPopup.initModality(Modality.WINDOW_MODAL);
        dialogPopup.initOwner(stage);
        Scene dialogScene = new Scene(addBox, 300, 250);
        dialogPopup.setScene(dialogScene);
        dialogPopup.show();
    }

    public Stage getDialogPopup()
    {
        return dialogPopup;
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
        Label bookISBNLabel = new Label("ISBN:");

        TextField firstNameBar = new TextField();
        firstNameBar.setPromptText("write the first name here!");

        TextField lastNameBar = new TextField();
        lastNameBar.setPromptText("write the last name here!");

        TextField ssnBar = new TextField();
        ssnBar.setPromptText("write social security number here!");

        TextField bookISBNBar = new TextField();
        bookISBNBar.setPromptText("write book isbn here!");

        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");

        HBox authorDialogAction = new HBox();
        authorDialogAction.getChildren().addAll(saveButton, cancelButton);

        authorDialog = new VBox();
        authorDialog.getChildren().addAll(addBookLabel, firstNameLabel, firstNameBar, lastNameLabel, lastNameBar,
                ssnLabel, ssnBar, bookISBNLabel, bookISBNBar, authorDialogAction);

        return authorDialog;
    }

    public TextField getAuthorFirstname()
    {
        return (TextField) authorDialog.getChildren().get(2);
    }

    public TextField getAuthorLastname()
    {
        return (TextField) authorDialog.getChildren().get(4);
    }

    public TextField getAuthorSSN()
    {
        return (TextField) authorDialog.getChildren().get(6);
    }

    public TextField getAuthorBookISBN()
    {
        return (TextField) authorDialog.getChildren().get(8);
    }

    public Button getAuthorDialogSaveButton()
    {
        return (Button) ((HBox) authorDialog.getChildren().get(9)).getChildren().getFirst();
    }

    public Button getAuthorDialogCancelButton()
    {
        return (Button) ((HBox) authorDialog.getChildren().get(9)).getChildren().get(1);
    }
}
