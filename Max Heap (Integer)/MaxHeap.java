import java.util.Arrays;

public class MaxHeap {
	
	int cap = 10;
	int size = 0;
	int[] arr;
	
	MaxHeap() {
		arr = new int[cap];
	}
	
	MaxHeap(int val) {
		this();
		arr[0] = val;
		size++;
	}
	
	public int parentIndex(int index){
		return (index-1)/2;
	}
	
	public int parentVal(int index){
		return arr[parentIndex(index)];
	}
	
	public boolean hasLeft(int index){
		return index*2+1 < size;
	}
	
	public int leftIndex(int index){
		return index*2+1;
	}
	
	public int leftVal(int index){
		return arr[leftIndex(index)];
	}
	
	public boolean hasRight(int index){
		return index*2+2 < size;
	}
	
	public int rightIndex(int index){
		return index*2+2;
	}
	
	public int rightVal(int index){
		return arr[rightIndex(index)];
	}
	
	
	public void insert(int val){
		if(size==cap){
			cap *= 2;
			arr = Arrays.copyOf(arr, cap);
		}
		arr[size] = val;
		size++;
		heapifyUp();
	}
	
	public void insert(int[] a){
		for(int i: a){
			insert(i);
		}
	}
	
	
	private void heapifyUp() {
		int index = size-1;
		while( parentIndex(index) != index && parentVal(index) < arr[index]){
			swap(parentIndex(index), index);
			index = parentIndex(index);
		}
	}
	
	
	public int poll(){
		if(size==0){
			throw new IllegalStateException();
		}
		int ans = arr[0];
		arr[0] = arr[size-1];
		size--;
		heapifyDown();
		System.out.print(ans + " | ");
		return ans;
	}
	
	
	private void heapifyDown() {
		int index=0;
		while(hasLeft(index)){
			int largerIndex = leftIndex(index);
			if(hasRight(index) && rightVal(index) > leftVal(index)){
				largerIndex = rightIndex(index);
			}
			if(arr[index] > arr[largerIndex]){
				break;
			}
			else{
				swap(index, largerIndex);
			}
			index = largerIndex;
		}
		
	}

	public void swap(int a, int b){
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public void pollAll(){
		int temp = size;
		for(int i=0; i<temp; i++){
			poll();
		}
		System.out.println();
	}
	
	public void print(){
		for(int i=0; i<size; i++){
			System.out.print(this.arr[i]+" ");
		}
		System.out.println("Cap: " + this.cap);
	}

	public static void main(String[] args) {
		
		MaxHeap mh = new MaxHeap(5);	
		mh.insert(new int[]{1,2,3,4,5,6,7});
		mh.print();
		mh.pollAll();
		
		mh.insert(5);
		mh.insert(new int[]{1,2,3,4,5,6,7});
		mh.print();
		mh.pollAll();
	}

}
