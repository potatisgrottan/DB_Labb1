package se.kth.olof.beyar.labb.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import se.kth.olof.beyar.labb.model.NavbarModel;
import se.kth.olof.beyar.labb.View.NavbarView;

public class NavbarController
{
    NavbarModel model;
    NavbarView view;

    public NavbarController(NavbarModel model, NavbarView view) {
        this.model = model;
        this.view = view;

        //attachActionsToButtons();
    }

    /*
    navbarController.attachAction(optionButton, _ -> {
        System.out.println("Options button clicked!");
    });
    */
    public void attachActionsToButtons(Button buttonToTrigger, EventHandler<ActionEvent> actionToPerform)
    {
        buttonToTrigger.setOnAction(actionToPerform);
    }
}
