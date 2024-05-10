module org.example.java_dictionary {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires freetts;

    opens org.java_dictionary to javafx.fxml;
    exports org.java_dictionary;
}