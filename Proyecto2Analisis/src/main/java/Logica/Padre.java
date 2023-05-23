/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList;
import java.util.Random; //Importacion para utilizar el random

/**
 *
 * @author Usuario
 */
public class Padre {
    
    /**
     * Valores que van a caracterizar a los padres
     */
    private ArrayList<Comida> cromozoma; //ArrayList que contiene los alimentos.
    private int totalCalorias; //El total de calorías que tiene el padre.
    private int totalPeso; //El peso total que tiene el padre.
    private int puntuacion; //La puntuación que se le asigna al padre.
    private boolean isMutated; //Booleano que determina si el padre ha sido mutado, o no.
    private final SumaCaloriasPeso herramientasSumar = new SumaCaloriasPeso(); //Herramienta par adeterminar el peso total y calorías totales del cromozoma.
    
    long asignaciones = 0;
    long comparaciones = 0;
    long memoria = 0;
    
    /**
     * Constructor de los alimentos para los atributos individuales
     * @param cromozomas lista de alimentos
     */    
    public Padre(ArrayList<Comida> cromozomas) {
        this.cromozoma= cromozomas;
        this.totalCalorias = herramientasSumar.obtenerSumaCalorias(cromozomas);
        this.totalPeso = herramientasSumar.obtenerSumaPeso(cromozomas);
        this.puntuacion = (herramientasSumar.obtenerSumaCalorias(cromozomas)-herramientasSumar.obtenerSumaPeso(cromozomas))/cromozomas.size();
        this.isMutated = false;
        asignaciones+=6;
        this.asignaciones += herramientasSumar.getAsignaciones();
        this.comparaciones += herramientasSumar.getComparaciones();
        herramientasSumar.resetCounters();
    }
    
    /**
     * Constructor de los alimentos para un tipo 'Comida'
     * @param padre objeto de tipo (Padre)
     */
    public Padre(Padre padre) {
        this.cromozoma = padre.getCromozoma();
        this.totalCalorias = padre.getTotalCalorias();
        this.totalPeso = padre.getTotalPeso();
        this.puntuacion = padre.getPuntuacion();
        this.isMutated = padre.getMutacion();
        asignaciones+=6;
    }
    
    //*************LOS GETS DE LOS ATRIBUTOS*************
    public ArrayList<Comida> getCromozoma() {
        asignaciones++;
        return this.cromozoma;
    }
    public int getTotalCalorias() {
        asignaciones++;
        return totalCalorias;
    }
    public int getTotalPeso() {
        asignaciones++;
        return totalPeso;
    }
    public int getPuntuacion() {
        asignaciones++;
        return puntuacion;
    }
    public boolean getMutacion() {
        asignaciones++;
        return isMutated;
    }
    
    //***************LOS SETS DE LOS ATRIBUTOS*************
    public void setCromozoma(ArrayList<Comida> cromozoma) {
        asignaciones+=2;
        this.cromozoma = cromozoma;
    }
    public void setTotalCalorias(int totalCalorias) {
        asignaciones+=2;
        this.totalCalorias = totalCalorias;
    }
    public void setTotalPeso(int totalPeso) {
        asignaciones+=2;
        this.totalPeso = totalPeso;
    }
    public void setPuntuacion(int puntuacion) {
        asignaciones+=2;
        this.puntuacion = puntuacion;
    }
    
    /**
     * Metodo que resetea los contadores de asignaciones y comparacioens
    */
    public void resetCounters(){
        this.asignaciones=0;
        this.comparaciones=0;
    }
    
    /**
     * Obtiene el total de asignacioens que se hicieron durante el proceso de el vertexCover
     * @return long
     */
    public long getAsignaciones(){
        this.asignaciones++;//Por el return
        return this.asignaciones;
    }
        
    /**
     * Obtiene el total de comparaciones que se hicieron durante el proceso de el vertexCover
     * @return long
     */
    public long getComparaciones(){
        this.comparaciones++; //Por el return
        return this.comparaciones;
    }
    
    /**
     * Este método se encarga de volver a realizar la lógica para obtener la puntuación del Padre.
     */
    public void refrescarPuntuacion(){
            this.puntuacion = (herramientasSumar.obtenerSumaCalorias(this.cromozoma)-herramientasSumar.obtenerSumaPeso(this.cromozoma))/this.cromozoma.size();
            this.asignaciones += herramientasSumar.getAsignaciones();
            this.comparaciones += herramientasSumar.getComparaciones();  
            herramientasSumar.resetCounters();
    }
    
