//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
/////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//                Protocolo.java                            //
//                   Descripcion                            //
//             Pantalla para el primer juego               //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      09/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////
package com.jayktec.grafico.Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
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
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.Encriptado;
import com.jayktec.archivos.NuevaPartida;
import com.jayktec.archivos.Partida;
import com.jayktec.comunicacion.Conversacion;
import com.jayktec.comunicacion.Oyente;
import com.jayktec.comunicacion.Protocolo;
import com.jayktec.comunicacion.Traductor;
import com.jayktec.grafico.Dialogo;
import com.jayktec.grafico.Enums;
import com.jayktec.grafico.Enums.eColores;
import com.jayktec.grafico.Enums.eContadores;
import com.jayktec.grafico.Enums.eEstadoPieza;
import com.jayktec.grafico.Enums.eRespuestaDialgo;
import com.jayktec.grafico.Enums.eTipoDialogo;
import com.jayktec.grafico.Enums.eTipoMensaje;
import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Enums.eTipoVideo;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Preferencias;
import com.jayktec.grafico.Piezas.ActorExtra;
import com.jayktec.grafico.Piezas.ActorExtra.TipoDeActor;
import com.jayktec.grafico.Piezas.Alfil;
import com.jayktec.grafico.Piezas.Caballo;
import com.jayktec.grafico.Piezas.Casilla;
import com.jayktec.grafico.Piezas.Fen;
import com.jayktec.grafico.Piezas.Peon;
import com.jayktec.grafico.Piezas.Pieza;
import com.jayktec.grafico.Piezas.Reina;
import com.jayktec.grafico.Piezas.Rey;
import com.jayktec.grafico.Piezas.Tablero;
import com.jayktec.grafico.Piezas.Torre;
import com.jayktec.grafico.Screen.ScreenManager;
import com.jayktec.grafico.Screen.ScreenVideo;
import com.jayktec.grafico.Screen.mapaGeneralScreen;
import com.sun.org.apache.bcel.internal.generic.I2F;
import com.jayktec.archivos.TraductorAlg;

public class ScreenJuegoPrincipal extends ScreenManager {

	/**
	 * Declaracion de variables
	 */

	private Dialogo videoScreen;
	private Sound sonidoMoneda;
	private eColores colorActivas;
	private Color colorOponente = Color.BLACK;
	private boolean jugandovsMangoPaola = true;
	private Color Turno;
	private Miniheroes game;
	private Sound reyMovimiento, reinaMovimiento, torreMovimiento, alfilMovimiento, caballoMovimiento, peonMovimiento;
	private Music cortinaFondo, cortinaDerrota;
	private Stage stage;
	private TextButton salir, reiniciar, cambioPieza, juegaMango, deshacer, Opcion; //Guardar
	private Skin skin1, skin2;
	//private Partida partida;
	private TextureAtlas pack;
	private BitmapFont font;
	private Label[] letras= new Label[8];
	private Label[] numeros = new Label[8];
	private Label dif;
	private Conversacion conversacion;
	public TiledMap tiledMap;
	public String TexturaTablero;
	private Tablero tablero = new Tablero();
	private Casilla casilla = new Casilla();
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	private Casilla[] casillas = new Casilla[32];
	;
	private Peon peonalpaso;
	String pgnPeonalPaso = "-";
	int movimientosMedios = 0;
	int movimientosCompletos = 0;
	int reintentosMangoPaola = 0;
	String cPiezaPromovida;
	private boolean jaqueMate = false;
	private boolean tablas = false;
	private boolean activaDeshacer = false;
	private ArrayList<Pieza> piezasPromovida = new ArrayList<Pieza>();
	private ActorExtra cuadroIni, cuadroFin,casillaJaque;
	private Casilla[] casillasDeshacer;
	private ArrayList<Casilla[]> listaCasillasDeshacer = new ArrayList<Casilla[]>();
//	public ArrayList<Casilla[]> listaCasillasDeshacerp= new ArrayList<Casilla[]>();
	private SelectBox<String> box;
	public String valorDificultad = "sd 1";
//	public String dificulty = "";
	public String varRes = "";
	public String comando;
	public String guardarMovimientos;
	public String test;
	public String vStringFen;
	public String[] comandos = new String[0];
	String txcargaFen;
	public List<Object> listaDeJugadas;
	
	public ScrollPane pane;
	private ArrayList<String> jugadas = new ArrayList<String>();
	
	
	private ArrayList<String> jugadasAlg =new ArrayList<String>();
	private int validaFen = 0;
	private Preferencias preferencias;
	int w = ((int) super.getGameWidth());
	int h = ((int) super.getGameHeight());
	private boolean tocarSonido = false;
	private boolean tocarCortinas = false;
	private String minijuego;
	private String batalla;
	private String nombrePartida;
	private Dialogo opcionesScreen = null;
	//private Configuracion configuracion;
//	private Texture textureCuadro;
	private Sound movIncorrecto;
	private Sound sonidoDerrota,sonidoTriunfo;
	private static String ultimaJudada="";
	private ActorExtra[] posiblesMovimientos;
	private String dificulty;
	private ActorExtra canon,explosion,flecha,actorExtra,magia,lanza,machete,cetro;
	private SelectBox<String> box2;
	private Label tab;
	private Label pie,tiempo,retardo;
	private SelectBox<String> box3,box4,box5;
	private Boolean historia;
	
	private Boolean ultimoMinBatalla;
	//private char[] moneda;
	private ActorExtra[] movimientoAyuda;
	private TextButton botonjugAyuda,btnTablas;
	private int monedaAyuda;
	private int cantidadMonedas;
	private Image imagenMoneda;
	private Label etiquetaMoneda;
	private ArrayList<String> jugadaHumano;
	private TextureAtlas alfilPatriotaDerechaAbajo,alfilPatriotaDerechaArriba,alfilPatriotaIzquierdaArriba,alfilPatriotaIzquierdaAbajo;
	private TextureAtlas torrePatriotaIzquierda,torrePatriotaDerecha,torrePatriotaArriba,torrePatriotaAbajo;
	private TextureAtlas damaPatriotaIzquierda,damaPatriotaDerecha,damaPatriotaArriba,damaPatriotaAbajo,damaPatriotaDerechaAbajo,damaPatriotaDerechaArriba,damaPatriotaIzquierdaArriba,damaPatriotaIzquierdaAbajo;
	private TextureAtlas reyPatriotaIzquierda,reyPatriotaDerecha,reyPatriotaArriba,reyPatriotaAbajo,reyPatriotaDerechaAbajo,reyPatriotaDerechaArriba,reyPatriotaIzquierdaArriba,reyPatriotaIzquierdaAbajo;
	private TextureAtlas peonPatriotaArriba,peonPatriotaAbajo,peonPatriotaDerechaAbajo,peonPatriotaDerechaArriba,peonPatriotaIzquierdaArriba,peonPatriotaIzquierdaAbajo;
	private TextureAtlas caballoAbajoDerecha,caballoAbajoIzquierda,caballoArribaDerecha,	caballoArribaIzquierda,	caballoDerechaArriba,	caballoDerechaAbajo,	caballoIzquierdaArriba,	caballoIzquierdaAbajo;
	private String avatar;
	
	private TextureAtlas alfilRealistaDerechaAbajo,alfilRealistaDerechaArriba,alfilRealistaIzquierdaArriba,alfilRealistaIzquierdaAbajo;
	private TextureAtlas torreRealistaIzquierda,torreRealistaDerecha,torreRealistaArriba,torreRealistaAbajo;
	private TextureAtlas damaRealistaIzquierda,damaRealistaDerecha,damaRealistaArriba,damaRealistaAbajo,damaRealistaDerechaAbajo,damaRealistaDerechaArriba,damaRealistaIzquierdaArriba,damaRealistaIzquierdaAbajo;
	private TextureAtlas reyRealistaIzquierda,reyRealistaDerecha,reyRealistaArriba,reyRealistaAbajo,reyRealistaDerechaAbajo,reyRealistaDerechaArriba,reyRealistaIzquierdaArriba,reyRealistaIzquierdaAbajo;
	private TextureAtlas peonRealistaArriba,peonRealistaAbajo,peonRealistaDerechaAbajo,peonRealistaDerechaArriba,peonRealistaIzquierdaArriba,peonRealistaIzquierdaAbajo;
	private TextureAtlas caballoRealistaAbajoDerecha,caballoRealistaAbajoIzquierda,caballoRealistaArribaDerecha,	caballoRealistaArribaIzquierda,	caballoRealistaDerechaArriba,	caballoRealistaDerechaAbajo,	caballoRealistaIzquierdaArriba,	caballoRealistaIzquierdaAbajo;
	private String videoAyuda;
	private String videoHistoria;
	private String videoBiografia;
	private String batallaNombre;
	private static int jugadasSecretasSeguidas = 0;
 	private static int jugadasSecretasNoSeguidas = 0;
 	String rutaTexturaPersonaje="";
 	private String jugadaAlgSecreta = "";	
 	private String tipoDePiezas = "MiniHeroe";
 	private boolean flagSonido=true;
 	private boolean hard = false;
	private TextureAtlas pack2,pack3;
	private Skin skin3,skin5;
	private TextButton contadorHumano;
	private TextButton contadorHumano2,contadorHumano3,contadorHumano4;
	private TextButton contadorMaquina;
	private TextButton contadorMaquina2,contadorMaquina3,contadorMaquina4;
	private Boolean pantallaContador;
	private Integer cantidadContador;
	private String tipoContadorHumano;
	private String tipoContadorMaquina;
	private String minijuegoPartida;
	private String batallaPartida;
	private boolean pasoPreferencia=false;
	private int tiempoRetardo;
	private int retardoBlancas=tiempoRetardo;
	private int retardoNegras=tiempoRetardo;
	private int tiempoJugadasBla=120;
	private int tiempoJugadasNe=120;
	private float timecount;
	private boolean mostrarcontTiempo=false;
	private String selec4,selectTipoPieza,selec5,select1;
	private String cont1,cont2,cont3,cont4;
	boolean flagTiempo=true;
	boolean flagTiempo2=true;
	boolean flagPane=true;
	private String[] jugadores =  new String[2];
	private Image imagenReloj;
	private TextButton atras, siguiente;
	private int jugadaRevisar = 0 ;
	private int CantidadjugadaRevisar = 0 ;
	private Dialogo dialog1 = null;
	private Partida partida;
	private Dificultad dificultad;
	private Configuracion configuracion;
	TextButtonStyle styleNomb,blancas,negras;
	private boolean bGanar = false;
	private boolean bTablas = false;
	private boolean bPerder = false;
	private TextButton imagenBotones;
	private boolean reinicio = false;
	private Skin skin6,  skin7;
	private TextureAtlas pack6, pack7;
	int itemSeleccionado =-1;
	private boolean noMostrarSalir = false;
	private boolean flagReloj=true;
	private boolean primerajugada=true;
	private boolean motorIniciado;
	
	private static boolean videoAyudaMostrado = false;
	private boolean cambioPiezaPermitido = true;
	private int monedasInicio;
	private ScreenVideo a;

	private Texture turnoBlanco;
//	private Image imagenMoneda;

	public mapaGeneralScreen mapGen;
//	private boolean ultimaJugadaGuardada = false;
	/**
	 * Constructor de la clase ScreenJuegoPrincipal
	 * 
	 * @param pGame
	 */

	public ScreenJuegoPrincipal(final Miniheroes pGame) {
		super(pGame);
		pasoPreferencia=false;
		game = pGame;
		//textureCuadro = game.getManager().get("assets/Texturas/cuadroOscuro.png");
		cuadroIni = new ActorExtra(this, TipoDeActor.CUADROJUGADOS, 0);
		cuadroFin = new ActorExtra(this, TipoDeActor.CUADROJUGADOS, 0);
		cuadroIni.setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
		cuadroFin.setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
		historia= false;
		monedaAyuda=0;
		
		//turnoBlanco = new Texture("assets/Texturas/turnoBlanco.png"); 
		
		skin1 = new Skin();
		pack = new TextureAtlas(Gdx.files.internal("assets/skins/juegoPrincipal.pack"));
		skin1.addRegions(pack);
		
		skin2 = new Skin(Gdx.files.internal("assets/skins/uiskin1.json"));

		
		dif = new Label("Campeones", skin2);
		tab = new Label("Tablero", skin2);
		pie = new Label("Tipo de Pieza", skin2);
		tiempo = new Label("Tiempo", skin2);
		retardo = new Label("Incremento", skin2);
		

		font = new BitmapFont();
		
	//	font.setColor(0,0,0,1);
		
		canon = new ActorExtra(this, TipoDeActor.CANON, 0);
		explosion= new ActorExtra(this, TipoDeActor.EXPLOSION, 0);
		flecha= new ActorExtra(this, TipoDeActor.FLECHA, 0);
		magia= new ActorExtra(this, TipoDeActor.MAGIA, 0);
		lanza= new ActorExtra(this, TipoDeActor.LANZA, 0);
		machete= new ActorExtra(this, TipoDeActor.MACHETE, 0);
		cetro= new ActorExtra(this, TipoDeActor.CETRO, 0);
		
	}

	@Override
	public Miniheroes getGame() {
		return game;
	}

	@Override
	public void show() {
		System.gc();
		motorIniciado=false;
		
		game.mapMin.screenfrom(this);
		preferencias = Preferencias.getInstance();
		//preferencias.load();
		

		if(historia){
			try {
				System.out.println("seteando personaje");
				avatar = partida.getPersonaje();
			} catch (Exception e) {

				System.out.println("fallo seteo personaje");
				avatar="Sebastian";
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		cargarAnimacionesPatriotas();
		cargarAnimacionesRealistas();
		

		sonidoMoneda= game.getManager().get("assets/Sonidos/moneda.mp3");
		
		try {

			cortinaFondo = game.getManager().get("assets/Sonidos/cortinaFondo.ogg");
			
			// Configuracion.GetAtributo(batalla, "Minijuego", minijuego,
			// "Sonido"));
			if(historia){
			//TexturaTablero = "assets/mapasTiled/tableroMadera.tmx";
			TexturaTablero = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Fondo");
			pantallaContador= Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Contador"));
			cantidadContador= Integer.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "CantidadContador"));
			tipoContadorHumano=configuracion.GetAtributo(batalla, "Minijuego", minijuego, "TipoContadorHumano");
			tipoContadorMaquina=configuracion.GetAtributo(batalla, "Minijuego", minijuego, "TipoContadorMaquina");
			jugadas = new ArrayList<String>();
			jugadasAlg = new ArrayList<String>();
			casillas = new Casilla[32];
					
			}
			else{
				if(select1==null){
					TexturaTablero = "assets/mapasTiled/tableroValle.tmx";
				}
				if(selectTipoPieza==null){
					tipoDePiezas = "MiniHeroe";
					selectTipoPieza="MiniHéroe";
				}

			}
		//	dificultad = "sd 1";
			// Configuracion.GetAtributo(batalla, "Minijuego", minijuego,
			// "Fondo");
		} catch (Exception e1) {
			e1.printStackTrace();
		}


		sonidoTriunfo = game.getManager().get("assets/Sonidos/sonidoTriunfo.ogg");
		sonidoDerrota = game.getManager().get("assets/Sonidos/sonidoDerrota.mp3");
		cortinaDerrota = game.getManager().get("assets/Sonidos/lenta.ogg");
		movIncorrecto = game.getManager().get("assets/Sonidos/movIncorrecto.ogg");
	
		reyMovimiento = game.getManager().get("assets/Sonidos/paso.ogg"); // REY
		reinaMovimiento = game.getManager().get("assets/Sonidos/paso.ogg"); // REINA
		torreMovimiento = game.getManager().get("assets/Sonidos/torre.ogg"); // TORRE
		alfilMovimiento = game.getManager().get("assets/Sonidos/alfil.ogg"); // ALFIL
		caballoMovimiento = game.getManager().get("assets/Sonidos/caballo.ogg"); // CABALLO
		peonMovimiento = game.getManager().get("assets/Sonidos/paso.ogg"); // PEON
		stage = new Stage();
		
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		// conversacion con mango paola
		if (reinicio){
			primerajugada=true;
			reinicio=false;
			bGanar=false;
			bPerder=false;
			bTablas=false;
			nuevaPartida();
			System.out.println("entro en reinicio");
		}
		
	
		conversacion = new Conversacion(Oyente.XBOARD);
		
		//this.habilitarPiezas(eColores.Blancas);
		// float w = Gdx.graphics.getWidth();
		// float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		
		
		tiledMap = new TmxMapLoader().load(TexturaTablero);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		
//		System.out.println("minijuego: " + this.minijuego);
		//botones();
		//System.out.println("show");

		camera = (OrthographicCamera) stage.getCamera();
		camera.setToOrtho(false,super.getGameWidth(),super.getGameHeight());				
		
		stage.addActor(cuadroFin);
		stage.addActor(cuadroIni);
		stage.addActor(machete);
		stage.addActor(cetro);
		stage.addActor(canon);
		stage.addActor(explosion);
		stage.addActor(flecha);
		stage.addActor(magia);
		stage.addActor(lanza);
		
		canon.setVisible(false);

        canon.setSize(58,58);
		canon.setZIndex(64);
		
		machete.setVisible(false);
		machete.setSize(58,58);
		machete.setZIndex(64);
		
		lanza.setVisible(false);
		lanza.setSize(58,58);
		lanza.setZIndex(64);
		
		cetro.setVisible(false);
		cetro.setSize(58,58);
		cetro.setZIndex(64);
		
		magia.setVisible(false);
		magia.setSize(58,58);
		magia.setZIndex(64);
		
		explosion.setVisible(false);
		explosion.setSize(58,58);
		explosion.setZIndex(64);
		
		flecha.setVisible(false);
		flecha.setSize(58,58);
		flecha.setZIndex(64);
		
		cuadroFin.setVisible(false);
		cuadroIni.setVisible(false);
		//System.out.println("ultima size :"+ ultimaJudada.length());
		if (ultimaJudada.length()>0 ){
		String[] ultimaJudadaPgn =  ultimaJudada.split("/");
		dibujarCuadro(ultimaJudadaPgn [0], ultimaJudadaPgn [1]);
		}
	
		Gdx.input.setInputProcessor(stage);
		peonalpaso = null;
		 boolean tocandoMusica=super.playingMusic();

		 super.setVolume(preferencias.getPreferenciav("volumen"));
		
		if (preferencias.getPreferencia("musica")) {
			if(tocandoMusica)
			{
				super.stopMusic();
			}
			}
		if (preferencias.getPreferencia("sonido")) {			
			tocarSonido = true;
		} else {
			tocarSonido = false;
		}
		Preferencias(true);
		
//		
		
		if (historia){
			skin3=new Skin();
		iniciarMotor();
			if(pantallaContador)
				{
					contador(cantidadContador,true);
					comprobarContadoresHumano();
					comprobarContadoresMaquina();
				}
				
				Texture textureMoneda=game.getManager().get("assets/Texturas/moneda.png");
				imagenMoneda=new Image(textureMoneda);
				imagenMoneda.setPosition(10, 500);
				imagenMoneda.setSize(48, 48);
				imagenMoneda.setTouchable(Touchable.disabled);
				stage.addActor(imagenMoneda);
				
				etiquetaMoneda = new Label("x" + String.valueOf(cantidadMonedas), skin2);
				etiquetaMoneda.setPosition(65, 512);
				etiquetaMoneda.setFontScale(1);
				stage.addActor(etiquetaMoneda);
				cargarPersonaje();

				

					if (batallaPartida.equals(batalla))
							{
								if (minijuegoPartida.equals( minijuego))
										{						
											noMostrarSalir = true;						
											cargarayuda();
											System.out.println("mostrando video ayuda");
											motorIniciado=true;
											noMostrarSalir = false;
										}
								
							}
				
		}
		else{
			iniciarMotor();
			Texture texture2=game.getManager().get("assets/Texturas/relojAjedrez.png");
			imagenReloj =new Image(texture2);
			imagenReloj.setPosition(490, 325);
			imagenReloj.setSize(363, 155);
			stage.addActor(imagenReloj);
			if(selec4==null){
				selec4="Ilimitado";
			}
		
			if(mostrarcontTiempo){
				if(selec5==null){
					selec5="0 seg";
				}
				iniciarRetardo();				
			}
			
		if(mostrarcontTiempo)
			imagenReloj.setZIndex(64);
		else
			imagenReloj.setZIndex(0);

		}	
		Texture texture=game.getManager().get("assets/Texturas/cuadrotransparente.png");
		Image imagen=new Image(texture);

		if(historia){
			cambiarTexturaRey();
			imagen.setPosition(520, 20);
			imagen.setSize(247, 475);
		}
		else{
			imagen.setPosition(530, 20);
			imagen.setSize(290, 470);
			Texture texture2=game.getManager().get("assets/Texturas/cuadrotransparente.png");
			Image imagen2=new Image(texture2);
			stage.addActor(imagen2);
			imagen2.setSize(900, 55);
			imagen2.setPosition(0, 500);
			imagen2.setZIndex(0);					

		}
		
		stage.addActor(imagen);
		imagen.setZIndex(0);
		
		
		
	}
	
	private void nuevaPartida() {
		cambioPiezaPermitido = true;
		bGanar=false;
		bPerder=false;
		bTablas=false;
		jaqueMate = false;
		// TODO Auto-generated method stub
		System.out.println("reiniciando");
//		resultadoJugarMangoPaola(enviarMangoPaola("new"));
		//System.out.println("termine de reiniciando");
		ultimaJudada ="";		
		listaCasillasDeshacer.clear();
		txcargaFen = null;
		peonalpaso=null;
		jugadasSecretasNoSeguidas=0;
		jugadasSecretasSeguidas=0;
		jugadas = new ArrayList<String>();
		jugadasAlg = new ArrayList<String>();
		listaDeJugadas.setItems(jugadasAlg.toArray());
		for (int i = 0; i <= 31; i++){
			try {

			if(casillas[i].getPieza()!=null)
				{
			
						Pieza pPieza=casillas[i].getPieza();
						stage.getActors().removeValue(pPieza, true);	
				}
			} catch (Exception e) {
				// TODO: handle exception
			}	

		}
		
		for(Actor miActor:stage.getActors())
		{
			if (miActor.getName() != null ) 
				{
				miActor.setVisible(false);
				stage.getActors().removeValue(miActor, false);
				miActor.remove();
				}
		}
		
		
		for(Actor miActor:stage.getActors())
		{
			if (miActor.getName() != null ) 
				{
				miActor.setVisible(false);
				stage.getActors().removeValue(miActor, true);
				miActor.remove();
				}
		}
		
		for(Actor miActor:stage.getActors())
		{
			if (miActor.getName() != null ) 
				{
					System.out.println("nombre de pieza :"+miActor.getName());
				}
		}
		
		casillas = new Casilla[32];
	cortinaDerrota.stop();
	if (tocarCortinas)
	{
		cortinaFondo.play();
		cortinaFondo.setLooping(true);
	} else 
	{
		cortinaFondo.stop();
	}
	
	
	
		if(mostrarcontTiempo){
			comprobartiempo();
			}
		jugadasSecretasSeguidas=0;
		jugadasSecretasNoSeguidas=0;
		reinicio = true;
//		for (Actor miActor : stage.getActors())
//			
//				if (miActor.getName()!=null) 
//					stage.getActors().removeValue(miActor, true);	
cuadroIni.setVisible(false);
cuadroFin.setVisible(false);
if(siguiente!=null){
	siguiente.setVisible(false);
}
if(atras!=null){
	atras.setVisible(false);
}
eliminarMovimientoPosibles();
eliminarJugadaAyuda();
peonalpaso=null;
primerajugada=true;
bPerder=false;
bGanar=false;
bTablas=false;
tiempoJugadasBla=0;
tiempoJugadasNe=0;
jaqueMate=false;
System.out.println("termine de reiniciar todo");
System.out.println("este es select "+selec4);
if(mostrarcontTiempo){
			System.out.println("este es select "+selec4);
			comprobartiempo();
			inicializarRelojes();
			}
	CargarFen();

System.out.println("selectpiezas:"+selectTipoPieza);
cambioDePiezas();
if (historia && pantallaContador){
	
	actualizarContador (true, jugadasSecretasSeguidas*-1)	;
	actualizarContador (false,jugadasSecretasSeguidas*-1)	;
}
System.out.println("termine de reiniciar todo");

//conversacion.protocolo.clean();
//game.setScreen(game.juegoPrincipal);
//try {
//	resultadoJugarMangoPaola(enviarMangoPaola("exit"));
//	conversacion.finalize();
//	conversacion=null;
//} catch (Throwable e) {
//	e.printStackTrace();
//}
//motorIniciado=false;
//txcargaFen = null;
//iniciarMotor();
//System.out.println("selectpiezas:"+selectTipoPieza);
//cambioDePiezas();
//
////
//	conversacion= new Conversacion(Oyente.XBOARD);
//conversacion.start();
//
//while (!conversacion.isArriba()) {
//	try {
//		Thread.sleep(1000);
//	} catch (InterruptedException e) {
//
//		e.printStackTrace();
//	}
//}

motorIniciado=true;
//enviarMangoPaola("setboard  " + vStringFen);

	}

	/**
	 * Cargar personaje de la batalla final 
	 */
	private void cargarPersonaje() {
		
		try {
			videoBiografia = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Biografia");
			rutaTexturaPersonaje = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Personaje");
			videoAyuda = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ayuda");
			videoHistoria = configuracion.GetAtributo(batalla, "Mapa", "mapa", "Biografia");
			batallaNombre=configuracion.GetAtributo(batalla, "Mapa", "mapa", "Historia");
			
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		Texture texturaPersonaje = game.getManager().get(rutaTexturaPersonaje);
		Texture texturaPersonajeAura=game.getManager().get(rutaTexturaPersonaje.split("/")[0]+"/"+rutaTexturaPersonaje.split("/")[1]+"/Aura/"+rutaTexturaPersonaje.split("/")[2]);

		ImageButtonStyle style=new ImageButtonStyle();
		style.up=new TextureRegionDrawable(new TextureRegion(texturaPersonaje));
		style.over=new TextureRegionDrawable(new TextureRegion(texturaPersonajeAura));
		ImageButton	imagenPersonaje=new ImageButton(style);
		
		imagenPersonaje.setPosition(645, 70);

		imagenPersonaje.setSize(120, 230);
		imagenPersonaje.setTouchable(Touchable.enabled);
		imagenPersonaje.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {				
				cargarBiografia();
			}
		});
		stage.addActor(imagenPersonaje);
		
		nombreBatalla();
		nombrePersonaje();	
	}

/**
 * coloca el nombre de la batalla en que pertenece el minijuego
 */
