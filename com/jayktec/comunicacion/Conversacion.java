package com.jayktec.comunicacion;

import java.util.ArrayList;

import com.jayktec.comunicacion.Protocolo;

public class Conversacion extends Thread
	{
	
	public Protocolo protocolo;
	Boolean encendido=false;
	private Oyente oyente;
	private int reinicio=0;
	private boolean cerrando = false;
	 	
			
	
	/**Clase que permite iniciar y gestionar una coversacion con el motor de ajedrez mango paola
			 * 
			 * @param oyente parametro que defino el tipo de protocolo de conversación a utilizar , los valores estan en el Enum oyente
			 */
	public Conversacion(Oyente oyente) 
	{
		super();
		this.oyente = oyente;
	}	
	 
	/**
	 * Inicio de protocolo utilizado por el metodo start()
	 */
	public void run()
	   {
	      encendido=true;
		   protocolo = new Protocolo("juego",oyente);
		   protocolo.start();
		   
		 	
	   }


	/**
	 * metodo que recibe un comando de acción para el juego y lo gestiona a traves del protoco, 
	 * @param comando . orden a ser ejecutada por el motor de ajedrez
	 * @param fen
	 * @param dificultad
	 * @return
	 * @throws Exception , devuelve una excepción si se reinicia la conversación una cantidad de veces mayor a 5
	 */
	   // yle 20160516 recibir fen para rehabilitar correctamente mango paola y continuar el juego..
	
	   public ArrayList<String> recibirComando(String comando,String fen , String dificultad) throws Exception
	   {
		 //  System.out.println("recibirComando: " + comando) ;
		   ArrayList<String> resp;
				
		   if (protocolo.isArriba())
		  {
			  
			   if (reinicio>0)
			   {
				   protocolo.enviar(dificultad);

				   protocolo.enviar("setboard  " + fen);   
				   resp= protocolo.enviar(comando);
			   }
			   else
			   {
				    resp= protocolo.enviar(comando);
			   }
			  reinicio=0;
			  
			  return resp;
		  }
		  else
		  {
			  reinicio++;
			 if ((reinicio <5))
			 {
				 // System.out.println("REINICIANDO "+reinicio);
			  return reiniciar(comando,fen , dificultad);
			  
			  }
			 
			 else
				 throw new Exception("Cantidad maxima de reinicios alcanzado"); 
		  }		  
	   }
	   
	  /**
	   * metodo que recibe un comando de acción para el juego y lo gestiona a traves del protoco, 
	   * @param comando . orden a ser ejecutada por el motor de ajedrez
	   * @throws Exception
	   */
	   public void enviarComando(String comando) throws Exception
	   {
		   if (encendido){
			   protocolo.enviar(comando, 'c');
			   if (comando.contains("quit")){
				   encendido=false;
				   protocolo.stop();
				   protocolo.finalize();				   
			   }
		   }						  		   
	   }
	   
	   /**
	    * Metodo para reiniciar una conversacion
	 * @param dificultad 
	 * @param fen 
	 * @throws Exception 
	    */
	   private ArrayList<String>  reiniciar( String comando, String fen, String dificultad) throws Exception  {
		   
		   new Thread(this).start();
		   int i=0;
		   //		   System.out.println("REVISAR SI ESTA ARRIBA");
			 
		   while (!isArriba())
			{
			   i++;
			   //System.out.println("......");
			   if (i==15)
			   {
				 //  System.out.print("///");
				   i=0;
			   }  
			}
		   
		   	  // System.out.println("VOLVER A ENVIAR EL COMANDO");
		   	   //System.out.println("protocolo:"+ protocolo.isEncendido());
			
			  return this.recibirComando(comando,fen , dificultad);
		  
		   // TODO solicitar el numero de minijuego y el fen y enviar el comando a ser ejecutado.
	}


	/**
	    * comprueba si la conversacion esta arriba o si no lo esta.
	    * @return
	    */
	   public Boolean isArriba(){
		  try
		  {
		   return protocolo.isArriba();
		  }
		  catch (Exception e)
		  {
			  return false;
		  }		  		
	   }
	   
	   public void finalize() throws Throwable{
		   try {
			   protocolo.finalize();
		} catch (Exception e) {
			// TODO: handle exception
		}
		   
		  //this.getThreadGroup().destroy();
		   this.interrupt();	
		//   this.finalize();
		  
	   }
	   
	   public void enviarFen(String pFen) throws Exception{		   
		   enviarComando ("setboard " + pFen);	   
	   }
	   
	  
	}
	
	