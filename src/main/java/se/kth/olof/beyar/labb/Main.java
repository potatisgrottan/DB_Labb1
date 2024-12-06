package se.kth.olof.beyar.labb;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.controller.AppController;
import se.kth.olof.beyar.labb.controller.NavbarController;
import se.kth.olof.beyar.labb.model.NavbarModel;
import se.kth.olof.beyar.labb.view.*;

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
        appController.buildLayout(
                navbarController.getNavbar(),
                new SearchView().createSearchView()
        );

        Scene scene = new Scene(appController.getView(), 320, 240);
        // Options button gets highlighted even though the default view is Search
        navbarController.focusButtonOnStart(navbarView.getSearchButton());
        stage.setTitle("Library application");
        stage.setScene(scene);
        stage.show();
    }
}