public void nombreBatalla() {
	TextButtonStyle tbs = new TextButtonStyle();
	tbs.font= font;
	TextureAtlas pack =new TextureAtlas(Gdx.files.internal("assets/skins/mapaGeneral.pack"));
	skin2.addRegions(pack);

	tbs.up= skin2.getDrawable(batallaNombre);
	tbs.over= skin2.getDrawable(batallaNombre+"Aura");

	TextButton nombre = new TextButton("", tbs);
	nombre.setSize(246, 74);
	nombre.setPosition(520, 480);
	nombre.addListener(new ClickListener() {
		public void clicked (InputEvent event, float x, float y) {
				cargarhistoria();										
								
		}
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
	/**
	 * Muestra en un cuadro de dialogo un video
	 */
//	private void cargarvideo(String video, String mensaje) {
//
//		cortinaFondo.pause();
//		if (!stage.getActors().contains(videoScreen, true)) {
////			System.out.println("agregando el contenedor: " + video);
////			String[] sino = { "Repetir video", "Salir" };
//			videoScreen = new Dialogo(mensaje, video, eTipoVideo.Biografia);
//			videoScreen.setWidth(700);
//			videoScreen.setHeight(500);
//			videoScreen.setPosition(25, 25);
//			stage.addActor(videoScreen);
//
//			videoScreen.addListener(new ChangeListener() {
//				@Override
//				public void changed(ChangeEvent event, Actor actor) {
//					try {
//						if (videoScreen.getResult().equals(true)) {
//							stage.getActors().removeValue(videoScreen, true);
//							videoScreen.clear();
//							videoScreen.remove();
//							videoScreen = null;
//							cargarayuda();
//
//						} else {
//							videoScreen.clear();
//							videoScreen.remove();
//							videoScreen= null;
//							stage.getActors().removeValue(videoScreen, true);
//							videoScreen = null;
//							if (tocarCortinas){
//								cortinaFondo.play();
//								cortinaFondo.setLooping(true);
//							}
//							else
//								cortinaFondo.pause();						
//						}
//					} catch (Exception e) {
//						System.out.println("error: " + e.getMessage() + " - " + e.getCause());
//						//					}
//					stage.removeListener(this);
//				}
//			});
//		}

//	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		if (stage!=null){
			stage.act();
			stage.draw();
		}
	
      	
    		
		timecount+=delta;
			
		if(!jugar()){
//			System.out.println("deshabilito");
			cambioPieza.setTouchable(Touchable.disabled);
		}
		else{
//			System.out.println("habilito");
			cambioPieza.setTouchable(Touchable.enabled);
		}
		
		
		
		if(mostrarcontTiempo){
			if(timecount>=1){
				//System.out.println("6");
				if(getTurno().equals(Color.WHITE)&& !primerajugada){
					determinarTiempoBla();
				}	
				if(getTurno().equals(Color.BLACK)){
					determinarTiempoNe();
				}
				timecount=0;
			}
		}	
		
		if(motorIniciado)
		{
			if (conversacion.isArriba()){

		
		
			if(flagPane ){
				pane.scrollTo(0, 0, 0, 0);
			}
	
					
			if(mostrarcontTiempo){
				if(timecount>=1){	
					if(getTurno().equals(Color.WHITE)&& primerajugada){
						determinarTiempoBla();
					}	
					if(getTurno().equals(Color.BLACK)){
						determinarTiempoNe();
					}
					timecount=0;
				}
			}
			
					
			if (jugandovsMangoPaola) {
try {
	if (jugar() && Turno.equals(colorOponente)) {
		
		//	System.out.println("jmp3");
			if (!bGanar&&!primerajugada)
			JugarMangoPaola();
			else
			if(primerajugada &&Turno.equals(colorOponente))
			JugarMangoPaola();
			
		}

} catch (Exception e) {
	System.out.println("jugar vacio");
}
					
				
			} else {
				if (!Turno.equals(colorOponente)^tablero.getTableroRotado()) {
					Timer.schedule(new Task() {
						@Override
						public void run() {
							deshacer.setTouchable(Touchable.enabled);
						}
					}, 0.5f * 1);
				}
				else{		
					deshacer.setTouchable(Touchable.disabled);
				}
				
			}
		}

		if (jugar())
			if (bGanar){
				System.out.println("cambiar turno ganar");
				if(mostrarcontTiempo){
					setTurno(Color.BLUE);
				}
				ganar();
			}
			else if (bPerder){
				if(mostrarcontTiempo){
					setTurno(Color.BLUE);
				}
				perder();
			}
				else if (bTablas){
					if(mostrarcontTiempo){
						setTurno(Color.BLUE);
					}
					tablas();
				}
	}
	}

	@Override
	public void hide() {
		
		
		if(skin6!=null){skin6.dispose();skin6=null;}
		if(skin7!=null){skin7.dispose();skin7=null;}
		if(pack6!=null){pack6.dispose();pack6=null;}
		if(pack7!=null){pack7.dispose();pack7=null;}
//		hidding = true;
		if(pack2!=null){
		pack2.dispose();
		pack=null;
		}
		if(skin5!=null){
		skin5.dispose();
		skin5=null;
		}
		retardoNegras=tiempoRetardo;
		retardoBlancas=tiempoRetardo;
		flagTiempo=true;
		flagTiempo2=true;
		flagPane=true;
		
		if(skin3!=null){
		skin3.dispose();
		skin3=null;
		}
		
		if(pack3!=null){
		pack3.dispose();
		pack3=null;
		}
		stage.dispose();
		stage=null;
		camera=null;
		tiledMapRenderer=null;
		peonalpaso = null;
		jaqueMate = false;
		tablas = false;
		bGanar = false;
		bTablas = false;
		bPerder = false;
		//jugadas = null;
		//jugadasAlg=null;
		historia=false;
		reinicio=false;
	
		activaDeshacer = false;
		flagReloj=true;
		font.dispose();
		tiledMap.dispose();
		tiledMap=null;
		Gdx.input.setInputProcessor(null);
		listaCasillasDeshacer.clear();
		if (!reinicio)
		{
		try {
			conversacion.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		}
		
		System.gc();
		System.gc();
		
	}

	@Override
	public void dispose() {
	}

	/**
	 * habilita las pieza del color contrarias al que viene como parametro
	 * 
	 * @param pColor
	 *            color de las piezas del jugador actual
	 */

	public void habilitarPiezas(eColores pColor) {
//		 System.out.println("Habilitando: " + Turno.toString() );
		
		if (colorActivas != pColor) {
			colorActivas = pColor;
//			System.out.println("");
//			System.out.println("________________________________________________________________");
//			System.out.println(" Jugando: " + colorActivas.toString());
			for (Actor miActor : stage.getActors())
			{
				try {
					if (((Pieza) miActor).getInd() >= 0) {
						if (miActor.getColor().equals(Color.BLACK) || miActor.getColor().equals(Color.WHITE))
							if (pColor == eColores.Blancas)
							{
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
				catch (Exception e) {
				}
			}
		}
		if (pColor.equals(eColores.Blancas)&& historia)
			try {				
				if(minijuego.contains("Minijuego27"))
					jugadaSecreta();
					
			} catch (Exception e) {
			
			}
		
		
	}

	/**
	 * Metodo para iniciar el turno del 1er jugador
	 **/
	public void setTurno(Color pTurno) {
		Turno = pTurno;
	}

	/**
	 * Funcion que retorna el color de la pieza que le toca jugar
	 **/
	public Color getTurno() {
		return Turno;
	}

	/**
	 * Metodo para cambiar el turno de cada jugador
	 **/

	public void CambiarTurno() {
		
		System.out.println("cambiar turno in");
				
		primerajugada=false;
		if(mostrarcontTiempo){
			System.out.println("oprimiendo");
			oprimirBotonReloj();
		}
				

		
//			else
		
		if (Turno == Color.WHITE){
			System.out.println("1");
					if(mostrarcontTiempo){
						habilitarPiezas(eColores.Negras);
						Turno = Color.BLACK;
						if(retardoBlancas>0){
							tiempoJugadasBla=tiempoJugadasBla+retardoBlancas;
						}
						retardoBlancas=tiempoRetardo;
					if(!selec5.equals("0 seg")){
						System.out.println("entre en el cambiar turno");
						if(tiempoJugadasBla>=60){
							int minutos=tiempoJugadasBla/60;
							int seg=tiempoJugadasBla-(minutos*60);
							actualizarContadorT(cont4, seg);
							actualizarContadorT(cont3, minutos);
						}
						else{
							actualizarContadorT(cont4, tiempoJugadasBla);
							int minutos=0;
							actualizarContadorT(cont3, minutos);
						}
					}
				}
				else{

					System.out.println("2");
						
					habilitarPiezas(eColores.Negras);
					Turno = Color.BLACK;
					}
					movimientosCompletos++;
				} else {
					
					System.out.println("guardando las casillas cambiar turno");
					casillasDeshacer = new Casilla[32];
					for (int i = 0; i <= 31; i++)
						casillasDeshacer[i] = casillas[i];					
						listaCasillasDeshacer.add(casillasDeshacer);
		
					if(mostrarcontTiempo)
						{
						habilitarPiezas(eColores.Blancas);
						Turno = Color.WHITE;
						if(retardoNegras>0){
							tiempoJugadasNe=tiempoJugadasNe+retardoNegras;
						}
						retardoNegras=tiempoRetardo;
					if(!selec5.equals("0 seg"))
					{
						if(tiempoJugadasNe>=60)
							{
							int minutosN=tiempoJugadasNe/60;
							int segN=tiempoJugadasNe-(minutosN*60);
							actualizarContadorT(cont2, segN);
							actualizarContadorT(cont1, minutosN);
							}
						else
							{
							actualizarContadorT(cont2, tiempoJugadasNe);
							int minutosN=0;
							actualizarContadorT(cont1, minutosN);
							}
					}
						}
					else{
						habilitarPiezas(eColores.Blancas);
						Turno = Color.WHITE;
					}
					
					
				}
		
		System.out.println("cambiar turno out");
	}

	/**
	 * Metodo para asignar una casilla a una pieza
	 **/

	@Override
	public void setCasilla(String pPgn, Pieza pPieza, int pInd) {
		pPgn = pPgn.substring(0, 2);// TODO POR QUE HACER SUBSTRING......
		// porque cuando se realiza la promocion de un pe�n puede tener un
		// caracter dem�s...
		// System.out.println("SETEANDO PGN:"+pPgn);
		casillas[pInd] = new Casilla();
		casillas[pInd].setPieza(pPieza);
		casillas[pInd].setPgn(pPgn);
	}

	/**
	 * Funcion que retorna la pieza que ocupa una casilla
	 **/
	public String isnull(String arg) {
		if (arg.equals(null)) {
			return "";
		}
		return arg;
	}

	/**
	 * devuelve la pieza que ocupa una casilla, si dicho escaque esta vacio,
	 * devuelve null
	 * 
	 * @param pPgn:
	 *            posicion pgn de la casilla a buscar
	 * @return pieza que ocupa el escaque
	 */
	public Pieza casillaOcupada(String pPgn) {

		// System.out.println("Buscando :"+pPgn);
		for (int i = 0; i <= 31; i++) {

			if (casillas[i] != null) {
				// System.out.println("Buscando error :" +
				// casillas[i].getPgn());
			}
			if (casillas[i].getPgn().equals(pPgn)) {
				Pieza piezat = casillas[i].getPieza();
				// System.out.println("pieza :" +
				// casillas[i].getPieza().getName());

				// System.out.println("se encontr�:" + piezat.getName() + " en:
				// " + pPgn);
				return piezat;
			} else {
				// TODO encapsular mediante un else alguna excepcion
			}
		}

		return null;

	}

	/**
	 * Funcion que retorna la casilla que ocupa una pieza
	 **/
	public String casillaOcupada(Pieza pPieza) {

		for (int i = 0; i <= 31; i++) {
			try {
				// System.out.println(casillas[i].getPieza().getName() + "
				// indice:" + i +" en: " + casillas[i].getPgn());
				if (casillas[i].getPieza().equals(pPieza)) {
					// System.out.println(casillas[i].getPieza().getName() + "
					// indice:" + i +" en: " + casillas[i].getPgn());
					return casillas[i].getPgn();
				}
			} catch (Exception e) {

			}
		}
		return null;
	}

	/**
	 * Funcion que retorna la casilla que ocupa el rey
	 **/
	public String casillaOcupadaRey() {

		for (Casilla vCasilla: casillas) {
			try {
				// System.out.println(casillas[i].getPieza().getName() + "
				// indice:" + i +" en: " + casillas[i].getPgn());
				
				if (vCasilla.getPieza().getTipoPieza() == eTipoPieza.Rey && !vCasilla.getPieza().getColor().equals(colorOponente)) {
					// System.out.println(casillas[i].getPieza().getName() + "
					// indice:" + i +" en: " + casillas[i].getPgn());
					return vCasilla.getPgn();
				}
			} catch (Exception e) {

			}
		}
		return null;
	}

	/**
	 * Validar Movimiento humano
	 */
	@Override
	public boolean ValidarMovimiento(Pieza pPieza, int pJuego, int mouseX, int mouseY, float actorX, float actorY,
			float rangoX, float rangoY, float posX, float posY) {

		//System.out.println("ScreenJuegoPrincipal.ValidarMovimiento() in");
		// Object[] res = new Object[2];
		// Pieza vPiezaPromovida =null;
		int juego = pJuego;

		if (pPieza.getColor().equals(getTurno())) {
//			System.out.println("pieza seleccionada:" + pPieza.getName() + " color: " + pPieza.getColor());
			movimientosPosibles(tablero.XY2pgn((int) actorX, (int) actorY));
			
			if (pPieza.getEstadoPieza() != eEstadoPieza.Seleccionado) {
//				System.out.println("pieza seleccionada2:" + pPieza.getName() + " color2: " + pPieza.getColor());
				cambiarEstadoEsperando();
				pPieza.cambiarEstadoPieza(eEstadoPieza.Seleccionado);				 
				
				return false;
			} else if (pPieza.getEstadoPieza() == eEstadoPieza.Seleccionado) {
				//eliminarMovimientoPosibles();
				String pgni = tablero.XY2pgn((int) actorX, (int) actorY);
				String pgn = tablero.XY2pgn((int) posX, (int) posY);

				double distancia = Math.sqrt((Math.pow(posX - actorX, 2)) + Math.pow(posY - actorY, 2));
				final float duracion = (float) distancia / ((casilla.GetAnchoCasilla() + casilla.GetAltoCasilla()) / 2);
				if (posX >= tablero.GetInicioX() && posX <= tablero.GetFinX()) {
					if (posY >= tablero.GetInicioY() && posY <= tablero.GetFinY()) {
						Pieza piezat = casillaOcupada(pgn);

						if (piezat != null) {
							if (piezat.getColor().equals(pPieza.getColor())) {
								// hacer click en una casilla ocupada por una
								// pieza del mismo color
								pPieza.cambiarEstadoPieza(eEstadoPieza.Esperando);
								piezat.cambiarEstadoPieza(eEstadoPieza.Seleccionado);
								movimientosPosibles(pgn);
								return false;
							}
						}
//						System.out.println(tablero.Pgn2XY(pgn)[1] + " " + tablero.GetInicioY() + " " + tablero.GetFinY()
//								+ " " + pPieza.getColor() + " " + tablero.getTableroRotado());
						if (cPiezaPromovida == null && pPieza.getTipoPieza() == eTipoPieza.Peon
								&& ((tablero.Pgn2XY(pgn)[1] == tablero.GetFinY()
										&& pPieza.getColor().equals(Color.WHITE) && !tablero.getTableroRotado()
										|| (tablero.Pgn2XY(pgn)[1] == tablero.GetInicioY()
												&& pPieza.getColor().equals(Color.BLACK)
												&& !tablero.getTableroRotado()))
										^ (tablero.Pgn2XY(pgn)[1] == tablero.GetInicioY()
												&& pPieza.getColor().equals(Color.WHITE) && tablero.getTableroRotado()
												|| (tablero.Pgn2XY(pgn)[1] == tablero.GetFinY()
														&& pPieza.getColor().equals(Color.BLACK)
														&& tablero.getTableroRotado()))))

						{
//							System.out.println(tablero.Pgn2XY(pgni)[1]);
							if (Math.abs(tablero.Pgn2XY(pgni)[1] - tablero.Pgn2XY(pgn)[1]) <= casilla
									.GetAltoCasilla()) {
								EscogerPromovida(pPieza, pgni, pgn);
								
								return false;
							}
						} else if (cPiezaPromovida != null)
							pgn = pgn + cPiezaPromovida;							
						

						if (!pPieza.getColor().equals(colorOponente) || !jugandovsMangoPaola) {
							if (!validarMotor(pgni, pgn)) {
							//	if (cPiezaPromovida != null)
						//			colocarJugadaAlg(pgni, pgn);
								
								cPiezaPromovida = null;
								cuadroFin.setVisible(false);
								cuadroIni.setVisible(false);
							//	System.out.println("Error, no se puede jugar:" + pPieza.getColor());
								if (tocarSonido){
									movIncorrecto.setVolume(movIncorrecto.play(),0.2f);
									mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.MovimientoIlegal);
								}
								else {
								//	System.out.println("Error, movimiento ilegal");
									mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.MovimientoIlegal);									
								}																
								return false;
							} else {
								if(jaqueMate||tablas){
									if (!jugandovsMangoPaola&&!historia){
										if (jaqueMate)
											if(getTurno().equals(Color.WHITE)){
												ganartiempo(true);
											}
											else{								
												ganartiempo(false);
											}							
										else{
									bTablas=true; 

									System.out.println("ct23");
									CambiarTurno();
									//tablas();
										}
									}
									else
										if(historia){
											if (!minijuego.contains("Minijuego27")){
												bGanar = true;
												System.out.println("ct1");
												CambiarTurno();
											//ganar();			
												}																							

										}
										
								}
								eliminarMovimientoPosibles();
								eliminarJugadaAyuda();
								
								
//								try {
								if (historia&& minijuego.contains("Minijuego27") &&  jugadasAlg.size()>=1){
									jugadaAlgSecreta =jugadasAlg.get(jugadasAlg.size()-1);
 									jugadaAlgSecreta   = jugadaAlgSecreta.substring(12,jugadaAlgSecreta.length()-1);
//	 									System.out.println("jugadaAlgSecreta: " + jugadaAlgSecreta);	 										
 									jugadasAlg.remove(jugadasAlg.size()-1);	 									
								}
//								} catch (Exception e) {
//									System.out.println("Error: " + e);
//									// TODO: handle exception
//								}	
								
							}
						}

						colocarJugadaAlg(pgni, pgn);
						
						// txtArea.setText(pPieza.toString()+" : "+ pgni+" -
						// "+pgn);

						// obtenerFen();
						
						if (pPieza.getTipoPieza() == eTipoPieza.Torre) {
							if (juego == 0) {
								if (((Torre) pPieza).Validar(mouseX, mouseY, (int) actorX, (int) actorY, (int) rangoX,
										(int) rangoY, (int) posX, (int) posY) == true) {
									((Torre) pPieza).setMovida(true);
									
									if(casillaEstaOcupada(pgn)){
										animacionCapturando(pPieza,TipoDeActor.CANON,pgni,pgn,duracion);										
									}
								else{										
										EliminarPieza(pPieza, pgn);
										setCasilla(pgn, pPieza, pPieza.getInd());
									}
									if(jugandovsMangoPaola)
										{
											if (colorOponente.equals(Color.BLACK))
												{
													animacionMovimientoPatriotas(pPieza,pgni, pgn);
												}
											else
												{
													animacionMovimientoRealistas(pPieza,pgni, pgn);
												}
										}
									else
										{
											if(getTurno().equals(Color.WHITE))
											{
												animacionMovimientoPatriotas(pPieza,pgni, pgn);
											}
											else
											{
												animacionMovimientoRealistas(pPieza,pgni, pgn);
											}
										}
								
									sonido(pPieza, duracion);
									deshacer.setTouchable(Touchable.disabled);

									System.out.println("ct2");
									CambiarTurno();
									peonalpaso = null;
									pgnPeonalPaso = "-";
									return true;
								}
							} else

							if (((Torre) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {

								if (piezat == null) {

									return true;
								}
							}
						}

						if (pPieza.getTipoPieza() == eTipoPieza.Alfil) {
							if (juego == 0) {
								if (((Alfil) pPieza).Validar(mouseX, mouseY, (int) actorX, (int) actorY, (int) rangoX,
										(int) rangoY, (int) posX, (int) posY) == true) {
									
									if(casillaEstaOcupada(pgn))
									{
										animacionCapturando(pPieza,TipoDeActor.FLECHA,pgni,pgn,duracion);
										
									}
								else
									{	
										EliminarPieza(pPieza, pgn);
										setCasilla(pgn, pPieza, pPieza.getInd());
									}
									if(jugandovsMangoPaola)
									{
										if (colorOponente.equals(Color.BLACK))
											{
												animacionMovimientoPatriotas(pPieza,pgni, pgn);
											}
										else
											{
												animacionMovimientoRealistas(pPieza,pgni, pgn);
											}
									}
								else
									{
										if(getTurno().equals(Color.WHITE))
										{
											animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else
										{
											animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									}
									deshacer.setTouchable(Touchable.disabled);
									sonido(pPieza, duracion);

									System.out.println("ct3");
									CambiarTurno();
									peonalpaso = null;
									pgnPeonalPaso = "-";
									return true;
								}
							} else

							if (((Alfil) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {
								if (piezat == null) {
									return true;
								}
							}
						}

						if (pPieza.getTipoPieza() == eTipoPieza.Reina) {
							if (juego == 0) {
								if (((Reina) pPieza).Validar(mouseX, mouseY, (int) actorX, (int) actorY, (int) rangoX,
										(int) rangoY, (int) posX, (int) posY) == true) {
									if(casillaEstaOcupada(pgn))
									{
										animacionCapturando(pPieza,TipoDeActor.MAGIA,pgni,pgn,duracion);
										
									}
								else
									{
										
										EliminarPieza(pPieza, pgn);
										setCasilla(pgn, pPieza, pPieza.getInd());
									}
									if(jugandovsMangoPaola)
									{
										if (colorOponente.equals(Color.BLACK))
											{
												animacionMovimientoPatriotas(pPieza,pgni, pgn);
											}
										else
											{
												animacionMovimientoRealistas(pPieza,pgni, pgn);
											}
									}
								else
									{
										if(getTurno().equals(Color.WHITE))
										{
											animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else
										{
											animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									}
									deshacer.setTouchable(Touchable.disabled);
									sonido(pPieza, duracion);

									System.out.println("ct4");
									CambiarTurno();
									peonalpaso = null;
									pgnPeonalPaso = "-";
									return true;
								}
							} else

							if (((Reina) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {
								if (piezat == null) {
									return true;
								}
							}
						}

						if (pPieza.getTipoPieza() == eTipoPieza.Rey) {
							if (juego == 0) {
								// System.out.println("movido :
								// "+((Rey)pPieza).isMovido());
								if (((Rey) pPieza).Validar(mouseX, mouseY, (int) actorX, (int) actorY, (int) rangoX,
										(int) rangoY, (int) posX, (int) posY) == true) {
//									System.out.println("enrocado : " + ((Rey) pPieza).isEnrocado());
									// System.out.println("movido :
									// "+((Rey)pPieza).isMovido());
									if (((Rey) pPieza).isEnrocado() == true) {
										verificarEnroque((Rey) pPieza, pgni, pgn);
									}
									((Rey) pPieza).setEnrocado(false);

									if(casillaEstaOcupada(pgn))
									{
										animacionCapturando(pPieza,TipoDeActor.CETRO,pgni,pgn,duracion);
										
									}
								else
									{
										
										EliminarPieza(pPieza, pgn);
										setCasilla(pgn, pPieza, pPieza.getInd());
									}
									if(jugandovsMangoPaola)
									{
										if (colorOponente.equals(Color.BLACK))
											{
												animacionMovimientoPatriotas(pPieza,pgni, pgn);
											}
										else
											{
												animacionMovimientoRealistas(pPieza,pgni, pgn);
											}
									}
								else
									{
										if(getTurno().equals(Color.WHITE))
										{
											animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else
										{
											animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									}
									sonido(pPieza, duracion);
									deshacer.setTouchable(Touchable.disabled);

									System.out.println("ct5");
									CambiarTurno();
									peonalpaso = null;
									pgnPeonalPaso = "-";
									return true;
								}
							} else if (((Rey) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {
								if (piezat == null) {
									return true;
								}
							}
						}
						if (pPieza.getTipoPieza() == eTipoPieza.Caballo) {
							if (juego == 0) {
								if (((Caballo) pPieza).Validar(mouseX, mouseY, (int) actorX, (int) actorY, (int) rangoX,
										(int) rangoY, (int) posX, (int) posY) == true) {
									if(casillaEstaOcupada(pgn))
									{
										animacionCapturando(pPieza,TipoDeActor.LANZA,pgni,pgn,duracion);
									}
								else
									{
										EliminarPieza(pPieza, pgn);
										setCasilla(pgn, pPieza, pPieza.getInd());
									}
									if(jugandovsMangoPaola)
									{
										if (colorOponente.equals(Color.BLACK))
											{
												animacionMovimientoPatriotas(pPieza,pgni, pgn);
											}
										else
											{
												animacionMovimientoRealistas(pPieza,pgni, pgn);
											}
									}
								else
									{
										if(getTurno().equals(Color.WHITE))
										{
											animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else
										{
											animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									}
									sonido(pPieza, duracion);
									deshacer.setTouchable(Touchable.disabled);

									System.out.println("ct6");
									CambiarTurno();
									peonalpaso = null;
									pgnPeonalPaso = "-";
									return true;
								}
							} else

							if (((Caballo) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {
								if (piezat == null) {
									return true;
								}
							}
						}
						if (pPieza.getTipoPieza() == eTipoPieza.Peon) {
							if (juego == 0) {
								if (((Peon) pPieza).Validar(mouseX, mouseY, (int) actorX, (int) actorY, (int) rangoX,
										(int) rangoY, (int) posX, (int) posY) == true) {
									
									jugarPeon((Peon) pPieza, pgni, pgn,duracion);
									if(jugandovsMangoPaola)
									{
										if (colorOponente.equals(Color.BLACK))
											{
												animacionMovimientoPatriotas(pPieza,pgni, pgn);
											}
										else
											{
												animacionMovimientoRealistas(pPieza,pgni, pgn);
											}
									}
								else
									{
										if(getTurno().equals(Color.WHITE))
										{
											animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else
										{
											animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									}
									setCasilla(pgn, pPieza, pPieza.getInd());
									deshacer.setTouchable(Touchable.disabled);
									sonido(pPieza, duracion);

									System.out.println("ct7");
									CambiarTurno();
									return true;
								}
								// else{System.out.println("hola como estas");}
							} else

							if (((Peon) pPieza).ValidarMinijuego1((int) posX, (int) posY) == true) {

								// if(piezat==null){
								EliminarPieza(pPieza, pgn);
								setCasilla(pgn, pPieza, pPieza.getInd());
								if (posY == tablero.GetFinY() || posY == tablero.GetInicioY()) {
									// PromoverPeon(pPieza, pgn);

									System.out.println("ct8");
									CambiarTurno();
									return true;
								} else {

									System.out.println("ct9");
									CambiarTurno();
									return true;
								}
							}
						}
					} else {
						// System.out.println("toque afuera de Y");
						if (tocarSonido){
							movIncorrecto.setVolume(movIncorrecto.play(),0.2f);
						}
						else{
//							System.out.println("MovimientoNoPermitido-1");
							mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.MovimientoNoPermitido);							
						}
						cuadroFin.setVisible(false);
						cuadroIni.setVisible(false);
						cambiarEstadoEsperando();
						return false;
					}

				} else {
					// System.out.println("toque afuera de X ");
					if (tocarSonido){
						movIncorrecto.setVolume(movIncorrecto.play(),0.2f);
					}
					else {
//						System.out.println("MovimientoNoPermitido-2");
						mostrarDialogo(eTipoDialogo.Aceptar,eTipoMensaje.MovimientoNoPermitido);
					}
					cuadroFin.setVisible(false);
					cuadroIni.setVisible(false);
					cambiarEstadoEsperando();
					return false;
				}
			}
			////
		}
		if (tocarSonido){
			movIncorrecto.setVolume(movIncorrecto.play(),0.2f);
		}
		else{
//			System.out.println("MovimientoNoPermitido-3");
			mostrarDialogo(eTipoDialogo.Aceptar,eTipoMensaje.MovimientoNoPermitido);
		}				
		return false;
	
	}

	/**
	 * asigna el tipo de pieza a el peon promovido
	 * 
	 * @param pPieza
	 *            es el peon que se va a promonver
	 * @param pPgni
	 *            el la posicion donde se encuentra el peon a promover
	 * @param pPgnf
	 *            es la posici�n donde va a llegar el peon a promover + el tipo
	 *            de pieza a promover q = queen, r = torre, n = caballo, b=
	 *            alfil
	 */
	public void setPiezaPromovida(Pieza pPieza, String pPgni, String pPgnf) {

		// enviarMangoPaola("move " + pPgni.toLowerCase() + pPgnf.toLowerCase()+
		// cPiezaPromovida);
		// cPiezaPromovida = "";
		Pieza vPieza = null;
//		System.out.println("pPgnf " + pPgnf);
		String vTipoPromovida = pPgnf.substring(2, 3);
		vTipoPromovida = vTipoPromovida.toLowerCase();
		// System.out.println("setPiezaPromovida VTipoPromovida " +
		// vTipoPromovida);
		// System.out.println("setPiezaPromovida " + pPgnf);
		// System.out.println(pPgnf + " " + cPiezaPromovida);

		String vPgnf = pPgnf.substring(0, 2);

		eColores color;
		if (pPieza.getColor().equals(Color.WHITE))
			color = eColores.Blancas;
		else
			color = eColores.Negras;
        
		EliminarPieza(pPieza, pPgni);// eliminar el peon actual
		pPieza.setTouchable(Touchable.disabled);
		pPieza.setVisible(false);
		// pPieza.setColor(Color.BLUE);
		borrarCasilla(pPieza.getInd());

		if (vTipoPromovida.contains("r")) {
			 Torre vTorre= new Torre(color, vPgnf, pPieza.getInd(), 0, this);
			 vTorre.setTipodePiezas(color, tipoDePiezas);
			 vPieza=vTorre;
		} else if (vTipoPromovida.contains("n")) {
			Caballo vCaballo = new Caballo(color, vPgnf, pPieza.getInd(), 0, this); 
			vCaballo.setTipodePiezas(color, tipoDePiezas);
			vPieza = vCaballo;						
		} else if (vTipoPromovida.contains("b")) {
			Alfil vAlfil = new Alfil(color, vPgnf, pPieza.getInd(), 0, this);
			vAlfil.setTipodePiezas(color, tipoDePiezas);
			vPieza = vAlfil; 			
		} else if (vTipoPromovida.contains("q")) {
			Reina vReina =  new Reina(color, vPgnf, pPieza.getInd(), 0, this); 
			vReina.setTipodePiezas(color, tipoDePiezas);
			vPieza = vReina;			
		}
		
		vPieza.setInd(pPieza.getInd()); 
		setCasilla(vPgnf, vPieza, vPieza.getInd());
		stage.addActor(vPieza);
		piezasPromovida.add(vPieza);

		cPiezaPromovida = null;
		
	}

	/**
	 * elimina una pieza del tablero
	 * 
	 * @param pPieza
	 *            pieza a eliminar
	 * @param pPgn
	 *            posicion de la casilla donde se encuentra la pieza
	 */
	private void EliminarPieza(Pieza pPieza, String pPgn) {
		if (pPgn.length() > 2) {
			// TODO manejar el error de pPgn
			//System.out.println("error en pgpn:" + pPgn);
			pPgn = pPgn.substring(0, 2);
		}
		Pieza piezat = casillaOcupada(pPgn);

		// piezat = pieza capturada
		// pPieza = pieza que va a capturar

		// LOG.log(Level.INFO, "Eliminando Pieza: " + pPieza.getName() + " pgn:
		// " + pPgn );
		if (piezat != null) {
			if (pPieza == null || piezat != pPieza && (!piezat.getColor().equals(pPieza.getColor()))) {
				piezat.setTouchable(Touchable.disabled);
			//	System.out.println("Eliminando : " + piezat.getName());
//				piezat.setVisible(false);
				 
				borrarCasilla(piezat.getInd());
//				piezat.remove();
				piezat.setVisible(false);
			}
		}
	}

	/**
	 * Metodo para eliminar la pieza que ocupa una casilla se resetea el valor
	 * de la casilla colocando una nueva casilla sin valores seteados
	 * 
	 * @param pInd
	 *            numero de la casilla a borrar
	 */
	public void borrarCasilla(int pInd) {
		casillas[pInd] = new Casilla();
	}

	/**
	 * metodo que obtiene y ejecuta la jugada del motor
	 */
	private void JugarMangoPaola() {

		if (!jaqueMate && !tablas) {
			// int i = 0 ;
			System.out.println("aquiiiiiiiiiii");
			varRes = resultadoJugarMangoPaola(enviarMangoPaola("go"));
			System.out.println("aquiiiiiiiiiii");
			// System.out.println("MangoPaola Juega: " + varRes);
			String pgnF = null, pgnI = null, checkFlag = "";
//			System.out.println("respuesta varRes : " + varRes);
					if (varRes == null) {
						jaqueMate=true;
					} else if (varRes.contains("move")) {
							pgnI = varRes.substring(5, 7).toUpperCase();
		//					System.out.println(varRes);
							String [] jugadaMango= varRes.split(" \\[");
							varRes= jugadaMango[0];
						//	System.out.println("varRes:"+ varRes + " length: " + varRes.length()); 
		//					System.out.println("jugadamango:"+ jugadaMango[1].split("\\]")[0]); 									
						switch (varRes.length()) {
						
						case 11:// "q+" promoci�n y check
							pgnF = varRes.substring(7, 10).toUpperCase();
							checkFlag = varRes.substring(10, 11).toUpperCase();					
							break;
						case 10:// "+"
							pgnF = varRes.substring(7, 9).toUpperCase();
							checkFlag = varRes.substring(9, 10).toUpperCase();
							break;
						case 9:
							pgnF = varRes.substring(7, 9).toUpperCase();
							break;
			
						default:
						//	System.out.println("Error en el length de la jugada:" + varRes.length());					
							break;
						}			
					//	System.out.println("jugada: " + pgnF);
						
					if (colorOponente.equals(Color.BLACK)){
		//				System.out.println("pintando letra negra");
						if (jugadasAlg.size()>0){
							String temp = jugadasAlg.get(jugadasAlg.size()-1);
		//					System.out.println("jugada Mango :"+jugadaMango[0]+jugadaMango[1]);
							if (!jugadaMango[1].split("\\]")[0].contains("result")){	
		//						System.out.println("jugada Mango :"+jugadaMango[1]+jugadaMango[2]);
								jugadasAlg.remove(jugadasAlg.size()-1);
								jugadasAlg.add(temp  + " " + jugadaMango[1].split("\\]")[0]);
							}
							else if (jugadaMango[1].split("\\]")[0].contains("Black")){
								//System.out.println(jugadaMango[2].split("\\]")[0]);
									jugadasAlg.remove(jugadasAlg.size()-1);
									
									jugadasAlg.add(temp  + " " + jugadaMango[2].split("\\]")[0] );							
								}
								else{ // draw
									jugadasAlg.remove(jugadasAlg.size()-1);
									jugadasAlg.add(temp  + " " + (" 1/2 - 1/2") );							
								}
						}				
					}
					else{	
					//if (jugadasAlg.size()>0){			
						if (!jugadaMango[1].split("\\]")[0].contains("result")){											
							jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaMango[1].split("\\]")[0],15));
						}
						else if (jugadaMango[1].split("\\]")[0].contains("White")){
								jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaMango[2].split("\\]")[0],15));						
							}
							else{//draw
								jugadasAlg.add((" 1/2 - 1/2") );						
							}
						}
					//}
					
			listaDeJugadas.setItems(jugadasAlg.toArray());
			listaDeJugadas.setSelectedIndex(jugadasAlg.size() - 1);
									
			obtenerFen();
						
			jugadas.add(pgnI + " - " + pgnF);
			boolean peonpromovido = false;
			int Actor[] = tablero.Pgn2XY(pgnI);
			float actorX = Actor[0];
			float actorY = Actor[1];
			int pos[] = tablero.Pgn2XY(pgnF);
			float posX = pos[0];
			float posY = pos[1];
			double distancia = Math.sqrt((Math.pow(posX - actorX, 2)) + Math.pow(posY - actorY, 2));
			final float duracion = (float) distancia / ((casilla.GetAnchoCasilla() + casilla.GetAltoCasilla()) / 2);
			// System.out.println("MangoPaola Juega: " + pgnI+pgnF);

			//
			Pieza pieza = casillaOcupada(pgnI); // saber la pieza que voy a	mover
				if(flagSonido==true){
					sonido(pieza, duracion);
				}
			dibujarCuadro(pgnI, pgnF);
			pane.scrollTo(0,0 ,0, 0);
			//System.out.println("Mango paola Juega:" + pieza + " : " + pgnI + " - " + pgnF);
				if (colorOponente.equals(Color.BLACK)) {
				animacionMovimientoRealistas(pieza,pgnI, pgnF);
				}
				else{
				animacionMovimientoPatriotas(pieza,pgnI, pgnF);
				}
			pieza.move((tablero.Pgn2XY(pgnF.substring(0, 2))[0]), (tablero.Pgn2XY(pgnF.substring(0, 2))[1]));

			Timer.schedule(new Task(){@Override public void run() {deshacer.setTouchable(Touchable.enabled);}}, 0.5f * duracion);

			if (pieza.getTipoPieza() == eTipoPieza.Rey) 
					{
						((Rey) pieza).Validar(tablero.Pgn2XY(pgnF)[0], tablero.Pgn2XY(pgnF)[1], tablero.Pgn2XY(pgnI)[0],
						tablero.Pgn2XY(pgnI)[1], 0, 0, tablero.Pgn2XY(pgnF)[0], tablero.Pgn2XY(pgnF)[1]);
						if (((Rey) pieza).isEnrocado() == true)
								{
									verificarEnroque((Rey) pieza, pgnI, pgnF);
								}
						((Rey) pieza).setEnrocado(false);

					}
			
			if (pieza.getTipoPieza() == eTipoPieza.Peon){
						if (!tablero.getTableroRotado()){
								if (((tablero.Pgn2XY(pgnF.substring(0, 2))[1] == tablero.GetFinY() && pieza.getColor().equals(Color.WHITE))
									||(tablero.Pgn2XY(pgnF.substring(0, 2))[1] == tablero.GetInicioY() && pieza.getColor().equals(Color.BLACK)))){
//								System.out.println("va a promover");
										jugarPiezaPromovida(pieza, pgnI, pgnF + varRes.substring(9, 10));
	//							System.out.println("fin promover");
									peonpromovido = true;
							}
						}
						else{
							if (((tablero.Pgn2XY(pgnF.substring(0, 2))[1] == tablero.GetInicioY() && pieza.getColor().equals(Color.WHITE))
							||( tablero.Pgn2XY(pgnF.substring(0, 2))[1] == tablero.GetFinY() && pieza.getColor().equals(Color.BLACK)))){
		//							System.out.println("va a promover2");
								jugarPiezaPromovida(pieza, pgnI, pgnF + varRes.substring(9, 10));
			//						System.out.println("fin promover2");
								peonpromovido = true;
							}
						}


						if (!peonpromovido) 
							{
								jugarPeon((Peon) pieza, pgnI, pgnF,duracion);
							}
					} 
			else{
				peonalpaso = null;
				pgnPeonalPaso = "-";
//						EliminarPieza(pieza, pgnF);						
			}

				if (!peonpromovido)
						{
					if(casillaEstaOcupada(pgnF))
					{
						if(pieza.getTipoPieza().equals(eTipoPieza.Torre)){
						animacionCapturando(pieza,TipoDeActor.CANON,pgnI,pgnF,duracion);
						}
						else
						if(pieza.getTipoPieza().equals(eTipoPieza.Alfil)){
						animacionCapturando(pieza,TipoDeActor.FLECHA,pgnI,pgnF,duracion);
						}
						else
						if(pieza.getTipoPieza().equals(eTipoPieza.Caballo)){
						animacionCapturando(pieza,TipoDeActor.LANZA,pgnI,pgnF,duracion);
						}
						else
						if(pieza.getTipoPieza().equals(eTipoPieza.Reina)){
						animacionCapturando(pieza,TipoDeActor.MAGIA,pgnI,pgnF,duracion);
						}
						else
						if(pieza.getTipoPieza().equals(eTipoPieza.Peon)){
						}
						else
						if(pieza.getTipoPieza().equals(eTipoPieza.Rey)){
						animacionCapturando(pieza,TipoDeActor.CETRO,pgnI,pgnF,duracion);
						}
						else{
						animacionCapturando(pieza,TipoDeActor.MAGIA,pgnI,pgnF,duracion);
						}
							}
						else
							{
								EliminarPieza(pieza, pgnF);
								setCasilla(pgnF, pieza, pieza.getInd());
							}
						} 
				else 
						{
					
	//						System.out.println("Error, no seteo la casilla por que promovi un peon.. seg�n...");
						}
	
//			System.out.println("JugarMangoPaola() pgnf:" + pgnF);

			if (checkFlag.equals("++")||jaqueMate){ // cuando gana el motor
				if(historia){						
						if (!minijuego.contains("Minijuego27")){
							Timer.schedule(new Task() {@Override public void run(){					
										cortinaFondo.stop();
										bPerder=true;
										System.out.println("ct10");
										CambiarTurno();
									//	perder();
										//mostrarDialogo(eTipoDialogo.Mensaje,  eTipoMensaje.JuegoFinalizadoDerrota);
									}}, 0.45f * duracion);
							if (tocarCortinas){
									cortinaDerrota.play();
							}
						}				
						else{
							mostrarDialogo(eTipoDialogo.Mensaje,eTipoMensaje.MensajeSecretoDerrotaParcial);					
						}
				}
				else {
					bPerder = true;
					bGanar=false;
					System.out.println("ct11");
					CambiarTurno();
					//perder();
				}			
			}
			else if (tablas){
				bTablas = true;

				System.out.println("ct12");
				CambiarTurno();
				//tablas();
			}						
			else if (checkFlag.contains("+")){
		//				System.out.println("aca entro y no deberia");
						mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.ReyenJaque);
						casillaJaque= new ActorExtra(this, TipoDeActor.CUADROPOSIBLESCAPTURAS, 0);	
						String pgnRey=casillaOcupadaRey();
//						System.out.println("pgn rey : "+pgnRey);
						casillaJaque.setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
						casillaJaque.setPosition(tablero.Pgn2XY(pgnRey)[0], tablero.Pgn2XY(pgnRey)[1]);
						stage.addActor(casillaJaque);
						casillaJaque.setZIndex(0);
					}

			System.out.println("ct13");
			CambiarTurno();
			}
		}
		else { 
			if(jaqueMate){
				if (historia){
					if (minijuego.contains("Minijuego27")){
						reintentar(false);				 		
					}	
				}
				else{
					cortinaFondo.stop();
					if (!bPerder)
					bGanar = true;

					//ganar();
				}
			}
			else if(tablas) {
					if (historia){
						if (minijuego.contains("Minijuego27")){
							reintentar(false);				 		
						}	
					}
					else{
						cortinaFondo.stop();
						bTablas=true;

						//tablas();
					}
			}
//			else if(jaqueMate){
//					if (historia){
//						if (minijuego.contains("Minijuego27")){
//							reintentar(false);				 		
//						}
//					else
//						{
//						bPerder = true;
//
//						}
//					}
//			}		


			System.out.println("ct17a");
			System.out.println("bganar:"+ bGanar);
			System.out.println("bperder:"+bPerder);
			System.out.println("ct17b");
			
			CambiarTurno();
		}

	}

	/*
	 * private void reiniciarMangoPaola(){ // todo, arreglar
	 * esto--conversacion.destroy();
	 * //System.out.println("reiniciando mango Paola"); //conversacion = new
	 * Conversacion(); //conversacion.start(); // while
	 * (conversacion.isArriba()== false){ //
	 * System.out.println("esperando subir conversacion"); //}
	 * 
	 * }
	 */

	/**
	 * valida con el motor si la jugada que desea realizar el usuario es
	 * correcta
	 * 
	 * @param pPgnI
	 *            escaque inicial donde se encuentra la pieza a mover
	 * @param pPgnF
	 *            casilla fina hacia donde va el movimiento
	 * @return
	 */
	private boolean validarMotor(String pPgnI, String pPgnF) {
		
		//String comando = "move " + pPgnI + pPgnF;
		comando = pPgnI + pPgnF;
	System.out.println("move " + pPgnI + pPgnF);

		/*
		 * while (condicionAppend = false);{// comando != null);{ for (int i =0;
		 * i < varComandos.length; i++){ varComandos[i]= comando;
		 * if(varComandos[i] == ""){ condicionAppend = true; } } }
		 */
		// enviarMangoPaola("dale");

//		System.out.println("Jugador Moviendo " + pPgnI + " - " + pPgnF);
		dibujarCuadro(pPgnI, pPgnF);

		resultadoValidarMotor(enviarMangoPaola(("?")));
		jugadaHumano=enviarMangoPaola(comando.toLowerCase());
		System.out.println("mov "+enviarMangoPaola(comando.toLowerCase()));
//		System.out.println("valida"+jugadaHumano.size());
		
		boolean vRes = resultadoValidarMotor(jugadaHumano);

		
//		if (vRes && (Turno.equals(colorOponente)|| jugandovsMangoPaola) ^
//				   (vRes && !Turno.equals(colorOponente) && movimientosMedios > 0 && !jugandovsMangoPaola)){

//		if ((vRes&&jugandovsMangoPaola) ^ (vRes&&Turno.equals(colorOponente)&&!jugandovsMangoPaola&&tablero.getTableroRotado())||			
//										 (vRes&&!Turno.equals(colorOponente)&&!jugandovsMangoPaola&&!tablero.getTableroRotado())){							
//			System.out.println("guardarndo casillas");
//			cambiarEstadoEsperando();
//			if (!ultimaJugadaGuardada ){
//				casillasDeshacer = new Casilla[32];
//				for (int i = 0; i <= 31; i++)
//					casillasDeshacer[i] = casillas[i];			
//				
//					listaCasillasDeshacer.add(casillasDeshacer);
//					
//			}
//			else
//				ultimaJugadaGuardada = false;
//		}

		return vRes;
	}

//	private boolean validarMotor(String pAlg ) {
//
//		// String comando = "move " + pPgnI + pPgnF;
//		comando = pAlg;
//
//		/*
//		 * while (condicionAppend = false);{// comando != null);{ for (int i =0;
//		 * i < varComandos.length; i++){ varComandos[i]= comando;
//		 * if(varComandos[i] == ""){ condicionAppend = true; } } }
//		 */
//		// enviarMangoPaola("dale");
//
////		System.out.println("Jugador Moviendo " + pPgnI + " - " + pPgnF);
//		//dibujarCuadro(pPgnI, pPgnF);
//
//		resultadoValidarMotor(enviarMangoPaola(("?")));
//		jugadaHumano=enviarMangoPaola(comando.toLowerCase());
////		System.out.println("valida"+jugadaHumano.size());
//		return resultadoValidarMotor(jugadaHumano);
//	}

	/**
	 * env�a un comando al motor
	 * 
	 * @param pComando
	 *            comando a ser ejecucatodo por el motor
	 * @return lista con las respuesta del motor
	 */
	private ArrayList<String> enviarMangoPaola(String pComando) {
//		System.out.println("Enviando: " + pComando);

		String fen = null;
		if (pComando != "go") {
			fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";
		} else {
			fen = obtenerFen();
		}
		try {
			return conversacion.recibirComando(pComando, fen, valorDificultad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * evalua el resultado de la validaci�n del motor
	 * 
	 * @param lista
	 *            de respuesta del motor a un comando
	 * @return
	 */

	private boolean resultadoValidarMotor(ArrayList<String> pRes) {
		// System.out.println("resultadoValidarMangoPaola in");
		try {
			// todo s�lo para el desarrollo
			//
			// System.out.println("procesando item");
			for (String item : pRes) {
				// System.out.println(item);
				if (item.contains("Illegal move") || item.contains("COD"))
					return false;
				if (item.contains("Black mates") || item.contains("White mates"))
				{

					jaqueMate = true;
				}
					else {
					if (item.contains("Draw") || item.contains("Draw by repetition")) {
					//	System.out.println("tablas");
						if (historia)	{						
							bPerder = true; //perder();		

							System.out.println("ct18");
						CambiarTurno();
						}
						else{
							bTablas=true; //tablas();

							System.out.println("ct19");
							CambiarTurno();
						}
						tablas = true;					
					}
				}
			}
		} catch (Exception e) {
			// System.out.println("resultado Mango Paogla exception " +
			// e.getMessage());
		}
		return true;
	}

	/**
	 * evalua el resultado del juego del motor
	 * 
	 * @param pRes
	 *            lista de respuesta del motor
	 * @return es el resultado de la jugada del motor
	 */
	private String resultadoJugarMangoPaola(ArrayList<String> pRes) {
		String aux = null;

		if (pRes != null)
			for (String item : pRes) {

//				System.out.println("pRes:" + pRes);
				if (item.contains("Black mates") || item.contains("White mates")) {
//					System.out.println("Mate");
					jaqueMate = true;
				}
				else {
					if (item.contains("Draw") || item.contains("Draw by repetition")) {
//						System.out.println("tablas");
						tablas = true;
					}
				}
				aux = item;
				
//				 System.out.println("resultadoJugarMangoPaola: " + aux);
			}

		return aux;
	}

	/*
	 * private String FenInicial() { return
	 * "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq � 0 1"; }
	 */

	/**
	 * de acuerdo al moviento ejecutado por el rey, verifica y completa el
	 * enroque
	 * 
	 * @param pPieza
	 *            es el rey que se desea enrrocar
	 * @param pPgnI
	 *            es la posicion pgn donde se encuentra el rey
	 * @param pPgnF
	 *            es la posicion final hasta donde llega el movimiento del rey
	 */
	private void verificarEnroque(Rey pPieza, String pPgnI, String pPgnF) {

		Pieza vPieza = (Pieza) pPieza.getTorreMovida()[0];
		String vPgn = (String) pPieza.getTorreMovida()[1];

		if (pPieza.isEnrocado() == true) {

//			System.out.println("entre en pieza != null");
			setCasilla(vPgn, vPieza, vPieza.getInd());
//			System.out.println("seteo de casilla");
		} else {
			System.out.println("Error, explote");// TODO
		}
	}

	/**
	 * retorna si se puede jugar o no dependiendo de si todas las piezas hayan
	 * terminado de moverse en el tablero
	 * 
	 * @return
	 */

	private boolean jugar() {
		int i = 0;
try {
	for (Actor miActor : stage.getActors()) {
		i = miActor.getActions().size + i;
	}
	if (i == 0) {
		return true;
	}
	return false;
} catch (Exception e) {
	return false;
}
		
	}

	/**
	 * metodo para reliazar las posibles jugadas del pe�n
	 * 
	 * @param pPeon
	 *            pieza que se est� moviendo
	 * @param pActorX
	 *            coordenada x donde se encuentra el pe�n
	 * @param pActorY
	 *            coordenada y donde se encuenta el pe�n
	 * @param pPosX
	 *            coordenada x donde se desea mover el pe�n
	 * @param pPosY
	 */
	private void jugarPeon(Peon pPeon, String pPgni, String pPgnf,float pDuracion) {

		movimientosMedios = 0;
		if (casillaOcupada(pPgnf) != null) {
			animacionCapturando(pPeon,TipoDeActor.MACHETE, pPgni, pPgnf, pDuracion);
			
			
		} 
		else if (peonalpaso != null) {
		
//			System.out.println("peon al paso != null");
			String pgnpp = casillaOcupada(peonalpaso).toUpperCase();
//			System.out.println("Captura de peon al paso inicio" + pPgni + " fin: " + pPgnf + "peonalpaso:" + pgnpp);

			if (Math.abs(pPgni.charAt(0) - pgnpp.charAt(0)) == 1 // peon al paso
																	// al lado
																	// del peon
																	// movido
					&& pPgnf.charAt(0) - pgnpp.charAt(0) == 0 && Math.abs(pPgnf.charAt(1) - pgnpp.charAt(1)) == 1) {

//				System.out.println("Captura de peon al paso: " + peonalpaso.toString());
				
				EliminarPieza(pPeon, casillaOcupada(peonalpaso));
			}
		}
		
//		System.out.println((int) pPgni.charAt(1) + " - " + Math.abs(pPgnf.charAt(1)));
	
		if (Math.abs(pPgni.charAt(1) - pPgnf.charAt(1)) == 2) {
//			System.out.println("Peon al Paso " + pPeon.getName());
			peonalpaso = pPeon;
			pgnPeonalPaso = pPgnf;
		} else {
			peonalpaso = null;
			pgnPeonalPaso = "-";
		}

	}

	/**
	 * muestra un dialogo para escoger la pieza a ser promovida por el pe�n
	 * 
	 * @param pPieza
	 *            es el pe�n a ser promovido
	 * @param pPgni
	 *            posici�n inicial del pe�n
	 * @param pPgnf
	 *            posici�n final del pe�n
	 */
	public void EscogerPromovida(final Pieza pPieza, final String pPgni, final String pPgnf) {
//		for (int i = 0; i <= 31; i++)
//			casillasDeshacer[i] = casillas[i];		
//		listaCasillasDeshacer.add(casillasDeshacer);

		
	//	ArrayList<String> mensajes =  new ArrayList<String>();
		//String mensajeDialogo = "Seleccione una de las piezas";
		Texture[] TexturasPromocion = new Texture[4];
		
		
		if (tipoDePiezas.equals("MiniHeroe")){
			if (Turno.equals(Color.WHITE)){	
				TexturasPromocion[0] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/DamaG.png");
				TexturasPromocion[1] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/TorreG.png");
				TexturasPromocion[2] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/AlfilG.png");
				TexturasPromocion[3] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoG.png");
			}
			else {
				TexturasPromocion[0] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/dama/DamaG.png");
				TexturasPromocion[1] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/torre/TorreG.png");
				TexturasPromocion[2] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/alfil/AlfilG.png");
				TexturasPromocion[3] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoG.png");			
			}
		}
		else{
			if (Turno.equals(Color.WHITE)){	
				TexturasPromocion[0] = game.getManager().get("assets/Texturas/PiezasClasicas/Reina.png");
				TexturasPromocion[1] = game.getManager().get("assets/Texturas/PiezasClasicas/Torre.png");
				TexturasPromocion[2] = game.getManager().get("assets/Texturas/PiezasClasicas/Alfil.png");
				TexturasPromocion[3] = game.getManager().get("assets/Texturas/PiezasClasicas/Caballo.png");
			}
			else {
				TexturasPromocion[0] = game.getManager().get("assets/Texturas/PiezasClasicas/ReinaN.png");
				TexturasPromocion[1] = game.getManager().get("assets/Texturas/PiezasClasicas/TorreN.png");
				TexturasPromocion[2] = game.getManager().get("assets/Texturas/PiezasClasicas/AlfilN.png");
				TexturasPromocion[3] = game.getManager().get("assets/Texturas/PiezasClasicas/CaballoN.png");			
			}						
		}
										
		
		final Dialogo dialogo = new Dialogo(TexturasPromocion);

		dialogo.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
//				System.out.println("valor 1");
				if (dialogo.getResult() != null) {
//					System.out.println("valor 2");
					if (dialogo.getResult().equals(true)) {
						//System.out.println("valor del dialogo: " +  (Integer)dialogo.getValue());

						switch ((Integer)dialogo.getValue()) {
						case 0:
//							System.out.println("cPiezaPromovida " + 0);
							cPiezaPromovida =  "q";
							break;
						case 1:
//							System.out.println("cPiezaPromovida " + 1);
							cPiezaPromovida =  "q";			
							break;
						case 2:
//							System.out.println("cPiezaPromovida " + 2);
							cPiezaPromovida = "r"; 
							break;
						case 3:
//							System.out.println("cPiezaPromovida " + 3);
							cPiezaPromovida = "b";
							
							break;	
						case 4:
//							System.out.println("cPiezaPromovida " + 4);
							cPiezaPromovida = "n";
							break;
					
						default:
//							System.out.println("cPiezaPromovida default" );
//							System.out.println("valor escoger promovida" + Integer.parseInt(dialogo.getValue().toString()));
							cPiezaPromovida =  "q";
							break;						
						}
						dialogo.remove();
//						System.out.println("valor escoger promovida1 " + Integer.parseInt(dialogo.getValue().toString()));					
						jugarPiezaPromovida(pPieza, pPgni, pPgnf + cPiezaPromovida);
//						System.out.println("cPiezaPromovida: " + cPiezaPromovida);															
			}
				}
				}
		});						

		dialogo.setWidth(360);
		dialogo.setHeight(400);
		dialogo.align(Align.center | Align.top);
		stage.addActor(dialogo);
		dialogo.setPosition(super.gameWidth / 2 - dialogo.getWidth() / 2,
				super.gameHeight / 2 - dialogo.getHeight() / 2);

	}

	/**
	 * realiza el movimiento del peon a promover
	 * 
	 * @param pPieza
	 *            peon a promovido
	 * @param pPgni
	 *            posicion inicial donde se encuentra el pe�n
	 * @param pPgnf
	 *            posici�n final hacia donde va el pe�n + el tipo de pieza a
	 *            promover
	 */

	private void jugarPiezaPromovida(Pieza pPieza, String pPgni, String pPgnf) {
			
//		System.out.println("jugarPiezaPromovida in: " + pPgnf);
		if (!pPieza.getColor().equals(colorOponente) || !jugandovsMangoPaola) {
			if (!validarMotor(pPgni, pPgnf)) {
				cPiezaPromovida = null;
				cuadroFin.setVisible(false);
				cuadroIni.setVisible(false);
			//	System.out.println("Error, no se puede jugar:" + pPieza.getColor());
				if (tocarSonido){
					movIncorrecto.setVolume(movIncorrecto.play(),0.2f);
					mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.MovimientoIlegal);
				}
				else {
				//	System.out.println("Error, movimiento ilegal");
					mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.MovimientoIlegal);									
				}																
				return ;

				// System.out.println("no se puede jugar:" + pPieza.getColor());
			} else {
				// System.out.println("jugarPiezaPromovida turno mangoPaola");
			
				colocarJugadaAlg(pPgni, pPgnf);

				EliminarPieza(pPieza, pPgnf);
				setCasilla(pPgnf, pPieza, pPieza.getInd());

				if (pPieza.getTipoPieza() == eTipoPieza.Peon) {					
					setPiezaPromovida(pPieza, pPgni, pPgnf);
				}
				
				cPiezaPromovida = null;

				System.out.println("ct20");
				CambiarTurno();// cambia de turno cuando es una pieza promovida
			}
		} else {
			if (jugandovsMangoPaola && pPieza.getColor().equals(colorOponente)) {
				EliminarPieza(pPieza, pPgnf);
				setPiezaPromovida(pPieza, pPgni, pPgnf);
			}
		}
	}

	/**
	 * dialogo para informar que la partida ha finalizado, puedes elegir jugar
	 * de nuevo o salir
	 * 
	 * @param estatus
	 *            informa el si el usuario gan� o perdi�
	 */
	public void partidaFinalizada(String estatus) {

		Dialog dialog = new Dialog(estatus, skin2) {
			{
				button("Salir", "S");
				button("Reiniciar", "R");
			}

			@Override
			protected void result(Object object) {
				String str = object.toString();
				// System.out.println("str: " + str);
				if (str.equals("S")) {
				 if (historia)

				 {
					 game.setScreen(game.mapMin);
						 
				 }
				 else {
					 nuevaPartida();
					 //game.setScreen(game.juegoPrincipal);
					
				}
					
				} else if (str.equals("R")) {
					jaqueMate = false;
					nuevaPartida();
					//game.setScreen(game.juegoPrincipal);
				}
			}
		};
		if (Turno.equals(Color.WHITE)) {
			dialog.setColor(Color.BLACK);
		} else {
			dialog.setColor(Color.WHITE);
		}
		dialog.setVisible(true);
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.show(stage);
	}

	/**
	 * obtiene un fen actual del tablero de juego
	 * 
	 * @return String con el fen actual
	 */

	private String obtenerFen() {
		Fen fen = new Fen(this, 0);
		listaDeJugadas.setItems(jugadasAlg.toArray());
//		System.out.print("jugadasAlg.size()" + jugadasAlg.size());
		listaDeJugadas.setSelectedIndex(jugadasAlg.size() - 1);
		return fen.obtenerFen(casillas, getTurno(), 32, 64, 8, pgnPeonalPaso, movimientosMedios, movimientosCompletos);

	}

	@Override
	public void pause() {
		System.out.println("pausaaaaaaaa");
		flagSonido=false;
//		System.out.println();
		Timer.instance().clear();
		if (reyMovimiento != null) {
			reyMovimiento.pause();
		}
		if (reinaMovimiento != null) {
			reinaMovimiento.pause();
		}
		if (torreMovimiento != null) {
			torreMovimiento.pause();
		}
		if (alfilMovimiento != null) {
			alfilMovimiento.pause();
		}
		if (caballoMovimiento != null) {
			caballoMovimiento.pause();
		}
		if (peonMovimiento != null) {
			peonMovimiento.pause();
		}
		if (cortinaFondo != null) {
			cortinaFondo.pause();
		}
		super.pause();
	}

	@Override
	public void resume() {
		Timer.instance().clear();
		flagSonido=true;
		if (reyMovimiento != null) {
			reyMovimiento.pause();
		}
		if (reinaMovimiento != null) {
			reinaMovimiento.pause();
		}
		if (torreMovimiento != null) {
			torreMovimiento.pause();
		}
		if (alfilMovimiento != null) {
			alfilMovimiento.pause();
		}
		if (caballoMovimiento != null) {
			caballoMovimiento.pause();
		}
		if (peonMovimiento != null) {
			peonMovimiento.pause();
		}
		for(Actor miActor:stage.getActors())
			{
				if (miActor.getName() != null ) 
					{
						((Pieza) miActor).cambiarEstadoPieza(eEstadoPieza.Esperando);
					}
			}
		if(actorExtra!=null && actorExtra.isVisible()){
			actorExtra.setVisible(false);
			}
		Preferencias(true);	
		super.resume();
	}
	/*
	 * private void mostrarFen(String pFen){
	 * 
	 * }
	 */

	@Override
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * deshace las �ltimas dos jugadas realizadas por el usuario y el motor
	 */

	public void deshacer() {

	//	System.out.println("jugadasAlg: " + jugadasAlg.size() + " listaCasillasDeshacer: " + listaCasillasDeshacer.size());
		//System.out.println("casillas:" + casillas.length);
		
		cPiezaPromovida = null;
		
//		if (ultimaJugadaGuardada){
//			listaCasillasDeshacer.remove(listaCasillasDeshacer.size()-1);
//			
//		}
		
	
		if (jaqueMate) {
				
			System.out.println("cambiando a false");
			jaqueMate=false;
			bGanar=false;
			bPerder=false;
			bTablas=false;
			setTurno(Color.WHITE);
			listaCasillasDeshacer.remove(listaCasillasDeshacer.size()-1); 
		}
		
		motorIniciado=true;
	
		if (listaCasillasDeshacer.size() > 1) {
			for (Actor vActor : stage.getActors()) {
				try {
					if (((Pieza) vActor).getInd() >= 0) {
						vActor.setVisible(false);
						vActor.setTouchable(Touchable.disabled);
					}
				} catch (Exception e) {

				}
			}

			for (int i = 0; i <= 31; i++) {
				casillas[i] = listaCasillasDeshacer.get(listaCasillasDeshacer.size() - 2)[i];
				try {
					Pieza vPieza = casillas[i].getPieza();
					if (vPieza != null) {
						vPieza.setVisible(true);
						vPieza.setInd(i);
						vPieza.setPosPgn(casillas[i].getPgn());

						// if (Turno.equals(colorActivas)){
						vPieza.setTouchable(Touchable.enabled);
						//casillas[i].setPieza(vPieza);
						
						// }
					}
				} catch (Exception e) {
					System.out.println("Error: " +e);
					// TODO: handle exception
				}

			}
			cambioTipoPieza(tipoDePiezas);
			listaCasillasDeshacer.remove(listaCasillasDeshacer.size() - 1);

//			jugadas.remove(jugadas.size() - 1);
			jugadas.remove(jugadas.size() - 1);
			if (colorOponente.equals(Color.WHITE)&&jugadasAlg.size()==2)
			{
				jugadasAlg.remove(jugadasAlg.size()-1);
//				System.out.println("entre a deshacer primera jugada contricante negro");
				String temp= jugadasAlg.get(0).split(" ")[0];
				jugadasAlg.remove(jugadasAlg.size()-1);
				jugadasAlg.add(temp);
			}
			else
			{
//				System.out.println("entre a deshacer normal");
			
				jugadasAlg.remove(jugadasAlg.size()-1);
			}
			listaDeJugadas.setItems(jugadasAlg.toArray());
			listaDeJugadas.setSelectedIndex(jugadasAlg.size() - 1);
		
			if (jugadas.size() > 1) {
				String ultimoPGN = jugadas.get(jugadas.size() - 1);
				String[] parts = ultimoPGN.split("-");
				String pgnI = parts[0].trim();
				String pgnF = parts[1].trim();
				dibujarCuadro(pgnI, pgnF);
			} else {
				cuadroIni.setVisible(false);
				cuadroFin.setVisible(false);
			}
			
		//	CambiarTurno();

//			if (tablero.getTableroRotado())
//				CambiarTurno();

			
				if (Turno.equals(Color.WHITE)){
//					System.out.println("habilitando las blancas");
					habilitarPiezas(eColores.Blancas);
				}
				else{
					habilitarPiezas(eColores.Negras);
	//				System.out.println("habilitando las negras");
				}

			enviarMangoPaola("remove");

			System.out.println("fen despues de remover: " + obtenerFen());
		} else {
			deshacer.setTouchable(Touchable.disabled);
		}
		cambiarEstadoEsperando();
//
	}

	/**
	 * Metodo para que se emitan sonidos segun la pieza jugada
	 * 
	 * @param pieza
	 * @param duracion
	 */
	public void sonido(Pieza pieza, float duracion) {
		if (tocarSonido) {
			if (!jugandovsMangoPaola) {
				if (!Turno.equals(colorOponente)^ tablero.getTableroRotado()) {
					Timer.schedule(new Task() {
						@Override
						public void run() {
							deshacer.setTouchable(Touchable.enabled);
						}
					}, 0.5f * duracion);
				} else {
					Timer.schedule(new Task() {
						@Override
						public void run() {
							deshacer.setTouchable(Touchable.disabled);
						}
					}, 0.5f * duracion);
				}
			}
		

		if (pieza.toString().equals("peon")) {
			final long id = peonMovimiento.loop();
			peonMovimiento.play();
			Timer.schedule(new Task() {
				@Override
				public void run() {
					peonMovimiento.stop(id);
				}
			}, 0.4f * duracion);
		} else if (pieza.toString().equals("caballo")) {
			final long id = caballoMovimiento.loop();
			caballoMovimiento.play();
			Timer.schedule(new Task() {
				@Override
				public void run() {
					caballoMovimiento.stop(id);
				}
			}, 0.4f * duracion);
		} else if (pieza.toString().equals("alfil")) {
			final long id = alfilMovimiento.loop();
			alfilMovimiento.play();
			Timer.schedule(new Task() {
				@Override
				public void run() {
					alfilMovimiento.stop(id);
				}
			}, 0.4f * duracion);
		} else if (pieza.toString().equals("torre")) {
			final long id = torreMovimiento.loop();
			torreMovimiento.play();
			Timer.schedule(new Task() {
				@Override
				public void run() {
					torreMovimiento.stop(id);
				}
			}, 0.4f * duracion);
		} else if (pieza.toString().equals("rey")) {
			final long id = reyMovimiento.loop();
			reyMovimiento.play();
			Timer.schedule(new Task() {
				@Override
				public void run() {
					reyMovimiento.stop(id);
				}
			}, 0.4f * duracion);
		} else if (pieza.toString().equals("reina")) {
			final long id = reinaMovimiento.loop();
			reinaMovimiento.play();
			Timer.schedule(new Task() {
				@Override
				public void run() {
					reinaMovimiento.stop(id);
				}
			}, 0.4f * duracion);
		} else if (pieza.toString() == null) {
			System.out.println("Error no Pieza Null");
		}
	}
}

	/**
	 * cambia el estado de las piezas a esperando, para poder seleccionarlas
	 */
	private void cambiarEstadoEsperando() {

		for(int j=0;j<32;j++)
		{
			if(casillas[j]!=null && casillas[j].getPieza()!=null)
			{
				Pieza pPieza=casillas[j].getPieza();
				pPieza.setEstadoPieza(eEstadoPieza.Esperando);
				pPieza.cambiarEstadoPieza(eEstadoPieza.Esperando);
			
			}
	}	
		
//		for (Actor miActor : stage.getActors()) {
//			if (miActor.getName() != null) {
//				try {
//					((Pieza) miActor).setEstadoPieza(eEstadoPieza.Esperando);
//					((Pieza) miActor).cambiarEstadoPieza(eEstadoPieza.Esperando);
//				} catch (Exception ex) {
//
//				}
//			}
//		}

	}

	/**
	 * cargar un fen, si el usuario ingres� un fen a trav�s del dialogo, se
	 * carga, de los contrario se carga el fen inicial
	 */
	private void CargarFen() {
		boolean juegoNuevo = true;
		casillas = new Casilla[32];

		Fen vFen = new Fen(this, 0);

		if (historia){						
			enviarMangoPaola("book 0");
				
			try {
					if (pasoPreferencia)
					{
						pasoPreferencia=false;
						vStringFen = txcargaFen;
						System.out.println("entre aca..."+ vStringFen);
					}
						else
						{
					vStringFen = configuracion.GetAtributo(batalla, "Minijuego", minijuego,	 "Fen");
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("error fen :"+ e);
					
				}
				System.out.println("vstringFen : "+vStringFen);
				if (vStringFen.contains(".xml")){// es un archivos con muchos fenes
					vStringFen = obtenerFenExtra(valorDificultad, vStringFen);
				}
		}
		else
		{System.out.println("txcargafen : "+txcargaFen);
			if (txcargaFen == null||txcargaFen.equals("")) {
				jugadas = new ArrayList<String>();
				jugadasAlg = new ArrayList<String>();
				vStringFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";
				juegoNuevo = true;
			//	vStringFen = "4k3/ppp5/8/8/8/8/PPPPPPPP/4K3 w - 0 0";
			} else {
				//listaCasillasDeshacer = pasarCasillas(listaCasillasDeshacerp);
				verlista(listaCasillasDeshacer);
				vStringFen = txcargaFen;				
				juegoNuevo = false;
			}
		}
		
//		System.out.println("string del fen" + vStringFen);

		/**
		 * FEN inicial rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
		 * 
		 * Otros FEN r2qr3/ppp2pkp/2np1ppn/8/3P1P1N/P1N1PQ2/1PP2K2/R1B4R w - 0 1
		 * r1bq1rk1/ppp2ppp/2n5/2bPp3/8/3P1P2/PPP3PP/RNBQKB1R w
		 * k7/8/8/8/8/8/8/7K w
		 */

		// System.out.println("FENgbgg : " + vFen.Fen2Pgn(vStringFen,
		// 32).split("/"));
		try{
			System.out.println("fen:"+ vStringFen);
		String vTurno[] = vFen.Fen2Pgn(vStringFen, 32).split("/");

		for (int i = 0; i <= 31; i++) {
			// Actor vActor = casillas[i].getPieza();
			// vActor.setVisible(true);
			try {
				if (casillas[i].getPieza() != null) {
					stage.addActor(casillas[i].getPieza());
					// System.out.println(casillas[i].getPieza().toString() + "
					// - " + casillas[i].getPgn());
				}

			} catch (Exception e) {
				// TODO: handle exception
				casillas[i] = new Casilla();

			}
			// casillas[i].setPgn(casillas[i].getPgn());
		}
System.out.println("llegue aqui");
		if(!reinicio){
		referencias();
		botones();
		}
		enviarMangoPaola("setboard  " + vStringFen);
		
		int k = 0;
		if (vTurno.length == 6)
			k = 2;
		else
			k = 1;

//		System.out.println("turno.length: " + vTurno.length);
		// if (vTurno.length ==6){
		if (vTurno[k].contains("k")) {
			Torre vTorre = (Torre) casillaOcupada("H8");
			vTorre.setMovida(false);
			((Rey) casillaOcupada("E8")).setTorreReyNe(vTorre);
		}
		if (vTurno[k].contains("q")) {
			Torre vTorre = (Torre) casillaOcupada("A8");
			vTorre.setMovida(false);
			((Rey) casillaOcupada("E8")).setTorreReinaNe(vTorre);
		}
		if (vTurno[k].contains("K")) {
			Torre vTorre = (Torre) casillaOcupada("H1");
			vTorre.setMovida(false);
			((Rey) casillaOcupada("E1")).setTorreRey(vTorre);

		}
		if (vTurno[k].contains("Q")) {
			Torre vTorre = (Torre) casillaOcupada("A1");
			vTorre.setMovida(false);
			((Rey) casillaOcupada("E1")).setTorreReina(vTorre);
		}
		// }
		if (vTurno.length == k + 1) {
			if (!vTurno[k + 1].equals("-"))
				peonalpaso = (Peon) casillaOcupada(vTurno[3]);
		}

		// turnos completos
//		System.out.println("jugadas completas: " + vTurno[k + 3]);
		if (vTurno[k + 3].length() == 1)
			movimientosCompletos = vTurno[k + 3].charAt(0) - 48;
		else
			movimientosCompletos = (vTurno[k + 3].charAt(0) - 48) * 10 + (vTurno[k + 3].charAt(1) - 48);



			if (vTurno[1].equals("w")) {
				enviarMangoPaola("white");
				setTurno(Color.WHITE);			
				habilitarPiezas(eColores.Blancas);
				
				if (jugandovsMangoPaola && colorOponente.equals(Color.WHITE))
				{
					 System.out.println("jmp1");
					JugarMangoPaola();
				}
			} else {
				enviarMangoPaola("black");
				setTurno(Color.BLACK);
				habilitarPiezas(eColores.Negras);			
				if (jugandovsMangoPaola && colorOponente.equals(Color.BLACK))
				{

					 System.out.println("jmp2");
					JugarMangoPaola();
				}
			}
		
			if (juegoNuevo){
				System.out.println("guardando las casillas inicial");
				casillasDeshacer = new Casilla[32];
				for (int i = 0; i <= 31; i++)
					casillasDeshacer[i] = casillas[i];					
					listaCasillasDeshacer.add(casillasDeshacer);
			}
		}
		
		catch(Exception e)
		{
			// fen no se pudo cargar
			txcargaFen = null;
			mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.FENNoAdmitido);
			//game.setScreen(game.juegoPrincipal);
		}
	}

	/**
	 * dibuja las letras y los numeros en el tablero
	 */
private void referencias() {
		
		
		for (int i = 0; i < 8; i++) {
			letras[i] = new Label((Character.toString((char) (65 + i)).toLowerCase()), skin2);		
			numeros[i] = new Label(Character.toString((char) (49 + i)), skin2);		
		}

		posicionarReferencias();



		for (int i = 0; i < 8; i++) {			

//			if(historia){
//				numeros[i].setColor(Color.BLACK);
//			}
//			else{
			System.out.println("esta es la text tab :"+ TexturaTablero);
				if(TexturaTablero.equals("assets/mapasTiled/tableroPlaya.tmx")|| TexturaTablero.equals("assets/mapasTiled/tableroDesierto.tmx")){
					numeros[i].setColor(Color.BLACK);
				}
				else{
					numeros[i].setColor(Color.WHITE);
				}
				
				
				stage.addActor(numeros[i]);
		}

		for (int i = 0; i < 8; i++) {			

			if(TexturaTablero.equals("assets/mapasTiled/tableroPlaya.tmx")|| TexturaTablero.equals("assets/mapasTiled/tableroDesierto.tmx")){
				letras[i].setColor(Color.BLACK);
			}
			else{
				letras[i].setColor(Color.WHITE);
			}
			stage.addActor(letras[i]);
		}
		// stage.addActor(letras[0]);
	}

	/**
	 * dibuja los botones con sus respectivos listeners en la pantalla
	 */
	private void botones() {
		
		listaDeJugadas = new List<Object>(skin2);
		pane = new ScrollPane(listaDeJugadas, skin2);
		
		pane.setFlickScroll(false);
		pane.setScrollingDisabled(true, false);
		pane.setPosition(0, 0);
		pane.addListener(new ClickListener(){
			
	      public void clicked(InputEvent e, float x, float y) {
//            System.out.println("clicked seleccionado" + listaDeJugadas.getSelectedIndex());
            if (itemSeleccionado == listaDeJugadas.getSelectedIndex()){
            	//System.out.println("doublclicked");
            	if(deshacer.isTouchable()){
            		//for(int i=0;i<listaDeJugadas.getItems().size - itemSeleccionado ;i++){
//se debe guardar las ultimas 2 jugadas
//            		if(!ultimaJugadaGuardada){
//            			System.out.println("guardando la úlima jugada");
//	            		casillasDeshacer = new Casilla[32];
//	        			for (int i = 0; i <= 31; i++)
//	        				casillasDeshacer[i] = casillas[i];
//	        		
//	        			listaCasillasDeshacer.add(casillasDeshacer);
//	        			ultimaJugadaGuardada =true;
  //          		}
            			            	if (!historia){		
            			colocarFlechas();
            			jugadaRevisar = listaDeJugadas.getSelectedIndex();
            			revisar2(false);
            			            	}
            	//	}
            	}
            	
            }
            else
            	itemSeleccionado = listaDeJugadas.getSelectedIndex();
            
				Timer.schedule(new Task() {
					@Override
					public void run() {
			            itemSeleccionado = -1;
					}
				}, 0.2f );
        }
    });
		//pane.setForceScroll(false, true);
	
		stage.addActor(pane);
		
		if(historia){
			skin6 = new Skin();
			pack6 = new TextureAtlas(Gdx.files.internal("assets/skins/minijuegos.pack"));
			skin6.addRegions(pack6);
		}
		
//		TextButtonStyle styguardar = new TextButtonStyle();
		TextButtonStyle styreiniciar = new TextButtonStyle();
		TextButtonStyle styayuda = new TextButtonStyle();
		TextButtonStyle styopciones = new TextButtonStyle();
		TextButtonStyle stysalir = new TextButtonStyle();
		TextButtonStyle styjuegaMango = new TextButtonStyle();
//		TextButtonStyle stycargarFen = new TextButtonStyle();
		TextButtonStyle stydeshacer = new TextButtonStyle();
		TextButtonStyle stycambiarPieza = new TextButtonStyle();
		TextButtonStyle styTablas = new TextButtonStyle();

//		styguardar.font=font;
		styreiniciar.font=font;
		styayuda.font=font;
		styopciones.font=font;
		stysalir.font=font;
		styTablas.font=font;
		
		styjuegaMango.font=font;
//		stycargarFen.font=font;
		stydeshacer.font=font;
		stycambiarPieza.font=font;
		
//		styguardar.up = skin1.getDrawable("guardarUp");
//		styguardar.down = skin1.getDrawable("guardarDown");
//		styguardar.over = skin1.getDrawable("guardarOver");

		if(!jugandovsMangoPaola)
		{
			styjuegaMango.up = skin1.getDrawable("jvcUp");
			styjuegaMango.down = skin1.getDrawable("jvcDown");
			styjuegaMango.over = skin1.getDrawable("jvcOver");

		} else {

			styjuegaMango.up = skin1.getDrawable("jvjUp");
			styjuegaMango.down = skin1.getDrawable("jvjDown");
			styjuegaMango.over = skin1.getDrawable("jvjOver");
		}
//		stycargarFen.up = skin.getDrawable("cargarUp");
//		stycargarFen.down = skin.getDrawable("cargarDown");
//		stycargarFen.over = skin.getDrawable("cargarOver");

		stydeshacer.up = skin1.getDrawable("deshacerUp");
		stydeshacer.down = skin1.getDrawable("deshacerDown");
		stydeshacer.over = skin1.getDrawable("deshacerOver");
		
		stycambiarPieza.up = skin1.getDrawable("cambiarUp");
		stycambiarPieza.down = skin1.getDrawable("cambiarDown");
		stycambiarPieza.over = skin1.getDrawable("cambiarOver");
		if(!historia)
			{
				styopciones.up = skin1.getDrawable("ajustesUp");
				styopciones.down = skin1.getDrawable("ajustesDown");
				styopciones.over = skin1.getDrawable("ajustesOver");
				
				stysalir.up = skin1.getDrawable("salirUP");
				stysalir.down = skin1.getDrawable("salirDown");
				stysalir.over = skin1.getDrawable("salirOver");
				
				styayuda.up = skin1.getDrawable("ayudaUp");
				styayuda.down = skin1.getDrawable("ayudaDown");
				styayuda.over = skin1.getDrawable("ayudaOver");
				
				styreiniciar.up = skin1.getDrawable("reiniciarUp");
				styreiniciar.down = skin1.getDrawable("reiniciarDown");
				styreiniciar.over = skin1.getDrawable("reiniciarOver");
				
			}
		else
			{
				styopciones.up = 	skin6.getDrawable("ajustesUp");
				styopciones.down =	skin6.getDrawable("ajustesDown");
				styopciones.over = 	skin6.getDrawable("ajustesOver");
				
				stysalir.up = skin6.getDrawable("salirUp");
				stysalir.down = skin6.getDrawable("salirDown");
				stysalir.over = skin6.getDrawable("salirOver");
				
				styayuda.up = skin6.getDrawable("ayudaUp");
				styayuda.down = skin6.getDrawable("ayudaDown");
				styayuda.over = skin6.getDrawable("ayudaOver");
				
				styreiniciar.up = skin6.getDrawable("reiniciarUp");
				styreiniciar.down = skin6.getDrawable("reiniciarDown");
				styreiniciar.over = skin6.getDrawable("reiniciarOver");
		
			}
		
				
		styTablas.up = skin1.getDrawable("tablaUp");
		styTablas.down = skin1.getDrawable("tablaDown");
		styTablas.over = skin1.getDrawable("tablaOver");
		
		salir = new TextButton("", stysalir);
		reiniciar = new TextButton("", styreiniciar);
		cambioPieza = new TextButton("", stycambiarPieza);
		deshacer = new TextButton("", stydeshacer);
		juegaMango = new TextButton("", styjuegaMango);
//		FEN = new TextButton("", stycargarFen);
//		Guardar = new TextButton("", styguardar);
		Opcion = new TextButton("", styopciones);
		botonjugAyuda = new TextButton("", styayuda);
		btnTablas= new TextButton("", styTablas);
		
		btnTablas.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				accionTabla();
			}
		});
		
		botonjugAyuda.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (historia)
				{
					System.out.println("minijuego:"+minijuego);
					if (minijuego.equals("Minijuego27")){						
						cargarayuda();
						if(!conversacion.isArriba()){						
							iniciarMotor();					
						}
					}
					
					else if (monedaAyuda<=cantidadMonedas){
						try {
							gastarMonedas(monedaAyuda);						
							jugadaAyuda();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}												
					}
				}
				else
				{
					
					jugadaAyuda();
				}// game1.setScreen(game1.opcScreem);
			}

		});

		Opcion.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				loadSettings();
				// game1.setScreen(game1.opcScreem);
			}

		});

//			FEN.addListener(new ChangeListener() {
//				@Override
//				public void changed(ChangeEvent event, Actor actor){				
//					ultimaJudada ="";
//					if (validaFen == 0) {
//						
//						ArrayList<String> mensaje = new ArrayList<String>();
//						mensaje.add("Ingrese el FEN");
//						//final Dialogo dialog = new Dialogo("",truee ) ;
//						 final Dialogo dialog = new Dialogo("",mensaje, 1) ;
//						 
//							dialog.addListener(new ChangeListener() {
//								@Override
//								public void changed(ChangeEvent event, Actor actor) {			
//									try {
//										if (dialog.getResult() != null) {
//											if ( dialog.getValue()!=null){
//												stage.removeListener(this);											
//												procesarDialogoCargarFen(dialog.getResult(), dialog.getValue());
//												dialog.remove();
//											}
//											else
//												if (dialog.getResult() == "C") { // Cargar archivo
////													System.out.println("aqui 1");
//													stage.removeListener(this);											
//													procesarDialogoCargarFen(dialog.getResult(), dialog.getValue());											
//												}
//										}								
////										else{
////											stage.removeListener(this);											
////											procesarDialogoCargarFen(null, null);
////											dialog.remove();									
////										}										
//									} catch (Exception e) {
//											//stage.removeListener(this);									
//											System.out.println("Error " + e.getMessage());
//											stage.removeListener(this);
//											mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.FENNoAdmitido);
//											dialog.remove();											
//										}
//									}															
//								});
//							stage.addActor(dialog);
//							dialog.setPosition(gameWidth / 2 - dialog.getWidth() / 2,
//							gameHeight / 2 - dialog.getHeight() / 2);
//
//					}
//
//				}	
//
//				});

					
			/**
			 * Boton guardar para los juegos normales de ajedrez se guardan en
			 * la carpeta JuegosAjedrez
			 */
/*
		Guardar.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Dialog dialog = new Dialog("Introduzca nombre", skin2) {
					TextField txtUsername;

					{
						txtUsername = new TextField("", skin2);
						txtUsername.setWidth(500);
						this.getButtonTable().add(txtUsername);
						button("Guardar", "C");
					}

					@Override
					protected void result(Object object) {
						if (object.equals("C")) {
							txcargaFen = txtUsername.getText();
							if (txcargaFen != null) {// && test.contains("/")){
								guardarJuegos(Enums.indefindo);
								// game.setScreen(game.juegoPrincipal);

							}

						}
					}
				}.show(stage);
				dialog.setWidth(300);
			}
		});
//*/
		salir.addCaptureListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				ultimaJudada ="";
				//txcargaFen = null;
				if (historia){
					game.setScreen(game.mapMin);
					txcargaFen = null;
					
					jugadasSecretasNoSeguidas=0;
					jugadasSecretasSeguidas=0;
					historia=false;
				}
				else
				{	
					
					txcargaFen = obtenerFen();
				
					if(mostrarcontTiempo){
					comprobartiempo();
					}
					game.setScreen(game.inicio);
				}
			}
		});
		reiniciar.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				nuevaPartida();				
				if (historia)
				{
				cantidadMonedas=monedasInicio;
				try {
					gastarMonedas(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
//				game.setScreen(game.juegoPrincipal);
			}
		});

		cambioPieza.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if (cambioPiezaPermitido){
				System.out.println("turno entrante"+ Turno+" - "+ getTurno());
				Color vTurno = Turno;
				if(casillaJaque!=null){
				casillaJaque.setVisible(false);}
				Turno = Color.BROWN;
				ultimaJudada ="";
				if (colorOponente.equals(Color.WHITE)) {
					colorOponente = Color.BLACK;
				} else {
					colorOponente = Color.WHITE;
				}
				//txcargaFen = null;
				
//				if(mostrarcontTiempo){
//					comprobartiempo();
//					}
				
				rotarTablero();
				
//				jugadas = new ArrayList<String>();
//				jugadasAlg = new ArrayList<String>();
//				casillas = new Casilla[32];
				
				//game.setScreen(game.juegoPrincipal);
				
				Turno  = vTurno;
//				System.out.println("turno entrante"+ Turno+" - "+ getTurno());
//				if(Turno.equals(Color.WHITE)){Turno=Color.WHITE;}else{Turno=Color.BLACK;}
//				setTurno(Turno);
//				System.out.println(getTurno());
////				if(mostrarcontTiempo){
////					oprimirBotonReloj();
////				}
				}
			}
		});
		juegaMango.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
                //System.out.println("empiezo la tabla"); 
				ultimaJudada ="";
				if (jugandovsMangoPaola) {
					jugandovsMangoPaola = false;
					resultadoJugarMangoPaola(enviarMangoPaola("force 0"));
										
					TextButtonStyle styhumano = new TextButtonStyle();
					styhumano.font=font;
					
					
					styhumano.up = skin1.getDrawable("jvcUp");
					styhumano.down = skin1.getDrawable("jvcDown");
					styhumano.over = skin1.getDrawable("jvcOver");
					
					juegaMango.setStyle(styhumano);
					btnTablas.setVisible(false);
							
						
					
				} else {
					jugandovsMangoPaola = true;
					resultadoJugarMangoPaola(enviarMangoPaola("force 1"));
					
					
					TextButtonStyle stymaquina = new TextButtonStyle();
					stymaquina.font=font;
					
					stymaquina.up = skin1.getDrawable("jvjUp");
					stymaquina.down = skin1.getDrawable("jvjDown");
					stymaquina.over = skin1.getDrawable("jvjOver");
					juegaMango.setStyle(stymaquina);
					
					btnTablas.setVisible(true);
					
					
				}
				
				//txcargaFen = null;
				//jugadas = new ArrayList<String>();
				//jugadasAlg = new ArrayList<String>();
				//casillas = new Casilla[32];
				
				//game.setScreen(game.juegoPrincipal);
			}
		});
		deshacer.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				deshacer();
			}
		});

		String[] comboDificultad = new String[] 
				{
						"Nivel 1",
						"Nivel 2",
						"Nivel 3",
						"Nivel 4",
						"Nivel 5",
						"Saraí Sánchez",
						"Amelia Hernández",
						"Paul Morphy", 
						"Adolf Anderssen",
						"Wilhelm Steinitz",
						"Emanuel Lasker",
						"José Raúl Capablanca",
						"Alexander Alekhine",
						"Max Euwe",
						"Mijaíl Botvínnik",
						"Vasili Smyslov",
						"Mijaíl Tal",
						"Tigran Petrosian", 
						"Eduardo Iturrizaga"

					};
				byte ptext[] = null;
				try {
					ptext = comboDificultad[1].getBytes("UTF-8");
					comboDificultad[1] = new String(ptext, "UTF-8");
					
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				box = new SelectBox<String>(skin2);
				box.setItems(comboDificultad);
				if(valorDificultad == "sd 1")
					dificulty="Saraí Sánchez";
				box.setSelected(dificulty);
				box.toFront();
				
				box.addCaptureListener(new ChangeListener() {

					private int random;

					@Override
					public void changed(ChangeEvent event, Actor actor) {
						String selec = box.getSelected().toString();
						box.setSelected(selec);
						
//						System.out.println("seleccion : " + selec);
						 if (selec.equals("Nivel 1")) {
								valorDificultad = "sd 1";
								hard= false;
								dificulty = selec;
								random =9;
							} 
						 else if (selec.equals("Nivel 2")) {
								valorDificultad = "sd 1";
								hard= false;
								dificulty = selec;
								random =7;
							} 
						 else 
							 if (selec.equals("Nivel 3")) {
								valorDificultad = "sd 1";
								hard= false;
								dificulty = selec;
								random =5;
							}
							 else 
								 if (selec.equals("Nivel 4")) {
								valorDificultad = "sd 1";
								hard= false;
								dificulty = selec;
								random =3;
							}
								 else 
									 if (selec.equals("Nivel 5")) {
									valorDificultad = "sd 1";
									hard= false;
									dificulty = selec;
									random =1;
								}
								 else
						if (selec.equals("Saraí Sánchez")) {
							valorDificultad = "sd 1";
							hard= false;
							dificulty = selec;
							random=0;
						}
						else if (selec.equals("Amelia Hernández")) {
							valorDificultad = "sd 2";
							hard= true;
							dificulty = selec;
							random=0;
						}
						else if (selec.equals("Paul Morphy")) {
							valorDificultad = "sd 3";
							dificulty = selec;
							hard= false;
							random=0;
						} else if (selec.equals("Adolf Anderssen")) {
							valorDificultad = "sd 4";
							dificulty = selec;
							hard= true;
							random=0;
						} else if (selec.equals("Wilhelm Steinitz")) {
							valorDificultad = "sd 5";
							dificulty = selec;
							hard= false;
							random=0;
						} else if (selec.equals("Emanuel Lasker")) {
							valorDificultad = "sd 6";
							dificulty = selec;
							hard= true;
							random=0;
						} else if (selec.equals("José Raúl Capablanca")) {
							valorDificultad = "sd 7";
							dificulty = selec;
							hard= false;
							random=0;
						}else if (selec.equals("Alexander Alekhine")) {
							valorDificultad = "sd 8";
							dificulty = selec;
							hard= true;
							random=0;
						}else if (selec.equals("Max Euwe")) { 
							valorDificultad = "st 3";
							dificulty = selec;
							hard= false;
							random=0;
						} else if (selec.equals("Mijaíl Botvínnik")) {
							valorDificultad = "st 3";
							dificulty = selec;
							hard= true;
							random=0;
						} else if (selec.equals("Vasili Smyslov")) {
							valorDificultad = "st 4";
							dificulty = selec;
							hard= false;
							random=0;
						}else if (selec.equals("Mijaíl Tal")) {
							valorDificultad = "st 4";
							dificulty = selec;
							hard= true;
							random=0;
						}else if (selec.equals("Tigran Petrosian")) {
							valorDificultad = "st 5";
							dificulty = selec;
							hard= true;
							random=0;
						}else if (selec.equals("Eduardo Iturrizaga")) {
							valorDificultad = "st 6";
							hard= true;
							dificulty = selec;
							random=0;
						
						}
						txcargaFen = null;
						ultimaJudada ="";
						//game.setScreen(game.juegoPrincipal);
						resultadoJugarMangoPaola(enviarMangoPaola(valorDificultad));
/*
						if (hard)
							resultadoJugarMangoPaola(enviarMangoPaola("hard"));
						else
							resultadoJugarMangoPaola(enviarMangoPaola("easy"));
//*/				
						resultadoJugarMangoPaola(enviarMangoPaola("random "+random));
						
					}
				});

				String[] comboFondo = new String[] {   "Valles", "Playa", "Castillo","Madera",  "Mar","Llanos","Lagunas","Montañas"};
				box2 = new SelectBox<String>(skin2);
		box2.setItems(comboFondo);
		box2.setSelected(select1);
		box2.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				select1 = box2.getSelected().toString();
				box2.setSelected(select1);
				
//				System.out.println("seleccion : " + selec);
				if (select1.equals("Castillo")) {
					TexturaTablero = "assets/mapasTiled/tableroRoca.tmx";
				} else if (select1.equals("Playa")) {
					TexturaTablero = "assets/mapasTiled/tableroPlaya.tmx";
				} else if (select1.equals("Mar")) {
					TexturaTablero = "assets/mapasTiled/tableroAgua.tmx";
				} else if (select1.equals("Madera")) {
					TexturaTablero = "assets/mapasTiled/tableroMadera.tmx";
				}else if (select1.equals("Valles")) {
					TexturaTablero = "assets/mapasTiled/tableroValle.tmx";
				}else if (select1.equals("Llanos")) {
					TexturaTablero = "assets/mapasTiled/tableroLlano.tmx";
				}else if (select1.equals("Montañas")) {
					TexturaTablero = "assets/mapasTiled/tableroMontañas.tmx";
				}else if (select1.equals("Lagunas")) {
					TexturaTablero = "assets/mapasTiled/tableroAgua2.tmx";
				}
		tiledMap = new TmxMapLoader().load(TexturaTablero);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		actualizarReferencias();
	}
});

