//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Dificultad.java                        //
//                   Descripcion                            // 
//             Lectura de archivo de configuracion  de Dificultad        //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             //  
//   Yisheng León     15/07/2016     Versión inicial  //
//////////////////////////////////////////////////////////////

package com.jayktec.archivos;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 * lee el archivo de configuracion de los juegos.
 * 
 * @author yisheng
 *
 */

public class Dificultad {

	static Scanner entrada = new Scanner(System.in);
	private static String ruta = "./Configuracion/dificultad.xml";
	private static String rutaTemporal = "./Configuracion/Dificultadtmp.xml";

	public static void main(String[] args) throws Exception {
//		System.out.println(GetAtributo("1", "Muro", "Destruir"));
//		System.out.println(GetAtributo("1", "Moneda", "Ganar"));
//
//		System.out.println(GetAtributo("7", "Muro", "Construir"));

	}
	
	private String paja;
	private String reloj;
	private String bolsaMoneda;
	private String mostrarMoneda;
	private String monedaInicio;
	private String monedaGanar;
	private String monedaAyuda;
	private String muroConstruir;
	private String muroDestruir;
	private String valorDificultad;
	

	/**
	 * @return the valorDificultad
	 */
	public String getValorDificultad() {
		return valorDificultad;
	}

	public Dificultad(String dificultad)
	{
		valorDificultad=dificultad;
		try {
			GetAtributos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the paja
	 */
	public String getPaja() {
		return paja;
	}

	/**
	 * @param paja the paja to set
	 */
	public void setPaja(String paja) {
		this.paja = paja;
	}

	/**
	 * @return the reloj
	 */
	public String getReloj() {
		return reloj;
	}

	/**
	 * @param reloj the reloj to set
	 */
	public void setReloj(String reloj) {
		this.reloj = reloj;
	}

	/**
	 * @return the bolsaMoneda
	 */
	public String getBolsaMoneda() {
		return bolsaMoneda;
	}

	/**
	 * @param bolsaMoneda the bolsaMoneda to set
	 */
	public void setBolsaMoneda(String bolsaMoneda) {
		this.bolsaMoneda = bolsaMoneda;
	}

	/**
	 * @return the mostrarMoneda
	 */
	public String getMostrarMoneda() {
		return mostrarMoneda;
	}

	/**
	 * @param mostrarMoneda the mostrarMoneda to set
	 */
	public void setMostrarMoneda(String mostrarMoneda) {
		this.mostrarMoneda = mostrarMoneda;
	}

	/**
	 * @return the monedaInicio
	 */
	public String getMonedaInicio() {
		return monedaInicio;
	}

	/**
	 * @param monedaInicio the monedaInicio to set
	 */
	public void setMonedaInicio(String monedaInicio) {
		this.monedaInicio = monedaInicio;
	}

	/**
	 * @return the monedaGanar
	 */
	public String getMonedaGanar() {
		return monedaGanar;
	}

	/**
	 * @param monedaGanar the monedaGanar to set
	 */
	public void setMonedaGanar(String monedaGanar) {
		this.monedaGanar = monedaGanar;
	}

	/**
	 * @return the monedaAyuda
	 */
	public String getMonedaAyuda() {
		return monedaAyuda;
	}

	/**
	 * @param monedaAyuda the monedaAyuda to set
	 */
	public void setMonedaAyuda(String monedaAyuda) {
		this.monedaAyuda = monedaAyuda;
	}

	/**
	 * @return the muroConstruir
	 */
	public String getMuroConstruir() {
		return muroConstruir;
	}

	/**
	 * @param muroConstruir the muroConstruir to set
	 */
	public void setMuroConstruir(String muroConstruir) {
		this.muroConstruir = muroConstruir;
	}

	/**
	 * @return the muroDestruir
	 */
	public String getMuroDestruir() {
		return muroDestruir;
	}

	/**
	 * @param muroDestruir the muroDestruir to set
	 */
	public void setMuroDestruir(String muroDestruir) {
		this.muroDestruir = muroDestruir;
	}

	/**
	 * lee el archivo encriptado xml, lo desencripta en un temporal y devuelve
	 * el archivo temporal en un documento xml de formato .DOM
	 * 
	 * @param ruta
	 *            l
	 * @return el Documento XML en formato DOM
	 * @throws Exception
	 * @author yisheng
	 */
	// @SuppressWarnings("static-access")
	public static Document leerArchivo(String ruta) throws Exception {
		
		  Encriptado.desencriptado(ruta, rutaTemporal);
		  
		  File xmlFile = new File(rutaTemporal);
		  
		 
		DocumentBuilderFactory creadorDocumento = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentoCreado = creadorDocumento.newDocumentBuilder();
		Document documento = documentoCreado.parse(xmlFile);
		// pruebas sin encriptacion
		//String rutaPrueba = "Configuracion/dificultadsinencriptar.xml";
		//Document documento = documentoCreado.parse(rutaPrueba);

		return documento;

	}

	/**
	 * lee el archivo desencriptado xml en el temporal, lo encripta en un la
	 * ruta definitiva
	 * 
	 * @param ruta
	 *            el Documento XML en formato DOM
	 * @throws Exception
	 * @author yisheng
	 */
	@SuppressWarnings("static-access")
	public static void escribeArchivo(Document documento, String ruta) throws Exception {
		Encriptado encriptado = new Encriptado();

		TransformerFactory transformacion = TransformerFactory.newInstance();
		Transformer transformador = transformacion.newTransformer();
		DOMSource fuente = new DOMSource(documento);

		StreamResult streamResult = new StreamResult(new File(rutaTemporal));
		transformador.transform(fuente, streamResult);
		encriptado.encriptado(rutaTemporal, ruta);

	}

	/**
	 * Te dice si existe una dificultad en el registro de configuracion
	 * 
	 * @param dificultad
	 *            , nivel en el que se encuentra la estación
	 * @throws Exception
	 * @author yisheng
	 */
	public static Boolean ExisteDificultad(String dificultad) throws Exception {

		
		Document documento = leerArchivo(ruta);

		NodeList list = documento.getElementsByTagName("");
		// System.out.println("entre " + list.getLength());

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre: " + j);

			Node node = list.item(j);

			if (dificultad.equals((node.getFirstChild().getTextContent()))) {

						return true;
				
				
			}

		}

		return false;
	}

	/**
	 * devuelve el valor de una accion de un objeto para una dificultad que se
	 * encuentra almacenado en la configuracion
	 * 
	 * @param dificultad
	 *            , valor elegido por el jugador 
	 * @param objeto,
	 *            tipo de objeto a utilizar en la  estacion, mapa o minijuego
	 * @param accion,
	 *            tipo de uso que se le da al objeto
	 * @return devuelve el valor solicitado de la partida
	 * @throws Exception
	 * @author yisheng
	 */
	public static String GetAtributo(String dificultad, String objeto, String accion)
			throws Exception {

		String respuesta = "";

		Document documento = leerArchivo(ruta);

		NodeList list = documento.getElementsByTagName("Dificultad");

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre config: " + j);

			Node node = list.item(j);

			String capturaBatalla = node.getFirstChild().getTextContent().toString();

			if (dificultad.equals(capturaBatalla)) {
		//		 System.out.println("ENTRE EN BATALLA:" + capturaBatalla);

				NodeList lista = node.getChildNodes();

	//			 System.out.println("longitud:" + lista.getLength());
				for (int x = 0; x < lista.getLength(); x++) {

					Node Tag = lista.item(x);
					String capturaTag = Tag.getNodeName();

					if (capturaTag.equals(objeto)) {
//						System.out.println("ENTRE EN TAg:" + capturaTag);

						NodeList nodoInterno = Tag.getChildNodes();

							for (int i = 0; i < nodoInterno.getLength(); i++) {
								Node nodoAtributo = nodoInterno.item(i);

								if (accion.equals(nodoAtributo.getNodeName())) {

									respuesta = nodoAtributo.getTextContent();
//									 System.out.println("respuesta:" +
//									 respuesta + "/");
//									return respuesta;

								
							}
						}
					}

				}

			}

		}

		return respuesta;
	}

