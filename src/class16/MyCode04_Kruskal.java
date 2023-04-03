package class16;

import java.util.*;

/**
 * @author Judy
 * @create 2023-04-03-9:56
 * 最小生成树算法Kruskal
 * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 * 适用于边少
 */
public class MyCode04_Kruskal {
    //并查集
    //TODO 再写一次
    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public void makeSet(Collection<Node> nodes){
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public Node Find(Node node) {
            Stack<Node> path = new Stack<>();
            while (fatherMap.get(node) != node) {
                path.add(node);
                node = fatherMap.get(node);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), node);
            }
            return node;
        }

        public boolean isSameFather(Node a, Node b) {
            return Find(a) == Find(b);
        }

        public void union(Node a, Node b) {
            if (isSameFather(a, b)) {
                return;
            }
            Node fatherA = Find(a);
            Node fatherB = Find(b);
            Integer sizeA = sizeMap.get(fatherA);
            Integer sizeB = sizeMap.get(fatherB);
            if (sizeA >= sizeB) {
                fatherMap.put(fatherB, fatherA);
                sizeMap.put(fatherA, sizeA + sizeB);
                sizeMap.remove(fatherB);
            }
            if (sizeB > sizeA) {
                fatherMap.put(fatherA, fatherB);
                sizeMap.put(fatherB, sizeA + sizeB);
                sizeMap.remove(fatherA);
            }
        }
    }
    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight-o2.weight;
        }
    }
    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        HashSet<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameFather(edge.from,edge.to)){
                result.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return result;
    }

    public static Set<Edge> myKruskalMST(Graph graph){
        HashSet<Edge> set = new HashSet<>();
        UnionFind unionFind = new UnionFind();
        unionFind.makeSet(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameFather(edge.from,edge.to)){
                set.add(edge);
                unionFind.union(edge.from,edge.to);
            }
        }
        return set;

    }

}
