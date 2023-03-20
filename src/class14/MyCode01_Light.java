package class14;

import java.util.HashSet;

/**
 * @author Judy
 * @create 2023-03-20-15:59
 *
 * 给定一个字符串str，只由'X'和'.'两种字符构成
 * 'X'表示墙，不能放灯，也不需要点亮；'.'表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 * 当前i是x  去i+1
 *    i是。 i+1是。 i+2是x  fire++  去i+3
 *    i是。 i+1是。 i+2是。 fire++  去i+3
 *    i是。 i+1是x  fire++  去i+2
 */
public class MyCode01_Light {

    public static int minLight(String road) {

        char[] roads = road.toCharArray();
        int i = 0;
        int light = 0;
        while (roads.length > i){
            if (roads[i] == 'X'){
                i++;
            }else {
                if (i+1 == roads.length){
                    light++;
                    break;
                }else {
                    if (roads[i+1] == 'X'){
                        i+=2;
                        light++;
                    }else {
                        i+=3;
                        light++;
                    }
                }
            }
        }
        return light;
    }
}
