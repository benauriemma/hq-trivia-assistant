import java.io.IOException;

public class TextSearcher {
	
	public SearchResponse search(String searchTerm) throws IOException {
		SearchRequest request = new SearchRequest(searchTerm);
		return request.make();
	}
	
	public SearchResponse search(String searchTerm, String exclusionTerm) throws IOException {
		SearchRequest request = new SearchRequest(searchTerm, exclusionTerm);
		return request.make();
	}

}
