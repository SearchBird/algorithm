package org.tjw.codewar.Trainning;

/**
 * 将双数原位不动，然后排序奇数
 */
public class SortTheOdd {
    public static void main(String[] args) {
        Kata solution = new SortTheOdd().new Kata();

        int[] dd01 = new int[]{ 7, 3, 2, 8, 5, 4 };
        int[] result = solution.sortArray(dd01);
        for(int i = 0;i < result.length;i ++) {
            System.out.print(result[i] + " ");
        }
    }

    class Kata {

        public int[] sortArray(int[] array) {

            if(array == null || array.length < 2) return array;
            int[] indexs = new int[array.length];

            int j = 0;
            for(int i = 0;i < array.length;i ++) {
                if(array[i] % 2 != 0) indexs[j ++] = i;
            }

            for(int i = 0;i < j;i ++) {
                int y = j - i;
                for(int x = 0;x < y - 1;x ++) {
                    if(array[indexs[x]] > array[indexs[x + 1]]) {
                        int temp = array[indexs[x]];
                        array[indexs[x]] = array[indexs[x + 1]];
                        array[indexs[x + 1]] = temp;
                    }
                }
            }

            return array;
        }
    }

}
