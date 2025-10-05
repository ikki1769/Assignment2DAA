package com.algorithms.minheap.cli;

import com.algorithms.minheap.algorithm.MinHeap;
import com.algorithms.minheap.metrics.PerformanceTracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int size = 100000; // default size
        String csvFile = "results.csv";
        long seed = 1234;

        // обработка аргументов
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--size":
                    if (i + 1 < args.length) size = Integer.parseInt(args[++i]);
                    break;
                case "--csv":
                    if (i + 1 < args.length) csvFile = args[++i];
                    break;
                case "--seed":
                    if (i + 1 < args.length) seed = Long.parseLong(args[++i]);
                    break;
                case "--help":
                    printUsage();
                    return;
            }
        }

        // создаём CSV, если не существует
        File file = new File(csvFile);
        if (!file.exists()) {
            try (FileWriter fw = new FileWriter(csvFile, false)) {
                fw.write("algorithm,input_size,comparisons,swaps,array_accesses,time_ns,time_ms\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // создаём массив
        int[] arr = new int[size];
        Random random = new Random(seed);
        for (int i = 0; i < size; i++) arr[i] = random.nextInt(1_000_001);

        // бенчмарк
        PerformanceTracker tracker = new PerformanceTracker("MinHeap");
        long start = System.nanoTime();

        // **bulk build**
        MinHeap heap = new MinHeap(arr, tracker);

        while (!heap.isEmpty()) heap.extractMin();
        long end = System.nanoTime();

        // запись в CSV
        try (FileWriter fw = new FileWriter(csvFile, true)) {
            fw.write(String.format("MinHeap,%d,%d,%d,%d,%d,%d\n",
                    size,
                    tracker.getComparisons(),
                    tracker.getSwaps(),
                    tracker.getArrayAccesses(),
                    end - start,
                    (end - start) / 1_000_000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // вывод в консоль
        System.out.printf("OptimizedMinHeap: n=%d, time(ns)=%d, time(ms)=%d, comparisons=%d, swaps=%d, array_accesses=%d, CSV=%s%n",
                size,
                end - start,
                (end - start) / 1_000_000,
                tracker.getComparisons(),
                tracker.getSwaps(),
                tracker.getArrayAccesses(),
                csvFile);
    }

    private static void printUsage() {
        System.out.println("Usage: java -jar minheap-benchmark.jar --size <n> --csv <file> [--seed <seed>]");
        System.out.println("Example: java -jar minheap-benchmark.jar --size 10000 --csv results.csv --seed 42");
    }
}
