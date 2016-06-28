package com.pride.dungeon.controllers.drawers;

import android.graphics.Canvas;

import com.pride.dungeon.model.GameObject;

public abstract class AbstractDrawer {
    public abstract void draw(GameObject gameObject, Canvas canvas);
}
