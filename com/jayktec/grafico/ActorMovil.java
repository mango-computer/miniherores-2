
//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Protocolo.java                            //
//                   Descripcion                            //
//Clase de actor para que sea movido por la pantalla sirve  //
//para quitar y colocar muros en el tablero con el mouse    //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Vladimir Betancourt  12/07/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.jayktec.grafico.Piezas.Casilla;
import com.jayktec.grafico.Screen.ScreenManager;

public class ActorMovil extends Actor{
	private Texture _texture;
	private int Width = 0 ;
	private int Height = 0;
	private Casilla casilla = new Casilla();
	private ScreenManager screenManager;		
	int wActor = 0;
	int hActor = 0;
	private  boolean construir = false;
//	float width;// juego
//	float height; //
	float w;
	float h;

/**
 * 	
 * @param pTexture: imagen a ser dibujada en el tablero
 * @param _pScreen pantalla desde  la cual es invocado este objeto
 * @param pConstruir:inidica si este objeto esta destinado a colocar o quitar muros
 */
	public ActorMovil(Texture pTexture, ScreenManager _pScreen ,  boolean pConstruir) {
			_texture =  pTexture;
			Width  = casilla.GetAnchoCasilla();
			Height = casilla.GetAltoCasilla();
			this.setWidth(Width);
			this.setHeight(Height);
			screenManager = _pScreen;
			wActor = (int) screenManager.gameWidth;
			hActor = (int) screenManager.gameHeight;
//			 width=screenManager.getGameWidth();// juego
//			height=screenManager.getGameHeight(); //
			 w=Gdx.graphics.getWidth();
			 h=Gdx.graphics.getHeight();
			construir = pConstruir;
			agregarListener();
			
			}
			
	public void draw(Batch batch, float alpha){
		float wf=(w/2)-(wActor/2);
		float hf=(h/2)-(hActor/2);
    
	
      	
      

      	float X=Gdx.input.getX();
		float Y=Gdx.input.getY();
      	
      	if (Gdx.graphics.isFullscreen()==true){
      		
      	 	X=X-((int)wf);
      	 	X=X-(Width/2);
      	 	 Y=Gdx.graphics.getHeight()-Y;
      	 	Y=Y-((int)hf);
      	 	Y=Y-(Height/2);
       }
      	else
      	{
		
			 X =X - Width/2;
			 Y = Gdx.graphics.getHeight() - Y - Height/2;
      	}
			batch.draw(_texture, X, Y,Width,Height);
	 }
		
	public Actor hit(float x, float y, boolean touchable)
    {
			return this;
    }
	 public void agregarListener(){
	     this.addListener(new InputListener()
	     {	    	 
			@Override
	         public boolean touchDown(InputEvent pEvent, float pX, float pY, int pPointer, int pButtons){
				if (construir)
					screenManager.construirMuro(pX, pY);
				else
					screenManager.destruirMuro(pX, pY);				
					
				return true;
	         }						
	     });      	     	 
	 }
}
