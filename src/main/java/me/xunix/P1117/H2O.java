package me.xunix.P1117;

import java.util.concurrent.Semaphore;

class H2O {

  private Semaphore h = new Semaphore(2);
  private Semaphore o = new Semaphore(0);

  public H2O() {

  }

  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
    h.acquire();
    releaseHydrogen.run();
    o.release();
  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {
    o.acquire(2);
    releaseOxygen.run();
    h.release(2);
  }
}
