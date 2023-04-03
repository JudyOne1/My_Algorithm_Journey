package class16;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Judy
 * @create 2023-04-03-11:34
 * 最小生成树算法Prim
 */
public class MyCode05_Prim {
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
    public static Set<Edge> primMST(Graph graph){
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> nodeSet = new HashSet<>();//点集
        HashSet<Edge> result = new HashSet<>();//结果集
        for (Node node : graph.nodes.values()) {//随便找个node点
            if (!nodeSet.contains(node)){
                nodeSet.add(node);//将新出现的点加入至点集中
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);//解锁当前点的边
                }
                while (!priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;//通过边解锁下一个新的点
                    if (!nodeSet.contains(toNode)) {
                        nodeSet.add(toNode);//加入点集【登记】
                        result.add(edge);//加入至结果集
                        for (Edge nextEdge : toNode.edges) {//又解锁新的边
                            priorityQueue.add(nextEdge);
                        }
                    }
                    //目前的边走完了，继续循环
                }
            }
        }
        return result;
    }

    public static Set<Edge> myPrimMST(Graph graph){
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> nodeSet = new HashSet<>();
        HashSet<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)){
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)){
                        nodeSet.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

}
