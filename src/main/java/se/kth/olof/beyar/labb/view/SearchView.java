package se.kth.olof.beyar.labb.View;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchView
{

    public SearchView(){}

    public void createSerchView(Stage stage, HBox app){


        TextField searchBar = new TextField();
        searchBar.setPromptText("Search for books or authors here!");
        Button searchButton = new Button("Search");
        Label searchLable = new Label("Search");
        ScrollPane searchResults = new ScrollPane();
        searchResults.fitToWidthProperty();
        searchResults.setPrefHeight(150);

        TextArea searchResultsArea = new TextArea();
        searchResultsArea.setEditable(false);
        searchResultsArea.setWrapText(true);
        HBox searchApp = new HBox();
        searchApp.getChildren().addAll(searchBar,searchButton);
        searchResults.setContent( searchResultsArea );

        VBox verticalSearchBox = new VBox();
        verticalSearchBox.getChildren().addAll(app, searchLable, searchApp, searchResults);
        Scene searchScene = new Scene(verticalSearchBox,320, 240 );


        stage.setTitle("search example");
        stage.setScene(searchScene);
        stage.show();

        searchButton.setOnAction(_ ->
        {
            String query = searchBar.getText();
            searchResultsArea.setText("Search results for "+query+":\n");
        });
    }
}
