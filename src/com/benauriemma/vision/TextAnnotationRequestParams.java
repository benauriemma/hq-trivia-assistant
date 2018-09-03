package com.benauriemma.vision;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the parameters for a POST request to the Vision API
 * Used to construct the json object
 * 
 * @author benauriemma
 *
 */
public class TextAnnotationRequestParams {
	private List<Request> requests;

	public TextAnnotationRequestParams(String imageEncoding) {
		requests = new ArrayList<Request>();
		requests.add(new Request(imageEncoding));
	}
}

/**
 * Class representing the "request" json object
 */
class Request {
	private ImageObj image;
	private List<FeatureObj> features;

	public Request(String imageEncoding) {
		image = new ImageObj(imageEncoding);
		features = new ArrayList<FeatureObj>();
		features.add(new FeatureObj());
	}
}

/**
 * Class representing the "image" json object
 */
class ImageObj {
	private String content;

	ImageObj(String imageEncoding) {
		content = imageEncoding;
	}
}

/**
 * Class representing the "feature" json object
 */
class FeatureObj {
	private String type = "TEXT_DETECTION";
}
