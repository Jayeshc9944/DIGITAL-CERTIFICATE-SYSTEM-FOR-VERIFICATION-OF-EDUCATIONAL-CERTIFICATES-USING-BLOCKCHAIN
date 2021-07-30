/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qrcode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.JavaX;
import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
public class Read_QR {
    public static void main(String[] args) throws WriterException, IOException,
        NotFoundException {
    	String data=readQR("K:\\QRCode\\Raj\\Raj.png");
    	System.out.println(data);
//            try {
//                String filePath = "F:\\BE2019-2020\\Coding50%\\QRCode\\Raj\\Raj.png";
//                String charset = "UTF-8";
//                Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
//                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
//                System.out.println("Data read from QR Code: " + readQRCode(filePath, charset, hintMap));
//            } catch (NotFoundException | IOException e) {
//                System.out.println(e);
//            }
        }
    public static String readQR(String filePath)
    {
    	String textdata="";
    	try {
          
          String charset = "UTF-8";
          Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
          hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
          JavaX.initComponents();
          System.out.println("Data read from QR Code: " + readQRCode(filePath, charset, hintMap));
          textdata=readQRCode(filePath, charset, hintMap);
      } catch (NotFoundException | IOException e) {
          System.out.println(e);
      }
    	return textdata;
    }
    public static String readQRCode(String filePath, String charset, Map hintMap)
    throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
            new BufferedImageLuminanceSource(
                ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
        return qrCodeResult.getText();
    }
}

