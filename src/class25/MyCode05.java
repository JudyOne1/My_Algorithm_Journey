package class25;

import java.util.Stack;

/**
 * @author Judy
 * @create 2023-09-14-18:53
 * Leetcode1504.统计全 1 子矩形（中等）
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的子矩形数量
 */
//101  2
//210  4  1+3
//320  7  1+3+3  13

//0110  3
//0221  3+6
//1330  3+3+6    24
public class MyCode05 {
    public static int numSubmat(int[][] mat) {
        int res = 0;
        int[] heights = new int[mat[0].length];
        for (int i = 0; i < mat.length; i++) {        //i 层数
            for (int j = 0; j < mat[0].length; j++) { //j 列
                heights[j] = mat[i][j] == 0 ? 0 : heights[j] + 1;
            }
            Stack<Integer> stack = new Stack<>();
            for (int k = 0; k < heights.length; k++) {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[k]) {
                    Integer index = stack.pop();
                    if (heights[index] > heights[k]) {
                        int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                        int rightLessIndex = k;
                        int topToDown = heights[index] - Math.max(leftLessIndex == -1 ? 0 : heights[leftLessIndex], heights[rightLessIndex]);
                        res += topToDown * (rightLessIndex - leftLessIndex - 1) * (rightLessIndex - leftLessIndex) / 2;
                    }
                }
                stack.push(k);
            }
            while (!stack.isEmpty()) {
                Integer index = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                int topToDown = heights[index] - (leftLessIndex == -1 ? 0 : heights[leftLessIndex]);
                res += topToDown * (heights.length - leftLessIndex - 1) * (heights.length - leftLessIndex) / 2;
                //为什么右边界是heights.length，因为单调栈可能的最小值如果是0，那么在这个while循环里stack只剩下0
                //单调栈可能的最小值如果是1或>1，那么右边界只能是heights.length
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[][] mat = {{0, 1, 1, 0}, {0, 1, 1, 1}, {1, 1, 1, 0}};
        int i = numSubmat(mat);
        System.out.println("ans = " + i);
    }

    /**
     * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
     */
    public int numSubmatSec(int[][] mat) {
        int[] height = new int[mat[0].length];
        int nums = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }
            for (int k = 0; k < height.length; k++) {
                while (!stack.isEmpty()&& height[stack.peek()]>=height[k]){
                    Integer index = stack.pop();
                    if (height[index]>height[k]){
                        int left = stack.isEmpty()?-1:stack.peek();
                        int right = k;
                        int longSize = height[index]-Math.max((left==-1?0:height[left]),height[right]);
                        int num = (right-left-1)*(right-left)/2*longSize;
                        nums+=num;
                    }
                }
                stack.push(k);
            }
            while (!stack.isEmpty()){
                Integer index = stack.pop();
                int left = stack.isEmpty()?-1:stack.peek();
                int right = height.length;
                int longSize = height[index]-(left==-1?0:height[left]);
                int num = (right-left-1)*(right-left)/2*longSize;
                nums+=num;
            }
        }
        return nums;
    }
}
