package class12;

/**
 * @author Judy
 * @create 2023-03-17-20:55
 * 判断二叉树是不是平衡二叉树
 */
public class MyCode03_IsBalanced {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    public static class Info{
        int height;
        boolean isBalanced;

        public Info(int height,boolean isBalanced){
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
    public static boolean isBalanced(Node x){
        if (x == null){
            return true;
        }
        return process(x).isBalanced;
    }

    private static Info process(Node x) {
        if (x == null){
            return new Info(0,true);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        boolean isBalanced = true;
        
        if (!rightInfo.isBalanced || !leftInfo.isBalanced ) {
            isBalanced = false;
        }
        
        if (Math.abs(leftInfo.height-rightInfo.height)>1){
            isBalanced = false;
        }
        
        int heigh = Math.max(leftInfo.height,rightInfo.height)+1;

        return new Info(heigh,isBalanced);
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
    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
