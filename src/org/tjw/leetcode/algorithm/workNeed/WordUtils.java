package org.tjw.leetcode.algorithm.workNeed;

import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;

public class WordUtils {

    public static void main(String[] args) throws Exception {
        sqlKeyWordMain();
    }

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
    public static void getLogKeyWord(String[] args) throws Exception {
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


    /**
     * 拿插入sql相应关键字
     */
    public static void sqlKeyWordMain() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\glib\\algorithm\\src\\org\\tjw\\leetcode\\algorithm\\workNeed\\testdata\\insertKeyWordFind.txt"));
        String temp = "";
        StringBuilder builder = new StringBuilder();
        while((temp = reader.readLine()) != null) {
            builder.append(temp);
        }
        List<String> res = sqlInsertKeyWordFind(builder.toString(), 3);
        System.out.println(res);
    }

    public static List<String> sqlInsertKeyWordFind(String doc, int index) {
        String[] sqls = doc.split(";");
        List<String> res = new ArrayList<>();
        for(String str : sqls) {
            if(str != null && !"".equals(str)) {
                int i = str.indexOf("values ") + "values ".length();
                res.add(str.substring(i, str.length() - 1).split(",")[index]);
            }
        }
        return res;
    }
}
