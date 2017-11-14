/*www.jayktec.com.ve*/

//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Miniheroes.java                            //
//                   Descripcion                            //
//                  Inicio del Juego                        //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      09/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico;


import java.io.File;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.jayktec.archivos.BorrarPartida;
import com.jayktec.archivos.CargarPartida;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.CreditosJuego;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.NuevaPartida;
import com.jayktec.archivos.Partida;
import com.jayktec.grafico.Minijuegos.MiniJuegoGeneral;
import com.jayktec.grafico.Minijuegos.MiniJuegoOrdenando;
import com.jayktec.grafico.Minijuegos.MiniJuegoTrivia;
import com.jayktec.grafico.Principal.ScreenJuegoPrincipal;
import com.jayktec.grafico.Screen.AnimacionPrueba;
import com.jayktec.grafico.Screen.ExtrasScreen;
import com.jayktec.grafico.Screen.InicioScreen;

import com.jayktec.grafico.Screen.LoadScreen;
import com.jayktec.grafico.Screen.ScreenCarga;

import com.jayktec.grafico.Screen.ScreenManager;
import com.jayktec.grafico.Screen.ScreenVideo;

import com.jayktec.grafico.Screen.fvaScreen;
import com.jayktec.grafico.Screen.jayktecScreen;
import com.jayktec.grafico.Screen.mapaGeneralScreen;
import com.jayktec.grafico.Screen.mapaMinijuegoScreen;
import com.jayktec.grafico.Screen.mapaPrueba;


public class Miniheroes extends Game {

	public ExtrasScreen extrasScreen;
	public ScreenJuegoPrincipal juegoPrincipal;
	public InicioScreen inicio;
	private AssetManager manager;
	public jayktecScreen jayktec;
	public fvaScreen fva;
	public mapaGeneralScreen mapGen;
	public mapaPrueba mapGen2;
	public Dialogo opcScreem;
	public mapaMinijuegoScreen mapMin;
//	public ScreenVideo video;
	public NuevaPartida nuevaPartida;
	public CargarPartida cargarPartida;
	public BorrarPartida borrarPartida;
	public ScreenCarga carga;
//	public MiniJuegoGeneral miniJuegoGeneral;
	public AnimacionPrueba animacion;
	public MiniJuegoOrdenando ordenando;
	public MiniJuegoTrivia trivia;
	public mapaPrueba mapaPrueba;
	private Music cortinaFondo;
	public LoadScreen load;
	public CreditosJuego creditosJuego;
	private Preferencias preferencias;
	
	public AssetManager getManager() {
		return manager;
	}
	
	public void setManager(AssetManager pManager) {
		manager=pManager;
	}
	
	
	
	@Override
	public void create() {
		
		preferencias = Preferencias.getInstance();
		preferencias.load();
		
		manager = new AssetManager();
		manager.load("assets/Sonidos/cortinaFondo.ogg", Music.class);
		jayktec = new jayktecScreen(this);
		fva = new fvaScreen(this,cortinaFondo);
//		video = new ScreenVideo(this);
		carga=new ScreenCarga(this);
		setScreen(carga);
		
		

	}

	public void finalizarCarga(){
		cortinaFondo = 	this.getManager().get("assets/Sonidos/cortinaFondo.ogg");
		//cortinaFondo.play();
		
		Gdx.graphics.setContinuousRendering(true);
		// Secuencia 1
		creditosJuego=new CreditosJuego(this);
		load=new LoadScreen(this);
//		video=null;
		System.gc();
		inicio = new InicioScreen(this);
		nuevaPartida = new NuevaPartida(this);
		cargarPartida = new CargarPartida(this);
		borrarPartida = new BorrarPartida(this);
		extrasScreen = new ExtrasScreen(this);
		
		// Screem alternativas
		
		mapaPrueba = new mapaPrueba(this);
		
		animacion = new AnimacionPrueba(this);
		// Juegosx
		juegoPrincipal = new ScreenJuegoPrincipal(this);
		// System.out.println("cargado juego principal");
		ordenando = new MiniJuegoOrdenando(this);
		// System.out.println("cargado juego ordenado");
//		miniJuegoGeneral = new MiniJuegoGeneral(this);
		// System.out.println("cargado juego general");
		trivia = new MiniJuegoTrivia(this);
		// System.out.println("cargado juego trivia");
		// Mapas
//
		try {
			mapGen = new mapaGeneralScreen(this, "batalla0");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // "assets/mapasTiled/mapGen3.tmx");

		// setScreen(video);
		try {
			mapMin = new mapaMinijuegoScreen(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Seleccion de la primera pantalla de la aplicacion

			setScreen(inicio);
			
	}
	
	
	

	public void reiniciarScreens(Partida partida,
			Dificultad dificultad,
			Configuracion configuracion,
			boolean pPosicion){

eliminarScreens();

finalizarCarga();
//video = new ScreenVideo(this);	
//	mapGen.asignarPartida(partida, dificultad, configuracion, pPosicion);
	}
	
	
public void eliminarScreens(){
   
	
	System.out.println("inicio: "+inicio);
	
	
	
	// Secuencia 1
			creditosJuego=null;
			load=null;
			
	
			
//			video=null;
			
			
			inicio.dispose();
			
			inicio=null;
			
			nuevaPartida .dispose();
			nuevaPartida=null;
			
			cargarPartida.dispose();
			cargarPartida =null;
			
			borrarPartida.dispose();
			borrarPartida =null;
			
			extrasScreen.dispose();
			extrasScreen=null;
			
			// Screem alternativas
			
			mapaPrueba =null;
			
			animacion =null;
			// Juegos
			juegoPrincipal =null;
			// System.out.println("cargado juego principal");
			ordenando =null;
			// System.out.println("cargado juego ordenado");
			
			// System.out.println("cargado juego general");
			trivia =null;
			// System.out.println("cargado juego trivia");
			// Mapas

//				mapGen.dispose();
//				mapGen =null;
					
				mapMin.dispose();
				mapMin=null;
				System.out.println("inicio: "+inicio);
				 Runtime garbage = Runtime.getRuntime();
			        garbage.gc();
				
	}
	
	public void gc(){
		for (int i =0;i<=30;i++)
			System.gc();
		}
	
//	@Override
//	public void pause () {
//		salir();
//	}

	public void salir(){
		ScreenManager em = (ScreenManager) this.getScreen();
		em.salir();
	}

	@Override
	public void dispose(){
		
		preferencias.save();
		System.out.println("dispose miniheroes");
		File file = new File(System.getProperty("user.dir") + "/miniHeores.tmp");
		file.delete();
		super.dispose();
		
	}
}
