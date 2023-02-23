package com.hspedu.tankgame5;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author Agony
 * @Create 2023/2/15 20:44
 * @Version 1.0
 */
public class AgonyTankGame05 extends JFrame {

    public static void main(String[] args) {
        new AgonyTankGame05();
    }

    public AgonyTankGame05() {

        MyPanel myPanel = new MyPanel();
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
