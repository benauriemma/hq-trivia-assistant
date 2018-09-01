import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TextAnnotationRequest {

	private static String BASE_URL = "https://vision.googleapis.com/v1/images:annotate?key=";
	private static String FIELD_LIMITER = "&fields=responses(textAnnotations(description))";
	private static String REQUEST_METHOD = "POST";

	private BufferedImage myImage;

	public TextAnnotationRequest(BufferedImage bufferedImage) {
		myImage = bufferedImage;
	}

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

	private String getParameters(String imageEncoding) {
		return new Gson().toJson(new TextAnnotationRequestParams(imageEncoding));
	}

	private String getImageEncoding(BufferedImage bufferedImage) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, "png", bos);
		return Base64.getEncoder().encodeToString(bos.toByteArray());
	}

}
