
//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Trivia Screen.java                            //
//                   Descripcion                            //
//             Escena para juego de preguntas y respuestas              //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Yisheng León     06/06/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Minijuegos;

import java.io.File;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.Enunciado;
import com.jayktec.archivos.Partida;
import com.jayktec.archivos.Pregunta;
import com.jayktec.archivos.Trivia;
import com.jayktec.grafico.BotonImagen;
import com.jayktec.grafico.Dialogo;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Preferencias;
import com.jayktec.grafico.Enums.eContadores;
import com.jayktec.grafico.Enums.eTipoTrivia;
import com.jayktec.grafico.Enums.eTipoVideo;
import com.jayktec.grafico.Screen.ScreenManager;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class MiniJuegoTrivia extends ScreenManager {


	private Stage stage =  new Stage();
	public Miniheroes game;
	private String minijuego;
	private Skin skin1, skin2, skin3,skin4;
	private TextureAtlas buttonAtlas, atlas3;
	private BitmapFont font;
	private Preferencias  vopc;
	int w = ((int) super.getGameWidth());
	int h = ((int) super.getGameHeight());
	Boolean flag = false;
	int contadorEnunciado = 0;
	int contadorCorrectas = 0;
	int contadorIncorrectas = 0;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	private Dificultad dificultad;
	private String batalla;
	private ArrayList<Enunciado> enunciados = new ArrayList<Enunciado>();
	private Texture preguntaTexto;
	private Image preguntaImagen;
	private Image triviaImagen;
	private TextButton salir, reiniciar, guardar, Opcion, ayuda, ordenarPiezas;
	private Preferencias preferencias;
	//private boolean tocarSonido = false;
	private TextureAtlas Atlas3;
	private TextureAtlas pack;
	private eTipoTrivia tipoTrivia;
	private boolean flag2=true;
	private Sound sonidoDerrota, sonidoTriunfo, sonidoRespuestaOK , sonidoRespuestaError;
	private Dialogo videoScreen;
	private String videoAyuda;
	private String videoHistoria;
	private String videoBiografia;
	private TextureAtlas pack3;
	private TextButton contadorHumano;
	private TextButton contadorHumano2;
	private TextButton contadorMaquina;
	private TextButton contadorMaquina2;
	public boolean mostrarVideos = true;
	private Sound SonidoPregunta =null;
	private Partida partida;
	private String gradoDificultad;
	private Configuracion configuracion;
	Sound sPregunta =null;
	public FileHandle manejadorSonido;
	public File archivo;
	
	public MiniJuegoTrivia(final Miniheroes pGame) {
		super(pGame);
		game = pGame;
		vopc = Preferencias.getInstance();
		vopc.load();
		sonidoDerrota = game.getManager().get("assets/Sonidos/sonidoDerrota.mp3");
		sonidoTriunfo = game.getManager().get("assets/Sonidos/sonidoTriunfo.ogg");
		sonidoRespuestaError = game.getManager().get("assets/Sonidos/movIncorrecto.ogg");
		sonidoRespuestaOK= game.getManager().get("assets/Sonidos/moneda.mp3");

		font = new BitmapFont();
		
		skin1 = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("assets/skins/minijuegos.pack"));
		skin1.addRegions(buttonAtlas);
		
		skin2 = new Skin();
		pack = new TextureAtlas(Gdx.files.internal("assets/skins/minijuegos.pack"));
		skin2.addRegions(pack);
		
		skin3 = new Skin(Gdx.files.internal("assets/skins/uiskin1.json"));
		
		skin4=new Skin();
		pack3 = new TextureAtlas(Gdx.files.internal("assets/skins/numeros.pack"));
		skin4.addRegions(pack3);
		

		
		if (vopc.getPreferencia("pantallacompleta") == true) {
			DisplayMode currentMode = Gdx.graphics.getDisplayMode();
			Gdx.graphics.setFullscreenMode(currentMode);

		} else {
			Gdx.graphics.setWindowedMode(w, h);

		}
	}

	/**
	 * Procedimiento para actualizar una partida donde se gano el minijuego
	 * 
	 * @return Devuelver verdadero si actualizo sin error o falso en caso
	 *         contrario
	 * @author yisheng
	 */
	private Boolean ganar() {
		int nroMinijuego = Integer.parseInt(minijuego.substring(9));
		int nroBatalla = Integer.parseInt(batalla.substring(7));
		
		if (nroMinijuego == 5) {
			nroBatalla++;
			nroMinijuego = 1;
		} else {
			nroMinijuego++;
		}

		try {

			int batallaPartida = Integer.parseInt(partida.getBatalla().substring(7));
			//int minijuegoPartida = Integer.parseInt(Partida.GetAtributo(nombrePartida, "Minijuego").substring(9));

			if (batallaPartida < nroBatalla) {
				partida.setBatalla( "Batalla" + nroBatalla);
				partida.setMinijuego("Minijuego" + nroMinijuego);
				} 
			else if (batallaPartida == nroBatalla) {
				partida.setMinijuego("Minijuego" + nroMinijuego);
			}
			return partida.ActualizarPartida();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
				
	}
		
	/**
	 * Asigna la partida y sus características al minijuego
	 * 
	 * @param batalla
	 *            mundo en que juega el usuario
	 * @param minijuego
	 *            juego o escenario a cargar del archivo de de configuración de
	 *            minijuegos
	 * @param nombrePartida
	 *            nombre de la partida guardada o creada en el archivo de
	 *            configuración donde se toman los valores para iniciar el
	 *            minijuego
	 * @throws Exception
	 *             no se pudo asignar partida
	 */
	
	public void asignarPartida(String batalla, String minijuego, Partida partida, eTipoTrivia pTipoTrivia,Dificultad dificultad,Configuracion configuracion) throws Exception {
		
		this.minijuego = minijuego;
//		System.out.println("minijuego: " + minijuego +" de la batalla: " + batalla);
		this.batalla = batalla;
		this.dificultad= dificultad;
		this.gradoDificultad=dificultad.getValorDificultad();
//		System.out.println("nombrePartida:" + nombrePartida);
		this.partida=partida;
		tipoTrivia = pTipoTrivia;
		this.configuracion=configuracion;
	
	}
	
	private void cargarPreguntas(){
//	System.out.println("cargando las preguntas para la dificultad: " + dificultad);

		//cargar todas las  preguntas
/*  		try {
//			this.enunciados = crearEnunciados(100, 1, tipoTrivia);
//			this.enunciados.addAll(crearEnunciados(100, 2, tipoTrivia));
//			this.enunciados.addAll(crearEnunciados(100, 3, tipoTrivia));
//			this.enunciados.addAll(crearEnunciados(100, 4, tipoTrivia));
//			this.enunciados.addAll(crearEnunciados(100, 5, tipoTrivia));
//			
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
 */
  		
		
		try {
			  	if (gradoDificultad.equals("1")) {
			  		this.enunciados = crearEnunciados(10, 1, tipoTrivia);
			} else 
				if (gradoDificultad.equals("3")) {
					this.enunciados = crearEnunciados(5, 1, tipoTrivia);
					this.enunciados.addAll(crearEnunciados(5, 2, tipoTrivia));

			} else if (gradoDificultad.equals("5")) {
					this.enunciados = crearEnunciados(5, 2, tipoTrivia);
					this.enunciados.addAll(crearEnunciados(5, 3, tipoTrivia));

			} else if (gradoDificultad.equals("7")) {
					this.enunciados = crearEnunciados(5, 4, tipoTrivia);
					this.enunciados.addAll(crearEnunciados(5, 5, tipoTrivia));

			} else {
					this.enunciados = crearEnunciados(10, 5, tipoTrivia);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
			e.printStackTrace();
		}

	}

	/**
	 * Crea una lista de enunciados , según la cantidad y el grado de dificultad
	 * de la pregunta
	 * 
	 * @param cantidad
	 *            cantidad de Enunciados en la lista
	 * @param dificultad
	 *            grado de dificultad de las preguntas.
	 * @throws Exception
	 * @author yisheng
	 * @return
	 */
	private ArrayList<Enunciado> crearEnunciados(int cantidad, int dificultad, eTipoTrivia tipoTrivia) throws Exception {	
		ArrayList<Enunciado> enunciado = new ArrayList<Enunciado>();
		ArrayList<Pregunta> preguntas = Trivia.GetPreguntas(dificultad , tipoTrivia);
//		 System.out.println(dificultad);
//		 System.out.println("tamaño de las preg: " + preguntas.size());
		for (int i = 0; i < cantidad && i <= preguntas.size(); i++) {
			// System.out.println("crear enunciados:" + i);
			Enunciado temp = new Enunciado(preguntas , tipoTrivia);
			preguntas.remove(temp.index);
			temp.index = -1;
			enunciado.add(temp);
		}
		return enunciado;
	}

	@Override
	public void show() {
		
		
		 preferencias = Preferencias.getInstance();
		 //preferencias.load();
		 super.setVolume(preferencias.getPreferenciav("volumen"));
		boolean tocandoMusica=super.playingMusic();
		if (preferencias.getPreferencia("musica")) {
			if(tocandoMusica)
			{
				super.stopMusic();
			}
			}
		try {
			
			videoAyuda = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ayuda");
//			System.out.println("batallaNombre" +batallaNombre);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cargarPreguntas();
		
		contadorEnunciado = 0;
		contadorCorrectas = 0;
		contadorIncorrectas = 0;
		camera = new OrthographicCamera();

		tiledMap = new TmxMapLoader().load("assets/mapasTiled/fondoTrivia.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		

		triviaImagen = new Image(new Texture("assets/Trivia/Titulo.png"));

		triviaImagen.setPosition(50, 490);
		
		cargaPregunta(enunciados.get(contadorEnunciado));
		

		contadorEnunciado=0;
		contador(2);
		int nroMinijuego = Integer.parseInt(minijuego.substring(9));
		int nroBatalla = Integer.parseInt(batalla.substring(7));
		int batallaPartida = 0;
		int minijuegoPartida = 0;
		
			try {
				batallaPartida = Integer.parseInt(partida.getBatalla().substring(7));
				minijuegoPartida = Integer.parseInt(partida.getMinijuego().substring(9));
			} catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		if (nroBatalla < batallaPartida){
			mostrarVideos = false;
		}
		else
		if (nroMinijuego < minijuegoPartida) 
		{
			mostrarVideos = false;	
		}
		else{
			//System.out.println("entrando en el else");
			mostrarVideos = true;
		}
		if(mostrarVideos)
		{
			cargarayuda();
		}
		Gdx.input.setInputProcessor(stage);
		
		Preferencias(true);		
	}
	/**
	 * reinicia el stage y carga nuevamente los elementos visuales
	 */
	public void reiniciarStage() {
//		System.out.println("reiniciar stage");
System.out.println("stage:"+stage);
if(stage!=null){
		stage.clear();
		stage.dispose();
		stage=null;
		}


		stage = new Stage();
		stage.addActor(triviaImagen);

		camera = (OrthographicCamera) stage.getCamera();
		botones(true);
		mostrarBotones(true);
		contador(2);
		actualizarContador(true, contadorCorrectas);
		nombreContador(eContadores.Correctas,true);
		actualizarContador(false, contadorIncorrectas);
		nombreContador(eContadores.Incorrectas,false);
		Gdx.input.setInputProcessor(stage);	
	}

	public void cargaPregunta(final Enunciado enunciado) {
		
		reiniciarStage();
		// cargando Pregunta
		final String vPregunta = enunciado.getPregunta().getTexto();
		preguntaTexto = new Texture(vPregunta);
		
		final String correcta = enunciado.getPregunta().getCorrecta();
		final String resolucion = enunciado.getPregunta().getResolucion();
		
		System.out.println("Cargando la pregunta: " + vPregunta + " la respuesta correcta es " + correcta); 
		preguntaImagen = new Image(preguntaTexto);
		float duraccion = 0;
		
		try{
			if(archivo!=null){
				archivo=null;
				System.gc();
			}
			
		archivo=new File((Gdx.files.getLocalStoragePath()+ vPregunta.substring(0,vPregunta.length()-4) +".ogg"));
		 duraccion = obtenertiempo(archivo);

		 sPregunta= Gdx.audio.newSound(sonidoPregunta(vPregunta.substring(0,vPregunta.length()-4) +".ogg"));
		 
		 
//		preguntaImagen.addListener(new FocusListener(){
//		@Override
//		public boolean handle (Event event) {
//			if (event.toString().contains("enter"))
//				
//				
//			
//				sPregunta.resume();
//			return false;
//		}});	
//
		
		}catch(Exception e){
			System.out.println("Error: " + e);
			
		}
		
		
		
		//if (ssPregunta!=null )
			playSonidoPregunta(sPregunta);
			
	
		preguntaImagen.addListener(new FocusListener(){
			@Override
			public boolean handle (Event event) {
				if (event.toString().contains("enter"))																
					resumeSonidoPregunta();
					//ssPregunta.resume();
				return false;
			}});	

		stage.addActor(preguntaImagen);
		preguntaImagen.setPosition(30, 180);

		// cargando respuestas
		
		Texture textureOptionBackGround = new Texture("assets/Trivia/nombres/TRANSPARENCIA.png");
		
		final String nombre1 = enunciado.getRespuestas().get(0).getValor();									
		Texture textureOpcion1 = new Texture(enunciado.getRespuestas().get(0).getValor());
		Texture textureOpcion1R =  new Texture(nombre1.substring(0,nombre1.length()-4) + "Resaltado.png");
		final String respuestaOpcion1 = enunciado.getRespuestas().get(0).getOrden();
		final BotonImagen opcion1 = new BotonImagen(textureOpcion1, textureOpcion1, textureOpcion1R, textureOptionBackGround);
		opcion1.setPosition(50, 140);
		opcion1.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				opcion1.stop();
				stopSonidoPregunta();
				resolucion(resolucion, respuestaOpcion1, correcta);
			}});	
		opcion1.addListener(new FocusListener(){
		@Override
		public boolean handle (Event event) {			
			if (event.toString().contains("enter"))
				pauseSonidoPregunta();
				
			return false;
		}});	

		
		stage.addActor(opcion1);

		final String nombre2 = enunciado.getRespuestas().get(1).getValor();
		Texture textureOpcion2 = new Texture(enunciado.getRespuestas().get(1).getValor());
		Texture textureOpcion2R = new Texture(nombre2.substring(0,nombre2.length()-4) + "Resaltado.png");		
		final String respuestaOpcion2 = enunciado.getRespuestas().get(1).getOrden();
		final BotonImagen opcion2 = new BotonImagen(textureOpcion2, textureOpcion2, textureOpcion2R, textureOptionBackGround);
		
		
		opcion2.setPosition(50, 100);
		opcion2.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
//				System.out.println("clicked 2");
				opcion2.stop();
				stopSonidoPregunta();
				resolucion(resolucion, respuestaOpcion2, correcta);
			}});
		opcion2.addListener(new FocusListener(){
		@Override
		public boolean handle (Event event) {
			if (event.toString().contains("enter"))
				pauseSonidoPregunta();
			return false;
		}});	

		stage.addActor(opcion2);
		try {
			if (enunciado.getRespuestas().size() > 2) {
				final String nombre3 = enunciado.getRespuestas().get(2).getValor();		
				Texture textureOpcion3 = new Texture(enunciado.getRespuestas().get(2).getValor());
				Texture textureOpcion3R = new Texture(nombre3.substring(0,nombre3.length()-4) + "Resaltado.png");
				
				final String nombre4 = enunciado.getRespuestas().get(3).getValor();		
				Texture textureOpcion4 = new Texture(enunciado.getRespuestas().get(3).getValor());
				Texture textureOpcion4R = new Texture(nombre4.substring(0,nombre4.length()-4) + "Resaltado.png");
			
				final String respuestaOpcion3 = enunciado.getRespuestas().get(2).getOrden();
				final String respuestaOpcion4 = enunciado.getRespuestas().get(3).getOrden();
	
				final BotonImagen opcion3 = new BotonImagen(textureOpcion3, textureOpcion3, textureOpcion3R, textureOptionBackGround);
				final BotonImagen opcion4 = new BotonImagen(textureOpcion4, textureOpcion4, textureOpcion4R, textureOptionBackGround);
		
				opcion3.setPosition(50, 60);
				opcion3.setTouchable(Touchable.enabled);
				opcion3.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
	//					System.out.println("clicked 3");
						opcion3.stop();
						stopSonidoPregunta();
						resolucion(resolucion, respuestaOpcion3, correcta);
					}});
	
				opcion3.addListener(new FocusListener(){
					@Override
					public boolean handle (Event event) {
						if (event.toString().contains("enter"))
							pauseSonidoPregunta();
						return false;
					}});	
	
				stage.addActor(opcion3);
	
				opcion4.setPosition(50, 20);
				opcion4.setTouchable(Touchable.enabled);
				opcion4.addListener(new ClickListener(){
					@Override
					public void clicked(InputEvent event, float x, float y) {
	//					System.out.println("clicked 3");
						opcion4.stop();
						stopSonidoPregunta();
						resolucion(resolucion, respuestaOpcion4, correcta);
					}});
						
				opcion4.addListener(new FocusListener(){
					@Override
					public boolean handle (Event event) {
						pauseSonidoPregunta();
						return false;
					}});
			
		
			
	
				//opcion4.AddFocusListener(new FileHandle(new File( nombre4.substring(0,nombre4.length()-4) +".ogg")));
				stage.addActor(opcion4);
				Timer.schedule(new Task() {
					@Override
					public void run() {
						try {
							opcion3.AddFocusListener(new FileHandle(new File( nombre3.substring(0,nombre3.length()-4) +".ogg")));	
						} catch (Exception e) {
							System.out.println(" trivia opcion3.AddFocusListener: " + e.getMessage());
						}
						try {
							opcion4.AddFocusListener(new FileHandle(new File( nombre4.substring(0,nombre4.length()-4) +".ogg")));
						} catch (Exception e) {
							System.out.println(" trivia opcion4.AddFocusListener: " + e.getMessage());
							
						}
						
						
	
					}
				}, duraccion);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		Timer.schedule(new Task() {
			@Override
			public void run() {
				try {
					opcion1.AddFocusListener(new FileHandle(new File( nombre1.substring(0,nombre1.length()-4) +".ogg")));
				} catch (Exception e) {
					System.out.println(" trivia opcion1.AddFocusListener: " + e.getMessage());
					
				}
				try {
					opcion2.AddFocusListener(new FileHandle(new File( nombre2.substring(0,nombre2.length()-4) +".ogg")));
				} catch (Exception e) {
					System.out.println(" trivia opcion2.AddFocusListener: " + e.getMessage());
					
				}




			}
		}, duraccion);
			
		

			
			//opcion4.setTouchable(Touchable.enabled);

	}

	/**
	 * evalua la respuesta a la pregunta y comprueba si es correcta o erronea,
	 * además levanta la corrección de existir en el enunciado
	 * 
	 * @param resolucion
	 * @param respuestaOpcion
	 * @param respuestaCorrecta
	 */
	private void resolucion(String resolucion, String respuestaOpcion, String respuestaCorrecta) {
		// TODO Auto-generated method stub

//		System.out.println("entre a resolucion");

		contadorEnunciado++;

//		System.out.println("la respuesta correcta es: " + respuestaCorrecta);
		if (respuestaOpcion.equals(respuestaCorrecta)){
			contadorCorrectas++;
			actualizarContador(true, contadorCorrectas);
			sonidoRespuestaOK.play();
			if (contadorEnunciado >= enunciados.size()){
				// conclusion();				 
			}
			else{
				//System.out.println("Contador de Enunciados: " + contadorEnunciado);
				//contadorEnunciado++;	
			}			
		} else {
			contadorIncorrectas++;
			actualizarContador(false, contadorIncorrectas);

			sonidoRespuestaError.setVolume(sonidoRespuestaError.play(),0.2f);
			if (!resolucion.equals("")) {
//				System.out.println("resolucion:" + resolucion);
				if(!resolucion.equals("PREGUNTA26 RESPUESTA.png"))
				loadSettings(" la respuesta correcta es:", resolucion);
			} else {
				//System.out.println("resolucion:" + resolucion + "##");
				//if (contadorEnunciado >= enunciados.size())
					//conclusion();
			}
		}
//		 System.out.println("correctas:" + contadorCorrectas);
//		
//		 System.out.println("incorrectas:" + contadorIncorrectas);
//		
//		 System.out.println("cor:" + contadorEnunciado);
//		
//		 System.out.println("size:" + enunciados.size());

		 if (contadorEnunciado < enunciados.size())
			 cargaPregunta(enunciados.get(contadorEnunciado));
		 else
			 conclusion();
	}

	/**
	 * Muestra la pantalla final de la trivia.
	 */
	private void conclusion() {
		// TODO Auto-generated method stub
		reiniciarStage();
		if (contadorCorrectas > contadorIncorrectas) {
			sonidoTriunfo.play();			
			ganar();
			game.mapMin.asignarPartida(partida, batalla,dificultad,configuracion,true);
			cargarVideoInformativo(eTipoVideo.Victoria);


		} else {
			sonidoDerrota.play();
			cargarVideoInformativo(eTipoVideo.Derrota);

			
		}
		
	}

	@Override
	public void hide() {
		if(sonidoTriunfo!=null){sonidoTriunfo.stop();}
		stage.dispose();
		stage=null;
		camera=null;
		tiledMap.dispose();
		tiledMap=null;
		tiledMapRenderer=null;
		sPregunta.stop();
		sPregunta.dispose();
		sPregunta=null;
		Gdx.input.setInputProcessor(null);
		System.gc();
	}
	
	@Override
	 public void resume() {
		
	};
	

	@Override
	public void dispose() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		stage.act();
		stage.draw();
		Gdx.graphics.requestRendering();
		int mouseX=Gdx.input.getX();
		float width = Gdx.graphics.getWidth();
		float wf = (width / 2) - (w / 2);
		if (Gdx.graphics.isFullscreen()) {
			mouseX=(int) (mouseX-wf);
			
		}
		if(mouseX>=700 && flag2==true){
//			mostrarBotones(false);
			flag2=false;
		}
		else

		if(mouseX<700 && flag2==false)
		{
//			ocultarBotones();
			flag2=true;}


	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.position.x = super.getGameWidth() / 2;
		camera.position.y = super.getGameHeight() / 2;
	}

	private void loadSettings(String pTitulo, String pPath) {

		final Dialogo opcionesScreen = new Dialogo(pTitulo, pPath);
		opcionesScreen.setWidth(600);
		opcionesScreen.setHeight(400);
		opcionesScreen.align(Align.center | Align.top);
		stage.addActor(opcionesScreen);
		opcionesScreen.setPosition(super.gameWidth / 2 - opcionesScreen.getWidth() / 2,
				super.gameHeight / 2 - opcionesScreen.getHeight() / 2);

		opcionesScreen.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (opcionesScreen.getResult() != null){
					if (opcionesScreen.getResult().equals(true)) {
						if (contadorEnunciado >= enunciados.size()){
							conclusion();
						}
					}
				}
			}
		});

		opcionesScreen.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keyCode) {
				if (keyCode == Keys.ENTER)
					if (opcionesScreen.getResult() != null)
						if (opcionesScreen.getResult().equals(true)) {
//							System.out.println("opciones resultado" + opcionesScreen.getValue());
							if (contadorEnunciado >= enunciados.size())
								conclusion();
						}
				return false;

			}
		});

	}
	/**
	 * Metodo que genera y muestra los botones  
	 */
		private void botones(boolean reiniciandoStage) {

			TextButtonStyle styguardar = new TextButtonStyle();
			TextButtonStyle styreiniciar = new TextButtonStyle();
			TextButtonStyle styayuda = new TextButtonStyle();
			TextButtonStyle stydiana = new TextButtonStyle();
			TextButtonStyle styopciones = new TextButtonStyle();
			TextButtonStyle stysalir = new TextButtonStyle();
						
			
			styguardar.font=font;
			styreiniciar.font=font;
			styayuda.font=font;
			stydiana.font=font;
			styopciones.font=font;
			stysalir.font=font;
			System.out.println(skin1.getAtlas());
			
			styguardar.up = skin1.getDrawable("guardarUp");
			styguardar.down = skin1.getDrawable("guardarDown");
			styguardar.over = skin1.getDrawable("guardarOver");
			
			styreiniciar.up = skin1.getDrawable("reiniciarUp");
			styreiniciar.down = skin1.getDrawable("reiniciarDown");
			styreiniciar.over = skin1.getDrawable("reiniciarOver");
			
			styayuda.up = skin1.getDrawable("ayudaUp");
			styayuda.down = skin1.getDrawable("ayudaDown");
			styayuda.over = skin1.getDrawable("ayudaOver");
			
			stydiana.up = skin1.getDrawable("dianaUp");
			stydiana.down = skin1.getDrawable("dianaDown");
			stydiana.over = skin1.getDrawable("dianaOver");
			
			styopciones.up = skin1.getDrawable("ajustesUp");
			styopciones.down = skin1.getDrawable("ajustesDown");
			styopciones.over = skin1.getDrawable("ajustesOver");
			
			stysalir.up = skin1.getDrawable("salirUp");
			stysalir.down = skin1.getDrawable("salirDown");
			stysalir.over = skin1.getDrawable("salirOver");
			
			salir = new TextButton("", stysalir);
			reiniciar = new TextButton("", styreiniciar);
			Opcion = new TextButton("", styopciones);
			ayuda = new TextButton("", styayuda);
					
			ordenarPiezas = new TextButton("", stydiana);
			guardar= new TextButton("", styguardar);
			

			Opcion.addCaptureListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					loadSettings();
					// game1.setScreen(game1.opcScreem);
				}

			});
			salir.addCaptureListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					game.setScreen(game.mapMin);
				}
			});
			reiniciar.addCaptureListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					game.setScreen(game.trivia);
				}
			});
			ayuda.addCaptureListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {

					cargarayuda();
				}
			});

			guardar.addCaptureListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {
					// System.out.println("boton guardar");
					//guardarMinijuego();
				}

			});
			
			salir.setSize(70,70);
			reiniciar.setSize(70,70);
			Opcion.setSize(70,70);
			ordenarPiezas.setSize(70,70);
			ayuda.setSize(70,70);
			guardar.setSize(70,70);
			
		 //  ayuda2.setPosition(720,300);
			int posX = 0;
			 if  (reiniciandoStage)
				 posX = 770;
			 else 
				posX  = 770;
			
	      	     Opcion.setPosition(posX,460);
		          ayuda.setPosition(posX,380);
	      	  reiniciar.setPosition(posX,300);
			      salir.setPosition(posX,220);
		   
			
		      
			      if(Gdx.graphics.isFullscreen())
			      	{
			    	  guardar.setVisible(false);
					  Opcion.setVisible(false);
					  ayuda.setVisible(false);
					  ordenarPiezas.setVisible(false);
					  reiniciar.setVisible(false);
					  salir.setVisible(false);
			        }
		      
			
			reiniciar.setTouchable(Touchable.enabled);
			salir.setTouchable(Touchable.enabled);

			stage.addActor(salir);
			stage.addActor(reiniciar);
			stage.addActor(Opcion);
			stage.addActor(ayuda);
		
			
		}
		/**
		 * Mostrar los botones cuando el mouse se encuentra en un rango X a la posicion de los botones
		 */
		private void mostrarBotones(  boolean reiniciandoStage){	
				if (!reiniciandoStage){
					float duracion=0.3f; 
					
				          Opcion.addAction(Actions.moveTo(770,460, duracion));
					        ayuda.addAction(Actions.moveTo(770,380,duracion));
				        reiniciar.addAction(Actions.moveTo(770,300,duracion));
					        salir.addAction(Actions.moveTo(770,220,duracion));
				}
				        
				        if(Gdx.graphics.isFullscreen())
				        {		        
							
						       Opcion.setVisible(true);
						        ayuda.setVisible(true);
						    reiniciar.setVisible(true);
						        salir.setVisible(true);						        
				        }
			
		}
		
	/**
	 * ocultar los botones cuando el mouse se encuentra en un rango X diferente a la posicion de los botones
	 */
	private void ocultarBotones(){		
			float duracion=0.3f; 			
			 Opcion.addAction(Actions.moveTo(850,460, duracion));
		       ayuda.addAction(Actions.moveTo(850,380,duracion));
		   reiniciar.addAction(Actions.moveTo(850,300,duracion));
		       salir.addAction(Actions.moveTo(850,220, duracion));
			
	        if(Gdx.graphics.isFullscreen())
	        {
	        	Timer.schedule(new Task() {
					@Override
					public void run() {
						  guardar.setVisible(false);
					       Opcion.setVisible(false);
					        ayuda.setVisible(false);
					ordenarPiezas.setVisible(false);
					    reiniciar.setVisible(false);
					        salir.setVisible(false);
					}
				}, 0.2f);	        	
	        }	        	       
		}	

	/**
	 *  muestra un dialogo para cambiar las preferencias
	 */
	private void loadSettings() {
		// Dialogo opcionesScreen = null;
		final Dialogo opcionesScreen = new Dialogo(" Opciones...", this/* , skin3 */);
		opcionesScreen.setWidth(360);
		opcionesScreen.setHeight(400);
		opcionesScreen.align(Align.center | Align.top);

		stage.addActor(opcionesScreen);

		opcionesScreen.setPosition(super.gameWidth / 2 - opcionesScreen.getWidth() / 2,
				super.gameHeight / 2 - opcionesScreen.getHeight() / 2);

		opcionesScreen.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {

				try {
					if (opcionesScreen.getResult().equals(true)) {
						Preferencias(false);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		// Preferencias(false);
	}

	/**
	 *  obtiene las preferencias  del juego
	 * @param pCarga
	 */
	private void Preferencias(boolean pCarga) {

		preferencias = Preferencias.getInstance();
		//preferencias.load();

		if (preferencias.getPreferencia("pantallacompleta")) {
			DisplayMode currentMode = Gdx.graphics.getDisplayMode();
			Gdx.graphics.setFullscreenMode(currentMode);
			if (!pCarga) {
				game.setScreen(game.trivia);
			}
		} else {
			Gdx.graphics.setWindowedMode(w, h);
			if (!pCarga) {
				game.setScreen(game.trivia);
			}
		}

		
//		if (preferencias.getPreferencia("sonido")) {
//			tocarSonido = true;
//		} else {
//			tocarSonido = false;
//		}
	}	
	
public float obtenertiempo(File file) {
	try{
//		System.out.println("file: duracion" + file.getTotalSpace()/200000000000f);
		
    return (float)file.getTotalSpace()/250000000000f; //framrate en bytes de ogg.    
	}
	catch(Exception e){
		System.out.println("error obtenertiempo " + e.getMessage() + " " + e.getLocalizedMessage());
		return 0f;
	}   
}

/**
 * Muestra en un cuadro de dialogo el video de derrota o victoria para el minijuego
 */
private boolean cargarVideoInformativo(eTipoVideo pTipoVideo) {
	
	String vPath = "";

	switch (pTipoVideo) {
	case Victoria:
		vPath = "assets/video/" + batalla +  "/" + minijuego + "v.ogv";		
		break;
		
	case Derrota:			
		vPath = "assets/video/" + batalla +  "/" + minijuego + "d.ogv";		
		break; 
		
	case Ayuda:		
		vPath = "assets/video/"+ batalla +  "/" + minijuego + "a.ogv";
		break;
	
	default:			
		break;
	}					
	try{
		cargarvideo(vPath, "       ", pTipoVideo);
		return true;
	}
	catch(Exception e){
		System.out.println("no se puede cargar el video: " + vPath);
		return false;
	}
}


/**
 * Muestra en un cuadro de dialogo un video
 */
private void cargarvideo(String video, String mensaje,eTipoVideo tipoVideo) {
//	System.out.println("cargarvideo " + tipoVideo.toString());
	
	// TODO apagar los sonidos 
	pauseSonidoPregunta();
	
				
	if (stage.getActors().contains(videoScreen, true)) {
		videoScreen.dispose();
		videoScreen.remove();					
		videoScreen = null;
		gc();
	}
		//System.out.println("agregando el contenedor: " + video);
		//String[] sino  = new String[2];
		
		videoScreen = new Dialogo();
		videoScreen.create( video, tipoVideo , false);
		videoScreen.setWidth(800);
		videoScreen.setHeight(500);
		videoScreen.setPosition(25, 25);
		stage.addActor(videoScreen);

		final eTipoVideo vTipoVideo = tipoVideo;
					
		videoScreen.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				stage.removeListener(this);					
				procesarVideoListener(videoScreen.getResult(), vTipoVideo);
			}
		});
			
		videoScreen.addListener(new InputListener(){				
			@Override
			public boolean keyDown( InputEvent event, int keyCode){	   			
   		//	System.out.println("KeyCode" + keyCode);
   			if (keyCode  == Keys.ENTER || keyCode  == Keys.ESCAPE)
				stage.removeListener(this);
				procesarVideoListener(videoScreen.getResult(), vTipoVideo);
				return false;
   			}
		}); 						
		
}

