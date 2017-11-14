//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Configuración.java                        //
//                   Descripcion                            // 
//             Lectura de archivo de configuracion          //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             //  
//   Yisheng León     25/05/2016     Versión inicial  //
//////////////////////////////////////////////////////////////

package com.jayktec.archivos;

import java.io.File;
import java.util.ArrayList;
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

import com.jayktec.grafico.Estacion;

/**
 * lee el archivo de configuracion de los juegos.
 * 
 * @author yisheng
 *
 */

public class Configuracion {

	static Scanner entrada = new Scanner(System.in);
	private static String ruta = "./Configuracion/configuracion.xml";
	private static String rutaTemporal = "./Configuracion/configtmp.xml";
	private Document configCache;
	
	
	public Configuracion()
	{
		try {
			leerArchivo(rutaTemporal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		// nuevaPartida("Prueba");
		// ActualizarPartida("Prueba desencriptado 2", "2","3", "3", "4", "1",
		// "1");
		// BorrarPartida("Prueba desencriptado 2");

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
	public void leerArchivo(String ruta) throws Exception {
		
		  //System.out.println("leyendo y desencriptando");
		 // Encriptado.desencriptado(ruta, rutaTemporal);
		  
		  File xmlFile = new File(rutaTemporal);
		  
		 DocumentBuilderFactory creadorDocumento = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentoCreado = creadorDocumento.newDocumentBuilder();
		// Document documento = documentoCreado.parse(xmlFile);
		// pruebas sin encriptacion
		//String rutaPrueba = "Configuracion/configsinencriptar.xml";
		Document documento = documentoCreado.parse(xmlFile);
		this.configCache= documento;

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
		//encriptado.encriptado(rutaTemporal, ruta);

	}

	/**
	 * Te dice si existe una minijuego en el registro de configuracion
	 * 
	 * @param batalla
	 *            , mundo en el que se encuentra la estación
	 * @param estacion
	 *            estacion, minijuego en el que se busca la configuracion
	 * @throws Exception
	 * @author yisheng
	 */
	public  Boolean ExisteMinijuego(String batalla, String estacion) throws Exception {

		Boolean respuesta = false;

		Document documento = this.configCache;

		NodeList list = documento.getElementsByTagName("");
		// System.out.println("entre " + list.getLength());

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre: " + j);

			Node node = list.item(j);

			if (batalla.equals((node.getFirstChild().getTextContent()))) {

				NodeList nodoInterno = node.getChildNodes();
				for (int i = 0; i < nodoInterno.getLength(); i++) {
					Node nodoEstacion = nodoInterno.item(i);
					if (estacion.equals(nodoEstacion.getFirstChild().getTextContent())) {
						return true;
					}

				}

			}

		}

		return respuesta;
	}

	/**
	 * devuelve el valor de un atributo para una estacion de una batalla que se
	 * encuentra almacenado en la configuracion
	 * 
	 * @param batalla
	 *            , mundo en el que se encuentra la estacion
	 * @param objeto,
	 *            tipo de objeto configurado estacion, mapa o minijuego
	 * @param nombreDeObjeto,
	 *            nombre del objeto configurado
	 * @param atributo
	 *            valor del dato que se desea conocer del objeto confijjurado
	 * @return devuelve el valor solicitado de la partida
	 * @throws Exception
	 * @author yisheng
	 */
	public  String GetAtributo(String batalla, String objeto, String nombreDeObjeto, String atributo)
			throws Exception {

		String respuesta = "";

		Document documento = this.configCache ;

		NodeList list = documento.getElementsByTagName("Batalla");

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre config: " + j);

			Node node = list.item(j);

			String capturaBatalla = node.getFirstChild().getTextContent().toString();

			if (batalla.equals(capturaBatalla)) {
				// System.out.println("ENTRE EN BATALLA:" + capturaBatalla);

				NodeList lista = node.getChildNodes();

				// System.out.println("longitud:" + lista.getLength());
				for (int x = 0; x < lista.getLength(); x++) {

					Node Tag = lista.item(x);
					String capturaTag = Tag.getNodeName();

					if (capturaTag.equals(objeto)) {
						// xSystem.out.println("ENTRE EN TAg:" + capturaTag);

						if (Tag.getFirstChild().getTextContent().equals(nombreDeObjeto)) {

							// System.out.println("entre en nombre de objeto:" +
							// nombreDeObjeto);
							NodeList nodoInterno = Tag.getChildNodes();

							for (int i = 0; i < nodoInterno.getLength(); i++) {
								Node nodoAtributo = nodoInterno.item(i);

								if (atributo.equals(nodoAtributo.getNodeName())) {

									respuesta = nodoAtributo.getTextContent();
									// System.out.println("respuesta:" +
									// respuesta + "/");
									return respuesta;

								}
							}
						}
					}

				}

			}

		}

		return respuesta;
	}

	/**
	 * devuelve el valor de un atributo para una estacion de una batalla que se
	 * encuentra almacenado en la configuracion
	 * 
	 * @param batalla
	 *            mundo en el que se encuentra la estacion
	 * @return devuelve el valor solicitado de la partida
	 * @throws Exception
	 * @author yisheng
	 */
	public ArrayList<Estacion> GetEstaciones(String batalla) throws Exception {
		Float codigoX = (float) 0;
		Float codigoY = (float) 0;
		Boolean estacionar = false;
		String minijuego = "0";
		ArrayList<Estacion> respuesta = new ArrayList<Estacion>();

		Document documento = this.configCache;

		NodeList list = documento.getElementsByTagName("Batalla");

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre estacion batalla: " + j);

			Node node = list.item(j);

			String capturaBatalla = node.getFirstChild().getTextContent().toString();

			if (batalla.equals(capturaBatalla)) {
				// System.out.println("ENTRE EN BATALLA:" + capturaBatalla);

				NodeList lista = node.getChildNodes();

				// System.out.println("longitud:" + lista.getLength());
				for (int x = 0; x < lista.getLength(); x++) {

					Node Tag = lista.item(x);
					String capturaTag = Tag.getNodeName();
					// System.out.println("Tag0:" + capturaTag);
					// System.out.println("Tag1:" +
					// Tag.getFirstChild().getTextContent());

					String nombre = "";

					if (capturaTag.equals("Estacion")) {

						NodeList listaEstacion = Tag.getChildNodes();

						codigoX = (float) 0;
						codigoY = (float) 0;
						estacionar = false;
						minijuego = "0";

						for (int z = 0; z < listaEstacion.getLength(); z++) {

							Node estaciones = listaEstacion.item(z);
							String atributo = estaciones.getNodeName();
							if ("X".equals(atributo)) {

								codigoX = (float) Integer.parseInt(estaciones.getTextContent());

							} else if ("Y".equals(atributo)) {

								codigoY = (float) Integer.parseInt(estaciones.getTextContent());

							} else if ("Final".equals(atributo)) {
								estacionar = Boolean.valueOf(estaciones.getTextContent());

							} else if ("Minijuego".equals(atributo)) {
								minijuego = estaciones.getTextContent();

							} else if ("Nombre".equals(atributo)) {
								nombre = estaciones.getTextContent();
							}

						}
						// System.out.println("nombre:" + nombre);
						// codigoY + ":" + estacionar.toString());
						Estacion estacion = new Estacion(codigoX, codigoY, minijuego, estacionar, nombre);
						respuesta.add(estacion);

					}

				}
				return respuesta;

			}

		}

		return respuesta;
	}
}
