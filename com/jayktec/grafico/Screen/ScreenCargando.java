package com.jayktec.grafico.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Piezas.ActorExtra;
import com.jayktec.grafico.Piezas.ActorExtra.TipoDeActor;



public class ScreenCargando extends ScreenManager {

	public Miniheroes game;
	private ScreenManager screenFrom;
	private float timecount;
	private Stage stage;
	private TextureAtlas packAnimacionReloj;
	private TextureRegion[] textureReg= new TextureRegion[2];
	private ActorExtra fondoReloj;
	private int tiempo=5;
	
	
	
	
	public ScreenManager getScreenFrom() {
		return screenFrom;
	}

	public void setScreenFrom(ScreenManager screenFrom) {
		this.screenFrom = screenFrom;
	}

	public ScreenCargando(Miniheroes pGame) {
		super(pGame);
		game = pGame;
		
	}

	@Override
	public void show() {

	stage=new Stage();
	packAnimacionReloj=new TextureAtlas(Gdx.files.internal("assets/skins/relojAnimacion.pack"));
	fondoReloj= new ActorExtra(this, TipoDeActor.RELOJ, 0);
	fondoReloj.setPack(packAnimacionReloj);
	fondoReloj.setTextureReg(textureReg);
	fondoReloj.setSize(260,260);
	fondoReloj.setPosition(523,70);
	stage.addActor(fondoReloj);
	



		
	}

	@Override
	public void hide() {
		
	
		stage.dispose();
		packAnimacionReloj.dispose();
		tiempo=5;
		
		
		
	
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act();
		stage.draw();
		timecount+=delta;
		if(timecount>=1)
		{
			
			
			if(tiempo==0){
//			for (int i =0;i<=30;i++)
				
			game.setScreen(screenFrom);
			}
			else{
				System.gc();
				System.out.println("ejecute GC");
			}
			tiempo--;
			timecount=0;
		}
		
		
		
		
		
		
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	@Override
	public void playMusic() {
		// TODO Auto-generated method stub
		super.playMusic();
	}

	@Override
	public void stopMusic() {
		// TODO Auto-generated method stub
		super.stopMusic();
	}

	@Override
	public boolean playingMusic() {
		// TODO Auto-generated method stub
		return super.playingMusic();
	}

	@Override
	public Music getMusic() {
		// TODO Auto-generated method stub
		return super.getMusic();
	}
	
	public void screenfrom(ScreenManager pScreenFrom){
		screenFrom = pScreenFrom;		
		
	}
}
