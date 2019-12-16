package me.xunix.P1195;

import java.util.function.IntConsumer;

class FizzBuzz {
  private int n;
  private int current = 1;

  public FizzBuzz(int n) {
    this.n = n;
  }

  // printFizz.run() outputs "fizz".
  public void fizz(Runnable printFizz) throws InterruptedException {
    while (this.current <= n) {
      synchronized (this) {
        while (!(this.current % 3 == 0 && this.current % 5 != 0)) {
          this.wait();
          if (current > n) {
            return;
          }
        }
        printFizz.run();
        this.current += 1;
        this.notifyAll();
      }
    }
  }

  // printBuzz.run() outputs "buzz".
  public void buzz(Runnable printBuzz) throws InterruptedException {
    while (this.current <= n) {
      synchronized (this) {
        while (!(this.current % 5 == 0 && this.current % 3 != 0)) {
          this.wait();
          if (current > n) {
            return;
          }
        }
        printBuzz.run();
        this.current += 1;
        this.notifyAll();
      }
    }
  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
    while (this.current <= n) {
      synchronized (this) {
        while (!(this.current % 5 == 0 && this.current % 3 == 0)) {
          this.wait();
          if (current > n) {
            return;
          }
        }
        printFizzBuzz.run();
        this.current += 1;
        this.notifyAll();
      }
    }
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number(IntConsumer printNumber) throws InterruptedException {
    while (this.current <= n) {
      synchronized (this) {
        while (this.current % 3 == 0 || this.current % 5 == 0) {
          this.wait();
          if (current > n) {
            return;
          }
        }
        printNumber.accept(current);
        this.current += 1;
        this.notifyAll();
      }
    }
  }
}
