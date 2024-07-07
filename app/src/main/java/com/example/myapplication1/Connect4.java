package com.example.myapplication1;

import android.content.Context;
import android.widget.Toast;

public class Connect4 {
    Context c;
    boolean isActive = true;
    public enum Player{
        EMPTY,
        RED,
        YELLOW
    }
    Connect4(Context c){
        this.c = c;
    }
    Player[][] board = {
            {Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,},
            {Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,},
            {Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,},
            {Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,},
            {Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,},
            {Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,Player.EMPTY,}
    };
    Player turn = Player.YELLOW;
    //1 = red
    //2 = blue
    public boolean Play(int col){
        //Toast.makeText(c, "Game Active?: " + isActive, Toast.LENGTH_SHORT).show();
        if (!isActive) return false;
        if (col < 0 || col > board.length) return false;
        Player played = Player.RED;
        int iter = 5;
        while (played != Player.EMPTY && iter != -1) {
            played = board[iter][col];
            if(played != Player.EMPTY && iter != -1) iter--;
        }
        if (iter == -1) return false;
        else{
            board[iter][col] = turn;
            if(CheckForWin(iter, col)){
                isActive = false;
                return true;
            }
            SwitchTurn();
            return true;

        }
    }
    public void SwitchTurn(){
        turn = turn == Player.YELLOW ? Player.RED : Player.YELLOW;
    }
    public boolean CheckForWin(int row, int col){
        Player WhoWent = board[row][col];

        //straight down
        if(row+3 < 6 && board[row+1][col] == WhoWent && board[row+2][col] == WhoWent && board[row+3][col] == WhoWent) return true;

        //left
        if(col-3 >= 0 && board[row][col-1] == WhoWent && board[row][col-2] == WhoWent && board[row][col-3] == WhoWent) return true;

        //right
        if(col+3 < 7 && board[row][col+1] == WhoWent && board[row][col+2] == WhoWent && board[row][col+3] == WhoWent) return true;

        //diag, down left
        if(col-3 >= 0 && row+3 < 6 && board[row+1][col-1] == WhoWent && board[row+2][col-2] == WhoWent && board[row+3][col-3] == WhoWent) return true;

        //diag, down right
        if(col+3 < 7 && row+3 < 6 && board[row+1][col+1] == WhoWent && board[row+2][col+2] == WhoWent && board[row+3][col+3] == WhoWent) return true;

        //diag, up right
        if(col+3 < 7 && row-3 >= 0 && board[row-1][col+1] == WhoWent && board[row-2][col+2] == WhoWent && board[row-3][col+3] == WhoWent) return true;

        //diag, up left
        if(col-3 >=0 && row-3 >= 0 && board[row-1][col-1] == WhoWent && board[row-2][col-2] == WhoWent && board[row-3][col-3] == WhoWent) return true;

        //1 left, 2 right
        if(col-1 >=0 && col+2 < 7 && board[row][col-1] == WhoWent && board[row][col+1] == WhoWent && board[row][col+2] == WhoWent) return true;

        //2 left, 1 right
        if(col+1 < 7 && col-2 >=0 && board[row][col-1] == WhoWent && board[row][col+1] == WhoWent && board[row][col-2] == WhoWent) return true;

        //2 up left, 1 down right
        if((col-2 >=0 && row-2 >= 0) && (col+1 <7 && row+1<6) && board[row+1][col+1] == WhoWent && board[row-1][col-1] == WhoWent && board[row-2][col-2] == WhoWent) return true;

        //1 up left, 2 down right
        if((col-1 >=0 && row-1 >= 0) && (col+2 <7 && row+2<6) && board[row+1][col+1] == WhoWent && board[row-1][col-1] == WhoWent && board[row+2][col+2] == WhoWent) return true;

        //1 up right, 2 down left
        if((col+1 <7 && row-1 >= 0) && (col-2 >=0 && row+2<6) && board[row-1][col+1] == WhoWent && board[row+1][col-1] == WhoWent && board[row+2][col-2] == WhoWent) return true;

        //2 up right, 1 down left
        if((col+2 <7 && row-2 >= 0) && (col-1 >=0 && row+1<6) && board[row-1][col+1] == WhoWent && board[row-2][col+2] == WhoWent && board[row+1][col-1] == WhoWent) return true;
        return false;
    }

}
