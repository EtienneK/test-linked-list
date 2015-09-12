package com.etiennek.ll;

import java.util.Optional;

public interface LinkedList<T> {
	void addFirst(T toAdd);

	void addLast(T toAdd);

	void addAfter(T toAdd, T after);

	void addBefore(T toAdd, T before);

	boolean contains(T toCheck);

	void clear();

	Optional<T> remove(T toRemove);

	Optional<T> removeFirst();

	Optional<T> removeLast();

	boolean isBefore(T toCheck, T before);

	boolean isAfter(T toCheck, T after);

	int getCount();

	Optional<T> getFirst();

	Optional<T> getLast();
}
