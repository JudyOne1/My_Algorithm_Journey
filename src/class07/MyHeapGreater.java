package class07;

import java.util.*;

/**
 * @author Judy
 * @create 2023-04-02-19:37
 * 加强堆的实现
 */
public class MyHeapGreater<T> {

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;//反向索引表
    private int HeapSize;
    private Comparator<? super T> comp;

    public MyHeapGreater(Comparator<? super T> c) {
        this.comp = c;
        indexMap = new HashMap<>();
        HeapSize = 0;
        heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return HeapSize == 0;
    }

    public int size() {
        return HeapSize;
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }

    public T peek() {
        return heap.get(0);
    }

    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, HeapSize);
        heapInsert(HeapSize++);
    }

    public T pop(){
        T pop = heap.get(0);
        swap(0,HeapSize-1);
        indexMap.remove(pop);
        heap.remove(--HeapSize);
        heapify(0);
        return pop;
    }

    public void remove(T obj){
        int index = indexMap.get(obj);
        T replace = heap.get(HeapSize - 1);
        indexMap.remove(obj);
        heap.remove(--HeapSize);
        if (obj != replace){
            heap.set(index,replace);
            indexMap.put(replace,index);
            resign(replace);
        }

    }

    private void heapInsert(int index) {
        //往上找
        while (comp.compare(heap.get((index - 1) / 2), heap.get(index)) > 0) {
            swap((index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        T ii = heap.get(i);
        T jj = heap.get(j);
        heap.add(j, ii);
        heap.add(i, jj);
        indexMap.put(ii, j);
        indexMap.put(jj, i);
    }

    private void heapify(int index) {
        int left = index * 2 + 1;//往下
        while (left < HeapSize) {
            int bigSon = left + 1 < HeapSize && comp.compare(heap.get(left), heap.get(left + 1)) > 0 ? left : left + 1;
            bigSon = comp.compare(heap.get(index), heap.get(bigSon)) > 0 ? index : bigSon;
            if (bigSon == index) {
                break;
            }
            swap(index, bigSon);
            index = bigSon;
            left = index * 2 + 1;
        }
    }

    public void resign(T obj){
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    public List<T> getAllElement(){
        ArrayList<T> list = new ArrayList<>();
        for (T t : heap) {
            list.add(t);
        }
        return list;
    }
}
