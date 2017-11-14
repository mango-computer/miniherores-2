//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
/////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////
//                MiniJuegoGeneral                          //
//                   Descripcion                            //
//  MiniJuegos configurables por xml de configuracion       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Yisheng León      30/05/2016     Version Inicial       //
//////////////////////////////////////////////////////////////
package com.jayktec.grafico.Minijuegos;

import java.util.ArrayList;


import java.util.Random;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
//import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
//import com.badlogic.gdx.scenes.scene2d.ui.Button;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
//import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
//import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.Partida;
import com.jayktec.comunicacion.Conversacion;
import com.jayktec.comunicacion.Oyente;
import com.jayktec.comunicacion.Traductor;
import com.jayktec.grafico.ActorMovil;
import com.jayktec.grafico.Dialogo;
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
import com.jayktec.grafico.Piezas.Muro;
import com.jayktec.grafico.Piezas.Peon;
import com.jayktec.grafico.Piezas.Pieza;
import com.jayktec.grafico.Piezas.PiezasExcluidas;
import com.jayktec.grafico.Piezas.Reina;
import com.jayktec.grafico.Piezas.Rey;
import com.jayktec.grafico.Piezas.Tablero;
import com.jayktec.grafico.Piezas.Torre;
import com.jayktec.grafico.Screen.ScreenManager;
import com.jayktec.grafico.Screen.ScreenVideo;
import com.jayktec.grafico.Screen.mapaGeneralScreen;

/**
 * Screen que se configuran automaticamente a través del xml de configuracion
 * 
 * @author yisheng
 *
 */
public class MiniJuegoGeneral extends ScreenManager {
	/**
	 * Declaracion de variables
	 */
	private Sound sonidoDerrota,sonidoTriunfo;
	private Dialogo opcionesScreen;
	private boolean limitadorMovimientos;
	private int cantidadMovimientos,intensidadMurosFen;
	private eColores colorActivas;
	private Color colorOponente = Color.BLACK;
	private boolean jugandovsMangoPaola = true;
	private Color Turno;
	private Miniheroes game;
	private Sound reyMovimiento, reinaMovimiento, torreMovimiento, alfilMovimiento, caballoMovimiento, peonMovimiento,disparoSeco;
	private Music cortinaFondo;
	private Stage stage;
	private TextButton salir, reiniciar, guardar, Opcion,ayuda,ordenarPiezas;
	private Skin skin1, skin2, skin3,skin4;
	
//	private TextureAtlas buttonAtlas;
	private BitmapFont font;
	private Label[] letras;
	private Label[] numeros;
	RotateToAction rotateAction = new RotateToAction();
	private Conversacion conversacion;
	public TiledMap tiledMap;
	public String TexturaTablero,colorMurosFen;
	private Tablero tablero = new Tablero();
	private Casilla casilla = new Casilla();
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	private Casilla[] casillas;
	private Peon peonalpaso;
	String pgnPeonalPaso = "-";
	int movimientosMedios = 0;
	int movimientosCompletos = 0;
	int reintentosMangoPaola = 0;
	String cPiezaPromovida;
	private boolean jaqueMate = false;
	private ArrayList<Pieza> piezasPromovida = new ArrayList<Pieza>();
	private ArrayList<Casilla[]> listaCasillasDeshacer = new ArrayList<Casilla[]>();
	public String varRes = "";
	public String comando;
	public String guardarMovimientos;
	public String test;
	public String vStringFen;
	String txcargaFen;
	public ScrollPane pane2;
	private String batalla;
	private String minijuego;
	private Dificultad dificultad;
	private Preferencias preferencias;
	int w = ((int) super.getGameWidth());
	int h = ((int) super.getGameHeight());
	private boolean tocarSonido = false;
	private boolean tocarMusica = false;
	private String Xmm, pgnSalida;
	private Sound movIncorrecto;
	private float segundos;
	int contadorJugadas=0,contadorPiezas=0,contadorCapturadas=0,jugadasPeon=0,contadorMovIncorrectos=0,contadorDobleAtaque=0,contadorPeonAlPaso=0;
	private boolean flag = true;
	private String rutaTexturaPersonaje;
	private Texture texturaPersonaje,texturaPersonajeAura;
	private ImageButton imagenPersonaje;
	private Image  imagenMuros, imagenMartillos;
	private ActorExtra fondoReloj;
	private TextureAtlas pack,pack2,pack3,packAnimacionReloj,packRelojDerrota;
	//private TextButton botonAyuda;
	private String partidaMinijuego;
	private int moneda;
	//private ScreenManager sManager;
	private Dialogo videoScreen;
	private String videoAyuda;
	private String videoHistoria;
	private String videoBiografia;
	private boolean flag3=false;
	public boolean mostrarVideos = true;
	public boolean recargaScreen = false;
	private Boolean juegaMotor,ultimoMinBatalla,tiempo,agregarMurosFen,contarPeones,bloquearSalida,modoSigiloso,permitirJaque,movEscaqueNoAmenazado,prioridadCaptura,informarUltimaCaptura;
	private boolean mostrarAlcanceOponente=false;
	private ActorExtra[] posiblesMovimientos;
	private ArrayList<PiezasExcluidas> piezasExcluidas= new ArrayList<PiezasExcluidas>();
	private Color colorAliado = Color.WHITE;
	private boolean visto=false;
	private String reyJaque;
	private Partida partida;
	Texture texture;
	Pieza piezaPadre;
	//private  ActorExtra imagenMuro;
	private boolean eliminandoMuro = false;
	private boolean colocandoMuro = false;
	private boolean eliminarMuros = false;
	private boolean colocarMuros = false;
	private int costoMuro,contadorPaja=0;
	private int derriboMuro;
	private Sound sonidoMoneda, sonidoPierdeMoneda;
	private int ganarMoneda;
	private Image imagenMoneda;
	private Label etiquetaMoneda;

	private ActorExtra canon,explosion,flecha,actorExtra,magia,lanza,machete,cetro;
	private boolean flagPauseResume=true;
	private ActorExtra[] explosionMJN19;
	private Boolean mostrarMonedas;
	private int cantidadMonedas;
	private int cantidadBolsas;
	private int agregarBolsasExtra = 0;
	private int bolsasCapturadas;
	private String []vPiezasExcluidas;
	private TextureRegion[] textureReg;
	private int  cambiarCamino = 0;
	private ArrayList<String> pgnMantenerPieza = new ArrayList<String>();
	private ArrayList<String> pgnMantenerActor = new ArrayList<String>();
	private boolean capturarMuros = false;
	private int cantidadPaja;
	private Boolean mostrarPaja;
	private ArrayList<String> pajas;
	private boolean flagPeonAlPaso=false;
	ArrayList<Pieza> piezasAMantener = new ArrayList<Pieza>();
	private String colorMuros;
	int contadorPromovidas = 0;
	String respuestaPgnImango,respuestaPgnFmango ;
	String pgnActorEliminarDespues="";
	private ActorExtra reloj;
	private int rotacionIni=0;
	private int rotacionAcc=0;
	private int tiRotacion=0;
	private boolean flagReloj=false;
	private String avatar;
	private boolean bPerderMJN02=false;

	private TextureAtlas alfilPatriotaDerechaAbajo,alfilPatriotaDerechaArriba,alfilPatriotaIzquierdaArriba,alfilPatriotaIzquierdaAbajo;
	private TextureAtlas torrePatriotaIzquierda,torrePatriotaDerecha,torrePatriotaArriba,torrePatriotaAbajo;
	private TextureAtlas damaPatriotaIzquierda,damaPatriotaDerecha,damaPatriotaArriba,damaPatriotaAbajo,damaPatriotaDerechaAbajo,damaPatriotaDerechaArriba,damaPatriotaIzquierdaArriba,damaPatriotaIzquierdaAbajo;
	private TextureAtlas reyPatriotaIzquierda,reyPatriotaDerecha,reyPatriotaArriba,reyPatriotaAbajo,reyPatriotaDerechaAbajo,reyPatriotaDerechaArriba,reyPatriotaIzquierdaArriba,reyPatriotaIzquierdaAbajo;
	private TextureAtlas peonPatriotaArriba,peonPatriotaAbajo,peonPatriotaDerechaAbajo,peonPatriotaDerechaArriba,peonPatriotaIzquierdaArriba,peonPatriotaIzquierdaAbajo;
	private TextureAtlas caballoPatriotaAbajoDerecha,caballoPatriotaAbajoIzquierda,caballoPatriotaArribaDerecha,	caballoPatriotaArribaIzquierda,	caballoPatriotaDerechaArriba,	caballoPatriotaDerechaAbajo,	caballoPatriotaIzquierdaArriba,	caballoPatriotaIzquierdaAbajo;
	
	private TextureAtlas alfilRealistaDerechaAbajo,alfilRealistaDerechaArriba,alfilRealistaIzquierdaArriba,alfilRealistaIzquierdaAbajo;
	private TextureAtlas torreRealistaIzquierda,torreRealistaDerecha,torreRealistaArriba,torreRealistaAbajo;
	private TextureAtlas damaRealistaIzquierda,damaRealistaDerecha,damaRealistaArriba,damaRealistaAbajo,damaRealistaDerechaAbajo,damaRealistaDerechaArriba,damaRealistaIzquierdaArriba,damaRealistaIzquierdaAbajo;
	private TextureAtlas reyRealistaIzquierda,reyRealistaDerecha,reyRealistaArriba,reyRealistaAbajo,reyRealistaDerechaAbajo,reyRealistaDerechaArriba,reyRealistaIzquierdaArriba,reyRealistaIzquierdaAbajo;
	private TextureAtlas peonRealistaArriba,peonRealistaAbajo,peonRealistaDerechaAbajo,peonRealistaDerechaArriba,peonRealistaIzquierdaArriba,peonRealistaIzquierdaAbajo;
    private TextureAtlas alfilDormido,torreDormido,peonDormido;
	private TextureAtlas caballoRealistaAbajoDerecha,caballoRealistaAbajoIzquierda,caballoRealistaArribaDerecha,	caballoRealistaArribaIzquierda,	caballoRealistaDerechaArriba,	caballoRealistaDerechaAbajo,	caballoRealistaIzquierdaArriba,	caballoRealistaIzquierdaAbajo;

	public int enroquesRealizados = 0, reintentoEnroques = 0;
	private String batallaNombre;
	private TextButton contadorHumano;
	private TextButton contadorMaquina;
	private TextButton contadorHumano2;
	private TextButton contadorMaquina2;
	
	private Boolean pantallaContador;
	private Integer cantidadContador;

	boolean hayReina = false;
	boolean hayRey = false;
	boolean hayPuerta = false;
	private String tipoContadorHumano,tipoContadorMaquina;
	private int limiteMovMJN20=25,limiteMovMJN19=10,limiteMovMJN22=30;
	private ArrayList<String> fensMJN23 = new ArrayList<String>();
	private boolean flagSonido=true;
	private String valorDificultad;
	private Configuracion configuracion;
	
	private boolean bGanar = false;
	private boolean bPerder = false;
	private boolean bTablas = false;
	ArrayList<String> bolsas = null;
	private boolean motorIniciado;
	private int monedaInicial;
	private float timecount;
	private boolean flagVideorender=true;
	private MiniJuegoGeneral miniJuegoGeneral;
	
