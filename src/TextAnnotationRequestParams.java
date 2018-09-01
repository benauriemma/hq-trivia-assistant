import java.util.ArrayList;
import java.util.List;

public class TextAnnotationRequestParams {
	private List<Request> requests;

	public TextAnnotationRequestParams(String imageEncoding) {
		requests = new ArrayList<Request>();
		requests.add(new Request(imageEncoding));
	}
}

class Request {
	private ImageObj image;
	private List<FeatureObj> features;

	public Request(String imageEncoding) {
		image = new ImageObj(imageEncoding);
		features = new ArrayList<FeatureObj>();
		features.add(new FeatureObj());
	}
}

class ImageObj {
	private String content;

	ImageObj(String imageEncoding) {
		content = imageEncoding;
	}
}

class FeatureObj {
	private String type = "TEXT_DETECTION";
}

