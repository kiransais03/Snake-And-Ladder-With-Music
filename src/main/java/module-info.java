module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.snakeandladder to javafx.fxml;
    exports com.example.snakeandladder;
}