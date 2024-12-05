package se.kth.olof.beyar.labb.View;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class AddButtonViews {

    public AddButtonViews(){}

    public void createBookView(Stage stage, HBox app){

        Label addBookLabel = new Label("Add Book");

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

        VBox vBVBox = new VBox();
        vBVBox.getChildren().addAll(app,addBookLabel,isbnBar,titleBar,genreBar,gradeBar,hBookViewBox);

        Scene addBookScene = new Scene(vBVBox);

        stage.setScene(addBookScene);
        stage.show();
    }

    public void createAuthorView(Stage stage, HBox app){

        Label addBookLabel = new Label("Add author");

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
        vBVBox.getChildren().addAll(app, addBookLabel, lastNameBar, firstNameBar, ssnBar,hBookViewBox);

        Scene addBookScene = new Scene(vBVBox);

        stage.setScene(addBookScene);
        stage.show();
    }
}
