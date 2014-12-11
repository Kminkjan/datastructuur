package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import datastructuur.RSHeap;

public class RSHeapTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRSHeap() {
		List<Integer> runsList = new ArrayList<Integer>();
		int[] test = new int[10];
		for (int i = 0; i < 10; i++) {
			test[i] = (int) (Math.random()*10);
		}
		RSHeap heap = new RSHeap(test);
		assertNotNull(heap);
		assertTrue(heap.validHeap());
		
		for (int i = 0; i < 100; i++) {
			runsList.add(heap.delete());
			heap.insert((int)(Math.random()*500)); 
		}
		
		for (int i = 100; i > 0; i--) {
			runsList.add(heap.delete());
			heap.insert(i); 
		}
		
//		for (int i = 0; i < runsList.size(); i++) {
//			System.out.println(runsList.get(i));
//		} 
		
		assertTrue(heap.validHeap());
		
		
		int[] crap = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
		
	}

//	@Test
//	public void testInsert() {
//		/* Also tests the getRoot() method */
//		RSHeap heap = new RSHeap(-1);
//		heap.insert(10);
//		assertEquals(10, heap.getRoot());
//		assertEquals(1, heap.size());
//		heap.insert(8);
//		assertEquals(8, heap.getRoot());
//		assertEquals(2, heap.size());
//	}
//
//	@Test
//	public void testBuildHeap() {
//		//TODO needs to test the heap integrity
//	}
//	
//	@Test
//	public void testIsDead() {
//		RSHeap heap = new RSHeap(-1);
//		assertTrue(heap.isDead());
//	}
//
//	@Test
//	public void testSize() {
//		RSHeap heap = new RSHeap(-1);
//		heap.insert(10);
//		assertEquals(1, heap.size());
//		heap.insert(8);
//		assertEquals(2, heap.size());
//		heap.insert(15);
//		assertEquals(3, heap.size());
//		
//		/* Add ten thousand for dramatic effect */
//		for (int i = 0; i < 10000; i++) {
//			heap.insert(i);
//		}
//		assertEquals(10003, heap.size());
//	}
}
