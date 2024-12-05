package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.kth.olof.beyar.labb.view.AppView;

public class AppController
{
    AppView appView;

    public AppController(AppView appView) {
        this.appView = appView;
    }

    public void buildLayout(FlowPane navbar, VBox action)
    {
        appView.buildLayout(navbar, action);
    }
}
