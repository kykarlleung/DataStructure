import java.util.Arrays;

public class MinHeap {
	
	int cap = 10;
	int size = 0;
	int[] arr;
	
	MinHeap() {
		arr = new int[cap];
	}
	
	MinHeap(int val) {
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
		while( parentIndex(index) != index && parentVal(index) > arr[index]){
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
			int smallerIndex = leftIndex(index);
			if(hasRight(index) && rightVal(index) < leftVal(index)){
				smallerIndex = rightIndex(index);
			}
			if(arr[index] < arr[smallerIndex]){
				break;
			}
			else{
				swap(index, smallerIndex);
			}
			index = smallerIndex;
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
		
		MinHeap mh = new MinHeap(5);
//		mh.print();
//		mh.insert(1);
//		mh.print();
//		mh.insert(2);
//		mh.print();
//		mh.insert(3);
//		mh.print();
//		mh.insert(4);
//		mh.print();
//		mh.insert(5);
//		mh.print();
//		mh.insert(6);
//		mh.print();
//		mh.insert(7);
		
		mh.insert(new int[]{1,2,3,4,5,6,7});
		mh.print();
		mh.pollAll();
		mh.insert(new int[]{1,2,3,4,5,6,7});
		mh.print();
		mh.pollAll();
	}

}
