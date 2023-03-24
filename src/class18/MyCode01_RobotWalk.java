package class18;

import java.util.HashMap;

/**
 * @author Judy
 * @create 2023-03-23-15:09
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * - 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * - 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * - 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数
 */
public class MyCode01_RobotWalk {
    public static int ways1(int N, int start, int aim, int K) {
        return process1(start, K, aim, N);
    }

    // 机器人当前来到的位置是cur，
    // 机器人还有rest步需要去走，
    // 最终的目标是aim，
    // 有哪些位置？1~N
    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    private static int process1(int cur, int rest, int aim, int N) {
        //base case
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(2, rest - 1, aim, N);
        }
        if (cur == N) {
            return process1(N - 1, rest - 1, aim, N);
        }
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);

    }

    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;//初始化为 -1
            }
        }
        return process2(start, K, aim, N, dp);
    }

    //傻缓存
    private static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }

    private static int ways3(int N, int start, int aim, int K) {
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < N + 1; i++) {
            dp[aim][0] = 1;
        }
        for (int i = 1; i < K + 1; i++) {//col
            dp[1][i] = dp[2][i - 1];
            for (int j = 2; j < N; j++) {//row
                dp[j][i] = dp[j + 1][i - 1] + dp[j - 1][i - 1];
            }
            dp[N][i] = dp[N - 1][i - 1];
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }
}
