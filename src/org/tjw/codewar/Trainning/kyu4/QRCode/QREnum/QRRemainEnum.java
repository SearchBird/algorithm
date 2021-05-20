package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public enum QRRemainEnum {
    V1(1, 0),
    V2(2, 7),
    V3(3, 7),
    V4(4, 7),
    V5(5, 7),
    V6(6, 7),
    V7(7, 0),
    V8(8, 0),
    V9(9, 0),
    V10(10, 0),
    V11(11, 0),
    V12(12, 0),
    V13(13, 0),
    V14(14, 3),
    V15(15, 3),
    V16(16, 3),
    V17(17, 3),
    V18(18, 3),
    V19(19, 3),
    V20(20, 3),
    V21(21, 4),
    V22(22, 4),
    V23(23, 4),
    V24(24, 4),
    V25(25, 4),
    V26(26, 4),
    V27(27, 4),
    V28(28, 3),
    V29(29, 3),
    V30(30, 3),
    V31(31, 3),
    V32(32, 3),
    V33(33, 3),
    V34(34, 3),
    V35(35, 0),
    V36(36, 0),
    V37(37, 0),
    V38(38, 0),
    V39(39, 0),
    V40(40, 0),
    ;

    QRRemainEnum(int version, int requiredBit) {
        this.version = version;
        this.requiredBit = requiredBit;
    }

    private static Map<Integer, QRRemainEnum> cache = new HashMap<>();

    static {
        for(QRRemainEnum remainEnum : QRRemainEnum.values()) {
            cache.put(remainEnum.getVersion(), remainEnum);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\glib\\algorithm\\src\\org\\tjw\\codewar\\Trainning\\kyu4\\QRCode\\remain.txt"));

        String str;
        while((str = reader.readLine()) != null) {
            String[] ps = str.split("\\s+");
            System.out.println("V" + ps[0] + "(" + ps[0] + ", " + ps[1] + "),");
        }

        reader.close();
    }

    public static QRRemainEnum getRemainEnum(int version) {
        return cache.get(version);
    }

    private int version;
    private int requiredBit;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getRequiredBit() {
        return requiredBit;
    }

    public void setRequiredBit(int requiredBit) {
        this.requiredBit = requiredBit;
    }
}
