package com.example.cs151weatherapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class MainController {

    @FXML
    private Label mainWeatherLabel;
    @FXML
    private Label currTempMax;
    @FXML
    private Label currTempMin;
    @FXML
    private Label mainWeatherLocationLabel;
    @FXML
    private TextField citySearch;
    @FXML
    private Label helper;
    @FXML
    private Label weatherDescriptionLabel;
    @FXML private Label slot0;
    @FXML private Label slot1;
    @FXML private Label slot2;
    @FXML private Label slot3;
    @FXML private Label slot4;

    private String key = "856a1b5bb6769a9c2402634734e13087";

    private void updatePage(String lat, String longi){
        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + longi + "&appid="  + key + "&units=imperial");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String weatherString = content.toString();
            JSONObject weatherJSON = new JSONObject(weatherString);

            JSONArray weatherDescriptionList = new JSONArray(weatherJSON.getJSONArray("weather"));
            Map<String, Object> map = new ObjectMapper().readValue(weatherDescriptionList.getJSONObject(0).toString(), HashMap.class);

            weatherDescriptionLabel.setText(map.get("main") + ", " + map.get("description"));
            mainWeatherLabel.setText("CURRENT: " + weatherJSON.getJSONObject("main").get("feels_like").toString() + "°F ");
            currTempMax.setText("MAX temp: " + weatherJSON.getJSONObject("main").get("temp_max").toString() + "°F ");
            currTempMin.setText("MIN temp: " + weatherJSON.getJSONObject("main").get("temp_min").toString() + "°F ");
            mainWeatherLocationLabel.setText("Location: " + weatherJSON.get("name").toString() );
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon=" + longi + "&appid=" + key + "&units=imperial");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            String forecastString = content.toString();
            JSONObject forecastJSON = new JSONObject(forecastString);
            JSONArray forecastList = new JSONArray(forecastJSON.getJSONArray("list"));
            ArrayList<JSONObject> days = new ArrayList<>();
            for(int x = 0; x < 5; x++){
                days.add(forecastList.getJSONObject(x));
            }
            slot0.setText(timeConvert(days.get(0).get("dt_txt").toString()) + "\n      " + days.get(0).getJSONObject("main").get("feels_like").toString() + "°F ");
            slot1.setText(timeConvert(days.get(1).get("dt_txt").toString()) + "\n      " + days.get(1).getJSONObject("main").get("feels_like").toString() + "°F ");
            slot2.setText(timeConvert(days.get(2).get("dt_txt").toString()) + "\n      " + days.get(2).getJSONObject("main").get("feels_like").toString() + "°F ");
            slot3.setText(timeConvert(days.get(3).get("dt_txt").toString()) + "\n      " + days.get(3).getJSONObject("main").get("feels_like").toString() + "°F ");
            slot4.setText(timeConvert(days.get(4).get("dt_txt").toString()) + "\n      " + days.get(4).getJSONObject("main").get("feels_like").toString() + "°F ");



        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void handleSearch(){
        mainWeatherLabel.setText("WORKING");
        String inputCity = citySearch.getText();
        CitySelector city = new CitySelector(inputCity);
        try{
            city.checkCity();
            String lat = city.getLat();
            String longi = city.getLongi();
            updatePage(lat, longi);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private String timeConvert(String orig){
        try{
            SimpleDateFormat military = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat standard = new SimpleDateFormat("MM/dd hh:mm a");
            Date date = military.parse(orig);
            String standardtime = standard.format(date);
            return standardtime;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @FXML
    public void initialize(){
        updatePage("37.335480","-121.893028");
    }
    }
