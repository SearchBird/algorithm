package org.tjw.leetcode.algorithm;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Callable;

public class test {

    public void count() throws InterruptedException {
        long sleepRandom = new Double(Math.random()* 1000).longValue() * 3;
        Thread.sleep(sleepRandom);
        System.out.println(sleepRandom);
    }

    public static void main(String[] args) throws Exception {
        test test = new test();
        for(int i = 0;i < 100;i ++)
            test.count();
    }

}
