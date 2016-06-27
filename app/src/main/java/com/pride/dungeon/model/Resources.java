package com.pride.dungeon.model;

import android.util.Pair;

import java.util.Hashtable;

public class Resources {


    private static Hashtable<String, Pair<String, String>> mResources = new Hashtable<>();

    public static void setResource(String name, String fileName, String fileType) {
        mResources.put(name, new Pair<String, String>(fileName, fileType));
    }

    public static Pair<String, String> getResource(String name) {
        return mResources.get(name);
    }
}
