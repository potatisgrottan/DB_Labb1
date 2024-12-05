module se.kth.olof.beyar.labb {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.sql;
    requires mysql.connector.j;

    opens se.kth.olof.beyar.labb to javafx.fxml;
    exports se.kth.olof.beyar.labb;

    exports se.kth.olof.beyar.labb.controller;
    opens se.kth.olof.beyar.labb.controller to javafx.fxml;

    exports se.kth.olof.beyar.labb.examples;
    opens se.kth.olof.beyar.labb.examples to javafx.fxml;
}
