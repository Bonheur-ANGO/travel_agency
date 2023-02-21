package fr.lernejo.travelsite;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RestController
public class TravelSiteController {

    UserRepository userList = new UserRepository();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectMapper objectMapper2 = new ObjectMapper();

    public TravelSiteController(UserRepository userList) {
        this.userList = userList;
    }

    @PostMapping("/api/inscription")
    public String inscription(@ModelAttribute User user, Model model) throws JsonProcessingException {
        model.addAttribute("user", user);
        userList.addUser(user);
        System.out.println(userList.getUserList());
        return objectMapper.writeValueAsString(user);
    }

    @GetMapping("/api/travels")
    public String getTravels(@RequestParam("userName") String userName) throws JsonProcessingException {
        Destination destination1 = new Destination("Caribbean", 32.4);
        Destination destination =new Destination("Australia", 35.1);
        DestinationList destinationList = new DestinationList();
        destinationList.addDestination(destination);
        destinationList.addDestination(destination1);
        System.out.println(destinationList.getDestinationList());
        return objectMapper2.writeValueAsString(destinationList);

    }
}
