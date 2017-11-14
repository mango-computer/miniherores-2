//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Alfil.java                                 //
//                   Descripcion                            //
//             Pieza alfil para el juego                     //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Vladimir Betancourt  15/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Piezas;

import com.jayktec.grafico.CargarParametros;

public class Casilla {

	private int alto;
	private int ancho;
	private String pgn;
	private Pieza pieza;
	private ActorExtra actorExtra;

	/**
	 * Constructor de la clase casilla la cual permite obtener de la
	 * configuracion xml el alto y ancho de la casilla
	 */
	public Casilla() {
		int[] gValor = new int[1];
		CargarParametros cargarParametros = new CargarParametros();
		gValor = cargarParametros.GetDatosCasilla();
		alto = gValor[0];
		ancho = gValor[1];
		pgn = "";

	}

	/**
	 * Constructor de la clase casilla la cual permite obtener de la
	 * configuracion xml el alto y ancho de la casilla pasando como parametro el
	 * fondo de pantalla
	 * 
	 * @param mapa
	 *            fondo de pantalla
	 */
	public Casilla(String mapa) {
		int[] gValor = new int[1];
		CargarParametros cargarParametrosMapa = new CargarParametros(mapa);
		gValor = cargarParametrosMapa.GetDatosCasilla();
		alto = gValor[0];
		ancho = gValor[1];
		pgn = "";

	}

	public Casilla(int pAlto, int pAncho) {
		alto = pAlto;
		ancho = pAncho;
	}

	/**
	 * Metodo para obtener el arreglo del alto y el ancho de la casilla
	 * 
	 * @return valores de alto y ancho de la casilla
	 */
	public int[] GetCasilla() {
		int[] rValor = new int[1];
		rValor[0] = alto;
		rValor[1] = ancho;
		return rValor;
	}

	/**
	 * Metodo para obtener el alto de la casilla
	 * 
	 * @return valor del alto de la casilla
	 */
	public int GetAltoCasilla() {
		return alto;
	}

	/**
	 * Metodo para obtener el ancho de la casilla
	 * 
	 * @return valor del alto de la casilla
	 */
	public int GetAnchoCasilla() {

		return ancho;
	}

	/**
	 * Metodo para asignar un arreglo de alto y ancho de la casilla
	 * 
	 * @param pValor
	 *            arreglo con alto y ancho de la casilla
	 */
	public void SetCasilla(int[] pValor) {
		alto = pValor[0];
		ancho = pValor[1];
	}

	/**
	 * Metodo para asignar la posición pgn
	 * 
	 * @param pPgn
	 *            que se le asigna a una pieza
	 */
	public void setPgn(String pPgn) {
		pgn = pPgn;
	}

	/**
	 * Metodo para asignar uns pieza
	 * 
	 * @param pPieza
	 *            seteo de pieza
	 */
	public void setPieza(Pieza pPieza) {
		pieza = pPieza;
		actorExtra = null;
	}

	public void setActor(ActorExtra pActor) {
		pieza = null;
		actorExtra = pActor;
	}

	/**
	 * Metodo para obtener una posición pgn
	 * 
	 * @return posición PGN
	 */
	public String getPgn() {
		return pgn;
	}

	/*
	 * Metodo para obtener una pieza
	 */
	public Pieza getPieza() {
		return pieza;
	}

	/*
	 * Metodo para obtener una actor
	 */
	public ActorExtra getActor() {
		return actorExtra;
	}

	public int ordenFen() {
		String vPgn;
		int i, j = 0;

		vPgn = this.getPgn();
		// System.out.println("pgn: " + vPgn);
		if (this.pieza != null) {
			char cx = vPgn.charAt(0);
			char cy = vPgn.charAt(1);

			j = cy - 48; // j de la matriz
			i = cx - 64; // i de la matriz

			j = Math.abs(j - 8);
			j = j * 7 + i + j;
			// System.out.println("pgn" + vPgn + " orden fen:" + j);
			return j;
		} else if (this.actorExtra != null) {
			char cx = vPgn.charAt(0);
			char cy = vPgn.charAt(1);

			j = cy - 48; // j de la matriz
			i = cx - 64; // i de la matriz

			j = Math.abs(j - 8);
			j = j * 7 + i + j;
			// System.out.println("pgn" + vPgn + " orden fen:" + j);
			return (j * -1);
		} else
			return 0;
	}

	public void setPiezaInd(int pInd) {
		if (this.pieza != null)
			this.pieza.setInd(pInd);

	}
}