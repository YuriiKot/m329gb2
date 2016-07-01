package com.pride.dungeon.model;

import com.pride.dungeon.util.Move;

import java.util.ArrayList;

public class PlayerMoveProcessor {
    public static ArrayList<Move> processMove(Move move, ModelHolder holder) {
        ArrayList<Move> legitMoves = new ArrayList<>();
        legitMoves.add(move);
        if (    Math.abs(move.xTo - move.xFrom) >
                Math.abs(move.yTo - move.yFrom)) {
            move.yTo = move.yFrom;
        } else {
            move.xTo = move.xFrom;
        }
        move.calculateAngle();

        //If heading obstacle
        int mazeXTo =   (int) (move.xTo     / Settings.cellWidth);
        int mazeXFrom = (int) (move.xFrom   / Settings.cellWidth);
        int mazeYTo =   (int) (move.yTo     / Settings.cellWidth);
        int mazeYFrom = (int) (move.yFrom   / Settings.cellWidth);

        if (mazeXTo == mazeXFrom) {
            int direction = mazeYTo > mazeYFrom ? 1 : -1;
                for (int i = mazeYFrom; i != mazeYTo; i+=direction) {
                    if (!GameObjectMapper.getObjectById(holder.maze.maze[mazeXFrom][i+direction]).transparent) {
                        move.yTo = (i) * Settings.cellHeight;
                        break;
                    }
                }
        }
        else if(mazeYTo == mazeYFrom) {
            int direction = mazeXTo > mazeXFrom ? 1 : -1;
                for (int i = mazeXFrom; i != mazeXTo ; i+=direction) {
                    if (!GameObjectMapper.getObjectById(holder.maze.maze[i+direction][mazeYFrom]).transparent) {
                        move.xTo = (i) * Settings.cellWidth;
                        break;
                    }
                }
        }

        return legitMoves;
    }
}
