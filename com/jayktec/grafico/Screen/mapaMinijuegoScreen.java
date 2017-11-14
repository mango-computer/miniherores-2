//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//               mapaMinijuegoScreen.java                   //
//                   Descripcion                            //
//     clase encargada de los mapas de los minijuegos       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      05/04/2016     Version Inicial       //
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
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.archivos.Configuracion;
import com.jayktec.archivos.Dificultad;
import com.jayktec.archivos.Partida;
import com.jayktec.grafico.Enums.eTipoPersonaje;
import com.jayktec.grafico.Enums.eTipoTrivia;
import com.jayktec.grafico.Estacion;
import com.jayktec.grafico.Heroe_Dificultad;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Preferencias;
import com.jayktec.grafico.Minijuegos.MiniJuegoGeneral;
import com.jayktec.grafico.Piezas.ActorExtra;
import com.jayktec.grafico.Piezas.ActorExtra.TipoDeActor;
import com.jayktec.grafico.Piezas.Casilla;
import com.jayktec.grafico.Piezas.Tablero;;

public class mapaMinijuegoScreen extends ScreenManager {
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	private Stage stage;
	public Heroe_Dificultad myActor;
	public Miniheroes game;
	public int anchoCasilla, altoCasilla;
	
	private Skin skin,skin3;
	private BitmapFont font;
	private String tilemap;
	ArrayList<Estacion> estacion;
	private int estacionActual = 0;
	int i = 0;
	private boolean clickback = false, clickSig = false,jugar=false;
	int count = 0, c = 0;
	private TextButton volver;
	private TextureAtlas pack,pack2;
	public float duracion2 = 0, duracion = 0;
	private String batalla;
	private String avatar;
	private int minijuego;
	private Configuracion configuracion;
	private String nombreMinijuego;
	private String avanzar = "avanzar";
	private String retroceder = "retroceder";
	private float tiempo = 0.04f;
	private ActorExtra pared;
	private boolean showEnd=false;
	boolean ultimoAnt=false;
	private boolean avanzarEstacion;
	private int numMin;
	private float[] posEstX ;
	private float[] posEstY ;
	private Preferencias preferencias;
	private Texture texturaPersonaje;
	private TextureAtlas animacionIzq,animacionDer,animacionArri,animacionAba;
	
	private ScreenManager screenFrom;
	private String batallaPartida;
	
	private Partida partida;
	private Dificultad dificultad;
	private TextButton[] estacionRoja;
	private TextButton[] estacionAzul;
	public MiniJuegoGeneral a;
//	public mapaGeneralScreen mapGen;
	private TextureAtlas packEstaciones;
	private Skin skinEstaciones;
	TextButtonStyle stylevol = new TextButtonStyle();
	TextButtonStyle estAzul = new TextButtonStyle();
	TextButtonStyle estRoja = new TextButtonStyle();
	
	/**
	 * Clase encargada de mostrar el mapa de los mini-juegos, permitir al
	 * usuario que elija el mini-juego que desea y Mover al avatar entre las
	 * estaciones
	 * 
	 * @param pGame
	 *            Variable para que la clase Miniheroes.java genere la pantalla
	 * @throws Exception
	 *             si no puede cargar una partida en el metodo asignar partida
	 */
	public mapaMinijuegoScreen(Miniheroes pGame) throws Exception {
		super(pGame);
		game = pGame;
		myActor = new Heroe_Dificultad(game);
		myActor.setSize(65, 95);
		
		font = new BitmapFont();
		packEstaciones=game.getManager().get("assets/skins/packEstaciones.pack");
		skinEstaciones=new Skin();
		skinEstaciones.addRegions(packEstaciones);
		
		skin = new Skin();
		skin3 = new Skin();
		pack = new TextureAtlas(Gdx.files.internal("assets/skins/mapaGeneral.pack"));
		skin3.addRegions(pack);
		pack2 = new TextureAtlas(Gdx.files.internal("assets/skins/botones.pack"));
		skin.addRegions(pack2);
		
		stylevol.font = font;
		stylevol.up = skin.getDrawable("salirUp");
		stylevol.down = skin.getDrawable("salirDown");
		stylevol.over = skin.getDrawable("salirOver");
		
	
		estAzul.font = font;
		estAzul.up=skinEstaciones.getDrawable("estacionAzul");
		estAzul.over=skinEstaciones.getDrawable("estacionAzul2");
		
	
		estRoja.font = font;
		estRoja.up=skinEstaciones.getDrawable("estacionRoja");
		
		
	}



