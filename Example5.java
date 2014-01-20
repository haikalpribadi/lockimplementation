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

public class Example5 implements Runnable {

    public static int[] numbers;
    public static AtomicInteger lock;
    
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
    Example5(int arg) {
        this.arg = arg;
        this.sum = 0;
    }
    // Example thread function.  This just delays for a little while,
    // controlled by the parameter passed when the thread is started.
    public void run() {
        System.out.println("Thread started arg=" + arg);
        loopSum(arg);
        result = sum;
        System.out.println("Thread done result=" + result);
    }
    
    void acquireRead(){
        do{
            int old = lock.get();
            if(old>=0 && lock.compareAndSet(old, old+1)){
                break;
            }
        }while(true);
    }
    
    void releaseRead(){
        lock.getAndDecrement();
    }
    
    void acquireWrite(){
        do{
           if(lock.get()==0 && lock.compareAndSet(0, -1))
               break;
        }while(true);
    }
    
    void releaseWrite(){
        lock.set(0);
    }
    
    void sumNumbers(){
        acquireRead();
        for(int i=0; i<numbers.length; i++){
            sum += numbers[i];
        }
        releaseRead();
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
        System.out.println("Start Example 5");
        int n = Integer.parseInt(args[0]);
        int x = Integer.parseInt(args[1]);
        int d = Integer.parseInt(args[2]);

        Thread[] threads = new Thread[n];
        initializeNumbers(x);
        lock = new AtomicInteger(0);

        for(int i=0; i<n; i++){
            Example5 e = new Example5(d);
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
