//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//               mapaGeneralScreen.java                     //
//                   Descripcion                            //
//   Clase encargada de mostrar el mapa general del juego,  //
//   permitir al usuario que elija la batalla que desea y   //
//   Mover al avatar entre las estaciones                   //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      28/03/2016     Version Inicial       //
//Vladimir Betancourt  01/04/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.Partida;
import com.jayktec.grafico.Estacion;
import com.jayktec.grafico.Heroe_Dificultad;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Enums.eTipoPersonaje;
import com.jayktec.grafico.Piezas.Casilla;
import com.jayktec.grafico.Piezas.Tablero;
import com.jayktec.grafico.Principal.ScreenJuegoPrincipal;
import com.sun.java.swing.plaf.gtk.resources.gtk_de;

import de.tomgrill.gdxdialogs.core.dialogs.FallbackGDXButtonDialog;

public class mapaGeneralScreen extends ScreenManager {
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	private Stage stage;
	TextButtonStyle styleNomb, trinceras, puerta, victoria, matasiete, carabobo, naval, bFinal,bSecreta;
	public Heroe_Dificultad myActor;
	public Miniheroes game;
	public int anchoCasilla, altoCasilla;
	Casilla casilla;
	Tablero tablero;
	private TextButton atras, siguiente, nombre, volver;
	private boolean clickback = false, clickSig = false,jugar=false;
	private Skin skin3,skin4,skinEstaciones;
	private TextureAtlas  pack,pack2,packEstaciones;
	private BitmapFont font;
	public boolean flag = true,flag2=false,flag3=false,flag4=false,flag5=false,flag6=false;
	// public Dialog dialog;
	private String tilemap;
	private String nombreBatalla;
	ArrayList<Estacion> estacion;
	private int estacionActual = 0;
	private Texture textureVen;
	private Image imgVen;
	int i = 0;
	int count = 0, c = 0, x=0;
	float duracion, duracion2;
	private String avatar;
	private float tiempo = .9f;
	private String avanzar = "avanzar";
	private String retroceder = "retroceder";
	private int batalla = 1; // primer mundo o batalla
	private Image bandera;
	private Texture txbandera, tximgEstacionPass,tximgEstacionPass2, tximgEstacionRep;
	private float posInicialX, posInicialY;
	private float[] posEstX;
	private boolean showEnd=false;
	private float[] posEstY;
	private boolean posicionInicial;
	private Texture texturaPersonaje;
	private Partida partida;
	private TextureAtlas animacionIzq,animacionDer,animacionArri,animacionAba;
	// private Texture nombreB ;
	private Dificultad dificultad;
	private Configuracion configuracion;
	private String pTilemap;
	private TextButton[] imgEstacionPass=new TextButton[20];
	private TextButton[] imgEstacionRep=new TextButton[20];
	private ScreenVideo a;
	TextButtonStyle estAzul = new TextButtonStyle();
	TextButtonStyle estRoja = new TextButtonStyle();
	private float timecount;
	/**
	 * Clase encargada de mostrar el mapa general del juego, permitir al usuario
	 * que elija la batalla que desea y Mover al avatar entre las estaciones
	 * 
	 * @param pGame
	 *            Variable para que la clase Miniheroes.java genere la pantalla
	 * @param pTilemap
	 *            Varible en donde se setea el tilemap de la pantalla
	 *            Constructor para el mapa general
	 * 
	 * @throws Exception
	 *             si no puede obtener los datos de asignar partida
	 */

