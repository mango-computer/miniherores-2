//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Protocolo.java                            //
//                   Descripcion                            // 
//             Pantalla para el juego                       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             //  
//    Luis Diaz       09/03/2016      Version Inicial       //
//   Yisheng León     31/03/2016     Inclusion del encriptamiento  //
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Crea un archivo Xml en una localizacion especifica conel Id = 1 y donde los
 * usuarios podran introducir su nombre lo demas se llenara solo al ser la
 * primera vez que lo creen luego ya solo se podria sobre escribir las partida
 * en la que se encuentre
 * 
 * @author luis
 *
 */

public class Partida {

	static Scanner entrada = new Scanner(System.in);
	private static String ruta = "Partidas/Xml1.xml";
	private static String rutaTemporal = "Partidas/Xml1tmp.xml";
//	private static String rutaCreditos = ".Partidas/Creditos";

	// cambio de lectura de partida
	private String nombre; 
	private String numero;
	private String batalla;
	private String minijuego;
	private String fen;
	private  String nivel;
	private String monedas; 
	private String personaje;
	private String dificultad;
	
	
	/**
	 * @return the dificultad
	 */
	public String getDificultad() {
		return dificultad;
	}

	/**
	 * @param dificultad the dificultad to set
	 */
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	/**
	 * @return the batalla
	 */
	public String getBatalla() {
		return batalla;
	}

	/**
	 * @param batalla the batalla to set
	 */
	public void setBatalla(String batalla) {
		this.batalla = batalla;
	}

	
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the minijuego
	 */
	public String getMinijuego() {
		return minijuego;
	}

