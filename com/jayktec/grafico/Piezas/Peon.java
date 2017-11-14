///////////////////////////////////////////////////                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                  Peon.java                                 //
//                   Descripcion                            //
//             Pieza peon para el juego                     //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Vladimir Betancourt  09/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////
package com.jayktec.grafico.Piezas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jayktec.grafico.Enums.eColores;
import com.jayktec.grafico.Enums.eEstadoPieza;
import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Screen.ScreenManager;

//import com.jayktec.miniheroes.ScreenJuegoPrincipal;
public class Peon extends Pieza {

	private eTipoPieza tipoPieza = eTipoPieza.Peon;
	private String Nombre = "peon";
	private int anchoTablero;
	private int altoTablero;
	private int inicioTableroX;
	private int inicioTableroY;
	private int finTableroY;
	private int finTableroX;
	private Miniheroes game;
	private Tablero tablero;
	TextureRegion[] textureReg;

	/**
  	 * Constructor para la clase encargada de crear y asignar valores a una pieza tipo muro
	 * asi como a verificar graficamente si el moviento de la pieza es valido o no	
	 * @param pColor Color asignado para la pieza
	 * @param pPosicion posicion de la pieza en el tablero 
	 * @param pInd Indice unico para la pieza
	 * @param pJuego variable que determina el comportamiento de la pieza dependiendo de el juego
	 * @param pScreen indica de que pantalla se esta creando la pieza
 */
	public Peon(eColores pColor, String pPosicion, int pInd, int pJuego, ScreenManager pScreen) {

		game = pScreen.getGame();

		tablero = pScreen.getTablero();
		textureReg = new TextureRegion[4];
		this.setInd(pInd);

			
			
			if (pColor == eColores.Blancas) {
//				this.texturaEsperando = game.getManager().get("assets/Texturas/muro.png");
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon_mov.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon.png");
				// this.setPertenencia(ePertenencia.Blancas);
				this.setColor(Color.WHITE);
			} else {
				this.texturaEsperando = game.getManager().get(   "assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon.png");
				// this.setPertenencia(ePertenencia.Negras);
				this.setColor(Color.BLACK);
			}
		this.setTextureReg(textureReg);
		this.setColor(pColor);
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

		pScreen.setCasilla(pPosicion, this, pInd);

	}

	/**
	 * metodo encargado de validar el movimiento del muro
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
	
	

	@Override
	/**
	 * metodo para ser sobrescrito para cambiar las texturas por los tipos de piezas existentes
	 * @param color , oscuro o claro,  valor de la pieza
	 * @param tipoPieza , tipo de pieza a cambiar, por ejemplo miniheroes, ó clasica
	 * @author yisheng 
	 */
	public void setTipodePiezas(eColores pColor, String tipoPieza)
	{
		//System.out.println("peon");
		
		if (tipoPieza.equals("MiniHeroe"))
				{
		
			
			if (pColor == eColores.Blancas) {
//				this.texturaEsperando = game.getManager().get("assets/Texturas/muro.png");
//				System.out.println("blanco");
				
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon_mov.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon.png");
				// this.setPertenencia(ePertenencia.Blancas);
				this.setColor(Color.WHITE);
			} else {
//				System.out.println("negro");
				
				this.texturaEsperando = game.getManager().get(   "assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon.png");
				// this.setPertenencia(ePertenencia.Negras);
				this.setColor(Color.BLACK);
			}
		}
		else
		{
		//	System.out.println("clasico");
			
			if (pColor == eColores.Blancas) {
//				System.out.println("blanco");
				
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/Peon.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/Peon.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/Peon.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/Peon.png");
				// this.setPertenencia(ePertenencia.Blancas);
				this.setColor(Color.WHITE);
			} else {
				
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/PeonN.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/PeonN.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/PeonN.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/PeonN.png");
				// this.setPertenencia(ePertenencia.Negras);
				this.setColor(Color.BLACK);
			}
		}
		this._texture = this.texturaEsperando;
		
	//	System.out.println("=============");
		
	}

	
	
	

}