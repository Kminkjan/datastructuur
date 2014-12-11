package datastructuur;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

	private RSHeap heap;
	private List<Integer> currentRun;
	private int[] runs;

	public void runAlgorithm(int size) {
		currentRun = new ArrayList<Integer>();
		runs = new int[size];
		this.heap = new RSHeap(-1);
		for (int i = 0; i < size; i++) {
			this.heap.insert((int) (Math.random() * 800));
		}

	}

	public void createRun() {
		currentRun.clear();
		while(!heap.isDead()) {
			currentRun.add(heap.getRoot());
		}
		for (int i = 0; i < currentRun.size(); i++) {
			
		}
	}
}
