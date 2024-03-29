package class18;

public class Code01_RobotWalk {

    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    // 机器人当前来到的位置是cur，
    // 机器人还有rest步需要去走，
    // 最终的目标是aim，
    // 有哪些位置？1~N
    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) { // 如果已经不需要走了，走完了！
            return cur == aim ? 1 : 0;
        }
        // (cur, rest) 当前在最左边 只能往右走
        if (cur == 1) { // 1 -> 2
            return process1(2, rest - 1, aim, N);
        }
        // (cur, rest) 当前在最右边 只能往左走
        if (cur == N) { // N-1 <- N
            return process1(N - 1, rest - 1, aim, N);
        }
        // (cur, rest) 中间  往左走的方法数+往右走的方法数
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    //傻缓存 【自顶向下的动态规划，记忆化搜索】
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
        // dp就是缓存表
        // dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
        // dp[cur][rest] != -1 -> process1(cur, rest)之前已经算过！返回值是 dp[cur][rest]
        // N+1 * K+1
        return process2(start, K, aim, N, dp);
    }

    // cur  范围: 1 ~ N
    // rest 范围：0 ~ K
    // 所有的重复过程只碰一遍
    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) { //缓存命中
            return dp[cur][rest];
        }
        // 之前没算过！ 缓存中没有
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
        dp[cur][rest] = ans; //加入缓存表
        return ans;

    }

    //  final  生成表格
    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;  //dp[...][0] = 0
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];

            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }

            dp[N][rest] = dp[N - 1][rest - 1];
        }
        printTwoDimensionalArray(dp);
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
        System.out.println(myRobotWalk3(5, 2, 6, 4));
    }

    public static int myRobotWalk3(int N, int M, int K, int P) {
        int[][] dp = new int[N + 1][K + 1];
        int aim = P;
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            for (int cur = 1; cur <= N; cur++) {
                if (cur == 1) {
                    dp[cur][rest] = dp[cur + 1][rest - 1];
                } else if (cur == N) {
                    dp[cur][rest] = dp[cur - 1][rest - 1];
                } else {
                    dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
                }
            }
        }
        System.out.println("---");
        printTwoDimensionalArray(dp);
        return dp[M][K];
    }

    public static void printTwoDimensionalArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();  // 打印完一行后换行
        }
    }

}
