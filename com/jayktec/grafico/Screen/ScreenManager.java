
//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Protocolo.java                            //
//                   Descripcion                            //
//             Manejador de pantallas                       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      09/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Screen;


import javax.xml.stream.util.EventReaderDelegate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch.Config;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Piezas.ActorExtra;
import com.jayktec.grafico.Piezas.Pieza;
import com.jayktec.grafico.Piezas.Tablero;

public class ScreenManager implements Screen{

	private Miniheroes game;
	public float gameWidth = 848;
	//public float gameWidth = 548;
	public float gameHeight = 552;
	private Tablero tablero;
	public Music musicaFondo;
	protected float volume = 0.5f;
	Texture texture;
	Image imagen;
	

	
	public ScreenManager(){
		
	}
	
	
	public ScreenManager(Miniheroes game) {
		
		this.game = game;
		if(game.getManager().update())
		musicaFondo = game.getManager().get("assets/Sonidos/cortinaFondo.ogg");
	
//			
//		if(game.getManager().update()){
////		texture=game.getManager().get("assets/Texturas/images.png");
//		imagen=new Image(texture);}
	}


	
	
	@Override
	public void show() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {
		// System.out.println(width+" - "+height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		System.gc();
		
		
	}

	public boolean ValidarMovimiento(Pieza pPieza, int pJuego, int mouseX, int mouseY, float actorX, float actorY,
			float rangoX, float rangoY, float posX, float posY) {
		return false;
	}

	public void setCasilla(String pPosicion, Pieza pPieza, int pInd) {
		// TODO Auto-generated method stub

	}

	public Tablero getTablero() {
		return tablero;
	}

	public float getGameWidth() {
		return gameWidth;
	}

	public float getGameHeight() {
		return gameHeight;
	}

	public Miniheroes getGame() {
		return this.game;
	}

	public void setActorExtra(String pPgn, ActorExtra pActor, int pInd) {
		

	}
	
	public void destruirMuro(float x, float y){
		
	}
	
	public void construirMuro(float x, float y){
	}	
	
public void playMusic(){
	
	musicaFondo.play();
	musicaFondo.setLooping(true);
	musicaFondo.setVolume(volume);

}	

public void stopMusic(){
	if(musicaFondo.isPlaying())
		musicaFondo.stop();
}	

public boolean playingMusic(){
	if(musicaFondo.isPlaying())
	{
		return true;
	}
	else
	return false;
}
public Music getMusic() {
	return musicaFondo;
}

public float getVolume(){
	return volume;

}
public void setVolume(float pVolume){
	 volume = pVolume;
}

public boolean movimientosPosibles(String pPgnI, String pPgnF){
	return false;
}


public void salir(){
	
}


}