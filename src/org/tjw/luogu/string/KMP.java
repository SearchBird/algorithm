package org.tjw.luogu.string;

import java.util.ArrayList;
import java.util.List;

public class KMP {

    public static void main(String[] args) {
        KMP p = new KMP();
        System.out.println(p.match("aaabbaa", "aaaba"));
        System.out.println(p.match("aaa", "ccaababaaa"));
        System.out.println(p.match("aaa", "aabcacbaaa"));
        System.out.println(p.matchList("aa", "aabaacaaa"));
    }

    public int match(String target, String doc) {
        int len = doc.length(), site = 0, tLen = target.length(); char[] docC = doc.toCharArray(), tarC = target.toCharArray();
        int[] preNum = new PrefixFunction().prefixCalculate(target);
        for(int i = 0;i < len;i ++) {
            while(site > 0 && tarC[site] != docC[i])
                site = preNum[site - 1];
            if(tarC[site] == docC[i]) site ++;
            if(site == tLen) return i - site + 1;
        }
        return -1;
    }

    public List<Integer> matchList(String target, String doc) {
        List<Integer> res = new ArrayList<>();
        int len = doc.length(), site = 0, tLen = target.length(); char[] docC = doc.toCharArray(), tarC = target.toCharArray();
        int[] preNum = new PrefixFunction().prefixCalculate(target);
        for(int i = 0;i < len;i ++) {
            while(site > 0 && tarC[site] != docC[i])
                site = preNum[site - 1];
            if(tarC[site] == docC[i]) site ++;
            if(site == tLen) {
                res.add(i - site + 1);
                site = 0;
            }
        }
        return res;
    }
}
