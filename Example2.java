/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author haikalpribadi
 */

import java.util.concurrent.atomic.*;

public class Example2 implements Runnable {

    // Delay function waits a variable time controlled by "d".  The function
    // writes to a per-object volatile field -- this aims to prevent the compiler
    // from optimizing the delay away completely.

    volatile int temp;
    void delay(int arg) {
        for (int i = 0; i < arg; i++) {
            for (int j = 0; j < 1000000; j++) {
                this.temp += i + j;
            }
        }
    }

    // Constructor for an "Example2" object.  Fields in the object can be
    // used to pass values to/from the thread when it is started and
    // when it finishes.

    int arg;
    int result;

    Example2(int arg) {
        this.arg = arg;
    }

    // Example2 thread function.  This just delays for a little while,
    // controlled by the parameter passed when the thread is started.

    public void run() {
        System.out.println("Thread started arg=" + arg);
        delay(arg);
        result = 42;
        System.out.println("Thread done result=" + result);
    }

    // Shared variable for use with example atomic compare and swap
    // operations (ai.compareAndSet in this example).

    static AtomicInteger ai = new AtomicInteger(0);

    // Main function

    public static void main(String args[]) {

    // Start a new thread, and then wait for it to complete:

    System.out.println("Start Example 2");
    int n = Integer.parseInt(args[0]);
    int delay = Integer.parseInt(args[1]);
    Thread[] threads = new Thread[n];

    for(int i=0; i<n; i++){
        Example2 e = new Example2(delay);
        threads[i] = new Thread(e);
        threads[i].start();
    }
    try {
        for(int i=0; i<n; i++){
            threads[i].join();
        }
        //System.out.println("Joined with thread, ret=" + e1.result);
    } catch (InterruptedException ie) {
        System.out.println("Caught " + ie);
    }
    
    System.out.println("End");

    // Example2 compare and swap operations
    /*
    boolean s;
    System.out.println("ai=" + ai);
    s = ai.compareAndSet(0, 1);
    System.out.println("ai=" + ai + " s=" + s);
    s = ai.compareAndSet(0, 2);
    System.out.println("ai=" + ai + " s=" + s);
    s = ai.compareAndSet(1, 2);
    System.out.println("ai=" + ai + " s=" + s);
    */
    }
}
