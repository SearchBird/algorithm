package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRAlpEnum;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRMaskPattern;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRTextModEnum;

import java.util.List;


public class QRUtil {

    public static int converBitToInt(String bits) {
        int total = 0, len = bits.length();
        for(int i = 0; i < len; i ++) {
            char ch = bits.charAt(7 - i);
            if(ch != '0') {
                total += 1 << i;
            }
        }
        return total;
    }

    public static String converteToBit(List<Integer> text) {
        StringBuilder builder = new StringBuilder();
        for(int ch : text)
            builder.append(String.format("%08d", Integer.valueOf(Integer.toBinaryString(ch))));
        return builder.toString();
    }

    public static boolean maskCalculate(QRMaskPattern maskPattern, int x, int y) {
        switch (maskPattern) {
            case M0 : return ((x + y) & 1) == 0;
            case M1 : return (x & 1) == 0;
            case M2 : return y % 3 == 0;
            case M3 : return (x + y) % 3 == 0;
            case M4 : return (x >> 1) + y / 3 % 2 == 0;
            case M5 : return (x * y) % 2 + (x * y) % 3 == 0;
            case M6 : return ((x * y) % 2 + (x * y) % 3) % 2 == 0;
            case M7 : return (x + y) % 2 + (x * y) % 3 == 0;
            default: return false;
        }
    }

    public static String converteToBitByMod(String text, QRTextModEnum textModEnum) {
        StringBuilder builder = new StringBuilder();
        StringBuilder appender = new StringBuilder();
        switch (textModEnum) {
            case NUMERIC: {
                int i = 0, len = text.length();
                for(; i + 3 <= len; i += 3) {
                    int num = Integer.valueOf(text.substring(i, i + 3));
                    if(num / 100 != 0)
                        builder.append(String.format("%010d", Integer.valueOf(Integer.toBinaryString(num))));
                    else if(num / 10 != 0)
                        builder.append(String.format("%07d", Integer.valueOf(Integer.toBinaryString(num))));
                    else
                        builder.append(String.format("%04d", Integer.valueOf(Integer.toBinaryString(num))));
                }
                if(i != len) {
                    int num = Integer.valueOf(text.substring(i, len));
                    if(num / 10 != 0)
                        builder.append(String.format("%07d", Integer.valueOf(Integer.toBinaryString(num))));
                    else
                        builder.append(String.format("%04d", Integer.valueOf(Integer.toBinaryString(num))));
                }
                return builder.toString();
            }
            case ALPHANUMERIC: {
                char[] chs = text.toCharArray();
                for(int i = 1, len = chs.length; i < len; i += 2) {
                    appender.append(Integer.toBinaryString(QRAlpEnum.getEnum(chs[i - 1]).getCode() * 45 + QRAlpEnum.getEnum(chs[i]).getCode()));
                    while(appender.length() != 11) appender.insert(0, "0");
                    builder.append(appender);
                    appender.setLength(0);
                }
                if((chs.length ^ 1) != 0) {
                    appender.append(Integer.toBinaryString(QRAlpEnum.getEnum(chs[chs.length - 1]).getCode()));
                    while(appender.length() != 6) appender.insert(0, "0");
                    builder.append(appender);
                }
                return builder.toString();
            }
            case BYTE: {
                for(char ch : text.toCharArray())
                    builder.append(String.format("%08d", Integer.valueOf(Integer.toBinaryString((int)ch))));
                return builder.toString();
            }
            default : return "";
        }
    }
}
