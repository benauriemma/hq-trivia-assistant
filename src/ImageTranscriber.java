import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTranscriber {

	public ImageTranscriber() {
		// do nothing
	}

	public String transcribe(BufferedImage bufferedImage) throws IOException {
		TextAnnotationRequest request = new TextAnnotationRequest(bufferedImage);
		return request.make().getAllText();
	}

}
