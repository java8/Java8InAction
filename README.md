LambdasInAction
===============

This repository contains all the source code for the examples and quizzes in the Java 8: Lambdas In Action book.

You can purchase the early access here: [http://manning.com/urma/](http://manning.com/urma/)

We are very keen to hear your feedback and improve the book based on your comments!

The source code for all examples can be found in the directory [src/main/java/lambdasinaction](https://github.com/java8/LambdasInAction/tree/master/src/main/java/lambdasinaction)

* Chapter 1: Introduction
* Chapter 2: Passing Code/Behaviour Parameterisation
* Chapter 3: Lambda expressions
* Chapter 4: Streams
* Chapter 5: Collectors
* Chapter 6: Parallel streams and performance
* Chapter 7: Default methods

We will update the repository as we update the book. Stay tuned!

### Make sure to have JDK8 installed
For example:

$ java -version

java version "1.8.0-ea"

Java(TM) SE Runtime Environment (build 1.8.0-ea-lambda-nightly-h109-20130902-b106-b00)

Java HotSpot(TM) 64-Bit Server VM (build 25.0-b45, mixed mode)


You can download a preview version here: https://jdk8.java.net/

### Compile/Run the examples
Using maven:

$ mvn compile

$ cd target/classes

$ java lambdasinaction/chap1/FilteringApples


Alternatively you can compile the files manually inside the directory src/main/java
