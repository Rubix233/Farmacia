/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author Fran
 * @review Andy
 */
public class Ticket implements Serializable{
    
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
