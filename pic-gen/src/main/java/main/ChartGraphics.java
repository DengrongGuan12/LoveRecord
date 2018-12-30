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
    int realTotalWidth = totalWidth * ratio;
    int realTotalHeight = totalHeight * ratio;
    int realWidth = imageWidth * ratio;
    int realHeight = imageHeight * ratio;
    int realHeadHeight = headHeight * ratio;
    int realMainHeight = mainHeight * ratio;
    int realFootHeight = footHeight * ratio;

    public int getRealHeadHeight(){
        return realHeadHeight;
    }
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

    public void graphicsGeneration(String dateNum, String date, String weekday, String oldDate, String weatherPicUrl, String fromImgUrl, String targetImgUrl, String[] words, String firstTitle, String secondTitle, int weatherPicX, int weatherPicY) {


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
            weatherPic.drawImage(wimg, weatherPicX, weatherPicY, 60, 60, null);
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
//                    "当我挨着你坐在操场边，", "仰脸看云，","我感觉，", "我会飞～"
//            };
//            cg.graphicsGeneration("26", "4月26日", "星期五", "三月廿二", "/Users/dengrongguan/Documents/LoveRecord/img/moon.png", "/Users/dengrongguan/Documents/LoveRecord/img/1.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/4-26.jpeg", words1, "鼓楼的夜晚", "时间匆匆~~", 220,cg.getRealHeadHeight() - 110);

//            String[] words2 = new String[]{
//                    "第一次收到자기야的礼物，", "刚好是我没看过但很想看的《小王子》，", "很幸运，", "我现在也有一株可以一直守护的玫瑰花～"
//            };
//            cg.graphicsGeneration("31", "3月31日", "星期日", "二月廿五", "/Users/dengrongguan/Documents/LoveRecord/img/rose.png", "/Users/dengrongguan/Documents/LoveRecord/img/0.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/3-31.jpeg", words2, "想成为小王子", "守护我的小玫瑰花~", 300,cg.getRealHeadHeight() - 110);

//            String[] words3 = new String[]{
//                    "자기야，你知道嘛", "在准备这个礼物的时候，","像在和未来的你说话，", "很神奇！"
//            };
//            cg.graphicsGeneration("6", "1月6日", "星期日", "腊月初一", "/Users/dengrongguan/Documents/LoveRecord/img/cake.png", "/Users/dengrongguan/Documents/LoveRecord/img/1-6-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/1-6.jpeg", words3, "생일축하합니다", "자기야～", 230,cg.getRealHeadHeight() - 110);

//            String[] words4 = new String[]{
//                    "那天下着小雨，", "你撑着伞帮我推行李，", "我在后面跟着，看着你的背影，","觉得寄几很幸福～"
//            };
//            cg.graphicsGeneration("22", "6月22日", "星期六", "五月二十", "/Users/dengrongguan/Documents/LoveRecord/img/littlerain.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/6-22-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/6-22.jpeg", words4, "离别都是为了", "下一次再见～", 220,cg.getRealHeadHeight() - 110);
//            String[] words5 = new String[]{
//                    "不知不觉，", "已经在一起一周年啦！", "一年来很多时间都是异地，", "但最大的感受就是，","乍看心欢，小别思恋，久处仍怦然～"
//            };
//            cg.graphicsGeneration("29", "4月29日", "星期一", "三月廿五", "/Users/dengrongguan/Documents/LoveRecord/img/sunny.png", "/Users/dengrongguan/Documents/LoveRecord/img/4-29-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/4-29.jpeg", words5, "愿今后的日子里", "朝暮更替皆有你～", 260,cg.getRealHeadHeight() - 110);

//            String[] words6 = new String[]{
//                    "我们还要去更大的世界，", "去闻闻新鲜的春天，", "感受阳光洒落肩上的夏天，", "整个世界涂着金色的秋天，","飘雪的冬天～"
//            };
//            cg.graphicsGeneration("2", "6月2日", "星期日", "四月廿九", "/Users/dengrongguan/Documents/LoveRecord/img/sunny.png", "/Users/dengrongguan/Documents/LoveRecord/img/6-2-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/6-2.jpeg", words6, "与你分享的快乐", "胜过独自拥有～", 260,cg.getRealHeadHeight() - 110);

//            String[] words7 = new String[]{
//                    "谁能够代替你呢，", "趁年轻尽情的爱吧，", "最最亲爱的人啊，", "路途遥远我们在一起吧~"
//            };
//            cg.graphicsGeneration("21", "6月21日", "星期五", "五月十九", "/Users/dengrongguan/Documents/LoveRecord/img/graduate.png", "/Users/dengrongguan/Documents/LoveRecord/img/6-21-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/6-21.jpeg", words7, "想把我唱给你听", "趁现在年少如花～", 260,cg.getRealHeadHeight() - 110);
//            String[] words8 = new String[]{
//                    "某些时候，我想，", "我与你的存在本身，", "不起任何作用，", "唯有你我之间的心意，","或许才是这个世界真正需要的东西～"
//            };
//            cg.graphicsGeneration("8", "8月8日", "星期四", "七月初八", "/Users/dengrongguan/Documents/LoveRecord/img/fengye.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/8-8-main-2.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/8-8.jpeg", words8, "立秋，天冷了，", "我想和你交换温暖与灵魂～", 240, cg.getRealHeadHeight() - 130);
//            String[] words9 = new String[]{
//                    "2018年最难忘的惊喜，", "是你在我生日这天突然来看我。", "有时候觉得，你那么的好，", "应该遇见一个比我更好的人，","可是我喜欢你的时候，","觉得寄几也是值得喜欢的～"
//            };
//            cg.graphicsGeneration("30", "10月30日", "星期二", "九月廿二", "/Users/dengrongguan/Documents/LoveRecord/img/gift.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/10-30-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/10-30.jpeg", words9, "我收到一个快递，", "是你～", 160, cg.getRealHeadHeight() - 80);
//            String[] words10 = new String[]{
//                    "去年的初雪，", "比往年早了些～", "我们牵着手踩在雪中，咯吱咯吱，", "工作中所有的疲惫烦恼瞬间就消散了～","今年有没有下雪呢？"
//            };
//            cg.graphicsGeneration("7", "12月7日", "星期六", "十一月十二", "/Users/dengrongguan/Documents/LoveRecord/img/snowman.png", "/Users/dengrongguan/Documents/LoveRecord/img/12-7-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/12-7.jpeg", words10, "天气真冷啊，", "我陪你过冬吧～", 240, cg.getRealHeadHeight() - 100);
//            String[] words11 = new String[]{
//                    "冬至前一夜称为\"冬至夜\"，", "这是全年最长的一夜，", "据说这夜做的梦最准，能预言以后的事。", "去年我们没有一起相拥度过，","今年肯定在一起取暖咯～晚安～","希望我们都能做个好梦呀～"
//            };
//            cg.graphicsGeneration("21", "12月21日", "星期六", "十一月廿六", "/Users/dengrongguan/Documents/LoveRecord/img/hug.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/12-21-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/12-21.jpeg", words11, "冬至夜，", "宜相拥～", 180, cg.getRealHeadHeight() - 100);
//            String[] words12 = new String[]{
//                    "去年的圣诞节，你赖床不想起来，", "当时真的超想在你身边，", "想用咖啡唤醒你～", "不过下午还是乖乖去学习了，","心疼你这只狗子～"
//            };
//            cg.graphicsGeneration("25", "12月25日", "星期三", "十一月三十", "/Users/dengrongguan/Documents/LoveRecord/img/chrismas.jpg", "/Users/dengrongguan/Documents/LoveRecord/img/12-25-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/12-25.jpeg", words12, "圣诞快乐，", "자기야～", 180, cg.getRealHeadHeight() - 100);

            String[] words13 = new String[]{
                    "那天做了一夜的梦，梦里全是你。", "梦到我的能量没了，很难过，", "梦到我们一起去了培训班，", "你教语文，我教信息技术，", "还梦到你睡在我身边，我压到你的头发。","我醒了，发现这次你真的在身边～"
            };
            cg.graphicsGeneration("30", "12月30日", "星期日", "腊月初三", "/Users/dengrongguan/Documents/LoveRecord/img/chrismas.jpg", "/Users/dengrongguan/Documents/LoveRecord/img/12-30-main.jpeg", "/Users/dengrongguan/Documents/LoveRecord/img/12-30.jpeg", words13, "即使见不到你，", "你总归会在某处，就足够了～", 180, cg.getRealHeadHeight() - 100);

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