	/**
	 * Constructor de la clase MiniJuegoGeneral que nos permite crear un
	 * minijuego cargando su configuración dinamicamente a través del archivo de
	 * configuración
	 * 
	 * @param pGame
	 *            pantalla del juego
	 */
	public MiniJuegoGeneral(final Miniheroes pGame) {
		super(pGame);
		game = pGame;
		//sManager = this;		
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
	
	public void asignarPartida(
			Partida partida,
			String batalla,
			String minijuego,
			Dificultad dificultad,
			Configuracion configuracion/* , String XMM */) throws Exception {

		// System.out.println("ALGUNA VEZ ENTRE AQUI? 2");
		// IniciariMiniJuegoMangoPaola();

		this.partida= partida;
		this.minijuego = minijuego;
		this.batalla = batalla;
		bolsasCapturadas=0;
		this.dificultad=dificultad;
		this.configuracion= configuracion;
		// this.XMM = XMM;
	
		 System.out.println("nombrePartida:" + partida.getNombre());
		this.valorDificultad=partida.getDificultad();	
		this.avatar = partida.getPersonaje();
		gestionarDificultad();		
		valorDificultad ="NDF "+ valorDificultad;		
		this.partidaMinijuego = this.partida.getMinijuego();

		try {

			 moneda = Integer.parseInt(this.partida.getMonedas());	
		
			 System.out.println("monedaPartida:" + moneda);
				
		} catch (Exception e) {
			
			moneda= Integer.parseInt(dificultad.getMonedaInicio());
			 System.out.println("monedaPartidaDefecto:" + moneda);
				
		}
		monedaInicial=moneda;
		if (partidaMinijuego.equals(minijuego)){
			// System.out.println("cargando FenActual");
			txcargaFen = this.partida.getFen();
			 System.out.println("FenActual:" + txcargaFen);
		} else {
			txcargaFen = "";
		}
	}
	/**
	 * carga toda la configuración de la dificultad de un juego
	 * @param valorDificultad
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private void gestionarDificultad() throws NumberFormatException, Exception {
		//Dificultad dif = new Dificultad();
		
		costoMuro= Integer.parseInt(dificultad.getMuroConstruir());
		derriboMuro=Integer.parseInt(dificultad.getMuroDestruir());
		ganarMoneda=Integer.parseInt(dificultad.getMonedaGanar());
		segundos= Integer.parseInt(dificultad.getReloj());
		cantidadBolsas=  Integer.parseInt(dificultad.getMostrarMoneda());
		//System.out.println("cantidadBolsas:"+ cantidadBolsas);
		cantidadMonedas= Integer.parseInt(dificultad.getBolsaMoneda());
		cantidadPaja= Integer.parseInt(dificultad.getPaja());		
		//System.out.println("cantidadMonedas:"+ cantidadMonedas);		
	}
				
	@Override
	public void show() 
	{
		System.gc();
		bGanar = false;
		bPerder = false;
		bTablas = false;
        motorIniciado=false;
		musicaFondo.stop();
	
		System.out.println("show enroque:"+enroquesRealizados);
		cargarAnimacionesPatriotas();
		cargarAnimacionesRealistas();

		System.out.flush();
		//campana =game.getManager().get("assets/Sonidos/campana.mp3");
		leerConfiguracion();
		//conversacion.start();
			
		int nroMinijuego = Integer.parseInt(minijuego.substring(9));
		int nroBatalla = Integer.parseInt(batalla.substring(7));
		int batallaPartida = 0;
		int minijuegoPartida = 0;
		try {
			batallaPartida = Integer.parseInt(partida.getBatalla().substring(7));
			minijuegoPartida = Integer.parseInt(partida.getMinijuego().substring(9));
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		configurarAlcance(vPiezasExcluidas);			

		if (!recargaScreen)
		{
			if (nroBatalla < batallaPartida){
				mostrarVideos = false;
			}
			else
			if (nroMinijuego < minijuegoPartida) 
				mostrarVideos = false;				
			else
			{
				mostrarVideos = true;
			}
		}

		System.out.println("mostrarvideos:"+mostrarVideos);
		try {

			System.out.println("leyendo xmm:"+Xmm);
			
			cantidadBolsas= cantidadBolsas-bolsasCapturadas;
		
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("Error dentro del try: " + e1.getMessage());
		}			

		skin2 = new Skin(Gdx.files.internal("uiskin.json"));
		skin3 = new Skin();
		pack3 = new TextureAtlas(Gdx.files.internal("assets/skins/numeros.pack"));
		skin3.addRegions(pack3);

		skin4 =	new Skin();
		letras = new Label[8];
		numeros = new Label[8];
		stage = new Stage();
		font = new BitmapFont();
		skin1 = new Skin();
		pack = new TextureAtlas(Gdx.files.internal("assets/skins/minijuegos.pack"));
		skin1.addRegions(pack);
			
		recargaScreen = false;	

		canon = new ActorExtra(this, TipoDeActor.CANON, 0);
		explosion= new ActorExtra(this, TipoDeActor.EXPLOSION, 0);
		flecha= new ActorExtra(this, TipoDeActor.FLECHA, 0);
		magia= new ActorExtra(this, TipoDeActor.MAGIA, 0);
		lanza= new ActorExtra(this, TipoDeActor.LANZA, 0);
		machete= new ActorExtra(this, TipoDeActor.MACHETE, 0);
		cetro= new ActorExtra(this, TipoDeActor.CETRO, 0);

		texture=game.getManager().get("assets/Texturas/explosion2.png");//TODO
		texturaPersonaje=game.getManager().get(rutaTexturaPersonaje);
		texturaPersonajeAura=game.getManager().get(rutaTexturaPersonaje.split("/")[0]+"/"+rutaTexturaPersonaje.split("/")[1]+"/Aura/"+rutaTexturaPersonaje.split("/")[2]);
//		System.out.println(rutaTexturaPersonaje.split("/")[0]+"/"+rutaTexturaPersonaje.split("/")[1]+"/Aura/"+rutaTexturaPersonaje.split("/")[2]);
		Preferencias(true);
		disparoSeco=game.getManager().get("assets/Sonidos/disparoSeco.mp3");
		sonidoDerrota = game.getManager().get("assets/Sonidos/sonidoDerrota.mp3");
		sonidoTriunfo = game.getManager().get("assets/Sonidos/sonidoTriunfo.ogg");
		movIncorrecto = game.getManager().get("assets/Sonidos/movIncorrecto.ogg");
		reyMovimiento = game.getManager().get("assets/Sonidos/paso.ogg"); // REY
		reinaMovimiento = game.getManager().get("assets/Sonidos/paso.ogg"); // REINA
		torreMovimiento = game.getManager().get("assets/Sonidos/torre.ogg"); // TORRE
		alfilMovimiento = game.getManager().get("assets/Sonidos/alfil.ogg"); // ALFIL
		caballoMovimiento = game.getManager().get("assets/Sonidos/caballo.ogg"); // CABALLO
		peonMovimiento = game.getManager().get("assets/Sonidos/paso.ogg"); // PEON
		sonidoMoneda= game.getManager().get("assets/Sonidos/moneda.mp3");
//		sonidoPierdeMoneda = Gdx.audio.newSound(new FileHandle("assets/Sonidos/moneda_gasta.wav"));
		sonidoPierdeMoneda= game.getManager().get("assets/Sonidos/moneda_gasta.wav");
		
		// PeonBlanco= new Peon[8];
//		referencias();
		
	//	botones();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		casillas = new Casilla[64];

		camera = new OrthographicCamera(w, h);
		tiledMap = new TmxMapLoader().load(TexturaTablero);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

//		for(Pieza vPieza: piezasAMantener)
//		{
//			
////			System.out.println("piezasAMantener: " + vPieza);
//		}
		
		camera = (OrthographicCamera) stage.getCamera();
		
		stage.addActor(canon);
		stage.addActor(explosion);
		
		stage.addActor(flecha);
		stage.addActor(magia);
		stage.addActor(lanza);
		stage.addActor(machete);
		stage.addActor(cetro);

		canon.setVisible(false);
		canon.setSize(58,58);
		canon.setZIndex(64);
		 
		magia.setVisible(false);
		magia.setSize(58,58);
		magia.setZIndex(64);
		
		lanza.setVisible(false);
		lanza.setSize(58,58);
		lanza.setZIndex(64);
		
		machete.setVisible(false);
		machete.setSize(58,58);
		machete.setZIndex(64);
		
		cetro.setVisible(false);
		cetro.setSize(58,58);
		cetro.setZIndex(64);
		
		explosion.setVisible(false);
		explosion.setSize(58,58);
		explosion.setZIndex(100);
		flecha.setVisible(false);
		flecha.setSize(58,58);
		flecha.setZIndex(64);
		
		Texture texture=game.getManager().get("assets/Texturas/cuadrotransparente.png");
		Image imagen=new Image(texture);
		imagen.setPosition(520, 20);
		imagen.setSize(247, 475);
		stage.addActor(imagen);
		imagen.setZIndex(0);
		
//		contador(2);
	
		preferencias = Preferencias.getInstance();
		 //preferencias.load();
		boolean tocandoMusica=super.playingMusic();
		super.setVolume(preferencias.getPreferenciav("volumen"));
		
		if (preferencias.getPreferencia("musica")) 
		{
			if(tocandoMusica)
			{
				super.stopMusic();
			}
		}
 
	
		if (tocarMusica)
		{
			if (videoScreen!=null)
			{
				if (!videoScreen.isPlaying())
				{
//					cortinaFondo.play();
					cortinaFondo.setLooping(true);
					cortinaFondo.setVolume(volume);
				}

			} else {

//				cortinaFondo.play();
				cortinaFondo.setLooping(true);
			}

		} else {

			cortinaFondo.pause();
	   }
		
		
		conversacion = new Conversacion(Oyente.XMM);
		
		if (!recargaScreen)
		{
			if (nroBatalla < batallaPartida)
			{
				mostrarVideos = false;

			} else if (nroMinijuego < minijuegoPartida) {
		 
				mostrarVideos = false;
				
			} else {
				
				mostrarVideos = true;
			}
		}
		
		System.out.println("mostrarVideos:" + mostrarVideos);
		if (mostrarVideos&& enroquesRealizados==0)
		{
			System.out.println("mostrando video");
			cortinaFondo.pause();
			cargarayuda();
			
			if (tiempo&&reloj!=null)
			{
				System.out.println("pausando reloj");
				reloj.clearActions();
				flagReloj=true;
			}

			mostrarVideos = false;

		} else {
				
			iniciarMotor();
		}

		Gdx.input.setInputProcessor(stage);
		System.out.println("termine show");
}

	/**
	 * función que busca la casilla para agregar un actor y lo pinta en la escena
	 * @param objetos lista de objetos a pintar de un solo tipo
	 * @param tipoDeActor tipo  de actor a pintar
	 * @author yisheng
	 */
	private void pintarActor(ArrayList<String> objetos,TipoDeActor tipoDeActor) {
		try {					
			if (!objetos.equals(null)){
				for (String linea : objetos) {				
					//System.out.println("bolsa:" +linea);
					StringTokenizer tokens = new StringTokenizer(linea);
					
					while(tokens.hasMoreTokens()){
						int posicion= Integer.parseInt(tokens.nextToken());
						
						Traductor traductor= new Traductor();
						String coordenada=traductor.NumerosACoordenadas(8, 8, posicion);
//						System.out.println("pgn posicion:"+ posicion);
//						
//						System.out.println("pgn bolsa:"+ coordenada);
//						
						
						for (int i=0 ; i<64; i++)
						{
							//System.out.println("valor i:"+i);
							//casil
							if (casillas[i].getPieza()==null && casillas[i].getActor()==null) 
							{
//								System.out.println("ind bolsa:"+ i);
						
//								System.out.println("tipoDeActor:" +tipoDeActor);
								ActorExtra actor = new ActorExtra(this, tipoDeActor, i);
								actor.setColor(colorOponente);
								setActorExtra(coordenada,actor , i);
								actor.setZIndex(0);
								i=64;
							
							}
						}				
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception						
		}
	}

	@Override
	public void render(float delta) {
//		System.out.println(reloj.getActions());
		
		try {
			
			timecount+=delta;
			
		Gdx.gl.glClearColor(0, 0, 0, 0);
		//Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		if (stage!=null){
			
		stage.act();
		stage.draw();
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
		
		//if (jugandovsMangoPaola) {
		//System.out.println(" jugar:" + jugar()+ " turno:" + Turno + " Xmm: " + Xmm);
		if(motorIniciado)
		if (conversacion!=null)		
		if (conversacion.isArriba())
		{
			if (jugar() && Turno.equals(colorOponente) && juegaMotor)
				{ 
						if(Xmm.contains("MJN 11")&&(bGanar||bPerder)){
							
						}
						else{
					 JugarMangoPaola();
					 }
				}
				else {
				if (!Turno.equals(colorOponente)) {
				Timer.schedule(new Task() {
						@Override
						public void run() {
						}
					}, 0.5f * 1);
				}
			}
					if(tiempo){			
						if ((int) rotateAction.getDuration() == (int) rotateAction.getTime()&& flagPeonAlPaso==false){
							if (flag == true) {
								fondoReloj.setPack(packRelojDerrota);						
								bPerder = true; //perder();
								flag=false;

								System.out.println("ct23");
								CambiarTurno();
							}
								
						}
					}
					
					if (tocarMusica){
						if (!cortinaFondo.isPlaying())
						try {
							if (!videoScreen.isPlaying()&&!videoScreen.isPaused())
							{
								
							cortinaFondo.play();
							cortinaFondo.setVolume(volume);
							}	
						} catch (Exception e) {
							// TODO: handle exception
							cortinaFondo.play();
							cortinaFondo.setVolume(volume);
						}
					}		
		}
//		int mouseX=Gdx.input.getX();
//		//int mouseY=Gdx.input.getY();
//		float width = Gdx.graphics.getWidth();
//		//float height = Gdx.graphics.getHeight();
//		float wf = (width / 2) - (w / 2);
//	//	float hf = (height/ 2) - (h / 2);
//		if (Gdx.graphics.isFullscreen()) {
//			mouseX=(int) (mouseX-wf);
//			
//			}
//		if(mouseX>=740 && flag2==true){
////			mostrarBotones();
//			flag2=false;
//			}
//			else
//
//		if(mouseX<740 && flag2==false)
//		{
////			ocultarBotones();
//			flag2=true;}
			
		}
		
		if(bPerderMJN02){
			perder();
			bPerderMJN02=false;
		}
		if(jugar()){
			if (bGanar){
				ganar();}
			
			else if (bPerder){
				perder();}
			else if(bTablas){
				tablas();}
		}

		//Gdx.graphics.requestRendering();		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		}

	@Override
	public void hide() {
		//Timer.instance().stop();
		Gdx.input.setInputProcessor(null);
		if(miniJuegoGeneral!=null){
			miniJuegoGeneral=null;
			System.gc();
			
		}
		if(sonidoTriunfo!=null){sonidoTriunfo.stop();}
		
		flagVideorender=true;
		Timer.instance().clear();
		bPerderMJN02=false;
		if(videoScreen!=null)
		{				
			videoScreen.setAtlas(null);
			videoScreen.dispose();
			videoScreen=null;
		}
		flagReloj=false;
		rotacionIni=0;
		rotacionAcc=0;
		tiRotacion=0;
		flagPeonAlPaso=false;
		if(tiempo)
			reloj.clearActions();

		flag3=false;
		flag=true;
		contadorPaja=0;
		contadorPiezas=0;
		flagPauseResume=true;
		alfilPatriotaDerechaAbajo.dispose();
		
		alfilPatriotaDerechaArriba.dispose();
		alfilPatriotaIzquierdaArriba.dispose();
		alfilPatriotaIzquierdaAbajo.dispose();

		torrePatriotaIzquierda.dispose();
		torrePatriotaDerecha.dispose();
		torrePatriotaArriba.dispose();
		torrePatriotaAbajo.dispose();
		damaPatriotaIzquierda.dispose();
		damaPatriotaDerecha.dispose();
		damaPatriotaArriba.dispose();
		damaPatriotaAbajo.dispose();
		damaPatriotaDerechaAbajo.dispose();
		damaPatriotaDerechaArriba.dispose();
		damaPatriotaIzquierdaArriba.dispose();
		damaPatriotaIzquierdaAbajo.dispose();
		reyPatriotaIzquierda.dispose();
		reyPatriotaDerecha.dispose();
		reyPatriotaArriba.dispose();
		reyPatriotaAbajo.dispose();
		reyPatriotaDerechaAbajo.dispose();
		reyPatriotaDerechaArriba.dispose();
		reyPatriotaIzquierdaArriba.dispose();
		reyPatriotaIzquierdaAbajo.dispose();;
		peonPatriotaArriba.dispose();
		peonPatriotaAbajo.dispose();
		peonPatriotaDerechaAbajo.dispose();
		peonPatriotaDerechaArriba.dispose();
		peonPatriotaIzquierdaArriba.dispose();
		peonPatriotaIzquierdaAbajo.dispose();;
		caballoPatriotaAbajoDerecha.dispose();
		caballoPatriotaAbajoIzquierda.dispose();
		caballoPatriotaArribaDerecha.dispose();	
		caballoPatriotaArribaIzquierda.dispose();	
		caballoPatriotaDerechaArriba.dispose();	
		caballoPatriotaDerechaAbajo.dispose();	
		caballoPatriotaIzquierdaArriba.dispose();	
		caballoPatriotaIzquierdaAbajo.dispose();;
		
		alfilRealistaDerechaAbajo.dispose();
		alfilRealistaDerechaArriba.dispose();
		alfilRealistaIzquierdaArriba.dispose();
		alfilRealistaIzquierdaAbajo.dispose();
		torreRealistaIzquierda.dispose();
		torreRealistaDerecha.dispose();
		torreRealistaArriba.dispose();
		torreRealistaAbajo.dispose();
		damaRealistaIzquierda.dispose();
		damaRealistaDerecha.dispose();
		damaRealistaArriba.dispose();
		damaRealistaAbajo.dispose();
		damaRealistaDerechaAbajo.dispose();
		damaRealistaDerechaArriba.dispose();
		damaRealistaIzquierdaArriba.dispose();
		damaRealistaIzquierdaAbajo.dispose();
		reyRealistaIzquierda.dispose();
		reyRealistaDerecha.dispose();
		reyRealistaArriba.dispose();
		reyRealistaAbajo.dispose();
		reyRealistaDerechaAbajo.dispose();
		reyRealistaDerechaArriba.dispose();
		reyRealistaIzquierdaArriba.dispose();
		
		reyRealistaIzquierdaAbajo.dispose();
		peonRealistaArriba.dispose();
		peonRealistaAbajo.dispose();
		peonRealistaDerechaAbajo.dispose();
		peonRealistaDerechaArriba.dispose();
		peonRealistaIzquierdaArriba.dispose();
		peonRealistaIzquierdaAbajo.dispose();	
		alfilDormido.dispose();
		torreDormido.dispose();
		peonDormido.dispose();
		caballoRealistaAbajoDerecha.dispose();
		caballoRealistaAbajoIzquierda.dispose();
		caballoRealistaArribaDerecha.dispose();	
		caballoRealistaArribaIzquierda.dispose();	
		caballoRealistaDerechaArriba.dispose();	
		caballoRealistaDerechaAbajo.dispose();	
		caballoRealistaIzquierdaArriba.dispose();	
		caballoRealistaIzquierdaAbajo.dispose();;

		pack.dispose();
		pack2.dispose();
		if (packAnimacionReloj!=null)
		{
			packAnimacionReloj.dispose();
		}
		if (packRelojDerrota!=null)
		{
			packRelojDerrota.dispose();
		}
		
		if(pack3!=null)
		pack3.dispose();
		
		reyMovimiento.stop();
		reinaMovimiento.stop();
		torreMovimiento.stop();
		alfilMovimiento.stop();
		caballoMovimiento.stop();
		peonMovimiento.stop();
		disparoSeco.stop();
		

		if (alfilDormido!=null)
		alfilDormido.dispose();
		
		if (torreDormido!=null)
			torreDormido.dispose();
			
		if (peonDormido!=null)
			peonDormido.dispose();
			

		if (cortinaFondo != null) {
			cortinaFondo.stop();
		}

		vPiezasExcluidas=null;
		pgnSalida=null;
		tiledMap.dispose();
		
		skin1=null;
		skin3=null;
		skin4=null;
		
		font.dispose();
				
		peonalpaso = null;
		jaqueMate = false;
		visto=false;
		
		if (skin2 != null) {
			skin2=null;
		}
		
	
		listaCasillasDeshacer.clear();
		System.out.println("hide c;");

		pgnMantenerPieza.clear();
		pgnMantenerActor.clear();

		System.out.println("hide d;"+conversacion);
		try {
			if(conversacion!=null){
			if (conversacion.isArriba()){
				conversacion.recibirComando("FIN", "", "");
				conversacion.recibirComando("quit", "", "");
				conversacion.finalize();
			}}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		bPerder = false;
		bGanar = false;
		bTablas = false;
		conversacion=null;
		stage.dispose();
		stage=null;
		camera=null;
		tiledMapRenderer=null;
		System.gc();
		System.out.println("termine el hide");
}

	@Override
	public void dispose() {
		hide();
		System.gc();
	}	
	public void habilitarPiezas(eColores pColor) {
//		 System.out.println("Habilitando: " + Turno.toString() );
		if (colorActivas != pColor) {
//			colorActivas = pColor;
//			 System.out.println("");
//			 System.out.println("________________________________________________________________");
//			 System.out.println(" Jugando: " + colorActivas.toString());
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

	private void CambiarTurno() {
		if (!bGanar&&!bPerder&&!bTablas){
			System.out.println("cambiarturno");
					
			comprobarContadoresHumano();
			comprobarContadoresMaquina();
			comprobarEstadoJuego();	
	//		 System.out.println("cambiando de turno: " + Turno.toString());		
	//		 if(Xmm.contains("MJN 07")){
	//			 int conTorres=conteoDePiezas(eTipoPieza.Torre,Color.WHITE);
	//			 if(conTorres<2)
	//			 {
	//				 bPerder = true; //perder();
	//			 }
	//		 }
				
			
			if (!juegaMotor){
				System.out.println("no juega el motor");
				Turno = Color.WHITE;
				habilitarPiezas(eColores.Blancas);
				
			} else 	
				//if(Xmm.contains("MJN 02") || Xmm.contains("MJN 04")){
			{
				System.out.println("juega el motor");
				
				if (Turno == Color.WHITE) {
					habilitarPiezas(eColores.Negras);
					Turno = Color.BLACK;
					//movimientosCompletos++;
					
				} else {
					habilitarPiezas(eColores.Blancas);
					Turno = Color.WHITE;
				}		
			}
		}
			
//		if (bGanar)
//			ganar();
//		else if (bPerder)
//			perder();
//		else if(bTablas)
//			tablas();
	}

	
	/**
	 * Metodo para asignar una casilla a una pieza
	 **/

	@Override
	public void setCasilla(String pPgn, Pieza pPieza, int pInd) {
		if (!pgnMantenerPieza.equals(pPgn)){
			pPgn = pPgn.substring(0, 2);
			casillas[pInd] = new Casilla();
			casillas[pInd].setPieza(pPieza);
			casillas[pInd].setPgn(pPgn);	
		}
	}

	/**
	 * Metodo para asignar una casilla a una pieza
	 **/

	@Override
	public void setActorExtra(String pPgn, ActorExtra pActor, int pInd) {
		pPgn = pPgn.substring(0, 2);
		pActor.setPosition(tablero.Pgn2XY(pPgn)[0], tablero.Pgn2XY(pPgn)[1]);
		
		
		if (pActor.getTipo().equals(TipoDeActor.PUERTA))
		pgnSalida = pPgn;
		
//		if( Xmm.contains("MJN 16")){
//			
//			System.out.println("ANDAMOS POR AQUI");
//			pActor.equals(TipoDeActor.REY2);
//			pgnSalida = pPgn;
//			
//		}
		
	
		
		ActorExtra actorExtra = pActor;
		actorExtra.setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
		
//		System.out.println(actorExtra.getTipo());
		
		//if(!actorExtra.getTipo().equals(TipoDeActor.PUERTA)){
		if (!pgnMantenerActor.equals(pPgn)){
			casillas[pInd] = new Casilla();
			casillas[pInd].setActor(pActor);
			casillas[pInd].setPgn(pPgn);		
		}
	//}
//		float actorY=actorExtra.getY();
//		float actorX=actorExtra.getY();
//		actorExtra.po
		stage.addActor(actorExtra);
		actorExtra.setZIndex(0);

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
	 * devuelve un arreglo de  casilla ocupadas por el tipo de pieza y color o null en caso contrario
	 * 
	 * @param pTipoPieza tipo de pieza
	 * @param pColor Color de la pieza
	 * @return
	 */
	public Casilla[] casillaOcupada(eTipoPieza pTipoPieza , Color pColor) {
		Casilla[] vCasillasOcupadas = new Casilla[64];
		
//	  System.out.println("Buscando :"+pPgn);
		int j=0;
		for (int i = 0; i <= 63; i++) {			
			
			if ( casillas[i].getPieza() != null) 
			{
				//System.out.println("encontrando:"+casillas[i].getPgn());				
				if (casillas[i].getPieza().getTipoPieza()==pTipoPieza && casillas[i].getPieza().getColor().equals(pColor) ) {					
//					System.out.println("encontre: " + casillas[i].getPieza() + " color: " + casillas[i].getPieza().getColor());					
					vCasillasOcupadas[j] = casillas[i];
					j++;
				}
			//}
		   }
		}
//		System.out.println("retornando null");
		if (j>0){
			Casilla[] retCasillasOcupadas = new Casilla[j];
			for (int i = 0 ;i<j ; i++)	{
				retCasillasOcupadas[i] = vCasillasOcupadas[i];				
			}
			return retCasillasOcupadas;
		}
		else
			return null;
	}

	/**
	 * devuelve la casilla si esta ocupada o null en caso contrario
	 * 
	 * @param pPgn
	 * @return
	 */
	public Casilla casillaOcupada(String pPgn) {

//	  System.out.println("Buscando :"+pPgn);
		for (int i = 0; i <= 63; i++) {			
		try {
			if (casillas[i].getPieza() != null || casillas[i].getActor() != null) 
			{

				if (casillas[i].getPgn().equals(pPgn) ) {					
					//System.out.println("encontre: " + casillas[i].getPieza() + " color: " + casillas[i].getPieza().getColor());					
						return casillas[i];
				}
			//}
		   }			
		} catch (Exception e) {
			// TODO: handle exception
		}

		}
//		System.out.println("retornando null");
		return null;
	}

	/**
	 * Funcion que retorna la casilla que ocupa una pieza
	 **/
	public String casillaOcupada(Pieza pPieza) {

		for (int i = 0; i <= 63; i++) {
			try {
				
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
	 * 
	 * @param pPgn posición de tablero para saber si está opcupada o no 
	 * @return 
	 */

	public boolean casillaEstaOcupada(String pPgn) {		
		for (int i = 0; i <= 63; i++) {			
			try {				
				if (casillas[i].getPgn().equals(pPgn)){					
					return true;
				}
			} 
			catch (Exception e) {				
			}
		}	
		return false;		
	}
	
	/**
	 * 
	 * @param pPgn posición de tablero para saber si está opcupada o no 
	 * @return 
	 */

	public boolean casillaEstaOcupadaTipoPieza(String pPgn , eTipoPieza pTipoPieza) {
//		System.out.println("pgn grrr : "+pPgn);
		for (int i = 0; i <= 63; i++) {
//			System.out.println("get pgn : "+casillas[i].getPgn());
			try {				
				if (casillas[i].getPgn().equals(pPgn)){
					if (casillas[i].getPieza().getTipoPieza() == pTipoPieza){						
						return true;
					}
					else{
						
						return false;
					}
				}				
			} 
			catch (Exception e) {
				
			}
		}	
		
		return false;		
	}

	/**
	 * 
	 * @param pPgn posición de tablero para saber si está opcupada o no 
	 * @return 
	 */

	public boolean casillaEstaOcupadaTipoActor(String pPgn , TipoDeActor pTipoDeActor) {
//		System.out.println("pgn grrr : "+pPgn);
		for (int i = 0; i <= 63; i++) {
//			System.out.println("get pgn : "+casillas[i].getPgn());
			try {				
				if (casillas[i].getPgn().equals(pPgn)){
					if (casillas[i].getActor().getTipo()== pTipoDeActor){
						
						return true;
					}
					else{
						
						return false;
					}
				}				
			} 
			catch (Exception e) {
				
			}
		}	
		
		return false;		
	}


	@Override
	public boolean ValidarMovimiento(Pieza pPieza, int pJuego, int mouseX, int mouseY, float actorX, float actorY,
			float rangoX, float rangoY, float posX, float posY) {
		
		
		
		if (eliminandoMuro)
			return false;
				
		eliminandoMuro = false;
		colocandoMuro = false;
		

		int juego = pJuego;

		if (pPieza.getColor().equals(getTurno())) {
		
			if (pPieza.getEstadoPieza() != eEstadoPieza.Seleccionado) {
	
				cambiarEstadoEsperando();
				if(pPieza.getTipoPieza()!=eTipoPieza.Muro){
				pPieza.cambiarEstadoPieza(eEstadoPieza.Seleccionado);
				}
//				System.out.println("retornando falso");
				return false;
			} else if (pPieza.getEstadoPieza() == eEstadoPieza.Seleccionado) {
				String pgni = tablero.XY2pgn((int) actorX, (int) actorY);
				String pgn = tablero.XY2pgn((int) posX, (int) posY);
				
				double distancia = Math.sqrt((Math.pow(posX - actorX, 2)) + Math.pow(posY - actorY, 2));
				final float duracion = (float) distancia / ((casilla.GetAnchoCasilla() + casilla.GetAltoCasilla()) / 2);
				if (posX >= tablero.GetInicioX() && posX <= tablero.GetFinX()) {
					if (posY >= tablero.GetInicioY() && posY <= tablero.GetFinY()) {
						Pieza piezat = null;
						Casilla casillat = casillaOcupada(pgn);
						if (casillat != null) {

							if (casillat.getPieza() != null) {
								piezat = casillat.getPieza();
								if(piezat.getTipoPieza()==eTipoPieza.Muro){
									if (!capturarMuros){										
										pPieza.cambiarEstadoPieza(eEstadoPieza.Esperando);
										System.out.println("retornando false 2");
										return false;	
									}
								}
								else
								if (piezat.getColor().equals(pPieza.getColor())) {
									// hacer click en una casilla ocupada por
									// una
									// pieza del mismo color
//									System.out.println("piezat : "+piezat.getTipoPieza());						
									pPieza.cambiarEstadoPieza(eEstadoPieza.Esperando);
									piezat.cambiarEstadoPieza(eEstadoPieza.Seleccionado);
								
//						System.out.println("retornando false 3");
									return false;
								}
							} else {																
							//	actorT = casillat.getActor();
								
							}
						}

					
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
							
							if (Math.abs(tablero.Pgn2XY(pgni)[1] - tablero.Pgn2XY(pgn)[1]) <= casilla
									.GetAltoCasilla()) {								
								Traductor traductor =  new Traductor();
//								System.out.println("MVI " +  traductor.CoordenadasANumeros(8, 8,pgni));
								String vMovimientos = enviarMangoPaola("MVI " +  traductor.CoordenadasANumeros(8, 8,pgni)).get(0);
								boolean escogiendoPieza = false;
								for (String vPgn1:vMovimientos.split(" ")){				
									if (vPgn1.contains( String.valueOf(traductor.CoordenadasANumeros(8, 8, pgn)))){
										escogiendoPieza = true;
										EscogerPromovida(pPieza, pgni, pgn);										
										return true;
										//break;									
									}												
								}
								if (!escogiendoPieza)
									playMovimientoIncorrecto();
								
								return false;
								
							}
						} else if (cPiezaPromovida != null)
							pgn = pgn + cPiezaPromovida;
						

						if (!pPieza.getColor().equals(colorOponente) || !jugandovsMangoPaola) {

							//System.out.println("pgn: " + pgn + " pgnSalida: " +pgnSalida + " bolsasCapturadas: " + bolsasCapturadas);
							if (Xmm.contains("MJN 13") && pgn.equals(pgnSalida) && pPieza.getTipoPieza().equals(eTipoPieza.Alfil)){
								if (bolsasCapturadas <5){
									ArrayList<String> vMensaje = new ArrayList<String>();
									vMensaje.add("Debes capturar ");
									vMensaje.add("5 bolsas para poder salir");
									mostrarDialogo(eTipoDialogo.Aceptar, false, eTipoMensaje.Capturar5Bolsas);
//									System.out.println("retornando false porque no capturo 5 bolsas");
									return false;
								}
							}
							
							if (!validarMotor(pgni, pgn)){

								System.out.println("validar motor falso");
									contadorMovIncorrectos++;
									cPiezaPromovida = null;
									playMovimientoIncorrecto();
									if(pantallaContador)
									{
										comprobarContadoresHumano();
										comprobarContadoresMaquina();
									}
									System.out.println("limitador de mvimientos = "+limitadorMovimientos);
									System.out.println("cantidad limite : "+cantidadMovimientos);
									if(limitadorMovimientos==true)
										{
//										System.out.println("cantida de movimientos:"+cantidadMovimientos);
//										System.out.println("contadorMovIncorrectos:"+contadorMovIncorrectos);
//										
											if(contadorMovIncorrectos==cantidadMovimientos)
												{
												//System.out.println("pierde maldito");
												if(Xmm.contains("MJN 02")){
													bPerderMJN02=true;
												}
												else {
													bPerder = true; //perder();
													System.out.println("ct24");
													CambiarTurno();
												}
													

													
												}
										}
//									System.out.println("Jugadas : "+contadorJugadas);
//									System.out.println("capturadas : "+contadorCapturadas);
//									System.out.println("Movimientos Incorrectos : "+contadorMovIncorrectos);
//									System.out.println("no se puede jugar:" + pPieza.getColor());
									
									return false;
							} 
							else     // La jugada fue correcta 
								{

								System.out.println("La jugada fue correcta");
									contadorJugadas++;
																																				
								//	if (!Turno.equals(colorOponente)){
									//System.out.println("entre");
									// casillasDeshacer = new Casilla[32];
									// for (int i = 0; i <= 31; i++)
									// casillasDeshacer[i] = casillas[i];
									// listaCasillasDeshacer.add(casillasDeshacer);
									//}
								}
						}

						// txtArea.setText(pPieza.toString()+" : "+ pgni+" -
						// "+pgn);

						// obtenerFen();
						
						if (pPieza.getTipoPieza() == eTipoPieza.Torre) {
							if (juego == 0) {
								if (((Torre) pPieza).Validar(mouseX, mouseY, (int) actorX, (int) actorY, (int) rangoX,
										(int) rangoY, (int) posX, (int) posY) == true) {
									((Torre) pPieza).setMovida(true);
									if(casillaEstaOcupada(pgn)&& !casillaEstaOcupadaTipoActor(pgn, TipoDeActor.PUERTA)){
											animacionCapturando(pPieza,TipoDeActor.CANON,pgni,pgn,duracion);
											
										}
									else{
											
											EliminarPieza(pPieza, pgn);
											setCasilla(pgn, pPieza, pPieza.getInd());
										}
									if (colorOponente.equals(Color.BLACK)) {
										animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else{
										animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									sonido(pPieza, duracion);

									System.out.println("ct25");
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
								//	String dir=determinarMov(pgni,pgn);
//									System.out.println("dir : "+ dir);
									if(casillaEstaOcupada(pgn) && !casillaEstaOcupadaTipoActor(pgn, TipoDeActor.PUERTA)){										 
										animacionCapturando(pPieza,TipoDeActor.FLECHA,pgni,pgn,duracion);
										
									}
								else{
																				
										setCasilla(pgn, pPieza, pPieza.getInd());
									}
									if (colorOponente.equals(Color.BLACK)) {
										animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else{
										animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									sonido(pPieza, duracion);
									
								//	CambiarFen(piezasAMantener);

									if (pgn.equals(pgnSalida))	{
										if (Xmm.contains("MJN 13")){																				
												int cantAlfiles =0;												
												for(int i=0;i<=63;i++){
													try{
														if (casillas[i].getPieza().getTipoPieza().equals(eTipoPieza.Alfil))		
																cantAlfiles++;
													}
													catch(Exception e){													
													}
												}
//												System.out.println("cantidad de alfiles: " + cantAlfiles);
												if (pPieza.getTipoPieza().equals(eTipoPieza.Alfil) && cantAlfiles == 2){																																																								
													final Pieza fPieza = pPieza; 
													final String fPgn = pgn;
													
													Timer.schedule(new Task() {
														@Override
														public void run() {													
															Traductor traductor = new Traductor();
															enviarMangoPaola("QPP " +  traductor.CoordenadasANumeros(8,8, fPgn));
															casillas[fPieza.getInd()] =  new Casilla();	
															fPieza.remove();
														}
													},(duracion*0.5f));
												}
												else{									
													bGanar = true; //ganar();																										
//													CambiarTurno();									
	//												return true;
												}																			
										}
										else{									
											bGanar = true; //ganar();
		//									CambiarTurno();		
			//								return true;
										}																																
									}

									System.out.println("ct26");

									CambiarTurno();

									if(Xmm.contains("MJN 13"))	{
										 eliminarMonedasManual();
										 agregarMonedasManual();
										}
									else
									if(Xmm.contains("MJN 11"))	{
										 cambiarColorMonedas(Turno);
										}
																											
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
									if(casillaEstaOcupada(pgn)&& !casillaEstaOcupadaTipoActor(pgn, TipoDeActor.PUERTA)){
										animacionCapturando(pPieza,TipoDeActor.MAGIA,pgni,pgn,duracion);										
									}
									else{									
										EliminarPieza(pPieza, pgn);
										setCasilla(pgn, pPieza, pPieza.getInd());
									}
									if (colorOponente.equals(Color.BLACK)) {
										animacionMovimientoPatriotas(pPieza,pgni, pgn);
									}
									else{
										animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									sonido(pPieza, duracion);
									
									if(Xmm.contains("MJN 17")){
										eliminarMurosManual();
										agregarMurosManual(35);
									
										int conteo=0;
										if (pgn.equals(pgnSalida) && pPieza.getTipoPieza() == eTipoPieza.Reina)
										{					
											final Pieza fPieza = pPieza; 
											Traductor traductor = new Traductor();
											enviarMangoPaola("QPP " +  traductor.CoordenadasANumeros(8,8, pgn));	
											
											borrarCasilla(pPieza.getInd());
											
											Timer.schedule(new Task() {
												@Override
												public void run() {
													fPieza.remove();
													
												}
											},(duracion*0.5f));
										
											conteo= conteoDePiezas(eTipoPieza.Rey, Color.WHITE);
											if(conteo==0){
												bGanar = true; //ganar();
											}
										}
									}
									else if (pgn.equals(pgnSalida)&& visto==false){
//											comprobarContadoresHumano();
//											comprobarContadoresMaquina();											
											bGanar = true; //ganar();

										//	System.out.println("ct27");
//											CambiarTurno();
//											return true;
									}

									peonalpaso = null;
									pgnPeonalPaso = "-";
									System.out.println("ct1");
									CambiarTurno();
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
								if (((Rey) pPieza).Validar(mouseX, mouseY, (int) actorX, (int) actorY, (int) rangoX,
										(int) rangoY, (int) posX, (int) posY) == true) {
//									 System.out.println("enrocado : " + ((Rey) pPieza).isEnrocado());
//									 System.out.println("movido :"+ ((Rey)pPieza).isMovido());
									if (((Rey) pPieza).isEnrocado() == true) {
										verificarEnroque((Rey) pPieza, pgni, pgn);
									}
				
//									System.out.println("enroquesRealizados: " + enroquesRealizados);
									if (Xmm.contains("MJN 23")){																			
										if (((Rey) pPieza).isEnrocado()&& pPieza.getColor().equals(colorAliado)){
											reintentar(false);
											if (enroquesRealizados>=4){
												enroquesRealizados = 0;
												bGanar = true; //ganar();											
											}											
										}
										else {								
											if (reintentoEnroques<3)
												reintentar(true);		
											else 
												bPerder = true; //perder();
										}
									}										
									
									((Rey) pPieza).setEnrocado(false);																						

									if (permitirJaque==false && reyJaque.contains("+Ok")){									
											return false;
										}
									else{
										if(casillaEstaOcupada(pgn))
										{
											animacionCapturando(pPieza,TipoDeActor.CETRO,pgni,pgn,duracion);									
										}
									else
										{
											
											EliminarPieza(pPieza, pgn);
											setCasilla(pgn, pPieza, pPieza.getInd());
										}
										if (colorOponente.equals(Color.BLACK)) {
											animacionMovimientoPatriotas(pPieza,pgni, pgn);
											}
											else{
											animacionMovimientoRealistas(pPieza,pgni, pgn);
											}
									sonido(pPieza, duracion);
									
									peonalpaso = null;
									pgnPeonalPaso = "-";
									CambiarFen(piezasAMantener);																
									
								
									
									if(Xmm.contains("MJN 17")){
										eliminarMurosManual();
										agregarMurosManual(35);
									
										if(pgn.equals(pgnSalida) &&pPieza.getTipoPieza() == eTipoPieza.Rey)
										{int conteo=0;
											
											final Pieza jPieza = pPieza; 
											Traductor traductor = new Traductor();
											enviarMangoPaola("QPP " +  traductor.CoordenadasANumeros(8,8, pgn));	
											borrarCasilla(pPieza.getInd());
											
											
											Timer.schedule(new Task() {
												@Override
												public void run() {
													jPieza.remove();
												}
											},(duracion*0.5f));
									
											conteo= conteoDePiezas(eTipoPieza.Reina, Color.WHITE);
										
											if(conteo==0){
												bGanar = true; //ganar();
											}
										}
									}
									else{
										if (pgn.equals(pgnSalida)&& visto==false){
												comprobarContadoresHumano();
												comprobarContadoresMaquina();
												bGanar = true; //ganar();																					
										}

									}
									if(mostrarAlcanceOponente){
										
										alcanceOponente(piezasExcluidas);
										
									}

										for (int i=0;i <=63;i++){
											if (casillas[i]!=null){
												if (casillas[i].getPieza() != null);
//													System.out.println("casilla " +" i:" + i + " " + casillas[i].getPgn() +  casillas[i].getPieza() + " "+casillas[i].getPieza().getColor());
												else {
													if (casillas[i].getActor()!=null);
//													System.out.println("casilla " +" i:" + i + " " +  casillas[i].getPgn() +  casillas[i].getActor() + " "+casillas[i].getActor().getColor());
												}
											}
										}
									pPieza.setZIndex(64);	

									System.out.println("ct2");
									CambiarTurno();
									return true;
									}
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
									
									//String dir=determinarMov(pgni,pgn);
//									System.out.println("dir : "+dir);
									if(casillaEstaOcupada(pgn))
									{
										animacionCapturando(pPieza,TipoDeActor.LANZA,pgni,pgn,duracion);
									}
								else
									{
										EliminarPieza(pPieza, pgn);
										setCasilla(pgn, pPieza, pPieza.getInd());
									}
									
									if (colorOponente.equals(Color.BLACK)) {
										animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else{
										animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									
									if(Xmm.contains("MJN 19"))
									{
										Traductor traductor=new Traductor();	
										int num =traductor.CoordenadasANumeros(8, 8, pgn);
										eliminarexplosiones();
										String numEliminadas;
										numEliminadas= resultadoJugarMangoPaola(enviarMangoPaola("QAC "+num));
//										System.out.println("Posiciones eliminadas : "+ numEliminadas);
/* SI REALIZO DOBLE ATAQUE  */			if(numEliminadas!=null)
											{
												contadorDobleAtaque++;
												actualizarContador(true, contadorDobleAtaque);
												actualizarContador(false, 10-contadorJugadas);
												

												int numeroPosiciones= 0;
												numeroPosiciones=numEliminadas.split(" ").length;
/* TRIPLE ATAQUE  */							if(numeroPosiciones>2)
													{
														try {gastarMonedas(-5);} 
														catch (Exception e){e.printStackTrace();}
		
													}
												String[] pos = new String[numeroPosiciones];
												explosionMJN19=new ActorExtra[numeroPosiciones];
												String[] pgnEliminar = new String[numeroPosiciones];
												for(int i=0; i<numeroPosiciones;i++)
													{
														pos[i]=numEliminadas.split(" ")[i];
														pgnEliminar[i]=traductor.NumerosACoordenadas(8, 8,Integer.parseInt(pos[i]));
														if(casillaOcupada(pgnEliminar[i]).getPieza()!=null)
															{
																final Pieza piezaEliminar=casillaOcupada(pgnEliminar[i]).getPieza();
																borrarCasilla(piezaEliminar.getInd());
																piezaEliminar.remove();
																explosionMJN19[i]=new ActorExtra(this, TipoDeActor.EXPLOSION, 0);
																																
																stage.addActor(explosionMJN19[i]);	
																explosionMJN19[i].setPosition(tablero.Pgn2XY(pgnEliminar[i])[0],tablero.Pgn2XY(pgnEliminar[i])[1]);
																explosionMJN19[i].setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
																explosionMJN19[i].setVisible(true);
																explosionMJN19[i].setZIndex(0);
																															
															}
													}
											}										
									}
									
									if(mostrarAlcanceOponente && !bGanar&& !bPerder){
										alcanceOponente(piezasExcluidas);}
									
									sonido(pPieza, duracion);

									System.out.println("ct3");
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
									if (colorOponente.equals(Color.BLACK)) {
										animacionMovimientoPatriotas(pPieza,pgni, pgn);
										}
										else{
										animacionMovimientoRealistas(pPieza,pgni, pgn);
										}
									setCasilla(pgn, pPieza, pPieza.getInd());

									if(Xmm.contains("MJN 20")){
									 eliminarMurosManual();
									 agregarMurosManual(10);
									}
									sonido(pPieza, duracion);
									
									if(Xmm.contains("MJN 21")){
										if(contadorPeonAlPaso>=3){
											flagPeonAlPaso=true;
											rotateAction.finish();					
											bGanar = true; //ganar();																							
										}
									}

									System.out.println("ct4");
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

									System.out.println("ct5");
									CambiarTurno();
									return true;
								} else {

									System.out.println("ct6");
									CambiarTurno();
									return true;
								}
							}
						}
					} else {
						playMovimientoIncorrecto();
						cambiarEstadoEsperando();
						return false;
					}

				} else {
					playMovimientoIncorrecto();
					cambiarEstadoEsperando();
					return false;
				}
			}
			////
		} else {

		}
		playMovimientoIncorrecto();
		return false;
	}

	/**
	 * Metodo para Cambiar el estado de una pieza
	 * 
	 * @param pPieza
	 * @param pPgni
	 * @param pPgnf
	 */
	public void setPiezaPromovida(Pieza pPieza, String pPgni, String pPgnf) {
		// enviarMangoPaola("move " + pPgni.toLowerCase() + pPgnf.toLowerCase()+
		// cPiezaPromovida);
		// cPiezaPromovida = "";
		Pieza vPieza = null;
		// System.out.println("pPgnf " + pPgnf);
		String vTipoPromovida = pPgnf.substring(2, 3);
		vTipoPromovida = vTipoPromovida.toLowerCase();
		// System.out.println("vTipoPromovida " + vTipoPromovida);
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
		pPieza.remove();
		// pPieza.setColor(Color.BLUE);
		borrarCasilla(pPieza.getInd());

		if (vTipoPromovida.contains("r")) {
			vPieza = new Torre(color, vPgnf, pPieza.getInd(), 0, this);
		} else if (vTipoPromovida.contains("n")) {
			vPieza = new Caballo(color, vPgnf, pPieza.getInd(), 0, this);
		} else if (vTipoPromovida.contains("b")) {
			vPieza = new Alfil(color, vPgnf, pPieza.getInd(), 0, this);

		} else if (vTipoPromovida.contains("q")) {
			vPieza = new Reina(color, vPgnf, pPieza.getInd(), 0, this);
		}
		vPieza.setInd(pPieza.getInd());
		// vPieza.setVisible(true);

		setCasilla(vPgnf, vPieza, vPieza.getInd());
		stage.addActor(vPieza);
		piezasPromovida.add(vPieza);

		cPiezaPromovida = null;
		if (vPieza.getColor().equals(Color.WHITE)){
			jugadasPeon++;
			contadorPromovidas++;
			//actualizarContador(true, contadorPromovidas);
			contadorJugadas++;
		//	actualizarContador(false, contadorJugadas);
			
		}
		// vPieza=null;
	}

	private void EliminarPieza(Pieza pPieza, String pPgn) {	
		

		if (pPgn.length() > 2) {
			// TODO manejar el error de pPgn
//			System.out.println("error en pgpn:" + pPgn);
			pPgn = pPgn.substring(0, 2);
		}
		Pieza piezat = null;
		ActorExtra actort = null;
		Casilla casillat = casillaOcupada(pPgn);
			
		if (casillat != null) {
			piezat = casillat.getPieza();
			actort = casillat.getActor();
				if (piezat != null) {
				//System.out.println("eliminar pieza 2");
				if (pPieza == null || piezat != pPieza && (!piezat.getColor().equals(pPieza.getColor()))) {			
					if(Xmm.contains("MJN 17")){
						if(piezat.getTipoPieza().equals(eTipoPieza.Reina)){
							bPerder = true; //perder();

							System.out.println("ct7");
							CambiarTurno();
						}					
					}
					if(piezat.getTipoPieza().equals(eTipoPieza.Muro) && piezat.getEcolor().equals(eColores.Moneda)){
						try {
							gastarMonedas(-cantidadMonedas);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
					}
					
					//System.out.println("eliminar pieza 3");
					//piezat.setTouchable(Touchable.disabled);
//					System.out.println("Eliminando : " + piezat);
					contadorCapturadas++;
				//	actualizarContador(true, contadorCapturadas);

					
					borrarCasilla(piezat.getInd());
					//piezat.setVisible(false);
					piezat.remove();
					if (Xmm.contains("MJN 11")){
						int cantAlfiles =conteoDePiezas(eTipoPieza.Alfil, Color.WHITE);						

						if (cantAlfiles!=2){
							bPerder = true; //perder();

							System.out.println("ct8");
							CambiarTurno();
						}												
					}
					 if(Xmm.contains("MJN 07"))
					 	{
						 int conTorres=conteoDePiezas(eTipoPieza.Torre,Color.WHITE);
						 if(conTorres<2)
						 	{
							 bPerder = true; //perder();

								System.out.println("ct9");
							 CambiarTurno();
						 	}
					 	}
					 if(Xmm.contains("MJN 06"))
					 	{
						 int conTorres=conteoDePiezas(eTipoPieza.Torre,Color.WHITE);
						 if(conTorres<2)
						 	{
							 bPerder = true; //perder();

								System.out.println("ct10");
							 CambiarTurno();
						 	}
					 	}

					 String numPiezasPatriotas;
					 if(Xmm.contains("MJN 22"))
					 {
						 int numPeones =conteoDePiezas(eTipoPieza.Peon,colorAliado);
//						System.out.println("numero de peones : "+ numPeones);
						if(numPeones<4)
						{
							bPerder = true; //perder();

							System.out.println("ct11");
							CambiarTurno();
						}
					 }
					 if(contarPeones){
						 numPiezasPatriotas=resultadoJugarMangoPaola(enviarMangoPaola("NBP"));
					 }
					 else{
						  numPiezasPatriotas=resultadoJugarMangoPaola(enviarMangoPaola("NBS"));
					 }
					 /*TODO*/
					if (Xmm.contains("MJN 17") ){
						int conRey = conteoDePiezas(eTipoPieza.Rey,Color.WHITE);
						if(conRey>0){
							numPiezasPatriotas = "1";
						}						
					}	
					
					int nroBatalla = Integer.parseInt(batalla.substring(7));
										 
					if(numPiezasPatriotas.equals("0")&& nroBatalla>1){		
						bPerder = true; //perder();

						System.out.println("ct12");
						CambiarTurno();
					}
					 					 					 					
					if (piezat.getTipoPieza().equals(eTipoPieza.Muro) && piezat.getEcolor().equals(eColores.Moneda))  
						bolsasCapturadas++;
					if(pantallaContador)
					{
						comprobarContadoresHumano();
						comprobarContadoresMaquina();
					}
					//System.out.println("eliminar pieza 4 bolsas Capturadas: " + bolsasCapturadas);										
				}
			} else{
//				System.out.println("Eliminando : " + casillat);
					if (Xmm.contains("MJN 18")){
						contadorPaja++;
						actualizarContador(true, contadorPaja);
							if(contadorPaja==8){
								flagPeonAlPaso=true;
								rotateAction.finish();
								bGanar = true; //ganar();

								System.out.println("ct13");
								CambiarTurno();
							}
					}
				if (!actort.getTipo().equals(TipoDeActor.PUERTA)){ // para no eliminar las puertas
					actort.remove();
					borrarCasilla(actort.getInd());
					
				}
//				
				if (actort.getTipo().equals(TipoDeActor.BOLSA)&& pPieza.getColor().equals(colorAliado)) {
					try {
						bolsasCapturadas++;
						if(pantallaContador)
						{
							comprobarContadoresHumano();
							comprobarContadoresMaquina();
						}
						gastarMonedas(-cantidadMonedas);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
				}

				if (Xmm.contains("MJN 11")){					
					int cantBolsas = conteoDePiezas(TipoDeActor.BOLSA);
					
					if(cantBolsas  == 0){ 
						if (bolsasCapturadas>3){
							bGanar = true; //ganar();
						}
						else{
							bPerder = true; //perder();
						}

						System.out.println("ct13");
						CambiarTurno();
					}
				}																	
			}
		}
	

//			System.out.println("Error: casilla t vino null");
	}
	/**
	 * elimina un muro de
	 * @param pPgn
	 */

	private void EliminarMuro(String pPgn) {
		if (pPgn.length() > 2) {
			// TODO manejar el error de pPgn
//			System.out.println("error en pgpn:" + pPgn);
			pPgn = pPgn.substring(0, 2);
		}
		Pieza piezat = null;
		//ActorExtra actort= null;
		Casilla casillat = casillaOcupada(pPgn);
		if (casillat != null && casillat.getPieza().getTipoPieza().equals(eTipoPieza.Muro)) {
			piezat = casillat.getPieza();
		//	actort = casillat.getActor();

			// piezat = pieza capturada
			// pPieza = pieza que va a capturar

			if (piezat != null) {
					piezat.setTouchable(Touchable.disabled);
//					System.out.println("Eliminando Muro: " + piezat.getName());					
					borrarCasilla(piezat.getInd());
					
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

	private void IniciariMiniJuegoMangoPaola(String xmm) {
		String[] varRes1 = new String[2];
		try {

//			 System.out.println("ENVIARE ESTE COMANDO: " + xmm);

			if (txcargaFen != "") {
				varRes1 = xmm.split(" ");
//				System.out.println("FEN:" + txcargaFen);
				varRes = resultadoJugarMangoPaola(enviarMangoPaola("MJF " + varRes1[1] + " " + txcargaFen));//

			} else {
				
				varRes = resultadoJugarMangoPaola(enviarMangoPaola(xmm));
//				System.out.println(varRes + " ESTE ES");

				varRes1 = varRes.split(" ");

				if (Xmm.contains("MJN 13")) {// reiniciar mjn 13
					int pB = varRes.indexOf("B");
					//System.out.println("varRes 1" + varRes) ;
					while (varRes.indexOf("B", pB+1) <=0){
						//System.out.println("varRes " + varRes +  " varRes.split(B): " + varRes.split("B"));
						varRes = resultadoJugarMangoPaola(enviarMangoPaola(xmm));
						pB = varRes.indexOf("B");
						System.out.println("reiniciando mjn13");
					}
				}

				//txcargaFen = varRes1[0] + " " + varRes1[1];
//				if(Xmm.contains("MJN 16"))
//					txcargaFen.replace("E", "Z");
				
				if (xmm.contains("MJN 23")){
					while (fensMJN23.contains(varRes)){
						varRes = resultadoJugarMangoPaola(enviarMangoPaola(xmm));										
					}																
					fensMJN23.add(varRes);
				}

				
				txcargaFen = varRes;
			
			}
			//System.out.println("hola : "+txcargaFen);
		} catch (Exception e) {
			System.out.println("IniciariMiniJuegoMangoPaola Error : " + e.getMessage());
		
			e.printStackTrace();

		}
	}

	/**
	 *pasa el turno al motor y ejecuta el procesamiento gráfico
	 */
	private void JugarMangoPaola() {
		Traductor traductor = new Traductor();
//		System.out.println("jugarMangoPaola in: " + Xmm );

		if (!jaqueMate&&!bTablas&&!bGanar&&!bPerder) {
			// int i = 0 ;

			if(juegaMotor){
			String checkFlag = "";
				String pgnF = null, pgnI = null;
				if(Xmm.contains("MJN 22"))
					{
						varRes = resultadoJugarMangoPaola(enviarMangoPaola("GON NEGRO"));
						if (varRes==null || varRes.contains("COD903")|| varRes.contains("COD900")||varRes.contains("COD931")){

							System.out.println("ct14");	
							CambiarTurno();
								if (!bGanar &&!bPerder)
									enviarMangoPaola("BLA");
							} 
						else 
							{
								if(varRes.contains("+")){
								checkFlag = varRes.split(" ")[1].substring(2,5);
								pgnI=varRes.split(" ")[1].substring(0,2).toUpperCase();
								pgnF=varRes.split(" ")[1].substring(2,4).toUpperCase();
								
								}
								else{
									pgnI=varRes.split(" ")[1].substring(0,2).toUpperCase();
									pgnF=varRes.split(" ")[1].substring(2,4).toUpperCase();
								}
							}		
					}
				else
					{
						if(Xmm.contains("MJN 21"))
						{
							varRes = resultadoJugarMangoPaola(enviarMangoPaola("M21"));
						}
						else
						{
						varRes = resultadoJugarMangoPaola(enviarMangoPaola("GMO NEGRO SI_CAPTURA"));
						}
						
						if (varRes==null || varRes.contains("COD903")|| varRes.contains("COD900") ) 
							{			

							System.out.println("ct15");
								CambiarTurno();
							} 
						else 
							{
								String[] vPgn = varRes.split(" ");

								int iPgnF =0, iPgnI =0;
								iPgnI = Integer.parseInt(vPgn[0].toUpperCase());
								if (vPgn[1].contains("+"))
									{
										iPgnF =Integer.parseInt(vPgn[1].substring(0, vPgn[1].length()-1).toUpperCase());				
										checkFlag = vPgn[1].substring(vPgn[1].length()-1, vPgn[1].length()).toUpperCase();
//										System.out.println("checkflag: " + checkFlag + " vPgnF" + iPgnF);
									}
								else
									{
										iPgnF =Integer.parseInt(vPgn[1].toUpperCase());
									}
								pgnI = traductor.NumerosACoordenadas(8,8, iPgnI);				
								pgnF = traductor.NumerosACoordenadas(8,8, iPgnF);
							}
	

					}
			if(varRes!=null && !varRes.contains("COD903") && !varRes.contains("COD900")&& !varRes.contains("COD931")){
//			 System.out.println("MangoPaola Juega: " + varRes);
//			 System.out.println( "pgni" + pgnI + " pgnf" + pgnF);
	
				boolean peonPromovido = false;
				int Actor[] = tablero.Pgn2XY(pgnI);
				float actorX = Actor[0];
				float actorY = Actor[1];
				int pos[] = tablero.Pgn2XY(pgnF);
				float posX = pos[0];
				float posY = pos[1];
				double distancia = Math.sqrt((Math.pow(posX - actorX, 2)) + Math.pow(posY - actorY, 2));
				final float duracion = (float) distancia / ((casilla.GetAnchoCasilla() + casilla.GetAltoCasilla()) / 2);
				// System.out.println("MangoPaola Juega: " + pgnI+pgnF);
	
	
//				for (int i = 0; i <= 63; i++) {
//					
//					if (casillas[i].getPieza() != null) 
//						{
//						
//						Pieza pieza=casillas[i].getPieza();
//					System.out.println("Pieza : "+ pieza +"  Posicion : "+casillas[i].getPgn());
//						}
//		}
				
				
				//
				// saber la pieza que voy a
				// mover
				
				Pieza pieza = null;
				Casilla casillat = casillaOcupada(pgnI);
//				System.out.println(casillaOcupada(pgnI));
//				System.out.println(casillaOcupada(pgnF));
				if (casillat != null)
					{
						pieza = casillat.getPieza();
						if(flagSonido==true){
						sonido(pieza, duracion);
						}
					}
				else
				{
				System.out.println("no pude encontrar la pieza linea 1800");
				
				}
				// System.out.println("Mango paola Juega:" + pieza + " : " + pgnI +
				// " - " + pgnF);
				// System.out.println(tablero.Pgn2XY(pgnF)[0] + " - " +
				// tablero.Pgn2XY(pgnF)[1]);
				// EliminarPieza(pieza, pgnF);
				// txtArea.setText(pieza.toString()+" : "+ pgnI+" - "+pgnF);
				// guardarMovimientos = txtArea.getMessageText();
				pieza.move((tablero.Pgn2XY(pgnF)[0]), (tablero.Pgn2XY(pgnF)[1]));
				animacionMovimientoRealistas(pieza,pgnI, pgnF);
				Timer.schedule(new Task() {
					@Override
					public void run() {
						if(mostrarAlcanceOponente)
						{
							alcanceOponente(piezasExcluidas);
						}
					}
				}, 0.5f * duracion);
	
				if (pieza.getTipoPieza() == eTipoPieza.Rey) {
	
					((Rey) pieza).Validar(tablero.Pgn2XY(pgnF)[0], tablero.Pgn2XY(pgnF)[1], tablero.Pgn2XY(pgnI)[0],
							tablero.Pgn2XY(pgnI)[1], 0, 0, tablero.Pgn2XY(pgnF)[0], tablero.Pgn2XY(pgnF)[1]);
					// System.out.println("enrocado : " + ((Rey)
					// pieza).isEnrocado());
					// System.out.println("movido : "+((Rey)pieza).isMovido());
					if (((Rey) pieza).isEnrocado() == true) {
						verificarEnroque((Rey) pieza, pgnI, pgnF);
					}
					((Rey) pieza).setEnrocado(false);
	
				}
				
				if (pieza.getTipoPieza() == eTipoPieza.Peon) {
	
					/*
					 * if (varRes.length()> 4){ debo hacer primero la carga de fen
					 * para probar jugarpeonpromovido(pieza, pgnI , pgnF +
					 * varRes.substring(5,5));
					 * System.out.println("cPiezaPromovida: " + cPiezaPromovida Por
					 * jugandovsMangoPaola pa); }
					 */
					// System.out.println("es un peon " + tablero.Pgn2XY(pgnF)[1] +
					// " inicio: " + tablero.GetInicioY()
					// + " color: " + pieza.getColor());
	
					if (tablero.Pgn2XY(pgnF)[1] == tablero.GetFinY() && pieza.getColor().equals(Color.WHITE)
							|| (tablero.Pgn2XY(pgnF)[1] == tablero.GetInicioY() && pieza.getColor().equals(Color.BLACK))) {
						// System.out.println("va a promover");
						

						System.out.println("varres con substring : " + varRes.substring(9, 10));
						jugarPiezaPromovida(pieza, pgnI, pgnF + varRes.substring(9, 10));


						// System.out.println("fin promover");
						// pieza=casillaOcupada(pgnF);
						peonPromovido = true;
					}
	
					if (!peonPromovido) {
						jugarPeon((Peon) pieza, pgnI, pgnF, duracion);
					}
				} else {
					peonalpaso = null;
					pgnPeonalPaso = "-";					
//					EliminarPieza(pieza, pgnF);	
				}
				

				if (!peonPromovido) {

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
							if(pieza.getTipoPieza().equals(eTipoPieza.Rey)){
							animacionCapturando(pieza,TipoDeActor.CETRO,pgnI,pgnF,duracion);
							}
						else
						if(pieza.getTipoPieza().equals(eTipoPieza.Peon)){
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
				} else {
//					System.out.println("no seteo la casilla por que promovi un peon.. según...");
				}
				
				
				if (checkFlag.contains("+")) {
//					System.out.println("mostrar mensaje de jaque");
					mostrarDialogo(eTipoDialogo.Aceptar, false, eTipoMensaje.ReyenJaque);
					//casillaJaque= new ActorExtra(this, TipoDeActor.CUADROPOSIBLESCAPTURAS, 0);	
//					String pgnRey=casillaOcupadaRey();
//					System.out.println("pgn rey : "+pgnRey);
					//casillaJaque.setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
					//casillaJaque.setPosition(tablero.Pgn2XY(pgnRey)[0], tablero.Pgn2XY(pgnRey)[1]);
					
					//stage.addActor(casillaJaque);
					//casillaJaque.setZIndex(0);
				}
			}
				if(getTurno().equals(colorOponente)){
					System.out.println("conteoDePiezas(null, Color.WHITE):" +conteoDePiezas(null, Color.WHITE));
					if (conteoDePiezas(null, Color.WHITE)==0)// no hay piezas blancas
						bPerder=true;
				//	else{			

					System.out.println("ct16");
						CambiarTurno();
					//}
				}
			
				if (jaqueMate) { // cuando gana el motor
					// System.out.println("JAQUE MATEEEEEE")
					bPerder = true; //perder();													
				//	partidaFinalizada("Juego Finalizado", "MP");

					System.out.println("ct17");
					CambiarTurno();
				}								
			}			
		}
		else { // cuando gana el usuario			
				//bGanar = true; //ganar();
			//partidaFinalizada("Juego Finalizado", "Jugador");

				System.out.println("ct18");
				//CambiarTurno();
		}
		
		
	}


	/**
	 * arma el comandao de validacion de movimeintos para el motor
	 * @param pPgnI
	 * @param pPgnF
	 * @return
	 */
	private boolean validarMotor(String pPgnI, String pPgnF) {

	

		// String comando = "move " + pPgnI + pPgnF;
		comando = "MOV " + pPgnI + pPgnF;


		// resultadoValidarMotor(enviarMangoPaola(("?")));

		return resultadoValidarMotor(enviarMangoPaola(comando));
	}

	/**
	 * envía comandos al motor para su procesamiento
	 * @param pComando
	 * @return
	 */
	private ArrayList<String> enviarMangoPaola(String pComando) {

	
			String fen = null;
			try {
				
				ArrayList<String> res = conversacion.recibirComando(pComando, fen, valorDificultad);			
				return res;
			
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}

	/**
	 * evalua el resultado del uso del motor como validador del juego
	 * @param pRes
	 * @return
	 */
	private boolean resultadoValidarMotor(ArrayList<String> pRes) {
		// System.out.println("resultadoValidarMangoPaola in");
		try {
			// todo sólo para el desarrollo
			//
			// System.out.println("procesando item");
			for (String item : pRes) {
				//System.out.println("item" + item);
				if (item.contains("Illegal move")||item.contains("COD")){
					return false;
				}
				
				if (item.contains("OK")){
					reyJaque="Ok";
				}
							
				
				
				if (item.contains("+OK"))
				{visto=true;
				 reyJaque="+Ok";
				 if(permitirJaque==true){
					 bPerder = true; //perder();

						System.out.println("ct19");
					CambiarTurno();
				 }		
				}
				
				
				if (item.contains("OK #")||item.contains("White mates")){
					bGanar = true; //ganar();
				}
				if (item.contains("FIN OK")&& !Xmm.contains("MJN 04") ){
					bGanar = true; //ganar();
				}
				
				if (item.contains("Black mates") || item.contains("White mates")){
						jaqueMate = true;
				}
				else if (item.contains("Draw") || item.contains("Draw by repetition")) {
						// System.out.println("tablas");
//						partidaFinalizada("Tablas", "MP");
						bTablas = true;

						System.out.println("ct20");
						CambiarTurno();
					}
			}
			
		} catch (Exception e) {
			// System.out.println("resultado Mango Paogla exception " +
			// e.getMessage());
		}
		return true;

	}

	/**
	 * evalua el resultado del último comando envíado al motor
	 * @param pRes
	 * @return
	 */
	private String resultadoJugarMangoPaola(ArrayList<String> pRes) {
		String aux = null;

		if (pRes != null)
			for (String item : pRes) {

//				 System.out.println("pRes:" + pRes);
				if (item.contains("Black mates") || item.contains("White mates")) {
					// System.out.println("Mate");
					jaqueMate = true;
				} else if (item.contains("FEN")) {
					// System.out.println("Hayamos el FENNNNNNNNNNNn");
					// System.out.println(pRes);
					// System.out.println((item));
					return pRes.get(1);
				}
				else if (item.contains("COD903")||item.contains("COD900")||item.contains("COD912")) {				
					return pRes.get(0);
				}

				else {
					if (item.contains("Draw")|| item.contains("Draw by repetition")) {
						// System.out.println("tablas");
						jaqueMate = true;
					} else {
						if (item.contains("COD")){
							//txcargaFen = "";
							//game.setScreen(game.miniJuegoGeneral);
						}						
						else{
						aux = item;
						}
					}
				}

				
				// System.out.println("resultadoJugarMangoPaola: " + aux);

			}

		return aux;
	}

	/*
	 * private String FenInicial() { return
	 * "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq  0 1"; }
	 */

	private void verificarEnroque(Rey pPieza, String pPgnI, String pPgnF) {

		Pieza vPieza = (Pieza) pPieza.getTorreMovida()[0];
		String vPgn = (String) pPieza.getTorreMovida()[1];

		if (pPieza.isEnrocado() == true) {

			// System.out.println("entre en pieza != null");
			setCasilla(vPgn, vPieza, vPieza.getInd());
		}
		else {
//			System.out.println("Error: Explote");
		}
	}

	private boolean jugar() {
if (Xmm.contains("MJN 20"))
{
		int i = 0;

		for (Actor miActor : stage.getActors()) {
					i = miActor.getActions().size + i;
					if (i > 0) {
						return false;
					}
		}
		
		return true;
}
else{
		    int		i=0;
			for (Actor miActor : stage.getActors()) {
				if(miActor.getName()!=null && !miActor.getName().equals("reloj")){
					try {
						Pieza pieza = (Pieza) miActor;		
						//System.out.println("actor ocupado: "+ miActor.getName());
						
						if (!pieza.getEstadoPieza().equals(eEstadoPieza.Esperando)){
							
							i++;
							//System.out.println("actor ocupado: "+ miActor.getName());
							
							return false;
							
						}
					} catch (Exception e) {
						// TODO: handle exception
					}								
				}
			}
		if (i > 0) {
			return false;
		}
		//System.out.println("no hay actores ocupados " );
		return true;
}
}

	/**
	 * metodo para realizar las posibles jugadas del peón
	 * 
	 * @param pPeon
	 *            pieza que se está moviendo
	 * @param pActorX
	 *            coordenada x donde se encuentra el peón
	 * @param pActorY
	 *            coordenada y donde se encuenta el peón
	 * @param pPosX
	 *            coordenada x donde se desea mover el peón
	 * @param pPosY
	 */

	private void jugarPeon(Peon pPeon, String pPgni, String pPgnf, float pDuracion) {

		
//		if(Xmm.contains("MJN 21")){
//			bGanar = true; //ganar();
//			CambiarTurno();
//		}
		movimientosMedios = 0;
		if(Xmm.contains("MJN 22"))
		{
			if(!pPeon.getColor().equals(Color.BLACK))
				{
					jugadasPeon++;
				}
		}
		
		if (casillaOcupada(pPgnf) != null) {
			
			animacionCapturando(pPeon,TipoDeActor.MACHETE, pPgni, pPgnf, pDuracion);
							
		} else if (peonalpaso != null) {
			// System.out.println("peon al paso != null");
			String pgnpp = casillaOcupada(peonalpaso).toUpperCase();
			// System.out.println("Captura de peon al paso inicio" + pPgni + "
			// fin: " + pPgnf + "peonalpaso:" + pgnpp);

			if (Math.abs(pPgni.charAt(0) - pgnpp.charAt(0)) == 1 // peon al paso
																	// al lado
																	// del peon
																	// movido
					&& pPgnf.charAt(0) - pgnpp.charAt(0) == 0 && Math.abs(pPgnf.charAt(1) - pgnpp.charAt(1)) == 1) {

				EliminarPieza(pPeon, casillaOcupada(peonalpaso));
				contadorPeonAlPaso++;
												
				if(Xmm.contains("MJN 21")){
					if(contadorPeonAlPaso>=4){
						flagPeonAlPaso=true;
						rotateAction.finish();					
						bGanar = true; //ganar();						
												
					}														
				}
			}
		}

		// System.out.println("asignando de peon al paso "+ "pPosX" + pPosX + "
		// Math.abs(pActorX-pPosX): "+ Math.abs(pActorX-pPosX) + " anchocasilla:
		// " + casilla.GetAnchoCasilla() + " altoCasilla:" +
		// casilla.GetAltoCasilla());
		// System.out.println((int) pPgni.charAt(1) + " - " +
		// Math.abs(pPgnf.charAt(1)));

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
	 * muestra un dialogo para escoger la pieza a ser promovida por el peón
	 * 
	 * @param pPieza
	 *            es el peón a ser promovido
	 * @param pPgni
	 *            posición inicial del peón
	 * @param pPgnf
	 *            posición final del peón
	 */
	public void EscogerPromovida(final Pieza pPieza, final String pPgni, final String pPgnf) {
//		System.out.println("escoger promovida in");
		//ArrayList<String> mensajes =  new ArrayList<String>();
		//String mensajeDialogo = "Seleccione una de las piezas";
		Texture[] TexturasPromocion = new Texture[4];

	
		TexturasPromocion[0] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/dama/DamaG.png");
		TexturasPromocion[1] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/torre/TorreG.png");
		TexturasPromocion[2] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/AlfilG.png");
		TexturasPromocion[3] = game.getManager().get("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoG.png");
		
//		System.out.println("escoger promovida 1");
		
		
		final Dialogo dialogo = new Dialogo(TexturasPromocion);

		dialogo.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
//				System.out.println("valor 1");
				if (dialogo.getResult() != null) {
//					System.out.println("valor 2");
					if (dialogo.getResult().equals(true)) {
//						System.out.println("valor 3");

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
//						System.out.println("removiendo el dialogo de promocion");
						dialogo.remove();
					System.out.println("valor escoger promovida1 " + Integer.parseInt(dialogo.getValue().toString()));
						jugarPiezaPromovida(pPieza, pPgni, pPgnf + cPiezaPromovida);
//						System.out.println("cPiezaPromovida: " + cPiezaPromovida);															
			}
				}
				}
		});
//		System.out.println("escoger promovida out");
		dialogo.setWidth(360);
		dialogo.setHeight(400);
		dialogo.align(Align.center | Align.top);
		stage.addActor(dialogo);
		dialogo.setPosition(super.gameWidth / 2 - dialogo.getWidth() / 2,
				super.gameHeight / 2 - dialogo.getHeight() / 2);

	}
	/**
	 * Ejecuta el cambio de pieza de peon por la promovida y la coloca en la posicion de promoción
	 * @param pPieza
	 * @param pPgni
	 * @param pPgnf
	 */
	private void jugarPiezaPromovida(Pieza pPieza, String pPgni, String pPgnf) {
		if (!pPieza.getColor().equals(colorOponente) || !jugandovsMangoPaola) {
			if (validarMotor(pPgni, pPgnf) == false) {
//				System.out.println("Error, no se puede jugar:" + pPieza.getColor());
			} else {
				System.out.println("jugarPiezaPromovida turno mangoPaola");
				EliminarPieza(pPieza, pPgnf);
				setCasilla(pPgnf, pPieza, pPieza.getInd());

				if (pPieza.getTipoPieza() == eTipoPieza.Peon) {
					setPiezaPromovida(pPieza, pPgni, pPgnf);

				}
				cPiezaPromovida = null;

				System.out.println("ct21");
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
	 * crea un fen con las piezas del tablero y el estado del juego
	 * @return
	 */
	private String obtenerFen() {
		Fen fen = new Fen(this, 0);
//		int i = 0;
//
//		for (String item : jugadas) {
//			i++;
//			// System.out.println(i + "-" + item);
//
//		}

		return fen.obtenerFen(casillas, getTurno(), 64, 64, 8, pgnPeonalPaso, movimientosMedios, movimientosCompletos);

	}


	@Override
	public void pause() {
		System.out.println("pausandooooooo");
		flagSonido=false;
		
		if (reyMovimiento != null) {
			reyMovimiento.pause();
		}
		
		if(tiempo && reloj!=null){
		
			pausarReloj();
			flagPauseResume=false;
		}
		else{
			flagPauseResume=true;
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
		if(flagPauseResume==false)
			{
				if(tiempo && reloj!=null)
					{
						reanudarReloj();
						
					}
			}
		if (videoScreen==null){
		flagSonido=true;
		Preferencias(true);
		}
		super.resume();
	}

	/**
	 * Procedimiento para actualizar una partida donde se gano el minijuego
	 * 
	 * @return Devuelver verdadero si actualizo sin error o falso en caso
	 *         contrario
	 * @author yisheng
	 */
	private Boolean actualizarPartida() {
		//Partida partida = new Partida();
		
		int nroMinijuego = Integer.parseInt(minijuego.substring(9));
		int nroBatalla = Integer.parseInt(batalla.substring(7));
		if (ultimoMinBatalla) {
			nroBatalla++;
			nroMinijuego++;
		} else {
			nroMinijuego++;
		}

		try {

			int batallaPartida = Integer.parseInt(partida.getBatalla().substring(7));
			int minijuegoPartida = Integer.parseInt(partida.getMinijuego().substring(9));
			partida.setMonedas(String.valueOf(moneda));
			//Partida.ActualizarAtributo(nombrePartida, "Monedas", String.valueOf(moneda));
			// (nombrePartida, "Minijuego", "Moneda" + moneda);

			if (batallaPartida < nroBatalla) {
				partida.setBatalla("Batalla" + nroBatalla);
				partida.setMinijuego("Minijuego" + nroMinijuego);
			} else {
				if (batallaPartida == nroBatalla) {
					if (minijuegoPartida < nroMinijuego) {
						partida.setMinijuego("Minijuego" + nroMinijuego);
						partida.setFen("");

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
	 * devuelve el tablero que es usado por este screen  
	 * Metodo para obtener el tablero actual
	 */

	@Override
	public Tablero getTablero() {
		return tablero;
	}

	/**
	 * deshace las últimas dos jugadas
	 */
//	public void deshacer() {
//
//		cPiezaPromovida = null;
//		
//		if (listaCasillasDeshacer.size() > 0) {
//
//			for (int i = 0; i <= 31; i++) {
//
//				try {
//					if (!casillas[i].getPieza().equals(casillasDeshacer[i].getPieza())) {
////						System.out.println("        casilla:" + casillas[i].getPieza() + casillas[i].getPieza().getInd()
////								+ " pgn: " + casillas[i].getPgn());
////						System.out.println("casillaDeshacer:" + casillasDeshacer[i].getPieza()
////								+ casillasDeshacer[i].getPieza().getInd() + " pgn: " + casillasDeshacer[i].getPgn());
//					}
//				} catch (Exception e) {
//					System.out.println("Error: " + e);
//				}
//			}
//
//			for (Actor vActor : stage.getActors()) {
//				try {
//					if (((Pieza) vActor).getInd() >= 0) {
//						vActor.setVisible(false);
//						vActor.setTouchable(Touchable.disabled);
//					}
//				} catch (Exception e) {
//
//				}
//			}
//
//			for (int i = 0; i <= 31; i++) {
//				casillas[i] = listaCasillasDeshacer.get(listaCasillasDeshacer.size() - 1)[i];
//				try {
//					Pieza vPieza = casillas[i].getPieza();
//					if (vPieza != null) {
//						vPieza.setVisible(true);
//						vPieza.setInd(i);
//						vPieza.setPosPgn(casillas[i].getPgn());
//
//						// if (Turno.equals(colorActivas)){
//						vPieza.setTouchable(Touchable.enabled);
//						casillas[i].setPieza(vPieza);
//						// }
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//
//			}
//			listaCasillasDeshacer.remove(listaCasillasDeshacer.size() - 1);
//			jugadas.remove(jugadas.size() - 1);
//			jugadas.remove(jugadas.size() - 1);
//			CambiarTurno();
//
//			cambiarEstadoEsperando();
//
//			if (Turno.equals(colorOponente))
//				CambiarTurno();
//
//			if (colorOponente.equals(Color.BLACK))
//				habilitarPiezas(eColores.Blancas);
//			else
//				habilitarPiezas(eColores.Negras);
//
//			enviarMangoPaola("remove");
//
//			// System.out.println("fen despues de remover: " + obtenerFen());
//		} else {
//			// TODO QUE ES ESTO VACIO...
//		}
//		//
//	}
//
	/**
	 * Muestra en un cuadro de dialogo el video de ayuda para el minijuego
	 */
	private void cargarayuda() {
		try{
			System.out.println("cargando ayuda");
			super.playingMusic();
			cortinaFondo.pause();
			if (!videoAyuda.equals("0")){
				System.out.println("video de Ayuda:" + videoAyuda);
				cargarVideoInformativo(eTipoVideo.Ayuda);
			}
			else
				System.out.println("Error, no se ha configurado el video de Ayuda para este minijuego");
		}
		catch(Exception e){
			System.out.println("Error cargando el video: " + videoAyuda);
//			reanudarReloj();
		}	
		
	}

	/**
	 * Muestra en un cuadro de dialogo el video de ayuda para el minijuego
	 */
	private void cargarhistoria() {
		try{	
			if (!videoHistoria.equals("0"))
			{
				cortinaFondo.stop();
				cargarvideo(videoHistoria, " Ayuda ", eTipoVideo.Historia);
			}
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
				{
				cortinaFondo.stop();
				
				cargarvideo(videoBiografia, " Ayuda ", eTipoVideo.Biografia);
				}
			else
				System.out.println("Error, no se ha configurado el video de Biografía para este minijuego");
		}
		catch(Exception e){
			System.out.println("Error cargando el video: " + videoHistoria);	
			}
		}

	
	/**
	 * Muestra en un cuadro de dialogo el video de derrota o victoria para el minijuego
	 */
	private boolean cargarVideoInformativo(eTipoVideo pTipoVideo) {
System.out.println("cargando video informativo:"+pTipoVideo.toString());
		String vPath = "";
		//eTipoMensaje vTipoMensaje = null;
		switch (pTipoVideo) {
		case Victoria:
			System.out.println("Victoria!!!");
			
			vPath = "assets/video/" + batalla +  "/" + minijuego + "v.ogv";
		//	vTipoMensaje= eTipoMensaje.JuegoFinalizadoVictoria;
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
			System.out.println("video: " + vPath);
			
			cargarvideo(vPath, "       ", pTipoVideo);
			return true;
		}
		catch(Exception e){
			System.out.println("Error, no se puede cargar el video: " + vPath);
			iniciarMotor();
			return false;
		}
	}

	/**
	 * Muestra en un cuadro de dialogo un video
	 */
	private void cargarvideo(String video, String mensaje,eTipoVideo tipoVideo) {
		System.out.println("cargarvideo " + tipoVideo.toString());
		motorIniciado=false;
//		if(cortinaFondo!=null){
//		cortinaFondo.pause();
//		}
		if(reloj!=null&&tiempo&&flagReloj==false){
			pausarReloj();
		}
		
		if (stage.getActors().contains(videoScreen, true)) {
			System.out.println("existe un videoscreen");
			
			videoScreen.setAtlas(null);
			videoScreen.dispose();
			videoScreen.remove();
			videoScreen=null;
			System.gc();
		
		}
			//System.out.println("agregando el contenedor: " + video);
			//String[] sino  = new String[2];
		
			videoScreen = new Dialogo(); // TODO wilmer
			videoScreen.create(video, tipoVideo, false);
			//videoScreen = Dialogo.getInstance(video, tipoVideo);
			videoScreen.setWidth(800);
			videoScreen.setHeight(500);
			videoScreen.setPosition(25, 25);
			System.out.println("cargando dialogo de video");
			stage.addActor(videoScreen);
			System.out.println("cargó dialogo de video");
			
			final eTipoVideo vTipoVideo = tipoVideo;
			if (cortinaFondo != null)
				cortinaFondo.stop();
		
			videoScreen.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {				
					System.out.println("change listener");
					stage.removeListener(this);					
					procesarVideoListener(videoScreen.getResult(), vTipoVideo);
					motorIniciado =true;
					
				}
			});
				
			videoScreen.addListener(new InputListener(){				
				@Override
				public boolean keyDown( InputEvent event, int keyCode){	   			
	   			System.out.println("KeyCode" + keyCode);
	   			if (keyCode  == Keys.ENTER || keyCode  == Keys.ESCAPE)
					stage.removeListener(this);
//				if (primeraPartida)	
	   			procesarVideoListener(videoScreen.getResult(), vTipoVideo);
					motorIniciado =true;
					return false;
	   			}
			}); 
			System.out.println("salgo de cargar ayuda()");
	}
	
	public void procesarVideoListener(Object pResult, eTipoVideo pTipoVideo){
		try {
			System.out.println("videoScreen.getResult(): " + pResult);
			if (pResult.equals(true)) {
				if (pTipoVideo.equals(eTipoVideo.Victoria)){
					actualizarPartida();
					if(ultimoMinBatalla){
					
						videoScreen.setAtlas(null);
						videoScreen.remove();
						videoScreen.dispose();
						videoScreen=null;
						System.gc();
						
						
//						if (mapGen!=null)
//						{
//							mapGen.dispose();
//							mapGen=null;
//							System.gc();
//							System.out.println("entre a limpiar");
//						}
//						mapGen = new mapaGeneralScreen(game,"batalla0");
//						try {
//							mapGen.asignarPartida(partida,dificultad,configuracion, true
//									);
//							recargaScreen=false;
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
						
						game.mapGen.asignarPartida(partida,dificultad,configuracion,false);
						game.setScreen(game.mapGen);
						
						dispose();
						System.gc();
					}
					else{
						
						videoScreen.setAtlas(null);
						videoScreen.remove();
						videoScreen.dispose();
						videoScreen=null;
						System.gc();
						game.mapMin.asignarPartida(partida, batalla ,dificultad,configuracion,true);
						inicializar();
						System.gc();
						recargaScreen=false;
					    game.setScreen(game.mapMin);
//					    dispose();
					//dispose();
					}
					
				}
				else if(pTipoVideo.equals(eTipoVideo.Derrota))
					reiniciar();													
				else if (pTipoVideo.equals(eTipoVideo.Ayuda))				
					cargarayuda();
				else if(pTipoVideo.equals(eTipoVideo.Historia))				
					cargarhistoria();									
				else if(pTipoVideo.equals(eTipoVideo.Biografia))
					cargarBiografia();
				
			} else if (videoScreen.getResult().equals(false)){
				if (!pTipoVideo.equals(eTipoVideo.Ayuda)&& !pTipoVideo.equals(eTipoVideo.Biografia)&&!pTipoVideo.equals(eTipoVideo.Historia)){
					
						recargaScreen=false;
						game.setScreen(game.mapMin);
						cortinaFondo.pause();
					
						videoScreen.setAtlas(null);
						videoScreen.remove();
						videoScreen.dispose();
						videoScreen=null;
						System.gc();
						dispose();
				}
				else{
					if (tocarMusica){
					super.playingMusic();				
//						cortinaFondo.play();
						cortinaFondo.setVolume(volume);
					}
					if(tiempo&&reloj!=null){
						reanudarReloj();
					}
					
					videoScreen.setAtlas(null);
					videoScreen.remove();
					videoScreen.dispose();
					videoScreen=null;
					System.gc();
					if (pTipoVideo.equals(eTipoVideo.Ayuda) && !conversacion.isArriba()){						
						iniciarMotor();					
					}
					
				}				
			}
			System.out.println("empiezo a render");
			//Gdx.graphics.setContinuousRendering(true);
		} catch (Exception e) {
			//Gdx.graphics.setContinuousRendering(true);
			System.out.println("Error: " + e.getMessage() + " - " + e.getCause());
			// TODO: handle exception
		}
		
	}

	/**
	 * Metodo para que se emitan sonidos segun la pieza jugada
	 * 
	 * @param pieza
	 *            la cual se quiere emitir sonido
	 * @param duracion
	 *            duracion del sonido
	 */
	public void sonido(Pieza pieza, float duracion) {
		
//		System.out.println("tocando el sonido de la pieza" + pieza.getName() + pieza.getColor());
		if (tocarSonido) {
			if (!jugandovsMangoPaola) {
				if (Turno.equals(colorOponente)) {
					Timer.schedule(new Task() {
						@Override
						public void run() {
						}
					}, 0.5f * duracion);
				}
				else {
					Timer.schedule(new Task() {

						@Override
						public void run() {
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
	 * cambia el estado de las pieza a esperando
	 */

	private void cambiarEstadoEsperando() {
		for(int j=0;j<64;j++){
			if(casillas[j]!=null && casillas[j].getPieza()!=null && casillas[j].getPieza().getColor().equals(Color.WHITE)){
				Pieza pPieza=casillas[j].getPieza();
				pPieza.setEstadoPieza(eEstadoPieza.Esperando);
				pPieza.cambiarEstadoPieza(eEstadoPieza.Esperando);			
			}
	}
		
		
		
		
//		for (Actor miActor : stage.getActors()) {
//
//			if (miActor.getName() != null && !miActor.getName().equals("reloj")) {
//				try {
//					((Pieza) miActor).cambiarEstadoPieza(eEstadoPieza.Esperando);
//					
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
//
//			}
//		}

	}

	/**
	 * carga el fen en el tablero
	 */
	private void CargarFen() {
		casillas = new Casilla[64];
		// stage.dispose();

//System.out.println(txcargaFen);
		Fen vFen = new Fen(this, 0);

		if (txcargaFen == null) {
			vStringFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";

		} else {
			vStringFen = txcargaFen;

		}
		String vTurno[] = null;
		
		if (Xmm.contains("MJN 23")) { //cargar las torres de enroque
			vTurno = vFen.Fen2Pgn(vStringFen, 32).split("/");
			if (vTurno[2].contains("k")) {
				Torre vTorre = (Torre) casillaOcupada("H8").getPieza();
				vTorre.setMovida(false);
				((Rey) casillaOcupada("E8").getPieza()).setTorreReyNe(vTorre);
			}
			if (vTurno[2].contains("q")) {
				Torre vTorre = (Torre) casillaOcupada("A8").getPieza();
				vTorre.setMovida(false);
				((Rey) casillaOcupada("E8").getPieza()).setTorreReinaNe(vTorre);
			}
			if (vTurno[2].contains("K")) {
				Torre vTorre = (Torre) casillaOcupada("H1").getPieza();
				vTorre.setMovida(false);
				((Rey) casillaOcupada("E1").getPieza()).setTorreRey(vTorre);

			}
			if (vTurno[2].contains("Q")) {
				Torre vTorre = (Torre) casillaOcupada("A1").getPieza();
				vTorre.setMovida(false);
				((Rey) casillaOcupada("E1").getPieza()).setTorreReina(vTorre);
			}

		}
		else {
			vTurno = vFen.Fen2PgnMiniJuego(vStringFen, 32).split("/");
			// }

		}
		

		for (int i = 0; i <= 63; i++) {
			try {
				if (casillas[i].getPieza() != null) {

					stage.addActor(casillas[i].getPieza());

				}

			} catch (Exception e) {
				// TODO: handle exception

				casillas[i] = new Casilla();
			}
			try {
				if (casillas[i].getActor() != null) {

					stage.addActor(casillas[i].getActor());

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
System.out.println("voy a cargar botones y referencias");
		referencias();
		botones();
	//	for (String turno : vTurno)
//			System.out.println("vturno :" + turno);

		if (vTurno[1].equals("w")) {
			setTurno(Color.WHITE);
			habilitarPiezas(eColores.Blancas);

			if (jugandovsMangoPaola && colorOponente.equals(Color.WHITE)) {
				
			}
		} else {
			setTurno(Color.BLACK);
			habilitarPiezas(eColores.Negras);
		}
		int k = 0;
		if (vTurno.length == 6)
			k = 2;
		else
			k = 1;

		if (Xmm.equals("MJN 23")){
			if (vTurno[k].contains("k")) {
				Torre vTorre = (Torre) casillaOcupada("H8").getPieza();
				vTorre.setMovida(false);
				((Rey) casillaOcupada("E8").getPieza()).setTorreRey(vTorre);
			}
			if (vTurno[k].contains("q")) {
				Torre vTorre = (Torre) casillaOcupada("A8").getPieza();
				vTorre.setMovida(false);
				((Rey) casillaOcupada("E8").getPieza()).setTorreReinaNe(vTorre);
	
			}
			if (vTurno[k].contains("K")) {
				Torre vTorre = (Torre) casillaOcupada("H1").getPieza();
				vTorre.setMovida(false);
				((Rey) casillaOcupada("E1").getPieza()).setTorreRey(vTorre);
	
			}
			if (vTurno[k].contains("Q")) {
				Torre vTorre = (Torre) casillaOcupada("A1").getPieza();
				vTorre.setMovida(false);
				((Rey) casillaOcupada("E1").getPieza()).setTorreReina(vTorre);
			}
		}
	}

	/**
	 * dibuja los numeros y letras de referencias del tablero
	 */
	private void referencias() {
		for (int i = 0; i <= 7; i++) {
			
			letras[i] = new Label((Character.toString((char) (65 + i)).toLowerCase()), skin2);
			numeros[i] = new Label(Character.toString((char) (49 + i)), skin2);
		}

		int vDistaciaLetrasX = (int) (casilla.GetAnchoCasilla() * 2.4);
		int vDistaciaLetrasY = (int) (casilla.GetAltoCasilla() * 0.325);

		if (colorOponente.equals(Color.WHITE)) {
			tablero.setTableroRotado(true);

			for (int i = 0; i <= 7; i++) {
				letras[i].setPosition((vDistaciaLetrasX - 75) + ((7 - i) * casilla.GetAnchoCasilla()), 5);
				numeros[i].setPosition(12, (vDistaciaLetrasX - 100) + ((7 - i) * casilla.GetAltoCasilla()));

				letras[i].setFontScale(vDistaciaLetrasY / 10);
				numeros[i].setFontScale(vDistaciaLetrasY / 10);
			}
		} else {
			tablero.setTableroRotado(false);
			for (int i = 0; i <= 7; i++) {
				letras[i].setPosition((vDistaciaLetrasX - 75) + i * casilla.GetAnchoCasilla(), 5);
				letras[i].setFontScale(vDistaciaLetrasY / 10);
				numeros[i].setPosition(12, (vDistaciaLetrasX - 100) + i * casilla.GetAnchoCasilla());
				numeros[i].setFontScale(vDistaciaLetrasY / 10);
			}

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
 * Metodo que genera y muestra los botones  
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
		
		ordenarPiezas.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
//				ordenarPiezas();
//				count++;

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
				txcargaFen = null;
				contadorPromovidas=0;
				contadorJugadas=0;
				contadorCapturadas=0;
				contadorMovIncorrectos=0;
				contadorDobleAtaque=0;
				contadorPeonAlPaso=0;
				jugadasPeon=0;
				if (bolsasCapturadas>0)
				{
					try {
						gastarMonedas(bolsasCapturadas*cantidadMonedas);
						bolsasCapturadas=0;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				recargaScreen=false;
				game.setScreen(game.mapMin);

			}
		});
		
		reiniciar.addCaptureListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
			System.out.println("reinicindo");	
		reiniciar();
		
			}
		});
		
		ayuda.addCaptureListener(new ChangeListener() {

			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				cargarayuda();
			}
		});

		
		guardar.addCaptureListener(new ChangeListener(){
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				// System.out.println("boton guardar");
				guardarMinijuego();
			}
		});
		
		salir.setSize(70,70);
		reiniciar.setSize(70,70);
		Opcion.setSize(70,70);
		ordenarPiezas.setSize(70,70);
		ayuda.setSize(70,70);
		guardar.setSize(70,70);
		
//		salir.setSize(90,90);
//		reiniciar.setSize(90,90);
//		Opcion.setSize(90,90);
//		ordenarPiezas.setSize(90,90);
//		ayuda.setSize(90,90);
//		guardar.setSize(90,90);
		
	 //  ayuda2.setPosition(720,300);
	
		
//	     guardar.setPosition(10,495);
//	      Opcion.setPosition(100,495);
//	       ayuda.setPosition(190,495);
//   ordenarPiezas.setPosition(568,495);
//	   reiniciar.setPosition(658,495);
//	       salir.setPosition(748,495);
//	   

	  	     Opcion.setPosition(780,420);
	          ayuda.setPosition(780,340);
	  	  reiniciar.setPosition(780,260);
		      salir.setPosition(780,180);
		
	      
		      if(Gdx.graphics.isFullscreen()){
		    	
				  Opcion.setVisible(false);
				  ayuda.setVisible(false);
				
				  reiniciar.setVisible(false);
				  salir.setVisible(false);
		        }
	      
		
		reiniciar.setTouchable(Touchable.enabled);
		salir.setTouchable(Touchable.enabled);

//		stage.addActor(ordenarPiezas);
		stage.addActor(salir);
		stage.addActor(reiniciar);
		stage.addActor(Opcion);
//		stage.addActor(guardar);
		stage.addActor(ayuda);
		//stage.addActor(ayuda2);
		System.out.println("añadi botones las stage");
	}
	/**
	 * Mostrar los botones cuando el mouse se encuentra en un rango X a la posicion de los botones
	 
	private void mostrarBotones(){
		float duracion=0.3f; 
	          
		       Opcion.addAction(Actions.moveTo(780,420, duracion));
		        ayuda.addAction(Actions.moveTo(780,340,duracion));
	        reiniciar.addAction(Actions.moveTo(780,260,duracion));
		        salir.addAction(Actions.moveTo(780,180,duracion));
		        
		        if(Gdx.graphics.isFullscreen())
		        {		        
					 
				       Opcion.setVisible(true);
				        ayuda.setVisible(true);
				    reiniciar.setVisible(true);
				        salir.setVisible(true);						        
		        }		        		
	}
	*/
/**
 * ocultar los botones cuando el mouse se encuentra en un rango X diferente a la posicion de los botones
 
private void ocultarBotones(){
		float duracion=0.3f; 
		
		
       Opcion.addAction(Actions.moveTo(850,420, duracion));
        ayuda.addAction(Actions.moveTo(850,340,duracion));
    reiniciar.addAction(Actions.moveTo(850,260,duracion));
        salir.addAction(Actions.moveTo(850,180, duracion));

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
*/		
	/**
	 * Guarda la partida en el archivo, guarda el fen generado hasta el momento
	 * en el minijuego *
	 * 
	 * @return Devuelver verdadero si actualizo sin error o falso en caso
	 *         contrario
	 * @author yisheng
	 */
	private boolean guardarMinijuego() {
//		System.out.println("guardando");
		String minijuegoPartida;
		try {
			String batallaPartida = partida.getBatalla();

			minijuegoPartida = partida.getMinijuego();

			if ((minijuego.equals(minijuegoPartida)) && (batalla.equals(batallaPartida))) {
				// System.out.println("guardando fen");
				partida.setFen( obtenerFen());
//				System.out.println(obtenerFen());
			}
			partida.setMonedas(String.valueOf(moneda));
			partida.ActualizarPartida();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		// System.out.println(width / 2 + " - " + height / 2);
		// System.out.println(super.getGameWidth() / 2 + " - " +
		// super.getGameHeight() / 2);
		camera.position.x = super.getGameWidth() / 2;
		camera.position.y = super.getGameHeight() / 2;
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
//						pasoPreferencia= true;
						Preferencias(true);
						
//						
						stage.removeListener(this);
						//opcionesScreen.dispose();
						
					}
					setVolumen();
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
				if(Xmm.contains("MJN 01"))
					{
						txcargaFen="";
					}
				else
					{
						txcargaFen = obtenerFen();
					}
				mostrarVideos = false;
				recargaScreen = true;
				System.out.println("game.miniJuegoGeneral 1");
				reiniciar();
				//cambiarCamino=0;				
			}

		} else {
			Gdx.graphics.setWindowedMode(w, h);
			if (!pCarga) {
				if(Xmm.contains("MJN 01"))
				{
					txcargaFen="";
				}
			else
				{
					txcargaFen = obtenerFen();
				}
				mostrarVideos = false;
				recargaScreen = true;
				System.out.println("game.miniJuegoGeneral 2");
				reiniciar();				
			}
		}

		if (preferencias.getPreferencia("musica")) {
			tocarMusica = true;

			if(!cortinaFondo.isPlaying()){
					if (videoScreen!=null){
						if (!videoScreen.isPlaying()){
//							cortinaFondo.play();
//							cortinaFondo.setLooping(true);
//							cortinaFondo.setVolume(volume);
						}
					}
					else {
//						cortinaFondo.play();
						cortinaFondo.setLooping(true);
						cortinaFondo.setVolume(volume);
					}
			}
		} else{
			
			tocarMusica = false;

			if(cortinaFondo.isPlaying())
				cortinaFondo.stop();

		}
		if (preferencias.getPreferencia("sonido")) {			
			tocarSonido = true;
		} else {
			tocarSonido = false;
		}

	}	

	/**
	 * 
	 * @param pTipoDialogo: tipo de dialogo a mostrar
	 * @param pMate : si el mensaje es de jaque mate o finalizacion de juego
	 * @param pMensaje : mensaje a mostrar en caso de que no haya uno predeterminado
	 */
	private void mostrarDialogo(eTipoDialogo pTipoDialogo, boolean pMate,  eTipoMensaje pTipoMensaje) {
				
		
		
		if (pTipoDialogo == eTipoDialogo.Mensaje){
			if (pTipoMensaje.equals(eTipoMensaje.JuegoFinalizadoDerrota)){		
				opcionesScreen = new Dialogo("Informacion ", new Texture(new FileHandle("assets/mensajes/mensaje 6.png")),"");
			}	
			else  if (pTipoMensaje.equals(eTipoMensaje.EnroqueVictoriaParcial)){					
				opcionesScreen = new Dialogo("Informacion ", new Texture(new FileHandle("assets/mensajes/mensaje 3.png")),"",false);
			}else if (pTipoMensaje.equals(eTipoMensaje.EnroqueDerrotaParcial)){					
					opcionesScreen = new Dialogo("Informacion ", new Texture(new FileHandle("assets/mensajes/mensaje 2.png")),"", false);
				}
			else  if (pTipoMensaje.equals(eTipoMensaje.JuegoFinalizadoTablas)){
				opcionesScreen = new Dialogo("Informacion ", new Texture(new FileHandle("assets/mensajes/mensaje 15.png")),"");
			}
		}
		else{
			if(pTipoDialogo == eTipoDialogo.Aceptar){
				if (pTipoMensaje.equals(eTipoMensaje.JuegoFinalizadoVictoria)){  						
					opcionesScreen = new Dialogo("Informacion ", new Texture(new FileHandle("assets/mensajes/mensaje 7.png")));
				}
				else  if (pTipoMensaje.equals(eTipoMensaje.ReyenJaque)){					
					opcionesScreen = new Dialogo("Informacion ", new Texture(new FileHandle("assets/mensajes/mensaje 8.png")));
				}
				else  if (pTipoMensaje.equals(eTipoMensaje.Capturar5Bolsas)){					
					opcionesScreen = new Dialogo("Informacion ", new Texture(new FileHandle("assets/mensajes/mensaje 12.png")));
				}							
				else if  (pTipoMensaje.equals(eTipoMensaje.MovimientoNoPermitido)){
					opcionesScreen = new Dialogo("Informacion ", new Texture(new FileHandle("assets/mensajes/mensaje 5.png")));
					
				}
			}
		}
			opcionesScreen.setModal(true);
			opcionesScreen.align(Align.center | Align.top);
			stage.addActor(opcionesScreen);
			opcionesScreen.setPosition(super.gameWidth / 2 - opcionesScreen.getWidth() / 2,
			super.gameHeight - opcionesScreen.getHeight() / 2);
//System.out.println("pmate"+pMate);
			if(pMate){	
				
				opcionesScreen.addListener(new ChangeListener() {
					@Override
					public void changed(ChangeEvent event, Actor actor) {
	
						try {
						
							if (opcionesScreen.getResult() != null) {
//								System.out.println("opcionesScreen.getValue()" + opcionesScreen.getValue());
								procesarResultadoDialogo(opcionesScreen.getValue());
							}
						} catch (Exception e) {
								System.out.println("Error" + e.getMessage());
								procesarResultadoDialogo(0);
							}
						}								
				});
			
			
				opcionesScreen.addListener(new InputListener(){				
		   		@Override
		   		public boolean keyDown( InputEvent event, int keyCode)
		   			{	   			
//		   			System.out.println("KeyCode" + keyCode);
		   			if (keyCode  == Keys.ENTER)
			   			if (opcionesScreen.getResult()!=null)
			   				//if (opcionesScreen.getResult().equals(true))
			   					procesarResultadoDialogo(opcionesScreen.getValue());
			   			//System.out.println("opciones resultado"+ opcionesScreen.getValue());
		   				return false;
		   			}
				}); 						
			}
			
			else{
			 if(pTipoDialogo == eTipoDialogo.Aceptar && pTipoMensaje.equals(eTipoMensaje.ReyenJaque)){
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
	}

	/**
	 * 
	 * @param pResp repuesta del dialogo mostrado
	 */
	
	private void procesarResultadoDialogo(Object pResp) {
		try {
			
			eRespuestaDialgo vResp = (eRespuestaDialgo) pResp;
			if (vResp == eRespuestaDialgo.Si) {
				reiniciar();
			} else if (vResp == eRespuestaDialgo.No) {
				contadorPromovidas=0;
				contadorJugadas=0;
				contadorCapturadas=0;
				contadorMovIncorrectos=0;
				jugadasPeon=0;
				contadorDobleAtaque=0;
				contadorPeonAlPaso=0;
				recargaScreen=false;
				game.setScreen(game.mapMin);
				//dispose();
			}
			else if (vResp == eRespuestaDialgo.Aceptar) {

				contadorPromovidas=0;
				contadorJugadas=0;
				contadorCapturadas=0;
				contadorMovIncorrectos=0;
				contadorDobleAtaque=0;
				jugadasPeon=0;
				contadorPeonAlPaso=0;
//				System.out.println("vResp == eRespuestaDialgo.Aceptar");
				try {
					ultimoMinBatalla=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ultimo"));
				} catch (Exception e1) {
					System.out.println("Error obteniendo el ultimo minijjuego de la batalla");
					
					e1.printStackTrace();
				}
				actualizarPartida();
				if(ultimoMinBatalla){
					
					
//					if (mapGen!=null)
//					{
//						mapGen.dispose();
//						mapGen=null;
//						System.gc();
//						System.out.println("entre a limpiar");
//					}
//					mapGen = new mapaGeneralScreen(game,"batalla0");
//					try {
//						mapGen.asignarPartida(partida,dificultad, configuracion,false
//								);
//						recargaScreen=false;
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					
//
//					game.setScreen(mapGen);
//					
//					mapGen=null;
//					System.gc();
					
					game.mapGen.asignarPartida(partida,dificultad, configuracion,false);
					game.setScreen(game.mapGen);
				}
				else{
				game.mapMin.asignarPartida(partida, batalla, dificultad,configuracion,true);
				recargaScreen=false;
				game.setScreen(game.mapMin);
				dispose();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			recargaScreen=false;
			game.setScreen(game.mapMin);
			dispose();
		}

	}
/**
 * Metodo se ejecuta cuando el jugador pierde 
 */
	private void perder(){
		bPerder = false;
		System.out.println("perder");
		Turno = Color.WHITE;
		for (Actor vActor: stage.getActors())
			while(vActor.getActions().size>1){
				System.out.println("vActor.getActions()" + vActor.getActions().size);
			}
			
		enroquesRealizados = 0;		
		if (tocarSonido){
			cortinaFondo.stop();
			sonidoDerrota.play();
		}
		try {
			System.out.println("reiniciando las monedas");
			partida.setMonedas(String.valueOf(monedaInicial));
			moneda=monedaInicial;
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(" no pudo reiniciar las monedas");
		}
		if (!cargarVideoInformativo(eTipoVideo.Derrota))		
			mostrarDialogo(eTipoDialogo.Mensaje, true,  eTipoMensaje.JuegoFinalizadoDerrota);	
	}
	
	/**
	 * Metodo se ejecuta cuando el jugador pierde 
	 */
		private void tablas(){
			bTablas = false;
			System.out.println("tablas");
			Turno = Color.WHITE;
			for (Actor vActor: stage.getActors())
				while(vActor.getActions().size>1){
					System.out.println("vActor.getActions()" + vActor.getActions().size);
				}
				
			enroquesRealizados = 0;		
			if (tocarSonido){
				cortinaFondo.stop();
				sonidoDerrota.play();
			}
			//if (!cargarVideoInformativo(eTipoVideo.Derrota))		
			mostrarDialogo(eTipoDialogo.Mensaje, true,  eTipoMensaje.JuegoFinalizadoTablas);	
		}

	/**
	 * Metodo se ejecuta cuando el jugador
	 */
		private void reintentar( boolean pPerdido){
			//enroquesRealizados = 0;		
			if (tocarSonido){
				cortinaFondo.stop();
				if (pPerdido)
					sonidoDerrota.play();				
				else
					sonidoTriunfo.play();							
			}
	//		ArrayList<String> vMensaje = new ArrayList<String>();									
			if (pPerdido){
				reintentoEnroques++;
				mostrarDialogo(eTipoDialogo.Mensaje, true,  eTipoMensaje.EnroqueDerrotaParcial);
			}
			else{
				enroquesRealizados++;							
				
				actualizarContador(true, enroquesRealizados);
				if (enroquesRealizados<4)
					mostrarDialogo(eTipoDialogo.Mensaje, true,  eTipoMensaje.EnroqueVictoriaParcial);
				}								
		}

	/**
	 * Metodo se ejecuta cuando el jugador gana
	 */
	private void ganar(){
		bGanar = false;
		Gdx.graphics.setContinuousRendering(true);
		Turno = Color.WHITE;
		System.out.println("ganar minijuego " + minijuego);
		cortinaFondo.stop();
		moneda=moneda+ganarMoneda;
		if (tocarSonido){		
			sonidoTriunfo.stop();
			sonidoTriunfo.play();
		}
		
		
		System.out.println("jugar()"+ jugar());
		while (!jugar())
		{System.out.println("aún no llego");}
		
		if (!cargarVideoInformativo(eTipoVideo.Victoria)) {		
			mostrarDialogo(eTipoDialogo.Aceptar, true, eTipoMensaje.JuegoFinalizadoVictoria);

		}
	}
		
	/**
	 * Consulta al motor las posibles jugadas para la pieza que se encuentra en el escaque seleccionado
	 * @param pPgn posicion pgn de la casilla donde se encuentra las pieza a consultar
	 * @return Vector con las posicion de las casillas donde se puede mover la pieza
	 */

	private void alcanceOponente(ArrayList<PiezasExcluidas> pTipoPiezasExcluidas){
		

		if(mostrarAlcanceOponente){
		eliminarMovimientoPosibles();
		Traductor traductor = new Traductor();		
		ArrayList<String> lPgn = new ArrayList<String>();		
		//ArrayList<String> lPgnp = new ArrayList<String>();
	
		for (int i=0; i<=63;i++){
			Casilla vCasilla = new Casilla();			
			try{
				vCasilla= casillaOcupada(traductor.NumerosACoordenadas(8, 8, i));
				boolean excluida=false;
				if  (vCasilla != null){
					//System.out.println("coordenada ocupada: "+ vCasilla.getPgn());
					for (PiezasExcluidas vTipoPiezaExcluidas: pTipoPiezasExcluidas){	

						if (vCasilla.getPieza().getTipoPieza().equals(vTipoPiezaExcluidas.tipoPieza) &&
							vCasilla.getPieza().getColor().equals(vTipoPiezaExcluidas.colores ) ||
							vCasilla.getPieza().getTipoPieza() == eTipoPieza.Peon){								
							excluida = true;												
							break;
						}
					}				
				}							
				if (!excluida&&vCasilla != null)
					lPgn.add(vCasilla.getPgn());
			}
			catch(Exception e){
				//System.out.println("Error alcance oponente " +  e.getMessage() + " ind: " + i);
			}						
		}
		
		String vMovimientos;
		ArrayList<String> vMovimientosTotales = new ArrayList<String>();
		for (String vPgn:lPgn){			
			//System.out.println("MVI " +  traductor.CoordenadasANumeros(8, 8,vPgn)+ " 3");
			vMovimientos= enviarMangoPaola("MVI " +  traductor.CoordenadasANumeros(8, 8,vPgn)).get(0);
			for (String vPgn1:vMovimientos.split(" ")){
				//System.out.println(" "+vPgn1);
				if (!vPgn1.contains("COD"))
			
					vMovimientosTotales.add(traductor.NumerosACoordenadas(8, 8,Integer.parseInt(vPgn1)));				
			}
		}	
		if (!piezasExcluidas.contains(eTipoPieza.Peon))
			for ( String vPgn: enviarMangoPaola("MCP ")){
//				System.out.println("vPGn" + vPgn);
				if (!vPgn.isEmpty())
					for (String vPgn1:vPgn.split(" ")){				
						if (!vPgn1.contains("COD"))
							vMovimientosTotales.add(traductor.NumerosACoordenadas(8, 8,Integer.parseInt(vPgn1)));				
					}				
			}
				
		if (vMovimientosTotales.size() > 0){
				posiblesMovimientos = new ActorExtra[vMovimientosTotales.size()];				
				int i = 0;
				for(String item: vMovimientosTotales){
						posiblesMovimientos[i] = new ActorExtra(this, TipoDeActor.CUADROPOSIBLESCAPTURAS, stage.getActors().size + 1);
						posiblesMovimientos[i].setPosition(tablero.Pgn2XY(item)[0], tablero.Pgn2XY(item)[1]);
						posiblesMovimientos[i].setSize(casilla.GetAnchoCasilla(), casilla.GetAltoCasilla());
						posiblesMovimientos[i].setVisible(true);
						stage.addActor(posiblesMovimientos[i]);
						posiblesMovimientos[i].setZIndex(0);
						i++;
				}				
			}
		}	
	}

	/**
	 * elimina del stage los posibles movimientos
	 */
	private void eliminarMovimientoPosibles() {
		
			if (posiblesMovimientos!=null)			
				for(ActorExtra vActorExtra: posiblesMovimientos){ // remover los cuadros de las posibles jugadas 
					if (posiblesMovimientos.length >0)
						stage.getActors().removeValue(vActorExtra, true);	
			}
		
		
	}
	/**
	 * elimina del stage las explosiones
	 */
	private void eliminarexplosiones() {
		
			if (explosionMJN19!=null)			
				for(ActorExtra vActorExtra: explosionMJN19){ // remover las explosiones 
					if (explosionMJN19.length >0)
						stage.getActors().removeValue(vActorExtra, true);	
			}
		
		
	}	
	
	
	
	
	/**
	 * 
	 * @param x pos x del mouse cuando hace click en la pantalla
	 * @param y pos y del mouse cuando hace click en la pantalla
	 */
				
	public void construirMuro(float x, float y){
		if (eliminandoMuro){
//			System.out.println("No Colocar muro 1 en: ");
			eliminarPiezaMovil();
			eliminandoMuro = false;
		}

		if (colocandoMuro || Xmm.contains("MJN 20") ){
			eliminarPiezaMovil();

			try {
				colorMuros= configuracion.GetAtributo(batalla, "Minijuego", minijuego, "ColorMuros");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Traductor traductor = new Traductor();		
			String vPgn = tablero.XY2pgn((int)x, (int) y);
//			System.out.println("Colocar muro 1 en: " +  vPgn);
			int vNumeros= traductor.CoordenadasANumeros(8, 8, vPgn);
			if(x<=tablero.GetFinX() + casilla.GetAnchoCasilla() &&
			   y<= tablero.GetFinY()+casilla.GetAltoCasilla()){

				if  (vNumeros>= 0 &&vNumeros<= 63){ 				
					if (!casillaEstaOcupada(vPgn)){
						enviarMangoPaola("AGM " +  vNumeros + " "+colorMuros);
						if(!Xmm.contains("MJN 20")){
						try {
							
							gastarMonedas(costoMuro);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}}
					
						int vInd = 0;
						Muro vMuro;
						for(Casilla vCasilla : casillas){
							try {
								Pieza vPieza = vCasilla.getPieza();
								ActorExtra vActor = vCasilla.getActor();
								
								if (vPieza==null && vActor == null)
								{	if(colorMuros.equals("NEGRO")&& flag3){
									 vMuro = new Muro	(eColores.Negras, vPgn, vInd, 0, this);		
									 vMuro.setColor(Color.BLACK);
									 flag3=false;	
									}
								else 
								if(Xmm.contains("MJN 20") && flag3==false){
									vMuro = new Muro	(eColores.Moneda, vPgn, vInd, 0, this);	
									flag3=true;
								}
								else{
									vMuro = new Muro	(eColores.Blancas, vPgn, vInd, 0, this);	
									 vMuro.setColor(Color.WHITE);
									 flag3=true;	
								}
									stage.addActor(vMuro);	
									
									setCasilla(vPgn, vMuro, vMuro.getInd());

									System.out.println("ct22");
									//CambiarTurno();
									break;
							}
								vInd++;
								} catch (Exception e) {
							//  nada que hacer
							}					
					}					
				}

					else{
						//System.out.println("Error, la casilla esta ocupada : " + vPgn);
						}
					if (mostrarAlcanceOponente){						
						alcanceOponente(piezasExcluidas);
					}

				}				
			}
			colocandoMuro = false;
		}else  {			
//				System.out.println("construir muro  3");
				colocandoMuro = true;

				int vInd = 0;
				
				for(Casilla vCasilla : casillas){
					try {
						Pieza vPieza = vCasilla.getPieza();
						if (vPieza!=null)
							if (vInd < vPieza.getInd())
								vInd = vPieza.getInd();	
					} catch (Exception e) {
					//  nada que hacer
					}					
				}				
				vInd++;				
				ActorMovil  vMuro = new ActorMovil((Texture) game.getManager().get("assets/Texturas/muro.png"), this, true);
				vMuro.setName("piezamovil");
				stage.addActor(vMuro);
//				System.out.println("primer click");				
			}
	}

	/**
	 * 
	 * @param x posiscion X del mouse cuando se clickea la pantalla
	 * @param y posicion  Y del mouse cuando se clickea la pantalla
	 */
	
	public void destruirMuro(float x, float y){

		if (colocandoMuro){
			eliminarPiezaMovil();
			colocandoMuro = false;
		}		
		
		if (eliminandoMuro){
//			System.out.println("entre a eliminando muro");
			eliminarPiezaMovil();
			Traductor traductor = new Traductor();			
			String vPgn = tablero.XY2pgn((int)x, (int) y);
			int vNumeros= traductor.CoordenadasANumeros(8, 8, vPgn);
			if(x<=tablero.GetFinX() + casilla.GetAnchoCasilla() &&
			   y<= tablero.GetFinY()+casilla.GetAltoCasilla()){

				if  (vNumeros>= 0 &&vNumeros<= 63){
					if (casillaEstaOcupadaTipoPieza( vPgn , eTipoPieza.Muro)){					
						String vRes = resultadoJugarMangoPaola(enviarMangoPaola("QPP " +  vNumeros));
//						System.out.println("vres: " + vRes);
						if (!vRes.contains("COD")){										
							EliminarMuro(vPgn);		
							if (mostrarAlcanceOponente)
								alcanceOponente(piezasExcluidas);
							try {								
								gastarMonedas(derriboMuro);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else {
							ArrayList<String> vMensaje = new ArrayList<String>();
							vMensaje.add("No se puede quitar");
							vMensaje.add("este muro");
							playMovimientoIncorrecto();					
							mostrarDialogo(eTipoDialogo.Aceptar, false, eTipoMensaje.NoEliminarMuro);
							}				
					}
				}
				
//				System.out.println("eliminandoMuro = false");
			}
			eliminandoMuro = false;
		}else  {			
//				System.out.println("eliminandoMuro = true");
				eliminandoMuro = true;
				ActorMovil  vMartillo = new ActorMovil((Texture) game.getManager().get("assets/Texturas/martillo.png"), this, false);
				vMartillo.setName("piezamovil");
				stage.addActor(vMartillo);
			}
	}    			
	
	private void eliminarPiezaMovil(){
		for(Actor vActor: stage.getActors()){
			try{
				if (vActor.getName().equals("piezamovil")){
					stage.getActors().removeValue(vActor, true);
					break;
				}
			}
			catch(Exception e){
				// nada que hacer
			}
		}		
	}

	/**
	 * 
	 * @param pPiezasExcluidas Vector con las piezas excluidas para mostrar  el alcance de las ppiezas del oponente
	 */
	
	public void configurarAlcance(String[] pPiezasExcluidas)
	{
		if (mostrarAlcanceOponente){
			for(String item: pPiezasExcluidas){
//				System.out.println("pieza excluida: " + item);
				if (item !=null){						
					PiezasExcluidas vPiezasExcluidas;
					if (item.equals("r")){												
						vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Torre, colorOponente);							
						piezasExcluidas.add(vPiezasExcluidas);
					}
					else 
						if(item.equals("n")){												
							vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Caballo, colorOponente);
							piezasExcluidas.add(vPiezasExcluidas);
						}						
						else 
							if(item.equals("b")){
								vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Alfil, colorOponente);
								piezasExcluidas.add(vPiezasExcluidas);
							}
							else 
								if(item.equals("q")){
									vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Reina, colorOponente);
									piezasExcluidas.add(vPiezasExcluidas);
								}
								else 
									if(item.equals("k")){
										vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Rey, colorOponente);
										piezasExcluidas.add(vPiezasExcluidas);
									}
									else 
										if(item.equals("p")){
											vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Peon, colorOponente);
											piezasExcluidas.add(vPiezasExcluidas);
										}
										else 
											if(item.equals("w")){
												vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Muro, colorOponente);
												piezasExcluidas.add(vPiezasExcluidas);
											}	
											else 
												if (item.equals("R")){
													vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Torre, colorAliado);
													piezasExcluidas.add(vPiezasExcluidas);
												}
												else 
													if(item.equals("N")){
														vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Caballo, colorAliado);
														piezasExcluidas.add(vPiezasExcluidas);
													}													
													else 
														if(item.equals("B")){
															vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Alfil, colorAliado);
															piezasExcluidas.add(vPiezasExcluidas);
														}
														else 
															if(item.equals("Q")){
																vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Reina, colorAliado);
																piezasExcluidas.add(vPiezasExcluidas);
															}
															else 
																if(item.equals("K")){
//																	System.out.println("incluyendo el rey");
																	vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Rey, colorAliado);
																	piezasExcluidas.add(vPiezasExcluidas);
																}
																else 
																	if(item.equals("P")){
																		vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Peon, colorAliado);
																		piezasExcluidas.add(vPiezasExcluidas);
																	}
																	else 
																		if(item.equals("W")){
																			vPiezasExcluidas = new PiezasExcluidas(eTipoPieza.Muro, colorAliado);
																			piezasExcluidas.add(vPiezasExcluidas);
																		}
				}
			}
		}
	}
	
	/*
	 * coloca la imagenes y agrega los respectivos Listeners
	 */
	private void colocarImagenes() {
		
		ImageButtonStyle style=new ImageButtonStyle();
		style.up=new TextureRegionDrawable(new TextureRegion(texturaPersonaje));
		style.over=new TextureRegionDrawable(new TextureRegion(texturaPersonajeAura));
		imagenPersonaje=new ImageButton(style);
//		imagenPersonaje = new BotonImagen(texturaPersonaje, texturaPersonaje, texturaPersonajeAura, textureOptionBackGround);
		imagenPersonaje.setPosition(645, 70);
		imagenPersonaje.setSize(120, 230);
		imagenPersonaje.setTouchable(Touchable.enabled);
		imagenPersonaje.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {				
				cargarBiografia();
			
			}
		
		});
		
		stage.addActor(imagenPersonaje);
		Texture textureMoneda=game.getManager().get("assets/Texturas/moneda.png");	
		Texture textureMoneda2=game.getManager().get("assets/Texturas/moneda2.png");	
		imagenMoneda=new Image(textureMoneda);
		imagenMoneda.setPosition(10, 500);
		imagenMoneda.setSize(48, 48);
		imagenMoneda.setTouchable(Touchable.disabled);
		stage.addActor(imagenMoneda);
		
		etiquetaMoneda = new Label("x" + String.valueOf(moneda), skin2);
		etiquetaMoneda.setPosition(65, 512);
		etiquetaMoneda.setFontScale(1);
		stage.addActor(etiquetaMoneda);

		
		if (colocarMuros){
				Texture textureMuro=game.getManager().get("assets/Texturas/muro.png");
				imagenMuros = new Image(textureMuro);
				
				imagenMuros.setPosition(130, 504);
				imagenMuros.setSize(40, 40);
						
				imagenMuros.setTouchable(Touchable.enabled);
				
				imagenMuros.addListener(new ClickListener() {
					public void clicked (InputEvent event, float x, float y) {
						
//						System.out.println("costoMuro:"+ costoMuro);
//						System.out.println("moneda:"+ moneda);
//						
						if (costoMuro<=moneda)
						{
							if (!colocandoMuro)
								construirMuro(x,y);
							else
								colocandoMuro=false;															
						}
					}
				});
				
				//imagenMuros.addListener(new InputListener() {
					Label ayudaMuro =new Label(String.valueOf("= "+costoMuro), skin2);
					
					Image ayudaMoneda=new Image(textureMoneda2);
				
//					  @Override
//					  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){	
						ayudaMuro.setPosition(imagenMuros.getX()+50, imagenMuros.getY()+8);
						ayudaMuro.setFontScale(1);
						stage.addActor(ayudaMuro);
						ayudaMoneda.setPosition(imagenMuros.getX()+87, imagenMuros.getY()+5);
					    ayudaMoneda.setSize(28,28);
						ayudaMoneda.setTouchable(Touchable.disabled);
						stage.addActor(ayudaMoneda);
//											
						
//					}
//					  @Override
//				       public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
//	
//						ayudaMuro.remove();
//						ayudaMoneda.remove();
//					}
//					
					
			//	});
				stage.addActor(imagenMuros);
		}

		if (eliminarMuros){
			Texture textureMartillo=game.getManager().get("assets/Texturas/martillo.png");
				imagenMartillos = new Image(textureMartillo);		
				imagenMartillos.setPosition(270, 504);
				imagenMartillos.setSize(40, 40);
						
				imagenMartillos.setTouchable(Touchable.enabled);
				
				imagenMartillos.addListener(new ClickListener() {
					public void clicked (InputEvent event, float x, float y) {
						
						if (derriboMuro<=moneda)
						{
							if (!eliminandoMuro)
								destruirMuro(x,y);
							else
								eliminandoMuro=false;										
						}						
					}
				});
//				imagenMartillos.addListener(new InputListener() {
				String igual= "=";
				
				if (derriboMuro<10)
					igual=igual+" "+ "0";
				
					Label ayudaMartillo =new Label(String.valueOf(igual+derriboMuro), skin2);
					Image ayudaMoneda=new Image(textureMoneda2);
				
//					  @Override
//					  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){	
						ayudaMartillo.setPosition(imagenMartillos.getX()+50, imagenMartillos.getY()+8);
						ayudaMartillo.setFontScale(1);
						stage.addActor(ayudaMartillo);
						ayudaMoneda.setPosition(imagenMartillos.getX()+87, imagenMartillos.getY()+5);
					    ayudaMoneda.setSize(28,28);
						ayudaMoneda.setTouchable(Touchable.disabled);
						stage.addActor(ayudaMoneda);
//											
						
//					}
//					  @Override
//				       public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
//	
//						ayudaMartillo.remove();
//						ayudaMoneda.remove();
//					}
//					
//					
//				});
					stage.addActor(imagenMartillos);													
		}
	}


	/**
	 * Gasto de monedas
	 *@param costo, valor a reducir en las monedas
	 * @throws Exception
	 * @author yisheng
	 */
	protected void gastarMonedas(int costo) throws Exception {

		moneda=moneda-costo;
		etiquetaMoneda.setText("x" + String.valueOf(moneda));
		partida.setMonedas(String.valueOf(moneda));
		if (costo > 0)
		{
			sonidoPierdeMoneda.stop();

		} else {

			sonidoMoneda.stop();
		}

		if (tocarSonido){		
			if (costo > 0)
			{
				sonidoPierdeMoneda.play();

			} else {

				sonidoMoneda.play();
			}
		}
	}
	/**
	 * cambia el fen del minijuego 1
	 */
	public void CambiarFen(ArrayList<Pieza> pPiezaMantener){
		
		if (Xmm.contains("MJN 01")) {
			String vRes = "";
//			System.out.println("cambiarcamino : "+cambiarCamino);
		 if (cambiarCamino%2==0){
//			 System.out.println("entre en el camino %2");
				vRes = resultadoJugarMangoPaola(enviarMangoPaola("CM1"));
			}			
//			System.out.println("CM1 fen: " + vRes);
			if (vRes.contains("/")){			
				
				CargarFenCambiado(vRes, pPiezaMantener);
				
				Gdx.input.setInputProcessor(stage);
			}		
			cambiarCamino ++;			
		}
	}
	/**
	 * carga el fen en el tablero
	 */
	private void CargarFenCambiado(String pNuevoFen, ArrayList<Pieza> pPiezaMantener) {
		//pgnMantenerPieza.clear();
		//pgnMantenerActor.clear();
		
		
		Fen vFen = new Fen(this, 0);

		for (int i = 0; i <= 63; i++) {
			try {
				if (casillas[i].getPieza() != null) {
					//if (!pPiezaMantener.contains(casillas[i].getPieza().pieza)) {					
					if (!pPiezaMantener.contains(casillas[i].getPieza())) {						
						stage.getActors().removeValue(casillas[i].getPieza() , true);						
						}
					else
						pgnMantenerPieza.add(casillas[i].getPgn());					
				}
				else if (!casillas[i].getActor().getTipo().equals(TipoDeActor.PUERTA)){
						pgnMantenerActor.add(casillas[i].getPgn());
					}									
			}									
			catch(Exception e){				
			}
		}
		
		String vTurno[] = vFen.Fen2PgnMiniJuego(pNuevoFen, 32).split("/");
		for (int i = 0; i <= 63; i++) {
			try {				
				if (casillas[i].getPieza() != null  && !pgnMantenerPieza.contains( casillas[i].getPgn())){					
					stage.addActor(casillas[i].getPieza());
					casillas[i].getPieza().setZIndex(0);
				}				

			} catch (Exception e) {
				// TODO: handle exception

				casillas[i] = new Casilla();
			}
			try {
				if (casillas[i].getActor() != null && !pgnMantenerActor.contains( casillas[i].getPgn())) {
					stage.addActor(casillas[i].getActor());
					casillas[i].getActor().setZIndex(0);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

		referencias();
		botones();
	//	for (String turno : vTurno)
//			System.out.println("vturno :" + turno);

		if (vTurno[1].equals("w")) {
			setTurno(Color.WHITE);
			habilitarPiezas(eColores.Blancas);

			if (jugandovsMangoPaola && colorOponente.equals(Color.WHITE)) {
				
			}
		} else {
			setTurno(Color.BLACK);
			habilitarPiezas(eColores.Negras);
		}
		int k = 0;
		if (vTurno.length == 6)
			k = 2;
		else
			k = 1;

		if (vTurno[k].contains("k")) {
		}
		if (vTurno[k].contains("q")) {
			Torre vTorre = (Torre) casillaOcupada("A8").getPieza();
			vTorre.setMovida(false);
			((Rey) casillaOcupada("E8").getPieza()).setTorreReinaNe(vTorre);

		}
		if (vTurno[k].contains("K")) {

		}
		if (vTurno[k].contains("Q")) {
			Torre vTorre = (Torre) casillaOcupada("A1").getPieza();
			vTorre.setMovida(false);
			((Rey) casillaOcupada("E1").getPieza()).setTorreReina(vTorre);
		}

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
		System.out.println("animacion capturando");
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

//		actorExtra.addAction(Actions.moveTo(tablero.Pgn2XY(pPgnf)[0],tablero.Pgn2XY(pPgnf)[1], (pDuracion/4)));
		MoveToAction moveAction = new MoveToAction();
		moveAction.setPosition(tablero.Pgn2XY(pPgnf)[0],tablero.Pgn2XY(pPgnf)[1]);
		moveAction.setDuration(pDuracion/4);
		actorExtra.addAction(moveAction);
		final float posXfinal=tablero.Pgn2XY(pPgnf)[0];
		final float posYfinal=tablero.Pgn2XY(pPgnf)[1];
		final Pieza piezaE=pPieza;
		final String pgnE=pPgnf;
		boolean eliminarActorDespues  = false;
		
		if ((Xmm.contains("MJN 13") && casillaEstaOcupadaTipoPieza(pPgnf, eTipoPieza.Muro))){
			if (casillaOcupada(pPgnf).getPieza().getEcolor().equals(eColores.Moneda)){				
				eliminarActorDespues  = true;
				pgnActorEliminarDespues = pPgnf;			
			}
			else{
				EliminarPieza(piezaE, pgnE);		
				setCasilla(pgnE, piezaE, piezaE.getInd());				
			}			
		}
		else
		if(Xmm.contains("MJN 03")||Xmm.contains("MJN 18")||Xmm.contains("MJN 20")||Xmm.contains("MJN 17")||Xmm.contains("MJN 15")){
			EliminarPieza(piezaE, pgnE);		
			setCasilla(pgnE, piezaE, piezaE.getInd());
		}

		else{
			eliminarActorDespues  = true;
			pgnActorEliminarDespues = pPgnf;
		}
		final boolean eliminarActordespues = eliminarActorDespues; 
		Timer.schedule(new Task() {
			@Override
			public void run() {
				actorExtra.setVisible(false);
				actorExtra.setRotation(0);
				explosion.setPosition(posXfinal,posYfinal);
				explosion.setVisible(true);
				explosion.setZIndex(100);
				
				Timer.schedule(new Task() {
				@Override
				public void run() {		
				explosion.setVisible(false);
				if (eliminarActordespues){				
					EliminarPieza(piezaE, pgnE);		
					setCasilla(pgnE, piezaE, piezaE.getInd());
				}
							
				}
			},(pDuracion/4)); //6
			}
		},(pDuracion/4));//4
		
	}	
	

	/**
	 * Metodo para animar el movimiento de las piezas patriotas
	 * 
	 * @param pPieza pieza que esta en movimiento
	 * @param pPgni  pgn de inicio de la pieza
	 * @param pPgnf	 pgn final de la pieza 
	 */
	public void animacionMovimientoPatriotas(Pieza pPieza,String pPgni,String pPgnf){
		System.out.println("animando patriotas...");
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
			pPieza.setPack(caballoPatriotaDerechaAbajo);		
			}		
			
		else
		if(direccion.equals("abajoDerecha"))
			{
			pPieza.set_texture(null);
			pPieza.setPack(caballoPatriotaAbajoDerecha);			
			}			
							
		else
		if(direccion.equals("abajoIzquierda"))
			{
			pPieza.set_texture(null);
			pPieza.setPack(caballoPatriotaAbajoIzquierda);		
			}			
					
		else
		if(direccion.equals("izquierdaAbajo"))
			{
			pPieza.set_texture(null);
			pPieza.setPack(caballoPatriotaIzquierdaAbajo);		
			}		
			else
			if(direccion.equals("izquierdaArriba"))
					{
				pPieza.set_texture(null);
				pPieza.setPack(caballoPatriotaIzquierdaArriba);
					}		
			else
			if(direccion.equals("arribaIzquierda"))
					{
				pPieza.set_texture(null);
				pPieza.setPack(caballoPatriotaArribaIzquierda);
					}	
			else
			if(direccion.equals("arribaDerecha"))
					{
						pPieza.set_texture(null);
						pPieza.setPack(caballoPatriotaArribaDerecha);
					}		
			else
			if(direccion.equals("derechaArriba"))
					{
				pPieza.set_texture(null);
				pPieza.setPack(caballoPatriotaDerechaArriba);
					}		
				
			else
					{
						
					}
		
	}	
	
	
	/**
	 * Metodo para animar el movimiento de las piezas realistas
	 * 
	 * @param pPieza pieza que esta en movimiento
	 * @param pPgni  pgn de inicio de la pieza
	 * @param pPgnf	 pgn final de la pieza 
	 */
	public void animacionMovimientoRealistas(Pieza pPieza,String pPgni,String pgnf){

		String pPgnf=pgnf.substring(0, 2);
		String direccion=determinarMov(pPgni, pPgnf);
//		System.out.println("direccion : " + direccion);
//		System.out.println("pgn :"+pPgni+" - "+ pPgnf);
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
	
	private void playMovimientoIncorrecto(){
		if (tocarSonido){
			movIncorrecto.setVolume(movIncorrecto.play(),0.2f);
		}
		else{
			ArrayList<String> vMensaje = new ArrayList<String>();										
			vMensaje.add("No se puede Jugar");							
			mostrarDialogo(eTipoDialogo.Aceptar, false, eTipoMensaje.MovimientoNoPermitido);
		}
	}

/**
 * Envia los comando de comportamiento de las piezas al motor de ajedrez
 */
public void enviarComandosBanderas(){

	if(Xmm.contains("MJN 03"))
	{
		for(int j=0;j<64;j++)
			{
				if(casillas[j]!=null && casillas[j].getPieza()!=null)
				{
					TextureRegion[] textureRegion = new TextureRegion[8];
					Pieza pPieza=casillas[j].getPieza();
					
					if(pPieza.getTipoPieza().equals(eTipoPieza.Torre))
					{
						pPieza.set_texture(null);
						pPieza.setTextureReg(textureRegion);
						pPieza.setPack(torreDormido);
					}
					else
					if(pPieza.getTipoPieza().equals(eTipoPieza.Alfil))
					{
						pPieza.set_texture(null);
						pPieza.setTextureReg(textureRegion);
						pPieza.setPack(alfilDormido);
					}
					else
					if(pPieza.getTipoPieza().equals(eTipoPieza.Peon))
					{
						pPieza.set_texture(null);
						pPieza.setTextureReg(textureRegion);
						pPieza.setPack(peonDormido);
					}
				}
		}
	}
	
	
	
	if(Xmm.contains("MJN 05"))
		{
			Random random = new Random();
			int num = random.nextInt(2);
			if(num==0)
				{
					enviarMangoPaola("CMF 1");
				}
			else
				{
					enviarMangoPaola("CML 1");
				}
		}
	
	if(tiempo){
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
		reloj.setName("reloj");
		
		stage.addActor(fondoReloj);
		stage.addActor(reloj);
	}
	
	if(Xmm.contains("MJN 19")){
		Traductor traductor=new Traductor();
		for(int j=0;j<64;j++){
			if(casillas[j]!=null && casillas[j].getPieza()!=null){
			Pieza pPieza=casillas[j].getPieza();
//			System.out.println("pieza : "+pPieza);
				if(pPieza.getTipoPieza().equals(eTipoPieza.Caballo)){
//					System.out.println("es un caballo");
					String vPgnCa =casillas[j].getPgn();
					int numero=traductor.CoordenadasANumeros(8, 8, vPgnCa);
					String numEliminadas;
					numEliminadas= resultadoJugarMangoPaola(enviarMangoPaola("QAC "+numero));
					if(numEliminadas!=null)
					{
						
						int numeroPosiciones= 0;
						numeroPosiciones=numEliminadas.split(" ").length;							
						String[] pos = new String[numeroPosiciones];
						String[] pgnEliminar = new String[numeroPosiciones];;
						for(int i=0; i<numeroPosiciones;i++)
							{
								pos[i]=numEliminadas.split(" ")[i];
								pgnEliminar[i]=traductor.NumerosACoordenadas(8, 8,Integer.parseInt(pos[i]));
								if(casillaOcupada(pgnEliminar[i]).getPieza()!=null)
									{
										Pieza piezaEliminar=casillaOcupada(pgnEliminar[i]).getPieza();
										borrarCasilla(piezaEliminar.getInd());
										piezaEliminar.remove();

									}
							}
					}
					
				}
			}
		}
	}
	else
	if(Xmm.contains("MJN 21")){
		
		Muro vMuroBla = null;
		Muro vMuroNeg = null;
		Traductor traductor=new Traductor();
		for(int i=0;i<24;i++){
			enviarMangoPaola("AGM " + i  + " "+"NEGRO");	
			String vPgnNe=traductor.NumerosACoordenadas(8, 8, i);
			vMuroNeg=new Muro(eColores.Negras, vPgnNe, i+16, 0, this);
			setCasilla(vPgnNe, vMuroNeg, i+16);
			stage.addActor(vMuroNeg);
		}
		for(int i=56;i<64;i++){
			enviarMangoPaola("AGM " + i  + " "+"BLANCO");
			String vPgnBla=traductor.NumerosACoordenadas(8, 8, i);
			vMuroBla=new Muro(eColores.Blancas, vPgnBla, i, 0, this);
			setCasilla(vPgnBla, vMuroBla, i);
			stage.addActor(vMuroBla);
		}
		
		
	}
	
	if(modoSigiloso)
		{
			enviarMangoPaola("CMT 1");
		}
	if(informarUltimaCaptura)
		{
			enviarMangoPaola("AUC 1");
		}
	if(prioridadCaptura)
		{
			enviarMangoPaola("CMC 1");
		}
	if(movEscaqueNoAmenazado)
		{
			enviarMangoPaola("CMA 1");
		}
	if (permitirJaque)
		{
			enviarMangoPaola("IGJ 1");
		}
}	
	
/**
 * Metodo que lee los atributos del archivo de configuracion
 */
public void leerConfiguracion(){
	
	try {
			batallaNombre=configuracion.GetAtributo(batalla, "Mapa", "mapa", "Historia");
//			System.out.println("batallaNombre" +batallaNombre);
			pantallaContador= Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Contador"));
			cantidadContador= Integer.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "CantidadContador"));
			
			tipoContadorHumano=configuracion.GetAtributo(batalla, "Minijuego", minijuego, "TipoContadorHumano");
//			System.out.println("contadorhumano : "+tipoContadorHumano);
			tipoContadorMaquina=configuracion.GetAtributo(batalla, "Minijuego", minijuego, "TipoContadorMaquina");
//			System.out.println("contadormaquina : "+tipoContadorMaquina);
			contarPeones=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "ContarPeones"));
			modoSigiloso=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "ModoSigiloso"));
			agregarMurosFen=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "AgregarMurosFen"));
			colorMurosFen= configuracion.GetAtributo(batalla, "Minijuego", minijuego, "ColorMurosFen");
			bloquearSalida=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "BloquearSalida"));
			intensidadMurosFen=Integer.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "IntensidadMurosFen"));
			movEscaqueNoAmenazado=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "MovEscaqueNoAmenazado"));
			informarUltimaCaptura=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "InformarUltimaCaptura"));
			prioridadCaptura=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "PrioridadCaptura"));
			permitirJaque=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "PermitirJaque"));
			limitadorMovimientos=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "LimitadorMovimientos"));
			System.out.println("limitadorMovimientos:"+limitadorMovimientos);
			cantidadMovimientos=Integer.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "CantidadMov"));
			System.out.println("cantida de movimientos:"+cantidadMovimientos);
			cortinaFondo = game.getManager().get(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Sonido"));
			TexturaTablero = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Fondo");			
			rutaTexturaPersonaje = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Personaje");
			videoAyuda = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ayuda");
			videoBiografia = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Biografia");
			videoHistoria = configuracion.GetAtributo(batalla, "Mapa", "mapa", "Biografia");
			juegaMotor = Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "JuegaMotor"));
			mostrarAlcanceOponente = Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "MostrarAlcanceOponente"));
			mostrarMonedas = Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Moneda"));
			mostrarPaja = Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Paja"));
			vPiezasExcluidas = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "TipoPiezasExcluidas").split(" ");
			colocarMuros= Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "ColocarMuros"));
			eliminarMuros = Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "EliminarMuros"));
			capturarMuros = Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "CapturarMuros"));
			agregarBolsasExtra = Integer.parseInt(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "agregarBolsasExtras"));
			ultimoMinBatalla=Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Ultimo"));
			tiempo = Boolean.valueOf(configuracion.GetAtributo(batalla, "Minijuego", minijuego, "Tiempo"));
			Xmm = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "XMM");
			
			//			System.out.println("bolsas agregadas: " + agregarBolsasExtra);

		} 
		catch (Exception e) 
		{
			System.out.println("Error verifique el archivo de configuracion" + e);
			e.printStackTrace();
		}			
	}	
	
	private void ConfigurarPiezasAMantener(String[] pPiezasAMantener){
		
	//	Casilla[] vCasillas = new Casilla[64];
		if (pPiezasAMantener != null)
		for(String item: pPiezasAMantener){
//			System.out.println("piezaaMantener: " + item);
			if (item !=null){						
				if (item.equals("r"))
					AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Torre, colorOponente));							
				else 
					if(item.equals("n"))						
						AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Caballo, colorOponente));

					else 
						if(item.equals("b"))
							AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Alfil, colorOponente));
						else 
							if(item.equals("q"))
								AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Reina, colorOponente));
							else 
								if(item.equals("k"))
									AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Rey, colorOponente));
								else 
									if(item.equals("p"))
										AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Peon, colorOponente));
									else 
										if(item.equals("w"))
											AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Muro, colorOponente));
										else 
											if (item.equals("R"))
												AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Torre, colorAliado));
											else 
												if(item.equals("N"))
													AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Caballo, colorAliado));
												else 
													if(item.equals("B"))
														AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Alfil, colorAliado));
													else 
														if(item.equals("Q"))
															AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Reina, colorAliado));
														else 
															if(item.equals("K"))
																AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Rey, colorAliado));
															else 
																if(item.equals("P"))
																	AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Peon, colorAliado));																
																else 
																	if(item.equals("W"))																		
																		AgregarPiezasAMantener(casillaOcupada(eTipoPieza.Muro, colorAliado));																	
			}
		}
	}			
		
	private void AgregarPiezasAMantener(Casilla[] pCasillas){
		if (pCasillas!=null){ 		
//		System.out.println("pCasillas.length" + pCasillas.length);
			for (int i = 0; i<pCasillas.length;i++)			
				piezasAMantener.add(pCasillas[i].getPieza());
		}
	}

	
