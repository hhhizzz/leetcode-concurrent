package me.xunix.P1115;

public class FooBarTest {
  public static void main(String[] args) throws InterruptedException {

    int n = 1000;
    FooBar fooBar = new FooBar(n);

    StringBuilder sb = new StringBuilder();

    Runnable printFoo = () -> {
      sb.append("foo");
    };
    Runnable printBar = () -> {
      sb.append("bar");
    };

    Thread t1 = new Thread(() -> {
      try {
        fooBar.foo(printFoo);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    Thread t2 = new Thread(() -> {
      try {
        fooBar.bar(printBar);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    t1.start();
    t2.start();

    t1.join();
    t2.join();

    String result = sb.toString();
    System.out.println(result);

    String expected = "";
    for (int i = 0; i < n; i++) {
      expected = expected.concat("foobar");
    }

    if (!result.equals(expected)) {
      throw new RuntimeException("the answer is not right");
    }

  }
}
