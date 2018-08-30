import java.util.List;
import java.util.stream.Collectors;

public class SearchResponse {
	private List<WebsiteSnippet> items;
	
	public List<String> getTitles() {
		return items.stream().map(site -> site.getTitle()).collect(Collectors.toList());
	}
	
	public List<String> getSnippets() {
		return items.stream().map(site -> site.getSnippet()).collect(Collectors.toList());
	}
}

class WebsiteSnippet {
	private String title;
	private String snippet;
	protected String getTitle() {return title;};
	protected String getSnippet() {return snippet;};
}