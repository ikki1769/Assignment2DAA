package com.algorithms.minheap.jmh;

import com.algorithms.minheap.algorithm.MinHeap;
import com.algorithms.minheap.metrics.PerformanceTracker;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class MinHeapJmh {

    @Param({"100", "1000", "10000", "100000"})
    public int size;

    public int[] randomArray;
    public int[] sortedArray;
    public int[] reverseArray;
    public int[] nearlySortedArray;

    public PerformanceTracker tracker = new PerformanceTracker("MinHeapJmh");

    @Setup(Level.Trial)
    public void setUp() {
        Random rand = new Random(42);

        randomArray = new int[size];
        for (int i = 0; i < size; i++) randomArray[i] = rand.nextInt(1_000_001);

        sortedArray = new int[size];
        for (int i = 0; i < size; i++) sortedArray[i] = i + 1;

        reverseArray = new int[size];
        for (int i = 0; i < size; i++) reverseArray[i] = size - i;

        nearlySortedArray = Arrays.copyOf(sortedArray, size);
        int swaps = Math.max(1, size / 100);
        for (int i = 0; i < swaps; i++) {
            int a = rand.nextInt(size);
            int b = rand.nextInt(size);
            int tmp = nearlySortedArray[a];
            nearlySortedArray[a] = nearlySortedArray[b];
            nearlySortedArray[b] = tmp;
        }
    }

    private void buildAndExtract(int[] arr) {
        MinHeap heap = new MinHeap(arr, tracker); // bulk build
        while (!heap.isEmpty()) heap.extractMin();
    }

    @Benchmark
    public void randomArrayBenchmark() {
        buildAndExtract(randomArray);
    }

    @Benchmark
    public void sortedArrayBenchmark() {
        buildAndExtract(sortedArray);
    }

    @Benchmark
    public void reverseArrayBenchmark() {
        buildAndExtract(reverseArray);
    }

    @Benchmark
    public void nearlySortedArrayBenchmark() {
        buildAndExtract(nearlySortedArray);
    }
}
