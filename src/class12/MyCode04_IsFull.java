package class12;

import java.util.Calendar;

/**
 * @author Judy
 * @create 2023-03-17-21:37
 * 判断二叉树是不是满二叉树
 */
public class MyCode04_IsFull {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static class Info{
        boolean isFull;
        int height;
        
        public Info(boolean isFull,int height){
            this.height = height;
            this.isFull = isFull;
        }
    }
    public static boolean isFull(Node x){
        if (x == null){
            return true;
            
        }
        return process(x).isFull;
    }

    private static Info process(Node x) {
        if (x == null){
            return new Info(true,0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = 0;
        boolean isFull = true;
        if (leftInfo.height == rightInfo.height){
            height = leftInfo.height + 1;
        }
        if (!leftInfo.isFull || !rightInfo.isFull || leftInfo.height != rightInfo.height){
            isFull = false;
        }
        return new Info(isFull,height);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

    public static class Info1 {
        public int height;
        public int nodes;

        public Info1(int h, int n) {
            height = h;
            nodes = n;
        }
    }

    public static Info1 process1(Node head) {
        if (head == null) {
            return new Info1(0, 0);
        }
        Info1 leftInfo = process1(head.left);
        Info1 rightInfo = process1(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info1(height, nodes);
    }
    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        Info1 all = process1(head);
        return (1 << all.height) - 1 == all.nodes;
    }
}
