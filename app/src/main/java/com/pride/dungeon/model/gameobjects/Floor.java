package com.pride.dungeon.model.gameobjects;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.controllers.drawers.concrete.SimpleDrawer;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.controllers.updaters.concrete.EmptyUpdater;
import com.pride.dungeon.model.GameObject;

public class Floor extends GameObject {
    public Floor(float x, float y) {
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
