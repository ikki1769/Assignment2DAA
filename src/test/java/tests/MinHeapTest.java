package tests;

import com.algorithms.minheap.algorithm.MinHeap;
import com.algorithms.minheap.metrics.PerformanceTracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    void testInsertAndPeek() {
        PerformanceTracker tracker = new PerformanceTracker("MinHeapTest");
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(5);
        heap.insert(3);
        heap.insert(8);

        assertEquals(3, heap.peek());
        assertEquals(3, heap.extractMin());
    }

    @Test
    void testExtractMinOrder() {
        PerformanceTracker tracker = new PerformanceTracker("MinHeapTest");
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(4);
        heap.insert(2);
        heap.insert(6);
        heap.insert(1);

        assertEquals(1, heap.extractMin());
        assertEquals(2, heap.extractMin());
        assertEquals(4, heap.extractMin());
        assertEquals(6, heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testPeekOnEmptyHeapThrows() {
        PerformanceTracker tracker = new PerformanceTracker("MinHeapTest");
        MinHeap heap = new MinHeap(5, tracker);

        assertThrows(IllegalStateException.class, heap::peek);
    }

    @Test
    void testExtractOnEmptyHeapThrows() {
        PerformanceTracker tracker = new PerformanceTracker("MinHeapTest");
        MinHeap heap = new MinHeap(5, tracker);

        assertThrows(IllegalStateException.class, heap::extractMin);
    }

    @Test
    void testInsertSingleElement() {
        PerformanceTracker tracker = new PerformanceTracker("MinHeapTest");
        MinHeap heap = new MinHeap(5, tracker);

        heap.insert(42);
        assertEquals(42, heap.peek());
        assertEquals(42, heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testDuplicateElements() {
        PerformanceTracker tracker = new PerformanceTracker("MinHeapTest");
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(7);
        heap.insert(7);
        heap.insert(7);

        assertEquals(7, heap.extractMin());
        assertEquals(7, heap.extractMin());
        assertEquals(7, heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testLargeHeap() {
        PerformanceTracker tracker = new PerformanceTracker("MinHeapTest");
        MinHeap heap = new MinHeap(1, tracker);

        for (int i = 1000; i >= 1; i--) {
            heap.insert(i);
        }

        for (int i = 1; i <= 1000; i++) {
            assertEquals(i, heap.extractMin());
        }

        assertTrue(heap.isEmpty());
    }
}
