package se.kth.olof.beyar.labb.view;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SearchView
{
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

        TextArea searchResultsArea = new TextArea();
        searchResultsArea.setEditable(false);
        searchResultsArea.setWrapText(true);

        HBox searchApp = new HBox();
        searchApp.getChildren().addAll(searchBar, searchButton);
        searchResults.setContent(searchResultsArea);

        VBox verticalSearchBox = new VBox();
        verticalSearchBox.getChildren().addAll(searchLabel, searchApp, searchResults);

        // Den här borde vara hos search controllern
        searchButton.setOnAction(_ ->
        {
            String query = searchBar.getText();
            searchResultsArea.setText("Search results for " + query + ":\n");
        });

        return verticalSearchBox;
    }
}
