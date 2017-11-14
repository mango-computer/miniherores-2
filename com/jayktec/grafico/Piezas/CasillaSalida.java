package com.jayktec.grafico.Piezas;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jayktec.grafico.Miniheroes;

//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.Texture; 

	public class CasillaSalida extends Actor {
		//private static final Logger LOG = Logger.getLogger(Miniheroes.class.getName() );
//		private int juego;
		public Texture texture;

		private Casilla casilla = new Casilla();
	   
		
	    /**
	     * Constructor por defecto de esta clase pieza el cual se encarga de dibujar en pantalla las distintas
	     * piezas asi como verificar si el mouse esta haciendo click sobre una pieza
	     **/
	    
		public CasillaSalida(Miniheroes pGame, int pX, int pY) {
//			texture =  new Texture( Gdx.files.internal("desktop/assets/Texturas/black.jpg"));			
		}

	 /**
	  * Metodo para dibujar este elemento en la pantalla
	  */
	    public void draw(Batch batch, float alpha){
	        batch.draw(texture, getX(), getY(),casilla.GetAnchoCasilla(),casilla.GetAltoCasilla());
	    }
	    
	    /**
	     * Metodo para manejar la entrada del mouse en esta Pieza 
	     **/
	    public Actor hit(float x, float y, boolean touchable)
	    { 	    
	            return null;
	      }
}