String[] comboPieza = new String[] {"MiniHéroe", "Clásica" };
box3 = new SelectBox<String>(skin2);
box3.setItems(comboPieza);
box3.setSelected(selectTipoPieza);
box3.addCaptureListener(new ChangeListener() {

	@Override
	public void changed(ChangeEvent event, Actor actor) {
	 System.out.println("seleccione:"+ box3.getSelected().toString());
	 selectTipoPieza=box3.getSelected().toString();
   cambioDePiezas();
		}
});

String[] combotiempo = new String[] {   "Ilimitado","5 min","10 min","20 min","30 min","60 min"};
box4 = new SelectBox<String>(skin2);
box4.setItems(combotiempo);
box4.setSelected(selec4);
box4.addCaptureListener(new ChangeListener() {
@Override
public void changed(ChangeEvent event, Actor actor) {
	selec4 = box4.getSelected().toString();
	System.out.println(" salir listaCasillasDeshacer.size() " + listaCasillasDeshacer.size());
box4.setSelected(selec4);

comprobartiempo();
selec5 = box5.getSelected().toString();
iniciarRetardo();
//nuevaPartida();
//game.setScreen(game.juegoPrincipal);
}
});

String[] comboretardo = new String[] { "0 seg",  "1 seg",  "2 seg",  "3 seg",  "5 seg", "10 seg", "20 seg","30 seg","60 seg"};
box5 = new SelectBox<String>(skin2);
box5.setItems(comboretardo);
box5.setSelected(selec5);
box5.addCaptureListener(new ChangeListener() {
@Override
public void changed(ChangeEvent event, Actor actor) {
	selec5 = box5.getSelected().toString();
	
	box5.setSelected(selec5);
if(selec5.equals("0 seg"))
{
	tiempoRetardo=0;
}
else
	if(selec5.equals("1 seg"))
		{
			tiempoRetardo=1;
		}
	else
		if(selec5.equals("2 seg"))
			{
				tiempoRetardo=2;
			}
		else
			if(selec5.equals("3 seg"))
				{
					tiempoRetardo=3;
				}
else
	if(selec5.equals("5 seg"))
		{
			tiempoRetardo=5;
		}
	else
		if(selec5.equals("10 seg"))
		{
			tiempoRetardo=10;
		}
		else
	
	if(selec5.equals("20 seg"))
	{
		tiempoRetardo=20;
	}
	else
	if(selec5.equals("30 seg"))
	{
		tiempoRetardo=30;
	}
	else
	if(selec5.equals("60 seg"))
	{
		tiempoRetardo=60;
	}

	
}
});





