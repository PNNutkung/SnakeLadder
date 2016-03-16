package com.ske.snakebaddesign.models;

import java.util.Random;

/**
 * Created by Pipatpol on 2559-03-14.
 * @author Pipatpol Tanavongchinda
 * @version 0.1.2
 * A singleton dice for roll in game.
 */
public class Dice {
    private static Dice die;
    private Random rand;
    private Dice(){
        rand = new Random();
    }

    /**
     * Get an instance of dice.
     * if dice does not create, it will create first.
     * @return a dice
     */
    public static Dice getInstance(){
        if(die == null)
            die = new Dice();
        return die;
    }

    /**
     * Roll a dice.
     * @return a number of face that rolled
     */
    public int roll(){
        return 1 + rand.nextInt(6);
    }

}
