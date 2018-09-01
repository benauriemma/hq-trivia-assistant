import java.io.IOException;

public class TextSearcher {
	
	public SearchResponse search(String searchTerm) throws IOException {
		SearchRequest request = new SearchRequest(searchTerm);
		return request.make();
	}

}
