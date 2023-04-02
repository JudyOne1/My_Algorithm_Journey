package class16;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author Judy
 * @create 2023-04-02-20:48
 */
public class MyCode02_DFS {

    // 从node出发，进行深度优先遍历
    public static void dfs(Node node){
        if (node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();//装途径点
        HashSet<Node> set = new HashSet<>();//放入已经来过的点
        stack.add(node);
        set.add(node);
        System.out.println(node.value);//入栈时打印
        while (!stack.isEmpty()){//栈中放着目前的整条路径
            Node cur = stack.pop();
            for (Node next : cur.nexts) {//有 来过的点 就换下一个
                //遍历邻居，只要有一个邻居没有被访问过，那当前点就重新入栈
                if (!set.contains(next)){//邻居没有被访问过
                    stack.push(cur);//弹出的节点重新入栈
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }

        }
    }

}
