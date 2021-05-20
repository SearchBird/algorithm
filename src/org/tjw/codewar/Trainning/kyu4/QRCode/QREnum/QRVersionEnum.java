package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.util.HashMap;

public enum QRVersionEnum {
    V1(1,"1", 21, "21"),
    V2(2,"2", 25, "25"),
    V3(3,"3", 29, "29"),
    V4(4,"4", 33, "33"),
    V5(5,"5", 37, "37"),
    V6(6,"6", 41, "41"),
    V7(7,"7", 45, "45"),
    V8(8,"8", 49, "49"),
    V9(9,"9", 53, "53"),
    V10(10,"10", 57, "57"),
    V11(11,"11", 61, "61"),
    V12(12,"12", 65, "65"),
    V13(13,"13", 69, "69"),
    V14(14,"14", 73, "73"),
    V15(15,"15", 77, "77"),
    V16(16,"16", 81, "81"),
    V17(17,"17", 85, "85"),
    V18(18,"18", 89, "89"),
    V19(19,"19", 93, "93"),
    V20(20,"20", 97, "97"),
    V21(21,"21", 101, "101"),
    V22(22,"22", 105, "105"),
    V23(23,"23", 109, "109"),
    V24(24,"24", 113, "113"),
    V25(25,"25", 117, "117"),
    V26(26,"26", 121, "121"),
    V27(27,"27", 125, "125"),
    V28(28,"28", 129, "129"),
    V29(29,"29", 133, "133"),
    V30(30,"30", 137, "137"),
    V31(31,"31", 141, "141"),
    V32(32,"32", 145, "145"),
    V33(33,"33", 149, "149"),
    V34(34,"34", 153, "153"),
    V35(35,"35", 157, "157"),
    V36(36,"36", 161, "161"),
    V37(37,"37", 165, "165"),
    V38(38,"38", 169, "169"),
    V39(39,"39", 173, "173"),
    V40(40,"40", 177, "177"),
    ;
    private static HashMap<Integer, QRVersionEnum> cache = new HashMap<>();

    static {
        for(QRVersionEnum qr : QRVersionEnum.values())
            cache.put(qr.getVersion(), qr);
    }

    QRVersionEnum(int version, String versionStr, int size, String sizeStr) {
        this.version = version;
        this.versionStr = versionStr;
        this.size = size;
        this.sizeStr = sizeStr;
    }
    private int version;
    private String versionStr;
    private int size;
    private String sizeStr;

    public static void main(String[] args) {
        for(int i = 1; i <= 40; i ++) {
            System.out.println("V" + i
                    + "("
                    + i + ", \""
                    + i + "\", "
                    + String.valueOf(21 + 4 * (i - 1)) + ", \""
                    + String.valueOf(21 + 4 * (i - 1)) + "\"),");
        }
    }

    public static QRVersionEnum getQRVersion(Integer version) {
        return cache.get(version);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getVersionStr() {
        return versionStr;
    }

    public void setVersionStr(String versionStr) {
        this.versionStr = versionStr;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSizeStr() {
        return sizeStr;
    }

    public void setSizeStr(String sizeStr) {
        this.sizeStr = sizeStr;
    }
}
