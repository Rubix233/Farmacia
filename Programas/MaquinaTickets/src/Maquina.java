
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andy
 */
public class Maquina {

    public static void main(String[] args) {
        int puerto = 50001;
        String host = "localhost";
        char[] tipos = {'G', 'H', 'G', 'O', 'H', 'O', 'O', 'G', 'O', 'H', 'H', 'O'};
        int usuario = 1;

        try {
            Socket socket = new Socket(host, puerto);
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            for (char tipo : tipos) {
                System.out.println("Usuario " + usuario + " llega.");
                dataOut.writeChar(tipo);
                int turno = dataIn.readInt();
                System.out.println("Recibido Usuario " + usuario + " el nmero " + turno);
                usuario++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
                }
            }


            dataIn.close();
            dataOut.close();
            socket.close();

        } catch (SocketException e) {
            System.out.println("Conection reset");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
