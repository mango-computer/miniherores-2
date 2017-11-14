//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//               ScreenMinijuego.java                       //
//                   Descripcion                            //
//   Clase encargada de controlar logicamente las piezas    //
//   y pantallas para el minijuego numero 1 de la batalla 1 //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      15/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Minijuegos;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.Partida;
import com.jayktec.grafico.Dialogo;
import com.jayktec.grafico.Enums.eColores;
import com.jayktec.grafico.Enums.eEstadoPieza;
import com.jayktec.grafico.Enums.eRespuestaDialgo;
import com.jayktec.grafico.Enums.eTipoDialogo;
import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Enums.eTipoVideo;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Preferencias;
import com.jayktec.grafico.Piezas.ActorExtra;
import com.jayktec.grafico.Piezas.ActorExtra.TipoDeActor;
import com.jayktec.grafico.Piezas.Alfil;
import com.jayktec.grafico.Piezas.Caballo;
import com.jayktec.grafico.Piezas.Casilla;
import com.jayktec.grafico.Piezas.Peon;
import com.jayktec.grafico.Piezas.Pieza;
import com.jayktec.grafico.Piezas.Reina;
import com.jayktec.grafico.Piezas.Rey;
import com.jayktec.grafico.Piezas.Tablero;
import com.jayktec.grafico.Piezas.Torre;
import com.jayktec.grafico.Screen.ScreenManager;
//import com.sun.xml.internal.bind.v2.TODO;


public class MiniJuegoOrdenando extends ScreenManager {
	private eColores colorActivas;
	private Stage stage;
	private TextButton salir;
	private Skin skin;
	private TextureAtlas pack,pack2,packAnimacionReloj,packRelojDerrota;
	
	private BitmapFont font;
	private Preferencias preferencias;
	private boolean tocarSonido = false;
	private boolean tocarMusica = false;

	int w = ((int) super.getGameWidth());
	int h = ((int) super.getGameHeight());
	private Torre TorreBlancaFlancoReina;
	private Torre TorreBlancaFlancoRey;
	private Alfil AlfilBlancaFlancoReina;
	private Alfil AlfilBlancaFlancoRey;
	private Caballo CaballoBlancaFlancoReina;
	private Caballo CaballoBlancaFlancoRey;
	private Reina ReinaBlanca;
	private Rey ReyBlanca;
	private Music cortinaFondo;
	private Peon peon1;
	private Peon peon2;
	private Peon peon3;
	private Peon peon4;
	private Peon peon5;
	private Peon peon6;
	private Peon peon7;
	private Peon peon8;
	private Casilla casilla = new Casilla();
	private Color Turno;
	private String dif;
	private Miniheroes game;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	private Casilla[] casillas;
	private int altoCasilla;
	private int inicioTableroY;
	Tablero tablero = new Tablero();
	Casilla miCasilla = new Casilla();
	private String batalla;
	private String minijuego;
	private String nombrePartida;
	private TextButton Opcion;
	private TextButton reiniciar;
	private float segundos;
	RotateToAction rotateAction = new RotateToAction();
	private TextButton ordenarPiezas;
	private boolean flag = true;
	private String[] pos;
	private int count = 0;
	private String avatar;
	private Sound sonidoDerrota,sonidoTriunfo;
	private Sound movIncorrecto;
	private Dialogo opcionesScreen;
	private TextButton ayuda;
	private TextButton guardar;
	private Label[] letras;
	private Label[] numeros;
	private Skin skin2;
	private boolean flag2=true;
	private String rutaTexturaPersonaje;
	private ImageButton imagenPersonaje;
	private Texture texturaPersonaje,texturaPersonajeAura;
	private TextureRegion[] textureReg;
	private ActorExtra fondoReloj;
	private Skin skin3;
	private String batallaNombre;
	private Image imagenMoneda;
	private Label etiquetaMoneda;
	private int moneda;
	private Dialogo videoScreen;
	private String videoAyuda;
	private String videoHistoria;
	private String videoBiografia;
	private ActorExtra reloj;
	private int rotacionIni=0;
	private int rotacionAcc=0;
	private int tiRotacion=0;
	public boolean mostrarVideos = true;
	public boolean recargaScreen = false;
	private boolean flagReloj=false;
	private Partida partida;
	private Dificultad dificultad;
	private Configuracion configuracion;
	private boolean flagPauseResume=true;
	private boolean flagVideorender=true;
	
	public MiniJuegoOrdenando(Miniheroes pGame) {
		super(pGame);
		game = pGame;
	}

