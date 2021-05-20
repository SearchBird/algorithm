package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 对齐图案坐标类
 */
public enum  QRAlignmentEnum {

    V2(2, new int[]{6, 18}),
    V3(3, new int[]{6, 22}),
    V4(4, new int[]{6, 26}),
    V5(5, new int[]{6, 30}),
    V6(6, new int[]{6, 34}),
    V7(7, new int[]{6, 22, 38}),
    V8(8, new int[]{6, 24, 42}),
    V9(9, new int[]{6, 26, 46}),
    V10(10, new int[]{6, 28, 50}),
    V11(11, new int[]{6, 30, 54}),
    V12(12, new int[]{6, 32, 58}),
    V13(13, new int[]{6, 34, 62}),
    V14(14, new int[]{6, 26, 46, 66}),
    V15(15, new int[]{6, 26, 48, 70}),
    V16(16, new int[]{6, 26, 50, 74}),
    V17(17, new int[]{6, 30, 54, 78}),
    V18(18, new int[]{6, 30, 56, 82}),
    V19(19, new int[]{6, 30, 58, 86}),
    V20(20, new int[]{6, 34, 62, 90}),
    V21(21, new int[]{6, 28, 50, 72, 94}),
    V22(22, new int[]{6, 26, 50, 74, 98}),
    V23(23, new int[]{6, 30, 54, 78, 102}),
    V24(24, new int[]{6, 28, 54, 80, 106}),
    V25(25, new int[]{6, 32, 58, 84, 110}),
    V26(26, new int[]{6, 30, 58, 86, 114}),
    V27(27, new int[]{6, 34, 62, 90, 118}),
    V28(28, new int[]{6, 26, 50, 74, 98, 122}),
    V29(29, new int[]{6, 30, 54, 78, 102, 126}),
    V30(30, new int[]{6, 26, 52, 78, 104, 130}),
    V31(31, new int[]{6, 30, 56, 82, 108, 134}),
    V32(32, new int[]{6, 34, 60, 86, 112, 138}),
    V33(33, new int[]{6, 30, 58, 86, 114, 142}),
    V34(34, new int[]{6, 34, 62, 90, 118, 146}),
    V35(35, new int[]{6, 30, 54, 78, 102, 126, 150}),
    V36(36, new int[]{6, 24, 50, 76, 102, 128, 154}),
    V37(37, new int[]{6, 28, 54, 80, 106, 132, 158}),
    V38(38, new int[]{6, 32, 58, 84, 110, 136, 162}),
    V39(39, new int[]{6, 26, 54, 82, 110, 138, 166}),
    V40(40, new int[]{6, 30, 58, 86, 114, 142, 170});

    QRAlignmentEnum(int version, int[] site) {
        this.version = version;
        this.site = site;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\glib\\algorithm\\src\\org\\tjw\\codewar\\Trainning\\kyu4\\QRCode\\alignment.txt"));
        String str;
        while((str = reader.readLine()) != null) {
            String[] ps = str.split("\\s+");
            StringBuilder builder = new StringBuilder();
            for(int i = 3; i < ps.length; i ++) builder.append(ps[i]).append(", ");
            builder.setLength(builder.length() - 2);
            System.out.println("V" + ps[2] + "(" + ps[2] + ", new int[]{" + builder.toString() + "}),");
        }
    }

    private static Map<Integer, QRAlignmentEnum> cache = new HashMap<>();
    static {
        for(QRAlignmentEnum alignmentEnum : QRAlignmentEnum.values()) {
            cache.put(alignmentEnum.getVersion(), alignmentEnum);
        }
    }

    public static QRAlignmentEnum getQRAlignmentEnum(QRVersionEnum versionEnum) {
        return cache.get(versionEnum.getVersion());
    }

    private int version;
    private int[] site;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int[] getSite() {
        return site;
    }

    public void setSite(int[] site) {
        this.site = site;
    }
}
