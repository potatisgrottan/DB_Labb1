module se.kth.olof.beyar.dbl1.db_labb1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.kth.olof.beyar.dbl1.db_labb1 to javafx.fxml;
    exports se.kth.olof.beyar.dbl1.db_labb1;
}