/**
 * Metodo para agregar muros extras a un minijuego 	
 */
	
public void agregarMurosManual(int cantidadMuros){
	

	for(int i=0;i<cantidadMuros;i++){
		float intX;
		int intY ;
		
		if(Xmm.contains("MJN 17")){
		intX = randInt(65, 72);
		intY = randInt(1, 8);
		}
		else{
		 intX = randInt(65, 72);
		 intY = randInt(4, 8);	
		}
		String pgnRamdom=Character.toString((char) intX) + "" + intY;
	
		//if (Xmm.contains("MJN 20")){
				Traductor traductor = new Traductor();		
				int vNumeros= traductor.CoordenadasANumeros(8, 8, pgnRamdom);
				if (!casillaEstaOcupada(pgnRamdom))
					{
						enviarMangoPaola("AGM " +  vNumeros);
						int vInd = 0;
						Muro vMuro = null;
						for(Casilla vCasilla : casillas){
							Pieza vPieza = vCasilla.getPieza();
							ActorExtra vActor = vCasilla.getActor();
					
							if (vPieza==null && vActor == null)
							{
								if(Xmm.contains("MJN 20")){
								if(flag3==true)
									{
									 	vMuro = new Muro(eColores.Negras, pgnRamdom, vInd, 0, this);	
									 	flag3=false;
									}
								else
								if(flag3==false)
									{
										vMuro = new Muro(eColores.Moneda, pgnRamdom, vInd, 0, this);
										flag3=true;
									}
								}
								else{
								 	vMuro = new Muro(eColores.Negras, pgnRamdom, vInd, 0, this);	
								}
								vMuro.setColor(Color.BLACK);
								stage.addActor(vMuro);	
								setCasilla(pgnRamdom, vMuro, vMuro.getInd());
								break;
							}
								vInd++;								
					}
						}
					else
					{
//						System.out.println("la casilla esta ocupada : " + pgnRamdom);
					}

			}
	//}
	
}
/**
 * Metodo para eliminar muros extras a un minijuego 	
 */
