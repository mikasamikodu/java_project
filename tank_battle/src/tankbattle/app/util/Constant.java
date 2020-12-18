package tankbattle.app.util;

import java.awt.*;

public class Constant {

    /*************************主窗体相关*********************************/
    //定义主窗体的标题
    public static final String FRAME_TITLE = "坦克大战";

    //定义主窗体的宽高
    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 800;

    //定义主窗体的位置
    public static final int FRAME_X = Toolkit.getDefaultToolkit().getScreenSize().width-FRAME_WIDTH>>1;
    public static final int FRAME_Y = Toolkit.getDefaultToolkit().getScreenSize().height-FRAME_HEIGHT>>1;


    /***************************游戏菜单相关**********************************************/
    public static final int STATE_MENU = 0;
    public static final int STATE_RUN = 1;
    public static final int STATE_HELP = 2;
    public static final int STATE_OVER = 3;
    public static final int STATE_ABOUT = 4;

    public static final String[] MENUS = {
            "开始游戏",
            "继续游戏",
            "退出游戏",
            "游戏帮助",
            "游戏关于",
    };

    public static final Font FONT = new Font("微软雅黑",Font.BOLD, 24);
    public static final int GAME_FPS = 30;

    /*******************************坦克相关***********************************************/
    public static final int HOSTILE_MAX_NUMBER = 10;
}
