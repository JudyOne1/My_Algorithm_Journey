package class21;

/**
 * @author Judy
 * @create 2023-03-28-14:39
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class MyCode01_MinPathSum {
    public static int minPathSum3(int[][] matrix) {
        return process(matrix, 0, 0);
    }

    private static int process(int[][] matrix, int i, int j) {
        if (i == matrix.length - 1 && j == matrix[0].length - 1) {
            return matrix[i][j];
        }
        if (i == matrix.length - 1) {
            return matrix[i][j] + process(matrix, i, j + 1);
        }
        if (j == matrix[0].length - 1) {
            return matrix[i][j] + process(matrix, i + 1, j);
        }
        int p1 = matrix[i][j] + process(matrix, i + 1, j);
        int p2 = matrix[i][j] + process(matrix, i, j + 1);

        return Math.min(p2, p1);
    }

    public static int minPathSum1(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N + 1][M + 1];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int j = 1; j < N; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + matrix[i][j],dp[i-1][j] + matrix[i][j]);
            }
        }
        return dp[N-1][M-1];
    }

}
