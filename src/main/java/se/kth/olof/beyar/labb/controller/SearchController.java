package se.kth.olof.beyar.labb.controller;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import se.kth.olof.beyar.labb.model.Book;
import se.kth.olof.beyar.labb.model.SearchModel;
import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;
import se.kth.olof.beyar.labb.view.SearchView;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchController
{
    SearchModel model;
    SearchView view;
    DBServiceProtocol databaseService;

    public SearchController(SearchView view, SearchModel model, DBServiceProtocol databaseService)
    {
        this.model = model;
        this.view = view;
        this.databaseService = databaseService;
    }

    public void addEventListener()
    {
        view.getSearchButton().setOnAction(_ ->
        {
            String query = view.getSearchBar().getText();
            int chosenGrade = view.getChosenGrade();
            queryDBByText(query, chosenGrade);
        });
    }

    public void queryDBByText(String find, int grade)
    {
        new Thread(() -> {
            ArrayList<Book> books;
            try
            {
                books = databaseService.findByText(find, grade);
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }

            ArrayList<Book> finalBooks = books;
            Platform.runLater(() -> {
                StringBuilder response = new StringBuilder();
                finalBooks.forEach((book -> response.append(book.getTitle()).append("\n")));
                view.setResponseText(response.toString());
            });
        }).start();
    }

    public VBox createSearchView()
    {
        // The listener has to be attached before returning the view (and therefore creating it)
        // this is because otherwise, the event listener won't react when pressing the button
        VBox createdSearchView = view.createSearchView();
        addEventListener();
        return createdSearchView;
    }
}
