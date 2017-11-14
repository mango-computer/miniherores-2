package com.jayktec.grafico;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jayktec.grafico.Enums.eTipoPersonaje;

public class Heroe_Dificultad extends Actor {
	private Texture texture;
	private Miniheroes juego;
	private TextureRegion[] textureReg = new TextureRegion[4];
	private TextureAtlas pack;
	private Animation animation;
	private int size;
	private float frameDuration;
	private float elapsedTime = 0;
	
	/**
	 * Constructor para crear
	 * 
	 * @param game
	 *            Juego Miniheroes
	 * @param dimension
	 *            tamaño de la texturas 1 pequeño 2 mediano 3 grande cualquier
	 *            otro pequeño
	 * @author yisheng
	 */
	public Heroe_Dificultad(Miniheroes game) {
		juego = game;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		if(texture!=null){
	        batch.draw(texture, getX(), getY(),getWidth(),getHeight());
	        }
	    	else {
	    		{
	    			
	    			size=textureReg.length;
	    	     	frameDuration=0.12f;
	    	     	for(int i=0;i<size;i++)
	    	     	{
	    	     		textureReg[i] = (pack.findRegion(Integer.toString(i+1)));
	    	     		
	    	     	}
	    	        
	    		      animation = new Animation(frameDuration,textureReg);
	    			
	    			 elapsedTime += Gdx.graphics.getDeltaTime();
	    			 batch.draw((TextureRegion)animation.getKeyFrame(elapsedTime, true), getX(), getY(),getWidth(),getHeight());
	    		}
			}
		
		
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	
	
	
	
	
	
	/**
	 * cambiar el tamaño del avatar
	 * 
	 * @param ancho
	 *            , medida para el ancho
	 * @param alto
	 *            m medida para el alto
	 * @author yisheng
	 */
//	public void redimensionar(float ancho, float alto) {
//		if (batch.equals(null)) {
//		}
//		batch.draw(texture, this.getX(), getY(), ancho, alto);
//	}

	public TextureAtlas getPack() {
		return pack;
	}

	public void setPack(TextureAtlas pack) {
		this.pack = pack;
	}

	/**
	 * Cambia el avatar según el personaje deseado
	 * 
	 * @param personaje 0 = personaejs, 1 = dificultades
	 *            nombre del avatar a mostrar
	 */
	public void personaje(String personaje, eTipoPersonaje pPersonaje) {
		if (pPersonaje == eTipoPersonaje.Personaje) {
			if (personaje.equals("Camila")) {
				texture = juego.getManager().get("assets/Texturas/camila.png");
			} else if (personaje.equals("Alejandro")) {
				texture = juego.getManager().get("assets/Texturas/alejandro.png");

			} else if (personaje.equals("Sebastian")) {
				texture = juego.getManager().get("assets/Texturas/sebastian.png");

			}else if (personaje.equals("barco")) {
				texture = juego.getManager().get("assets/Texturas/barco.png");

			}else {
				texture = juego.getManager().get("assets/Texturas/heroeMasculino.png");
			}
		} else if (pPersonaje == eTipoPersonaje.Dificultad) {
				if (personaje.equals("Soldado")) {
//					System.out.println("soldado");
				texture = juego.getManager().get("assets/Texturas/dificultades/1soldado.png");

			} else if (personaje.equals("Teniente")) {
//				System.out.println("Teniente");
				texture = juego.getManager().get("assets/Texturas/dificultades/2teniente.png");

			} else if (personaje.equals("Capitan")) {
//				System.out.println("capitan");
				texture =  juego.getManager().get("assets/Texturas/dificultades/3capitan.png");

			}else if (personaje.equals("Mayor")) {
//				System.out.println("mayor");
				texture =  juego.getManager().get("assets/Texturas/dificultades/4mayor.png");
			}  
			else if (personaje.equals("Coronel")) {
//				System.out.println("coronel");
				texture =  juego.getManager().get("assets/Texturas/dificultades/5coronel.png");

			} else if (personaje.equals("General")) {
//				System.out.println("general");
				texture =  juego.getManager().get("assets/Texturas/dificultades/6general.png");

			} else {
				texture = juego.getManager().get("assets/Texturas/dificultades/1soldado.png");
			}
		}else if (pPersonaje == eTipoPersonaje.PiezaPromocion){
			if (personaje.toLowerCase().equals("reina")) {
				texture = new Texture("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama.png");	
			}
			else if (personaje.toLowerCase().equals("torre")) {
				texture = new Texture("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre.png");
			}
			else if (personaje.toLowerCase().equals("alfil")) {
				texture = new Texture("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/Alfil.png");		
			}		
			else if (personaje.toLowerCase().equals("caballo")) {
				texture = new Texture("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoDer.png");
			}
		}
	
		this.setName(personaje);
	}	
		
}