package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import datastructuur.RSHeap;

public class RSHeapTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRSHeap() {
		int[] test = {1,5,3,7,2,3,8,8,9,2};
		RSHeap heap = new RSHeap(test, 3);
		assertNotNull(heap);
		assertTrue(heap.validHeap());
		System.out.println("\ntest");
		heap.createRuns();
		System.out.println("Result: heapsize = " + 5
				+ "\n Amount of estimated runs: 10/5 = " + 10 / 5
				+ ". result: " + heap.getRunCount());

		for (int i = 0; i < 10; i++) {
			test[i] = (int) (Math.random() * 10);
		}

		heap = new RSHeap(test, 15);
		heap.createRuns();

		int[] bigNTest = new int[100];

		for (int i = 0; i < 100; i++) {
			bigNTest[i] = (int) (Math.random() * 500);
		}

		heap = new RSHeap(bigNTest, 10);
		assertTrue(heap.validHeap());
		System.out.println("\ntest2");
		heap.createRuns();

		heap = new RSHeap(bigNTest, 3);
		assertTrue(heap.validHeap());
		System.out.println("\ntest3");
		heap.createRuns();

		int[] worstCase = new int[100];

		for (int i = 0; i < 100; i++) {
			worstCase[99 - i] = i;
		}

		heap = new RSHeap(worstCase, 10);
		assertTrue(heap.validHeap());
		System.out.println("\ntest4");
		heap.createRuns();

	}
}
