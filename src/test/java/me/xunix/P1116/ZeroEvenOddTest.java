package me.xunix.P1116;

import java.util.function.IntConsumer;

public class ZeroEvenOddTest {
  public static void main(String[] args) throws InterruptedException {
    int n = 5;
    ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);
    StringBuilder builder = new StringBuilder();
    StringBuilder finalBuilder = builder;
    IntConsumer consumer = x -> {
      System.out.print(x);
      finalBuilder.append(x);
    };

    Thread tZero = new Thread(() -> {
      try {
        zeroEvenOdd.zero(consumer);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread tOdd = new Thread(() -> {
      try {
        zeroEvenOdd.odd(consumer);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread tEven = new Thread(() -> {
      try {

        zeroEvenOdd.even(consumer);

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    tZero.start();
    tEven.start();
    tOdd.start();

    tZero.join();
    tEven.join();
    tOdd.join();


    String actual = builder.toString();
    System.out.println();

    builder = new StringBuilder();
    for (int i = 1; i <= n; i++) {
      builder.append(0);
      builder.append(i);
    }
    String expect = builder.toString();
    System.out.println(expect);
    if (!actual.equals(expect)) {
      throw new RuntimeException("the answer is not right");
    }
  }
}
