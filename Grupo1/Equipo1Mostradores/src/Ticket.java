

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fran
 */
public class Ticket{
    
    private int numero;
    private char tipo;

    public Ticket(int numero, char tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public char getTipo() {
        return tipo;
    }
    
}
