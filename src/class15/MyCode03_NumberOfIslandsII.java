package class15;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Judy
 * @create 2023-03-23-14:34
 * 假设你设计一个游戏，用一个 m 行 n 列的 2D 网格来存储你的游戏地图。
 * 起始的时候，每一个格子的地形都被默认标记为「水」。咱们能够经过使用 addLand 进行操作，将位置 (row, col) 的「水」变成「陆地」。
 * 你将会被给定一个列表，来记录全部须要被操作的位置，而后你须要返回计算出来 每次 addLand 操作后岛屿的数量。
 * 注意：一个岛的定义是被「水」包围的「陆地」，经过水平方向或者垂直方向上相邻的陆地链接而成。
 */
public class MyCode03_NumberOfIslandsII {

    public static List<Integer> addIsland(int m,int n,int[][] position){
        UnionFind unionFind = new UnionFind(m, n);
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] ints : position) {
            list.add(unionFind.connect(ints[0],ints[1]));
        }
        return list;

    }

    public static class UnionFind{
        private int[] parents;
        private int[] size;
        private int[] help;
        private int col;
        private int row;
        private int sets;

        public UnionFind(int m,int n){
            col = n;
            row = m;
            parents = new int[row*col];
            size = new int[row*col];
            help = new int[row*col];
            sets = 0;
        }

        public int find(int cur){
            int hi = 0;
            if (cur != parents[cur]){
                help[hi++] = cur;
                cur = parents[cur];
            }
            for (hi-- ; hi >= 0; hi--) {
                parents[help[hi]] = cur;
            }
            return cur;
        }



        private void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
                return;//检查越界
            }
            int i1 = index(r1, c1);
            int i2 = index(r2, c2);
            if (size[i1] == 0 || size[i2] == 0) {
                return;
            }
            int f1 = find(i1);
            int f2 = find(i2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parents[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parents[f1] = f2;
                }
                sets--;
            }
        }

        private int index(int r, int c) {
            return col*r+c;
        }

        public int connect(int r,int c){
            int index = index(r, c);
            if (size[index] == 0){
                parents[index] = index;
                size[index] = 1;
                sets++;
                union(r-1,c,r,c);
                union(r+1,c,r,c);
                union(r,c-1,r,c);
                union(r,c+1,r,c);
            }
            return sets;
        }
    }
}
