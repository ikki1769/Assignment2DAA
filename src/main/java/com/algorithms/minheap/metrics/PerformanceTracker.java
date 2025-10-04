package com.algorithms.minheap.metrics;

import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private final String algorithmName;
    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long startTime;
    private long endTime;

    public PerformanceTracker(String algorithmName) {
        this.algorithmName = algorithmName;
        reset();
    }


    public void incrementComparisons() { comparisons++; }
    public void incrementSwaps() { swaps++; }
    public void incrementArrayAccesses() { arrayAccesses++; }


    public void startTimer() { startTime = System.nanoTime(); }
    public void stopTimer() { endTime = System.nanoTime(); }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getElapsedTimeNanos() { return endTime - startTime; }
    public long getElapsedTimeMillis() { return (endTime - startTime) / 1_000_000; }


    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        startTime = 0;
        endTime = 0;
    }


    public void writeCsv(String filename, int inputSize) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(algorithmName + "," +
                    inputSize + "," +
                    getComparisons() + "," +
                    getSwaps() + "," +
                    getArrayAccesses() + "," +
                    getElapsedTimeNanos() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV", e);
        }
    }
}
