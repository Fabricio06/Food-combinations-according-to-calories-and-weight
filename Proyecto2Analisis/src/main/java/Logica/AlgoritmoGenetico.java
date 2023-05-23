/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList; //Importacion para utilizar los ArrayList
import java.util.HashSet; //Importacion para utilizar los HashSet
import java.util.Random; //Importacion para utilizar el random.

/**
 *
 * @author Usuario
 */
public class AlgoritmoGenetico{
    private int caloriasMinimas; //Calorias minimas que se necesitan para completar los requisitos.
    private int pesoMaximo; //Peso maximo que se soporta para completar los requisitos.
    private ArrayList<Comida> listaAlimentos = new ArrayList<>(); //Lista con todos los alimentos.
    private final SumaCaloriasPeso herramientasSumar = new SumaCaloriasPeso(); //Clase que agrega herramientas para sumar las calorias y peso.   
    private ArrayList<Padre> listaPreMutados = new ArrayList<Padre>();
    private ArrayList<Padre> listaMutados = new ArrayList<Padre>();
    private ArrayList<ArrayList<Padre>> MejoresPoblaciones = new ArrayList<ArrayList<Padre>>(5);
    
    long asignaciones = 0;
    long comparaciones = 0;
    long memoria = 0;
    
    /**
     * Función que genera una instancia del algoritmo genético
     * @param caloriasMinimas
     * @param pesoMaximo
     * @param listaAlimentos 
     */
    public AlgoritmoGenetico(int caloriasMinimas, int pesoMaximo, ArrayList<Comida> listaAlimentos) {
        this.caloriasMinimas = caloriasMinimas;
        this.pesoMaximo = pesoMaximo;
        this.listaAlimentos = listaAlimentos;
    }
    
    //*************LOS GETS DE LOS ATRIBUTOS*************
    public int getCalorias_minimas() {
        this.asignaciones++;
        return caloriasMinimas;
    }
    public ArrayList<Comida> getLista_alimentos() {
        this.asignaciones++;
        return listaAlimentos;
    }
    public int getPeso_maximo() {
        this.asignaciones++;
        return pesoMaximo;
    }

    //***************LOS SETS DE LOS ATRIBUTOS*************
    public void setCalorias_minimas(int caloriasMinimas) {
        this.caloriasMinimas = caloriasMinimas;
        this.asignaciones++;
    }
    public void setLista_alimentos(ArrayList<Comida> listaAlimentos) {
        this.listaAlimentos = listaAlimentos;
        this.asignaciones++;
    }
    public void setPeso_maximo(int pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
        this.asignaciones++;
    }
    
    public int puntuacionPoblacion(ArrayList<Padre> poblacionAnalizada){
        int puntosTotales = 0;
        this.asignaciones+=2;
    
        for(int i = 0 ; i<poblacionAnalizada.size() ; i++){
            asignaciones++;
            comparaciones++;
            int puntosPadre = poblacionAnalizada.get(i).getPuntuacion();
            puntosTotales = puntosTotales + puntosPadre;
            asignaciones+=2;
        }
        asignaciones+=2;
        return puntosTotales;
    }
    
