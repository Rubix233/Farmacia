
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
 * @author Andy Jan
 * @review Fran
 */
public class Usuario extends Thread {

    int id;
    char tipo;
    String host;
    int puerto;

    public Usuario(int id, char tipo, String host, int puerto) {
        this.id = id;
        this.tipo = tipo;
        this.host = host;
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try {
            //Abrimos socket
            Socket socket = new Socket(host, puerto);
            //Abrimos flujos de datos
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            System.out.println("Usuario " + id + " llega.");

            //Nos identificamos como usuario
            dataOut.writeUTF("U");
            //Decimos que tipo de atencion queremos
            dataOut.writeChar(tipo);

            //Recibimos el turno que nos ha tocado
            int turno = dataIn.readInt();

            //Decimos quienes somos y que turno nos ha dado el servidor
            System.out.println("    Recibido Usuario " + id + " el número " + turno);



            dataIn.close();
            dataOut.close();
            socket.close();

        } catch (SocketException e) {
            System.out.println("Conection reset");
        } catch (UnknownHostException ex) {
            Logger.getLogger(LanzarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LanzarUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
