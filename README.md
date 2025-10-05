
# Assignment 2: MinHeap Implementation

## Overview
This project implements a **MinHeap** data structure in **Java 17**, supporting key operations such as **insert**, **extract-min**, and **peek**.  

The implementation features **bulk construction (Floyd's algorithm)** for optimal O(n) heap building and includes **comprehensive performance benchmarks** to validate empirical performance across various input types and sizes.

---

## Project Features
- **CLI Benchmarks** – Measure time and memory usage for different input sizes (100, 1,000, 10,000, 100,000)
- **JMH Microbenchmarks** – Precise performance measurements for multiple input distributions (random, sorted, reverse, nearly-sorted)
- **Performance Tracking** – Metrics including comparisons, swaps, array accesses, and execution time
- **Performance Plots** – Visualize time vs input size on a logarithmic scale

---

## Project Structure

| Component | Path | Description |
|-----------|------|-------------|
| Source Code | `src/main/java/com/algorithms/minheap/algorithm/MinHeap.java` | Core min-heap implementation |
| Unit Tests | `src/test/java/tests/MinHeapTest.java` | JUnit tests for correctness |
| JMH Benchmarks | `src/main/java/com/algorithms/minheap/jmh/MinHeapJmh.java` | Microbenchmarks for different input distributions |
| CLI Benchmarks | `src/main/java/com/algorithms/minheap/cli/BenchmarkRunner.java` | Outputs `results.csv` and `newresults.csv` |
| Performance Metrics | `src/main/java/com/algorithms/minheap/metrics/PerformanceTracker.java` | Metrics collection for comparisons, swaps, accesses, and time |
| JMH Runner | `src/main/java/com/algorithms/minheap/jmh/JmhRunner.java` | JMH benchmark executor |

---

## Complexity Analysis

### Theoretical Complexity
- **Bulk Construction (Floyd's)** – O(n)
- **Sequential Construction** – O(n log n)
- **Insert Operation** – O(log n)
- **ExtractMin Operation** – O(log n)
- **Peek Operation** – O(1)

### Space Complexity
- **Total Space:** Θ(n) for heap array storage  
- **Auxiliary Space:** O(1) in-place operations  

### Summary Table

| Case | Time Complexity | Space Complexity | Notes |
|------|----------------|-----------------|-------|
| Best | Ω(n) | O(1) | Bulk construction |
| Average | Θ(n log n) | O(1) | Sequential operations |
| Worst | O(n log n) | O(1) | Reverse-ordered input |

---

## Performance Results

### JMH Microbenchmarks (ms/op)

| Input Type    | 100    | 1,000  | 10,000 | 100,000 |
|---------------|--------|--------|--------|---------|
| Random        | 0.002  | 0.037  | 1.120  | 15.381  |
| Sorted        | 0.002  | 0.034  | 0.919  | 10.225  |
| Reverse       | 0.002  | 0.042  | 0.852  | 9.825   |
| Nearly Sorted | 0.002  | 0.034  | 0.930  | 10.483  |

---

### CLI Benchmarks

**Before Optimization (Sequential Insert - O(n log n))**

| Size    | Comparisons | Swaps   | Array Accesses | Time        |
|---------|------------|--------|----------------|------------|
| 100     | 698        | 628    | 728            | 594,292 ns |
| 1,000   | 12,011     | 9,590  | 10,590         | 1,890,250 ns |
| 10,000  | 169,705    | 129,390| 139,390        | ~5.3 ms   |
| 100,000 | 2,904,545  | 1,554,080 | 1,558,066   | ~35.9 ms  |

**After Optimization (Bulk Build - O(n))**

| Size    | Comparisons | Swaps   | Array Accesses | Time        |
|---------|------------|--------|----------------|------------|
| 100     | 1,031      | 588    | 588            | 551,875 ns |
| 1,000   | 16,885     | 9,040  | 9,040          | 1,209,585 ns |
| 10,000  | 235,444    | 124,235| 124,235        | 4.4 ms    |
| 100,000 | 3,019,840  | 1,574,479 | 1,574,479   | 24.1 ms   |

**Improvement:** ~33% faster construction for 100,000 elements (35.9ms → 24.1ms)

---

## Setup Instructions

### Prerequisites
- Java 17  
- Maven  
- Python 3.x (optional, for plots)  
- Git  

### Build Project
```bash
mvn clean compile
````

### Run Tests

```bash
mvn test
```

### Build JAR

```bash
mvn clean package
```

---

## Usage

### CLI Benchmark

```bash
# Run default (size=100000)
java -jar target/assignment2-minheap-1.0-SNAPSHOT.jar

# Custom size & output CSV
java -jar target/assignment2-minheap-1.0-SNAPSHOT.jar --size 10000 --csv results.csv

# Seed for reproducibility
java -jar target/assignment2-minheap-1.0-SNAPSHOT.jar --size 10000 --csv results.csv --seed 42

# Show help
java -jar target/assignment2-minheap-1.0-SNAPSHOT.jar --help
```

### JMH Microbenchmarks

```bash
# Run all JMH benchmarks
mvn exec:java -Dexec.mainClass="com.algorithms.minheap.jmh.JmhRunner"
```

### Generate Performance Plots (Optional)

```bash
pip install pandas matplotlib
python plot_benchmarks.py
```

---

## Output Files

* `results.csv` – Original benchmark results
* `newresults.csv` – Optimized benchmark results
* `docs/performance-plots/jmhresults.csv` – JMH microbenchmark results
* `docs/performance-plots/minheap_before_after.png` – Visualization of performance improvement

---

## Key Features

* Bulk Heap Construction (Floyd's Algorithm, O(n))
* Comprehensive Metrics: comparisons, swaps, array accesses, execution time
* Multiple Benchmark Types: CLI & JMH
* Various Input Distributions: random, sorted, reverse-sorted, nearly-sorted
* Reproducible Results using random seeds

