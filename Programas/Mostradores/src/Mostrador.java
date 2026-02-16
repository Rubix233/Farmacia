
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andy
 */
public class Mostrador extends Thread {

    int id;
    int puerto;
    String host;

    public Mostrador(int id, String host, int puerto) {
        this.id = id;
        this.host = host;
        this.puerto = puerto;
    }

    public void run() {


        while (true) {
            try {
                Socket socket = new Socket(this.host, this.puerto);
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

                System.out.println("Mostrador " + this.id + " siguiente...");

                salida.writeInt(id);
                Ticket ticket = (Ticket) entrada.readObject();
                char tipo = ticket.getTipo();
                System.out.println("Mostrador " + this.id + " Empieza gestion: " + tipo);

                if (tipo == 'G') {
                    Thread.sleep(2000);
                } else {
                    if (tipo == 'H') {
                        Thread.sleep(3000);
                    } else {
                        Thread.sleep(1000);
                    }
                }
                System.out.println("\t\t\t Mostrador " + this.id + " termina gestion " + tipo);

                salida.close();
                entrada.close();
                socket.close();
                

            } catch (SocketException | EOFException e) {
                System.out.println("Connection reset");
                break;

            } catch (IOException | ClassNotFoundException | InterruptedException ex) {
                System.err.println("Error inesperado: " + ex.getMessage());
            }

        }


    }
}
