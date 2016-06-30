package com.pride.dungeon.controllers.drawers.concrete;

import android.graphics.Canvas;

import com.pride.dungeon.controllers.drawers.AbstractDrawer;
import com.pride.dungeon.model.GameObject;
import com.pride.dungeon.model.maze.MazeFragment;

public class MazeFragmentDrawer extends AbstractDrawer {

    private static MazeFragmentDrawer ourInstance = new MazeFragmentDrawer();

    public static MazeFragmentDrawer getDrawer() {
        return ourInstance;
    }

    @Override
    public void draw(GameObject gameObject, Canvas canvas, float dx, float dy) {
        if (gameObject instanceof MazeFragment) {
            MazeFragment fragment = (MazeFragment) gameObject;
            for (GameObject mazeObject : fragment.mazeObjects) {
                mazeObject.getDrawer().draw(mazeObject, canvas, dx, dy);
            }
        }
    }
}
