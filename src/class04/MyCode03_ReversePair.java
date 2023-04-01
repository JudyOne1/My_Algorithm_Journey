package class04;

/**
 * @author Judy
 * @create 2023-04-01-20:42
 * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为降序对
 * 给定一个数组arr，求数组的降序对总数量
 */
public class MyCode03_ReversePair {

    public static int reverPairNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        //base case
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    private static int merge(int[] arr, int L, int m, int R) {
        int[] help = new int[R - L + 1];
        int i = help.length - 1;
        int p1 = m;
        int p2 = R;
        int res = 0;
        //从后往前放 【小到大】
        while (p1 >= L && p2 >= m + 1) {
            res += arr[p1] > arr[p2] ? (p2 - m) : 0;//找小的
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[i--] = arr[p1--];
        }
        while (p2 >= m + 1) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return res;
    }
}
