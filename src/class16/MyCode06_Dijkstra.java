package class16;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import java.util.HashMap;

/**
 * @author Judy
 * @create 2023-04-03-20:20+
 * 最短路径
 * 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 */
public class MyCode06_Dijkstra {
    //利用加强堆
    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes;
        private HashMap<Node, Integer> heapIndexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node,int distance){
            if (inHeap(node)){
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                insertHeapify(node,heapIndexMap.get(node));
            }
            if (!isEntered(node)){
                nodes[size] = node;
                heapIndexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node,size++);
            }
        }


        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size-1],-1);
            distanceMap.remove(nodes[size-1]);
            nodes[size-1] = null;
            heapify(0,--size);
            return nodeRecord;
        }

        public void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallSon = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1 : left;
                smallSon = distanceMap.get(nodes[smallSon]) < distanceMap.get(nodes[index]) ? smallSon : index;
                if (smallSon == index) {
                    break;
                }
                swap(index, smallSon);
                index = smallSon;
                left = index * 2 + 1;

            }
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int a, int b) {
            heapIndexMap.put(nodes[a], b);
            heapIndexMap.put(nodes[b], a);
            Node temp = nodes[a];
            nodes[a] = nodes[b];
            nodes[b] = temp;
        }


        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        public static HashMap<Node,Integer> dijkstra(Node head,int size){
            Code06_Dijkstra.NodeHeap nodeHeap = new Code06_Dijkstra.NodeHeap(size);
            nodeHeap.addOrUpdateOrIgnore(head, 0);
            HashMap<Node, Integer> result = new HashMap<>();
            while (!nodeHeap.isEmpty()) {
                Code06_Dijkstra.NodeRecord record = nodeHeap.pop();
                Node cur = record.node;
                int distance = record.distance;
                for (Edge edge : cur.edges) {
                    nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
                }
                result.put(cur, distance);
            }
            return result;
        }
    }
}
