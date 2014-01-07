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

public class Example6 implements Runnable {

    public static int[] numbers;
    public static AtomicIntegerArray readlock;
    public static AtomicInteger writelock;
    
    public static void initializeNumbers(int x){
        numbers = new int[x];
        for(int i=0; i<x; i++)
            numbers[i] = i+1;
    }
    
    public static void initializeReadLocks(int n){
        readlock = new AtomicIntegerArray(n);
        for(int i=0; i<n; i++)
            readlock.set(i, 0);
    }
    
    private int id;
    public int arg;
    public int result;
    public int sum;
    
    // Constructor for an "Example" object.  Fields in the object can be
    // used to pass values to/from the thread when it is started and
    // when it finishes.
    Example6(int i, int arg) {
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
    
    void acquireRead(){
        do{
            readlock.set(id, 1);
            if(writelock.get()==0){
                break;
            }
            readlock.set(id, 0);
            while(writelock.get()>0){}
        }while(true);
    }
    
    void releaseRead(){
        readlock.set(id, 0);
    }
    
    void acquireWrite(){
        do{
            if(writelock.compareAndSet(0, 1)){
                int readsum = writelock.get();
                for(int i=0; i<readlock.length(); i++)
                    readsum += readlock.get(i);

                if(readsum == 1)
                    break;
            }
        }while(true);
      
    }
    
    void releaseWrite(){
        writelock.getAndDecrement();
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
            for(int j=0; j<100000; j++){
                sumNumbers();
            }
        }
    }

    // Main function
    public static void main(String args[]) {
        // Start a new thread, and then wait for it to complete:
        System.out.println("Start ");
        int n = Integer.parseInt(args[0]);
        int x = Integer.parseInt(args[1]);

        Thread[] threads = new Thread[n];
        
        initializeNumbers(x);
        initializeReadLocks(n);
        writelock = new AtomicInteger(0);

        for(int i=0; i<n; i++){
            Example6 e = new Example6(i, 10);
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