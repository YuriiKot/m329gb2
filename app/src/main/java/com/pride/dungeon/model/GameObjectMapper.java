package com.pride.dungeon.model;

import com.pride.dungeon.model.gameobjects.Floor;
import com.pride.dungeon.model.gameobjects.Wall;

public class GameObjectMapper {
    public static GameObject getObjectById(int id, float x, float y, boolean transparency) {
        GameObject gameObject = null;
        switch (id) {
            case 0: {
                gameObject = new Floor(x, y);
            } break;
            case 1: {
                gameObject = new Wall(x, y);
            } break;
        }
        return gameObject;
    }
}
