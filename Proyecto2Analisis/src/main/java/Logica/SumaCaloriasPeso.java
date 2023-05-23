package Logica;
import java.util.ArrayList; //Importacion para utilizar los arreglos
/**
 * Clase unicamente para agregar herramientas generales a los algoritmos
 * @author Fabricio Porras 
 * @author Carlos Solis
 */
public class SumaCaloriasPeso {
    private long asignaciones = 0;
    private long comparaciones =0;

    /**
     * Metodo que resetea los contadores de asignaciones y comparacioens
    */
    public void resetCounters(){
        this.asignaciones=0;
        this.comparaciones=0;
    }    
    
    public long getAsignaciones() {
        return asignaciones;
    }

    public long getComparaciones() {
        return comparaciones;
    }
 
     /**
     * Metodo que permite sumar las calorias en una lista con los alimentos
     * @param listaActual lista con las intancias de tipo Comida
     * @param caloriasMinimas calorias minimas requeridas
     * @return boolean true si la suma de calorias supera las calorias minimas
     */
    public boolean sumaCalorias(ArrayList<Comida> listaActual,int caloriasMinimas){ 
        int suma = 0; //La suma inicia en 0
        for(int i = 0; i<listaActual.size();i++){ //Recorre toda la lista
            suma+=listaActual.get(i).getCalorias(); //Se suman las calorias
            this.asignaciones++;
            this.comparaciones++;
        }
        this.asignaciones+=2;
        this.comparaciones+=2; //Falsa del for y la que sigue
        if(suma>=caloriasMinimas)return true; //Si la suma safistace retorna true
        return false; //sino retorna false
    }
        
    /**
     * Metodo que permite sumar el peso en una lista con los alimentos
     * @param listaActual lista con las instancias de tipo Comida
     * @param pesoMaximo peso maximo permitido
     * @return boolean true si el peso sobrepasa el limine
     */
    public boolean sumaPeso(ArrayList<Comida> listaActual,int pesoMaximo){
        int suma = 0; //Se inicializa la suma del peso en 0
        for(int i = 0; i<listaActual.size();i++){ //Se recorre la lista de alimentos
            suma+=listaActual.get(i).getPeso(); //Se suman los pesos
            this.asignaciones++;
            this.comparaciones++;
        }
        this.asignaciones+=2;
        this.comparaciones+=2;
        if(suma<=pesoMaximo)return false; //Si el peso aun no sobrepasa el limite retorna falso
        return true;//caso contrario retorna true
    }
    
    /**
     * Metodo que permite sumar el peso en una lista con los alimentos
     * @param listaActual lista con las instancias de tipo Comida
     * @return int suma de todos los pesos de todas las intancias de tipo Comida en la lista
     */
     public int obtenerSumaPeso(ArrayList<Comida> listaActual){
        int suma = 0; //Se inicializa la suma del peso en 0
        for(int i = 0; i<listaActual.size();i++){ //Se recorre la lista de alimentos
            suma+=listaActual.get(i).getPeso(); //Se suman los pesos
            this.asignaciones++;
            this.comparaciones++;
        }
        this.asignaciones+=2;
        this.comparaciones++;
        return suma;
    }
    
     /**
     * Metodo que permite sumar las calorias en una lista con los alimentos
     * @param listaActual lista con las intancias de tipo Comida
     * @return int suma de todas las calorias de todas las instancias de tipo Comida en la lista
     */
    public int obtenerSumaCalorias(ArrayList<Comida> listaActual){ 
        int suma = 0; //La suma inicia en 0
        for(int i = 0; i<listaActual.size();i++){ //Recorre toda la lista
            suma+=listaActual.get(i).getCalorias(); //Se suman las calorias
            this.comparaciones++;
            this.asignaciones++;
        }
        this.asignaciones+=2;
        this.comparaciones++;
        return suma;
    }
}
