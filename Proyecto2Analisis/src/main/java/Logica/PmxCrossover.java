/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList; //Importacion para utilizar los ArrayList.
import java.util.HashSet; //Importacion para utilizar los HashSet.
import java.util.Random; //Importacion para utilizar el Random.

/**
 *
 * @author Usuario
 */
public class PmxCrossover 
{
    
    long asignaciones = 0;  
    long comparaciones = 0;    
    long memoria = 0;
    
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
     * Metodo que resetea los contadores de asignaciones y comparacioens
    */
    public void resetCounters(){
        this.asignaciones=0;
        this.comparaciones=0;
    } 
    
    /**
     * Método que se encarga de preparar el cruce.
     * @param padre1 Primer padre que se cruzará.
     * @param padre2 Segundo Padre que se cruzará.
     * @return  Se retorna un Array con los 2 hijos generados.
     */
    public ArrayList<ArrayList<Comida>> crossover(Padre padre1, Padre padre2)
    {  
        
        asignaciones+=2;
        
        ArrayList<Comida> firstParent = padre1.getCromozoma(); //Se obtiene el cromozoma del primer padre
        ArrayList<Comida> secondParent = padre2.getCromozoma(); //Se obtiene el cromozoma del segundo padre
        
        asignaciones+=2;
        
        int length = firstParent.size(); //Se toma el size del primer padre.
        
        asignaciones+=2;
                
        if(secondParent.size()<firstParent.size()){ //Si el segundo padrre es más pequeño, se realiza el cambio.
            length = secondParent.size(); //Se toma el size del segundo padre en lugar del del primero.
            asignaciones++;
        }
        else{
            length = firstParent.size();//Se toma el size del primer padre.
            asignaciones++;
        }          
        
        comparaciones++;
        
        Random rand = RandomNumberGenerator.getRandom(); //Se obtiene el Random de la calse RandomNumberGenerator.
        int start = rand.nextInt(length); //Se genera un numero aleatorio como punto de inicio.
        int end = rand.nextInt(length); //Se genera un numero aleatorio como punto final.
        int temp; //Se crea un int temporal
        int current; //Se crea un segundo int
        
        ArrayList<ArrayList<Comida>> children = new ArrayList<ArrayList<Comida>>(); //Se crea un ArrayList de una estructura equivalente a un Padre.
        
        asignaciones+=6;
        
        // If start is before end, swap start and end. 
        if (start > end) //Si el punto inicial es mayor al final, se intercambian.
        {
            temp = start; //El temporal pasa a ser el inicial.
            start = end; //El inicial pasa a ser el final.
            end = temp; //El final pasa a ser el temporal.
            asignaciones+=3;
        }
        
        comparaciones++;
        
        children.add(crossoverhelper(firstParent, secondParent, start, end)); //Se llama a la función encargada de realizar el cruce y se añade el primer hijo.
        children.add(crossoverhelper(secondParent, firstParent, start, end)); //Se llama a la función encargada de realizar el cruce y se añade el segundo hijo.
        
        asignaciones+=3;
        
        return children; //Se retorna el ArrayList con los hijos. 
    }
    
