
//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Protocolo.java                            //
//                   Descripcion                            //
//Funciones para traducir el tablero a FEN y FEN a Tablero  //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Vladimir Betancourt  20/04/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Piezas;

import com.badlogic.gdx.graphics.Color;
import com.jayktec.grafico.Enums.eColores;
import com.jayktec.grafico.Piezas.ActorExtra.TipoDeActor;
import com.jayktec.grafico.Screen.ScreenManager;

public class Fen {

	int juego;
	ScreenManager screenManager;

	/**
	 * Solo para tener acceso a los metodos de la fen
	 */
	public Fen() {

	}

	/**
	 * Contructro de un fen funcional
	 * 
	 * @param pScreen
	 * @param pJuego
	 */
	public Fen(ScreenManager pScreen, int pJuego) {
		screenManager = pScreen;
		juego = pJuego;
	}

	/**
	 * 
	 * @param pCasillas
	 *            vector con la informacion del tablero
	 * @param pPertenencia
	 *            indica el turno a quien le toca jugar
	 * @param pCantEntrada
	 *            indica la cantidad de elementos del Vector de entrada
	 * @param pCantSalidas
	 *            indica la cantida de elementos del vector de salida
	 * @param pElementosFila
	 *            indica la cantidad de elementos por fila en el fen
	 * @return devuelve el fen de un tablero
	 */
	public String obtenerFen(Casilla[] pCasillas, Color pPertenencia, int pCantEntrada, int pCantSalidas,
			int pElementosFila, String pPgnPeonAlPaso, int pMovimientosMedios, int pMovimientosCompletos) {

		String enroques = "";
		String enroquesBlancas = "";
		String enroquesNegras = "";

		Casilla vCasilla;
		for (int i = 0; i < pCantEntrada - 1; i++) {
			for (int j = 0; j < pCantEntrada - 1; j++) {
				// System.out.println(i);
				if (pCasillas[j].ordenFen() > 0 && pCasillas[j].ordenFen() > pCasillas[j + 1].ordenFen()) {
					vCasilla = pCasillas[j];
					pCasillas[j] = pCasillas[j + 1];
					pCasillas[j].setPiezaInd(j);
					pCasillas[j + 1] = vCasilla;
					pCasillas[j + 1].setPiezaInd(j + 1);
				}

			}
		}

		String[] vectorFen = new String[pCantSalidas + 1];

		boolean movidoRey = true;
		boolean movidoReyNe = true;

		String lineaFen = "";
		Pieza vPieza = null;
		String fenPieza = "";
//		System.out.println("entrando a verificar ");
		for (int i = 0; i <= pCantEntrada - 1; i++) {
			if (pCasillas[i].ordenFen() > 0) {

				vPieza = pCasillas[i].getPieza();
				switch (vPieza.getTipoPieza()) {
				case Alfil:
					fenPieza = "B";
					break;

				case Reina:
					fenPieza = "Q";
					break;

				case Rey:
					fenPieza = "K";
					if (((Rey) vPieza).getColor().equals(Color.WHITE))
						movidoRey = ((Rey) vPieza).getMovido();
					else {
						movidoReyNe = ((Rey) vPieza).getMovido();
					}
					break;

				case Torre:
					fenPieza = "R";
					if (!((Torre) vPieza).getMovida() && pCasillas[i].getPgn().equals("A1"))
						enroquesBlancas = "Q";
					else if (!((Torre) vPieza).getMovida() && pCasillas[i].getPgn().equals("H1"))
						enroquesBlancas = "K" + enroquesBlancas;
					else if (!((Torre) vPieza).getMovida() && pCasillas[i].getPgn().equals("A8"))
						enroquesNegras = "q";
					else if (!((Torre) vPieza).getMovida() && pCasillas[i].getPgn().equals("H8"))
						enroquesNegras = "k" + enroquesNegras;
					break;

				case Caballo:
					fenPieza = "N";
					break;

				case Peon:
					fenPieza = "P";
					break;
				case Muro:
					fenPieza = "W";
					break;
					
				case Salida:
					fenPieza = "E";

				}
				if (vPieza.getColor().equals(Color.BLACK)) {
					fenPieza = fenPieza.toLowerCase();
				}

			} else {
				// System.out.println("no es pieza");
				if (!(pCasillas[i].getActor() == null)) {
					// System.out.println("actor" + pCasillas[i].getActor());
					fenPieza = pCasillas[i].getActor().getTipo().getLetraFen();
					// System.out.println("fenPieza:" + fenPieza);
				}

			}
			if (pCasillas[i].getActor() == null)
				vectorFen[pCasillas[i].ordenFen()] = fenPieza;
			else
				vectorFen[(pCasillas[i].ordenFen() * (-1))] = fenPieza;

		}

		int j = 0;

		for (int i = 1; i < pCantSalidas + 1; i++) {
			// System.out.println( "indice: " + i + " : "+ vectorFen[i]);

			if (vectorFen[i] != null) {
				lineaFen = lineaFen + vectorFen[i];
			} else {
				j++;
			}

			if (i < pCantSalidas) {
				if (j > 0 && (vectorFen[i + 1] != null || i % pElementosFila == 0)) {
					lineaFen = lineaFen + j;
					j = 0;
				}
				if (i % pElementosFila == 0) {
					lineaFen = lineaFen + "/";
				}
			} else {
				if (j != 0) {
					lineaFen = lineaFen + j;
				}
			}
		}

		if (pPertenencia.equals(Color.WHITE))
			lineaFen = lineaFen + " w ";
		else
			lineaFen = lineaFen + " b ";

		if (!movidoRey)
			enroques = enroquesBlancas;

		if (!movidoReyNe)
			enroques = enroques + enroquesNegras;

		if (enroques.length() > 0)
			enroques = enroques + " ";

		return lineaFen = lineaFen + enroques + pPgnPeonAlPaso + " " + pMovimientosMedios + " " + pMovimientosCompletos;

	}

