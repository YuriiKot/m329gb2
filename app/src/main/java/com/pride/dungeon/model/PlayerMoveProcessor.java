package com.pride.dungeon.model;

import com.pride.dungeon.util.Move;

import java.util.ArrayList;

public class PlayerMoveProcessor {
    public static ArrayList<Move> processMove(Move move, ModelHolder holder) {
        ArrayList<Move> legitMoves = new ArrayList<>();
        legitMoves.add(move);
        if (Math.abs(move.xTo - move.xFrom) > Math.abs(move.yTo - move.yFrom)) {
            move.yTo = move.yFrom;
        } else {
            move.xTo = move.xFrom;
        }
        move.calculateAngle();

        //If heading obstacle
        int mazeXTo = (int) (move.xTo / Settings.cellWidth);
        int mazeXFrom = (int) (move.xFrom / Settings.cellWidth);
        int mazeYTo = (int) (move.yTo / Settings.cellWidth);
        int mazeYFrom = (int) (move.yFrom / Settings.cellWidth);

        if (mazeXTo == mazeXFrom) {
            for (int i = mazeYFrom; i != mazeYTo + (mazeYTo > mazeYFrom ? 1 : -1); i += mazeYTo > mazeYFrom ? 1 : -1) {
                if (!GameObjectMapper.getObjectById(holder.maze.maze[mazeXFrom][i]).transparent) {
                    move.yTo = (i - (mazeYTo > mazeYFrom ? 1 : -1)) * 64;
                    break;
                }
            }
        } else {

        }

        return legitMoves;
    }
}
