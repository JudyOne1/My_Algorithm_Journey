package class03;

public class Code02_DeleteGivenValue {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// head = removeValue(head, 2);
	public static Node removeValue(Node head, int num) {
		// head来到第一个不需要删的位置
		while (head != null) {
			if (head.value != num) {
				break;
			}

		// 1 ) head == null  直接return
			head = head.next;
		}
		// 2 ) head != null
		Node pre = head;
		Node cur = head;
		while (cur != null) {
			//向后遍历的过程中，是num就跳过
			if (cur.value == num) {
				pre.next = cur.next;
			} else {
				//不是num就取这个数
				pre = cur;
			}
			//往后移动
			cur = cur.next;
		}
		return head;
	}

}
