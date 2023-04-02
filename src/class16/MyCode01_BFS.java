package class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Judy
 * @create 2023-04-02-20:15
 */
public class MyCode01_BFS {
    // 从node出发，进行宽度优先遍历
    public static void bfs(Node node){
        if (node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();//装途径点
        HashSet<Node> set = new HashSet<>();//放入已经来过的点
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();//queue中一个一个遍历
            System.out.println(cur.value);
            for (Node next : cur.nexts) {//有 来过的点 就换下一个
                if (!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }

        }
    }
}
