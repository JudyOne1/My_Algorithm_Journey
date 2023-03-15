package class10;

/**
 * @author Judy
 * @create 2023-03-15-21:48
 * 我的二叉树遍历
 */
public class MyCode02_RecursiveTraversalBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int _value){
            this.value = _value;
        }
    }

    //先序遍历
    public static void pres(Node head){
        if (head == null){
            return;
        }
        //1   先序
        pres(head.left);
        //2   中序
        pres(head.right);
        //3   后序
    }
}
