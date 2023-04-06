package class16;

/**
 * @author Judy
 * @create 2023-04-06-16:45
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 *
 * 如果小镇法官真的存在，那么：
 *
 * 小镇法官不会信任任何人。
 * 每个人（除了小镇法官）都信任这位小镇法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 *
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 */
public class Leetcode997 {
    public int findJudge(int n, int[][] trust) {
        if(n == 1){
            return 1;
        }
        if (trust.length == 0){
            return -1;
        }
        Graph graph = createGraph(trust,n);
        int[] helper = new int[n + 1];
        for (Edge edge : graph.edges) {
            helper[edge.to.value]++;
        }
        for (Node node : graph.nodes.values()) {
            if (node.out == 0 && helper[node.value]==n-1){
                return node.value;
            }
        }
        return -1;
    }
    public static Graph createGraph(int[][] matrix,int n) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            // 拿到每一条边， matrix[i]
            int weigh = matrix[i][0];
            int from = matrix[i][0];
            int to = matrix[i][1];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weigh, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
