package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ChartGraphics {
    private BufferedImage image;
    private int totalWidth = 210;
    private int totalHeight = 297;
    private int imageWidth = 115;  //图片的宽度
    private int imageHeight = 166; //图片的高度
    private int headHeight = 45; //头部高度
    private int mainHeight = 79; //主图高度
    private int footHeight = 42; //尾部高度
    private int ratio = 8; // 比例
    //生成图片文件
    @SuppressWarnings("restriction")
    public void createImage(String fileLocation) {
        BufferedOutputStream bos = null;
        if(image != null){
            try {
                FileOutputStream fos = new FileOutputStream(fileLocation);
                bos = new BufferedOutputStream(fos);

                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
                encoder.encode(image);
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(bos!=null){//关闭输出流
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void graphicsGeneration(String dateNum, String date, String weekday, String oldDate, String weatherPicUrl, String fromImgUrl, String targetImgUrl, String[] words, String firstTitle, String secondTitle, int weatherPicX) {

        int realTotalWidth = totalWidth * ratio;
        int realTotalHeight = totalHeight * ratio;
        int realWidth = imageWidth * ratio;
        int realHeight = imageHeight * ratio;
        int realHeadHeight = headHeight * ratio;
        int realMainHeight = mainHeight * ratio;
        int realFootHeight = footHeight * ratio;
        image = new BufferedImage(realTotalWidth, realTotalHeight, BufferedImage.TYPE_INT_RGB);

//        image = new BufferedImage(realWidth, realHeight, BufferedImage.TYPE_INT_RGB);
        //设置整张图背景色
        Graphics2D main = image.createGraphics();
        main.setColor(Color.WHITE);
        main.fillRect(0, 0, realTotalWidth, realTotalHeight);

        //头部
        Graphics title = image.createGraphics();

        //设置区域颜色
        title.setColor(Color.WHITE);
        //填充区域并确定区域大小位置
        title.fillRect(0, 0, realWidth, realHeadHeight);
        //设置字体颜色，先设置颜色，再填充内容
        title.setColor(Color.BLACK);
        //设置字体
        Font titleFont = new Font("微软雅黑", Font.PLAIN, 180);
        title.setFont(titleFont);
        if (dateNum.length() == 1){
            title.drawString(dateNum, realWidth - 300, realHeadHeight - 20);
        }else{
            title.drawString(dateNum, realWidth - 400, realHeadHeight - 20);
        }
        titleFont = new Font("微软雅黑", Font.ITALIC, 28);
        title.setFont(titleFont);
        title.drawString(firstTitle, 50, realHeadHeight - 90);
        title.drawString(secondTitle, 50, realHeadHeight - 40);
        titleFont = new Font("微软雅黑", Font.PLAIN, 25);
        title.setFont(titleFont);
        title.drawString(date, realWidth - 160, realHeadHeight - 130);
        title.drawString(weekday, realWidth - 160, realHeadHeight - 95);
        title.drawString("农历", realWidth - 160, realHeadHeight - 60);
        title.drawString(oldDate, realWidth - 160, realHeadHeight - 25);

        Graphics weatherPic = image.getGraphics();
        BufferedImage wimg = null;
        try {
            wimg = javax.imageio.ImageIO.read(new java.io.File(weatherPicUrl));
        } catch (Exception e) {}

        if(wimg!=null){
            weatherPic.drawImage(wimg, weatherPicX, realHeadHeight - 110, 60, 60, null);
            weatherPic.dispose();
        }


        //中间主图
        Graphics mainPic = image.getGraphics();
        BufferedImage bimg = null;
        try {
            bimg = javax.imageio.ImageIO.read(new java.io.File(fromImgUrl));
        } catch (Exception e) {}

        if(bimg!=null){
            mainPic.drawImage(bimg, 0, realHeadHeight, realWidth, realMainHeight, null);
            mainPic.dispose();
        }

        //尾部
        Graphics2D tip = image.createGraphics();
        //设置区域颜色
        tip.setColor(Color.WHITE);
        //填充区域并确定区域大小位置
        tip.fillRect(0, realHeadHeight + realMainHeight, realWidth, realFootHeight);
        //设置字体颜色，先设置颜色，再填充内容
        tip.setColor(Color.BLACK);
        //设置字体
        Font tipFont = new Font("STXihei", Font.PLAIN, 25);
        tip.setFont(tipFont);
        int i = -50;
        for (String word: words){
            tip.drawString(word, 50, realHeadHeight + realMainHeight + (realFootHeight)/2 + i);
            i += 40;
        }
        createImage(targetImgUrl);
    }

    public static void main(String[] args) {
        ChartGraphics cg = new ChartGraphics();
        try {
//            String[] words1 = new String[]{
//                    "去年的今天，我们并肩坐在操场边，", "这样的夜晚特别值得记住，就开心又紧张地让路人帮忙拍照，", "那时的我们还有一些拘谨，", "虽然中间还隔了一只狗子的距离，但心已经离你很近很近了～"
//            };
//            cg.graphicsGeneration("26", "4月26日", "星期五", "三月廿二", "/Users/dengrongguan/Documents/LoveRecord/img/moon.png", "/Users/dengrongguan/Documents/LoveRecord/img/1.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/4-26.jpeg", words1, "鼓楼的夜晚", "时间匆匆~~", 220);

//            String[] words2 = new String[]{
//                    "第一次收到자기야的礼物，", "刚好是我没看过但很想看的《小王子》，", "很幸运，", "我现在也有一株可以一直守护的玫瑰花～"
//            };
//            cg.graphicsGeneration("31", "3月31日", "星期日", "二月廿五", "/Users/dengrongguan/Documents/LoveRecord/img/rose.png", "/Users/dengrongguan/Documents/LoveRecord/img/0.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/3-31.jpeg", words2, "想成为小王子", "守护我的小玫瑰花~", 300);

            String[] words3 = new String[]{
                    "자기야，你知道嘛", "在准备这个礼物的时候，","有种在和未来的你说话的感觉，", "很神奇！"
            };
            cg.graphicsGeneration("6", "1月6日", "星期日", "腊月初一", "/Users/dengrongguan/Documents/LoveRecord/img/cake.png", "/Users/dengrongguan/Documents/LoveRecord/img/1-6-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/1-6.jpeg", words3, "생일축하합니다", "자기야～", 230);

//            String[] words4 = new String[]{
//                    "，", "今天可以给你加一个愿望哦～"
//            };
//            cg.graphicsGeneration("22", "6月22日", "星期六", "五月二十", "/Users/dengrongguan/Documents/LoveRecord/img/cake.png", "/Users/dengrongguan/Documents/LoveRecord/img/1-6-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/1-6.jpeg", words4, "离别都是为了", "下一次再见～", 220);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        String[] fonts =
//                GraphicsEnvironment  // GraphicsEnvironment(抽象类)  图形环境类
//                        .getLocalGraphicsEnvironment()  // 获取本地图形环境
//                        .getAvailableFontFamilyNames();  // 获取可用字体family名
//
//        int fontCount = 0;   // 字体数统计
//        for(String font : fonts) {
//            fontCount ++;
//            System.out.println(font);
//        }
//        System.out.println("系统字体数:" + fontCount);
//    }
}
