package org.tjw.codewar.Trainning.kyu4.bwt;

import java.io.BufferedReader;
import java.io.FileReader;

public class BWTTest {
    public static void main(String[] args) throws Throwable {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\glib\\algorithm\\src\\org\\tjw\\codewar\\Trainning\\kyu4\\bwt\\data\\data02"));
        String temp;
        StringBuilder builder = new StringBuilder();
        while((temp = reader.readLine()) != null) {
            builder.append(temp.trim());
        }
        String read = builder.toString();
        System.out.println(read);

        HuffmanCode.TreeNode hTree = new HuffmanCode.TreeNode(-1, -1); // 哈夫曼码表
        byte[] bArr = read.getBytes();
        for(byte i : bArr) System.out.print(Integer.toBinaryString(i));
        System.out.println();
        BurrowsWheeler.BWT bwt = BurrowsWheeler.encode(read);
        System.out.println(bwt);
        int[] mtfRes = Movetofront.encode(bwt);
        for(int i : mtfRes) System.out.print(i + " ");
        System.out.println();
        // 需要判断是否使用游程编码，否则可能变长
        int[] rlcRes = RunLengthCode.encode(mtfRes);
        for(int i : rlcRes) System.out.print(i + " ");
        System.out.println();
        boolean isRLC = rlcRes.length < mtfRes.length;
        String res;
        if(isRLC)
            res = HuffmanCode.encode(rlcRes, hTree);
        else
            res = HuffmanCode.encode(mtfRes, hTree);
        System.out.print(res);
        System.out.println();
        System.out.println();

        // 解码
        // 解哈夫曼
        int[] deArr = HuffmanCode.decode(res, hTree);
        for(int i : deArr) System.out.print(i + " ");
        System.out.println();
        // 解词频
        int[] marr;
        if(isRLC) {
            marr = RunLengthCode.decode(deArr);
            for(int i : marr) System.out.print(i + " ");
            System.out.println();
        } else marr = deArr;
        // 解mtf
        String mStr = Movetofront.decode(marr);
        System.out.println(mStr);
        // 解bwt
        String dRes = BurrowsWheeler.decode(mStr, bwt.index);
        System.out.println(dRes);
    }
}
