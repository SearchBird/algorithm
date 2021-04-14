package org.tjw.codewar.Trainning.kyu4.bwt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageByteBWT {
    public static void main(String[] args) throws Exception {

    }

    public void read() throws Exception {
        FileInputStream fis = new FileInputStream("C:\\Users\\Luke\\Documents\\WeChat Files\\All Users\\3e85fb0caf903787bc4cab86a7467dce.jpg");
        StringBuilder builder = new StringBuilder();
        int a;
        while((a = fis.read()) != -1) {
            builder.append((char)a);
        }
        System.out.print(builder.toString());
    }

    public void write() throws Exception {
        FileOutputStream fos = new FileOutputStream("E:\\glib\\algorithm\\src\\org\\tjw\\codewar\\Trainning\\kyu4\\bwt\\data\\newFile");
        //fos.write();
    }
}
