package class13;

import java.util.ArrayList;

/**
 * @author Judy
 * @create 2023-03-18-20:28
 * 给定一棵二叉树的头节点head，返回这棵二叉树中最大的二叉搜索子树的头节点
 * 二叉搜索树：
 * 左右是搜索树
 * 左最大小于x小于右最小
 * 最大子树的结点个数
 * 最大子树的头节点
 */
public class MyCode02_MaxSubBSTHead {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info{
        int max;
        int min;
        int allSize;
        Node maxHead;

        public Info(int max,int min,int allSize,Node maxHead){
            this.max = max;
            this.min = min;
            this.allSize = allSize;
            this.maxHead = maxHead;
        }

        public static Node maxSubBSTHead(Node x){
            if (x == null){
                return null;
            }
            return process(x).maxHead;
        }

        private static Info process(Node x) {
            if (x == null){
                return new Info(0,0,0,null);
            }
            Info leftInfo = process(x.left);
            Info rightInfo = process(x.right);
            int max = Math.max(Math.max(leftInfo.max, rightInfo.max),x.value);
            int min = Math.min(Math.min(leftInfo.min, rightInfo.min),x.value);
            //不含x
            int allSize = Math.max(leftInfo.allSize, rightInfo.allSize);
            Node maxHead = leftInfo.allSize>rightInfo.allSize ? leftInfo.maxHead:rightInfo.maxHead;
            //包含x
//            if ((leftInfo.maxHead == null && rightInfo.maxHead == null)
//                    ||(leftInfo.maxHead == x.left
//                    && rightInfo.maxHead == x.right
//                    && leftInfo.max<x.value
//                    && x.value< rightInfo.min)){
            if ((leftInfo.maxHead == null ? true : (leftInfo.maxHead == x.left && leftInfo.max < x.value))
                    && (rightInfo.maxHead == null ? true : (rightInfo.maxHead == x.right && rightInfo.min > x.value))) {
                allSize = leftInfo.allSize+rightInfo.allSize+1;
                maxHead = x;
            }
            return new Info(max,min,allSize,maxHead);
        }

        
        //---------------------------
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
            int maxLevel = 4;
            int maxValue = 100;
            int testTimes = 10;
            for (int i = 0; i < testTimes; i++) {
                Node head = generateRandomBST(maxLevel, maxValue);
                if (maxSubBSTHead1(head) != maxSubBSTHead(head)) {
                    System.out.println(maxSubBSTHead1(head).value);
                    System.out.println(maxSubBSTHead(head).value);
                    System.out.println("Oops!");
                }
            }
            System.out.println("finish!");
        }
        public static int getBSTSize(Node head) {
            if (head == null) {
                return 0;
            }
            ArrayList<Node> arr = new ArrayList<>();
            in(head, arr);
            for (int i = 1; i < arr.size(); i++) {
                if (arr.get(i).value <= arr.get(i - 1).value) {
                    return 0;
                }
            }
            return arr.size();
        }

        public static void in(Node head, ArrayList<Node> arr) {
            if (head == null) {
                return;
            }
            in(head.left, arr);
            arr.add(head);
            in(head.right, arr);
        }

        public static Node maxSubBSTHead1(Node head) {
            if (head == null) {
                return null;
            }
            if (getBSTSize(head) != 0) {
                return head;
            }
            Node leftAns = maxSubBSTHead1(head.left);
            Node rightAns = maxSubBSTHead1(head.right);
            return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
        }
        
    }
}