    /**
     * Función que inicializa todos los componentes para el correcto funcionamiento del algoritmo genético.
     */
    public void funcionGenetica(){
    Instant startTime = Instant.now();// gets current time as bruteForce start time
        
    Random random = new Random(); //Se genera una instancia para generar numeros randomizados.
    Poblacion poblacion = new Poblacion(); //Se cre una instancia de la  clase población.
    
    ArrayList<Padre> PoblacionObjetivo = poblacion.GenerarPoblacion(listaAlimentos, caloriasMinimas, pesoMaximo); //Se llama a la función GenerarPoblación, con la lista de alimentos, las calorías mínimas y peso máximo necesitado. Y devuelve una lista con los padres a analizar.
    ArrayList<ArrayList<Padre>> CompetenciaPoblaciones = new ArrayList<ArrayList<Padre>>();
    
    if(PoblacionObjetivo==null){ 
        asignaciones++;
        return;
    }
    comparaciones++;
    
    this.asignaciones += poblacion.getAsignaciones();
    
    this.comparaciones += poblacion.getComparaciones();
    
    PmxCrossover pmxcrossver = new PmxCrossover(); //Se crea una instancia de la clase del cruce PMX.
    
    int repeticionCantidadHijos = 0; //Contador para guardar la cantidad de ciclos necesarios para realizar la creacion de los hijos necesitados.
    int cantidadGeneraciones = 0;//Contador para guardar la cantidad de ciclos necesarios para generar el numero de generaciones necesitado.   
    
    CompetenciaPoblaciones.add(PoblacionObjetivo);
    
    asignaciones+=8;
    
    switch (listaAlimentos.size()) { //Un swtich para analizar los diferentes casos en los que la cantidad de hijos y generaciones varían.
        case 6:
            repeticionCantidadHijos = 20; //La cantidad de hijos serán 40.
            cantidadGeneraciones = 10; //La cantidad de generaciones serán 10.
            asignaciones+=2;
            break;
        case 8:
            repeticionCantidadHijos = 50; //La cantidad de hijos serán 100.
            cantidadGeneraciones = 50; //La cantidad de generaciones serán 50.
            asignaciones+=2;
            break;
        case 12:
            repeticionCantidadHijos = 100; //La cantidad de hijos serán 200.
            cantidadGeneraciones = 50; //La cantidad de generaciones serán 50.
            asignaciones+=2;
            break;
        case 16:
            repeticionCantidadHijos = 100; //La cantidad de hijos serán 200.
            cantidadGeneraciones = 50; //La cantidad de generaciones serán 50.
            asignaciones+=2;
            break;
        default:
            break;
    }

    ArrayList<Padre> listaCompeticion = new ArrayList<>(); //Lista en la que se ingresarán tanto los padres como los hijos de estos y competirán todos para obtener los mejores ejemplares.
    
    asignaciones++;
    
    System.out.println("------------------------------------Cruces para Cada Generación------------------------------------");
    
    for(int i = 0 ; i<=cantidadGeneraciones ; i++){ //Ciclo para realizar la cantidad de generaciones indicada.
        
        System.out.println("\nGeneración: " + (i+1)); //Print con el contador para indicar el numero cada generacion.
        System.out.println("-------------------------------------------------------------");
        
        for(int j = 0 ; j <= repeticionCantidadHijos ; j++ ){ //Ciclo para realizar la cantidad de hijos indicada.
            int numeroAleatorio = 0; //Variable para guardar un numero aleatorio.
            int numeroAleatorio2 = 0; //Variable para guardar otro numero aleatorio.
            asignaciones+=2;
            do{ //Ciclo do While(true) para encontrar dos numeros random distintos.
                comparaciones++;
                numeroAleatorio = random.nextInt(PoblacionObjetivo.size()); //Se genera un numero aleatiorio con el objeto del Random.
                numeroAleatorio2 = random.nextInt(PoblacionObjetivo.size()); //Se genera un numero aleatiorio con el objeto del Random.
                asignaciones+=2;
                if(numeroAleatorio!=numeroAleatorio2){ //Si los dos numeros Random generados son distintos, se hace un break del do While pra utilizar esos numeros.
                    break;//Se hace un break del ciclo do While.
                }   
                comparaciones++;
            }while(true);
            
            Padre padre1 = PoblacionObjetivo.get(numeroAleatorio); //Se obtiene un Padre en la posicion de primer numero aleatorio generado de la PoblacionObjetivo.
            Padre padre2 = PoblacionObjetivo.get(numeroAleatorio2); //Se obtiene un Padre en la posicion de segundo numero aleatorio generado de la PoblacionObjetivo.
            
            asignaciones+=2;
            
            System.out.println("\nPadre " + (j+1) + ": " + padre1.toStringNombres() + ", Puntuacion: " + padre1.getPuntuacion()); //Print del primer padre obtenido.
            System.out.println("\nPadre " + (j+2) + ": " + padre2.toStringNombres() + ", Puntuacion: " + padre2.getPuntuacion()); //Print del segundo padre obtenido.
            
            ArrayList<ArrayList<Comida>> hijos = pmxcrossver.crossover(padre1, padre2); //Se genera el cruce PMX con los dos padres obtenidos, y se crea un Arraycon los 2 hijos generados.
            
            Padre nuevoHijo = new Padre(hijos.get(0)); //Se obtiene el primer hijo de la lista.
            Padre nuevoHijo2 = new Padre(hijos.get(1)); //Se obtiene el segundo hijo de la lista.
            
            asignaciones+=3;
            
            System.out.println("\nHijo: " + (j+1) +  ": " + nuevoHijo.toStringNombres() + ", Puntuacion: " + nuevoHijo.getPuntuacion()); //Se hace un print del primer hijo, sus alimentos y su puntuación.
            System.out.println("\nHijo: " + (j+2) + ": " + nuevoHijo2.toStringNombres() + ", Puntuacion: " + nuevoHijo2.getPuntuacion()); //Se hace un print del segundo hijo, sus alimentos y su puntuación.
            
            if(herramientasSumar.sumaCalorias(nuevoHijo.getCromozoma(), caloriasMinimas)==false){ //Si la suma de calorías no satisface el mínimo deseado, se muta.
                ArrayList<Comida> cromozoma = new ArrayList<Comida>(nuevoHijo.getCromozoma());
                Padre padreIntacto = new Padre(cromozoma);
                this.listaPreMutados.add(padreIntacto); 
                ArrayList<Comida> cromozomaMutado = nuevoHijo.mutacion(listaAlimentos); //Se realiza una mutación normal en el primer hijo.
                Padre padreMutado = new Padre(cromozomaMutado);
                this.listaMutados.add(padreMutado);
                asignaciones+=6;
            }
            else if(herramientasSumar.sumaPeso(nuevoHijo.getCromozoma(), pesoMaximo)==true){ //Si la suma del peso excede el máximo deseado, se muta.
                ArrayList<Comida> cromozoma = new ArrayList<Comida>(nuevoHijo.getCromozoma());
                Padre padreIntacto = new Padre(cromozoma);
                this.listaPreMutados.add(padreIntacto);          
                ArrayList<Comida> cromozomaMutado = nuevoHijo.mutacionCriterio(listaAlimentos); //Se realiza una mutación normal en el primer hijo.
                Padre padreMutado = new Padre(cromozomaMutado);
                this.listaMutados.add(padreMutado);
                asignaciones+=6;
            }
            
            comparaciones+=2;
            
            if(herramientasSumar.sumaCalorias(nuevoHijo2.getCromozoma(), caloriasMinimas)==false){ //Si la suma de calorías no satisface el mínimo deseado, se muta.
                ArrayList<Comida> cromozoma = new ArrayList<Comida>(nuevoHijo2.getCromozoma());
                Padre padreIntacto = new Padre(cromozoma);
                this.listaPreMutados.add(padreIntacto); 
                ArrayList<Comida> cromozomaMutado = nuevoHijo2.mutacion(listaAlimentos); //Se realiza una mutación normal en el primer hijo.
                Padre padreMutado = new Padre(cromozomaMutado);
                this.listaMutados.add(padreMutado);  
                asignaciones+=6;
            }
            else if(herramientasSumar.sumaPeso(nuevoHijo2.getCromozoma(), pesoMaximo)==true){ //Si la suma del peso excede el máximo deseado, se muta.
                ArrayList<Comida> cromozoma = new ArrayList<Comida>(nuevoHijo2.getCromozoma());
                Padre padreIntacto = new Padre(cromozoma);
                this.listaPreMutados.add(padreIntacto);          
                ArrayList<Comida> cromozomaMutado = nuevoHijo2.mutacionCriterio(listaAlimentos); //Se realiza una mutación normal en el primer hijo.
                Padre padreMutado = new Padre(cromozomaMutado);
                this.listaMutados.add(padreMutado); 
                asignaciones+=6;
            }    
            
            comparaciones+=2;
            
            if((herramientasSumar.sumaCalorias(nuevoHijo.getCromozoma(), caloriasMinimas)==true)&&(herramientasSumar.sumaPeso(nuevoHijo.getCromozoma(), pesoMaximo)==false)){ //Si el primer hijo satisface el minimo de calorias y no excede el peso máximo, se realiza el análisis para comprobar si se puede insertar en la listaCompetición.
                
                HashSet<Comida> comidasUnicas = new HashSet<Comida>(); //Se crea un HashSet de tipo Comida.
                boolean flag = false; //Se crea una flag de tipo booleano.
                
                asignaciones+=2;
                
                for(int n = 0 ; n<nuevoHijo.getCromozoma().size() ; n++){ //Ciclo para añadir al HashSet creado, todos los alimentos del primer hijo.
                    asignaciones++;
                    comparaciones++;
                    comidasUnicas.add(nuevoHijo.getCromozoma().get(n)); //Se añade al HashSet la comida actual del primer hijo.       
                    asignaciones++;
                }
                
               asignaciones++; 
                
                for(int x = 0 ; x<PoblacionObjetivo.size() ; x++){ //Ciclo para reccorrer todos los Padres de la lista PoblaciónObjetivo.
                    asignaciones++;
                    comparaciones++;
                    
                    int contadorMaximo = 0; //Contador para guardar el numero de alimentos que coinciden con los contenidos en el HashSet.
                
                    ArrayList<Comida> cromozomaRevisado = PoblacionObjetivo.get(x).getCromozoma(); //Se obtiene el cromozoma con las comidas del Padre actual del ciclo.
                
                    asignaciones+=2;
                    
                    for(int a = 0 ; a<cromozomaRevisado.size() ; a++){ //Ciclo para recorrer todos los alimentos del cromozoma actual.
                    
                        if(comidasUnicas.contains(cromozomaRevisado.get(a)) ){ //Si el HashSet contiene la comida actual se aumenta el contador.
                            contadorMaximo+=1; //Se aumenta en 1 el contador.
                            asignaciones++;
                        }
                        comparaciones++;
                        if(contadorMaximo==comidasUnicas.size()){ //Si el contadorMáximo  iguala el size del HashSet, significa que ya hay un Padre en laP oblaciónObjetivo que tiene los mismos alimentos.
                            flag = true; //Se pone el boolean flag en true, pues se encontró un padre con el mismo cromozoma de comidas.                  
                            asignaciones++;
                        }
                        comparaciones++;
                    }
                    asignaciones++;
                }
                asignaciones++;
                
                if(flag==false){ //Si flag está en false se realiza la inclusión del hijo en la lista Competición.
                    listaCompeticion.add(nuevoHijo); //Se añade el primer hijo a la lista Competición.
                }
                comparaciones++;
            }
            
            comparaciones+=2;
            
            if((herramientasSumar.sumaCalorias(nuevoHijo2.getCromozoma(), caloriasMinimas)==true)&&(herramientasSumar.sumaPeso(nuevoHijo2.getCromozoma(), pesoMaximo)==false)){ //Si el segundo hijo satisface el minimo de calorias y no excede el peso máximo, se realiza el análisis para comprobar si se puede insertar en la listaCompetición.
                HashSet<Comida> comidasUnicas = new HashSet<Comida>(); //Se crea un HashSet de tipo Comida.
                boolean flog = false; //Se crea una flag de tipo booleano.
                asignaciones+=2;
                for(int n = 0 ; n<nuevoHijo2.getCromozoma().size() ; n++){ //Ciclo para añadir al HashSet creado, todos los alimentos del segundo hijo.
                    asignaciones++;
                    comparaciones++;                    
                    comidasUnicas.add(nuevoHijo2.getCromozoma().get(n)); //Se añade al HashSet la comida actual del segundo hijo.          
                    asignaciones++;
                }
                asignaciones++;
            
                for(int x = 0 ; x<PoblacionObjetivo.size() ; x++){ //Ciclo para reccorrer todos los Padres de la lista PoblaciónObjetivo.
                    asignaciones++;
                    comparaciones++;
                    
                    int contadorMaximo = 0; //Contador para guardar el numero de alimentos que coinciden con los contenidos en el HashSet.
                
                    ArrayList<Comida> cromozomaRevisado = PoblacionObjetivo.get(x).getCromozoma(); //Se obtiene el cromozoma con las comidas del Padre actual del ciclo.
                    asignaciones+=2;
                    for(int a = 0 ; a<cromozomaRevisado.size() ; a++){ //Ciclo para recorrer todos los alimentos del cromozoma actual.
                    
                        if(comidasUnicas.contains(cromozomaRevisado.get(a)) ){ //Si el HashSet contiene la comida actual se aumenta el contador.
                            contadorMaximo+=1; //Se aumenta en 1 el contador.  
                            asignaciones++;
                        }
                        if(contadorMaximo==comidasUnicas.size()){ //Si el contadorMáximo  iguala el size del HashSet, significa que ya hay un Padre en laP oblaciónObjetivo que tiene los mismos alimentos.
                            flog = true; //Se pone el boolean flag en true, pues se encontró un padre con el mismo cromozoma de comidas.                   
                            asignaciones++;
                        }
                        comparaciones+=2;
                    } 
                    asignaciones++;
                }
                asignaciones++;
                
                if(flog==false){ //Si flag está en false se realiza la inclusión del segundo hijo en la lista Competición.
                    listaCompeticion.add(nuevoHijo2); //Se añade el segundo hijo a la lista Competición.
                }
            }
            comparaciones+=2;
            
        this.asignaciones+=padre1.getAsignaciones();
        this.comparaciones+=padre2.getComparaciones();
            
        this.asignaciones+=nuevoHijo.getAsignaciones();
        this.comparaciones+=nuevoHijo2.getComparaciones();
            
        padre1.resetCounters();
        padre2.resetCounters();
            
        nuevoHijo.resetCounters();
        nuevoHijo2.resetCounters();
            
        }
        asignaciones++;
        
        this.asignaciones+=pmxcrossver.getAsignaciones();
        this.comparaciones+=pmxcrossver.getComparaciones();
        
        for(int x = 0 ; x<PoblacionObjetivo.size() ; x++){ //Se recorre la lista de PoblaciónObjetivo
            asignaciones++;
            comparaciones++;
            listaCompeticion.add(PoblacionObjetivo.get(x)); //Se realiza la inclusión del Padre actual del ciclo.
            asignaciones++;
        }
            
        ArrayList<Padre> NuevaPoblacionObjetivo = new ArrayList<Padre>(); //Se crea un nuevo ArrayList donde guardar la nueva población objetivo.
        asignaciones++;    
        for(int y = 0 ; y<PoblacionObjetivo.size() ; y++){ //Se repite el ciclo la cantidad de Padres que hay en la PoblaciónObjetivo.
            asignaciones++;
            comparaciones++;
            Padre mejorCandidato = listaCompeticion.get(0); //Se obtiene el primer Padre de la listaCompeticion.
            int posicionArreglo = 0; //Se crea una variable para guardar una posición de un arreglo.
            asignaciones+=2;
            for(int z = 0 ; z<listaCompeticion.size() ; z++){ //Se recorre la listaCompeticion.
            asignaciones++;
            comparaciones++;               
                if(listaCompeticion.get(z).getPuntuacion()>mejorCandidato.getPuntuacion()){ //Si el la puntuación del Padre actual del ciclo, es mayor al mejorCandidato, se convierte en el nuevo mejorCandiato.
                    mejorCandidato = listaCompeticion.get(z);//Se convierte el Padre actual, en el nuevo mejorCandidato.
                    posicionArreglo = z; //Se guarda la posición del arreglo en el que se encontró el nuevo mejorCandidato.
                    asignaciones+=2;
                }
                comparaciones++;
            }
            asignaciones++;
            NuevaPoblacionObjetivo.add(mejorCandidato); //Se añade el mejorCandidato a la nueva PoblaciónObjetivo.
            listaCompeticion.remove(posicionArreglo); //Se quita el mejorCandidato encontrado para no volver a elegirlo.
            asignaciones+=2;
        } 
        asignaciones++;

        CompetenciaPoblaciones.add(NuevaPoblacionObjetivo);
        
        PoblacionObjetivo = NuevaPoblacionObjetivo; //La PoblaciónObjetivo se convierte en la nueva PoblaciónObjetivo.
        asignaciones++;
    }
    asignaciones++;
  
    System.out.println("\n------------------------------------Poblaciones Optimizadas------------------------------------");
    for(int k = 0 ; k<PoblacionObjetivo.size() ; k++){ //Se recorre la PoblaciónObjetivo.
            
        Padre padre = PoblacionObjetivo.get(k); //Se obtiene el Padre actual del ciclo.
        ArrayList<Comida> cromozoma = padre.getCromozoma(); //Se obtiene el cromozoma con las Comidas del Padre actual.
        System.out.println("\n--> CromozomaOptimizado: "+ k + ", Calorias Totales: " + padre.getTotalCalorias() + ", Peso total: " + herramientasSumar.obtenerSumaPeso(cromozoma) + ", Puntuacion: " + padre.getPuntuacion()); //Print de toda la información pertinente del Padre actual.
            
        for(int l = 0 ; l<cromozoma.size() ; l++){ //Se recorre el cromozoma del Padre actual.
            Comida comida = cromozoma.get(l); //Se obtiene la comida actual del cromozoma.
            System.out.println("\nNombre: " + comida.getNombre() + ", calorias: " + comida.getCalorias() + ", peso: " + comida.getPeso()); //Se imprime toda la información pertinente de la comida actual.           
        }        
    }
    
    for(int z = 0 ; z<5 ; z++){
        asignaciones++;
        comparaciones++;
        ArrayList<Padre> PoblacionActualMayor = CompetenciaPoblaciones.get(0);
        int posicionEliminar = 0;
        asignaciones+=2;
        for(int v = 0 ; v<CompetenciaPoblaciones.size() ; v++){
            asignaciones++;
            comparaciones++;
            ArrayList<Padre> poblacionAnalizada = CompetenciaPoblaciones.get(v);
            asignaciones++;
            if(puntuacionPoblacion(poblacionAnalizada)>puntuacionPoblacion(PoblacionActualMayor)){
                PoblacionActualMayor = poblacionAnalizada;
                posicionEliminar = v;
                asignaciones+=2;
            }
            comparaciones++;
        }
        asignaciones++;
        this.MejoresPoblaciones.add(PoblacionActualMayor);
        CompetenciaPoblaciones.remove(posicionEliminar);
        asignaciones+=2;
    }
    asignaciones++;
    
    Instant endTime = Instant.now();// gets current time as end time for greedyCover approach
    
    System.out.println("\n------------------------------------Top 5 poblaciones------------------------------------");
    
    for(int p = 0 ; p<this.MejoresPoblaciones.size() ; p++){  
        ArrayList<Padre> poblacionActual = new ArrayList<Padre>(this.MejoresPoblaciones.get(p));
        System.out.println("\nPoblacion " + (p+1) + ", Puntuacion total: " + puntuacionPoblacion(poblacionActual));
        
        for(int k = 0 ; k<poblacionActual.size() ; k++){ //Se recorre la PoblaciónObjetivo.  
            Padre padre = poblacionActual.get(k); //Se obtiene el Padre actual del ciclo.
            
            System.out.println("Padre " + k + ": " + padre.toStringNombres() + ", Puntuacion: " + padre.getPuntuacion());
        }             
    }
    
    System.out.println("\n---------------------Mutaciones-----------------------");
    for(int m = 0 ; m<this.listaPreMutados.size() ; m++){
        Padre individuo = this.listaPreMutados.get(m);
        Padre mutacion = this.listaMutados.get(m);
        
        System.out.println("Padre " + m + ": " + individuo.toStringNombres() + ", Puntuacion: " + individuo.getPuntuacion());
        System.out.println("Mutacion " + m + ": " + mutacion.toStringNombres() + ", Puntuacion: " + mutacion.getPuntuacion());
        System.out.println(" ");         
    }
    
    this.asignaciones += herramientasSumar.getAsignaciones();
    this.comparaciones += herramientasSumar.getComparaciones();
    
    System.out.println("\n\nAsignaciones totales: " + this.asignaciones);
    System.out.println("Comparaciones totales: " + this.comparaciones);
    System.out.println("Lineas ejecutadas totales: " + (this.comparaciones+this.asignaciones));

    float sec = Duration.between(startTime,endTime).toMillis()/1000.0f;
    System.out.print("Duracion algoritmo Genético: ");// Muestra la duracion del algoritmo voraz
    System.out.format("%.3f",sec);
    System.out.print(" milisegundos");
    
    }
    
    //Fin de la clase
}
