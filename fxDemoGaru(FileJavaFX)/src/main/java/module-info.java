module com.example.demogaru {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.demogaru to javafx.fxml;
    exports com.example.demogaru;
}