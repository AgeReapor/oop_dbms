module com.dbms {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires java.sql;

    opens com.dbms to javafx.fxml;

    exports com.dbms;
}
