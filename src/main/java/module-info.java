module se.kth.olof.beyar.labb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens se.kth.olof.beyar.labb to javafx.fxml;
    exports se.kth.olof.beyar.labb;
    exports se.kth.olof.beyar.labb.demo;
    opens se.kth.olof.beyar.labb.demo to javafx.fxml;
}