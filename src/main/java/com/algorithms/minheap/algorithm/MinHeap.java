package com.algorithms.minheap.algorithm;

import com.algorithms.minheap.metrics.PerformanceTracker;
import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int size;
    private final PerformanceTracker tracker;

    public MinHeap(int capacity, PerformanceTracker tracker) {
        this.heap = new int[Math.max(1, capacity)];
        this.size = 0;
        this.tracker = tracker;
    }

    public void insert(int value) {
        tracker.startTimer();
        ensureCapacity();
        heap[size] = value;
        tracker.incrementArrayAccesses();
        size++;
        heapifyUp(size - 1);
        tracker.stopTimer();
    }

    public int extractMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        tracker.startTimer();
        int min = heap[0];
        heap[0] = heap[size - 1];
        tracker.incrementArrayAccesses();
        tracker.incrementSwaps();
        size--;
        heapifyDown(0);
        tracker.stopTimer();
        return min;
    }

    public int peek() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        tracker.incrementArrayAccesses();
        return heap[0];
    }

    public boolean isEmpty() { return size == 0; }

    private void heapifyUp(int i) {
        while (i > 0 && heap[parent(i)] > heap[i]) {
            tracker.incrementComparisons();
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void heapifyDown(int i) {
        while (true) {
            int left = leftChild(i);
            int right = rightChild(i);
            int smallest = i;

            if (left < size && heap[left] < heap[smallest]) {
                tracker.incrementComparisons();
                smallest = left;
            }
            if (right < size && heap[right] < heap[smallest]) {
                tracker.incrementComparisons();
                smallest = right;
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
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }
}
