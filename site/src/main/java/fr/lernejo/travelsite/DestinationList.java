package fr.lernejo.travelsite;

import java.util.ArrayList;
import java.util.List;

public class DestinationList {
    private final List<Destination> destinationList = new ArrayList<>();

    public void addDestination(Destination destination){
        destinationList.add(destination);
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }
}
