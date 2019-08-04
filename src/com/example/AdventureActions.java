package com.example;

import java.util.ArrayList;

public class AdventureActions {

    public static void output(Room room) {

        StringBuilder directionOptions = new StringBuilder();
        for(int i = 0; i < room.getDirection().length; i++) {
            directionOptions.append((room.getDirection()[i]).getDirectionName() + ", ");
        }
        StringBuilder itemList = new StringBuilder();
        for(int i = 0; i < room.getItems().size(); i++) {
            itemList.append((room.getItems().get(i)).getItemName() + ", ");
        }
        StringBuilder monsterList = new StringBuilder();
        for(int i = 0; i < room.getMonstersInRoom().size(); i++) {
            monsterList.append((room.getMonstersInRoom().get(i)) + ", ");
        }

        System.out.println(room.getName());
        System.out.println(room.getDescription());
        System.out.println("This room contains: " + itemList);
        System.out.print("Watch out! There's a " + monsterList);
    }


    public static boolean grabItem(Room currentRoom, String[] inputWords, Player currentPlayer, ArrayList<Item> inventory) {

        for (int i = 0; i < currentRoom.getItems().size(); i++) {
            if (inputWords[1].equalsIgnoreCase(currentRoom.getItems().get(i).getItemName())) {
                inventory.add(currentRoom.getItems().get(i));
                currentRoom.getItems().remove(i);
                return true;
            }
        }
        System.out.print("I can't grab " + inputWords[1]);
        return false;
    }

    public static boolean dropItem(Room currentRoom, String[] inputWords, Player currentPlayer, ArrayList<Item> inventory) {

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItemName().equalsIgnoreCase(inputWords[1])) {
                currentRoom.getItems().add(inventory.get(i));
                inventory.remove(i);
                return true;
            }
        }
        System.out.print("I can't drop " + inputWords[1]);
        return false;
    }

}
