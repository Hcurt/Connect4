package com.example.myapplication1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Map;

public class ImageGenerator {
    public static int[] getDrawable = {R.drawable.empty,R.drawable.red,R.drawable.yellow};
    public static ImageButton GetImage(Connect4.Player color, Context c, int row, int col){
        ImageButton imageButton = new ImageButton(c);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams(
                GridLayout.spec(row, 1, 1f),
                GridLayout.spec(col, 1, 1f)
        );
        params.width = 0;
        params.height = 0;
        params.setMargins(-20, -20, -20, -20);
        //params.setGravity(77);
        imageButton.setLayoutParams(params);
        imageButton.setScaleType(ImageButton.ScaleType.FIT_XY);
        imageButton.setImageResource(getDrawable[color.ordinal()]);
        imageButton.setBackground(null);
        return imageButton;
    }
}