	@Override
	public void show() {
		stage = new Stage();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		texturaPersonaje=myActor.getTexture();
		cargarAnimaciones();
		 preferencias = Preferencias.getInstance();

		super.setVolume(preferencias.getPreferenciav("volumen"));
		boolean tocandoMusica=super.playingMusic();
		if (preferencias.getPreferencia("musica")) {
		if(!tocandoMusica){
			super.playMusic();
		}
		}
		try {
			if(avanzarEstacion==true && this.batallaPartida.equals(batalla)){
			if(estacionActual!=minijuego){
				determinarMov(estacionActual, estacionActual+1, myActor);
				float pDuracion= calcularDuracion(estacionActual, avanzar);
				estacionActual++;
				myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
						estacion.get(estacionActual).getY()+15, pDuracion));
			}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Timer.schedule(new Task() {
			@Override
			public void run() {
				showEnd=true;

			}
		}, 0.1f);
		
		for (Estacion estacion : estacion) {
			if (estacion != null) {
				count = count + 1;
			}
		}

		dibujarEstaciones();
		botones();
		stage.addActor(myActor);
		
	
		
		camera = (OrthographicCamera) stage.getCamera();
		tiledMap = new TmxMapLoader().load(tilemap);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		volver.setPosition(10, 485);
		stage.addActor(volver);
		Gdx.input.setInputProcessor(stage);
	
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
		// System.out.println("nombre del minijuego"+nombreMinijuego);
		for (int j = 0; j < estacion.size(); j++) {

//			 System.out.println("minijuego:" + estacion.get(j).getMinijuego());
//			
//			 System.out.println("batalla:" + nombreMinijuego);

			if (estacion.get(j).getMinijuego().equals(nombreMinijuego)) {
//				 System.out.println("valor de i a devolver:" + x);

				return x;
			}
			x++;
		}

		return -1;
	}

