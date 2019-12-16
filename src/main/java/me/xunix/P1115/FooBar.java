package me.xunix.P1115;

class FooBar {
  private int n;
  private int current = 1;

  public FooBar(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {
    synchronized (this) {
      for (int i = 0; i < n; i++) {
        while (current != 1) {
          this.wait();
        }
        // printFoo.run() outputs "foo". Do not change or remove this line.
        printFoo.run();
        current = 2;
        this.notify();
      }
    }
  }

  public void bar(Runnable printBar) throws InterruptedException {
    synchronized (this) {
      for (int i = 0; i < n; i++) {
        while (current != 2) {
          this.wait();
        }
        // printBar.run() outputs "bar". Do not change or remove this line.
        printBar.run();
        current = 1;
        this.notify();
      }
    }
  }
}