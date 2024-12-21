package se.kth.olof.beyar.labb.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import se.kth.olof.beyar.labb.common.Grades;
import se.kth.olof.beyar.labb.common.SearchOptions;

public class SearchView
{
    private HBox searchApp;
    private TextArea searchResultsArea;
    private ComboBox<Grades> gradeOptions;
    private ComboBox<SearchOptions> searchOptions;

    public SearchView()
    {
    }

    public VBox createSearchView(Grades preferredGrade)
    {
        TextField searchBar = new TextField();
        searchBar.setPromptText("Search for books or authors here!");

        Label searchForLabel = new Label("Search for");
        ObservableList<SearchOptions> optionsValues = FXCollections.observableArrayList(SearchOptions.values());
        searchOptions = new ComboBox<>(optionsValues);
        searchOptions.setPromptText("Title");
        searchOptions.setValue(SearchOptions.Title);

        Label gradeOptionLabel = new Label("Filter rating");
        ObservableList<Grades> grades = FXCollections.observableArrayList(Grades.values());
        gradeOptions = new ComboBox<>(grades);
        gradeOptions.setConverter(convertEnumConstantsToNumbers());
        gradeOptions.setPromptText("Grade");
        gradeOptions.setValue(preferredGrade);

        Button searchButton = new Button("Search");
        Label searchLabel = new Label("Search");

        searchResultsArea = new TextArea();
        searchResultsArea.setEditable(false);
        searchResultsArea.setWrapText(true);

        searchApp = new HBox();
        searchApp.getChildren().addAll(searchBar, searchButton);

        VBox verticalSearchBox = new VBox();
        verticalSearchBox.getChildren().addAll(
                searchLabel, searchApp,
                searchForLabel, searchOptions,
                gradeOptionLabel, gradeOptions,
                searchResultsArea
        );

        return verticalSearchBox;
    }

    public ComboBox<SearchOptions> getSearchOptions(){
        return searchOptions;
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
