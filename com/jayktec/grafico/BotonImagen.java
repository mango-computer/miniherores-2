package com.jayktec.grafico;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class BotonImagen extends ImageButton {

	 private Sound sOpcion1=null;
	public BotonImagen(Texture texture_up, Texture texture_down, Texture texture_over, Texture background) {
						
		super(new ImageButtonStyle(
				new SpriteDrawable(new Sprite(texture_up)),
				new SpriteDrawable(new Sprite(background)), 
				new SpriteDrawable(new Sprite(background)),
				new SpriteDrawable(new Sprite(background)), 
				new SpriteDrawable(new Sprite(texture_down)),
				new SpriteDrawable(new Sprite(texture_over))));
		
		this.getStyle().over = new SpriteDrawable(new Sprite(texture_over));	
		 this.setBackground(new SpriteDrawable(new Sprite(background)));
	}

	public void AddFocusListener(final FileHandle pFileHandle){
		sOpcion1 =  Gdx.audio.newSound(pFileHandle);
	this.addListener(new FocusListener() {
		@Override
		public boolean handle (Event event) {
			try{
			//System.out.println("event: " + event);
				
				if (event.toString() == "enter"){										
					sOpcion1.play(1f);				
				}
				if (event.toString().contains("exit")){
					//System.out.println(" evento:" + event.toString());
					sOpcion1.stop();				
				} 
				
				
			}
			catch(Exception e){
				System.out.println("error en el sOpcion1 :" + e.getMessage());						
			}
			return false;
		}});

	}
	
	public void stop() {
		try {
			sOpcion1.stop();
		} catch (Exception e) {

		}
		
	}
}
