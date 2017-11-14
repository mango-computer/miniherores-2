//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Tablero.java                              //
//                   Descripcion                            //
//             Tablero para el juego                        //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Vladimir Betancourt  09/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////
package com.jayktec.grafico.Piezas;

import java.util.ArrayList;

import com.jayktec.grafico.CargarParametros;
import com.jayktec.grafico.Estacion;
//import com.jayktec.grafico.Miniheroes;


public class Tablero {
	private int inicioX;
	private int inicioY;
	private int finX;
	private int finY;
	private int anchoCasilla;
	private int altoCasilla;
	private ArrayList<Estacion> estacion;
	private boolean tableroRotado = false;
	// private static final Logger LOG =
	// Logger.getLogger(Miniheroes.class.getName());

	public Tablero() {
		int[] gValores = new int[4];
		CargarParametros cargarParametros = new CargarParametros();
		gValores = cargarParametros.GetDatosTablero();
		SetTablero(gValores);
		Casilla casilla = new Casilla();
		anchoCasilla = casilla.GetAnchoCasilla();
		altoCasilla = casilla.GetAltoCasilla();

	}

	public Tablero(String mapa) {
		int[] gValores = new int[4];
		CargarParametros cargarParametrosMapa = new CargarParametros(mapa);
		gValores = cargarParametrosMapa.GetDatosTablero();
		SetTablero(gValores);
		Casilla casilla = new Casilla();
		anchoCasilla = casilla.GetAnchoCasilla();
		altoCasilla = casilla.GetAltoCasilla();
		estacion = cargarParametrosMapa.getEstacion();

	}

	/**
	 * Metodo para asignar los valores a un tablero
	 * 
	 * @param pTablero array con los valores de un tablero
	 */
	public void SetTablero(int[] pTablero) {
		inicioX = pTablero[0];
		inicioY = pTablero[1];
		finX = pTablero[2];
		finY = pTablero[3];

	}

	/**
	 * Metodo para obtener las coordenadas de un tablero
	 * 
	 * @return array con las coordenadas del tablero
	 */
	public int[] GetTablero() {
		int[] rValor = new int[5];
		rValor[0] = inicioX;
		rValor[1] = inicioY;
		rValor[2] = finX;
		rValor[3] = finY;
		return rValor;
	}

	/**
	 * Metodo para obtener el inicio del vector X de un tablero
	 * 
	 * @return inicio del vector X
	 */
	public int GetInicioX() {
		return inicioX;

	}

	/**
	 * Metodo para obtener el inicio del vector Y de un tablero
	 * 
	 * @return inicio del vector Y
	 */
	public int GetInicioY() {
		return inicioY;
	}

	/**
	 * Metodo para obtener el final del vector X de un tablero
	 * 
	 * @return final del vector x
	 */
	public int GetFinX() {
		return finX;
	}

	/**
	 * Metodo para obtener el final del vector Y de un tablero
	 * 
	 * @return final del vector Y
	 */
	public int GetFinY() {
		return finY;
	}

	/**
	 * Metodo para convertir coordenadas X Y a posiciones PGN
	 * 
	 * @param pX coordenada X de la posicion
	 * @param pY coordenada Y de la posicion
	 * @return posicion pgn 
	 */
	public String XY2pgn(int pX, int pY) {
		if (tableroRotado) {
			// System.out.println("tablero rotado xy2pgn");
			pX = (pX - (finX + anchoCasilla)) / anchoCasilla;
			pY = (pY - (finY + altoCasilla)) / altoCasilla;
			pX = Math.abs(pX) + 64; // Codigo ASCII
			pY = Math.abs(pY - 48);
		} else {
			pX = (pX - (inicioX - anchoCasilla)) / anchoCasilla;
			pY = (pY - (inicioY - altoCasilla)) / altoCasilla;

			pX = pX + 64; // Codigo ASCII
			pY = pY + 48;
		}
		String ret = Character.toString((char) pX) + Character.toString((char) pY);
//		System.out.println("pgn2:" + ret);
		return ret.toString();
	}

	/**
	 * Metodo para convertir una posición PGN a coordenadas
	 * 
	 * @param pPgn posicion pgn
	 * @return arreglo de coordenadas X Y
	 */
	public int[] Pgn2XY(String pPgn) {

		int rValor[] = new int[2];
		int x = 0, y = 0, y1 = 0;
		if (pPgn.length() == 2) {
			char cx = pPgn.charAt(0);
			char cy = pPgn.charAt(1);
			if (tableroRotado) {
				// System.out.println("tablero rotado pgn2xy");
				x = Math.abs((cx - 63 - 9) * anchoCasilla) + inicioX; // codigo
																		// ASCII
																		// de A
				y = Math.abs((cy - 47 - 9) * altoCasilla) + inicioY; // codigo
																		// ASCII
																		// de 1
				
			} else

			{
				// System.out.println("iniciox:" + inicioX+" ancho: "
				// +anchoCasilla);
				x = (cx - 65) * anchoCasilla + inicioX; // codigo ASCII de A
				y = (cy - 49) * altoCasilla + inicioY; // codigo ASCII de 1
			}
			rValor[0] = x;
			rValor[1] = y;
			// LOG.logp(Level.INFO,this.toString(), "Pgn2XY",pPgn);
			// System.out.println("x: " + x + " y: " + y);
			return rValor;
		} else if (pPgn.length() == 3) {
			char cx = pPgn.charAt(0);
			char cy = pPgn.charAt(1);
			char cy1 = pPgn.charAt(2);

			x = (cx - 64) * anchoCasilla; // codigo ASCII de A
			y = (cy - 48) * altoCasilla; // codigo ASCII de 1
			y1 = (cy1 - 48) * altoCasilla; // codigo ASCII de A

			rValor[0] = x;
			rValor[1] = y;
			if (y1 > 0 && y1 < 10)
				rValor[2] = y1;
			// LOG.logp(Level.INFO,this.toString(), "Pgn2XY","3");
			return rValor;
		}

		return null;
	}

	public ArrayList<Estacion> getEstacion() {

		return estacion;
	}

	public boolean getTableroRotado() {
		return tableroRotado;
	}

	public void setTableroRotado(boolean pTableroRotado) {
		this.tableroRotado = pTableroRotado;
	}

}