package fr.lernejo.travelsite;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {
    private final List<UserEntity> userList = new ArrayList<>();

    public void addUser(UserEntity user){
        userList.add(user);
    }

    public List<UserEntity> getUserList(){
        return userList;
    }

}
