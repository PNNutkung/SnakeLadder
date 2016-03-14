package com.ske.snakebaddesign.models;

/**
 * Created by Pipatpol on 2559-03-14.
 * @author Pipatpol Tanavongchinda
 * @version 0.1.1
 */
public class Player {
    private Dice die;
    private String name;
    public Player(String name){
        die = new Dice();
        this.name = name;
    }

    public int rollDice(){
        return die.roll();
    }

    public String getName() {
        return name;
    }
}
