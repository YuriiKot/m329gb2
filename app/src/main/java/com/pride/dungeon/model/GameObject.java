package com.pride.dungeon.model;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;

public abstract class GameObject {
    public float x;
    public float y;
    public int mazeX;
    public int mazeY;
    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public abstract AbstractDrawer getDrawer();
    public abstract AbstractUpdater getUpdater();
}
