package com.hspedu.tankgame5;

/**
 * @Author Agony
 * @Create 2023/2/23 21:11
 * @Version 1.0
 */
public class Node {

    int x;
    int y;
    int direction;

    public Node(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
