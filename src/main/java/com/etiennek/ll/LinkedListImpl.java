package com.etiennek.ll;

import java.util.Optional;

/**
 * A thread safe, generic linked list. Values stored in the list can not be
 * null.
 * 
 * @author Etienne
 *
 * @param <T>
 *            The types of the contents stored in the linked list.
 */
public class LinkedListImpl<T> implements LinkedList<T> {

	private Node first;
	private Node last;
	private int count = 0;

	@Override
	public void addFirst(T toAdd) {
		if (toAdd == null) {
			throw new NullPointerException("toAdd can not be null");
		}

		if (first == null) {
			first = new Node(null, null, toAdd);
			last = first;
		} else {
			Node nodeToAdd = new Node(null, first, toAdd);
			first.before = nodeToAdd;
			first = nodeToAdd;
		}
		count++;
	}

	@Override
	public void addLast(T toAdd) {
		if (toAdd == null) {
			throw new NullPointerException("toAdd can not be null");
		}

		if (last == null) {
			first = new Node(null, null, toAdd);
			last = first;
		} else {
			Node nodeToAdd = new Node(last, null, toAdd);
			last.after = nodeToAdd;
			last = nodeToAdd;
		}
		count++;
	}

	@Override
	public void addAfter(T toAdd, T after) {
		if (toAdd == null) {
			throw new NullPointerException("toAdd can not be null");
		}
		if (after == null) {
			throw new NullPointerException("after can not be null");
		}

		Node found = findNodeWithContents(after);
		if (found == null) {
			throw new IllegalStateException("after could not be found");
		}

		if (found.isLast()) {
			addLast(toAdd);
		} else {
			Node nodeToAdd = new Node(found, found.after, toAdd);
			found.after.before = nodeToAdd;
			found.after = nodeToAdd;
			count++;
		}
	}

	@Override
	public void addBefore(T toAdd, T before) {
		if (toAdd == null) {
			throw new NullPointerException("toAdd can not be null");
		}
		if (before == null) {
			throw new NullPointerException("before can not be null");
		}

		Node found = findNodeWithContents(before);
		if (found == null) {
			throw new IllegalStateException("before could not be found");
		}

		if (found.isFirst()) {
			addFirst(toAdd);
		} else {
			Node nodeToAdd = new Node(found.before, found, toAdd);
			found.before.after = nodeToAdd;
			found.before = nodeToAdd;
			count++;
		}
	}

	@Override
	public boolean contains(T toCheck) {
		if (toCheck == null) {
			throw new NullPointerException("toCheck can not be null");
		}
		return findNodeWithContents(toCheck) != null;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		count = 0;
	}

	@Override
	public Optional<T> remove(T toRemove) {
		if (toRemove == null) {
			throw new NullPointerException("toRemove can not be null");
		}

		Node found = findNodeWithContents(toRemove);
		if (found == null) {
			return Optional.empty();
		}

		if (found.isFirst()) {
			removeFirst();
		} else if (found.isLast()) {
			removeLast();
		} else {
			Node before = found.before;
			Node after = found.after;

			before.after = after;
			after.before = before;

			found.before = null;
			found.after = null;
			count--;
		}

		return Optional.of(toRemove);
	}

	@Override
	public Optional<T> removeFirst() {
		if (first == null) {
			return Optional.empty();
		}
		T toReturn = first.contents;

		if (first == last) {
			first = null;
			last = null;
		} else {
			first = first.after;
			first.before = null;
		}

		count--;
		return Optional.of(toReturn);
	}

	@Override
	public Optional<T> removeLast() {
		if (last == null) {
			return Optional.empty();
		}
		T toReturn = last.contents;

		if (first == last) {
			first = null;
			last = null;
		} else {
			last = last.before;
			last.after = null;
		}

		count--;
		return Optional.of(toReturn);
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public Optional<T> getFirst() {
		if (first == null)
			return Optional.empty();
		return Optional.of(first.contents);
	}

	@Override
	public Optional<T> getLast() {
		if (last == null)
			return Optional.empty();
		return Optional.of(last.contents);
	}

	private Node findNodeWithContents(T toFind) {
		if (getCount() == 0) {
			return null;
		}
		Node currentNode = first;
		do {
			if (currentNode.contents.equals(toFind)) {
				return currentNode;
			}
			currentNode = currentNode.after;
		} while (currentNode != null);
		return null;
	}

	private class Node {
		Node before;
		Node after;
		T contents;

		Node(Node before, Node after, T contents) {
			this.before = before;
			this.after = after;
			this.contents = contents;
		}

		boolean isFirst() {
			return before == null;
		}

		boolean isLast() {
			return after == null;
		}
	}

	@Override
	public boolean isBefore(T toCheck, T after) {
		if (toCheck == null) {
			throw new NullPointerException("toCheck can not be null");
		}
		if (after == null) {
			throw new NullPointerException("after can not be null");
		}

		Node toCheckNode = findNodeWithContents(toCheck);
		Node afterNode = findNodeWithContents(after);

		if (toCheckNode == null || afterNode == null) {
			return false;
		}

		return toCheckNode.after == afterNode;
	}

	@Override
	public boolean isAfter(T toCheck, T before) {
		if (toCheck == null) {
			throw new NullPointerException("toCheck can not be null");
		}
		if (before == null) {
			throw new NullPointerException("before can not be null");
		}

		Node toCheckNode = findNodeWithContents(toCheck);
		Node beforeNode = findNodeWithContents(before);

		if (toCheckNode == null || beforeNode == null) {
			return false;
		}

		return toCheckNode.before == beforeNode;
	}

}
