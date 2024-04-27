package com.example.cs151weatherapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WeatherApp extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // First row: City name box and search bar
        Label cityNameLabel = new Label("City Name");
        cityNameLabel.setFont(Font.font("Arial", 14));
        TextField searchTextField = new TextField();
        searchTextField.setPromptText("Enter city name");
        HBox searchBox = new HBox(10, cityNameLabel, searchTextField);
        searchBox.setPadding(new Insets(5));

        // Second row: Icon, Temperature, and Advice button
        ImageView iconImageView = new ImageView(new Image(new FileInputStream("src/main/resources/com/example/cs151weatherapp/clear-weather.png")));
        iconImageView.setFitWidth(50); // Set the desired width
        iconImageView.setFitHeight(50); // Set the desired height
        Text temperatureText = new Text("Temperature: 25°C");
        temperatureText.setFont(Font.font("Arial", 14));
        Button adviceButton = new Button("Advice");
        adviceButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14;");
        HBox weatherBox = new HBox(10, iconImageView, temperatureText, adviceButton);
        weatherBox.setPadding(new Insets(5));

        // Third row: Air quality box
        Text airQualityText = new Text("Air Quality: Good");
        airQualityText.setFont(Font.font("Arial", 14));
        airQualityText.setStyle("-fx-fill: #333;");
        VBox airQualityBox = new VBox(5, airQualityText);
        airQualityBox.setPadding(new Insets(5));

        // Fourth row: Graph box
        // Here you would add your graph component, e.g., LineChart, AreaChart, etc.
        VBox graphBox = new VBox();
        // Add your graph component to the graphBox
        graphBox.setPadding(new Insets(5));

        // Add all rows to the grid
        gridPane.addRow(0, searchBox);
        gridPane.addRow(1, weatherBox);
        gridPane.addRow(2, airQualityBox);
        gridPane.addRow(3, graphBox);

        // Advice button event handler
        adviceButton.setOnAction(event -> {
            // Open a new tab with advice text and image
            Stage adviceStage = new Stage();
            VBox adviceLayout = new VBox(10);
            Text adviceText = new Text("Here's some weather advice...");
            adviceText.setFont(Font.font("Arial", 14));
            ImageView adviceImage = null;
            try {
                adviceImage = new ImageView(new Image(new FileInputStream("src/main/resources/com/example/cs151weatherapp/umbrella.jpeg")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            adviceImage.setFitWidth(200); // Set the desired width
            adviceImage.setFitHeight(200); // Set the desired height
            adviceLayout.getChildren().addAll(adviceText, adviceImage);
            adviceLayout.setPadding(new Insets(20));
            Scene adviceScene = new Scene(adviceLayout, 400, 300);
            adviceStage.setScene(adviceScene);
            adviceStage.show();
        });

        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Weather App");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
