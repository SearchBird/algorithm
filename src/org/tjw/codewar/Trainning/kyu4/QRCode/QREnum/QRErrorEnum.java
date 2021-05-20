package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.util.HashMap;
import java.util.Map;

public enum QRErrorEnum {
    L("L", "01", 1), M("M", "00", 0), Q("Q", "11", 3), H("H", "10", 2);
    QRErrorEnum(String enName, String bits, int equivalent) {
        this.enName = enName;
        this.bits = bits;
        this.equivalent = equivalent;
    }

    private static Map<String, QRErrorEnum> cache = new HashMap<>();
    static {
        for(QRErrorEnum errorEnum : QRErrorEnum.values()) {
            cache.put(errorEnum.getEnName(), errorEnum);
        }
    }

    public static QRErrorEnum getErrorEnum(String lev) {
        return cache.get(lev);
    }

    private String enName;
    private String bits;
    private int equivalent;

    public int getEquivalent() {
        return equivalent;
    }

    public void setEquivalent(int equivalent) {
        this.equivalent = equivalent;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getBits() {
        return bits;
    }

    public void setBits(String bits) {
        this.bits = bits;
    }
}
