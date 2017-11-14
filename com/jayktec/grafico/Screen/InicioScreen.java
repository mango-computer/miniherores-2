
//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                InicioScreen.java                            //
//                   Descripcion                            //
//             Pantalla para el menu Principal              //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      09/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////


package com.jayktec.grafico.Screen;

import java.io.File;

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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.grafico.Dialogo;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Preferencias;


public class InicioScreen extends ScreenManager {

	private Stage stage;
	private TextButton continuar,nuevaPartida,borrar,opcion,extra,salir,salir2,campana,juego;
	public Miniheroes game;
	private Skin skin,skin2;
	private TextureAtlas pack;
	private BitmapFont font;
	private Preferencias preferencias, vopc;
	int w = ((int) super.getGameWidth());
	int h = ((int) super.getGameHeight());
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	float delay = 0;
	boolean flag = true;
	private Sound sonidoMenu;
	private Dialogo opcionesScreen = null;
	private float gameWidth =0;
	private float gameHeight =0;
	

	

	public InicioScreen(final Miniheroes pGame) {
		super(pGame);
		game = pGame;
		vopc = Preferencias.getInstance();
		vopc.load();



		gameWidth = super.gameWidth;
		gameHeight = super.gameHeight;
	}
		
    
	@Override
	public void show() {
		stage = new Stage();
		 preferencias = Preferencias.getInstance();
		 //preferencias.load();
		 super.setVolume(preferencias.getPreferenciav("volumen"));
		if (preferencias.getPreferencia("musica")) {
			
		super.playMusic();
		} 

		camera = new OrthographicCamera();
		camera = (OrthographicCamera) stage.getCamera();
		camera.setToOrtho(false,super.getGameWidth(),super.getGameHeight());
		tiledMap = new TmxMapLoader().load("assets/mapasTiled/fondoPrincipal3.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
//		
		font = new BitmapFont();
		skin = new Skin();
		
        pack = new TextureAtlas(Gdx.files.internal("assets/skins/menuPrincipal.pack"));
		skin.addRegions(pack);
	
		sonidoMenu=game.getManager().get("assets/Sonidos/sonidoMenu.mp3");
		
		
		Texture texture=game.getManager().get("assets/Texturas/pergamino.png");
		Image imagenTransparente=new Image(texture);
		stage.addActor(imagenTransparente);
		botones();
		imagenTransparente.setSize(850, 50);
		imagenTransparente.setPosition(-10, 60);
		Gdx.input.setInputProcessor(stage);
		
	}
	
@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		stage.act();
		stage.draw();
		Gdx.graphics.requestRendering();
	}

	@Override
	public void hide() {
		if (skin2 !=null){
			skin2.dispose();
			skin2 = null;
		}
		stage.dispose();
		skin.dispose();
		pack.dispose();
		font.dispose();
		tiledMap.dispose();
		Gdx.input.setInputProcessor(null);
		this.dispose();
	}

