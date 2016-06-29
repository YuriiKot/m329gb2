package com.pride.dungeon.model.gameobjects;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.controllers.drawers.concrete.SimpleDrawer;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.controllers.updaters.concrete.EmptyUpdater;
import com.pride.dungeon.model.GameObject;

/**
 * Created by administrator on 6/29/16.
 */
public class Player extends GameObject {

    public Player(float x, float y) {
        super(x, y, true);
    }

    @Override
    public AbstractDrawer getDrawer() {
        return SimpleDrawer.getDrawer();
    }

    @Override
    public AbstractUpdater getUpdater() {
        return EmptyUpdater.getUpdater();
    }
}
