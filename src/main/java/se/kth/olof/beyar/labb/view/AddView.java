package se.kth.olof.beyar.labb.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddView
{
    HBox addApp;

    public AddView()
    {
    }

    public VBox createAddView()
    {
        Label addLabel = new Label("Add");
        Button addBook = new Button("Book");
        Button addAuthor = new Button("Author");
        Button addBoth = new Button("Both");

        addApp = new HBox();
        addApp.getChildren().addAll(addBoth, addBook, addAuthor);
        return new VBox(addLabel, addApp);
    }

    public Button getAddBothButton()
    {
        return (Button) addApp.getChildren().getFirst();
    }

    public Button getAddBookButton()
    {
        return (Button) addApp.getChildren().get(1);
    }

    public Button getAddAuthorButton()
    {
        return (Button) addApp.getChildren().get(2);
    }
}
