package com.pride.dungeon.model.gameobjects;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.controllers.drawers.concrete.SimpleDrawer;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.controllers.updaters.concrete.EmptyUpdater;
import com.pride.dungeon.controllers.updaters.concrete.PlayerUpdater;
import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.ModelHolder;
import com.pride.dungeon.model.PlayerMoveProcessor;
import com.pride.dungeon.util.Move;

import java.util.ArrayList;

public class Player extends GameObject {

    public ArrayList<Move> moves;
    public float acceleration = 2;
    public float maxspeed = 10;
    public float speed = 0;

    public Player(float x, float y) {
        super(x, y, 48, 48, true);
    }

    public void moveTo(float x, float y, ModelHolder modelHolder) {
        if (moves == null && (x != this.x || y != this.y)) {
            Move theoreticalMove = new Move(this.x, x, this.y, y);
            moves = PlayerMoveProcessor.processMove(theoreticalMove, modelHolder);
        }
    }

    @Override
    public AbstractDrawer getDrawer() {
        return SimpleDrawer.getDrawer();
    }

    @Override
    public AbstractUpdater getUpdater() {
        return PlayerUpdater.getUpdater();
    }
}
