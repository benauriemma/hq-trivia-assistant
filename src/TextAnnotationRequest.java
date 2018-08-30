import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextAnnotationRequest {

	private static String BASE_URL = "https://vision.googleapis.com/v1/images:annotate?key=";
	private String myApiKey;
	private static String FIELD_LIMITER = "&fields=responses(textAnnotations(description))";
	private static String REQUEST_METHOD = "POST";

	public TextAnnotationRequest(String apiKey) {
		myApiKey = apiKey;
	}

	public TextAnnotationResponse make(BufferedImage bufferedImage) throws IOException {
		URL url = new URL(BASE_URL+myApiKey+FIELD_LIMITER);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(REQUEST_METHOD);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		DataOutputStream output = new DataOutputStream(conn.getOutputStream());
		output.writeBytes(this.getParameters(this.getImageEncoding(bufferedImage)));
		output.flush();
		output.close();
		DataInputStream input = new DataInputStream(conn.getInputStream());
		String response = new String(input.readAllBytes());
		int responseCode = conn.getResponseCode();
		String responseMessage = conn.getResponseMessage();
		return new Gson().fromJson(response, TextAnnotationResponse.class);
	}

	private String getParameters(String imageEncoding) throws IOException {
		JsonObject parameters = new JsonObject();
		JsonArray requests = new JsonArray();
		parameters.add("requests", requests);
		JsonObject request = new JsonObject();
		requests.add(request);
		JsonObject imageJson = new JsonObject();
		imageJson.addProperty("content", imageEncoding);
		request.add("image", imageJson);
		JsonArray features = new JsonArray();
		request.add("features", features);
		JsonObject type = new JsonObject();
		type.addProperty("type", "TEXT_DETECTION");
		features.add(type);
		//System.out.println(parameters.toString());
		return parameters.toString();
	}

	private String getImageEncoding(BufferedImage bufferedImage) throws IOException {
		//BufferedImage bufferedImage = ImageIO.read(new File(myImageFilepath));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", bos);
		return Base64.getEncoder().encodeToString(bos.toByteArray());
	}

}
