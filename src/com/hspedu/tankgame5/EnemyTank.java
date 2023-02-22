package com.hspedu.tankgame5;

import java.util.Vector;

/**
 * @Author Agony
 * @Create 2023/2/16 14:35
 * @Version 1.0
 */
public class EnemyTank extends Tank implements Runnable {

    boolean isLive = true;
    Vector<Shot> shots = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    Vector<EnemyTank> enemyTanks = new Vector<>();

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public boolean isTouch() {

        switch (getDirection()) {

            // 坦克朝上
            case 0 -> {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);

                    // 不和自己比较

                    if (enemyTank != this) {
                        switch (enemyTank.getDirection()) {

                            // 敌人坦克上/下情况
                            // x范围 [enemyTank.getX(), enemyTank.getX() + 40]
                            // y范围 [enemyTank.getY(), enemyTank.getY() + 60]

                            // 当前坦克左上角坐标 [getX(), getY()]
                            // 当前坦克右上角坐标 [getX() + 40, getY()]
                            case 0, 2 -> {

                                // 左上角是否在范围内
                                if (getX() >= enemyTank.getX()
                                        && getX() <= enemyTank.getX() + 40
                                        && getY() >= enemyTank.getY()
                                        && getY() <= enemyTank.getY() + 60) {
                                    return true;
                                }
                                // 右上角是否在范围内
                                if (getX() + 40 >= enemyTank.getX()
                                        && getX() + 40 <= enemyTank.getX() + 40
                                        && getY() >= enemyTank.getY()
                                        && getY() <= enemyTank.getY() + 60) {
                                    return true;
                                }

                            }
                            // 敌人坦克左/右情况
                            // x范围 [enemyTank.getX(), enemyTank.getX() + 60]
                            // y范围 [enemyTank.getY(), enemyTank.getY() + 40]

                            // 当前坦克左上角坐标 [getX(), getY()]
                            // 当前坦克右上角坐标 [getX() + 40, getY()]
                            case 1, 3 -> {
                                // 左上角是否在范围内
                                if (getX() >= enemyTank.getX()
                                        && getX() <= enemyTank.getX() + 60
                                        && getY() >= enemyTank.getY()
                                        && getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                // 左上角是否在范围内
                                if (getX() + 40 >= enemyTank.getX()
                                        && getX() + 40 <= enemyTank.getX() + 60
                                        && getY() >= enemyTank.getY()
                                        && getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }

                            }
                        }
                    }


                }
            }
            // 坦克朝右
            case 1 -> {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        switch (enemyTank.getDirection()) {

                            // 敌人坦克上/下情况
                            // x范围 [enemyTank.getX(), enemyTank.getX() + 40]
                            // y范围 [enemyTank.getY(), enemyTank.getY() + 60]

                            // 当前坦克右上角坐标 [getX() + 60, getY()]
                            // 当前坦克右下角坐标 [getX() + 60, getY() + 40]
                            case 0, 2 -> {

                                // 右上角是否在范围内
                                if (getX() + 60 >= enemyTank.getX()
                                        && getX() + 60 <= enemyTank.getX() + 40
                                        && getY() >= enemyTank.getY()
                                        && getY() <= enemyTank.getY() + 60) {
                                    return true;
                                }
                                // 右下角是否在范围内
                                if (getX() + 60 >= enemyTank.getX()
                                        && getX() + 60 <= enemyTank.getX() + 40
                                        && getY() + 40 >= enemyTank.getY()
                                        && getY() + 40 <= enemyTank.getY() + 60) {
                                    return true;
                                }
                            }
                            // 敌人坦克左/右情况
                            // x范围 [enemyTank.getX(), enemyTank.getX() + 60]
                            // y范围 [enemyTank.getY(), enemyTank.getY() + 40]

                            // 当前坦克右上角坐标 [getX() + 60, getY()]
                            // 当前坦克右下角坐标 [getX() + 60, getY() + 40]
                            case 1, 3 -> {

                                // 右上角是否在范围内
                                if (getX() + 60 >= enemyTank.getX()
                                        && getX() + 60 <= enemyTank.getX() + 60
                                        && getY() >= enemyTank.getY()
                                        && getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                // 右下角是否在范围内
                                if (getX() + 60 > enemyTank.getX()
                                        && getX() + 60 <= enemyTank.getX() + 60
                                        && getY() + 40 >= enemyTank.getY()
                                        && getY() + 40 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            // 坦克朝下
            case 2 -> {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (this != enemyTank) {
                        switch (enemyTank.getDirection()) {

                            // 敌人坦克上/下情况
                            // x范围 [enemyTank.getX(), enemyTank.getX() + 40]
                            // y范围 [enemyTank.getY(), enemyTank.getY() + 60]

                            // 当前坦克左下角坐标 [getX(), getY() + 60]
                            // 当前坦克右下角坐标 [getX() + 40, getY() + 60]
                            case 0, 2 -> {

                                // 左下角否在范围内
                                if (getX() >= enemyTank.getX()
                                        && getX() <= enemyTank.getX() + 40
                                        && getY() + 60 >= enemyTank.getY()
                                        && getY() + 60 <= enemyTank.getY() + 60) {
                                    return true;
                                }

                                // 右下角是否在范围内
                                if (getX() + 40 >= enemyTank.getX()
                                        && getX() + 40 <= enemyTank.getX() + 40
                                        && getY() + 60 >= enemyTank.getY()
                                        && getY() + 60 <= enemyTank.getY() + 60) {
                                    return true;
                                }
                            }
                            // 敌人坦克左/右情况
                            // x范围 [enemyTank.getX(), enemyTank.getX() + 60]
                            // y范围 [enemyTank.getY(), enemyTank.getY() + 40]

                            // 当前坦克左下角坐标 [getX(), getY() + 60]
                            // 当前坦克右下角坐标 [getX() + 40, getY() + 60]
                            case 1, 3 -> {

                                // 左下角是否在范围内
                                if (getX() >= enemyTank.getX()
                                        && getX() <= enemyTank.getX() + 60
                                        && getY() + 60 >= enemyTank.getY()
                                        && getY() + 60 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                // 右下角是否在范围内
                                if (getX() + 40 >= enemyTank.getX()
                                        && getX() + 40 <= enemyTank.getX() + 60
                                        && getY() + 60 >= enemyTank.getY()
                                        && getY() + 60 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            // 朝左
            case 3 -> {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (this != enemyTank) {
                        switch (enemyTank.getDirection()) {

                            // 敌人坦克上/下情况
                            // x范围 [enemyTank.getX(), enemyTank.getX() + 40]
                            // y范围 [enemyTank.getY(), enemyTank.getY() + 60]

                            // 当前坦克左上角坐标 [getX(), getY()]
                            // 当前坦克左下角坐标 [getX(), getY() + 40]

                            case 0, 2 -> {

                                // 左上角否在范围内
                                if (getX() >= enemyTank.getX()
                                        && getX() <= enemyTank.getX() + 40
                                        && getY() >= enemyTank.getY()
                                        && getY() <= enemyTank.getY() + 60) {
                                    return true;
                                }

                                // 左下角是否在范围内
                                if (getX() >= enemyTank.getX()
                                        && getX() <= enemyTank.getX() + 40
                                        && getY() + 40 >= enemyTank.getY()
                                        && getY() + 40 <= enemyTank.getY() + 60) {
                                    return true;
                                }
                            }
                            // 敌人坦克左/右情况
                            // x范围 [enemyTank.getX(), enemyTank.getX() + 60]
                            // y范围 [enemyTank.getY(), enemyTank.getY() + 40]

                            // 当前坦克左上角坐标 [getX(), getY()]
                            // 当前坦克左下角坐标 [getX(), getY() + 40]
                            case 1, 3 -> {

                                // 左上角是否在范围内
                                if (getX() >= enemyTank.getX()
                                        && getX() <= enemyTank.getX() + 60
                                        && getY() >= enemyTank.getY()
                                        && getY() <= enemyTank.getY() + 40) {
                                    return true;
                                }
                                // 左下角是否在范围内
                                if (getX() >= enemyTank.getX()
                                        && getX() <= enemyTank.getX() + 60
                                        && getY() + 40 >= enemyTank.getY()
                                        && getY() + 40 <= enemyTank.getY() + 40) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {

            if (isLive && shots.size() <= 3) {
                Shot shot = switch (getDirection()) {
                    case 0 -> new Shot(getX() + 20, getY(), 0);
                    case 1 -> new Shot(getX() + 60, getY() + 20, 1);
                    case 2 -> new Shot(getX() + 20, getY() + 60, 2);
                    case 3 -> new Shot(getX(), getY() + 20, 3);
                    default -> null;
                };
                shots.add(shot);
                new Thread(shot).start();

            }
            switch (getDirection()) {
                case 0 -> {
                    for (int i = 0; i < 20; i++) {
                        if (getY() > 0 && !isTouch())
                            moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 1 -> {
                    for (int i = 0; i < 20; i++) {
                        if (getX() + 60 < 1000 && !isTouch())
                            moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 2 -> {
                    for (int i = 0; i < 20; i++) {
                        if (getY() + 60 < 750 && !isTouch())
                            moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case 3 -> {
                    for (int i = 0; i < 20; i++) {
                        if (getX() > 0 && !isTouch())
                            moveLeft();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }


            setDirection((int) (Math.random() * 4));

            if (!isLive) {
                return;
            }
        }
    }
}
