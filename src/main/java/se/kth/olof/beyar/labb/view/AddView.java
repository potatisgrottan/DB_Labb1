package se.kth.olof.beyar.labb.View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddView {

    public AddView(){}

    public void createAddView(Stage stage, HBox app){
        Label addLable = new Label("Add");
        Button addBook = new Button("Book");
        Button addAuthor = new Button("Author");
        Button addBoth = new Button("Both");

        HBox addApp = new HBox();
        addApp.getChildren().addAll(addBoth,addBook,addAuthor);
        VBox VertAddBox = new VBox(app,addLable,addApp);
        Scene addScene = new Scene(VertAddBox,320,240);

        stage.setScene(addScene);
        stage.show();

        AddButtonViews bv = new AddButtonViews();
        addBook.setOnAction(_->bv.createBookView(stage,app));
        addAuthor.setOnAction(_-> bv.createAuthorView(stage,app));


    }
}
