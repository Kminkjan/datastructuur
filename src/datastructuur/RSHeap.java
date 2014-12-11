package datastructuur;

/**
 * The RSHeap class represents a heap data structure
 * 
 * @author Kris & Artemis
 *
 */
public class RSHeap {

	private RSHeap leftNode, rightNode, parent;
	private int value;
	private boolean isDead = false;

	/**
	 * Creates the heap <b>NODE</b>.
	 * <br>
	 * Can only be called from in this class.
	 * 
	 * @param value The value this Node should have.
	 * @param parent  This Node's parent Node.
	 */
	private RSHeap(int value, RSHeap parent) {
		this.value = value;
		this.parent = parent;
	}
	
	/**
	 * Creates the heap <b>RootNode</b> if called from another class, which is the base of the heap.
	 * @param value The value this RootNode should have. Enter -1 for default, this will be overridden.
	 */
	public RSHeap(int value) {
		this.value = value;
		this.parent = null;
	}
	
	public boolean isDead() {
		return this.isDead || leftNode.isDead() && rightNode.isDead();
	}

	/**
	 * Insert an item into the heap by first setting it at the correct place
	 * (the <b>MOST</> down - left place) and then bubbling it up so the heap
	 * integrity is guaranteed.
	 * 
	 * @param item The item that needs to be inserted
	 */
	public void insert(int item) {
		if (this.value == -1) {
			this.value = item;
		} else if (leftNode == null) {
			leftNode = new RSHeap(item, this);
			leftNode.bubbleUp();
		} else if (rightNode == null) {
			rightNode = new RSHeap(item, this);
			rightNode.bubbleUp();
		} else if (rightNode.size() < leftNode.size()) {
			rightNode.insert(item);
		} else {
			leftNode.insert(item);
		}
	}

	/**
	 * Swaps the value of two nodes
	 * 
	 * @param newValue
	 *            The new value this Node will get
	 * @return The old value of this Nod
	 */
	public int swap(int newValue) {
		int temp = this.value;
		this.value = newValue;
		return temp;
	}

	/**
	 * Checks if the value of this node is lower then this node's parent. If
	 * this is not true, the two values swap and the parent(Which now has this
	 * node's old value) bubbles up.
	 */
	public void bubbleUp() {
		if (parent != null && parent.value > this.value) {
			this.value = parent.swap(this.value);
			parent.bubbleUp();
		}
	}

	/**
	 * Builds the heap by going down to the leaves, then bubbling them up
	 */
	public void buildHeap() {
		if (isLeaf()) {
			bubbleUp();
		} else {
			if (leftNode != null) {
				leftNode.buildHeap();
			}
			if (rightNode != null) {
				rightNode.buildHeap();
			}
		}
	}

	/**
	 * Gets the value of the root TODO is this good?
	 * 
	 * @return The int value of the root
	 */
	public int getRoot() {
		return value;
	}

	/**
	 * Check if this node is a Leaf
	 * 
	 * @return True if this is a leaf
	 */
	public boolean isLeaf() {
		if (leftNode == null && rightNode == null) {
			return true;
		}
		return false;
	}

	/** 
	 * Returns the size of the heap
	 * @return An int with the Size
	 */
	public int size() {
		if (rightNode == null && leftNode == null) {
			return 1;
		} else if (rightNode == null) {
			return leftNode.size() + 1;
		} else if (leftNode == null) {
			return rightNode.size() + 1;
		}
		return leftNode.size() + rightNode.size() + 1;
	}
}
