package org.tjw.openjudge;

import java.util.Scanner;

public class poj_1222 {
    private static boolean find = false;
    private static byte[][] temp = new byte[5][6], lights = new byte[5][6], btns = new byte[5][6];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0;i < 5;i ++)
            for(int j = 0;j < 6;j ++) lights[i][j] = sc.nextByte();
        recurtion(lights, btns, 0, 0);
        if(!find)
            recurtion(lights, btns, 0, 1);
        for(int i = 0;i < 5;i ++) {
            for(int j = 0;j < 6;j ++)
                System.out.print(btns[i][j] + " ");
            System.out.println();
        }
    }

    private static void recurtion(byte[][] lights, byte[][] btns, int begin, int val) {
        if(begin == 5) {
            if(val == 1) {
                btns[0][begin] = 1;
                click(lights, 0, 5);
            }
            for(int i = 0;i < 5;i ++)
                for(int j = 0;j < 6;j ++) temp[i][j] = lights[i][j];
            for(int i = 1;i < 5;i ++)
                for(int j = 0;j < 6;j ++)
                    if (temp[i - 1][j] == 1) {
                        btns[i][j] = 1;
                        click(temp, i, j);
                    }
                    else btns[i][j] = 0;

            for(int i = 0;i < 6; i ++)
                if(temp[4][i] == 1) {
                    btns[0][5] = 0;
                    if(val == 1) click(lights, 0, 5);
                    return;
                }
            find = true;
            return;
        }
        if(val == 1) {
            btns[0][begin] = 1;
            click(lights, 0, begin);
        }
        recurtion(lights, btns, begin + 1, 0);
        if(find) return;
        recurtion(lights, btns, begin + 1, 1);
        if(find) return;
        if(val == 1) {
            btns[0][begin] = 0;
            click(lights, 0, begin);
        }
    }

    private static void click(byte[][] lights, int x, int y) {
        lights[x][y] ^= 1;
        if(x + 1 < 5) lights[x + 1][y] ^= 1;
        if(y + 1 < 6) lights[x][y + 1] ^= 1;
        if(y - 1 >= 0) lights[x][y - 1] ^= 1;
    }
}