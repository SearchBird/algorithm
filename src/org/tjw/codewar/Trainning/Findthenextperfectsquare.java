package org.tjw.codewar.Trainning;


public class Findthenextperfectsquare {


    public static void main(String[] args) {
        NumberFun solution = new Findthenextperfectsquare().new NumberFun();
        System.out.print(solution.findNextSquare(15241630849L));
    }

    class NumberFun {
        public long findNextSquare(long sq) {

            long left = 0;
            long right = sq;

            while(left <= right) {
                long mid = left + right >> 1;
                long mid2 = mid * mid;

                if(mid2 == sq) {
                    return (mid + 1) * (mid + 1);
                } else if(mid2 < sq && mid2 > 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return -1;
        }
    }
}
