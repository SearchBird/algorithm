package org.tjw.luogu;

import java.io.*;
import java.util.Map;

public class DataHandler {
    public static void main(String[] args) throws Throwable {
        String path = "E:\\glib\\algorithm\\src\\org\\tjw\\luogu\\tree\\treap\\treap_data\\";
        String[] fileList = new String[]{
                "input0.in",
                "input1.in",
                "input2.in",
                "input3.in",
                "input4.in",
                "input5.in",
        };
        for(String str : fileList) {
            String temp = path + str;
            no12Handler(temp);
            get12Handler(temp);
            get1Handler(temp);
            get2Handler(temp);
        }
    }

    public static void get2Handler(String path) throws Exception {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String temp;
        StringBuilder builder = new StringBuilder();
        while((temp = reader.readLine()) != null) {
            if(temp.startsWith("2")) builder.append(temp).append("\r\n");
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getParentFile().getAbsolutePath() + "\\" + file.getName() + "-get2.txt"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    public static void get1Handler(String path) throws Exception {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String temp;
        StringBuilder builder = new StringBuilder();
        while((temp = reader.readLine()) != null) {
            if(temp.startsWith("1")) builder.append(temp).append("\r\n");
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getParentFile().getAbsolutePath() + "\\" + file.getName() + "-get1.txt"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    public static void get12Handler(String path) throws Exception {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String temp;
        StringBuilder builder = new StringBuilder();
        while((temp = reader.readLine()) != null) {
            if(temp.startsWith("1") || temp.startsWith("2")) builder.append(temp).append("\r\n");
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getParentFile().getAbsolutePath() + "\\" + file.getName() + "-get12.txt"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }

    public static void no12Handler(String path) throws Exception {
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String temp;
        StringBuilder builder = new StringBuilder();
        while((temp = reader.readLine()) != null) {
            if(!temp.startsWith("1") && !temp.startsWith("2")) builder.append(temp).append("\r\n");
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getParentFile().getAbsolutePath() + "\\" + file.getName() + "-no12.txt"));
        writer.write(builder.toString());
        writer.flush();
        writer.close();
    }
}
