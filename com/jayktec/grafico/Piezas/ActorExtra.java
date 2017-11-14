package com.jayktec.grafico.Piezas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.jayktec.grafico.Miniheroes;

//import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Screen.ScreenManager;

public class ActorExtra extends Actor {

	private static Miniheroes game;
	private Texture _texture;
	private TipoDeActor tipo;
	private int ind;
	private int size;
	private float frameDuration;
	private float elapsedTime = 0;
	private TextureAtlas pack;
	private Animation animation;
	private TextureRegion[] textureReg;
	
	
	public int getInd() {
		return ind;
	}

	public void setInd(int ind) {
		this.ind = ind;
	}

	/**
	 * 
	 * @return
	 */
	public TipoDeActor getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo
	 */
	public void setTipo(TipoDeActor tipo) {
		this.tipo = tipo;
	}

	public enum TipoDeActor {
		
		RELOJ(null,""),
		
		ESTACIONAZUL("assets/Texturas/estacionAzul.png",""),
		
		ESTACIONROJA("assets/Texturas/estacionRoja.png",""),
		
		CETRO("assets/Texturas/cetro.png",""),
		
		MACHETE("assets/Texturas/MACHETE.png",""),
		
		LANZA("assets/Texturas/lanza.png",""),
		
		MAGIA("assets/Texturas/Magia.png",""),
		
		FLECHA("assets/Texturas/FLECHA.png",""),
		
		CANON("assets/Texturas/ball.png",""),

		EXPLOSION("assets/Texturas/explosion2.png",""),
		
		PUERTA("assets/Texturas/puertaSalida.png", "E"),

		MONEDA("", "C"),

		PAJA("assets/Texturas/paja.png", "S"),

		BOLSA("assets/Texturas/bolsa.png", ""),

		CUADROPOSIBLES("assets/Texturas/cuadroBlanco.png", "P"),
		
		CUADROPOSIBLESCAPTURAS("assets/Texturas/cuadroRojo.png", "P"),

		CUADROAYUDA("assets/Texturas/cuadroVerde.png", "P"),

		AGUJA("assets/Texturas/aguja.png", ""),

		PARED("assets/Texturas/pared.png", ""),

		CUADROJUGADOS("assets/Texturas/cuadroOscuro.png", "J"),
		
		MURO("assets/Texturas/muro.png", "W"),
		
		MARTILLO("assets/Texturas/aguja.png", "M"),
		
		REYSEB("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png",""),

		REYCAM("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyCamila.png",""),
	
		REYAND("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyAndres.png","");
	
		
		
		
		
		
		

		// Campos tipo constante

		private String textura;

		private final String LetraFen;

		/**
		 * Constructor. Al asignarle uno de los valores posibles a una variable
		 * del tipo enumerado el constructor asigna automáticamente valores de
		 * los campos
		 */

		TipoDeActor(String textura, String LetraFen) {

			// this.textura = new Texture(new FileHandle(new File(textura)));
			this.textura = (textura);
			this.LetraFen = LetraFen;

		} // Cierre del constructor

		// Métodos de la clase tipo Enum

		public String getTextura() {
			return textura;
		}

		public void setTextura(String pTextura) {
			this.textura = pTextura;
		}

		public String getLetraFen() {
			return LetraFen;
		}

	}

	/**
	 * Actores extras dentro del tablero de los minijuegos
	 * 
	 * @param pScreen
	 * @param tipo
	 * @param ind
	 */

	public ActorExtra(ScreenManager pScreen, TipoDeActor tipo, int ind) {

		game = pScreen.getGame();
		if(tipo.getTextura()!=null)
		_texture = game.getManager().get(tipo.getTextura());

		this.tipo = tipo;
		this.setInd(ind);
		//this.screenFrom = pScreen;
//		this.estadoPieza = eEstadoPieza.Esperando;
	
		if( this.tipo  == TipoDeActor.MURO || this.tipo == TipoDeActor.MARTILLO){			
//			agregarListener();
		}

	}

		@Override
	    public Actor hit(float x, float y, boolean touchable)
	
	    {
			return null;
		  }

		@Override
		public void draw(Batch batch, float alpha) 
		
		{
				if(!this.getTipo().equals(TipoDeActor.RELOJ))
					{
						batch.draw(_texture, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(), this.getWidth(),
						this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation(), 0, 0, _texture.getWidth(),
						_texture.getHeight(), false, false);
					}
				else
					{
						size=getTextureReg().length;
						frameDuration=0.20f;
						for(int i=0;i<size;i++)
							{
								textureReg[i] = (pack.findRegion(Integer.toString(i+1)));	
							}
						animation = new Animation(frameDuration,textureReg);
						elapsedTime += Gdx.graphics.getDeltaTime();
						batch.draw((TextureRegion)animation.getKeyFrame(elapsedTime, true), getX(), getY(),this.getWidth(),
								this.getHeight());
					}
		}
	
		
		public TextureAtlas getPack() {
			return pack;
		}

		public void setPack(TextureAtlas pack) {
			this.pack = pack;
		}

		public TextureRegion[] getTextureReg() {
			return textureReg;
		}

		public void setTextureReg(TextureRegion[] textureReg) {
			this.textureReg = textureReg;
		}	
		
		
		
    }
