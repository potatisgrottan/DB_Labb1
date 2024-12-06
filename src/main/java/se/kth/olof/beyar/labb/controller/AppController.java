package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.kth.olof.beyar.labb.common.Views;
import se.kth.olof.beyar.labb.view.AddView;
import se.kth.olof.beyar.labb.view.AppView;
import se.kth.olof.beyar.labb.view.SearchView;
import java.util.function.Consumer;

public class AppController
{
    AppView view;
    NavbarController navbarController;

    public AppController(AppView view, NavbarController navbarController)
    {
        this.view = view;
        this.navbarController = navbarController;

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
                view.rerenderActionLayout(new SearchView().createSerchView());
                System.out.println("Change view to search");
                break;
            case ADD:
                System.out.println("Change view to add");
                view.rerenderActionLayout(new AddView().createAddView());
                break;
            case OPTIONS:
                System.out.println("Change view to options");
                break;
        }
    }

    public void buildLayout(FlowPane navbar, VBox action) {
        view.buildLayout(navbar, action);
    }
}
