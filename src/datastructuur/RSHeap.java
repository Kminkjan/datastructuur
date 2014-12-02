package datastructuur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The RSHeap class represents a datastructure
 * @author Kris
 *
 */
public class RSHeap {
	
	private File file;
	private List<Integer> heap;
	private int heapSize, deadSize;

	public RSHeap(File file, int size) {
		this.heap = new int[size];
		for (int i = 0; i < heap.length; i++) {
			// Read int from file
		}
		buildHeap(heap);
	}
	
	private void buildHeap(int[] heap) {
		int min = heap[0];
		for (int i = 0; i < heap.length; i++) {
			
		}
	}
	
	private void createRun() {
		while(heapSize != 0) {
			
		}
	}
}

