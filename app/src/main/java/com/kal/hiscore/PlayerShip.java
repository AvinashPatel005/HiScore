package com.kal.hiscore;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class PlayerShip {

    private final int GRAVITY = -22;

    private int maxY,minY;

    private final int MIN_SPEED=1,MAX_SPEED=50;
    private Bitmap bitmap;
    private int x,y;
    private int speed = 0;
    private boolean boosting;

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
            speed +=2;
        }else{
            speed-=5;
        }

        if(speed>MAX_SPEED){
            speed = MAX_SPEED;
        }
        if(speed<MIN_SPEED){
            speed = MIN_SPEED;
        }
        y -= speed+GRAVITY;

        if(y<minY){
            y=minY;
        }
        if (y>maxY){
            y=maxY;
        }
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
