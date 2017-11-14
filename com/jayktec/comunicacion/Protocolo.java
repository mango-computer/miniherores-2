
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
//   Yisheng Le�n     14/03/2016      Cambios en recibir()  //
//////////////////////////////////////////////////////////////

package com.jayktec.comunicacion;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.jayktec.archivos.Respuesta;
import com.jayktec.archivos.errores;

public class Protocolo {

	private boolean encendido = true;
	private boolean inicio = true;
	private boolean arriba = false;
	private boolean linux= true;
	InputStreamReader br;
	OutputStreamWriter bw;
	Scanner orden = new Scanner(System.in);
	String comando;
	int TotalFilas = 0;
	int TotalColumnas = 0;
	String modo = "";
	char nuevalinea = '\n'; // (char) 13;
	Process p;
	Oyente oyente;
	int XMM = 0;
	/**
	 * solo para pruebas.
	 * 
	 * @param args
	 *            solo para pruebas
	 */
	public static void main(String[] args) {
		new Protocolo("consola", Oyente.XMM).start();

	}

	/**
	 * inicia Mango Paola y enciende el interruptor para escuchar los comandos
	 * que se le envia y las instrucciones que se reciben
	 * 
	 * @param tipo
	 */

	public Protocolo(String tipo, Oyente oyente) {
		modo = tipo;
		this.oyente = oyente;

	}
	public Protocolo() {
	
	}
	
	
	public void stop(){
		if (p.isAlive())
			p.destroy();
	}

