//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   Torre.java                             //
//                   Descripcion                            //
//             Pieza Torre para el juego                     //
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

public class Torre extends Pieza {

	private eTipoPieza tipoPieza = eTipoPieza.Torre;
	private String Nombre = "torre";
	private int inicioTableroX;
	private int inicioTableroY;
	private int finTableroX;
	private Miniheroes game;
	private boolean movida;
	TextureRegion[] textureReg;
	
/**
 	 * Constructor para la clase encargada de crear y asignar valores a una pieza tipo torre
	 * asi como a verificar graficamente si el moviento de la pieza es valido o no	
	 * @param pColor Color asignado para la pieza
	 * @param pPosicion posicion de la pieza en el tablero 
	 * @param pInd Indice unico para la pieza
	 * @param pJuego variable que determina el comportamiento de la pieza dependiendo de el juego
	 * @param pScreen indica de que pantalla se esta creando la pieza
 */

	public Torre(eColores pColor, String pPosicion, int pInd, int pJuego, ScreenManager pScreen) {

		game = pScreen.getGame();
		Tablero tablero = pScreen.getTablero();
		 textureReg = new TextureRegion[4];
		this.setInd(pInd);
		if (pColor == eColores.Blancas) {
			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre_selec.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre.png");
			// this.setPertenencia(ePertenencia.Blancas);
			this.setColor(Color.WHITE);
		} else {
			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre_selec.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre.png");
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
		finTableroX = tablero.GetFinX();
		this.setEstadoPieza(eEstadoPieza.Esperando);
		pScreen.setCasilla(pPosicion, this, pInd);
		this.setColor(pColor);
	}

	
/**
 * metodo encargado de validar el movimiento de la torre
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
public boolean Validar (int pMouseX,int pMouseY,int pActorX,int pActorY,int pRangoX,int pRangoY,int pPosX,int pPosY){	 	 
	 if(pPosY>pActorY && pPosX==pActorX )
     {
    	 return true;
     }
	 if(pPosY<pActorY && pPosX==pActorX )
     {
    	 return true;
     }
	 
	 
     if( pPosX>pActorX && pPosY==pActorY)
     {
        	
    	 return true; 
     }
     if( pPosX<pActorX && pPosY==pActorY)
     {
        	
    	 return true; 
     }
    	 System.out.println("Error, movimiento Invalido"); 
    	 return false;	 
 }
/**
 * Funcion que valida el movimiento de las torres en el minijuego numero 1 de la batalla 1
 * @param pPosX valor X del punto donde el usuario hace click transformada para que quede en una casilla exacta
 * @param pPosY valor Y del punto donde el usuario hace click transformada para que quede en una casilla exacta
 * @return true si el movimiento es valido, false si es invalido
 */

	public boolean ValidarMinijuego1(int pPosX, int pPosY) {
		if (pPosX == inicioTableroX && pPosY == inicioTableroY) {
			return true;
		}

		if (pPosX == finTableroX && pPosY == inicioTableroY) {
			return true;
		}

		System.out.println("Error, movimiento Invalido");
		return false;

	}

	/**
	 * Obtener el nombre de a pieza en String
	 */

	@Override
	public String toString() {

		// System.out.println( "" + Nombre);
		return Nombre;
	}

	public boolean getMovida() {
		return movida;

	}

	public void setMovida(boolean pMovida) {
		movida = pMovida;
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
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre.png");
				// this.setPertenencia(ePertenencia.Blancas);
				this.setColor(Color.WHITE);
			} else {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre_selec.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre.png");
				// this.setPertenencia(ePertenencia.Negras);
				this.setColor(Color.BLACK);
			}
			}
		else
		{
			if (pColor == eColores.Blancas) {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/Torre.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/Torre.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/Torre.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/Torre.png");
				// this.setPertenencia(ePertenencia.Blancas);
				this.setColor(Color.WHITE);
			} else {
				this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/TorreN.png");
				this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/TorreN.png");
				this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/TorreN.png");
				this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/TorreN.png");
				// this.setPertenencia(ePertenencia.Negras);
				this.setColor(Color.BLACK);
			}
		}
		

		this._texture = this.texturaEsperando;
	}
	
}