pane.addListener(new InputListener() {
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
		flagPane=false;
		//System.out.println("pane");
	}
	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
	if (!jaqueMate)
		flagPane=true;
		//System.out.println("NOpane");

	}
	});



//salir.setSize(100,55);
//reiniciar.setSize(100,55);
//Opcion.setSize(100,55);
//botonjugAyuda.setSize(100,55);

//salir.setHeight(55);
//reiniciar.setHeight(55);
//Opcion.setHeight(55);
//botonjugAyuda.setHeight(55);
if(!historia){
		pane.sizeBy(80, -60);
		pane.setPosition(560, 245);
		
		box.sizeBy(120, 0);
		box2.sizeBy(120, 0);
		box3.sizeBy(120, 0);
		box4.sizeBy(120, 0);
		box5.sizeBy(120, 0);
		
		cambioPieza.setHeight(55);
		juegaMango.setHeight(55);
		deshacer.setHeight(55);
//		FEN.setSize(60,60);
//		Guardar.setHeight(55);
		btnTablas.setHeight(55);
		
		
			
			
//		   juegaMango.setPosition(850, 485);
//		  cambioPieza.setPosition(850, 425);
//		     deshacer.setPosition(850, 365);
//		botonjugAyuda.setPosition(850, 305);
//		      Guardar.setPosition(850, 245);
//		          FEN.setPosition(850, 185);
//		       Opcion.setPosition(850, 125);
//		    reiniciar.setPosition(850, 65);
//		        salir.setPosition(850, 5);
			
			//	  FEN.setPosition(440, 495);
	
		        
			         juegaMango.setPosition(0, 500);
				  cambioPieza.setPosition(100, 500);
				     deshacer.setPosition(200, 500);
				botonjugAyuda.setPosition(303, 500);
				 //     Guardar.setPosition(396, 500);
				       Opcion.setPosition(407, 500); //Opcion.setPosition(500, 500)
				    reiniciar.setPosition(507, 500); //reiniciar.setPosition(600, 500)
				    btnTablas.setPosition(595, 500); //btnTablas.setPosition(688, 500)
				        salir.setPosition(770, 500); //salir.setPosition(770, 500)
			
			
			
		
		dif.setPosition(538, 200);
		box.setPosition(655, 200);
		
		 tab.setPosition(538, 155);
		box2.setPosition(655, 155);
		
		 pie.setPosition(538, 110);
		box3.setPosition(655, 110);
		
		tiempo.setPosition(538, 65);
		  box4.setPosition(655, 65);
		  
		  
		  retardo.setPosition(538, 25);
		  box5.setPosition(655, 25);
		
		
//		FEN.setTouchable(Touchable.enabled);
		reiniciar.setTouchable(Touchable.enabled);
		cambioPieza.setTouchable(Touchable.enabled);
		juegaMango.setTouchable(Touchable.enabled);
		salir.setTouchable(Touchable.enabled);
//		Guardar.setTouchable(Touchable.enabled);
		btnTablas.setTouchable(Touchable.enabled);
		if (!activaDeshacer) {
			deshacer.setTouchable(Touchable.disabled);
			activaDeshacer = true;
		}
		if(jugandovsMangoPaola){
			stage.addActor(btnTablas);
			stage.addActor(box);

			stage.addActor(dif);
		}
		
	//	stage.addActor(Guardar);
		//stage.addActor(FEN); //no se por qué no quieren el fen :-( VB.
		stage.addActor(salir);
		stage.addActor(reiniciar);
		stage.addActor(cambioPieza);
		stage.addActor(juegaMango);
		stage.addActor(deshacer);
		
		stage.addActor(box2);
		stage.addActor(box3);
		stage.addActor(box4);
		
		stage.addActor(box5);
		stage.addActor(retardo);
		box5.setVisible(false);
		retardo.setVisible(false);
		
		if(mostrarcontTiempo){
		box5.setVisible(true);
		retardo.setVisible(true);
		}
		stage.addActor(tab);
		stage.addActor(pie);
		stage.addActor(tiempo);
		
		stage.addActor(Opcion);
		stage.addActor(botonjugAyuda);

	    if(Gdx.graphics.isFullscreen()){
	    	//  Guardar.setVisible(false);
			  Opcion.setVisible(false);
			  botonjugAyuda.setVisible(false);
			  reiniciar.setVisible(false);
			  salir.setVisible(false);
			  juegaMango.setVisible(false);
			  cambioPieza.setVisible(false);
			  deshacer.setVisible(false);
//			  FEN.setVisible(false);

	        }

}
else
{
	pane.sizeBy(50, -10);
	pane.setPosition(545, 335);
	
	Opcion.setSize(70, 70);
	 botonjugAyuda.setSize(70, 70);
	 reiniciar.setSize(70, 70);
	 salir.setSize(70, 70);
	 
           Opcion.setPosition(780,420);
    botonjugAyuda.setPosition(780,340);
        reiniciar.setPosition(780,260);
            salir.setPosition(780,180);
	
	stage.addActor(Opcion);
	stage.addActor(botonjugAyuda);
	stage.addActor(salir);
	stage.addActor(reiniciar);
	
	   if(Gdx.graphics.isFullscreen()){
	    	
			  Opcion.setVisible(false);
			  botonjugAyuda.setVisible(false);
			  reiniciar.setVisible(false);
			  salir.setVisible(false);
	        }
	
	
	
}
		
		
	}

	protected void cambioDePiezas() {
		if (historia)
		{
			tipoDePiezas = "MiniHeroe";

			box3.setSelected(selectTipoPieza);
		}		
		else
		if (selectTipoPieza.equals("MiniHéroe"))
		{
			tipoDePiezas = "MiniHeroe";

			box3.setSelected(selectTipoPieza);
		}
		else {
			tipoDePiezas = "Clasica";
			box3.setSelected(selectTipoPieza);
		}
		//box3.setSelected(tipoDePiezas);
		
		cambioTipoPieza(tipoDePiezas);
	}

	/**
	 * Mostrar los botones cuando el mouse se encuentra en un rango X a la posicion de los botones
	 */
	private void mostrarBotones(){
		float duracion=0.3f; 
		
		if(!historia){
			juegaMango.addAction(Actions.moveTo(780,485,duracion));
		   cambioPieza.addAction(Actions.moveTo(780,425,duracion));
		      deshacer.addAction(Actions.moveTo(780,365,duracion));
		 botonjugAyuda.addAction(Actions.moveTo(780,305,duracion));
 //              Guardar.addAction(Actions.moveTo(780,245,duracion));
//                   FEN.addAction(Actions.moveTo(780,185,duracion));
                Opcion.addAction(Actions.moveTo(780,125, duracion));
	         reiniciar.addAction(Actions.moveTo(780,65,duracion));
		         salir.addAction(Actions.moveTo(780,5,duracion));

		        if(Gdx.graphics.isFullscreen())
		        {		        
		        	 // Guardar.setVisible(true);
					  Opcion.setVisible(true);
					  botonjugAyuda.setVisible(true);
					  reiniciar.setVisible(true);
					  salir.setVisible(true);
					  juegaMango.setVisible(true);
					  cambioPieza.setVisible(true);
					  deshacer.setVisible(true);
//					  FEN.setVisible(true);				        
		        }
		}
		else{
		     Opcion.addAction(Actions.moveTo(780,420, duracion));
		     botonjugAyuda.addAction(Actions.moveTo(780,340,duracion));
	        reiniciar.addAction(Actions.moveTo(780,260,duracion));
		        salir.addAction(Actions.moveTo(780,180,duracion));
		        
		        if(Gdx.graphics.isFullscreen())
		        {		        
					 
				       Opcion.setVisible(true);
				      botonjugAyuda.setVisible(true);
				    reiniciar.setVisible(true);
				        salir.setVisible(true);						        
		        }		        		
		}
	}
	
