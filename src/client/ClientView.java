package client;

public class ClientView {
	 
	public ClientView() {
		/**
		 * Delegate the presenter to do all the testable logic stuff
		 */
		ClientPresenter presenter = new ClientPresenter(this);
		presenter.present();
	}
	
	/**
	 * Return the cooked data for the view to display
	 */
	public void topWordsFetched(String serverName, String[] words, Integer[] wordsCount) {
		
		if (words.length == 0) {
		    System.out.println("Couldn't fetch any words from "+serverName);
		}else {
		    System.out.println("The top "+words.length+" words from "+serverName);
			for (int i = 0;i < words.length;i++) {
			    System.out.println(words[i] + " (" + wordsCount[i] +")");
			}
		}
	}
}
