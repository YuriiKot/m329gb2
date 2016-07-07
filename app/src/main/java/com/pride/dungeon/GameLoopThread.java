package com.pride.dungeon;

import android.graphics.Canvas;
import android.util.Log;

import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.Settings;
import com.pride.dungeon.model.maze.MazeFragment;
import com.pride.dungeon.model.maze.MazeFragmentator;
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
                clearCanvas(canvas);
                canvas.translate(gameView.getWidth() / 2, gameView.getHeight() / 2);

                drawMazeFragment(canvas);
                drawAndUpdateGameObjects(canvas);
                drawAndUpdatePlayer(canvas);
                gameView.getHolder().unlockCanvasAndPost(canvas);
            }

            sleep(10);
        }
    }

    private void drawMazeFragment(Canvas canvas) {
        MazeFragment mazeFragment = MazeFragmentator.getMazeFragment(GameActivity.modelHolder.maze,
                (int)(GameActivity.modelHolder.player.x / Settings.cellWidth    - Settings.loadCellCountX),
                (int)(GameActivity.modelHolder.player.x / Settings.cellWidth    + Settings.loadCellCountX),
                (int)(GameActivity.modelHolder.player.y / Settings.cellHeight   - Settings.loadCellCountY),
                (int)(GameActivity.modelHolder.player.y / Settings.cellHeight   + Settings.loadCellCountY));
        mazeFragment.getDrawer().draw(mazeFragment, canvas, -GameActivity.modelHolder.player.x, -GameActivity.modelHolder.player.y);
    }

    private void drawAndUpdateGameObjects(Canvas canvas) {
        for (GameObject gameObject : GameActivity.modelHolder.gameObjects) {
            gameObject.getDrawer().draw(gameObject,
                    canvas,
                    GameActivity.modelHolder.player.x,
                    GameActivity.modelHolder.player.y);
            gameObject.getUpdater().update(gameObject);
        }
    }

    private void drawAndUpdatePlayer(Canvas canvas) {
        GameActivity.modelHolder.player.getDrawer().draw(GameActivity.modelHolder.player, canvas, -GameActivity.modelHolder.player.x, -GameActivity.modelHolder.player.y);
        GameActivity.modelHolder.player.getUpdater().update(GameActivity.modelHolder.player);
    }

    private void clearCanvas(Canvas canvas) {
        canvas.drawARGB(255,255,255,255);
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}
