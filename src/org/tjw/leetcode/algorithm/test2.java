package org.tjw.leetcode.algorithm;

import java.util.Scanner;

public class test2 {

    public static void main(String[] args) throws Exception {
        char[] input = new Scanner(System.in).next().toCharArray();

        int status = 0;
        System.out.print("0");
        for(char ch : input) {
            switch (status) {
                case 0 :
                    if(ch == 'a') {
                        System.out.print(" -> 1");
                        status = 1;
                    } else {
                        System.out.print(" -> 0");
                        status = 0;
                    }
                    break;
                case 1:
                    if(ch == 'a') {
                        System.out.print(" -> 1");
                        status = 1;
                    } else {
                        System.out.print(" -> 2");
                        status = 2;
                    }
                    break;
                case 2:
                    if(ch == 'a') {
                        System.out.print(" -> 1");
                        status = 1;
                    } else {
                        System.out.print(" -> 3");
                        status = 3;
                    }
                    break;
                case 3:
                    if(ch == 'a') {
                        System.out.print(" -> 1");
                        status = 1;
                    } else {
                        System.out.print(" -> 0");
                        status = 0;
                    }
                    break;
            }
        }
        if(status == 3) System.out.print(" -> end");
        else throw new Exception("没结束");
    }

}
