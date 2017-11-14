//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Rey.java                                 //
//                   Descripcion                            //
//             Pieza Rey para el juego                     //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Vladimir Betancourt  09/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////
package com.jayktec.grafico.Piezas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jayktec.grafico.Enums.eColores;
import com.jayktec.grafico.Enums.eEstadoPieza;
import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Screen.ScreenManager;


public class Rey extends Pieza {
		
private eTipoPieza tipoPieza = eTipoPieza.Rey;
private String Nombre = "rey";
private int anchoTablero;
private int altoTablero;
private int inicioTableroX;
private int inicioTableroY;
private Torre torreReinaNe;
private Torre torreReyNe;
private Torre torreReina;
private Torre torreRey;
private Torre torreEnroque;
private String torreEnroquePgn; 
public boolean enrocado=false;
private boolean movido = false;
TextureRegion[] textureReg;
private TextureAtlas torrePatriotaIzquierda,torrePatriotaDerecha;
private TextureAtlas torreRealistaIzquierda,torreRealistaDerecha;
private String tipopiezas;
public boolean isMovido() {
	return movido;
}

/**
 * verifica si el rey a sido movido anteriormente
 * @param movido true si es movido false lo contrario
 */
public void setMovido(boolean movido) {
	this.movido = movido;
}

/**
 * obtener si el rey fue enrocado
 * @return true si el rey fue enrocado false lo contrario
 */
public boolean isEnrocado() {
	return enrocado;
}

/**
 * seteo de enroque
 * @param enrocado variable que verifica si el rey fue enrocado
 */
public void setEnrocado(boolean enrocado) {
	this.enrocado = enrocado;
}



private Tablero tablero ;
private Miniheroes game;

/**
 * Constructor para la clase encargada de crear y asignar valores a una pieza tipo rey
 * asi como a verificar graficamente si el moviento de la pieza es valido o no	
 * @param pColor Color asignado para la pieza
 * @param pPosicion posicion de la pieza en el tablero 
 * @param pInd Indice unico para la pieza
 * @param pJuego variable que determina el comportamiento de la pieza dependiendo de el juego
 * @param pScreen indica de que pantalla se esta creando la pieza
 */
	public Rey(eColores pColor, String pPosicion,int pInd,int pJuego,ScreenManager pScreen){

		game = pScreen.getGame();
		torrePatriotaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/torre/torreDerecha.pack"));
		torrePatriotaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/torre/torreIzquierda.pack"));
		torreRealistaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/torre/torreDerecha.pack"));
		torreRealistaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/torre/torreIzquierda.pack"));
		tablero =  pScreen.getTablero();
		textureReg = new TextureRegion[4];
		tipopiezas="MiniHeroe";
//CargarParametros cargarParametros = new CargarParametros();
	

this.setInd(pInd);


//	if (pColor == eColores.Blancas){
//		this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasClasicas/Rey.png");	
//		this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/Rey.png");
//		this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasClasicas/Rey.png");
//		this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasClasicas/Rey.png");
//	//	this.setPertenencia(ePertenencia.Blancas);
//		this.setColor(Color.WHITE);
//	}
//	else{
//		this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasClasicas/ReyN.png");	
//		this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/ReyN.png");	
//		this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasClasicas/ReyN.png");	
//		this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasClasicas/ReyN.png");	
//		//this.setPertenencia(ePertenencia.Negras);
//		this.setColor(Color.BLACK);
//	}



	if (pColor == eColores.Blancas){
		this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");	
		this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/Rey_Sebas_selec.png");
		this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");
		this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");
	//	this.setPertenencia(ePertenencia.Blancas);
		this.setColor(Color.WHITE);
	}
	else{
		this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey.png");	
		this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey_selec.png");	
		this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey.png");	
		this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey.png");	
		//this.setPertenencia(ePertenencia.Negras);
		this.setColor(Color.BLACK);
	}
this.setTextureReg(textureReg);
this.setScreen(pScreen);		
this.setName(Nombre + "_" + pInd);
this._texture  = this.texturaEsperando   ;			
this.pieza = this;
this.agregarListener();
super.setTablero(tablero);
this.setPosPgn(pPosicion);
this.setTipoPieza(tipoPieza);
	super.setjuego(pJuego);
	inicioTableroX  = tablero.GetInicioX();
	inicioTableroY =  tablero.GetInicioY();
	altoTablero=casilla.GetAltoCasilla();
	anchoTablero=casilla.GetAnchoCasilla();
	this.setEstadoPieza(eEstadoPieza.Esperando);	
	pScreen.setCasilla(pPosicion, this, pInd);
this.setColor(pColor);

	}

