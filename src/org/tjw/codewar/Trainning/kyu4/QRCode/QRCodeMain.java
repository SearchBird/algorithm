package org.tjw.codewar.Trainning.kyu4.QRCode;

import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRErrorEnum;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRMaskPattern;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRTextModEnum;
import org.tjw.codewar.Trainning.kyu4.QRCode.QREnum.QRVersionEnum;


public class QRCodeMain {
    // 只实现到V4
    public static void main(String[] args) {
        QRVersionEnum versionEnum = QRVersionEnum.V1;
        QRTextModEnum textModEnum = QRTextModEnum.BYTE;
        QRMaskPattern maskPattern = QRMaskPattern.M0;
        QRErrorEnum errorEnum = QRErrorEnum.H;
        String byteSeq = QRDataEncoding.dataEncode("t", textModEnum, versionEnum, errorEnum);
        String structMsg = QRStructureMSG.struct(byteSeq, versionEnum, errorEnum);
        new DrawUtil().new QRDraw(QRPlacer.createQRPlace(structMsg, versionEnum, errorEnum, maskPattern));
    }
}
