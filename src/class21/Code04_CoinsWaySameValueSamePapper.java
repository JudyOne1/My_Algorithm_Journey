package class21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Code04_CoinsWaySameValueSamePapper {

	public static class Info {
		public int[] coins;
		public int[] zhangs;

		public Info(int[] c, int[] z) {
			coins = c;
			zhangs = z;
		}
	}

	public static Info getInfo(int[] arr) {
		HashMap<Integer, Integer> counts = new HashMap<>();
		for (int value : arr) {//统计  去重
			if (!counts.containsKey(value)) {
				counts.put(value, 1);
			} else {
				counts.put(value, counts.get(value) + 1);
			}
		}
		int N = counts.size();//面值的多少
		int[] coins = new int[N];
		int[] zhangs = new int[N];
		int index = 0;
		for (Entry<Integer, Integer> entry : counts.entrySet()) {
			coins[index] = entry.getKey();//面值
			zhangs[index++] = entry.getValue();//张数
		}
		return new Info(coins, zhangs);
	}

	public static int coinsWay(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		return process(info.coins, info.zhangs, 0, aim);
	}

	// coins 面值数组，正数且去重
	// zhangs 每种面值对应的张数
	public static int process(int[] coins, int[] zhangs, int index, int rest) {
		if (index == coins.length) {
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		for (int zhang = 0; zhang * coins[index] <= rest
				&& zhang <= zhangs[index]; zhang++) {
			ways += process(coins, zhangs, index + 1, rest - (zhang * coins[index]));
		}
		return ways;
	}

	public static int dp1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] zhangs = info.zhangs;
		int N = coins.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for (int zhang = 0; zhang * coins[index] <= rest
				&& zhang <= zhangs[index]; zhang++) {
					ways += dp[index + 1][rest - (zhang * coins[index])];
				}
				dp[index][rest] = ways;
			}
		}
		return dp[0][aim];
	}

	public static int dp2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		Info info = getInfo(arr);
		int[] coins = info.coins;
		int[] zhangs = info.zhangs;
		int N = coins.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				dp[index][rest] = dp[index + 1][rest];//a
				if (rest - coins[index] >= 0) {
					dp[index][rest] += dp[index][rest - coins[index]];//※
				}
				if (rest - coins[index] * (zhangs[index] + 1) >= 0) {//检查是否多加了：rest - (极限张数 + 1) * 面值 如果还大于0，那就是多加了
					dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];//减去多加的部分
				}
			}
		}
		return dp[0][aim];
	}

	// 为了测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (int) (Math.random() * maxValue) + 1;
		}
		return arr;
	}

	// 为了测试
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 为了测试
	public static void main(String[] args) {
		int maxLen = 10;
		int maxValue = 20;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinsWay(arr, aim);
//			int ans3 = coinsWay1(arr, aim);
			int ans2 = dp1(arr, aim);
			int ans3 = dp2(arr, aim);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
				break;
			}
		}
		System.out.println("测试结束");
	}
//	public static int coinsWay1(int[] arr, int aim) {
//		if (arr == null || arr.length == 0 || aim < 0) {
//			return 0;
//		}
//		Arrays.sort(arr);
//		return process1(arr, 0, aim);
//	}
//
//	private static int process1(int[] arr, int index, int rest) {
//		if (rest < 0) {
//			return 0;
//		}
//		if (rest == 0){
//			return 1;
//		}
//		if (arr.length == index) {
//			return rest == 0 ? 1 : 0;
//		}
//		return process1(arr, index + 1, rest) + process1(arr, index + 1, rest - arr[index]);
//	}

}
