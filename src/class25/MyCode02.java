package class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Judy
 * @create 2023-09-13-18:46
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 * https://leetcode.com/problems/maximum-subarray-min-product/
 */
public class MyCode02 {

    public static int[][] getNearLess(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popI : popIs) {
                    res[popI][0] = leftLessIndex;
                    res[popI][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popI : popIs) {
                res[popI][0] = leftLessIndex;
                res[popI][1] = -1;
            }
        }

        return res;
    }

    public static int max3(int[] arr) {
        int max = 0;
        //生成前缀和数组
        int[] sums = new int[arr.length];
        sums[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sums[i] = arr[i] + sums[i - 1];
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                Integer index = stack.pop();//以index作为最小值
                //                                          index右边最小的值👇    index左边最小的数👇
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[index]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[arr.length - 1] : (sums[arr.length - 1] - sums[stack.peek()])) * arr[index]);
        }
        return max;
    }

    public static int max1(int[] nums) {
        long max = 0;
        //生成前缀和数组
        long[] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = nums[i] + sums[i - 1];
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                Integer index = stack.pop();//以index作为最小值
                //                                          index右边最小的值👇    index左边最小的数👇
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * nums[index]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[nums.length - 1] : (sums[nums.length - 1] - sums[stack.peek()])) * nums[index]);
        }
        return (int) (max % 1000000007);
    }


}
