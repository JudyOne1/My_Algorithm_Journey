package class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Judy
 * @create 2023-03-20-16:49
 * 输入正数数组costs、正数数组profits、正数K和正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目，不能并行的做项目。
 * 输出：最后获得的最大钱数
 */
public class MyCode04_IPO {
    //按照成本升序 找成本最低的
    //按照收益降序 找目前能做的
    //做收益高的
    public static class Program {
        public int p;//利润
        public int c;//成本

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> costsQueue = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> profitQueue = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
            costsQueue.add(new Program(Profits[i],Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!costsQueue.isEmpty() && costsQueue.peek().c<=W){
                profitQueue.add(costsQueue.poll());//找目前能做的项目
            }
            if (profitQueue.isEmpty()){//没有项目能做了
                return W;
            }
            //选择目前能做的中利润最高的
            W+=profitQueue.poll().p;
        }
        return W;
    }

    //成本升序
    public static class MinCostComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }
    //收益降序
    public static class MaxProfitComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }

}
