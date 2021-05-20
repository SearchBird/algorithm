package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public enum QEECInfoEnum {
    L1(QRVersionEnum.V1, QRErrorEnum.L, 7, 1, 19, 0, 0),
    M1(QRVersionEnum.V1, QRErrorEnum.M, 10, 1, 16, 0, 0),
    Q1(QRVersionEnum.V1, QRErrorEnum.Q, 13, 1, 13, 0, 0),
    H1(QRVersionEnum.V1, QRErrorEnum.H, 17, 1, 9, 0, 0),

    L2(QRVersionEnum.V2, QRErrorEnum.L, 10, 1, 34, 0, 0),
    M2(QRVersionEnum.V2, QRErrorEnum.M, 16, 1, 28, 0, 0),
    Q2(QRVersionEnum.V2, QRErrorEnum.Q, 22, 1, 22, 0, 0),
    H2(QRVersionEnum.V2, QRErrorEnum.H, 28, 1, 16, 0, 0),

    L3(QRVersionEnum.V3, QRErrorEnum.L, 15, 1, 55, 0, 0),
    M3(QRVersionEnum.V3, QRErrorEnum.M, 26, 1, 44, 0, 0),
    Q3(QRVersionEnum.V3, QRErrorEnum.Q, 18, 2, 17, 0, 0),
    H3(QRVersionEnum.V3, QRErrorEnum.H, 22, 2, 13, 0, 0),

    L4(QRVersionEnum.V4, QRErrorEnum.L, 20, 1, 80, 0, 0),
    M4(QRVersionEnum.V4, QRErrorEnum.M, 18, 2, 32, 0, 0),
    Q4(QRVersionEnum.V4, QRErrorEnum.Q, 26, 2, 24, 0, 0),
    H4(QRVersionEnum.V4, QRErrorEnum.H, 16, 4, 9, 0, 0),

    L5(QRVersionEnum.V5, QRErrorEnum.L, 26, 1, 108, 0, 0),
    M5(QRVersionEnum.V5, QRErrorEnum.M, 24, 2, 43, 0, 0),
    Q5(QRVersionEnum.V5, QRErrorEnum.Q, 18, 2, 15, 2, 16),
    H5(QRVersionEnum.V5, QRErrorEnum.H, 22, 2, 11, 2, 12),

    L6(QRVersionEnum.V6, QRErrorEnum.L, 18, 2, 68, 0, 0),
    M6(QRVersionEnum.V6, QRErrorEnum.M, 16, 4, 27, 0, 0),
    Q6(QRVersionEnum.V6, QRErrorEnum.Q, 24, 4, 19, 0, 0),
    H6(QRVersionEnum.V6, QRErrorEnum.H, 28, 4, 15, 0, 0),

    L7(QRVersionEnum.V7, QRErrorEnum.L, 20, 2, 78, 0, 0),
    M7(QRVersionEnum.V7, QRErrorEnum.M, 18, 4, 31, 0, 0),
    Q7(QRVersionEnum.V7, QRErrorEnum.Q, 18, 2, 14, 4, 15),
    H7(QRVersionEnum.V7, QRErrorEnum.H, 26, 4, 13, 1, 14),

    L8(QRVersionEnum.V8, QRErrorEnum.L, 24, 2, 97, 0, 0),
    M8(QRVersionEnum.V8, QRErrorEnum.M, 22, 2, 38, 2, 39),
    Q8(QRVersionEnum.V8, QRErrorEnum.Q, 22, 4, 18, 2, 19),
    H8(QRVersionEnum.V8, QRErrorEnum.H, 26, 4, 14, 2, 15),

    L9(QRVersionEnum.V9, QRErrorEnum.L, 30, 2, 116, 0, 0),
    M9(QRVersionEnum.V9, QRErrorEnum.M, 22, 3, 36, 2, 37),
    Q9(QRVersionEnum.V9, QRErrorEnum.Q, 20, 4, 16, 4, 17),
    H9(QRVersionEnum.V9, QRErrorEnum.H, 24, 4, 12, 4, 13),

    L10(QRVersionEnum.V10, QRErrorEnum.L, 18, 2, 68, 2, 69),
    M10(QRVersionEnum.V10, QRErrorEnum.M, 26, 4, 43, 1, 44),
    Q10(QRVersionEnum.V10, QRErrorEnum.Q, 24, 6, 19, 2, 20),
    H10(QRVersionEnum.V10, QRErrorEnum.H, 28, 6, 15, 2, 16),

    L11(QRVersionEnum.V11, QRErrorEnum.L, 20, 4, 81, 0, 0),
    M11(QRVersionEnum.V11, QRErrorEnum.M, 30, 1, 50, 4, 51),
    Q11(QRVersionEnum.V11, QRErrorEnum.Q, 28, 4, 22, 4, 23),
    H11(QRVersionEnum.V11, QRErrorEnum.H, 24, 3, 12, 8, 13),

    L12(QRVersionEnum.V12, QRErrorEnum.L, 24, 2, 92, 2, 93),
    M12(QRVersionEnum.V12, QRErrorEnum.M, 22, 6, 36, 2, 37),
    Q12(QRVersionEnum.V12, QRErrorEnum.Q, 26, 4, 20, 6, 21),
    H12(QRVersionEnum.V12, QRErrorEnum.H, 28, 7, 14, 4, 15),

    L13(QRVersionEnum.V13, QRErrorEnum.L, 26, 4, 107, 0, 0),
    M13(QRVersionEnum.V13, QRErrorEnum.M, 22, 8, 37, 1, 38),
    Q13(QRVersionEnum.V13, QRErrorEnum.Q, 24, 8, 20, 4, 21),
    H13(QRVersionEnum.V13, QRErrorEnum.H, 22, 12, 11, 4, 12),

    L14(QRVersionEnum.V14, QRErrorEnum.L, 30, 3, 115, 1, 116),
    M14(QRVersionEnum.V14, QRErrorEnum.M, 24, 4, 40, 5, 41),
    Q14(QRVersionEnum.V14, QRErrorEnum.Q, 20, 11, 16, 5, 17),
    H14(QRVersionEnum.V14, QRErrorEnum.H, 24, 11, 12, 5, 13),

    L15(QRVersionEnum.V15, QRErrorEnum.L, 22, 5, 87, 1, 88),
    M15(QRVersionEnum.V15, QRErrorEnum.M, 24, 5, 41, 5, 42),
    Q15(QRVersionEnum.V15, QRErrorEnum.Q, 30, 5, 24, 7, 25),
    H15(QRVersionEnum.V15, QRErrorEnum.H, 24, 11, 12, 7, 13),

    L16(QRVersionEnum.V16, QRErrorEnum.L, 24, 5, 98, 1, 99),
    M16(QRVersionEnum.V16, QRErrorEnum.M, 28, 7, 45, 3, 46),
    Q16(QRVersionEnum.V16, QRErrorEnum.Q, 24, 15, 19, 2, 20),
    H16(QRVersionEnum.V16, QRErrorEnum.H, 30, 3, 15, 13, 16),

    L17(QRVersionEnum.V17, QRErrorEnum.L, 28, 1, 107, 5, 108),
    M17(QRVersionEnum.V17, QRErrorEnum.M, 28, 10, 46, 1, 47),
    Q17(QRVersionEnum.V17, QRErrorEnum.Q, 28, 1, 22, 15, 23),
    H17(QRVersionEnum.V17, QRErrorEnum.H, 28, 2, 14, 17, 15),

    L18(QRVersionEnum.V18, QRErrorEnum.L, 30, 5, 120, 1, 121),
    M18(QRVersionEnum.V18, QRErrorEnum.M, 26, 9, 43, 4, 44),
    Q18(QRVersionEnum.V18, QRErrorEnum.Q, 28, 17, 22, 1, 23),
    H18(QRVersionEnum.V18, QRErrorEnum.H, 28, 2, 14, 19, 15),

    L19(QRVersionEnum.V19, QRErrorEnum.L, 28, 3, 113, 4, 114),
    M19(QRVersionEnum.V19, QRErrorEnum.M, 26, 3, 44, 11, 45),
    Q19(QRVersionEnum.V19, QRErrorEnum.Q, 26, 17, 21, 4, 22),
    H19(QRVersionEnum.V19, QRErrorEnum.H, 26, 9, 13, 16, 14),

    L20(QRVersionEnum.V20, QRErrorEnum.L, 28, 3, 107, 5, 108),
    M20(QRVersionEnum.V20, QRErrorEnum.M, 26, 3, 41, 13, 42),
    Q20(QRVersionEnum.V20, QRErrorEnum.Q, 30, 15, 24, 5, 25),
    H20(QRVersionEnum.V20, QRErrorEnum.H, 28, 15, 15, 10, 16),

    L21(QRVersionEnum.V21, QRErrorEnum.L, 28, 4, 116, 4, 117),
    M21(QRVersionEnum.V21, QRErrorEnum.M, 26, 17, 42, 0, 0),
    Q21(QRVersionEnum.V21, QRErrorEnum.Q, 28, 17, 22, 6, 23),
    H21(QRVersionEnum.V21, QRErrorEnum.H, 30, 19, 16, 6, 17),

    L22(QRVersionEnum.V22, QRErrorEnum.L, 28, 2, 111, 7, 112),
    M22(QRVersionEnum.V22, QRErrorEnum.M, 28, 17, 46, 0, 0),
    Q22(QRVersionEnum.V22, QRErrorEnum.Q, 30, 7, 24, 16, 25),
    H22(QRVersionEnum.V22, QRErrorEnum.H, 24, 34, 13, 0, 0),

    L23(QRVersionEnum.V23, QRErrorEnum.L, 30, 4, 121, 5, 122),
    M23(QRVersionEnum.V23, QRErrorEnum.M, 28, 4, 47, 14, 48),
    Q23(QRVersionEnum.V23, QRErrorEnum.Q, 30, 11, 24, 14, 25),
    H23(QRVersionEnum.V23, QRErrorEnum.H, 30, 16, 15, 14, 16),

    L24(QRVersionEnum.V24, QRErrorEnum.L, 30, 6, 117, 4, 118),
    M24(QRVersionEnum.V24, QRErrorEnum.M, 28, 6, 45, 14, 46),
    Q24(QRVersionEnum.V24, QRErrorEnum.Q, 30, 11, 24, 16, 25),
    H24(QRVersionEnum.V24, QRErrorEnum.H, 30, 30, 16, 2, 17),

    L25(QRVersionEnum.V25, QRErrorEnum.L, 26, 8, 106, 4, 107),
    M25(QRVersionEnum.V25, QRErrorEnum.M, 28, 8, 47, 13, 48),
    Q25(QRVersionEnum.V25, QRErrorEnum.Q, 30, 7, 24, 22, 25),
    H25(QRVersionEnum.V25, QRErrorEnum.H, 30, 22, 15, 13, 16),

    L26(QRVersionEnum.V26, QRErrorEnum.L, 28, 10, 114, 2, 115),
    M26(QRVersionEnum.V26, QRErrorEnum.M, 28, 19, 46, 4, 47),
    Q26(QRVersionEnum.V26, QRErrorEnum.Q, 28, 28, 22, 6, 23),
    H26(QRVersionEnum.V26, QRErrorEnum.H, 30, 33, 16, 4, 17),

    L27(QRVersionEnum.V27, QRErrorEnum.L, 30, 8, 122, 4, 123),
    M27(QRVersionEnum.V27, QRErrorEnum.M, 28, 22, 45, 3, 46),
    Q27(QRVersionEnum.V27, QRErrorEnum.Q, 30, 8, 23, 26, 24),
    H27(QRVersionEnum.V27, QRErrorEnum.H, 30, 12, 15, 28, 16),

    L28(QRVersionEnum.V28, QRErrorEnum.L, 30, 3, 117, 10, 118),
    M28(QRVersionEnum.V28, QRErrorEnum.M, 28, 3, 45, 23, 46),
    Q28(QRVersionEnum.V28, QRErrorEnum.Q, 30, 4, 24, 31, 25),
    H28(QRVersionEnum.V28, QRErrorEnum.H, 30, 11, 15, 31, 16),

    L29(QRVersionEnum.V29, QRErrorEnum.L, 30, 7, 116, 7, 117),
    M29(QRVersionEnum.V29, QRErrorEnum.M, 28, 21, 45, 7, 46),
    Q29(QRVersionEnum.V29, QRErrorEnum.Q, 30, 1, 23, 37, 24),
    H29(QRVersionEnum.V29, QRErrorEnum.H, 30, 19, 15, 26, 16),

    L30(QRVersionEnum.V30, QRErrorEnum.L, 30, 5, 115, 10, 116),
    M30(QRVersionEnum.V30, QRErrorEnum.M, 28, 19, 47, 10, 48),
    Q30(QRVersionEnum.V30, QRErrorEnum.Q, 30, 15, 24, 25, 25),
    H30(QRVersionEnum.V30, QRErrorEnum.H, 30, 23, 15, 25, 16),

    L31(QRVersionEnum.V31, QRErrorEnum.L, 30, 13, 115, 3, 116),
    M31(QRVersionEnum.V31, QRErrorEnum.M, 28, 2, 46, 29, 47),
    Q31(QRVersionEnum.V31, QRErrorEnum.Q, 30, 42, 24, 1, 25),
    H31(QRVersionEnum.V31, QRErrorEnum.H, 30, 23, 15, 28, 16),

    L32(QRVersionEnum.V32, QRErrorEnum.L, 30, 17, 115, 0, 0),
    M32(QRVersionEnum.V32, QRErrorEnum.M, 28, 10, 46, 23, 47),
    Q32(QRVersionEnum.V32, QRErrorEnum.Q, 30, 10, 24, 35, 25),
    H32(QRVersionEnum.V32, QRErrorEnum.H, 30, 19, 15, 35, 16),

    L33(QRVersionEnum.V33, QRErrorEnum.L, 30, 17, 115, 1, 116),
    M33(QRVersionEnum.V33, QRErrorEnum.M, 28, 14, 46, 21, 47),
    Q33(QRVersionEnum.V33, QRErrorEnum.Q, 30, 29, 24, 19, 25),
    H33(QRVersionEnum.V33, QRErrorEnum.H, 30, 11, 15, 46, 16),

    L34(QRVersionEnum.V34, QRErrorEnum.L, 30, 13, 115, 6, 116),
    M34(QRVersionEnum.V34, QRErrorEnum.M, 28, 14, 46, 23, 47),
    Q34(QRVersionEnum.V34, QRErrorEnum.Q, 30, 44, 24, 7, 25),
    H34(QRVersionEnum.V34, QRErrorEnum.H, 30, 59, 16, 1, 17),

    L35(QRVersionEnum.V35, QRErrorEnum.L, 30, 12, 121, 7, 122),
    M35(QRVersionEnum.V35, QRErrorEnum.M, 28, 12, 47, 26, 48),
    Q35(QRVersionEnum.V35, QRErrorEnum.Q, 30, 39, 24, 14, 25),
    H35(QRVersionEnum.V35, QRErrorEnum.H, 30, 22, 15, 41, 16),

    L36(QRVersionEnum.V36, QRErrorEnum.L, 30, 6, 121, 14, 122),
    M36(QRVersionEnum.V36, QRErrorEnum.M, 28, 6, 47, 34, 48),
    Q36(QRVersionEnum.V36, QRErrorEnum.Q, 30, 46, 24, 10, 25),
    H36(QRVersionEnum.V36, QRErrorEnum.H, 30, 2, 15, 64, 16),

    L37(QRVersionEnum.V37, QRErrorEnum.L, 30, 17, 122, 4, 123),
    M37(QRVersionEnum.V37, QRErrorEnum.M, 28, 29, 46, 14, 47),
    Q37(QRVersionEnum.V37, QRErrorEnum.Q, 30, 49, 24, 10, 25),
    H37(QRVersionEnum.V37, QRErrorEnum.H, 30, 24, 15, 46, 16),

    L38(QRVersionEnum.V38, QRErrorEnum.L, 30, 4, 122, 18, 123),
    M38(QRVersionEnum.V38, QRErrorEnum.M, 28, 13, 46, 32, 47),
    Q38(QRVersionEnum.V38, QRErrorEnum.Q, 30, 48, 24, 14, 25),
    H38(QRVersionEnum.V38, QRErrorEnum.H, 30, 42, 15, 32, 16),

    L39(QRVersionEnum.V39, QRErrorEnum.L, 30, 20, 117, 4, 118),
    M39(QRVersionEnum.V39, QRErrorEnum.M, 28, 40, 47, 7, 48),
    Q39(QRVersionEnum.V39, QRErrorEnum.Q, 30, 43, 24, 22, 25),
    H39(QRVersionEnum.V39, QRErrorEnum.H, 30, 10, 15, 67, 16),

    L40(QRVersionEnum.V40, QRErrorEnum.L, 30, 19, 118, 6, 119),
    M40(QRVersionEnum.V40, QRErrorEnum.M, 28, 18, 47, 31, 48),
    Q40(QRVersionEnum.V40, QRErrorEnum.Q, 30, 34, 24, 34, 25),
    H40(QRVersionEnum.V40, QRErrorEnum.H, 30, 20, 15, 61, 16),
    ;

    private static Map<QRVersionEnum, Map<QRErrorEnum, QEECInfoEnum>> cache = new HashMap<>();

    static {
        for(QEECInfoEnum infoEnum : QEECInfoEnum.values()) {
            QRVersionEnum versionEnum = infoEnum.getVersionEnum();
            Map<QRErrorEnum, QEECInfoEnum> errorMap;
            if(cache.containsKey(versionEnum)) {
                errorMap = cache.get(versionEnum);
            } else {
                errorMap = new HashMap<>();
                cache.put(versionEnum, errorMap);
            }
            errorMap.put(infoEnum.getErrorEnum(), infoEnum);
        }
    }

    public static void main(String[] args) throws Exception {
        int dis = 0;
        File file = new File("E:\\glib\\algorithm\\src\\org\\tjw\\codewar\\Trainning\\kyu4\\QRCode\\errorInfo.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        for(int i = 1; i < 41; i ++) {
            for(QRErrorEnum errorEnum : QRErrorEnum.values()) {
                if(dis == 4) {
                    System.out.println(); dis = 0;
                }
                String str = br.readLine();
                while(str == null) str = br.readLine();
                String[] infoStrs = str.split("\\s+");

                try{
                    System.out.println(errorEnum.getEnName() + i + "(QRVersionEnum.V" + i
                            + ", ErrorEnum." + errorEnum.getEnName()
                            + ", " + infoStrs[2] + ", "
                            + infoStrs[3] + ", "
                            + infoStrs[4] + ", "
                            + (infoStrs.length > 6 ? (infoStrs[5] + ", " + infoStrs[6]) : "0, 0")
                            + "),");

                } catch ( Exception e) {
                    System.out.println(i + errorEnum.getEnName());
                }

                dis ++;
            }
        }
    }

    public static QEECInfoEnum getECInfo(QRVersionEnum versionEnum, QRErrorEnum errorEnum) {
        return cache.containsKey(versionEnum) ? cache.get(versionEnum).get(errorEnum) : null;
    }

    QEECInfoEnum(QRVersionEnum versionEnum, QRErrorEnum errorEnum, int ECNum, int g1b, int g1bNum, int g2b, int g2bNum) {
        this.versionEnum = versionEnum;
        this.errorEnum = errorEnum;
        this.totalData = g1b * g1bNum + g2b * g2bNum;
        this.ECNum = ECNum;
        this.g1b = g1b;
        this.g1bNum = g1bNum;
        this.g2b = g2b;
        this.g2bNum = g2bNum;
    }

    private QRVersionEnum versionEnum;
    private QRErrorEnum errorEnum;
    private int totalData;
    private int ECNum;
    private int g1b;
    private int g1bNum;
    private int g2b;
    private int g2bNum;

    public QRVersionEnum getVersionEnum() {
        return versionEnum;
    }

    public void setVersionEnum(QRVersionEnum versionEnum) {
        this.versionEnum = versionEnum;
    }

    public QRErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(QRErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public int getTotalData() {
        return totalData;
    }

    public void setTotalData(int totalData) {
        this.totalData = totalData;
    }

    public int getECNum() {
        return ECNum;
    }

    public void setECNum(int ECNum) {
        this.ECNum = ECNum;
    }

    public int getG1b() {
        return g1b;
    }

    public void setG1b(int g1b) {
        this.g1b = g1b;
    }

    public int getG1bNum() {
        return g1bNum;
    }

    public void setG1bNum(int g1bNum) {
        this.g1bNum = g1bNum;
    }

    public int getG2b() {
        return g2b;
    }

    public void setG2b(int g2b) {
        this.g2b = g2b;
    }

    public int getG2bNum() {
        return g2bNum;
    }

    public void setG2bNum(int g2bNum) {
        this.g2bNum = g2bNum;
    }
}
