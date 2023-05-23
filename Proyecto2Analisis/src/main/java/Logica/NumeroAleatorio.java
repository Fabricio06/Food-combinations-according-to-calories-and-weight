package Logica;
import java.util.Random;
/**
 *
 * @author Carlos Solis
 * @author Fabricio Porras
 */
public class NumeroAleatorio {
	static Random generado;
        /**
         * Funcion encargada de generar un numero al azar
         * @return numero aleatorio
         */
	public static Random getRandom()
	{
		if (generado == null)
		{
			generado = new Random();
		}
		
		return generado;
	}    
}
