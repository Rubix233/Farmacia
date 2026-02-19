/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy Jan
 * @review Fran
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int puerto = 50000;

        Cola cola = new Cola();

        try {
            //Abrimos puerto
            ServerSocket serverSocket = new ServerSocket(puerto);

            System.out.println("Server escuchando por el puerto: " + puerto);
            
            while(true){
                //Acepta conexion
                Socket socket = serverSocket.accept();
                String quien;
                
                //Abrimos canal de entrada
                DataInputStream entrada = new DataInputStream(socket.getInputStream());
                
                //Leemos quien se ha conectado
                quien = entrada.readUTF();
                
                //Arrancamos hilo correspondiente
                if(quien.equals("U")){
                    new HiloUsuario(socket, cola).start();
                } else {
                    new HiloMostrador(socket,cola).start();
                }     
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
