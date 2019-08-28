package com.schneideredu.tictacthor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    String winner = "";
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){
        ImageView imageWinner = findViewById(R.id.imageWinner);
        ImageView counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.thor);
                activePlayer = 1;
            } else if (activePlayer == 1) {
                counter.setImageResource(R.drawable.spiderman);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                winner = getString(R.string.has_won);
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    if (activePlayer == 1) {
                        winner = String.format(winner, "Thor");
                        imageWinner.setImageResource(R.drawable.thor);
                    } else {
                        winner = String.format(winner, "Spiderman");
                        imageWinner.setImageResource(R.drawable.spiderman);
                    }
                    Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
                    TextView txtWinnerText = findViewById(R.id.txtWinnerText);
                    txtWinnerText.setText(winner);
                    imageWinner.setVisibility(View.VISIBLE);
                    btnPlayAgain.setVisibility(View.VISIBLE);
                    txtWinnerText.setVisibility(View.VISIBLE);
                    androidx.gridlayout.widget.GridLayout board= findViewById(R.id.layoutBoard);
                    board.setVisibility(View.INVISIBLE);
                } else {
                    gameActive = false;
                    for(int counterState : gameState){
                        if(counterState == 2) gameActive = true;
                    }
                    if(!gameActive){
                        winner = String.format(winner, "Nobody");
                        imageWinner.setImageResource(R.drawable.captain_america);
                        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
                        TextView txtWinnerText = findViewById(R.id.txtWinnerText);
                        txtWinnerText.setText(winner);
                        imageWinner.setVisibility(View.VISIBLE);
                        btnPlayAgain.setVisibility(View.VISIBLE);
                        txtWinnerText.setVisibility(View.VISIBLE);
                        androidx.gridlayout.widget.GridLayout board= findViewById(R.id.layoutBoard);
                        board.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){
        ImageView imageWinner = findViewById(R.id.imageWinner);
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
        TextView txtWinnerText = findViewById(R.id.txtWinnerText);
        imageWinner.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.INVISIBLE);
        txtWinnerText.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout board = findViewById(R.id.layoutBoard);
        board.setVisibility(View.VISIBLE);
        for(int i = 0; i<board.getChildCount(); i++) {
            ImageView counter = (ImageView) board.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i<gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
