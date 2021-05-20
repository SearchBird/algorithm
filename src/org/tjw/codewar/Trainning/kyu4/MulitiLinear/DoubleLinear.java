package org.tjw.codewar.Trainning.kyu4.MulitiLinear;

public class DoubleLinear {

    public static void main(String[] args) {
        System.out.print(dblLinear(100));
    }

    public static int dblLinear (int n) {
        int[] tempArr = new int[n + 1];
        deal(n, tempArr);
        return tempArr[n];
    }

    public static void deal(int n, int[] tempArr) {
        tempArr[0] = 1;
        int ai = 1, bi = 1, av = 3, bv = 4;

        for(int i = 1; i <= n; i ++) {
            tempArr[i] = Math.min(av, bv);
            if(tempArr[i] == av) av = (tempArr[ai ++] << 1) + 1;
            if(tempArr[i] == bv) bv = (tempArr[bi ++] * 3) + 1;
        }
        System.out.println(tempArr[n]);
    }

    public static int[] dblLinearArr (int n) {
        int[] tempArr = new int[n + 1];
        deal(n, tempArr);
        return tempArr;
    }
}
