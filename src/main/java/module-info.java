module org.example.java_dictionary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires freetts;
    requires google.cloud.translate;
    //requires proto.google.cloud.translate.v3;

    opens org.java_dictionary to javafx.fxml;
    exports org.java_dictionary;
}