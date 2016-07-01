package com.pride.dungeon.model.maze;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.controllers.drawers.concrete.MazeFragmentDrawer;
import com.pride.dungeon.controllers.updaters.AbstractUpdater;
import com.pride.dungeon.controllers.updaters.concrete.EmptyUpdater;
import com.pride.dungeon.model.GameObject;

import java.util.ArrayList;

public class MazeFragment extends GameObject {

    public ArrayList<GameObject> mazeObjects;

    public MazeFragment(ArrayList<GameObject> mazeObjects) {
        super(0, 0, 0, 0, true);
        this.mazeObjects = mazeObjects;
    }

    @Override
    public AbstractDrawer getDrawer() {
        return MazeFragmentDrawer.getDrawer();
    }

    @Override
    public AbstractUpdater getUpdater() {
        return EmptyUpdater.getUpdater();
    }
}
