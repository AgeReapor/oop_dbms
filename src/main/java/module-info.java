module com.dbms {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dbms to javafx.fxml;
    exports com.dbms;
}
