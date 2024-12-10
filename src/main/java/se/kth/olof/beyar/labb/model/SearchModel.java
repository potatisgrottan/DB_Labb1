package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.common.Grades;

/**
 * Represents the model for search preferences, specifically holding the preferred grade.
 */
public class SearchModel
{
    Grades preferredGrade;

    /**
     * Constructs a SearchModel with the preferred grade set to NO_PREFERENCE.
     */
    public SearchModel()
    {
        this.preferredGrade = Grades.NO_PREFERENCE;
    }

    /**
     * Returns the preferred grade for the search.
     * @return the preferred grade for the search
     */
    public Grades getPreferredGrade()
    {
        return preferredGrade;
    }

    /**
     * Sets the preferred grade for the search.
     * @param preferredGrade the grade to be set as preferred
     */
    public void setPreferredGrade(Grades preferredGrade)
    {
        this.preferredGrade = preferredGrade;
    }
}
