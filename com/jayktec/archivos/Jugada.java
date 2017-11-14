//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   Jugada.java                        //
//                   Descripcion                            //
//     clase encargada de las jugadas que se incluye ne los juegos       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Yisheng León      20/07/2016     Version Inicial       //
/////////////////////////////////////////////////////////////

package com.jayktec.archivos;
import com.jayktec.grafico.Enums.eColores;

public class Jugada {
	private int orden;
	private eColores color;
	private String pgn;
	private String posPartida;
	private String posLLegada;
	/**
	 * @param pgn
	 * @param posPartida
	 * @param posLLegada
	 */
	public Jugada(int orden,eColores color ,String pgn, String posPartida, String posLLegada) {
		super();
		this.color=color;
		this.orden=orden;
		this.pgn = pgn;
		this.posPartida = posPartida;
		this.posLLegada = posLLegada;

	}
	
	
	/**
	 * @return the orden
	 */
	public int getOrden() {
		return orden;
	}



	/**
	 * @param orden the orden to set
	 */
	public void setOrden(int orden) {
		this.orden = orden;
	}



	/**
	 * @return the color
	 */
	public eColores getColor() {
		return color;
	}



	/**
	 * @param color the color to set
	 */
	public void setColor(eColores color) {
		this.color = color;
	}



	/**
	 * @return the pgn
	 */
	public String getPgn() {
		return pgn;
	}
	/**
	 * @param pgn the pgn to set
	 */
	public void setPgn(String pgn) {
		this.pgn = pgn;
	}
	/**
	 * @return the posPartida
	 */
	public String getPosPartida() {
		return posPartida;
	}
	/**
	 * @param posPartida the posPartida to set
	 */
	public void setPosPartida(String posPartida) {
		this.posPartida = posPartida;
	}
	/**
	 * @return the posLLegada
	 */
	public String getPosLLegada() {
		return posLLegada;
	}
	/**
	 * @param posLLegada the posLLegada to set
	 */
	public void setPosLLegada(String posLLegada) {
		this.posLLegada = posLLegada;
	}
	
	
	
	
}
