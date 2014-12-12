package datastructuur;

public class Main {

	public static void main(String[] args) {
		System.out.println("Heap started");
		
		/* NOTE: ALL THE TESTS ARE LOCATED IN THE TEST PACKAGE */
		
		int[] test = {1,5,3,7,2,3,8,8,9,2};
		RSHeap heap = new RSHeap(test, 5);
		heap.createRuns();
	}

}
