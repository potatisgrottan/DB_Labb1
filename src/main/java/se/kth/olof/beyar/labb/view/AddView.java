package se.kth.olof.beyar.labb.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddView
{

    public AddView()
    {
    }

    public VBox createAddView()
    {
        Label addLable = new Label("Add");
        Button addBook = new Button("Book");
        Button addAuthor = new Button("Author");
        Button addBoth = new Button("Both");

        HBox addApp = new HBox();
        addApp.getChildren().addAll(addBoth, addBook, addAuthor);
        VBox vertAddBox = new VBox(addLable, addApp);

        AddButtonViews bv = new AddButtonViews();
        addBook.setOnAction(_ -> bv.createBookView());
        addAuthor.setOnAction(_ -> bv.createAuthorView());

        return vertAddBox;
    }
}
