package se.kth.olof.beyar.labb;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.controller.AppController;
import se.kth.olof.beyar.labb.controller.NavbarController;
import se.kth.olof.beyar.labb.model.NavbarModel;
import se.kth.olof.beyar.labb.view.AddView;
import se.kth.olof.beyar.labb.view.AppView;
import se.kth.olof.beyar.labb.view.NavbarView;
import se.kth.olof.beyar.labb.view.SearchView;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        /*
        NavbarModel navbarModel = new NavbarModel();
        NavbarView navbarView = new NavbarView();
        NavbarController navbarController = new NavbarController(navbarModel, navbarView);
        navbarView.buildNavbar();
        */
        Button optionButton = new Button("Options");
        Button searchButton = new Button("Search");
        Button addButton = new Button("Add");
        FlowPane navbar = new FlowPane();
        navbar.getChildren().addAll(optionButton, searchButton, addButton);

        AppView appView = new AppView();
        AppController appController = new AppController(appView);
        VBox searchView = new SearchView().createSerchView();
        VBox addView = new AddView().createAddView();
        appController.buildLayout(navbar, searchView);

        optionButton.setOnAction(_ -> {
            System.out.println("Options button clicked!");
        });

        searchButton.setOnAction(_ -> {
            System.out.println("Search button clicked!");
            appView.rerenderActionLayout(searchView);
        });

        addButton.setOnAction(_ -> {
            appView.rerenderActionLayout(addView);
            System.out.println("Add button clicked!");
        });

        Scene scene = new Scene(appView.getLayout(), 320, 240);
        stage.setTitle("Library application");
        stage.setScene(scene);
        stage.show();
    }
}