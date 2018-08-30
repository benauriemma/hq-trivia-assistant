import java.io.IOException;

public class TextSearcher {
	
	private String myApiKey;
	
	public TextSearcher(String apiKey) {
		this.myApiKey = apiKey;
	}
	
	public SearchResponse search(String searchTerm) throws IOException {
		SearchRequest request = new SearchRequest(myApiKey);
		return request.make(searchTerm);
	}

}
