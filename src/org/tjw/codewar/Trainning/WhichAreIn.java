package org.tjw.codewar.Trainning;

/**
 * 这条叼题目是：要你识别每一个字符串包含哪个a，然后输出时候，不要重复，还要按字符的ascii大小排序
 */
public class WhichAreIn {

    public static void main(String[] args) {

        String a[] = new String[]{ "cod","code","wars","ewar","ar" };
        String b[] = new String[] { "lively","alive","harp","sharp","armstrong","codewars" };

        String[] str = inArray(a,b);

        for(int i = 0;i < str.length;i ++) {
            System.out.print(str[i] + " ");
        }

    }

    public static String[] inArray(String[] array1, String[] array2) {
        if(array1 == null || array2 == null || array1.length == 0 || array2.length == 0) return new String[]{};

        int count = 0;
        String[] res1 = new String[array1.length];

        for(int i = 0;i < array2.length;i ++) {
            String temp = array2[i];
            for(int j = 0;j < array1.length;j ++) {
                if(array1[j] != null && temp.indexOf(array1[j]) != -1) {
                    res1[j] = array1[j];
                    array1[j] = null;
                    count ++;
                }
            }
        }

        String[] res2 = new String[count];
        for (int i = array1.length;i -- > 0;) {
            if(res1[i] != null) res2[-- count] = res1[i];
        }

        for(int i = 0;i < res2.length - 1;i ++) {
            for(int j = 0;j < res2.length - i - 1;j ++) {
                String str1 = res2[j];
                String str2 = res2[j + 1];
                int len2 = str1.length() < str2.length() ? str1.length() : str2.length();
                for(int x = 0;x < len2;x ++) {
                    int de = str1.charAt(x) - str2.charAt(x);
                    if(de > 0) {
                        res2[j] = str2;
                        res2[j + 1] = str1;
                        break;
                    } else if(de < 0) break;
                }
            }
        }

        return res2;
    }
}
