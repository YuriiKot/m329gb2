package com.pride.dungeon.managers;
import com.pride.dungeon.model.Resources;


import org.json.JSONObject;
import org.json.JSONArray;

import java.io.*;
import java.util.Iterator;

public class ResourceManager {
    public static void loadResources(InputStream inputStream) {
        try {
            StringBuilder sb = new StringBuilder();
            int data = inputStream.read();
            while (data != -1) {
                sb.append((char)data);
                data = inputStream.read();
            }
            String jsondata = sb.toString();

            JSONArray jsonArr = new JSONArray(jsondata);
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject row = jsonArr.getJSONObject(i);
                Iterator<String> iterator = row.keys();
                while (iterator.hasNext()) {
                    String name = iterator.next();
                    JSONArray array = (JSONArray) row.get(name);
                    Resources.setResource(name, (String)array.get(0), (String)array.get(1));
                }
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
