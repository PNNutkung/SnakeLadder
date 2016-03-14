package com.ske.snakebaddesign.models;

import java.util.Random;

/**
 * Created by Pipatpol on 2559-03-14.
 * @author Pipatpol Tanavongchinda
 * @version 0.1.1
 */
public class Dice {
    private int face;
    public Dice(){
        face = 0;
    }

    public int roll(){
        return 1 + new Random().nextInt(5);
    }

}
