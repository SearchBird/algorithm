package org.tjw.leetcode.algorithm;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
        long aL = 13;
        for(int j = 0; j <= 7; j++){
            aL = aL * 13;
            if(aL > MOD) System.out.println(j);
        }
        return ;
    }

}
