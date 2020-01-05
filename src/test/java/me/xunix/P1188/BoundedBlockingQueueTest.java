package me.xunix.P1188;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoundedBlockingQueueTest {

  public static void main(String[] args) throws InterruptedException {
    BoundedBlockingQueue queue = new BoundedBlockingQueue(2);

    ExecutorService executor = Executors.newFixedThreadPool(2);


    executor.execute(() -> {
      try {
        queue.enqueue(1);
        queue.enqueue(0);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    executor.execute(() -> {
      try {
        int ele;
        ele = queue.dequeue();
        System.out.println(ele);

        ele = queue.dequeue();
        System.out.println(ele);

        ele = queue.dequeue();
        System.out.println(ele);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    executor.shutdown();
  }
}
