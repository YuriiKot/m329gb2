package com.pride.dungeon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pride.dungeon.model.gameobjects.Player;
import com.pride.dungeon.model.maze.Maze;

import java.util.ArrayList;

public class ModelHolder {
    @JsonIgnore
    public Resources resources;
    @JsonIgnore
    public ArrayList<GameObject> gameObjects;
    public Maze maze;
    public Player player;
}
