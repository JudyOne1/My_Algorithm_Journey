package class19;

/**
 * @author Judy
 * @create 2023-03-24-20:48
 * 规定1和A对应、2和B对应、3和C对应 ... 26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class MyCode02_ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] str, int i) {
        //base case
        if (str.length == i) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        //可能性1：i单独转换;
        int ways = process(str, i + 1);
        //可能性2：两位数转换;
        if (str.length > 1 + i && str[i + 1] < '8' && str[i] < '3') {
            ways = process(str, i + 2);
        }
        return ways;
    }

    private static int dp1(String str) {
        int[] dp = new int[str.toCharArray().length];
        if (str == null || str.length() == 0) {
            return 0;
        }
        for (int j = 0; j < dp.length; j++) {
            dp[j] = -1;
        }
        return process(str.toCharArray(), 0, dp);
    }

    private static int process(char[] str, int i, int[] dp) {
        //base case
        if (dp[i] != -1) {
            return dp[i];
        }
        if (str.length == i) {
            return 1;
        }
        if (str[i] == '0') {
            return 0;
        }
        //可能性1：i单独转换;
        int ways = process(str, i + 1);
        //可能性2：两位数转换;
        if (str.length > 1 + i && str[i + 1] < '8' && str[i] < '3') {
            ways = process(str, i + 2);
        }
        dp[i] = ways;
        return ways;
    }

    public static int dp2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] != '0') {
                //可能性1：i单独转换;
                int ways = dp[i + 1];
                //可能性2：两位数转换;
                if (str.length > 1 + i && str[i + 1] < '8' && str[i] < '3') {
                    ways = dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    // 为了测试
    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = number(s);
            int ans1 = dp1(s);
            int ans2 = dp2(s);
            int ans3 = ConvertToLetterString00(s);
            if (ans0 != ans1 || ans0 != ans2 || ans0 != ans3) {
                //貌似出错了
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 规定1和A对应、2和B对应、3和C对应 ... 26和Z对应
     * 那么一个数字字符串比如"111”就可以转化为:
     * "AAA"、"KA"和"AK"
     * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
     */
    public static int ConvertToLetterString00(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArray = str.toCharArray();
        return process00(charArray, 0);
    }

    private static int process00(char[] charArray, int index) {
        //base case
        if (index == charArray.length) {
            return 1;
        }
        if (index > charArray.length) {
            return 0;
        }
        if (charArray[index] == '0') {
            return 0;
        }
        //只是一个
        int ways = process00(charArray, index + 1);
        //两个一起
        if (index + 1 < charArray.length && Integer.valueOf(charArray[index] + "" + charArray[index + 1]) < 27) {
            ways += process00(charArray, index + 2);
        }
        return ways;
    }


}
