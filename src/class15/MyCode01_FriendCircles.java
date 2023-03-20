package class15;

/**
 * @author Judy
 * @create 2023-03-20-18:46
 * 给定一个二维的 0-1 矩阵(正方形)，如果第 (i, j) 位置是 1，
 * 则表示第 i 个人和第 j 个人是朋友。已知 朋友关系是可以传递的，
 * 即如果 a 是 b 的朋友，b 是 c 的朋友，那么 a 和 c 也是朋友，
 * 换言之这 三个人处于同一个朋友圈之内。求一共有多少个朋友圈。
 */
public class MyCode01_FriendCircles {

    public int findFriendCycle(int[][] M){
        UnionFind unionFind = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = i+1; j < M.length; j++) {
                if (M[i][j] == 1){
                    unionFind.union(i,j);
                }
            }
        }
        return unionFind.sets();
    }

    public static class Node{
        int value;

        public Node(int value){
            this.value = value;
        }
    }
    public static class UnionFind{
        private int[] parents;
        private int[] size;
        private int[] help ;
        private int sets;

        public UnionFind(int N){
            parents = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int findFather(int i){
            int point = 0;
            while (i != parents[i]){
                help[point++] = i;
                i = parents[i];
            }
            for (point--; point >= 0 ; point--) {
                parents[help[point]] = i;
            }
            return i;
        }

        public void union(int a,int b){
            int ahead = findFather(a);
            int bhead = findFather(b);
            if (ahead != bhead){
                if (size[ahead] >= size[bhead]){
                    parents[b] = a;
                    size[a] += size[b];
                }else {
                    parents[a] = b;
                    size[b] += size[a];
                }
                sets--;
            }

        }
        public int sets() {
            return sets;
        }
    }

}
