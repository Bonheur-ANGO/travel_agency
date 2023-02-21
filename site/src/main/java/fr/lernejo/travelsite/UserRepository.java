package fr.lernejo.travelsite;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public void addUser(User user){
        userList.add(user);
    }

    public List<User> getUserList(){
        return userList;
    }

}
