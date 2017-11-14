//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   Estacion.java                        //
//                   Descripcion                            //
//     clase encargada de las estaciones de cada mapa       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      05/04/2016     Version Inicial       //
//Vladimir Betancourt  05/04/2016     Version Inicial 		//
//////////////////////////////////////////////////////////////

package com.jayktec.grafico;

public class Estacion {

	private float x;
	private float y;
	private boolean estacionar;
	private String anterior;
	private String proxima;
	private String nombre;
	private String minijuego;

	/**
	 * Constructor de la clase estacion
	 * 
	 * @param pX posicion x de la estacion
	 * @param pY posicion Y de la estacion
	 * @param pEstacionar si el actor se estaciona o no en la estacion
	 * @param minijuego nombre del minijuego
	 * @param nombre nombre de la estacion
	 * @author yisheng
	 */
	public Estacion(float pX, float pY, String minijuego, Boolean pEstacionar, String nombre) {

		setX(pX);
		setY(pY);
		setEstacionar(pEstacionar);
		setMinijuego(minijuego);
		setNombre(nombre);
	}

	/*
	 * Metodos getters y setters de cada atributo de la clase estacion
	 */

	public String getMinijuego() {
		return minijuego;
	}

	public void setMinijuego(String minijuego) {

		this.minijuego = minijuego;
	}

	public float getX() {
		return x;
	}

	private void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	private void setY(float y) {
		this.y = y;
	}

	public boolean isEstacionar() {
		return estacionar;
	}

	private void setEstacionar(boolean estacionar) {
		this.estacionar = estacionar;
	}

	public String getAnterior() {
		return anterior;
	}

	public void setAnterior(String anterior) {
		this.anterior = anterior;
	}

	public String getProxima() {
		return proxima;
	}

	public void setProxima(String proxima) {
		this.proxima = proxima;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
