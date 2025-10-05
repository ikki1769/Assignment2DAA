
```markdown
# **Assignment 2: MinHeap Implementation**

## **Overview**
This project implements a **MinHeap** data structure in **Java 17**, supporting key operations such as **insert**, **extract-min**, and **peek**.  
The implementation features **bulk construction (Floyd's algorithm)** for optimal O(n) heap building and includes **comprehensive performance benchmarks** to validate empirical performance across various input types and sizes.

## **The project provides:**
- **CLI Benchmarks:** Measure time and memory usage for different input sizes (100, 1000, 10000, 100000)
- **JMH Microbenchmarks:** Provide precise performance measurements for different input distributions (random, sorted, reverse, nearly-sorted)
- **Performance Tracking:** Comprehensive metrics including comparisons, swaps, array accesses, and execution time
- **Performance Plots:** Visualize time vs. input size on logarithmic scale

---

## **Project Structure**

- **Source Code**: `src/main/java/com/algorithms/minheap/algorithm/MinHeap.java` *(core min-heap implementation)*
- **Unit Tests**: `src/test/java/tests/MinHeapTest.java` *(JUnit tests for correctness)*
- **JMH Benchmarks**: `src/main/java/com/algorithms/minheap/jmh/MinHeapJmh.java` *(microbenchmarks for various inputs)*
- **CLI Benchmarks**: `src/main/java/com/algorithms/minheap/cli/BenchmarkRunner.java` *(outputs results.csv and newresults.csv)*
- **Performance Metrics**: `src/main/java/com/algorithms/minheap/metrics/PerformanceTracker.java` *(metrics collection)*
- **JMH Runner**: `src/main/java/com/algorithms/minheap/jmh/JmhRunner.java` *(JMH benchmark executor)*

## **Complexity Analysis**

### Theoretical Complexity
- **Bulk Construction (Floyd's)**: O(n) - Bottom-up heapification
- **Sequential Construction**: O(n log n) - n insertions at O(log n) each
- **Insert Operation**: O(log n) - Worst-case height of the heap
- **ExtractMin Operation**: O(log n) - Heapify down from root
- **Peek Operation**: O(1) - Direct access to root element

### Space Complexity
- **Total Space**: Θ(n) for heap array storage
- **Auxiliary Space**: O(1) for all operations (in-place)
- **Memory Overhead**: Minimal for capacity management

### Recurrence Relations
**Heap Construction**:
- Sequential: `T(n) = T(n-1) + O(log n)` → `O(n log n)`
- Bulk: `T(n) = O(n)` (Floyd's algorithm)

**Heapify Operations**:
- `T(h) = T(h-1) + O(1)` → `O(log n)` where h is heap height


### Summary
| Case | Time Complexity | Space Complexity | Notes |
|------|-----------------|------------------|-------|
| Best | Ω(n) | O(1) | Bulk construction |
| Average | Θ(n log n) | O(1) | Sequential operations |
| Worst | O(n log n) | O(1) | Reverse-ordered input |


## **Performance Results**

### JMH Microbenchmarks (ms/op)
| Input Type      | Size=100 | Size=1,000 | Size=10,000 | Size=100,000 |
|-----------------|----------|------------|-------------|--------------|
| Random Array    | 0.002    | 0.037      | 1.120       | 15.381       |
| Sorted Array    | 0.002    | 0.034      | 0.919       | 10.225       |
| Reverse Array   | 0.002    | 0.042      | 0.852       | 9.825        |
| Nearly Sorted   | 0.002    | 0.034      | 0.930       | 10.483       |

**Before Optimization (Sequential Insert - O(n log n))**
| Size  | Comparisons | Swaps  | Array Accesses | Time      |
|-------|-------------|--------|----------------|-----------|
| 100   | 698         | 628    | 728            | 594,292 ns|
| 1,000 | 12,011      | 9,590  | 10,590         | 1,890,250 ns|
| 10,000| 169,705     | 129,390| 139,390        | ~5.3 ms   |
| 100,000| 2,904,545  | 1,554,080| 1,558,066    | ~35.9 ms  |

**After Optimization (Bulk Build - O(n))**
| Size  | Comparisons | Swaps  | Array Accesses | Time      |
|-------|-------------|--------|----------------|-----------|
| 100   | 1,031       | 588    | 588            | 551,875 ns|
| 1,000 | 16,885      | 9,040  | 9,040          | 1,209,585 ns|
| 10,000| 235,444     | 124,235| 124,235        | 4.4 ms    |
| 100,000| 3,019,840  | 1,574,479| 1,574,479    | 24.1 ms   |

**Improvement**: ~33% faster construction for 100,000 elements (35.9ms → 24.1ms)

---

## **Setup Instructions**

### Prerequisites
- **Java 17**
- **Maven** (for building and running benchmarks)
- **Python 3.x** (for generating plots - optional)
- **Git**

### Build Project
```bash
mvn clean compile
```

### Run Tests
```bash
mvn test
```

### Build JAR
```bash
mvn clean package
```

---

## **Usage**

### CLI Benchmark
```bash
# Run with default parameters (size=100000)
java -jar target/assignment2-minheap-1.0-SNAPSHOT.jar

# Custom size and output file
java -jar target/assignment2-minheap-1.0-SNAPSHOT.jar --size 10000 --csv results.csv

# With custom seed for reproducibility
java -jar target/assignment2-minheap-1.0-SNAPSHOT.jar --size 10000 --csv results.csv --seed 42

# Show help
java -jar target/assignment2-minheap-1.0-SNAPSHOT.jar --help
```

### Generate Performance Plots 
```bash
# Install Python dependencies
pip install pandas matplotlib

# Generate performance plots
python plot_benchmarks.py
```

---

## **Output Files**

- **`results.csv`**: Original benchmark results (before optimization)
- **`newresults.csv`**: Optimized benchmark results (after bulk construction)
- **`docs/performance-plots/jmhresults.csv`**: JMH microbenchmark results
- **`docs/performance-plots/minheap_before_after.png`**: Performance comparison visualization
- **JMH Results**: Console output with detailed microbenchmark metrics

---

## **Key Features**

- **Bulk Heap Construction**: O(n) Floyd's algorithm vs traditional O(n log n) sequential insert
- **Comprehensive Metrics**: Track comparisons, swaps, array accesses, and execution time
- **Multiple Benchmark Types**: CLI for quick tests, JMH for precise measurements
- **Input Distribution Testing**: Random, sorted, reverse-sorted, and nearly-sorted data
- **Reproducible Results**: Seed-based random generation for consistent benchmarking

---