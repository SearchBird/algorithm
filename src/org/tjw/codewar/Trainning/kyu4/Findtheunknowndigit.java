package org.tjw.codewar.Trainning.kyu4;

import java.util.Arrays;

public class Findtheunknowndigit {

    public static void main(String[] args) {
        System.out.println(solveExpression("123?45*?=?"));
    }

    private static int index = 0;
    private static boolean noZero = false;
    private static boolean[] tenFlag = new boolean[10];

    public static int solveExpression( final String exp ) {
        index = 0;
        noZero = false;
        Arrays.fill(tenFlag, false);
        String[] strs = splitChs(exp);

        char sig = exp.charAt(index);
        for(char unknow = noZero ? '1' : '0'; unknow <= '9'; unknow ++) {
            if(tenFlag[unknow - '0']) continue;
            String[] newStrs = Arrays.copyOf(strs, 3);
            for(int i = 0; i < 3; i ++) newStrs[i] = newStrs[i].replaceAll("\\?", String.valueOf(unknow));
            if(sig == '*') {
                if(Long.valueOf(newStrs[0]) * Long.valueOf(newStrs[1]) == Long.valueOf(newStrs[2])) return unknow - '0';
            }
            else if(sig == '-') {
                if(Long.valueOf(newStrs[0]) - Long.valueOf(newStrs[1]) == Long.valueOf(newStrs[2])) return unknow - '0';
            }
            else
                if(Long.valueOf(newStrs[0]) + Long.valueOf(newStrs[1]) == Long.valueOf(newStrs[2])) return unknow - '0';
        }
        return -1;
    }

    public static String[] splitChs(String exp) {
        if(exp.indexOf('*') > 0) {
            index = exp.indexOf('*');
            String[] left = exp.split("\\*");
            String[] right = left[1].split("=");

            String[] res = new String[]{left[0], right[0], right[1]};
            return replaceSig(res);
        } else {
            boolean findNum = false;
            for(int len = exp.length(); index < len; index ++) {
                char ch = exp.charAt(index);
                if(index > 0 && findNum && (ch == '+' || ch == '-')) {
                    String[] right = exp.substring(index + 1, len).split("=");
                    String[] res = new String[]{exp.substring(0, index), right[0], right[1]};
                    return replaceSig(res);
                }
                if(isNum(exp.charAt(index))) findNum = true;
            }
        }
        return null;
    }

    public static String[] replaceSig(String[] strs) {
        for(int i = 0; i < 3; i ++) {
            String str = strs[i].replaceAll("\\+", "");
            int strSig = countSub(str);
            String first = str.replaceAll("-", "");
            if(first.startsWith("?") && first.length() > 1) noZero = true;
            for(char ch : first.toCharArray())
                if(ch != '?') tenFlag[ch - '0'] = true;
            str = ((strSig & 1) == 0 ? "" : "-") + first;
            strs[i] = str;
        }
        return strs;
    }

    public static boolean isNum(char ch) {
        return ch == '?' || (ch >= '0' && ch <= '9');
    }

    public static int countSub(String str) {
        int total = 0;
        for(char ch : str.toCharArray())
            if(ch == '-') total ++;
        return total;
    }

}
