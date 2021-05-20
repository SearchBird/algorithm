package org.tjw.codewar.Trainning.kyu4;

import java.util.HashMap;
import java.util.Map;

public class ParseEnglishToInt {

    private static Map<String, Integer> cache = new HashMap<String, Integer>();
    static {
        cache.put("zero", 0);
        cache.put("one", 1);
        cache.put("two", 2);
        cache.put("three", 3);
        cache.put("four", 4);
        cache.put("five", 5);
        cache.put("six", 6);
        cache.put("seven", 7);
        cache.put("eight", 8);
        cache.put("nine", 9);
        cache.put("ten", 10);
        cache.put("eleven", 11);
        cache.put("twelve", 12);
        cache.put("thirteen", 13);
        cache.put("fourteen", 14);
        cache.put("fifteen", 15);
        cache.put("sixteen", 16);
        cache.put("seventeen", 17);
        cache.put("eighteen", 18);
        cache.put("nineteen", 19);
        cache.put("twenty", 20);
        cache.put("thirty", 30);
        cache.put("forty", 40);
        cache.put("fifty", 50);
        cache.put("sixty", 60);
        cache.put("seventy", 70);
        cache.put("eighty", 80);
        cache.put("ninety", 90);
    }

    public static void main(String[] args) {
        System.out.print(parseInt("one hundred seventy-four million seven hundred eighty-three thousand nine hundred and seventy-four"));
        System.out.print(parseInt("sixty-seven"));
    }

    public static int parseInt(String numStr) {
        String[] strs = numStr.split("\\s");
        boolean mf = false;
        int res = 0,tCount = 0, total = 0;
        for(int i = 0, len = strs.length; i < len; i ++) {
            String temp = strs[i];
            if(temp.contains("-")) {
                String[] strs2 = temp.split("-");
                total += cache.get(strs2[0]);
                total += cache.get(strs2[1]);
            } else if("hundred".equals(temp)) {
                total *= 100;
                tCount += total;
                total = 0;
            } else if("thousand".equals(temp)) {
                tCount = (tCount + total) * 1000;
                total = 0;
                if(mf) {
                    res += tCount;
                    tCount = 0;
                }
            } else if("million".equals(temp)) {
                res = (tCount + total) * 1000000;
                total = tCount = 0;
                mf = true;
            } else if(cache.containsKey(temp)) total += cache.get(temp);
        }
        return res + tCount + total;
    }
}
