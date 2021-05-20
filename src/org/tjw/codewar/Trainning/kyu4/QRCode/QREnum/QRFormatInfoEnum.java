package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 目前已经有算法得出，所以这个表格暂存
 */
public enum QRFormatInfoEnum {

    L0(QRErrorEnum.L, QRMaskPattern.M0, "111011111000100"),
    L1(QRErrorEnum.L, QRMaskPattern.M1, "111001011110011"),
    L2(QRErrorEnum.L, QRMaskPattern.M2, "111110110101010"),
    L3(QRErrorEnum.L, QRMaskPattern.M3, "111100010011101"),
    L4(QRErrorEnum.L, QRMaskPattern.M4, "110011000101111"),
    L5(QRErrorEnum.L, QRMaskPattern.M5, "110001100011000"),
    L6(QRErrorEnum.L, QRMaskPattern.M6, "110110001000001"),
    L7(QRErrorEnum.L, QRMaskPattern.M7, "110100101110110"),
    M0(QRErrorEnum.M, QRMaskPattern.M0, "101010000010010"),
    M1(QRErrorEnum.M, QRMaskPattern.M1, "101000100100101"),
    M2(QRErrorEnum.M, QRMaskPattern.M2, "101111001111100"),
    M3(QRErrorEnum.M, QRMaskPattern.M3, "101101101001011"),
    M4(QRErrorEnum.M, QRMaskPattern.M4, "100010111111001"),
    M5(QRErrorEnum.M, QRMaskPattern.M5, "100000011001110"),
    M6(QRErrorEnum.M, QRMaskPattern.M6, "100111110010111"),
    M7(QRErrorEnum.M, QRMaskPattern.M7, "100101010100000"),
    Q0(QRErrorEnum.Q, QRMaskPattern.M0, "011010101011111"),
    Q1(QRErrorEnum.Q, QRMaskPattern.M1, "011000001101000"),
    Q2(QRErrorEnum.Q, QRMaskPattern.M2, "011111100110001"),
    Q3(QRErrorEnum.Q, QRMaskPattern.M3, "011101000000110"),
    Q4(QRErrorEnum.Q, QRMaskPattern.M4, "010010010110100"),
    Q5(QRErrorEnum.Q, QRMaskPattern.M5, "010000110000011"),
    Q6(QRErrorEnum.Q, QRMaskPattern.M6, "010111011011010"),
    Q7(QRErrorEnum.Q, QRMaskPattern.M7, "010101111101101"),
    H0(QRErrorEnum.H, QRMaskPattern.M0, "001011010001001"),
    H1(QRErrorEnum.H, QRMaskPattern.M1, "001001110111110"),
    H2(QRErrorEnum.H, QRMaskPattern.M2, "001110011100111"),
    H3(QRErrorEnum.H, QRMaskPattern.M3, "001100111010000"),
    H4(QRErrorEnum.H, QRMaskPattern.M4, "000011101100010"),
    H5(QRErrorEnum.H, QRMaskPattern.M5, "000001001010101"),
    H6(QRErrorEnum.H, QRMaskPattern.M6, "000110100001100"),
    H7(QRErrorEnum.H, QRMaskPattern.M7, "000100000111011"),
    ;
    QRFormatInfoEnum(QRErrorEnum errorEnum, QRMaskPattern maskPattern, String typeBits) {
        this.errorEnum = errorEnum;
        this.maskPattern = maskPattern;
        this.typeBits = typeBits;
    }

    private static Map<QRErrorEnum, Map<QRMaskPattern, QRFormatInfoEnum>> cache = new HashMap<>();

    static {
        for(QRFormatInfoEnum formatInfoEnum : QRFormatInfoEnum.values()) {
            if(cache.containsKey(formatInfoEnum.getErrorEnum()))
                cache.get(formatInfoEnum.getErrorEnum()).put(formatInfoEnum.getMaskPattern(), formatInfoEnum);
            else {
                Map<QRMaskPattern, QRFormatInfoEnum> map = new HashMap<>();
                map.put(formatInfoEnum.getMaskPattern(), formatInfoEnum);
                cache.put(formatInfoEnum.getErrorEnum(), map);
            }
        }
    }

    public static QRFormatInfoEnum getEnum(QRErrorEnum errorEnum, QRMaskPattern maskPattern) {
        Map<QRMaskPattern, QRFormatInfoEnum> map = cache.get(errorEnum);
        if(map != null) return map.get(maskPattern);
        return null;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\glib\\algorithm\\src\\org\\tjw\\codewar\\Trainning\\kyu4\\QRCode\\formatInfo.txt"));

        String str;
        while((str = reader.readLine()) != null) {
            String[] infos = str.split("\\s+");
            System.out.println(infos[0] + infos[1] + "(QRErrorEnum." + infos[0] + ", QRMaskPattern.M" + infos[1] + ", \"" + infos[2] + "\"),");
        }
        reader.close();
    }



    private QRErrorEnum errorEnum;
    private QRMaskPattern maskPattern;
    private String typeBits;

    public QRErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(QRErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public QRMaskPattern getMaskPattern() {
        return maskPattern;
    }

    public void setMaskPattern(QRMaskPattern maskPattern) {
        this.maskPattern = maskPattern;
    }

    public String getTypeBits() {
        return typeBits;
    }

    public void setTypeBits(String typeBits) {
        this.typeBits = typeBits;
    }
}
