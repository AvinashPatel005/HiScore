package com.kal.hiscore;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class PlayerShip {

    private final int GRAVITY = -22;

    private int maxY,minY;
    public int score=0;
    private  int MIN_SPEED,MAX_SPEED;
    private Bitmap bitmap;
    private int x,y;
    private int speed = 0;
    private boolean boosting;
    private int i=1;



    public PlayerShip(Context context,int screenX,int screenY) {
        x=50;
        y=50;
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ship);
        maxY = screenY-bitmap.getHeight();
        minY = 0;
        speed=1;
        boosting = false;

    }

    public void update(){

        if(boosting){
            i = i+10;
            speed = speed+10;
            if(speed>100) speed = 50;
            x=x+10;
            if(x>200) x=200;
        }
        else{
            i++;
            speed = i/100;
            x=x-10;
            if(x<0) x=0;
        }
       score=i;


    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
    public void setBoosting(){
        boosting = true;
    }
    public void stopBoosting(){
        boosting = false;
    }
}