/**
 * ocultar los botones cuando el mouse se encuentra en un rango X diferente a la posicion de los botones
 */
private void ocultarBotones(){
		float duracion=0.3f; 
		
	if(!historia){
		    juegaMango.addAction(Actions.moveTo(850,485,duracion));
		   cambioPieza.addAction(Actions.moveTo(850,425,duracion));
		      deshacer.addAction(Actions.moveTo(850,365,duracion));
		 botonjugAyuda.addAction(Actions.moveTo(850,305,duracion));
 //              Guardar.addAction(Actions.moveTo(850,245,duracion));
//                   FEN.addAction(Actions.moveTo(850,185,duracion));
                Opcion.addAction(Actions.moveTo(850,125, duracion));
	         reiniciar.addAction(Actions.moveTo(850,65,duracion));
		         salir.addAction(Actions.moveTo(850,5,duracion));

		
        if(Gdx.graphics.isFullscreen())
        {
        	Timer.schedule(new Task() {
				@Override
				public void run() {
					
//					  Guardar.setVisible(false);
					  Opcion.setVisible(false);
					  botonjugAyuda.setVisible(false);
					  reiniciar.setVisible(false);
					  salir.setVisible(false);
					  juegaMango.setVisible(false);
					  cambioPieza.setVisible(false);
					  deshacer.setVisible(false);
//					  FEN.setVisible(false);
					  

				}
			}, 0.2f);
        	
        }
        
	}  
	else{
		   Opcion.addAction(Actions.moveTo(850,420, duracion));
		   botonjugAyuda.addAction(Actions.moveTo(850,340,duracion));
	    reiniciar.addAction(Actions.moveTo(850,260,duracion));
	        salir.addAction(Actions.moveTo(850,180, duracion));

	        if(Gdx.graphics.isFullscreen())
	        {
	        	Timer.schedule(new Task() {
					@Override
					public void run() {
					       Opcion.setVisible(false);
					       botonjugAyuda.setVisible(false);
					    reiniciar.setVisible(false);
					        salir.setVisible(false);

					}
				}, 0.2f);
	        	
	        }
	
	}
	}	
	
	
	
	/**
	 * 
	 * @param select1
	 * @author yisheng
	 */
	
	protected void cambioTipoPieza(String tipoPieza) {
		// TODO Auto-generated method stub
//		System.out.println("cambio tipo de pieza");
		for (int i=0 ; i<casillas.length;i++)
		{
			if (!(casillas[i].getPieza()==null))
			{
				//System.out.println(casillas[i].getPieza());
				
				
				casillas[i].getPieza().setTipodePiezas(casillas[i].getPieza().getEcolor(), tipoPieza);
			}
		}
		if(casillasDeshacer!=null){
		for (int i=0 ; i<casillasDeshacer.length;i++)
		{
			if (!(casillasDeshacer[i].getPieza()==null))
			{
				//System.out.println(casillas[i].getPieza());
				
				
				casillasDeshacer[i].getPieza().setTipodePiezas(casillasDeshacer[i].getPieza().getEcolor(), tipoPieza);
			}
		}
		}
		
		
	}