	/**
	 * convierte un Fen en casillas.
	 * 
	 * @param pFen
	 * @param pCantPiezas
	 *            piezas por / en el fen
	 * @return vector de casillas con las piezas indicadas en un fen
	 */

	public String Fen2Pgn(String pFen, int pCantPiezas) {
		// casillas = new Casilla[32];
		Torre vTorre;
		Caballo vCaballo;
		Alfil vAlfil;
		Reina vReina;
		Rey vRey;
		Peon vPeon;
		
		char e = 0;

		String[] camposFen = pFen.split(" ");

		String[] FilaTablero = camposFen[0].split("/");

		String vPgn = null;
		int j = 0, l = 9;
		for (String linea : FilaTablero) {
			l--;
			char vChar = 64;
			vChar++;
			for (int i = 0; i < linea.length(); i++) {

				vPgn = Character.toString(vChar) + l;

				e = linea.charAt(i);

				if (e == 32) {
					e = linea.charAt(i + 1);
					break;
				} else if (e == 114) {// r torre Negra
					vTorre = new Torre(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vTorre, vPgn, j);
					j++;
				} else if (e == 110) { // n caballo negro
					vCaballo = new Caballo(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vCaballo, vPgn, j);
					j++;
				} else if (e == 98) { // b alfil negro
					vAlfil = new Alfil(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vAlfil, vPgn, j);
					j++;
				} else if (e == 113) { // q reina negra
					vReina = new Reina(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vReina, vPgn, j);
					j++;
				} else if (e == 107) { // K rey negro
					vRey = new Rey(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vRey, vPgn, j);
					j++;
				} else if (e == 112) { // p peon negro
					vPeon = new Peon(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vPeon, vPgn, j);
					j++;
				} else if (e == 82) {// R torre blanca
					vTorre = new Torre(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vTorre, vPgn, j);
					j++;
				} else if (e == 78) { // n caballo blanco
					vCaballo = new Caballo(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vCaballo, vPgn, j);
					j++;
				} else if (e == 66) { // B alfil blanco
					vAlfil = new Alfil(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vAlfil, vPgn, j);
					j++;
				} else if (e == 81) { // Q reina blanca
					vReina = new Reina(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vReina, vPgn, j);
					j++;
				} else if (e == 75) { // K rey blanco
					vRey = new Rey(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vRey, vPgn, j);
					j++;
				} else if (e == 80) { // p peon blanco
					vPeon = new Peon(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vPeon, vPgn, j);
					j++;
				} else if (e >= 49 && e <= 57) {
					for (int k = 49; k < e; k++) {
						// setCasilla(null, null, j);
						if (k > 48) {
							vChar++;
							// j++;
						}
					}
				}
				// j++;
				vChar++;
			}
			// System.out.println(linea);
		}

		String vRet = null; // turno
		int k = 0;
		// System.out.println("campos fen :" + camposFen.length);
		//kkSystem.out.println("Cargando Fen:" + pFen);

		if (camposFen.length == 6)

			k = 1;
		else
			k = 0;

		vRet = "/" + camposFen[1]; // turno;

		vRet = vRet + "/" + camposFen[k + 1]; // enroques

		vRet = vRet + "/" + camposFen[k + 2]; // peon al paso

		vRet = vRet + "/" + camposFen[k + 3]; // movimientos medios

		vRet = vRet + "/" + camposFen[k + 4]; // movimientos completos

		return vRet;
		// return casillas;
	}

