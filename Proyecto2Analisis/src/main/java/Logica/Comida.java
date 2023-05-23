package Logica;

/**
 * Clase encargada de crear los diferentes alimentos que se van a utilizar y sus valores caloricos y de peso en gramos
 * @author Fabricio Porras
 * @author Carlos Solis
 */
public class Comida {
    
    /**
     * Valores que van a caracterizar los alimentos
     */
    private String nombre; 
    private int calorias;
    private int peso;
        
    /**
     * Constructor de los alimentos para los atributos individuales
     * @param nombre nombre del alimento
     * @param calorias calorias del alimento
     * @param peso peso del alimento
     */    
    public Comida(String nombre, int calorias, int peso) {
        this.nombre = nombre;
        this.calorias = calorias;
        this.peso = peso;
    }
    
    /**
     * Constructor de los alimentos para un tipo 'Comida'
     * @param alimento objeto de tipo (Comida)
     */
    public Comida(Comida alimento) {
        this.nombre = alimento.getNombre();
        this.calorias = alimento.getCalorias();
        this.peso = alimento.getPeso();
    }
    
    //*************LOS GETS DE LOS ATRIBUTOS*************
    public int getCalorias() {
        return calorias;
    }
    public String getNombre() {
        return nombre;
    }
    public int getPeso() {
        return peso;
    }
    
    //***************LOS SETS DE LOS ATRIBUTOS*************
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * Funcion encargada de concatenar y mostrar los atributos de la instancia
     * @return la informacion del alimento
     */
    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\nCalorias: "+this.calorias+"\nPeso: "+this.peso+"g\n"; 
    }
}