public void eliminarMurosManual(){
	Traductor traductor= new Traductor();

	for (int i = 0; i <= 63; i++) {
		
				if (casillas[i].getPieza() != null && casillas[i].getPieza().getTipoPieza().equals(eTipoPieza.Muro)) 
					{
//					System.out.println("contador :"+ i);
					Pieza pieza=casillas[i].getPieza();
					String pgn=casillas[i].getPgn();
					int coordenada=traductor.CoordenadasANumeros(8, 8, pgn);
					enviarMangoPaola("QPP " + coordenada);
					borrarCasilla(pieza.getInd());
					pieza.remove();	
					}
	}	
}

public void agregarMonedasManual(){
	int cantidadMonedas=3;
	int vInd = 0;
	for (int i =0;i<=63; i++){
		try{
			int vInd2 = casillas[i].getPieza().getInd();
			if (vInd2>vInd)
				vInd = vInd2;
		}
		catch(Exception e) {							
		}								
}

	for (int j = 0 ;j<cantidadMonedas;){
				Traductor traductor = new Traductor();		
				int vNumeros= randInt(0, 63);
				String pgnRamdom=traductor.NumerosACoordenadas(8,8,vNumeros);
				if (!casillaEstaOcupada(traductor.NumerosACoordenadas(8,8,vNumeros))){
						enviarMangoPaola("AGM " +  vNumeros);					
						vInd++;
						Muro vMuro = new Muro	(eColores.Moneda, pgnRamdom, vInd, 0, this);
						vMuro.setColor(Color.BLACK);																										
						stage.addActor(vMuro);	
						vMuro.setZIndex(0);	
						setCasilla(pgnRamdom, vMuro, vMuro.getInd());
						j++;
					}
					else
					{
						System.out.println("Error, la casilla esta ocupada : " + pgnRamdom);					
					}
		}

}


