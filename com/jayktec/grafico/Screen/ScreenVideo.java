
/*www.jayktec.com.ve*/

//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   ScreenVideo.java                       //
//                     Descripcion                          //
//                 Video de presentacion                   //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      20/04/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Screen;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.Partida;
import com.jayktec.grafico.Dialogo;
import com.jayktec.grafico.Enums.eTipoVideo;
import com.jayktec.grafico.Miniheroes;

//import net.indiespot.media.impl.VideoMetadata;

public class ScreenVideo extends ScreenManager {

	public Texture playerTexture;
	public Miniheroes game;
//	public MoviePlayer player;
	public File mov;
	float delay = 0;
	boolean flag = true;
	private String video;
	private Boolean modificado = false;
	private Partida partida;
	private String minijuego;
	private int w = Gdx.graphics.getWidth();
	private int h= Gdx.graphics.getHeight();
	private Dialogo dialogo;
	private Stage stage;
	FileHandle fh;
	String path;
	private Dificultad dificultad;
	private Configuracion configuracion;
	private boolean videoPaused = false;
private boolean videoFinal=false;

	String minijuegoPartida;
	String batallaPartida;
	boolean noMostrarSalir = false;
//	public mapaGeneralScreen mapGen;
	
	
	/**
	 * Constructor para la clase ScreenVideo que reproduce el video de
	 * presentacion
	 * 
	 * @param pGame
	 *            Variable para que la clase Miniheroes.java genere la pantalla
	 */
	public ScreenVideo(Miniheroes pGame) {
		super(pGame);
		game = pGame;
		video = "assets/video/primero.ogv";
	}
	
	/**
	 * @return the video
	 */
	public String getVideo() {
		return video;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(String video) {
		this.video = video;
	}



	@Override
	public void show() {		

		cargarDialogo();
	}
	
	private void cargarDialogo(){
		fh = Gdx.files.internal(video);	
		path = fh.file().getAbsolutePath();
//		System.out.println("video: " + path);

if ( videoFinal)
	noMostrarSalir=true;
else 
	noMostrarSalir=false;
System.out.println("creando dialogo");
	dialogo = new Dialogo();
	dialogo.create( path, eTipoVideo.Historia, noMostrarSalir);
	//dialogo = Dialogo.getInstance(path, eTipoVideo.Historia);
	dialogo.setWidth(gameWidth);
	dialogo.setHeight(gameHeight);
	stage = new Stage();
	stage.addActor(dialogo);
	System.out.println("new stage");
	Gdx.input.setInputProcessor(stage);
	System.out.println("termine cargar dialogo");
	
	dialogo.addListener(new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			//stage.removeListener(this);					
			procesarVideoListener(dialogo.getResult(), eTipoVideo.Historia);
		}
	});
		
