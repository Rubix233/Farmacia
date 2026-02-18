
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Fran
 * @review Andy
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
        try {
            //Abrimos socket
            Socket socket = new Socket(this.host, this.puerto);

            //Establecemos flujo de salida
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            //Decimos que somos mostrador
            salida.writeUTF("M");
        
            //Decimos que mostrador somos
            salida.writeInt(id);


            //Abrimos flujo de entrada
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            while (true) {
                //Avisamos que queremos nuevo ticket
                salida.writeBoolean(true);
                System.out.println("Mostrador " + this.id + " siguiente...");
                
                //Guardamos el ticket que recibimos
                Ticket ticket = (Ticket) entrada.readObject();
                //Pillamos el tipo de consulta que es
                char tipo = ticket.getTipo();
                System.out.println("Mostrador " + this.id + " Empieza gestion: " + tipo);

                //Atender segun tipo de consulta
                if (tipo == 'G') {
                    Thread.sleep(2000);
                } else {
                    if (tipo == 'H') {
                        Thread.sleep(3000);
                    } else {
                        Thread.sleep(1000);
                    }

                    System.out.println("\t\t\t Mostrador " + this.id + " termina gestion " + tipo);
                }
            }

        } catch (SocketException | EOFException e) {
            System.out.println("Connection reset");

        } catch (IOException | ClassNotFoundException | InterruptedException ex) {
            System.err.println("Error inesperado: " + ex.getMessage());
        }
    }
}
