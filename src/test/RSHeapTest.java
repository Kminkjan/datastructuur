package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastructuur.RSHeap;

public class RSHeapTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRSHeap() {
		RSHeap heap = new RSHeap(-1);
		assertNotNull(heap);
		assertEquals(-1, heap.getRoot());
		assertEquals(1, heap.size());
	}

	@Test
	public void testInsert() {
		/* Also tests the getRoot() method */
		RSHeap heap = new RSHeap(-1);
		heap.insert(10);
		assertEquals(10, heap.getRoot());
		assertEquals(1, heap.size());
		heap.insert(8);
		assertEquals(8, heap.getRoot());
		assertEquals(2, heap.size());
	}

	@Test
	public void testBuildHeap() {
		//TODO needs to test the heap integrity
	}
	
	@Test
	public void testIsDead() {
		RSHeap heap = new RSHeap(-1);
		assertTrue(heap.isDead());
	}

	@Test
	public void testSize() {
		RSHeap heap = new RSHeap(-1);
		heap.insert(10);
		assertEquals(1, heap.size());
		heap.insert(8);
		assertEquals(2, heap.size());
		heap.insert(15);
		assertEquals(3, heap.size());
		
		/* Add ten thousand for dramatic effect */
		for (int i = 0; i < 10000; i++) {
			heap.insert(i);
		}
		assertEquals(10003, heap.size());
	}
}
