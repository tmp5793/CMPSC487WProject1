module com.example.cmpsc487wproject1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.cmpsc487wproject1 to javafx.fxml;
    exports com.example.cmpsc487wproject1;
}