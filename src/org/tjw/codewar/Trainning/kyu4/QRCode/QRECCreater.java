package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRErrorEnum;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QEECInfoEnum;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRVersionEnum;

import java.util.*;

public class QRECCreater {

    // 256 以内进行 反 log 运算表, 取模求得
    private static int[] alpExp = new int[]
            {-1, 0, 1, 25, 2, 50, 26, 198, 3, 223, 51, 238, 27, 104, 199, 75, 4, 100, 224, 14, 52, 141, 239,
            129, 28, 193, 105, 248, 200, 8, 76, 113, 5, 138, 101, 47, 225, 36, 15, 33, 53, 147, 142, 218, 240, 18, 130,
            69, 29, 181, 194, 125, 106, 39, 249, 185, 201, 154, 9, 120, 77, 228, 114, 166, 6, 191, 139, 98, 102, 221, 48,
            253, 226, 152, 37, 179, 16, 145, 34, 136, 54, 208, 148, 206, 143, 150, 219, 189, 241, 210, 19, 92, 131, 56, 70, 64, 30, 66, 182, 163, 195,
            72, 126, 110, 107, 58, 40, 84, 250, 133, 186, 61, 202, 94, 155, 159, 10, 21, 121, 43, 78, 212, 229, 172, 115, 243, 167, 87, 7, 112, 192,
            247, 140, 128, 99, 13, 103, 74, 222, 237, 49, 197, 254, 24, 227, 165, 153, 119, 38, 184, 180, 124, 17, 68, 146, 217, 35, 32, 137, 46, 55,
            63, 209, 91, 149, 188, 207, 205, 144, 135, 151, 178, 220, 252, 190, 97, 242, 86, 211, 171, 20, 42, 93, 158, 132, 60, 57, 83, 71, 109, 65, 162,
            31, 45, 67, 216, 183, 123, 164, 118, 196, 23, 73, 236, 127, 12, 111, 246, 108, 161, 59, 82, 41, 157, 85, 170, 251, 96, 134, 177, 187, 204, 62,
            90, 203, 89, 95, 176, 156, 169, 160, 81, 11, 245, 22, 235, 122, 117, 44, 215, 79, 174, 213, 233, 230, 231, 173, 232, 116, 214, 244, 234, 168, 80, 88, 175},
    // 2 的指数表
            alpExpVal = new int[256];
    static {
        alpExpVal[0] = 1;
        for(int i = 1; i < 256; i ++) {
            alpExpVal[i] = alpExpVal[i - 1] << 1;
            alpExpVal[i] = alpExpVal[i] > 255 ? alpExpVal[i] ^ 285 : alpExpVal[i];
        }
    }

    public static void main(String[] args) {
        String test = "00100000010110110000101101111000110100010111001011011100010011010100001101000000111011000001000111101100000100011110110000010001";
        QEECInfoEnum infoEnum = QEECInfoEnum.getECInfo(QRVersionEnum.V1, QRErrorEnum.M);
        // 信息多项式测试
        List<List<Integer>> msgList = genMsgPolynomial(test, infoEnum.getECNum());
        for(int i = 0, len = msgList.get(0).size(); i < len; i ++) {
            System.out.print(msgList.get(0).get(i) + "x" + msgList.get(1).get(i) + "+");
        }
        System.out.println();
        // 多项式相乘测试
        List<List<Integer>> alpList = genPolynomial(infoEnum.getECNum(), msgList);
        for(int i = 0, len = alpList.get(0).size(); i < len; i ++) {
            System.out.print("a" + alpList.get(0).get(i) + "x" + alpList.get(1).get(i) + "+");
        }

        createErrorCorrection(test, QRVersionEnum.V1, QRErrorEnum.M);
    }

