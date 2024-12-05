package se.kth.olof.beyar.labb.model;

public class NavbarModel
{
    enum NavbarViews {OPTIONS, SEARCH, ADD}
    NavbarViews chosenView;

    public NavbarModel(NavbarViews chosenView) {
        this.chosenView = chosenView;
    }

    public NavbarModel() {
        this(NavbarViews.SEARCH);
    }

    public NavbarViews getChosenView()
    {
        return chosenView;
    }

    public void setChosenView(NavbarViews chosenView)
    {
        this.chosenView = chosenView;
    }
}