	public mapaGeneralScreen(Miniheroes pGame, String pTilemap) throws Exception {
		super(pGame);
		game = pGame;
		this.pTilemap=pTilemap;
		styleNomb = new TextButtonStyle();
		trinceras = new TextButtonStyle();
		puerta = new TextButtonStyle();
		victoria = new TextButtonStyle();
		matasiete = new TextButtonStyle();
		carabobo = new TextButtonStyle();
		naval = new TextButtonStyle();
		bFinal = new TextButtonStyle();
		bSecreta=new TextButtonStyle();
		myActor = new Heroe_Dificultad(game);
		myActor.setSize(40,70);
		
		packEstaciones=game.getManager().get("assets/skins/packEstaciones.pack");
		skinEstaciones=new Skin();
		skinEstaciones.addRegions(packEstaciones);
		
		
		
		font = new BitmapFont();
		skin3 = new Skin();
		skin4 = new Skin();
		pack = new TextureAtlas(Gdx.files.internal("assets/skins/mapaGeneral.pack"));
		pack2= new TextureAtlas(Gdx.files.internal("assets/skins/botones.pack"));
		
		skin3.addRegions(pack);
		skin4.addRegions(pack2);
		
		trinceras.up = skin3.getDrawable("baTrincheras");
		trinceras.over = skin3.getDrawable("baTrincherasAura");
		puerta.up = skin3.getDrawable("baPuerta");
		puerta.over = skin3.getDrawable("baPuertaAura");
		victoria.up = skin3.getDrawable("baVictoria");
		victoria.over = skin3.getDrawable("baVictoriaAura");
		matasiete.up = skin3.getDrawable("baMatasiete");
		matasiete.over = skin3.getDrawable("baMatasieteAura");
		carabobo.up = skin3.getDrawable("baCarabobo");
		carabobo.over = skin3.getDrawable("baCaraboboAura");
		naval.up = skin3.getDrawable("baLago");
		naval.over = skin3.getDrawable("baLagoAura");
		bFinal.up = skin3.getDrawable("baFinal");
		bFinal.over = skin3.getDrawable("baFinalAura");
		bSecreta.up =skin3.getDrawable("batalla secreta");
		bSecreta.over =skin3.getDrawable("batalla secretaAura");
		
		
		estAzul.font = font;
		estAzul.up=skinEstaciones.getDrawable("estacionPass");
		estAzul.over=skinEstaciones.getDrawable("estacionPass2");
		
		estRoja.font = font;
		estRoja.up=skinEstaciones.getDrawable("estacionRep");
		

	}


	@Override
	public void show() {
		stage = new Stage();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		showEnd=false;
		texturaPersonaje=myActor.getTexture();
		if (!musicaFondo.isPlaying())
			{
				musicaFondo.play();
			}
		cargarAnimaciones();
		botones();
		CambiarNombre();
		
		Timer.schedule(new Task() {@Override public void run() {showEnd=true;}	}, 0.2f);

		for (Estacion estacion : estacion)
			{
				if (estacion != null)
					{
						count = count + 1;
					}
			}
		dibujarEstaciones();
		if(estacionActual==count-1&&posicionInicial==false){
			stage.getCamera().translate(466, 122, 0);
			cargarPosiciones(2);
		}
		else{
			stage.getCamera().translate(0, 465, 0);
			cargarPosiciones(1);
		}
		camera = (OrthographicCamera) stage.getCamera();
		tiledMap = new TmxMapLoader().load(tilemap);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		stage.addActor(myActor);
		Gdx.input.setInputProcessor(stage);
		System.out.println("finalizando show");
//		System.out.println("stage position:"+camera.position.y);

	}

	
	
	/**
	 * Asignar una partida a un juego con el nombre del mismo y su respectivo
	 * avatar.
	 * 
	 * @param Partida
	 *            objeto con todas las caracteristicas de la partida
	 * @return el valor dependiendo si pudo o no asignar la partida
	 * @author yisheng
	 */
	
