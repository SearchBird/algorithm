package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

public enum QRMaskPattern {

    M0(0),
    M1(1),
    M2(2),
    M3(3),
    M4(4),
    M5(5),
    M6(6),
    M7(7),
    ;
    QRMaskPattern(int type) {
        this.type = type;
    }
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
