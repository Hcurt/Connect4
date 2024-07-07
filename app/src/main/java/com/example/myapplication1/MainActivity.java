package com.example.myapplication1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    Connect4 game = new Connect4(this);
    TextView winnerMessage;
    TextView TurnMessage;
    ImageView turnImage;
    ImageView winnerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerMessage = findViewById(R.id.winnerMessage);
        gridLayout = findViewById(R.id.gridLayout);
        gridLayout = findViewById(R.id.gridLayout);
        TurnMessage = findViewById(R.id.turn);
        turnImage = findViewById(R.id.turnImage);
        winnerImage = findViewById(R.id.winnerImage);
        // Set the row and column count for the grid

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
        setupGrid();

    }

    private void setupGrid() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                ImageButton imageButton = ImageGenerator.GetImage(Connect4.Player.EMPTY, this, row, col);
                gridLayout.addView(imageButton);
                final int finalCol = col;
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (game.Play(finalCol)) {
                            updateGrid();
                            if(!game.isActive){
                                winnerMessage.setVisibility(View.VISIBLE);
                                winnerImage.setImageResource(ImageGenerator.getDrawable[game.turn.ordinal()]);
                                winnerImage.setVisibility(View.VISIBLE);
                                TurnMessage.setVisibility(View.INVISIBLE);
                                turnImage.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                });
            }
        }
    }

    private void updateGrid() {
        turnImage.setImageResource(ImageGenerator.getDrawable[game.turn.ordinal()]);
        int index = 0;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                ImageButton button = (ImageButton) gridLayout.getChildAt(index);
                Drawable drawable = ImageGenerator.GetImage(game.board[row][col], this, row, col).getDrawable();
                button.setImageDrawable(drawable);
                index++;
            }
        }
    }
    private void resetGame(){
        game = new Connect4(this);
        winnerMessage.setVisibility(View.INVISIBLE);
        winnerImage.setVisibility(View.INVISIBLE);
        TurnMessage.setVisibility(View.VISIBLE);
        turnImage.setVisibility(View.VISIBLE);
        updateGrid();
    }
}