	@Override
	public void dispose() {
//	System.out.println("dispose");
	super.dispose();
	gc();
	}

	
	@Override
	public void resize(int width, int height) {
//		camera.setToOrtho(false, width, height);
//		camera.viewportWidth = width;
//		camera.viewportHeight = height;
//		camera.position.x = super.getGameWidth() / 2;
//		camera.position.y = super.getGameHeight() / 2;
	}


private void loadSettings() {

	    opcionesScreen = new Dialogo(" Opciones...",this);				   
		opcionesScreen.setWidth(360);
		opcionesScreen.setHeight(400);			
		opcionesScreen.align(Align.center | Align.top);
		stage.addActor(opcionesScreen);
		opcionesScreen.setPosition(super.gameWidth/2 - opcionesScreen.getWidth()/2, super.gameHeight/2-opcionesScreen.getHeight()/2);		
		opcionesScreen.addListener(new ChangeListener() {
	   		

			@Override
	   		public void	changed(ChangeEvent event, Actor actor){
				procesarResultadoDialogo(opcionesScreen.getValue());
			
			}
  		}); 		
}
	
/**
 * dibuja los botones con sus respectivos listeners en la pantalla
 */
private void botones() {
		TextButtonStyle styContinuar = new TextButtonStyle();
		TextButtonStyle styNuevo = new TextButtonStyle();
		TextButtonStyle styborrar = new TextButtonStyle();
		TextButtonStyle styExtras = new TextButtonStyle();
		TextButtonStyle styopciones = new TextButtonStyle();
		TextButtonStyle stysalir = new TextButtonStyle();
	
		TextButtonStyle styCampana = new TextButtonStyle();
		TextButtonStyle styJuego = new TextButtonStyle();
		
		
		styContinuar.font=font;
		styNuevo.font=font;
		styborrar.font=font;
		styExtras.font=font;
		styopciones.font=font;
		stysalir.font=font;
		styCampana.font=font;
		styJuego.font=font;
		
	styContinuar.up = skin.getDrawable("cargarPartidaUp");
	styContinuar.down = skin.getDrawable("cargarPartidaDown");
	styContinuar.over = skin.getDrawable("cargarPartidaOver");
	
	styNuevo.up = skin.getDrawable("nuevaPartidaUp");
	styNuevo.down = skin.getDrawable("nuevaPartidaDown");
	styNuevo.over = skin.getDrawable("nuevaPartidaOver");
	
	styborrar.up = skin.getDrawable("borrarUp");
	styborrar.down = skin.getDrawable("borrarDown");
	styborrar.over = skin.getDrawable("borrarOver");
	
	styopciones.up = skin.getDrawable("ajustesUp");
	styopciones.down = skin.getDrawable("ajustesDown");
	styopciones.over = skin.getDrawable("ajustesOver");
	
	styExtras.up = skin.getDrawable("extrasUp");
	styExtras.down = skin.getDrawable("extrasDown");
	styExtras.over = skin.getDrawable("extrasOver");
	
	stysalir.up = skin.getDrawable("salirUp");
	stysalir.down = skin.getDrawable("salirDown");
	stysalir.over = skin.getDrawable("salirOver");

	styCampana.up = skin.getDrawable("campanaUp");
	styCampana.down = skin.getDrawable("campanaDown");
	styCampana.over = skin.getDrawable("campanaOver");
	
	Texture PRUP=game.getManager().get("assets/Texturas/PRUP.png");
	Texture PROVER=game.getManager().get("assets/Texturas/PROVER.png");
	Texture PRDOWN=game.getManager().get("assets/Texturas/PRDOWN.png");
	
	styJuego.up = new TextureRegionDrawable(new TextureRegion(PRUP));
	styJuego.down = new TextureRegionDrawable(new TextureRegion(PROVER));
	styJuego.over =new TextureRegionDrawable(new TextureRegion(PRDOWN));
	
	
	 campana= new TextButton("",styCampana);
	 juego= new TextButton("",styJuego);
     continuar = new TextButton("",styContinuar);
     nuevaPartida = new TextButton("", styNuevo);
     borrar = new TextButton("", styborrar);
	 opcion = new TextButton("", styopciones);
     extra = new TextButton("", styExtras);
	 salir = new TextButton("", stysalir);
	 salir2 = new TextButton("", stysalir);
	 extra.addCaptureListener(new ChangeListener() {

		 
		@Override
		public void changed(ChangeEvent event, Actor actor) {
//			loadSettings();
			game.setScreen(game.extrasScreen);
		}
	});
	
	borrar.addCaptureListener(new ChangeListener() {

		@Override
		public void changed(ChangeEvent event, Actor actor) {

			game.setScreen(game.borrarPartida);
		}
	});

	juego.addCaptureListener(new ChangeListener() {

		@Override
		public void changed(ChangeEvent event, Actor actor) {

			game.setScreen(game.juegoPrincipal);
		}
	});
	
	continuar.addCaptureListener(new ChangeListener() {

		@Override
		public void changed(ChangeEvent event, Actor actor) {

			game.setScreen(game.cargarPartida);
		}
	});

	nuevaPartida.addCaptureListener(new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {

			game.setScreen(game.nuevaPartida);
		}
	});