    /**
     * Método que se encarga de realizar una mutación en el cromozoma del Padre, cambia un alimento aleatorio, por otro también aleatorio.
     * @param listaActual 
     */
    public ArrayList<Comida> mutacion(ArrayList<Comida> listaActual){
        Random random = new Random(); //Se crea un objeto para generar números aleatorios.
        
        int numeroAleatorio = 0; //Numero para guardar un numero aleatorio.
        int numeroAleatorio2 = 0; //Numero para generar otro numero aleatorio.
        
        asignaciones+=4;

        do{ //Ciclo do While(true) que se repite hasta encontrar 2 números aleatorios que no generen conflicto.
            comparaciones++;
            numeroAleatorio = random.nextInt(listaActual.size()); //Se genera un número aleatorio.
            numeroAleatorio2 = random.nextInt(this.cromozoma.size()); //Se genera un segundo número aleatorio.
            asignaciones+=2;
            if(!this.cromozoma.get(numeroAleatorio2).equals(listaActual.get(numeroAleatorio)) && this.cromozoma.contains(listaActual.get(numeroAleatorio)) == false){ //Si la comida aleatoria no estaba ya contenida en el cromozoma, se realiza el cambio.
                this.cromozoma.set(numeroAleatorio2, listaActual.get(numeroAleatorio)); //Se realiza el cambio de la comida, por la nueva elegida.
                asignaciones++;
                break; //Se corta el ciclo.
            }
            comparaciones++;
        }while(true);
        
        asignaciones++;
        this.isMutated = true; //Se pone el booleano isMutated en true, pues se ha realizado la mutación correctamente.
        this.refrescarPuntuacion(); //Se actualiza la puntuación de este padre.
        
        this.asignaciones += herramientasSumar.getAsignaciones();
        this.comparaciones += herramientasSumar.getComparaciones();
        herramientasSumar.resetCounters();
        
        return this.cromozoma;
    }
    
    /**
     * Método que se encarga de realizar una mutación en el cromozoma del Padre, cambia el alimento de mayor peso, por otro aleatorio.
     * @param listaActual 
     */
    public ArrayList<Comida> mutacionCriterio(ArrayList<Comida> listaActual){
        
        Random random = new Random(); //Se crea un objeto para generar números aleatorios.
        Comida alimentoMayor = this.cromozoma.get(0); //Se obtiene el primer elemento del cromozoma.
        int posicionArreglo = 0; //Para guardar la posición del arreglo donde se encuentra el alimento con el mayor peso.

        asignaciones+=4;
        
        for(int i = 0 ; i<this.cromozoma.size() ; i++){ //Ciclo que recorre todo el cromozoma.          
            comparaciones++;
            asignaciones++;
            if(this.cromozoma.get(i).getPeso()>alimentoMayor.getPeso() ){ //Si el alimento actual tiene mayor peso que el alimentoMayor, se realiza el cambio.
                alimentoMayor = this.cromozoma.get(i); //El alimento actual se convierte en el alimentoMayor. 
                posicionArreglo = i; //Se guarda la posición actual.
                asignaciones+=2;
            }
            comparaciones++;
        }
        asignaciones++;
        
        int numeroAleatorio = 0; //Numero para guardar un numero aleatorio.
        
        asignaciones++;

        do{ //Ciclo do While(true) que se repite hasta encontrar un números aleatorios que no genere conflicto.
            comparaciones++;
            asignaciones++;
            numeroAleatorio = random.nextInt(listaActual.size()); //Se genera un número aleatorio.
            if(!alimentoMayor.equals(listaActual.get(numeroAleatorio)) && this.cromozoma.contains(listaActual.get(numeroAleatorio)) == false){ //Si la comida aleatoria no estaba ya contenida en el cromozoma, se realiza el cambio.
                this.cromozoma.set(posicionArreglo, listaActual.get(numeroAleatorio)); //Se realiza el cambio de la comida, por la nueva elegida.
                asignaciones++;
                break; //Se corta el ciclo.
            }
            comparaciones++;
        }while(true);
        asignaciones++;
        this.isMutated = true; //Se pone el booleano isMutated en true, pues se ha realizado la mutación correctamente.
        this.refrescarPuntuacion(); //Se actualiza la puntuación de este padre.
        
        this.asignaciones += herramientasSumar.getAsignaciones();
        this.comparaciones += herramientasSumar.getComparaciones();
        herramientasSumar.resetCounters();
        
        return this.cromozoma;
    }
    
    /**
     * Método que se encarga de meter el nombre de todos los alimentos del cromozoma en un string y lo retorna.
     * @return Retorna un string con los nombres de los alimentos del cromozoma.
     */
    public String toStringNombres(){
        String nombresProductos = ""; //Se crea un string vacío para concatenar.
        asignaciones++;
        for(int i = 0 ; i<this.cromozoma.size() ; i++){ //Se recorre el cromozoma.
            asignaciones++;
            comparaciones++;
            Comida alimento = this.cromozoma.get(i); //Se obtiene el alimento del ciclo actual.
            nombresProductos = nombresProductos + alimento.getNombre() + " "; //Se concatena el nombre del alimento actual al string.
            asignaciones+=2;
        }
        asignaciones+=2;
        return nombresProductos; //Se retorna el string, con los nombres de todos los alimentos del cromozoma.
    }
    
    //Fin de la clase.
}
