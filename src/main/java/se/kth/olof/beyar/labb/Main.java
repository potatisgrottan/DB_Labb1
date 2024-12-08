package se.kth.olof.beyar.labb;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.controller.AddController;
import se.kth.olof.beyar.labb.controller.AppController;
import se.kth.olof.beyar.labb.controller.NavbarController;
import se.kth.olof.beyar.labb.controller.SearchController;
import se.kth.olof.beyar.labb.model.*;
import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;
import se.kth.olof.beyar.labb.view.*;
import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException
    {
        String user = System.getenv("username");
        String pass = System.getenv("password");
        Database db = new Database("Library", "nahro.ddns.net", user, pass);
        DBServiceProtocol databaseService;
        try
        {
            Connection connection = db.connect();
            databaseService = new MySQLServiceProtocol(connection);
        } catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }

        stage.setOnCloseRequest(_ -> {
            try
            {
                db.disconnect();
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });

        NavbarModel navbarModel = new NavbarModel();
        NavbarView navbarView = new NavbarView();
        NavbarController navbarController = new NavbarController(navbarModel, navbarView);
        navbarController.initializeListeners();

        SearchModel searchModel = new SearchModel();
        SearchView searchView = new SearchView();
        SearchController searchController = new SearchController(searchView, searchModel, databaseService);

        AddView addView = new AddView();
        AddController addController = new AddController(addView,databaseService);

        AppView appView = new AppView();
        AppController appController = new AppController(stage, appView, navbarController, searchController, addController);
        appController.buildLayout(
                navbarController.getNavbar(),
                searchController.createSearchView()
        );

        Scene scene = new Scene(appController.getView(), 320, 240);
        // Options button gets highlighted even though the default view is Search
        navbarController.focusButtonOnStart(navbarView.getSearchButton());
        stage.setTitle("Library application");
        stage.setScene(scene);
        stage.show();
    }
}