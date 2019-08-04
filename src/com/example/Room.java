package com.example;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private String name;
    private String description;
    private List<Item> items = new ArrayList<>();
    private Direction[] directions;
    private List<String> monstersInRoom = new ArrayList<>();


    public Direction[] getDirection() {
        return directions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        if(items == null) {
            return null;
        }
        return items;
    }

    public List<String> getMonstersInRoom() {
        if(monstersInRoom == null) {
            return null;
        }
        return monstersInRoom;
    }


}
