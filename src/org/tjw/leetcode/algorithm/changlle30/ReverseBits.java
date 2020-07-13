package org.tjw.leetcode.algorithm.changlle30;

public class ReverseBits {

    public static void main(String[] args) {
        Solution solution = new ReverseBits().new Solution();


        System.out.println(String.format("%32s", solution.reverseBits(Integer.valueOf("00000010100101000001111010011100", 2))));
    }

    private static int[] intArr = new int[32];

    static{
        for(int i = 32;i -- > 0;) {
            intArr[i] = 1 << i;
        }
    }
    public class Solution {


        // you need treat n as an unsigned value
        public int reverseBits(int n) {

            System.out.println(String.format("%32s", Integer.toBinaryString(n)));

            n = (n >>> 16) | (n << 16);
            // n = ((n & 0xffff0000) >>> 16) | ((n & 0x0000ffff) << 16);
            System.out.println(String.format("%32s", Integer.toBinaryString(n & 0xff00ff00)) + "\r\n" + Integer.toBinaryString(n & 0x00ff00ff));
            System.out.println(String.format("%32s", Integer.toBinaryString((n & 0xff00ff00) >>> 8)) + "\r\n" + Integer.toBinaryString((n & 0x00ff00ff) << 8));
            n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
            System.out.println(Integer.toBinaryString(n) + "\r\n");

            System.out.println(String.format("%32s", Integer.toBinaryString(n & 0xf0f0f0f0)) + "\r\n" + Integer.toBinaryString(n & 0x0f0f0f0f));
            System.out.println(String.format("%32s", Integer.toBinaryString((n & 0xf0f0f0f0) >>> 4)) + "\r\n" + Integer.toBinaryString((n & 0x0f0f0f0f) << 4));
            n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
            System.out.println(Integer.toBinaryString(n) + "\r\n");

            System.out.println(String.format("%32s", Integer.toBinaryString(n & 0xcccccccc)) + "\r\n" + String.format("%32s", Integer.toBinaryString(n & 0x33333333)));
            System.out.println(String.format("%32s", Integer.toBinaryString((n & 0xcccccccc) >>> 2)) + "\r\n" + String.format("%32s", Integer.toBinaryString((n & 0x33333333) << 2)));
            n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
            System.out.println(String.format("%32s", Integer.toBinaryString(n)) + "\r\n");

            System.out.println(String.format("%32s", Integer.toBinaryString(n & 0xaaaaaaaa)) + "\r\n" + String.format("%32s", Integer.toBinaryString(n & 0x55555555)));
            System.out.println(String.format("%32s", Integer.toBinaryString((n & 0xaaaaaaaa) >>> 1)) + "\r\n" + String.format("%32s", Integer.toBinaryString((n & 0x55555555) << 1)));
            n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
            System.out.println(String.format("%32s", Integer.toBinaryString(n)) + "\r\n");
            return n;
        }

        public int reverseBits2(int n) {
            int a = 0;
            for(int i = 32;i -- > 0;) {
                if((n & intArr[i]) != 0) a |= intArr[31 ^ i];
            }
            return a;
        }
    }
}
