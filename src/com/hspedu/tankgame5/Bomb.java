package com.hspedu.tankgame5;

/**
 * @Author Agony
 * @Create 2023/2/18 19:00
 * @Version 1.0
 */
public class Bomb {

    int x;
    int y;
    int life = 9;
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown() {
        if (life > 0) {
            life--;
        } else {
            isLive = false;
        }
    }
}
