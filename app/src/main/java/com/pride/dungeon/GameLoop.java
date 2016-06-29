package com.pride.dungeon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.view.GameView;


public class GameLoop implements Runnable {
    GameView gameView;
    int x = 10;
    public GameLoop(GameView gv)
    {
        gameView = gv;
    }

    @Override
    public void run() {
        while (true)
        {
            Canvas canvas = gameView.getHolder().lockCanvas(null);

            if (canvas != null)
            {
                for (GameObject gameObject : GameActivity.modelHolder.gameObjects) {
                    gameObject.getDrawer().draw(gameObject, canvas);
                    gameObject.getUpdater().update(gameObject);
                }
                gameView.getHolder().unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {

            }
        }
    }

}
