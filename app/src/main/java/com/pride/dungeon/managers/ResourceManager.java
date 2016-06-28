package com.pride.dungeon.managers;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.pride.dungeon.model.Resources;


import org.json.JSONObject;
import org.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceManager {
    private static class ResourseTempStructure {
        private static class ResourceChunk {
            public String name;
            public String filename;
            public String filetype;
        }
        public List<ResourceChunk> chunks;
    }
    public static void loadResources(InputStream inputStream) {
        try {
            StringBuilder sb = new StringBuilder();
            int data = inputStream.read();
            while (data != -1) {
                sb.append((char)data);
                data = inputStream.read();
            }
            String jsondata = sb.toString();

            ResourseTempStructure resourseTempStructure = (new ObjectMapper()).readValue(jsondata, ResourseTempStructure.class);
            for (ResourseTempStructure.ResourceChunk chunk : resourseTempStructure.chunks) {
                Resources.setResource(chunk.name, chunk.filename, chunk.filetype);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void loadResources(String fileName) {
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            loadResources(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