	/**
	 * Metodo para asignar el valor de la torre blanca flanco reina para el Enroque
	 * @param Torre pieza escogida para realizar enroque
*/
	public void setTorreReina(Torre Torre) {
		torreReina = Torre;

	}

	/**
	 * Metodo para asignar el valor de la torre blanca flanco rey para el Enroque
	 * @param Torre pieza escogida para realizar enroque
	 */
	public void setTorreRey(Torre Torre) {
		torreRey = Torre;

	}

	/**
	 *Metodo para asignar el valor de la torre negra flanco rey para el Enroque
	 * @param Torre pieza escogida para realizar enroque
	 */
	public void setTorreReinaNe(Torre Torre) {
		torreReinaNe = Torre;

	}

	/**
	 * Metodo para asignar el valor de la torre negra flanco reina para el Enroque
	 * @param Torre pieza escogida para realizar enroque

	 */
	public void setTorreReyNe(Torre Torre) {
		torreReyNe = Torre;

	}

	/**
	 * metodo encargado de validar el movimiento del rey
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
		// System.out.println("FIN Y"+finTableroY);


		if (Math.abs(pPosX - pActorX) == anchoTablero && pPosY - pActorY == 0) {
			movido = true;
			return true;
		}

		if (Math.abs(pPosY - pActorY) == altoTablero && pPosX - pActorX == 0) {
			movido = true;
			return true;
		}

		if (pPosX != pActorX) {
			if (Math.abs((pPosY - pActorY) / (pPosX - pActorX)) == 1.0f && Math.abs(pPosX - pActorX) == anchoTablero
					&& Math.abs(pPosY - pActorY) == altoTablero) {
				movido = true;
				return true;
			}
			String vPgnI = tablero.XY2pgn(pActorX, pActorY);
			String vPgnf = tablero.XY2pgn(pPosX, pPosY);

			if (this.getColor().equals(Color.WHITE)) {
				if (vPgnI.contains("E1") && vPgnf.contains("C1")) {
					// if(pPosX==inicioTableroX+(anchoTablero*2) &&
					// pPosY==pActorY){
					// if (!torreReina.getMovida()){
					// torreReina.setMovida(true);
					// movido = true;
					moverTorreiz();
					movido = true;
					enrocado = true;
					return true;
					// }
				}
				if (vPgnI.contains("E1") && vPgnf.contains("G1")) {
					// if(pPosX==finTableroX-anchoTablero && pPosY==pActorY){
					// if (!torreRey.getMovida()){
					// torreRey.setMovida(true);
					// movido = true;
					moverTorreder();
					movido = true;
					enrocado = true;
					return true;
					// }
				}
			} else {
				if (vPgnI.contains("E8") && vPgnf.contains("C8")) {
					// if(pPosX==inicioTableroX+(anchoTablero*2) &&
					// pPosY==pActorY){
					// if (!torreReinaNe.getMovida()){
					// torreReinaNe.setMovida(true);
					// movido = true;
					moverTorreizNe();
					movido = true;
					enrocado = true;
					return true;
					// }
				}
				if (vPgnI.contains("E8") && vPgnf.contains("G8")) {
					// if(pPosX==finTableroX-anchoTablero && pPosY==pActorY){
					// if (!torreReyNe.getMovida()){
					// torreReyNe.setMovida(true);
					// movido = true;
					moverTorrederNe();
					enrocado = true;
					movido = true;
					return true;
					// }
				}
			}

		}

		System.out.println("Error, movimiento Invalido");
		return false;
	}


/**
 * Funcion que valida el movimiento del rey en el minijuego numero 1 de la batalla 1
 * @param pPosX valor X del punto donde el usuario hace click transformada para que quede en una casilla exacta
 * @param pPosY valor Y del punto donde el usuario hace click transformada para que quede en una casilla exacta
 * @return true si el movimiento es valido, false si es invalido
 */
public boolean ValidarMinijuego1 (int pPosX,int pPosY){

	
	if(pPosX==inicioTableroX+(anchoTablero*4) && pPosY==inicioTableroY){
		return true;
	} 

 	 System.out.println("Error, movimiento Invalido"); 
 	 return false;

}

@Override
public String toString(){
	   return Nombre;
}	

/**
 * Movimiento de Enroque largo negras
 */
public void moverTorreizNe(){
	int[] vPosf= tablero.Pgn2XY("D8");
	torreReinaNe.move(vPosf[0], vPosf[1]);
	if (tipopiezas.equals("MiniHeroe"))
	{
	torreReinaNe.set_texture(null);
	torreReinaNe.setPack(torreRealistaDerecha);
	}
	torreEnroque = torreReinaNe;
	torreEnroquePgn="D8";
}

/**
 * Movimiento de Enroque corto negras
 */
public void moverTorrederNe(){	
	int[] vPosf= tablero.Pgn2XY("F8");
	torreReyNe.move(vPosf[0], vPosf[1]);
	if (tipopiezas.equals("MiniHeroe"))
	{
	torreReyNe.set_texture(null);
	torreReyNe.setPack(torreRealistaIzquierda);
	}
	torreEnroque = torreReyNe;
	torreEnroquePgn="F8";
}


/**
 * Movimiento de Enroque largo blancas
 */

public void moverTorreiz(){
	int[] vPosf= tablero.Pgn2XY("D1");
	torreReina.move(vPosf[0], vPosf[1]);
	if (tipopiezas.equals("MiniHeroe"))
	{
	torreReina.set_texture(null);
	torreReina.setPack(torrePatriotaDerecha);
	}
	torreEnroque = torreReina;
	torreEnroquePgn="D1";	
	
}
/**
 * Movimiento de Enroque corto blancas
 */
public void moverTorreder(){
	int[] vPosf= tablero.Pgn2XY("F1");
	torreRey.move(vPosf[0], vPosf[1]);
	if (tipopiezas.equals("MiniHeroe"))
	{
	torreRey.set_texture(null);
	torreRey.setPack(torrePatriotaIzquierda);
	}
	torreEnroque = torreRey;
	torreEnroquePgn="F1";

}



public Object[] getTorreMovida(){
	
Object[] ret =  new Object[2];
ret[0] = torreEnroque;
ret[1] = torreEnroquePgn;
	return ret;	
}

public boolean getMovido(){


		return movido;
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
	
	tipopiezas=tipoPieza;
	if (tipoPieza.equals("MiniHeroe"))
			{
//		System.out.println("rey miniheroe");
		
		if (pColor == eColores.Blancas){
			this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");	
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/Rey_Sebas_selec.png");
			this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");
			this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");
		//	this.setPertenencia(ePertenencia.Blancas);
			this.setColor(Color.WHITE);
		}
		else{
			this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey.png");	
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey_selec.png");	
			this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey.png");	
			this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey.png");	
			//this.setPertenencia(ePertenencia.Negras);
			this.setColor(Color.BLACK);
		}
	}
	else
	{
		if (pColor == eColores.Blancas) {
			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/Rey.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/Rey.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/Rey.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/Rey.png");
			// this.setPertenencia(ePertenencia.Blancas);
			this.setColor(Color.WHITE);
		} else {
			this.texturaEsperando = game.getManager().get("assets/Texturas/PiezasClasicas/ReyN.png");
			this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasClasicas/ReyN.png");
			this.texturaMoviendo = game.getManager().get("assets/Texturas/PiezasClasicas/ReyN.png");
			this.texturaCapturando = game.getManager().get("assets/Texturas/PiezasClasicas/ReyN.png");
			// this.setPertenencia(ePertenencia.Negras);
			this.setColor(Color.BLACK);
		}

		
	}
	this._texture = this.texturaEsperando;
	
}

public void setAvatar(String pAvatar){
	if(pAvatar.equals("Sebastian"))
	{
		this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");	
		this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/Rey_Sebas_selec.png");
		this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");
		this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png");
	}
	else
	if (pAvatar.equals("Camila")) 
	{
		this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyCamila.png");	
		this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/Rey_Camila_selec.png");
		this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyCamila.png");
		this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyCamila.png");
	}
	else
	{
		this.texturaEsperando   = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyAndres.png");	
		this.texturaSeleccionado = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/Rey_Andres_selec.png");
		this.texturaMoviendo  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyAndres.png");
		this.texturaCapturando  = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyAndres.png");
	}
}


}