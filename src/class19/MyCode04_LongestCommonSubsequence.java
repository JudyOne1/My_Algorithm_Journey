package class19;

/**
 * @author Judy
 * @create 2023-03-25-20:19
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * 链接：https://leetcode.com/problems/longest-common-subsequence/
 * <p>
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class MyCode04_LongestCommonSubsequence {
    public int longestCommonSubsequence01(String text1, String text2) {
        char[] charArray1 = text1.toCharArray();
        char[] charArray2 = text2.toCharArray();
        int ans = process01(charArray1, charArray2, text1.length() - 1, text2.length() - 1);
        return ans;
    }

    private int process01(char[] charArray1, char[] charArray2, int i, int j) {
        if (i == 0 && j == 0) {
            return charArray1[i] == charArray2[j] ? 1 : 0;
        } else if (i == 0) {
            return charArray1[i] == charArray2[j] ? 1 : process01(charArray1, charArray2, i, j - 1);
        } else if (j == 0) {
            return charArray1[i] == charArray2[j] ? 1 : process01(charArray1, charArray2, i - 1, j);
        } else {
            int p1 = process01(charArray1, charArray2, i, j - 1);
            int p2 = process01(charArray1, charArray2, i - 1, j);
            int p3 = charArray1[i] == charArray2[j] ? 1 + process01(charArray1, charArray2, i - 1, j - 1) : 0;
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    public int longestCommonSubsequence02(String text1, String text2) {
        char[] charArray1 = text1.toCharArray();
        char[] charArray2 = text2.toCharArray();
        int[][] dp = new int[charArray1.length][charArray2.length];
        for (int i = 0; i < charArray1.length; i++) {
            for (int j = 0; j < charArray2.length; j++) {
                dp[i][j] = -1;
            }
        }
        int ans = process02(charArray1, charArray2, text1.length() - 1, text2.length() - 1, dp);
        return ans;
    }

    private int process02(char[] charArray1, char[] charArray2, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = 0;
        if (i == 0 && j == 0) {
            ans = charArray1[i] == charArray2[j] ? 1 : 0;
        } else if (i == 0) {
            ans = charArray1[i] == charArray2[j] ? 1 : process02(charArray1, charArray2, i, j - 1, dp);
        } else if (j == 0) {
            ans = charArray1[i] == charArray2[j] ? 1 : process02(charArray1, charArray2, i - 1, j, dp);
        } else {
            int p1 = process02(charArray1, charArray2, i, j - 1, dp);
            int p2 = process02(charArray1, charArray2, i - 1, j, dp);
            int p3 = charArray1[i] == charArray2[j] ? 1 + process02(charArray1, charArray2, i - 1, j - 1, dp) : 0;
            ans = Math.max(Math.max(p1, p2), p3);
        }
        dp[i][j] = ans;
        return ans;
    }

    public int longestCommonSubsequence03(String text1, String text2) {
        char[] charArray1 = text1.toCharArray();
        char[] charArray2 = text2.toCharArray();
        int N = charArray1.length;
        int M = charArray2.length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = charArray1[i] == charArray2[j] ? 1 : 0;
                } else if (i == 0) {
                    dp[i][j] = charArray1[i] == charArray2[j] ? 1 : dp[i][j-1];
                } else if (j == 0) {
                    dp[i][j] = charArray1[i] == charArray2[j] ? 1 : dp[i-1][j];
                } else {
                    int p1 = dp[i][j-1];
                    int p2 = dp[i-1][j];
                    int p3 = charArray1[i] == charArray2[j] ? 1 + dp[i-1][j-1] : 0;
                    dp[i][j] = Math.max(Math.max(p1, p2), p3);
                }
            }
        }
        return dp[N][M];
    }

    private int process03(char[] charArray1, char[] charArray2, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int ans = 0;
        if (i == 0 && j == 0) {
            ans = charArray1[i] == charArray2[j] ? 1 : 0;
        } else if (i == 0) {
            ans = charArray1[i] == charArray2[j] ? 1 : process03(charArray1, charArray2, i, j - 1, dp);
        } else if (j == 0) {
            ans = charArray1[i] == charArray2[j] ? 1 : process03(charArray1, charArray2, i - 1, j, dp);
        } else {
            int p1 = process03(charArray1, charArray2, i, j - 1, dp);
            int p2 = process03(charArray1, charArray2, i - 1, j, dp);
            int p3 = charArray1[i] == charArray2[j] ? 1 + process03(charArray1, charArray2, i - 1, j - 1, dp) : 0;
            ans = Math.max(Math.max(p1, p2), p3);
        }
        dp[i][j] = ans;
        return ans;
    }

//-------------------------------------------------------------------------
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
