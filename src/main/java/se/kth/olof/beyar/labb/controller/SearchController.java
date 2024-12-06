package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.VBox;
import se.kth.olof.beyar.labb.model.Book;
import se.kth.olof.beyar.labb.model.Database;
import se.kth.olof.beyar.labb.model.MySQLServiceProtocol;
import se.kth.olof.beyar.labb.model.SearchModel;
import se.kth.olof.beyar.labb.view.SearchView;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchController
{
    SearchModel model;
    SearchView view;

    public SearchController(SearchView view, SearchModel model)
    {
        this.model = model;
        this.view = view;
        view.createSearchView();
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
        String user = System.getenv("username");
        String pass = System.getenv("password");
        Database db = new Database("Library", "nahro.ddns.net", user, pass);
        MySQLServiceProtocol mysql = new MySQLServiceProtocol(db);

        ArrayList<Book> books = mysql.findByText(find);
        StringBuilder response = new StringBuilder();

        books.forEach((book -> response.append(book.getTitle()).append("\n")));

        view.setResponseText(response.toString());
    }

    public VBox createSearchView()
    {
        addEventListener();
        return view.createSearchView();
    }
}
