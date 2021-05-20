package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.*;

import java.util.ArrayList;
import java.util.List;

public class QRStructureMSG {

    public static void main(String[] args) {
        String res = struct("0100001101010101010001101000011001010111001001100101010111000010011101110011001000000110000100100000011001100111001001101111011011110110010000100000011101110110100001101111001000000111001001100101011000010110110001101100011110010010000001101011011011100110111101110111011100110010000001110111011010000110010101110010011001010010000001101000011010010111001100100000011101000110111101110111011001010110110000100000011010010111001100101110000011101100000100011110110000010001111011000001000111101100", QRVersionEnum.V5, QRErrorEnum.Q);
        new DrawUtil().new QRDraw(QRPlacer.createQRPlace(res, QRVersionEnum.V5, QRErrorEnum.Q,QRMaskPattern.M0));

    }

    public static String struct(String bitText, QRVersionEnum versionEnum, QRErrorEnum errorEnum) {
        QEECInfoEnum infoEnum = QEECInfoEnum.getECInfo(versionEnum, errorEnum);
        if(infoEnum == null) return "";

        List<List<Integer>> groupList01 = new ArrayList<>(), groupList02 = new ArrayList<>();
        // group 1
        String[] group01 = getBlockGroup(bitText, 0, infoEnum.getG1b(), infoEnum.getG1bNum());
        for(int i = 0, len = group01.length; i < len; i ++) {
            groupList01.add(QRECCreater.createErrorCorrection(group01[i], versionEnum, errorEnum));
        }

        // group 2
        String[] group02 = getBlockGroup(bitText, infoEnum.getG1b() * infoEnum.getG1bNum() * 8, infoEnum.getG2b(), infoEnum.getG2bNum());
        for(int i = 0, len = group02.length; i < len; i ++) {
            groupList02.add(QRECCreater.createErrorCorrection(group02[i], versionEnum, errorEnum));
        }

        List<Integer> byteList = interleaveCodeAndConnect(group01, group02, groupList01, groupList02);
        bitText = QRUtil.converteToBit(byteList);
        bitText = contactRemain(bitText, versionEnum);
        return bitText;
    }

    private static String contactRemain(String bitText, QRVersionEnum versionEnum) {
        StringBuilder builder = new StringBuilder(bitText);
        QRRemainEnum remainEnum = QRRemainEnum.getRemainEnum(Integer.valueOf(versionEnum.getVersion()));
        for(int i = 0, len = remainEnum.getRequiredBit(); i < len; i ++) {
            builder.append("0");
        }
        return builder.toString();
    }

    private static List<Integer> interleaveCodeAndConnect(String[] group01, String[] group02, List<List<Integer>> groupList01, List<List<Integer>> groupList02) {
        List<Integer> resList = new ArrayList<>();

        int len1 = group01[0].length() / 8, len2 = group02.length == 0 ? 0 : group02[0].length() / 8,
            max = Math.max(len1, len2);
        for(int i = 0; i < max; i ++) {
            if(i < len1) {
                for(int j = 0, blcok = group01.length; j < blcok; j ++)
                    resList.add(QRUtil.converBitToInt(group01[j].substring(i * 8, (i + 1) * 8)));
            }
            if(i < len2) {
                for(int j = 0, blcok = group02.length; j < blcok; j ++)
                    resList.add(QRUtil.converBitToInt(group02[j].substring(i * 8, (i + 1) * 8)));
            }
        }

        len1 = groupList01.get(0).size();
        if(!groupList02.isEmpty()) len2 = groupList02.get(0).size();
        max = Math.max(len1, len2);
        for(int i = 0; i < max; i ++) {
            if(i < len1) {
                for(int j = 0, blcok = groupList01.size(); j < blcok; j ++)
                    resList.add(groupList01.get(j).get(i));
            }
            if(i < len2) {
                for(int j = 0, blcok = groupList02.size(); j < blcok; j ++)
                    resList.add(groupList02.get(j).get(i));
            }
        }

        return resList;
    }

    private static String[] getBlockGroup(String byteText, int begin, int blockNum, int blockNumByte) {
        String[] res = new String[blockNum];
        for(int i = 0, len = res.length; i < len; i ++) {
            res[i] = byteText.substring(i * 8 * blockNumByte + begin, (i + 1) * 8 * blockNumByte + begin);
        }
        return res;
    }
}
