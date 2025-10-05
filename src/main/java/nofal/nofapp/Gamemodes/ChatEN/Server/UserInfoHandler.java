package nofal.nofapp.Gamemodes.ChatEN.Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import nofal.nofapp.MainForm.User;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserInfoHandler implements HttpHandler {
	private static final  Path path = Paths.get("UsersInfo", "Info.json");
	private static final  File USER_FILE = path.toFile();
	private final Gson gson = new Gson();
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String method = exchange.getRequestMethod();

		switch(method){
			case "GET":
				handleGet(exchange);
				break;
			case "POST":
				handlePost(exchange);
				break;
			default:
				exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, -1);
				break;

		}
	}
	private void handleGet(HttpExchange exchange) throws IOException {
		List<User> users = readUser();
		String response = gson.toJson(users);

		exchange.getResponseHeaders().add("Content-Type", "application/json");
		exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);

		try(OutputStream os = exchange.getResponseBody()){
			os.write(response.getBytes(StandardCharsets.UTF_8));
		}
	}

	private void handlePost(HttpExchange exchange) throws IOException {
		InputStream is = exchange.getRequestBody();
		String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);

		User newUser = gson.fromJson(body, User.class);
		List<User> users = readUser();

		users.add(newUser);
		writeUser(users);
		String response = "{\"status\":\"user added\"}";
		exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);

		try (OutputStream os = exchange.getResponseBody()) {
			os.write(response.getBytes(StandardCharsets.UTF_8));
		}
	}

	private List<User> readUser(){
		if(!USER_FILE.exists()) return new ArrayList<>();

		try(BufferedReader br = new BufferedReader(new FileReader(USER_FILE))){
			Type ListType = new TypeToken<List<User>>() {}.getType();
			List<User> users = gson.fromJson(br, ListType);
			return users != null ? users : new ArrayList<>();
		}
		catch (IOException exception){
			exception.printStackTrace();
			return new ArrayList<>();
		}
	}
	private void writeUser(List<User> users){
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE))){
			gson.toJson(users, bw);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}
