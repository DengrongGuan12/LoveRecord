package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ChartGraphics {
    private BufferedImage image;
    private int imageWidth = 134;  //图片的宽度
    private int imageHeight = 170; //图片的高度
    private int headHeight = 46; //头部高度
    private int mainHeight = 80; //主图高度
    private int footHeight = 44; //尾部高度
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

        int realWidth = imageWidth * ratio;
        int realHeight = imageHeight * ratio;
        int realHeadHeight = headHeight * ratio;
        int realMainHeight = mainHeight * ratio;
        int realFootHeight = footHeight * ratio;
        image = new BufferedImage(realWidth, realHeight, BufferedImage.TYPE_INT_RGB);
        //设置整张图背景色
        Graphics2D main = image.createGraphics();
        main.setColor(Color.white);
        main.fillRect(0, 0, realWidth, realHeight);

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
        title.drawString("12", realWidth - 400, realHeadHeight - 20);
        titleFont = new Font("微软雅黑", Font.PLAIN, 25);
        title.setFont(titleFont);
        title.drawString("7月12日", realWidth - 160, realHeadHeight - 130);
        title.drawString("星期五", realWidth - 160, realHeadHeight - 95);
        title.drawString("农历", realWidth - 160, realHeadHeight - 60);
        title.drawString("六月初十", realWidth - 160, realHeadHeight - 25);


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
        Font tipFont = new Font("宋体", Font.PLAIN, 14);
        tip.setFont(tipFont);
        tip.drawString("foot", 60, realHeadHeight + realMainHeight + (realFootHeight)/2-10);

        createImage("/Users/dengrongguan/Pictures/test.jpeg");
    }

    public static void main(String[] args) {
        ChartGraphics cg = new ChartGraphics();
        try {
            cg.graphicsGeneration("ewew", "1", "12", "/Users/dengrongguan/Pictures/1.jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
