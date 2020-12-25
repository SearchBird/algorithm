package org.tjw.codewar.Trainning.kyu4.bwt;

import java.util.*;

public class BurrowsWheeler {

    public static void main(String[] args) {
        System.out.println(new BWT("nnbbraaaa", 4).equals(BurrowsWheeler.encode("bananabar")));
        System.out.println(new BWT("rdarcaaaabb", 2).equals(BurrowsWheeler.encode("abracadabra")));

        System.out.println("bananabrbanrrabarr".equals(BurrowsWheeler.decode("rnnbbbrraaaaarrbna", 6)));
    }

    private static StringBuilder builder = new StringBuilder();
    public static BWT encode(String s) {
        builder.setLength(0);
        int len = s.length(), index = 0;
        char[] chs = s.toCharArray();
        boolean[] map = new boolean[10000];
        Map<Character, List<Integer>> indexMap = new HashMap<Character, List<Integer>>();
        for(int i = 0;i < len;map[chs[i]] = true,i ++) { // 先 hash 排序一次, 并记录索引
            List<Integer> list = indexMap.getOrDefault(chs[i], new ArrayList<Integer>());
            list.add(i);
            indexMap.put(chs[i], list);
        }
        for(int i = 0;i < 256;i ++) {
            if(!map[i]) continue;
            List<Integer> list = indexMap.get((char)i);
            Collections.sort(list, new Comparator<Integer>() { // 每个字符开头排序一次
                @Override
                public int compare(Integer o1, Integer o2) {
                    int i = 1;
                    for(;i < len && chs[(o1 + i) % len] == chs[(o2 + i) % len];i ++);
                    return chs[(o1 + i) % len] > chs[(o2 + i) % len] ? 1 : -1;
                }
            });
            for(int l : list)
                if(l == 0) { // 拼最后一个, 并记录原始位置
                    index = builder.length();
                    builder.append(chs[len - 1]);
                }
                else builder.append(chs[l - 1]); // 拼前一个
        }
        return new BWT(builder.toString(), len == 0 ? -1 : index);
    }

    public static String decode(String s, int n) {
        if(n < 0) return "";
        builder.setLength(0);
        int len = s.length();
        char[] preArr = s.toCharArray(), nxtArr = s.toCharArray();
        Arrays.sort(nxtArr);
        Map<Character, List<Integer>> preMap = new HashMap<>(), nxtMap = new HashMap<>();
        for(int i = 0;i < len;i ++) { // 按字符, 将前缀和后继索引存放
            List<Integer> prelist = preMap.getOrDefault(preArr[i], new ArrayList<>()), nxtlist = nxtMap.getOrDefault(nxtArr[i], new ArrayList<>());
            prelist.add(i); nxtlist.add(i);
            preMap.put(preArr[i], prelist); nxtMap.put(nxtArr[i], nxtlist);
        }
        while(len -- > 0) {
            char temp = nxtArr[n];
            builder.append(temp); // 元素
            n = nxtMap.get(temp).indexOf(n); // 排名
            n = preMap.get(temp).get(n); // 索引位置
        }
        return builder.toString();
    }

    static class BWT {
        String res;
        int index;
        BWT(String res, int index){
            this.res = res;
            this.index = index;
        }
        public boolean equals(BWT b) {
            System.out.println(b);
            return this.res.equals(b.res) && this.index == b.index;
        }
        public String toString() {
            return "obj :" + res + ":" + index;
        }
    }
}