	/**
	 * asignar una partida a un juego con el nombre del mismo y su respectivo
	 * avatar.
	 * 
	 * @param partida
	 * @param batle
	 * @param pAvanzar
	 * @return el valor dependiendo si pudo o no asignar la partida
	 * @author yisheng
	 */
	public Boolean asignarPartida(
			Partida partida,
			String batle,
			Dificultad dificultad,
			Configuracion configuracion,
			boolean pAvanzar) {

		avanzarEstacion=pAvanzar;
		
		try {
			this.dificultad=dificultad;
			this.partida =partida; 
			this.avatar = partida.getPersonaje();
			this.batallaPartida= partida.getBatalla();
			this.batalla= batle;
			this.nombreMinijuego = partida.getMinijuego();
			this.configuracion=configuracion;
			myActor.personaje(avatar, eTipoPersonaje.Personaje);
			String strNumMin=nombreMinijuego.split("o")[1];
			String nombMin=nombreMinijuego.split("(?<=o)")[0];
			numMin=Integer.parseInt(strNumMin);
			String minAnt=nombMin+(numMin-1);
			
			String nombBat=batle.substring(0, 7);
			String strNumBat=batle.substring(7);
			int numBat=Integer.parseInt(strNumBat);
			String batAnt=nombBat+(numBat-1);
				
			if(!nombreMinijuego.equals("Minijuego1")|| !this.batalla.equals("Batalla1")){
		ultimoAnt=Boolean.valueOf(configuracion.GetAtributo(batAnt, "Minijuego",minAnt, "Ultimo"));
			}
//			System.out.println("Minijuego Actual : " + nombreMinijuego);
//			System.out.println("Batalla Actual mmh" + "j : " + batalla);
			
			estacion = configuracion.GetEstaciones(batalla);
			this.minijuego = estacion();

			if (batallaPartida.equals(batle)) {

				
					if(ultimoAnt==true || nombreMinijuego.equals("Minijuego1")|| pAvanzar==false){
						
						estacionActual = minijuego;
					}
					else{
						
						estacionActual = minijuego-1;
					}
				// estacionActual = estacion();
				
				myActor.setPosition(estacion.get(estacionActual).getX(), estacion.get(estacionActual).getY()+15);

			} else {
				
//                System.out.println(estacionActual);
				minijuego = estacion.size() - 1;
				//estacionActual = minijuego;
				myActor.setPosition(estacion.get(estacionActual).getX(), estacion.get(estacionActual).getY()+15);
				

			}
//			System.out.println("la batalla sera: " + batalla);
//			System.out.println("minijuego jo:" + minijuego);
//			 System.out.println("estacionActual:"+ estacionActual);
				
			tilemap = configuracion.GetAtributo(batalla, "Mapa", "mapa", "Fondo");

//			System.out.println("fondo:" + tilemap);

			
			anchoCasilla = Integer.parseInt(configuracion.GetAtributo(batalla, "Mapa", "mapa", "AnchoCasilla"));
			altoCasilla = Integer.parseInt(configuracion.GetAtributo(batalla, "Mapa", "mapa", "AltoCasilla"));

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e.getMessage().toString());
			return false;
		}

	}
	
	
	
	
	
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		if (stage!=null){
			stage.act(Gdx.graphics.getDeltaTime());
	
			stage.draw();
	
			mover();
			
			if(myActor.getActions().size==0)
			{		
			myActor.setTexture(texturaPersonaje);
		
			
			}
		}
		Gdx.graphics.requestRendering();
		Gdx.graphics.setContinuousRendering(true);
	}

	@Override
	public void hide() {

		Timer.instance().clear();
		c = 0;
		count = 0;
		i = 0;
		showEnd=false;
//		skin.dispose();
		tiledMap.dispose();
if(a!=null){
	a=null;
}
		stage.dispose();
		stage=null;
		camera = null;
//		font.dispose();
//		pack2.dispose();
//		skinEstaciones.dispose();
		 estacionRoja=null;
		 estacionAzul=null;
		 posEstX =null;
		 posEstY =null;
//		skin=null;
		tiledMap=null;
//		stage=null;
//		font=null;
//		pack2=null;
//		skinEstaciones=null;
//		
		System.gc();
		
//		animacionAba.dispose();
//		animacionArri.dispose();
//		animacionDer.dispose();
//		animacionIzq.dispose();
		
		Gdx.input.setInputProcessor(null);

//		gc();
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
		for (int j = 0; j < estacionActual; j++) {
			if (estacion.get(j).isEstacionar()) {
				i++;
			}
		}

		return i;
	}

	/**
	 * Metodo encargado de mover al personaje atraves del mapa asi como de
	 * mostrar el dialogo cuando el personaje se encuentre en la casilla de cada
	 * nivel
	 */
	public void mover() {
		float actualX = myActor.getX();
		float actualY = myActor.getY();
		float destinoX = estacion.get(estacionActual).getX();
		float destinoY = estacion.get(estacionActual).getY();
		
	
		
		if (actualX == destinoX && actualY-15 == destinoY) {
			
				int est=comprobarBoton();
		if(est!=99)
			{
				if(est>estacionActual)
					{
						clickSig=true;
					}
				else if(est<estacionActual)
					{
						clickback=true;
					}
				else{
					jugar=true;
				}
			}
			
			
			if (Gdx.input.isKeyPressed(Keys.ENTER)&& showEnd==true || jugar==true && showEnd==true) {
				jugar=false;
				try {//System.out.println("entre");
					
	//				gc();
					String tipo = configuracion.GetAtributo(batalla, "Minijuego",estacion.get(estacionActual).getMinijuego(), "Tipo");							
//					System.out.println("batalla:"+ batalla);
//					System.out.println("estacion:"+ estacion.get(estacionActual).getNombre());
//					System.out.println("tipo:"+ tipo);
//					
//					System.out.println("tipo:"+ tipo);
					if (tipo.equals("MangoPaola")) {

						if (a!=null)
						{
							a.dispose();
							a=null;
							System.gc();
							System.out.println("entre a limpiar");
						}
						a = new MiniJuegoGeneral(game);
						
//						game.miniJuegoGeneral.asignarPartida(partida, batalla, 
						a.asignarPartida(partida, batalla, 
								estacion.get(estacionActual).getMinijuego(),dificultad,configuracion
								);
//						game.setScreen(game.miniJuegoGeneral);
						game.setScreen(a);
						a=null;
						System.gc();
					} else if (tipo.equals("Trivia")) {
						String tipoTrivia = configuracion.GetAtributo(batalla, "Minijuego",estacion.get(estacionActual).getMinijuego(), "TipoTrivia");
						eTipoTrivia tipoTrivia2 = null;
						if (tipoTrivia.toLowerCase().contains("general"))
							tipoTrivia2 = eTipoTrivia.General;
						else if (tipoTrivia.toLowerCase().contains("ajedrez"))
							tipoTrivia2 = eTipoTrivia.Ajedrez;
						else if (tipoTrivia.toLowerCase().contains("piezas"))
							tipoTrivia2 = eTipoTrivia.Piezas;																			
						game.trivia.asignarPartida(batalla, estacion.get(estacionActual).getMinijuego(),partida , tipoTrivia2,dificultad,configuracion);
						game.setScreen(game.trivia);
					} else if (tipo.equals("Ordenando")) {
						game.ordenando.asignarPartida(batalla, estacion.get(estacionActual).getMinijuego(),partida,dificultad,configuracion);
						game.setScreen(game.ordenando);
					} else if (tipo.equals("Ajedrez")) {
						game.juegoPrincipal.asignarPartida(batalla, estacion.get(estacionActual).getMinijuego(),
								partida,dificultad,configuracion);
						game.setScreen(game.juegoPrincipal);
					}else{System.out.println("no existe el tipo de minijuego");}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error: " +e);
					e.printStackTrace();

				}
			}
			if ((clickSig == true || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) && estacionActual == count - 1) {
				
				
				
//				if (mapGen!=null)
//				{
//					mapGen.dispose();
//					mapGen=null;
//					System.gc();
//					System.out.println("entre a limpiar");
//				}
//				try {
//					mapGen = new mapaGeneralScreen(game,"batalla0");
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				try {
//					mapGen.asignarPartida(partida, dificultad,configuracion,false
//							);
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				
//
//				game.setScreen(mapGen);
//				
//				mapGen=null;
//				System.gc();
				
				
				game.mapGen.asignarPartida(partida, dificultad,configuracion,false);
				
				game.setScreen(game.mapGen);
			} else if ((clickSig == true || Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) && estacionActual < count - 1
					&& batalla(estacionActual) < minijuego) {
				
				determinarMov(estacionActual, estacionActual+1,myActor);
				duracion = calcularDuracion(estacionActual, avanzar);
				estacionActual++;
				myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
						estacion.get(estacionActual).getY()+15, duracion));
				if (estacion.get(estacionActual).isEstacionar() == false) {
					while (estacion.get(estacionActual).isEstacionar() == false) {
						duracion2 = calcularDuracion(estacionActual, avanzar);
						estacionActual++;
						clickSig=false;
						Timer.schedule(new Task() {
							@Override
							public void run() {
								determinarMov(estacionActual-1, estacionActual,myActor);
								myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
										estacion.get(estacionActual).getY()+15, duracion2));

							}
						}, duracion);
					}
				}
				
			} else if ((clickback == true || Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) && estacionActual > 0) {
				duracion = calcularDuracion(estacionActual, retroceder);
				determinarMov(estacionActual, estacionActual-1,myActor);
				estacionActual--;
				clickback=false;
				myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
						estacion.get(estacionActual).getY()+15, duracion));
				if (estacion.get(estacionActual).isEstacionar() == false) {
					while (estacion.get(estacionActual).isEstacionar() == false) {
						duracion2 = calcularDuracion(estacionActual, retroceder);
						estacionActual--;

						Timer.schedule(new Task() {
							@Override
							public void run() {
								determinarMov(estacionActual+1, estacionActual,myActor);
								myActor.addAction(Actions.moveTo(estacion.get(estacionActual).getX(),
										estacion.get(estacionActual).getY()+15, duracion2));

							}
						}, duracion);
					}
				}
			}else {
				clickSig = false;
				clickback = false;
				jugar=false;

			}
		}else {
			clickSig = false;
			clickback = false;
			jugar=false;
		}
	}

	@Override
	public void resize(int width, int height) {

		camera.setToOrtho(false, width, height);
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.position.x = super.getGameWidth() / 2;
		camera.position.y = super.getGameHeight() / 2;

	}

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
		
