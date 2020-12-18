package tankbattle.app.frame;

import tankbattle.app.util.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 游戏主窗体
 继承Frame类，创建窗体
 实现Runnable接口，实现窗体的定时刷新
 */
public class GameFrame extends Frame implements Runnable{

//    1.定义一张和游戏屏幕一样大小的图片
    private BufferedImage bufferedImage = new BufferedImage(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
//  判断游戏状态,以此绘制游戏暂停时的屏幕状态
    private static int gameState;
//    判断鼠标指向菜单位置
    private static int menuIndex;
//      计算窗体标题栏高度
    public static int titleBarHeight;
//      创建我方坦克
    private Tank myTank;
    //      创建敌方坦克
    private java.util.List<Tank> hostileTanks = new ArrayList<>();

//    无参构造器，初始化主窗体相关属性以及窗体按钮相关监听事件
    public GameFrame(){
//        调用方法，初始化窗体属性
        initFrame();
//      调用方法,初始化窗体相关的监听事件
        initFrameListenerList();
//        启动屏幕的定时刷新
        new Thread(this).start();
    }

//    初始化窗体属性
    private void initFrame(){
        gameState = Constant.STATE_MENU;
        menuIndex = Constant.STATE_MENU;
//        初始化窗体的标题
        setTitle(Constant.FRAME_TITLE);
//        初始化窗体的宽高
        setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
//        初始化窗体的位置
        setLocation(Constant.FRAME_X, Constant.FRAME_Y);
//        初始化窗体左上角的图标
        setIconImage(new ImageIcon("image/icon.jpg").getImage());
//        设置窗体宽高不可变
        setResizable(false);
//        设置窗体可见
        setVisible(true);
//        初始化窗体标题栏的高度
        titleBarHeight = getInsets().top;
    }

/**
 该方法是frame类的方法,该方法负责所有的绘制内容,所有需要在屏幕中显示的内容都需要在这个方法内调用,但该方法不能主动调用,需要调用repaint()方法去回调该方法
 */
    @Override
    public void update(Graphics g1) {
//        2.得到图片的画笔
        Graphics g = bufferedImage.getGraphics();
//        3.使用图片画笔绘制所有内容到图片上
        g.setFont(Constant.FONT);
//        判断游戏的状态，以此绘制游戏暂停时的屏幕
        switch (gameState){
            case(Constant.STATE_MENU):
                drawMenu(g);
                break;
            case(Constant.STATE_RUN):
                drawRun(g);
                break;
            case(Constant.STATE_OVER):
                drawOver(g);
                break;
            case(Constant.STATE_HELP):
                drawHelp(g);
                break;
            case(Constant.STATE_ABOUT):
                drawAbout(g);
                break;
        }
//      用系统画笔将图片绘制到屏幕上
        g1.drawImage(bufferedImage, 0, 0, null);
    }

    private void drawAbout(Graphics g) {

    }

    private void drawHelp(Graphics g) {

    }

    private void drawOver(Graphics g) {

    }

    private void drawRun(Graphics g) {
//        将窗体背景设置位黑色
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
//        然后调用坦克的相关逻辑，使坦克移动、攻击
        myTank.draw(g);
        for(int i=0;i<hostileTanks.size();i++){
            Tank hostileTank = hostileTanks.get(i);
            hostileTank.draw(g);
        }
    }

    public void drawMenu(Graphics g){
//        将窗体背景设置位黑色
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

//        将字体设为白色
        g.setColor(Color.WHITE);
        String[] menus = Constant.MENUS;
//        假设每个菜单宽30像素
        int strLength = 100;
        int x = Constant.FRAME_WIDTH-strLength>>1;
//        设置第一个菜单离窗体顶部的高度
        int y = Constant.FRAME_HEIGHT/5 <<1;
//      设置每个菜单间的间距
        int dis = 50;
//        通过循环绘制,显示5个菜单
        for (int i = 0; i < menus.length; i++) {
//            选中的菜单字体颜色设置为红色
            if(i == menuIndex)
                g.setColor(Color.RED);
            else
                g.setColor(Color.WHITE);
            g.drawString(menus[i], x, y+dis*i);
        }

    }

    //    初始化窗体相关的监听事件
    private void initFrameListenerList(){

        //添加窗口监听事件
        addWindowListener(new WindowAdapter(){
            //监听窗体的关闭按钮,设置窗体的关闭事件
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

//        添加键盘按键监听事件
        addKeyListener(new KeyAdapter() {

            @Override//监听键盘按键按下的事件
            public void keyPressed(KeyEvent e) {

//                判断游戏的状态，确保敲击键盘按键时产生正常的反应或效果
                switch (gameState){
                    case(Constant.STATE_MENU):
                        keyEventMenu(e);
                        break;
                    case(Constant.STATE_RUN):
                        keyEventRun(e);
                        break;
                    case(Constant.STATE_OVER):
                        keyEventOver(e);
                        break;
                    case(Constant.STATE_HELP):
                        keyEventHelp(e);
                        break;
                    case(Constant.STATE_ABOUT):
                        keyEventAbout(e);
                        break;
                }

            }

            @Override//监听键盘按键松开的事件
            public void keyReleased(KeyEvent e) {
//                判断游戏的状态是否时游戏运行时，确保键盘按键效果正常触发
                if(gameState == Constant.STATE_RUN){
                    keyReleasedEventRun(e);
                }
            }
        });
    }

//    当松开上下左右键或w、a、s、d键时，将坦克状态设置为停止
    private void keyReleasedEventRun(KeyEvent e){
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case(KeyEvent.VK_UP):
            case(KeyEvent.VK_W):
            case(KeyEvent.VK_DOWN):
            case(KeyEvent.VK_S):
            case(KeyEvent.VK_LEFT):
            case(KeyEvent.VK_A):
            case(KeyEvent.VK_RIGHT):
            case(KeyEvent.VK_D):
                myTank.setState(Tank.STATE_STAND);
                break;
        }
    }

    private void keyEventMenu(KeyEvent e) {
        //获取键盘按键
        int keyCode = e.getKeyCode();
        int menusLength = Constant.MENUS.length-1;
//        通过键盘的上下键或w、s键选择菜单
        switch (keyCode){
            case(KeyEvent.VK_UP):
            case(KeyEvent.VK_W):
                if(--menuIndex<0)
                    menuIndex = menusLength;
                break;
            case(KeyEvent.VK_DOWN):
            case(KeyEvent.VK_S):
                if(++menuIndex>menusLength)
                    menuIndex = 0;
                break;
            case(KeyEvent.VK_ENTER):
//                当是开始游戏的选项时，按下enter键会开始游戏
                if(menuIndex==0)
                    newGame();
                break;
        }
    }

    private void newGame(){
//        游戏状态切换到游戏运行时状态
        gameState = Constant.STATE_RUN;
//        初始化坦克对象
        myTank = new Tank(300, 200, Tank.DIR_DOWN);
        Tank hostileTank = null;
        while(hostileTanks.size() < 10){
            hostileTank = Tank.createHostile();
            hostileTank.setX(hostileTank.getX()+hostileTank.getRadis());
            hostileTanks.add(hostileTank);
        }

    }

    private void keyEventRun(KeyEvent e) {
        int keyCode = e.getKeyCode();
//        当按下键盘上的上下左右键或w、a、s、d键时，设置塔克方向，将坦克状态切换至移动
        switch (keyCode){
            case(KeyEvent.VK_UP):
            case(KeyEvent.VK_W):
                myTank.setDir(Tank.DIR_TOP);
                myTank.setState(Tank.STATE_MOVE);
                break;
            case(KeyEvent.VK_DOWN):
            case(KeyEvent.VK_S):
                myTank.setDir(Tank.DIR_DOWN);
                myTank.setState(Tank.STATE_MOVE);
                break;
            case(KeyEvent.VK_LEFT):
            case(KeyEvent.VK_A):
                myTank.setDir(Tank.DIR_LEFT);
                myTank.setState(Tank.STATE_MOVE);
                break;
            case(KeyEvent.VK_RIGHT):
            case(KeyEvent.VK_D):
                myTank.setDir(Tank.DIR_RIGHT);
                myTank.setState(Tank.STATE_MOVE);
                break;
            case(KeyEvent.VK_SPACE):
//                按下空格键时，坦克会开始攻击发射炮弹
                myTank.fire();
                break;
        }
    }

    private void keyEventOver(KeyEvent e) {
    }

    private void keyEventHelp(KeyEvent e) {
    }

    private void keyEventAbout(KeyEvent e) {
    }

    @Override
    public void run() {
        try {
            while (true){
                repaint();
                Thread.sleep(Constant.GAME_FPS);
//                每隔0.03秒刷新一次窗体
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
