package se.kth.olof.beyar.labb.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchView
{
    HBox searchApp;
    TextArea searchResultsArea;

    public SearchView()
    {
    }

    public VBox createSearchView()
    {
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search for books or authors here!");

        Button searchButton = new Button("Search");
        Label searchLabel = new Label("Search");

        ScrollPane searchResults = new ScrollPane();
        searchResults.fitToWidthProperty();
        searchResults.setPrefHeight(150);

        searchResultsArea = new TextArea();
        searchResultsArea.setEditable(false);
        searchResultsArea.setWrapText(true);

        searchApp = new HBox();
        searchApp.getChildren().addAll(searchBar, searchButton);
        searchResults.setContent(searchResultsArea);

        VBox verticalSearchBox = new VBox();
        verticalSearchBox.getChildren().addAll(searchLabel, searchApp, searchResults);

        return verticalSearchBox;
    }

    public Button getSearchButton()
    {
        return (Button) searchApp.getChildren().get(1);
    }

    public TextField getSearchBar()
    {
        return (TextField) searchApp.getChildren().getFirst();
    }

    public void setResponseText(String text)
    {
        searchResultsArea.setText(text);
    }
}
