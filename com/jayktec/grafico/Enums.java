//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Alfil.java                                 //
//                   Descripcion                            //
//             Pieza alfil para el juego                     //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Vladimir Betancourt  14/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////


package com.jayktec.grafico;

//import com.jayktec.grafico.Piezas.Pieza;

public class Enums {
/**
 * Enumeración de piezas
 * @author jayktec
 *
 */
	public static final int victoria = 0;
	public static final int derrota = 1;
	public static final int tablas = 2;
	public static final int indefindo = 3;

	public enum eTipoPieza {
		Torre, Alfil, Caballo, Reina, Rey, Peon, Muro, Salida;
		}
	/**
	 * Enumeración de colores
	 * @author jayktec
	 *
	 */
		public enum eColores{
		Blancas, Negras, Moneda
		}
		
	/**
	 * Enumeración de colores
	 * @author jayktec
	 *
	 */
		public enum eContadores{
		ContadorJugadas,ContadorMovIncorrectos,ContadorPromovidas,ContadorJugadasPeon,ContadorDobleAtaque,
		ContadorPeonAlPaso,ContadorPaja,ContadorPiezasCapturadas,ContadorBolsasCapturadas,ContadorEnrroqueRealizados
		,ContadorJugadasSecretasSeguidas,ContadorJugadasSecretasNoSeguidas,Correctas,Incorrectas
		}
		/**
		 * Enumeración de estados de piezas
		 * @author jayktec
		 *
		 */
		public enum eEstadoPieza{
		Esperando, Seleccionado, Moviendo, FinMovimiento , Capturando	
		}
		/**
		 * Enumeración de estados del jugador
		 * @author jayktec
		 *
		 */
		public enum eEstadoJugador{
		Jugando, Esperando
		}
		/**
		 * Enumeración de pertenencias
		 * @author jayktec
		 *
		 */
		public enum ePertenencia{
		Blancas, Negras
		}
		/**
		 * Enumeración de turnos
		 * @author jayktec
		 *
		 */
		public enum eTurno{
		Blancas, Negras
		}
/**
 * Enumeración de los tipos de Dialogos en la aplicacion
 */
		public enum eTipoDialogo{
			Opciones,Tablas, Mensaje, SiNoCancelar,  Preferencias, Entrada, OpcionesImagenes, Aceptar, Video, Promocion, AbrirArchivo, Opciones25 
		}
		public enum eTipoMensaje{
			MovimientoNoPermitido, MovimientoIlegal,GanaBlancas,GanaNegras,JuegoNoAceptaTablas,JuegoAceptaTablas, JuegoFinalizadoDerrota, ReyenJaque, NoEliminarMuro,JuegoFinalizadoVictoria, EnroqueVictoriaParcial, EnroqueDerrotaParcial, MensajeSecretoVictoriaParcial, MensajeSecretoDerrotaParcial, Capturar5Bolsas, FENNoAdmitido, JuegoFinalizadoTablas 
		}
		public enum eRespuestaDialgo{
			Si,No, Aceptar
		}

		public enum eTipoAyuda{
			Muro, Martillo
		}
		public enum eTipoPersonaje{
			Personaje, Dificultad, PiezaPromocion
		}
		public enum eTipoTrivia{
			General, Ajedrez, Piezas
		}
		public enum eTipoVideo{
			Ayuda, Biografia, Victoria, Derrota, Historia, VideoInfo 
		}
}
