package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public enum QRVersionInfo {
    V7(7, "000111110010010100"),
    V8(8, "001000010110111100"),
    V9(9, "001001101010011001"),
    V10(10, "001010010011010011"),
    V11(11, "001011101111110110"),
    V12(12, "001100011101100010"),
    V13(13, "001101100001000111"),
    V14(14, "001110011000001101"),
    V15(15, "001111100100101000"),
    V16(16, "010000101101111000"),
    V17(17, "010001010001011101"),
    V18(18, "010010101000010111"),
    V19(19, "010011010100110010"),
    V20(20, "010100100110100110"),
    V21(21, "010101011010000011"),
    V22(22, "010110100011001001"),
    V23(23, "010111011111101100"),
    V24(24, "011000111011000100"),
    V25(25, "011001000111100001"),
    V26(26, "011010111110101011"),
    V27(27, "011011000010001110"),
    V28(28, "011100110000011010"),
    V29(29, "011101001100111111"),
    V30(30, "011110110101110101"),
    V31(31, "011111001001010000"),
    V32(32, "100000100111010101"),
    V33(33, "100001011011110000"),
    V34(34, "100010100010111010"),
    V35(35, "100011011110011111"),
    V36(36, "100100101100001011"),
    V37(37, "100101010000101110"),
    V38(38, "100110101001100100"),
    V39(39, "100111010101000001"),
    V40(40, "101000110001101001"),
    ;

    QRVersionInfo(int version, String versionInfoStr) {
        this.version = version;
        this.versionInfoStr = versionInfoStr;
    }

    public static void main(String[] args) throws  Exception {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\glib\\algorithm\\src\\org\\tjw\\codewar\\Trainning\\kyu4\\QRCode\\versionInfo.txt"));
        String str;
        while((str = reader.readLine()) != null) {
            String[] infos = str.split("\\s+");
            System.out.println("V" + infos[0] + "(" + infos[0] + ", \"" + infos[1] + "\"),");
        }
    }

    private static Map<Integer, QRVersionInfo> cache = new HashMap<>();

    static {
        for(QRVersionInfo versionInfo : QRVersionInfo.values()) {
            cache.put(versionInfo.getVersion(), versionInfo);
        }
    }

    public static QRVersionInfo getEnum(int version) {
        return cache.get(version);
    }

    private int version;
    private String versionInfoStr;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getVersionInfoStr() {
        return versionInfoStr;
    }

    public void setVersionInfoStr(String versionInfoStr) {
        this.versionInfoStr = versionInfoStr;
    }
}
