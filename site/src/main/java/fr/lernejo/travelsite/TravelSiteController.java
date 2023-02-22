package fr.lernejo.travelsite;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TravelSiteController {

    private final UserRepository userList;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectMapper objectMapper2 = new ObjectMapper();

    public TravelSiteController(UserRepository userList) {
        this.userList = userList;
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
    public String getTravels(@RequestParam("userName") String userName) throws JsonProcessingException {
        Destination destination1 = new Destination("a country", 3.25);
        Destination destination =new Destination("another country", 7.52);
        List<Destination> destinationList = new ArrayList<>();
        destinationList.add(destination);
        destinationList.add(destination1);
        System.out.println(destinationList);
        return objectMapper.writeValueAsString(destinationList);

    }


}
