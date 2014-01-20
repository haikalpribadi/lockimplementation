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

public class Example3 implements Runnable {

    public static int[] numbers;
    
    public static void initializeNumbers(int x){
        numbers = new int[x];
        for(int i=0; i<x; i++)
            numbers[i] = i+1;
    }
    
    public int arg;
    public int result;
    public volatile int sum;
    
    // Constructor for an "Example" object.  Fields in the object can be
    // used to pass values to/from the thread when it is started and
    // when it finishes.
    Example3(int arg) {
        this.arg = arg;
    }

    // Example thread function.  This just delays for a little while,
    // controlled by the parameter passed when the thread is started.
    public void run() {
        System.out.println("Thread started arg=" + arg);
        loopSum(arg);
        result = sum;
        System.out.println("Thread done result=" + result);
    }

    synchronized void sumNumbers(){
        for(int i=0; i<numbers.length; i++){
            sum += numbers[i];
        }
    }
    
    void loopSum(int arg){
        for(int i=0; i<arg; i++){
            for(int j=0; j<330; j++){
                sumNumbers();
            }
        }
    }

    // Main function
    public static void main(String args[]) {
        // Start a new thread, and then wait for it to complete:
        System.out.println("Start Example 3");
        int n = Integer.parseInt(args[0]);
        int x = Integer.parseInt(args[1]);
        int d = Integer.parseInt(args[2]);

        Thread[] threads = new Thread[n];
        initializeNumbers(x);

        for(int i=0; i<n; i++){
            Example3 e = new Example3(d);
            threads[i] = new Thread(e);
            threads[i].start();
        }
        try {
            for(int i=0; i<n; i++){
                threads[i].join();
            }
        } catch (InterruptedException ie) {
            System.out.println("Caught " + ie);
        }

        System.out.println("End");
    }
}