	dialogo.addListener(new InputListener(){				
		@Override
		public boolean keyDown( InputEvent event, int keyCode){	   			
//			System.out.println("KeyCode" + keyCode);
			if (keyCode  == Keys.ENTER )
			//stage.removeListener(this);
			procesarVideoListener(dialogo.getResult(), eTipoVideo.Historia);	
			return false;
			}
	}); 							
}
	public void procesarVideoListener(Object pResult, eTipoVideo pTipoVideo){
		try {
			System.out.println("dialogo.getResult(): " + pResult);
			
				if (pResult.equals(true)) {
					flag = true;
					dialogo.dispose();															
					cargarDialogo();	
				}
				else if (pResult.equals(false)){
					//stage.dispose();
						
					System.out.println("pasando por aqui");
					dialogo.dispose();				
					if (modificado){
						
						if (minijuego.equals("Minijuego1")) {
							
//							if (mapGen!=null)
//							{
//								mapGen.dispose();
//								mapGen=null;
//								System.gc();
//								System.out.println("entre a limpiar");
//							}
//							mapGen = new mapaGeneralScreen(game,"batalla0");
//							try {
//								mapGen.asignarPartida(partida,dificultad,configuracion,false
//										);
//								
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							
//							
//System.out.println("voy a map gen");
//							game.setScreen(mapGen);
//							
//							mapGen=null;
//							System.gc();
							game.mapGen.asignarPartida(partida,dificultad,configuracion,false);
							game.setScreen(game.mapGen);
						}
						else if(minijuego.equals("Minijuego35")){
							
//							if (mapGen!=null)
//							{
//								mapGen.dispose();
//								mapGen=null;
//								System.gc();
//								System.out.println("entre a limpiar");
//							}
//							mapGen = new mapaGeneralScreen(game,"batalla0");
//							try {
//								mapGen.asignarPartida(partida,dificultad,configuracion,true
//										);
//								
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							
//							
//
//							game.setScreen(mapGen);
//							mapGen=null;
//							System.gc();
//							System.out.println("salgo de screen");
//							
							
							game.mapGen.asignarPartida(partida,dificultad,configuracion,true);
							game.setScreen(game.mapGen);
							}

						else{						
														
							game.mapMin.asignarPartida(partida, minijuego,dificultad,configuracion,false);
							game.setScreen(game.mapMin);							
						}
					}
					//TODO game.setScreen(game.carga);
				}
				else  if (pResult.toString().contains("pausa")) {
					if (dialogo.isPlaying()){
						videoPaused = true;
						System.out.println("pausar el video");
						//flag = true;
					}
					
					else if (dialogo.isPaused()){
						videoPaused = false;
						System.out.println("resume el video");
						//flag = false;						
					}
										
						System.out.println("pausado el video");
				}
				else  if (pResult.toString().contains("repetir")) {
					flag = true;
				}
				else {
					game.setScreen(game.carga);
				}							
			}
			catch (Exception e) {
				
			}
	}
	

	@Override
	public void hide() {
	//	stage.getActors().removeValue(dialogo, true);
		
		Gdx.input.setInputProcessor(null);
		
		try {
			this.finalize();
			//this.dispose();
			super.finalize();
		} catch (Throwable e) {
		System.out.println("player.dipose error:" + e.getMessage());
			e.printStackTrace();
		}
		System.gc();
		this.dispose();
	}

	@Override
	public void dispose() {
		if (dialogo!=null) {
			dialogo.dispose();
			dialogo = null;
		}

		if (stage!=null) {
			stage.dispose();
			stage = null;
		}
		System.out.println("disposing");
		fh = null;
		path =null;
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 playerTexture=null;
		 game=null;

		 mov=null;
		
		  video=null;
		
		partida=null;
		 minijuego=null;
		
		 dialogo=null;
		 stage=null;
		 fh=null;
		 path=null;
		 dificultad=null;
		 configuracion=null;
		

	minijuegoPartida=null;
	batallaPartida=null;

//		 mapGen=null;	
		
		
		
		
	System.gc();	
	System.gc();
	
	}
	@Override
	public void render(float delta) {

		Gdx.gl.glViewport(0, 0, w, h);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
//System.out.println(getVideo());
	if(stage!=null){
		
		stage.act();
		
		stage.draw();
		
	}
		
//		System.out.println("renders");
//		if (player != null) {
//			player.tick();
//			player.syncTexture(5);
//
//			/**
//			 * Renderizar el video
//			 */
//			batch.begin();
//			float lh = player.movie.height() * w / player.movie.width();
//			batch.draw(playerTexture, 0, (h - lh) / 2, w, lh);
//			batch.end();
//		} else {
//			delay += Gdx.graphics.getDeltaTime();
//		}
		if (flag == false) {

//System.out.println("player.isPlaying()" + player.isPlaying() + "Gdx.input.isKeyPressed(Keys.ESCAPE) " + Gdx.input.isKeyPressed(Keys.ESCAPE));

			
			
			if (dialogo!=null&& !dialogo.isPlaying() && !videoPaused) {//|| Gdx.input.isKeyPressed(Keys.ESCAPE)) {				
				if (modificado){
		
					System.out.println("modificado");

					if (minijuego.equals("Minijuego1")) {
						
//						if (mapGen!=null)
//						{
//							mapGen.dispose();
//							mapGen=null;
//							System.gc();
//							System.out.println("entre a limpiar");
//						}
//						try {
//							mapGen = new mapaGeneralScreen(game,"batalla0");
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						try {
//							mapGen.asignarPartida(partida,dificultad, configuracion,false
//									);
//							
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//						
//
//						game.setScreen(mapGen);
//						mapGen=null;
//						System.gc();
						
						game.mapGen.asignarPartida(partida,dificultad, configuracion,false);
						game.setScreen(game.mapGen);
					}
					else if(minijuego.equals("Minijuego35"))
						{
						
//						if (mapGen!=null)
//						{
//							mapGen.dispose();
//							mapGen=null;
//							System.gc();
//							System.out.println("entre a limpiar");
//						}
//						try {
//							mapGen = new mapaGeneralScreen(game,"batalla0");
//						} catch (Exception e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						try {
//							mapGen.asignarPartida(partida,dificultad, configuracion,true
//									);
//							
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//						
//
//						game.setScreen(mapGen);
//						
//						mapGen=null;
//						System.gc();
						game.mapGen.asignarPartida(partida,dificultad, configuracion,true);
						game.setScreen(game.mapGen);
						}
					else
						{
						stage.getActors().removeValue(dialogo, true);
						game.mapMin.asignarPartida(partida, minijuego,dificultad, configuracion,false);
							
							game.setScreen(game.mapMin);
						}										
				} else {					
						game.setScreen(game.carga);
				}
			}
		}
		flag = false;
		
	}

	/**
	 * Ver el video de una batalla y una partida específica
	 * 
	 * @param video
	 *            nombre del archivo que se encuentra en el archivo de
	 *            configuración
	 * @param nombrePartida
	 *            partida que se encuentra jugando el usuario
	 * @param minijuego
	 *            minijuego o batalla a ingresar
	 * @author yisheng
	 */
	public void asignarPartida(String video, Partida partida, String minijuego,Dificultad dificultad, Configuracion configuracion) {
		
		this.video = video;
		this.partida = partida;
		this.minijuego = minijuego;
		this.modificado = true;
		this.flag = true;
		this.dificultad=dificultad;
		this.configuracion=configuracion;
		
		minijuegoPartida = partida.getMinijuego();
		batallaPartida = partida.getBatalla();
	
		System.out.println("minijuego " + minijuego + " partida.BATALLA " + partida.getBatalla());
		if (minijuego.equals(partida.getBatalla())||( minijuego.equals("Minijuego1") && partida.getBatalla().equals("Batalla1") )){						
				noMostrarSalir = true;			
			}
		else{
			noMostrarSalir = false;
		}
	}
	
	public void partidaActual(){
		
		
	}

	public void setWidth(int pWidth){		
		w = pWidth;
	}
	public void setHeight(int pHeight){		
		h = pHeight;
	}
	public void setVideoFinal(boolean videoFinal) {
	this.videoFinal = videoFinal;
}

	 
}