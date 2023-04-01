package class04;

/**
 * @author Judy
 * @create 2023-04-01-21:22
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个 重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 * 翻译就是：在一个数组中，对于任何一个数num，求有多少个(后面的数*2)依然<num，返回总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0 & 1的后面有：0 & 7的后面有：0，2 & 0的后面没有 & 2的后面没有
 * 所以总共有5个
 */
public class MyCode04_BiggerThanRightTwice {

    public static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int L, int mid, int r) {
        int count = 0;
        for (int i = L; i <= mid; i++) {
            for (int j = mid + 1; j <= r; j++) {
                if (arr[i] == arr[j]) {

                } else if ((long) arr[i] > (long) 2 * arr[j]) {
                    count++;
                }
            }
        }
        int[] help = new int[r - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return count;
    }
}
