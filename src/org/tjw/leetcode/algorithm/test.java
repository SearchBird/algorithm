package org.tjw.leetcode.algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\gitlab\\wtdubbo-2020-02-25\\serv_web\\src\\main\\java\\com\\gffunds\\wtgf\\datac\\dao\\DataCenterMapper.xml"));

        String temp = "";
        HashSet<String> set = new HashSet<String>();
        StringBuilder builder = new StringBuilder();
        while((temp = reader.readLine()) != null) {
            int index = contains(temp);
            if(contains(temp) != -1) {
                int last = temp.indexOf(' ', index);
                if(last == -1)
                    set.add(temp.substring(index));
                else
                    set.add(temp.substring(index, last));
            }
        }

        for(String str : set) {
            builder.append(str).append("\r\n");
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\output.txt"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
        reader.close();
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
