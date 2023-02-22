package com.hspedu.tankgame5;

import java.util.Vector;

/**
 * @Author Agony
 * @Create 2023/2/15 20:41
 * @Version 1.0
 */
public class MyTank extends Tank {

    Shot shot = null;

    Vector<Shot> shots = new Vector<>();

    public MyTank(int x, int y) {
        super(x, y);
    }

    public void shotTank() {
        switch (getDirection()) {
            case 0 -> shot = new Shot(getX() + 20, getY(), 0);
            case 1 -> shot = new Shot(getX() + 50, getY() + 30, 1);
            case 2 -> shot = new Shot(getX() + 20, getY() + 60, 2);
            case 3 -> shot = new Shot(getX() - 10, getY() + 30, 3);

        }
        shots.add(shot);

        new Thread(shot).start();
    }

}
