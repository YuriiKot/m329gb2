package com.pride.dungeon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.maze.MazeFragment;
import com.pride.dungeon.model.maze.MazeFragmentator;
import com.pride.dungeon.util.Pair;
import com.pride.dungeon.view.GameView;


public class GameLoopThread extends Thread {
    GameView gameView;
    int x = 10;

    public GameLoopThread(GameView gv)
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
                canvas.drawARGB(255,255,255,255);
                canvas.translate(gameView.getWidth() / 2, gameView.getHeight() / 2);

                MazeFragment mazeFragment = MazeFragmentator.getMazeFragment(GameActivity.modelHolder.maze,
                        (int)(GameActivity.modelHolder.player.x / 64 - 4),
                        (int)(GameActivity.modelHolder.player.x / 64 + 4),
                        (int)(GameActivity.modelHolder.player.y / 64 - 4),
                        (int)(GameActivity.modelHolder.player.y / 64 + 4));
                mazeFragment.getDrawer().draw(mazeFragment, canvas, -GameActivity.modelHolder.player.x, -GameActivity.modelHolder.player.y);
                for (GameObject gameObject : GameActivity.modelHolder.gameObjects) {
                    gameObject.getDrawer().draw(gameObject,
                            canvas,
                            GameActivity.modelHolder.player.x,
                            GameActivity.modelHolder.player.y);
                    gameObject.getUpdater().update(gameObject);
                }
                gameView.getHolder().unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {

            }
        }
    }

}
