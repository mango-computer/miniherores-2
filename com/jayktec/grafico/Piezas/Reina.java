//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   Reina.java                             //
//                   Descripcion                            //
//             Pieza Reina para el juego                     //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      11/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Piezas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jayktec.grafico.Enums.eColores;
import com.jayktec.grafico.Enums.eEstadoPieza;
import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Screen.ScreenManager;

public class Reina extends Pieza {

	private eTipoPieza tipoPieza = eTipoPieza.Reina;
	private String Nombre = "reina";
	private int anchoTablero;
	private int inicioTableroX;
	private int inicioTableroY;
	private Miniheroes game;
	TextureRegion[] textureReg;
	/**

	 * Constructor para la clase encargada de crear y asignar valores a una pieza tipo reina
	 * asi como a verificar graficamente si el moviento de la pieza es valido o no	
	 * @param pColor Color asignado para la pieza
	 * @param pPosicion posicion de la pieza en el tablero 
	 * @param pInd Indice unico para la pieza
	 * @param pJuego variable que determina el comportamiento de la pieza dependiendo de el juego
	 * @param pScreen indica de que pantalla se esta creando la pieza

	 */
	public Reina(eColores pColor, String pPosicion, int pInd, int pJuego, ScreenManager pScreen) {

		game = pScreen.getGame();

		Tablero tablero = pScreen.getTablero();
		textureReg = new TextureRegion[4];
		this.setInd(pInd);

		if (pColor == eColores.Blancas) {
			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama_selec.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama.png");
			// this.setPertenencia(ePertenencia.Blancas);
			this.setColor(Color.WHITE);
		} else {
			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama_selec.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama.png");
			// this.setPertenencia(ePertenencia.Negras);
			this.setColor(Color.BLACK);
		}
		this.setTextureReg(textureReg);
		this.setScreen(pScreen);
		this.setName(Nombre + "_" + pInd);
		this._texture = this.texturaEsperando;
		this.pieza = this;
		this.agregarListener();
		super.setTablero(tablero);
		this.setPosPgn(pPosicion);
		this.setTipoPieza(tipoPieza);

		this.setjuego(pJuego);
		inicioTableroX = tablero.GetInicioX();
		inicioTableroY = tablero.GetInicioY();
		anchoTablero = casilla.GetAnchoCasilla();
		this.setEstadoPieza(eEstadoPieza.Esperando);
		this.setColor(pColor);
		pScreen.setCasilla(pPosicion, this, pInd);
		

	}

	/**

	 * metodo encargado de validar el movimiento de la reina
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

		if (pPosX != pActorX) {
			if (Math.abs((pPosY - pActorY) / (pPosX - pActorX)) == 1.0f) {
				return true;
			}

		}

		if (pPosY > pActorY && pPosX == pActorX) {
			return true;
		}
		if (pPosY < pActorY && pPosX == pActorX) {
			return true;
		}

		if (pPosX > pActorX && pPosY == pActorY) {

			return true;
		}
		if (pPosX < pActorX && pPosY == pActorY) {

			return true;
		}

		System.out.println("Error, movimiento Invalido");
		return false;

	}


/**
 * Metodo para validar el movimiento de la Reina para el minijuego 1 batalla 1 
 * @param pPosX valor X del punto donde el usuario hace click transformada para que quede en una casilla exacta
 * @param pPosY valor Y del punto donde el usuario hace click transformada para que quede en una casilla exacta
 * @return true si el movimiento es valido, false si es invalido
 */
	public boolean ValidarMinijuego1(int pPosX, int pPosY) {

		if (pPosX == inicioTableroX + (anchoTablero * 3) && pPosY == inicioTableroY) {
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

		// System.out.println( "" + Nombre);
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
		if (tipoPieza.equals("MiniHeroe"))
				{
			if (pColor == eColores.Blancas) {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama.png");
				// this.setPertenencia(ePertenencia.Blancas);
				this.setColor(Color.WHITE);
			} else {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama.png");
				// this.setPertenencia(ePertenencia.Negras);
				this.setColor(Color.BLACK);
			}
		}
		else
		{
			if (pColor == eColores.Blancas) {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/Reina.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/Reina.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/Reina.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/Reina.png");
				// this.setPertenencia(ePertenencia.Blancas);
				this.setColor(Color.WHITE);
			} else {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/ReinaN.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/ReinaN.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/ReinaN.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/ReinaN.png");
				// this.setPertenencia(ePertenencia.Negras);
				this.setColor(Color.BLACK);
			}
		}

		this._texture = this.texturaEsperando;
		
	}
}
