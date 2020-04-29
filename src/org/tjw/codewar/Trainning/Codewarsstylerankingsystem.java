package org.tjw.codewar.Trainning;

public class Codewarsstylerankingsystem {

    public static void main(String[] args) {
        User user = new Codewarsstylerankingsystem().new User();
        System.out.println(user.rank); // => -8
        System.out.println(user.progress); // => 0
        user.incProgress(-7);
        System.out.println(user.progress); // => 10
        user.incProgress(-5); // will add 90 progress
        System.out.println(user.progress); // => 0 // progress is now zero
        System.out.println(user.rank); // => -7 // ra
    }

    class User {
        public int rank = -8;
        public int progress = 0;
        private int[] pros = new int[]{1, 3, 10};

        public void incProgress(int rank) {
            System.out.println(rank);

            if(rank < -8 || rank == 0 || rank > 8) throw new IllegalArgumentException();

            if(rank > 0) rank -= 1;
            int temp = this.rank;
            if(temp > 0) temp -= 1;
            if(rank < temp - 1) return;

            int dis = rank - temp + 1;
            int d = 1;
            if(dis > 1) {
                d = dis - 1;
                dis = 2;
            }

            progress += pros[dis] * d * d;

            if(progress >= 100) {
                int pos = temp + progress / 100;
                temp = pos > 7 ? 7 : pos;
                if(temp >= 0) this.rank = temp + 1;
                else this.rank = temp;
            }

            progress %= 100;
            if(this.rank == 8) progress = 0;
        }
    }
}
