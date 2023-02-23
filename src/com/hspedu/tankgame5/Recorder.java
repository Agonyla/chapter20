package com.hspedu.tankgame5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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

    public static void keepRecord() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(recordFile));
            bufferedWriter.write(allEnemyTankNum + "");
            bufferedWriter.newLine();
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
