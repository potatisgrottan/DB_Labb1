package se.kth.olof.beyar.labb.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddView
{

    public AddView()
    {
    }

    public VBox createAddView(Stage stage)
    {
        Label addLable = new Label("Add");
        Button addBook = new Button("Book");
        Button addAuthor = new Button("Author");
        Button addBoth = new Button("Both");

        HBox addApp = new HBox();
        addApp.getChildren().addAll(addBoth, addBook, addAuthor);
        VBox vertAddBox = new VBox(addLable, addApp);

        AddButtonViews bv = new AddButtonViews();
        addBoth.setOnAction(_ -> bv.createAndShowDialog(stage, bv.createAddBothBox()));
        addBook.setOnAction(_ -> bv.createAndShowDialog(stage, bv.createAddBookBox()));
        addAuthor.setOnAction(_ -> bv.createAndShowDialog(stage, bv.createAuthorBox()));

        return vertAddBox;
    }
}
