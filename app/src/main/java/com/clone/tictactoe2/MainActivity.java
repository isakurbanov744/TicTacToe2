package com.clone.tictactoe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[][] buttons = new Button[3][3];

    boolean playerOneTurn = true;
    int playerOnePoints;
    int playerTwoPoints;
    int drawPoints;
    int roundCount;

    TextView textViewPlayerOne;
    TextView textViewPlayerTwo;
    TextView textViewDrawPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayerOne = findViewById(R.id.text_view_p1);
        textViewPlayerTwo = findViewById(R.id.text_view_p2);
        textViewDrawPoints = findViewById(R.id.draw);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        Button buttonO = findViewById(R.id.button_00);
    }


    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (playerOneTurn) {
            ((Button) v).setText("X");
        }
        else {
            ((Button) v).setText("O");
        }

        ++roundCount;

        if (winCheck()) {
            if (playerOneTurn) {
                winPlayerOne();
            }
            else {
                winPlayerTwo();
            }
        }
        else if (roundCount == 9) {
            draw();
        }
        else {
            playerOneTurn = !playerOneTurn;
        }
    }


    public boolean winCheck() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; ++i) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; ++i) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }


    public void winPlayerOne() {
        ++playerOnePoints;
        Toast.makeText(this, "Player One Wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    public void winPlayerTwo() {
        ++playerTwoPoints;
        Toast.makeText(this, "Player Two Wins", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }


    public void draw() {
        ++drawPoints;
        Toast.makeText(this, "It is a draw", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }


    public void updatePointsText() {
        textViewPlayerTwo.setText("Player 2: " + playerTwoPoints);
        textViewPlayerOne.setText("Player 1: " + playerOnePoints);
        textViewDrawPoints.setText("Draw: " + drawPoints);
    }


    public void resetBoard() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        playerOneTurn = true;
    }


    public void resetGame() {
        drawPoints = 0;
        playerOnePoints = 0;
        playerTwoPoints = 0;
        updatePointsText();
        resetBoard();
    }
}