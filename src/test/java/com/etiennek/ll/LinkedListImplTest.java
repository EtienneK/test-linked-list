package com.etiennek.ll;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class LinkedListImplTest {
	private LinkedList<Integer> toTest;
	private int itemCount = 0;

	@Before
	public void init() {
		toTest = new LinkedListImpl<>();
	}

	@Test
	public void A_newly_initiated_list_should_be_empty() {
		// Arrange
		int count;

		// Act
		count = toTest.getCount();

		// Assert
		assertEquals(toTest.getFirst(), Optional.empty());
		assertEquals(toTest.getLast(), Optional.empty());
		assertEquals(count, 0);
	}

	@Test
	public void addFirst_should_add_an_item_in_the_first_and_last_position_of_an_empty_list() {
		// Arrange
		int firstItem = newItem();

		// Act
		toTest.addFirst(firstItem);

		// Assert
		assertEquals((int) toTest.getFirst().get(), firstItem);
		assertEquals((int) toTest.getLast().get(), firstItem);
		assertEquals(toTest.getCount(), 1);
	}

	@Test
	public void addFirst_should_add_an_item_in_the_first_position_of_a_non_empty_list() {
		// Arrange
		int firstItem = newItem();
		int middleItem = newItem();
		int lastItem = newItem();

		// Act
		toTest.addFirst(lastItem);
		toTest.addFirst(middleItem);
		toTest.addFirst(firstItem);

		// Assert
		assertEquals((int) toTest.getFirst().get(), firstItem);
		assertEquals((int) toTest.getLast().get(), lastItem);
		assertEquals(toTest.getCount(), 3);

		assertTrue(toTest.isBefore(firstItem, middleItem));
		assertTrue(toTest.isAfter(lastItem, middleItem));
	}

	@Test
	public void addLast_should_add_an_item_in_the_first_and_last_position_of_an_empty_list() {
		// Arrange
		int lastItem = newItem();

		// Act
		toTest.addLast(lastItem);

		// Assert
		assertEquals((int) toTest.getFirst().get(), lastItem);
		assertEquals((int) toTest.getLast().get(), lastItem);
		assertEquals(toTest.getCount(), 1);
	}

	@Test
	public void addLast_should_add_an_item_in_the_last_position_of_a_non_empty_list() {
		// Arrange
		int firstItem = newItem();
		int middleItem = newItem();
		int lastItem = newItem();

		// Act
		toTest.addLast(firstItem);
		toTest.addLast(middleItem);
		toTest.addLast(lastItem);

		// Assert
		assertEquals((int) toTest.getFirst().get(), firstItem);
		assertEquals((int) toTest.getLast().get(), lastItem);
		assertEquals(toTest.getCount(), 3);

		assertTrue(toTest.isBefore(firstItem, middleItem));
		assertTrue(toTest.isAfter(lastItem, middleItem));
	}

	@Test(expected = IllegalStateException.class)
	public void addAfter_should_throw_an_exception_if_after_could_not_be_found() {
		// Arrange
		// Act
		toTest.addAfter(newItem(), newItem());
		// Assert
	}

	@Test
	public void addAfter_should_add_an_item_at_end_last_position_when_after_is_the_last_item() {
		// Arrange
		int firstItem = newItem();
		int lastItem = newItem();

		// Act
		toTest.addFirst(firstItem);
		toTest.addAfter(lastItem, firstItem);

		// Assert
		assertEquals((int) toTest.getFirst().get(), firstItem);
		assertEquals((int) toTest.getLast().get(), lastItem);
		assertEquals(toTest.getCount(), 2);

		assertTrue(toTest.isBefore(firstItem, lastItem));
		assertTrue(toTest.isAfter(lastItem, firstItem));
	}

	@Test
	public void addAfter_should_add_an_item_somewhere_in_the_middle_when_after_is_not_the_last_item() {
		// Arrange
		int firstItem = newItem();
		int afterFirstItem = newItem();
		int beforeLastItem = newItem();
		int lastItem = newItem();

		// Act
		toTest.addFirst(firstItem);
		toTest.addAfter(lastItem, firstItem);
		toTest.addAfter(afterFirstItem, firstItem);
		toTest.addAfter(beforeLastItem, afterFirstItem);
		toTest.addAfter(newItem(), afterFirstItem);
		toTest.addAfter(newItem(), afterFirstItem);

		// Assert
		assertEquals((int) toTest.getFirst().get(), firstItem);
		assertEquals((int) toTest.getLast().get(), lastItem);
		assertEquals(toTest.getCount(), 6);

		assertTrue(toTest.isBefore(firstItem, afterFirstItem));
		assertTrue(toTest.isAfter(afterFirstItem, firstItem));
		assertTrue(toTest.isBefore(beforeLastItem, lastItem));
		assertTrue(toTest.isAfter(lastItem, beforeLastItem));
	}

	@Test(expected = IllegalStateException.class)
	public void addBefore_should_throw_an_exception_if_before_could_not_be_found() {
		// Arrange
		// Act
		toTest.addBefore(newItem(), newItem());
		// Assert
	}

	@Test
	public void addBefore_should_add_an_item_at_the_first_position_when_before_is_the_first_item() {
		// Arrange
		int firstItem = newItem();
		int lastItem = newItem();

		// Act
		toTest.addFirst(lastItem);
		toTest.addBefore(firstItem, lastItem);

		// Assert
		assertEquals((int) toTest.getFirst().get(), firstItem);
		assertEquals((int) toTest.getLast().get(), lastItem);
		assertEquals(toTest.getCount(), 2);
		
		assertTrue(toTest.isBefore(firstItem, lastItem));
		assertTrue(toTest.isAfter(lastItem, firstItem));
	}

	public void addBefore_should_add_an_item_somewhere_in_the_middle_when_before_is_not_the_first_item() {
		// Arrange
		int firstItem = newItem();
		int afterFirstItem = newItem();
		int beforeLastItem = newItem();
		int lastItem = newItem();

		// Act
		toTest.addFirst(lastItem);
		toTest.addBefore(firstItem, lastItem);
		toTest.addBefore(beforeLastItem, lastItem);
		toTest.addBefore(afterFirstItem, beforeLastItem);
		toTest.addBefore(newItem(), beforeLastItem);
		toTest.addBefore(newItem(), beforeLastItem);

		// Assert
		assertEquals((int) toTest.getFirst().get(), firstItem);
		assertEquals((int) toTest.getLast().get(), lastItem);
		assertEquals(toTest.getCount(), 6);
		
		assertTrue(toTest.isBefore(firstItem, afterFirstItem));
		assertTrue(toTest.isAfter(afterFirstItem, firstItem));
		assertTrue(toTest.isBefore(beforeLastItem, lastItem));
		assertTrue(toTest.isAfter(lastItem, beforeLastItem));
	}

	private int newItem() {
		itemCount = itemCount + 63;
		return itemCount;
	}
}
