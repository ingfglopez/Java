/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp;

/**
 *
 * @author mcatvd01
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Libreria de Base de Datos
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class ListaPartidos {
    
    private List<Partido> partidos;
    private String partidosCSV;

    public ListaPartidos(List<Partido> partidos, String partidosCSV) {
        this.partidos = partidos;
        this.partidosCSV = partidosCSV;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }
        public ListaPartidos() {
        this.partidos = new ArrayList<Partido>();
        this.partidosCSV = "partidos.csv";
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public String getPartidosCSV() {
        return partidosCSV;
    }

    public void setPartidosCSV(String partidosCSV) {
        this.partidosCSV = partidosCSV;
    }
    
    public void addPartido(Partido p) {
        this.partidos.add(p);
    }
    public void removePartido(Partido p) {
        this.partidos.remove(p);
    }
    
    public String listar() {
        String lista = "";
        for (Partido partido: partidos) {
            lista += "\n" + partido;
        }           
        return lista;
    }
    
    
    public Partido getPartido (int idPartido) {
        // Defini un objeto de tipo Equipo en dónde va a ir mi resultado
        // Inicialmente es null, ya que no he encontrado el equipo que 
        // buscaba todavía.
        Partido encontrado = null;
        // Recorro la lista de equipos que está cargada
        for (Partido eq : this.getPartidos()) {
            // Para cada equipo obtengo el valor del ID y lo comparo con el que
            // estoy buscando
            if (eq.getIdPartido() == idPartido) {
                // Si lo encuentro (son iguales) lo asigno como valor de encontrado
                encontrado = eq;
                // Y hago un break para salir del ciclo ya que no hace falta seguir buscando
                break;
            }
        }
        // Una vez fuera del ciclo retorno el equipo, pueden pasar dos cosas:
        // 1- Lo encontré en el ciclo, entonces encontrado tiene el objeto encontrado
        // 2- No lo encontré en el ciclo, entonces conserva el valor null del principio
        return encontrado;
    }


    @Override
    public String toString() {
        return "ListaPartidos{" + "partidos=" + partidos + ", partidosCSV=" + partidosCSV + '}';
    }
    
    
    
    
    
    /// CARGAR DESDE BASE E DATOS
    
    public void cargarDeBd(int idPartido,  ListaEquipos listaequipos) {
        
        Connection conn = null;
    
        try { 
        conn = DriverManager.getConnection("jdbc:sqlite:pronosticos.db");
        Statement stmt = conn.createStatement();
        String sql = "Select idPartido , idEquipo1 , idEquipo2 , golesEquipo1 , golesEquipo2  from partidos WHERE idPartido= "+ idPartido;
        
        ResultSet rs = stmt.executeQuery(sql);
        Partido partido;
        
        while (rs.next()) {
                        
                       /*System.out.println(rs.getInt("idPartido") + "\t"
                        + rs.getString("Nombre") + "\t");*/
                       //int idPartido = rs.getInt("idPartido");
                       int equipo1 = rs.getInt("idEquipo1");
                       int equipo2 = rs.getInt("idEquipo2");
                       int golesEquipo1 = rs.getInt("golesEquipo1");
                       int golesEquipo2 = rs.getInt("golesEquipo2");
                       
                       Equipo equipoA = listaequipos.getEquipo(equipo1);
                       Equipo equipoB = listaequipos.getEquipo(equipo2);
                       partido = new Partido (idPartido,equipoA,equipoB,golesEquipo1,golesEquipo2);
                       // llama al metodo add para grabar el equipo en la lista en memoria
                       this.addPartido(partido);
                            }
                 
        
            } catch (SQLException e) {
            System.out.println(e.getMessage());
            } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                } catch (SQLException e) {
                // conn close failed.
                System.out.println(e.getMessage());
            }
        }
    }   
    
    
    
    
    // cargar desde el archivo
    public void cargarDeArchivo(
             //int idEquipo, // id del participante que realizó el pronóstico
            ListaEquipos listaequipos // lista de equipos
            
    ) {
        // para las lineas del archivo csv
        String datosPartido;
        // para los datos individuales de cada linea
        String vectorPartido[];
        // para el objeto en memoria
        Partido partido;
        
        int fila = 0;
       
        try { 
            Scanner sc = new Scanner(new File(this.getPartidosCSV()));
            sc.useDelimiter("\n");   //setea el separador de los datos
                
            while (sc.hasNext()) {
                // levanta los datos de cada linea
                datosPartido = sc.next();
                // Descomentar si se quiere mostrar cada línea leída desde el archivo
                // System.out.println(datosEquipo);  //muestra los datos levantados 
                fila ++;
                // si es la cabecera la descarto y no se considera para armar el listado
                if (fila == 1)
                    continue;              
                 
                //Proceso auxiliar para convertir los string en vector
                // guarda en un vector los elementos individuales
                vectorPartido = datosPartido.split(",");   
                
                // graba el equipo en memoria
                //convertir un string a un entero;
                int idPartido = Integer.parseInt(vectorPartido[0]);
                // equipo1= new Equipo (Integer.parseInt(vectorPartido[1]));
                // equipo2= new Equipo (Integer.parseInt(vectorPartido[2]));
                int equipo1 = Integer.parseInt(vectorPartido[1]);
                int equipo2 = Integer.parseInt(vectorPartido[2]);
                //Equipo equipo1 = ListaEquipos.getEquipo(Integer.parseInt(vectorPartido[1]));
                //Equipo equipo2 = ListaEquipos.getEquipo(Integer.parseInt(vectorPartido[2]));
                Equipo equipoA = listaequipos.getEquipo(equipo1);
                Equipo equipoB = listaequipos.getEquipo(equipo2);
                int golesEquipo1 = Integer.parseInt(vectorPartido[3]);
                int golesEquipo2 = Integer.parseInt(vectorPartido[4]);
                
                // crea el objeto en memoria
               //partido = new Partido(idPartido,equipo1,equipo2,golesEquipo1,golesEquipo2);
                partido = new Partido (idPartido,equipoA,equipoB,golesEquipo1,golesEquipo2);
                // llama al metodo add para grabar el equipo en la lista en memoria
                this.addPartido(partido);
            }
            //closes the scanner
        } catch (IOException ex) {
                System.out.println("Mensaje: " + ex.getMessage());
        }       

    }
    
    
}