	/**
	 * convierte un Fen en casillas.
	 * 
	 * @param pFen
	 * @param pCantPiezas
	 *            piezas por / en el fen
	 * @return vector de casillas con las piezas indicadas en un fen
	 */

	public String Fen2PgnMiniJuego(String pFen, int pCantPiezas) {
		// casillas = new Casilla[64];
		Torre vTorre;
		Caballo vCaballo;
		Alfil vAlfil;
		Reina vReina;
		Rey vRey;
		Peon vPeon;
		Muro vMuro;
		ActorExtra vSalida;

		char e = 0;
		// Fen2Pgn ("r1bq1rk1/ppp2ppp/2n5/2bPp3/8/3P1P2/PPP3PP/RNBQKB1R b", 64);
//		System.out.println("Esto esta llegando: " + pFen);
		String[] camposFen = pFen.split(" ");

		String[] FilaTablero = camposFen[0].split("/");

		String vPgn = null;
		int j = 0, l = 9;
		for (String linea : FilaTablero) {
			l--;
			//char vCharLinea = (char) l;
			char vChar = 64;
			vChar++;
			for (int i = 0; i < linea.length(); i++) {

				vPgn = Character.toString(vChar) + l;
				e = linea.charAt(i);
//				System.out.println("vpgn:" + vPgn + " e:" + e);

				if (e == 32) {
					e = linea.charAt(i + 1);
					break;
				} else if (e == 114) {// r torre Negra
					vTorre = new Torre(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vTorre, vPgn, j);
					j++;
				} else if (e == 110) { // n caballo negro
					vCaballo = new Caballo(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vCaballo, vPgn, j);
					j++;
				} else if (e == 98) { // b alfil negro
					vAlfil = new Alfil(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vAlfil, vPgn, j);
					j++;
				} else if (e == 113) { // q reina negra
					vReina = new Reina(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vReina, vPgn, j);
					j++;
				} else if (e == 107) { // K rey negro
					vRey = new Rey(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vRey, vPgn, j);
					j++;
				} else if (e == 112) { // p peon negro
					vPeon = new Peon(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vPeon, vPgn, j);
					j++;
				} else if (e == 82) {// R torre blanca
					vTorre = new Torre(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vTorre, vPgn, j);
					j++;
				} else if (e == 78) { // n caballo blanco
					vCaballo = new Caballo(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vCaballo, vPgn, j);
					j++;
				} else if (e == 66) { // B alfil blanco
					vAlfil = new Alfil(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vAlfil, vPgn, j);
					j++;
				} else if (e == 81) { // Q reina blanca
					vReina = new Reina(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vReina, vPgn, j);
					j++;
				} else if (e == 75) { // K rey blanco
					vRey = new Rey(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vRey, vPgn, j);
					j++;
				} else if (e == 80) { // p peon blanco
					vPeon = new Peon(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vPeon, vPgn, j);
					j++;
				} else if (e == 87) { // W muro blanco
					vMuro = new Muro(eColores.Blancas, vPgn, j, juego, screenManager);
					setCasilla(vMuro, vPgn, j);
					j++;
				} else if (e == 119) {// w muro negro
					vMuro = new Muro(eColores.Negras, vPgn, j, juego, screenManager);
					setCasilla(vMuro, vPgn, j);
					j++;

					// TODO
				} else if (e == 69) {// E Exit (Salida)
					//texture = game.getManager().get("assets/Texturas/cuadroOscuro.png");
					vSalida = new ActorExtra(screenManager, TipoDeActor.PUERTA, j);
					// vSalida.setPosition(tablero.Pgn2XY(vPgn)[0],
					// tablero.Pgn2XY(vPgn)[1]);

					setActorExtra(vSalida, vPgn, j);
					j++;
				}

				else if (e >= 49 && e <= 57) {
					for (int k = 49; k < e; k++) {
						// setCasilla(null, null, j);
						if (k > 48) {
							vChar++;
							// j++;
						}
					}
				}
				// j++;
				vChar++;
			}
			// System.out.println(linea);
		}

		String vRet = null; // turno
		vRet = "/" + camposFen[1]; // turno;
				
		 
		if (camposFen.length == 6)		
			vRet = vRet + "/" + camposFen[2]; // enroques
					
//		System.out.println("cargando enroques: " + vRet );
		return vRet;

	}

