package Logica;
import java.util.ArrayList; //Importacion para utilizar los arreglos
import java.time.Duration;// Importacion para tomar las mediciones en milisegundos
import java.time.Instant;// Importacion para tomar los tiempos de inicio y final 

/**
 * Clase encargada de ejecutar el algoritmo voraz
 * @author Fabricio Porras y Carlos Solis
 */
public class AlgoritmoVoraz {
    private int caloriasMinimas; //Calorias minimas que se necesitan para completar los requisitos
    private int pesoMaximo; //Peso maximo que se soporta para completar los requisitos
    private ArrayList<Comida> listaAlimentos = new ArrayList<>(); //Lista con todos los alimentos
    private int indice; //Variable general para obtener el indice del alimento a eliminar en la lista general
    private final SumaCaloriasPeso herramientasSumar = new SumaCaloriasPeso(); //Clase que agrega herramientas para sumar las calorias y peso
    private long asignaciones = 0;
    private long comparaciones = 0;
    
    
    /**
     * Constructor de la prueba del algoritmo voraz
     * @param caloriasMinimas calorias minimas requeridas
     * @param pesoMaximo peso maximo permitido
     * @param listaAlimentos la lista con las instancias de Comida
     */
    public AlgoritmoVoraz(int caloriasMinimas, int pesoMaximo, ArrayList<Comida> listaAlimentos) {
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
     * Este metodo se encarga de obtener la lista y buscar al candidato que tenga mas calorias en menor cantidad de peso
     * @param listaActual Lista con todos los alimentos actuales
     * @return un objeto Comida que es el que cumple los requisitos
     */
    public Comida mejorCandidato(ArrayList<Comida> listaActual){
        this.asignaciones++;
        
        Comida mejorAlimento = listaActual.get(0); //Se toma el primer alimento como el mejor
        this.indice = 0; //El indice del primer elemento
        this.asignaciones +=2;
        
        for(int i = 0; i<listaActual.size();i++){ //Se recorre toda la lista de alimentos
            Comida alimentoActual = new Comida(listaActual.get(i)); //obtiene el alimento actual
            this.asignaciones+=2;
            this.comparaciones+=3;
            
            if(mejorAlimento.getCalorias()<=alimentoActual.getCalorias() && mejorAlimento.getPeso() >= alimentoActual.getPeso()){ //Se realiza el criterio de comparacion (Mas calorias en menor peso)
                mejorAlimento = alimentoActual;   //Si cumple el criterio se queda como mejor alimento    
                this.indice = i; //Y se guarda su indice para eliminarlo posteriormente
                this.asignaciones+=2;
            }
            
        }
        this.comparaciones++;
        this.asignaciones++;
        return mejorAlimento; // Se retorna el alimento que cumple con el criterio en toda la lista
    }
    
    /**
     * Algoritmo que ejecuta la funcion voraz para obtener una combinacion de alimentos eficaz mediante criterios de comparacion [Mayor calorias en menor tiempo]
     */
    public void funcionVoraz(){
        Instant tiempoInicial = Instant.now(); // Obtiene el tiempo actual de inicio
        
        this.comparaciones++;
        if(this.listaAlimentos.isEmpty()) return; //Verifica que la lista enviada no sea vacia
        
        ArrayList<Comida> mejoresSelecciones = new ArrayList<>(); //Arreglo donde iremos agregando a los mejores candidatos
        ArrayList<Comida> listaActual = this.listaAlimentos; //El arreglo con todos los alimentos
        this.asignaciones+=2;
        
        while(!listaActual.isEmpty()){ //El ciclo se hará hasta que la lista principal esté vacía
            Comida mejorAlimento = mejorCandidato(listaActual); //Llama al metodo de buscar el mejor candidatos con la lista actual
            mejoresSelecciones.add(mejorAlimento); //Se agrega dicho alimento a la lista de las mejores selecciones/candidatos
            this.asignaciones +=2;
            
            if(!this.herramientasSumar.sumaPeso(mejoresSelecciones,this.pesoMaximo)){ //Se revisa que el peso no supere lo establecido por this.pesoMaximo
                listaActual.remove(this.indice); //Si no sobrepasa se elimina dicho alimento de la lista general y se continua con los demas
                this.asignaciones++;
            }
            else{
                mejoresSelecciones.remove(mejoresSelecciones.size()-1); //Si sobrepasa el peso se elimina el alimento
                listaActual.remove(this.indice); //Se elimina igualmente de la lista general y se continua viendo si otro alimento si cumple
                this.asignaciones+=2;
            }
            
            this.comparaciones+=2;
            
        }
        this.comparaciones++;
        
        if(!herramientasSumar.sumaCalorias(mejoresSelecciones,this.caloriasMinimas)){ //Si la suma de calorias no satisface el minimo
            Instant tiempoFinal = Instant.now();
            this.asignaciones += this.herramientasSumar.getAsignaciones();
            this.comparaciones += this.herramientasSumar.getComparaciones();
            
            System.out.println("----------Algoritmo voraz en la seleccion de alimentos--------------------------------------------------");
            System.out.println("Se contó una cantidad de: "+this.asignaciones+" asignaciones,"+" y: "+this.comparaciones +" comparaciones\n"+(this.comparaciones+this.asignaciones)+"\n");
            float milis = Duration.between(tiempoInicial,tiempoFinal).toMillis()/1000.0f;
            System.out.print("Duracion algoritmo voraz: ");// Muestra la duracion del algoritmo voraz
            System.out.format("%.3f",milis);
            System.out.print(" milisegundos\n");
            System.out.println("No hubo heuristica voraz que completara las calorias minimas en el peso establecido");
            System.out.println("\n------------------------------------------------------------------------");
        }
        else{
            Instant tiempoFinal = Instant.now();
            
            this.asignaciones += this.herramientasSumar.getAsignaciones();
            this.comparaciones += this.herramientasSumar.getComparaciones();
            
            System.out.println("----------Algoritmo voraz en la seleccion de alimentos--------------------------------------------------");
            System.out.println("Se contó una cantidad de: "+this.asignaciones+" asignaciones,"+" y: "+this.comparaciones +" comparaciones\n"+(this.comparaciones+this.asignaciones)+"\n");
            float milis = Duration.between(tiempoInicial,tiempoFinal).toMillis()/1000.0f;
            System.out.print("Duracion algoritmo voraz: ");// Muestra la duracion del algoritmo voraz
            System.out.format("%.3f",milis);
            System.out.print(" milisegundos\nHeuristica encontrada: [Calorias: "+this.herramientasSumar.sumaCalorias(mejoresSelecciones,this.caloriasMinimas)+", Peso: "+herramientasSumar.sumaPeso(mejoresSelecciones,this.pesoMaximo) +"]\n");
            System.out.print("[Calorias: "+this.herramientasSumar.obtenerSumaCalorias(mejoresSelecciones)+", Peso: "+herramientasSumar.obtenerSumaPeso(mejoresSelecciones) +"]\n");
            System.out.println(mejoresSelecciones.toString());
            System.out.println("\n------------------------------------------------------------------------");
        }
    }
    
    
    
    
    
    
    
    
}
