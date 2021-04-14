package org.tjw.codewar.Trainning.kyu4.bwt;

import java.util.LinkedList;
import java.util.List;

public class Movetofront {

    public static void main(String[] args) {
        int[] rese = encode(BurrowsWheeler.encode("banbanaba"));
        for(int i : rese) System.out.print(i + " ");
        System.out.println();
        System.out.println(decode(rese));
    }

    private static List<Character> aToz = new LinkedList<Character>();

    public static int[] encode(BurrowsWheeler.BWT bwt) {
        recover();
        String str = bwt.res;
        int len = str.length();
        char[] chs = str.toCharArray();
        int[] res = new int[str.length()];
        for(int i = 0;i < len;i ++)
            aToz.add(0, aToz.remove((res[i] = aToz.indexOf(chs[i]))));
        return res;
    }

    public static String decode(int[] arr) {
        recover();
        StringBuilder builder = new StringBuilder();
        for(int i : arr) {
            builder.append(aToz.get(i));
            aToz.add(0, aToz.remove(i));
        }
        return builder.toString();
    }

    private static void recover() {
        aToz.clear();
        for(int i = 0;i <= 255 ;i ++)
            aToz.add((char)i);
    }
}
