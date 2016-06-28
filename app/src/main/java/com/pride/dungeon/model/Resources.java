package com.pride.dungeon.model;

import com.pride.dungeon.util.Pair;

import java.util.Hashtable;

public class Resources {

    private Hashtable<String, Pair<String, String>> mResources;

    public Resources() {
        mResources = new Hashtable<>();
    }

    public void setResource(String name, String fileName, String fileType) {
        mResources.put(name, new Pair<String, String>(fileName, fileType));
    }

    public Pair<String, String> getResource(String name) {
        return mResources.get(name);
    }
}