	opcion.addCaptureListener(new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			loadSettings();
			stage.removeListener(this);
			gc();
		
		}
	});
	salir.addCaptureListener(new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			salir();
			}});
	
	
	
	salir2.addCaptureListener(new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			ocultarMenuPartidas();
			sonidoMenu.play();
		}
	});
	campana.addCaptureListener(new ChangeListener() {
		@Override
		public void changed(ChangeEvent event, Actor actor) {
			mostrarMenuPartidas();
			sonidoMenu.play();
		}
	});
	
	
	stage.addActor(campana);
	stage.addActor(juego);
	stage.addActor(nuevaPartida);
	stage.addActor(continuar);
	stage.addActor(opcion);
	stage.addActor(extra);
	stage.addActor(borrar);
	stage.addActor(salir);
	stage.addActor(salir2);

//	continuar.setPosition(70, 30);
//	nuevaPartida.setPosition(190, 30);
//	borrar.setPosition(310, 30);
	
	
	continuar.setPosition((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30);
	nuevaPartida.setPosition((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30);
	borrar.setPosition((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30);
	salir2.setPosition((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30);
	
	continuar.setTouchable(Touchable.disabled);
	nuevaPartida.setTouchable(Touchable.disabled);
	borrar.setTouchable(Touchable.disabled);
	salir2.setTouchable(Touchable.disabled);
	
	continuar.setVisible(false);
	nuevaPartida.setVisible(false);
	borrar.setVisible(false);
	salir2.setVisible(false);
	
	extra.setPosition(70, 30);
	juego.setPosition(200, 30);
	campana.setPosition((super.getGameWidth()/2)-(campana.getWidth()/2), 30);
	opcion.setPosition(520, 25);
	salir.setPosition(650, 30);	
}


public void mostrarMenuPartidas(){
	final float duracion = .5f;
	
	juego.setTouchable(Touchable.disabled);
	opcion.setTouchable(Touchable.disabled);
	extra.setTouchable(Touchable.disabled);
	salir.setTouchable(Touchable.disabled);
	campana.setVisible(false);
	campana.setTouchable(Touchable.disabled);
	
	juego.addAction(Actions.moveTo((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30,duracion));
	opcion.addAction(Actions.moveTo((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30,duracion));
	extra.addAction(Actions.moveTo((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30,duracion));
	salir.addAction(Actions.moveTo((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30,duracion));

	
	Timer.schedule(new Task() {
		@Override
		public void run() {
			juego.setVisible(false);
			opcion.setVisible(false);
			extra.setVisible(false);
			salir.setVisible(false);
		
			
		}
	}, duracion);
	
	
	Timer.schedule(new Task() {
		@Override
		public void run() {
			continuar.setVisible(true);
			nuevaPartida.setVisible(true);
			borrar.setVisible(true);
			salir2.setVisible(true);
			continuar.addAction(Actions.moveTo(100, 30,duracion));
			nuevaPartida.addAction(Actions.moveTo(275, 30,duracion));
			borrar.addAction(Actions.moveTo(490, 30,duracion));
			salir2.addAction(Actions.moveTo(650, 30,duracion));	
			
			Timer.schedule(new Task() {
				@Override
				public void run() {
	
					continuar.setTouchable(Touchable.enabled);
					nuevaPartida.setTouchable(Touchable.enabled);
					borrar.setTouchable(Touchable.enabled);
					salir2.setTouchable(Touchable.enabled);
				}
			}, duracion);
			
			
			
			
		}
	}, duracion);
	
	
}

public void ocultarMenuPartidas(){
	final float duracion = .5f;
	
	continuar.setTouchable(Touchable.disabled);
	nuevaPartida.setTouchable(Touchable.disabled);
	borrar.setTouchable(Touchable.disabled);
	salir2.setTouchable(Touchable.disabled);
	
	
	continuar.addAction(Actions.moveTo((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30,duracion));
	nuevaPartida.addAction(Actions.moveTo((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30,duracion));
	borrar.addAction(Actions.moveTo((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30,duracion));
	salir2.addAction(Actions.moveTo((super.getGameWidth()/2)-(campana.getWidth()/2)+50, 30,duracion));
	
	
	Timer.schedule(new Task() {
		@Override
		public void run() {
			continuar.setVisible(false);
			nuevaPartida.setVisible(false);
			borrar.setVisible(false);
			salir2.setVisible(false);
		}
	}, duracion);
	
	
	Timer.schedule(new Task() {
		@Override
		public void run() {
			juego.setVisible(true);
			opcion.setVisible(true);
			extra.setVisible(true);
			salir.setVisible(true);
			
			extra.addAction(Actions.moveTo(70, 30,duracion));
			juego.addAction(Actions.moveTo(200, 30,duracion));
			opcion.addAction(Actions.moveTo(520, 25,duracion));
			salir.addAction(Actions.moveTo(650, 30,duracion));
			campana.setVisible(true);
			
			
			Timer.schedule(new Task() {
				@Override
				public void run() {
					
					campana.setTouchable(Touchable.enabled);
			juego.setTouchable(Touchable.enabled);
			opcion.setTouchable(Touchable.enabled);
			extra.setTouchable(Touchable.enabled);
			salir.setTouchable(Touchable.enabled);
					
				}
			}, duracion);
		}
	}, duracion);
	
	
}


private void tocarMusica(){
super.playMusic();
}
private void detenerMusica(){
super.stopMusic();
}

private void procesarResultadoDialogo(Object pResp) {	
	try {
		if(opcionesScreen.getResult()!=null){
		if (opcionesScreen.getResult().equals(true)){
			 preferencias = Preferencias.getInstance();
			 //preferencias.load();
				super.setVolume(preferencias.getPreferenciav("volumen"));
				if(preferencias.getPreferencia("pantallacompleta")==true){
					DisplayMode currentMode=Gdx.graphics.getDisplayMode();
					Gdx.graphics.setFullscreenMode(currentMode);					
					opcionesScreen.dispose();
					opcionesScreen.remove();
					opcionesScreen = null;					
					game.setScreen(game.inicio);					
				}
				else{
					Gdx.graphics.setWindowedMode(w, h);
					opcionesScreen.dispose();
					opcionesScreen.remove();
					opcionesScreen = null;				
				//	game.setScreen(game.inicio);					
				}
				
				if (preferencias.getPreferencia("musica")) {
					tocarMusica();				
				} else{							
					detenerMusica();
				}				
		
			}
		else{
			opcionesScreen.dispose();
			opcionesScreen.remove();
			opcionesScreen = null;		
		}
		System.gc();
		}
	} catch (Exception e) {
			e.printStackTrace();		
	}	
}

public void gc(){

//Runtime garbage = Runtime.getRuntime();
//garbage.gc();
//
//for (int i =0;i<=30;i++)
//System.gc();

}


public void salir(){
	boolean resp = false;
	opcionesScreen = new Dialogo( "", new Texture(new FileHandle("assets/mensajes/mensaje 20.png")),"");
	opcionesScreen.addListener(new ChangeListener() {

		@Override
		public void changed(ChangeEvent event, Actor actor) {

			try {
			
				if (opcionesScreen.getResult() != null){			
					if (opcionesScreen.getResult().equals(true))
						
						Gdx.app.exit();								
				}
				opcionesScreen.remove();
			} catch (Exception e) {
					System.out.println("Error" + e.getMessage());
					procesarResultadoDialogo(0);
				}
			
		}								
	});
	opcionesScreen.setModal(true);
	opcionesScreen.align(Align.center | Align.top);
	opcionesScreen.setPosition(gameWidth / 2 - opcionesScreen.getWidth() / 2,
			gameHeight - opcionesScreen.getHeight() / 2);
	
	stage.addActor(opcionesScreen);		
}

}





