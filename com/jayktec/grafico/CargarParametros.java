package com.jayktec.grafico;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.jayktec.archivos.Configuracion;

public class CargarParametros {
	int[] datosTablero = new int[4];
	int[] datosCasilla = new int[2];
	ArrayList<Estacion> estacion;

	public CargarParametros() {
		// se debe de leer del xml
		// parametros de prueba para el tablero de 32 pixeles 160,320,256,416
		// parametros de prueba para el tablero de 64 pixeles 64,64,512,512
		// parametros de prueba para el tablero de 8 52 pixeles 92,64,460,430 -
		// 95,65,508,424

		datosCasilla[0] = 58; // Ancho de cada Casilla
		datosCasilla[1] = 58; // Alto de cada Casilla

		datosTablero[0] = 38; // Posicion X del inicio del tablero
		datosTablero[1] = 32; // Posicion Y del inicio del Tablero

		datosTablero[2] =444;// Posicion X del Fin del Tablero
		datosTablero[3] = 438;// Posicon Y del Fin del Tablero

	}

	public CargarParametros(String batalla) {
		try {
			// prueba de configuracion
			Configuracion configuracion = new Configuracion();

			// se debe de leer del xml
			// parametros de prueba para el tablero de 32 pixeles
			// 160,320,256,416
			// parametros de prueba para el tablero de 64 pixeles 64,64,512,512
			// if (mapa.equals("assets/mapasTiled/mapaMin1.tmx")){
//			System.out.println("batalla a configurar:" + batalla);
			datosCasilla[0] = Integer.parseInt(configuracion.GetAtributo(batalla, "Mapa", "mapa", "AnchoCasilla"));// 50;
																													// //
																													// Ancho
																													// de
																													// cada
																													// Casilla
			datosCasilla[1] = Integer.parseInt(configuracion.GetAtributo(batalla, "Mapa", "mapa", "AltoCasilla")); // Alto
																													// de
																													// cada
																													// Casilla
			datosTablero[0] = Integer.parseInt(configuracion.GetAtributo(batalla, "Mapa", "mapa", "InicioX"));// 0;
																												// //
																												// Posicion
																												// X
																												// del
																												// inicio
																												// del
																												// tablero
			datosTablero[1] = Integer.parseInt(configuracion.GetAtributo(batalla, "Mapa", "mapa", "InicioY"));// 0;
																												// //
																												// Posicion
																												// Y
																												// del
																												// inicio
																												// del
																												// Tablero
			datosTablero[2] = Integer.parseInt(configuracion.GetAtributo(batalla, "Mapa", "mapa", "FinX"));// 624;//
																											// Posicion
																											// X
																											// del
																											// Fin
																											// del
																											// Tablero
			datosTablero[3] = Integer.parseInt(configuracion.GetAtributo(batalla, "Mapa", "mapa", "FinY"));// 624;//
																											// Posicon
																											// Y
																											// del
																											// Fin
																											// del
																											// Tablero

			// estacion[0]=new Estacion(65,380,false);
			// estacion[0]=new Estacion(155,390,true);
			// estacion[1]=new Estacion(155,75,true);
			// estacion[2]=new Estacion(550,75,true);
			// estacion[3]=new Estacion(550,410,true);
			// estacion[4]=new Estacion(725,410,true);
			estacion = configuracion.GetEstaciones(batalla);

			/*
			 * } else if (mapa.equals("assets/mapasTiled/mapGen3.tmx")){
			 * datosCasilla[0] = 8; // Ancho de cada Casilla datosCasilla[1] =
			 * 8; // Alto de cada Casilla
			 * 
			 * datosTablero[0] = 0; // Posicion X del inicio del tablero
			 * datosTablero[1] = 0; // Posicion Y del inicio del Tablero
			 * datosTablero[2] = 624;// Posicion X del Fin del Tablero
			 * datosTablero[3] = 624;// Posicon Y del Fin del Tablero
			 * 
			 * estacion[0]=new Estacion(365,320,true); estacion[1]=new
			 * Estacion(365,280,false); estacion[2]=new Estacion(395,280,true);
			 * estacion[3]=new Estacion(455,280,false); estacion[4]=new
			 * Estacion(455,305,true); estacion[5]=new Estacion(455,350,false);
			 * estacion[6]=new Estacion(625,350,true); estacion[7]=new
			 * Estacion(625,250,false); estacion[8]=new Estacion(375,250,true);
			 * estacion[9]=new Estacion(105,250,false); estacion[10]=new
			 * Estacion(105,290,true); estacion[11]=new Estacion(105,390,true);
			 * estacion[12]=new Estacion(200,390,true); }
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int[] GetDatosTablero() {
		return datosTablero;
	}

	public int[] GetDatosCasilla() {
		return datosCasilla;
	}

	public ArrayList<Estacion> getEstacion() {

		return estacion;
	}

}
