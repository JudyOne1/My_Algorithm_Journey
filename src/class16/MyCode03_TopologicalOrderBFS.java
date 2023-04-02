package class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Judy
 * @create 2023-04-02-21:13
 * 给定一个有向图，图节点的拓扑排序定义如下:
 * ● 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
 * ● 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
 * 针对给定的有向图找到任意一种拓扑排序的顺序.
 */
public class MyCode03_TopologicalOrderBFS {
    // 不要提交这个类
    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();//邻居
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<>();
        for (DirectedGraphNode cur : graph) {//放入Map中并且设置好初始值
            indegreeMap.put(cur, 0);
        }
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode next : cur.neighbors) {//设置【被指向】个数
                indegreeMap.put(next, indegreeMap.get(next) + 1);//邻居有它，【被指向】个数+1
            }
        }
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        for (DirectedGraphNode cur : indegreeMap.keySet()) {//广度优先的queue
            if (indegreeMap.get(cur) == 0) {//没有【被指向】的加入到队列中
                zeroQueue.add(cur);
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroQueue.isEmpty()){
            DirectedGraphNode cur = zeroQueue.poll();//弹出没有【被指向】的
            ans.add(cur);//加入到结果集
            for (DirectedGraphNode next : cur.neighbors) {
                indegreeMap.put(next,indegreeMap.get(next) -1);//关联的邻居【被指向】个数-1
                if (indegreeMap.get(next) == 0){//没有【被指向】的加入到队列中
                    zeroQueue.offer(next);//Java中Queue的offer方法是用于将指定的元素插入队列的末尾。
                }
            }
        }
        return ans;
    }
}
