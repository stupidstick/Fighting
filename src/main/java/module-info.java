module fighting{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.postgresql.jdbc;
    requires java.sql;

    exports controller;
    opens controller to javafx.fxml;
    opens main;
    opens view;
    opens objects;
    opens database;
}