package class13;

import java.util.LinkedList;

/**
 * @author Judy
 * @create 2023-03-18-19:51
 * <p>
 * 判断是否是完全二叉树
 * 可能性
 * 1 左满+右满   |左高 = 右高
 * 2 左满+右满   |左高 = 右高+1
 * 3 左满+右完全 |左高 = 右高
 * 4 左完全+右满 |左高 = 右高+1
 */
public class MyCode01_IsCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class Info {
        int height;
        boolean isCBT;//完全二叉树
        boolean isBST;//满二叉树

        public Info(int height, boolean isCBT, boolean isBST) {
            this.isBST = isBST;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static boolean isCBT(Node x){
        if (x == null){
            return true;
        }
        return process(x).isCBT;
    }

    private static Info process(Node x) {
        if (x == null){
            return new Info(0,true,true);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height)+1;
        boolean isBST = false;//满二叉树
        if (leftInfo.isBST && rightInfo.isBST && (leftInfo.height == rightInfo.height)){
            isBST = true;
        }
        boolean isCBT = false;//完全二叉树
        if (leftInfo.isBST && rightInfo.isBST && (leftInfo.height == rightInfo.height)){
            isCBT = true;
        }
        if (leftInfo.isBST && rightInfo.isBST && (leftInfo.height == rightInfo.height+1)){
            isCBT = true;
        }
        if (leftInfo.isBST && rightInfo.isCBT && (leftInfo.height == rightInfo.height)){
            isCBT = true;
        }
        if (leftInfo.isCBT && rightInfo.isBST && (leftInfo.height == rightInfo.height+1)){
            isCBT = true;
        }
        return new Info(height,isCBT,isBST);

    }

    ///------------------------------------------------------
    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null)) || (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
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
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
