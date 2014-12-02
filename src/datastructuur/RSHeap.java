package datastructuur;

/**
 * The RSHeap class represents a datastructure
 * 
 * @author Kris
 *
 */
public class RSHeap {

	private RSHeap leftNode, rightNode, parent;
	private int value;

	public RSHeap(int value, RSHeap parent) {
		this.value = value;
		this.parent = parent;
	}

	public void insert(int item) {
		if (leftNode == null) {
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

	public int swap(int newValue) {
		int temp = this.value;
		this.value = newValue;
		return temp;
	}

	public void bubbleUp() {
		if (parent != null && parent.value > this.value) {
			this.value = parent.swap(this.value);
			parent.bubbleUp();
		}
	}

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
	
	public int getRoot() {
		return -1;
	}

	public boolean isLeaf() {
		if (leftNode == null && rightNode == null) {
			return true;
		}
		return false;
	}


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
