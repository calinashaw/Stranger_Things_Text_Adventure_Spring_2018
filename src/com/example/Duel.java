package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Duel {

    public static void startDuel(Layout layout, Room currentRoom, Player currentPlayer, String monster, ArrayList<Item> inventory,
                                 double playerAttack, double playerDefense, double playerHealth, double maxHealth, int level) {

        Scanner in = new Scanner(System.in);
        Monster currentMonster = new Monster();
        double monsterHealth = 0.0;
        int level1 = 25;
        int level2 = 50;
        double levelUpPoints = 1.5;
        double levelUpHealth = 1.3;
        int xp = 0;
        boolean monsterAlive = true;
        boolean playerAlive = true;

        System.out.print("You are now dueling " + monster);

        StringBuilder directions = new StringBuilder();
        for(int i = 0; i < currentRoom.getDirection().length; i++) {
            directions.append(currentRoom.getDirection()[i].getDirectionName() + ", ");
        }

        for(int i = 0; i < layout.getMonster().length; i++) {
            if(monster.equalsIgnoreCase(layout.getMonster()[i].getMonsterName())) {
                currentMonster = layout.getMonster()[i];
                monsterHealth = layout.getMonster()[i].getMonsterHealth();
                break;
            }
        }

        while(monsterAlive && playerAlive) {

            String userInput = in.nextLine();
            String[] inputWords = userInput.split(" ");
            Item fightItem = new Item();

            if (userInput.equalsIgnoreCase("attack")) {
                monsterHealth -= playerAttack - currentMonster.getMonsterDefense();
            }
            else if (inputWords[0].equalsIgnoreCase("attack") &&
                    inputWords[1].equalsIgnoreCase("with")) {
                for (int i = 0; i < inventory.size(); i++) {
                    if (inputWords[2].equalsIgnoreCase(inventory.get(i).getItemName())) {
                        fightItem = inventory.get(i);
                        break;
                    }
                }
                monsterHealth -= playerAttack + fightItem.getItemDamage() - currentMonster.getMonsterDefense();

            }

            if (userInput.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            else if (userInput.equalsIgnoreCase("disengage")) {
                return;
            }

            else if (userInput.equalsIgnoreCase("status")) {

                StringBuilder statusHealth = new StringBuilder();
                StringBuilder monsterStatus = new StringBuilder();

                for (int i = 0; i < (int) ((monsterHealth / currentMonster.getMonsterHealth()) * 10); i++) {
                    monsterStatus.append("#");
                }
                for (int i = (int) ((monsterHealth / currentMonster.getMonsterHealth()) * 10); i < 10; i++) {
                    monsterStatus.append("-");
                }
                for (int i = 0; i < (int) ((playerHealth / maxHealth) * 10); i++) {
                    statusHealth.append("#");
                }
                for (int i = (int) ((playerHealth / maxHealth) * 10); i < 10; i++) {
                    statusHealth.append("-");
                }
                System.out.println("Monster Status " + monsterStatus);
                System.out.println("Your Status " + statusHealth);
            }

            else if (userInput.equalsIgnoreCase("playerinfo")) {
                System.out.println("Level " + level);
                System.out.println("Attack " + playerAttack);
                System.out.println("Defense " + playerDefense);
                System.out.println("Health " + playerHealth);
            }

            // Output and Monster attack after each player action
            playerHealth -= currentMonster.getMonsterAttack();
            System.out.println("The monster attacked! Your health is " + playerHealth);
            System.out.println("The monsters health is " + monsterHealth);

            if (playerHealth <= 0.0) {
                playerAlive = false;
                System.out.print("You have been defeated");
                System.exit(0);
            }
            if(monsterHealth <= 0.0) {
                for(int i = 0; i < currentRoom.getMonstersInRoom().size(); i++) {
                    if(monster.equalsIgnoreCase(currentRoom.getMonstersInRoom().get(i))) {
                        currentRoom.getMonstersInRoom().remove(i);
                        monsterAlive = false;
                    } break;
                }
            }
        }
        System.out.println("From here you can go: " + directions);
    }
}
