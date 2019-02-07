package com.springcloud.weibo.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VerifyCode {
    private static int imageWidth = 100;
    private static int imageHeight = 30;
    private static String fontCode = "23456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM"; // 汉子范围 //
    // \u4e00-\u9fa5
    private static int[] fontStyle = {0, 1, 2, 4};
    private static int[] RGB = new int[3];
    private static String verifyCode = null;

    public static String getVerifyString() {
        return VerifyCode.verifyCode;

    }

    public static BufferedImage getBufferedImage() throws IOException {

        BufferedImage bImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2d = (Graphics2D) bImage.getGraphics();
        drawBackground(graphics2d);
        drawLine(graphics2d);
        drawString(graphics2d);
        return bImage;
    }

    private static void drawBackground(Graphics2D graphics2d) {
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0, 0, imageWidth, imageHeight);
    }

    private static void drawLine(Graphics2D graphics2d) {
        for (int i = 0; i < 4; i++) {
            setRGB(RGB);
            graphics2d.setColor(new Color(RGB[0], RGB[1], RGB[2]));
            int y1 = (int) (Math.random() * (imageHeight - 2));
            int y2 = (int) (Math.random() * (imageHeight - 2));
            graphics2d.drawLine(1, y1, imageWidth - 2, y2);
        }
    }

    private static void drawString(Graphics2D graphics2d) {
        int len = fontCode.length();
        String string = "";
        for (int i = 0; i < 4; i++) {
            setRGB(RGB);
            graphics2d.setColor(new Color(RGB[0], RGB[1], RGB[2]));
            int style = (int) (Math.random() * 4);
            graphics2d.setFont(new Font("宋体", fontStyle[style], 30));
            char str = fontCode.charAt((int) (Math.random() * len));
            graphics2d.drawString("" + str, i * 25 + 3, 25);
            string += str;
        }
        setVerifyCode(string);
    }

    private synchronized static void setVerifyCode(String verifyCode) {
        VerifyCode.verifyCode = verifyCode;
    }

    private static void setRGB(int[] rGB) {
        for (int i = 0; i < rGB.length; i++) {
            rGB[i] = (int) (Math.random() * 250) + 3;
        }
        RGB = rGB;
    }
}
