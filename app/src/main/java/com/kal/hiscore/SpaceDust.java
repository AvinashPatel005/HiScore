package com.kal.hiscore;

import java.util.Random;

public class SpaceDust {
    private int x,y;

    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;

    public SpaceDust(int screenX,int screenY) {
        maxX=screenX;
        maxY=screenY;
        minY = 0;
        minX = 0;

        Random generator = new Random();

        speed = generator.nextInt(10);

        x=generator.nextInt(maxX);
        y=generator.nextInt(maxY);
    }

    public void update(int playerSpeed){
        x-=playerSpeed;
        x-=speed;

        if(x<0){
            x=maxX;
            Random generator = new Random();
            y=generator.nextInt(maxY);
            speed = generator.nextInt(15);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