public void eliminarMonedasManual(){
	Traductor traductor= new Traductor();
//System.out.println("pgnBolsaEliminarDespues: " + pgnBolsaEliminarDespues);
	for (int i = 0; i <= 63; i++) {
		try{
		
				if (casillas[i].getPieza() != null && casillas[i].getPieza().getTipoPieza().equals(eTipoPieza.Muro) && casillas[i].getPieza().getEcolor().equals(eColores.Moneda)&& !casillas[i].getPgn().equals(pgnActorEliminarDespues)) 
					{
//					System.out.println("contador :"+ i);
					Pieza pieza=casillas[i].getPieza();
					String pgn=casillas[i].getPgn();
					int coordenada=traductor.CoordenadasANumeros(8, 8, pgn);
					enviarMangoPaola("QPP " + coordenada);
					borrarCasilla(pieza.getInd());
					pieza.remove();
					
					}
		}
		catch(Exception e){
			
		}
	}
	pgnActorEliminarDespues = "";
}

/**
 * Meotodo para generar un numero al azar entre Min y Max
 * @param min valor minimo del rango 
 * @param max valor maximo del rango
 * @return numero al azar
 */

public static int randInt(int min, int max) {
	Random rand = new Random();
	int randomNum = rand.nextInt((max - min) + 1) + min;
	return randomNum;

}	

