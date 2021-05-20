package org.tjw.codewar.Trainning.kyu4;

public class Smallestpossiblesum {

    public static void main(String[] args) {
        System.out.println(sum(new int[]{1234,4,321,5,5,1,43,26431,324,143}));
    }

    public static int sum(int[] numbers) {
        int minMod = numbers[0];
        for(int i = 1, len = numbers.length; i < len; ) {
            int mod = numbers[i] % minMod;
            if(mod != 0 && mod < minMod) {
                numbers[i] = mod;
                minMod = numbers[i];
                i = 0;
            } else {
                numbers[i] = minMod;
                i ++;
            }
        }

        return minMod * numbers.length;
    }
}
