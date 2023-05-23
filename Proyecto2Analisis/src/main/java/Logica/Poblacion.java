/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.util.ArrayList; //Importacion para utilizar los arreglos.
import java.util.Random; //Importacion para utilizar el random.
import java.util.*; //Importación de varias funcionalidades necesarias.

/**
 *
 * @author Usuario
 */
public class Poblacion {
    
    long comparaciones = 0;
    long asignaciones = 0;
    long memoria = 0;
    
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
     * Método que se encarga de realizar una población válida para utilizar en el algoritmo genético.
     * @param ListaActual La lista de alimentos que se necesita para generar la población.
     * @param caloriasMinimas El número de calorías mínimas que se debe llegar para cada padre.
     * @param pesoMaximo El número de peso máximo el cual no debe exceder cada padre.
     * @return Retorna una lista con los padres correctamente generados.
     */
    public ArrayList<Padre> GenerarPoblacion(ArrayList<Comida> ListaActual, int caloriasMinimas, int pesoMaximo){
        
        asignaciones+=3;

        Random random = new Random(); //Se genera un objeto de la clase Random.
        SumaCaloriasPeso herramientasSumar = new SumaCaloriasPeso(); //Clase que agrega herramientas para sumar las calorias y peso
        int tamañoPoblacion = 1; //Se inicializa un numero para guardar el tamaño que debe tener la población.
        ArrayList<Padre> listaPoblacion = new ArrayList<Padre>(); //Se crea un ArrayList donde se guardarán los padres correctamente generados.
        
        asignaciones+=4;
        
        if(ListaActual.size()==6) { //Caso 6 el tamaño de la población será de 20.
            tamañoPoblacion = 10; //Se pone el tamaño de la población en 20.
            asignaciones++;
        }
        else if(ListaActual.size()==8){ //Caso 8 el tamaño de la población será de 100.
            tamañoPoblacion = 40; //Se pone el tamaño de la población en 100.
            asignaciones++;
        }
        else if(ListaActual.size()==12){ //Caso 12 el tamaño de la población será de 200.
            tamañoPoblacion = 200; //Se pone el tamaño de la población en 200.
            asignaciones++;
        }        
        else if(ListaActual.size()==16){ //Caso 16 el tamaño de la población será de 200.
            tamañoPoblacion = 200; //Se pone el tamaño de la población en 200.
            asignaciones++;
        }
        else{
            System.out.println("El algoritmo no acepta ese tamaño de la lista");
            asignaciones++;
            return null;
        }
        
        
        
        comparaciones+=4;
        
        HashSet<Comida> comidasUnicasGeneral = new HashSet<Comida>(); //Se crea un HashSet de tipo comida donde se guardarán comidas que no deben repetirse.
        
        asignaciones++;
        
        for(int p = 0 ; p<ListaActual.size() ; p++){ //Un ciclo que recorre toda la ListaActual.
            asignaciones++;
            comparaciones++;
            
            comidasUnicasGeneral.add(ListaActual.get(p)); //Se añade en el HashSet la comida actual del ciclo.   
            asignaciones++;
        }
        asignaciones++;        
        
        for(int i = 0; i<=tamañoPoblacion; i++){ //Ciclo que se repite por el número del tamaño de población indicado.
            asignaciones++;
            comparaciones++;
            ArrayList<Comida> ListaModificable = new ArrayList<>(); //Se crea un ArrayList al que se le puede añadir y elimilar comidas.
            ArrayList<Comida> ListaTemporal = new ArrayList<>(); //Se crea un ArrayList para añadir una combinación de comidas.
            boolean flag = false; //Se crea un booleano seteado en false.
            
            asignaciones+=3;
            
            for(int r = 0 ; r < ListaActual.size() ; r++){ //Se recorre la ListaActual.
                comparaciones++;
                asignaciones++;
                
                Comida elemento = ListaActual.get(r); //Se obtiene el elemento actual del ciclo.
                ListaModificable.add(elemento); //Se añade este elemento a la lista modificable.
                asignaciones+=2;
            }
            asignaciones++;
            
            do{//Un ciclo que se repite hasta que se encuentra un alimento válido.
                if(ListaModificable.isEmpty()){ //Si la lista modificable está vacía, se sale del ciclo.
                 break;
                }
                comparaciones++;
                
                int numeroAleatorio = (int)(Math.random() * ListaModificable.size()); //Se obtiene un número aleatorio.
                Comida candidato = ListaModificable.get(numeroAleatorio); //Se obtiene una comida en la posición del número aleatorio.
                
                asignaciones+=2;
                
                if(!ListaTemporal.contains(candidato)){ //Si la lista no contiene la comida elegida, se añade a la lista tempora, y se elimina de la lista modificable para no volver a elegir el mismo alimento.
                    ListaModificable.remove(numeroAleatorio); //Se elimina el alimento encontrado de la lista modificable.
                    ListaTemporal.add(candidato); //Se añade el alimento encontrado a la lista temporal.
                    asignaciones+=2;
                }
                comparaciones++;
                
                if(herramientasSumar.sumaPeso(ListaTemporal, pesoMaximo)){ //Si la suma del peso de la lista temporal excede el peso máximo, elimina el útlimo elemento añadido de la lista.
                    ListaTemporal.remove(ListaTemporal.size()-1); //Se elimina el último elemento de la lista temporal.
                    asignaciones++;
                }
                comparaciones++;

                if(herramientasSumar.sumaCalorias(ListaTemporal, caloriasMinimas)){ //Si la suma de calorías de la lista temporal satisface el mínimo necesitado, se sale del ciclo.
                    break; //Se sale del ciclo.
                }
                comparaciones++;
                
                comparaciones++; //Por el while true
            }while(true);
            
            Padre padreCandidato = new Padre(ListaTemporal); //Se crea un nuevo Padre con la lista temporal.
            
            this.asignaciones+= padreCandidato.getAsignaciones();
            
            this.comparaciones+= padreCandidato.getComparaciones();
            
            HashSet<Comida> comidasUnicas = new HashSet<Comida>(); //Se crea un HashSet para añadir comidas únicas.
            
            asignaciones+=2;
            
            for(int n = 0 ; n<padreCandidato.getCromozoma().size() ; n++){ //Se recorre el cromozoma del padre creado.
                comparaciones++;
                asignaciones++;
                
                comidasUnicas.add(padreCandidato.getCromozoma().get(n)); //Se añade la comida actual del cromozoma al HashSet.            
                asignaciones++;
            }
            asignaciones++;
  
            for(int x = 0 ; x<listaPoblacion.size() ; x++){ //Se recorre la listaPoblacion.
                asignaciones++;
                comparaciones++;
                
                int contadorMaximo = 0; //Un contador para determinar el numero de coincidencias del HashSet y los diferentes padres de la listaPoblacion.
                ArrayList<Comida> cromozomaRevisado = listaPoblacion.get(x).getCromozoma(); //Se obtiene el cromozoma del padre actualde del ciclo.
                
                asignaciones++;
                
                for(int a = 0 ; a<cromozomaRevisado.size() ; a++){ //Se recorre el cromozoma revisado.
                    asignaciones++;
                    comparaciones++;
                    if(comidasUnicas.contains(cromozomaRevisado.get(a)) ){ //Si el hashSet contiene la comida actual del cromozoma revisado, se suma uno al contador.
                        contadorMaximo+=1; //Se suma uno al contador.  
                        asignaciones++;
                    }
                    if(contadorMaximo==comidasUnicas.size()){ //Si el contador se igual al size del HashSet, significa que hay un otro padre igual en la listaPoblacion.
                        flag = true; //Se setea el booleano flag a true. 
                        asignaciones++;
                    }
                    comparaciones+=2;
                }
                asignaciones++;
            }
            asignaciones++;
            
            if(herramientasSumar.sumaCalorias(ListaTemporal, caloriasMinimas) && flag == false){ //Si la suma de las calorías del padreCandidato satisface el mínimo necesario, y la flag es igual a false, se añade el padre candidato a la población.
                listaPoblacion.add(padreCandidato); //Se añade el padreCandidato a la listaPoblacion.
                asignaciones++;
            }
            else{
                i = i - 1; //El index se disminuye en uno, pues no se añadió ningún nuevo padre.
                System.out.println("Si aparece este mensaje de forma repetida, no se encontraron suficientes combinaciones que satisfacen el minimo de poblacion establecido");
            }
            comparaciones++;
        }
        asignaciones++;
    
        this.asignaciones += herramientasSumar.getAsignaciones();
        this.comparaciones += herramientasSumar.getComparaciones();
        
        System.out.println("--------------------Población Generada------------------------");
        for(int k = 0 ; k<listaPoblacion.size() ; k++){ //Se recorre la listaPoblacion.
            
            Padre padre = listaPoblacion.get(k); //Se obtiene el padre actual de la listaPoblacion.
            ArrayList<Comida> cromozoma = padre.getCromozoma(); //Se obtiene el cromozoma del padre.
            System.out.println(":D Cromozoma: "+ k + ", Calorias Totales: " + padre.getTotalCalorias() + ", Peso total: " + herramientasSumar.obtenerSumaPeso(cromozoma) + ", Puntuacion: " + padre.getPuntuacion()); //Se realiza una impresión de toda la información pertinente del padre.
            
            for(int l = 0 ; l<cromozoma.size() ; l++){ //Se recorre el cromozoma actual.
                Comida comida = cromozoma.get(l); //Se obtiene la comida actual del cromozoma.
                System.out.println("\nNombre: " + comida.getNombre() + ", calorias: " + comida.getCalorias() + ", peso: " + comida.getPeso()); //Se realiza una impresión de toda la información pertinente de la comida.
            }       
        }  
        return listaPoblacion; //Se retorna la listaPoblacion.
    }
    
    
    
}
