package com.pride.dungeon.controllers.drawers.concrete;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.model.GameObject;

import java.util.Hashtable;

public class SimpleDrawer extends AbstractDrawer {

    private Hashtable<Class<? extends GameObject>, Bitmap> spriteMap;

    private static SimpleDrawer ourInstance = new SimpleDrawer();

    public static SimpleDrawer getDrawer() {
        return ourInstance;
    }

    private SimpleDrawer() {
        spriteMap = new Hashtable<>();
        //Fill map with records
    }

    @Override
    public void draw(GameObject gameObject, Canvas canvas) {
        Bitmap sprite = spriteMap.get(gameObject.getClass());
        if (sprite != null) {
            Paint paint = null;
            canvas.drawBitmap(sprite, gameObject.x, gameObject.y, paint);
        }
    }
}