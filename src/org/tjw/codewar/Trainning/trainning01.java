package org.tjw.codewar.Trainning;

import java.util.List;

public class trainning01 {


    public static void main(String[] args) {
        isogram solution = new trainning01().new isogram();

        System.out.println(solution.isIsogram("Dermatoglyphics"));
        System.out.println(solution.isIsogram("isogram"));
        System.out.println(solution.isIsogram("aba"));
        System.out.println(solution.isIsogram("moOse"));
        System.out.println(solution.isIsogram(""));
    }

    class isogram {

        private int[] primes = new int[]{
                2, 3, 5, 7, 11,
                13, 17, 19, 23, 29,
                31, 37, 41, 43, 47,
                53, 59, 61, 67, 71,
                73, 79 ,83, 89 ,97,
                101};

        public boolean  isIsogram(String str) {
            if(str == null || str.equals("")) return true;
            int len = str.length();
            long sum = 1;
            for(int i = len;i -- > 0;) {
                int ch = str.charAt(i) - 'a';
                if(ch < 0) ch += 32;
                if(sum % primes[ch] == 0) return false;
                sum *= primes[ch];
            }
            return true;
        }
    }
}
