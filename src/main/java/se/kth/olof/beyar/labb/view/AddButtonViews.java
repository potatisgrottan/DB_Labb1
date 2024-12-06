package se.kth.olof.beyar.labb.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddButtonViews
{

    public AddButtonViews()
    {
    }

    public void createBookView()
    {
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
        vBVBox.getChildren().addAll(addBookLabel, isbnBar, titleBar, genreBar, gradeBar, hBookViewBox);
    }

    public void createAuthorView()
    {

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
        vBVBox.getChildren().addAll(addBookLabel, lastNameBar, firstNameBar, ssnBar, hBookViewBox);
    }
}
