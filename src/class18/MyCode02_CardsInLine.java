package class18;

/**
 * @author Judy
 * @create 2023-03-23-21:51
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 */
public class MyCode02_CardsInLine {
    public static int win1(int[] arr) {
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    private static int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[R];
        }
        int count = Math.max(arr[L] + g1(arr, L + 1, R), arr[R] + g1(arr, L, R - 1));
        return count;
    }

    private static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int count = Math.min(f1(arr, L + 1, R), f1(arr, L, R - 1));
        return count;
    }

    private static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(arr, 0, arr.length - 1, fmap, gmap);
        int second = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, second);
    }


    // arr[L..R]，先手获得的最好分数返回
    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            ans = Math.max(arr[L] + f2(arr, L + 1, R, fmap, gmap), arr[R] + g2(arr, L, R - 1, fmap, gmap));
            fmap[L][R] = ans;
        }
        return ans;
    }

    public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans = 0;
        if (L != R) {
            int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
            int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }

    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];

        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i]; // 对角线  base case
            gmap[i][i] = arr[i];
        }
        //
        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) {
                fmap[L][R] = Math.max((arr[L] + gmap[L + 1][R]), (arr[R] + gmap[L][R - 1]));
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }



    public static boolean predictTheWinner1(int[] nums) {

        return winner1(nums);
    }

    private static boolean winner1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int first = frist1(nums, left, right);
        int later = later1(nums, left, right);
        System.out.println(first);
        System.out.println(later);
        return first >= later ? true : false;
    }

    private static int frist1(int[] nums, int left, int right) {
        if (left == right){
            return nums[left];
        }
        return Math.max(nums[left]+later1(nums,left+1,right),nums[right]+later1(nums,left,right-1));
    }

    private static int later1(int[] nums, int left, int right) {
        if (left == right){
            return 0;
        }
        return Math.min(frist1(nums,left+1,right),frist1(nums,left,right-1));
    }
    private static boolean winner2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int N = nums.length;
        int[][] fMap = new int[N][N];
        int[][] lMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fMap[i][j] = -1;
                lMap[i][j] = -1;
            }
        }
//        for (int i = 0; i < N; i++) {
//            lMap[i][i] = 0;
//            fMap[i][i] = nums[i];
//        }
        int first = frist2(nums, left, right,fMap,lMap);
        int later = later2(nums, left, right,fMap,lMap);
        System.out.println(first);
        System.out.println(later);
        return first >= later ? true : false;
    }

    private static int frist2(int[] nums, int left, int right, int[][] fMap, int[][] lMap) {
        if (fMap[left][right] != -1){
            return fMap[left][right];
        }
        int ans = 0;
        if (left == right){
            ans = nums[left];
        }else {
            ans =  Math.max(nums[left]+later2(nums,left+1,right,fMap,lMap),nums[right]+later2(nums,left,right-1,fMap,lMap));
        }
        fMap[left][right] = ans;
        return ans;
    }

    private static int later2(int[] nums, int left, int right, int[][] fMap, int[][] lMap) {
        if (lMap[left][right] != -1){
            return lMap[left][right];
        }
        int ans = 0;
        if (left != right){
            ans = Math.min(frist2(nums,left+1,right,fMap,lMap),frist2(nums,left,right-1,fMap,lMap));
        }
        lMap[left][right] = ans;
        return ans;
    }
    private static boolean winner3(int[] nums) {
        int N = nums.length;
        int[][] fMap = new int[N][N];
        int[][] lMap = new int[N][N];

        //base case
        for (int i = 0; i < N; i++) {
            fMap[i][i] = nums[i];
        }

        //推演
        for (int startCol = 1; startCol < N; startCol++) {
            int l = 0;
            int r = startCol;
            while (r<N){
                fMap[l][r] = Math.max(nums[l]+lMap[l+1][r],nums[r]+lMap[l][r-1]);
                lMap[l][r] = Math.min(fMap[l+1][r],fMap[l][r-1]);
                l++;
                r++;
            }
        }

        int first = fMap[0][N-1];
        int later = lMap[0][N-1];

        printTwoDimensionalArray(fMap);
        printTwoDimensionalArray(lMap);
        System.out.println(first);
        System.out.println(later);

        return first >= later ? true : false;

        //
//        for (int i = 0; i < N; i++) {
//            fMap[i][i] = nums[i];
//        }
//        for (int startCol = 0; startCol < N; startCol++) {
//            int left = 0;
//            int right = startCol;
//            while (right<N){
//                //从下往上
//                fMap[left][right] = Math.max(nums[left]+lMap[left+1][right],nums[right]+lMap[left][right-1]);
//                lMap[left][right] = Math.min(fMap[left+1][right],fMap[left][right-1]);
//                right--;
//                left++;
//            }
//
//
//        }
//        int first = fMap[0][N-1];
//        int later = lMap[0][N-1];
    }


    public static void main(String[] args) {
        int[] nums = {1,5,233,7};
        System.out.println(winner3(nums));
    }
    public static void printTwoDimensionalArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();  // 打印完一行后换行
        }
    }

}
