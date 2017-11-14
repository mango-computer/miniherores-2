package com.jayktec.grafico.Piezas;


import com.badlogic.gdx.graphics.Color;
import com.jayktec.grafico.Enums.eColores;
import com.jayktec.grafico.Enums.eEstadoPieza;
import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Screen.ScreenManager;

public class Muro extends Pieza{

	private eTipoPieza tipoPieza = eTipoPieza.Muro;
	private String Nombre = "Muro";
	private int anchoTablero;
	private int altoTablero;
	private int inicioTableroX;
	private int inicioTableroY;
	private int finTableroY;
	private int finTableroX;
	private Miniheroes game;
	private Tablero tablero;
	
	/**
  	 * Constructor para la clase encargada de crear y asignar valores a una pieza tipo peon
	 * asi como a verificar graficamente si el moviento de la pieza es valido o no	
	 * @param pColor Color asignado para la pieza
	 * @param pPosicion posicion de la pieza en el tablero 
	 * @param pInd Indice unico para la pieza
	 * @param pJuego variable que determina el comportamiento de la pieza dependiendo de el juego
	 * @param pScreen indica de que pantalla se esta creando la pieza
 */
	
	public Muro(eColores pColor, String pPosicion, int pInd, int pJuego, ScreenManager pScreen) {

		game = pScreen.getGame();

		tablero = pScreen.getTablero();
		this.setInd(pInd);
	
		if (pColor == eColores.Blancas) {
           this.texturaEsperando = game.getManager().get("assets/Texturas/muro.png");
			//this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/muro.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/muro.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/muro.png");
			// this.setPertenencia(ePertenencia.Blancas);
			this.setColor(Color.WHITE);
			
		}else 
		if(pColor==eColores.Moneda)
			{
			this.texturaEsperando = game.getManager().get("assets/Texturas/bolsa.png");
			this.texturaEsperando = game.getManager().get("assets/Texturas/bolsa.png");
			this.texturaEsperando = game.getManager().get("assets/Texturas/bolsa.png");
			this.texturaEsperando = game.getManager().get("assets/Texturas/bolsa.png");
			this.setColor(Color.BLACK);
			}
		else {
			this.texturaEsperando = game.getManager().get("assets/Texturas/muro.png");
		//	this.texturaEsperando = game.getManager().get(   "assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/muro.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/muro.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/muro.png");
			// this.setPertenencia(ePertenencia.Negras);
			this.setColor(Color.BLACK);
		}
		this.setScreen(pScreen);
		this.setName(Nombre + "_" + pInd);
		this._texture = this.texturaEsperando;
		this.setjuego(pJuego);
		this.pieza = this;
		this.agregarListener();
		super.setTablero(tablero);
		this.setPosPgn(pPosicion);
		this.setTipoPieza(tipoPieza);
		inicioTableroX = tablero.GetInicioX();
		inicioTableroY = tablero.GetInicioY();
		altoTablero = casilla.GetAltoCasilla();
		anchoTablero = casilla.GetAnchoCasilla();
		finTableroY = tablero.GetFinY();
		finTableroX = tablero.GetFinX();
		this.setEstadoPieza(eEstadoPieza.Esperando);
this.setColor(pColor);
		pScreen.setCasilla(pPosicion, this, pInd);

	}

	/**
	 * metodo encargado de validar el movimiento del peon
	 * @param pMouseX valor X del punto donde el usuario hace click
	 * @param pMouseY valor Y del punto donde el usuario hace click
	 * @param pActorX posicion X actual del actor
	 * @param pActorY posicion Y actual del actor
	 * @param pRangoX suma entre la posicion del actor X y su ancho
	 * @param pRangoY suma entre la posicion del actor Y y su alto
	 * @param pPosX valor X del punto donde el usuario hace click transformada para que quede en una casilla exacta
	 * @param pPosY valor Y del punto donde el usuario hace click transformada para que quede en una casilla exacta
	 * @return true si el movimiento es valido, false si es invalido
	 */
	public boolean Validar(int pMouseX, int pMouseY, int pActorX, int pActorY, int pRangoX, int pRangoY, int pPosX,
			int pPosY) {
//		System.out.println("pPosY: " + pPosY + " pActorY: " + pActorY + " altotablero: " + altoTablero + " pPosX"
//				+ pPosX + " pActorX" + pActorX);
//		System.out.println("color: " + this.getColor() + " tablero: " + tablero.getTableroRotado());
		if (this.getColor().equals(Color.WHITE) && !tablero.getTableroRotado()
				|| this.getColor().equals(Color.BLACK) && tablero.getTableroRotado()) {
			if (pPosY == pActorY + altoTablero && pPosX == pActorX) {
				return true;
			}

			if (pPosY == pActorY + (altoTablero * 2) && pActorY == inicioTableroY + altoTablero && pPosX == pActorX) {
				return true;
			}

			if (pPosY == pActorY + (altoTablero) && Math.abs(pPosX - pActorX) == anchoTablero) {
				return true;
			}
			return false;
		}

		if (this.getColor().equals(Color.BLACK) && !tablero.getTableroRotado()
				|| this.getColor().equals(Color.WHITE) && tablero.getTableroRotado()) {
			if (pPosY == pActorY - altoTablero && pPosX == pActorX) {
				return true;
			}

			if (pPosY == pActorY - (altoTablero * 2) && pActorY == finTableroY - (altoTablero) && pPosX == pActorX) {
				return true;
			}

			if (pPosY == pActorY - (altoTablero) && Math.abs(pPosX - pActorX) == anchoTablero) {
				return true;
			}
			return false;
		}

		return false;
	}

	/**
	 * Metodo para validar el movimiento del peon en el minijuego 1 batalla 1
	 * @param pPosX valor X del punto donde el usuario hace click transformada para que quede en una casilla exacta
	 * @param pPosY valor Y del punto donde el usuario hace click transformada para que quede en una casilla exacta
	 * @return true si el movimiento es valido, false si es invalido
	 */
	public boolean ValidarMinijuego1(int pPosX, int pPosY) {

		if (pPosX == inicioTableroX && pPosY == inicioTableroY + altoTablero) {
			return true;
		}

		if (pPosX == inicioTableroX + (anchoTablero) && pPosY == inicioTableroY + altoTablero) {
			return true;
		}

		if (pPosX == inicioTableroX + (anchoTablero * 2) && pPosY == inicioTableroY + altoTablero) {
			return true;
		}

		if (pPosX == inicioTableroX + (anchoTablero * 3) && pPosY == inicioTableroY + altoTablero) {
			return true;
		}

		if (pPosX == finTableroX - (anchoTablero * 3) && pPosY == inicioTableroY + altoTablero) {
			return true;
		}

		if (pPosX == finTableroX - (anchoTablero * 2) && pPosY == inicioTableroY + altoTablero) {
			return true;
		}

		if (pPosX == finTableroX - (anchoTablero) && pPosY == inicioTableroY + altoTablero) {
			return true;
		}

		if (pPosX == finTableroX && pPosY == inicioTableroY + altoTablero) {
			return true;
		}

		System.out.println("Error, movimiento Invalido");
		return false;

	}

	/**
	 * Metodo para obtener el nombre de la pieza
	 */

	@Override
	public String toString() {
		return Nombre;
	}



}
