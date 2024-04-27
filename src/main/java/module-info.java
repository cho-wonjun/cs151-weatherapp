module com.example.cs151weatherapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs151weatherapp to javafx.fxml;
    exports com.example.cs151weatherapp;
}