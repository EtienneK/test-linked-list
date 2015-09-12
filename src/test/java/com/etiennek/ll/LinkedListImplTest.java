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
		assertEquals(Optional.empty(), toTest.getFirst());
		assertEquals(Optional.empty(), toTest.getLast());
		assertEquals(count, 0);
	}

	@Test
	public void addFirst_should_add_an_item_in_the_first_and_last_position_of_an_empty_list() {
		// Arrange
		int firstItem = newItem();

		// Act
		toTest.addFirst(firstItem);

		// Assert
		assertEquals(firstItem, (int) toTest.getFirst().get());
		assertEquals(firstItem, (int) toTest.getLast().get());
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

	@Test
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

	@Test
	public void contains_Should_return_false_when_an_item_cant_be_found_in_the_list() {
		// Arrange
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());

		// Act
		boolean contains = toTest.contains(newItem());

		// Assert
		assertFalse(contains);
	}

	@Test
	public void contains_Should_return_true_when_an_item_can_be_found_in_the_list() {
		// Arrange
		int toFind = newItem();
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(toFind);
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());

		// Act
		boolean contains = toTest.contains(toFind);

		// Assert
		assertTrue(contains);
	}

	@Test
	public void clear_Should_empty_the_list() {
		// Arrange
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());
		toTest.addFirst(newItem());

		// Act
		toTest.clear();

		// Assert
		assertEquals(toTest.getFirst(), Optional.empty());
		assertEquals(toTest.getLast(), Optional.empty());
		assertEquals(toTest.getCount(), 0);
	}

	@Test
	public void remove_Should_remove_an_item_in_the_middle_of_a_list() {
		// Arrange
		int itemToRemove = newItem();
		int itemBeforeRemove = newItem();
		int itemAfterRemove = newItem();

		toTest.addLast(newItem());
		toTest.addLast(newItem());
		toTest.addLast(newItem());
		toTest.addLast(itemBeforeRemove);
		toTest.addLast(itemToRemove);
		toTest.addLast(itemAfterRemove);
		toTest.addLast(newItem());
		toTest.addLast(newItem());

		// Act
		toTest.remove(itemToRemove);

		// Assert
		assertEquals(toTest.getCount(), 7);
		assertFalse(toTest.contains(itemToRemove));
		assertTrue(toTest.isBefore(itemBeforeRemove, itemAfterRemove));
		assertTrue(toTest.isAfter(itemAfterRemove, itemBeforeRemove));
	}

	@Test
	public void remove_Should_remove_the_first_item_of_a_list() {
		// Arrange
		int itemToRemove = newItem();
		int itemAfterRemove = newItem();

		toTest.addFirst(itemToRemove);
		toTest.addAfter(itemAfterRemove, itemToRemove);
		toTest.addLast(newItem());
		toTest.addLast(newItem());
		toTest.addLast(newItem());

		// Act
		int removed = toTest.remove(itemToRemove).get();

		// Assert
		assertEquals(itemToRemove, removed);
		assertEquals(toTest.getCount(), 4);
		assertFalse(toTest.contains(itemToRemove));
		assertEquals(itemAfterRemove, (int) toTest.getFirst().get());
	}

	@Test
	public void remove_Should_remove_the_last_item_of_a_list() {
		// Arrange
		int itemToRemove = newItem();
		int itemBeforeRemove = newItem();

		toTest.addLast(newItem());
		toTest.addLast(newItem());
		toTest.addLast(newItem());
		toTest.addLast(itemBeforeRemove);
		toTest.addLast(itemToRemove);

		// Act
		int removed = toTest.remove(itemToRemove).get();

		// Assert
		assertEquals(itemToRemove, removed);
		assertEquals(toTest.getCount(), 4);
		assertFalse(toTest.contains(itemToRemove));
		assertEquals(itemBeforeRemove, (int) toTest.getLast().get());
	}

	@Test
	public void removeFirst_should_remove_the_first_and_only_item_in_a_list() {
		// Arrange
		int itemToRemove = newItem();

		toTest.addLast(itemToRemove);

		// Act
		int removed = toTest.removeFirst().get();

		// Assert
		assertEquals(itemToRemove, removed);
		assertFalse(toTest.contains(itemToRemove));
		assertEquals(Optional.empty(), toTest.getFirst());
		assertEquals(Optional.empty(), toTest.getLast());
		assertEquals(toTest.getCount(), 0);
	}

	@Test
	public void removeFirst_should_remove_the_first_item_in_a_multi_valued_list() {
		// Arrange
		int itemToRemove = newItem();
		int itemAfterRemove = newItem();
		int itemAfterAfterRemove = newItem();

		toTest.addLast(itemToRemove);
		toTest.addLast(itemAfterRemove);
		toTest.addLast(itemAfterAfterRemove);
		toTest.addLast(newItem());
		toTest.addLast(newItem());

		// Act
		int removed = toTest.remove(itemToRemove).get();

		// Assert
		assertEquals(itemToRemove, removed);
		assertEquals(toTest.getCount(), 4);
		assertFalse(toTest.contains(itemToRemove));
		assertEquals(itemAfterRemove, (int) toTest.getFirst().get());

		assertTrue(toTest.isBefore(itemAfterRemove, itemAfterAfterRemove));
		assertTrue(toTest.isAfter(itemAfterAfterRemove, itemAfterRemove));
	}

	@Test
	public void removeLast_should_remove_the_last_and_only_item_in_a_list() {
		// Arrange
		int itemToRemove = newItem();

		toTest.addLast(itemToRemove);

		// Act
		int removed = toTest.removeLast().get();

		// Assert
		assertEquals(itemToRemove, removed);
		assertFalse(toTest.contains(itemToRemove));
		assertEquals(Optional.empty(), toTest.getFirst());
		assertEquals(Optional.empty(), toTest.getLast());
		assertEquals(toTest.getCount(), 0);
	}

	@Test
	public void removeLast_should_remove_the_last_item_in_a_multi_valued_list() {
		// Arrange
		int itemToRemove = newItem();
		int itemBeforeRemove = newItem();
		int itemBeforeBeforeRemove = newItem();

		toTest.addLast(newItem());
		toTest.addLast(newItem());
		toTest.addLast(itemBeforeBeforeRemove);
		toTest.addLast(itemBeforeRemove);
		toTest.addLast(itemToRemove);

		// Act
		int removed = toTest.remove(itemToRemove).get();

		// Assert
		assertEquals(itemToRemove, removed);
		assertEquals(toTest.getCount(), 4);
		assertFalse(toTest.contains(itemToRemove));
		assertEquals(itemBeforeRemove, (int) toTest.getLast().get());

		assertTrue(toTest.isBefore(itemBeforeBeforeRemove, itemBeforeRemove));
		assertTrue(toTest.isAfter(itemBeforeRemove, itemBeforeBeforeRemove));
	}

	private int newItem() {
		itemCount = itemCount + 63;
		return itemCount;
	}
}
