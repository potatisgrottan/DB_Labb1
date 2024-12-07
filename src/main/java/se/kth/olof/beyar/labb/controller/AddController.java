package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.view.AddView;

public class AddController
{
    AddView view;

    public AddController(AddView view)
    {
        this.view = view;
    }

    public void initializeListeners(Stage stage)
    {
        view.getAddBothButton().setOnAction(_ -> view.createAndShowDialog(stage, view.createAddBothBox()));
        view.getAddBookButton().setOnAction(_ -> view.createAndShowDialog(stage, view.createAddBookBox()));
        view.getAddAuthorButton().setOnAction(_ -> view.createAndShowDialog(stage, view.createAuthorBox()));
    }

    public VBox createAddView()
    {
        return view.createAddView();
    }
}