	public Boolean asignarPartida(
			Partida partida,
			Dificultad dificultad,
			Configuracion configuracion,
			boolean pPosicion) {

		try {
			posicionInicial=pPosicion;
			this.avatar = partida.getPersonaje();
//			System.out.println("avatar : " + avatar);
			nombreBatalla = partida.getBatalla();
			this.partida=partida;
			this.batalla = Integer.parseInt(nombreBatalla.substring(7));
			this.dificultad= dificultad;
			this.configuracion=configuracion;
		/// cambio de configuracion
			
			tilemap = configuracion.GetAtributo(pTilemap, "Mapa", "mapa", "Fondo");
//			System.out.println("tiled : "+tilemap);
//			System.out.println("ancho : "+ Configuracion.GetAtributo(pTilemap, "Mapa", "mapa", "Fondo"));
			anchoCasilla = Integer.parseInt(configuracion.GetAtributo(pTilemap, "Mapa", "mapa", "AnchoCasilla"));
			altoCasilla = Integer.parseInt(configuracion.GetAtributo(pTilemap, "Mapa", "mapa", "AltoCasilla"));
			estacion = configuracion.GetEstaciones(pTilemap);// tablero.getEstacion();
		//
			myActor.personaje(avatar, eTipoPersonaje.Personaje);
			
			estacionActual = estacion();
			System.out.println("estacion actual:"+estacionActual);
			if(!pPosicion)
				{
					posInicialX = estacion.get(estacionActual).getX();
					posInicialY = estacion.get(estacionActual).getY();
				}
			else
				{
					posInicialX = estacion.get(estacionActual-2).getX();
					posInicialY = estacion.get(estacionActual-2).getY();
					estacionActual=estacionActual-2;
					Timer.schedule(new Task() {
						@Override
						public void run() {
							flag6=true;
						}
					}, 0.2f);
					
				}
			myActor.setPosition(posInicialX, posInicialY);
			return true;
		} catch (Exception e) {

			avatar = "";
			System.out.println("buu" + e.getMessage().toString());
			return false;
		}

	}

	
	
	
	
	
	@Override
	public void render(float delta) {
	
		
	
		if(Gdx.input.getInputProcessor()==null){
			Gdx.input.setInputProcessor(stage);
		}
		
		
		timecount+=delta;
		if(timecount>=2){
		showEnd=true;
		timecount=0;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
	
		float actualX = myActor.getX();
		float actualY = myActor.getY();
		float destinoX = estacion.get(estacionActual).getX();
		float destinoY = estacion.get(estacionActual).getY();

		if(myActor.getY()>=600 && myActor.getX()>=206 && estacionActual==count-3){
			cambiarPersonaje("barco");
		}
		else
			if(myActor.getY()<=600&&estacionActual==count-1){
				cambiarPersonaje(avatar);
				myActor.setTexture(null);
				myActor.setPack(animacionAba);
			}
		
if ((clickSig == true || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) && estacionActual < count - 1
		&& batalla(estacionActual) < batalla)
		{				
			if(estacionActual == count - 3 && actualX == destinoX && actualY == destinoY)
				{
					flag2=true;
				}
		}
else if(flag2==true)
		{
			if(camera.position.x<890)
				{	
					camera.position.x+=100.0f * Gdx.graphics.getDeltaTime();
					camera.update();
				}
			else
				{
				flag2=false;
				}
		}

if ((clickback == true || Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) && estacionActual > 0 &&flag3==false) 
		{
			if(estacionActual == count - 1&& actualX == destinoX && actualY == destinoY)
				{
					flag3=true;
				}
		}

else if(flag3==true)
{
	if(camera.position.y<=741)
		{	
			camera.position.y+=100.0f * Gdx.graphics.getDeltaTime();
			camera.update();
		}
	else
		{
		flag3=false;
		}
}

if(flag4==true)
{
	camera.position.y-=100.0f * Gdx.graphics.getDeltaTime();
	camera.update();
	if(camera.position.y<=400)
	{	
		flag4=false;
	}

}
if(flag5==true)
{
	camera.position.x-=100.0f * Gdx.graphics.getDeltaTime();
	camera.update();
	if(camera.position.x<=425)
	{	
		flag5=false;
	}

}

if(myActor.getActions().size==0)
{
myActor.setTexture(texturaPersonaje);
}


		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		mover();
		
		Gdx.graphics.setContinuousRendering(true);
		if(camera!=null)
		camera.update();
	}

	@Override
	public void hide() {
		Timer.instance().clear();
//		skinEstaciones.dispose();
//		skinEstaciones=null;
		myActor.getActions().clear();
//		skin4.dispose();
//		pack2.dispose();
		showEnd=false;
		c = 0;
		count = 0;
		i = 0;
//		skin3.dispose();
		tiledMap.dispose();
		
//		font.dispose();			
//		stage.dispose();
//		pack.dispose();
		
		stage.dispose();
		stage=null;
		camera=null;
		
		 posEstX = null;
		 posEstY = null;
		
		System.out.println("tiledmap: "+tilemap);
		tiledMap=null;
		tiledMapRenderer=null;
//		camera=null;
		
//		animacionAba.dispose();
//		animacionArri.dispose();
//		animacionDer.dispose();
//		animacionIzq.dispose();}
		
//		styleNomb = null;
//		trinceras = null;
//		puerta = null;
//		victoria = null;
//		matasiete = null;
//		carabobo = null;
//		naval = null;
//		bFinal = null;
//		bSecreta=null;
//		myActor =null;
		Gdx.input.setInputProcessor(null);

//		 stage=null;
//		 myActor=null;
//		 game=null;
//		 atras=null;
//		 siguiente=null;
//		 nombre=null;
//		 volver=null;	
//		 skin3=null;
//		 skin4=null;
//		 skinEstaciones=null;
//		  pack=null;
//		  pack2=null;
//		 font=null;
//		 estacion=null;
//         posEstX =null;	
//		 posEstY =null;	
//	    imgEstacionPass=null;
//		 imgEstacionRep=null;
//		 a=null;

//		 try {
//			this.finalize();
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		
		 System.gc();
		
	}

	@Override
	public void dispose() {
		
		System.gc();
		System.gc();
//		super.dispose();
	}

	/**
	 * devuelve el numero de batalla de la estacion , si la estación no es una
	 * batalla devuelve el valor de la ultima batalla
	 * 
	 * @param estacionActual
	 *            estacion en que se encuentra el avatar
	 * @return
	 */
	private int batalla(int estacionActual) {
		int i = 0;

		for (int j = 0; j <= estacionActual; j++) {

			if (estacion.get(j).isEstacionar()) {

				i++;

			}
		}

		return i;
	}

	/**
	 * devuelve el valor de la estacion de la batalla actual de la partida o -1
	 * sino la consigue
	 * 
	 * @return
	 * @author yisheng
	 */
	private int estacion() {
		int x = 0;
		for (int j = 0; j < estacion.size(); j++) {

			if (estacion.get(j).getMinijuego().equals(nombreBatalla)) {

				return x;
			}
			x++;
		}

		return -1;
	}

	/**
	 * 
	 * Metodo encargado de mover al personaje atraves del mapa asi como de
	 * mostrar el dialogo cuando el personaje se encuentre en la casilla de cada
	 * nivel
	 */
	public void mover() {

		float actualX = myActor.getX();
		float actualY = myActor.getY();
		float destinoX=estacion.get(estacionActual).getX(); 
		float destinoY=estacion.get(estacionActual).getY(); 
	
		
		
		if (actualX == destinoX && actualY == destinoY) {
			CambiarNombre();
		nombre.setStyle(styleNomb);

			int est=comprobarBoton();
			if(est!=99)
				{
					if(est>x)
						{
							clickSig=true;
						}
					else if(est<x)
						{
							clickback=true;
						}
					else{
						
						jugar=true;
					}
				}
			cambiarPersonaje(avatar);
			if (Gdx.input.isKeyPressed(Keys.ENTER)&& showEnd==true || nombre.isPressed()&& showEnd==true||jugar==true&&showEnd==true ) {
				jugar=false;
				try {
//					game.reiniciarScreens(this.partida, this.dificultad, this.configuracion, posicionInicial);
					
					
					
					
					
					if (Boolean.parseBoolean(configuracion.GetAtributo(estacion.get(estacionActual).getMinijuego(),
							"Mapa", "mapa", "VideoPlayer"))) {
						stopMusic();
						
						
						if (a!=null)
						{
							a.dispose();
							a=null;
							System.gc();
							System.out.println("entre a limpiar");
						}
						a = new ScreenVideo(game);
						try {
							a.asignarPartida(configuracion.GetAtributo(estacion.get(estacionActual).getMinijuego(),
									"Mapa", "mapa", "Video"), partida, estacion.get(estacionActual).getMinijuego(),dificultad,configuracion
									);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						

						game.setScreen(a);
						a=null;
						System.gc();
						
						
//						game.video.asignarPartida(configuracion.GetAtributo(estacion.get(estacionActual).getMinijuego(),
//								"Mapa", "mapa", "Video"), partida, estacion.get(estacionActual).getMinijuego(),dificultad,configuracion);
//						game.setScreen(game.video);
					} else {
							
						if (this.partida.getMinijuego().equals("Minijuego36") && estacionActual==count -1)
						{
							game.juegoPrincipal.setHistoria(false);
							game.juegoPrincipal= new ScreenJuegoPrincipal(game);
							game.setScreen(game.juegoPrincipal);
						}
						else
						{

							//System.out.println("nombrePartida:" +nombrePartida + " estacion "+estacion.get(estacionActual).getMinijuego());

//				game.load.screenFrom(game.mapMin,partida,estacion.get(estacionActual).getMinijuego(),dificultad,configuracion,false);
//				game.setScreen(game.load);
				game.mapMin.asignarPartida(partida,estacion.get(estacionActual).getMinijuego(),dificultad,configuracion,false);
				game.setScreen(game.mapMin);

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


			if ((clickSig == true || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) && estacionActual < count - 1
					&& batalla(estacionActual) < batalla|| flag6==true)

			{
				flag6=false;
				if(estacionActual == count - 3){
//					System.out.println("cambiando a barco");
					cargarPosiciones(2);
					cambiarPersonaje("barco");
					flag2=true;
				}else{
					determinarMov(estacionActual, estacionActual+1,myActor);
				}
				
				duracion = calcularDuracion(estacionActual, avanzar);
				estacionActual++;
				clickSig=false;
				myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
						estacion.get(estacionActual).getY(), duracion));
			
				
				
				
				if (estacion.get(estacionActual).isEstacionar() == false) {
					while (estacion.get(estacionActual).isEstacionar() == false) {
						duracion2 = calcularDuracion(estacionActual, avanzar);
						estacionActual++;
						Timer.schedule(new Task() {
							@Override
							public void run() {
								
								if(estacionActual == count - 1){
								flag4=true;
								}
								else{
									determinarMov(estacionActual-1, estacionActual,myActor);
								}
								myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
										estacion.get(estacionActual).getY(), duracion2));
							}
						}, duracion);

					}
				}

			} else if ((clickback == true || Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) && estacionActual > 0) {
				
				if(estacionActual == count - 1){
					
					cargarPosiciones(1);

					flag3=true;
					flag4=false;
					
				}
				determinarMov(estacionActual, estacionActual-1,myActor);

				duracion = calcularDuracion(estacionActual, retroceder);
				estacionActual--;
				myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
						estacion.get(estacionActual).getY(), duracion));

				if (estacion.get(estacionActual).isEstacionar() == false) {
					while (estacion.get(estacionActual).isEstacionar() == false) {
						duracion2 = calcularDuracion(estacionActual, retroceder);
						estacionActual--;
						Timer.schedule(new Task() {
							@Override
							public void run() {
								determinarMov(estacionActual+1, estacionActual,myActor);

								if(estacionActual == count - 3){
									flag5=true;
									}
								myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
										estacion.get(estacionActual).getY(), duracion2));
							}
						}, duracion);
					}
				}

			} else {
				clickSig = false;
				clickback = false;

			}
		} else {
			clickSig = false;
			clickback = false;
		}
	}

	@Override
	public void resize(int width, int height) {
//
//		camera.setToOrtho(false, width, height);
//		camera.viewportWidth = width;
//		camera.viewportHeight = height;
//		if(Gdx.graphics.isFullscreen()){
//		if(estacionActual==count-1){
//			stage.getCamera().translate(466, 122, 0);
//			cargarPosiciones(2);
//			
//			
//		}
//		else{
//			stage.getCamera().translate(0, 465, 0);
//			cargarPosiciones(1);
//		}
//		}
////		camera.position.x = super.getGameWidth() / 2;
////		camera.position.y = super.getGameHeight() / 2;

	}

	/**
	 * Metodo que cambia el nombre de la batalla segun la posicion del actor
	 * 
	 * @return textura con el nombre de la batalla
	 */

	public TextButtonStyle CambiarNombre() {
		

		if (estacionActual == 0) {
			styleNomb = trinceras;
			x=0;
		} else if (estacionActual == 2) {
			styleNomb = puerta;
			x=1;
		} else if (estacionActual == 4) {
			styleNomb = victoria;
			x=2;
		} else if (estacionActual == 6) {
			styleNomb = matasiete;
			x=3;
		} else if (estacionActual == 8) {
			styleNomb = carabobo;
			x=4;
		} else if (estacionActual == 10) {
			styleNomb = naval;
			x=5;
		} else if (estacionActual == 12) {
			styleNomb = bFinal;
			x=6;
		} else if (estacionActual == 14) {
			styleNomb = bSecreta;
			x=7;
		}else {
			styleNomb = bFinal;
		}

		return styleNomb;
	}

	/**
	 * 
	 * Metodo que calcula el tiempo que el actor debe emplear para llegar a una
	 * estacion
	 * 
	 * @param pEstacionActual
	 *            estacion actual del actor
	 * @return tiempo empleado para que el actor se mueva a una estacion
	 */
	private float calcularDuracion(int pEstacionActual, String pDireccion) {
		float pDuracion = 0;
		double distancia = 0;
		estacionActual = pEstacionActual;
		if (pDireccion.equals(avanzar)) {
			distancia = Math.sqrt(
					(Math.pow(estacion.get(estacionActual + 1).getX() - estacion.get(estacionActual).getX(), 2)) + Math
							.pow(estacion.get(estacionActual + 1).getY() - estacion.get(estacionActual).getY(), 2));
		} else if (pDireccion.equals(retroceder)) {
			distancia = Math.sqrt(
					(Math.pow(estacion.get(estacionActual - 1).getX() - estacion.get(estacionActual).getX(), 2)) + Math
							.pow(estacion.get(estacionActual - 1).getY() - estacion.get(estacionActual).getY(), 2));
		} else {
			System.out.println("Error de direccion verifique el valor");
		}
		pDuracion = ((float) distancia / ((anchoCasilla + altoCasilla) / 2)) * tiempo;

		return pDuracion;

	}

	private void cambiarPersonaje(String pPersonaje){
		
myActor.personaje(pPersonaje,eTipoPersonaje.Personaje);		
	
	}
	
