package client;

import java.util.HashMap;
import java.util.Map;

import helper.RemoteFileLineFetcher;
import helper.ParallelJobsManager;
import utility.HashMapUtility;

public class ClientPresenter {
	private ClientView view;
	private ParallelJobsManager pManager = new ParallelJobsManager();
	
	private HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
	
	private boolean firstJobIsDone = false;
	private boolean secondJobIsDone = false;
	
//	static final String server1Name = "Server 1";
//	static final String server2Name = "Server 2";

	public ClientPresenter(ClientView view) {
		this.view = view;
	}
	
	public void present() {
		/**
		 * Run the multithreading wrapper to run two processes in parallel
		 */
		pManager.execute(new Runnable() {
		    @Override
		    public void run() {
		    	/**
		    	 * Connect to the server with the designated port
		    	 */
		    	RemoteFileLineFetcher server1 = new RemoteFileLineFetcher(9876);
		    	/**
		    	 * Keep calling the next line until it's null
		    	 */
				String line = server1.getNextLine();
				while (line != null) {

					updateWordsHashmapWithString(line);
					line = server1.getNextLine();
				}
				concludeFirstJob();
				//Whoever finished first only displays error
				if (server1.encounteredAnError && secondJobIsDone == false) { 

					view.displayError(server1.errorMessage, server1.errorDetails);
				}

		    }
		}).inParallelTo(new Runnable() {
		    @Override
		    public void run() {
		    	/**
		    	 * Connect to the server with the designated port
		    	 */
		    	RemoteFileLineFetcher server2 = new RemoteFileLineFetcher(9877);
		    	/**
		    	 * Keep calling the next line until it's null
		    	 */
				String line = server2.getNextLine();
				while (line != null) {

					updateWordsHashmapWithString(line);
					line = server2.getNextLine();
				}

				concludeSecondJob();
				//Whoever finished first only displays error
				if (server2.encounteredAnError && firstJobIsDone == false) { 
					view.displayError(server2.errorMessage, server2.errorDetails);
				}
				
		    }
		});
	}
	
	private void updateWordsHashmapWithString(String line) {
		if (line.length() > 0) {
			/**
	    	*  Server 1 & 2 concurrently
	    	*  Split the line to words separated by the space character and update the count to the hashap
	    	*/ 
			String[] words = line.split(" ");
			for (int i = 0;i < words.length;i++) {
				synchronized (this) {
					if (wordCountMap.get(words[i]) == null) {
						wordCountMap.put(words[i], 1);
					}else {
						wordCountMap.put(words[i], (int)wordCountMap.get(words[i])+1 );
					}
				}
			}
		}
	}
	private void concludeFirstJob() {
		firstJobIsDone = true;
		
		if (secondJobIsDone) {
			//If the other file is done close the thread manager
			concludeAll();
		}
	}
	private void concludeSecondJob() {
		secondJobIsDone = true;
		
		if (firstJobIsDone) {
			//If the other file is done close the thread manager
			concludeAll();
		}
	}
	private void concludeAll() {
		pManager.stop();
		
		Map<String, Integer> sortedFilteredMap = HashMapUtility.getTheFirst(5, HashMapUtility.sortByValueDesc(wordCountMap));

		String[] words = sortedFilteredMap.keySet().toArray(new String[0]);
		Integer[] wordsCount = sortedFilteredMap.values().toArray(new Integer[0]);
		
		/**
		 * 
    	 * Call the view handler with the final data
    	 */
		view.topWordsFetched(words, wordsCount);
	}
}
