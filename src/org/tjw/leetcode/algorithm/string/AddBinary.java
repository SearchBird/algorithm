package org.tjw.leetcode.algorithm.string;

public class AddBinary {
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();

        String str = solution.addBinary("101111", "10");
        System.out.println(str);
    }

    class Solution {
        public String addBinary(String a, String b) {
            char[] aa = a.toCharArray();
            char[] bb = b.toCharArray();

            // 获取长处理
            boolean aaB = aa.length <= bb.length ? true : false;
            char[] longer = null;
            int len = 0;
            int ext = 0;
            if(aaB) {
                longer = bb;
                len = aa.length;
                ext = bb.length - aa.length;
            } else {
                longer = aa;
                len = bb.length;
                ext = aa.length - bb.length;
            }
            boolean addFlag = false;

            for(int i = len;i -- > 0;) {
                int num = 0;
                // 拿短的
                if(aaB) num = aa[i] - '0'; else num = bb[i] - '0';
                if(addFlag) {
                    num += 1;
                }
                num = longer[i + ext] - '0' + num;
                if(num > 1) {
                    addFlag = true;
                    if(num == 3) longer[i + ext] = '1';
                    else longer[i + ext] = '0';
                } else {
                    addFlag = false;
                    longer[i + ext] = (char)('0' + num);
                }
            }

            // 判断是否进位
            if(addFlag) {
                if(ext == 0) longer = newArr(longer);
                else {
                    for(int i = ext;i -- > 0;) {
                        if(!addFlag) break;
                        int num = longer[i] - '0' + 1;
                        if(num > 1) longer[i] = '0';
                        else {
                            longer[i] = (char)('0' + num);
                            addFlag = false;
                        }
                    }
                    // 需要在else内判断, 是否进位, 不影响外面的if
                    if(addFlag) longer = newArr(longer);
                }
            }

            return new String(longer);
        }

        public char[] newArr(char[] longer) {
            char[] arr = new char[longer.length + 1];
            System.arraycopy(longer, 0, arr, 1, longer.length);
            arr[0] = '1';
            return arr;
        }
    }
}
