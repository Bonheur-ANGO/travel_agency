package fr.lernejo.travelsite;

import java.util.List;

public record CountryTemperature(String country, List<TemperatureWithDate> temperatures) {

}
