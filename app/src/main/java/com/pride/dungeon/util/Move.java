package com.pride.dungeon.util;

import android.util.Log;

public class Move {
    public float xFrom;
    public float xTo;
    public float yFrom;
    public float yTo;
    public float angle;

    public Move(float xFrom, float xTo, float yFrom, float yTo) {
        this.xFrom = xFrom;
        this.xTo = xTo;
        this.yFrom = yFrom;
        this.yTo = yTo;
        this.calculateAngle();
    }
    public void calculateAngle() {
        if (xTo != xFrom) {
            if (yTo < yFrom) {
                angle = -(float) Math.acos((xTo - xFrom) / Math.sqrt((xTo - xFrom) * (xTo - xFrom) + (yTo - yFrom) * (yTo - yFrom)));
            } else {
                angle = (float) Math.acos((xTo - xFrom) / Math.sqrt((xTo - xFrom) * (xTo - xFrom) + (yTo - yFrom) * (yTo - yFrom)));
            }
        }
        else
            angle = (float) Math.asin((yTo - yFrom) / Math.sqrt((xTo - xFrom) * (xTo - xFrom) + (yTo - yFrom) * (yTo - yFrom)));
    }
}