    /**
     * 生成纠错码多项式, byteSeq 必须是 8 的倍数
     */
    public static List<Integer> createErrorCorrection(String byteSeq, QRVersionEnum versionEnum, QRErrorEnum errorEnum) {
        QEECInfoEnum infoEnum = QEECInfoEnum.getECInfo(versionEnum, errorEnum);
        if(infoEnum == null) return null;

        // MsgPolynomial
        List<List<Integer>> msgP = genMsgPolynomial(byteSeq, infoEnum.getECNum());

        // GenPolynomial
        List<List<Integer>> genP = genPolynomial(infoEnum.getECNum(), msgP);

        // Loop
        for(int r = 0, times = msgP.get(0).size(); r < times; r ++) {
            // 首项指数
            int exp = alpExp[msgP.get(0).get(0)];
            List<Integer> genList = new ArrayList<>(genP.get(0));
            List<Integer> msgList = msgP.get(0);

            for(int i = 0, len = genList.size(), len2 = msgList.size(); i < len; i ++) {
                // 1、首项指数放入系数中相乘
                genList.set(i, (genList.get(i) + exp) % 255);
                // 2、通过指数转为数字
                genList.set(i, alpExpVal[genList.get(i)]);
                if(i >= len2) {
                    msgList.add(genList.get(i));
                } else {
                    // 3、对信息多项式进行异或
                    int xor = genList.get(i) ^ msgList.get(i);
                    msgList.set(i, xor);
                }

            }
            while(msgList.get(0) == 0) msgList.remove(0);
        }

        return msgP.get(0);
    }

    /**
     * 生成信息多项式
     */
    public static List<List<Integer>> genMsgPolynomial(String byteSeq, int ecNum) {
        TreeMap<Integer, Integer> msgP = new TreeMap<>((o1, o2) -> {return o2 - o1;});
        for(int i = 0, len = byteSeq.length() / 8; i < len; i ++) {
            int seq = QRUtil.converBitToInt(byteSeq.substring(i * 8, (i + 1) * 8));
            // 进行误码数量相乘
            msgP.put(len - i - 1 + ecNum, seq);
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        while(!msgP.isEmpty()) {
            Map.Entry<Integer, Integer> entry = msgP.pollFirstEntry();
            res.get(0).add(entry.getValue());
            res.get(1).add(entry.getKey());
        }
        return res;
    }

    /**
     * 多项式相乘 (x - a0)(x - a1)......(x - a(n - 1))
     */
    public static List<List<Integer>> genPolynomial(int ECNum, List<List<Integer>> msgP) {
        int cur = 0, pre = 1;
        // 指数 - 系数
        Map<Integer, Integer>[] alpMap = new Map[]{new HashMap<Integer, Integer>(), new HashMap<Integer, Integer>()};
        alpMap[cur].put(1, 0);
        alpMap[cur].put(0, 0);
        for(int i = 1; i < ECNum; i ++) {
            int[][] alp = new int[][]{{1, 0}, {0, i}};
            for(Map.Entry<Integer, Integer> entry : alpMap[cur].entrySet()) {
                for(int j = 0; j < alp.length; j ++) {
                    int key = entry.getKey() + alp[j][0];
                    int val = (entry.getValue() + alp[j][1]) % 255; // 限制 256 区域 Galois Field

                    if(alpMap[pre].containsKey(key))
                        val = alpExpVal[alpMap[pre].get(key)] ^ alpExpVal[val];
                    else
                        val = alpExpVal[val];
                    alpMap[pre].put(key, alpExp[val]);
                }
            }
            alpMap[cur].clear();
            cur ^= 1;
            pre ^= 1;
        }
        TreeMap<Integer, Integer> res = new TreeMap<>((o1, o2) -> {return o2 - o1;});
        res.putAll(alpMap[cur]);

        // 最高项指数一致
        int dis = msgP.get(1).get(0) - res.firstKey();
        if(dis > 0) {
            TreeMap<Integer, Integer> resNew = new TreeMap<>((o1, o2) -> {return o2 - o1;});
            while(!res.isEmpty()) {
                Map.Entry<Integer, Integer> entry = res.pollFirstEntry();
                resNew.put(entry.getKey() + dis, entry.getValue());
            }
            res = resNew;
        }

        List<List<Integer>> resList = new ArrayList<>();
        resList.add(new ArrayList<>());
        resList.add(new ArrayList<>());
        while(!res.isEmpty()) {
            Map.Entry<Integer, Integer> entry = res.pollFirstEntry();
            resList.get(0).add(entry.getValue());
            resList.get(1).add(entry.getKey());
        }

        return resList;
    }


}
