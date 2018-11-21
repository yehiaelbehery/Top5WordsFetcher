package client;

public class ClientView {
	 
	public ClientView() {
		ClientPresenter presenter = new ClientPresenter(this);
		presenter.present();
		
		/*String[] words = {"a", "b", "c", "d", "e"};
		Integer[] wordsCount = {1, 2, 3, 4, 5};
		
		this.topWordsFetched("Server1", words, wordsCount);*/
	}
	
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
