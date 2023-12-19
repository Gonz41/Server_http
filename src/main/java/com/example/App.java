package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Server in Avvio");
            ServerSocket server = new ServerSocket(8080);
            while (true) {

                Socket s = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                String richiesta = in.readLine();
                String path = richiesta.split(" ")[1];
                System.out.println("---" + path + "---");

                do {
                    richiesta = in.readLine();
                    System.out.println(richiesta);
                } while(!richiesta.isEmpty());

                if (path.endsWith("/")) 
                    path += "index.html";
                
                int index = path.lastIndexOf('.');
                if (index > 0) {  
                    File file = new File("htdocs"+path);
                    if(file.exists()){
                        sendFile(out, file);
                    } else {
                        String msg = "File non trovato";
                        out.writeBytes("HTTP/1.1 404 Not Found\n");
                        out.writeBytes("Content-Length: " + msg.length() + "\n");
                        out.writeBytes("Server: Java HTTP Server from Gonza: 1.0" + "\n");
                        out.writeBytes("Date: " + new Date() + "\n");
                        out.writeBytes("Content-Type: text/plain; charset=utf-8\n");
                        out.writeBytes("\n");
                        out.writeBytes(msg);
                    }
                }
                s.close();
            }
            //server.close(); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void sendFile(DataOutputStream out, File file) throws IOException{
            String [] riga = file.getName().split("\\.");
            String ind = riga[riga.length-1];
            System.out.println(ind);
            out.writeBytes("HTTP/1.1 200 OK" + "\n");
            out.writeBytes("Content-Lenght: " + file.length() + "\n");
            out.writeBytes("Server: Java HTTP Server from Gonza: 1.0" + "\n");
            out.writeBytes("Date: " + new Date() + "\n");   
            switch (ind) {
                case "html":
                case "hml":
                    out.writeBytes("Content-Type: text/html; charset=utf-8" + "\n");
                    break;
                case "css":
                    out.writeBytes("Content-Type: text/css; charset=utf-8" + "\n");
                    break;
                case "png":
                    out.writeBytes("Content-Type: image/png; charset=utf-8" + "\n");
                    break;
                case "jpg":
                    out.writeBytes("Content-Type: image/jpg; charset=utf-8" + "\n"); 
                    break;
                case "js":
                    out.writeBytes("Content-Type: application/js; charset=utf-8" + "\n");
                    break;
                case "ico":
                    out.writeBytes("Content-Type: favicon/ico; charset=utf-8" + "\n");
                    break;
                default:
                    //out.writeBytes("Content-Type: charset=utf-8\n");
                    break;
            }
            
            InputStream input = new FileInputStream(file);
            byte[] buf = new byte[8192];
            int n;
            while ((n = input.read(buf)) != -1) {
                out.write(buf, 0, n);
            }
            input.close();
    }
}