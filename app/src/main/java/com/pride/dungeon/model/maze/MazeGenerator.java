package com.pride.dungeon.model.maze;


import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class MazeGenerator {
    
    private static int defaultRoomTries         = 20;
    private static int defaultRoomMinHeight     = 3;
    private static int defaultRoomMaxHeight     = 7;
    private static int defaultRoomMinWidth      = 3;
    private static int defaultRoomMaxWidth      = 7;
    private static int defaultRoomMinEntrances  = 1;
    private static int defaultRoomMaxEntrances  = 2;
    public enum MazeType {
        RANDOM,
        PREFER_HORIZONTAL,
        PREFER_VERTICAL
    }

    private static ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> createRooms(int[][] maze, int roomTries, int minRoomHeight, int maxRoomHeight, int minRoomWidth, int maxRoomWidth, Random random) {
        ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> rooms = new ArrayList<>();

        for (int _try = 0; _try < roomTries; _try++) {
            int roomWidth = minRoomWidth + random.nextInt(maxRoomWidth - minRoomWidth + 1);
            int roomHeight = minRoomWidth + random.nextInt(maxRoomHeight - minRoomHeight + 1);
            int left = random.nextInt(maze[0].length - roomWidth - 1) + 1;
            int top = random.nextInt(maze.length - roomHeight - 1) + 1;
            boolean flag = true;
            for (int i = left - 1; i < left + roomWidth + 1; i++) {
                for (int j = top - 1; j < top + roomHeight + 1; j++) {
                    if (maze[j][i] == 0) {
                        flag = false;
                    }
                }
            }
            if (flag) {
                rooms.add(new Pair<>(new Pair<Integer, Integer>(left, top), new Pair<Integer, Integer>(roomWidth, roomHeight)));
                for (int i = left; i < left + roomWidth; i++) {
                    for (int j = top; j < top + roomHeight; j++) {
                        maze[j][i] = 0;
                    }
                }
            }
        }
        return rooms;
    }
    private static boolean carve(int[][] maze, int x, int y, MazeType mazeType, Random random) {
        carve(maze, x, y, mazeType, random, -1);
        return false;
    }
    private static boolean carve(int[][] maze, int x, int y, MazeType mazeType, Random random, int lastDir) {
        ArrayList<Integer> funList = new ArrayList<>();
        switch (mazeType) {
            case PREFER_HORIZONTAL: {
                funList.add(3);
                funList.add(2);
                funList.add(1);
                funList.add(0);
            }
            break;
            case PREFER_VERTICAL: {
                funList.add(0);
                funList.add(1);
                funList.add(2);
                funList.add(3);
            }
            break;
            case RANDOM: {
                funList.add(0);
                funList.add(1);
                funList.add(2);
                funList.add(3);
                Collections.shuffle(funList, random);
            }
            break;
        }
        for (int fun : funList) {
            int shiftx = (fun / 2 == 0) ? (fun % 2) * 2 - 1 : 0;
            int shifty = (fun / 2 == 1) ? (fun % 2) * 2 - 1 : 0;
            int newx = x + 2 * shiftx;
            int newy = y + 2 * shifty;
            int newxside1 = x + (fun % 2) * 2 - 1;
            int newyside1 = y + -((fun / 2 + fun % 2) % 2) * 2 + 1;
            int newxside2 = x + ((fun / 2 + fun % 2) % 2) * 2 - 1;
            int newyside2 = y + (fun % 2) * 2 - 1;
            int newxdiag1 = x + ((fun / 2 == 0) ? 2 * ((fun % 2) * 2 - 1) : ((fun / 2 + fun % 2) % 2) * 2 - 1);
            int newydiag1 = y + ((fun / 2 == 1) ? 2 * ((fun % 2) * 2 - 1) : (fun % 2) * 2 - 1);
            int newxdiag2 = x + ((fun / 2 == 0) ? 2 * ((fun % 2) * 2 - 1) : (fun % 2) * 2 - 1);
            int newydiag2 = y + ((fun / 2 == 1) ? 2 * ((fun % 2) * 2 - 1) : -((fun / 2 + fun % 2) % 2) * 2 + 1);
            if ((!(fun % 2 != lastDir % 2 && fun / 2 == lastDir / 2) || lastDir == -1) && newx > 0 && newy > 0 && newx < maze.length - 1 && newy < maze[x].length - 1 && maze[newx][newy] * maze[newxside1][newyside1] * maze[newxside2][newyside2] * maze[newxdiag1][newydiag1] * maze[newxdiag2][newydiag2] != 0 ) {
                maze[x + shiftx][y + shifty] = 0;
                carve(maze, x + shiftx, y + shifty, mazeType, random, fun);
            }
        }
        return false;
    }
    private static void connectRoomsToCorridors(int[][] maze, ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> rooms, int minRoomEntrances, int maxRoomEntrances, Random random) {
        for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> room : rooms) {
            int left = room.first.first;
            int top = room.first.second;
            int roomWidth = room.second.first;
            int roomHeight = room.second.second;

            ArrayList<Pair<Integer, Integer>> entrances = new ArrayList<>();
            if (left > 1) {
                for (int i = top; i < top + roomHeight; i++) {
                    if (maze[i][left - 2] == 0) {
                        entrances.add(new Pair<>(left - 1, i));
                    }
                }
            }
            if (left + roomWidth < maze[0].length - 1) {
                for (int i = top; i < top + roomHeight; i++) {
                    if (maze[i][left + roomWidth + 1] == 0) {
                        entrances.add(new Pair<>(left + roomWidth, i));
                    }
                }
            }
            if (top > 1) {
                for (int i = left; i < left + roomWidth; i++) {
                    if (maze[top - 2][i] == 0) {
                        entrances.add(new Pair<>(i, top - 1));
                    }
                }
            }
            if (top + roomHeight < maze.length - 1) {
                for (int i = left; i < left + roomWidth; i++) {
                    if (maze[top + roomHeight + 1][i] == 0) {
                        entrances.add(new Pair<>(i, top + roomHeight));
                    }
                }
            }
            Collections.shuffle(entrances, random);
            int entranceCount = minRoomEntrances + random.nextInt(maxRoomEntrances - minRoomEntrances + 1);
            for (int _entr = 0; _entr < entranceCount && _entr < entrances.size(); _entr++) {
                maze[entrances.get(_entr).second][entrances.get(_entr).first] = 0;
            }
        }
    }
    public static int[][] getMaze(int width, int height, MazeType mazeType, int seed) {
        return getMaze(width, height, mazeType, defaultRoomTries, defaultRoomMinHeight, defaultRoomMaxHeight, defaultRoomMinWidth, defaultRoomMaxWidth, seed);
    }
    public static int[][] getMaze(int width, int height, MazeType mazeType, int roomTries, int minRoomHeight, int maxRoomHeight, int minRoomWidth, int maxRoomWidth, int seed) {
        return getMaze(width, height, mazeType, roomTries, minRoomHeight, maxRoomHeight, minRoomWidth, maxRoomWidth, defaultRoomMinEntrances, defaultRoomMaxEntrances, seed);
    }
    public static int[][] getMaze(int width, int height, MazeType mazeType, int roomTries, int minRoomHeight, int maxRoomHeight, int minRoomWidth, int maxRoomWidth, int minRoomEntrances, int maxRoomEntrances, int seed) {
        Random random = new Random(seed);
        int[][] maze = new int[height][width];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = 1;
            }
        }

        ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> rooms = createRooms(maze, roomTries, minRoomHeight, maxRoomHeight, minRoomWidth, maxRoomWidth, random);

        for (int i = 1; i < maze.length - 1; i++) {
            for (int j = 1; j < maze[i].length - 1; j++) {
                if (maze[i][j] * maze[i - 1][j] * maze[i][j - 1] * maze[i - 1][j - 1] * maze[i + 1][j] * maze[i][j + 1] * maze[i + 1][j + 1] * maze[i - 1][j + 1] * maze[i + 1][j - 1] != 0)
                    carve(maze, i, j, mazeType, random);
            }
        }

        connectRoomsToCorridors(maze, rooms, minRoomEntrances, maxRoomEntrances, random);

        return maze;
    }
}