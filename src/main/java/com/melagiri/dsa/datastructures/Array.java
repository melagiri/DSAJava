package com.melagiri.dsa.datastructures;

import java.util.Iterator;
import java.util.stream.IntStream;

public class Array <T> implements Iterable <T> {
    private T [] arr;
    private int len = 0;
    private int capacity = 0;

    public Array() {
        this(16);
    }

    /**
     * @param capacity
     */
    public Array(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return len;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T elem) {
        arr[index] = elem;
    }

    // Clear the array
    public void clear() {
        IntStream.range(0, capacity).forEach(i -> arr[i] = null);
        len = 0;
    }

    public void add(T element) {
        // Time to resize
        if (len + 1 >= capacity) {
            if (capacity == 0) capacity = 1; //set size to 1
            else capacity *= 2; // double the size

            T[] newArr = (T[]) new Object[capacity];
            IntStream.range(0, len).forEach(i -> newArr[i] = arr[i]);
            arr = newArr; // arr has extra nulls padded
        }
        arr[len++] = element;
    }

    // Removes the element at the specified index in the list
    public T removeAt(int index) {
        if (index >= len || index < 0) throw new ArrayIndexOutOfBoundsException();

        T data = arr[index];
        T[] newArr = (T[]) new Object[len - 1];

        for (int i = 0, j = 0; i < len; i++, j++) {
            if (i == index) j--; // Skip over the index by fixing j temporarily
            else newArr[j] = arr[i];
        }
        arr = newArr;
        capacity = --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < len; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i <len; i++)
            if (arr[i].equals(obj)) return i;
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;
            @Override public boolean hasNext() { return index < len;}
            @Override public T next() { return arr[index++]; }
        };
    }

    @Override public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder string = new StringBuilder(len).append("[");
            IntStream.range(0, len - 1).forEach(i -> string.append(arr[i]).append(", "));
            return string.append(arr[len - 1]).append("]").toString();
        }
    }
}
