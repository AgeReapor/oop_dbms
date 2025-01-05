module com.dbms {
    requires javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires org.controlsfx.controls;

    requires java.sql;

    opens com.dbms to javafx.fxml;

    exports com.dbms;
}
