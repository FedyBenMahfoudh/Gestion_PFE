module org.example.gest_pfe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens org.example.gest_pfe to javafx.fxml;
    opens org.example.gest_pfe.models to javafx.base;
    opens org.example.gest_pfe.controllers to javafx.fxml;

    exports org.example.gest_pfe;
    exports org.example.gest_pfe.controllers;
}