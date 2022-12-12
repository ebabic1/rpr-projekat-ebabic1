module ba.unsa.etf.rpr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    opens ba.unsa.etf.rpr to javafx.fxml;
    opens ba.unsa.etf.rpr.controller to javafx.fxml;
    exports ba.unsa.etf.rpr;
}