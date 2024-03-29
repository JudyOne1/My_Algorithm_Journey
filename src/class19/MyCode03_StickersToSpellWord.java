package class19;


import java.util.HashMap;

/**
 * @author Judy
 * @create 2023-03-24-20:48
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c -> 3  ||  abcd + abcd -> 2  ||  abcd+ba -> 2
 * 所以返回 2
 * 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
 */
public class MyCode03_StickersToSpellWord {
    //统计不同贴纸字母个数，反正可以剪开，只需要满足str中字母个数即可

    public static void main(String[] args) {
        String target ="thehat";
        String[] stickers = {"with", "example", "science"};
        int i = minStickers00(stickers, target);
        System.out.println(i);
    }
    public static int minStickers00(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process00(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(target, first);
            if (rest.length() != target.length()) {//有效
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (char cha : str1) {//统计原字符长度
            count[cha - 'a']++;
        }
        for (char cha : str2) {//减去原字符个数
            count[cha - 'a']--;
        }
        //重组
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }
//-----------------------------------------
    public int minStickers01(String[] stickers, String target) {
        int N = stickers.length;

        int[][] counts = new int[N][26];

        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c : str) {
                counts[i][c-'a']++;
            }
        }

        int num = process01(counts, target);
        return num == Integer.MAX_VALUE ? -1 : num;
        
    }

    private int process01(int[][] stickers, String target) {
        //base case
        if (target.length() == 0){
            return 0;
        }
        char[] array = target.toCharArray();
        int[] tcounts = new int[26];
        for (char c : array) {
            tcounts[c-'a']++;
        }
        //操作
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stickers.length; i++) {
            int[] sticker = stickers[i];
            //只有包含target第一个字符的才有机会   剪枝
            if (sticker[array[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < sticker.length; j++) {
                    int number = tcounts[j] - sticker[j];
                    for (int k = 0; k < number; k++) {
                        builder.append((char)j+'a');
                    }
                }
                String restTarget = builder.toString();
                min += Math.min(min, process01(stickers, restTarget));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);

//        if (t.length() == 0) {
//            return 0;
//        }
//        // target做出词频统计
//        // target  aabbc  2 2 1..
//        //                0 1 2..
//        char[] target = t.toCharArray();
//        int[] tcounts = new int[26];
//        for (char cha : target) {
//            tcounts[cha - 'a']++;
//        }
//        int N = stickers.length;
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < N; i++) {
//            // 尝试第一张贴纸是谁
//            int[] sticker = stickers[i];
//            // 最关键的优化(重要的剪枝!这一步也是贪心!)
//            if (sticker[target[0] - 'a'] > 0) {
//                //只有包含target第一个字符的才有机会
//                StringBuilder builder = new StringBuilder();
//                for (int j = 0; j < 26; j++) {
//                    if (tcounts[j] > 0) {
//                        int nums = tcounts[j] - sticker[j];
//                        for (int k = 0; k < nums; k++) {
//                            builder.append((char) (j + 'a'));
//                        }
//                    }
//                }
//                String rest = builder.toString();
//                min = Math.min(min, process2(stickers, rest));
//            }
//        }
//        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }
//

    public static int minStickers1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    /**
     * "abc"  :  {a,b,c}
     * a -> "bc" -> b -> "c" -> c -> " "
     * 2+1    <-   1+1   <-   0+1 <- 0
     *
     * @param stickers
     * @param target
     * @return
     */
    private static int process1(String[] stickers, String target) {
        //base case
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus1(target, sticker);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static String minus1(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] count = new int[26];
        for (int i = 0; i < str1.length; i++) {
            count[str1[i] - 'a']++;
        }
        for (int i = 0; i < str2.length; i++) {
            count[str2[i] - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                builder.append((char) (i + 'a'));
                count[i]--;
            }
        }
        return builder.toString();

    }

    public static int minStickers2(String[] stickers, String target) {
        int length = stickers.length;
        int[][] count = new int[length][26];//词频优化
        for (int i = 0; i < length; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                count[i][cha - 'a']++;
            }
        }
        int ans = process2(count, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process2(int[][] stickers, String t) {
        //base case
        if (t.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        //对target做出词频统计
        char[] target = t.toCharArray();
        int[] tcount = new int[26];
        for (int i = 0; i < target.length; i++) {
            tcount[target[i] - 'a']++;
        }
        int N = stickers.length;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] -'a']>0){//剪枝  只有sticker中包含target的第一个字符
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcount[j]>0){
                        int nums = tcount[j] - sticker[j];
                        while (nums>0){
                            builder.append((char) (j+'a'));
                            nums--;
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min,process2(stickers,rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    //傻缓存
    public static int minStickers3(String[] stickers, String target) {
        int length = stickers.length;
        int[][] count = new int[length][26];//词频优化
        for (int i = 0; i < length; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                count[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(count, target,dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t) ){
            return dp.get(t);
        }
        //base case
        if (t.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        //对target做出词频统计
        char[] target = t.toCharArray();
        int[] tcount = new int[26];
        for (int i = 0; i < target.length; i++) {
            tcount[target[i] - 'a']++;
        }
        int N = stickers.length;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[target[0] -'a']>0){//剪枝  只有sticker中包含target的第一个字符
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcount[j]>0){
                        int nums = tcount[j] - sticker[j];
                        while (nums>0){
                            builder.append((char)(j+'a'));
                            nums--;
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min,process3(stickers,rest, dp));

            }
        }

        int ans =  min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t,ans);
        return ans;
    }
//    public static String minus(String s1, String s2) {
//        char[] str1 = s1.toCharArray();
//        char[] str2 = s2.toCharArray();
//        int[] count = new int[26];
//        for (char cha : str1) {//统计原字符长度
//            count[cha - 'a']++;
//        }
//        for (char cha : str2) {//减去原字符个数
//            count[cha - 'a']--;
//        }
//        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < 26; i++) {
//            if (count[i] > 0) {
//                for (int j = 0; j < count[i]; j++) {
//                    builder.append((char) (i + 'a'));
//                }
//            }
//        }
//        return builder.toString();
//    }
//
//    public static void main(String[] args) {
//        System.out.println(minus("abcdefaaa", "abcde"));
//        System.out.println(minus1("abcdefaaa", "abcde"));
//    }


}
