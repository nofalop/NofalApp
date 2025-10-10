package nofal.nofapp.Gamemodes.ChatEN.Server;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

public class ChatClient {
	private static final String SERVER_URL = "http://localhost:8080";
	private static final Gson gson = new Gson();

	public static void sendMessage(String username, String text) {
		try {

			ChatHandler.ChatMessage msg = new ChatHandler.ChatMessage(username, text, System.currentTimeMillis());

			String json = gson.toJson(msg);


			URL url = new URL(SERVER_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json; utf-8");
			connection.setDoOutput(true);


			try (OutputStream os = connection.getOutputStream()) {
				os.write(json.getBytes(StandardCharsets.UTF_8));
			}

			int code = connection.getResponseCode();
			System.out.println("Server responded: " + code);

			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
