module se.kth.olof.beyar.labb {
    requires javafx.controls;
    requires javafx.fxml;

    opens se.kth.olof.beyar.labb to javafx.fxml;
    exports se.kth.olof.beyar.labb;
}