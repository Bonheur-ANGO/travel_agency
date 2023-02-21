package fr.lernejo.prediction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "/api/temperatures")
    public String getCountryTemperatures(@RequestParam("country") String country) throws JsonProcessingException {
        try {
            Temperature temperature = new Temperature("2022-12-04", temperatureService.getTemperature(country));
            Temperature temperature1 = new Temperature("2022-12-03", temperatureService.getTemperature(country));
            List<Temperature> temperatureList = new ArrayList<>();
            temperatureList.add(temperature);
            temperatureList.add(temperature1);
            CountryTemperature countryTemperature = new CountryTemperature(country, temperatureList);
            return mapper.writeValueAsString(countryTemperature);
        } catch (UnknownCountryException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
