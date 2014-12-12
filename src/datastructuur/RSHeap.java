package datastructuur;

/**
 * The RSHeap class represents a heap data structure
 * 
 * @author Kris & Artemis
 *
 */
public class RSHeap {

	private int[] heap, memory;
	private int inputIndex, lastDelete, deadspaceCount = 0, memoryIndex = 0,
			lastRunIndex = 0, runCount = 1;
	private boolean deleted = false;

	/**
	 * Creates the RSHeap
	 * 
	 * @param inputArray
	 *            The int[] with all the input numbers
	 * @param heapSize
	 *            The size of the heap/memory
	 */
	public RSHeap(int[] inputArray, int heapSize) {
		assert inputArray != null : "inputArray is null";
		assert inputArray.length > 0 : "inputArray length is 0";
		assert heapSize > 0 : "heapSize too small";

		this.inputIndex = 0;
		this.memory = inputArray;

		/* If the amount is lower then the heapSize, adjust the heapSize */
		if (memory.length < heapSize) {
			heapSize = memory.length;
		}
		this.heap = new int[heapSize];

		/* Fill the heap */
		for (int i = 0; i < heapSize; i++) {
			if (memoryIndex < memory.length) {
				insert(memory[i]);
				++memoryIndex;
			}
		}
//		System.out.println(printToDotString());

		assert heap != null : "heap is null";
	}

	/**
	 * Puts everything that is in the deadspace and makes a final run of it
	 */
	private void emptyDeadSpace() {
		int[] tempArray = heap;
		this.inputIndex = 0;
		this.heap = new int[deadspaceCount];
		deleted = false;

		/* Put all the deadspace items in the new heap */
		for (int i = 0; i < heap.length; i++) {
			insert(tempArray[tempArray.length - (i + 1)]);
		}
		deadspaceCount = 0;
		++runCount;
		inputIndex = heap.length;
		if (heap.length == 1) {
			inputIndex = 1;
		}
		System.out.println();

		/* Make a run */
		for (int i = 0; i < heap.length; i++) {
			delete();
		}
	}

	/**
	 * Starts creating runs with the input array
	 */
	public void createRuns() {
		while (memoryIndex < memory.length + heap.length) {
			if (memoryIndex < memory.length) {
				/* Still numbers to insert */
				delete();
				insert(memory[memoryIndex]);
			} else if (lastRunIndex + deadspaceCount < heap.length - 1) {
				lastRunIndex++;
				delete();
			} else if (inputIndex > 0) {
				/* Numbers left in the heap */
				delete();
			} else if (deadspaceCount > 0) {
				/* Numbers left in the deadspace */
				emptyDeadSpace();
			}
			++memoryIndex;
		}
	}

	/**
	 * Inserts an item in the heap
	 * 
	 * @param item
	 *            The item int that should be added to the heap
	 */
	private void insert(int item) {
		/* insert the item */
		heap[inputIndex] = item;
		if (deleted && item < lastDelete) {
			/* It can't be added: put it in the deadspace */
			++deadspaceCount;
			if (lastRunIndex > 0) {
				--lastRunIndex;
			}
			if (heap.length - deadspaceCount == 0) {
				/* Deadspace as big as the heap: do heapify and reset the counters */
				++runCount;
				inputIndex = 0;
				deadspaceCount = 0;
				lastRunIndex = 0;
				deleted = false;
				int[] arrayTemp = heap;
				heap = new int[arrayTemp.length];
				for (int i = 0; i < heap.length; i++) {
					insert(arrayTemp[i]);
				}
				System.out.println();
			}
		} else {
			/* valid insert */
			percolateUp(inputIndex);
			++inputIndex;
		}
	}

	/**
	 * Returns the amount of runs that happened
	 * 
	 * @return An init with the amount of runs
	 */
	public int getRunCount() {
		return runCount;
	}

	/**
	 * Deletes the root
	 * 
	 * @return The root item
	 */
	private int delete() {
		deleted = true;
		lastDelete = heap[0];

		System.out.print(lastDelete + " ");

		if (inputIndex > 0) {
			--inputIndex;
		}

		/* Put the last item in the live heap in the root and percolate down */
		heap[0] = heap[inputIndex];
		percolateDown(0);
		return lastDelete;
	}

	/**
	 * Swaps the two items at the given indexes
	 * 
	 * @param index
	 * @param parent
	 */
	private void swap(int index, int parent) {
		assert index >= 0 : "index too low";
		assert parent >= 0 : "parent too low";

		int temp = heap[index];
		heap[index] = heap[parent];
		heap[parent] = temp;
	}

	/**
	 * Percolates the given index down
	 * 
	 * @param index
	 *            The index that should be percolated
	 */
	private void percolateDown(int index) {
		assert index >= 0 : "index too low";

		int childLeft = index * 2 + 1, childRight = (index * 2) + 2;
		while (childLeft < inputIndex) {

			if (childRight < inputIndex) {
				if (heap[childRight] < heap[childLeft]
						&& heap[index] > heap[childRight]) {
					swap(index, childRight);
					index = childRight;
				} else if (heap[index] > heap[childLeft]) {
					swap(index, childLeft);
					index = childLeft;
				} else {
					break;
				}
			} else if (heap[index] > heap[childLeft]) {
				swap(index, childLeft);
				index = childLeft;
			} else {
				break;
			}
			childLeft = index * 2 + 1;
			childRight = (index * 2) + 2;
		}
	}

	/**
	 * Percolates the given item up
	 * 
	 * @param index
	 *            Te index thad should be percolated
	 */
	private void percolateUp(int index) {
		assert index >= 0 : "index too low";

		while (index != 0) {
			int parent = ((index - 1) / 2);
			if (heap[index] < heap[parent]) {
				swap(index, parent);
				index = parent;
			} else {
				break;
			}
		}
	}

	/**
	 * This is a test method <br>
	 * <br>
	 * <b> USE STRICTLY FOR TEST METHODS </b>
	 * 
	 * @return A string that can be used for displaying the heap
	 */
	public String printToDotString() {
		String result = "digraph heap{\n";

		// Generate nodes
		for (int i = 0; i < heap.length; i++) {
			result += "n" + i + "[label=\"" + heap[i] + "\"]\n";
		}

		// Generate relationships between nodes.
		for (int i = 0; i < heap.length / 2; i++) {
			result += "n" + i + " -> n" + (i * 2 + 1) + ";\n";
			result += "n" + i + " -> n" + (i * 2 + 2) + ";\n";
		}
		return result += "}";
	}

	/**
	 * Used to test if a heap is valid
	 * 
	 * @return True if the heap is valid
	 */
	public boolean validHeap() {
		boolean valid = true;
		for (int i = 0; i < heap.length; i++) {
			if (i * 2 + 1 < heap.length && i * 2 + 2 < heap.length) {
				valid = (heap[i] <= heap[i * 2 + 1] && heap[i] <= heap[i * 2 + 2]);
			}
		}
		return valid;
	}
}
