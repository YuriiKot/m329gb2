package com.pride.dungeon;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.pride.dungeon.controllers.move.MoveEngine;
import com.pride.dungeon.managers.ResourceManager;
import com.pride.dungeon.model.ModelHolder;
import com.pride.dungeon.model.ModelLoader;
import com.pride.dungeon.model.Settings;
import com.pride.dungeon.view.GameView;

import java.io.IOException;

public class GameActivity extends AppCompatActivity {

    GameLoopThread gameLoop;
    static GameView gameView;
    static ModelHolder modelHolder;
    GestureDetectorCompat mGestureDetector;
    public static MoveEngine moveEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_main);
        gameView = (GameView) findViewById(R.id.gameView);
        mGestureDetector = new GestureDetectorCompat(this, new MyGestureListener());

        loadModel();
        moveEngine = new MoveEngine(modelHolder);
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        boolean detectedUp = event.getAction() == MotionEvent.ACTION_UP;
        boolean detectedDown = event.getAction() == MotionEvent.ACTION_DOWN;
        if(detectedUp)      moveEngine.unLock();
        if(detectedDown)    moveEngine.lock();
        mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void runMainLoop() {
        gameLoop = new GameLoopThread(gameView);
        gameLoop.start();
    }

    private void loadModel() {
        ResourceManager.init(getAssets());
        try {
            modelHolder = ModelLoader.loadModel(
                    getAssets().open("resources.json"),
                    getAssets().open("1.lvl"        ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //moveEngine.scrollHolder.set(distanceX, distanceY);
            //modelHolder.player.x+=distanceX;
            modelHolder.player.y+=distanceY;
            return true;
        }

       /* @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float vX, float vY) {
            if (event1.getRawX() - event2.getRawX() > gameView.getWidth() * Settings.widthFlingCoef) {
                modelHolder.player.moveTo(0, modelHolder.player.y, modelHolder);
            } else if (event2.getRawX() - event1.getRawX() > gameView.getWidth() * Settings.widthFlingCoef) {
                modelHolder.player.moveTo(modelHolder.maze.width * Settings.cellWidth, modelHolder.player.y, modelHolder);
            } else if (event1.getRawY() - event2.getRawY() > gameView.getHeight() * Settings.heightFlingCoef) {
                modelHolder.player.moveTo(modelHolder.player.x, 0, modelHolder);
            } else if (event2.getRawY() - event1.getRawY() > gameView.getHeight() * Settings.heightFlingCoef) {
                modelHolder.player.moveTo(modelHolder.player.x, modelHolder.maze.height * Settings.cellHeight, modelHolder);
            } else {
                float dx = Math.round((event2.getRawX() - event1.getRawX()) / Settings.dxForSingleCellShift) * Settings.cellWidth;
                float dy = Math.round((event2.getRawY() - event1.getRawY()) / Settings.dyForSingleCellShift) * Settings.cellHeight;

                modelHolder.player.moveTo(modelHolder.player.x + dx, modelHolder.player.y + dy, modelHolder);
            }

            return true;
        }*/


        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            float mazedx = (((event.getX() - gameView.getWidth() / 2 )) / Settings.cellWidth);
            float mazedy = (((event.getY() - gameView.getHeight() / 2)) / Settings.cellHeight);
            float xTo = (float) (Math.round(mazedx) * Settings.cellWidth + modelHolder.player.x);
            float yTo = (float) (Math.round(mazedy) * Settings.cellHeight + modelHolder.player.y);

            //modelHolder.player.moveTo(xTo, yTo, modelHolder);

            return true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            gameLoop.wait();
        }
        catch (InterruptedException e) {e.printStackTrace();}
    }

    @Override
    protected void onResume() {
        super.onResume();
        runMainLoop();
    }
}
