package com.pride.dungeon.controllers.drawers;

import android.graphics.Canvas;

import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.Resources;

public abstract class AbstractDrawer {
    protected static Resources resources;
    public static void setResources(Resources resources) {
        AbstractDrawer.resources = resources;
    }
    public abstract void draw(GameObject gameObject, Canvas canvas, float dx, float dy);
}
