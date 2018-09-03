import java.util.List;

/**
 * Class representing a response from the Vision API
 * 
 * @author benauriemma
 *
 */
public class TextAnnotationResponse {
	
	private List<Response> responses;
	
	public String getAllText() {
		return responses.get(0).getAnnotations().get(0).getText().trim();
	}

}

/**
 * Class representing the "response" json object
 */
class Response {
	private List<Text> textAnnotations;
	public List<Text> getAnnotations() {return textAnnotations;};
}

/**
 * Class representing the "text" json object
 */
class Text {
	private String description;
	protected String getText() {return description.replace("\n", " ");};
}