/**
 * Muestra en un cuadro de dialogo el video de ayuda para el minijuego
 */
private void cargarayuda() {

	try{
		if (!videoAyuda.equals("0")){
//			System.out.println("videoAyuda" + videoAyuda);
			cargarVideoInformativo(eTipoVideo.Ayuda);
		}
		else
			System.out.println("Error, no se ha configurado el video de Ayuda para este minijuego");
	}
	catch(Exception e){
		System.out.println("Error cargando el video: " + videoAyuda + " "+ e.getMessage());		
	}	
}

/**
 * Muestra en un cuadro de dialogo el video de ayuda para el minijuego
 */
private void cargarhistoria() {
	try{	
		if (!videoHistoria.equals("0"))
			cargarvideo(videoHistoria, " Ayuda ", eTipoVideo.Historia);
		else
			System.out.println("Error, no se ha configurado el video de Historia para este minijuego");
	}
	catch(Exception e){
		System.out.println("Error cargando el video: " + videoHistoria);		
	}

}

/**
 * Muestra en un cuadro de dialogo el video de ayuda para el minijuego
 */
private void cargarBiografia() {	
	try{
		if (!videoBiografia.equals("0"))
			cargarvideo(videoBiografia, " Ayuda ", eTipoVideo.Biografia);
		else
			System.out.println("Error, no se ha configurado el video de Biografía para este minijuego");
	}
	catch(Exception e){
		System.out.println("Error cargando el video: " + videoHistoria);	
		}
	}
