package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

public enum QRTextModEnum {

    NUMERIC("Numeric", "0001", "数字"),
    ALPHANUMERIC("Alphanumeric", "0010", "字母"),
    BYTE("Byte", "0100", "字节"),
    KANJI("Kanji", "1000", "汉字"),
    ECI("ECI", "0111", "适用UTF-8"),
    ;

    QRTextModEnum(String name, String code, String cnName) {
        this.name = name;
        this.code = code;
        this.cnName = cnName;
    }
    private String name;
    private String code;
    private String cnName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
