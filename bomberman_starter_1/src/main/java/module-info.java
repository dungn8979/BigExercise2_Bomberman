module com.example.bomberman_starter_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bomberman_starter_1 to javafx.fxml;
    exports com.example.bomberman_starter_1;
}