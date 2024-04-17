/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_tcp_multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prova
 */
public class Server {

    private ServerSocket server = null;
    private List<Socket> clients = new ArrayList<>();
    private int porta;

    public Server(int porta) {
        try {
            this.porta = porta;
            server = new ServerSocket(porta);
            System.out.println("S1 - Il server è in ascolto sulla porta " + porta);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.err.println("Si è verificato un errore durante la fase di apertura ed ascolto");
        }
    }

    public void start() {
        while (true) {
            try {
                Socket client = server.accept();
                clients.add(client);
                System.out.println("S2 - Connessione stabilita con un client");

                new Thread(() -> handleClient(client)).start();

            } catch (IOException e) {
                System.err.println(e.getMessage());
                System.err.println("Si è verificato un errore durante la connessione con il server");
            }
        }
    }

    private void handleClient(Socket client) {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            out.println("Ciao client");

            String message = in.readLine();
            System.out.println("Messaggio ricevuto dal client: " + message);

            out.println("Messaggio ricevuto: " + message);

            client.close();
            clients.remove(client);

        } catch (IOException e) {
            System.err.println("Errore durante la gestione del client: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server s = new Server(2000);
        s.start();
    }
}
