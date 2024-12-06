package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.common.Views;
import se.kth.olof.beyar.labb.view.AddView;
import se.kth.olof.beyar.labb.view.AppView;
import java.util.function.Consumer;

public class AppController
{
    AppView view;
    NavbarController navbarController;
    SearchController searchController;
    Stage stage;

    public AppController(AppView view, NavbarController navbarController, SearchController searchController, Stage stage)
    {
        this.view = view;
        this.navbarController = navbarController;
        this.searchController = searchController;
        this.stage = stage;

        Consumer<Views> updateViewCallback = this::updateView;
        navbarController.setViewHandler(updateViewCallback);
    }

    public BorderPane getView()
    {
        return view.getView();
    }

    private void updateView(Views currentView) {
        switch (currentView) {
            case SEARCH:
                view.rerenderActionLayout(searchController.createSearchView());
                break;
            case ADD:
                view.rerenderActionLayout(new AddView().createAddView(stage));
                break;
            case OPTIONS:
                break;
        }
    }

    public void buildLayout(FlowPane navbar, VBox action) {
        view.buildLayout(navbar, action);
    }
}
