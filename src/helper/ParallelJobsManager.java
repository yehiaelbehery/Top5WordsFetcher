package helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelJobsManager {
	ExecutorService executor;
	Runnable executionHandler;
	Runnable inParallelHandler;
	
	public  ParallelJobsManager() {

		executor = Executors.newFixedThreadPool(2);
	}

	public ParallelJobsManager execute(Runnable executionHandler) {
		
    	executor.submit(() -> {
    		executionHandler.run();
    	});
		return this;
	}
	public ParallelJobsManager inParallelTo(Runnable inParallelHandler) {
		

    	executor.submit(() -> {
    		inParallelHandler.run();
    	});
    	
		return this;
	}
	
	public void stop() {
		try {
    	    System.out.println("attempt to shutdown executor");
    	    executor.shutdown();
    	    executor.awaitTermination(5, TimeUnit.SECONDS);
    	}
    	catch (InterruptedException e) {
    	    System.err.println("tasks interrupted");
    	}
    	finally {
    	    if (!executor.isTerminated()) {
//    	        System.err.println("cancel non-finished tasks");
    	    }
    	    executor.shutdownNow();
    	    System.out.println("shutdown finished");
    	}
	}
}
