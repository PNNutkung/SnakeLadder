package com.ske.snakebaddesign.models;

/**
 * Created by Pipatpol on 2559-03-14.
 * @author Pipatpol Tanavongchinda
 * @version 0.1.1
 */
public class Player {
    private Dice die;
    private String name;
    private int position;
    public Player(String name){
        die = Dice.getInstance();
        this.name = name;
        this.position = 0;
    }

    public int rollDice(){
        return die.roll();
    }

    public String getName() {
        return name;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }
}
