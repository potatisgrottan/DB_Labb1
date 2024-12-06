package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.BorderPane;
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
        navbarController.setViewChangeHandler(updateViewCallback);
    }

    public BorderPane getView()
    {
        return view.getView();
    }

    private void updateView(Views currentView) {
        switch (currentView) {
            case SEARCH:
                view.rerenderActionLayout(new SearchView().createSerchView());
                break;
            case ADD:
                view.rerenderActionLayout(new AddView().createAddView());
                break;
            case OPTIONS:
                break;
        }
    }
}
