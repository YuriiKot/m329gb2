package com.pride.dungeon.model;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import com.pride.dungeon.util.Pair;

import java.io.IOException;
import java.util.Hashtable;

public class Resources {

    AssetManager assetManager;

    private Hashtable<String, Pair<String, String>> mResources = new Hashtable<>();

    private Hashtable<String, Bitmap> mSprites = new Hashtable<>();

    public Resources(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void setResourceMeta(String resStrId, String fileName, String fileType) {
        mResources.put(resStrId, new Pair<String, String>(fileName, fileType));
    }

    public Pair<String, String> getResourceMeta(String name) {
        return mResources.get(name);
    }

    public Bitmap getSprite(@NonNull String resStrId) {
        Pair<String, String> spriteFileMeta = getResourceMeta(resStrId);
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
