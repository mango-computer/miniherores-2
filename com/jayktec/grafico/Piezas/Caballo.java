//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Caballo.java                               //
//                   Descripcion                            //
//             Pieza caballo para el juego                   //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez     11/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Piezas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jayktec.grafico.Enums.eColores;
import com.jayktec.grafico.Enums.eEstadoPieza;
import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Screen.ScreenManager;

public class Caballo extends Pieza {

	private eTipoPieza tipoPieza = eTipoPieza.Caballo;
	private String Nombre = "caballo";
	private int anchoTablero;
	private int altoTablero;
	private int inicioTableroX;
	private int inicioTableroY;
	private int finTableroX;
	private Miniheroes game;
	TextureRegion[] textureReg;
	/**
	 * Constructor para la clase encargada de crear y asignar valores a una pieza tipo caballo
	 * asi como a verificar graficamente si el moviento de la pieza es valido o no	
	 * @param pColor Color asignado para la pieza
	 * @param pPosicion posicion de la pieza en el tablero 
	 * @param pInd Indice unico para la pieza
	 * @param pJuego variable que determina el comportamiento de la pieza dependiendo de el juego
	 * @param pScreen indica de que pantalla se esta creando la pieza
	 */

	public Caballo(eColores pColor, String pPosicion, int pInd, int pJuego, ScreenManager pScreen) {

		game = pScreen.getGame();
//		System.out.println(pScreen.getTablero());
		Tablero tablero = pScreen.getTablero();
		textureReg = new TextureRegion[4];
		///System.out.println(tablero.toString());
		this.setInd(pInd);
		
		if (pColor == eColores.Blancas) {
			if(pPosicion.equals("B1"))
				{
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoIzq.png");
				}
			else
				{
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoDer.png");
				}
//			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/Caballo.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/Caballo_selec.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoDer.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoDer.png");
			
			
			this.setColor(Color.WHITE);
		} else {
			if(pPosicion.equals("B8"))
			{
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoIzq.png");
			}
		else
			{
			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoDer.png");
			}
//			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/Caballo.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/Caballo_selec.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoDer.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoDer.png");
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

		super.setjuego(pJuego);

		inicioTableroX = tablero.GetInicioX();
		inicioTableroY = tablero.GetInicioY();
		altoTablero = casilla.GetAltoCasilla();
		anchoTablero = casilla.GetAnchoCasilla();
		finTableroX = tablero.GetFinX();
		this.setEstadoPieza(eEstadoPieza.Esperando);
		this.setColor(pColor);
		pScreen.setCasilla(pPosicion, this, pInd);

	}

/**
 * metodo encargado de validar el movimiento del caballo
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

		// if (Math.abs((posY-actorY)/(posX-actorX))==1.0f)

		if (pPosX == pActorX + (anchoTablero * 2) && Math.abs(pPosY - pActorY) == altoTablero
				|| pPosY == pActorY + (altoTablero * 2) && Math.abs(pPosX - pActorX) == anchoTablero) {
			return true;
		}

		if (pPosX == pActorX - (anchoTablero * 2) && Math.abs(pPosY - pActorY) == altoTablero
				|| pPosY == pActorY - (altoTablero * 2) && Math.abs(pPosX - pActorX) == anchoTablero) {
			return true;
		}
		return false;


	}

	/**
	 * Metodo para validar el movimiento del caballo en el minijuego 1 batalla 1
	 * 
	 * @param pPosX
	 *            valor X del punto donde el usuario hace click transformada
	 *            para que quede en una casilla exacta
	 * @param pPosY
	 *            valor Y del punto donde el usuario hace click transformada
	 *            para que quede en una casilla exacta
	 * @return true si el movimiento es valido, false si es invalido
	 */
	public boolean ValidarMinijuego1(int pPosX, int pPosY) {

		if (pPosX == inicioTableroX + (anchoTablero) && pPosY == inicioTableroY) {
			return true;
		}

		if (pPosX == finTableroX - (anchoTablero) && pPosY == inicioTableroY) {
			return true;

		}

		System.out.println("Error, Movimiento Invalido");
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
		if (tipoPieza.equals("MiniHeroe"))
				{
		

			if (pColor == eColores.Blancas) {
				
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoDer.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/Caballo_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoDer.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoDer.png");
				this.setColor(Color.WHITE);
			} else {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoDer.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/Caballo_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoDer.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoDer.png");
				this.setColor(Color.BLACK);
			}
		}
		else
		{
			if (pColor == eColores.Blancas) {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/Caballo.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/Caballo.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/Caballo.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/Caballo.png");
				// this.setPertenencia(ePertenencia.Blancas);
				this.setColor(Color.WHITE);
			} else {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/CaballoN.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/CaballoN.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/CaballoN.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/CaballoN.png");
				// this.setPertenencia(ePertenencia.Negras);
				this.setColor(Color.BLACK);
			}
		
		}

		this._texture = this.texturaEsperando;
		
	}
}
