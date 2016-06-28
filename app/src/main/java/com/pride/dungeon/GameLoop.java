package com.pride.dungeon;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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
                canvas.drawColor(Color.WHITE);
                canvas.drawRect(80+x, 80+x, 120+x, 120+x, new Paint());
                x+= 4;
                gameView.getHolder().unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {

            }
        }
    }

}