	/**
	 * 
	 * @param pPieza
	 *            pieza a agregar al vecor de casillas
	 * @param pPgn
	 *            posicion pgn donde debe ir la pieza
	 * @param pInd
	 *            indice de la pieza en el vector de casillas
	 */

	private void setCasilla(Pieza pPieza, String pPgn, int pInd) {

		// System.out.println("agregando " + pPieza + " en: " + pPgn +" con el
		// ind:" + pInd ) ;

		screenManager.setCasilla(pPgn, pPieza, pInd);
		/*
		 * casillas[pInd] = new Casilla(); if (pInd> 0){
		 * casillas[pInd].setPieza(pPieza); casillas[pInd].setPgn(pPgn);
		 * casillas[pInd].setPiezaInd(pInd); }
		 */
	}

	private void setActorExtra(ActorExtra pActor, String pPgn, int pInd) {

		// System.out.println("agregando " + pActor + " en: " + pPgn +" con el
		// ind:" + pInd ) ;

		// System.out.println("fen del actor : "+pActor);
		screenManager.setActorExtra(pPgn, pActor, pInd);
		/*
		 * casillas[pInd] = new Casilla(); if (pInd> 0){
		 * casillas[pInd].setPieza(pPieza); casillas[pInd].setPgn(pPgn);
		 * casillas[pInd].setPiezaInd(pInd); }
		 */
	}

