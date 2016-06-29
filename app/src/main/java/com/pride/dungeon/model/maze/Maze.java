package com.pride.dungeon.model.maze;

public class Maze {
    public int[][] maze;
    public int width;
    public int height;
    public Maze(){}
    public Maze(int[][] maze) {
        this.maze = maze;
        this.width = maze[0].length;
        this.height = maze.length;
    }
    public Maze(int[][] maze, int width, int height) {
        this.maze = maze;
        this.width = width;
        this.height = height;
    }
}

