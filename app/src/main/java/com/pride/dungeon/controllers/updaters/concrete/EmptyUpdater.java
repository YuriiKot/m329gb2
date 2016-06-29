package com.pride.dungeon.controllers.updaters.concrete;

import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.model.GameObject;

public class EmptyUpdater extends AbstractUpdater {
    private static EmptyUpdater ourInstance = new EmptyUpdater();

    public static EmptyUpdater getUpdater() {
        return ourInstance;
    }

    private EmptyUpdater() {
    }

    @Override
    public void update(GameObject gameObject) {
    }
}
