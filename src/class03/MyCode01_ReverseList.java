package class03;

/**
 * @author Judy
 * @create 2023-03-04-22:01
 * 实现链表的反转
 */
public class MyCode01_ReverseList {
    //java实现单链表
    public static class Node {
        Node next;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }

    //java实现双向链表链表
    public static class DoubleNode {
        DoubleNode before;
        DoubleNode next;
        int data;

        public DoubleNode(int data) {
            this.data = data;
        }
    }

    //实现链表的反转
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;

        while (head.next != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    //实现删除给定的值
    public static Node removeValue(Node head, int num) {
        while (head != null) {
            if (head.data != num) {
                break;

            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.data == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
