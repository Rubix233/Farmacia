/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class HiloUsuario extends Thread {

    Socket socket;
    Cola cola;

    public HiloUsuario(Socket socket, Cola cola) {
        this.socket = socket;
        this.cola = cola;
    }

    @Override
    public void run() {
        try {

            //Establecemos flujos
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());

            //Lee tipo de consulta
            char tipo = dataIn.readChar();
            //Pide a la cola nuevo turno y guarda el numero
            int turno = cola.nuevoTurno(tipo);

            //Devuelve el turno que le ha tocado
            dataOut.writeInt(turno);
        } catch (IOException ex) {
            Logger.getLogger(HiloUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
