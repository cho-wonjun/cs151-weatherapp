Weather App

Team #7: Brian Shih, WonJun Cho, Kenneth Naing

Proposal: We all worked on the diagrams together in a call.
Presentation: Wonjun presented the introduction, Brian presented the code snippits, Kenneth presented the live demo.
Project: Wonjun created the skeleton of fxml view and base controller models, Brian bridged the two apis and conneected the backend with frontend, Kenneth fleshed out and finalized the frontend view and designed better user experience.


Issue: Most apps require extensive steps in order to access the information of other cities, and we wanted to simplify this process.

Functionality: This app can be used to quickly check the weather and future forecasts of any city with a quick search. 

Operation: Search bar to quickly navigate to any city in the world. Current specific weather type is displayed (sunny? rainy? etc). The current temperature in fahrenheit is shown on the screen. There is also the max and min temp of the day shown. 
           Forecast can also be seen in intervals of 3 hours with the predicted feel-like weather and weather type shown. If the wrong city is being shown, add more search parameters behind the city name (cityname state/country).

Solution: We solved our focus issue by bridging together the geocoding api with the weather api. Using the geocoder, we can get the coordinates of any city in the world. This information is fed into the weather api with specific coordinates to return
          the forecast of that city. 

Run MainPage.java under src/main/com.example.cs151weatherapp to execute the program.

snapshots of working program: 
![image](https://github.com/cho-wonjun/cs151-weatherapp/assets/79282102/bfed3e15-609b-4e0b-a77b-88ffbcd29b30)

![image](https://github.com/cho-wonjun/cs151-weatherapp/assets/79282102/a55037ec-5d7a-4098-b7e9-d1da9966a0f3)


