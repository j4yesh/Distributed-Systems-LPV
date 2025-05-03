from mpi4py import MPI
import numpy as np

# Initialize MPI
comm = MPI.COMM_WORLD
rank = comm.Get_rank()  # Rank of the current processor
size = comm.Get_size()  # Total number of processors

# The array size (N) and number of processors (n)
N = 100
n = size  # Total processors

# Each processor will handle N/n elements
chunk_size = N // n

# Create the full array and divide it into chunks (done on rank 0)
if rank == 0:
    array = np.random.randint(1, 100, N)  # Example array with random elements
    print("Original Array:", array)
else:
    array = None

# Scatter the array into chunks, so each processor gets its part
local_array = np.zeros(chunk_size, dtype=int)
comm.Scatter(array, local_array, root=0)

# Each processor calculates its local sum
local_sum = np.sum(local_array)

# Display the intermediate sum at each processor
print(f"Processor {rank} local sum: {local_sum}")

# Gather the local sums at the root processor
total_sum = comm.reduce(local_sum, op=MPI.SUM, root=0)

# Display the total sum at the root processor
if rank == 0:
    print(f"Total sum calculated: {total_sum}")
