package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.*;

public class QRDataEncoding {

    private static String[] extData = new String[]{"11101100", "00010001"};

    public static String dataEncode(String text, QRTextModEnum textModEnum, QRVersionEnum versionEnum, QRErrorEnum errorEnum) {
        StringBuilder textBitsBuilder = new StringBuilder();
        // 1、选择模式
        textBitsBuilder.append(textModEnum.getCode());

        // 2、模式需要长度
        QRCountLenEnum countLenEnum = QRCountLenEnum.getCountLenEnum(versionEnum, textModEnum);
        int countlen = countLenEnum.getCountLen() + 4;
        textBitsBuilder.append(Integer.toBinaryString(text.length()));
        while(textBitsBuilder.length() != countlen) textBitsBuilder.insert(4, "0");

        textBitsBuilder.append(QRUtil.converteToBitByMod(text, textModEnum));
        textBitsBuilder.append("0000"); // 拼接终结符
        QEECInfoEnum ECInfoEnum = QEECInfoEnum.getECInfo(versionEnum, errorEnum);
        int len = ECInfoEnum.getTotalData() * 8;
        if(textBitsBuilder.length() > len) textBitsBuilder.setLength(len);
        while(textBitsBuilder.length() % 8 != 0) textBitsBuilder.append("0");

        int index = 0; // 反复填充
        while(textBitsBuilder.length() < len) {
            textBitsBuilder.append(extData[index]);
            index ^= 1;
        }
        textBitsBuilder.setLength(len);
        return textBitsBuilder.toString();
    }
}
