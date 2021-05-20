package org.tjw.codewar.Trainning.kyu4.QRCode.QREnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public enum QRAlpEnum {
    ALP_0('0', 0),
    ALP_1('1', 1),
    ALP_2('2', 2),
    ALP_3('3', 3),
    ALP_4('4', 4),
    ALP_5('5', 5),
    ALP_6('6', 6),
    ALP_7('7', 7),
    ALP_8('8', 8),
    ALP_9('9', 9),
    ALP_10('A', 10),
    ALP_11('B', 11),
    ALP_12('C', 12),
    ALP_13('D', 13),
    ALP_14('E', 14),
    ALP_15('F', 15),
    ALP_16('G', 16),
    ALP_17('H', 17),
    ALP_18('I', 18),
    ALP_19('J', 19),
    ALP_20('K', 20),
    ALP_21('L', 21),
    ALP_22('M', 22),
    ALP_23('N', 23),
    ALP_24('O', 24),
    ALP_25('P', 25),
    ALP_26('Q', 26),
    ALP_27('R', 27),
    ALP_28('S', 28),
    ALP_29('T', 29),
    ALP_30('U', 30),
    ALP_31('V', 31),
    ALP_32('W', 32),
    ALP_33('X', 33),
    ALP_34('Y', 34),
    ALP_35('Z', 35),
    ALP_36(' ', 36),
    ALP_37('$', 37),
    ALP_38('%', 38),
    ALP_39('*', 39),
    ALP_40('+', 40),
    ALP_41('-', 41),
    ALP_42('.', 42),
    ALP_43('/', 43),
    ALP_44(':', 44),
    ;
    QRAlpEnum(char ch, int code) {
        this.ch = ch;
        this.code =code;
    }

    private char ch;
    private int code;

    private static Map<Character, QRAlpEnum> cache = new HashMap<>();
    static {
        for(QRAlpEnum alpEnum : QRAlpEnum.values()) {
            cache.put(alpEnum.getCh(), alpEnum);
        }
    }

    public static QRAlpEnum getEnum(char ch) {
        return cache.get(ch);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("E:\\glib\\algorithm\\src\\org\\tjw\\codewar\\Trainning\\kyu4\\QRCode\\QRAlpTable.txt"));
        String str;
        while((str = reader.readLine()) != null) {
            String[] sps = str.split("\\s+");
            System.out.println("ALP_" + sps[1]
                    + "('"
                    + (sps[0].isEmpty()? " " : sps[0])
                    + "', "
                    + sps[1]
                    + "),");
        }
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
