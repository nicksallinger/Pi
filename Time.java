package Time;

import java.util.*;

public class Time {
	
	public Thread[] threads;
	
	//x^2 + y^2 < 1, then that point is "inside" the circle
	
	public void doThreading(long numThreads, long numIterations){
		threads = new Thread[(int) numThreads];
		long iterationsPerThread = numIterations/numThreads;
		
		
		for(int i = 0; i < numThreads; i++){
			threads[i] = new Thread();
		}
		
		
		for(int i = 0; i < numThreads; i++){
			threads[i].start();
		}
	}

    public static void main(String[] args){
    	long numThreads = Long.parseLong(args[0]);
    	long numIterations = Long.parseLong(args[1]);
    	//long iterationsPerThread = numIterations/numThreads;
    			
    	Time time = new Time();

    	System.out.println("Total = ");
    	System.out.println("Inside = ");
    	System.out.println("Ratio = ");
    	System.out.println("Pi = ");
    	System.out.println();
    	System.out.println("real = ");
    	System.out.println("user = ");
    	System.out.println("sys = ");
    }
}