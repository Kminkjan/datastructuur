package datastructuur;

/**
 * The RSHeap class represents a heap data structure
 * 
 * @author Kris & Artemis
 *
 */
public class RSHeap {

	int[] heap, memory;
	private int inputIndex, lastDelete, deadspaceCount = 0, memoryIndex = 0, runCount = 1;
	boolean deleted = false;

	public RSHeap(int mSize) {
		inputIndex = 0;
		this.heap = new int[mSize];
		for (int i = 0; i < heap.length; i++) {
			insert((int) (Math.random() * mSize));
		}
	}

	public RSHeap(int[] init, int heapSize) {
		inputIndex = 0;
		this.memory = init;
		
		/* If the amount is lower then the heapSize, adjust the heapSize */
		if(memory.length < heapSize) {
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
	}

	public void createRuns() {
		while (memoryIndex < memory.length + heap.length) {
			delete();
			if (memoryIndex < memory.length) {
				insert(memory[memoryIndex]);
			}
			++memoryIndex;
		}
	}

	public void insert(int item) {
		heap[inputIndex] = item;
		if (deleted && item < lastDelete) {
			++deadspaceCount;
			if (heap.length - 1 - deadspaceCount == 0) {
				++runCount;
				inputIndex = 0;
				deadspaceCount = 0;
				deleted = false;
				int[] arrayTemp = heap;
				heap = new int[arrayTemp.length];
				for (int i = 0; i < heap.length; i++) {
					insert(arrayTemp[i]);
				}
				System.out.println();
				// System.out.println(printToDotString());
			}
		} else {
			percolateUp(inputIndex);
			++inputIndex;
		}
	}
	
	public int getRunCount() {
		return runCount;
	}

	public int delete() {
		deleted = true;
		lastDelete = heap[0];

		System.out.print(lastDelete + " ");

		heap[0] = heap[heap.length - 1 - deadspaceCount];
		--inputIndex;
		percolateDown(0);
		return lastDelete;
	}

	private void swap(int index, int parent) {
		int temp = heap[index];
		heap[index] = heap[parent];
		heap[parent] = temp;
	}

	private void percolateDown(int index) {
		int childLeft = index * 2 + 1, childRight = (index * 2) + 2;
		;
		while (childLeft < heap.length - 1 - deadspaceCount) {

			// System.out.println("index: " + memory[index]);
			// System.out.println("child: " + memory[childLeft]);

			if (childRight < heap.length - 1 - deadspaceCount) {
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

	private void percolateUp(int index) {
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

	public boolean validHeap() {
		boolean valid = true;
		for (int i = 0; i < heap.length; i++) {
			if (i * 2 + 1 < heap.length && i * 2 + 2 < heap.length) {
				valid = (heap[i] < heap[i * 2 + 1] && heap[i] < heap[i * 2 + 2]);
			}
		}
		return valid;
	}
}