/**
 * Metodo para comprobar cuantas jugadas, promociones, etc. Lleva un jugador durante un minijuego	
 */

public void comprobarEstadoJuego(){
	
	if(Xmm.contains("MJN 20"))
	{
		if(contadorJugadas==limiteMovMJN20&&contadorPromovidas<4)
			{
				bPerder = true; //perder();
			}
		else
		if(contadorPromovidas==4 && contadorJugadas<=limiteMovMJN20)
			{
				bGanar = true; //ganar();
			}
	}
	else
	if(Xmm.contains("MJN 22"))
	{
		if(jugadasPeon==limiteMovMJN22 && contadorPromovidas<4)
		{
			bPerder = true; //perder();
		}
		else
		if(contadorPromovidas==4 && jugadasPeon<=limiteMovMJN22)
		{
			bGanar = true; //ganar();
		}		
	}
	else
	if(Xmm.contains("MJN 19")){
		if(contadorJugadas==limiteMovMJN19 && contadorDobleAtaque<4)
		{
			bPerder = true; //perder();
		}
	else
	if(contadorDobleAtaque==4 && contadorJugadas<=limiteMovMJN19)
		{
			bGanar = true; //ganar();
		}
	}				
}
/**
 * Metodo para contar cuantas piezas hay en el tablero de un tipo y color en especifico 
 * @param pTipoPieza tipo de pieza a buscar
 * @param pColor color de pieza a buscar
 * @return cantidad de piezas encontradas
 */
