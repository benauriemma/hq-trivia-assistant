package com.benauriemma.search;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class representing a response from Google's Custom Search Engine
 * 
 * @author benauriemma
 *
 */
public class SearchResponse {
	private List<WebsiteSnippet> items;
	private SearchInformationObj searchInformation;
	
	/**
	 * 
	 * @return a list of titles of results that Google returned in the first page
	 */
	public List<String> getTitles() {
		return items.stream().map(site -> site.getTitle()).collect(Collectors.toList());
	}
	
	/**
	 * 
	 * @return a list of snippets from each result that google returned in the first page
	 */
	public List<String> getSnippets() {
		return items.stream().map(site -> site.getSnippet()).collect(Collectors.toList());
	}
	
	/**
	 * Note: this is the total number of results that Google found which is much larger than the number results it returned to us
	 * I.e., numberOfResutls() >>> getTitles.size()
	 * 
	 * @return the total number of results that Google found for this search
	 */
	public Integer numberOfResults() {
		return searchInformation.getTotalResults();
	}
}

/**
 * Class representing a website snippet as it is returned from Google in json
 */
class WebsiteSnippet {
	private String title;
	private String snippet;
	protected String getTitle() {return title;};
	protected String getSnippet() {return snippet;};
}

/**
 * Class representing search information as it is returned from Google in json
 */
class SearchInformationObj {
	private String totalResults;
	protected Integer getTotalResults() {return Integer.valueOf(totalResults);};
}