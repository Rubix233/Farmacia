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
public class HiloMaquina extends Thread {

    ServerSocket serverSocket;
    Cola cola;

    public HiloMaquina(ServerSocket serverSocket, Cola cola) {
        this.serverSocket = serverSocket;
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = this.serverSocket.accept();
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                boolean seguirLeyendo = true;
                while (seguirLeyendo) {
                    try {
                        char tipo = dataIn.readChar();
                        int turno = this.cola.nuevoTurno(tipo);
                        dataOut.writeInt(turno);
                    } catch (EOFException | SocketException e) {

                        System.out.println("La máquina ha cerrado la conexión.");
                        seguirLeyendo = false;
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(HiloMostrador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
