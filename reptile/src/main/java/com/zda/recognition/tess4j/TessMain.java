package com.zda.recognition.tess4j;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TessMain {
    public static void main(String [] args){

        try {
            File imageFile = new File("E:\\bigData\\workspace\\imgs\\esik.png");
            Tesseract instance = new Tesseract();
            instance.setDatapath("E:\\bigData\\workspace\\reptile\\tessdata");
            instance.setLanguage("eng");
            //将验证码图片的内容识别为字符串
            String result = instance.doOCR(imageFile);
                System.out.println("图片名：" + imageFile.getName() +" 识别结果："+result);

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
    }
}
