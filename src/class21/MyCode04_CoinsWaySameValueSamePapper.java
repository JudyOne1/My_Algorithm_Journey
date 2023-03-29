package class21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author Judy
 * @create 2023-03-29-15:35
 * <p>
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 * 从左到右
 */
public class MyCode04_CoinsWaySameValueSamePapper {
    /*public static int coinsWay1(int[] arr, int aim) {
        //有重复解，失败
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Arrays.sort(arr);
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (arr.length == index) {
            return rest == 0 ? 1 : 0;
        }
        //拿与不拿
        return process1(arr, index + 1, rest) + process1(arr, index + 1, rest - arr[index]);
    }*/
    public static class Info {
        public int[] coins;
        public int[] zhangs;

        public Info(int[] c, int[] z) {
            coins = c;
            zhangs = z;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {//统计  去重
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhang = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhang[index++] = entry.getValue();
        }
        return new Info(coins, zhang);
    }

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.coins, info.zhangs, 0, aim);
    }

    private static int process(int[] coins, int[] zhangs, int index, int rest) {
        if (coins.length == index) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang <= zhangs[index]
                && rest >= zhang * coins[index]; zhang++) {
            ways += process(coins, zhangs, index + 1, rest - zhang * coins[index]);
        }

        return ways;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                for (int zhang = 0; zhang <= zhangs[index]
                        && rest >= zhang * coins[index]; zhang++) {//有枚举
                    dp[index][rest] += dp[index + 1][rest - zhang * coins[index]];
                }
            }
        }
        return dp[0][aim];

    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];//下方a
                if (rest - coins[index] >= 0){
                    //※
                    dp[index][rest] = dp[index][rest-coins[index]];
                }
                if (rest - coins[index] * (zhangs[index] + 1) >= 0){
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];//减去多加的部分
                }
            }
        }
        return dp[0][aim];

    }
}
