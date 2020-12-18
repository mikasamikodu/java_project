package tankbattle.app.frame;

import tankbattle.app.util.BulletPool;
import tankbattle.app.util.Constant;
import tankbattle.app.util.MyUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.sun.webkit.graphics.WCImage.getImage;

/**
 制造坦克类
 */
public class Tank {

//    加载坦克图片
    private static Image[] myTankImg;
    private static Image[] hostileTankImg;
    static {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        myTankImg = new Image[4];
        myTankImg[0] = toolkit.getImage("image/myTankLeft.jpg");
        myTankImg[1] = toolkit.getImage("image/myTankTop.jpg");
        myTankImg[2] = toolkit.getImage("image/myTankRight.jpg");
        myTankImg[3] = toolkit.getImage("image/myTankDown.jpg");

        hostileTankImg = new Image[4];
        hostileTankImg[0] = toolkit.getImage("image/hostileTankLeft.jpg");
        hostileTankImg[1] = toolkit.getImage("image/hostileTankTop.jpg");
        hostileTankImg[2] = toolkit.getImage("image/hostileTankRight.jpg");
        hostileTankImg[3] = toolkit.getImage("image/hostileTankDown.jpg");
    }

//    坦克默认半径
    private static final int DEFAULT_RADIUS = 30;
//    坦克初始生命值
    private static final int DEFAULT_HP = 1050;
    //    坦克默认攻击力
    private static final int DEFAULT_ATK = 100;
    //    坦克默认速度
    public static final int DEFAULT_SPEED = 10;
//    坦克前进的方向
    public static final int DIR_LEFT = 0;
    public static final int DIR_TOP = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_DOWN = 3;
//  坦克状态
    public static final int STATE_STAND = 0;
    public static final int STATE_MOVE = 1;
    public static final int STATE_DIE = 2;

//    坦克位置
    private int x,y;
//    坦克半径
    private int radis = DEFAULT_RADIUS;
    //    坦克生命值
    private int hp = DEFAULT_HP;
    //    坦克速度
    private int speed = DEFAULT_SPEED;
    //    坦克攻击力
    private int atk = DEFAULT_ATK;
    //    坦克方向：上下左右
    private int dir;
    //    坦克状态：停止、移动、死亡
    private int state = STATE_STAND;
//    坦克颜色
    private Color color;
//    是否是敌人，默认是否
    private boolean isHostile = false;
//坦克弹夹
    private List<Bullet> bullets = new ArrayList<>();

    public Tank(int x, int y, int dir) {
//        创建坦克时，初始化坦克位置，方向及颜色
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.color = new Color(242, 175, 59);
    }

    public static Tank createHostile(){
        int x = MyUtils.randomNum(0, 2) == 0?DEFAULT_RADIUS:Constant.FRAME_WIDTH-DEFAULT_RADIUS;
        Tank tank = new Tank(x, DEFAULT_RADIUS+GameFrame.titleBarHeight, DIR_DOWN);
        tank.setHostile(true);
        return tank;
    }

    public void draw(Graphics g){
//        绘制坦克
          drawTankByImg(g);
//        判断坦克状态
        logic();
//        发射弹夹里的炮弹
        drawBullets(g);
    }

    //用图片绘制坦克
    private void drawTankByImg(Graphics g){
        if(isHostile)
            g.drawImage(hostileTankImg[dir], x-radis, y-radis, null);
        else
            g.drawImage(myTankImg[dir], x-radis, y-radis, null);
    }

    //通过系统画笔绘制坦克整体背景
    private void drawTank(Graphics g){
//        绘制坦克整体背景
        g.setColor(color);
        g.fillRect(x-radis, y-radis, radis<<1, radis<<1);
//      绘制炮管
        int endX = x;
        int endY = y;
        switch (dir){
            case DIR_DOWN:
                endY += radis <<1;
//                通过绘制三条并行的线，将炮管变粗
                g.drawLine(x-1, y, endX-1, endY);
                g.drawLine(x+1, y, endX+1, endY);
                break;
            case DIR_LEFT:
                endX -= radis <<1;
                // 通过绘制三条并行的线，将炮管变粗
                g.drawLine(x, y-1, endX, endY-1);
                g.drawLine(x, y+1, endX, endY+1);
                break;
            case DIR_TOP:
                endY -= radis <<1;
                // 通过绘制三条并行的线，将炮管变粗
                g.drawLine(x-1, y, endX-1, endY);
                g.drawLine(x+1, y, endX+1, endY);
                break;
            case DIR_RIGHT:
                endX += radis <<1;
                // 通过绘制三条并行的线，将炮管变粗
                g.drawLine(x, y-1, endX, endY-1);
                g.drawLine(x, y+1, endX, endY+1);
                break;
        }
        g.drawLine(x, y, endX, endY);
    }

//    判断坦克状态：停在原地，移动，死亡
    private void logic() {
        switch (state){
            case STATE_STAND:
            case STATE_DIE:
                break;
            case STATE_MOVE:
//                处于移动状态就让它移动
                move();
                break;
        }
    }

    private void move(){
        switch (dir){
            case DIR_DOWN:
                y += speed;
                // 判断窗体边界，防止坦克越过边界，这里是下边界
                int downEdge = Constant.FRAME_HEIGHT-radis;
                if(y > downEdge)
                    y = downEdge;
                break;
            case DIR_LEFT:
                x -= speed;
                // 判断窗体边界，防止坦克越过边界，这里是左边界
                if(x < radis)
                    x = radis;
                break;
            case DIR_TOP:
                y -= speed;
                // 判断窗体边界，防止坦克越过边界，这里是上边界
                if(y < radis+GameFrame.titleBarHeight)
                    y = radis+GameFrame.titleBarHeight;
                break;
            case DIR_RIGHT:
                x += speed;
                // 判断窗体边界，防止坦克越过边界，这里是右边界
                int rightEdge = Constant.FRAME_WIDTH-radis;
                if(x > rightEdge)
                    x = rightEdge;
                break;
        }
    }

    //制造炮弹，加入弹夹
    public void fire(){
        int bulletX = x;
        int bulletY = y;
//        通过坦克位置，判断子弹出现时的初始位置
        switch (dir){
            case DIR_DOWN:
                bulletY += radis;
                break;
            case DIR_LEFT:
                bulletX -= radis;
                break;
            case DIR_TOP:
                bulletY -= radis;
                break;
            case DIR_RIGHT:
                bulletX += radis;
                break;
        }
//        Bullet bullet = new Bullet(bulletX, bulletY, dir, atk, color);
        Bullet bullet = BulletPool.getBullet();
        bullet.setX(bulletX);
        bullet.setY(bulletY);
        bullet.setDir(dir);
        bullet.setAtk(atk);
        bullet.setColor(color);
//        初始化完炮弹，将其放入弹夹，等待发射
        bullets.add(bullet);
    }

    private void drawBullets(Graphics g){
        for (Bullet bullet : bullets) {
//            发射弹夹里的炮弹
            bullet.draw(g);
        }
        for (int i=0;i<bullets.size();i++) {
            Bullet bullet = bullets.get(i);
//            当子弹不可见,从炮弹池移除,归还到子弹池
            if(!bullet.isVisibility())
                bullets.remove(bullet);
                BulletPool.returnBullet(bullet);
        }

    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadis() {
        return radis;
    }

    public void setRadis(int radis) {
        this.radis = radis;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDir() {
        return dir;
    }

    public int getState() {
        return state;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public boolean isHostile() {
        return isHostile;
    }

    public void setHostile(boolean hostile) {
        isHostile = hostile;
    }
}