	@Override
	public void show() {
		System.gc();
		game.mapMin.screenfrom(this);
		 preferencias = Preferencias.getInstance();
		 //preferencias.load();
		 
		 
		 
		boolean tocandoMusica=super.playingMusic();
		 super.setVolume(preferencias.getPreferenciav("volumen"));
		if (preferencias.getPreferencia("musica")) {
			if(tocandoMusica)
			{
				super.stopMusic();
			}
			}
		try {
			cortinaFondo = game.getManager().get(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Sonido"));
			batallaNombre=configuracion.GetAtributo(batalla, "Mapa", "mapa", "Historia");
			moneda = Integer.parseInt(partida.getMonedas());
			videoAyuda = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ayuda");
//			System.out.println("batallaNombre" +batallaNombre);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
			
		skin3 = new Skin();
		packAnimacionReloj=new TextureAtlas(Gdx.files.internal("assets/skins/relojAnimacion.pack"));
		packRelojDerrota=new TextureAtlas(Gdx.files.internal("assets/skins/RelojDerrota.pack"));
		sonidoTriunfo = game.getManager().get("assets/Sonidos/sonidoTriunfo.ogg");
		sonidoDerrota = game.getManager().get("assets/Sonidos/sonidoDerrota.mp3");
		movIncorrecto = game.getManager().get("assets/Sonidos/movIncorrecto.ogg");
//		System.out.println("dificultad : " + dif);
		skin2 = new Skin(Gdx.files.internal("uiskin.json"));
		try {
			gestionarDificultad();
		} catch (NumberFormatException e1) {
			segundos=60;
			e1.printStackTrace();
		} catch (Exception e1) {
			segundos=60;
			e1.printStackTrace();
		}
		//System.out.println("Segundos : " + segundos);
		Preferencias(true);
		
		
		packAnimacionReloj=new TextureAtlas(Gdx.files.internal("assets/skins/relojAnimacion.pack"));
		packRelojDerrota=new TextureAtlas(Gdx.files.internal("assets/skins/RelojDerrota.pack"));
		reloj = new ActorExtra(this, TipoDeActor.AGUJA, 0);
		textureReg = new TextureRegion[2];
		fondoReloj= new ActorExtra(this, TipoDeActor.RELOJ, 0);
		fondoReloj.setPack(packAnimacionReloj);
		fondoReloj.setTextureReg(textureReg);
		reloj.setSize(15, 55);
		reloj.setPosition(586, 134);
		reloj.setOrigin(reloj.getWidth() / 2, 3);
		
		fondoReloj.setSize((reloj.getHeight()*2.5f), (reloj.getHeight()*3.5f));
		fondoReloj.setPosition(523,70);
		
		
		rotateAction = new RotateToAction();
		rotateAction.setRotation(360f);
		rotateAction.setDuration(segundos);
		rotateAction.setReverse(true);

		reloj.addAction(rotateAction);
	
		font = new BitmapFont();
		skin = new Skin();
		pack = new TextureAtlas(Gdx.files.internal("assets/skins/minijuegos.pack"));
		skin.addRegions(pack);
		

		casillas = new Casilla[32];
		int i = 0;
		int j = 0;

		int randomStr;
		int randomNum;

		int randomStrTorreFlancoReina = randInt(65, 72);
		int randomNumTorreFlancoReina = randInt(3, 8);

		int randomStrCaballoFlancoReina = randInt(65, 72);
		int randomNumCaballoFlancoReina = randInt(3, 8);

		int randomStrAlfilFlancoReina = randInt(65, 72);
		int randomNumAlfilFlancoReina = randInt(3, 8);

		int randomStrReina = randInt(65, 72);
		int randomNumReina = randInt(3, 8);

		int randomStrRey = randInt(65, 72);
		int randomNumRey = randInt(3, 8);

		int randomStrAlfilFlancoRey = randInt(65, 72);
		int randomNumAlfilFlancoRey = randInt(3, 8);

		int randomStrCaballoFlancoRey = randInt(65, 72);
		int randomNumCaballoFlancoRey = randInt(3, 8);

		int randomStrTorreFlancoRey = randInt(65, 72);
		int randomNumTorreFlancoRey = randInt(3, 8);

		int randomStrPeon1 = randInt(65, 72);
		int randomNumPeon1 = randInt(3, 8);

		int randomStrPeon2 = randInt(65, 72);
		int randomNumPeon2 = randInt(3, 8);

		int randomStrPeon3 = randInt(65, 72);
		int randomNumPeon3 = randInt(3, 8);

		int randomStrPeon4 = randInt(65, 72);
		int randomNumPeon4 = randInt(3, 8);

		int randomStrPeon5 = randInt(65, 72);
		int randomNumPeon5 = randInt(3, 8);

		int randomStrPeon6 = randInt(65, 72);
		int randomNumPeon6 = randInt(3, 8);

		int randomStrPeon7 = randInt(65, 72);
		int randomNumPeon7 = randInt(3, 8);

		int randomStrPeon8 = randInt(65, 72);
		int randomNumPeon8 = randInt(3, 8);

		pos = new String[16];
		/**
		 * Obtener al azar los valores de las posiciones PGN de cada actor
		 */
		pos[0] = Character.toString((char) randomStrTorreFlancoReina) + "" + randomNumTorreFlancoReina;
		pos[1] = Character.toString((char) randomStrTorreFlancoRey) + "" + randomNumTorreFlancoRey;
		pos[2] = Character.toString((char) randomStrAlfilFlancoReina) + "" + randomNumAlfilFlancoReina;
		pos[3] = Character.toString((char) randomStrAlfilFlancoRey) + "" + randomNumAlfilFlancoRey;
		pos[4] = Character.toString((char) randomStrCaballoFlancoReina) + "" + randomNumCaballoFlancoReina;
		pos[5] = Character.toString((char) randomStrCaballoFlancoRey) + "" + randomNumCaballoFlancoRey;
		pos[6] = Character.toString((char) randomStrReina) + "" + randomNumReina;
		pos[7] = Character.toString((char) randomStrRey) + "" + randomNumRey;
		pos[8] = Character.toString((char) randomStrPeon1) + "" + randomNumPeon1;
		pos[9] = Character.toString((char) randomStrPeon2) + "" + randomNumPeon2;
		pos[10] = Character.toString((char) randomStrPeon3) + "" + randomNumPeon3;
		pos[11] = Character.toString((char) randomStrPeon4) + "" + randomNumPeon4;
		pos[12] = Character.toString((char) randomStrPeon5) + "" + randomNumPeon5;
		pos[13] = Character.toString((char) randomStrPeon6) + "" + randomNumPeon6;
		pos[14] = Character.toString((char) randomStrPeon7) + "" + randomNumPeon7;
		pos[15] = Character.toString((char) randomStrPeon8) + "" + randomNumPeon8;
		/**
		 * Validacion de posiciones iniciales
		 */
		for (i = 0; i < pos.length; i++) {
			for (j = 0; j < pos.length; j++) {
				while (pos[i].equals(pos[j]) && i != j) {
					randomStr = randInt(65, 72);
					randomNum = randInt(3, 8);
					pos[j] = Character.toString((char) randomStr) + "" + randomNum;
					// System.out.println(" cambiando "+pos[i]+" - "+pos[j]);
				}
			}
		}
		for (i = pos.length - 1; i >= 0; i--) {
			for (j = pos.length - 1; j >= 0; j--) {
				while (pos[i].equals(pos[j]) && i != j) {
					randomStr = randInt(65, 72);
					randomNum = randInt(3, 8);
					pos[j] = Character.toString((char) randomStr) + "" + randomNum;
					// System.out.println(" cambiando 2 "+pos[i]+" - "+pos[j]);
				}
			}
		}

		Gdx.graphics.getWidth();
		Gdx.graphics.getHeight();
		stage = new Stage();

		camera = new OrthographicCamera();
		camera = (OrthographicCamera) stage.getCamera();
		try {

			tiledMap = new TmxMapLoader().load(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Fondo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		letras = new Label[8];
		numeros = new Label[8];
		botones();
		referencias();
		TorreBlancaFlancoReina = new Torre(eColores.Blancas, pos[0], 10, 1, this);
		TorreBlancaFlancoRey = new Torre(eColores.Blancas, pos[1], 11, 1, this);
		AlfilBlancaFlancoReina = new Alfil(eColores.Blancas, pos[2], 8, 1, this);
		AlfilBlancaFlancoRey = new Alfil(eColores.Blancas, pos[3], 9, 1, this);
		CaballoBlancaFlancoReina = new Caballo(eColores.Blancas, pos[4], 14, 1, this);
		CaballoBlancaFlancoRey = new Caballo(eColores.Blancas, pos[5], 15, 1, this);
		ReinaBlanca = new Reina(eColores.Blancas, pos[6], 12, 1, this);
		
		ReyBlanca = new Rey(eColores.Blancas, pos[7], 13, 1, this);
		ReyBlanca.setAvatar(avatar);
		ReyBlanca.cambiarEstadoPieza(eEstadoPieza.Esperando);
		
		peon1 = new Peon(eColores.Blancas, pos[8], 0, 1, this);
		peon2 = new Peon(eColores.Blancas, pos[9], 1, 1, this);
		peon3 = new Peon(eColores.Blancas, pos[10], 2, 1, this);
		peon4 = new Peon(eColores.Blancas, pos[11], 3, 1, this);
		peon5 = new Peon(eColores.Blancas, pos[12], 4, 1, this);
		peon6 = new Peon(eColores.Blancas, pos[13], 5, 1, this);
		peon7 = new Peon(eColores.Blancas, pos[14], 6, 1, this);
		peon8 = new Peon(eColores.Blancas, pos[15], 7, 1, this);
		try {
			rutaTexturaPersonaje = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Personaje");
			videoBiografia = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Biografia");
			videoHistoria = configuracion.GetAtributo(batalla, "Mapa", "mapa", "Biografia");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		texturaPersonaje=game.getManager().get(rutaTexturaPersonaje);
		texturaPersonajeAura=game.getManager().get(rutaTexturaPersonaje.split("/")[0]+"/"+rutaTexturaPersonaje.split("/")[1]+"/Aura/"+rutaTexturaPersonaje.split("/")[2]);

		ImageButtonStyle style=new ImageButtonStyle();
		style.up=new TextureRegionDrawable(new TextureRegion(texturaPersonaje));
		style.over=new TextureRegionDrawable(new TextureRegion(texturaPersonajeAura));
		imagenPersonaje=new ImageButton(style);
		imagenPersonaje.setPosition(655, 70);
		imagenPersonaje.setSize(120, 230);
		imagenPersonaje.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {				
				cargarBiografia();
			}
		});
		
		
		Texture textureMoneda=game.getManager().get("assets/Texturas/moneda.png");	
		imagenMoneda=new Image(textureMoneda);
		imagenMoneda.setPosition(10, 500);
		imagenMoneda.setSize(48, 48);
		imagenMoneda.setTouchable(Touchable.disabled);
		//stage.addActor(imagenMoneda);
		
		etiquetaMoneda = new Label("x" + String.valueOf(moneda), skin2);
		etiquetaMoneda.setPosition(65, 512);
		etiquetaMoneda.setFontScale(1);
		//stage.addActor(etiquetaMoneda);
		stage.addActor(peon1);
		stage.addActor(peon2);
		stage.addActor(peon3);
		stage.addActor(peon4);
		stage.addActor(peon5);
		stage.addActor(peon6);
		stage.addActor(peon7);
		stage.addActor(peon8);
		stage.addActor(AlfilBlancaFlancoRey);
		stage.addActor(AlfilBlancaFlancoReina);
		stage.addActor(ReinaBlanca);
		stage.addActor(ReyBlanca);
		stage.addActor(fondoReloj);
		stage.addActor(reloj);
		stage.addActor(CaballoBlancaFlancoRey);
		stage.addActor(CaballoBlancaFlancoReina);
		stage.addActor(imagenPersonaje);
		stage.addActor(TorreBlancaFlancoReina);
		stage.addActor(TorreBlancaFlancoRey);
		Texture texture=game.getManager().get("assets/Texturas/cuadrotransparente.png");
		Image imagen=new Image(texture);
		imagen.setPosition(520, 20);
		imagen.setSize(247, 475);
		stage.addActor(imagen);
		imagen.setZIndex(0);
		stage.addActor(salir);
		nombreBatalla();
		nombrePersonaje();
		this.setTurno(Color.WHITE); // toca jugar a ls blancas
		this.habilitarPiezas(eColores.Blancas);
		Gdx.input.setInputProcessor(stage);
//		System.out.println("recarga : "+recargaScreen);
		if (recargaScreen==false){
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
		}
		
	recargaScreen = false;	

		if (mostrarVideos){
			reloj.clearActions();
			flagReloj=true;
			cargarayuda();
			mostrarVideos = false;
		}
	

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
		int mouseX=Gdx.input.getX();
		Gdx.input.getY();
		float width = Gdx.graphics.getWidth();
		float wf = (width / 2) - (w / 2);
		
		
		if(videoScreen!=null){
			
		if(videoScreen.isPlaying()){
			
			flagVideorender=true;
			cortinaFondo.stop();
			
		}else if(videoScreen.isPaused()) {
			if(flagVideorender){
				System.out.println("stop");
			cortinaFondo.stop();
			flagVideorender=false;
			}
		}
		else
			if(tocarMusica){
				flagVideorender=true;
				cortinaFondo.play();
				
				}
		}
		else{
			if(tocarMusica){
				cortinaFondo.play();
				cortinaFondo.setLooping(true);
				System.out.println("tocando musica");
				}
		}
		
		
		
		if (Gdx.graphics.isFullscreen()) {
			mouseX=(int) (mouseX-wf);
			
		}
		Gdx.graphics.requestRendering();
		if (count == 3) {
			ordenarPiezas.setTouchable(Touchable.disabled);
		}

		if ((int) rotateAction.getDuration() == (int) rotateAction.getTime()) {
			if (flag == true) {
				try {
					cortinaFondo.stop();
					//cortinaFondo = null;
					if (tocarSonido){
						System.out.println("estoy entrando en el sonido de derrota");
						sonidoDerrota.play();
					}	
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				fondoReloj.setPack(packRelojDerrota);
				perder();
				
				
			}
			flag = false;
		} else

		
		if (comprobarPiezas() == true && flag == true&& game.getScreen()== game.ordenando) {
			flag=false;
			reloj.clearActions();
			
			try {
			
				cortinaFondo.stop();
				//cortinaFondo = null;
				if (tocarSonido){
					System.out.println("estoy entrando en el sonido de victoria"+delta);
						sonidoTriunfo.play();
					

				}	

				ganar();
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("fallo parando la cortina de fondo" + e.getMessage());
			}
			
			
			
			
			

		}

		if(mouseX>=740 && flag2==true){
//			mostrarBotones();
			flag2=false;
		}
		else

		if(mouseX<740 && flag2==false)
		{
//			ocultarBotones();
			flag2=true;}
		
	}

	/**
	 * Procedimiento para actualizar una partida donde se gano el minijuego
	 * 
	 * @return Devuelver verdadero si actualizo sin error o falso en caso
	 *         contrario
	 * @author yisheng
	 */
	private Boolean actualizarPartida() {
		
		int nroMinijuego = Integer.parseInt(minijuego.substring(9));
		int nroBatalla = Integer.parseInt(batalla.substring(7));
		if (nroMinijuego == 5) {
			nroBatalla++;
			nroMinijuego = 1;
		} else {
			nroMinijuego++;
		}

		try {
//			partida.ActualizarAtributo(nombrePartida, "Batalla", "Batalla" + "7"); // hack de Vladimir
//			partida.ActualizarAtributo("Vladimir", "Minijuego", "Minijuego" + 35);// comentar hasta antes del return true;
//			
			int batallaPartida = Integer.parseInt(partida.getBatalla().substring(7));
			Integer.parseInt(partida.getMinijuego().substring(9));

			if (batallaPartida < nroBatalla) {
				partida.setBatalla( "Batalla" + nroBatalla);
				partida.setMinijuego("Minijuego" + nroMinijuego);
			} else if (batallaPartida == nroBatalla) {
				partida.setMinijuego("Minijuego" + nroMinijuego);
			}
			
			return partida.ActualizarPartida();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public void hide() {
		
		flagVideorender=true;
		if(sonidoTriunfo!=null){sonidoTriunfo.stop();}
		
		if (cortinaFondo != null) {
		cortinaFondo.stop();		
		}
		rotacionIni=0;
		rotacionAcc=0;
		tiRotacion=0;
		reloj.clearActions();
		flagReloj=false;
		skin3.dispose();
		flag = true;
		skin.dispose();
		pack.dispose();
		font.dispose();
		tiledMap.dispose();
		stage.dispose();
		// worldTimer = 100;
		count = 0;
		Gdx.input.setInputProcessor(null);
gc();
	}

	/**
	 * Metodo para obtener un valor entero al azar entre el rango de los
	 * parametros min y max
	 * 
	 * @param min
	 *            minimo valor numerico que se genera
	 * @param max
	 *            maximo valor numerico que se genera
	 * @return numeros al azar
	 */
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;

	}

	/**
	 * habilita las piezas segun sea el turno
	 * 
	 * @param pColor
	 */
	public void habilitarPiezas(eColores pColor) {

		if (colorActivas != pColor) {
			colorActivas = pColor;
			for (Actor miActor : stage.getActors()) {
				if (pColor == eColores.Blancas) {
					if (miActor.getColor().equals(Color.WHITE)) {
						miActor.setTouchable(Touchable.enabled);
					} else {
						miActor.setTouchable(Touchable.disabled);
					}
				} else if (miActor.getColor().equals(Color.BLACK)) {
					miActor.setTouchable(Touchable.enabled);
				} else {
					miActor.setTouchable(Touchable.disabled);
				}
			}
		}
	}

	/**
	 * Metodo para iniciar el turno del 1er jugador
	 * 
	 * @param pTurno
	 *            color de la pieza que tiene que ingresar
	 */
	public void setTurno(Color pTurno) {
		Turno = pTurno;
	}

	/**
	 * Funcion que retorna el color de la pieza que le toca jugar
	 * 
	 * @return color que toca jugar
	 */
	public Color getTurno() {
		return Turno;
	}

	/**
	 * Metodo para cambiar el turno de cada jugador
	 **/

	public void CambiarTurno() {
		if (Turno == Color.WHITE) {
			habilitarPiezas(eColores.Negras);
			Turno = Color.BLACK;
		} else {
			habilitarPiezas(eColores.Blancas);
			Turno = Color.WHITE;
		}
	}

	/**
	 * Metodo para asignar una casilla a una pieza
	 **/

	@Override
	public void setCasilla(String pPgn, Pieza pPieza, int pInd) {

		casillas[pInd] = new Casilla();
		casillas[pInd].setPieza(pPieza);
		casillas[pInd].setPgn(pPgn);
	}

	/**
	 * Funcion que retorna la pieza que ocupa una casilla
	 * 
	 * @param pPgn
	 *            pgn de la casilla ocupada
	 * @return pieza que ocupa la casilla
	 */
	public Pieza casillaOcupada(String pPgn) {

		for (int i = 0; i <= 15; i++) {

			// System.out.println( "pgn casilla" + pPgn);
			// System.out.println( "i casilla" + i);
try {
	if (casillas[i].getPgn().matches(pPgn)) {
		Pieza piezat = casillas[i].getPieza();

		// casillas[i] = new Casilla();
		return piezat;
	}
} catch (Exception e) {
	// TODO: handle exception
}
		
		}

		return null;

	}

	public void borraCasilla(int pInd) {
		casillas[pInd] = new Casilla();
	}

	/**
	 * comprueba si todas las piezas esta ordenadas correctamente
	 * 
	 * @return
	 */
	public boolean comprobarPiezas() {
		inicioTableroY = tablero.GetInicioY();
		altoCasilla = miCasilla.GetAltoCasilla();
		int p1 = (int) peon1.getY();
		int p2 = (int) peon2.getY();
		int p3 = (int) peon3.getY();
		int p4 = (int) peon4.getY();
		int p5 = (int) peon5.getY();
		int p6 = (int) peon6.getY();
		int p7 = (int) peon7.getY();
		int p8 = (int) peon8.getY();
		int t1 = (int) TorreBlancaFlancoReina.getY();
		int c1 = (int) CaballoBlancaFlancoReina.getY();
		int a1 = (int) AlfilBlancaFlancoReina.getY();
		int r1 = (int) ReinaBlanca.getY();
		int r2 = (int) ReyBlanca.getY();
		int a2 = (int) AlfilBlancaFlancoRey.getY();
		int c2 = (int) CaballoBlancaFlancoRey.getY();
		int t2 = (int) TorreBlancaFlancoRey.getY();
		int posPeon = inicioTableroY + altoCasilla;
		int posPiezas = inicioTableroY;
		if (p1 == posPeon && p2 == posPeon && p3 == posPeon && p4 == posPeon && p5 == posPeon && p6 == posPeon
				&& p7 == posPeon && p8 == posPeon) {
			if (t1 == posPiezas && c1 == posPiezas && a1 == posPiezas && r1 == posPiezas && r2 == posPiezas
					&& a2 == posPiezas && c2 == posPiezas && t2 == posPiezas) {

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean ValidarMovimiento(Pieza pPieza, int pJuego, int mouseX, int mouseY, float actorX, float actorY,
			float rangoX, float rangoY, float posX, float posY) {

		int juego = pJuego;

		if (pPieza.getColor().equals(getTurno())) {
//			System.out.println("pieza seleccionada:" + pPieza.getName() + " color: " + pPieza.getColor());
			if (pPieza.getEstadoPieza() != eEstadoPieza.Seleccionado) {
//				System.out.println("pieza seleccionada2:" + pPieza.getName() + " color2: " + pPieza.getColor());
				cambiarEstadoEsperando();
				pPieza.cambiarEstadoPieza(eEstadoPieza.Seleccionado);
				// pPieza.setZIndex(3);
				return false;
			} else if (pPieza.getEstadoPieza() == eEstadoPieza.Seleccionado) {
				tablero.XY2pgn((int) actorX, (int) actorY);
				String pgn = tablero.XY2pgn((int) posX, (int) posY);

//				double distancia = Math.sqrt((Math.pow(posX - actorX, 2)) + Math.pow(posY - actorY, 2));
				casilla.GetAnchoCasilla();
				casilla.GetAltoCasilla();
				if (posX >= tablero.GetInicioX() && posX <= tablero.GetFinX()) {
					if (posY >= tablero.GetInicioY() && posY <= tablero.GetFinY()) {
						Pieza piezat = casillaOcupada(pgn);

						if (piezat != null) {
							if (piezat.getColor().equals(pPieza.getColor())) {
								// hacer click en una casilla ocupada por una
								// pieza del mismo color
								pPieza.cambiarEstadoPieza(eEstadoPieza.Esperando);
								piezat.cambiarEstadoPieza(eEstadoPieza.Seleccionado);
								return false;
							}
						}

						if (pPieza.getTipoPieza() == eTipoPieza.Torre) {
							if (juego == 0) {

							} else

							if (((Torre) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {

								if (piezat == null) {
									setCasilla(pgn, pPieza, pPieza.getInd());
									return true;
								}
							}
						}

						if (pPieza.getTipoPieza() == eTipoPieza.Alfil) {
							if (juego == 0) {

							} else

							if (((Alfil) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {
								if (piezat == null) {
									setCasilla(pgn, pPieza, pPieza.getInd());
									return true;
								}
							}
						}

						if (pPieza.getTipoPieza() == eTipoPieza.Reina) {
							if (juego == 0) {

							} else

							if (((Reina) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {
								if (piezat == null) {
									setCasilla(pgn, pPieza, pPieza.getInd());
									return true;
								}
							}
						}

						if (pPieza.getTipoPieza() == eTipoPieza.Rey) {
							if (juego == 0) {

							} else if (((Rey) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {
								if (piezat == null) {
									setCasilla(pgn, pPieza, pPieza.getInd());
									return true;
								}
							}
						}
						if (pPieza.getTipoPieza() == eTipoPieza.Caballo) {
							if (juego == 0) {

							} else

							if (((Caballo) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {
								if (piezat == null) {
									setCasilla(pgn, pPieza, pPieza.getInd());
									return true;
								}
							}
						}
						if (pPieza.getTipoPieza() == eTipoPieza.Peon) {
							if (juego == 0) {

							} else

							if (((Peon) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {

								setCasilla(pgn, pPieza, pPieza.getInd());

								return true;

							}
						}
					} else {
						System.out.println("Error, toque fuera de y");
						cambiarEstadoEsperando();
						return false;
					}

				} else {
					System.out.println("Error, toque fuera de ");
					cambiarEstadoEsperando();
					return false;
				}
			}
			////
		}
		
		if (tocarSonido)
			movIncorrecto.setVolume(movIncorrecto.play(),0.2f);
		else{
		ArrayList<String> vMensaje = new ArrayList<String>();
		vMensaje.add("no se puede jugar");
			mostrarDialogo(eTipoDialogo.Aceptar, true, vMensaje);
		}
		
		return false;
	}

//	private void cambiarEstadoPieza(Pieza pPieza, eEstadoPieza pEstado) {
//		pPieza.setEstadoPieza(pEstado);
//	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.position.x = super.getGameWidth() / 2;
		camera.position.y = super.getGameHeight() / 2;
	}

	@Override
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * cambia el estado de las piezas a esperando, para poder seleccionarlas
	 */
	private void cambiarEstadoEsperando() {

		for (Actor miActor : stage.getActors()) {
			if (miActor.getName() != "boton" && miActor.getName() != "tiempo" && miActor.getName() != "deshacer") {
				try {
					((Pieza) miActor).setEstadoPieza(eEstadoPieza.Esperando);
					((Pieza) miActor).cambiarEstadoPieza(eEstadoPieza.Esperando);
				} catch (Exception ex) {

				}
			}
		}

	}

	public void asignarPartida(String batalla, String minijuego, Partida partida, Dificultad dificultad,Configuracion configuracion) {
		//this.partida = new Partida();
		try {
			dif = partida.getDificultad();
			avatar = partida.getPersonaje();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.batalla = batalla;
		this.minijuego = minijuego;
		this.nombrePartida = partida.getNombre();
		this.partida=partida;
		this.dificultad= dificultad;
		this.configuracion=configuracion;
	}

	@Override
	public void pause() {
		if (videoScreen==null){
			pausarReloj();
			flagPauseResume=false;
			}
		else{
			
			flagPauseResume=true;
		}
		
		
		if (cortinaFondo != null) {
			cortinaFondo.pause();
		}

	}

	@Override
	public void resume() {
		if(flagPauseResume==false)
		{
			if (videoScreen==null)
				{
					reanudarReloj();
				}
		}
		if (videoScreen==null){
		if (cortinaFondo != null) {
			cortinaFondo.play();
			cortinaFondo.setLooping(true);
		}
		}
		super.resume();
	}

	/**
	 * Muestra los botones en la pantalla
	 */

	private void botones() {
		
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
		
		styguardar.up = skin.getDrawable("guardarUp");
		styguardar.down = skin.getDrawable("guardarDown");
		styguardar.over = skin.getDrawable("guardarOver");
		
		styreiniciar.up = skin.getDrawable("reiniciarUp");
		styreiniciar.down = skin.getDrawable("reiniciarDown");
		styreiniciar.over = skin.getDrawable("reiniciarOver");
		
		styayuda.up = skin.getDrawable("ayudaUp");
		styayuda.down = skin.getDrawable("ayudaDown");
		styayuda.over = skin.getDrawable("ayudaOver");
		
		stydiana.up = skin.getDrawable("dianaUp");
		stydiana.down = skin.getDrawable("dianaDown");
		stydiana.over = skin.getDrawable("dianaOver");
		
		styopciones.up = skin.getDrawable("ajustesUp");
		styopciones.down = skin.getDrawable("ajustesDown");
		styopciones.over = skin.getDrawable("ajustesOver");
		
		stysalir.up = skin.getDrawable("salirUp");
		stysalir.down = skin.getDrawable("salirDown");
		stysalir.over = skin.getDrawable("salirOver");
		
		salir = new TextButton("", stysalir);
		reiniciar = new TextButton("", styreiniciar);
		Opcion = new TextButton("", styopciones);
		ayuda = new TextButton("", styayuda);
		ordenarPiezas = new TextButton("", stydiana);
		guardar= new TextButton("", styguardar);
		
		ordenarPiezas.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ordenarPiezas();
				count++;

			}

		});

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

				game.setScreen(game.ordenando);
			}
		});

		ayuda.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
			
				
				cargarayuda();
			}
		});
		salir.setSize(70,70);
		reiniciar.setSize(70,70);
		Opcion.setSize(70,70);
		ordenarPiezas.setSize(70,70);
		ayuda.setSize(70,70);
		guardar.setSize(70,70);
		
	 //  ayuda2.setPosition(720,300);
	
	   
		  Opcion.setPosition(780,420);
          ayuda.setPosition(780,340);
  ordenarPiezas.setPosition(780,260);
  	  reiniciar.setPosition(780,180);
	      salir.setPosition(780,100);
	
	
		      if(Gdx.graphics.isFullscreen())
		      	{
				  Opcion.setVisible(false);
				  ayuda.setVisible(false);
				  ordenarPiezas.setVisible(false);
				  reiniciar.setVisible(false);
				  salir.setVisible(false);
		        }
	      
		
		reiniciar.setTouchable(Touchable.enabled);
		salir.setTouchable(Touchable.enabled);

		stage.addActor(ordenarPiezas);
		stage.addActor(salir);
		stage.addActor(reiniciar);
		stage.addActor(Opcion);
//		stage.addActor(guardar);
		stage.addActor(ayuda);
		//stage.addActor(ayuda2);
		
	}

	private void loadSettings() {
		// Dialogo opcionesScreen = null;
		final Dialogo opcionesScreen = new Dialogo(" Opciones...",this/* , skin3 */);
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
					setVolumen();
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		});

		// Preferencias(false);
	}

	private void Preferencias(boolean pCarga) {

		preferencias = Preferencias.getInstance();
		//preferencias.load();

		if (preferencias.getPreferencia("pantallacompleta")) {
			DisplayMode currentMode = Gdx.graphics.getDisplayMode();
			Gdx.graphics.setFullscreenMode(currentMode);
			if (!pCarga) {
				reiniciar();
//				game.setScreen(game.ordenando);
			}
		} else {

			Gdx.graphics.setWindowedMode(w, h);
			if (!pCarga) {
				reiniciar();
//				game.setScreen(game.ordenando);
			}
		}

		tocarMusica = preferencias.getPreferencia("musica");
		if(videoScreen!=null){
			
		if(videoScreen.isPlaying()){
			
			flagVideorender=true;
			cortinaFondo.stop();
			
		}else if(videoScreen.isPaused()) {
			if(flagVideorender){
				System.out.println("stop");
			cortinaFondo.stop();
			flagVideorender=false;
			}
		}
		else
			if(tocarMusica){
				flagVideorender=true;
				cortinaFondo.play();
				
				}
		}
		
		if (preferencias.getPreferencia("sonido")) {			
			tocarSonido = true;
		} else {
			tocarSonido = false;
		}
		
		
	}

	/**
	 * Metodo encargado de colocar el tiempo en el reloj segun sea la dificultad
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */


	public void gestionarDificultad() throws NumberFormatException, Exception {
	
		segundos= Integer.parseInt(dificultad.getReloj());


	}

	/**
	 * Metodo para que las piezas se muevan hacia la casilla donde pertenecen
	 */
	private void ordenarPiezas() {
		flag = false;
		ordenarPiezas.setTouchable(Touchable.disabled);
		int TorreBlancaFlancoReinaXY[] = tablero.Pgn2XY("A1");
		float TorreBlancaFlancoReinaX = TorreBlancaFlancoReinaXY[0];
		float TorreBlancaFlancoReinaY = TorreBlancaFlancoReinaXY[1];

		int CaballoBlancaFlancoReinaXY[] = tablero.Pgn2XY("B1");
		float CaballoBlancaFlancoReinaX = CaballoBlancaFlancoReinaXY[0];
		float CaballoBlancaFlancoReinaY = CaballoBlancaFlancoReinaXY[1];

		int AlfilBlancaFlancoReinaXY[] = tablero.Pgn2XY("C1");
		float AlfilBlancaFlancoReinaX = AlfilBlancaFlancoReinaXY[0];
		float AlfilBlancaFlancoReinaY = AlfilBlancaFlancoReinaXY[1];

		int ReinaBlancaXY[] = tablero.Pgn2XY("D1");
		float ReinaBlancaX = ReinaBlancaXY[0];
		float ReinaBlancaY = ReinaBlancaXY[1];

		int ReyBlancaXY[] = tablero.Pgn2XY("E1");
		float ReyBlancaX = ReyBlancaXY[0];
		float ReyBlancaY = ReyBlancaXY[1];

		int AlfilBlancaFlancoReyXY[] = tablero.Pgn2XY("F1");
		float AlfilBlancaFlancoReyX = AlfilBlancaFlancoReyXY[0];
		float AlfilBlancaFlancoReyY = AlfilBlancaFlancoReyXY[1];

		int CaballoBlancaFlancoReyXY[] = tablero.Pgn2XY("G1");
		float CaballoBlancaFlancoReyX = CaballoBlancaFlancoReyXY[0];
		float CaballoBlancaFlancoReyY = CaballoBlancaFlancoReyXY[1];

		int TorreBlancaFlancoReyXY[] = tablero.Pgn2XY("H1");
		float TorreBlancaFlancoReyX = TorreBlancaFlancoReyXY[0];
		float TorreBlancaFlancoReyY = TorreBlancaFlancoReyXY[1];

		int peon1XY[] = tablero.Pgn2XY("A2");
		float peon1X = peon1XY[0];
		float peon1Y = peon1XY[1];

		int peon2XY[] = tablero.Pgn2XY("B2");
		float peon2X = peon2XY[0];
		float peon2Y = peon2XY[1];

		int peon3XY[] = tablero.Pgn2XY("C2");
		float peon3X = peon3XY[0];
		float peon3Y = peon3XY[1];

		int peon4XY[] = tablero.Pgn2XY("D2");
		float peon4X = peon4XY[0];
		float peon4Y = peon4XY[1];

		int peon5XY[] = tablero.Pgn2XY("E2");
		float peon5X = peon5XY[0];
		float peon5Y = peon5XY[1];

		int peon6XY[] = tablero.Pgn2XY("F2");
		float peon6X = peon6XY[0];
		float peon6Y = peon6XY[1];

		int peon7XY[] = tablero.Pgn2XY("G2");
		float peon7X = peon7XY[0];
		float peon7Y = peon7XY[1];

		int peon8XY[] = tablero.Pgn2XY("H2");
		float peon8X = peon8XY[0];
		float peon8Y = peon8XY[1];

		TorreBlancaFlancoReina.addAction(Actions.moveTo(TorreBlancaFlancoReinaX, TorreBlancaFlancoReinaY, 2f));
		CaballoBlancaFlancoReina.addAction(Actions.moveTo(CaballoBlancaFlancoReinaX, CaballoBlancaFlancoReinaY, 2f));
		AlfilBlancaFlancoReina.addAction(Actions.moveTo(AlfilBlancaFlancoReinaX, AlfilBlancaFlancoReinaY, 2f));
		ReinaBlanca.addAction(Actions.moveTo(ReinaBlancaX, ReinaBlancaY, 2f));
		ReyBlanca.addAction(Actions.moveTo(ReyBlancaX, ReyBlancaY, 2f));
		TorreBlancaFlancoRey.addAction(Actions.moveTo(TorreBlancaFlancoReyX, TorreBlancaFlancoReyY, 2f));
		CaballoBlancaFlancoRey.addAction(Actions.moveTo(CaballoBlancaFlancoReyX, CaballoBlancaFlancoReyY, 2f));
		AlfilBlancaFlancoRey.addAction(Actions.moveTo(AlfilBlancaFlancoReyX, AlfilBlancaFlancoReyY, 2f));

		peon1.addAction(Actions.moveTo(peon1X, peon1Y, 2f));
		peon2.addAction(Actions.moveTo(peon2X, peon2Y, 2f));
		peon3.addAction(Actions.moveTo(peon3X, peon3Y, 2f));
		peon4.addAction(Actions.moveTo(peon4X, peon4Y, 2f));
		peon5.addAction(Actions.moveTo(peon5X, peon5Y, 2f));
		peon6.addAction(Actions.moveTo(peon6X, peon6Y, 2f));
		peon7.addAction(Actions.moveTo(peon7X, peon7Y, 2f));
		peon8.addAction(Actions.moveTo(peon8X, peon8Y, 2f));

		Timer.schedule(new Task() {
			@Override
			public void run() {
				desordenarPiezas();

			}
		}, 5f);

	}

	/**
	 * Metodo para que las piezas vuelvan a su posicion original "desordenada"
	 */
	private void desordenarPiezas() {
		TorreBlancaFlancoReina.setPosPgn(pos[0]);
		TorreBlancaFlancoRey.setPosPgn(pos[1]);
		AlfilBlancaFlancoReina.setPosPgn(pos[2]);
		AlfilBlancaFlancoRey.setPosPgn(pos[3]);
		CaballoBlancaFlancoReina.setPosPgn(pos[4]);
		CaballoBlancaFlancoRey.setPosPgn(pos[5]);
		ReinaBlanca.setPosPgn(pos[6]);
		ReyBlanca.setPosPgn(pos[7]);
		peon1.setPosPgn(pos[8]);
		peon2.setPosPgn(pos[9]);
		peon3.setPosPgn(pos[10]);
		peon4.setPosPgn(pos[11]);
		peon5.setPosPgn(pos[12]);
		peon6.setPosPgn(pos[13]);
		peon7.setPosPgn(pos[14]);
		peon8.setPosPgn(pos[15]);
		casillas=new Casilla[32];
		setCasillaDesordenando();
		
		Timer.schedule(new Task() {
			@Override
			public void run() {
				flag = true;
				ordenarPiezas.setTouchable(Touchable.enabled);
			}
		}, .3f);

	}

	private void mostrarDialogo(eTipoDialogo pTipoDialogo, boolean pDerrota, ArrayList<String> pMensaje) {

		
		if (pTipoDialogo == eTipoDialogo.Mensaje) 
				{
					ArrayList<String> mensaje = new ArrayList<String>();
						if (pDerrota) 
							{
								mensaje.add("El juego ha finalizado");
								mensaje.add("Deseas reintentarlo?");
								opcionesScreen = new Dialogo("Informacion ", mensaje, "");
							}
				}
		else if(pTipoDialogo == eTipoDialogo.Aceptar){
				if(pMensaje==null){
					ArrayList<String> mensaje = new ArrayList<String>();					
					mensaje.add("Felicidades");
					mensaje.add("Has ganado");
					opcionesScreen = new Dialogo("Informacion ", mensaje);				
				}
				else {
					opcionesScreen = new Dialogo("Informacion ", pMensaje);			
				}
		}
			opcionesScreen.setWidth(180);
			opcionesScreen.setHeight(200);
			opcionesScreen.align(Align.center | Align.top);
			stage.addActor(opcionesScreen);
			opcionesScreen.setPosition(super.gameWidth / 2 - opcionesScreen.getWidth() / 2,
					super.gameHeight - opcionesScreen.getHeight() / 2);

			if (pMensaje == null){										
				opcionesScreen.addListener(new ChangeListener() {
					@Override
					public void changed(ChangeEvent event, Actor actor) {
	
						try {
	
							if (opcionesScreen.getResult() != null) {
//								System.out.println("opcionesScreen.getValue()" + opcionesScreen.getValue());
								procesarResultadoDialogo(opcionesScreen.getValue());
	
							}
						} catch (Exception e) {
							System.out.println("error" + e.getMessage());
							procesarResultadoDialogo(0);
						}
					}
				});
				opcionesScreen.addListener(new InputListener(){				
			   		@Override
			   		public boolean keyDown( InputEvent event, int keyCode)
			   			{	   			
//			   			System.out.println("KeyCode" + keyCode);
			   			if (keyCode  == Keys.ENTER)
				   			if (opcionesScreen.getResult()!=null){
				   				//if (opcionesScreen.getResult().equals(true))
//				   				System.out.println("opciones resultado"+ opcionesScreen.getValue());
				   					procesarResultadoDialogo(opcionesScreen.getValue());
				   			
				   			}
			   				return false;
				   			
			   			}
					});
			}
	}

	private void procesarResultadoDialogo(Object pResp) {
		try {
			eRespuestaDialgo vResp = (eRespuestaDialgo) pResp;
			if (vResp == eRespuestaDialgo.Si) {
				game.setScreen(game.ordenando);
			} else if (vResp == eRespuestaDialgo.No) {
				game.setScreen(game.mapMin);
			}
			else if (vResp == eRespuestaDialgo.Aceptar) {
				actualizarPartida();
				game.mapMin.asignarPartida(partida, batalla, dificultad,configuracion,true);				
				game.setScreen(game.mapMin);
			}
		} catch (Exception e) {
			e.printStackTrace();
			game.setScreen(game.mapMin);
		}

	}

	private void referencias() {
		for (int i = 0; i <= 7; i++) {
			letras[i] = new Label((Character.toString((char) (65 + i)).toLowerCase()), skin2);
			numeros[i] = new Label(Character.toString((char) (49 + i)), skin2);
		}

		int vDistaciaLetrasX = (int) (casilla.GetAnchoCasilla() * 2.4);
		int vDistaciaLetrasY = (int) (casilla.GetAltoCasilla() * 0.325);


			for (int i = 0; i <= 7; i++) {
				letras[i].setPosition((vDistaciaLetrasX - 75) + i * casilla.GetAnchoCasilla(), 5);
				letras[i].setFontScale(vDistaciaLetrasY / 10);
				numeros[i].setPosition(12, (vDistaciaLetrasX - 100) + i * casilla.GetAnchoCasilla());
				numeros[i].setFontScale(vDistaciaLetrasY / 10);
			}
		
		
		for (int i = 0; i < 8; i++) {
			stage.addActor(numeros[i]);
		}

		for (int i = 0; i < 8; i++) {
			stage.addActor(letras[i]);
		}
		// stage.addActor(letras[0]);

	}
	
	/**
	 * Mostrar los botones cuando el mouse se encuentra en un rango X a la posicion de los botones
	 */
	private void mostrarBotones(){
		float duracion=0.3f; 
	        
			   Opcion.addAction(Actions.moveTo(780,420, duracion));
		 		ayuda.addAction(Actions.moveTo(780,340,duracion));
		ordenarPiezas.addAction(Actions.moveTo(780,260, duracion));
	        reiniciar.addAction(Actions.moveTo(780,180,duracion));
		        salir.addAction(Actions.moveTo(780,100,duracion));
		        
		        if(Gdx.graphics.isFullscreen()){		        							
				       Opcion.setVisible(true);
				        ayuda.setVisible(true);
				ordenarPiezas.setVisible(true);
				    reiniciar.setVisible(true);
				        salir.setVisible(true);
		        }		        		
	}
	
/**
 * ocultar los botones cuando el mouse se encuentra en un rango X diferente a la posicion de los botones
 */
private void ocultarBotones(){
		float duracion=0.3f; 
		
	  
			Opcion.addAction(Actions.moveTo(850,420, duracion));
			 ayuda.addAction(Actions.moveTo(850,340,duracion));
	 ordenarPiezas.addAction(Actions.moveTo(850,260, duracion));
         reiniciar.addAction(Actions.moveTo(850,180,duracion));
	         salir.addAction(Actions.moveTo(850,100,duracion));
	         
        if(Gdx.graphics.isFullscreen())
        {
        	Timer.schedule(new Task() {
				@Override
				public void run() {
					
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
 * Metodo que setea la casilla de las piezas nuevamente a su posicion inicial
 */
private void setCasillaDesordenando(){
	
	setCasilla(pos[0], TorreBlancaFlancoReina, TorreBlancaFlancoReina.getInd());	
	setCasilla(pos[1], TorreBlancaFlancoRey, TorreBlancaFlancoRey.getInd());
	setCasilla(pos[2], AlfilBlancaFlancoReina, AlfilBlancaFlancoReina.getInd());
	setCasilla(pos[3], AlfilBlancaFlancoRey,AlfilBlancaFlancoRey.getInd());
	setCasilla(pos[4], CaballoBlancaFlancoReina, CaballoBlancaFlancoReina.getInd());
	setCasilla(pos[5], CaballoBlancaFlancoRey, CaballoBlancaFlancoRey.getInd());
	setCasilla(pos[6],ReinaBlanca, ReinaBlanca.getInd());setCasilla(pos[7], ReyBlanca, ReyBlanca.getInd());
	setCasilla(pos[8], peon1, peon1.getInd());
	setCasilla(pos[9], peon2, peon2.getInd());
	setCasilla(pos[10], peon3, peon3.getInd());
	setCasilla(pos[11], peon4, peon4.getInd());
	setCasilla(pos[12], peon5, peon5.getInd());
	setCasilla(pos[13], peon6, peon6.getInd());
	setCasilla(pos[14], peon7, peon7.getInd());
	setCasilla(pos[15], peon8, peon8.getInd());
}
/**
 * coloca el nombre de la batalla en que pertenece el minijuego
 */
public void nombreBatalla() {
	TextButtonStyle tbs = new TextButtonStyle();
	tbs.font= font;
	pack2 = new TextureAtlas(Gdx.files.internal("assets/skins/mapaGeneral.pack"));
	skin3.addRegions(pack2);

	tbs.up= skin3.getDrawable(batallaNombre);
	tbs.over= skin3.getDrawable(batallaNombre+"Aura");
	TextButton nombre = new TextButton("", tbs);
	nombre.setSize(246, 74);
	nombre.setPosition(520, 480);
	nombre.addListener(new ClickListener() {
		public void clicked (InputEvent event, float x, float y) {				
			cargarhistoria()	;	}
	});
	stage.addActor(nombre);
	
}
/**
 * coloca el nombre del personaje en que pertenece el minijuego
 */
public void nombrePersonaje() {
	
	Texture texture=game.getManager().get("assets/personajes/nombre/"+rutaTexturaPersonaje.split("/")[2]);
	Image nombre=new Image(texture);
	nombre.setSize(246, 74);
	nombre.setPosition(520, 8);
	
	stage.addActor(nombre);
	
}

public void ganar(){
	actualizarPartida();
	game.mapMin.asignarPartida(partida, batalla,dificultad,configuracion,true);
	if (!cargarVideoInformativo(eTipoVideo.Victoria))
		mostrarDialogo(eTipoDialogo.Aceptar, true, null);


}

public void perder(){
	if (!cargarVideoInformativo(eTipoVideo.Derrota))
		mostrarDialogo(eTipoDialogo.Mensaje, true,null);

}

/**
 * Muestra en un cuadro de dialogo el video de derrota o victoria para el minijuego
 */
private boolean cargarVideoInformativo(eTipoVideo pTipoVideo) {
	
	String vPath = "";
	//eTipoMensaje vTipoMensaje = null;
	switch (pTipoVideo) {
	case Victoria:
//		System.out.println("victoria");
		vPath = "assets/video/" + batalla +  "/" + minijuego + "v.ogv";
		//vTipoMensaje= eTipoMensaje.JuegoFinalizadoVictoria;
		break;
	case Derrota:			
	//	System.out.println("DERROTA");
		vPath = "assets/video/" + batalla +  "/" + minijuego + "d.ogv";
		//vTipoMensaje= eTipoMensaje.JuegoFinalizadoDerrota;
		break; 
	case Ayuda:
		vPath = "assets/video/" + batalla +  "/" + minijuego + "a.ogv";

	break;
	
	default:		
		System.out.println("tipo de video  que llegó " + pTipoVideo);
		break;
	}					

	try{
		cargarvideo(vPath, "       ", pTipoVideo);
		return true;
	}
	catch(Exception e){
		System.out.println("Error, no se puede cargar el video: " + vPath + e.getCause());
		return false;
	}
}


/**
 * Muestra en un cuadro de dialogo un video
 */
private void cargarvideo(String video, String mensaje,eTipoVideo tipoVideo) {
//	System.out.println("cargarvideo " + tipoVideo.toString());
	
	if (tocarMusica)		
		cortinaFondo.pause();	
			
	if(flagReloj==false){
		pausarReloj();
	}

	if (stage.getActors().contains(videoScreen, true)) {
		videoScreen.dispose();
		videoScreen.remove();					
		videoScreen = null;
		gc();
	}
		//System.out.println("agregando el contenedor: " + video);
		//String[] sino  = new String[2];
		videoScreen = new Dialogo(); //TODO wilmer
		videoScreen.create( video, tipoVideo, false);
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


public void procesarVideoListener(Object pResult, eTipoVideo pTipoVideo){
	//System.out.println("procesando videolistener");
	try {
//System.out.println("videoScreen.getResult(): " + pResult);
		if (pResult.equals(true)) {
			if (pTipoVideo.equals(eTipoVideo.Victoria)){				
				actualizarPartida();				
				videoScreen.dispose();
				videoScreen.remove();
				tocarMusica=false;
				game.mapMin.asignarPartida(partida, batalla,dificultad,configuracion,true);				
				game.setScreen(game.mapMin);
							
			}
			else if(pTipoVideo.equals(eTipoVideo.Derrota))
				reiniciar();													
			else if (pTipoVideo.equals(eTipoVideo.Ayuda))
				cargarayuda();
			else if(pTipoVideo.equals(eTipoVideo.Historia)){
				gc();
				reanudarReloj();
				cargarhistoria();					
			}
			else if(pTipoVideo.equals(eTipoVideo.Biografia)){
				gc();
				reanudarReloj();
				cargarBiografia();					
			}
				
			
		} else if (pResult.equals(false)){
					
					if (!pTipoVideo.equals(eTipoVideo.Ayuda)&& !pTipoVideo.equals(eTipoVideo.Biografia)&&!pTipoVideo.equals(eTipoVideo.Historia)){				
							game.setScreen(game.mapMin);
							cortinaFondo.pause();
				//			dispose();
						}
					else{
						if (tocarMusica){						
							cortinaFondo.play();							
						}
					}

					reanudarReloj();					
					//videoScreen.clear();
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

private void reiniciar(){
	mostrarVideos = false;
	recargaScreen = true;
	reloj.clearActions();
	flagReloj=false;
	game.setScreen(game.ordenando);
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
		System.out.println("Error cargando el video: " + videoAyuda);		
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
		System.out.println(videoBiografia);
		if (!videoBiografia.equals("0"))
			cargarvideo(videoBiografia, " Ayuda ", eTipoVideo.Biografia);
		else
			System.out.println("Error, no se ha configurado el video de Biografía para este minijuego");
	}
	catch(Exception e){
		System.out.println("Error cargando el video: " + videoBiografia);	
		}
	}
public void pausarReloj(){
	flag=false;
	rotacionIni=(int) reloj.getRotation();
	rotacionAcc=rotacionAcc+(int) rotateAction.getRotation();
	tiRotacion=tiRotacion+(int) rotateAction.getTime();
	reloj.clearActions();
}
public void reanudarReloj(){
reloj.clearActions();
	flag=true;
	stage.getActors().removeValue(reloj, true);
	reloj = new ActorExtra(this, TipoDeActor.AGUJA, 0);
	reloj.setSize(15, 55);
	reloj.setPosition(586, 134);
	reloj.setOrigin(reloj.getWidth() / 2, 3);
	reloj.rotateBy(rotacionIni);
	rotateAction=new RotateToAction();
	rotateAction.setRotation(360f-rotacionAcc);
	rotateAction.setDuration(segundos-(tiRotacion-1));
	if(flagReloj==true){
	rotateAction.setReverse(true);
	}
	flagReloj=false;
	reloj.addAction(rotateAction);
	stage.addActor(reloj);
	
}

public void gc(){
//	for (int i =0;i<=5;i++)
//		System.gc();
	}

private void setVolumen(){
	cortinaFondo.setVolume(super.getVolume());	
}
}
