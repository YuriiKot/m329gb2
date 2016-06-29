package com.pride.dungeon.controllers.drawers.concrete;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.gameobjects.Floor;
import com.pride.dungeon.model.gameobjects.Player;
import com.pride.dungeon.model.gameobjects.Wall;

import java.util.Hashtable;

public class SimpleDrawer extends AbstractDrawer {

    private Hashtable<Class<? extends GameObject>, Bitmap> spriteMap;

    private static SimpleDrawer ourInstance = new SimpleDrawer();

    public static SimpleDrawer getDrawer() {
        return ourInstance;
    }

    private SimpleDrawer() {
        spriteMap = new Hashtable<>();
        spriteMap.put(Wall  .class  , resources.getSprite("wall_sprite")    );
        spriteMap.put(Floor .class  , resources.getSprite("floor_sprite")   );
        spriteMap.put(Player.class  , resources.getSprite("wall_sprite")    );
    }

    @Override
    public void draw(GameObject gameObject, Canvas canvas, float dx, float dy) {
        Bitmap sprite = spriteMap.get(gameObject.getClass());
        if (sprite != null) {
            canvas.drawBitmap(sprite,
                    gameObject.x + dx,
                    gameObject.y + dy,
                    null);
        }
    }
}
