package com.ske.snakebaddesign.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ske.snakebaddesign.R;
import com.ske.snakebaddesign.guis.BoardView;
import com.ske.snakebaddesign.models.Board;
import com.ske.snakebaddesign.models.Player;

public class GameActivity extends AppCompatActivity {

    private int turn;

    private BoardView boardView;
    private Button buttonTakeTurn;
    private Button buttonRestart;
    private TextView textPlayerTurn;

    private Player player1;
    private Player player2;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        resetGame();
    }

    private void initComponents() {
        boardView = (BoardView) findViewById(R.id.board_view);
        buttonTakeTurn = (Button) findViewById(R.id.button_take_turn);
        buttonTakeTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn % 2 == 0)
                    takeTurn(player1);
                else
                    takeTurn(player2);
            }
        });
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
        textPlayerTurn = (TextView) findViewById(R.id.text_player_turn);


        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        setPlayerName("Player 2 Name", player2);
        setPlayerName("Player 1 Name", player1);
        board = Board.getInstance();
        board.setBoardSize(6);
    }

    private void setPlayerName(String title,Player player){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(GameActivity.this);
        alertDialog.setTitle(title);
        alertDialog.setMessage("Enter name: ");
        final EditText input = new EditText(GameActivity.this);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("ok", null);

        final Player thisPlayer = player;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        final AlertDialog setPlayerNameDialog = alertDialog.create();
        setPlayerNameDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                Button b = setPlayerNameDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        if (input.getText().toString().length() == 0) {
                            Toast.makeText(getApplicationContext(),
                                    "Please enter a name.", Toast.LENGTH_SHORT).show();
                        } else {
                            thisPlayer.setName(input.getText().toString());
                            resetGame();
                            setPlayerNameDialog.dismiss();
                        }
                    }
                });
            }
        });
        setPlayerNameDialog.show();
    }

    private void resetGame() {
        turn = 0;
        player1.setPosition(0);
        player2.setPosition(0);
        textPlayerTurn.setText(player1.getName() + "'s Turn");
        boardView.resetBoard(player1, player2);
    }

    private void takeTurn(Player player) {
        final int value = player.rollDice();//1 + new Random().nextInt(6);
        String title = player.getName()+" rolled a die";
        String msg = player.getName()+" got " + value;
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveCurrentPiece(value);
                dialog.dismiss();
            }
        };
        displayDialog(title, msg, listener);
    }

    private void moveCurrentPiece(int value) {
        if (turn % 2 == 0) {
            player1.setPosition(adjustPosition(player1.getPosition(), value));
            boardView.setP1Position(player1.getPosition());
            textPlayerTurn.setText(player1.getName() + "'s Turn");
        } else {
            player2.setPosition(adjustPosition(player2.getPosition(), value));
            boardView.setP2Position(player2.getPosition());
            textPlayerTurn.setText(player2.getName()+"'s Turn");
        }
        checkWin();
        turn++;
    }

    private int adjustPosition(int current, int distance) {
        current = current + distance;
        int maxSquare = board.getBoardSize() * board.getBoardSize() - 1;
        if(current > maxSquare) {
            current = maxSquare - (current - maxSquare);
        }
        return current;
    }

    private void checkWin() {
        String title = "Game Over";
        String msg = "";
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
                dialog.dismiss();
            }
        };
        if (player1.getPosition() == board.getBoardSize() * board.getBoardSize() - 1) {
            msg = player1.getName()+" won!";
        } else if (player2.getPosition() == board.getBoardSize() * board.getBoardSize() - 1) {
            msg = player2.getName()+" won!";
        } else {
            return;
        }
        displayDialog(title, msg, listener);
    }

    private void displayDialog(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", listener);
        alertDialog.show();
    }

}
