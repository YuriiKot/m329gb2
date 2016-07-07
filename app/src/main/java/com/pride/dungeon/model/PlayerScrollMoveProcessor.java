package com.pride.dungeon.model;

import com.pride.dungeon.util.Move;

import java.util.ArrayList;

public class PlayerScrollMoveProcessor {
    public static ArrayList<Move> processMove(Move move, ModelHolder holder) {
        ArrayList<Move> legitMoves = new ArrayList<>();
        legitMoves.add(move);

        //move.calculateAngle();

        return legitMoves;
    }
}
