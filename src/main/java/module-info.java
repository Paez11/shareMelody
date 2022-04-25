module proyect.sharemelody {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml.bind;
    requires java.sql;

    opens proyect.sharemelody to javafx.fxml;
    exports proyect.sharemelody;
}