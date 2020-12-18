package tankbattle.app.util;

import java.awt.*;

public class MyUtils {

    /**
     * 在指定区间得到随机数
     * @param min 区间最小值，包含
     * @param max  区间最大值，不包含
     * @return 区间的随机数
     */
    public static int randomNum(int min, int max){
        return (int)(Math.random()*(max-min)+min);
    }

    public static Color randomColor(){
        return new Color(randomNum(0, 256), randomNum(0, 256), randomNum(0, 256));
    }
}
