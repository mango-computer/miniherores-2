//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   Respuesta.java                        //
//                   Descripcion                            //
//     clase encargada de las Respuestas de las trivias       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Yisheng León      06/06/2016     Version Inicial       //
/////////////////////////////////////////////////////////////

package com.jayktec.archivos;

public class Respuesta {

	private String grupo;
	private String orden;
	private String valor;

	/**
	 * Genera una respuesta
	 * 
	 * @param grupo
	 *            Tipo de respuesta
	 * @param orden
	 *            posicion en su grupo
	 * @param valor
	 *            valor de la respuesta
	 */
	public Respuesta(String grupo, String orden, String valor) {
		super();
		this.grupo = grupo;
		this.orden = orden;
		this.valor = valor;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
