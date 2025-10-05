package nofal.nofapp.Gamemodes.ChatEN.Server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ChatHttpsServer {
	private HttpServer server;

	public void start(int port) throws IOException {
		server = HttpServer.create(new InetSocketAddress(port), 0);
		// Register the /messages endpoint with ChatHandler
		server.createContext("/messages", new ChatHandler());
		server.setExecutor(null); // default executor
		server.start();
		System.out.println("Chat server started at https://localhost:" + port);
	}

	public void stop() {
		if (server != null) {
			server.stop(0);
			System.out.println("Chat server stopped.");
		}
	}
}
