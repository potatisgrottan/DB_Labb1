package se.kth.olof.beyar.labb.view;


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
    TextField searchBar;
    Button searchButton;
    public SearchView(){}

    public void createSerchView(Stage stage, HBox app){
        searchBar = new TextField();
        searchBar.setPromptText("Search for books or authors here!");
        searchButton = new Button("Search");
        Label searchLable = new Label("Search");

        HBox searchapp = new HBox();
        searchapp.getChildren().addAll(searchBar,searchButton);


        VBox app2 = new VBox();
        app2.getChildren().addAll(app, searchLable, searchapp);
        Scene searchScene = new Scene(app2,320, 240 );


        stage.setTitle("search example");
        stage.setScene(searchScene);
        stage.show();
    }
}
