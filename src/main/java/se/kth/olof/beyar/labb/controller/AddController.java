package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb.view.AddButtonViews;
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
        AddButtonViews bv = new AddButtonViews();
        view.getAddBothButton().setOnAction(_ -> bv.createAndShowDialog(stage, bv.createAddBothBox()));
        view.getAddBookButton().setOnAction(_ -> bv.createAndShowDialog(stage, bv.createAddBookBox()));
        view.getAddAuthorButton().setOnAction(_ -> bv.createAndShowDialog(stage, bv.createAuthorBox()));
    }

    public VBox createAddView()
    {
        return view.createAddView();
    }
}
