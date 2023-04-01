package class03;

/**
 * @author Judy
 * @create 2023-03-31-15:13
 */
//双向链表实现栈和队列
public class MyCode03_DoubleEndsQueueToStackAndQueue {

    public static class Node {
        public int value;
        public Node last;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class NodeQueueAndStack {
        public Node head;
        public Node tail;

        public void addFromHead(int value) {
            Node cur = new Node(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        public void addFromBottom(int value) {
            Node cur = new Node(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        public Node popFromHead() {
            if (head == null) {
                return null;
            }
            Node cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                cur.next = null;
            }
            return cur;
        }

        public Node popFromBottom(){
            if (head == null) {
                return null;
            }
            Node cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.last = null;
                cur.last = null;
            }
            return cur;
        }
    }

    public static class MyStack{

    }

    public static class MyQueue{

    }

}
