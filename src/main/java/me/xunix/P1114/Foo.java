package me.xunix.P1114;

class Foo {
  Integer current = 1;

  public Foo() {
  }

  public void first(Runnable printFirst) throws InterruptedException {
    synchronized (this) {
      while (this.current != 1) {
        this.wait();
      }
      // printFirst.run() outputs "first". Do not change or remove this line.
      printFirst.run();
      current = 2;
      this.notifyAll();
    }

  }

  public void second(Runnable printSecond) throws InterruptedException {
    synchronized (this) {
      while (this.current != 2) {
        this.wait();
      }
      // printSecond.run() outputs "second". Do not change or remove this line.
      printSecond.run();
      current = 3;
      this.notifyAll();
    }
  }

  public void third(Runnable printThird) throws InterruptedException {
    synchronized (this) {
      while (this.current != 3) {
        this.wait();
      }
      // printThird.run() outputs "third". Do not change or remove this line.
      printThird.run();
      current = 1;
      this.notifyAll();
    }
  }
}
