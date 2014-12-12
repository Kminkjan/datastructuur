package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import datastructuur.RSHeap;

public class RSHeapTest {

	@Test
	public void testRandomAmount() {
		int randomSize = (int) (Math.random() * 500);
		int[] randomArray = new int[randomSize];
		for (int i = 0; i < randomArray.length; i++) {
			randomArray[i] = (int) (Math.random() * randomArray.length);
		}
		RSHeap heap = new RSHeap(randomArray, 10);
		System.out.println("\nRandom test:");
		heap.createRuns();
		System.out.println("\nHeapsize = " + 10
				+ "\nAmount of estimated (non-extended) runs: " + randomSize
				+ "/10 = " + randomSize / 10 + ". result: "
				+ heap.getRunCount());
	}

	@Test
	public void testGoodCase() {
		int[] test2 = { 1, 2, 2, 3, 3, 5, 7, 8, 8, 9 };
		RSHeap heap = new RSHeap(test2, 5);
		assertNotNull(heap);
		assertTrue(heap.validHeap());
		System.out.println("\ntest2: good scenario test");
		heap.createRuns();
		System.out.println("\nHeapsize = " + 5
				+ "\nAmount of estimated (non-extended) runs: 10/5 = " + 10 / 5
				+ ". result: " + heap.getRunCount());
	}

	@Test
	public void testSameNumbers() {
		int[] test4 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

		RSHeap heap = new RSHeap(test4, 5);
		assertNotNull(heap);
		// assertTrue(heap.validHeap());
		System.out.println("\ntest3: all numbers the same");
		heap.createRuns();
		System.out.println("\nHeapsize = " + 5
				+ "\nAmount of estimated (non-extended) runs: 10/5 = " + 10 / 5
				+ ". result: " + heap.getRunCount());
	}

	@Test
	public void testBadCase() {
		int[] test3 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

		RSHeap heap = new RSHeap(test3, 5);
		assertNotNull(heap);
		assertTrue(heap.validHeap());
		System.out.println("\ntest3: small bad scenario test");
		heap.createRuns();
		System.out.println("\nHeapsize = " + 5
				+ "\nAmount of estimated (non-extended) runs: 10/5 = " + 10 / 5
				+ ". result: " + heap.getRunCount());
	}

	@Test
	public void testBigBadCase() {
		int[] bad = new int[524];
		for (int i = 0; i < bad.length; i++) {
			bad[bad.length - 1 - i] = i;
		}
		RSHeap heap = new RSHeap(bad, 20);
		System.out.println("\ntest5: big bad case scenario test:");
		heap.createRuns();
		System.out.println("\nHeapsize = " + 20
				+ "\nAmount of estimated (non-extended) runs: 524/20 = " + 542
				/ 20 + ". result: " + heap.getRunCount());
	}

	@Test
	public void testSmallHeapSize() {
		int[] test = { 1, 5, 3, 7, 2, 3, 8, 8, 9, 2 };

		RSHeap heap = new RSHeap(test, 20);
		assertNotNull(heap);
		assertTrue(heap.validHeap());
		System.out.println("\ntest: small heap");
		heap.createRuns();
		System.out.println("\nHeapsize = " + 20
				+ "\nAmount of estimated (non-extended) runs: 10/20 = " + 10
				/ 20 + ". result: " + heap.getRunCount());
	}

	@Test
	public void testRSHeap() {
		int[] test = { 1, 5, 3, 7, 2, 3, 8, 8, 9, 2 };

		RSHeap heap = new RSHeap(test, 5);
		assertNotNull(heap);
		assertTrue(heap.validHeap());
		System.out.println("\ntest");
		heap.createRuns();
		System.out.println("\nHeapsize = " + 5
				+ "\nAmount of estimated (non-extended) runs: 10/5 = " + 10 / 5
				+ ". result: " + heap.getRunCount());
	}
}
