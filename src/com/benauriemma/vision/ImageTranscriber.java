package com.benauriemma.vision;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Class used to extract text from an image
 * 
 * @author benauriemma
 *
 */
public class ImageTranscriber {

	/**
	 * Transcribe an image, and return all of its text as a single string
	 * 
	 * @param bufferedImage to transcribe
	 * @return a string containing all of the text
	 * @throws IOException
	 */
	public String transcribe(BufferedImage bufferedImage) throws IOException {
		TextAnnotationRequest request = new TextAnnotationRequest(bufferedImage);
		return request.make().getAllText();
	}

}
