package com.example;

import com.google.gson.annotations.SerializedName;

public class Player {

    private String playerName;
    private double attack;
    private double defense;
    @SerializedName("Level")
    private int level;
    @SerializedName("Health")
    private int health;

    public String getPlayerName() { return playerName; }

    public double getAttack() { return attack; }

    public double getDefense() { return defense; }

    public int getLevel() { return level; }

    public int getHealth() { return health; }
}
