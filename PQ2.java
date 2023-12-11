import java.util.Comparator;


public   class PQ2<T> {
    private T[] heap;
    private int size;
    private Comparator<T> comparator; 
    private TreeMap<Integer, Integer> idToIndexMap;
    private static final int DEFAULT_CAPACITY = 4; 
    private static final int AUTOGROW_SIZE  = 4;
    public PQ(Comparator<T> comparator){
        this.heap = (T[]) new Object[DEFAULT_CAPACITY +1];
        this.size = 0 ;
        this.comparator = comparator;
        this.idToIndexMap = new TreeMap<>();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size ==0 ;
    }
    public void insert(T item){
        if(size >= heap.length*75/100 ){
            grow();
        }
        heap[++size] = item;
        idToIndexMap.put(((City)item).getID(), size);
        swim(size);
    }

    public  T  min() {
       
        if (size == 0)
            return null;

        return heap[1];
    }

    public T  getmin(){
        if(size==0){
            return null;
        }
        T root = heap[1];
        heap[1] = heap[size];
        size--;
        sink(1);
        return root;
    }
    
    private void swim(int i){
        if(i==1){
            return; 
        }
        int parent = i/2 ;
        while (i!=1 && comparator.compare((heap[i]),heap[parent])<0) {
            swap(i,parent); 
            i = parent;
            parent = i/2;
        }
    }

     // Edw den exw idea pws na to kanw se O(n) h o O(logn)???
     public T remove(int id){
        if(isEmpty() || !idToIndexMap.containsKey(id)){
            return null;
        }
        int i = idToIndexMap.get(id);
        T item = heap[i];
        swap(i, size);
        size--;
        sink(i);
        swim(i);
        idToIndexMap.remove(id);
        return item;
    }

    private void sink(int i){
            while (2*i <= size) { 
                

                    int j = 2*i;  

                    if ((j < size) && comparator.compare(heap[j],heap[j+1])>0) 
                    j++;

                    if (comparator.compare(heap[i],heap[j])<0) 
                    break;  

                    swap(i, j);;  
                    i = j;      
                    }
                    
    }

    private void swap(int i, int j) {
        T tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        idToIndexMap.put(((City)heap[i]).getID(), i);
        idToIndexMap.put(((City)heap[j]).getID(), j);
    }

    private void grow() {
    	T[] newHeap = (T[]) new Object[heap.length + AUTOGROW_SIZE];
    
       for (int i = 0; i <= size; i++) {
           newHeap[i] = heap[i];   
       }
        heap = newHeap;
       
    }

}
