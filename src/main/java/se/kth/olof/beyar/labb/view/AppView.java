package se.kth.olof.beyar.labb.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class AppView
{
    BorderPane layout;

    public AppView() {
        this.layout = new BorderPane();
    }

    public void buildLayout(FlowPane navbar, VBox action) {
        layout.setTop(navbar);
        layout.setCenter(action);
    }

    public void rerenderActionLayout(VBox action)
    {
        layout.setCenter(action);
    }

    public BorderPane getLayout()
    {
        return layout;
    }
}
