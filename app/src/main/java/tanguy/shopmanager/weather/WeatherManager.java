package tanguy.shopmanager.weather;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherManager {

    private static String LIST_OF_CITIES = "citylist.json"; //TODO: JSON de 30 mega :(
    private static String API_KEY = "93c9c3ae0f624c7ff4402be5f85b7b2e";

    Context context;
    String  cityID = "-1";
    JSONObject forecast;

    public WeatherManager (String cityName, Context myContext) throws IOException, ParseException {
        context = myContext;
        cityID = searchForCityID(cityName);
        Log.d("conditionName", cityName);
    }

    private String searchForCityID (String cityName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONArray listOfCities = (JSONArray) parser.parse(new InputStreamReader(context.getResources().getAssets().open(LIST_OF_CITIES))); //getDatabasePath ??

        for (Object o : listOfCities) {
            JSONObject city = (JSONObject) o;

            Log.d("CityName", "CityName: " + city.get("name") + " CityID: " + city.get("id"));

            if (city.get("name").equals(cityName)) {
                return String.valueOf(city.get("id"));
            }
        }

        return "-1";
    }


    public String getCityID() {
        return cityID;
    }

    public String generateWeatherReport() throws Exception {
        String weatherReport;

        if (cityID.equals("-1")) {
            return "There seems to be a problem with the request to the OpenWeatherMap API";
        }
        
        JSONParser parser = new JSONParser();
        forecast = (JSONObject) parser.parse(readUrl("http://api.openweathermap.org/data/2.5/weather?id=" + cityID + "&appid=" + API_KEY));

        Log.d("JSON", readUrl("http://api.openweathermap.org/data/2.5/weather?id=" + cityID + "&appid=" + API_KEY));
        for(Object key : forecast.keySet())
            Log.d("JSON",(String) key);

        String weather = (String) ((JSONObject)(((JSONArray) forecast.get("weather")).get(0))).get("main");

        return ("The weather in " + forecast.get("name") + " is " + weather);
    }

    public void setForecastImage(ImageView image) {
        String imageID = (String) ((JSONObject)((JSONArray)forecast.get("weather")).get(0)).get("icon");
        Log.d("imageID", "setForecastImage: " + imageID);
        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(image);
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}