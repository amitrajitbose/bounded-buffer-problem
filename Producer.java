import java.util.*;

class Producer implements Runnable{
  private final List<Integer> taskQueue;
  private final int MAX_CAPACITY;
  public Producer(List<Integer> sharedQueue, int size){
    this.taskQueue = sharedQueue;
    this.MAX_CAPACITY = size;
  }

  @Override
  public void run(){
    int counter = 0;
    while(true){
      try{
        produce(counter++);
      }
      catch (InterruptedException e){
        System.out.println("Interrupted Exception @ Producer Code");
        e.printStackTrace();
      }
    }
  }

  private void produce(int i) throws InterruptedException{
    synchronized(taskQueue){
      while(taskQueue.size() == MAX_CAPACITY){
        System.out.print("QUEUE OVERFLOW ");
        System.out.println(Thread.currentThread().getName() + " is waiting, size: " + taskQueue.size());
        taskQueue.wait();
      }
      Thread.sleep(1000); // wait for a second
      taskQueue.add(i); // add to task QUEUE
      System.out.println("PRODUCED: " + i);
      taskQueue.notifyAll();
    }
  }
}