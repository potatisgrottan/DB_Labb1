package se.kth.olof.beyar.labb;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.controller.AppController;
import se.kth.olof.beyar.labb.controller.NavbarController;
import se.kth.olof.beyar.labb.model.NavbarModel;
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
        NavbarModel navbarModel = new NavbarModel();
        NavbarView navbarView = new NavbarView();
        NavbarController navbarController = new NavbarController(navbarModel, navbarView);

        AppView appView = new AppView();
        AppController appController = new AppController(appView, navbarController);

        appView.buildLayout(
                navbarController.getNavbar(),
                new SearchView().createSerchView()
        );

        Scene scene = new Scene(appController.getView(), 320, 240);
        stage.setTitle("Library application");
        stage.setScene(scene);
        stage.show();
    }
}