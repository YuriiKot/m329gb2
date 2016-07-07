package com.pride.dungeon.controllers.updaters.concrete;

import android.os.Build;

import com.pride.dungeon.BuildConfig;
import com.pride.dungeon.GameActivity;
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
    public void update( GameObject gameObject) {
        if(BuildConfig.DEBUG) assert gameObject instanceof Player;
        Player player = (Player)gameObject;
        if (player.moves != null) {
            if (player.moves.size() > 0 &&
                    (player.moves.get(0).xFrom != player.moves.get(0).xTo ||
                            player.moves.get(0).yFrom != player.moves.get(0).yTo))   {
                if (player.speed < player.maxspeed) {
                    player.speed += player.acceleration;
                }
                //player.x += player.speed * Math.cos(player.moves.get(0).angle);
                player.y += player.speed * Math.sin(player.moves.get(0).angle);
                //If missed target position
                if ((player.moves.get(0).xTo - player.moves.get(0).xFrom) * (player.x - player.moves.get(0).xTo) > 0 ||
                        (player.moves.get(0).yTo - player.moves.get(0).yFrom) * (player.y - player.moves.get(0).yTo) > 0) {
                    //player.x = player.moves.get(0).xTo;
                    player.y = player.moves.get(0).yTo;
                    player.moves.remove(0);
                    player.moves = null;
                }
            } else {
                player.moves = null;
                player.speed = 0;
            }
        }

    }
}
