package class25;

import com.sun.glass.ui.Size;

import java.util.Stack;

/**
 * @author Judy
 * @create 2023-09-13-21:20
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 */
public class MyCode03 {
    public int largestRectangleArea(int[] heights) {
        int max = Integer.MIN_VALUE;
        int size = heights.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                Integer index = stack.pop();
                int y = heights[index];
                int j = stack.isEmpty() ? -1 : stack.peek();
                int x = i - j - 1;
                max = Math.max(max, y * x);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            int y = heights[index];
            int j = stack.isEmpty() ? -1 : stack.peek();
            int x = size - j - 1;
            max = Math.max(max, y * x);
        }
        return max;
    }

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * <p>
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     */
    public int largestRectangleAreaSec(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                Integer index = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                int area = (right - left - 1) * heights[index];
                max = Math.max(area, max);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int right = heights.length;
            int area = (right - left - 1) * heights[index];
            max = Math.max(area, max);
        }
        return max;
    }
}
