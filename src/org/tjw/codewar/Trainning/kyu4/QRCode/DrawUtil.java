package org.tjw.codewar.Trainning.kyu4.QRCode;

import javax.swing.*;
import java.awt.*;

public class DrawUtil {

    public static void main(String[] args) {
        new QRDraw(CreatetheQRCode.createQrCode("Hello"));
    }

    static class QRDraw extends JFrame {

        private static final int bx = 40, by = 40; // 图层初始定位
        private static final int w = 20;
        private static final int qrLen = 420;
        private Graphics jg;

        private Color rectColor = new Color(0xf5f5f5);

        public QRDraw(int[][] qrMatirx) {
            Container p = getContentPane();
            setBounds(100, 100, 500, 500);
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
                g.setColor(Color.BLACK);

                // 绘制外层矩形框
                g.drawRect(bx, by, qrLen, qrLen);

                for(int i = 0; i < 21; i ++) {
                    for(int j = 0; j < 21; j ++) {
                        if(qrMatirx[i][j] == 1)
                            g.fillRect(bx + (i * w), by + (j * w), w, w);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
