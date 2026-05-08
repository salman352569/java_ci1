package com.demo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HelloWorld {

    public static void main(String[] args) throws Exception {

        String version ="version 2- Deployed via jenkins CI/CD";
        String DeployTime =LocalDateTime.now().toString();

        //Bind to all interfaces so it is accessibly externally
    
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new HttpHandler() {
            public void handle(HttpExchange exchange) {
                try {
                    String response = 
                    "=======  jenkins CI/CD Demo Applications ======\n\n" +
                    "Hello from jenkins Pipeline! \n\n" +
                    "Application version: " + version + "\n" +
                    "Deployment Time :" + deployTime + "\n" +
                    "server Hostname   : " + hostname + "\n" +
                    "Request Details \n" + 
                    "-------------------- \n"+
                    "Requested url         : " +  requestURI + "\n" +
                    "User Agent            :" + userAgent + "\n" ;
                

                    exchange.sendResponseHeaders(200, response.length());
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        server.setExecutor(null);
        server.start();

        System.out.println("Server started on port 8080");
        System.out.println("Appliction version: " + version);
        System.out.println("Deployment Time: " + deployTime);
        System.out.println("Hostname:  " + hostname);
    }
}