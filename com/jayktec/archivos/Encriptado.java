//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Encriptado                           //
//                   Descripcion                            // 
//             Pantalla para el juego                       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             //  
//    Luis Diaz       09/03/2016      Version Inicial       //
//   Yisheng León     31/03/2016      Cambios para encriptar archivos  //
//////////////////////////////////////////////////////////////

 
package com.jayktec.archivos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import com.jayktec.archivos.AesEncryptionAndDecryption;

/**
 * Lanza el encriptador para un archivo predeterminado que sera en este caso el archivo Xml donde los usuarios guardarian su perfil
 * @author luis
 *
 */
public class Encriptado {
static String linea;
	

public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String ruta="/Partidas/Xml1.xml";
		String ruta3="/Partidas/Xmldes.xml";
		
		File f = new File( ruta );
		BufferedReader entrada;
		try {
			entrada = new BufferedReader( new FileReader( f ) );
			//String linea;
			while(entrada.ready()){
				linea = entrada.readLine();
				//System.out.println(linea);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Aqui termino");
		
				encriptado(ruta3,ruta);
			// desencriptado(ruta,ruta2);
				//sobreEscribir(ruta2, ruta);
						
			}
		
/**
 * lee un archivo y lo encripta en otro archivo	
 * @param rutaLectura archivo a leer
 * @param rutaEscritura archivo a encriptar
 * @return el exito de la operacion
 * @throws Exception
 */
public static Boolean encriptado(String rutaLectura,String rutaEscritura) throws Exception
{
	try {
	

		File f = new File( rutaLectura );
		String archivo="";
		File fileS = new File( rutaEscritura );
		BufferedReader entrada;
		linea="";
		try {
			entrada = new BufferedReader( new FileReader( f ) );
			//String linea;
			while(entrada.ready()){
				linea = entrada.readLine();
				archivo= archivo+linea+'\n';
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		//		System.out.println("Hola mundo encryptado");
				BufferedWriter salida= new BufferedWriter(new FileWriter(fileS));
				
				//salida.write( AesEncryptionAndDecryption.encryption(linea));
				
				salida.write(new String( (byte[]) AesEncryptionAndDecryption.encryption(archivo)));
				salida.close();

				return true;
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}
	
}


/**
 * lee un archivo y lo desencripta en otro archivo	
 * @param rutaLectura archivo a leer
 * @param rutaEscritura archivo a encriptar
 * @return el exito de la operacion
 * @throws Exception
 */
public static Boolean desencriptado(String rutaLectura,String rutaEscritura) throws Exception
{

	try {
		
		String archivo="";
		File f = new File( rutaLectura );

		File fileS = new File( rutaEscritura );
		BufferedReader entrada;
		linea="";
		try {
			entrada = new BufferedReader( new FileReader( f ) );
			//String linea;
			while(entrada.ready()){
				linea = entrada.readLine();
				archivo= archivo+linea+'\n';

			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
			//	System.out.println("Hola mundo encryptado");
			//	System.out.println(AesEncryptionAndDecryption.encryption(linea));
				BufferedWriter salida= new BufferedWriter(new FileWriter(fileS));
				
				//salida.write( AesEncryptionAndDecryption.encryption(linea));
				
				salida.write(AesEncryptionAndDecryption.decryption(archivo));
				salida.close();

				return true;
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}
}

/**
 * lee un archivo y lo sobreescribe en otro archivo	
 * @param rutaLectura archivo a leer
 * @param rutaEscritura archivo a encriptar
 * @return el exito de la operacion
 * @throws Exception
 */
public static Boolean sobreEscribir(String rutaLectura,String rutaEscritura) throws Exception
{	try {
	

	File f = new File( rutaLectura );

	File fileS = new File( rutaEscritura );
	BufferedReader entrada;
	String archivo="";
	linea="";
	try {
		entrada = new BufferedReader( new FileReader( f ) );
		//String linea;
		while(entrada.ready()){
			linea = entrada.readLine();
			archivo= archivo+linea+'\n';

		}
	}catch (IOException e) {
		e.printStackTrace();
	}
	
		//System.out.println("Hola mundo encryptado");
		//	System.out.println(AesEncryptionAndDecryption.encryption(linea));
			BufferedWriter salida= new BufferedWriter(new FileWriter(fileS));
			
			salida.write(archivo);
			
			salida.close();

			return true;
} catch (Exception e) {
	// TODO: handle exception
	return false;
}
}


}

