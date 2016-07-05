package com.pride.dungeon.controllers.drawers.concrete;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Rect;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.Settings;
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
        spriteMap.put(Player.class  , resources.getSprite("player_sprite")    );
    }

    @Override
    public void draw(GameObject gameObject, Canvas canvas, float dx, float dy) {
        Bitmap sprite = spriteMap.get(gameObject.getClass());
        if (sprite != null) {
            Rect bitmapRect = new Rect(0, 0, sprite.getWidth(), sprite.getHeight());
            Rect drawRect = new Rect((int)(gameObject.x + dx - Settings.cellWidth / 2),
                    (int)(gameObject.y + dy - Settings.cellWidth / 2),
                    (int)(gameObject.x + dx + Settings.cellHeight / 2),
                    (int)(gameObject.y + dy + Settings.cellHeight / 2));
            canvas.drawBitmap(sprite, bitmapRect, drawRect, null);
        }
    }
}
