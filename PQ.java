import java.util.Comparator;

public class PQ<T> {
    private T[] heap;
    private int size;
    private Comparator<T> comparator;
    private int[] idToIndex;
    private int maxId;


    private static final int DEFAULT_CAPACITY = 128;
    private static final int AUTOGROW_SIZE = 4;

    public PQ(Comparator<T> comparator) {
        this.heap = (T[]) new Object[DEFAULT_CAPACITY + 1];
        this.size = 0;
        this.comparator = comparator;
        this.maxId = DEFAULT_CAPACITY;
        this.idToIndex = new int[maxId + 1];
      }
      

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void insert(T item){
        if(size >= heap.length * 75 / 100){
            grow();
        }
        heap[++size] = item;
        idToIndex[((City)item).getID()] = size;
        swim(size);
    }

    public T min(){
        if (size == 0)
            return null;
        return heap[1];
    }

    public T getmin(){
        if(size == 0){
            return null;
        }
        T root = heap[1];
        heap[1] = heap[size];
        idToIndex[((City)heap[1]).getID()] = 1;
        size--;
        sink(1);
        return root;
    }

    public T remove(int id){
        if(isEmpty()){
            return null;
        }
        int i = idToIndex[id];
        T removedItem = heap[i];
        swap(i, size);
        idToIndex[((City)heap[i]).getID()] = i;
        idToIndex[id] = 0;
        size--;
        sink(i);
        return removedItem;
    }

    private void swim(int i){
        while (i != 1) {
            int parent = i / 2;
            if (comparator.compare(heap[i], heap[parent]) < 0) {
                swap(i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    private void sink(int i){
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && comparator.compare(heap[j], heap[j + 1]) > 0) {
                j++;
            }
            if (comparator.compare(heap[i], heap[j]) < 0) {
                break;
            }
            swap(i, j);
            i = j;
        }
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        int tmpId = idToIndex[((City)heap[i]).getID()];
        idToIndex[((City)heap[i]).getID()] = idToIndex[((City)heap[j]).getID()];
        idToIndex[((City)heap[j]).getID()] = tmpId;
    }

    private void grow() {
        T[] newHeap = (T[]) new Object[heap.length + AUTOGROW_SIZE];
        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
        int[] newIdToIndex = new int[maxId * 2 + 1];
        System.arraycopy(idToIndex, 0, newIdToIndex, 0, idToIndex.length);
        idToIndex = newIdToIndex;
        maxId *= 2;
    }
}