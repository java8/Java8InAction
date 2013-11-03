LambdasInAction
===============

This repository contains all the source code for the examples and quizzes in the Java 8: Lambdas in Action book.
You can purchase the early access here: http://manning.com/urma/
We are very keen to hear your feedback and improve the book based on your comments!

Instructions to compile and run the source code:

1) Make sure to have a JDK8 installed
For example:
$ java -version
java version "1.8.0-ea"
Java(TM) SE Runtime Environment (build 1.8.0-ea-lambda-nightly-h109-20130902-b106-b00)
Java HotSpot(TM) 64-Bit Server VM (build 25.0-b45, mixed mode)

You can download a preview version here: https://jdk8.java.net/

2) Compile/Run the examples
Using maven:
$ mvn compile
$ cd target/classes
$ java lambdasinaction/chap1/FilteringApples

Alternatively you can compile the files manually in src/main/java
