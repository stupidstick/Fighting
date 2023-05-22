module fighting{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.postgresql.jdbc;
    requires java.sql;

    opens controller;
    opens main;
    opens view;
    opens objects;
    opens database;
}