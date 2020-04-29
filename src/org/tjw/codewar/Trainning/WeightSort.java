package org.tjw.codewar.Trainning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WeightSort {

    public static void main(String[] str) {
        System.out.println(
                WeightSort.orderWeight(
                        "103 123 4444 99 2000" +
                                " 2000 10003 1234000 44444444 9999 11 11 22 123"));
    }

    public static String orderWeight(String strng) {
        ArrayList<StrObj> al = new ArrayList<StrObj>();

        boolean cF = false;
        int count = 0;
        StringBuilder builder = new StringBuilder();
        int len = strng.length();
        int ii = 0;

        for(int i = 0;i < len;i ++) {
            char ch = strng.charAt(i);

            if(ch != ' ') {
                cF = true;
                builder.append(ch);
                count += ch - '0';
            } else if(cF) {
                cF = false;
                al.add(new StrObj(count, builder.toString(), ii ++));
                count = 0;
                builder.setLength(0);
            }
        }
        if(builder.length() > 0) {
            al.add(new StrObj(count, builder.toString(), ii));
            builder.setLength(0);
        }

        Collections.sort(al, (s1, s2) -> {
            if(s1.val - s2.val == 0) {
                String str1 = s1.str;
                String str2 = s2.str;
                int len2 = str1.length() <= str2.length() ? str1.length() : str2.length();

                // 先进行前缀排序
                for(int x = 0;x < len2;x ++) {
                    int de = str1.charAt(x) - str2.charAt(x);
                    if(de > 0) {
                        return 1;
                    } else if(de < 0) return -1;
                }
                // 如果两个前缀都一样，就判断index
                return s2.i - s1.i;
            }
            return s1.val - s2.val;
        });

        for(int i = 0;i < al.size(); i ++) {
            builder.append(al.get(i).str + " ");
        }

        // 注意判空，有时候来个0的长度，没有结果的
        if(builder.length() != 0) builder.setLength(builder.length() - 1);
        return builder.toString();
    }
}

class StrObj {
    int val;
    String str;
    int i;
    StrObj(int val, String str, int i) {
        this.val = val;
        this.str = str;
        this.i = i;
    }
}