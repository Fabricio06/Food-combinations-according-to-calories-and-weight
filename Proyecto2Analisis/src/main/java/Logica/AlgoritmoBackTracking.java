package Logica;
import java.util.ArrayList; //Importacion para utilizar los arreglos
import java.time.Duration;// Importacion para tomar las mediciones en milisegundos
import java.time.Instant;// Importacion para tomar los tiempos de inicio y final 

/**
 * Clase encargada de ejecutar el algoritmo backtracking
 * @author Fabricio Porras y Carlos Solis
 */
public class AlgoritmoBackTracking {
    private int caloriasMinimas; //Calorias minimas que se necesitan para completar los requisitos
    private int pesoMaximo; //Peso maximo que se soporta para completar los requisitos
    private ArrayList<Comida> listaAlimentos = new ArrayList<>(); //Lista con todos los alimentos
    private final SumaCaloriasPeso herramientasSumar = new SumaCaloriasPeso(); //Clase que agrega herramientas para sumar las calorias y peso
    private ArrayList<ArrayList<Comida>> combinaciones = new ArrayList<>(); //Lista donde se meten todas las combinaciones de los alimentos
    
    private long asignaciones = 0;
    private long comparaciones = 0;
    
    /**
     * Constructor de la prueba backtracking
     * @param caloriasMinimas calorias minimas requeridas
     * @param pesoMaximo calorias maximas permitidas
     * @param listaAlimentos  arreglo con las intancias de tipo Comida
     */
    public AlgoritmoBackTracking(int caloriasMinimas, int pesoMaximo, ArrayList<Comida> listaAlimentos) {
        this.caloriasMinimas = caloriasMinimas;
        this.pesoMaximo = pesoMaximo;
        this.listaAlimentos = listaAlimentos;
    }
    
    //*************LOS GETS DE LOS ATRIBUTOS*************
    public int getCalorias_minimas() {
        return caloriasMinimas;
    }
    public ArrayList<Comida> getLista_alimentos() {
        return listaAlimentos;
    }
    public int getPeso_maximo() {
        return pesoMaximo;
    }

    //***************LOS SETS DE LOS ATRIBUTOS*************
    public void setCalorias_minimas(int caloriasMinimas) {
        this.caloriasMinimas = caloriasMinimas;
    }
    public void setLista_alimentos(ArrayList<Comida> listaAlimentos) {
        this.listaAlimentos = listaAlimentos;
    }
    public void setPeso_maximo(int pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }
    
    /**
     * Metodo que encuentra la mejor lista de opciones con instancias de tipo Comida
     * @param mejoresSelecciones arreglo con los arreglos de los alimentos
     * @return ArrayList(Comida) el mejor alimento de la lista
     */
    public ArrayList<Comida> mejorAlimentoLista(ArrayList<ArrayList<Comida>> mejoresSelecciones){
       this.asignaciones++;
        
        ArrayList<Comida> mejorCandidato = mejoresSelecciones.get(0); //Se deduce que el mejor va a ser la primera instancia
        this.asignaciones++;
        for(int i = 0; i<mejoresSelecciones.size();i++){ //Se recorren todas las listas
            this.asignaciones++;
            this.comparaciones+=2;
            if(this.herramientasSumar.obtenerSumaCalorias(mejorCandidato)<this.herramientasSumar.obtenerSumaCalorias(mejoresSelecciones.get(i))){ //Se compara si el siguiente tiene mas calorias en menos peso
                mejorCandidato = mejoresSelecciones.get(i); //Se obtiene una mejor opcion
                this.asignaciones++;
            }
        }
        this.asignaciones++;
        this.comparaciones++;
       return mejorCandidato;
    }
    
    /**
     * Metodo que funciona para ver si una lista de alimentos es solucion cumpliendo los requisitos
     * @param alimento lista con una serie de instancias de tipo Comida
     * @return boolean true si cumple con las condiciones
     */
    public boolean esSolucion(ArrayList<Comida> alimento){
        this.asignaciones++;
        this.comparaciones+=2;
        if(this.herramientasSumar.sumaCalorias(alimento, this.caloriasMinimas) && this.herramientasSumar.sumaPeso(alimento, this.pesoMaximo) == false) return true; //Compara si cumple con los requisitos y devuelte true si los cumple
        return false; //Devuelve false en caso contrario
    }
    
    /**
     * Este metodo se encarga de seleccionar todas las combinaciones de alimentos posibles
     * @param listaEntrante Lista con los alimentos
     * @param largoLista Largo de la lista con los alimentos
     * @param cantSeleccionar Cantidad de valores que vamos a combinar
     * @param indiceLista Indice actual de la listaEntrante
     * @param listaVacia Lista vacia
     * @param x control para que el indiceArreglo no quede fuera de rango y conseguir una condicion de parada
     */
    public void posiblesCombinaciones(ArrayList<Comida> listaEntrante, int largoLista, int cantSeleccionar, int indiceLista,
						ArrayList<Comida> listaVacia, int x) {
        this.asignaciones+=6;
        if (indiceLista == cantSeleccionar) //Se revisa si la cantidad de elementos recorridos e ingresados a la listaVacia es igual a la cantidad que vamos a seleccionar
            return;//Se retorna si es el caso
        this.comparaciones+=2;
        if (x >= largoLista) //Condicion para revisar que no se exceda del tamano de la lista
            return; //Si es el caso se retorna
         
        listaVacia.add(indiceLista, listaEntrante.get(x)); //Se agrega a la listaVacia el valor correspondiente
        this.asignaciones++;
        
        posiblesCombinaciones(listaEntrante, largoLista, cantSeleccionar, indiceLista+1, listaVacia, x+1); //Se llama recursivamente nuevamente incrementandose el indice y el valor para el comparador
        
        ArrayList<Comida> temporal = new ArrayList<>(); //Se crea una lista temporal
        this.asignaciones++;
        if(cantSeleccionar!=1){ //Se revisa que la cantidad a querer ingresar no sea 1
           
            for(int h = 0; h<cantSeleccionar;h++){ //Se recorre hasta la cantidad de seleccionados que deseamos
                temporal.add(listaVacia.get(h)); //Y se agregan a la nueva lista temporal
                this.asignaciones+=2;
                this.comparaciones++;
            }
            this.asignaciones++;
            this.comparaciones++;
            
            listaVacia = new ArrayList<>(temporal); //Se limpia la listaVacia 
            this.combinaciones.add(temporal); //Se agrega a las combinaciones posibles
            temporal = new ArrayList<>(listaVacia); //Se vuelve a instanciar el temporal para que no quede vinculado con combinaciones
            this.asignaciones+=3;
        }
        else{
            this.combinaciones.add(listaVacia); //Si es uno se ingresa directamente
            this.asignaciones++;
        }
        this.comparaciones++;
        posiblesCombinaciones(listaEntrante, largoLista, cantSeleccionar, indiceLista, temporal, x+1); //Se vuelve a llamar a la funcion recursiva
    }
    
