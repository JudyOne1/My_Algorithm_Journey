package AMyTest;

import java.util.*;

/**
 * @author Judy
 * @create 2023-04-06-21:39
 */
public class test01 {
    //    public static void main(String[] args) {
//        System.out.println(Integer.BYTES);
//    }
//    class Graph {
//        public HashMap<Integer, Node> nodes;
//        public HashSet<Edge> edges;
//
//        public Graph() {
//            nodes = new HashMap<>();
//            edges = new HashSet<>();
//        }
//    }
//    class Node {
//        public int value;
//        public int in;
//        public int out;
//        public ArrayList<Node> nexts;
//        public ArrayList<Edge> edges;
//
//        public Node(int value) {
//            this.value = value;
//            in = 0;
//            out = 0;
//            nexts = new ArrayList<>();
//            edges = new ArrayList<>();
//        }
//    }
//    class Edge {
//        public int weight;
//        public Node from;
//        public Node to;
//
//        public Edge(int weight, Node from, Node to) {
//            this.weight = weight;
//            this.from = from;
//            this.to = to;
//        }
//    }
    //1. 返回最大价值，2. 返回刚好装满的最大价值
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int a = in.nextInt();
        int b = in.nextInt();

        int[][] arr = new int[a + 1][2];//Index  V
        int i = 0;
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            a = in.nextInt();
            b = in.nextInt();
            arr[i][0]=a;
            arr[i++][1]=b;
        }
        int ans = process1(arr,0,b);
        System.out.println(ans);
    }
    public static int process1(int[][] arr, int Index, int rest){
        //base case
        if (Index == arr.length){
            return 0;
        }
        if (rest == 0){
            return 0;
        }
        if (rest<0){
            return -1;
        }

        int p1 = process1(arr,Index+1,rest);
        int p2 = 0;
        int next = process1(arr,Index+1,rest-arr[Index][1]);
        if (next != -1){
            p2 = arr[Index][1] + next;
        }
        return Math.max(p1,p2);
    }
}
