package org.tjw.leetcode.algorithm.string;

public class PushDominoes {

    public static void main(String[] a) {
        Solution s = new PushDominoes().new Solution();
        System.out.println(s.pushDominoes(".L.R...LR..L.."));
        System.out.println(s.pushDominoes(".......L.L"));
    }

    class Solution {
        public String pushDominoes(String dominoes) {
            int Ridx = -1;
            char[] chs = dominoes.toCharArray();
            for(int i = 0, len = dominoes.length(); i < len; ) {
                if(chs[i] == 'L') {
                    int j = i, begin = Ridx == -1 ? -1 : (i + Ridx >> 1);
                    while(-- j >= 0 && ((chs[j] != 'R' || j > begin) && chs[j] != 'L'))
                        chs[j] = 'L';
                    if(begin != -1 && j >= begin && ((i + Ridx) & 1) == 0)
                        chs[begin] = Ridx != -1 && begin != Ridx ? '.' : 'L';
                    Ridx = i;
                    i ++;
                } else if(chs[i] == 'R') {
                    Ridx = i;
                    while(++ i < len && chs[i] == '.') chs[i] = 'R';
                } else i ++;
            }
            return new String(chs);
        }
    }
}
