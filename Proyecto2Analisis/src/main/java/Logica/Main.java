package Logica;
import java.util.ArrayList; //Importacion para utilizar los arreglos

/**
 * :____________________________________________:
 * :                                            : 
 * : Fecha de inicio: 26/10/2022-10:00 Pm       :
 * :                                            :
 * : Última modificación 13/11/2022-5:00 Pm    :
 * :____________________________________________:
 * 
 * Clase principal encargada de instanciar las demas clases y realizar las llamadas principales
 * 
 * @author Fabricio Porras Morera
 * @author Carlos Solis Mora
 */
public class Main {

    
    public static void main(String[] args) {
        //Se tomaron 16 alimentos que era lo maximo que pedía el enunciado pero a futuro se pueden comentar algunos depende
        //del funcionamiento
        
        //Cada alimento cuenta con peso o calorias diferentes a los demas, no hay alimentos con mismo peso y misma caloria
        
        //VERDURAS Y HORTALIZAS
        Comida coliflor = new Comida("Coliflor",100,300);
        Comida tomate = new Comida("Tomate",100,500); 
        Comida lechuga = new Comida("Lechuga",200,900);
        
        //FRUTAS
        Comida manzana = new Comida("Manzana",400,300);
        Comida ciruelaSeca = new Comida("Ciruela Seca",300,100);
        Comida coco = new Comida("Coco",700,100);
        
        //CEREALES Y DERIVADOS
        Comida arrozIntegral = new Comida("Arroz integral",400,100); 
        Comida panTrigoIntegral = new Comida("Pan de trigo integral", 400,200); //
        
        //BEBIDAS
        Comida aguaMineral = new Comida("Agua Mineral",200,500); 
        Comida batidoLacteoCacao = new Comida("Batido lácteo de cacao",200,200);
        
        //LÁCTEOS Y DERIVADOS
        Comida lecheEntera = new Comida("Leche entera",200,300);
        
        //CARNES, CAZA Y EMBUTIDOS
        Comida chuletaCerdo = new Comida("Chuleta de cerdo",600,200); //
        Comida chicharron = new Comida("Chicharron",600,100); //
        Comida ternera = new Comida("Ternera",200,100); //
        
        //HUEVOS
        Comida clara = new Comida("Clara de huevo",100,200);
        Comida huevoEntero = new Comida("Huevo entero",300,200);
        
        //CREACION DE LA LISTA Y INSERCION DE LOS ALIMENTOS EN ESTA
        ArrayList<Comida> listaAlimentos = new ArrayList<>();
        
        listaAlimentos.add(ternera);
        listaAlimentos.add(batidoLacteoCacao);
        listaAlimentos.add(clara);
        listaAlimentos.add(huevoEntero);
        listaAlimentos.add(coliflor);
        listaAlimentos.add(tomate);
        listaAlimentos.add(manzana);
        listaAlimentos.add(ciruelaSeca);
        listaAlimentos.add(coco);
        listaAlimentos.add(arrozIntegral);
        listaAlimentos.add(panTrigoIntegral);
        listaAlimentos.add(aguaMineral);
        listaAlimentos.add(lecheEntera);
        listaAlimentos.add(chuletaCerdo);
        listaAlimentos.add(chicharron);
        listaAlimentos.add(lechuga);
        
        int xValor = 2000; //Minimo de calorias requeridas
        int yValor = 2000; //Maximo de peso permitido 
        
        ArrayList<Comida> listaAlimentoCopia = new ArrayList<>(listaAlimentos); //Copia de los alimentos
        ArrayList<Comida> listaAlimentoCopia2 = new ArrayList<>(listaAlimentos); //Copia de los alimentos
        
        //Prueba del algoritmo voraz para la seleccion de alimentos
        AlgoritmoVoraz pruebaVoraz = new AlgoritmoVoraz(xValor,yValor,listaAlimentos); //Se crea la instancia con los datos predefinidos
        pruebaVoraz.funcionVoraz(); //Se hace la prueba y se imprime el mejor candidato con la heuristica voraz
        
        //Prueba del algoritmo de backtracking para la seleccion de alimentos
        AlgoritmoBackTracking pruebaBackTracking = new AlgoritmoBackTracking(xValor,yValor,listaAlimentoCopia); //Se crea la instancia con los datos predefinidos
        pruebaBackTracking.funcionBackTraking(); //Se bace la prueba de y se imprime el mejor candidato con backtracking
        
        //Prueba del algoritmo de programacion genetica para la seleccion de alimentos
        AlgoritmoGenetico pruebaGenetica = new AlgoritmoGenetico(xValor,yValor,listaAlimentoCopia2); //Instancia con los parametros predeterminados
        pruebaGenetica.funcionGenetica(); //Se hace la prueba, se imprime el top 5 candidatos y el proceso
        
    }
}