//	@Override
//	public void resize(int width, int height) {
//
//		camera.setToOrtho(false, width, height);
//
//		camera.viewportWidth = width;
//		camera.viewportHeight = height;
//
//		camera.position.x = super.getGameWidth() / 2;
//		camera.position.y = super.getGameHeight() / 2;
//
//	}

	/**
	 * obtiene las preferencias del juego y las muestra para ser cambiadas
	 */
	private void loadSettings() {
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
					System.out.println("seteando loadSettings");
					if (opcionesScreen.getResult().equals(true)) {
						pasoPreferencia= false;
						Preferencias(false);
						opcionesScreen.dispose();
						stage.removeListener(this);
						gc();
						
					}
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
//System.out.println("entre a preferencias");
		if (preferencias.getPreferencia("pantallacompleta")) {
			DisplayMode currentMode = Gdx.graphics.getDisplayMode();
			Gdx.graphics.setFullscreenMode(currentMode);
			
			if (!pCarga) {
				//txcargaFen = obtenerFen();
				//game.setScreen(game.juegoPrincipal);
			}
		} else {

			Gdx.graphics.setWindowedMode(w, h);
			if (!pCarga) {
				txcargaFen = obtenerFen();
				//game.setScreen(game.juegoPrincipal);
			}
		}

		if (preferencias.getPreferencia("musica")) {
			tocarCortinas = true;
			cortinaFondo.play();
			cortinaFondo.setLooping(true);
		} else {
			cortinaFondo.stop();
			tocarCortinas = false;
		}
		
		if (preferencias.getPreferencia("sonido")) {
			tocarSonido = true;
		} else {
			tocarSonido = false;
		}

		
//		System.out.println("tocarSonido: " + tocarSonido);
		
	}

	private void dibujarCuadro(String pPgnI, String pPgnF) {
		int Actor[] = tablero.Pgn2XY(pPgnI);
		float actorX = Actor[0];
		float actorY = Actor[1];
		int pos[] = tablero.Pgn2XY(pPgnF.substring(0, 2));
		float posX = pos[0];
		float posY = pos[1];
		cuadroIni.setPosition(actorX, actorY);
		cuadroFin.setPosition(posX, posY);

		cuadroFin.setVisible(true);
		cuadroIni.setVisible(true);

		cuadroFin.setZIndex(0);
		cuadroIni.setZIndex(1);
		ultimaJudada = pPgnI + "/"+ pPgnF;
	}

	public void asignarPartida(String batalla, String minijuego, Partida partida,Dificultad dificultad,Configuracion configuracion) throws Exception {
		// TODO Auto-generated method stub

		//
		System.out.println("ALGUNA VEZ ENTRE AQUI? 2");
		// IniciariMiniJuegoMangoPaola();
		historia=true;
		this.minijuego = minijuego;
		this.batalla = batalla;
		this.nombrePartida = partida.getNombre();
		this.partida=partida;
		this.dificultad= dificultad;
		this.configuracion= configuracion;
		
		//Partida partida = new Partida();
//		System.out.println("nombrePartida:" + nombrePartida);
		String dificil = partida.getDificultad();
		minijuegoPartida = partida.getMinijuego();
		batallaPartida = partida.getBatalla();
		
		cantidadMonedas =   Integer.parseInt(partida.getMonedas());
		monedasInicio=cantidadMonedas;
		this.valorDificultad ="sd "+ dificil;
		try {
			monedaAyuda =  Integer.parseInt(dificultad.getMonedaAyuda());
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private void mostrarDialogo( eTipoDialogo pTipoDialogo, final eTipoMensaje pTipoMensaje) {
		//System.out.println("mostrarDialogo: " + pTipoDialogo + " " + pTipoMensaje);
		Texture vTexture = null;
		
		if (pTipoDialogo == eTipoDialogo.Mensaje || pTipoDialogo == eTipoDialogo.Aceptar || pTipoMensaje== eTipoMensaje.FENNoAdmitido) {
			
		//	ArrayList<String> mensaje = new ArrayList<String>();
			if (pTipoMensaje == eTipoMensaje.MensajeSecretoDerrotaParcial){
				vTexture = new Texture("assets/mensajes/mensaje 10.png");
			}else 
 			if(pTipoMensaje==eTipoMensaje.MensajeSecretoVictoriaParcial){
				vTexture = new Texture(new FileHandle("assets/mensajes/mensaje 11.png"));
			}else
			if (pTipoMensaje == eTipoMensaje.JuegoFinalizadoDerrota){//mensaje 6
				vTexture = new Texture(new FileHandle("assets/mensajes/mensaje 6.png"));
				if (tocarSonido&&historia){
					sonidoDerrota.play();
				}				
			}else
			if (pTipoMensaje == eTipoMensaje.JuegoFinalizadoTablas){//mensaje 15
				vTexture = new Texture(new FileHandle("assets/mensajes/mensaje 15.png"));
				if (tocarSonido){
					sonidoDerrota.play();
				}				
			}else 

			if (pTipoMensaje == eTipoMensaje.ReyenJaque){// mensaje 8			
				vTexture =new Texture(new FileHandle("assets/mensajes/mensaje 8.png"));
			}else
				if (pTipoMensaje == eTipoMensaje.JuegoNoAceptaTablas){ // mensaje 16
					vTexture =new Texture(new FileHandle("assets/mensajes/mensaje 16.png"));
				}else
					if (pTipoMensaje == eTipoMensaje.GanaBlancas){ // mensaje 9
						vTexture =new Texture(new FileHandle("assets/mensajes/mensaje 18.png"));
					}else
						if (pTipoMensaje == eTipoMensaje.GanaNegras){ // mensaje 9
							vTexture =new Texture(new FileHandle("assets/mensajes/mensaje 19.png"));
						}else
							if (pTipoMensaje == eTipoMensaje.JuegoAceptaTablas){ // mensaje 9
								vTexture =new Texture(new FileHandle("assets/mensajes/mensaje 17.png"));
					}else
					if (pTipoMensaje == eTipoMensaje.MovimientoNoPermitido){ // mensaje 9
				vTexture =new Texture(new FileHandle("assets/mensajes/mensaje 9.png"));
			}else
			if (pTipoMensaje == eTipoMensaje.MovimientoIlegal){ //mensaje 5				
				vTexture =new Texture(new FileHandle("assets/mensajes/mensaje 5.png"));
			}
			else
			if (pTipoMensaje == eTipoMensaje.JuegoFinalizadoVictoria){ // mensaje 7
				vTexture = new Texture(new FileHandle("assets/mensajes/mensaje 7.png"));				
//				if (tocarSonido)
//				{
//					System.out.println("tocando sonido victoria");
//					sonidoTriunfo.play();
//				}
				
				if (historia){
					videoAyudaMostrado = false;
					actualizarPartida();
					game.mapMin.asignarPartida(partida, batalla,dificultad,configuracion,true);									
				}								
			}
			else 
			if (pTipoMensaje == eTipoMensaje.FENNoAdmitido) // mensaje 14
				vTexture = new Texture(new FileHandle("assets/mensajes/mensaje 14.png"));

			if (historia){
				if (pTipoDialogo == eTipoDialogo.Mensaje)
						opcionesScreen = new Dialogo(" Informacion ", vTexture, "");
				else
					if (pTipoDialogo == eTipoDialogo.Aceptar)
						if (pTipoMensaje.equals(eTipoMensaje.ReyenJaque))
							opcionesScreen = new Dialogo(" Informacion ", vTexture, false);
						else
							opcionesScreen = new Dialogo(" Informacion ", vTexture);
			}
			else{
				if (pTipoDialogo == eTipoDialogo.Mensaje)
					opcionesScreen = new Dialogo(" Informacion ", vTexture, "",false);
				else
					opcionesScreen = new Dialogo(" Informacion ", vTexture, false);
				
				
			}
						
			
			stage.addActor(opcionesScreen);
			if(historia)

			opcionesScreen.setPosition(520,
					super.gameHeight - opcionesScreen.getHeight() / 2);
			else
				opcionesScreen.setPosition(530,0);
//				opcionesScreen.setPosition(530,
//						super.gameHeight - opcionesScreen.getHeight() / 2);

				

			opcionesScreen.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {

					try {

						if (opcionesScreen.getResult() != null) {
							System.out.println("opcionesScreen.getValue()" + opcionesScreen.getValue());
							procesarResultadoDialogo(opcionesScreen.getValue(), pTipoMensaje);
						}
					} catch (Exception e) {
						System.out.println("error" + e.getMessage());
						procesarResultadoDialogo(0, pTipoMensaje);
					}
				}
			});
			opcionesScreen.addListener(new InputListener(){				
		   		@Override
		   		public boolean keyDown( InputEvent event, int keyCode)
		   			{	   			
//		   			System.out.println("KeyCode" + keyCode);
		   			if (keyCode  == Keys.ESCAPE)
			   			if (opcionesScreen.getResult()!=null)
			   				//if (opcionesScreen.getResult().equals(true))
			   					procesarResultadoDialogo(opcionesScreen.getValue(), pTipoMensaje);
			   			//System.out.println("opciones resultado"+ opcionesScreen.getValue());
		   				return false;
		   			}
				}); 	
		}
		 	if(pTipoDialogo == eTipoDialogo.Aceptar && (pTipoMensaje == eTipoMensaje.ReyenJaque)){
				 opcionesScreen.addListener(new FocusListener() {
				@Override
				public boolean handle (Event event) {
					if (event.toString().equals("exit")){												
						opcionesScreen.setVisible(false);
					}
//				System.out.println("event " + event.toString());
				return false;
				}
			});
		 	}
	}

	private void procesarResultadoDialogo(Object pResp, eTipoMensaje pTipoMensaje) {
		try {			
			
			
			
			
			eRespuestaDialgo vResp = (eRespuestaDialgo) pResp;

			System.out.println("vResp:" + vResp);
			
			if (vResp == eRespuestaDialgo.Si) {							
				if (!historia){
//					game.setScreen(game.juegoPrincipal);
					jugadas = new ArrayList<String>();
					jugadasAlg = new ArrayList<String>();
					listaDeJugadas.setItems(jugadasAlg.toArray());
					jaqueMate = false;
					nuevaPartida();
					
				}
				else{	
					
					if(mostrarcontTiempo){
						comprobartiempo();
						}
					int vjugadasSecretasSeguidas = jugadasSecretasSeguidas;
					int vjugadasSecretasNoSeguidas = jugadasSecretasNoSeguidas;
					nuevaPartida();
					if (pTipoMensaje!= eTipoMensaje.MensajeSecretoVictoriaParcial ||
						pTipoMensaje!= eTipoMensaje.MensajeSecretoDerrotaParcial){
						jugadasSecretasSeguidas = vjugadasSecretasSeguidas;
						jugadasSecretasNoSeguidas = vjugadasSecretasNoSeguidas;
						actualizarContador(true, jugadasSecretasSeguidas);
						actualizarContador(false, jugadasSecretasNoSeguidas);												
					}

					
				} 

			}else if (vResp == eRespuestaDialgo.No) {
				if (historia){ 
					if (minijuego.equals("Minijuego27")){
						jugadasSecretasSeguidas = 0;
						jugadasSecretasNoSeguidas = 0;
					}
					game.setScreen(game.mapMin);
					historia=false;
				}

				else{
					System.out.println("entrandoacomprobarTiempoo");
					if(mostrarcontTiempo){
						System.out.println("comprobarTiempo");
						//comprobartiempo();
						setTurno(Color.BLUE);
						}
				}
			}else if (vResp == eRespuestaDialgo.Aceptar) {
				if (pTipoMensaje!=eTipoMensaje.FENNoAdmitido){
					if(historia){ 
						if (jugadasSecretasSeguidas>=6 && minijuego.equals("Minijuego27")){
							jugadasSecretasSeguidas = 0;
							jugadasSecretasNoSeguidas = 0;						
						}

						if (pTipoMensaje!= eTipoMensaje.Capturar5Bolsas &&
							pTipoMensaje != eTipoMensaje.MovimientoIlegal &&
							 pTipoMensaje != eTipoMensaje.MovimientoNoPermitido)
							{
								historia=false;											
								try {
									ultimoMinBatalla=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ultimo"));
								} catch (Exception e1) {
									System.out.println("Error obteniendo el ultimo minijjuescreenSize.width-go de la batalla");
									
									e1.printStackTrace();
								}
								actualizarPartida();
								if(ultimoMinBatalla){
									
									if (mapGen!=null)
									{
										mapGen.dispose();
										mapGen=null;
										System.gc();
										System.out.println("entre a limpiar");
									}
									try {
										mapGen = new mapaGeneralScreen(game,"batalla0");
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									try {
										mapGen.asignarPartida(partida,dificultad,configuracion,false
												);
										
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									

									game.setScreen(mapGen);
									
									
//									game.mapGen.asignarPartida(partida,dificultad,configuracion,false);
//									game.setScreen(game.mapGen);
								}
								else{
								game.mapMin.asignarPartida(partida, batalla,dificultad,configuracion,true);				
								game.setScreen(game.mapMin);
								dispose();
								}
							}

//					game.setScreen(game.mapMin);												
					}						 				
					else{
						ultimaJudada ="";
						if(mostrarcontTiempo&&(pTipoMensaje.equals(eTipoMensaje.GanaBlancas)||pTipoMensaje.equals(eTipoMensaje.GanaNegras))){
							comprobartiempo();
							setTurno(Color.BLUE);
							}
					}
				}
				else{
					nuevaPartida();
					//game.setScreen(game.juegoPrincipal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			nuevaPartida();
			//game.setScreen(game.juegoPrincipal);
		}
	}
	
	/**
	 * Consulta al motor las posibles jugadas para la pieza que se encuentra en el escaque seleccionado
	 * @param pPgn posicion pgn de la casilla donde se encuentra las pieza a consultar
	 * @return Vector con las posicion de las casillas donde se puede mover la pieza
	 */

	public boolean movimientosPosibles(String pPgnI, String pPgnF){
		Traductor traductor = new Traductor();
		ArrayList< String> resPos =  new  ArrayList<String>() ;	
		
//System.out.println(pPgnI + " traducion: " + traductor.CoordenadasANumeros(8,8, pPgnI.toUpperCase()));
	 resPos = enviarMangoPaola("moves " + traductor.CoordenadasANumeros(8,8, pPgnI.toUpperCase()));
	 
	 for(String item : resPos) {
//		 System.out.println(" item: " + item + " pPgnF: " + pPgnF.toLowerCase());
		 if (item.contains(pPgnF.toLowerCase()))
			 return true;
	 } 
	 return false;
	}
	
	/**
	 * Consulta al motor las posibles jugadas para la pieza que se encuentra en el escaque seleccionado
	 * @param pPgn posicion pgn de la casilla donde se encuentra las pieza a consultar
	 * @return Vector con las posicion de las casillas donde se puede mover la pieza
	 */
	
	
	private void movimientosPosibles(String pPgn){
		eliminarMovimientoPosibles();
		eliminarJugadaAyuda();
		Traductor traductor = new Traductor();		
		ArrayList< String> resPos =  new  ArrayList<String>() ; 		
		//System.out.println("movimientosPosibles enviando: " + "moves " + traductor.CoordenadasANumeros(8,8, pPgn));					
		resPos = enviarMangoPaola("moves " + traductor.CoordenadasANumeros(8,8, pPgn));		

		posiblesMovimientos = new ActorExtra[resPos.size()];				
		int i = 0;
		for(String item: resPos){
			//System.out.println("Recibiendo del motor: " + item);
			if (item.contains("Destino=")){
				//System.out.println("item contiene destino=");
				String vPgn1 = item.substring(2,4).toUpperCase();				
				//System.out.println("vpgn1:" + vPgn1 + " x:" + tablero.Pgn2XY(vPgn1)[0] + " Y: " + tablero.Pgn2XY(vPgn1)[1]);
				if(item.contains("Captura=0")){
					posiblesMovimientos[i] = new ActorExtra(this, TipoDeActor.CUADROPOSIBLES, stage.getActors().size + 1);
					posiblesMovimientos[i].setPosition(tablero.Pgn2XY(vPgn1)[0], tablero.Pgn2XY(vPgn1)[1]);
					posiblesMovimientos[i].setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
					posiblesMovimientos[i].setVisible(true);
					stage.addActor(posiblesMovimientos[i]);
				}				
				else {
					posiblesMovimientos[i] = new ActorExtra(this, TipoDeActor.CUADROPOSIBLESCAPTURAS, stage.getActors().size + 1);
					posiblesMovimientos[i].setPosition(tablero.Pgn2XY(vPgn1)[0], tablero.Pgn2XY(vPgn1)[1]);
					posiblesMovimientos[i].setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
					posiblesMovimientos[i].setVisible(true);
					stage.addActor(posiblesMovimientos[i]);
					posiblesMovimientos[i].setZIndex(2);
				}								
				i++;
			}					
		}							
	}

	/**
	 * elimina del stage los posibles movimientos
	 */
	private void eliminarMovimientoPosibles() {
//		System.out.println("eliminado las jugadas posibles");
		if (posiblesMovimientos!=null)
		for(ActorExtra vActorExtra: posiblesMovimientos){ // remover los cuadros de las posibles jugadas 
			if (posiblesMovimientos.length >0)
				stage.getActors().removeValue(vActorExtra, true);	
			}		
	
		stage.getActors().removeValue(casillaJaque, true);	
	}
	
	/**
	 * Metodo para animar la captura de las piezas
	 * 
	 * @param pPieza pieza que va a capturar
	 * @param ptipo  tipo de actor que se reprodusca durante la captura
	 * @param pPgni  pgn de inicio de la pieza que va a capturar
	 * @param pPgnf	 pgn final de la pieza que va a capturar
	 * @param pDuracion duracion del movimiento de captura
	 */
	public void animacionCapturando(Pieza pPieza,TipoDeActor ptipo,String pPgni,String pPgnf,final float pDuracion){
		
		if(tipoDePiezas.equals("MiniHeroe")){
		if(ptipo.equals(TipoDeActor.MACHETE))
		{
		actorExtra=machete;	
		}
		else
		if(ptipo.equals(TipoDeActor.FLECHA))
		{
		actorExtra=flecha;	
		}
		else
		if(ptipo.equals(TipoDeActor.CANON))
		{
		actorExtra=canon;	
		}
		else
		if(ptipo.equals(TipoDeActor.MAGIA))
		{
		actorExtra=magia;	
		}
		else
		if(ptipo.equals(TipoDeActor.LANZA))
		{
		actorExtra=lanza;	
		}
		else
		if(ptipo.equals(TipoDeActor.CETRO))
		{
		actorExtra=cetro;	
		}
		String direccion=determinarMov(pPgni, pPgnf);
//		System.out.println(direccion);
		float ancho=actorExtra.getWidth();
		float alto=actorExtra.getHeight();
		actorExtra.setOrigin(ancho/2,alto/2);
		rotarActorExtra(direccion);
		actorExtra.setPosition(tablero.Pgn2XY(pPgni)[0], tablero.Pgn2XY(pPgni)[1]);
		actorExtra.setVisible(true);

		actorExtra.addAction(Actions.moveTo(tablero.Pgn2XY(pPgnf)[0],tablero.Pgn2XY(pPgnf)[1], (pDuracion/4)));
		final float posXfinal=tablero.Pgn2XY(pPgnf)[0];
		final float posYfinal=tablero.Pgn2XY(pPgnf)[1];
		//final Pieza piezaE=pPieza;
		//final String pgnE=pPgnf;
		//final Pieza piezat = casillaOcupada(pPgnf);
		
		Timer.schedule(new Task() {
			@Override
			public void run() {
				//System.out.println("desapareciendo");
				actorExtra.setVisible(false);
				actorExtra.setRotation(0);
				explosion.setPosition(posXfinal,posYfinal);
				explosion.setVisible(true);
				//piezat.setVisible(false);
			Timer.schedule(new Task() {
				@Override
				public void run() {
				
				explosion.setVisible(false);
//				setCasilla(pgnE, piezaE, piezaE.getInd());
			
				}
			},(pDuracion/6));
			}
		},(pDuracion/4));
		
		}
		EliminarPieza(pPieza, pPgnf);
		setCasilla(pPgnf, pPieza, pPieza.getInd());
	}	
	
    /**
     * Metodo que determinar la direccion de movimiento de la pieza utilizada
     * @param pPgni pgn de inicio del movimiento de la pieza que va a capturar
     * @param pPgnf pgn final del movimiento de la pieza que va a captura 
     * @return direccion del movimiento
     */
	public String determinarMov(String pPgni,String pPgnf){
		
//		System.out.println("entrando a determinar");
		float actorX=tablero.Pgn2XY(pPgni)[0];
		float actorY=tablero.Pgn2XY(pPgni)[1];
		float destinoX=tablero.Pgn2XY(pPgnf)[0];
		float destinoY=tablero.Pgn2XY(pPgnf)[1];
		String verticalup="arriba";
		String HorizontalDer="derecha";
		String verticalDown="abajo";
		String HorizontalIzq="izquierda";
		String diagonalUpIzq="diagonalArribaIzquierda";
		String diagonalUpDer="diagonalArribaDerecha";
		String diagonalDownIzq="diagonalAbajoIzquierda";
		String diagonalDownDer="diagonalAbajoDerecha";
		String izquierdaArriba="izquierdaArriba";
		String izquierdaAbajo="izquierdaAbajo";
		String abajoIzquierda="abajoIzquierda";
		String abajoDerecha="abajoDerecha";
		String derechaAbajo="derechaAbajo";
		String derechaArriba="derechaArriba";
		String arribaDerecha="arribaDerecha";
		String arribaIzquierda="arribaIzquierda";
		
		boolean diagonal=false;
		
		if(Math.abs(((float) destinoX - (float) actorX) / ((float) destinoY - (float) actorY))==1){
			diagonal=true;
		}
		else
		{
			diagonal=false;
		}
		
		if(actorX==destinoX && actorY<destinoY)
			{
				return verticalup;
			
			}
		else 
		if(actorX==destinoX && actorY>destinoY)
			{
				return verticalDown;
			
			}
		else 
		if(actorY==destinoY && actorX<destinoX)
			{
				return HorizontalDer;
			}
		else 
		if(actorY==destinoY && actorX>destinoX)
			{
				return HorizontalIzq;
			}
		else 

		if(actorY<destinoY && actorX<destinoX && diagonal)
			{
				return diagonalUpDer;
			}	
		else 
		if(actorY<destinoY && actorX>destinoX && diagonal)
			{
				return diagonalUpIzq;
			}	
		else 
		if(actorY>destinoY && actorX<destinoX && diagonal)
			{
				return diagonalDownDer;
			}	
		else 
		if(actorY>destinoY && actorX>destinoX && diagonal)
		{
			return diagonalDownIzq;
		}
		else
		if (destinoX == actorX + (casilla.GetAnchoCasilla() * 2) && destinoY==actorY+casilla.GetAltoCasilla())
		{
//			System.out.println("derecha arriba");
			return derechaArriba;
		}
		else
		if (destinoX == actorX + (casilla.GetAnchoCasilla() * 2) && destinoY==actorY-casilla.GetAltoCasilla())
		{
//			System.out.println("derecha abajo");
			return derechaAbajo;
		}
		else
		if (destinoY == actorY + (casilla.GetAltoCasilla()*2) && destinoX==actorX+casilla.GetAnchoCasilla()) 
		{
//			System.out.println("arriba derecha");
			return arribaDerecha;
		}
		else
		if (destinoY == actorY + (casilla.GetAltoCasilla()*2) && destinoX==actorX-casilla.GetAnchoCasilla()) 
		{
//			System.out.println("arriba izquierda");
			return arribaIzquierda;
		}
		else
		if (destinoX == actorX - (casilla.GetAnchoCasilla() * 2) && destinoY==actorY+casilla.GetAltoCasilla())
		{
//			System.out.println("izquierda arriba");
			return izquierdaArriba;
		}
		else
		if (destinoX == actorX - (casilla.GetAnchoCasilla() * 2) && destinoY==actorY-casilla.GetAltoCasilla())
		{
//			System.out.println("izquierda abajo");
			return izquierdaAbajo;
		}
		else
		if (destinoY == actorY - (casilla.GetAltoCasilla()*2) && destinoX==actorX+casilla.GetAnchoCasilla()) 
		{
//			System.out.println("abajo derecha");
			return abajoDerecha;
		}
		else
		if (destinoY == actorY - (casilla.GetAltoCasilla()*2) && destinoX==actorX-casilla.GetAnchoCasilla()) 
		{
//			System.out.println("abajo izquierda");
			return abajoIzquierda;
		}

		return null;	
	}
	
	
	/**
	 * Metodo para rotar el actor segun sea la direccion de movimiento
	 * @param direccion direccion del movimiento
	 */
	public void rotarActorExtra(String direccion){
		
		
		if(direccion.equals("diagonalAbajoDerecha"))
		{
			actorExtra.setRotation(315);
		}
else
if(direccion.equals("abajo"))
		{
			actorExtra.setRotation(270);
		}
else
if(direccion.equals("diagonalAbajoIzquierda"))
		{
			actorExtra.setRotation(225);
		}
else
if(direccion.equals("izquierda"))
		{
			actorExtra.setRotation(180);
		}
else
if(direccion.equals("diagonalArribaIzquierda"))
		{               
			actorExtra.setRotation(135);
		}
else
if(direccion.equals("arriba"))
		{
			actorExtra.setRotation(90);
		}
else
if(direccion.equals("diagonalArribaDerecha"))
		{
			actorExtra.setRotation(45);
		}
	
else
if(direccion.equals("derechaAbajo"))
		{
			actorExtra.setRotation(337.5f);
		}		

else
if(direccion.equals("abajoDerecha"))
		{
			actorExtra.setRotation(292.5f);
		}			
				
else
if(direccion.equals("abajoIzquierda"))
		{
			actorExtra.setRotation(247.5f);
		}			
		
else
if(direccion.equals("izquierdaAbajo"))
		{
			actorExtra.setRotation(202.5f);
		}		
else
if(direccion.equals("izquierdaArriba"))
		{
			actorExtra.setRotation(157.5f);
		}		
else
if(direccion.equals("arribaIzquierda"))
		{
			actorExtra.setRotation(112.5f);
		}	
else
if(direccion.equals("arribaDerecha"))
		{
			actorExtra.setRotation(67.5f);
		}		
else
if(direccion.equals("derechaArriba"))
		{
			actorExtra.setRotation(22.5f);
		}		
	
else
		{
			actorExtra.setRotation(0);
		}
	}

	/**
	 * 
	 * @param pPgn posici�n de tablero para saber si est� opcupada o no 
	 * @return 
	 */

	public boolean casillaEstaOcupada(String pPgn) {
		
		for (int i = 0; i <= 31; i++) {
		
			try {				
				if (casillas[i].getPgn().equals(pPgn)){
					
						return true;
				}
			} catch (Exception e) {
				
			}
		}
	
		return false;		
	}
	
	
	/**
	 * Procedimiento para actualizar una partida donde se gano el minijuego
	 * 
	 * @return Devuelver verdadero si actualizo sin error o falso en caso
	 *         contrario
	 * @author yisheng
	 */
	private Boolean actualizarPartida() {
		try {
			ultimoMinBatalla=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ultimo"));
		} catch (Exception e1) {
			System.out.println("Error obteniendo el ultimo minijjuego de la batalla");
			
			e1.printStackTrace();
		}
		int nroMinijuego = Integer.parseInt(minijuego.substring(9));
		int nroBatalla = Integer.parseInt(batalla.substring(7));
		if (ultimoMinBatalla) {
			nroBatalla++;
			nroMinijuego++;
		} else {
			nroMinijuego++;
		}

		try {

		//	historia=false;
			if (nroBatalla==8 )
			{
				
				cortinaFondo.stop();
				sonidoTriunfo.stop();
				partida.setBatalla( "Batalla" + nroBatalla);
				partida.setMinijuego("Minijuego" +  36);
				//game.video.setVideo();
				
				if (a!=null)
				{
					a.dispose();
					a=null;
					System.gc();
					System.out.println("entre a limpiar");
				}
				a = new ScreenVideo(game);
				try {
					a.setVideoFinal(true);
					a.asignarPartida("assets/video/cuarto.ogv", partida, minijuego,dificultad,configuracion
							);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

				game.setScreen(a);
				
				
//				game.video.setVideoFinal(true);
//				game.video.asignarPartida("assets/video/cuarto.ogv", partida, minijuego,dificultad,configuracion);
//				game.setScreen(game.video);
	
				
			}

			else
			{
				int batallaPartida = Integer.parseInt(partida.getBatalla().substring(7));
				int minijuegoPartida = Integer.parseInt(partida.getMinijuego().substring(9));
				
				partida.setMonedas( String.valueOf(cantidadMonedas));
				// (nombrePartida, "Minijuego", "Moneda" + moneda);
	
				if (batallaPartida < nroBatalla) {
					partida.setBatalla( "Batalla" + nroBatalla);
					partida.setMinijuego("Minijuego" +  nroMinijuego);

				} else {
					
					if (batallaPartida == nroBatalla) {
						if (minijuegoPartida < nroMinijuego) {
							partida.setMinijuego("Minijuego" +  nroMinijuego);
							partida.setFen( "");
	
						}
					}
				}
					
			}
			return partida.ActualizarPartida();
			

		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		

	}

	/**
	 * Consulta al motor las posibles jugadas para la pieza que se encuentra en el escaque seleccionado
	 * @param pPgn posicion pgn de la casilla donde se encuentra las pieza a consultar
	 * @return Vector con las posicion de las casillas donde se puede mover la pieza
	 */

	private void jugadaAyuda(){
		//eliminarJugadaAyuda();
		eliminarMovimientoPosibles();
		
		eliminarJugadaAyuda();
		//Traductor traductor = new Traductor();		
		ArrayList< String> resPos =  new  ArrayList<String>() ; 		
		//System.out.println("movimientosPosibles enviando: " + "moves " + traductor.CoordenadasANumeros(8,8, pPgn));					
		resPos = enviarMangoPaola("hint" );
		String respuesta= resPos.get(0).substring(6, 10);
//		System.out.println("respuesta:"+ respuesta);
		movimientoAyuda = new ActorExtra[2];				
			
			    String vPgn1 = respuesta.substring(0,2).toUpperCase();	
//			    System.out.println("vPgn1:"+ vPgn1);
					movimientoAyuda[0] = new ActorExtra(this, TipoDeActor.CUADROAYUDA, stage.getActors().size + 1);
					movimientoAyuda[0].setPosition(tablero.Pgn2XY(vPgn1)[0], tablero.Pgn2XY(vPgn1)[1]);
					movimientoAyuda[0].setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
					movimientoAyuda[0].setVisible(true);
					stage.addActor(movimientoAyuda[0]);
					movimientoAyuda[0].setZIndex(1);
							
						
					vPgn1 = respuesta.substring(2,4).toUpperCase();				
//				    System.out.println("vPgn1:"+ vPgn1);
					
					movimientoAyuda[1] = new ActorExtra(this, TipoDeActor.CUADROAYUDA, stage.getActors().size + 1);
					movimientoAyuda[1].setPosition(tablero.Pgn2XY(vPgn1)[0], tablero.Pgn2XY(vPgn1)[1]);
					movimientoAyuda[1].setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
					movimientoAyuda[1].setVisible(true);
					stage.addActor(movimientoAyuda[1]);
					movimientoAyuda[1].setZIndex(1);	
									
	}

	/**
	 * elimina del stage los posibles movimientos
	 */
	private void eliminarJugadaAyuda() {
//		System.out.println("eliminado las jugadas posibles");
		if (movimientoAyuda!=null)
		for(ActorExtra vActorExtra: movimientoAyuda){ // remover los cuadros de las posibles jugadas 
			if (movimientoAyuda.length >0)
				stage.getActors().removeValue(vActorExtra, true);	
			}		
	
			
	}

	/**
	 * Gasto de monedas
	 *@param costo, valor a reducir en las monedas
	 * @throws Exception
	 * @author yisheng
	 */
	protected void gastarMonedas(int costo) throws Exception {
		cantidadMonedas=cantidadMonedas-costo;
		etiquetaMoneda.setText("x" + String.valueOf(cantidadMonedas));
		partida.setMonedas(""+cantidadMonedas);
		sonidoMoneda.stop();
		if (tocarSonido){		
			sonidoMoneda.play();
		}
	}

	/**
	 * Metodo para animar el movimiento de las piezas patriotas
	 * 
	 * @param pPieza pieza que esta en movimiento
	 * @param pPgni  pgn de inicio de la pieza
	 * @param pPgnf	 pgn final de la pieza 
	 */
	public void animacionMovimientoPatriotas(Pieza pPieza,String pPgni,String pgnf){
		if(tipoDePiezas.equals("MiniHeroe")){
		String pPgnf=pgnf.substring(0, 2);
		String direccion=determinarMov(pPgni, pPgnf);
		
		if(direccion.equals("diagonalAbajoDerecha"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.setPack(alfilPatriotaDerechaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaPatriotaDerechaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyPatriotaDerechaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
					{
						pPieza.setPack(peonPatriotaDerechaAbajo);
					}
			}
		else
		if(direccion.equals("abajo"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.setPack(torrePatriotaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaPatriotaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyPatriotaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
					{
						pPieza.setPack(peonPatriotaAbajo);
					}
					
			}
		else
		if(direccion.equals("diagonalAbajoIzquierda"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.setPack(alfilPatriotaIzquierdaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaPatriotaIzquierdaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyPatriotaIzquierdaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
					{
						pPieza.setPack(peonPatriotaIzquierdaAbajo);
					}
			}
		else
		if(direccion.equals("izquierda"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.setPack(torrePatriotaIzquierda);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaPatriotaIzquierda);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyPatriotaIzquierda);
					}
			}
		else
		if(direccion.equals("diagonalArribaIzquierda"))
			{               
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.setPack(alfilPatriotaIzquierdaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaPatriotaIzquierdaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyPatriotaIzquierdaArriba);
					}
				else
					if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
						{
							pPieza.setPack(peonPatriotaIzquierdaArriba);
						}
			}
		else
		if(direccion.equals("arriba"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.setPack(torrePatriotaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaPatriotaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyPatriotaArriba);
					}
				else
					if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
						{
							pPieza.setPack(peonPatriotaArriba);
						}
			}
		else
		if(direccion.equals("diagonalArribaDerecha"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.setPack(alfilPatriotaDerechaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaPatriotaDerechaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyPatriotaDerechaArriba);
					}
				else
					if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
						{
							pPieza.setPack(peonPatriotaDerechaArriba);
						}
			}
		else
		if(direccion.equals("derecha"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.setPack(torrePatriotaDerecha);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaPatriotaDerecha);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyPatriotaDerecha);
					}
			}		
			
		else	
			if(direccion.equals("derechaAbajo"))
				{
				pPieza.set_texture(null);
				pPieza.setPack(caballoDerechaAbajo);		
				}		
				
			else
			if(direccion.equals("abajoDerecha"))
				{
				pPieza.set_texture(null);
				pPieza.setPack(caballoAbajoDerecha);			
				}			
								
			else
			if(direccion.equals("abajoIzquierda"))
				{
				pPieza.set_texture(null);
				pPieza.setPack(caballoAbajoIzquierda);		
				}			
						
			else
			if(direccion.equals("izquierdaAbajo"))
				{
				pPieza.set_texture(null);
				pPieza.setPack(caballoIzquierdaAbajo);		
				}		
				else
				if(direccion.equals("izquierdaArriba"))
						{
					pPieza.set_texture(null);
					pPieza.setPack(caballoIzquierdaArriba);
						}		
				else
				if(direccion.equals("arribaIzquierda"))
						{
					pPieza.set_texture(null);
					pPieza.setPack(caballoArribaIzquierda);
						}	
				else
				if(direccion.equals("arribaDerecha"))
						{
							pPieza.set_texture(null);
							pPieza.setPack(caballoArribaDerecha);
						}		
				else
				if(direccion.equals("derechaArriba"))
						{
					pPieza.set_texture(null);
					pPieza.setPack(caballoDerechaArriba);
						}		
					
				else
					{
						
					}

	}	}
	
	
	/**
	 * Metodo para animar el movimiento de las piezas realistas
	 * 
	 * @param pPieza pieza que esta en movimiento
	 * @param pPgni  pgn de inicio de la pieza
	 * @param pPgnf	 pgn final de la pieza 
	 */
	public void animacionMovimientoRealistas(Pieza pPieza,String pPgni,String pgnf){
		if(tipoDePiezas.equals("MiniHeroe")){
		String pPgnf=pgnf.substring(0, 2);
		String direccion=determinarMov(pPgni, pPgnf);
		
		if(direccion.equals("diagonalAbajoDerecha"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.setPack(alfilRealistaDerechaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaRealistaDerechaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyRealistaDerechaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
					{
						pPieza.setPack(peonRealistaDerechaAbajo);
					}
			}
		else
		if(direccion.equals("abajo"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.setPack(torreRealistaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaRealistaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyRealistaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
					{
						pPieza.setPack(peonRealistaAbajo);
					}
					
			}
		else
		if(direccion.equals("diagonalAbajoIzquierda"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.setPack(alfilRealistaIzquierdaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaRealistaIzquierdaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyRealistaIzquierdaAbajo);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
					{
						pPieza.setPack(peonRealistaIzquierdaAbajo);
					}
			}
		else
		if(direccion.equals("izquierda"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.setPack(torreRealistaIzquierda);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaRealistaIzquierda);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyRealistaIzquierda);
					}
			}
		else
		if(direccion.equals("diagonalArribaIzquierda"))
			{               
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.setPack(alfilRealistaIzquierdaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaRealistaIzquierdaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyRealistaIzquierdaArriba);
					}
				else
					if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
						{
							pPieza.setPack(peonRealistaIzquierdaArriba);
						}
			}
		else
		if(direccion.equals("arriba"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.setPack(torreRealistaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaRealistaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyRealistaArriba);
					}
				else
					if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
						{
							pPieza.setPack(peonRealistaArriba);
						}
			}
		else
		if(direccion.equals("diagonalArribaDerecha"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.setPack(alfilRealistaDerechaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaRealistaDerechaArriba);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyRealistaDerechaArriba);
					}
				else
					if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
						{
							pPieza.setPack(peonRealistaDerechaArriba);
						}
			}
		else
		if(direccion.equals("derecha"))
			{
				pPieza.set_texture(null);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.setPack(torreRealistaDerecha);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Reina))
					{
						pPieza.setPack(damaRealistaDerecha);
					}
				else
				if(pPieza.getTipoPieza().equals(eTipoPieza.Rey))
					{
						pPieza.setPack(reyRealistaDerecha);
					}
			}		
			
		else	
			if(direccion.equals("derechaAbajo"))
				{
				pPieza.set_texture(null);	
				pPieza.setPack(caballoRealistaDerechaAbajo);
				}		
				
			else
			if(direccion.equals("abajoDerecha"))
				{
				pPieza.set_texture(null);	
				pPieza.setPack(caballoRealistaAbajoDerecha);		
				}			
								
			else
			if(direccion.equals("abajoIzquierda"))
				{
				pPieza.set_texture(null);	
				pPieza.setPack(caballoRealistaAbajoIzquierda);		
				}			
						
			else
			if(direccion.equals("izquierdaAbajo"))
				{
				pPieza.set_texture(null);	
				pPieza.setPack(caballoRealistaIzquierdaAbajo);
							
					}		
				else
				if(direccion.equals("izquierdaArriba"))
						{
					pPieza.set_texture(null);	
					pPieza.setPack(caballoRealistaIzquierdaArriba);
						}		
				else
				if(direccion.equals("arribaIzquierda"))
						{
					pPieza.set_texture(null);	
					pPieza.setPack(caballoRealistaArribaIzquierda);
						}	
				else
				if(direccion.equals("arribaDerecha"))
						{
					pPieza.set_texture(null);	
					pPieza.setPack(caballoRealistaArribaDerecha);
						}		
				else
				if(direccion.equals("derechaArriba"))
						{
					pPieza.set_texture(null);	
					pPieza.setPack(caballoRealistaDerechaArriba);
						}		
					
				else
					{
						
					}
		}
	}	
	
	
	
	
	/**
	 * Metodo encargado de cargar todas las animaciones de las piezas
	 */
	private void cargarAnimacionesPatriotas(){
		
		alfilPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/alfil/AlfilDerechaAbajo.pack"));
		alfilPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/alfil/AlfilDerechaArriba.pack"));
		alfilPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/alfil/AlfilIzquierdaArriba.pack"));
		alfilPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/alfil/AlfilIzquierdaAbajo.pack"));
		
		torrePatriotaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/torre/torreArriba.pack"));
		torrePatriotaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/torre/torreAbajo.pack"));
		torrePatriotaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/torre/torreDerecha.pack"));
		torrePatriotaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/torre/torreIzquierda.pack"));
		
		damaPatriotaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/dama/damaIzquierda.pack"));
		damaPatriotaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/dama/damaDerecha.pack"));
		damaPatriotaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/dama/damaEspalda.pack"));
		damaPatriotaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/dama/damaFrontal.pack"));
		damaPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/dama/damaDiagonalDerAbajo.pack"));
		damaPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/dama/damaDiagonalDerArriba.pack"));
		damaPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/dama/damaDiagonalIzqArriba.pack"));
		damaPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/dama/damaDiagonalIzqAbajo.pack"));

		if(historia){
		
		if(avatar.equals("Sebastian"))
		{
		reyPatriotaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasIzq.pack"));
		reyPatriotaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasDer.pack"));
		reyPatriotaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasEspalda.pack"));
		reyPatriotaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasFrontal.pack"));
		reyPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasDerAbajo.pack"));
		reyPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasDerArriba.pack"));
		reyPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasIzqArriba.pack"));
		reyPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasIzqAbajo.pack"));
		}
	else
	if (avatar.equals("Camila")) 
		{
		reyPatriotaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyCamilaIzq.pack"));
		reyPatriotaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyCamilaDer.pack"));
		reyPatriotaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyCamilaEspalda.pack"));
		reyPatriotaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyCamilaFrontal.pack"));
		reyPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyCamilaDerAbajo.pack"));
		reyPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyCamilaDerArriba.pack"));
		reyPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyCamilaIzqArriba.pack"));
		reyPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyCamilaIzqAbajo.pack"));
		}
	else
		{
		reyPatriotaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyAndresIzq.pack"));
		reyPatriotaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyAndresDer.pack"));
		reyPatriotaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyAndresEspalda.pack"));
		reyPatriotaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyAndresFrontal.pack"));
		reyPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyAndresDerAbajo.pack"));
		reyPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyAndresDerArriba.pack"));
		reyPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyAndresIzqArriba.pack"));
		reyPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reyAndresIzqAbajo.pack"));	
		}
		
		}
		else
		{
		reyPatriotaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasIzq.pack"));
		reyPatriotaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasDer.pack"));
		reyPatriotaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasEspalda.pack"));
		reyPatriotaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasFrontal.pack"));
		reyPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasDerAbajo.pack"));
		reyPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasDerArriba.pack"));
		reyPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasIzqArriba.pack"));
		reyPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/rey/reySebasIzqAbajo.pack"));
		}
		peonPatriotaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonEspalda.pack"));
		peonPatriotaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonFrontal.pack"));
		peonPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonDiagonalDerAbajo.pack"));
		peonPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonDiagonalDerArriba.pack"));
		peonPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonDiagonalIzqArriba.pack"));
		peonPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonDiagonalIzqAbajo.pack"));
		
		caballoAbajoDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoAbajoDerecha.pack"));
		caballoAbajoIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoAbajoIzquierda.pack"));
		caballoArribaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoArribaDerecha.pack"));
		caballoArribaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoArribaIzquierda.pack"));
		caballoDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoDerechaArriba.pack"));
		caballoDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoDerechaAbajo.pack"));
		caballoIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoIzquierdaArriba.pack"));
		caballoIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoIzquierdaAbajo.pack"));
		
		
	}


	/**
	 * Metodo encargado de cargar todas las animaciones de las piezas realistas
	 */
	private void cargarAnimacionesRealistas(){
		
		alfilRealistaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/alfil/alfilAbajoDerecha.pack"));
		alfilRealistaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/alfil/alfilArribaDerecha.pack"));
		alfilRealistaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/alfil/alfilArribaIzquierda.pack"));
		alfilRealistaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/alfil/alfilAbajoIzquierda.pack"));
		
		torreRealistaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/torre/torreArriba.pack"));
		torreRealistaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/torre/torreAbajo.pack"));
		torreRealistaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/torre/torreDerecha.pack"));
		torreRealistaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/torre/torreIzquierda.pack"));
		
		damaRealistaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dama/damaIzquierda.pack"));
		damaRealistaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dama/damaDerecha.pack"));
		damaRealistaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dama/damaEspalda.pack"));
		damaRealistaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dama/damaFrontal.pack"));
		damaRealistaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dama/damaDiagonalDerAbajo.pack"));
		damaRealistaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dama/damaDiagonalDerArriba.pack"));
		damaRealistaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dama/damaDiagonalIzqArriba.pack"));
		damaRealistaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dama/damaDiagonalIzqAbajo.pack"));

		reyRealistaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/rey/reyIzquierda.pack"));
		reyRealistaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/rey/reyDerecha.pack"));
		reyRealistaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/rey/reyEspalda.pack"));
		reyRealistaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/rey/reyFrontal.pack"));
		reyRealistaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/rey/reyDiagonalDerAbajo.pack"));
		reyRealistaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/rey/reyDiagonalDerArriba.pack"));
		reyRealistaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/rey/reyDiagonalIzqArriba.pack"));
		reyRealistaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/rey/reyDiagonalIzqAbajo.pack"));
		
		peonRealistaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/peon/peonEspalda.pack"));
		peonRealistaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/peon/peonFrontal.pack"));
		peonRealistaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/peon/peonDiagonalDerAbajo.pack"));
		peonRealistaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/peon/peonDiagonalDerArriba.pack"));
		peonRealistaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/peon/peonDiagonalIzqArriba.pack"));
		peonRealistaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/peon/peonDiagonalIzqAbajo.pack"));
		
		caballoRealistaAbajoDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/caballo/caballoAbajoDerecha.pack"));
		caballoRealistaAbajoIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/caballo/caballoAbajoIzquierda.pack"));
		caballoRealistaArribaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/caballo/caballoArribaDerecha.pack"));
		caballoRealistaArribaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/caballo/caballoArribaIzquierda.pack"));
		caballoRealistaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/caballo/caballoDerechaArriba.pack"));
		caballoRealistaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/caballo/caballoDerechaAbajo.pack"));
		caballoRealistaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/caballo/caballoIzquierdaArriba.pack"));
		caballoRealistaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/caballo/caballoIzquierdaAbajo.pack"));
		
	}

	private String  obtenerFenExtra(String pDificultad, String pArchivo){	
//		System.out.println("obtenerFenExtra  acrhivo " + pArchivo );
	   
		
		
//			System.out.println("ruta: " + Gdx.files.getLocalStoragePath() + "Configuracion/" + pArchivo);
			Document documento = null;
			try {
				documento = leerArchivo(Gdx.files.getLocalStoragePath() + "Configuracion/" + pArchivo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			NodeList list = documento.getElementsByTagName("Dificultad");

			for (int j = 0; j <= list.getLength(); j++) {
//				 System.out.println("entre : " + j);
				 System.out.println("dificultad : " + pDificultad);
//				 System.out.println("dificultad : " + list.item(j).getFirstChild().getNodeValue());

				
				if (list.item(j).getFirstChild().getNodeValue().contains(pDificultad)){
					
//					System.out.println("nodos " + list.item(j).getNodeName());
//					System.out.println("encontr� mi difcultad");
					NodeList nodos = list.item(j).getChildNodes();
				int vRnd = 0;
				while(vRnd%2==0)	 
					vRnd =(int) (Math.random()*nodos.getLength()-1 );
//				System.out.println("escogiendo el elemento :" + vRnd);
//					System.out.println("nombre " + list.item(j).getChildNodes().item(vRnd).getNodeName() + " valor" + list.item(j).getChildNodes().item(vRnd).getTextContent());
//					System.out.println("random: " + vRnd);
					return 	list.item(j).getChildNodes().item(vRnd).getTextContent();					
				}
			}		
		//	return respuesta;			
		return "rnb1kbnr/ppp1p1pp/3p4/8/8/8/8/RNBQKBNR w  - 0 0";
}

	public static Document leerArchivo(String ruta) throws Exception {
		//System.out.println("leyendo y desencriptando");
		String rutaTemporal="destmp.xml";
		//System.out.println(ruta);
		//System.out.println(rutaTemporal);
		
		Encriptado.desencriptado(ruta, rutaTemporal);
		  
		File xmlFile = new File(rutaTemporal);
		DocumentBuilderFactory creadorDocumento = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentoCreado = creadorDocumento.newDocumentBuilder();
		Document documento = documentoCreado.parse(xmlFile);
		return documento;
	}

	private void jugadaSecreta(){
	//	Traductor traductor = new Traductor();		
		ArrayList< String> resPos =  new  ArrayList<String>() ; 						
		resPos = enviarMangoPaola("hint" );
		String respuesta= "[" + resPos.get(0).split(" \\[")[1];
//		System.out.println("jugada secreta respuesta:  "+ respuesta);
		jugadasAlg.add("debes jugar" + respuesta);
		listaDeJugadas.setItems(jugadasAlg.toArray());
		listaDeJugadas.setSelectedIndex(jugadasAlg.size() - 1);


		}
	
	/**
	 * completar con espacios una cadena de caracteres hasta un tama�o determinado
	 * @param cadena
	 * @param tamano
	 * @return
	 * @author yisheng
	 */
	private String tamano (String cadena, int tamano)
	{
		
		for (int i=cadena.length();i<tamano;i++)
		{
		 cadena+=" ";	
		}
		return cadena;
	}
	
	private void reintentar( boolean pPerdido){
		 				 		
		 		if (tocarSonido){
		 			cortinaFondo.stop();
		 			if (pPerdido)
		 				sonidoDerrota.play();
		 			
		 			//else
		 				//sonidoTriunfo.play();						      
		 		}
		 		System.out.println("minijuego reintentar:"+ minijuego);
		 		if (minijuego.equals("Minijuego27"))
		 		if (jugadasSecretasSeguidas >=6 ){// gan�
		 			bGanar = true;

					System.out.println("ct21");
		 			CambiarTurno();
		 			//ganar();		 					 
		 		}
		 		else if (jugadasSecretasNoSeguidas >=4){// perdi�
		 				bPerder =true; 

						System.out.println("ct22");
		 				CambiarTurno();
		 				//perder();		 
		 			}
		 		else if (pPerdido){		
		 				mostrarDialogo(eTipoDialogo.Mensaje, eTipoMensaje.MensajeSecretoDerrotaParcial);
		 				System.out.println("pperdido true");
		 		}
		 		
		 			else{
		 				System.out.println("pperdido true");
		 				mostrarDialogo(eTipoDialogo.Mensaje, eTipoMensaje.MensajeSecretoVictoriaParcial);
		 				}
		 	
		 		
		 	}
	

	public void ganar(){
		
		if (historia)
			Turno = Color.WHITE;
		
		motorIniciado=false;
		bGanar=false;
		
		while (!jugar())
		{System.out.println("aún no llego");}

		if(!primerajugada)
		{
		System.out.println( "entrando a ganar");
		if(tocarSonido){
			System.out.println("tocando sonido victoria");
			sonidoTriunfo.play();
		}
		
		
		if(historia){			
			if (jugadasSecretasSeguidas>=6 && minijuego.equals("Minijuego27")){
				jugadasSecretasSeguidas = 0;
				jugadasSecretasNoSeguidas = 0;
			}
			actualizarPartida();				
            txcargaFen="";
            
			cargarVideoInformativo(eTipoVideo.Victoria);
		}
		else{
			guardarJuegos(Enums.victoria);
			mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.JuegoFinalizadoVictoria);
		}
		
		ultimaJudada="";
		}				
	}
	
	public void ganartiempo(boolean blancas){
			
			if(tocarSonido)
			{
				System.out.println("tocando sonido victoria");
				sonidoTriunfo.play();
			}
	setTurno(Color.BLUE);
	if(blancas)
			{
			mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.GanaBlancas);	
			}
	else 	{
			mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.GanaNegras);	
			}
			
	}
		
	public void perder(){
		cambioPiezaPermitido = false;
		if (historia)
			Turno = Color.WHITE;
		
		motorIniciado=false;
		bPerder=false;
		if(tocarSonido){
			sonidoDerrota.play();
		}
		
		if (historia){
			jugadasSecretasSeguidas = 0;
			jugadasSecretasNoSeguidas = 0;
		}
		ultimaJudada="";
		
		if (historia)
			cargarVideoInformativo(eTipoVideo.Derrota);
		else{
			guardarJuegos(Enums.derrota);
			mostrarDialogo(eTipoDialogo.Mensaje, eTipoMensaje.JuegoFinalizadoDerrota);
		}
		if (historia)
		{
		cantidadMonedas=monedasInicio;
		try {
			gastarMonedas(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void tablas(){
		if (historia)
			Turno = Color.WHITE;

		motorIniciado=false;

		bTablas=false;
if(tocarSonido){
			sonidoDerrota.play();
		}
		
		ultimaJudada="";
		
		if (historia)
			cargarVideoInformativo(eTipoVideo.Derrota);
		else{
			guardarJuegos(Enums.tablas);
			mostrarDialogo(eTipoDialogo.Mensaje, eTipoMensaje.JuegoFinalizadoTablas);
		}
		if (historia)
		{
		cantidadMonedas=monedasInicio;
		try {
			gastarMonedas(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public void setHistoria(Boolean historia) {
		this.historia = historia;
	}

	/**
	 * Muestra en un cuadro de dialogo el video de derrota o victoria para el minijuego
	 */
	private boolean cargarVideoInformativo(eTipoVideo pTipoVideo) {
	
		String vPath = "";
		//eTipoMensaje vTipoMensaje = null;
		switch (pTipoVideo) {
		case Victoria:

			vPath = "assets/video/" + batalla +  "/" + minijuego + "v.ogv";
//			vTipoMensaje= eTipoMensaje.JuegoFinalizadoVictoria;
			
			break;
		case Derrota:			
			vPath = "assets/video/" + batalla +  "/" + minijuego + "d.ogv";
	//		vTipoMensaje= eTipoMensaje.JuegoFinalizadoDerrota;
			
			break; 
		case Ayuda:
			vPath = "assets/video/"+ batalla +  "/" + minijuego + "a.ogv";
		break;
		
		default:			
			break;
		}					
		try{
			System.out.println("entrando a cargar este video: " + vPath);
			cargarvideo(vPath, "       ", pTipoVideo);
			return true;
		}
		catch(Exception e){
//			System.out.println("Error, no se puede cargar el video: " + vPath);
			return false;
		}
	}

	/**
	 * Muestra en un cuadro de dialogo un video
	 */
	private void cargarvideo(String video, String mensaje,eTipoVideo tipoVideo) {
		System.out.println("cargarvideo abriendo " + tipoVideo.toString());
		System.out.println("cargarvideo abriendo " + video);
			cortinaFondo.stop();
		
		motorIniciado=false;	
		if (stage.getActors().contains(videoScreen, true)) {			
			//videoScreen.remove();
			videoScreen.dispose();
			videoScreen=null;
			videoScreen.remove();
			gc();
		}

		videoScreen = new Dialogo(); // TODO wilmer
		videoScreen.create( video, tipoVideo, false);
		//videoScreen = Dialogo.getInstance(video, tipoVideo);
		videoScreen.setWidth(800);
		videoScreen.setHeight(500);
		videoScreen.setPosition(25, 25);
		System.out.println("cargando dialogo de video");
		stage.addActor(videoScreen);
		System.out.println("cargó dialogo de video");
		
			final eTipoVideo vTipoVideo = tipoVideo;
				
			
			videoScreen.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					try {
						stage.removeListener(this);
						procesarVideoListener(videoScreen.getResult(), vTipoVideo);
				      System.out.println("video procesado");
							
					    System.out.println("borrar el listener");
					    //motorIniciado =true;
					    //videoScreen.remove();
					} catch (Exception e) {
						System.out.println("Error explote: " + e.getMessage() + " - " + e.getCause());
						videoScreen.remove();
					}
				// TODO: handle exception
			
				
				}
			});
			videoScreen.addListener(new InputListener(){				
				@Override
				public boolean keyDown( InputEvent event, int keyCode){	   			
	   			System.out.println("KeyCode" + keyCode);
	   			if (keyCode  == Keys.ENTER || keyCode  == Keys.ESCAPE)
					stage.removeListener(this);
					
	   			    procesarVideoListener(videoScreen.getResult(), vTipoVideo);
					motorIniciado =true;
					
					return false;
	   			}
			}); 	
			
			
			System.out.println("sali del video");
	}
	public void procesarVideoListener(Object pResult, eTipoVideo pTipoVideo){
		try {
//			System.out.println("videoScreen.getResult(): " + pResult);
			if (historia){
				System.out.println("modo historia " + pTipoVideo);
				System.out.println("este es el result : "+pResult);
				if (pResult.equals(true)) {
					//videoScreen.dipose();
					if (pTipoVideo.equals(eTipoVideo.Victoria)){									
						System.out.println("video de victoria");
						historia= false;
						try {
							ultimoMinBatalla=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ultimo"));
						} catch (Exception e1) {
							System.out.println("Error obteniendo el ultimo minijjuego de la batalla");
							ultimoMinBatalla= false;
							e1.printStackTrace();
						}
						actualizarPartida();
						if(ultimoMinBatalla){
							System.out.println("mapageneral");
							motorIniciado=true;
							
							
							if (mapGen!=null)
							{
								mapGen.dispose();
								mapGen=null;
								System.gc();
								System.out.println("entre a limpiar");
							}
							mapGen = new mapaGeneralScreen(game,"batalla0");
							try {
								mapGen.asignarPartida(partida,dificultad,configuracion,false
										);
								
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							

							game.setScreen(mapGen);
							
//							game.mapGen.asignarPartida(partida,dificultad,configuracion,false);
//							game.setScreen(game.mapGen);
						}
						else{
							System.out.println("minimapa");
							motorIniciado=true;
							game.mapMin.asignarPartida(partida, batalla,dificultad,configuracion,true);				
							game.setScreen(game.mapMin);
							//dispose();
						}
					}
					else if  (pTipoVideo.equals(eTipoVideo.Derrota)){
						motorIniciado=true;
						nuevaPartida();
						//game.setScreen(game.juegoPrincipal);
					}
					else if (pTipoVideo.equals(eTipoVideo.Ayuda)){
						System.out.println("cargando ayuda");
						cargarayuda();	}			
					else if (pTipoVideo.equals(eTipoVideo.Historia)){
						System.out.println("cargando video de historia");
						cargarhistoria();
					}																						
					else if (pTipoVideo.equals(eTipoVideo.Biografia))
					{	System.out.println("cargando video de biografia 2");
						cargarBiografia();							
					}	
				}						
					else if (pResult.equals(false)) {
						System.out.println("videoScreen.getResult().equals(false) vTipoVideo: "+pTipoVideo);
							if(pTipoVideo.equals(eTipoVideo.Derrota)){											
								cortinaDerrota.stop();
								System.out.println("derrota");
								motorIniciado=true;
								game.setScreen(game.mapMin);
							}
							else if (pTipoVideo.equals(eTipoVideo.Ayuda) ||pTipoVideo.equals(eTipoVideo.Historia)||pTipoVideo.equals(eTipoVideo.Biografia)){
								motorIniciado=true;
								videoScreen.remove();
								if (!conversacion.isArriba())
								  iniciarMotor();											
							}										
					}
					else if (pResult.equals("pausa")||pResult.equals("repetir")){
						System.out.println("repetir o pausa");
					motorIniciado=false;
				}else
					motorIniciado=true;
				}			
			}																		
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage() + " - " + e.getCause());
			motorIniciado=true;

			// TODO: handle exception
		}
	}


	/**
	 * Muestra en un cuadro de dialogo el video de ayuda para el minijuego
	 */
	private void cargarayuda() {	
		try{
			if (!videoAyuda.equals("0")){
				System.out.println("*-+-+-+-+-+-+-+-+" );
				
				System.out.println("cargarAyuda : " + videoAyuda);
				cargarVideoInformativo(eTipoVideo.Ayuda);
				videoAyudaMostrado = true;
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
			if (!videoBiografia.equals("0"))
				cargarvideo(videoBiografia, " Ayuda ", eTipoVideo.Biografia);
			else
				System.out.println("Error, no se ha configurado el video de Biograf�a para este minijuego");
		}
		catch(Exception e){
			System.out.println("Error cargando el video: " + videoHistoria);	
			}
		}
/**
 * Metodo que cambia la textura del rey segun sea el avatar seleccionado
 */
	public void cambiarTexturaRey(){
		for(int i=0;i<32;i++)
		{
			if(casillas[i].getPieza()!=null)
				{
					if(casillas[i].getPieza().getTipoPieza().equals(eTipoPieza.Rey)&&casillas[i].getPieza().getColor().equals(Color.WHITE) )
						{
							Pieza pieza=casillas[i].getPieza();
							((Rey) pieza).setAvatar(avatar);
							pieza.cambiarEstadoPieza(eEstadoPieza.Esperando);
							
						}
				}	
		}

	}

	/**
	 * coloca los contadores dependiendo del juego
	 * @param cantidad de contadores, 1 si solo es el humano 2 si es debe mostrar adem�s contaddor de la maquina
	 * @param humano numero hasta de dos digitos del contador
	 * @param maquina numero hasta de dos digitos del contador
	 */
	public void contador(int cantidad,boolean historia)
	{
		
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.font= font;
		pack3 = new TextureAtlas(Gdx.files.internal("assets/skins/numeros.pack"));
		skin3.addRegions(pack3);
		tbs.up= skin3.getDrawable("0");

		contadorHumano = new TextButton("", tbs);
		contadorHumano2 = new TextButton("", tbs);
		
		stage.addActor(contadorHumano);
		contadorHumano.setSize(30, 30);
		
		
		
		stage.addActor(contadorHumano2);
		contadorHumano2.setSize(30, 30);
		if(historia){
		contadorHumano.setPosition(560, 220);
		contadorHumano2.setPosition(590, 220);
		}
		else{
			contadorHumano.setPosition(690, 90);
			contadorHumano2.setPosition(720, 90);	
		}
		
		if (cantidad>1)
		{
			 contadorMaquina = new TextButton("", tbs);
			 contadorMaquina2 = new TextButton("", tbs);
			 stage.addActor(contadorMaquina);
			 contadorMaquina.setSize(30,30);
			
			
			 stage.addActor(contadorMaquina2);
			 contadorMaquina2.setSize(30, 30);
			 
			if(historia)
				{
				contadorMaquina.setPosition(560, 120);
				contadorMaquina2.setPosition(590, 120);
				}
			else
				{
				contadorMaquina.setPosition(690, 40);
				contadorMaquina2.setPosition(720, 40);
				}
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
		   tbs.up= skin3.getDrawable(digito1);
		   tbs2.up= skin3.getDrawable(digito2);
			
		   
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
	 * Comprobar tipo de contador Humano
	 */
	public void comprobarContadoresHumano(){
		if(pantallaContador){
		if(tipoContadorHumano.equals(eContadores.ContadorJugadasSecretasSeguidas.toString()))
		{	
			actualizarContador(true, jugadasSecretasSeguidas);	
			nombreContador(eContadores.ContadorJugadasSecretasSeguidas,true);
		}
		else
		if(tipoContadorHumano.equals(eContadores.ContadorJugadasSecretasNoSeguidas.toString()))
		{
			actualizarContador(true, jugadasSecretasNoSeguidas);
			nombreContador(eContadores.ContadorJugadasSecretasNoSeguidas,true);
		}
		
		}
	}

	/**
	 * Comprobar tipo de contador Maquina
	 */

	public void comprobarContadoresMaquina(){
		if(pantallaContador){
		if(cantidadContador>1){
		if(tipoContadorMaquina.equals(eContadores.ContadorJugadasSecretasNoSeguidas.toString()))
		{
			actualizarContador(false, jugadasSecretasNoSeguidas);	
			nombreContador(eContadores.ContadorJugadasSecretasNoSeguidas,false);
		}
		else
		if(tipoContadorMaquina.equals(eContadores.ContadorJugadasSecretasSeguidas.toString()))
		{
			actualizarContador(false, jugadasSecretasSeguidas);	
			nombreContador(eContadores.ContadorJugadasSecretasSeguidas,false);
		}
		
	}
		}}



	/**
	 * Metodo encargado de colocar el nombre al contador en pantalla
	 * 
	 * @param contador tipo de contador 
	 * @param humano si es contador 1 o 2
	 */
	public void nombreContador(eContadores contador,boolean humano){
		
	Texture	texture=null;
	Texture texture2=game.getManager().get("assets/Texturas/contadores/pergaminoContador.png");
		
	if(contador.equals(eContadores.ContadorJugadas)){
	texture=game.getManager().get("assets/Texturas/contadores/movimientos.png");	
	}	
	else
	if(contador.equals(eContadores.ContadorMovIncorrectos)){
	texture=game.getManager().get("assets/Texturas/contadores/incorrectos.png");	
	}
	else	
	if(contador.equals(eContadores.ContadorPromovidas)){
		texture=game.getManager().get("assets/Texturas/contadores/promovidas.png");	
	}
	else	
	if(contador.equals(eContadores.ContadorJugadasPeon)){
		texture=game.getManager().get("assets/Texturas/contadores/jugadasPeon.png");	
			
	}
	else	
	if(contador.equals(eContadores.ContadorDobleAtaque)){
		texture=game.getManager().get("assets/Texturas/contadores/doble.png");	

	}
	else	
	if(contador.equals(eContadores.ContadorPeonAlPaso)){
		texture=game.getManager().get("assets/Texturas/contadores/alPaso.png");	

	}
	else	
	if(contador.equals(eContadores.ContadorPaja)){
		texture=game.getManager().get("assets/Texturas/contadores/contPaja.png");	
			
	}
	else	
	if(contador.equals(eContadores.ContadorPiezasCapturadas)){
		texture=game.getManager().get("assets/Texturas/contadores/capturadas.png");	

	}
	else	
	if(contador.equals(eContadores.ContadorBolsasCapturadas)){
		texture=game.getManager().get("assets/Texturas/contadores/bolsasCapturadas.png");	
			
	}
	else	
	if(contador.equals(eContadores.ContadorEnrroqueRealizados)){
		texture=game.getManager().get("assets/Texturas/contadores/enroques.png");	
			
	}
	else	
	if(contador.equals(eContadores.ContadorJugadasSecretasSeguidas)){
		texture=game.getManager().get("assets/Texturas/contadores/seguidas.png");	
			
	}
	else	
	if(contador.equals(eContadores.ContadorJugadasSecretasNoSeguidas)){
		texture=game.getManager().get("assets/Texturas/contadores/noSeguidas.png");	

	}
	Image image =new Image(texture);
	Image image2 =new Image(texture2);

	if(humano)
		{
		image2.setPosition(522, 220);
		image.setPosition(525, 260);
		}
	else
		{
		image2.setPosition(522, 120);
		image.setPosition(525, 160);
		}

	image.setSize(140, 30);
	image2.setSize(137, 80);
	stage.addActor(image);
	stage.addActor(image2);
	image.setZIndex(0);
	image2.setZIndex(0);
	}

	public void gc(){
//	for (int i =0;i<=5;i++)
//		System.gc();
	}
	
	@SuppressWarnings("unchecked")
	private void procesarDialogoCargarFen( Object object, Object value){
//		System.out.println("object.toString()" + object.toString());
		
		if (value !=null){
			//System.out.println("procesarDialogoCargarFen in 2");
			int  validacion = 0;			
				if (object!=null &&object.equals(true)) {				
					txcargaFen = ((ArrayList<String>)value).get(0);							
					if (txcargaFen != null && txcargaFen.contains("/")) { // TODO
						Fen valida = new Fen();
						validacion = valida.validarFen(txcargaFen);
						validaFen = validacion;
	
						if (validacion != 0) {						
							txcargaFen = null;
							mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.FENNoAdmitido);
						} else {
							game.setScreen(game.juegoPrincipal);
						}				
					} else {
						txcargaFen  = null;
						mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.FENNoAdmitido);
					}
				}	
		}
		else if (object.toString().equals("C")){ // cargar archivo					
			dialog1 = new Dialogo("",true ) ;
			stage.addActor(dialog1);			
			dialog1.setPosition(super.gameWidth / 2 - dialog1.getWidth() / 2,
					super.gameHeight / 2 - dialog1.getHeight() / 2);
			
			dialog1.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {			
					try {								
						if (dialog1.getResult() != null) {
							if ( dialog1.getValue()!=null){
								stage.removeListener(this);
								dialog1.remove();
								if (dialog1.getResult().equals(true))
									procesarListenerCargarArchivo(dialog1.getResult(), dialog1.getValue());
							 																								
								dialog1.dispose();
								dialog1 = null;
								gc();
							}
						}
					} catch (Exception e) {									
//							System.out.println("Error " + e.getMessage());
//							stage.removeListener(this);
//							dialog.remove();
//							mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.FENNoAdmitido);								
//							dialog.dipose();
						}
					}															
			});
		}
	}
	
	private void procesarListenerCargarArchivo(Object pResultado, Object pValor) {		
		//System.out.println("procesarListenerCargarArchivo" + pResultado + " - " +  pValor);		
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
	
		ArrayList<String> mensajes =  new ArrayList<String>();
		jugadores =  new String[2];	
		
		String enunciado=null;
		
		String linea;
		
		ArrayList<String> listaJugadasRondas = new ArrayList<String>();
		ArrayList<String>listaJugadas = new ArrayList<String>();
		
		boolean EventoEncontrado = false;
		//boolean Jugador1Encontrado = false;
		//boolean Jugador2Encontrado = false;
		
		
		try {
			archivo = new File(pValor.toString());
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
				
			while((linea=br.readLine())!=null) {
				if (linea.contains("Event ")&&!EventoEncontrado) {
					EventoEncontrado = true;
					enunciado=linea.substring(8, linea.length()-2);;
				}
				
				if (EventoEncontrado) {
					if (linea.contains("White ")) {
						//Jugador1Encontrado = true;
						jugadores[0] = linea.substring(8, linea.length()-2);
					}else  
					if (linea.contains("Black ")) {
						//Jugador2Encontrado = true;
						jugadores[1] = linea.substring(8, linea.length()-2);
						//Jugador1Encontrado = false;
						//Jugador2Encontrado = false;
						mensajes.add(jugadores[0] + " vs "+ jugadores[1]);
						if (listaJugadas.size()>0) {
							String vlistaJugadas = new String();							
							for (String vLinea: listaJugadas)
								vlistaJugadas = vlistaJugadas + " " +  vLinea;							
								listaJugadasRondas.add(vlistaJugadas);
							
							listaJugadas = new ArrayList<String>();
//							System.out.println("cerrando la lista");
						}
					}  							
				}							
				
				if (!linea.contains("[")&& linea.length()>0) {
					//System.out.println("agregando: " + linea);
					 listaJugadas.add(linea);
				}
				
			}
			if (listaJugadas.size()>0) {
				String vlistaJugadas = new String();
				for (String vLinea: listaJugadas)
					vlistaJugadas = vlistaJugadas +" "+ vLinea;							
					listaJugadasRondas.add(vlistaJugadas);

				listaJugadas = new ArrayList<String>();
//				System.out.println("cerrando la lista");
			}
		}
		 catch (IOException e) {
			e.printStackTrace();
		}	
		
		final ArrayList<String> listaJugadasRondasAux = listaJugadasRondas;
		
		if(mensajes!=null) {
			final Dialogo escogerRonda = new Dialogo("  " + enunciado,false , mensajes );
									
			escogerRonda.setSize(400, 550);
			escogerRonda.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {			
					try {								
						if (escogerRonda.getResult() != null) {
							if ( escogerRonda.getValue()!=null){
								stage.removeListener(this);
								escogerRonda.remove();														
								//System.out.println("ronda esogida"+escogerRonda.getValue() );
								procesarlistaJugadas(listaJugadasRondasAux.get(Integer.parseInt(escogerRonda.getValue().toString())));						
								escogerRonda.dispose();
							}
						}
					} catch (Exception e) {									
		//					System.out.println("Error " + e.getMessage());
							stage.removeListener(this);
							escogerRonda.remove();
							mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.FENNoAdmitido);								
							escogerRonda.dispose();
						}
					}																			
			});

			stage.addActor(escogerRonda);
			escogerRonda.setPosition(super.gameWidth / 2 - escogerRonda.getWidth() / 2,
					super.gameHeight / 2 - escogerRonda.getHeight() / 2);				
		}		
	}
	
	private void procesarlistaJugadas(String pListaJugadas) {				

		
		while(pListaJugadas.indexOf("{")>0 || pListaJugadas.indexOf("}") >0 ) 	
			if ( pListaJugadas.indexOf("{") > 0 ) {				
				int vHasta =  pListaJugadas.indexOf("}") ;
				int vDesde =  pListaJugadas.lastIndexOf("{",vHasta-1) ;
				pListaJugadas  = pListaJugadas.substring(0,vDesde ) +  pListaJugadas.substring(vHasta+1,pListaJugadas.length());

		}
	//	System.out.println("jugadas1: " + pListaJugadas );
			
		while(pListaJugadas.indexOf("(")>0 || pListaJugadas.indexOf(")") >0 )
			if ( pListaJugadas.indexOf("(") > 0 ) {
				int vHasta =  pListaJugadas.indexOf(")") ;
				int vDesde =  pListaJugadas.lastIndexOf("(",vHasta-1) ;				
				pListaJugadas  = pListaJugadas.substring(0,vDesde ) + " " + pListaJugadas.substring(vHasta+1,pListaJugadas.length());
		}
		
		//System.out.println("jugadas2: " + pListaJugadas );
		while(pListaJugadas.indexOf("...")>0  )
			if ( pListaJugadas.indexOf("...") > 0 ) {
				int vHasta =  pListaJugadas.indexOf("...") ;
				int vDesde =  pListaJugadas.lastIndexOf(" ",vHasta-1) ;
				pListaJugadas  = pListaJugadas.substring(0,vDesde )  +  pListaJugadas.substring(vHasta+3,pListaJugadas.length());
		}
//		System.out.println("jugadas3: " + pListaJugadas );
		
	
	while(pListaJugadas.indexOf("$")>0  )
		if ( pListaJugadas.indexOf("$") > 0 ) {
			int vDesde =  pListaJugadas.indexOf("$") ;		
			int vHasta =  pListaJugadas.indexOf(" ",vDesde) ;
			//pListaJugadas  = pListaJugadas.substring(0,vDesde-0 ) +  pListaJugadas.substring(vDesde+3,pListaJugadas.length());
			pListaJugadas  = pListaJugadas.substring(0,vDesde ) +  pListaJugadas.substring(vHasta+1,pListaJugadas.length());
	}
//	buscar cantidad de jugadas

	
 int vHasta =	pListaJugadas.lastIndexOf(".");
 int vDesde =  pListaJugadas.lastIndexOf(" ",vHasta-1) ;

//	System.out.println("jugadas: " + pListaJugadas );
	TraductorAlg traductorAlg = new TraductorAlg(this);		
	pListaJugadas = traductorAlg.traducirLista(pListaJugadas);
	
//	System.out.println("jugadas: " + pListaJugadas);
	
 int CantidadJugadas =  Integer.parseInt(pListaJugadas.substring(vDesde+1,vHasta));
	 
 //System.out.println("cantidadJugadas: " + CantidadJugadas  );
	
	for (int i=CantidadJugadas; i>0; i--) {		
		pListaJugadas = pListaJugadas.replace(i + ".", ":");
	}
	//System.out.println("jugadas5: " + pListaJugadas );		
	
	//jugadasAlg.clear();
	
	String[] jugadasCargadas = pListaJugadas.split(":");
	
	for (int i = 1 ; i< jugadasCargadas.length; i++) {		
		jugadasCargadas[i]=  i + "." + jugadasCargadas[i].trim();
		
		while (jugadasCargadas[i].contains("  ")) {
			jugadasCargadas[i] = jugadasCargadas[i].replace("  ", " ");	
		}
	
		//System.out.println("jugadasCargadas: " +  jugadasCargadas[i]);
//		if (!jugadasCargadas[i].contains("1-0")&&!jugadasCargadas[i].contains("0-1")&&!jugadasCargadas[i].contains("1/2-1/2"))

			if (jugadasCargadas[i].split(" ").length ==2)
				jugadasAlg.add(tamano(jugadasCargadas[i].split(" ")[0] ,15)+" " + jugadasCargadas[i].split(" ")[1]);
			else 
				jugadasAlg.add(tamano(jugadasCargadas[i].split(" ")[0] ,15));
			
		//jugadasAlg.add(jugadasCargadas[i]);
	}
	listaDeJugadas.setItems(jugadasAlg.toArray());


	jugandovsMangoPaola = false;
	
	casillasDeshacer = new Casilla[32];		
	for (int k = 0; k <= 31; k++)
		casillasDeshacer[k] = casillas[k];
	listaCasillasDeshacer.add(casillasDeshacer);

	
	for (int i=1; i<jugadasCargadas.length; i++) {
	//for (int i=1; i<3; i++) {
		String[] vRes  = new String [2];		
		String[] alg = jugadasCargadas[i].split(" ");
		
//System.out.println("el 0"+alg[0].toString());

//System.out.println("el 1"+alg[1].toString());
if (jugadores[0].contains("Mango Paola")) {
			colorOponente = Color.WHITE;
			tablero.setTableroRotado(true);
				//jugandovsMangoPaola = true;
		}
			
		if (jugadores[1].contains("Mango Paola")) {
			colorOponente = Color.BLACK;
			tablero.setTableroRotado(false);
				//jugandovsMangoPaola = true;
		}
		
		
			
		if (!alg[0].contains("0-1") && !alg[0].contains("1/2-1/2") && !alg[0].contains("1-0")&&!alg[0].contains("++")) {
			this.setTurno(Color.WHITE);		
			vRes =	traductorAlg.Alg2Pgn(true,alg[0] , casillas);		
	//		System.out.println( i + ". blancas "  + alg[0]  + " " +  vRes[0] + " " + vRes[1] );

			if 	(alg[0].contains("O-O-O")) {
				Pieza vTorre = casillaOcupada("A1");
				vTorre.setPosPgn("D1");			
			}
			else
			if (alg[0].contains("O-O")) {
				Pieza vTorre = casillaOcupada("H1");
				vTorre.setPosPgn("F1");
			}
				
			Pieza vPieza = 	casillaOcupada(vRes[0]);
			if (vPieza.getTipoPieza().equals(eTipoPieza.Peon)){		
				if( vRes[1].substring(1, 2).contains("4")) {			
					peonalpaso= (Peon)vPieza;
					pgnPeonalPaso = vRes[1];
					}
				else {		
					if (peonalpaso!=null) {
						if (Math.abs(vRes[0].charAt(0) - pgnPeonalPaso.charAt(0)) == 1 && // peon al paso
						   vRes[1].charAt(0) - pgnPeonalPaso.charAt(0) == 0 &&
						   Math.abs(vRes[1].charAt(1) - pgnPeonalPaso.charAt(1)) == 1){
							EliminarPieza(vPieza, casillaOcupada(peonalpaso));
							peonalpaso=null;
						}
					}
					else {
						peonalpaso=null;
					}
				}
			}
			else			
				peonalpaso=null;
			
			EliminarPieza(vPieza, vRes[1] );
			validarMotor(vRes[0], vRes[1]);
			vPieza.setPosPgn(vRes[1]);
			//jugadasAlg.add(vRes[0] + "  " + vRes[1]);			
			jugadas.add(vRes[0] + " - " + vRes[1]);
		}
		else
		{
			if (alg[0].contains("1/2-1/2"))
				tablas = true;
			else
				jaqueMate = true;

		}
		
		if (alg.length==2){					
			if (!alg[1].contains("0-1") && !alg[1].contains("1/2-1/2") && !alg[1].contains("1-0")&&!alg[1].contains("++")) {
				
				this.setTurno(Color.BLACK);
//				System.out.println("entre" );
				vRes =	traductorAlg.Alg2Pgn(false,alg[1] , casillas);
		//		System.out.println( i + ". negras " + alg[1]  + " " + vRes[0] + " " + vRes[1] );
		
				if 	(alg[1].contains("O-O-O")) {
					Pieza vTorre = casillaOcupada("A8");				
					vTorre.setPosPgn("D8");				
				}
				else
				if (alg[1].contains("O-O")) {
					Pieza vTorre = casillaOcupada("H8");
					vTorre.setPosPgn("F8");
				}
				
				Pieza vPieza = 	casillaOcupada(vRes[0]);
	
				if (vPieza.getTipoPieza().equals(eTipoPieza.Peon)){		
					if( vRes[1].substring(1, 2).contains("5")) {			
						peonalpaso= (Peon)vPieza;
						pgnPeonalPaso = vRes[1];
						}
					else {		
						if (peonalpaso!=null) {
							if (Math.abs(vRes[0].charAt(0) - pgnPeonalPaso.charAt(0)) == 1 && // peon al paso
							   vRes[1].charAt(0) - pgnPeonalPaso.charAt(0) == 0 &&
							   Math.abs(vRes[1].charAt(1) - pgnPeonalPaso.charAt(1)) == 1){
								EliminarPieza(vPieza, casillaOcupada(peonalpaso));
								peonalpaso=null;
							}
						}
						else {
							peonalpaso=null;
						}
					}
				}
				else 
					peonalpaso=null;
									
				EliminarPieza(vPieza, vRes[1]);			
				validarMotor(vRes[0], vRes[1]);
				vPieza.setPosPgn(vRes[1]);
			}
			else {		
//				System.out.println("entre aqui 2");
				if (alg[1].contains("1/2-1/2"))
					tablas = true;
				else
					jaqueMate = true;
			}
		}
		else {// alg.length = 1 // no hay jugadas de las negras
			//System.out.println("entre aca por que tengo mas");
		}
		
	 	if (alg.length==3){// fin del juego
			if (alg[1].contains("1/2-1/2"))
				tablas = true;
			else
				jaqueMate = true;	 			 	
	 	}
			
		if (i<jugadasCargadas.length-1) {		
			casillasDeshacer = new Casilla[32];		
			for (int k = 0; k <= 31; k++)
				casillasDeshacer[k] = casillas[k];
			
			listaCasillasDeshacer.add(casillasDeshacer);
		}	
	}
	
	String[] alg = jugadasCargadas[jugadasCargadas.length-1].split(" ");
	
	if (alg.length ==2) { // juegan las blancas
		//colorOponente = Color.BLACK;
		setTurno(Color.WHITE);
		habilitarPiezas(eColores.Blancas);
	}
		else
		{
			//colorOponente = Color.WHITE;
			setTurno(Color.BLACK);		
			habilitarPiezas(eColores.Negras);				
		}

	activaDeshacer =true;


	for (int i = 0 ; i<=1; i++)
		if (jugadores[i].contains("Mango Paola")){
			jugandovsMangoPaola = true;
			break;
		}

	
	if (!jaqueMate&&!tablas)
		deshacer.setTouchable(Touchable.enabled);
	else {		

		listaCasillasDeshacer.remove(0);
		
		casillasDeshacer = new Casilla[32];		
		for (int k = 0; k <= 31; k++)
			casillasDeshacer[k] = casillas[k];
		
		listaCasillasDeshacer.add(casillasDeshacer);

		CantidadjugadaRevisar = jugadasCargadas.length-1;
		
		jugadaRevisar = CantidadjugadaRevisar-1; 
		
		listaDeJugadas.setSelectedIndex(jugadaRevisar);
		
		TextButtonStyle styleder = new TextButtonStyle();
		TextButtonStyle styleizq = new TextButtonStyle();
			
		font = new BitmapFont();
		skin3 = new Skin();
		pack = new TextureAtlas(Gdx.files.internal("assets/skins/mapaGeneral.pack"));
//		skin3.addRegions(pack);
		styleder.font = font;	
		styleizq.font = font;
		styleder.up = skin3.getDrawable("der");
		styleder.down = skin3.getDrawable("derDown");
		styleizq.up = skin3.getDrawable("izq");
		styleizq.down = skin3.getDrawable("izqDown");
			
		siguiente = new TextButton("", styleder);
		atras = new TextButton("", styleizq);
		
		siguiente.setSize(50, 40);
		atras.setSize(50, 40);

		siguiente.setVisible(true);
		atras.setVisible(true);
		
		siguiente.setPosition(690, 450);
		atras.setPosition(610, 450);

		siguiente.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				flagPane=false;
				revisar(true);
			}
	
		});
		atras.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				flagPane=false;
			revisar(false);	
			}
	
		});
		
	stage.addActor(siguiente);
	stage.addActor(atras);
	}
	
	}
	

	private void colocarFlechas(){
		CantidadjugadaRevisar = listaDeJugadas.getItems().size;
		
		TextButtonStyle styleder = new TextButtonStyle();
		TextButtonStyle styleizq = new TextButtonStyle();
			
		
		
		 skin7 = new Skin();
		pack7 = new TextureAtlas(Gdx.files.internal("assets/skins/mapaGeneral.pack"));
		skin7.addRegions(pack7);
		styleder.font = font;	
		styleizq.font = font;
		styleder.up = skin7.getDrawable("der");
		styleder.down = skin7.getDrawable("derDown");
		styleizq.up = skin7.getDrawable("izq");
		styleizq.down = skin7.getDrawable("izqDown");
			
		siguiente = new TextButton("", styleder);
		atras = new TextButton("", styleizq);
		
		siguiente.setSize(50, 40);
		atras.setSize(50, 40);

		siguiente.setVisible(true);
		atras.setVisible(true);
		
		siguiente.setPosition(690, 450);
		atras.setPosition(610, 450);

		siguiente.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				flagPane=false;				
				revisar2(true);
			}
	
		});
		atras.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				flagPane=false;
			revisar2(false);	
			}
	
		});
		
	stage.addActor(siguiente);
	stage.addActor(atras);

	}

	private void revisar(boolean pAvanzar) {
		flagPane = false;		
				
		if (pAvanzar) {
			if (jugadaRevisar < CantidadjugadaRevisar-1) {
				jugadaRevisar++;
				pane.scrollTo(0, CantidadjugadaRevisar*22 - ((jugadaRevisar+1) * 22), 0, 0);
			}
			else
				return ;
		}
		else {
			if (jugadaRevisar > 0) {
				jugadaRevisar--;
				pane.scrollTo(0, CantidadjugadaRevisar*22 - ((jugadaRevisar) * 22), 0, 0);
			}
			else 
				return ;		
		}
		
		
		listaDeJugadas.setSelectedIndex(jugadaRevisar);

		for (int i = 0; i <= 31; i++) {
		try {
			Pieza vPieza = casillas[i].getPieza();
//		System.out.println(listaDeJugadas.setSelectedIndex(jugadaRevisar););
			if (vPieza != null) {
				
			vPieza.setVisible(false);
			}
		}
		catch(Exception e){	
			}
		}
		
		for (int i = 0; i <= 31; i++) {
			casillas[i] = listaCasillasDeshacer.get(jugadaRevisar)[i];
//			System.out.println(casillas[i].ge);
			try {
				Pieza vPieza = casillas[i].getPieza();
				if (vPieza != null) {
					vPieza.setVisible(true);
					vPieza.setInd(i);
					vPieza.setPosPgn(casillas[i].getPgn());
//					System.out.println(casillas[i].getPgn());
					// if (Turno.equals(colorActivas)){
					vPieza.setTouchable(Touchable.enabled);
					casillas[i].setPieza(vPieza);
					// }
				}
									
			} catch (Exception e) {
			System.out.println("Error: " +e);
				// TODO: handle exception
			}
			

		}}
	private void revisar2(boolean pAvanzar) {
		
		//System.out.println("revisando: " + pAvanzar + " CantidadjugadaRevisar: " + CantidadjugadaRevisar + " listaCasillasDeshacer.size() " + listaCasillasDeshacer.size());
		
		deshacer.setTouchable(Touchable.disabled);
		flagPane = false;				
		
		if (pAvanzar) {
			if (jugadaRevisar < CantidadjugadaRevisar) {
				jugadaRevisar++;
				pane.scrollTo(0, CantidadjugadaRevisar*22 - ((jugadaRevisar+1) * 22), 0, 0);
			}
			else
				return ;
		}
		else {
			if (jugadaRevisar > 0) {
				jugadaRevisar--;
				pane.scrollTo(0, CantidadjugadaRevisar*22 - ((jugadaRevisar) * 22), 0, 0);
			}
			else 
				return ;		
		}
		
		
		System.out.println("jugada a revisar: " + jugadaRevisar);
		listaDeJugadas.setSelectedIndex(jugadaRevisar-1);

		verlista(listaCasillasDeshacer);
		
		verCasillas(listaCasillasDeshacer.get(jugadaRevisar));
		casillas = new Casilla[32];
		for (int i = 0; i <= 31; i++) {
			
			casillas[i] = listaCasillasDeshacer.get(jugadaRevisar)[i];
			
			//try {
				
				Pieza vPieza = casillas[i].getPieza(); 
						//casillas[i].getPieza();
				
				if (casillas[i].getPieza() != null) {
					System.out.println(1);
					vPieza.setTipoPieza(casillas[i].getPieza().getTipoPieza());
					System.out.println(2);
					vPieza.setTablero(tablero);
					System.out.println("2b");
					vPieza.setPosPgn(casillas[i].getPgn());
					System.out.println(3);
					vPieza.setTouchable(Touchable.enabled);
					System.out.println(4);
					vPieza.setColor(casillas[i].getPieza().getColor());
					System.out.println(5);
					vPieza.setEstadoPieza(casillas[i].getPieza().getEstadoPieza());
					System.out.println(6);
					vPieza.setVisible(casillas[i].getPieza().isVisible());
					System.out.println(7);
															
					//casillas[i].setPieza(vPieza);
				
				}
				else{
					System.out.println("pieza = null");
					
				}
									
//			} catch (Exception e) {
//			System.out.println("Error: " +e);
//				// TODO: handle exception
//			}			
		}
		if (jugadaRevisar < CantidadjugadaRevisar){
		//System.out.println("jugadaRevisar < CantidadjugadaRevisar");	
			for (Actor miActor : stage.getActors())
				try {
					if (((Pieza) miActor).getInd() >= 0) 
							miActor.setTouchable(Touchable.enabled);
				}	
				catch(Exception e){
					
				}
		}
		else{
			//System.out.println("jugadaRevisar < CantidadjugadaRevisar FALSE");
			if (Turno.equals(eColores.Blancas))
				habilitarPiezas(eColores.Negras);
				
			else
				habilitarPiezas(eColores.Blancas);
			removerFlechas();
			deshacer.setTouchable(Touchable.enabled);
		}
					
	}

	public void removerFlechas(){
		try{
		siguiente.remove();
		atras.remove();
		
		}
		catch(Exception e){
			
		}
//		siguiente = null;
//		atras = null;

	}
	
	
	
	/**
	 * coloca los contadores dependiendo del juego
	 * @param cantidad de contadores, 1 si solo es el humano 2 si es debe mostrar adem�s contaddor de la maquina
	 * @param humano numero hasta de dos digitos del contador
	 * @param maquina numero hasta de dos digitos del contador
	 */
	public void contadorTiempo(String identificador){
		
		TextButtonStyle tbs = new TextButtonStyle();
		tbs.font= font;
		pack3 = new TextureAtlas(Gdx.files.internal("assets/skins/numeros.pack"));
		skin3.addRegions(pack3);
		tbs.up= skin3.getDrawable("0");
		Image image=new Image(skin3, "2puntos");
		Image image2=new Image(skin3, "2puntos");
		stage.addActor(image);
		stage.addActor(image2);
		
		image.setPosition(600, 384);
		image.setSize(20, 20);
		
		image2.setPosition(720, 384);
		image2.setSize(20, 20);
		
		if (identificador.equals("1"))
		{
		contadorHumano = new TextButton("", tbs);
		contadorHumano2 = new TextButton("", tbs);
	
		stage.addActor(contadorHumano);
		contadorHumano.setSize(30, 30);
		
		stage.addActor(contadorHumano2);
		contadorHumano2.setSize(30, 30);
		
		
		contadorHumano.setPosition(560, 380);
		contadorHumano2.setPosition(580, 380);	
		}	
		else
		
		if (identificador.equals("2"))
		{
			 contadorMaquina = new TextButton("", tbs);
			 contadorMaquina2 = new TextButton("", tbs);
			 
			 stage.addActor(contadorMaquina);
			 contadorMaquina.setSize(30,30);
			
			 stage.addActor(contadorMaquina2);
			 contadorMaquina2.setSize(30, 30);
			 
			 
			 contadorMaquina.setPosition(610, 380);
			 contadorMaquina2.setPosition(630, 380);
		}
		
		if (identificador.equals("3"))
		{
		contadorHumano3 = new TextButton("", tbs);
		contadorHumano4 = new TextButton("", tbs);



		stage.addActor(contadorHumano3);
		contadorHumano3.setSize(30, 30);
		
		stage.addActor(contadorHumano4);
		contadorHumano4.setSize(30, 30);
		
		contadorHumano3.setPosition(680, 380);
		contadorHumano4.setPosition(700, 380);	
		
		
		}	
		else
			if (identificador.equals("4"))
			{
				 contadorMaquina3 = new TextButton("", tbs);
				 contadorMaquina4 = new TextButton("", tbs);
				 
				 stage.addActor(contadorMaquina3);
				 contadorMaquina3.setSize(30,30);
				
				 stage.addActor(contadorMaquina4);
				 contadorMaquina4.setSize(30, 30);
				 
				 contadorMaquina3.setPosition(730, 380);
				 contadorMaquina4.setPosition(750, 380);			
			}	
	}
	
