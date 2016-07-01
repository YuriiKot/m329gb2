package com.pride.dungeon.model.maze;

import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.GameObjectMapper;
import com.pride.dungeon.model.Settings;

import java.util.ArrayList;

public class MazeFragmentator {
    private static int lastXFrom = 0;
    private static int lastXTo = 0;
    private static int lastYFrom = 0;
    private static int lastYTo = 0;
    private static Maze lastMaze;
    private static MazeFragment lastMazeFragment;


    public static MazeFragment getMazeFragment(Maze maze, int xFrom, int xTo, int yFrom, int yTo) {
        if (!(lastMaze == maze && lastXFrom == xFrom && lastXTo == xTo && lastYFrom == yFrom && lastYTo == yTo)) {
            ArrayList<GameObject> mazeObjects = new ArrayList<>();

            lastMaze = maze;
            lastXFrom = xFrom;
            lastXTo = xTo;
            lastYFrom = yFrom;
            lastYTo = yTo;

            if (xFrom < 0)
                xFrom = 0;
            if (yFrom < 0)
                yFrom = 0;
            if (xTo >= maze.height)
                xTo = maze.height - 1;
            if (yTo >= maze.width)
                yTo = maze.width - 1;

            for (int x = xFrom; x <= xTo; x++) {
                for (int y = yFrom; y <= yTo; y++) {
                    mazeObjects.add(GameObjectMapper.getObjectById(maze.maze[x][y], x * Settings.cellWidth, y * Settings.cellHeight));
                }
            }

            lastMazeFragment = new MazeFragment(mazeObjects);

        }
        return lastMazeFragment;
    }
}
