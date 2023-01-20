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

public class TDView extends SurfaceView implements Runnable {
    private PlayerShip player;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder ourHolder;
    volatile boolean playing;
    Thread gameThread = null;


    public TDView(Context context,int x,int y) {
        super(context);
        ourHolder = getHolder();

        paint = new Paint();

        player = new PlayerShip(context,x,y);
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
    }
    private void draw() {
        if(ourHolder.getSurface().isValid()){
            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.argb(255,0,0,0));

            canvas.drawBitmap(player.getBitmap(),player.getX(),player.getY(),paint);

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
        }catch (InterruptedException e){
        }
    }
    public void resume(){
        playing = true;
        gameThread =new Thread(this);
        gameThread.start();
    }

}
