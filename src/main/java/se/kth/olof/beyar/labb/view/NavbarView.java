package se.kth.olof.beyar.labb.view;

import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class NavbarView
{
    private final FlowPane navbar;

    public NavbarView() {
        navbar = new FlowPane();
        buildNavbar();
    }

    private void buildNavbar()
    {
        Button optionButton = new Button("Options");
        Button searchButton = new Button("Search");
        Button addButton = new Button("Add");

        navbar.getChildren().addAll(optionButton, searchButton, addButton);
    }

    public FlowPane getNavbar()
    {
        return navbar;
    }

    public Button getOptionsButton()
    {
        return (Button) navbar.getChildren().getFirst();
    }

    public Button getSearchButton()
    {
        return (Button) navbar.getChildren().get(1);
    }

    public Button getAddButton()
    {
        return (Button) navbar.getChildren().get(2);
    }
}
