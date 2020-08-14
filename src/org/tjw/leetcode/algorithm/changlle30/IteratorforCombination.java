package org.tjw.leetcode.algorithm.changlle30;

import java.util.ArrayList;
import java.util.List;

public class IteratorforCombination {
    public static void main(String[] args) {
        CombinationIterator iterator = new CombinationIterator("abc", 2);
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class CombinationIterator {

    private static int size;
    private static int listIndex;
    private static String[] tempArr = new String[(int)Math.pow(2, 12)];
    private static StringBuilder builder = new StringBuilder();

    public CombinationIterator(String characters, int combinationLength) {
        int len0 = characters.length();size = 0;listIndex = 0;
        recurtion(0, characters.toCharArray(), len0, combinationLength);
        builder.setLength(0);
    }

    public void recurtion(int index, char[] temp, int len0, int len) {
        if(builder.length() == len) {
            tempArr[size ++] = builder.toString();return;
        }
        for(int i = index;i < len0;i ++) {
            builder.append(temp[i]);
            recurtion(i + 1, temp, len0, len);
            builder.setLength(builder.length() - 1);
        }
    }

    public String next() {
        if(hasNext()) return tempArr[listIndex ++];
        return null;
    }

    public boolean hasNext() {
        return listIndex != size;
    }
}
