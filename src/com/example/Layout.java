package com.example;

public class Layout {

    private Room[] rooms;
    private String startingRoom;
    private String endingRoom;
    private Player[] players;
    private Monster[] monsters;


    public Room[] getRoom() {
        return rooms;
    }
    public String getStartingRoom() {
        return startingRoom;
    }
    public String getEndingRoom() {
        return endingRoom;
    }
    public Player[] getPlayer() { return players; }
    public Monster[] getMonster() { return monsters; }
}
