package com.ske.snakebaddesign.models;

import com.ske.snakebaddesign.guis.BoardView;

/**
 * Created by Pipatpol on 2559-03-14.
 */
public class Board {
    private BoardView boardView;
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
    public void setP1Position(int player1Pos){
        boardView.setP1Position(player1Pos);
    }

    public void setP2Position(int player2Pos){
        boardView.setP2Position(player2Pos);
    }

    public void setBoardView(BoardView boardView){
        this.boardView = boardView;
    }
}
