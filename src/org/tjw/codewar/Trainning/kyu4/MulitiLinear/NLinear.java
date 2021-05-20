package org.tjw.codewar.Trainning.kyu4.MulitiLinear;

import java.util.Arrays;

public class NLinear {

    public static void main(String[] args) {
        System.out.println(nLinear(new int[]{5,6,9,10,12,17}, 71));
    }

    public static int nLinear(int[] linearArr, int n) {
        Arrays.sort(linearArr);
        int[] tempArr = new int[n + 1];
        tempArr[0] = 1;
        int indexs[] = new int[linearArr.length],
                values[] = new int[linearArr.length];
        for(int i = 0; i < linearArr.length; i ++) {
            values[i] = linearArr[i] + 1;
        }

        for(int i = 1; i <= n; i ++) {
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < values.length; j ++) {
                if(values[j] < min) {
                    min = values[j];
                }
            }
            tempArr[i] = min;
            for(int j = 0; j < values.length; j ++) {
                if(values[j] == min) {
                    values[j] = tempArr[++ indexs[j]] * linearArr[j] + 1;
                }
            }

        }
        return tempArr[n];
    }
}
