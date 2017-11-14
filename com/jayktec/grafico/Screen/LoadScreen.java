package com.jayktec.grafico.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.Partida;
import com.jayktec.grafico.Miniheroes;

public class LoadScreen extends ScreenManager {

 private Miniheroes game;
 private ScreenManager screen;
 private String batle;
 boolean avanzar;
 private float timecount;
 private int cont=5;
 private Label carga;
 private Skin skin;
 private Stage stage;
private Partida partida;
private Dificultad dificultad;
private Configuracion configuracion;

 
	public LoadScreen(Miniheroes pGame) {
		super(pGame);
		game=pGame;
	}

	@Override
	public void show() {
		stage=new Stage();
		skin = new Skin(Gdx.files.internal("assets/skins/uiskin1.json"));
		carga=new Label("Cargando ..... ", skin);
		stage.addActor(carga);
		
		
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
		{System.out.println("hola");
			if(cont==3){
			game.mapMin.asignarPartida(partida, batle,dificultad,configuracion, avanzar);
				}
			if(cont==0){
				game.setScreen(screen);
			}
			cont--;
			timecount=0;
		}
		
		
		
	}
		
	@Override
	public void hide() {
		cont=5;
	}

	public void screenFrom(ScreenManager pScreen ,Partida partida, String pBatle, Dificultad dificultad,Configuracion configuracion,boolean pAvanzar){
		screen=pScreen;
		this.partida=partida;
		batle= pBatle;
		avanzar=pAvanzar;
		this.dificultad= dificultad;
		this.configuracion=configuracion;
	}
	
	
	
}
