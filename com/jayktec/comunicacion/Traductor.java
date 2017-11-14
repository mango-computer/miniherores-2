
//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Traductor.java                            //
//                   Descripcion                            // 
//Traduce las posiciones de coordenadas a numeros tomando en//
//consideración que la casilla a1 es la 00                  //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             //  
//    Yisheng León       14/03/2016      Version Inicial    //
//////////////////////////////////////////////////////////////

package com.jayktec.comunicacion;

/**
 * Traduce las posiciones de coordenadas a numeros tomando en consideracion que la casilla a1 es la 00 
 * @author yisheng Leon Espinoza
 *
 */
public class Traductor {


	
	/**
	 * Traduce las Coordenadas de la posición en el tablero a numeros
	 * @param TotalFilas numero de total de filas del tablero, un tablero normal de ajedrez tiene 8
	 * @param TotalColumnas numero de total de columnas del tablero, un tablero normal de ajedrez tiene 8
	 * @param Coordenadas Posicion del tablero siendo la letra el primer valor que representa la columna y el numero a continuación el que representa la fila 
	 * @return Posicion
	 */
	public int CoordenadasANumeros (int TotalFilas , int TotalColumnas, String Coordenadas)
	{
		
		if (esNumero(Coordenadas)){
			return Integer.parseInt(Coordenadas);
			
		}
		else {
			//System.out.println("Coordenadas:"+Coordenadas);
		
			//System.out.println("Coordenadas letra:"+Coordenadas.charAt(0));
			
						
		int filaActual=  (((int) (Coordenadas.charAt(1) ))- 48);
		//System.out.println("Fila:"+filaActual);
		int columnaActual=  (((int) (Coordenadas.charAt(0) ))- 64);
		//System.out.println("Coordenada:"+Coordenadas.charAt(0));
		//System.out.println("Colactual:"+columnaActual);
		
		return ((((filaActual-1)*(TotalColumnas))-1) + columnaActual);
		}
	}
	
	/**
	 * comprobar si la cadena de caracteres es un string	
	 * @param numero la cadena a compro bar
	 * @return el valor de la consulta
	 */
	private boolean esNumero (String numero)
		{
			try
			{
				Long.parseLong(numero);
			} catch (Exception e) {
				return false ;
			}
			return true;
		}
		
	/**
	 * Traduce  el numeros de la posicion en el tablero alas Coordenadas de la posicion en el tablero
	 * @param TotalFilas numero de total de filas del tablero, un tablero normal de ajedrez tiene 8
	 * @param TotalColumnas numero de total de columnas del tablero, un tablero normal de ajedrez tiene 8
	 * @param Posicion Posicion del tablero siendo la letra  
	 * @return Coordenadas
	 */
	public String NumerosACoordenadas (int TotalFilas , int TotalColumnas, int Posicion)
	{		
		int filaActual=  ((Posicion)/TotalFilas)+1;
		int columnaActual=  (Posicion+1)%TotalColumnas;
		if (columnaActual == 0 ) columnaActual =8; 
		return (((char) (columnaActual+64)) )+ Integer.toString(filaActual);
	
	}




}
