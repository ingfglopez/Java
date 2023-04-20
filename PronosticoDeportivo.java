/*
Para entrega 2
 */
package com.mycompany.tp;

import de.vandermeer.asciitable.AsciiTable;
/**
 *
 * @author aguzman
 */
public class PronosticoDeportivo {
    private ListaEquipos equipos;
    private ListaPartidos partidos;
    private ListaParticipantes participantes;
    private ListaPronosticos pronosticos;

    public PronosticoDeportivo() {
        equipos = new ListaEquipos();
        partidos = new ListaPartidos();
        participantes = new ListaParticipantes();
        pronosticos = new ListaPronosticos();
    }

    public void play(){
        // cargar y listar los equipos
        equipos.cargarDeBd();
        System.out.println("Los equipos cargados son: " + equipos.listar());
        
       
        
        
        partidos.cargarDeBd(equipos);
        System.out.println("Los partidos cargados son: " + partidos.listar());
        
        AsciiTable part= new AsciiTable();
        part.addRule();
        part.addRow("EQUIPO 1","GOLES","EQUIPO 2","GOLES");
        part.addRule();
        for (Partido p: partidos.getPartidos()) {
            part.addRow(p.getEquipo1().getNombre(),p.getEquipo1(),p.getEquipo2().getNombre(),p.getGolesEquipo2());
        }
        part.addRule();
        System.out.println(part.render());
        
        

        participantes.cargarDeBd();
        // Una vez cargados los participantes, para cada uno de ellos
        // cargar sus pronósticos
        for (Participante p : participantes.getParticipantes()) {
            p.cargarPronosticos(equipos, partidos);
        }
        
        AsciiTable at= new AsciiTable();
        at.addRule();
        at.addRow("PARTICIPANTE","PUNTAJE");
        at.addRule();
        for (Participante p: participantes.getParticipantes()) {
            at.addRow(p.getNombre(),p.getPuntaje());
        }
        at.addRule();
        System.out.println(at.render());
        
        AsciiTable at2= new AsciiTable();
        at2.addRule();
        at2.addRow("PARTICIPANTE","PUNTAJE");
        at2.addRule();
        for (Participante p: participantes.getOrdenadosPorPuntaje()) {
            at2.addRow(p.getNombre(),p.getPuntaje());
        }
        at2.addRule();
        System.out.println(at2.render());
        
        System.out.println("Los participantes cargados son: " + participantes.listar());
        
        // agregar y/o modificar el codigo que quieran
        
    }    
}
