package nofal.nofapp.Gamemodes.ChatEN.Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ChatHandler implements HttpHandler {
	private final Path UserChatpath = Paths.get("userChat", "userchat.json");
	private final File CHAT_FILE = UserChatpath.toFile();
	private final Gson gson = new Gson();

	// Represent a chat message
	static class ChatMessage {
		String user;
		String message;
		long msgTime;

		public ChatMessage(String user, String message, long msgTime) {
			this.user = user;
			this.message = message;
			this.msgTime = msgTime;
		}
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException  {
		if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
			handleGet(exchange);
		} else if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
			handlePost(exchange);
		} else {
			exchange.sendResponseHeaders(405, -1); // Method Not Allowed
		}
	}

	private void handleGet(HttpExchange exchange) throws IOException {
		List<ChatMessage> messages = readMessages();
		String response = gson.toJson(messages);

		exchange.getResponseHeaders().add("Content-Type", "application/json");
		exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);

		try (OutputStream os = exchange.getResponseBody()) {
			os.write(response.getBytes(StandardCharsets.UTF_8));
		}
	}

	private void handlePost(HttpExchange exchange) throws IOException {
		InputStream is = exchange.getRequestBody();
		String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

		ChatMessage newMsg = gson.fromJson(body, ChatMessage.class);

		List<ChatMessage> messages = readMessages();
		messages.add(newMsg);
		writeMessages(messages);

		String response = "{\"status\":\"ok\"}";
		exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
		try (OutputStream os = exchange.getResponseBody()) {
			os.write(response.getBytes(StandardCharsets.UTF_8));
		}
	}

	private List<ChatMessage> readMessages() {
		if (!CHAT_FILE.exists()) return new ArrayList<>();
		try (Reader reader = new FileReader(CHAT_FILE)) {
			Type listType = new TypeToken<List<ChatMessage>>() {}.getType();
			return gson.fromJson(reader, listType);
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private void writeMessages(List<ChatMessage> messages) {
		try (Writer writer = new FileWriter(CHAT_FILE, false)) {
			gson.toJson(messages, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