private void cargarPosiciones(int pNum){
	siguiente.setSize(50, 40);
	atras.setSize(50, 40);
	nombre.setSize(246, 74);
	if(pNum==1)
		{
		imgVen.setPosition(20, 515);

		nombre.setPosition((super.getGameWidth() / 2) - (nombre.getWidth() / 2), 900);
		siguiente.setPosition(((super.getGameWidth() / 2) - (siguiente.getWidth() / 2)) + 150, 920);
		atras.setPosition(((super.getGameWidth() / 2) - (atras.getWidth() / 2)) - 150, 920);
		volver.setPosition(15, 470);
		}
	else
		{
		imgVen.setPosition(490, 260);

		nombre.setPosition(770, 550);
		siguiente.setPosition(770 + 250, 570);
		atras.setPosition(770 - 50, 570);
		volver.setPosition(485, 130);
		}
	}


/**
 * Metodo que determinar la direccion de movimiento del avatar utilizado
 * @param posIni posicion de inicio del movimiento del avatar
 * @param posFin posicion final del movimiento del avatar 
 * @return direccion del movimiento
 */
public String determinarMov(int posIni,int posFin,Heroe_Dificultad pMyActor){
	
	float actorX=estacion.get(posIni).getX();
	float actorY=estacion.get(posIni).getY();
	float destinoX=estacion.get(posFin).getX();
	float destinoY=estacion.get(posFin).getY();
	
//	System.out.println(actorX);
//	System.out.println(destinoX);
//	System.out.println(actorY);
//	System.out.println(destinoY);
//	
	String verticalup="arriba";
	String HorizontalDer="derecha";
	String verticalDown="abajo";
	String HorizontalIzq="izquierda";
	
	if(actorX==destinoX && actorY<destinoY)
		{
//			System.out.println("arriba");
			pMyActor.setTexture(null);
			pMyActor.setPack(animacionArri);
			return verticalup;	
		}
	else 
	if(actorX==destinoX && actorY>destinoY)
		{
//		System.out.println("abajo");
		pMyActor.setTexture(null);
		pMyActor.setPack(animacionAba);
			return verticalDown;			
		}
	else 
	if(actorY==destinoY && actorX<destinoX)
		{
//			System.out.println("derecha");
			pMyActor.setTexture(null);
			pMyActor.setPack(animacionDer);
			return HorizontalDer;
		}
	else 
	if(actorY==destinoY && actorX>destinoX)
		{
//			System.out.println("izquierda");
			pMyActor.setTexture(null);
			pMyActor.setPack(animacionIzq);

			return HorizontalIzq;
		}
	
//System.out.println("retornando null");
	return null;	
}
	
