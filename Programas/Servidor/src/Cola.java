/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy Jan
 */
public class Cola {
    private int orden = 1;
    private Queue<Ticket> cola = new LinkedList<>();

    public Cola() {
    }

    public synchronized int nuevoTurno(char tipo) {
        int ordenActual = this.orden;
        Ticket ticket = new Ticket(ordenActual, tipo);    
        
        cola.add(ticket);
        this.orden++;
        if(this.orden > 100) this.orden = 1;
        
        notifyAll();
        return ordenActual;
    }
    
    public synchronized Ticket siguienteTicket(){
        
        while(cola.isEmpty()){
            try {          
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }
        return cola.poll();
        
    }
}
