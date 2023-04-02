package class06;

/**
 * @author Judy
 * @create 2023-04-02-14:42
 * 堆
 */
public class MyCode02_Heap {

    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            heap = new int[limit];
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }
        public int pop(){
            if (heapSize == 0){
                throw new RuntimeException("heap is empty");
            }
            int ans = heap[0];
            swap(heap,0,--heapSize);
            //数据不用删除，等下次覆盖就行了
            heapify(heap,0,heapSize);
            return ans;
        }


        private void heapInsert(int[] heap, int index) {
            while (heap[index] > heap[(index - 1) / 2]) {//看看是不是比父结点大
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int[] heap, int index, int heapSize) {
            int left = index * 2 - 1;
            while (left < heapSize) {
                int bigSon = left + 1 < heapSize &&
                        heap[left] > heap[left + 1] ? left : left + 1;
                bigSon = heap[bigSon] > heap[index]?bigSon:index;
                if (bigSon == index){
                    break;
                }
                swap(heap,bigSon,index);
                index = left;
                left = index * 2 - 1;
            }
        }

        private void swap(int[] heap, int a, int b) {
            int temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }


    }
}
