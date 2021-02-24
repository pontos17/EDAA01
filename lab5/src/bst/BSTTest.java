package bst;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BSTTest<E> {
	private BinarySearchTree<Integer> testBst;
	private BinarySearchTree<E> tb;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void setUp() throws Exception {
		testBst = new BinarySearchTree<Integer>();
		tb = new BinarySearchTree<E>((e1,e2) -> ((Comparable <E>) e2).compareTo(e1));
	}

	@AfterEach
	void tearDown() throws Exception {
		testBst = null;
		tb = null;
	}

	@Test
	void testAddSame() {
		testBst.add(10);
		testBst.add(15);
		testBst.add(5);
		assertFalse(testBst.add(10));
		assertEquals(3, testBst.size(), "Size should be three");
	}
	@SuppressWarnings("unchecked")
	@Test
	void testHeight() {
		tb.add((E) "b");
		tb.add((E) "a");
		tb.add((E) "c");
		tb.add((E) "d");
		assertEquals(2, tb.height(), "Height should be 2");
	}
	@Test
	void testSize() {
		testBst.add(10);
		testBst.add(15);
		testBst.add(5);
		assertEquals(3,testBst.size(), "Size should be three");
	}
	@Test
	void testClear () {
		testBst.add(10);
		testBst.add(15);
		testBst.clear();
		assertEquals(0, testBst.height(), "Tree should be empty");
		 assertEquals(0, testBst.size(), "Tree should be empty");
	}
	@Test
	void testHeightEmpty() {
		assertEquals(0,testBst.height(), "Tree should be empty");
	}
	@Test
	void testSizeEmpty() {
		assertEquals(0,testBst.size(), "Tree should be empty");
	}


}
