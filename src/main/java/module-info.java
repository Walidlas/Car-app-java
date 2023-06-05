module ma.emsi.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires json.simple;

    opens ma.emsi.gui to javafx.fxml;
    exports ma.emsi.gui;

    opens ma.emsi.entities to javafx.fxml;
    exports ma.emsi.entities;
}