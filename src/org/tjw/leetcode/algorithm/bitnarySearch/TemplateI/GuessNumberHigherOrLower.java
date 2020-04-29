package org.tjw.leetcode.algorithm.bitnarySearch.TemplateI;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results
 * (-1, 1, or 0):
 *
 * -1 : My number is lower
 *  1 : My number is higher
 *  0 : Congrats! You got it!
 * Example :
 *
 * Input: n = 10, pick = 6
 * Output: 6
 */
public class GuessNumberHigherOrLower {

    private int guessNum = 100;

    public static void main(String[] args) {
        Solution solution = new GuessNumberHigherOrLower().new Solution();
        System.out.println(solution.guessNumber(1000));
        System.out.println(solution.guessNumber(10000));
    }

    class Solution {
        public int guessNumber(int n) {
            if(n == 1 || guess(1) == 0) return 1;
            if(guess(n) == 0) return n;

            int left = 1;
            int right = n;
            int mid = 0;
            while(right >= left) {
                mid = left + ((right - left) >> 1);
                int flag = guess(mid);
                if(flag == 0) return mid;
                else if(flag < 0) right = mid - 1;
                else left = mid + 1;
            }
            return mid;
        }
    }

    public int guess(int i) {
        if(guessNum < i) return -1;
        else if(guessNum > i) return 1;
        else return 0;
    }
}
