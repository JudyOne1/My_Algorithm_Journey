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
            while (!stack.isEmpty() && heights[stack.peek()]>=heights[i]){
                Integer index = stack.pop();
                int y = heights[index];
                int j = stack.isEmpty()?-1:stack.peek();
                int x = i-j-1;
                max = Math.max(max,y*x);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer index = stack.pop();
            int y = heights[index];
            int j = stack.isEmpty()?-1:stack.peek();
            int x = size-j-1;
            max = Math.max(max,y*x);
        }
        return max;
    }
}
