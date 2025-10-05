package com.algorithms.minheap.algorithm;

import com.algorithms.minheap.metrics.PerformanceTracker;

public class MinHeap {
    private int[] heap;
    private int size;
    private final PerformanceTracker tracker;

    public MinHeap(int capacity, PerformanceTracker tracker) {
        this.heap = new int[Math.max(1, capacity)];
        this.size = 0;
        this.tracker = tracker;
    }

    // Bulk build from array (O(n))
    public MinHeap(int[] array, PerformanceTracker tracker) {
        this.heap = array.clone();
        this.size = array.length;
        this.tracker = tracker;
        buildHeap();
    }

    private void buildHeap() {
        for (int i = parent(size - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    public void insert(int value) {
        ensureCapacity();
        heap[size] = value;
        tracker.incrementArrayAccesses();
        size++;
        heapifyUp(size - 1);
    }

    public int extractMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        int min = heap[0];
        heap[0] = heap[size - 1];
        tracker.incrementArrayAccesses();
        tracker.incrementSwaps();
        size--;
        heapifyDown(0);
        return min;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        tracker.incrementArrayAccesses();
        return heap[0];
    }

    public boolean isEmpty() { return size == 0; }

    private void heapifyUp(int i) {
        while (i > 0) {
            int p = parent(i);
            tracker.incrementComparisons();
            if (heap[i] >= heap[p]) break;
            swap(i, p);
            i = p;
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int left = leftChild(i);
            int right = rightChild(i);
            int smallest = i;

            if (left < size) {
                tracker.incrementComparisons();
                if (heap[left] < heap[smallest]) smallest = left;
            }
            if (right < size) {
                tracker.incrementComparisons();
                if (heap[right] < heap[smallest]) smallest = right;
            }

            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        tracker.incrementArrayAccesses();
        tracker.incrementSwaps();
    }

    private void ensureCapacity() {
        if (size == heap.length) {
            int[] newHeap = new int[heap.length * 2];
            System.arraycopy(heap, 0, newHeap, 0, heap.length);
            heap = newHeap;
        }
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
}
