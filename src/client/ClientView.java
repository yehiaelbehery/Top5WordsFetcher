package client;

import java.util.Scanner;

public class ClientView {
	 
	public ClientView() {
		/**
		 * Delegate the presenter to do all the testable logic stuff
		 */

	    System.out.println("Please hold while we summon the data from the servers...");
		ClientPresenter presenter = new ClientPresenter(this);
		presenter.present();
	}
	
	/**
	 * Return the cooked data for the view to display
	 */
	public void topWordsFetched(String[] words, Integer[] wordsCount) {
		
		if (words.length == 0) {
			noWordsFetched();
		}else {
		    System.out.println("The top "+words.length+" words across the two files:"/*+serverName*/);
			for (int i = 0;i < words.length;i++) {
			    System.out.println(words[i] + " (" + wordsCount[i] +")");
			}
		}
	}
	public void noWordsFetched() {
	    System.out.println("Couldn't fetch any words");
	}
	public void displayError(String error, String details) {
    	System.out.println(error);
    	promptForMoreDetails(details);
    }
    private void promptForMoreDetails(String details) {
    	System.out.println("Type anything for more details...");
	    Scanner scan = new Scanner(System.in);
	    scan.nextLine();
	    System.err.println(details);
	    scan.close();
    }
		
}
