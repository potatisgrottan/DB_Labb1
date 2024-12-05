package se.kth.olof.beyar.labb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.view.SearchView;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Button optionButton = new Button("Options");
        Button searchButton = new Button("Search");
        Button addButton = new Button("Add");
        FlowPane topPane = new FlowPane();
        topPane.getChildren().addAll(optionButton, searchButton, addButton);
        BorderPane navbar = new BorderPane();
        navbar.setTop(topPane);

        optionButton.setOnAction(_ -> System.out.println("Options button clicked!"));
        searchButton.setOnAction(_ -> System.out.println("Home button clicked!"));
        addButton.setOnAction(_ -> System.out.println("Add button clicked!"));

        HBox app = new HBox();
        app.getChildren().addAll(navbar);

        Scene scene = new Scene(app, 320, 240);
        stage.setTitle("Library application");
        stage.setScene(scene);
        stage.show();
    }
}