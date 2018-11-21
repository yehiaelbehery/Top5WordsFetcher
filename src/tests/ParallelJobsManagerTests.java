package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import helper.ParallelJobsManager;

class ParallelJobsManagerTests {
	
	boolean task1Executed = false;
	boolean task2Executed = false;
	
	@Test
	void test() {
		
		ParallelJobsManager pManager = new ParallelJobsManager();
		pManager.execute(new Runnable() {
		    @Override
		    public void run() {
				
		    	executeTask1();
		    }
		}).inParallelTo(new Runnable() {
		    @Override
		    public void run() {
				executeTask2();
				
		    }
		});
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			fail(e.toString());
		}
		assertTrue(task1Executed);
		assertTrue(task2Executed);
	}
	void executeTask1() {
		task1Executed = true;
	}
	void executeTask2() {
		task2Executed = true;
	}

}