    /**
     * Método que realiza el cruce PMX.
     * @param firstParent El primer padre a cruzar.
     * @param secondParent El segundo padre a cruzar.
     * @param start El punto inicial del segmento copiado.
     * @param end Punto final del segmento copiado.
     * @return Retorna la lista de comidas cruzadas del hijo resultante.
     */
    private ArrayList<Comida> crossoverhelper(ArrayList<Comida> firstParent, ArrayList<Comida> secondParent, int start, int end)
    {
        asignaciones+=4;

        ArrayList<Comida> child = new ArrayList<Comida>(); //Array list donde se guardará el hijo resultate.
        HashSet<Comida> points_in_child = new HashSet<Comida>(); //Se crea un HashSet con los puntos del segmento a copiar.
        int size = firstParent.size(); //Se toma el tamaño del primer padre.
        
        asignaciones+=3;
        
        if(secondParent.size()<firstParent.size()){ //Si el tamaño del segundo padre es menor, se realiza el cambio.
            size = secondParent.size(); //Se toma el tamaño del segundo Padre.
            asignaciones++;
        }
        else if(secondParent.size()>firstParent.size()){ //Si el tamaño del primer padre es menor, se realiza el cambio.
            size = firstParent.size(); //Se toma el tamaño del sprimer Padre.
            asignaciones++;
        } 
        
        comparaciones+=2;
        
        int child_size = 0; //Int para guardar el tamaño del hijo resultante.
        int parent_index; //Index del padre.
        int child_index; //Index del hijo.
        
        asignaciones++;
        
        // Child starts off filled with null points    
        for (int i = 0; i < size; i++) //Ciclo que se realiza las veces del tamaño del padre elegido.
        {
            asignaciones++;
            comparaciones++;
            child.add(null); //Se añade un valor nulo en el Array para el hijo creado.
            asignaciones++;
        }
        asignaciones++;
        
        // Copy specified section from first parent to child
        for (int j = start; j < end; j++) //Se copia el segmento especificado del primer padre al hijo.
        {
            asignaciones++;
            comparaciones++;
            child.set(j, firstParent.get(j));
            // Add the points in the child to the set. 
            points_in_child.add(child.get(j));
            child_size += 1;
            asignaciones+=3;
        }
        asignaciones++;
        
        // New stuff for PMX crossover.
        for (int k = start; k < end; k++)  //Se realiza un ciclo para llenar el hijo con los elementos según corresponda.
        {
            comparaciones++;
            asignaciones++;
            
            // If point at index k is not in the child. 
            if (points_in_child.contains(secondParent.get(k)) == false) //Si la comida en el index actual no está en el HashSet, se realiza.
            {
                // Get the point we want to add. 
                Comida point_to_add = secondParent.get(k); //Se obtiene la comida que se quiere usar.
                // Find the point at that position in the parent, as
                // that is the point whose position in sequence we want to place
                // the above point in. 
                Comida point_to_replace = child.get(k); //Se obtiene el punto que se le añadirá la comida.
                
                asignaciones+=2;
                
                // Find the position of point_to_replace. 
                for (int l = 0; l < size; l++) //Ciclo que se realiza hasta encotnrar el punto en el que se quiere hacer el cambio.
                {
                    asignaciones++;
                    comparaciones++;
                    
                    if (secondParent.get(l) == point_to_replace) //Si el punto actual es el buscado, se realiza los siguiente.
                    {
                        // If this location is null in the child,
                        // Put the point there. 
                        if (child.get(l) == null) //Se añade la comida si el punto actual es nulo.
                        {
                            child.set(l, point_to_add);
                            points_in_child.add(child.get(l));
                            child_size += 1;
                            asignaciones+=3;
                        }
                        comparaciones++;
                    }
                    comparaciones++;
                }
                asignaciones++;
            }
            comparaciones++;
        }
        asignaciones++;
        parent_index = end; //Se pone el parent index al final del segmento a copiar.
        child_index = end; //Se pone el child index al final del segmento a copiar.
        
        asignaciones+=2;
        
        while(child_size < size) //Mientras que el tamaño del hijo sea menor que el del padre elegido.
        {
            comparaciones++;
            
            // Find a valid location to place the next point. 
            if (child.get(child_index) != null) //Se busca una nueva posciión válida donde realizar cambios.
            {       
                child_index = (child_index + 1) % size;
                asignaciones++;
                continue;
            }
            
            comparaciones++;
            
            // If the current point in the parent is already in the child,
            // move to the next point. 
            if (points_in_child.contains(secondParent.get(parent_index)) == true) //Si el punto ya está en el padre, se mueve al siguiente.
            {
                parent_index = (parent_index + 1) % size;
                asignaciones++;
                continue;
            }
           
            comparaciones++;
            
            // Add the next point to the child. 
            child.set(child_index, secondParent.get(parent_index)); //Se añade la comida en el punto.
            points_in_child.add(child.get(child_index));
            
            // Update the child and parent indexes. 
            parent_index = (parent_index + 1) % size; //Se actualizan los index del padre y el hijo.
            child_index = (child_index + 1) % size;
            child_size += 1;
            
            asignaciones+=5;
            
        }
        
        ArrayList<Comida> result = child; //El resultado es igual al hijo resultante.
        
        asignaciones+=2;
        
        return result; //Se retorna el resultado.
    }
//Fin de la clase    
}
