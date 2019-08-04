package com.example;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Adventure {

    public static void main(String[] args) throws MalformedURLException, UnirestException {
        Layout layout = new Layout();
        if (args.length > 0) {
            layout =
                    DownloadingParse.makeApiRequest(args[0]);
        } else {
            layout = DownloadingParse.makeApiRequest
                    ("https://api.myjson.com/bins/d0ebx");
        }
        play(layout);
    }


    public static void play(Layout layout) {

        boolean running = true;
        ArrayList<Item> inventory = new ArrayList<>();
        Room currentRoom = layout.getRoom()[0];
        Monster currentMonster = new Monster();
        Player currentPlayer = new Player();
        StringBuilder playerOptions = new StringBuilder();
        for (int i = 0; i < layout.getPlayer().length; i++) {
            playerOptions.append(layout.getPlayer()[i].getPlayerName() + ", ");
        }

        StringBuilder firstDirections = new StringBuilder();
        for (int i = 0; i < layout.getRoom()[0].getDirection().length; i++) {
            firstDirections.append((layout.getRoom()[0].getDirection()[i]).getDirectionName() + ",");
        }
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < layout.getRoom()[0].getItems().size(); i++) {
            items.append(layout.getRoom()[0].getItems().get(i).getItemName() + ", ");
        }
        StringBuilder monsters = new StringBuilder();
        for (int i = 0; i < layout.getRoom()[0].getMonstersInRoom().size(); i++) {
            monsters.append(layout.getRoom()[0].getMonstersInRoom().get(i) + ", ");
        }

        System.out.print("Please choose your player: " + playerOptions);
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        for (int i = 0; i < layout.getPlayer().length; i++) {
            if (userInput.equalsIgnoreCase(layout.getPlayer()[i].getPlayerName())) {
                currentPlayer = layout.getPlayer()[i];
            }
        }

        if(currentPlayer.getAttack() == 0) {
            System.out.println("Hey listen to my directions!!!!");
            System.exit(0);
        }

        System.out.println(layout.getRoom()[0].getDescription());
        System.out.println("your journey begins here");
        System.out.println("This room containts: " + items);
        System.out.println("Watch out!!! There's a " + monsters);

        double playerAttack = currentPlayer.getAttack();
        double playerDefense = currentPlayer.getDefense();
        double playerHealth = currentPlayer.getHealth();
        double maxHealth = currentPlayer.getHealth();
        int level = currentPlayer.getLevel();

        while (running) {

            userInput = in.nextLine();
            String[] inputWords = userInput.split(" ");

            if (inputWords[0].equalsIgnoreCase("exit") || inputWords[0].equalsIgnoreCase("quit")) {
                running = false;
            }

            else if (inputWords[0].equalsIgnoreCase("fight")
                    || inputWords[0].equalsIgnoreCase("duel")) {
                for (int i = 0; i < currentRoom.getMonstersInRoom().size(); i++) {
                    if (inputWords[1].equalsIgnoreCase(currentRoom.getMonstersInRoom().get(i))) {
                        String monster = inputWords[1];
                        Duel.startDuel(layout, currentRoom, currentPlayer, monster, inventory, playerAttack,
                                playerDefense, playerHealth, maxHealth, level);

                        for(int k = 0; k < layout.getMonster().length; k++) {
                            if (monster.equalsIgnoreCase(layout.getMonster()[k].getMonsterName())) {
                                currentMonster = layout.getMonster()[k];
                            }
                        }

                        // XP and level up
                        int level1 = 25;
                        int level2 = 50;
                        double levelUpPoints = 1.5;
                        double levelUpHealth = 1.3;
                        int xp = 0;
                        xp += (int) (((currentMonster.getMonsterAttack() + currentMonster.getMonsterDefense())
                                / 2) + currentMonster.getMonsterHealth()) * 20;

                        if(xp < level2 && xp >= level1 ||
                                xp >= level2 && xp < (xp*(level - 1) + xp*(level - 2)) * 1.1 ||
                                xp > (xp*(level - 1) + xp*(level - 2)) * 1.1) {
                            playerAttack *= levelUpPoints;
                            playerDefense *= levelUpPoints;
                            playerHealth = currentPlayer.getHealth() * levelUpHealth;
                            maxHealth = playerHealth;
                            xp -= level1;
                            System.out.println("Level up!");
                            level++;

                        } break;
                    }
                }
            }

            // movement code
            else if (inputWords[0].equalsIgnoreCase("go") || inputWords[0].equalsIgnoreCase("walk") ||
                    inputWords[0].equalsIgnoreCase("run")) {

                StringBuilder directionOptions = new StringBuilder();
                for (int i = 0; i < currentRoom.getDirection().length; i++) {
                    directionOptions.append((currentRoom.getDirection()[i]).getDirectionName().toLowerCase() + " ");
                }


                for (int i = 0; i < currentRoom.getDirection().length; i++) {
                    if (inputWords[1].equalsIgnoreCase(currentRoom.getDirection()[i].getDirectionName())) {

                        if (!currentRoom.getMonstersInRoom().isEmpty()) {
                            System.out.print("There are still monsters in here. I can't move");
                            break;
                        }

                        for (int j = 0; j < layout.getRoom().length; j++) {
                            if (currentRoom.getDirection()[i].getRoom().equalsIgnoreCase(layout.getRoom()[j].getName())) {
                                currentRoom = layout.getRoom()[j];
                                AdventureActions.output(currentRoom);
                                break;
                            }
                        }
                        break;
                    } else if (!directionOptions.toString().contains(inputWords[1].toLowerCase())) {
                        System.out.print("I can't go " + inputWords[1]);
                        break;
                    }
                }
                if (currentRoom.getName().equalsIgnoreCase(layout.getEndingRoom())) {
                    System.out.print("You have reached your final destination");
                    System.exit(0);
                }
            }


            // take code items
            else if (inputWords[0].equalsIgnoreCase("grab") || inputWords[0].equalsIgnoreCase("take") ||
                    inputWords[0].equalsIgnoreCase("pick")) {

                AdventureActions.grabItem(currentRoom, inputWords, currentPlayer, inventory);
            }

            // drop code items
            else if (inputWords[0].equalsIgnoreCase("leave") ||
                    inputWords[0].equalsIgnoreCase("drop")) {

                AdventureActions.dropItem(currentRoom, inputWords, currentPlayer, inventory);

            }
            else if (userInput.equalsIgnoreCase("list")) {
                StringBuilder inventoryList = new StringBuilder();
                for (int i = 0; i < inventory.size(); i++) {
                    inventoryList.append(inventory.get(i).getItemName() + ", ");
                }
                System.out.print(inventoryList);
            }
             else {
                System.out.print("I don't understand " + userInput);
            }
        }
    }
}




