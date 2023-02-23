package fr.lernejo.prediction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PingController {
    private final TemperatureService temperatureService;
    private final ObjectMapper mapper = new ObjectMapper();

    public PingController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }


    @GetMapping(path = "/api/ping")
    public String ping() {
        return "OK";
    }

    @GetMapping("/api/temperature")
    public ResponseEntity<String> getCountryTemperatures(@RequestParam("country") String country, @RequestBody CountryTemperature countryTemperature1) throws JsonProcessingException {
        /*Temperature temperature = new Temperature("2022-12-04", temperatureService.getTemperature(country));
        Temperature temperature1 = new Temperature("2022-12-03", temperatureService.getTemperature(country));
        List<Temperature> temperatureList = new ArrayList<>();
        temperatureList.add(temperature);
        temperatureList.add(temperature1);
        CountryTemperature countryTemperature1 = new CountryTemperature(country, temperatureList);
        System.out.println(countryTemperature1);
        System.out.println(country);*/
        return ResponseEntity.ok(mapper.writeValueAsString(countryTemperature1));
    }
}
