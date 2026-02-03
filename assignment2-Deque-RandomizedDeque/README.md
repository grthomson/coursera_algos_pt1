# Assignment 2 – Deque and Randomized Queue

This folder contains my solution to Assignment 2 from  
Princeton Algorithms, Part I (Coursera).

## Files

- Deque.java  
- RandomizedQueue.java  
- Permutation.java  

## How to run (command line)

Make sure `algs4.jar` is on your classpath.

Compile:

javac -cp ".;libs/algs4.jar" assignment2-Deque-RandomizedDeque/*.java

Run Permutation:

java -cp ".;libs/algs4.jar;assignment2-Deque-RandomizedDeque" Permutation 3 < data/animals.txt

(On macOS/Linux replace `;` with `:`)

## Notes

- Deque is implemented using a doubly linked list (O(1) operations).
- RandomizedQueue is implemented using a resizing array (O(1) amortized).
- Permutation prints k random strings from standard input.
