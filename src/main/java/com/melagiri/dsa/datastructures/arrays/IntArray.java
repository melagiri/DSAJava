package com.melagiri.dsa.datastructures.arrays;

import java.util.Iterator;
import java.util.stream.IntStream;

public class IntArray implements Iterable<Integer> {
    private int[] arr;
    private int len = 0;
    private int capacity = 0;

    public IntArray() {
        this(16);
    }

    /**
     * @param capacity Capacity
     */
    public IntArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        this.capacity = capacity;
        arr = new int[capacity];
    }

    // Iterator is still fast but not as fast as iterative for loop
    @Override
    public java.util.Iterator<Integer> iterator() {
        return new java.util.Iterator<>() {
            int index = 0;

            public boolean hasNext() {
                return index < len;
            }

            public Integer next() {
                return arr[index++];
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
