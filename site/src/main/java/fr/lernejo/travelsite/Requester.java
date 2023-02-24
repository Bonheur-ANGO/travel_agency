package fr.lernejo.travelsite;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class Requester {
    private final PredictionEngineClient predictionEngineClient;
    public Requester(PredictionEngineClient predictionEngineClient) {
        this.predictionEngineClient = predictionEngineClient;
    }

    public List<String> loadCountries() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
        String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        Stream<String> lines = content.lines();
        List<String> countries = lines.toList();
        return countries;
    }

    public List<Destination> getAllCountryTemperatureAndconvertJsonResponseToObject() throws IOException {
        List<String> countries = this.loadCountries();

        List<Destination> destinationList = new ArrayList<>();


        for (String country: countries){
            Call<String> call = predictionEngineClient.getTemperature(country);
            Response<String> response = call.execute();
            ObjectMapper objectMapper = new ObjectMapper();
            CountryTemperature countryTemperature = objectMapper.readValue(response.body(), CountryTemperature.class);
            int sum = 0;
            for (TemperatureWithDate temperatureWithDate : countryTemperature.temperatures()){
                if (countryTemperature.country().equals(country)){
                    sum += temperatureWithDate.temperature();
                }
            }
           Destination destination = new Destination(country, sum/countryTemperature.temperatures().size());
            destinationList.add(destination);
        }
        return destinationList;
    }



    public List<Destination> travelProposition(String country, String weatherExpectation, int minimumTemperatureDistance) throws IOException {
        List<Destination> destinationList = this.getAllCountryTemperatureAndconvertJsonResponseToObject();
        List<Destination> filterList = new ArrayList<>();
        for (Destination destination : destinationList){
            if (weatherExpectation.equals("COLDER") && !destination.country().equals(country)){
                if (destination.temperature() < minimumTemperatureDistance){
                    System.out.println(destination);
                    filterList.add(destination);
                }
            } else if (weatherExpectation.equals("WARMER") && !destination.country().equals(country)) {
                if (destination.temperature() > minimumTemperatureDistance){
                    System.out.println(destination);
                    filterList.add(destination);
                }
            }
        }
        return filterList;
    }
}
