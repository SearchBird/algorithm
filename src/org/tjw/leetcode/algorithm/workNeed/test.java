package org.tjw.leetcode.algorithm.workNeed;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class test {
    private static long MOD = (long) Math.pow(2, 32);
    public void count() throws InterruptedException {
        long sleepRandom = new Double(Math.random()* 1000).longValue() * 3;
        Thread.sleep(sleepRandom);
        Math.log(1);
        System.out.println(sleepRandom);
    }

    /**
     * 获取日志 一些关键语句
     */
    public static void main(String[] args) throws Exception {
        File dir = new File("E:\\lib");

        File[] files = dir.listFiles();
        StringBuilder builder = new StringBuilder();
        for(File file : files) {
            builder.append("../lib/").append(file.getName()).append(";");
        }

        BufferedReader reader = new BufferedReader(new FileReader(""));
        String str = "";
        while((str = reader.readLine()) != "") {

        }


        BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\output.txt"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    public static int contains(String temp) {
        int index = -1;
        if((index = temp.indexOf("gfods")) != -1) {
            return index;
        } else if((index = temp.indexOf("gfdown")) != -1) {
            return index;
        } else if((index = temp.indexOf("gfgam")) != -1) {
            return index;
        }
        return index;
    }
}
