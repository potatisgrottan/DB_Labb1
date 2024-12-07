package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.VBox;
import se.kth.olof.beyar.labb.model.Book;
import se.kth.olof.beyar.labb.model.Database;
import se.kth.olof.beyar.labb.model.MySQLServiceProtocol;
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
            try
            {
                queryDBByText(query);
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
    }

    public void queryDBByText(String find) throws SQLException
    {
        ArrayList<Book> books = databaseService.findByText(find);
        StringBuilder response = new StringBuilder();

        books.forEach((book -> response.append(book.getTitle()).append("\n")));
        view.setResponseText(response.toString());
    }

    public VBox createSearchView()
    {
        VBox createdSearchView = view.createSearchView();
        addEventListener();
        return createdSearchView;
    }
}
