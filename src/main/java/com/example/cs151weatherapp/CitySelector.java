package com.example.cs151weatherapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;


public class CitySelector {
    private String city;
    private String geokey = "662f497e33832032337633trb0492c7";
    private String lat;
    private String longi;
    public CitySelector(String city){
        this.city = city;
    }
    public void checkCity(){
        try{
            URL url = new URL("https://geocode.maps.co/search?q=" + city + "&api_key=" + geokey);
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
            String citiesString = content.toString();
            JSONArray citiesJSON = new JSONArray(citiesString);
            JSONObject firstEntry = citiesJSON.getJSONObject(0);
            lat = firstEntry.get("lat").toString();
            longi = firstEntry.get("lon").toString();

        }
        catch(Exception e){
            System.out.println("City Selector Error");
        }
    }

    public String getLat() {
        return lat;
    }

    public String getLongi() {
        return longi;
    }
}