	/**
	 * Método que valida que un fen este correctamente construido.
	 * 
	 * @param fen
	 *            expresión que representa el tablero , la posición de las
	 *            piezas, y condiciones del juego
	 * @return 0 si el fen esta correcto o un numero entero segun el error 1
	 *         error de tablero 2 error de jugador proximo a mover 3 error de
	 *         enroque 4 error de pieza con posibilidades de peon al paso 5
	 *         error en jugada media 6 error en numero de jugadas 7 error en
	 *         cantidad de elementos en el fen.
	 * @author yisheng leon
	 */
	public int validarFen(String fen) {
		// rnbqkb1r/pppppppp/5n2/8/8/3P4/PPP1PPPP/RNBQKBNR w KQkq - 0 1
		try {
			// System.out.println("validando fen");
			// 2R5/1Q6/8/5q2/2nk4/3p3p/3B1Pn1/K7 w - - 0 10
			String[] vVectorFen = fen.split(" ");
			System.out.println("largo de pruebaFen: " + vVectorFen.length);
			int indice = 0;
			String tablero = vVectorFen[indice];
			indice++;
			String mueve = vVectorFen[indice];
			indice++;
			String enroque = "-";

			if (vVectorFen.length == 6) {
				enroque = vVectorFen[indice];
				indice++;
			}

			String peonAlPaso = vVectorFen[indice];
			indice++;
			String jugadaMedia = vVectorFen[indice];
			indice++;
			String numeroJugadas = vVectorFen[indice];

			if (!validarTablero(tablero)) {
				return 1;
			}
			if (!validarMueve(mueve)) {

				return 2;
			}
			if (!validarEnroque(enroque)) {

				return 3;
			}
			if (!validarPeonalPaso(peonAlPaso)) {

				return 4;
			}
			if (!esNumero(jugadaMedia)) {

				return 5;
			}
			if (!esNumero(numeroJugadas)) {

				return 6;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return 7;
		}

		return 0;

	}

	/**
	 * 
	 * @param peonAlPaso
	 * @return verdadero si esta correcto el tablero o lo contrario si esta
	 *         errado
	 * @author yisheng
	 */
	private boolean validarPeonalPaso(String peonAlPaso) {
		String filas = "ABCDEFGH";
		String columnas = "45";
		if (peonAlPaso.equals("-")) {
			return true;
		} else if (peonAlPaso.length() != 2)
			return false;

		peonAlPaso = peonAlPaso.toUpperCase();
		String caracter = ("" + peonAlPaso.charAt(0));
		if (filas.contains(caracter)) {
			caracter = ("" + peonAlPaso.charAt(1));

			if (columnas.contains(caracter)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Comprueba los enroques del fen y que no se repita el tipo de enroque
	 * 
	 * @param enroque
	 * @return verdadero si esta correcto el tablero o lo contrario si esta
	 *         errado
	 * @author yisheng
	 */

	private boolean validarEnroque(String enroque) {
		String piezas = "QKqk-";
		int n = 0;
		for (int i = 0; i < enroque.length(); i++) {

			String caracter = ("" + enroque.charAt(i));
			if (piezas.contains(caracter)) {
				if (!caracter.equals("-")) {
					piezas.replace(caracter, "");
					n++;
				}
			}

			else {
				return false;
			}
		}
		if (n > 4)
			return false;
		return true;

	}

	/**
	 * Comprueba si juega blancas o negras
	 * 
	 * @param mueve
	 *            el valor definido
	 * @return verdadero si esta correcto el tablero o lo contrario si esta
	 *         errado
	 * @author yisheng
	 */
	private boolean validarMueve(String mueve) {
		if (mueve.length() != 1)
			return false;
		mueve = mueve.toUpperCase();
		if (mueve.equals("W")) {
			return true;
		}
		if (mueve.equals("B")) {
			return true;
		}
		return false;
	}

	/**
	 * Revisa si el tablero es consistente.
	 * 
	 * @param tablero
	 *            en nomenclatura fen
	 * @return verdadero si esta correcto el tablero o lo contrario si esta
	 *         errado
	 * @author yisheng
	 */
	private boolean validarTablero(String tablero) {
		// System.out.println("ENTRE EN VALIDAR TABLERO");
		Boolean verificar = true;
		int slash = conteo(tablero, '/');
//		System.out.println("slash:" + slash);
		if (slash != 7) {
			// System.out.println("CONTEO MALO DE /");
			return false;
		}
		// verificar el tablero
		for (int i = 0; i < 8; i++) {

			String fila = split(tablero, '/');
			// System.out.println("FILA:"+fila);
			verificar = validarFila(fila);
			if (!verificar) {
				return verificar;
			}
			if (i < 7)
				tablero = tablero.substring(fila.length() + 1);

		}

		return verificar;
	}

	/**
	 * Valida que tenga la fila del tablero 8 elementos y que sean los validos
	 * los valores validos son: R rook torre Q queen reina K king rey B bishop
	 * alfil W wall muro P peon D door puerta C coin moneda
	 * 
	 * @param fila
	 *            del tablero a ser procesado
	 * @return verdadero si esta correcto lo contrario si esta incorrecto
	 */

	private Boolean validarFila(String fila) {
		String piezas = "RQKNBWPDC";
		int n = 0;
		for (int i = 0; i < fila.length(); i++) {
			String caracter = ("" + fila.charAt(i)).toUpperCase();
//			System.out.println("VERIFICANDO CARACTER:" + caracter);

			if (piezas.contains(caracter)) {
				n++;
			} else if (esNumero(caracter)) {
				n = n + Integer.parseInt(caracter);
			} else {
				return false;
			}
		}
		if (n != 8)
			return false;
		return true;
	}

	/**
	 * comprobar si la cadena de caracteres es un string
	 * 
	 * @param numero
	 *            la cadena a compro bar
	 * @return el valor de la consulta
	 */
	public boolean esNumero(String numero) {
		try {
			Long.parseLong(numero);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Función que te devuelve la cantidad de veces que esta un caracter en un
	 * string
	 * 
	 * @param cadena
	 * @param caracter
	 * @return devuelve la cantidad de caracteres en la cadena dada
	 * @author yisheng
	 */

	public int conteo(String cadena, char caracter) {
		int n = 0;
		for (int i = 0; i < cadena.length(); i++) {

			if (cadena.charAt(i) == caracter) {
				n++;
			}
		}

		return n;
	}

	/**
	 * funcion para separar una expresión hasta un caracter deseado
	 * 
	 * @param cadena
	 *            un string a ser cortado
	 * @param caracter
	 *            hasta donde va a cortar la cadena
	 * @return la cadena hasta conseguir el caracter deseado
	 * @author yisheng leon
	 */
	public String split(String cadena, char caracter) {
		String aString = "";
		for (int i = 0; i < cadena.length(); i++) {
			char c = cadena.charAt(i);
			if (c == caracter) {
				return aString;
			} else {
				aString = aString + c;
			}
			// System.out.println("split:"+aString);
		}

		return aString;
	}

}
