/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import java.util.Random;
/**
 *
 * @author Usuario
 */
public class RandomNumberGenerator {
	static Random generator;
	
	public static Random getRandom()
	{
		if (generator == null)
		{
			generator = new Random();
		}
		
		return generator;
	}    
}
