# knapsack

A couple of solvers for the 0/1 Knapsack Problem.

## Prerequisites

Tested with:

- Clojure 1.6.0
- Leiningen 2.5.1
- Java 1.8.0_05 Java HotSpot(TM) 64-Bit Server VM

## Usage


To run tests, clone this project to your desktop:

- mkdir handojin
- cd handojin
- git clone https://github.com/handojin/knapsack.git

Change to the project directory and run test via leiningen:

- cd knapsack
- lein test

## Notes

The brute force solver explodes when the number of items is greater than 21.

The dynamic solver works but it needs some love. Chose to use mutable arrays as this is an array bashing problem.

## License

Copyright © 2015 

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
