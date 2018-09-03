package com.benauriemma.search;

import java.io.IOException;

/**
 * Class that allows you to make a search request with a search term, and with or without an exclusion term
 * 
 * @author benauriemma
 *
 */
public class TextSearcher {
	
	/**
	 * Search for this search term
	 * 
	 * @param searchTerm to search for
	 * @return the results of the search
	 * @throws IOException
	 */
	public SearchResponse search(String searchTerm) throws IOException {
		SearchRequest request = new SearchRequest(searchTerm);
		return request.make();
	}
	
	/**
	 * Search for this search term, but exclude results with this exclusion term
	 * 
	 * @param searchTerm to search for
	 * @param exclusionTerm exclude results with this term
	 * @return the results of the search
	 * @throws IOException
	 */
	public SearchResponse search(String searchTerm, String exclusionTerm) throws IOException {
		SearchRequest request = new SearchRequest(searchTerm, exclusionTerm);
		return request.make();
	}

}
