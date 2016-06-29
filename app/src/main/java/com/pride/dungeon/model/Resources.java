package com.pride.dungeon.model;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.pride.dungeon.util.Pair;

import java.io.IOException;
import java.util.Hashtable;

public class Resources {

    AssetManager assetManager;

    private Hashtable<String, Pair<String, String>> mResources;

    private Hashtable<String, Bitmap> mSprites;

    public Resources(AssetManager assetManager) {
        mResources = new Hashtable<>();
        mSprites = new Hashtable<>();
        this.assetManager = assetManager;
    }

    public void setResourceMeta(String name, String fileName, String fileType) {
        mResources.put(name, new Pair<String, String>(fileName, fileType));
    }

    public Pair<String, String> getResourceMeta(String name) {
        return mResources.get(name);
    }

    public Bitmap getSprite(String name) {
        Pair<String, String> spriteFileMeta = getResourceMeta(name);
        if (spriteFileMeta != null) {
            String fileName = spriteFileMeta.first;
            if (fileName != null) {
                Bitmap sprite = null;
                if (!mSprites.contains(fileName)) {
                    try {
                        sprite = BitmapFactory.decodeStream(assetManager.open(fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (mSprites != null)
                        mSprites.put(fileName, sprite);
                }
                return sprite;
            }
        }
        return null;
    }
}
