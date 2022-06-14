module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires json.simple;
    requires javafx.media;

    opens org.openjfx to javafx.fxml;
    exports org.openjfx;
}
