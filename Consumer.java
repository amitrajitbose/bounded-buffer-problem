import java.util.*;

class Consumer implements Runnable{
  private final List<Integer> taskQueue;
  public Consumer(List<Integer> sharedQueue){
    this.taskQueue = sharedQueue;
  }

  @Override
  public void run(){
    while(true){
      try{
        consume();
      }
      catch (InterruptedException e){
        System.out.println("Interrupted Exception @ Consumer Code");
        e.printStackTrace();
      }
    }
  }

  private void consume() throws InterruptedException{
    synchronized(taskQueue){
      while(taskQueue.isEmpty()){
        System.out.print("QUEUE UNDERFLOW ");
        System.out.println(Thread.currentThread().getName() + " is waiting, size: " + taskQueue.size());
        taskQueue.wait();
      }
      Thread.sleep(1000);
      int i = (Integer)taskQueue.remove(0); // remove from front
      System.out.println("CONSUMED: "+i);
      taskQueue.notifyAll();
    }
  }
}