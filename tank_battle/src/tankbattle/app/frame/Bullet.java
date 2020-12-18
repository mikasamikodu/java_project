package tankbattle.app.frame;

import tankbattle.app.util.Constant;

import java.awt.*;

/**
 * 绘制坦克的炮弹
 */
public class Bullet {
//    炮弹默认半径
    private static final int DEFAULT_RADIUS = 4;
//    炮弹默认速度
    private static final int DEFAULT_SPEED = Tank.DEFAULT_SPEED<<1;

//    炮弹位置
    private int x,y;
//    炮弹半径
    private int radis = DEFAULT_RADIUS;
//    炮弹速度
    private int speed = DEFAULT_SPEED;
//    炮弹半径
    private int dir;
//    炮弹攻击力
    private int atk;
//    炮弹颜色
    private Color color;
//    炮弹的可见性
    private boolean visibility = true;


    public Bullet(int x, int y, int dir, int atk, Color color) {
        // 创建炮弹时，初始化其位置、方向、颜色、攻击力
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.atk = atk;
        this.color = color;
    }

    public Bullet() {

    }

    public void draw(Graphics g){
        if(!visibility)
            return;
//        绘制炮弹
        g.setColor(color);
        g.fillOval(x-radis, y-radis, radis<<1, radis<<1);
//        调用其逻辑，使其移动
        logic();
    }

    private  void logic(){
        switch (dir){
            case Tank.DIR_DOWN:
                y += speed;
                if (y >= Constant.FRAME_HEIGHT)
                    visibility = false;
                break;
            case Tank.DIR_LEFT:
                x -= speed;
                if(x <= 0)
                    visibility = false;
                break;
            case Tank.DIR_TOP:
                y -= speed;
                if(y <= 0)
                    visibility = false;
                break;
            case Tank.DIR_RIGHT:
                x += speed;
                if(x >= Constant.FRAME_WIDTH)
                    visibility = false;
                break;
        }
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public boolean isVisibility() {
        return visibility;
    }
}
