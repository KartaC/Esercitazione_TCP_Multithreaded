/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package esercitazione_tcp_multithreaded;

/**
 *
 * @author Cartaginesi Thomas
 */
public class MainServer {

    public static void main(String[] args) {
        Server s = new Server(2000);
        s.start();
    }
}