/**
 * Metodo que genera y muestra los botones  
 */
	private void botones() {
		TextButtonStyle styleder = new TextButtonStyle();
		TextButtonStyle styleizq = new TextButtonStyle();
		TextButtonStyle stylevol = new TextButtonStyle();

		styleder.font = font;
		stylevol.font = font;
		styleizq.font = font;
		styleNomb.font = font;
		
		trinceras.font = font;
		puerta.font = font;
		victoria.font = font;
		matasiete.font = font;
		carabobo.font = font;
		naval.font = font;
		bFinal.font = font;
		bSecreta.font=font;
		styleder.up = skin3.getDrawable("der");
		styleder.down = skin3.getDrawable("derDown");
		styleizq.up = skin3.getDrawable("izq");
		styleizq.down = skin3.getDrawable("izqDown");
		
		stylevol.up = skin4.getDrawable("salirUp");
		stylevol.down = skin4.getDrawable("salirDown");
		stylevol.over = skin4.getDrawable("salirOver");
		
		siguiente = new TextButton("", styleder);
		atras = new TextButton("", styleizq);
		nombre = new TextButton("", styleNomb);
		volver = new TextButton("", stylevol);

		siguiente.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				clickSig = true;
			}

		});
		atras.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				clickback = true;
			}

		});
		volver.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.inicio);
			}

		});
		
		stage.addActor(volver);
		stage.addActor(nombre);
		stage.addActor(atras);
		stage.addActor(siguiente);

	}	
	/**
	 * Metodo que genera y muestra los botones  
	 */
		private void dibujarEstaciones() {
			
			 posEstX = new float[8];
			 posEstY = new float[8];
			
			
			for (int i = 0; i < estacion.size(); i++) {
				Estacion estaci = estacion.get(i);
				if (estaci.isEstacionar()) {
//					System.out.println("estaciones : "+c);
					posEstX[c] = estaci.getX() - 2;
					posEstY[c] = estaci.getY() - 1;
					c = c + 1;
				}
			}
			
			txbandera = game.getManager().get("assets/Texturas/banderita.png");
//			tximgEstacionPass = game.getManager().get("assets/Texturas/estacionPass.png");
//			tximgEstacionPass2 = game.getManager().get("assets/Texturas/estacionPass2.png");
//			tximgEstacionRep = game.getManager().get("assets/Texturas/estacionRep.png");
			textureVen=game.getManager().get("assets/Texturas/venezuela.png");
			imgVen=new Image(textureVen);
			bandera=new Image(txbandera);
			stage.addActor(imgVen);
			
			bandera=new Image(txbandera);
			
			
			for (int i = 0; i < c; i++) {
				if (batalla > i) {
					
					
					imgEstacionPass[i] =new TextButton("", estAzul);
					stage.addActor(imgEstacionPass[i]);
					imgEstacionPass[i].setPosition(posEstX[i] + imgEstacionPass[i].getWidth(), posEstY[i]);
					imgEstacionPass[i].setSize(15, 15);
					imgEstacionPass[i].setZIndex(0);
				} else {
					
					
					imgEstacionRep[i] =new TextButton("", estRoja);
					
					stage.addActor(imgEstacionRep[i]);
					imgEstacionRep[i].setPosition(posEstX[i] + imgEstacionRep[i].getWidth(), posEstY[i]);
					imgEstacionRep[i].setSize(15, 15);
					imgEstacionRep[i].setZIndex(0);
					imgEstacionRep[i].setDisabled(true);
				}
			}
			
			stage.addActor(bandera);
			bandera.setPosition(posInicialX + bandera.getWidth(),posInicialY+20);
		
			
		
			
			
		}
	
