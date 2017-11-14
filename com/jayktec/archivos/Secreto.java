//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   Secreto.java                        //
//                   Descripcion                            //
//     clase encargada del mensaje secreto que se incluye ne los juegos       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Yisheng León      20/07/2016     Version Inicial       //
/////////////////////////////////////////////////////////////

package com.jayktec.archivos;

import java.util.ArrayList;


public class Secreto {

	private int mensaje;
	private String fen;
	private ArrayList<Jugada> jugadas;
	
	/**
	 * 
	 * @param mensaje
	 * @param fen
	 * @param jugadas
	 */
	public Secreto(int mensaje, String fen , ArrayList<Jugada> jugadas) {
		super();
	this.fen=fen;
	this.jugadas=jugadas;
	this.mensaje=mensaje;
	}

	/**
	 * @return the mensaje
	 */
	public int getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(int mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the fen
	 */
	public String getFen() {
		return fen;
	}

	/**
	 * @param fen the fen to set
	 */
	public void setFen(String fen) {
		this.fen = fen;
	}

	/**
	 * @return the jugadas
	 */
	public ArrayList<Jugada> getJugadas() {
		return jugadas;
	}

	/**
	 * @param jugadas the jugadas to set
	 */
	public void setJugadas(ArrayList<Jugada> jugadas) {
		this.jugadas = jugadas;
	}

	
	
}
