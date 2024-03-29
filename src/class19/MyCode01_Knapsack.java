package class19;

/**
 * @author Judy
 * @create 2023-03-24-19:47
 * 给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表 i号物品的重量和价值
 * 给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量。
 * 返回能装下的最大价值。
 * 经典背包问题。
 */
public class MyCode01_Knapsack {
    public static int maxValue00(int[] weights, int[] values, int bag) {
        return process00(weights, values, 0, bag);
    }

    private static int process00(int[] weights, int[] values, int index, int rest) {
        //base case
        if (rest < 0) {
            //拿不下了
            return -1;
        }
        if (rest == 0){
            return 0;
        }
        if (index == weights.length){
            return 0;
        }
        //拿
        int price1 = process00(weights, values, index + 1, rest - weights[index]) + values[index];
        if (price1 == -1) {
            price1 = 0;
        }
        //不拿
        int price2 = process00(weights, values, index + 1, rest);
        int price = Math.max(price1, price2);
        return price;
    }


    public static int maxValue(int[] weights, int[] values, int bag) {
        return process(weights, values, 0, bag);
    }

    private static int process(int[] weights, int[] values, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == weights.length) {
            return 0;
        }
        int get = process(weights, values, index + 1, rest - weights[index]);//拿
        int noGet = process(weights, values, index + 1, rest);//不拿
        if (get == -1) {
            return noGet;
        } else {
            return Math.max(get + values[index], noGet);
        }
    }

    private static int dp(int[] weights, int[] values, int bag) {
        int[][] dp = new int[weights.length + 1][bag + 1];
        int rest = 0;//col
        int index = 0;//row
        for (index = weights.length - 1; index >= 0; index--) {
            for (rest = 0; rest <= bag; rest++) {
                int get = rest < weights[index] ? -1 : dp[index + 1][rest - weights[index]];//拿
                int noGet = dp[index + 1][rest];//不拿
                if (get == -1) {
                    dp[index][rest] = noGet;
                } else {
                    dp[index][rest] = Math.max(get + values[index], noGet);
                }
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(maxValue00(weights, values, bag));
        System.out.println(dp(weights, values, bag));
        System.out.println(maxValue01(weights, values, bag));
    }

    public static int maxValue01(int[] weights, int[] values, int bag) {
        int N = weights.length;
        int[][] dp = new int[N+1][bag+1];

        for (int index = N-1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                if (rest == 0){
                    //base case
                    dp[index][rest] = 0;
                }
                int ans1 = rest-weights[index] >= 0?dp[index+1][rest-weights[index]]+values[index]:0;
                int ans2 = dp[index+1][rest];

                dp[index][rest] = Math.max(ans1,ans2);
            }
        }
        return dp[0][bag];
    }

}