/**
 * Carga las animaciones segun sea el ni�o		
 */
		
public void cargarAnimaciones(){
	
	if(avatar.equals("Sebastian"))
		{
		animacionIzq=game.getManager().get( "assets/skins/ninos/sebastian/sebasizquierda.pack");
		animacionDer=game.getManager().get( "assets/skins/ninos/sebastian/sebasDerecha.pack");
		animacionArri=game.getManager().get("assets/skins/ninos/sebastian/sebasEspalda.pack");
		animacionAba=game.getManager().get("assets/skins/ninos/sebastian/sebasFrontal.pack");
		}
	else
	if (avatar.equals("Camila")) 
		{
		animacionIzq=game.getManager().get( "assets/skins/ninos/camila/camilaIzquierda.pack");
		animacionDer=game.getManager().get( "assets/skins/ninos/camila/camilaDerecha.pack");
		animacionArri=game.getManager().get("assets/skins/ninos/camila/camilaEspalda.pack");
		animacionAba=game.getManager().get( "assets/skins/ninos/camila/camilaFrontal.pack");
		}
	else{
		animacionIzq=game.getManager().get( "assets/skins/ninos/andres/andresIzquierda.pack");
		animacionDer=game.getManager().get( "assets/skins/ninos/andres/andresDerecha.pack");
		animacionArri=game.getManager().get("assets/skins/ninos/andres/andresEspalda.pack");
		animacionAba=game.getManager().get( "assets/skins/ninos/andres/andresFrontal.pack");
		}
}		
		
public int comprobarBoton(){
	int i;
	for (i = 0; i < c; i++) 
		{if(imgEstacionPass[i]!=null){
			if(imgEstacionPass[i].isPressed()){
	System.out.println("retornando :"+ i);
			return i;
			}
		}
		}
	
	
	return 99;
}		
}
