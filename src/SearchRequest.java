import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class SearchRequest {

	private static String BASE_URL = "https://www.googleapis.com/customsearch/v1?";
	private String myApiKey;
	
	private static String FIELD_LIMITER = "&fields=items(title,snippet)";
	private static String REQUEST_METHOD = "GET";

	public SearchRequest(String apiKey) {
		this.myApiKey = apiKey;
	} 

	public SearchResponse make(String searchTerm) throws IOException {
		URL url = new URL(BASE_URL+"key="+myApiKey+"&cx="+PersonalInfo.SEARCH_ENGINE_ID+"&q="+searchTerm+FIELD_LIMITER);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(REQUEST_METHOD);
		DataInputStream input = new DataInputStream(conn.getInputStream());
		String response = new String(input.readAllBytes());
		int responseCode = conn.getResponseCode();
		String responseMessage = conn.getResponseMessage();
		return new Gson().fromJson(response, SearchResponse.class);
	}

}
