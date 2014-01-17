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

public class Example7 implements Runnable {

    public static int[] numbers;
    public static AtomicInteger version;
    public static AtomicBoolean writelock;
    
    public static void initializeNumbers(int x){
        numbers = new int[x];
        for(int i=0; i<x; i++)
            numbers[i] = i+1;
    }
    public static void initializeLocks(){
        version = new AtomicInteger(0);
        writelock = new AtomicBoolean(false);
    }
    
    private int id;
    public int arg;
    public int result;
    public volatile int sum;
    public volatile int temp;
    
    // Constructor for an "Example" object.  Fields in the object can be
    // used to pass values to/from the thread when it is started and
    // when it finishes.
    Example7(int i, int arg) {
        this.id = i;
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
    
    int acquireRead(){
        while(version.get() % 2 ==1){}
        return version.get();
    }
    
    void acquireWrite(){
        do{
            if(!writelock.get() && writelock.compareAndSet(false, true)){
                version.getAndIncrement();
                break;
            }
        }while(true);
    }
    
    void releaseWrite(){
        version.getAndIncrement();
        writelock.set(false);
    }
    
    void sumNumbers(){
        int v;
        do{
            temp = 0;
            v = acquireRead();
            for(int i=0; i<numbers.length; i++){
                temp += numbers[i];
            }
        } while(v!=version.get());
        sum += temp;
    }
    
    void loopSum(int arg){
        for(int i=0; i<arg; i++){
            for(int j=0; j<5000; j++){
                sumNumbers();
            }
        }
    }

    // Main function
    public static void main(String args[]) {
        // Start a new thread, and then wait for it to complete:
        System.out.println("Start Example 7");
        int n = Integer.parseInt(args[0]);
        int x = Integer.parseInt(args[1]);
        int d = Integer.parseInt(args[2]);

        Thread[] threads = new Thread[n];
        
        initializeNumbers(x);
        initializeLocks();

        for(int i=0; i<n; i++){
            Example7 e = new Example7(i, d);
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