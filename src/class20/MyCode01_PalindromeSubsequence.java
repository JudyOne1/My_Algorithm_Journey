package class20;

import java.util.Arrays;
import java.util.IdentityHashMap;

/**
 * @author Judy
 * @create 2023-03-27-14:45
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 * -----------------------
 * 示例 1：
 * 输入：s = "bbbab"
 * 输出：4
 * 解释：一个可能的最长回文子序列为 "bbbb" 。
 * -----------------------
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出：2
 * 解释：一个可能的最长回文子序列为 "bb" 。
 */
public class MyCode01_PalindromeSubsequence {
    //字符串本身与字符串的反转的公共子序列不就是最长回文序列嘛  12 _23432_ 556   655 _23432_ 21
    public int longestPalindromeSubseq00(String s) {
        char[] charArray = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : charArray) {
            stringBuilder.insert(0, c);
        }

        String s1 = stringBuilder.toString();
        System.out.println(s1);
        return findLongestSameSubseq00(s, s1);
    }

    public int findLongestSameSubseq00(String s1, String s2) {
        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();
        int N = charArray1.length;
        int M = charArray2.length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = charArray1[i] == charArray2[j] ? 1 : 0;
                } else if (i == 0) {
                    dp[i][j] = charArray1[i] == charArray2[j] ? 1 : dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = charArray1[i] == charArray2[j] ? 1 : dp[i - 1][j];
                } else {
                    int p1 = dp[i][j - 1];
                    int p2 = dp[i - 1][j];
                    int p3 = charArray1[i] == charArray2[j] ? 1 + dp[i - 1][j - 1] : 0;
                    dp[i][j] = Math.max(Math.max(p1, p2), p3);
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public int longestPalindromeSubseq01(String s) {
        char[] charArray = s.toCharArray();
        int N = charArray.length;
        return process01(charArray, 0, N - 1);
    }

    private int process01(char[] charArray, int i, int j) {
        //base case
        if (i == j) {
            return 1;
        }
        if (i == j - 1) {
            return charArray[i] == charArray[j] ? 2 : 1;
        }

        int p1 = process01(charArray, i, j - 1);
        int p2 = process01(charArray, i + 1, j);
        int p3 = charArray[i] == charArray[j] ? 2 + process01(charArray, i + 1, j - 1) : 0;
        return Math.max(Math.max(p1, p2), p3);
    }

    public int longestPalindromeSubseq02(String s) {
        char[] charArray = s.toCharArray();
        int N = charArray.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;//最右下角
        for (int i = 0; i < N - 1; i++) {
            //base case 主对角线and旁边的对角线
            dp[i][i] = 1;
            dp[i][i + 1] = charArray[i] == charArray[i + 1] ? 2 : 1;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i + 1][j];
                int p3 = charArray[i] == charArray[j] ? 2 + dp[i + 1][j - 1] : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), p3);
            }
        }
        return dp[0][N - 1];
    }


    //way1 生成这个字符串的倒序，将这两个字符串求最大共同序列长度
    public static int longestCommonSubsequence1(String s1, String s2) {
        System.out.println(s1);
        System.out.println(s2);
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    private static int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        }
        if (i == 0) {
            return str1[i] == str2[j] ? 1 : process(str1, str2, i, j - 1);
        }
        if (j == 0) {
            return str1[i] == str2[j] ? 1 : process(str1, str2, i - 1, j);
        }
        int p1 = process(str1, str2, i - 1, j);
        int p2 = process(str1, str2, i, j - 1);
        int p3 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;
        return Math.max(Math.max(p1, p2), p3);
    }

    public static int PalindromeSubsequence(String str) {
        char[] str1 = str.toCharArray();
        char[] str2 = str.toCharArray();
        for (int i = 0; i < (str1.length / 2); i++) {
            char temp = str1[i];
            str1[i] = str1[str1.length - i - 1];
            str1[str1.length - i - 1] = temp;
        }

        return longestCommonSubsequence1(String.valueOf(str1), String.valueOf(str2));
    }

    public static void main(String[] args) {
        String str = "abcdefcba";
        char[] str1 = str.toCharArray();
        System.out.println(String.valueOf(str1));
        System.out.println(str1.toString());

        System.out.println(str);
        //法1
        for (int i = 0; i < (str1.length / 2); i++) {
            char temp = str1[i];
            str1[i] = str1[str1.length - i - 1];
            str1[str1.length - i - 1] = temp;
        }

        char[] str2 = str.toCharArray();
        //法2
        for (int i = 0, j = str2.length - 1; i < j; i++, j--) {
            char temp = str2[i];
            str2[i] = str2[j];
            str2[j] = temp;
        }
        System.out.println(str2);
        System.out.println(str1);
        System.out.println("======================");
        System.out.println(PalindromeSubsequence(str));
    }

    //way2  范围模型
    public static int lpsl1(String s) {
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    private static int f(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        if (L == R - 1) {
            return str[L] == str[R] ? 2 : 1;
        }
        int p1 = f(str, L, R - 1);
//        int p2 = f(str,L+1,R-1);
        int p3 = f(str, L + 1, R);
        int p4 = str[L] == str[R] ? 2 + f(str, L + 1, R - 1) : 0;
//        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
        return Math.max(p1, Math.max(p3, p4));
    }

    //dp
    public static int lpsl2(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N - 1][N - 1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) {//row
            for (int R = L + 2; R < N; R++) {//col
                int p1 = dp[L][R - 1];
                int p2 = dp[L + 1][R];
                int p3 = str[L] == str[R] ? 2 + dp[L + 1][R - 1] : 0;
                dp[L][R] = Math.max(p1, Math.max(p3, p2));
            }
        }
        return dp[0][N - 1];
    }

}