public void procesarVideoListener(Object pResult, eTipoVideo pTipoVideo){
//	System.out.println("procesando videolistener");
	try {
//System.out.println("videoScreen.getResult(): " + pResult);
		if (pResult.equals(true)) {
			if (pTipoVideo.equals(eTipoVideo.Victoria)){				
				//actualizarPartida();				
				videoScreen.dispose();
				videoScreen.remove();
				game.mapMin.asignarPartida(partida, batalla,dificultad,configuracion,true);				
				game.setScreen(game.mapMin);
							
			}
			else if(pTipoVideo.equals(eTipoVideo.Derrota))
				game.setScreen(game.trivia);													
			else if (pTipoVideo.equals(eTipoVideo.Ayuda))
				cargarayuda();
			else if(pTipoVideo.equals(eTipoVideo.Historia)){
				gc();
		//		reanudarReloj();
				cargarhistoria();					
			}
			else if(pTipoVideo.equals(eTipoVideo.Biografia)){
				gc();
			//	reanudarReloj();
				cargarBiografia();					
			}
				
			
		} else if (pResult.equals(false)){
					
					if (!pTipoVideo.equals(eTipoVideo.Ayuda)&& !pTipoVideo.equals(eTipoVideo.Biografia)&&!pTipoVideo.equals(eTipoVideo.Historia)){				
							game.setScreen(game.mapMin);
						
				//			dispose();
						}
					else{
						resumeSonidoPregunta();
						
					}
					videoScreen.remove();
					videoScreen.dispose();
					videoScreen=null;
					gc();										
		}
		
	} catch (Exception e) {
		System.out.println("Error: " + e.getMessage() + " - " + e.getCause());
		// TODO: handle exception

	}
}	

