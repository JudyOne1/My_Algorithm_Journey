package class25;

import java.util.Stack;

/**
 * @author Judy
 * @create 2023-09-13-21:55+
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
 */
public class MyCode04 {

    public static int maximalRectangle(char[][] map) {
        int[] heights = new int[map[0].length];
        //数组压缩
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                heights[j] = map[i][j] == '0' ? 0 : heights[j] + 1;
            }
            Stack<Integer> stack = new Stack<>();
            for (int k = 0; k < heights.length; k++) {
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[k]) {
                    Integer index = stack.pop();
                    int left = stack.isEmpty() ? -1 : stack.peek();//左边最小
                    int x = k - left - 1;
                    int y = heights[index];
                    int size = x * y;
                    max = Math.max(max, size);
                }
                stack.add(k);
            }
            while (!stack.isEmpty()) {
                Integer index = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();//左边最小
                int x = heights.length - left - 1;
                int y = heights[index];
                int size = x * y;
                max = Math.max(max, size);
            }
        }
        return max;
    }

    /**
     * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     *
     * @param matrix
     * @return
     */
    public int maximalRectangleSec(char[][] matrix) {
        //层层叠加
        int[] heights = new int[matrix[0].length];
        int maxSize = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0 : heights[j] + 1;
            }
            for (int k = 0; k < heights.length; k++) {
                while (!stack.isEmpty()&&heights[stack.peek()]>=heights[k]){
                    Integer index = stack.pop();
                    int left = stack.isEmpty()?-1: stack.peek();
                    int right = k;
                    int size = (right-1-left)*heights[index];
                    maxSize = Math.max(maxSize,size);
                }
                stack.push(k);
            }
            while (!stack.isEmpty()){
                Integer index = stack.pop();
                int left = stack.isEmpty()?-1: stack.peek();
                int right = heights.length;
                int size = (right-1-left)*heights[index];
                maxSize = Math.max(maxSize,size);
            }
        }
        return maxSize;


    }
}
