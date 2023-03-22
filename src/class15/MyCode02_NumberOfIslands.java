package class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Judy
 * @create 2023-03-20-20:06
 * 给定一个二维数组matrix，里面的值不是1就是0，上、下、左、右相邻的1认为是一片岛，
 * 返回matrix中岛的数量
 */
public class MyCode02_NumberOfIslands {

    //---------感染infect方法------------
    public static int numIslands(char[][] board) {
        int island = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '1') {
                    infect(board, i, j);
                    island++;
                }
            }
        }
        return island;
    }

    private static void infect(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board.length || board[i][j] != '1') {
            return;
        }
        board[i][j] = 0;//将到达过的地方设置为别的数
        infect(board, i + 1, j);
        infect(board, i - 1, j);
        infect(board, i, j + 1);
        infect(board, i, j - 1);
    }
    //------------------------并查集（map）方法----------------------

    public static int numIslands1(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        Dot[][] dots = new Dot[row][col];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        //往自己 左和上 找
        unionFind<Dot> unionFind = new unionFind<Dot>(dotList);
        for (int j = 1; j < col; j++) {//第0行
            // (0,j)  (0,0)跳过了不管了   (0,1) (0,2) (0,3)
            if (board[0][j - 1] == '1' && board[0][j] == '1') {
                unionFind.union(dots[0][j - 1], dots[0][j]);
            }
        }
        for (int i = 1; i < row; i++) {//第0列
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                unionFind.union(dots[i - 1][0], dots[i][0]);
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1' && board[i - 1][j] == '1') {
                    unionFind.union(dots[i][j], dots[i - 1][j]);
                }
                if (board[i][j] == '1' && board[i][j - 1] == '1') {
                    unionFind.union(dots[i][j], dots[i][j - 1]);
                }
            }
        }
        return unionFind.sizeMap.size();
    }

    public static class Dot {
    }

    public static class Node<V> {
        V value;

        public Node(V v) {
            this.value = v;
        }
    }

    public static class unionFind<V> {
        private HashMap<V, Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parents;
        private HashMap<Node<V>, Integer> sizeMap;

        public unionFind(List<V> value) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V v : value) {
                Node<V> node = new Node<V>(v);
                nodes.put(v, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> find(Node<V> cur) {
            List<Node<V>> list = new ArrayList<>();
            while (parents.get(cur) == cur) {
                list.add(cur);
                cur = parents.get(cur);
            }
            for (Node<V> node : list) {
                parents.put(node, cur);
            }
            return cur;
        }

        public void union(V a, V b) {
            Node<V> nodeA = nodes.get(a);
            Node<V> nodeB = nodes.get(b);
            Node<V> headA = find(nodeA);
            Node<V> headB = find(nodeB);
            if (headA == headB) {
                return;
            }
            Integer sizeA = sizeMap.get(headA);
            Integer sizeB = sizeMap.get(headB);
            if (sizeA > sizeB) {
                parents.put(headB, headA);
                sizeMap.put(headA, sizeA + sizeB);
                sizeMap.remove(headB);
            } else {
                parents.put(headA, headB);
                sizeMap.put(headB, sizeA + sizeB);
                sizeMap.remove(headA);
            }
        }
    }

    //----------------------并查集(数组)--------------------------
    public static int numIslands2(char[][] board) {
        UnionFind2 uf = new UnionFind2(board);
        int row = board.length;
        int col = board[0].length;
        //第一行
        for (int i = 1; i < col; i++) {
            if (board[0][i] == '1'&& board[0][i-1] == '1'){
                uf.union(0,i,0,i-1);
            }
        }
        //第一列
        for (int i = 1; i < row; i++) {
            if (board[i][0] == '1' && board[i-1][0] == '1'){
                uf.union(i,0,i-1,0);
            }
        }
        //其他行和列
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (board[i][j] == '1'){
                    if (board[i-1][j] == '1'){
                        uf.union(i,j,i-1,j);
                    }
                    if (board[i][j-1] == '1'){
                        uf.union(i,j,i,j-1);
                    }
                }
            }
        }
        return uf.sets;
    }

    public static class UnionFind2 {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int col;
        private int sets;

        public UnionFind2(char[][] board) {
            col = board[0].length;
            sets = 0;
            int row = board.length;
            int len = col * row;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    int i = index(r, c);
                    parent[i] = i;
                    size[i] = i;
                    sets++;
                }
            }
        }

        public int find (int i){
            int pointer = 0;
            if (parent[i] != i){
                help[pointer++] = i;
                i = parent[i];
            }
            for (pointer-- ; pointer >= 0; pointer--) {
                parent[help[pointer]] = i;
            }
            return i;
        }

        public void union(int r1,int c1,int r2,int c2){
            int a = index(r1,c1);
            int b = index(r2, c2);
            int headA = find(a);
            int headB = find(b);
            if (headA != headB){
                if (size[headA]>size[headB]){
                    size[headA] += size[headB];
                    parent[headB] = headA;
                }
                if (size[headA]<size[headB]){
                    size[headB] += size[headA];
                    parent[headA] = headB;
                }
                sets--;
            }

        }

        // (r,c) -> i  换算成下标
        private int index(int r, int c) {
            return r * col + c;
        }
    }


}