public void gc(){
//	for (int i =0;i<=10;i++)
//		System.gc();
	}

/**
 * coloca los contadores dependiendo del juego
 * @param cantidad de contadores, 1 si solo es el humano 2 si es debe mostrar además contaddor de la maquina
 * @param humano numero hasta de dos digitos del contador
 * @param maquina numero hasta de dos digitos del contador
 * @author yisheng
 */
public void contador(int cantidad)
{
	
	TextButtonStyle tbs = new TextButtonStyle();
	tbs.font= font;
	tbs.up= skin4.getDrawable("0");

	contadorHumano = new TextButton("", tbs);
	contadorHumano2 = new TextButton("", tbs);
	
	stage.addActor(contadorHumano);
	contadorHumano.setSize(30, 30);
	contadorHumano.setPosition(650, 230);
	
	stage.addActor(contadorHumano2);
	contadorHumano2.setSize(30, 30);
	contadorHumano2.setPosition(680, 230);
	
	if (cantidad>1)
	{
		 contadorMaquina = new TextButton("", tbs);
		 contadorMaquina2 = new TextButton("", tbs);
		 stage.addActor(contadorMaquina);
		 contadorMaquina.setSize(30,30);
		 contadorMaquina.setPosition(650, 130);
		
		 stage.addActor(contadorMaquina2);
		 contadorMaquina2.setSize(30, 30);
		 contadorMaquina2.setPosition(680, 130);
							
	}
}
/**
 * Actualiza el contador de ser necesario
 * @param humano valor booleano que define si es  la maquina o el jugador
 * @param contador valor a actualizar
 */
