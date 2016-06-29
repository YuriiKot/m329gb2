package com.pride.dungeon.managers;

import android.content.res.AssetManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pride.dungeon.model.Resources;
import java.io.*;
import java.util.List;

public class ResourceManager {
    private static AssetManager assetManager;
    private static class ResourseTempStructure {
        private static class ResourceChunk {
            public String name;
            public String filename;
            public String filetype;
        }
        public List<ResourceChunk> chunks;
    }
    public static void init(AssetManager assetManager) {
        ResourceManager.assetManager = assetManager;
    }
    public static Resources loadResourcesMeta(InputStream inputStream) {
        Resources resources = null;
        try {
            StringBuilder sb = new StringBuilder();
            int data = inputStream.read();
            while (data != -1) {
                sb.append((char)data);
                data = inputStream.read();
            }
            inputStream.close();
            String jsondata = sb.toString();

            ResourseTempStructure resourseTempStructure = (new ObjectMapper()).readValue(jsondata, ResourseTempStructure.class);
            resources = new Resources(assetManager);
            for (ResourseTempStructure.ResourceChunk chunk : resourseTempStructure.chunks) {
                resources.setResourceMeta(chunk.name, chunk.filename, chunk.filetype);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resources;
        }
    }
    public static Resources loadResourcesMeta(String fileName) {
        Resources resources = null;
        try {
            InputStream inputStream = assetManager.open(fileName);
            resources = loadResourcesMeta(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return resources;
        }
    }
}
