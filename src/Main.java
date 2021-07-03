import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    //Maximum Number to which we look for Prime nubmers
    public static int MAX = 1000000;

    //We save all found Primes in this list.
    //A normal ArrayList is not thread safe! We need to use a synchronzied List to be safe of race conditions.
    public static final List<Integer> primeNumbers = Collections.synchronizedList(new ArrayList<>());

    public static final int NUMBER_OF_THREADS = 8;

    public static void main(String[] args)  {

        Thread [] allThreads = new Thread[NUMBER_OF_THREADS];

        //Create and start Threads.

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            PrimeComputer pc = new PrimeComputer(i); //Create the "Job" of the Thread. Every Object of a Class which implements the Runnable Interface is possible here.

            allThreads[i] = new Thread(pc); //Give the Thread its Job.

            allThreads[i].start();
        }


        //Waiting for all Threads to finish.
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            try {
                allThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Found: " + primeNumbers.size() + " primes.");

//        for (int prime : primeNumbers) {
//            System.out.println(prime);
//        }
    }
}