	/**
	 * devuelve el valor de una accion de un objeto para una dificultad que se
	 * encuentra almacenado en la configuracion
	 * @throws Exception
	 * @author yisheng
	 */
	private void GetAtributos()	throws Exception {

		

		Document documento = leerArchivo(ruta);

		NodeList list = documento.getElementsByTagName("Dificultad");

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre config: " + j);

			Node node = list.item(j);

			String capturaDificultad = node.getFirstChild().getTextContent().toString();

			if (valorDificultad.equals(capturaDificultad)) {
		//		 System.out.println("ENTRE EN BATALLA:" + capturaBatalla);

				NodeList lista = node.getChildNodes();

	//			 System.out.println("longitud:" + lista.getLength());
				for (int x = 0; x < lista.getLength(); x++) {

					Node objeto = lista.item(x);
					String capturaObjeto = objeto.getNodeName();

				
						NodeList nodoInterno = objeto.getChildNodes();

							for (int i = 0; i < nodoInterno.getLength(); i++) {
								Node accion = nodoInterno.item(i);
								String capturaAccion = accion.getNodeName();
								
								if ( capturaObjeto.equals("Paja"))
								{
									if (capturaAccion.equals("Pintar"))
									{
										this.paja= accion.getTextContent();
									}	
								}
								else if ( capturaObjeto.equals("Reloj"))
								{
									if (capturaAccion.equals("Disminuir"))
									{
										this.reloj= accion.getTextContent();
									}	
								}
								else if ( capturaObjeto.equals("Moneda"))
								{
									if (capturaAccion.equals("Bolsa"))
									{
										this.bolsaMoneda= accion.getTextContent();
									}	
									else
										if (capturaAccion.equals("Mostrar"))
										{
											this.mostrarMoneda= accion.getTextContent();
										}
										else if (capturaAccion.equals("Inicio"))
										{
											this.monedaInicio= accion.getTextContent();
										}
											else if (capturaAccion.equals("Ganar"))
											{
												this.monedaGanar= accion.getTextContent();
											}
											else if (capturaAccion.equals("Ayuda"))
											{
												this.monedaAyuda= accion.getTextContent();
											}
										
								}
								else if ( capturaObjeto.equals("Muro"))
								{
									if (capturaAccion.equals("Construir"))
									{
										this.muroConstruir= accion.getTextContent();
									}	
									else
										if (capturaAccion.equals("Destruir"))
										{
											this.muroDestruir= accion.getTextContent();
										}	
								}
				
							}

				}

			}

		}

	}

}
