package com.benauriemma.search;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.benauriemma.constants.PersonalInfo;
import com.google.gson.Gson;

/**
 * Class representing a GET request to Google's Custom Search Engine
 * 
 * @author benauriemma
 *
 */
public class SearchRequest {

	private static String BASE_URL = "https://www.googleapis.com/customsearch/v1?";
	private static String FIELD_LIMITER = "&fields=items(title,snippet),searchInformation(totalResults)";
	private static String REQUEST_METHOD = "GET";

	private String mySearchTerm;
	private String myExclusionTerm;

	public SearchRequest(String searchTerm) {
		this(searchTerm, "");
	}
	
	public SearchRequest(String searchTerm, String exclusionTerm) {
		this.mySearchTerm = searchTerm;
		this.myExclusionTerm = exclusionTerm;
	}

	/**
	 * Make this request to Custom Search with the terms passed in the constructor
	 * 
	 * @return the response from Google as a SearchResponse object
	 * @throws IOException
	 */
	public SearchResponse make() throws IOException {
		URL url = new URL(
				BASE_URL+
				"key="+
				PersonalInfo.API_KEY+
				"&cx="+
				PersonalInfo.SEARCH_ENGINE_ID+
				"&q="+
				mySearchTerm.replace(" ", "+")+
				"&excludeTerms="+
				myExclusionTerm.replace(" ", "+")+
				FIELD_LIMITER
				);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(REQUEST_METHOD);
		DataInputStream input = new DataInputStream(conn.getInputStream());
		String response = new String(input.readAllBytes());
		int responseCode = conn.getResponseCode();
		String responseMessage = conn.getResponseMessage();
		return new Gson().fromJson(response, SearchResponse.class);
	}

}
