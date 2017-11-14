///////////////////////////////////////////////////////////////                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Trivia.java                        //
//                   Descripcion                            // 
//             Lectura de archivo de Trivia          //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             //  
//   Yisheng León     06/06/2016     Versión inicial  //
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

import com.jayktec.grafico.Enums.eTipoTrivia;

/**
 * lee el archivo de configuracion de los juegos.
 * 
 * @author yisheng
 *
 */

public class Trivia {

	static Scanner entrada = new Scanner(System.in);
	private static String ruta = "./Configuracion/Trivia.xml";
	private static String rutaTemporal = "./Configuracion/TriviaXml1tmp.xml";

	public static void main(String[] args) throws Exception {

		// GetPreguntas(1);
		// GetRespuesta("Booleano");
		// GetRespuesta("Personaje", "s");
		
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
	public static Document leerArchivo(String ruta, eTipoTrivia tipoTrivia) throws Exception {
		//System.out.println(" tipotrivia: " + tipoTrivia.toString());
		if (tipoTrivia.equals(eTipoTrivia.Ajedrez))
		{
			ruta = "Configuracion/triviaajedrez.xml";			
			rutaTemporal = "Configuracion/triviaajedreztmp.xml";			
			
		}
			else if (tipoTrivia.equals(eTipoTrivia.General))
			{
				ruta = "Configuracion/trivia.xml";		
				rutaTemporal = "Configuracion/triviatmp.xml";		
				
			}
				else if (tipoTrivia.equals(eTipoTrivia.Piezas))			
				{
					ruta = "Configuracion/triviapiezas.xml";			                     
					rutaTemporal = "Configuracion/triviapiezastmp.xml";			                     
					
				}
		//System.out.println("rutaPrueba: " + ruta);

		
		
		//System.out.println("leyendo y desencriptando");
		 Encriptado.desencriptado(ruta, rutaTemporal);
		 
		 File xmlFile = new File(rutaTemporal);
		 
		 DocumentBuilderFactory creadorDocumento = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentoCreado = creadorDocumento.newDocumentBuilder();
		 Document documento = documentoCreado.parse(xmlFile);
		// pruebas sin encriptaciony

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
	 * devuelve el conjunto de preguntas de una trivia dado el grado de su
	 * dificultado
	 * 
	 * @param dificultad
	 *            valor de dificultad de una pregunta
	 * @return devuelve el conjunto de elementos de tipo Clase Pregunta
	 * @throws Exception
	 * @author yisheng
	 */
	public static ArrayList<Pregunta> GetPreguntas(int dificultad, eTipoTrivia tipoTrivia) throws Exception {

		ArrayList<Pregunta> respuesta = new ArrayList<Pregunta>();

		Document documento = leerArchivo(ruta, tipoTrivia);

		NodeList list = documento.getElementsByTagName("Pregunta");

		for (int j = 0; j < list.getLength(); j++) {

			Node node = list.item(j);

			String pregunta = node.getFirstChild().getTextContent().toString();
			// System.out.println("pregunta:" + pregunta);
			// System.out.println("ENTRE EN BATALLA:" + capturaBatalla);

			NodeList lista = node.getChildNodes();
			int dificil = 0;
			String grupoRespuesta = "";
			String correcta = "";
			String resolucion = "";

			// System.out.println("longitud:" + lista.getLength());
			for (int x = 0; x < lista.getLength(); x++) {

				Node Tag = lista.item(x);
				String capturaTag = Tag.getNodeName();

				if (capturaTag.equals("Dificultad")) {

					dificil = Integer.parseInt(Tag.getTextContent());
					// System.out.println("dificultad:" + dificil);
				} else if (capturaTag.equals("Correcta")) {
					correcta = Tag.getTextContent();

				} else if (capturaTag.equals("Resolucion")) {
					resolucion = Tag.getTextContent();

				} else if (capturaTag.equals("GrupoRespuesta")) {
					grupoRespuesta = Tag.getTextContent();

				}
			}

			if (dificil == dificultad) {
				respuesta.add(new Pregunta(pregunta, dificil, grupoRespuesta, correcta, resolucion));
			}

		}

		return respuesta;
	}

	/**
	 * devuelve el conjunto de respuesta dependiendo del grupo de respuesta
	 * 
	 * @param grupo
	 *            Elementos de respuestas reunidos bajo la misma etiqueta de
	 *            GrupoDeRespuesta
	 * @return devuelve el conjunto de elementos de tipo Clase Respuesta
	 * @throws Exception
	 * @author yisheng
	 */
	public static ArrayList<Respuesta> GetRespuesta(String grupoRespuesta,  eTipoTrivia tipoTrivia) throws Exception {

		ArrayList<Respuesta> respuesta = new ArrayList<Respuesta>();

		Document documento = leerArchivo(ruta, tipoTrivia);

		NodeList list = documento.getElementsByTagName("GrupoDeRespuesta");
		for (int j = 0; j < list.getLength(); j++) {

			Node node = list.item(j);

			String grupo = node.getFirstChild().getTextContent().toString();
			// System.out.println("pregunta:" + grupo);
			// System.out.println("gp:" + grupoRespuesta);

			if (grupo.equals(grupoRespuesta)) {
				// System.out.println("entra pregunta:" + grupo);

				NodeList lista = node.getChildNodes();
				String orden = "";
				String valor = "";
				// System.out.println("empezamos:" + respuesta.size());
				// System.out.println("empezamos:");
				// System.out.println("longitud:" + lista.getLength());
				for (int x = 0; x < lista.getLength(); x++) {

					Node Tag = lista.item(x);
					orden = "";
					valor = "";
					// System.out.println("Tag1:" +
					// Tag.getFirstChild().getTextContent());
					NodeList listaRespuesta = Tag.getChildNodes();

					for (int k = 0; k < listaRespuesta.getLength(); k++) {
						Node nodoRespuesta = listaRespuesta.item(k);
						String capturaTag = nodoRespuesta.getNodeName();

					
						// System.out.println("Tag0:" + capturaTag);

						if (capturaTag.equals("Orden")) {
							orden = nodoRespuesta.getTextContent();
							// System.out.println("orden:" + orden);

						} else if (capturaTag.equals("Valor")) {
							valor = nodoRespuesta.getTextContent();
							// System.out.println("valor:" + valor);

						}
					}
					if (!valor.equals(""))
						respuesta.add(new Respuesta(grupo, orden, valor));
					// System.out.println("fin:" + respuesta.size());
				}

			}
		}
		// System.out.println("____________");
		return respuesta;
	}

	/**
	 * devuelve la respuesta dependiendo del grupo de respuesta
	 * 
	 * @param grupo
	 *            Elementos de respuestas reunidos bajo la misma etiqueta de
	 *            GrupoDeRespuesta
	 * @param orden
	 *            posición en el orden del grupo de respuesta
	 * @return devuelve la respuesta solicitada
	 * @throws Exception
	 * @author yisheng
	 */
	public static Respuesta GetRespuesta(String grupoRespuesta, String orden, eTipoTrivia tipoTrivia) throws Exception {

		Document documento = leerArchivo(ruta, tipoTrivia);

		NodeList list = documento.getElementsByTagName("GrupoDeRespuesta");
		for (int j = 0; j < list.getLength(); j++) {

			Node node = list.item(j);

			String grupo = node.getFirstChild().getTextContent().toString();
			// System.out.println("pregunta:" + grupo);
			// System.out.println("gp:" + grupoRespuesta);

			if (grupo.equals(grupoRespuesta)) {
				// System.out.println("entra pregunta:" + grupo);

				NodeList lista = node.getChildNodes();
				String posicion = "";
				String valor = "";

				// System.out.println("longitud:" + lista.getLength());
				for (int x = 0; x < lista.getLength(); x++) {
					Node Tag = lista.item(x);

					// System.out.println("Tag1:" +
					// Tag.getFirstChild().getTextContent());
					NodeList listaRespuesta = Tag.getChildNodes();

					for (int k = 0; k < listaRespuesta.getLength(); k++) {
						Node nodoRespuesta = listaRespuesta.item(k);
						String capturaTag = nodoRespuesta.getNodeName();

					
						// System.out.println("Tag0:" + capturaTag);

						if (capturaTag.equals("Orden")) {
							posicion = nodoRespuesta.getTextContent();
							// System.out.println("orden:" + orden);

						} else if (capturaTag.equals("Valor")) {
							valor = nodoRespuesta.getTextContent();
							// System.out.println("valor:" + valor);

						}

					}
					if (orden.equals(posicion)) {
						// System.out.println("valor:" + valor);
						return new Respuesta(grupo, orden, valor);

					}

				}
			}
		}

		return null;
	}

}
