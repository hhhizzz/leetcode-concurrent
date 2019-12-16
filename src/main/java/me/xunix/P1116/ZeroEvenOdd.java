package me.xunix.P1116;

import java.util.function.IntConsumer;

class ZeroEvenOdd {
  private int n;
  private int current = 1;
  private boolean printZero = true;

  public ZeroEvenOdd(int n) {
    this.n = n;
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {
    for (int i = 0; i < n; i++) {
      synchronized (this) {
        while (!printZero) {
          this.wait();
        }
        printNumber.accept(0);
        this.printZero = false;
        this.notifyAll();
      }
    }
  }

  public void even(IntConsumer printNumber) throws InterruptedException {
    int half = n / 2;
    for (int i = 1; i <= half; i++) {
      synchronized (this) {
        while (printZero || this.current % 2 != 0) {
          this.wait();
        }
        printNumber.accept(current);
        this.current += 1;
        this.printZero = true;
        this.notifyAll();
      }
    }

  }

  public void odd(IntConsumer printNumber) throws InterruptedException {
    int half = n / 2;
    if (n % 2 == 1) {
      half += 1;
    }
    for (int i = 1; i <= half; i++) {
      synchronized (this) {
        while (printZero || this.current % 2 != 1) {
          this.wait();
        }
        printNumber.accept(current);
        this.current += 1;
        this.printZero = true;
        this.notifyAll();
      }
    }
  }
}
