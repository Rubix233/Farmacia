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
        String host = "localhost";
        int mostradores = 4;

        for (int i = 1; i <= mostradores; i++) {
            new Mostrador(i, host, puerto).start();
        }

    }
}
