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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(R.layout.activity_main);
        gameView = (GameView) findViewById(R.id.gameView);
        mGestureDetector = new GestureDetectorCompat(this, new MyGestureListener());

        loadModel();
    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
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
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                                float distanceY) {
            //Log.d("GameActivity","onScroll1: " + event1.toString());
            //Log.d("GameActivity","onScroll2: " + event2.toString());

        //    modelHolder.player.x += distanceX;
        //    modelHolder.player.y += distanceY;
            return true;
        }
        @Override
        public boolean onDown(MotionEvent event) {
            float mazedx = (((event.getX() - gameView.getWidth() / 2 )) / Settings.cellWidth);
            float mazedy = (((event.getY() - gameView.getHeight() / 2)) / Settings.cellHeight);
            float xTo = (float) (Math.round(mazedx) * Settings.cellWidth + modelHolder.player.x);
            float yTo = (float) (Math.round(mazedy) * Settings.cellHeight + modelHolder.player.y);

            modelHolder.player.moveTo(xTo, yTo, modelHolder);

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
