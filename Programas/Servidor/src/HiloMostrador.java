/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy
 */
public class HiloMostrador extends Thread {

    private Socket socket;
    private Cola cola;

    public HiloMostrador(Socket socket, Cola cola) {
        this.socket = socket;
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            
            int id = entrada.readInt();
            Ticket ticket = cola.siguienteTicket();
            salida.writeObject(ticket);
            
            System.out.println("Nº"+ ticket.getNumero()+" para Mostrador "+id);
            
            entrada.close();
            salida.close();
                
            } catch (IOException ex) {
            Logger.getLogger(HiloMostrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        



    }
}
