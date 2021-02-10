package org.tjw.loj.datastruct;

import java.io.*;

public class treeArray_130 {
    static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int n = getNext(), q = getNext();
        long[] arr = new long[n + 1];
        for(int i = 1;i <= n;i ++) update(arr, n, i, getNext());
        while(q -- > 0) {
            in.nextToken();
            switch ((int) in.nval) {
                case 1:
                    update(arr, n, getNext(), getNextLong()); break;
                case 2:
                    out.println(- getSum(arr, getNext() - 1) + getSum(arr, getNext()));
                    out.flush();
            }
        }
        out.close();
    }
    public static int getNext() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }
    public static long getNextLong() throws IOException {
        in.nextToken();
        return (long) in.nval;
    }
    public static int lowbit(int i) {
        return i & -i;
    }
    public static void update(long[] arr, int n, int i, long v) {
        while(i <= n) {
            arr[i] += v;
            i += lowbit(i);
        }
    }
    public static long getSum(long[] arr, int i) {
        long count = 0;
        while(i > 0) {
            count += arr[i];
            i -= lowbit(i);
        }
        return count;
    }

    public static void testCase() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("E:\\glib\\algorithm\\src\\org\\tjw\\loj\\datastruct\\data\\treeArray_130_2.in")));
        BufferedReader reader2 = new BufferedReader(new FileReader(new File("E:\\glib\\algorithm\\src\\org\\tjw\\loj\\datastruct\\data\\treeArray_130_2.out")));

        String[] strs = reader.readLine().split("\\s");
        int n = Integer.valueOf(strs[0]), q = Integer.valueOf(strs[1]), ii = 0, index = 0;
        long[] arr = new long[n + 1];
        String[] outs = new String[q + 1];

        String str;
        while((str = reader2.readLine()) != null)
            outs[ii ++] = str;

        strs = reader.readLine().split("\\s");
        for(int i = 1;i <= n;i ++) {
            update(arr, n, i, Integer.valueOf(strs[i - 1]));
        }
        StringBuilder builder = new StringBuilder();
        while((str = reader.readLine()) != null) {
            strs = str.split("\\s");
            switch (Integer.valueOf(strs[0])) {
                case 1:
                    update(arr, n, Integer.valueOf(strs[1]), Integer.valueOf(strs[2])); break;
                case 2:
                    builder.append(- getSum(arr, Integer.valueOf(strs[1]) - 1) + getSum(arr, Integer.valueOf(strs[2])) + "\r\n");
            }
        }
        System.out.println(builder.toString());
    }
}

class Hepler {

}
