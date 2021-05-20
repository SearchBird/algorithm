package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRErrorEnum;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRMaskPattern;

public class QRFormatAndVersionInfo {

    private static String generator = "10100110111";

    public static void main(String[] args) {
        // 异或测试
        String res = XOR("11", "10");
        System.out.println(res);

        res = formatInfo(QRErrorEnum.L, QRMaskPattern.M4);
        System.out.println(res);
    }

    public static String formatInfo(QRErrorEnum errorEnum, QRMaskPattern maskPattern) {
        StringBuilder msgBuilder = new StringBuilder();
        StringBuilder genBuilder = new StringBuilder(generator);

        msgBuilder.append(Integer.toBinaryString(maskPattern.getType()));
        while(msgBuilder.length() != 3) msgBuilder.insert(0, "0");
        msgBuilder.insert(0, errorEnum.getBits());
        String formatStr = msgBuilder.toString();

        while(msgBuilder.length() != 15) msgBuilder.append("0"); // 加成 15 个
        // 有可能 误码等级 + 遮罩都是 000000
        while(msgBuilder.length() > 0 && msgBuilder.charAt(0) == '0') msgBuilder.deleteCharAt(0); // 去掉头部 0

        while(msgBuilder.length() != 10) {
            if(msgBuilder.length() < 10) {
                while(msgBuilder.length() != 10 ) msgBuilder.insert(0, "0");
                break;
            }
            while(genBuilder.length() != msgBuilder.length()) genBuilder.append("0");
            String res = XOR(genBuilder.toString(), msgBuilder.toString());
            msgBuilder.setLength(0);
            msgBuilder.append(res);
            genBuilder.setLength(0);
            genBuilder.append(generator);
            while(msgBuilder.charAt(0) == '0') msgBuilder.deleteCharAt(0); // 去掉头部 0
        }

        msgBuilder.insert(0, formatStr); // 最后做格式化信息异或
        String res = XOR(msgBuilder.toString(), "101010000010010");
        return res;
    }

    public static String XOR(String s1, String s2) {
        if(s1.length() != s2.length()) return "";
        char[] chars1 = s1.toCharArray(), chars2 = s2.toCharArray(), res = new char[chars1.length];
        for(int i = 0, len = chars1.length; i < len; i ++) {
            res[i] = (char)(((chars1[i] - '0') ^ (chars2[i] - '0')) + '0');
        }
        return new String(res);
    }
}
