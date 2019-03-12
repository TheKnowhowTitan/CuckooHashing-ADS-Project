# CuckooHashing-ADS-Project
## Problem Statment-
  Q.3) A memory of size 20 with three blocks is available for storage [20x3]. Assume suitable dataset of size 100 and demonstrate the implementation of Cuckoo Hashing, with different possibilities. [Each element of the dataset is an object, which will be stored in one block]. Comment on size of memory required for implementation.

## Technology Stack
Netbeans 8.0.2 (IDE)
Java (used as the preferred programming language)

## Key Functionality
As in Cuckoo Hashing, we have two tables. Each Table has 20 rows and every row has 3 blocks. In order for the Hash Function to uniformly occupy all the 20 rows, we have chosen it as follows-
1) h1(x) = x%17 + x%4
2) h2(x) = x%19 + x%2

Next, we are required to hash a dataset of 100 objects to our hash table. For convenience, we have chosen  the data type of the Object as Integer and used the random function to generate 100 such integers. 

There are 5 such datasets demonstrating various scenarios.

We then call the function place which maintains a queue. When we insert an element it gets added to the queue. If there is a cycle formed, the element at the front of the queue would be the same as the element to be  inserted. Thus, we can detect the cycle and print it. We also maintain two pointers that will point to the  next empty block in a row. Once all the blocks in a row are occupied, we start hashing the values to the next table.

Finally, there is a function print that would print the contents of both hash tables.

## How to execute
Open the Netbeans or any other IDE where the program is to be run.

Compile and Run the program.

When the prompt is displayed, Copy Paste the values from one of the datasets to the console.

The Output for the respective dataset would be displayed.
