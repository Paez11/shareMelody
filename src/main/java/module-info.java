module proyect.sharemelody {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.xml.bind;
    requires java.sql;

    opens proyect.sharemelody to javafx.fxml;
    opens proyect.sharemelody.utils to java.xml.bind;
    exports proyect.sharemelody;
}