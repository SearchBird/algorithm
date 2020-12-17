package org.tjw.luogu.string;

public class PrefixFunction {

    public static void main(String[] args) {
        PrefixFunction function = new PrefixFunction();
        int[] res = function.prefixCalculate("abcab");
        for(int i : res)
            System.out.print(i + " ");
    }

    public int[] prefixCalculate_sub(String target) {
        int len = target.length();
        int[] prefixNum = new int[len];
        char[] chs = target.toCharArray();
        for(int i = 1;i < len;i ++) {
            int j = prefixNum[i - 1];
            while(j > 0 && chs[j] != chs[i]) j = prefixNum[j - 1];
            if(chs[j] == chs[i]) j ++;
            prefixNum[i] = j;
        }
        return prefixNum;
    }

    public int[] prefixCalculate(String target) {
        int len = target.length();
        int[] prefixNum = new int[len];
        char[] chs = target.toCharArray();
        for(int i = 1;i < len;i ++) {
            int j = prefixNum[i - 1];
            while(j > 0 && chs[j] != chs[i]) j = prefixNum[j - 1];
            if(chs[j] == chs[i]) j ++;
            prefixNum[i] = j;
        }
        return prefixNum;
    }
}
