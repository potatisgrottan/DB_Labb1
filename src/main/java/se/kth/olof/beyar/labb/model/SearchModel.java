package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.common.Grades;

public class SearchModel
{
    Grades preferredGrade;

    public SearchModel()
    {
        this.preferredGrade = Grades.NO_PREFERENCE;
    }

    public Grades getPreferredGrade()
    {
        return preferredGrade;
    }

    public void setPreferredGrade(Grades preferredGrade)
    {
        this.preferredGrade = preferredGrade;
    }
}
