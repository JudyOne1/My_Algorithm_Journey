package class10;

import java.lang.annotation.ElementType;
import java.util.Stack;

/**
 * @author Judy
 * @create 2023-03-15-22:01
 * 非递归二叉树遍历
 */
public class MyCode03_UnRecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
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

        pre(head);
        System.out.println("========");
        ins(head);
        System.out.println("========");
        pos(head);
    }

    public static void pre(Node head){
        System.out.println("先序遍历");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            Node cur;
            while (!stack.isEmpty()){
                cur = stack.pop();
                System.out.print(cur.value+" ");
                if (cur.right!=null){
                    stack.push(cur.right);
                }
                if (cur.left!=null){
                    stack.push(cur.left);
                }
            }
        }
    }
    public static void ins(Node cur){
        System.out.println("中序遍历");
        if (cur != null){
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || cur!=null){
                if (cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }else {
                    cur = stack.pop();
                    System.out.print(cur.value+" ");
                    cur = cur.right;
                }
            }
        }
    }

    //头左右
    //左右头  头右左
    public static void pos(Node head){
        System.out.println("后序遍历");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            Stack<Node> str = new Stack<>();
            stack.push(head);
            Node cur;
            while (!stack.isEmpty()){
                cur = stack.pop();
                str.push(cur);
                if (cur.left!=null){
                    stack.push(cur.left);
                }
                if (cur.right!=null){
                    stack.push(cur.right);
                }
            }
            while (!str.isEmpty()){
                System.out.print(str.pop().value+" ");
            }
        }
    }
}
