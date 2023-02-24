package fr.lernejo.travelsite;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TravelSiteController {

    private final UserRepository userList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectMapper objectMapper2 = new ObjectMapper();

    @Autowired
    private final PredictionEngineClient predictionEngineClient;

    public TravelSiteController(UserRepository userList, PredictionEngineClient predictionEngineClient) {
        this.userList = userList;
        this.predictionEngineClient = predictionEngineClient;
    }

   /* @GetMapping("/api/inscription")
    public String inscriptionPage(){
        return "index";
    }*/

    @PostMapping("/api/inscription")
        public ResponseEntity<String> inscription(@RequestBody User user) {
        userList.addUser(user);
        return ResponseEntity.ok("Créé avec succès");
    }

    @GetMapping("/api/travels")
    public String getTravels(@RequestParam("userName") String userName) throws IOException {
        Destination destination1 = new Destination("a country", 3.25);
        Destination destination =new Destination("another country", 7.52);
        Requester requester = new Requester(predictionEngineClient);
        User user = userList.getUser(userName);
        if (user != null){
            List<Destination> destinationList = requester.travelProposition(user.userCountry(), user.weatherExpectation(), user.minimumTemperatureDistance());
            destinationList.add(destination);
            destinationList.add(destination1);
            System.out.println(requester.travelProposition(user.userCountry(), user.weatherExpectation(), user.minimumTemperatureDistance()));
            return objectMapper.writeValueAsString(destinationList);
        } else {
            return "l'utilisateur n'existe pas";
        }
    }



}
