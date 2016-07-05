package com.pride.dungeon.controllers.updaters.concrete;


import com.pride.dungeon.GameActivity;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.gameobjects.Player;

public class PlayerScrollUpdater extends AbstractUpdater {
    private static PlayerScrollUpdater ourInstance = new PlayerScrollUpdater();

    public static PlayerScrollUpdater getUpdater() {
        return ourInstance;
    }


    int acceleration = -2;
    private PlayerScrollUpdater() {
    }

    @Override
    public void update(GameObject gameObject) {
        if (gameObject instanceof Player) {
            Player player = (Player)gameObject;
            if(!GameActivity.moveEngine.locked)
            {
                // ToDo do here speed = maxspeed with high acceleration < 0 for example -5
            }
        }
    }
}