	public void start() {
		try {
//			FileHandle fh = Gdx.files.internal("mango/mangoPaola5");
			FileHandle fh;
			String sSistemaOperativo = System.getProperty("os.name");

		//    System.out.println(sSistemaOperativo.toUpperCase());

		    if (sSistemaOperativo.toUpperCase().contains("LINUX"))
			 {
		  //  	System.out.println("Mango paola");
			    	
		    	fh= Gdx.files.internal("mango/mangoPaola5");
			 }
		    else
		    {
		    	linux=false;
		    	fh= Gdx.files.internal("mango/mangoPaola5.exe");
		    }	
		    
			String path = fh.file().getAbsolutePath();

			this.p = new ProcessBuilder(path).start();

			// Se lanza el ejecutable.
			// System.out.println("llegue aqui!");

			// p = new ProcessBuilder("./mango/mangoPaola5").start();

			// Para propositos de prueba en consola de eclipse sin nada grafico
			// usar este ProcessBuilder
			// this.p = new
			// ProcessBuilder("/usr/games/mango/mangoPaola5").start();
			// this.p = new ProcessBuilder("mango/mangoPaola5").start();

			// ESTE FILEHANDLE SOLO FUNCIONA CORRIENDO EL DESKTOP LAUNCHER
			/*
			 * FileHandle fh = Gdx.files.internal("mango/mangoPaola5"); //
			 * FileHandle fh = Gdx.files.internal("mango/mangoPaola5-w32.exe");
			 * 
			 * String path = fh.file().getAbsolutePath(); this.p = new
			 * ProcessBuilder(path).start();
			 */
			// Para propositos de prueba en consola de eclipse sin nada grafico
			// usar este ProcessBuilder
			// p = new ProcessBuilder("mango/mangoPaola5").start();
			// ******************************************************************************************+

			// Solo una linea mas
//			System.out.println("...");
			// ln("ya envie la orden");
			while (encendido) {
				System.out.println("enciendo");
				// Se obtiene el stream de salida del programa
				br = new InputStreamReader(p.getInputStream());
            	bw = new OutputStreamWriter(p.getOutputStream());

				// se identifica el protocolo y ordena el inicio del motor
				if (inicio) {
					inicio = false;
					if (oyente.equals(Oyente.XBOARD)) {
						System.out.println("llegue aqui");
						inicioXBOARD();
						
						recibir("inicio");
						
					} else {
						inicioXMM();
					}

					// recibir("inicio");
					arriba = true;
				}
				System.out.println("estoy parado aqui");
				
				enviar();

			}

		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}

	private void inicioXMM() {
		encendido = false;
//		System.out.println("inicioXMM");
		this.recibir(String.valueOf(this.nuevalinea));
		ejecutar("XMM");
		this.recibir(String.valueOf(this.nuevalinea));
		encendido = true;		

	}

	private void inicioXBOARD() {
		encendido = false;
			//System.out.println("inicioXBOARD");
		this.ejecutar("xboard");
		this.ejecutar(this.nuevalinea);
		this.recibir(String.valueOf(this.nuevalinea));
		this.ejecutar("seeprompt 1");
		this.ejecutar(this.nuevalinea);
		this.recibir(String.valueOf(this.nuevalinea));
		this.ejecutar("seetree 0");
		this.recibir(String.valueOf(this.nuevalinea));
		this.ejecutar("seecheck 1");
		this.recibir(String.valueOf(this.nuevalinea));

		encendido = true;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isArriba() {
		return arriba;
	}

	/**
	 * Ejecuta el comando en Mango Paola, si el comando es salida apaga el
	 * interruptor y mango paola deja de escuchar.
	 * 
	 * @param comando
	 */
	public void ejecutar(String comando) {

		try {

			//System.out.println("traduccion de comando:" + traducirComando(comando));
			bw.write(traducirComando(comando) + '\n');
			// bw.write(comando + '\n');
			 //System.out.println("enviado a xmm");

			if (comando.equals("salida") || comando.equals("SALIDA")) {
				encendido = false;
				// System.out.println("Hasta la vista baby");
			}
			bw.flush();

		} catch (IOException e) {
			encendido = false;
			System.out.println("error:"+ e.getMessage().toString());
		}
	}

	/**
	 * metodo que comprueba el estado del protocolo
	 * 
	 * @return devuelve si el protocolo esta encendido o con errores
	 */
	public boolean isEncendido() {
		return encendido;
	}

	/**
	 * ejecuta un comando de nueva linea
	 * 
	 * @param nuevalinea
	 */
	private void ejecutar(char nuevalinea) {

		try {
			bw.write(nuevalinea);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			encendido = false;

			e.printStackTrace();
		}

	}

	/**
	 * finaliza el protocolo
	 */
	@Override
	protected void finalize() {
		// TODO Auto-generated method stub
		try {
			encendido=false;
			p.destroy();
			super.finalize();

		}

		catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Envia el comando a mango paola y escucha las instrucciones
	 */
	private String[] enviar() {
		comando = orden.nextLine();
		ejecutar(comando);
		recibir(comando);
		return null;
	}

	/**
	 * Envia el comando a mango paola y escucha las instrucciones
	 */
	public ArrayList<String> enviar(String comando) {
		// br = new InputStreamReader(p.getInputStream());
		// bw = new OutputStreamWriter(p.getOutputStream());
//		System.out.println("por ejecutar el :" + comando);
		// System.out.println("XMM antes:"+XMM);

		/*
		 * if (XMM<=2) { System.out.println("entre"); ejecutar(nuevalinea);
		 * recibir("vacio");
		 * 
		 * }
		 */
		
		ejecutar(comando);
		// ejecutar(nuevalinea);
		// System.out.println("ejecutado el :"+ comando);

		return recibir(comando);
	}

	/**
	 * Envia el comando a mango paola
	 * 
	 * @param comando
	 *            orden a ser ejecutada
	 * @param c
	 * @return
	 */
	public ArrayList<String> enviar(String comando, char c) {
		ejecutar(comando);
		return null;
	}

	/**
	 * metodo que escucha la respuesta del motor de busqueda
	 * 
	 * @return devuelve un arreglo de string con el mensaje interpretado seg�n
	 *         el protocolo de comunicacion a utilizar
	 */
	private ArrayList<String> recibir(String comando) {

		int bit = 0;
		String entrada = "";
		int cantEntradas = 0;
		char c;
		ArrayList<String> interpretado = null;

		while (bit != -1)
			
			// entrada="";
		{ 
			
			if (modo.equals("consola")) {
				try {
			//	br.wait(5);
					bit = br.read();
					c = (char) bit;		
					if (c == '\n') {
						// System.out.println(entrada);
						entrada = "";
						cantEntradas++;
					} else {
						
						entrada += c;
					}

					if (entrada.equals("Blancas> ") || entrada.equals("Negras> ") || entrada.equals("XMM> ")
							|| entrada.equals("xboard>")) {
						//System.out.println(entrada);

						interpretado = interpretarXMM(entrada);
						entrada = "";
						bit = -1;
						cantEntradas = 0;

					} else if (entrada.equals(" ")) {
						// System.out.println(entrada);
						bit = -1;

						cantEntradas = 0;

					}
				} catch (Exception e) {
					// System.out.println("error re:"+
					// e.getMessage().toString());;
				}
			}

			else
				try {

			//		 System.out.println("escuchando el juego");
//					 System.out.println("interpretarXboard");
					System.out.println("esperando respuesta");
					bit = br.read();//TODO
					 System.out.println("Ya tengo una respuesta");
//					 System.out.println("interpretarXboard2222");
					c = (char) bit;
					
					// System.out.println("CHAR:"+c+"--");
					
					if (entrada.equals(" ") || entrada.equals('\n')) {
						entrada = "";
						cantEntradas=0;
					}

					if (entrada.equals("Blancas>") || entrada.equals("Negras>") || entrada.equals("xboard>")) 
					{
						entrada = "";
						bit = -1;
						cantEntradas = 0;
					}

					if (entrada.equals("XMM>")) {

						// System.out.println(entrada);
						// System.out.println("xmm:"+XMM);

						entrada = "";
						if (XMM <= 1) {
							XMM++;

						}
						if (XMM > 1) {
							// XMM=3;
							if (cantEntradas > 0)
								bit = -1;
							else
								cantEntradas++;
							// ejecutar(nuevalinea);

						}
					}
					//// algmov
					if (entrada.contains("xboard>"))
					{
						entrada= entrada.substring(0,entrada.length()-"xboard>".length()-1);
//						System.out.println("entrada algmov:"+ entrada);
						
						if (cantEntradas == 0)
							interpretado = interpretarXBOARD(entrada);
						else
							interpretado.add(interpretarXBOARD(entrada).get(0));
						
						entrada = "xboard>";
						//c='\n';
						bit = -1;
						entrada="";
						cantEntradas++;

					}
					
					
					if (c == '\n') {
						 System.out.println(" entrada " + entrada);
					
if(!linux)
{
	if (entrada.length()>0)
	{
		entrada=entrada.substring(0,entrada.length()-1);
	}
}
						
						if (oyente.equals(Oyente.XMM)) {
							 if (cantEntradas == 0)
								 interpretado = interpretarXMM(entrada);								
							 else
								 interpretado.add(interpretarXMM(entrada).get(0));
							 
							entrada = "";
							cantEntradas++;
							

							if (entrada.equals("XMM>")) { // para Salir
								bit = -1;
								entrada = "";
								cantEntradas++;
							}

						} else {
//							 System.out.println("interpretarXboard");

							if (cantEntradas == 0)
								interpretado = interpretarXBOARD(entrada);
							else
								interpretado.add(interpretarXBOARD(entrada).get(0));

							entrada = "";
							cantEntradas++;

							if (entrada.equals("xboard>")) {
//								System.out.println("bit = - 1");
								bit = -1;
								entrada = "";
								cantEntradas++;
							}

						}
						entrada = "";
					} else {
						entrada += c;
					//	 System.out.println("interpretar: " + entrada);
					}

				}

				catch (NullPointerException e2) {
					// TODO: handle exception
					// System.out.println("error re:"+
					// e2.getMessage().toString());;

				} catch (Exception e) {
					// System.out.println("error re:"+
					// e.getMessage().toString());;
				}
		}

		//System.out.println("fin de recibir :" + comando);
		if (interpretado !=null)
			mostrarArreglos(interpretado);
		
		return interpretado;

	}

	/**
	 * Interpreta el mensaje recibido segun XBOARD y lo ordena probando
	 * 
	 * @return
	 */

	private ArrayList<String> interpretarXBOARD(String entrada) {

		// TODO Auto-generated method stub

//		System.out.println("interpretarXBOARD in");
		ArrayList<String> respuesta = new ArrayList<String>();	
		// System.out.println("longitud:"+ entrada.length());

//		System.out.println("entrada:" + entrada);

		if (entrada.length() > 2) {

			String comando = entrada.substring(0, 3);

			// System.out.println("las tres letras son:"+comando);
			if (comando.contains("COD")) {
				// System.out.println("ES UN ERROR");

				respuesta.add(comando);

				// System.out.println(entrada.substring(0, 6));
				for (errores er : errores.values()) {
					if (entrada.substring(0, 6).equals(er.toString())) {
						respuesta.add(er.Getdesc().toString());
						break;
					}

				}

			} else if (entrada.contains("Draw")) {
				respuesta.add(entrada);
			} else if (entrada.contains("/")) {
				// System.out.println("ES UN FEN");
				// guardarStringFen(entrada);
				respuesta.add("FEN");
				respuesta.add(entrada);
			}

			else if (esNumero(entrada)) {
				respuesta = traducir(entrada);
			} else if ((comando.equals("BLA")) || (comando.equals("NGR")) || (comando.equals("OK"))) {
				// System.out.println("ES UN NO SE");

				respuesta.add(comando);
			} else if (esMovimientoIlegal(entrada)) {
				// System.out.println("es movimiento ilegal: " + entrada );
				// System.out.println("agregando a la respuesta: " + entrada );
				respuesta.add(entrada);
			} else if (esPgn(entrada)) {
				// System.out.println("es un pgn: " + entrada );
				respuesta.add(entrada);
			}

			else {
				respuesta.add(entrada);
				// System.out.println(entrada);

			}
		}

		else {
			// System.out.println("aca loco:"+entrada+"--");
			respuesta.add(entrada);
			// System.out.println(entrada);
		}

		// System.out.println("saliendo de interpretar");
		// mostrarArreglos(respuesta);
		//System.out.println("devolviendo respuesta: " + respuesta);
		// respuesta.add("FIN");
		return respuesta;
	}

	public ArrayList<String> guardarStringFen(String entrada) {

		ArrayList<String> gFen = new ArrayList<String>();
		gFen = interpretarXMM(entrada);
		return gFen;
	}

	/**
	 * Interpreta el mensaje recibidose gun XMM y lo ordena probando
	 * 
	 * @param entrada
	 *            mensaje a interpretar
	 * @return listado de respuesta
	 */
	public ArrayList<String> interpretarXMM(String entrada) {

		//System.out.println("entre a interpretar Xmm: " + entrada );
		ArrayList<String> respuesta = new ArrayList<String>();

		if (entrada.length() > 2) {

			String comando = entrada.substring(0, 3);

			// System.out.println("las tres letras son:"+comando);
			if (comando.contains("COD")) {
				// System.out.println("ES UN ERROR");
				respuesta.add(entrada);

				// System.out.println(entrada.substring(0, 6));
				for (errores er : errores.values()) {
					if (entrada.substring(0, 6).equals(er.toString())) {
						respuesta.add(er.Getdesc().toString());
						break;
					}

				}

			} else if (entrada.contains("/")) {
				//System.out.println("ES UN FEN");
				// guardarStringFen(entrada);
				respuesta.add("FEN");
				respuesta.add(entrada);
			} else if (esNumero(entrada)) {

				respuesta = traducir(entrada);
			} else if ((comando.equals("BLA")) || (comando.equals("NGR"))){
				// System.out.println("ES UN NO SE");
				respuesta.add(comando);
			} else if (!entrada.contains("OK 1.0"))	{
				respuesta.add(entrada);
				// System.out.println(entrada);
			}
		}

		else {
			//System.out.println("aca loco:"+entrada+"--");
		//	if (!entrada.contains("OK"))
			respuesta.add(entrada);			
			// System.out.println(entrada);
		}
		 //System.out.println("saliendo de interpretar");
		//mostrarArreglos(respuesta);
		return respuesta;

	}

	/**
	 * metodo de prueba para mostra el arreglo
	 * 
	 * @param arreglo
	 *            a ser mostrado
	 */
	private void mostrarArreglos(ArrayList<String> arreglo) {
	//	 System.out.println("SALIDA:::");
		 //try {
				for (int i = 0; i < arreglo.size(); i++) {
//					System.out.println(arreglo.get(i));
				}
			
		//} catch (Exception e) {
			// TODO: handle exception
		//}

	}

	/**
	 * Recibe una cadena de posiciones y las regresa divididas en posiciones
	 * validas
	 * 
	 * @param entrada
	 * @return
	 */
	@SuppressWarnings("null")
	private ArrayList<String> traducir(String entrada) {
		ArrayList<String> salida = null;
		String posicion;
		Traductor traduce = new Traductor();
		salida.add("POS");
		for (int i = 0; i < entrada.length(); i++) {
			posicion = entrada.substring(i, i++);
			salida.add(traduce.NumerosACoordenadas(8, 8, Integer.parseInt(posicion)));

		}

		return salida;
	}

	/**
	 * Recibe una cadena de posiciones y las regresa divididas en posiciones
	 * validas SI EL COMANDO ES MOV,MVL , MVV MJF MJN
	 * 
	 * @param entrada
	 * @return
	 */
	private String traducirComando(String comando) {
		String salida;
		String coordenadas;
		Traductor traduce = new Traductor();
		if (comando.length() > 2) {

			salida = comando.substring(0, 3);
			// System.out.println("salida:"+salida);

			if ((salida.equals("MOV")) || (salida.equals("MVL")) || (salida.equals("MVV"))) {
				// salida = salida + " ";
				// System.out.println("comando:"+ comando);
				// System.out.println("comando length:"+ comando.length());

				for (int i = 4; i < comando.length(); i++) {
					if (i + 2 <= comando.length()) {
						coordenadas = comando.substring(i, i + 2);
						int rest = (traduce.CoordenadasANumeros(8, 8, coordenadas));

						String cambio = Integer.toString(rest);

						// System.out.println("AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII"
						// + cambio);

						salida = salida + " " + cambio;

						i++;
					}
				}

				// } else if (salida.equals("MJF")) {
				// coordenadas = comando.substring(3, 4);
				// salida = salida + (traduce.CoordenadasANumeros(8, 8,
				// coordenadas))
				// + comando.substring(5, comando.length() - 1);
				//
			} else {
				salida = comando;
			}

			// MJN
		} else
			salida = comando;
		// System.out.println("salida:"+salida);

		return salida;
	}

	/**
	 * comprobar si la cadena de caracteres es un string
	 * 
	 * @param numero
	 *            la cadena a compro bar
	 * @return el valor de la consulta
	 */
	public boolean esNumero(String numero) {
		try {
			Long.parseLong(numero);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * comprobar si la cadena de caracteres es un pgn
	 *
	 * @param pPgn
	 *            la cadena a compro bar
	 * @return el valor de la consulta
	 */
	public boolean esPgn(String pPgn) {
		try {
			if (pPgn.contains("move")) {
				// System.out.println("valindo pgn" + pPgn);
				if (pPgn.length() == 9 || pPgn.length() == 10) {
					Integer.parseInt(pPgn.substring(6, 7));
					Integer.parseInt(pPgn.substring(8, 9));
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
	}

	public boolean esMovimientoIlegal(String pEntrada) {
		if (pEntrada.contains("Illegal move"))
			return true;
		return false;

	}

	public boolean esMate(String pEntrada) {
		if (pEntrada.contains("{Black mates}") || pEntrada.contains("{White mates}")) {
			return true;
		}
		return false;
	}

	
	public void clean(){
		System.out.println(br+" - "+p);
		inicio = true;
		try {
			p.getInputStream().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stop();
		arriba = false;
		start();
		
		System.out.println(br+" - "+p);
	}
	
	
	
}