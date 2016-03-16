package com.ske.snakebaddesign.models;

/**
 * Created by Pipatpol on 2559-03-14.
 */
public class Board {

    private static Board board;
    private int boardSize;

    private Board(){
        this.boardSize = 1;
    }

    public static Board getInstance(){
        if(board == null)
            board = new Board();
        return board;
    }
    public int getBoardSize(){
        return this.boardSize;
    }
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }



}
