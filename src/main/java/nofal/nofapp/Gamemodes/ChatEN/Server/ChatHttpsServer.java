package nofal.nofapp.Gamemodes.ChatEN.Server;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ChatHttpsServer {
    private HttpServer server;
    public void start(int port) throws IOException {
        // Use the class field, not a new local variable
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/messages", new ChatHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server running at http://localhost:" + port);
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
            System.out.println("Chat server stopped.");
        }
    }
}
