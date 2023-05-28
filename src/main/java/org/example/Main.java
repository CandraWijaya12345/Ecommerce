package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 6092), 0);

        httpServer.createContext("/user", new UserHandler()); // Inisialisasi dengan UserHandler

        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();
    }

    public static class UserHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals((exchange.getRequestMethod()))) {
                String responseToBeSentBack = SQLkoneksi.Connection();
                exchange.sendResponseHeaders(200, responseToBeSentBack.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(responseToBeSentBack.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}