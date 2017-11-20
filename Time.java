
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Time {
	
	 //A long value that may be updated atomically.
	//x^2 + y^2 < 1, then that point is "inside" the circle
	
	public long total, inside, ratio, piCalc, real, sys, user;
	public static AtomicLong insideCircleTotal = new AtomicLong(0);
	public Thread[] threads;
	public static long start, end;
	
	public void doThreading(long numThreads, long numIterations){
		long numIterationsPerThread = numIterations/numThreads;
		threads = new Thread[(int) numThreads];
		
		

		for (int i = 0; i < numThreads; i++) {
		    threads[i] = new Thread(() -> {	 //stabby
		    	//each thread has own iteration number and how many times 
		    	//it was in the circle
		    	int insideCircle = 0;
			    int iterationNumber = 0;
				    
			    while (iterationNumber < numIterationsPerThread) 
			    {
					double x = ThreadLocalRandom.current().nextDouble(1);
					double y = ThreadLocalRandom.current().nextDouble(1);
					
					if ((Math.pow(x, 2) + Math.pow(y, 2)) < 1) {
						//inside the circle
					    insideCircle++;
					    
					} else{}
					
					iterationNumber++;
			    }				    
				  insideCircleTotal.addAndGet(insideCircle);
			});
		}
		
		for (int i = 0; i < numThreads; i++) {
		    threads[i].start();
		}
		for (int i = 0; i < numThreads; i++) {
		    try {
			threads[i].join();
		    } 
		    catch (Exception ex) 
		    { 
		    	//do nothing
		    }
		}
		//long ratio = (long) count.get()/numIterations;
		double piCalc = (double) 4*insideCircleTotal.get()/numIterations;
		
		end = System.nanoTime();
		double seconds = (double) (end-start) / 1000000000.000;

		
    	System.out.println("Total = " + numIterations);
    	System.out.println("Inside = " + insideCircleTotal.get());
    	System.out.println("Ratio = " + (double) insideCircleTotal.get()/numIterations);
    	System.out.println("Pi = " + piCalc);
    	System.out.println("Time = " + seconds + " sec");
	}

    public static void main(String[] args){
    	start = System.nanoTime();
    	long numThreads = Long.parseLong(args[0]);
    	long numIterations = Long.parseLong(args[1]); 
    	
    	Time time = new Time();
    	time.doThreading(numThreads, numIterations);
    }
}
