package class26;

import java.util.Stack;

/**
 * @author Judy
 * @create 2023-09-14-18:53
 * https://leetcode.com/problems/sum-of-subarray-minimums/
 * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 */
public class MyCode01 {
    public int sumSubarrayMins(int[] arr) {
        int MOD = (int)1e9+7;
        int res = 0;
        int[][] arrRes = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int t = i < arr.length ? arr[i] : 0;
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                Integer index = stack.pop();
                int leftNearLess = stack.isEmpty() ? -1 : stack.peek();
                arrRes[index][0] = leftNearLess;
                arrRes[index][1] = i;
                //
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer index = stack.pop();
            int leftNearLess = stack.isEmpty() ? -1 : stack.peek();
            arrRes[index][0] = leftNearLess;
            arrRes[index][1] = -1;
        }
        for (int i = 0; i < arr.length; i++) {
            int left = arrRes[i][0];
            int right = arrRes[i][1];
            int leftSize = i-left;
            int rightSize = right-i;
            res +=leftSize*rightSize*arr[i]%MOD;
        }
        return res;
    }
    public static int sumSubarrayMins0(int[] arr) {
        int[] left = nearLessEqualLeft(arr);//每个位置左边离它最近 <= 它的位置
        int[] right = nearLessRight(arr);//每个位置右边离它最近 < 它的位置
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * (long) arr[i];
            ans %= 1000000007;
        }
        return (int) ans;
    }

    public static int[] nearLessEqualLeft(int[] arr) {
        int N = arr.length;
        int[] left = new int[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = -1;
        }
        return left;
    }

    public static int[] nearLessRight(int[] arr) {
        int N = arr.length;
        int[] right = new int[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = N;
        }
        return right;
    }

    /**
     * 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
     * 每个子数组的最小值相加
     * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
     * @param arr
     * @return
     */

    public int sumSubarrayMinsSec(int[] arr) {
        long MOD = 1000000007;
        int size = arr.length;
        long res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty()&&arr[stack.peek()]>=arr[i]){
                //相等的情况要怎么办？
                Integer index = stack.pop();
                long left = stack.isEmpty() ? -1 : stack.peek();
                long right = i;
                if (arr[index]==arr[i]){
                    for (int j = size-1; j > index; j--) {
                        if (arr[j] == arr[index]){
                            right = j;
                        }
                    }
                }
                res += (right-index)*(index-left)*arr[index];
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer index = stack.pop();
            long left = stack.isEmpty() ? -1 : stack.peek();
            long right = size;
            res += (right-index)*(index-left)*arr[index];
        }
        return (int) (res%MOD);
    }

}
