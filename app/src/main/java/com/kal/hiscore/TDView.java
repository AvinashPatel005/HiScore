package com.kal.hiscore;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

public class TDView extends SurfaceView implements Runnable {
    public ArrayList<SpaceDust> dustList = new ArrayList<SpaceDust>();
    private PlayerShip player;
    private Paint paint,paint2;
    private Canvas canvas;
    private SurfaceHolder ourHolder;
    volatile boolean playing;
    Thread gameThread = null;

   public int sx,sy;
    public TDView(Context context,int x,int y) {
        super(context);
        ourHolder = getHolder();
        sx= x; sy=y;
        paint = new Paint();

        paint2 = new Paint();
        player = new PlayerShip(context,x,y);

        int numSpecs = 40;
        for(int i = 0 ; i < numSpecs;i++){
            SpaceDust spec = new SpaceDust(x,y);
            dustList.add(spec);
        }
    }


    @Override
    public void run() {
        while(playing){
            update();
            draw();
            control();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:

                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;
        }

        return true;
    }

    private void update() {

        player.update();
        for(SpaceDust sd:dustList){
            sd.update(player.getSpeed());
        }
    }
    private void draw() {
        if(ourHolder.getSurface().isValid()){
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255,0,0,0));

            paint2.setColor(Color.argb(255,255,255,255));

            for(SpaceDust sd:dustList){
                canvas.drawCircle(sd.getX(),sd.getY(),(float)(Math.random()*2+1),paint2);
            }
            paint2.setTextSize(28);
            canvas.drawBitmap(player.getBitmap(),player.getX(),player.getY(),paint);
            canvas.drawText(String.valueOf(player.score),sx-150,100,paint2);
            ourHolder.unlockCanvasAndPost(canvas);

        }
    }
    private void control() {
        try {
            gameThread.sleep(17);
        }catch (InterruptedException e){
        }
    }

    public void pause() {
        playing =false;
        try{
            gameThread.join();
        }
        catch (InterruptedException e){
        }
    }
    public void resume(){
        playing = true;
        gameThread =new Thread(this);
        gameThread.start();
    }

}
