package me.xunix.P1226;

class DiningPhilosophers {
  final Object[] forks;

  public DiningPhilosophers() {
    this.forks = new Object[5];
    for (int i = 0; i < 5; i++) {
      this.forks[i] = new Object();
    }
  }

  // call the run() method of any runnable to execute its code
  public void wantsToEat(int philosopher,
                         Runnable pickLeftFork,
                         Runnable pickRightFork,
                         Runnable eat,
                         Runnable putLeftFork,
                         Runnable putRightFork) throws InterruptedException {
    int left;
    int right;
    if (philosopher == 4) {
      left = 0;
      right = 4;
      synchronized (this.forks[left]) {
        pickLeftFork.run();
        synchronized (this.forks[right]) {
          pickRightFork.run();
          eat.run();
          putRightFork.run();
        }
        putLeftFork.run();
      }
    } else {
      left = philosopher + 1;
      right = philosopher;
      synchronized (this.forks[right]) {
        pickRightFork.run();
        synchronized (this.forks[left]) {
          pickLeftFork.run();
          eat.run();
          putLeftFork.run();
        }
        putRightFork.run();
      }
    }
  }
}
