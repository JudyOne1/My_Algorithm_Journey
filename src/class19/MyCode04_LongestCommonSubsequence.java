package class19;

/**
 * @author Judy
 * @create 2023-03-25-20:19
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * 链接：https://leetcode.com/problems/longest-common-subsequence/
 */
public class MyCode04_LongestCommonSubsequence {
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 尝试
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    private static int process1(char[] str1, char[] str2, int i, int j) {
        //base case
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            return str1[i] == str2[j] ? 1 : process1(str1, str2, i, j - 1);
        } else if (j == 0) {
            return str1[i] == str2[j] ? 1 : process1(str1, str2, i - 1, j);
        } else {
            int p1 = str1[i] == str2[j] ? 1 + process1(str1, str2, i - 1, j - 1) : 0;
            int p2 = process1(str1, str2, i, j - 1);
            int p3 = process1(str1, str2, i - 1, j);
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int j = 1; j < M; j++) {
            int i = 0;
            dp[i][j] = str1[i] == str2[j] ? 1 : dp[i][j - 1];
        }
        for (int i = 1; i < N; i++) {
            int j = 0;
            dp[i][j] = str1[i] == str2[j] ? 1 : dp[i - 1][j];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = str1[i] == str2[j] ? 1 + dp[i - 1][j - 1] : 0;
                int p2 = dp[i][j - 1];
                int p3 = dp[i - 1][j];
                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }
        return dp[N - 1][M - 1];
    }


}
