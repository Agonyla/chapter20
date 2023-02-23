package com.hspedu.tankgame5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @Author Agony
 * @Create 2023/2/15 20:44
 * @Version 1.0
 */
public class AgonyTankGame05 extends JFrame {

    static Scanner scanner = new Scanner(System.in);

    static String key = "";

    public static void main(String[] args) {


        new AgonyTankGame05();
    }

    public AgonyTankGame05() {

        System.out.println("请输入选择 1: 新游戏    2: 继续上局");
        key = scanner.next();
        MyPanel myPanel = new MyPanel(key);

        new Thread(myPanel).start();
        this.add(myPanel);
        this.addKeyListener(myPanel);

        this.setSize(1300, 950);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }

}
