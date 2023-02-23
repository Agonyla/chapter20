package com.hspedu.tankgame5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * @Author Agony
 * @Create 2023/2/23 20:24
 * @Version 1.0
 * 用于记录想相关信息用于文件交互
 */
public class Recorder {

    // 我方击毁坦克数量
    private static int allEnemyTankNum = 0;

    private static BufferedWriter bufferedWriter = null;
    private static String recordFile = "src\\myRecord.txt";
    // 定义Vector,指向 MyPanel 对象的 Vector
    private static Vector<EnemyTank> enemyTanks = null;

    // 基本思想 把别的类的参数传过来,在该类定义一个属性,设置set方法,在别的类调用set方法!!!
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static void keepRecord() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(recordFile));
            bufferedWriter.write(allEnemyTankNum + "");
            bufferedWriter.newLine();
            // 遍历敌人的Vector,然后根据情况保存
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                // 建议判断
                if (enemyTank.isLive) {
                    // 保存enemy坦克信息
                    String record = "x = " + enemyTank.getX() + " y = " + enemyTank.getY() + " direction = " + enemyTank.getDirection();
                    bufferedWriter.write(record);
                    bufferedWriter.newLine();
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static void addAllEnemyTankNum() {
        Recorder.allEnemyTankNum++;
    }
}