/**
* Luego de evaluar las tablas muestra un mensaje dependiendo de la decisión 
* si es tabla, le da instrucción de mango paola de reiniciar y un mensaje aceptando
* 
* sino muestra un dialogo que informa que las tablas no fueron aceptadas
*/
private void accionTabla()
{
boolean decision= decideTablas();
if (decision)
{
	tablas=true;
	setTurno(Color.BLUE);
	mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.JuegoAceptaTablas);		
}
else
{
	mostrarDialogo(eTipoDialogo.Aceptar, eTipoMensaje.JuegoNoAceptaTablas);		
}
}

	
	/**
	 * Actualiza el contador de ser necesario
	 * @param humano valor booleano que define si es  la maquina o el jugador
	 * @param contador valor a actualizar
	 */
	public void actualizarContadorT (String identificador, int contador)
		{
		System.out.println("contador:"+identificador+ "valor"+ contador);
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
		  skin3.addRegions(pack3);
		   tbs.up= skin3.getDrawable(digito1);
		   tbs2.up= skin3.getDrawable(digito2);
			
		   
			if(identificador.equals("1"))
			{

				contadorHumano.setStyle(tbs);
				contadorHumano2.setStyle(tbs2);
				
			}
			else
			if(identificador.equals("2"))
			{
				 contadorMaquina.setStyle(tbs);
				 contadorMaquina2.setStyle(tbs2);	
			}
			else
				if(identificador.equals("3"))
				{
					contadorHumano3.setStyle(tbs);
					contadorHumano4.setStyle(tbs2);	
				}
				else
					if(identificador.equals("4"))
					{
						 contadorMaquina3.setStyle(tbs);
						 contadorMaquina4.setStyle(tbs2);	
					}
		}
	private void comprobartiempo(){
		
		if (selec4.equals("Ilimitado")) 
		{
			mostrarcontTiempo=false;
			actualizarContadorT(cont4, 0);
			actualizarContadorT(cont3, 0);
			actualizarContadorT(cont2, 0);
			actualizarContadorT(cont1, 0);
			imagenReloj.setZIndex(0);
			box5.setVisible(false);
			retardo.setVisible(false);
		} 
		else 

		if (selec4.equals("5 min")) 
			{
			mostrarcontTiempo=true;
			tiempoJugadasBla=5*60;
			tiempoJugadasNe=5*60;
			imagenReloj.setZIndex(1);
			box5.setVisible(true);
			retardo.setVisible(true);
			} 
		else 
		if (selec4.equals("10 min"))
			{
			mostrarcontTiempo=true;
			tiempoJugadasBla=10*60;
			tiempoJugadasNe=10*60;
			imagenReloj.setZIndex(1);
			box5.setVisible(true);
			retardo.setVisible(true);
			} 
		else 
		if (selec4.equals("20 min")) 
			{System.out.println("si entre aca");
			mostrarcontTiempo=true;
			tiempoJugadasBla=20*60;
			tiempoJugadasNe=20*60;
			imagenReloj.setZIndex(1);
			box5.setVisible(true);
			retardo.setVisible(true);
			} 
		else 
		if (selec4.equals("30 min")) 
			{
			mostrarcontTiempo=true;
			tiempoJugadasBla=30*60;
			tiempoJugadasNe=30*60;
			imagenReloj.setZIndex(1);
			box5.setVisible(true);
			retardo.setVisible(true);
			}
		else
		if (selec4.equals("60 min"))
			{
			mostrarcontTiempo=true;
			tiempoJugadasBla=60*60;
			tiempoJugadasNe=60*60;
			imagenReloj.setZIndex(1);
			box5.setVisible(true);
			retardo.setVisible(true);
			}
	}
	public void inicializarRelojes(){
	if(mostrarcontTiempo){	
		if(tablero.getTableroRotado())
		{
			if(tiempoJugadasBla>60)
			{

			int minutos=tiempoJugadasBla/60;
			int seg=tiempoJugadasBla-(minutos*60);
			System.out.println("descuento3");
				tiempoJugadasBla--;
				actualizarContadorT(cont4, seg);
				
				actualizarContadorT(cont3, minutos);
			}
		else
			{
				if(tiempoJugadasBla!=0)
					{System.out.println("descuento4");
						tiempoJugadasBla--;
						actualizarContadorT(cont4, tiempoJugadasBla);
						int minutos=0;
						actualizarContadorT(cont3, minutos);
					}
			}
			
			if(tiempoJugadasNe>60)
			{				
				int minutosN=tiempoJugadasNe/60;
				int segN=tiempoJugadasNe-(minutosN*60);
				tiempoJugadasNe--;
				actualizarContadorT(cont2, segN);
				actualizarContadorT(cont1, minutosN);
			}
			else
			{
				if(tiempoJugadasNe!=0)
				{
				tiempoJugadasNe--;
				actualizarContadorT(cont2, tiempoJugadasNe);
				int minutos=0;
				actualizarContadorT(cont1, minutos);
				}
			}
			
			
			
		}
		
		else {	
		if(tiempoJugadasNe>60)
		{				
			int minutosN=tiempoJugadasNe/60;
			int segN=tiempoJugadasNe-(minutosN*60);
			tiempoJugadasNe--;
			actualizarContadorT(cont2, segN);
			actualizarContadorT(cont1, minutosN);
		}
		else
		{
			if(tiempoJugadasNe!=0)
			{
			tiempoJugadasNe--;
			actualizarContadorT(cont2, tiempoJugadasNe);
			int minutos=0;
			actualizarContadorT(cont1, minutos);
			}
		}
		if(tiempoJugadasBla>60)
		{

		int minutos=tiempoJugadasBla/60;
		int seg=tiempoJugadasBla-(minutos*60);
		System.out.println("descuento5");
			tiempoJugadasBla--;
			actualizarContadorT(cont4, seg);
			
			actualizarContadorT(cont3, minutos);
		}
	else
		{
			if(tiempoJugadasBla!=0)
				{System.out.println("descuento6");
					tiempoJugadasBla--;
					actualizarContadorT(cont4, tiempoJugadasBla);
					int minutos=0;
					actualizarContadorT(cont3, minutos);
				}
		}
		}
	}
	}
		
		
	 /** revisa el valor de los puntos y decide si dar o no tablas
	 * @return
	 */
	private boolean decideTablas()
	{
		boolean decision =false;
		
		int blancas= cuentaPuntos(Color.WHITE);
		int negras= cuentaPuntos(Color.BLACK);
		
		Color mango= colorOponente;
		int puntoMango=0;
		int puntoJugador=0;
		if (mango.equals(Color.BLACK))
		{
			puntoMango=negras;
			puntoJugador=blancas;
		}
		else
		{
			puntoMango=blancas;
			puntoJugador=negras;
		}
		
		if(puntoJugador>(puntoMango+15))
		{
			decision=true;
		}
		return decision;
	}
	
	/**
	 * cuenta los puntos relativos a las piezas de un color
	 * @param color : color de las piezas a contar
	 * @return valor total de las piezas
	 */
	private int cuentaPuntos(Color color)
	{
		int suma=0;

		//System.out.println(casillas.length);
		for (int i=0; i<casillas.length;i++)
		{
		
			if (casillas[i].getPieza()!=null)
			{
			//	System.out.println(i+" "+casillas[i].getPieza());
				Pieza temp=casillas[i].getPieza();
				if ((temp.getColor().equals(color)))
				{
					eTipoPieza tempPieza= temp.getTipoPieza();
					if (eTipoPieza.Peon.equals(tempPieza))
					suma++;
					else if (eTipoPieza.Alfil.equals(tempPieza))
						suma=suma+4;
					else if (eTipoPieza.Caballo.equals(tempPieza))
						suma=suma+3;
					else if (eTipoPieza.Torre.equals(tempPieza))
						suma=suma+6;
					else if (eTipoPieza.Reina.equals(tempPieza))
						suma=suma+10;	
				}	
			}		
			
		}	
	//	System.out.println(color+":"+suma);
		return suma;
	}

	private boolean colocarJugadaAlg(String pPgni , String pPgnf){
		System.out.println(pPgni+" - "+pPgnf);
		if (colorOponente.equals(Color.WHITE)){
			if(jugandovsMangoPaola){											
//					if (jugadasAlg.size()>0){
//						String temp = jugadasAlg.get(jugadasAlg.size()-1);
//						jugadasAlg.remove(jugadasAlg.size()-1);
//						jugadasAlg.add(temp  + " " + 	jugadaHumano.get(0).split("\\[")[1].split("\\]")[0]);
//					}
					if (jugadasAlg.size()>0){
						String temp = jugadasAlg.get(jugadasAlg.size()-1);
						if (!jugadaHumano.get(0).split("\\]")[0].contains("result")){										
							jugadasAlg.remove(jugadasAlg.size()-1);
							jugadasAlg.add(temp  + " " + jugadaHumano.get(0).split("\\[")[1].split("\\]")[0]);
							//System.out.println("jugada humano :"+jugadaHumano.get(0).split("\\[")[1].split("\\]")[0]);
						}
						else if (jugadaHumano.get(0).split("\\]")[0].contains("Black")){
								jugadasAlg.remove(jugadasAlg.size()-1);
								jugadasAlg.add(temp  + " " + (" 0-1") );							
							}
							else{ // draw
								jugadasAlg.remove(jugadasAlg.size()-1);
								jugadasAlg.add(temp  + " " + (" 1/2-1/2") );							
							}
					}
					else{
						jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaHumano.get(0).split("\\[")[1].split("\\]")[0], 15));										
					}
				}
			else{	System.out.println("estoy jugando solo");																																																					
				if(getTurno().equals(Color.WHITE)){
					//jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaHumano.get(0).split("\\[")[1].split("\\]")[0],15));											
					if (!jugadaHumano.get(0).split("\\]")[0].contains("result")){
						if (jugadasAlg.size()>1)
						//jugadasAlg.remove(jugadasAlg.size()-1);
						
						jugadasAlg.add(tamano((jugadasAlg.size()+1)+". "+jugadaHumano.get(0).split("\\[")[1].split("\\]")[0],15));
					}
					else if (jugadaHumano.get(0).split("\\]")[0].contains("White")){
							//jugadasAlg.remove(jugadasAlg.size()-1);
					
						jugadasAlg.add(" 1-0");							
						}
						else{ // draw
							//jugadasAlg.remove(jugadasAlg.size()-1);
							jugadasAlg.add(" 1/2-1/2");							
						}																			

				}
				
				else{

					if((jugadasAlg.size()-1)>=0){
					String temp = jugadasAlg.get(jugadasAlg.size()-1);
					jugadasAlg.remove(jugadasAlg.size()-1);
					jugadasAlg.add(temp  + " " + 	jugadaHumano.get(0).split("\\[")[1].split("\\]")[0]);

					}
					else
						jugadasAlg.add(jugadaHumano.get(0).split("\\[")[1].split("\\]")[0]);
				}										
			}										

		}																								
		else{								
			if(!jugandovsMangoPaola){
				System.out.println("turno: " +getTurno());
				if(getTurno().equals(Color.WHITE)){
					
					//String temp = jugadasAlg.get(jugadasAlg.size()-1);		
				//	jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaHumano.get(0).split("\\[")[1].split("\\]")[0],15));											
//					System.out.println("tal ves aqui");
//					System.out.println(jugadaHumano.get(0).split("\\]")[0]);
					if (!jugadaHumano.get(0).split("\\]")[0].contains("result")){										
						//jugadasAlg.remove(jugadasAlg.size()-1);
						System.out.println(jugadaHumano.get(0).toString());
						jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaHumano.get(0).split("\\[")[1].split("\\]")[0],15));
					}
					else if (jugadaHumano.get(0).split("\\]")[0].contains("Black")){
							//jugadasAlg.remove(jugadasAlg.size()-1);
						System.out.println(jugadaHumano.get(0).toString());
						//String temp = jugadasAlg.get(jugadasAlg.size()-1);	
							
						jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaHumano.get(0).split("\\[")[2].split("\\]")[0],15));
						
													
						}
						else{ // draw
						//	jugadasAlg.remove(jugadasAlg.size()-1);
							jugadasAlg.add(" 1/2 - 1/2");							
						}																			
