package com.pride.dungeon;

import android.graphics.Canvas;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.managers.ResourceManager;
import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.ModelHolder;
import com.pride.dungeon.model.ModelLoader;
import com.pride.dungeon.model.Resources;
import com.pride.dungeon.model.gameobjects.Floor;
import com.pride.dungeon.model.gameobjects.Wall;
import com.pride.dungeon.view.GameView;

import java.io.IOException;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    static GameView gameView;
    static ModelHolder modelHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResourceManager.init(getAssets());
        try {
            modelHolder = ModelLoader.loadModel(getAssets().open("resources.json"), getAssets().open("1.lvl"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        GameLoop gameLoop = new GameLoop((GameView) findViewById(R.id.gameView));
        handler.postDelayed(gameLoop, 1000);


    }
}
