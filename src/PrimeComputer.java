/**
 * Computes Prime numbers.
 */
public class PrimeComputer implements Runnable {

    /**
     * The id is needed to split the numbers up so that every thread only checks a part of all numbers.
     */
    private final int ID;

    public PrimeComputer(int id) {
        this.ID = id;
    }

    // https://www.geeksforgeeks.org/java-program-to-check-if-a-number-is-prime-or-not/
    //
    //Checks if a number is a Prime.
    //Simpler checks could be implemented too, this one has a good complexity (O(sqrt(n))
    private static boolean isPrime(int n) {
            // Corner cases
            if (n <= 1)
                return false;
            if (n <= 3)
                return true;

            // This is checked so that we can skip
            // middle five numbers in below loop
            if (n % 2 == 0 || n % 3 == 0)
                return false;

            for (int i = 5; i * i <= n; i = i + 6)
                if (n % i == 0 || n % (i + 2) == 0)
                    return false;

            return true;
    }

    //This is the what every Thread will do.
    //We run up to the MAX-Number and check if it is a prime number (We only check numbers that the thread is "responsible" for);
    @Override
    public void run() {
        for (int number = 0; number < Main.MAX; number++) {
            if (number % Main.NUMBER_OF_THREADS == ID) { //Checks if this thread is responsible for the Number
                if(isPrime(number)) {
                    Main.primeNumbers.add(number);
                    System.out.println("Thread Nr." + this.ID + " found a Prime: " + number);
                }
            }

        }
    }
}
