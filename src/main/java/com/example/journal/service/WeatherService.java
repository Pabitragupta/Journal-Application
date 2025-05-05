package com.example.journal.service;

import com.example.journal.api.response.WeatherResponse;
import com.example.journal.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

//    private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private AppCache appCache;


    @Autowired
    private RedisService redisService;


    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("Weather_of_" + city, WeatherResponse.class);

        if(weatherResponse != null){
            return weatherResponse;
        }
        else {
            String finalAPI = appCache.appCaches.get(AppCache.keys.WEATHER_API.toString()).replace("<city>", city).replace("<api_key>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();

            if(body != null){
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }

    }
}
