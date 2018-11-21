package client;

import java.util.HashMap;
import java.util.Map;

import helper.RemoteFileLineFetcher;
import helper.ParallelJobsManager;
import utility.HashMapUtility;

public class ClientPresenter {
	private ClientView view;
	private ParallelJobsManager pManager = new ParallelJobsManager();
	private HashMap<String, Integer> server1WordCountMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> server2WordCountMap = new HashMap<String, Integer>();
	private boolean firstJobIsDone = false;
	private boolean secondJobIsDone = false;
	static final String server1Name = "Server 1";
	static final String server2Name = "Server 2";

	public ClientPresenter(ClientView view) {
		this.view = view;
	}
	
	public void present() {
		pManager.execute(new Runnable() {
		    @Override
		    public void run() {
		    	RemoteFileLineFetcher server = new RemoteFileLineFetcher(9876);
				String line = server.getNextLine();
				while (line != null) {

					updateServer1WordsHashmapWithString(line);
					line = server.getNextLine();
				}
				concludeFirstJob();

		    }
		}).inParallelTo(new Runnable() {
		    @Override
		    public void run() {
		    	RemoteFileLineFetcher server = new RemoteFileLineFetcher(9877);
				String line = server.getNextLine();
				while (line != null) {
					updateServer2WordsHashmapWithString(line);
					line = server.getNextLine();
				}
				concludeSecondJob();
		    }
		});
	}
	private void updateServer1WordsHashmapWithString(String line) {
		if (line.length() > 0) {
			String[] words = line.split(" ");
			for (int i = 0;i < words.length;i++) {
				if (server1WordCountMap.get(words[i]) == null) {
					server1WordCountMap.put(words[i], 1);
				}else {
					server1WordCountMap.put(words[i], (int)server1WordCountMap.get(words[i])+1 );
				}
			}
		}
	}
	private void updateServer2WordsHashmapWithString(String line) {
		if (line.length() > 0) {
			String[] words = line.split(" ");
			for (int i = 0;i < words.length;i++) {
				if (server2WordCountMap.get(words[i]) == null) {
					server2WordCountMap.put(words[i], 1);
				}else {
					server2WordCountMap.put(words[i], (int)server2WordCountMap.get(words[i])+1 );
				}
			}
		}
	}
	private void concludeFirstJob() {
		firstJobIsDone = true;

		Map<String, Integer> sortedFilteredMap = HashMapUtility.getTheFirst(5, HashMapUtility.sortByValueDesc(server1WordCountMap));

		String[] words = sortedFilteredMap.keySet().toArray(new String[0]);
		Integer[] wordsCount = sortedFilteredMap.values().toArray(new Integer[0]);
		view.topWordsFetched(server1Name, words, wordsCount);
		
		if (secondJobIsDone) {
			concludeAll();
		}
	}
	private void concludeSecondJob() {
		secondJobIsDone = true;
		
		Map<String, Integer> sortedFilteredMap = HashMapUtility.getTheFirst(5, HashMapUtility.sortByValueDesc(server2WordCountMap));

		String[] words = sortedFilteredMap.keySet().toArray(new String[0]);
		Integer[] wordsCount = sortedFilteredMap.values().toArray(new Integer[0]);
		view.topWordsFetched(server2Name, words, wordsCount);
		
		if (firstJobIsDone) {
			concludeAll();
		}
	}
	private void concludeAll() {
		pManager.stop();
	}
}
