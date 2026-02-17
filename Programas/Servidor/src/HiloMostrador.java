/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
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
            //Abrimos los flujos de salida y entrada
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            
            //Leemos que mostrador se ha conectado
            int id = entrada.readInt();
            
            //Esperamos que nos confirme que quiere siguiente ticket
            boolean siguiente = entrada.readBoolean();

            while (siguiente) {
                //Sacamos ticket de la cola y se le envia al mostrador
                Ticket ticket = cola.siguienteTicket();
                salida.writeObject(ticket);

                //Avisamos que ticket ha ido a que mostrador
                System.out.println("Nº" + ticket.getNumero() + " para Mostrador " + id);

                //Espera a que nos pida otro ticket antes de continuar
                siguiente = entrada.readBoolean();
            }
            entrada.close();
            salida.close();

        } catch (SocketException e){
            
        } catch (IOException ex) {
            Logger.getLogger(HiloMostrador.class.getName()).log(Level.SEVERE, null, ex);
        }




    }
}
