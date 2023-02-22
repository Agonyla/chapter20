package com.hspedu.tankgame5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @Author Agony
 * @Create 2023/2/15 20:41
 * @Version 1.0
 * 坦克大战绘图区域
 * 注：使用增强for循环会抛出ConcurrentModificationException异常
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {

    MyTank myTank = null;

    Vector<EnemyTank> enemyTanks = new Vector<>();

    int enemyTankSize = 4;

    Vector<Bomb> bombs = new Vector<>();
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        myTank = new MyTank(500, 500);
        myTank.setSpeed(10);

        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(200 * (1 + i), 0);

            // 把enemyTanks传给每一个enemyTank
            enemyTank.setEnemyTanks(enemyTanks);

            enemyTank.setDirection(2);
            new Thread(enemyTank).start();

            enemyTanks.add(enemyTank);
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
            //
            enemyTank.shots.add(shot);
            new Thread(shot).start();
        }

        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);

        if (myTank != null && myTank.isLive) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirection(), 0);
            drawMyTankShot(myTank, g);
        }


        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 1);
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.fillOval(shot.x - 2, shot.y - 2, 4, 4);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);

            if (bomb.life > 6) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 3) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bomb.lifeDown();
            if (bomb.life == 0) {
                bombs.remove(bomb);
            }
        }


    }

    /**
     * @param x         坦克左上角x坐标
     * @param y         坦克左上角y坐标
     * @param g         画笔
     * @param direction 坦克朝向(上下左右)
     * @param type      坦克种类
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {
            // 自己的坦克
            case 0 -> g.setColor(Color.cyan);
            // 敌人的坦克
            case 1 -> g.setColor(Color.yellow);
        }

        // 0 -> 向上  1 -> 向右  2 -> 向下  3 -> 向左
        switch (direction) {
            // 向上朝向
            case 0 -> {
                g.fill3DRect(x, y, 10, 60, false); // 左边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间主体
                g.fill3DRect(x + 30, y, 10, 60, false); // 右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); // 中间盖子
                g.drawLine(x + 20, y + 30, x + 20, y); // 杆
            }
            //
            case 1 -> {
                g.fill3DRect(x - 10, y + 10, 60, 10, false); // 左边轮子
                g.fill3DRect(x, y + 20, 40, 20, false); // 中间主体
                g.fill3DRect(x - 10, y + 40, 60, 10, false); // 右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); // 中间盖子
                g.drawLine(x + 20, y + 30, x + 50, y + 30); // 杆
            }
            case 2 -> {
                g.fill3DRect(x, y, 10, 60, false); // 左边轮子
                g.fill3DRect(x + 10, y + 10, 20, 40, false); // 中间主体
                g.fill3DRect(x + 30, y, 10, 60, false); // 右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); // 中间盖子
                g.drawLine(x + 20, y + 30, x + 20, y + 60); // 杆
            }
            case 3 -> {
                g.fill3DRect(x - 10, y + 10, 60, 10, false); // 左边轮子
                g.fill3DRect(x, y + 20, 40, 20, false); // 中间主体
                g.fill3DRect(x - 10, y + 40, 60, 10, false); // 右边轮子
                g.fillOval(x + 10, y + 20, 20, 20); // 中间盖子
                g.drawLine(x + 20, y + 30, x - 10, y + 30); // 杆
            }
        }


    }

    /**
     * @param myTank 我方坦克
     * @param g      画笔
     */
    public void drawMyTankShot(MyTank myTank, Graphics g) {
        g.setColor(Color.cyan);
        for (int i = 0; i < myTank.shots.size(); i++) {
            Shot shot = myTank.shots.get(i);
            if (shot != null && shot.isLive) {
                g.fillOval(shot.x - 2, shot.y - 2, 4, 4);
            } else {
                myTank.shots.remove(shot);
            }
        }


    }

    /**
     * 多颗子弹判断是否击中对方坦克
     */
    public void hitEnemyTank() {

        for (int j = 0; j < myTank.shots.size(); j++) {
            Shot shot = myTank.shots.get(j);
            if (shot != null && shot.isLive) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(shot, enemyTank);
                }
            }
        }

    }


    /**
     * @param shot 子弹范围
     * @param tank 用于获得敌方坦克方向
     *             单颗子弹
     */
    public void hitTank(Shot shot, Tank tank) {
        switch (tank.getDirection()) {
            case 0, 2 -> {
                if (shot.x > tank.getX() && shot.x < tank.getX() + 40
                        && shot.y > tank.getY() && shot.y < tank.getY() + 60) {
                    shot.isLive = false;
                    tank.isLive = false;

                    if (tank instanceof EnemyTank) {
                        enemyTanks.remove((EnemyTank) tank);
                    }

                    bombs.add(new Bomb(tank.getX(), tank.getY()));

                }
            }
            case 1, 3 -> {
                if (shot.x > tank.getX() && shot.x < tank.getX() + 60
                        && shot.y > tank.getY() && shot.y < tank.getY() + 40) {
                    shot.isLive = false;
                    tank.isLive = false;

                    if (tank instanceof EnemyTank) {
                        enemyTanks.remove((EnemyTank) tank);
                    }

                    bombs.add(new Bomb(tank.getX(), tank.getY()));

                }
            }
        }
    }

    public void hitMyTank() {

        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                Shot shot = enemyTank.shots.get(j);
                if (myTank.isLive && shot.isLive) {
                    hitTank(shot, myTank);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                myTank.setDirection(0);
                if (myTank.getY() > 0)
                    myTank.moveUp();
            }
            case KeyEvent.VK_D -> {
                myTank.setDirection(1);
                if (myTank.getX() + 60 < 1000)
                    myTank.moveRight();
            }
            case KeyEvent.VK_S -> {
                myTank.setDirection(2);
                if (myTank.getY() + 60 < 750)
                    myTank.moveDown();
            }
            case KeyEvent.VK_A -> {
                myTank.setDirection(3);
                if (myTank.getX() > 0)
                    myTank.moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_J) {
            // 发射一颗子弹
            // if (myTank.shot == null || !myTank.shot.isLive)
            //     myTank.shotTank();
            // 发射多颗子弹
            myTank.shotTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // if (myTank.shot != null && myTank.shot.isLive) {
            //
            //     for (int i = 0; i < enemyTanks.size(); i++) {
            //         EnemyTank enemyTank = enemyTanks.get(i);
            //         hitTank(myTank.shot, enemyTank);
            //     }
            // }
            hitEnemyTank();
            hitMyTank();
            this.repaint();
        }
    }
}