public int conteoDePiezas(eTipoPieza pTipoPieza,Color pColor)
	{
		contadorPiezas=0;
		if (pTipoPieza != null){ 
			for(int i=0;i<64;i++){
				if(casillas[i].getPieza()!=null){
					if(casillas[i].getPieza().getTipoPieza().equals(pTipoPieza)&&casillas[i].getPieza().getColor().equals(pColor)){
							contadorPiezas++;
					}
				}	
			}
		}
		else{ // contar las piezas de un color
			for(int i=0;i<64;i++){
				if(casillas[i].getPieza()!=null){
					if(casillas[i].getPieza().getColor().equals(pColor)){
						contadorPiezas++;
					}
				}	
			}
			
		}
		if(Xmm.contains("MJN 22"))
			{
//				System.out.println("conteo : "+contadorPiezas +" - "+contadorPromovidas);
				contadorPiezas=contadorPiezas+contadorPromovidas;
			}
		return contadorPiezas;	
	}


/**
 * Metodo para contar la bolsas 
 * @param pTipoPieza tipo de pieza a buscar
 * @param pColor color de pieza a buscar
 * @return cantidad de piezas encontradas
 */
public int conteoDePiezas(TipoDeActor pTipoDeActor)	{
		contadorPiezas=0;
 
		for(int i=0;i<64;i++){
			if(casillas[i].getActor()!=null){
				
				if(casillas[i].getActor().getTipo().equals(pTipoDeActor)){
						contadorPiezas++;
				}
			}	

		}
		return contadorPiezas;
}