    public void funcionBackTraking(){
        Instant tiempoInicial = Instant.now(); // Obtiene el tiempo actual de inicio
        
        this.comparaciones++;
        if(this.listaAlimentos.isEmpty()) return; //Verifica que la lista enviada no sea vacia
        
        ArrayList<ArrayList<Comida>> listaSelecciones = new ArrayList<>(); //Arreglo donde iremos agregando a los mejores candidatos
        ArrayList<Comida> listaActual = this.listaAlimentos; //El arreglo con todos los alimentos
        this.asignaciones+=2;
        
        for(int x = 1;x<listaActual.size();x++){ //Se realiza un ciclo para ir realizando todas las combinaciones
            ArrayList<Comida> vacio = new ArrayList<>(); //Se crea un arreglo vacio
            posiblesCombinaciones(listaActual,listaActual.size(),x,0,vacio,0); //Se envian a realizar las combinaciones posibles
            this.asignaciones+=3;
            this.comparaciones++;
        }
        this.asignaciones++;
        this.comparaciones++;
        
        ArrayList<ArrayList<Comida>> listaCombinaciones = this.combinaciones; //Se instancia a la lista de todas las combinaciones no repetidas encontradas
        this.asignaciones++;
        
        for(int i = 0;i<listaCombinaciones.size();i++){ //Se recorren las combinaciones
            if(esSolucion(listaCombinaciones.get(i))){ //Se averigua si dicha combinacion es una solucion o no
                listaSelecciones.add(listaCombinaciones.get(i)); //Si es solucion, se envia a la lista de los seleccionados
                this.asignaciones++;
            }
            this.asignaciones++;
            this.comparaciones+=2;
        }
       this.asignaciones++;
       this.comparaciones++;
        
        this.comparaciones++;
        if(!listaSelecciones.isEmpty()){ //Si la lista de los seleccionados no esta vacía
            ArrayList<Comida> mejorOpcion = mejorAlimentoLista(listaSelecciones); //Obtiene a la mejor solucion entre toda la lista de seleccionados
            Instant tiempoFinal = Instant.now();
            
            this.asignaciones+= this.herramientasSumar.getAsignaciones();
            this.comparaciones += this.herramientasSumar.getComparaciones();
            
            System.out.println("----------Algoritmo backtracking en la seleccion de alimentos--------------------------------------------------");
            System.out.println("Se contó una cantidad de: "+this.asignaciones+" asignaciones,"+" y: "+this.comparaciones +" comparaciones\n"+(this.comparaciones+this.asignaciones)+"\n");
            float milis = Duration.between(tiempoInicial,tiempoFinal).toMillis()/1000.0f;
            System.out.print("Duracion algoritmo backtracking: ");// Muestra la duracion del algoritmo voraz
            System.out.format("%.3f",milis);
            System.out.print(" milisegundos\n");
            System.out.print(" milisegundos\n BackTracking encontrado: [Calorias: "+this.herramientasSumar.sumaCalorias(mejorOpcion,this.caloriasMinimas)+", Peso: "+herramientasSumar.sumaPeso(mejorOpcion,this.pesoMaximo) +"]\n");
            System.out.print("[Calorias: "+this.herramientasSumar.obtenerSumaCalorias(mejorOpcion)+", Peso: "+herramientasSumar.obtenerSumaPeso(mejorOpcion) +"]\n");
            System.out.println(mejorOpcion.toString());
            System.out.println("\n------------------------------------------------------------------------");
        }
        else{
            Instant tiempoFinal = Instant.now();
            
            this.asignaciones+= this.herramientasSumar.getAsignaciones();
            this.comparaciones += this.herramientasSumar.getComparaciones();
            
            System.out.println("----------Algoritmo backtracking en la seleccion de alimentos--------------------------------------------------");
            System.out.println("Se contó una cantidad de: "+this.asignaciones+" asignaciones,"+" y: "+this.comparaciones +" comparaciones\n"+(this.comparaciones+this.asignaciones)+"\n");
            float milis = Duration.between(tiempoInicial,tiempoFinal).toMillis()/1000.0f;
            System.out.print("Duracion algoritmo backtracking: ");// Muestra la duracion del algoritmo voraz
            System.out.format("%.3f",milis);
            System.out.print(" milisegundos\n");
            System.out.println("No hubo solucion que completara las calorias minimas y el peso maximo");
            System.out.println("\n------------------------------------------------------------------------");
        }
    }



}
