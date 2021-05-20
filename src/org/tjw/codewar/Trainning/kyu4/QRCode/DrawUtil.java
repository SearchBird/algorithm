package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRErrorEnum;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRMaskPattern;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRVersionEnum;

import javax.swing.*;
import java.awt.*;

public class DrawUtil {

    public static void main(String[] args) {
        DrawUtil util = new DrawUtil();
        util.new QRDraw(CreatetheQRCode.createQrCode("Hello"));
        QRVersionEnum versionEnum = QRVersionEnum.V5;
//        util.new QRDraw(QRPlacer.createQRPlace("01000011111101101011011001000110010101011111011011100110111101110100011001000010111101110111011010000110000001110111011101010110010101110111011000110010110000100010011010000110000001110000011001010101111100100111011010010111110000100000011110000110001100100111011100100110010101111110000000110010010101100010011011101100000001100001011001010010000100010001001011000110000001101110110000000110110001111000011000010001011001111001001010010111111011000010011000000110001100100001000100000111111011001101010101010111100101001000110011000111110011000111010001100100000010110110000010110001111110100010110100111100110101001111011101110011110010100100110001101100111101111011011010000101100000111111000101111100010010110010010111011111100111011111001001101000111001011100100011101110111111011111100010000110010011000111000110011010000110111100001101101111011101011000000111100110111010111001101011010001101111011100010101101111000100010000101001010011010101101010001101101100000001101010000110100011111100001100110101101111011110001100000001011001001001111000010110001101010010100000000",
//                versionEnum,
//                QRErrorEnum.M,
//                QRMaskPattern.M4));
    }

    class QRDraw extends JFrame {

        private int bx = 80, by = 80; // 图层初始定位
        private int w = 8;
        private int qrLen;
        private Graphics jg;

        private Color rectColor = Color.WHITE;

        public QRDraw(int[][] qrMatirx) {
            if(qrMatirx.length > 100) w = 5;
            Container p = getContentPane();
            qrLen = qrMatirx.length * w;
            setBounds(bx, by, qrLen + (bx << 1), qrLen + (by << 1));
            setVisible(true);
            p.setBackground(rectColor);
            setLayout(null);
            setResizable(false);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }

            jg =  this.getGraphics();
            paintComponents(jg, qrMatirx);
        }

        public void paintComponents(Graphics g, int[][] qrMatirx) {
            try {

                // 绘制外层矩形框
                //g.drawRect(bx, by, qrLen, qrLen);

                for(int i = 0, lenx = qrMatirx.length; i < lenx; i ++) {
                    for(int j = 0, leny = qrMatirx[0].length; j < leny; j ++) {
                        switch(qrMatirx[i][j]) {
                            case 1 : g.setColor(Color.BLACK); break;
                            case 2 : g.setColor(Color.WHITE); break;
                            case 3 : g.setColor(Color.BLUE); break;
                            default: continue;
                        }
                        g.fillRect(by + (j * w), bx + (i * w), w, w);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
