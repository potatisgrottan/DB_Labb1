package se.kth.olof.beyar.labb.view;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import se.kth.olof.beyar.labb.common.Grades;

public class SearchView
{
    private HBox searchApp;
    private TextArea searchResultsArea;
    private ComboBox<Grades> gradeOptions;

    public SearchView()
    {
    }

    public VBox createSearchView(Grades preferredGrade)
    {
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search for books or authors here!");

        Label gradeOptionLabel = new Label("Filter rating");
        gradeOptions = new ComboBox<>(FXCollections.observableArrayList(Grades.values()));
        gradeOptions.setConverter(convertEnumConstantsToNumbers());

        gradeOptions.setPromptText("Grade");
        gradeOptions.setValue(preferredGrade);

        Button searchButton = new Button("Search");
        Label searchLabel = new Label("Search");

        ScrollPane searchResults = new ScrollPane();
        searchResults.fitToWidthProperty();
        searchResults.setPrefHeight(150);

        searchResultsArea = new TextArea();
        searchResultsArea.setEditable(false);
        searchResultsArea.setWrapText(true);

        searchApp = new HBox();
        searchApp.getChildren().addAll(searchBar, searchButton);
        searchResults.setContent(searchResultsArea);

        VBox verticalSearchBox = new VBox();
        verticalSearchBox.getChildren().addAll(searchLabel, searchApp, gradeOptionLabel, gradeOptions, searchResults);

        return verticalSearchBox;
    }

    public ComboBox<Grades> getGradeOptions()
    {
        return gradeOptions;
    }

    public Button getSearchButton()
    {
        return (Button) searchApp.getChildren().get(1);
    }

    public TextField getSearchBar()
    {
        return (TextField) searchApp.getChildren().getFirst();
    }

    public void setResponseText(String text)
    {
        searchResultsArea.setText(text);
    }

    public int getChosenGrade(){
        return gradeOptions.getValue().ordinal();
    }

    private StringConverter<Grades> convertEnumConstantsToNumbers() {
        // This converts the combobox from presenting the options as
        // NO_PREFERENCE, ONE, ..., FIVE
        // To being
        // No preference, 1, ..., 5
        return new StringConverter<>() {
            @Override
            public String toString(Grades grade) {
                if (grade.ordinal() == 0) {
                    return "No preference";
                }
                return String.valueOf(grade.ordinal());
            }

            @Override
            public Grades fromString(String string) {
                return null;
            }
        };
    }
}
