/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andy
 */
public class Mostradores {

    public static void main(String[] args) {
        int puerto = 50000;
        //String host = "172.22.113.6";
        String host = "localhost";
        int mostradores = 4;

        //Arrancamos todos los mostradores
        for (int i = 1; i <= mostradores; i++) {
            new Mostrador(i, host, puerto).start();
        }

    }
}