	/**
	 * @param minijuego the minijuego to set
	 */
	public void setMinijuego(String minijuego) {
		this.minijuego = minijuego;
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
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the monedas
	 */
	public String getMonedas() {
		return monedas;
	}

	/**
	 * @param monedas the monedas to set
	 */
	public void setMonedas(String monedas) {
		this.monedas = monedas;
	}

	/**
	 * @return the personaje
	 */
	public String getPersonaje() {
		return personaje;
	}

	/**
	 * @param personaje the personaje to set
	 */
	public void setPersonaje(String personaje) {
		this.personaje = personaje;
	}

	public static void main(String[] args) throws Exception {
	
	boolean encriptar = true;

		
	if (!encriptar){		//		desencriptar
		System.out.println("desencriptando:");
		rutaTemporal ="/home/vladimir/git/miniHeroes/core/minijuego27.xml";
		Document desencriptado = leerArchivo("/home/vladimir/git/miniHeroes/core/minijuego27e.xml");
		escribeArchivo(desencriptado,"minijuego27.xml");
		System.out.println("desencriptado:");
	}
	else{// encriptar
		
		rutaTemporal = "triviatmp.xml"; 
		File xmlFile = new File(rutaTemporal);

		DocumentBuilderFactory creadorDocumento = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentoCreado = creadorDocumento.newDocumentBuilder();
		Document documento = documentoCreado.parse(xmlFile);

		System.out.println("encriptando");
		
		escribeArchivo(documento,"encriptado.xml");
		}
	}

	
	public  Partida(String nombrePartida) throws Exception
	{
		if (ExistePartida(nombrePartida))
		{
			this.nombre=nombrePartida;
//			this.numero=GetAtributo(nombrePartida, "Numero");
//			this.minijuego=GetAtributo(nombrePartida, "Minijuego");
//			this.fen=GetAtributo(nombrePartida, "FenActual");
//			this.nivel=GetAtributo(nombrePartida, "UltimoNivel");
//			this.monedas=GetAtributo(nombrePartida, "Monedas");
//			this.batalla=GetAtributo(nombrePartida, "Batalla");
//			this.personaje=GetAtributo(nombrePartida, "PersonajeNinio");
//			this.dificultad=GetAtributo(nombrePartida, "Dificultad");
//			
	GetAtributos();
		}
	}
	/**
	 * lee el archivo encriptado xml, lo desencripta en un temporal y devuelve
	 * el archivo temporal en un documento xml de formato .DOM
	 * 
	 * @param ruta
	 *            ruta donde se desencripta el archivo
	 * @return el Documento XML en formato DOM
	 * @throws Exception
	 *             no se pudo leer
	 */
	// @SuppressWarnings("static-access")
	public static Document leerArchivo(String ruta) throws Exception {
		
		
	 Encriptado.desencriptado(ruta, rutaTemporal);

		File xmlFile = new File(rutaTemporal);
		DocumentBuilderFactory creadorDocumento = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentoCreado = creadorDocumento.newDocumentBuilder();
		Document documento = documentoCreado.parse(xmlFile);

		return documento;

	}

	/**
	 * lee el archivo desencriptado xml en el temporal, lo encripta en un la
	 * ruta definitiva
	 * 
	 * @param documento
	 *            documento xml de partida
	 * @param ruta
	 *            ruta donde se encuentra el documento el Documento XML en
	 *            formato DOM
	 * @throws Exception
	 *             no se pudo escribir archivo
	 */
	@SuppressWarnings("static-access")
	public static void escribeArchivo(Document documento, String ruta) throws Exception {
		Encriptado encriptado = new Encriptado();
		// System.out.println("leyendo y encriptando");

		TransformerFactory transformacion = TransformerFactory.newInstance();
		Transformer transformador = transformacion.newTransformer();
		DOMSource fuente = new DOMSource(documento);

		StreamResult streamResult = new StreamResult(new File(rutaTemporal));
		transformador.transform(fuente, streamResult);
		encriptado.encriptado(rutaTemporal, ruta);

	}

	/**
	 * Actualiza una partida existente dado el valor de los parametros
	 * 

	 * @return el valor de la operación , si fue exitosa verdadero de lo
	 *         contrario falso
	 * @throws Exception
	 *             no se actualizo la partida
	 * @author yisheng
	 */
	public  Boolean ActualizarPartida() throws Exception {

		try {

			Document documento = leerArchivo(ruta);

			NodeList list = documento.getElementsByTagName("Partida");
			// System.out.println("entre " + list.getLength());

			for (int j = 0; j < list.getLength(); j++) {
				// System.out.println("entre: "+ j);

				Node node = list.item(j);
				NodeList lista = node.getChildNodes();

				if (nombre.equals((node.getFirstChild().getTextContent()))) {
					for (int i = 0; i < lista.getLength(); i++) {

						Node nodo = lista.item(i);

						if ("Numero".equals(nodo.getNodeName())) {

							nodo.setTextContent(numero);
						}
						if ("Minijuego".equals(nodo.getNodeName())) {
							nodo.setTextContent(minijuego);
						}
						if ("FenActual".equals(nodo.getNodeName())) {
							nodo.setTextContent(fen);
						}
						if ("UltimoNivel".equals(nodo.getNodeName())) {
							nodo.setTextContent(nivel);
						}
						if ("Monedas".equals(nodo.getNodeName())) {
							nodo.setTextContent(monedas);
						}

						if ("Batalla".equals(nodo.getNodeName())) {
							nodo.setTextContent(batalla);
						}
						if ("Dificultad".equals(nodo.getNodeName())) {
							nodo.setTextContent(dificultad);
						}
						if ("PersonajeNinio".equals(nodo.getNodeName())) {
							nodo.setTextContent(personaje);

						}
					}
				}

			}
			// System.out.println("entre a modificar");

			escribeArchivo(documento, ruta);

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	
	/**
	 * Actualiza una partida existente dado el valor de los parametros
	 * 
	 * @param nombre
	 *            nombre de la partida
	 * @param Minijuego
	 *            minijuego actual
	 * @param fen
	 *            fen del mini juego actual
	 * @param nivel
	 *            nivel , si el juego lo posee
	 * @param monedas
	 *            numero de monedas
	 * @param mundo
	 *            batalla en la que se encuentra el minijuego
	 * @param fondo
	 *            fondo de la pantalla
	 * @param muros
	 *            existencia de muros
	 * @param personaje
	 *            personaje seleccionado por el jugador
	 * @param numero
	 *            numero de partida
	 * @return el valor de la operación , si fue exitosa verdadero de lo
	 *         contrario falso
	 * @throws Exception
	 *             no se actualizo la partida
	 * @author yisheng
	 */
	public static Boolean ActualizarPartida(String nombre, String numero, String Minijuego, String fen, String nivel,
			String monedas, String mundo, String fondo, String muros, String personaje) throws Exception {

		try {

			Document documento = leerArchivo(ruta);

			NodeList list = documento.getElementsByTagName("Partida");
			// System.out.println("entre " + list.getLength());

			for (int j = 0; j < list.getLength(); j++) {
				// System.out.println("entre: "+ j);

				Node node = list.item(j);
				NodeList lista = node.getChildNodes();

				if (nombre.equals((node.getFirstChild().getTextContent()))) {
					for (int i = 0; i < lista.getLength(); i++) {

						Node nodo = lista.item(i);

						if ("Numero".equals(nodo.getNodeName())) {

							nodo.setTextContent(numero);
						}
						if ("Minijuego".equals(nodo.getNodeName())) {
							nodo.setTextContent(Minijuego);
						}
						if ("FenActual".equals(nodo.getNodeName())) {
							nodo.setTextContent(fen);
						}
						if ("UltimoNivel".equals(nodo.getNodeName())) {
							nodo.setTextContent(nivel);
						}
						if ("Monedas".equals(nodo.getNodeName())) {
							nodo.setTextContent(monedas);
						}

						if ("Mundo".equals(nodo.getNodeName())) {
							nodo.setTextContent(mundo);
						}
						if ("Fondo".equals(nodo.getNodeName())) {
							nodo.setTextContent(fondo);
						}
						if ("Muros".equals(nodo.getNodeName())) {
							nodo.setTextContent(muros);

						}
						if ("PersonajeHistorico".equals(nodo.getNodeName())) {
							nodo.setTextContent(personaje);
						}

					}
				}

			}
			// System.out.println("entre a modificar");

			escribeArchivo(documento, ruta);

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	/**
	 * Actualiza una partida existente dado el valor de los parametros
	 * 
	 * @param nombre
	 *            nombre de la partida
	 * @param atributo
	 *            nombre del atributo a modificar
	 * @param valor
	 *            del atributo a modificar
	 * @return el valor de la operación , si fue exitosa verdadero de lo
	 *         contrario falso
	 * @throws Exception
	 *             no se actualizo la partida
	 * @author yisheng
	 */
	private static Boolean ActualizarAtributo(String nombre, String atributo, String valor) throws Exception {

		try {

			Document documento = leerArchivo(ruta);

			NodeList list = documento.getElementsByTagName("Partida");
			// System.out.println("entre " + list.getLength());

			for (int j = 0; j < list.getLength(); j++) {
				// System.out.println("entre: "+ j);

				Node node = list.item(j);
				NodeList lista = node.getChildNodes();

				if (nombre.equals((node.getFirstChild().getTextContent()))) {
					for (int i = 0; i < lista.getLength(); i++) {

						Node nodo = lista.item(i);

						if (atributo.equals(nodo.getNodeName())) {

							nodo.setTextContent(valor);
						}
					}
				}

			}
			// System.out.println("entre a modificar");

			escribeArchivo(documento, ruta);

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	/**
	 * Te dice si existe una partida en el registro
	 * 
	 * @param nombre
	 *            nombre de la partida
	 * @throws Exception
	 *             no existe la partida
	 * @author yisheng
	 * @return si existe la partida o no
	 */

	public static Boolean ExistePartida(String nombre) throws Exception {

		Boolean respuesta = false;

		Document documento = leerArchivo(ruta);

		NodeList list = documento.getElementsByTagName("Partida");
		// 	.println("entre " + list.getLength());

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre: " + j);

			Node node = list.item(j);

			if (nombre.equals((node.getFirstChild().getTextContent()))) {

				return true;
			}

		}

		return respuesta;
	}

	/**
	 * Borra una partida existente en el registro
	 * 
	 * @param nombre
	 *            nombre de la partida
	 * @throws Exception
	 *             no se borro la partida
	 * @return Devuelve true si la operacion fue exitoso o false si sucedio lo
	 *         contrrario
	 * @author yisheng
	 */
	public static Boolean BorrarPartida(String nombre) throws Exception {

		Boolean respuesta = false;

		Document documento = leerArchivo(ruta);

		NodeList list = documento.getElementsByTagName("Partida");
		// System.out.println("entre " + list.getLength());

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre: " + j);

			Node node = list.item(j);
			// System.out.println("nombre: " + nombre);
			// System.out.println("nodo: " +
			// node.getFirstChild().getTextContent());

			if (nombre.equals((node.getFirstChild().getTextContent()))) {
				node.getParentNode().removeChild(node);
				respuesta = true;

			}

		}

		if (respuesta) {
			// System.out.println("entre a borrar");
			escribeArchivo(documento, ruta);
		}
		return respuesta;
	}

	/**
	 * Devuelve el listado de todas las partidas que se encuentran en el xml
	 * 
	 * @return devuelve el arreglo de partidas.
	 * @author yisheng
	 * @throws Exception
	 *             no se encontro lista de partidas
	 */
	public static ArrayList<String> ListadoPartidas() throws Exception {

		ArrayList<String> respuesta = new ArrayList<String>();

		Document documento = leerArchivo(ruta);

		NodeList list = documento.getElementsByTagName("Partida");
		// System.out.println("entre listado " + list.getLength());

		for (int j = 0; j < list.getLength(); j++) {
//			System.out.println("entre nodo: " + j);

			Node node = list.item(j);
			// NodeList lista= node.getChildNodes();
			// System.out.println("partida:" +
			// node.getFirstChild().getTextContent().toString());
			respuesta.add(node.getFirstChild().getTextContent().toString());

		}

		return respuesta;
	}
	
//	public static ArrayList<String> ListadoCreditos() throws Exception {
//	
//		ArrayList<String> respuesta = new ArrayList<String>();
//		
//		Document documento = leerArchivo(rutaCreditos);
//		
//		NodeList list = documento.getElementsByTagName("Creditos");
//		
//		
//		for(int j = 0; j<list.getLength(); j++){
//			Node node = list.item(j);
//			respuesta.add(node.getFirstChild().getTextContent().toString());
//		}
//		
//		return respuesta;
//	}

	/**
	 *
	 * Te devuelve el valor de un atributo guradado en una partida
	 * 
	 * @throws Exception
	 *             no pudo encontrar el atributo
	 * @author yisheng
	 */
	private void GetAtributos() throws Exception {

		
		Document documento = leerArchivo(ruta);

		NodeList list = documento.getElementsByTagName("Partida");

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre: " + j);

			Node node = list.item(j);
			NodeList lista = node.getChildNodes();

			if (nombre.equals((node.getFirstChild().getTextContent()))) {


					for (int i = 0; i < lista.getLength(); i++) {

						Node elemento = lista.item(i);

						if ("Numero".equals(elemento.getNodeName())) {

							numero=elemento.getTextContent();
							
						}
						if ("Minijuego".equals(elemento.getNodeName())) {
							minijuego=elemento.getTextContent();
						}
						if ("FenActual".equals(elemento.getNodeName())) {
							fen=elemento.getTextContent();
						}
						if ("UltimoNivel".equals(elemento.getNodeName())) {
							nivel=elemento.getTextContent();
						}
						if ("Monedas".equals(elemento.getNodeName())) {
							monedas=elemento.getTextContent();
						}

						if ("Batalla".equals(elemento.getNodeName())) {
							batalla=elemento.getTextContent();
						}
						if ("Dificultad".equals(elemento.getNodeName())) {
							dificultad=elemento.getTextContent();
						}
						if ("PersonajeNinio".equals(elemento.getNodeName())) {
							personaje=elemento.getTextContent();

						}
					
				}
			}

		}

	
	}
	
	
	
	/**
	 *
	 * Te devuelve el valor de un atributo guradado en una partida
	 * 
	 * @param nombre
	 *            nombre de la partida
	 * @param atributo
	 *            valor del dato que se desea conocer de la partida
	 * @return devuelve el valor solicitado de la partida
	 * @throws Exception
	 *             no pudo encontrar el atributo
	 * @author yisheng
	 */
	private static String GetAtributo(String nombre, String atributo) throws Exception {

		String respuesta = "";

		Document documento = leerArchivo(ruta);

		NodeList list = documento.getElementsByTagName("Partida");

		for (int j = 0; j < list.getLength(); j++) {
			// System.out.println("entre: " + j);

			Node node = list.item(j);
			NodeList lista = node.getChildNodes();

			if (nombre.equals((node.getFirstChild().getTextContent()))) {

				for (int i = 0; i < lista.getLength(); i++) {

					Node nodo = lista.item(i);

					if (atributo.equals(nodo.getNodeName())) {
						respuesta = nodo.getTextContent();
						return respuesta;
					}
				}
			}

		}

		return respuesta;
	}

	/**
	 * Creación de nueva partida en el archivo base colocando valores iniciales
	 * por defecto.
	 * 
	 * @param Partida
	 *            nombre de la partida.
	 * @param Personaje
	 *            personaje seleccionado para crear en la partida
	 * @param nivelDificultad
	 *            dificultad del juego seleccionado
	 * @return si es exitosa o no la operación de creación de la nueva partida
	 * @author yisheng
	 * @throws Exception
	 *             error crear partida
	 */
	public static Boolean nuevaPartida(String Partida, String Personaje, String nivelDificultad) throws Exception {

		try {
			
			String cantidadMonedas = Dificultad.GetAtributo(nivelDificultad, "Moneda", "Inicio"); 
		//	System.out.println("cantidadMonedas:"+ cantidadMonedas);
			Document documento = leerArchivo(ruta);

			NodeList list = documento.getElementsByTagName("Juego");

			Node nodo = list.item(0);
			Element raiz = (Element) nodo;

			Element partida = documento.createElement("Partida");
			partida.appendChild(documento.createTextNode((Partida)));
			raiz.appendChild(partida);

			Element numero = documento.createElement("Numero");
			numero.appendChild(documento.createTextNode("1"));
			partida.appendChild(numero);

			Element minijuego = documento.createElement("Minijuego");
			minijuego.appendChild(documento.createTextNode("Minijuego1"));
			// System.out.println("agregando minijuego1");
			partida.appendChild(minijuego);

			Element fen = documento.createElement("FenActual");
			fen.appendChild(documento.createTextNode(""));
			partida.appendChild(fen);

			Element ultimoM = documento.createElement("UltimoNivel");
			ultimoM.appendChild(documento.createTextNode("1"));
			partida.appendChild(ultimoM);

			Element nivel = documento.createElement("Nivel");
			nivel.appendChild(documento.createTextNode("1"));
			partida.appendChild(nivel);

			Element monedas = documento.createElement("Monedas");
			monedas.appendChild(documento.createTextNode(cantidadMonedas));
			partida.appendChild(monedas);

			Element batalla = documento.createElement("Batalla");
			batalla.appendChild(documento.createTextNode("Batalla1"));
			partida.appendChild(batalla);

			Element muros = documento.createElement("Muros");
			muros.appendChild(documento.createTextNode("0"));
			partida.appendChild(muros);

			Element personaje = documento.createElement("PersonajeNinio");
			personaje.appendChild(documento.createTextNode(Personaje));
			partida.appendChild(personaje);

			Element dificultad = documento.createElement("Dificultad");
			dificultad.appendChild(documento.createTextNode(nivelDificultad));
			partida.appendChild(dificultad);

			escribeArchivo(documento, ruta);
		} catch (Exception e) {
			System.out.println("no puedo:" + e.getMessage().toString());
			return false;
		}
		return true;

	}

}
