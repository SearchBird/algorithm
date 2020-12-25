package org.tjw.leetcode.algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Throwable {

    }

    class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int top = 0, len = nums.length, i;
            int[] greater = new int[len];
            int[][] mStack = new int[len][2];
            for(i = 1, mStack[top][0] = nums[0];i < len;i ++) {
                while(top >= 0 && nums[i] > mStack[top][0]) {
                    greater[mStack[top][1]] = nums[i]; top --;
                }
                mStack[++ top][0] = nums[i];
                mStack[top][1] = i;
            }
            while(top > 0) {
                greater[mStack[top --][1]] = -1;
            }
            return greater;
        }
    }
}
