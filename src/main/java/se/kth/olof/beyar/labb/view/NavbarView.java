package se.kth.olof.beyar.labb.View;

import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class NavbarView
{
    public NavbarView() { }

    public FlowPane buildNavbar()
    {
        Button optionButton = new Button("Options");
        Button searchButton = new Button("Search");
        Button addButton = new Button("Add");

        FlowPane navbar = new FlowPane();
        navbar.getChildren().addAll(optionButton, searchButton, addButton);

        return navbar;
    }
}
