package com.liuhg.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class CreateValidateCode {
    // 鍥剧墖鐨勫搴︺�
    private int width = 160;
    // 鍥剧墖鐨勯珮搴︺�
    private int height = 40;
    // 楠岃瘉鐮佸瓧绗︿釜鏁�
    private int codeCount = 4;
    // 楠岃瘉鐮佸共鎵扮嚎鏁�
    private int lineCount = 20;
    // 楠岃瘉鐮�
    private String code = null;
    // 楠岃瘉鐮佸浘鐗嘊uffer
    private BufferedImage buffImg = null;
    Random random = new Random();

    public CreateValidateCode() {
        creatImage();
    }

    public CreateValidateCode(int width, int height) {
        this.width = width;
        this.height = height;
        creatImage();
    }

    public CreateValidateCode(int width, int height, int codeCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        creatImage();
    }

    public CreateValidateCode(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        creatImage();
    }

    // 鐢熸垚鍥剧墖
    private void creatImage() {
        int fontWidth = width / codeCount;// 瀛椾綋鐨勫搴�
        int fontHeight = height - 5;// 瀛椾綋鐨勯珮搴�
        int codeY = height - 8;

        // 鍥惧儚buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = buffImg.getGraphics();
        // Graphics2D g = buffImg.createGraphics();
        // 璁剧疆鑳屾櫙鑹�
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 璁剧疆瀛椾綋
        // Font font1 = getFont(fontHeight);
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        g.setFont(font);

        // 璁剧疆骞叉壈绾�
        for (int i = 0; i < lineCount; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            g.setColor(getRandColor(1, 255));
            g.drawLine(xs, ys, xe, ye);
        }

        // 娣诲姞鍣偣
        float yawpRate = 0.01f;// 鍣０鐜�
        int area = (int) (yawpRate * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);

            buffImg.setRGB(x, y, random.nextInt(255));
        }

        String str1 = randomStr(codeCount);// 寰楀埌闅忔満瀛楃
        this.code = str1;
        for (int i = 0; i < codeCount; i++) {
            String strRand = str1.substring(i, i + 1);
            g.setColor(getRandColor(1, 255));
            // g.drawString(a,x,y);
            // a涓鸿鐢诲嚭鏉ョ殑涓滆タ锛寈鍜寉琛ㄧず瑕佺敾鐨勪笢瑗挎渶宸︿晶瀛楃鐨勫熀绾夸綅浜庢鍥惧舰涓婁笅鏂囧潗鏍囩郴鐨�(x, y) 浣嶇疆澶�

            g.drawString(strRand, i * fontWidth + 3, codeY);
        }

    }

    // 寰楀埌闅忔満瀛楃
    private String randomStr(int n) {
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    // 寰楀埌闅忔満棰滆壊
    private Color getRandColor(int fc, int bc) {// 缁欏畾鑼冨洿鑾峰緱闅忔満棰滆壊
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 浜х敓闅忔満瀛椾綋
     */
    @SuppressWarnings("unused")
    private Font getFont(int size) {
        Random random = new Random();
        Font font[] = new Font[5];
        font[0] = new Font("Ravie", Font.PLAIN, size);
        font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
        font[2] = new Font("Fixedsys", Font.PLAIN, size);
        font[3] = new Font("Wide Latin", Font.PLAIN, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
        return font[random.nextInt(5)];
    }

    // 鎵洸鏂规硶
    @SuppressWarnings("unused")
    private void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private void shearX(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(2);

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private void shearY(Graphics g, int w1, int h1, Color color) {

        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }
        }
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code.toLowerCase();
    }

    // 浣跨敤鏂规硶锛堝涓坊鍔犱笅闈唬鐮侊級
    // CreateValidateCode vCode = new CreateValidateCode(100, 30, 5, 10);
    // session.setAttribute("code", vCode.getCode());
    // vCode.write(response.getOutputStream());
}
