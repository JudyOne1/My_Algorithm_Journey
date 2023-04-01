package class03;

/**
 * @author Judy
 * @create 2023-04-01-14:42
 * 数组实现队列
 */
public class MyCode04_RingArray {

    public static class MyQueue {
        private int[] arr;
        private int pushi;//end
        private int polli;//begin
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            pushi = 0;
            polli = 0;
            size = 0;
            this.limit = limit;
        }

        public int nextIndex(int index) {
            return index < limit - 1 ? index + 1 : 0;
        }

        public void push(int value) {
            if (size == limit) {
                System.out.println("Array OUT OF index");
                return;
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);

        }

        public int poll(){
            if (size == 0){
                throw new RuntimeException("Array is null");
            }
            size--;
            int ans = arr[polli];
            polli = nextIndex(polli);
            return ans;
        }

    }

}
