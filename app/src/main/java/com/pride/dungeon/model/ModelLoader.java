package com.pride.dungeon.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.managers.ResourceManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ModelLoader {
    public static ModelHolder loadModel(InputStream resourceFileStream, InputStream levelFileStream) {
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
        String jsondata = sb.toString();

        ModelHolder holder = null;
        try {
            holder = (new ObjectMapper()).readValue(jsondata, ModelHolder.class);
            holder.gameObjects = new ArrayList<>();
            holder.resources = ResourceManager.loadResourcesMeta(resourceFileStream);
            AbstractDrawer.setResources(holder.resources);

            for (int i = 0; i < holder.maze.maze.length; i++) {
                for (int j = 0; j < holder.maze.maze[i].length; j++) {
                    holder.gameObjects.add(GameObjectMapper.getObjectById(holder.maze.maze[i][j], i * 32, j * 32, false));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return holder;
    }
}