//				
				}
				else{
					String temp = jugadasAlg.get(jugadasAlg.size()-1);		
//					System.out.println("es aqui");
					if (!jugadaHumano.get(0).split("\\]")[0].contains("result")){										
						jugadasAlg.remove(jugadasAlg.size()-1);
						System.out.println(jugadaHumano.get(0).toString());
						
						jugadasAlg.add(temp  + " " + jugadaHumano.get(0).split("\\[")[1].split("\\]")[0]);
						
					}
					else if (jugadaHumano.get(0).split("\\]")[0].contains("White")){
							//jugadasAlg.remove(jugadasAlg.size()-1);
							
							jugadasAlg.add((jugadasAlg.size()+1)+"."+ jugadaHumano.get(0).split("\\[")[2].split("\\]")[0]);
							
							jugadasAlg.add(" 1 - 0");							
						}
						else{ // draw
							jugadasAlg.remove(jugadasAlg.size()-1);
							
							jugadasAlg.add(temp  + " " + jugadaHumano.get(0).split("\\[")[2].split("\\]")[0]);
							
							jugadasAlg.add(" 1/2 - 1/2");							
						}																			
////				
//					jugadasAlg.remove(jugadasAlg.size()-1);
//					jugadasAlg.add(temp  + " " + 	jugadaHumano.get(0).split("\\[")[1].split("\\]")[0]);
					}
			}else{
				System.out.println("es aqui"+jugadaHumano);
				if (!jugadaHumano.get(0).split("\\]")[0].contains("result")){										
					//jugadasAlg.remove(jugadasAlg.size()-1);
					jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaHumano.get(0).split("\\[")[1].split("\\]")[0],15));
				}
				else if (jugadaHumano.get(0).split("\\]")[0].contains("White")){
						//jugadasAlg.remove(jugadasAlg.size()-1);
						jugadasAlg.add(" 1 - 0");							
					}
					else{ // draw
					//	jugadasAlg.remove(jugadasAlg.size()-1);
						jugadasAlg.add(" 1/2 - 1/2");							
					}																			
//				jugadasAlg.add(tamano((jugadasAlg.size()+1)+"."+jugadaHumano.get(0).split("\\[")[1].split("\\]")[0],15));
			}
			if (historia){
				if(minijuego.contains("Minijuego27")){
					System.out.println("jugadaAlgSecreta: " + jugadaAlgSecreta + " jugadaHumano: "+ jugadaHumano.get(0).split("\\[")[1].split("\\]")[0]);
					if (jugadaAlgSecreta.contains(jugadaHumano.get(0).split("\\[")[1].split("\\]")[0])||						
						jugadaHumano.get(0).split("\\[")[1].split("\\]")[0].contains(jugadaAlgSecreta)||
						jugadaHumano.get(0).split("\\[")[1].split("\\]")[0].contains("result 1-0")
						){
							jugadasSecretasSeguidas++;
							actualizarContador(true, jugadasSecretasSeguidas);
							if (jugadasSecretasSeguidas>=6){													
								reintentar(false);
								return false;
							}
					}
					else {
						jugadasSecretasNoSeguidas++;
						actualizarContador(false, jugadasSecretasNoSeguidas);

						if (jugadasSecretasNoSeguidas>=4){												
							reintentar(true);
							return false;
							}
						}
				}
		
			}	 																																
		}
		
		
		listaDeJugadas.setItems(jugadasAlg.toArray());								
//		System.out.println("jugadasAlg.size()" + jugadasAlg.size());
		listaDeJugadas.setSelectedIndex(jugadasAlg.size()-1);
		
		try{
		pane.scrollTo(0,0 ,0, 0);
	
		}
		catch(Exception e){
			System.out.println("Error en el pane " + e.getMessage());
		}
		jugadas.add(pPgni + " - " + pPgnf);
		
//		System.out.println("jugadahumano:"+jugadaHumano.get(0));
//		if (!Turno.equals(colorOponente)) {
////			System.out.println("entre");
//			casillasDeshacer = new Casilla[32];
//			for (int i = 0; i <= 31; i++)
//				casillasDeshacer[i] = casillas[i];
//			listaCasillasDeshacer.add(casillasDeshacer);
//		}

		return true;
	}

 
	public void actualizarReferencias(){
		for (int i = 0; i < 8; i++) {
			
				if(TexturaTablero.equals("assets/mapasTiled/tableroPlaya.tmx")){
					numeros[i].setColor(Color.BLACK);
				}
				else{
					numeros[i].setColor(Color.WHITE);
				}
		}

		for (int i = 0; i < 8; i++) {
			
			if(TexturaTablero.equals("assets/mapasTiled/tableroPlaya.tmx")){
				letras[i].setColor(Color.BLACK);
			}
			else{
				letras[i].setColor(Color.WHITE);
			}
		}	
	}	
	
	public void determinarTiempoBla(){
	//	System.out.println("6 blanca");
		if(tiempoJugadasBla>=60)
		{
		if(retardoBlancas!=0)
			retardoBlancas--;
		int minutos=tiempoJugadasBla/60;
		int seg=tiempoJugadasBla-(minutos*60);
		actualizarContadorT(cont4, seg);
		actualizarContadorT(cont3, minutos);
			if(retardoBlancas==0){
				
			tiempoJugadasBla--;
			}
			
			
		}
	else
		{
			if(tiempoJugadasBla!=0)
				{
				if(retardoBlancas!=0)
					retardoBlancas--;
				
					if(tiempoJugadasBla==60)
					{
						int minutos=tiempoJugadasBla/60;
						int seg=tiempoJugadasBla-(minutos*60);
						actualizarContadorT(cont4, seg);
						actualizarContadorT(cont3, minutos);
					}
					else{
					actualizarContadorT(cont4, tiempoJugadasBla);
					int minutos=0;
					actualizarContadorT(cont3, minutos);
					}
					if(retardoBlancas==0){
					tiempoJugadasBla--;
					}
				}
			else
				{
					if(flagTiempo)
						{
						actualizarContadorT(cont4, 0);
						actualizarContadorT(cont3, 0);

						ganartiempo(false);
						flagTiempo=false;
						}
				}
			}
		
	}
public void determinarTiempoNe(){
	//System.out.println("6 negra");
	
	if(tiempoJugadasNe>=60)
	{	if(retardoNegras!=0)
		retardoNegras--;			
		int minutosN=tiempoJugadasNe/60;
		int segN=tiempoJugadasNe-(minutosN*60);
		actualizarContadorT(cont2, segN);
		actualizarContadorT(cont1, minutosN);
		if(retardoNegras==0){
			
			tiempoJugadasNe--;
			}
		
		
	}
else
{
if(tiempoJugadasNe!=0)
	{
	if(retardoNegras!=0)
		retardoNegras--;
	if(tiempoJugadasNe==60){
		int minutosN=tiempoJugadasNe/60;
		int segN=tiempoJugadasNe-(minutosN*60);
		actualizarContadorT(cont2, segN);
		actualizarContadorT(cont1, minutosN);
		
	}
	else{
		actualizarContadorT(cont2, tiempoJugadasNe);
		int minutos=0;
		actualizarContadorT(cont1, minutos);
	}
	if(retardoNegras==0){
		tiempoJugadasNe--;
		}
	}
else
{
	if(flagTiempo2){
		actualizarContadorT(cont2, 0);
		
		actualizarContadorT(cont1, 0);

		ganartiempo(true);
	flagTiempo2=false;
	}
	
	
}
}
	}
	
public TextButtonStyle botonesReloj(boolean estado){
	blancas.up=skin5.getDrawable("1");
	negras.up=skin5.getDrawable("2");
	

	if(estado){
		System.out.println("true es negras");
		styleNomb=negras;
	}else{
		System.out.println("false es blanca");
		styleNomb=blancas;
	}

	return styleNomb;
}



public TextButtonStyle botonesReloj(Color estado){
	blancas.up=skin5.getDrawable("2");
	negras.up=skin5.getDrawable("1");
	
	 
		if(estado.equals(Color.BLACK)){
			System.out.println("true es negras");
			styleNomb=negras;
		}else{
			System.out.println("false es blanca");
			styleNomb=blancas;
		}
	
	
	
	return styleNomb;
}

 public void oprimirBotonReloj(){
	 	
				System.out.println("turno:"+getTurno().toString());
					TextButtonStyle style=	botonesReloj(Turno);
			
			imagenBotones.setStyle(style);
		
 }

 public ArrayList<Casilla[]>  pasarCasillas(ArrayList<Casilla[]> pCasillaIn){
	 ArrayList<Casilla[]> res = new ArrayList<Casilla[]>();
	// int j = 0;
	 for (Casilla[] vCasillain: pCasillaIn){
		 //System.out.println("pasando Casillas: " + j);
			Casilla[] vCasillasout = new Casilla[32];
			int i = 0;
			for (Casilla vcasilla: vCasillain){
//				System.out.println("pasando la casilla" + vcasilla.getPgn());
				vCasillasout[i] = vcasilla;			
				i++;		
			}			 
			res.add(vCasillasout);
		//	j++;
	 }
return res;
}

private void verlista (ArrayList<Casilla[]> pCasillaIn){
	 for (Casilla[] vCasillain: pCasillaIn){						
			for (Casilla vcasilla: vCasillain){
				System.out.println("valor la casilla" + vcasilla.getPgn());
			}
			System.out.println("fin lista");
	 }
	 System.out.println("fin verLista" );
	 
}
	
private void verCasillas (Casilla[] pCasillaIn){
	 						
			for (Casilla vcasilla: pCasillaIn){
				System.out.println("valor la casilla" + vcasilla.getPgn());
			}
			System.out.println("fin casilla");
	 
}

public void rotarTablero(){


	System.out.println("numeros.length: " + numeros.length);
	
	posicionarReferencias();				

	Pieza vPieza =null;
	for (Casilla  vCasilla : casillas){
		vPieza = vCasilla.getPieza();
		if (vPieza !=null){
			vPieza.setPosPgn(vCasilla.getPgn());			
		}		
	}


//	Turno = vTurno;
}
private void posicionarReferencias(){
	int vDistaciaLetrasX = (int) (casilla.GetAnchoCasilla() * 2.4);
	int vDistaciaLetrasY = (int) (casilla.GetAltoCasilla() * 0.325);

	if (colorOponente.equals(Color.WHITE)) {
		tablero.setTableroRotado(true);

		for (int i = 0; i < 8; i++) {
			letras[i].setPosition((vDistaciaLetrasX - 75) + ((7 - i) * casilla.GetAnchoCasilla()), 5);
			numeros[i].setPosition(12, (vDistaciaLetrasX - 100) + ((7 - i) * casilla.GetAltoCasilla()));
			letras[i].setFontScale(vDistaciaLetrasY / 10);
			numeros[i].setFontScale(vDistaciaLetrasY / 10);
		}
	} else {
		tablero.setTableroRotado(false);
		for (int i = 0; i < 8; i++) {
			letras[i].setPosition((vDistaciaLetrasX - 75) + i * casilla.GetAnchoCasilla(), 5);
			letras[i].setFontScale(vDistaciaLetrasY / 10);
			numeros[i].setPosition(12, (vDistaciaLetrasX - 100) + i * casilla.GetAnchoCasilla());
			numeros[i].setFontScale(vDistaciaLetrasY / 10);
		}
	}

} 

private void guardarJuegos(int resultado ){
	File f;
	FileWriter w;
	BufferedWriter bw;
	PrintWriter wr;
	String strResultado = "";
		
	try {
		f = new File(Gdx.files.internal(System.getProperty("user.home")) + "/"+ "miniHeroes" + ".pgn");
		w = new FileWriter(f,true);
		bw = new BufferedWriter(w);
		wr = new PrintWriter(bw);

		wr.write("[Event "  + "\"" + "miniHeroes" + "\"" + "]");
		wr.append("\n[Site " + "\"" + "Ordenador Personal" + "\"" + "]");
		wr.append("\n[Date " + "\""  + new Date()  + "\"" + "]");
		wr.append("\n[Round " + "-" + "]");
		if (jugandovsMangoPaola){										
			if (colorOponente.equals(Color.WHITE)){
				wr.append("\n[White "  + "\"" + "Mango Paola"  + "\"" + "]");
				wr.append("\n[Black "  + "\"" + "Por designar"  + "\"" + "]");
				
				switch(resultado){
					case Enums.victoria:
						strResultado = "0-1";					
						break;
						
					case Enums.derrota:
						strResultado = "1-0";
						break;
						
					case Enums.tablas:
						strResultado = "1/2-1/2";
						break;

					case Enums.indefindo:
						strResultado = "n-n";
						break;

					}
			
			}
			else{
				wr.append("\n[White " +  "\"" + "Por designar" + "\"" + "]");
				wr.append("\n[Black " + "\""+ "Mango Paola" + "\"" + "]");

				switch(resultado){
				case Enums.victoria:
					strResultado = "1-0";					
					break;
					
				case Enums.derrota:
					strResultado = "0-1";
					break;
					
				case Enums.tablas:
					strResultado = "1/2-1/2";
					break;

				case Enums.indefindo:
					strResultado = "n-n";
					break;

				}

			}
		}
		else
		{
			wr.append("\n[White "  + "\"" + "Por designar"  + "\"" + "]");
			wr.append("\n[Black "  + "\"" + "Por designar"  + "\"" + "]");
		}
		
		wr.append("\n[Result " + "\""  + strResultado + "\""  + "]");
		//wr.append("\n[Fen inicial: " + vStringFen + "]");
		//wr.append("\n[Fen final: " + obtenerFen() + "]");
		wr.append("\n");
		wr.append("\n");
//		int i = 1;
		System.out.println("jugadasAlg " +  jugadasAlg.size());
		for (String item : jugadasAlg) {
			if (vStringFen != "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0") {

				while (item.contains("  ")) {
					item = item.replace("  ", " ");	
				}

				String [] temp= item.split(" ");
				
//				while (i == 1) {
//				//	System.out.println("temp:"+ temp);	
//				
//					wr.append("..." + temp[0]   + " ");
//					i++;
//				}
//						wr.append(  item + " ");
				System.out.println("temp.length " + temp.length);
				if (temp.length==2)
					
					wr.append(temp[0]+" "+temp[1]+" ");
				
				else
					wr.append(temp[0]);	
				
				//i++;
			} else {
				
				while (item.contains("  ")) {
					item = item.replace("  ", " ");	
				}
				String [] temp= item.split(" ");
				//System.out.println("temp:"+ temp[0]+" "+temp[1]);	
				//System.out.println(" 1 temp.length " + temp.length);
				if (temp.length==2)
					
					wr.append(temp[0]+" "+temp[1]+" ");
				
				else
					wr.append(temp[0]);	


//				wr.append( " "+ temp[0]+" "+temp[temp.length-1]);
//				i++;
			}
		}
		wr.append("\n");
		wr.close();
		bw.close();

	} catch (Exception e) {
		System.out.println("Ha ocurrido un error guardando" + e);
	}

}
//actorextra, pieza, animator, heroe_diffultad
// 4k3/pppppppp/8/8/8/8/PPP5/4K3 w - 0 0  
 //4k3/ppp5/8/8/8/8/PPPPPPPP/4K3 w - 0 0
private void iniciarRetardo(){
	pack2=new TextureAtlas(Gdx.files.internal("assets/skins/botonesReloj.pack"));
	skin5=new Skin();
	skin5.addRegions(pack2);
	styleNomb = new TextButtonStyle();
	blancas = new TextButtonStyle();
	negras = new TextButtonStyle();
	blancas.font = font;
	negras.font = font;
	styleNomb.font = font;
	
	Texture txlabelNegras=game.getManager().get("assets/Texturas/labelNegras.png");
	Texture txlabelBlancas=game.getManager().get("assets/Texturas/labelBlancas.png");
	
	Image labelBlancas=new Image(txlabelBlancas);
	Image labelNegras=new Image(txlabelNegras);
	
	stage.addActor(labelBlancas);
	stage.addActor(labelNegras);
	
	labelBlancas.setSize(80, 25);
	labelNegras.setSize(80, 25);
		
	imagenBotones = new TextButton("", styleNomb);
	stage.addActor(imagenBotones);
	imagenBotones.setPosition(575, 435);
	imagenBotones.setSize(190, 70);
	imagenBotones.setZIndex(80);
	
	skin3=new Skin();
	if(flagReloj){
	oprimirBotonReloj();
	contadorTiempo("1");
	contadorTiempo("2");
	contadorTiempo("3");
	contadorTiempo("4");
	comprobartiempo();
	flagReloj=false;
	}
	if(tablero.getTableroRotado())
	{
		labelNegras.setPosition(692, 430);
		labelBlancas.setPosition(572, 430);
		cont1="3";	
		cont2="4";	
		cont3="1";	
		cont4="2";
	}else{
	labelBlancas.setPosition(692, 430);
	labelNegras.setPosition(572, 430);
	cont1="1";	
	cont2="2";	
	cont3="3";	
	cont4="4";	
	}
	
	
	
	inicializarRelojes();
	
}

public void iniciarMotor(){
	if (conversacion==null)
		conversacion= new Conversacion(Oyente.XBOARD);
	conversacion.start();
	
	while (!conversacion.isArriba()) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	if(historia)
	{
		resultadoJugarMangoPaola(enviarMangoPaola("random 9"));
	}
	if (jugandovsMangoPaola) {
//		System.out.println("Enviando dificultad : " + dificultad);
		resultadoJugarMangoPaola(enviarMangoPaola(valorDificultad));
/*
		if (hard)
			resultadoJugarMangoPaola(enviarMangoPaola("hard"));
		else
			resultadoJugarMangoPaola(enviarMangoPaola("easy"));
//*/
	}
	enviarMangoPaola("seeAlgMov 1");
	

	
	CargarFen();
	

	
	this.setTurno(Color.WHITE);
	Gdx.input.setInputProcessor(stage);
	motorIniciado=true;
}


}
