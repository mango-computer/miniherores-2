package com.jayktec.grafico.Screen;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Animator;


public class AnimacionPrueba extends ScreenManager {
		Stage stage;
	    Miniheroes game;
	    TextureAtlas pack;
	    Animator  actor;

	public AnimacionPrueba(Miniheroes pGame) {
		super(pGame);
		game=pGame;
	}

	@Override
	public void show() {
		 
	        pack= new TextureAtlas(Gdx.files.internal("assets/skins/animacion.pack"));
	        stage=new Stage();
	        TextureRegion[] textureReg = new TextureRegion[4];
	        
	       
 // 				    TextureRegion[]   TextureAtlas  pAncho   pAlto    pFrameDuration  
	    actor=new Animator(textureReg,        pack ,      150,    150,       0.15f);
	        stage.addActor(actor);
	        Gdx.graphics.setContinuousRendering(true);
	        Gdx.input.setInputProcessor(stage);
	}

	
	@Override
	public void render(float delta) {

		 Gdx.gl.glClearColor(0, 0, 0, 0);
	     Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        stage.act();
	        stage.draw();
	        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
	        float x = Gdx.input.getX();
	        float y = Gdx.input.getY();
	        y=super.getGameHeight()-y;
//	        System.out.println("moviendo");
	        actor.touched=true;
	    	actor.addAction(Actions.moveTo(x, y,1f));
	    	Timer.schedule(new Task()
				{
					@Override
					public void run()
						{ actor.touched=false;
							
						}
				},1f);	
	        }
	  
	}
	
	@Override
	public void hide() {
	
		
	}

}
