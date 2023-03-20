package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * @author Judy
 * @create 2023-03-20-17:19
 * 并差集的实现
 */
public class MyCode05_UnionFind {
    public static class Node<V>{
        V value;

        public Node(V v){
            this.value = v;
        }
    }

    public static class UnionFind<V>{
        public HashMap<V,Node<V>> nodes; //值 对应 包装类
        public HashMap<Node<V>,Node<V>> parents;// 子 对应 父
        public HashMap<Node<V>,Integer> sizeMap;// 父 对应 子的数量

        public UnionFind(List<V> values){
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<V>(value);
                parents.put(node,node);
                sizeMap.put(node,1);
            }
        }

        public Node<V> findFather(Node<V> cur){
            Stack<Node<V>> stack = new Stack<>();
            while (cur != parents.get(cur)) {
                stack.add(cur);
                cur = parents.get(cur);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(),cur);
            }
            return cur;
        }

        public boolean isSameFather(V a,V b){
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a,V b){
            Node<V> nodeA = nodes.get(a);
            Node<V> nodeB = nodes.get(b);
            if (isSameFather(a,b)){
                return;
            }
            int aSize = sizeMap.get(nodeA);
            int bSize = sizeMap.get(nodeB);
            if (aSize>bSize){
                parents.put(nodeB,nodeA);
                sizeMap.put(nodeA,aSize+bSize);
                sizeMap.remove(nodeB);
            }else {
                parents.put(nodeA,nodeB);
                sizeMap.put(nodeB,aSize+bSize);
                sizeMap.remove(nodeA);
            }
        }
    }

}
