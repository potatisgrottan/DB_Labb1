package se.kth.olof.beyar.labb.controller;

import javafx.scene.layout.VBox;
import se.kth.olof.beyar.labb.model.Database;
import se.kth.olof.beyar.labb.model.MySQLServiceProtocol;
import se.kth.olof.beyar.labb.model.OptionsModel;
import se.kth.olof.beyar.labb.protocol.DBServiceProtocol;
import se.kth.olof.beyar.labb.view.OptionsView;

import java.sql.Connection;
import java.sql.SQLException;

public class OptionsController
{
    OptionsModel model;
    OptionsView view;
    DBServiceProtocol databaseService;
    Database currentDatabase;

    public OptionsController(OptionsModel model, OptionsView view, DBServiceProtocol databaseService, Database currentDatabase)
    {
        this.model = model;
        this.view = view;
        this.databaseService = databaseService;
        this.currentDatabase = currentDatabase;
    }

    public void initializeListeners()
    {
        view.getDialogSaveButton().setOnAction(_ -> {
            readValuesFromView();
        });
    }

    public void readValuesFromView() {
        String host = view.getHostField().getText();
        String port = view.getPortField().getText();
        String username = view.getUsernameField().getText();
        String password = view.getPasswordField().getText();
        // New connection to DB
        tryConnect(host, port, username, password);
    }

    public void tryConnect(String host, String port, String username, String password)
    {
        Database newDatabase = new Database("Library", host, Integer.parseInt(port), username, password);
    }

    public VBox createView()
    {
        return view.createView();
    }
}
