Java8InAction
===============

This repository contains all the source code for the examples and quizzes in the book Java 8 in Action: Lambdas, Streams and functional-style programming.

You can purchase the early access here: [http://manning.com/urma/](http://manning.com/urma/)

We are very keen to hear your feedback and improve the book based on your comments!

The source code for all examples can be found in the directory [src/main/java/lambdasinaction](https://github.com/java8/Java8InAction/tree/master/src/main/java/lambdasinaction)

* Chapter 1: Java 8: why should you care?
* Chapter 2: Passing code with behavior parameterization
* Chapter 3: Lambda expressions
* Chapter 4: Processing data with streams
* Chapter 5: Collecting data with streams
* Chapter 6: Parallel data processing and performance
* Chapter 7: Refactoring, testing, debugging
* Chapter 8: Default methods
* Chapter 9: Optional: a better alternative to null
* Chapter 10: CompletableFuture: composable asynchronous programming
* Chapter 11: New Date and Time API
* Chapter 12: Thinking functionally
* Chapter 13: Functional programming techniques
* Chapter 14: Blending OOP and FP: comparing Java 8 and Scala
* Chapter 15: Conclusions and "where next" for Java

We will update the repository as we update the book. Stay tuned!

### Make sure to have JDK8 installed
The latest binary can be found here: http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html

$ java -version

java version "1.8.0_05"
Java(TM) SE Runtime Environment (build 1.8.0_05-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.5-b02, mixed mode)


You can download a preview version here: https://jdk8.java.net/

### Compile/Run the examples
Using maven:

$ mvn compile

$ cd target/classes

$ java lambdasinaction/chap1/FilteringApples


Alternatively you can compile the files manually inside the directory src/main/java