//		System.out.println(actorX);
//		System.out.println(destinoX);
//		System.out.println(actorY);
//		System.out.println(destinoY);
//		
		String verticalup="arriba";
		String HorizontalDer="derecha";
		String verticalDown="abajo";
		String HorizontalIzq="izquierda";
		
		if(actorX==destinoX && actorY<destinoY)
			{
//				System.out.println("arriba");
				pMyActor.setTexture(null);
				pMyActor.setPack(animacionArri);
				return verticalup;	
			}
		else 
		if(actorX==destinoX && actorY>destinoY)
			{
//			System.out.println("abajo");
			pMyActor.setTexture(null);
			pMyActor.setPack(animacionAba);
				return verticalDown;			
			}
		else 
		if(actorY==destinoY && actorX<destinoX)
			{
//				System.out.println("derecha");
				pMyActor.setTexture(null);
				pMyActor.setPack(animacionDer);
				return HorizontalDer;
			}
		else 
		if(actorY==destinoY && actorX>destinoX)
			{
//				System.out.println("izquierda");
				pMyActor.setTexture(null);
				pMyActor.setPack(animacionIzq);

				return HorizontalIzq;
			}
		
//	System.out.println("retornando null");
		return null;	
	}
		
	/**
	 * Carga las animaciones segun sea el ni�o		
	 */
			
	public void cargarAnimaciones(){
		System.out.println("avatar : "+avatar);
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
				

	public void screenfrom(ScreenManager pScreenFrom){
		screenFrom = pScreenFrom;		
		
	}
	
	public int comprobarBoton(){
		int i;
		for (i = 0; i < c; i++) 
			{if(estacionAzul[i]!=null){
				if(estacionAzul[i].isPressed()){
//				System.out.println("retornando :"+ i);
				return i;
				}
			}
			}
		
		
		return 99;
	}
	
	/**
	 * Metodo que genera y muestra los botones  
	 */
		private void dibujarEstaciones() {
			 posEstX = new float[7];
			 posEstY = new float[7];
			 estacionRoja=new TextButton[7];
			 estacionAzul=new TextButton[7];
			
			for (int i = 0; i < estacion.size(); i++) {
				Estacion estaci = estacion.get(i);
				if (estaci.isEstacionar()) {
					posEstX[c] = estaci.getX() - 2;
					posEstY[c] = estaci.getY() - 1;
					c = c + 1;

				}
			}

			for (int i = 0; i < c; i++) {
				String strNumMin=estacion.get(i).getMinijuego().split("o")[1];
				int estacionAct=Integer.parseInt(strNumMin);
//				System.out.println(estacionAct);
				if (numMin >= estacionAct) {
			
					estacionAzul[i] =new TextButton("", estAzul);
					estacionAzul[i].setPosition(posEstX[i],posEstY[i]-13);
					estacionAzul[i].setSize(70, 60);
					stage.addActor(estacionAzul[i]);
					estacionAzul[i].setZIndex(0);
				}
				else{
					
					estacionRoja[i] =new TextButton("", estRoja);
				
					estacionRoja[i].setPosition(posEstX[i],posEstY[i]-13);
					estacionRoja[i].setSize(70, 60);
					stage.addActor(estacionRoja[i]);
					estacionRoja[i].setDisabled(true);
					estacionRoja[i].setZIndex(0);
				}
			}
			
		}
	
		
		/**
		 * Metodo que genera y muestra los botones  
		 */
			private void botones() {

				if(batalla.equals("Batalla1"))
				{
					pared = new ActorExtra(this, TipoDeActor.PARED, 0);
					pared.setPosition(128, 279);
					pared.setSize(118, 71);
					stage.addActor(pared);
					pared.setZIndex(64);
				}

			volver = new TextButton("", stylevol);
			volver.addCaptureListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
//					if (mapGen!=null)
//					{
//						mapGen.dispose();
//						mapGen=null;
//						System.gc();
//						System.out.println("entre a limpiar");
//					}
//					try {
//						mapGen = new mapaGeneralScreen(game,"batalla0");
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//					try {
//						mapGen.asignarPartida(partida,dificultad,configuracion,false
//								);
//						
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					
	//
//					game.setScreen(mapGen);
//					mapGen=null;
//					System.gc();
					
					game.mapGen.asignarPartida(partida,dificultad,configuracion,false);
					game.setScreen(game.mapGen);
				}

			});
			
//			siguiente.setSize(50, 40);
//			atras.setSize(50, 40);
//			siguiente.setPosition(500, 500);
//			atras.setPosition(300, 500);
//			
////			stage.addActor(siguiente);
////			stage.addActor(atras);
			}	
		
		
		
		
		
}
