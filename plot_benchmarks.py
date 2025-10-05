import pandas as pd
import matplotlib.pyplot as plt

# Create data manually (based on your CSV)
data_before = pd.DataFrame({
    'input_size': [100, 1000, 10000, 100000],
    'time_ns': [594292, 1800250, 5269209, 35045545]
})

data_after = pd.DataFrame({
    'input_size': [100, 1000, 10000, 100000],
    'time_ns': [551875, 1209583, 4396750, 24082167]
})

# Plot
plt.plot(data_before['input_size'], data_before['time_ns'], marker='o', label='Before Optimization')
plt.plot(data_after['input_size'], data_after['time_ns'], marker='s', label='After Optimization')

plt.xscale('log')
plt.yscale('log')  # log scale to see differences clearly
plt.xlabel('Input Size (n)')
plt.ylabel('Time (ns)')
plt.title('MinHeap Benchmark: Before vs After Optimization')
plt.legend()
plt.grid(True, which="both", ls="--", linewidth=0.5)

# Save figure
plt.savefig('docs/performance-plots/minheap_before_after.png', dpi=300)
plt.show()
