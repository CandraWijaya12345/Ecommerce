package org.example;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class Response {
    public void getResponse(HttpExchange exchange,String jsonObject, int code) throws IOException{
        OutputStream outputstream = exchange.getResponseBody();
        exchange.getResponseHeaders().set("Content-Type","application/json");
        String response = jsonObject;
        exchange.sendResponseHeaders(code,response.length());
        outputstream.write(response.getBytes());
        outputstream.flush();
        outputstream.close();
    }

    public void sendResponse(HttpExchange exchange, String response) throws IOException {
        OutputStream outputStream = exchange.getResponseBody();
        exchange.sendResponseHeaders(404, response.length());
        outputStream.write(response.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
