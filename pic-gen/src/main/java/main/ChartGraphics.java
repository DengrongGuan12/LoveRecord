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

    public void graphicsGeneration(String name, String id, String classname, String imgurl) {
        int H_title = 100;     //头部高度
        int H_mainPic = 814;  //主图高度
        int H_tip = 60;  //上网提示框高度
        int H_btn = 25;  //按钮栏的高度
        int tip_2_top = (H_title+H_mainPic);
        int btns_2_top = tip_2_top+H_tip+20;
        int btn1_2_top = btns_2_top+10;
        int btn2_2_top = btn1_2_top+H_btn;
        int shops_2_top = btn2_2_top+H_btn+20;
        int W_btn = 712;  //按钮栏的宽度

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
        title.drawString("26", realWidth - 400, realHeadHeight - 20);
        titleFont = new Font("微软雅黑", Font.ITALIC, 28);
        title.setFont(titleFont);
        title.drawString("\"鼓楼的夜晚", 50, realHeadHeight - 90);
        title.drawString("时间匆匆~~\"", 50, realHeadHeight - 40);
        titleFont = new Font("微软雅黑", Font.PLAIN, 25);
        title.setFont(titleFont);
        title.drawString("4月26日", realWidth - 160, realHeadHeight - 130);
        title.drawString("星期五", realWidth - 160, realHeadHeight - 95);
        title.drawString("农历", realWidth - 160, realHeadHeight - 60);
        title.drawString("三月廿二", realWidth - 160, realHeadHeight - 25);

        Graphics weatherPic = image.getGraphics();
        BufferedImage wimg = null;
        try {
            wimg = javax.imageio.ImageIO.read(new java.io.File("/Users/dengrongguan/Projects/LoveRecord/img/moon.png"));
        } catch (Exception e) {}

        if(wimg!=null){
            weatherPic.drawImage(wimg, 220, realHeadHeight - 110, 60, 60, null);
            weatherPic.dispose();
        }


        //中间主图
        Graphics mainPic = image.getGraphics();
        BufferedImage bimg = null;
        try {
            bimg = javax.imageio.ImageIO.read(new java.io.File(imgurl));
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
        tip.drawString("去年的今天，我们并肩坐在操场边，", 50, realHeadHeight + realMainHeight + (realFootHeight)/2-50);
        tip.drawString("这样的夜晚特别值得记住，就开心又紧张地让路人帮忙拍照，", 50, realHeadHeight + realMainHeight + (realFootHeight)/2 -10 );
        tip.drawString("那时的我们还有一些拘谨哈哈哈，", 50, realHeadHeight + realMainHeight + (realFootHeight)/2 + 30);
        tip.drawString("虽然中间还隔了一只狗子的距离，但心已经离你很近很近了～", 50, realHeadHeight + realMainHeight + (realFootHeight)/2 + 70);
        createImage("/Users/dengrongguan/Projects/LoveRecord/img/test.jpeg");
    }

    public static void main(String[] args) {
        ChartGraphics cg = new ChartGraphics();
        try {
            cg.graphicsGeneration("ewew", "1", "12", "/Users/dengrongguan/Projects/LoveRecord/img/1.jpeg");
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
