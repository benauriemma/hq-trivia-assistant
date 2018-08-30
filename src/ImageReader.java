import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageReader {

	private String myApiKey;

	public ImageReader(String apiKey) {
		this.myApiKey = apiKey;
	}

	public String read(BufferedImage bufferedImage) throws IOException {
		TextAnnotationRequest request = new TextAnnotationRequest(myApiKey);
		return request.make(bufferedImage).getAllText();
	}

}
