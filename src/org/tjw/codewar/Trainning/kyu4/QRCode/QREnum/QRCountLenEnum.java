package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum QRCountLenEnum {

    RANK1T9_NUMERIC(1, 9, 10, QRTextModEnum.NUMERIC),
    RANK1T9_ALPHANUMERIC(1, 9, 9, QRTextModEnum.ALPHANUMERIC),
    RANK1T9_BYTE(1, 9, 8, QRTextModEnum.BYTE),
    RANK1T9_KANJI(1, 9, 8, QRTextModEnum.KANJI),

    RANK10T26_NUMERIC(10, 26, 12, QRTextModEnum.NUMERIC),
    RANK10T26_ALPHANUMERIC(10, 26, 11, QRTextModEnum.ALPHANUMERIC),
    RANK10T26_BYTE(10, 26, 16, QRTextModEnum.BYTE),
    RANK10T26_KANJI(10, 26, 10, QRTextModEnum.KANJI),

    RANK27T40_NUMERIC(27, 40, 14, QRTextModEnum.NUMERIC),
    RANK27T40_ALPHANUMERIC(27, 40, 13, QRTextModEnum.ALPHANUMERIC),
    RANK27T40_BYTE(27, 40, 16, QRTextModEnum.BYTE),
    RANK27T40_KANJI(27, 40, 12, QRTextModEnum.KANJI),
    ;

    QRCountLenEnum(int lessVersion, int lastVersion, int countLen, QRTextModEnum textModEnum) {
        this.lessVersion = lessVersion;
        this.lastVersion = lastVersion;
        this.countLen = countLen;
        this.textModEnum = textModEnum;
    };
    private int lessVersion;
    private int lastVersion;
    private int countLen;
    private QRTextModEnum textModEnum;

    private static HashMap<QRTextModEnum, List<QRCountLenEnum>> cache = new HashMap<>();

    static{
        for(QRCountLenEnum countLenEnum : QRCountLenEnum.values()) {
            if(cache.containsKey(countLenEnum.getTextModEnum())) {
                cache.get(countLenEnum.getTextModEnum()).add(countLenEnum);
            } else {
                List<QRCountLenEnum> list = new ArrayList<>();
                list.add(countLenEnum);
                cache.put(countLenEnum.getTextModEnum(), list);
            }
        }
    }

    public static QRCountLenEnum getCountLenEnum(QRVersionEnum versionEnum, QRTextModEnum textModEnum) {
        List<QRCountLenEnum> list = cache.get(textModEnum);
        int version = versionEnum.getVersion();
        for(QRCountLenEnum countLenEnum : list) {
            if(version >= countLenEnum.getLessVersion() && version <= countLenEnum.getLastVersion()) return countLenEnum;
        }
        return null;
    }

    public int getLessVersion() {
        return lessVersion;
    }

    public void setLessVersion(int lessVersion) {
        this.lessVersion = lessVersion;
    }

    public int getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(int lastVersion) {
        this.lastVersion = lastVersion;
    }

    public int getCountLen() {
        return countLen;
    }

    public void setCountLen(int countLen) {
        this.countLen = countLen;
    }

    public QRTextModEnum getTextModEnum() {
        return textModEnum;
    }

    public void setTextModEnum(QRTextModEnum textModEnum) {
        this.textModEnum = textModEnum;
    }
}
