package class21;

/**
 * @author Judy
 * @create 2023-03-28-15:16
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class MyCode02_CoinsWayEveryPaperDifferent {
    public static int coinWays(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int aim) {
        //base case
        if (aim == 0) {
            return 1;
        }
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        int ways = process(arr, index + 1, aim);
        if (aim - arr[index] >= 0) {
            ways += process(arr, index + 1, aim - arr[index]);
        }

        return ways;
    }

    public static int dp(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                int ways = dp[i + 1][j];
                if (j - arr[i] >= 0) {
                    ways += dp[i + 1][j - arr[i]];
                }
                dp[i][j] = ways;
            }
        }
        return dp[0][aim];
    }
}
