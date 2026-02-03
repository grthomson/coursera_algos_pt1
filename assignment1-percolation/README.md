# Assignment 1 – Percolation

This folder contains my solution to the Percolation assignment from  
Princeton Algorithms, Part I (Coursera).

## Files

- Percolation.java  
- PercolationStats.java  

## How to run (command line)

Make sure `algs4.jar` is on your classpath.

From the project root:

javac -cp ".;libs/algs4.jar" assignment1-percolation/*.java  
java  -cp ".;libs/algs4.jar;assignment1-percolation" PercolationStats 200 100  

(On macOS/Linux, replace `;` with `:`)

Example:

java PercolationStats 100 50

## How to run (IntelliJ IDEA)

1. Open the project in IntelliJ
2. Add `algs4.jar` as a library
3. Run `PercolationStats.java` with program arguments, for example:

200 100