/**
 * Metodo encargado de cargar todas las animaciones de las piezas patriotas
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
	
	peonPatriotaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonEspalda.pack"));
	peonPatriotaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonFrontal.pack"));
	peonPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonDiagonalDerAbajo.pack"));
	peonPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonDiagonalDerArriba.pack"));
	peonPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonDiagonalIzqArriba.pack"));
	peonPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/peon/peonDiagonalIzqAbajo.pack"));
	
	caballoPatriotaAbajoDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoAbajoDerecha.pack"));
	caballoPatriotaAbajoIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoAbajoIzquierda.pack"));
	caballoPatriotaArribaDerecha=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoArribaDerecha.pack"));
	caballoPatriotaArribaIzquierda=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoArribaIzquierda.pack"));
	caballoPatriotaDerechaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoDerechaArriba.pack"));
	caballoPatriotaDerechaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoDerechaAbajo.pack"));
	caballoPatriotaIzquierdaArriba=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoIzquierdaArriba.pack"));
	caballoPatriotaIzquierdaAbajo=new TextureAtlas(Gdx.files.internal("assets/skins/patriotas/caballo/caballoIzquierdaAbajo.pack"));
	
	
	

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
	
	
	
	alfilDormido=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dormidos/alfilDormido.pack"));
	torreDormido=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dormidos/torreDormido.pack"));
	peonDormido	=new TextureAtlas(Gdx.files.internal("assets/skins/realistas/dormidos/peonDormido.pack"));
	
	
}


private void inicializar(){
	listaCasillasDeshacer.clear();
	txcargaFen = "";				
	contadorPromovidas=0;
	contadorJugadas=0;
	contadorCapturadas=0;
	contadorMovIncorrectos=0;
	contadorDobleAtaque=0;
	mostrarVideos = false;
	recargaScreen = true;
	contadorPeonAlPaso=0;
	jugadasPeon=0;
	casillas = new Casilla[64];
			
	if (bolsasCapturadas>0){
		try {
			gastarMonedas(bolsasCapturadas*cantidadMonedas);
				} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}				
	
	bolsasCapturadas=0;
	
}


private void reiniciar(){
System.out.println("entre enroqu");
	listaCasillasDeshacer.clear();
	txcargaFen = "";				
	contadorPromovidas=0;
	contadorJugadas=0;
	contadorCapturadas=0;
	contadorMovIncorrectos=0;
	contadorDobleAtaque=0;
	mostrarVideos = false;
	recargaScreen = true;
	contadorPeonAlPaso=0;
	if(tiempo&&reloj!=null){
		System.out.println("limpiando reloj");
		reloj.clearActions();
		flagReloj=false;
	}
	jugadasPeon=0;
	casillas = new Casilla[64];
	if(tocarMusica)
	{
		cortinaFondo.play();
		cortinaFondo.setVolume(volume);
	}
			
	if (bolsasCapturadas>0){
		try {
			gastarMonedas(bolsasCapturadas*cantidadMonedas);
				} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}				
	
	bolsasCapturadas=0;
	recargaScreen=false;
	
	
	
	if (miniJuegoGeneral!=null)
	{
		miniJuegoGeneral.dispose();
		miniJuegoGeneral=null;
		System.gc();
		System.out.println("entre a limpiar");
	}
	miniJuegoGeneral = new MiniJuegoGeneral(game);
	try {
		miniJuegoGeneral.enroquesRealizados=enroquesRealizados;
		miniJuegoGeneral.asignarPartida(partida, batalla, 
				minijuego,dificultad,configuracion
				);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if(Xmm.contains("MJN 23"))
	{
		System.out.println("viene del juego de enroque realizados:"+enroquesRealizados);
	}
	

	game.setScreen(miniJuegoGeneral);
	miniJuegoGeneral=null;
	System.gc();
}

/**
 * coloca el nombre de la batalla en que pertenece el minijuego
 */
public void nombreBatalla() {
	TextButtonStyle tbs = new TextButtonStyle();
	tbs.font= font;
	pack2 = new TextureAtlas(Gdx.files.internal("assets/skins/mapaGeneral.pack"));
	skin4.addRegions(pack2);

	tbs.up= skin4.getDrawable(batallaNombre);
	tbs.over= skin4.getDrawable(batallaNombre+"Aura");
	TextButton nombre = new TextButton("", tbs);
	nombre.addListener(new ClickListener() {
		public void clicked (InputEvent event, float x, float y) {
			
		
				cargarhistoria();;										
							
		}
	});
	nombre.setSize(246, 74);
	nombre.setPosition(520, 480);
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
	
	tbs.up= skin3.getDrawable("0");

	contadorHumano = new TextButton("", tbs);
	contadorHumano2 = new TextButton("", tbs);
	
	stage.addActor(contadorHumano);
	contadorHumano.setSize(30, 30);
	contadorHumano.setPosition(690, 430);
	
	stage.addActor(contadorHumano2);
	contadorHumano2.setSize(30, 30);
	contadorHumano2.setPosition(720, 430);
	
	if (cantidad>1)
	{
		 contadorMaquina = new TextButton("", tbs);
		 contadorMaquina2 = new TextButton("", tbs);
		 stage.addActor(contadorMaquina);
		 contadorMaquina.setSize(30,30);
		 contadorMaquina.setPosition(690, 350);
		
		 stage.addActor(contadorMaquina2);
		 contadorMaquina2.setSize(30, 30);
		 contadorMaquina2.setPosition(720, 350);
							
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
	   if (skin3!=null){
		   tbs.up= skin3.getDrawable(digito1);
		   tbs2.up= skin3.getDrawable(digito2);
	   }
		
	   
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
	if(tipoContadorHumano.equals(eContadores.ContadorJugadas.toString()))
	{	if(Xmm.contains("MJN 19")){
		actualizarContador(true, (limiteMovMJN19-contadorJugadas));	
		}
		else
		if(Xmm.contains("MJN 20")){
		actualizarContador(true, (limiteMovMJN20-contadorJugadas));	
		}
		
		else {
			actualizarContador(true, contadorJugadas);	
		}
		nombreContador(eContadores.ContadorJugadas,true);
	}
	else
	if(tipoContadorHumano.equals(eContadores.ContadorPromovidas.toString()))
	{System.out.println("contadorPromovidas:"+ contadorPromovidas);
		
		actualizarContador(true, contadorPromovidas);
		nombreContador(eContadores.ContadorPromovidas,true);
	}
	else
	if(tipoContadorHumano.equals(eContadores.ContadorJugadasPeon.toString()))
	{
		if(Xmm.contains("MJN 22"))
		{
		actualizarContador(false, (limiteMovMJN22-jugadasPeon));	
		}
		else
		{
		actualizarContador(false, jugadasPeon);	
		}	
		nombreContador(eContadores.ContadorJugadasPeon,true);
	}
	else
	if(tipoContadorHumano.equals(eContadores.ContadorDobleAtaque.toString()))
	{
		actualizarContador(true,contadorDobleAtaque);	
		nombreContador(eContadores.ContadorDobleAtaque,true);
	}
	else
	if(tipoContadorHumano.equals(eContadores.ContadorPeonAlPaso.toString()))
	{
		actualizarContador(true,contadorPeonAlPaso);	
		nombreContador(eContadores.ContadorPeonAlPaso,true);
	}
	else
	if(tipoContadorHumano.equals(eContadores.ContadorPaja.toString()))
	{
		actualizarContador(true,contadorPaja);	
		nombreContador(eContadores.ContadorPaja,true);
	}
	else
	if(tipoContadorHumano.equals(eContadores.ContadorEnrroqueRealizados.toString()))
	{
		actualizarContador(true,enroquesRealizados);	
		nombreContador(eContadores.ContadorEnrroqueRealizados,true);
	}
	else
		if(tipoContadorHumano.equals(eContadores.ContadorBolsasCapturadas.toString()))
		{
			actualizarContador(true,bolsasCapturadas);	
			nombreContador(eContadores.ContadorBolsasCapturadas,true);
		}
	else
		if(tipoContadorHumano.equals(eContadores.ContadorMovIncorrectos.toString()))
		{
			actualizarContador(true,contadorMovIncorrectos);	
			nombreContador(eContadores.ContadorMovIncorrectos,true);
		}
	}
}

/**
 * Comprobar tipo de contador Maquina
 */

public void comprobarContadoresMaquina(){
	if(pantallaContador){
	if(cantidadContador>1){
	if(tipoContadorMaquina.equals(eContadores.ContadorJugadas.toString()))
	{
		if(Xmm.contains("MJN 19")){
			actualizarContador(false, (limiteMovMJN19-contadorJugadas));	
			}
			else
			if(Xmm.contains("MJN 20")){
			actualizarContador(false, (limiteMovMJN20-contadorJugadas));	
			}
			else {
				actualizarContador(false, contadorJugadas);	
			}
		nombreContador(eContadores.ContadorJugadas,false);
	}
	
	else
	if(tipoContadorMaquina.equals(eContadores.ContadorPromovidas.toString()))
	{
		actualizarContador(false, contadorPromovidas);	
		nombreContador(eContadores.ContadorPromovidas,false);
	}
	else
	if(tipoContadorMaquina.equals(eContadores.ContadorJugadasPeon.toString()))
	{
		if(Xmm.contains("MJN 22"))
			{
			actualizarContador(false, (limiteMovMJN22-jugadasPeon));	
			}
			else
			{
			actualizarContador(false, jugadasPeon);	
			}	
		nombreContador(eContadores.ContadorJugadasPeon,false);
	}
	else
	if(tipoContadorMaquina.equals(eContadores.ContadorDobleAtaque.toString()))
	{
		actualizarContador(false,contadorDobleAtaque);	
		nombreContador(eContadores.ContadorDobleAtaque,false);
	}
	else
	if(tipoContadorMaquina.equals(eContadores.ContadorPeonAlPaso.toString()))
	{
		actualizarContador(false,contadorPeonAlPaso);	
		nombreContador(eContadores.ContadorPeonAlPaso,false);
	}
	else
	if(tipoContadorMaquina.equals(eContadores.ContadorPaja.toString()))
	{
		actualizarContador(false,contadorPaja);	
		nombreContador(eContadores.ContadorPaja,false);
	}
	else
	if(tipoContadorMaquina.equals(eContadores.ContadorEnrroqueRealizados.toString()))
	{
		actualizarContador(false,enroquesRealizados);	
		nombreContador(eContadores.ContadorEnrroqueRealizados,false);
	}
	else
		if(tipoContadorMaquina.equals(eContadores.ContadorBolsasCapturadas.toString()))
		{
			actualizarContador(false,bolsasCapturadas);	
			nombreContador(eContadores.ContadorBolsasCapturadas,false);
		}
	else
		if(tipoContadorMaquina.equals(eContadores.ContadorMovIncorrectos.toString()))
		{
			actualizarContador(false,contadorMovIncorrectos);	
			nombreContador(eContadores.ContadorMovIncorrectos,false);
		}
}
	}}

/**
 * Metodo para pausar el reloj cuando se esta reproduciendo algun video
 */
public void pausarReloj(){
	flag=false;
	rotacionIni=(int) reloj.getRotation();
	rotacionAcc=rotacionAcc+(int) rotateAction.getRotation();
	tiRotacion=tiRotacion+(int) rotateAction.getTime();
	reloj.clearActions();
}

/**
 * Metodo para reanudar el reloj cuando se deja de reproducir algun video
 */
public void reanudarReloj(){

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

/**
 * Metodo para cambiar la textura del rey segun sea el avatar que seleccionó el niño
 */
public void cambiarTexturaRey(){
	for(int i=0;i<64;i++){
		if(casillas[i].getPieza()!=null){
			if(casillas[i].getPieza().getTipoPieza().equals(eTipoPieza.Rey)&&casillas[i].getPieza().getColor().equals(Color.WHITE)){
				Pieza pieza=casillas[i].getPieza();
				((Rey) pieza).setAvatar(avatar);
				pieza.cambiarEstadoPieza(eEstadoPieza.Esperando);					
			}
		}	
	}
}

/**
 * Cambia el color de las bolsas para que puedan ser capturadas en cualquier turno
 * @param pColor turno de jugador  actual
 */
public void cambiarColorMonedas(Color pColor){
	for (int i =0;i<=63; i++){
		try{		
			if (casillas[i].getActor().equals(TipoDeActor.BOLSA))		
				if (pColor.equals(Color.WHITE))
					 casillas[i].getActor().setColor(Color.BLACK);
				else
					 casillas[i].getActor().setColor(Color.WHITE);

		}
		catch(Exception e) {							
		}								
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
image2.setPosition(520, 425);
image.setPosition(525, 425);
}
else
{
image2.setPosition(520, 345);
image.setPosition(525, 345);
}

image.setSize(150, 30);
image2.setSize(250, 40);
stage.addActor(image);
stage.addActor(image2);
image.setZIndex(0);
image2.setZIndex(0);
}

private void setVolumen(){
	cortinaFondo.setVolume(super.getVolume());	
}

private void iniciarMotor(){
	
	conversacion.start();
	while (!conversacion.isArriba()) {
		System.out.println("conversacion esta abajo");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		motorIniciado=true;
	}
	
	System.out.println("Xmm: " + Xmm);
	IniciariMiniJuegoMangoPaola(Xmm);						 											
	enviarMangoPaola(valorDificultad);
	
	String[] vPiezasAMantener= null;
	
	try {
		vPiezasAMantener = configuracion.GetAtributo(batalla, "Minijuego", minijuego, "PiezasAMantener").split(" ");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	System.out.println("");
			

	if(agregarMurosFen){
		
		ArrayList<String> muros=(enviarMangoPaola("MUR"+" "+colorMurosFen+" "+bloquearSalida+" "+intensidadMurosFen));
		String[] fen=muros.toString().split(",");
		fen[1]=fen[1].substring(1);
		String[] fen2=fen[1].split("]");
		
		txcargaFen=fen2[0];
	
	}

	CargarFen();
	enviarComandosBanderas();
	cambiarTexturaRey();
	colocarImagenes();
	ConfigurarPiezasAMantener(vPiezasAMantener);	
	System.out.println("termine la configuracion");
	
	if (mostrarMonedas && cantidadBolsas>0){		
		 bolsas = (enviarMangoPaola("ATE "+ (cantidadBolsas + agregarBolsasExtra)));			 
		 pintarActor(bolsas,TipoDeActor.BOLSA);		
		 
	}
	
	if (mostrarPaja && cantidadPaja>0)
	{
		
		 pajas = (enviarMangoPaola("ATE "+ cantidadPaja));	
		 pintarActor(pajas,TipoDeActor.PAJA);
	}
	
	peonalpaso = null;

	

	if (mostrarAlcanceOponente){
		alcanceOponente(piezasExcluidas);
		}

	
	if(pantallaContador)
	{
		contador(cantidadContador);
		comprobarContadoresHumano();
		comprobarContadoresMaquina();
	}
	nombreBatalla();
	nombrePersonaje();
if (mostrarAlcanceOponente){
	alcanceOponente(piezasExcluidas);
	}
	if(Xmm.contains("MJN 20")){
		agregarMurosManual(10);
	}
	else
	if(Xmm.contains("MJN 13"))	{
		 eliminarMonedasManual();
		 agregarMonedasManual();
		}
	else
		if(Xmm.contains("MJN 17"))	{
			eliminarMurosManual();
			agregarMurosManual(35);
			}
	this.setTurno(Color.WHITE); // toca jugar a ls blancas
	this.habilitarPiezas(eColores.Blancas);
//	Gdx.input.setInputProcessor(stage);
}

 
}
//mjn 11 seda cuenta despues de que se acabaron las bolsas
