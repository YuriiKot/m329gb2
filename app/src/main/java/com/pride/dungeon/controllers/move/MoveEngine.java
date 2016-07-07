package com.pride.dungeon.controllers.move;

import android.util.Log;

import com.pride.dungeon.model.ModelHolder;
import com.pride.dungeon.model.Settings;
import com.pride.dungeon.model.gameobjects.Player;

/**
 * Created by administrator on 7/5/16.
 */
public class MoveEngine {
    public ScrollHolder scrollHolder;
    public boolean locked = false;
    ModelHolder modelHolder;
    Player player;

    public MoveEngine(ModelHolder modelHolder) {
        this.modelHolder = modelHolder;
        this.player = modelHolder.player;
        scrollHolder = new ScrollHolder();
    }

    public void lock()
    {
        player.moves = null;
        Log.d("MoveEngine", "lock");
    }

    public void unLock()
    {
        int dir = (player.y % Settings.cellHeight) > (Settings.cellHeight / 2) ? 1 : -1;
        float yTo = player.y + (dir < 0 ?
                - player.y % Settings.cellHeight : Settings.cellHeight - player.y % Settings.cellHeight);
        modelHolder.player.moveTo(player.x, yTo, modelHolder);
        Log.d("unLock", "dir = " + Integer.toString(dir) + "; yTo = " +  Float.toString(yTo) );

        Log.d("MoveEngine", "unLock");
    }
}
