//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   Pregunta.java                        //
//                   Descripcion                            //
//     clase encargada de las Pregutnas de las trivias       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Yisheng León      06/06/2016     Version Inicial       //
/////////////////////////////////////////////////////////////

package com.jayktec.archivos;

public class Pregunta {

	private String texto;
	private int dificultad;
	private String grupo;
	private String correcta;
	private String resolucion;

	/**
	 * Genera una pregunta que contiene toda la informacion
	 * 
	 * @param texto
	 *            Formulación de la pregunta
	 * @param dificultad
	 *            grado de dificultad de la pregunta
	 * @param grupo
	 *            grupo de respuestas posibles
	 * @param correcta
	 *            respuesta correcta
	 * @param resolucion
	 *            explicación de la respuesta
	 * @author yisheng
	 */
	public Pregunta(String texto, int dificultad, String grupo, String correcta, String resolucion) {
		super();
		this.texto = texto;
		this.dificultad = dificultad;
		this.grupo = grupo;
		this.correcta = correcta;
		this.resolucion = resolucion;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto
	 *            the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the dificultad
	 */
	public int getDificultad() {
		return dificultad;
	}

	/**
	 * @param dificultad
	 *            the dificultad to set
	 */
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * @param grupo
	 *            the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * @return the correcta
	 */
	public String getCorrecta() {
		return correcta;
	}

	/**
	 * @param correcta
	 *            the correcta to set
	 */
	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	/**
	 * @return the resolucion
	 */
	public String getResolucion() {
		return resolucion;
	}

	/**
	 * @param resolucion
	 *            the resolucion to set
	 */
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

}
