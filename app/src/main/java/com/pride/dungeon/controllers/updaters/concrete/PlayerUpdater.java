package com.pride.dungeon.controllers.updaters.concrete;

import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.gameobjects.Player;

public class PlayerUpdater extends AbstractUpdater {
    private static PlayerUpdater ourInstance = new PlayerUpdater();

    public static PlayerUpdater getUpdater() {
        return ourInstance;
    }

    private PlayerUpdater() {
    }

    @Override
    public void update(GameObject gameObject) {
        if (gameObject instanceof Player) {
            Player player = (Player)gameObject;
            if (player.curMove != null) {
                player.x += player.speed * Math.cos(player.curMove.angle);
                player.y += player.speed * Math.sin(player.curMove.angle);
                //If missed target position
                if ((player.curMove.xTo - player.curMove.xFrom) * (player.x - player.curMove.xTo) > 0 ||
                    (player.curMove.yTo - player.curMove.yFrom) * (player.y - player.curMove.yTo) > 0) {
                    player.x = player.curMove.xTo;
                    player.y = player.curMove.yTo;
                    player.curMove = null;
                }
            }
        }
    }
}
