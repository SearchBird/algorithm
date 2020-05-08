package org.tjw.codewar.Trainning;

public class SnailSort {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3}
                                , {4,5,6}
                                , {7,8,9}};

        int[] res = Snail.snail(arr);
        for(int i : res) System.out.print(i + ",");
    }

    static class Snail {
        public static int[] snail(int[][] array) {
            // enjoy
            if(array == null || array.length == 0 || array[0].length == 0) return new int[0];
            int[] res = new int[array.length * array[0].length];
            int end = 0;

            int top = 0;
            int bottom = array.length;
            int left = 0;
            int right = array[0].length;
            int dir = 0;

            while(top <= bottom && left <= right) {
                dir %= 4;
                if(dir == 0) {
                    for(int i = left;i < right;i ++) {
                        res[end ++] = array[top][i];
                    }
                    top ++;

                } else if(dir == 1) {
                    for(int i = top;i < bottom;i ++) {
                        res[end ++] = array[i][right - 1];
                    }
                    right --;

                } else if(dir == 2) {
                    for(int i = right - 1;i >= left;i --) {
                        res[end ++] = array[bottom - 1][i];
                    }
                    bottom --;

                } else {
                    for(int i = bottom - 1;i >= top;i --) {
                        res[end ++] = array[i][left];
                    }
                    left ++;
                }
                dir ++;
            }

            return res;
        }
    }
}
