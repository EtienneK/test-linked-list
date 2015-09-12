package com.etiennek.ll;

import java.util.stream.IntStream;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LinkedListConcurrentTest {
	private LinkedList<Integer> toTest;

	@Before
	public void init() {
		toTest = new LinkedListImpl<>();
	}

	@Test
	public void Test_concurrency() throws Exception {
		// Arrange

		// Act
		IntStream.range(0, 999).forEach(i -> new Thread(() -> {
			toTest.addFirst(i);
			toTest.remove(i);
		}).start());

		// Assert
		Thread.sleep(1000);
		assertEquals(0, toTest.getCount());
	}

}
