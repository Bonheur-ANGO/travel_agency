package fr.lernejo.travelsite;

public record UserEntity(String userEmail, String userName, String userCountry, String weatherExpectation, int minimumTemperatureDistance) {
}
