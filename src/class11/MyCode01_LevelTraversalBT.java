package class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Judy
 * @create 2023-03-15-21:58
 * 非递归二叉树层序遍历
 */
public class MyCode01_LevelTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void level(Node cur){
        if (cur!=null){
            Queue<Node> queue = new LinkedList<>();
            queue.add(cur);
            while (!queue.isEmpty()){
                cur = queue.poll();
                System.out.print(cur.value+" ");
                if (cur.left!=null){
                    queue.add(cur.left);
                }
                if (cur.right!=null){
                    queue.add(cur.right);
                }
            }
        }

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }


}
