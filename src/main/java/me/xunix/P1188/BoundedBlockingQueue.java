package me.xunix.P1188;

import java.util.LinkedList;

class BoundedBlockingQueue {
  private int capacity;
  private final LinkedList<Integer> list;


  public BoundedBlockingQueue(int capacity) {
    this.capacity = capacity;
    this.list = new LinkedList<>();
  }

  public void enqueue(int element) throws InterruptedException {
    synchronized (this.list) {
      while (this.list.size() >= this.capacity) {
        this.list.wait();
      }
      this.list.add(element);
      this.list.notifyAll();
    }
  }

  public int dequeue() throws InterruptedException {
    synchronized (this.list) {
      while (this.list.size() == 0) {
        this.list.wait();
      }
      int last = this.list.getFirst();
      this.list.removeFirst();
      this.list.notifyAll();
      return last;
    }
  }

  public int size() {
    synchronized (this.list) {
      return this.list.size();
    }
  }
}
