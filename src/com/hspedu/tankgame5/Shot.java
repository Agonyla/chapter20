package com.hspedu.tankgame5;

/**
 * @Author Agony
 * @Create 2023/2/18 14:30
 * @Version 1.0
 */
public class Shot implements Runnable {
    int x;
    int y;
    int direction;
    int speed = 10;

    boolean isLive = true;

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void shotTank() {

    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direction) {
                case 0 -> y -= speed;
                case 1 -> x += speed;
                case 2 -> y += speed;
                case 3 -> x -= speed;
            }
            System.out.println("x = " + x + " y = " + y);
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                System.out.println("子弹到达边界");
                isLive = false;
                break;
            }
        }
    }
}
