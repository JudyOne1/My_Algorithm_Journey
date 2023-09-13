package class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Judy
 * @create 2023-09-13-16:01
 * 单调栈的实现
 * 什么是单调栈
 * 找到数组index位置左边离自己最近比自己小的数
 * 找到数组index位置右边离自己最近比自己小的数
 */
public class MyCode01 {

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();//栈中只存储位置，位置所代表的值从栈底到栈顶由小到大
        for (int i = 0; i < arr.length; i++) {//数组遍历位置index
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {//栈非空并且栈顶位置上的数大于arr[i]【找到index位置上左边最小的】
                //eg  stack[0.1]  arr[5.6.3]  i=2  6>3
                //          ↑ ↑
                //          5.6
                int popIndex = stack.pop();//弹出1
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;//popIndex位置左边最小  0
                res[popIndex][1] = i;//popIndex位置右边最小  2
                //继续循环弹出 0 [-1,2]
            }
            stack.push(i);//加入3  stack[5.3]
        }
        while (!stack.isEmpty()) {
            //遍历完一遍arr了 stack[2]
            //                    ↑
            //                    3
            int popIndex = stack.pop();//2
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }

        // arr = [ 5.6.3 ]
        //         0 1 2
        // res[
        //     0 : [ -1,  2]
        //     1 : [  0,  2]
        //     2 : [ -1, -1]
        //    ]


        return res;
    }

    public static int[][] getNearLess(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek().get(0)>arr[i]){
                //找到更小的了
                List<Integer> popIs = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size()-1);
                for (Integer popI : popIs) {
                    //List中的每个左边最小和右边最小都找到了
                    res[popI][0] = leftLessIndex;
                    res[popI][1] = i;
                }
            }
            if (!stack.isEmpty() && stack.peek().get(0) == arr[i]){
                //相同，加到链表尾部
                stack.peek().add(Integer.valueOf(i));
            }else {
                //正常添加到栈中
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()){
            //单独结算
            List<Integer> popIs = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popI : popIs) {
                res[popI][0] = leftLessIndex;
                res[popI][1] = -1;
            }
        }
        return res;
    }


}
