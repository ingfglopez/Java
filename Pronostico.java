/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp;

/**
 *
 * @author mcasatti
 */
public class Pronostico {
    
    private int idPronostico;
    private Equipo equipo;
    private Partido partido;
    private char Resultado;
    
    public Pronostico() {
        
    }

    public Pronostico(int idPronostico, Equipo equipo, Partido partido, char Resultado) {
        this.idPronostico = idPronostico;
        this.equipo = equipo;
        this.partido = partido;
        this.Resultado = Resultado;
    }

    public int getIdPronostico() {
        return idPronostico;
    }

    public void setIdPronostico(int idPronostico) {
        this.idPronostico = idPronostico;
    }

    

   

    

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public char getResultado() {
        return Resultado;
    }

    public void setResultado(char Resultado) {
        this.Resultado = Resultado;
    }

    @Override
    public String toString() {
        return "Pronostico{" + "idPronostico=" + idPronostico + ", equipo=" + equipo + ", partido=" + partido + ", Resultado=" + Resultado + '}';
    }

    public int getPuntaje(){
    char resultado = this.partido.getResultado(this.equipo);
    if (resultado == this.getResultado()){
       return 1;
    } else {
       return 0;    
    }
    
    }
    
    
   
}
