
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
public class Usuarios {

    public static void main(String[] args) {
        int puerto = 50000;
        //String host = "172.22.113.6";
        String host = "localhost";
        //Usuarios a simular
        char[] tipos = {'G', 'H', 'G', 'O', 'H', 'O', 'O', 'G', 'O', 'H', 'H', 'O'};
        int usuario = 1;

        //Arrancar cada hilo de usuario asignandole un id y el tipo que va a pedirle al servidor
        for (char tipo : tipos) {

            new Usuario(usuario, tipo, host, puerto).start();
            usuario++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
