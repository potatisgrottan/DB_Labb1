package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.FlowPane;
import se.kth.olof.beyar.labb.common.Views;
import se.kth.olof.beyar.labb.model.NavbarModel;
import se.kth.olof.beyar.labb.view.NavbarView;

import java.util.function.Consumer;

public class NavbarController
{
    NavbarModel model;
    NavbarView view;
    private Consumer<Views> viewChangeHandler;

    public NavbarController(NavbarModel model, NavbarView view)
    {
        this.model = model;
        this.view = view;
        initializeListeners();
    }

    public void setViewChangeHandler(Consumer<Views> handler) {
        this.viewChangeHandler = handler;
    }

    private void initializeListeners()
    {
        view.getOptionsButton().setOnAction(_ -> {
            model.setChosenView(Views.OPTIONS);

            if (viewChangeHandler != null) {
                viewChangeHandler.accept(Views.OPTIONS);
            }
        });

        view.getSearchButton().setOnAction(_ -> {
            model.setChosenView(Views.SEARCH);

            if (viewChangeHandler != null) {
                viewChangeHandler.accept(Views.SEARCH);
            }
        });

        view.getAddButton().setOnAction(_ -> {
            model.setChosenView(Views.ADD);

            if (viewChangeHandler != null) {
                viewChangeHandler.accept(Views.ADD);
            }
        });
    }

    public FlowPane getNavbar()
    {
        return view.getNavbar();
    }
}
