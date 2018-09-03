package com.benauriemma.vision;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.benauriemma.constants.PersonalInfo;
import com.google.gson.Gson;

/**
 * Class representing a request to Google's Vision API
 * 
 * @author benauriemma
 *
 */
public class TextAnnotationRequest {

	private static String BASE_URL = "https://vision.googleapis.com/v1/images:annotate?key=";
	private static String FIELD_LIMITER = "&fields=responses(textAnnotations(description))";
	private static String REQUEST_METHOD = "POST";

	private BufferedImage myImage;

	public TextAnnotationRequest(BufferedImage bufferedImage) {
		myImage = bufferedImage;
	}

	/**
	 * Make the request to the Vision API with the image that was provided in the constructor
	 * 
	 * @return the response as a TextAnnotationResponse
	 * @throws IOException
	 */
	public TextAnnotationResponse make() throws IOException {
		URL url = new URL(BASE_URL+PersonalInfo.API_KEY+FIELD_LIMITER);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(REQUEST_METHOD);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		DataOutputStream output = new DataOutputStream(conn.getOutputStream());
		output.writeBytes(this.getParameters(this.getImageEncoding(myImage)));
		output.flush();
		output.close();
		DataInputStream input = new DataInputStream(conn.getInputStream());
		String response = new String(input.readAllBytes());
		int responseCode = conn.getResponseCode();
		String responseMessage = conn.getResponseMessage();
		return new Gson().fromJson(response, TextAnnotationResponse.class);
	}

	/**
	 * Create the json object that contains the parameters for the POST request
	 * 
	 * @param imageEncoding
	 * @return
	 */
	private String getParameters(String imageEncoding) {
		return new Gson().toJson(new TextAnnotationRequestParams(imageEncoding));
	}

	/**
	 * Encode the image that we're annotating in Base 64
	 * 
	 * @param bufferedImage to annotate
	 * @return the Base 64 representation
	 * @throws IOException
	 */
	private String getImageEncoding(BufferedImage bufferedImage) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", bos);
		return Base64.getEncoder().encodeToString(bos.toByteArray());
	}

}
