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
	private int[] heap;
	private int heapSize, deadSize;

	public RSHeap(File file, int size) {
		this.heap = new int[size];
		for (int i = 0; i < heap.length; i++) {
			// Read int from file
		}
	}
	

}

