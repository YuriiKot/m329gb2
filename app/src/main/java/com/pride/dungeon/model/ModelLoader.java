package com.pride.dungeon.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.managers.ResourceManager;
import com.pride.dungeon.model.gameobjects.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ModelLoader {
    public static ModelHolder loadModel(InputStream resourceFileStream, InputStream levelFileStream) {
        String jsondata = readJSONString(levelFileStream);

        return getModelHolder(resourceFileStream, jsondata);
    }

    @Nullable
    private static ModelHolder getModelHolder(InputStream resourceFileStream, String jsondata) {
        ModelHolder holder = null;
        try {
            holder = (new ObjectMapper()).readValue(jsondata, ModelHolder.class);
            holder.gameObjects = new ArrayList<>();
            holder.player = new Player( 100, 100);
            holder.resources = ResourceManager.loadResourcesMeta(resourceFileStream);
            AbstractDrawer.setResources(holder.resources);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return holder;
    }

    @NonNull
    private static String readJSONString(InputStream levelFileStream) {
        StringBuilder sb = new StringBuilder();
        int data = 0;
        try {
            data = levelFileStream.read();
            while (data != -1) {
                sb.append((char)data);
                data = levelFileStream.read();
            }
            levelFileStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
