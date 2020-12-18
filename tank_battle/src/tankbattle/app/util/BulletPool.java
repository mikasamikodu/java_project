package tankbattle.app.util;

import tankbattle.app.frame.Bullet;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用对象池技术优化子弹效率
 */
public class BulletPool {

//    子弹池最大容量为200
    private static final int POOL_SIZE = 200;
//    初始化子弹池
    private static List<Bullet> bullets = new ArrayList<>();

    private static Bullet bullet = null;
//  在类创建时初始化子弹池
    static {
        for(int i=0;i<POOL_SIZE;i++){
            bullet = new Bullet();
            bullets.add(bullet);
        }
    }

//    从对象池中获取子弹
    public static Bullet getBullet(){
//        为防止子弹池变空,检测子弹池,当池内没有子弹时,生成新的子弹返回,否则将子弹池内第一个拿出并返回
        if(bullets.size() == 0) {
            bullet = new Bullet();
        }else {
            bullet = bullets.remove(0);
        }
        return bullet;
    }

//    归还子弹到子弹池,但需要检测子弹池容量不超过其最大容量,达到最大值后将多余的子弹舍弃
    public static void returnBullet(Bullet bullet){
        if(POOL_SIZE == bullets.size())
            return;
        bullets.add(bullet);
    }
}