public void actualizarContador (Boolean humano, int contador)
	{
	   String digito1="";
	   String digito2="";
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.font= font;
		TextButtonStyle tbs2 = new TextButtonStyle();
		tbs2.font= font;   
	   int resto= contador%10;
	   if (contador>9)
	   {
		   digito1+=contador/10;
	   }
	   else
	   {
		   digito1+="0";
	   }
	   
	   digito2+= resto;
	   tbs.up= skin4.getDrawable(digito1);
	   tbs2.up= skin4.getDrawable(digito2);
		
	   
		if(humano)
		{

			contadorHumano.setStyle(tbs);
			contadorHumano2.setStyle(tbs2);
			
		}
		else
		{
			 contadorMaquina.setStyle(tbs);
			 contadorMaquina2.setStyle(tbs2);
				
		}
			
	}

/**
 * Metodo encargado de colocar el nombre al contador en pantalla
 * 
 * @param contador tipo de contador 
 * @param humano si es contador 1 o 2
 */
public void nombreContador(eContadores contador,boolean humano){
	
Texture	texture=null;
Texture texture2=game.getManager().get("assets/Texturas/contadores/pergaminoContador.png");
	
if(contador.equals(eContadores.Correctas)){
texture=game.getManager().get("assets/Texturas/contadores/respCorrectas.png");	
}	
else
if(contador.equals(eContadores.Incorrectas)){
texture=game.getManager().get("assets/Texturas/contadores/respIncorrectas.png");	
}

Image image =new Image(texture);
Image image2 =new Image(texture2);

if(humano)
	{
	image2.setPosition(612, 220);
	image.setPosition(615, 260);
	}
else
	{
	image2.setPosition(612, 120);
	image.setPosition(615, 160);
	}

image.setSize(140, 30);
image2.setSize(137, 80);
stage.addActor(image);
stage.addActor(image2);
image.setZIndex(0);
image2.setZIndex(0);
}

private void playSonidoPregunta(Sound pSound) {
	try {
	SonidoPregunta = pSound;
	
		SonidoPregunta.play(1f);
	}
	catch (Exception e) {
		// TODO: handle exception
	}
}

	private void pauseSonidoPregunta() {
		try {
		SonidoPregunta.pause();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void stopSonidoPregunta() {
		try {
		SonidoPregunta.stop();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void resumeSonidoPregunta() {
		try {
	
		SonidoPregunta.resume();
		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}


	public FileHandle sonidoPregunta(String file){
		
		if(manejadorSonido!=null){
			manejadorSonido=null;
			System.gc();
		}
		try {
			manejadorSonido=new FileHandle(file);
		return manejadorSonido;
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	
	
	
}
