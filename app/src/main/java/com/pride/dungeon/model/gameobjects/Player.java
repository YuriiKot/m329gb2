package com.pride.dungeon.model.gameobjects;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.controllers.drawers.concrete.SimpleDrawer;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.controllers.updaters.concrete.EmptyUpdater;
import com.pride.dungeon.controllers.updaters.concrete.PlayerUpdater;
import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.util.Move;

public class Player extends GameObject {

    public Move curMove;
    public float speed = 5;

    public Player(float x, float y) {
        super(x, y, true);
    }

    public void moveTo(float x, float y) {
        if (curMove == null && (x != this.x || y != this.y)) {
            curMove = new Move(this.x, x, this.y, y);
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
