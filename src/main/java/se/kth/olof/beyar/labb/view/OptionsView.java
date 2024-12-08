package se.kth.olof.beyar.labb.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OptionsView
{
    VBox elements;

    public OptionsView()
    {
    }

    public VBox createView()
    {
        Label dialogLabel = new Label("Options");
        Label hostLabel = new Label("Host:");
        Label portLabel = new Label("Port:");
        Label usernameLabel = new Label("username:");
        Label passwordLabel = new Label("password:");

        TextField hostField = new TextField();
        hostField.setPromptText("localhost");

        TextField portField = new TextField();
        portField.setPromptText("3306");

        TextField usernameField = new TextField();
        usernameField.setPromptText("username");

        TextField passwordField = new TextField();
        passwordField.setPromptText("password");

        Button updateButton = new Button("Update");

        HBox dialogActions = new HBox();
        dialogActions.getChildren().addAll(updateButton);

        elements = new VBox();
        elements.getChildren().addAll(dialogLabel, hostLabel, hostField, portLabel, portField,
                usernameLabel, usernameField, passwordLabel, passwordField, dialogActions);

        return elements;
    }

    public TextField getHostField()
    {
        return (TextField) elements.getChildren().get(2);
    }

    public TextField getPortField()
    {
        return (TextField) elements.getChildren().get(4);
    }

    public TextField getUsernameField()
    {
        return (TextField) elements.getChildren().get(6);
    }

    public TextField getPasswordField()
    {
        return (TextField) elements.getChildren().get(8);
    }

    public Button getDialogSaveButton()
    {
        return (Button) ((HBox) elements.getChildren().get(9)).getChildren().getFirst();
    }
}
