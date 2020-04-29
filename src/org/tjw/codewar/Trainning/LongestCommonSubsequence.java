package org.tjw.codewar.Trainning;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        System.out.println(Lcs.lcs("nothardlythefinaltest", "zzzfinallyzzz"));
        System.out.println(Lcs.lcs("a", "a"));
        System.out.println(Lcs.lcs("abcdefghijklmnopq", "apcdefghijklmnobq"));
    }


    static class Lcs {
        static String lcs(String a, String b) {
            if(a == null || b == null || a.equals("") || b.equals("")) return "";
            int alen = a.length();
            int blen = b.length();
            String[][] dp = new String[blen + 1][alen + 1];
            StringBuilder builder = new StringBuilder();

            for (int i = blen; i -- > 0;) {
                for (int j = alen; j -- > 0;) {
                    if (a.charAt(j) == b.charAt(i)) {
                        builder.append(a.charAt(j));
                        if(dp[i + 1][j + 1] != null) builder.append(dp[i + 1][j + 1]);
                        dp[i][j] = builder.toString();
                        builder.setLength(0);
                    }else {
                        if(dp[i + 1][j] == null) dp[i + 1][j] = "";
                        if(dp[i][j + 1] == null) dp[i][j + 1] = "";
                        dp[i][j] = dp[i + 1][j].length() < dp[i][j + 1].length()
                                ? builder.append(dp[i][j + 1]).toString() : builder.append(dp[i + 1][j]).toString();
                        builder.setLength(0);
                    }
                }
            }

            return dp[0][0]; // do it!
        }
    }
}
