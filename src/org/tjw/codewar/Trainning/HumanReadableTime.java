package org.tjw.codewar.Trainning;

public class HumanReadableTime {

    public static void main(String[] args) {
        HumanReadableTime2 solution = new HumanReadableTime().new HumanReadableTime2();
        System.out.print(solution.makeReadable(86399));
    }

    class HumanReadableTime2 {
        public String makeReadable(int seconds) {
            // Do something

            int ss = seconds % 60;
            int mm = seconds / 60 % 60;
            int hh = seconds / 60 / 60;

            return String.format("%02d", hh) + ":" + String.format("%02d", mm) + ":" + String.format("%02d", ss);
        }
    }

}
