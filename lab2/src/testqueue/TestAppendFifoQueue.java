package testqueue;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue<E> {
	private FifoQueue<Integer> testQueue1;
	private FifoQueue<Integer> testQueue2;

	@BeforeEach
	void setUp() throws Exception {
		testQueue1 = new FifoQueue<Integer>();
		testQueue2 = new FifoQueue<Integer>();
	}

	@AfterEach
	void tearDown() throws Exception {
		testQueue1 = null;
		testQueue2 = null;
	}
	/**
	 * Testar om en tom kö kan konkantenera en annan tom kö
	 */
	@Test
	public void twoEmpty() {
		assertTrue( testQueue1.isEmpty(), "Queue should be empty");
		assertTrue(testQueue2.isEmpty(),"Queue should be empty");
		testQueue1.append(testQueue2);
		assertTrue( testQueue1.isEmpty(), "Queue should be empty");
		
	}

	/**
	 * Testar om en tom kö kan konkantenera en icke tom kö
	 */
	@Test
	public void emptyQueueAppendsNoneEmptyQueue() {
		testQueue2.offer(1);
		testQueue2.offer(4);
		testQueue2.offer(2);
		testQueue1.append(testQueue2);
		assertEquals(3, testQueue1.size(), "Size should be 3");
		assertEquals(1, testQueue1.poll(),"Första elementet ska vara 1");
		assertEquals(4, testQueue1.poll(), "Andra elementet ska vara 4");
		assertEquals(2, testQueue1.poll(), "Tredje elementet ska vara 2");
		assertTrue(testQueue2.isEmpty(),"Den konkatenerade kön ska vara tom");
	}

	/**
	 * Testar om en icke tom kö kan konkatenera en tom kö
	 */
	@Test
	public void noneEmptyQueueAppendsEmptyQueue() {
		testQueue1.offer(1);
		testQueue1.offer(3);
		testQueue1.append(testQueue2);
		assertEquals(2, testQueue1.size(),"Size ska vara 2");
		assertEquals(1, testQueue1.poll(), "Första elementet ska vara 1");
		assertEquals(3, testQueue1.poll(),"Andra elementet ska vara 3");
		assertTrue(testQueue2.isEmpty(),"Den konkatenerade kön ska vara tom");
	}

	/**
	 * Testar om två icke tomma köer kan konkateneras
	 */
	@Test
	public void appendTwoQueue() {
		testQueue1.offer(1);
		testQueue1.offer(2);
		testQueue2.offer(3);
		testQueue2.offer(4);
		testQueue1.append(testQueue2);
		assertEquals(4, testQueue1.size(), "Size ska vara 4");
		assertEquals(1, testQueue1.poll(),"Första elementet ska vara 1");
		assertEquals(2, testQueue1.poll(), "Andra elementet ska vara 2");
		assertEquals(3, testQueue1.poll(),"Tredje elementet ska vara 3");
		assertEquals(4, testQueue1.poll(), "Fjärde elementet ska vara 4");
		assertTrue(testQueue2.isEmpty(), "Den konkatenerade kön ska vara tom");
	}

	/**
	 * Testar om en kö kan konkatenera sig själv
	 */
	@Test
	public void appendQueueWithItself() {
		{
			try {
				testQueue1.add(1);
				testQueue1.add(2);
				testQueue1.append(testQueue1);
				fail(" ");
			} catch (IllegalArgumentException e) {
			
			}
		}
	}

}
