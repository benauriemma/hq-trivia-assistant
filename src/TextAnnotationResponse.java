import java.util.List;

public class TextAnnotationResponse {
	
	private List<Response> responses;
	
	public String getAllText() {
		return responses.get(0).getAnnotations().get(0).getText().trim();
	}

}

class Response {
	private List<Text> textAnnotations;
	public List<Text> getAnnotations() {return textAnnotations;};
}

class Text {
	private String description;
	protected String getText() {return description.replace("\n", " ");};
}
