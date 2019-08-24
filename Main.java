import java.util.*;

class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!\nThis is a Java implementation of the Producer-Consumer Problem in OS.\nAuthor : Amitrajit Bose\n\n\n");
    List<Integer> taskQueue = new ArrayList<Integer>();
    int MAX_CAPACITY = 5; // Queue Size
    Thread tp = new Thread(new Producer(taskQueue, MAX_CAPACITY), "Producer");
    Thread tc = new Thread(new Consumer(taskQueue), "Consumer");
    tp.start();
    tc.start();
  }
}