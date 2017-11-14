package com.jayktec.grafico.Screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.jayktec.grafico.Miniheroes;

public class mapaPrueba extends ScreenManager {

	  private Stage stage;
		  RotateToAction rotateAction=new RotateToAction();
	
	public mapaPrueba(Miniheroes game) {
		super(game);
		
	}

	    @Override
	    public void show() {        
	        stage = new Stage();
	        Gdx.input.setInputProcessor(stage);
	        
//	      // final ActorExtra reloj = new ActorExtra(this,"reloj");
//	       reloj.setSize(200, 200);
//	       reloj.setPosition(300, 300);
//	       reloj.setOrigin(reloj.getWidth()/2,0);
//	       rotateAction=new RotateToAction();
//	       rotateAction.setRotation(360f);
//	       rotateAction.setDuration(segundos);
//	       rotateAction.setReverse(true);
//	    	
//           reloj.addAction(rotateAction);
//          
//	        stage.addActor(reloj);
	    }

	    @Override
	    public void dispose() {
	    }

	    @Override
	    public void render(float delta) {    
	    	Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        stage.act(Gdx.graphics.getDeltaTime());
	        stage.draw();
	      //System.out.println((int)rotateAction.getDuration()+" - "+(int)rotateAction.getTime());
	        if((int)rotateAction.getDuration()==(int)rotateAction.getTime()){
//	        	System.out.println("perdi");
	        	
	        }
	     
	    }

	    @Override
	    public void resize(int width, int height) {
	    }

	    @Override
	    public void pause() {
	    }

	    @Override
	    public void resume() {
	    }
	
	
	
}

