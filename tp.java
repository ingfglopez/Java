/*
 Para entrega 2
 */
package tp;

public class tp {

    public static PronosticoDeportivo PRODE;
    
   
    public static void main(String[] args) {
        System.out.println ("Sistema de simulación de pronósticos deportivos.");
        
        PRODE = new PronosticoDeportivo();

        PRODE.play();
    }
    
}
