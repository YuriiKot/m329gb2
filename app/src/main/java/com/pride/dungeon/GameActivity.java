package com.pride.dungeon;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;

import com.pride.dungeon.view.GameView;

public class GameActivity extends AppCompatActivity {

    static GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = (GameView) findViewById(R.id.gameView);
        Handler handler = new Handler();
        GameLoop gameLoop = new GameLoop((GameView) findViewById(R.id.gameView));
        handler.postDelayed(gameLoop, 1000);


    }
}
