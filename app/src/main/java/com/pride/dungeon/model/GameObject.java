package com.pride.dungeon.model;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;

public abstract class GameObject {
    public float x;
    public float y;
    public int mazeX;
    public int mazeY;
    public float hitboxWidth;
    public float hitboxHeight;
    public boolean transparent;

    public GameObject(float x, float y, float hitboxWidth, float hitboxHeight, boolean transparent) {
        this.x = x;
        this.y = y;
        this.hitboxWidth = hitboxWidth;
        this.hitboxHeight = hitboxHeight;
        this.transparent = transparent;
    }

    public abstract AbstractDrawer getDrawer();
    public abstract AbstractUpdater getUpdater();
}
