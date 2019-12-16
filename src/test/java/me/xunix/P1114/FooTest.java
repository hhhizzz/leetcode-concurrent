package me.xunix.P1114;

public class FooTest {
  public static void main(String[] args) throws InterruptedException {
    final Foo foo = new Foo();

    final Runnable printFirst = new Runnable() {
      public void run() {
        System.out.println("1 ");
      }
    };

    final Runnable printSecond = new Runnable() {
      public void run() {
        System.out.println("2 ");
      }
    };

    final Runnable printThird = new Runnable() {
      public void run() {
        System.out.println("3 ");
      }
    };

    Thread t1 = new Thread(new Runnable() {
      public void run() {
        try {
          foo.first(printFirst);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      public void run() {
        try {
          foo.second(printSecond);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t3 = new Thread(new Runnable() {
      public void run() {
        try {
          foo.third(printThird);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();
    t3.start();

    t1.join();
    t2.join();
    t3.join();
  }
}
