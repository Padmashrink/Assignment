import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class CounterThread extends Thread {
	//Global counter variable and the three hashset collection for three separate threads
    private volatile static int counter;
    static HashSet<Integer> thread1 = new HashSet<Integer>();
    static HashSet<Integer> thread2 = new HashSet<Integer>();
    static HashSet<Integer> thread3 = new HashSet<Integer>();
    

    public CounterThread(String name) {
        super(name);
    }

    //Method to run the tread
    public void run() {
    	//Perform the action till count is less than or equal to 100
    	while(counter <= 100){
    		//Stop all threads when count is greater than 100
    		if(counter > 100){
    			Thread.currentThread().stop();
    		}
    		//Print the thread name and the counter value
            System.out.print("Thread " + getName() + " " +counter + "\n");
            
            if("A".equals(Thread.currentThread().getName())){	
            	thread1.add(counter);
            	counter++;
            }
            else if("B".equals(Thread.currentThread().getName())){
            	thread2.add(counter);
            	counter++;
            }
            else if("C".equals(Thread.currentThread().getName()))
            {
            	thread3.add(counter);
            	counter++;
            }
    	} 
    }
    
    public static void main(String[] args) throws InterruptedException {
    	//Creating three threads
        CounterThread threadOne = new CounterThread("A");
        CounterThread threadTwo = new CounterThread("B");
        CounterThread threadThree = new CounterThread("C");

        //Starting the three threads
        threadOne.start();
        threadTwo.start();
        threadThree.start();
        
        Thread.sleep(500);
        //Sorting the collection of individual thread and printing them
        List<Integer> list_thread1 = new ArrayList<Integer>(thread1);
        Collections.sort(list_thread1);
        System.out.println("Collection of thread - 1 : "+ list_thread1);
        List<Integer> list_thread2 = new ArrayList<Integer>(thread2);
        Collections.sort(list_thread2);
        System.out.println("Collection of thread - 2 : "+ list_thread2);
        List<Integer> list_thread3 = new ArrayList<Integer>(thread3);
        Collections.sort(list_thread3);
        System.out.println("Collection of thread - 3 : "+ list_thread3);
    }
}