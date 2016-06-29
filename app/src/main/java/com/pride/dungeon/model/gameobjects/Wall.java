package com.pride.dungeon.model.gameobjects;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.controllers.drawers.concrete.SimpleDrawer;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.controllers.updaters.concrete.EmptyUpdater;
import com.pride.dungeon.model.GameObject;

public class Wall extends GameObject {
    public Wall(float x, float y, boolean transparent) {
        super(x, y, transparent);
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
