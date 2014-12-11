package datastructuur;

/**
 * The RSHeap class represents a heap data structure
 * 
 * @author Kris & Artemis
 *
 */
public class RSHeap {

	int[] heap, memory;
	private int inputIndex, lastDelete, deadspaceCount = 0, memoryIndex = 0;
	boolean deleted = false;

	public RSHeap(int mSize) {
		assert mSize > 0 : "size is to low";
		inputIndex = 0;
		this.heap = new int[mSize];
		for (int i = 0; i < heap.length; i++) {
			insert((int) (Math.random() * mSize));
		}
		// System.out.println(printToDotString());
	}

	public RSHeap(int[] init, int heapSize) {
		assert init !=null :"[] init is null";
		assert init.length> 0 :"[] init is empty";
		assert heapSize > 0 :"heapsize is to low";
		
		inputIndex = 0;
		this.memory = init;
		this.heap = new int[heapSize];
		for (int i = 0; i < heap.length; i++) {
			insert(memory[i]);
			++memoryIndex;
		}
		//System.out.println(printToDotString());
	}
	
	public void createRuns() {
		while(memoryIndex < memory.length) {
		delete();
		insert(memory[memoryIndex]);
		++memoryIndex;
		}
	}

	public void insert(int item){
		heap[inputIndex] = item;
		if (deleted && item < lastDelete) {
			++deadspaceCount;
			if (heap.length - 1 - deadspaceCount == 0 ) {
				inputIndex = 0;
				deadspaceCount = 0;
				deleted = false;
				int[] arrayTemp = heap;
				heap = new int[arrayTemp.length];
				for (int i = 0; i < heap.length; i++) {
					insert(arrayTemp[i]);
				}
				System.out.println();
				//System.out.println(printToDotString());
			}
		} else {
			percolateUp(inputIndex);
			++inputIndex;
		}
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
		assert index >= 0 : "index is to low";
		int temp = heap[index];
		heap[index] = heap[parent];
		heap[parent] = temp;
	}

	private void percolateDown(int index) {
		assert index >= 0 : "index is to low";
		int childLeft = index * 2 + 1, childRight= (index * 2) + 2;;
		while (childLeft < heap.length-1 - deadspaceCount) {
			
//			System.out.println("index: " + memory[index]);
//			System.out.println("child: " + memory[childLeft]);

			if (childRight < heap.length-1 - deadspaceCount) {
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
		assert index >= 0 : "index is to low";
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
		return true;
	}

	// childs x n * 2 + 1
	// childs y n * 2

	// /**
	// * Creates the heap <b>NODE</b>. <br>
	// * Can only be called from in this class.
	// *
	// * @param value
	// * The value this Node should have.
	// * @param parent
	// * This Node's parent Node.
	// */
	// private RSHeap(int value, RSHeap parent) {
	// this.value = value;
	// this.parent = parent;
	// }

	// // /**
	// // * Creates the heap <b>RootNode</b> if called from another class, which
	// is the base of the heap.
	// // * @param value The value this RootNode should have. Enter -1 for
	// default, this will be overridden.
	// // */
	// // public RSHeap(int value) {
	// // this.value = value;
	// // this.parent = null;
	// // }
	//
	// public boolean isDead() {
	// return this.isDead || leftNode.isDead() && rightNode.isDead();
	// }
	//
	// /**
	// * Insert an item into the heap by first setting it at the correct place
	// * (the <b>MOST</> down - left place) and then bubbling it up so the heap
	// * integrity is guaranteed.
	// *
	// * @param item The item that needs to be inserted
	// */
	// public void insert(int item) {
	// if (this.value == -1) {
	// this.value = item;
	// } else if (leftNode == null) {
	// leftNode = new RSHeap(item, this);
	// leftNode.bubbleUp();
	// } else if (rightNode == null) {
	// rightNode = new RSHeap(item, this);
	// rightNode.bubbleUp();
	// } else if (rightNode.size() < leftNode.size()) {
	// rightNode.insert(item);
	// } else {
	// leftNode.insert(item);
	// }
	// }
	//
	// /**
	// * Swaps the value of two nodes
	// *
	// * @param newValue
	// * The new value this Node will get
	// * @return The old value of this Nod
	// */
	// public int swap(int newValue) {
	// int temp = this.value;
	// this.value = newValue;
	// return temp;
	// }
	//
	// /**
	// * Checks if the value of this node is lower then this node's parent. If
	// * this is not true, the two values swap and the parent(Which now has this
	// * node's old value) bubbles up.
	// */
	// public void bubbleUp() {
	// if (parent != null && parent.value > this.value) {
	// this.value = parent.swap(this.value);
	// parent.bubbleUp();
	// }
	// }
	//
	// /**
	// * Builds the heap by going down to the leaves, then bubbling them up
	// */
	// public void buildHeap() {
	// if (isLeaf()) {
	// bubbleUp();
	// } else {
	// if (leftNode != null) {
	// leftNode.buildHeap();
	// }
	// if (rightNode != null) {
	// rightNode.buildHeap();
	// }
	// }
	// }
	//
	// /**
	// * Gets the value of the root TODO is this good?
	// *
	// * @return The int value of the root
	// */
	// public int getRoot() {
	// return value;
	// }
	//
	// /**
	// * Check if this node is a Leaf
	// *
	// * @return True if this is a leaf
	// */
	// public boolean isLeaf() {
	// if (leftNode == null && rightNode == null) {
	// return true;
	// }
	// return false;
	// }
	//
	// /**
	// * Returns the size of the heap
	// * @return An int with the Size
	// */
	// public int size() {
	// if (rightNode == null && leftNode == null) {
	// return 1;
	// } else if (rightNode == null) {
	// return leftNode.size() + 1;
	// } else if (leftNode == null) {
	// return rightNode.size() + 1;
	// }
	// return leftNode.size() + rightNode.size() + 1;
	// }
}
