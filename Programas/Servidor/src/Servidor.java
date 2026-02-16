/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy Jan
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int puertoMostrador = 50000;
        int puertoMaquina = 50001;

        Cola cola = new Cola();

        try {
            ServerSocket atenderMostrador = new ServerSocket(puertoMostrador);
            ServerSocket atenderMaquina = new ServerSocket(puertoMaquina);
            System.out.println("Server escuchando");
            
            new HiloMaquina(atenderMaquina,cola).start();
            
            while(true){
                Socket socketMostrador = atenderMostrador.accept();
                new HiloMostrador(socketMostrador, cola).start();
            }
            
            

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
