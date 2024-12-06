package se.kth.olof.beyar.labb.model;

import se.kth.olof.beyar.labb.common.Views;

public class NavbarModel
{
    Views chosenView;

    public NavbarModel(Views chosenView) {
        this.chosenView = chosenView;
    }

    public NavbarModel() {
        this(Views.SEARCH);
    }

    public Views getChosenView()
    {
        return chosenView;
    }

    public void setChosenView(Views chosenView)
    {
        this.chosenView = chosenView;
    }
}
