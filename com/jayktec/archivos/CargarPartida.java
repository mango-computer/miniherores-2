
//////////////////////////////////////////////////////////////
//www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//Nueva Partida                            //
//Descripcion                            //
//Creación de Nueva Partida              //
//////////////////////////////////////////////////////////////
//Autor            Fecha           Motivo             // 
//Yisheng León      20/05/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.archivos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Minijuegos.MiniJuegoGeneral;
import com.jayktec.grafico.Screen.ScreenManager;
import com.jayktec.grafico.Screen.ScreenVideo;

public class CargarPartida extends ScreenManager {

	private Stage stage;
	private TextButton aceptar;
	private TextButton salir;
	public Miniheroes game1;
	private Skin skin, skin2;
	private ScrollPane lista;
	private TextureAtlas pack;
	private BitmapFont font;
	private TextButtonStyle salirStyle,aceptarStyle;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	private ScreenVideo a;
	//private Partida juego = new Partida();
	private List<Object> listaPartidas;

	public CargarPartida(final Miniheroes game) {
		super(game);
		game1 = game;
		
		skin2 = new Skin(Gdx.files.internal("uiskin.json"));
		font = new BitmapFont();
		skin = new Skin();
		 pack = new TextureAtlas(Gdx.files.internal("assets/skins/botones.pack"));
		    skin.addRegions(pack);
		        
		        salirStyle = new TextButtonStyle();
		        salirStyle.font = font;
		        salirStyle.up = skin.getDrawable("salirUp");
		        salirStyle.down = skin.getDrawable("salirDown");
		        salirStyle.over=skin.getDrawable("salirOver");
		       
		        aceptarStyle = new TextButtonStyle();
		        aceptarStyle.font = font;
		        aceptarStyle.up = skin.getDrawable("aceptarUp");
		        aceptarStyle.down = skin.getDrawable("aceptarDown");
		        aceptarStyle.over=skin.getDrawable("aceptarOver");
		
		

	}

	@Override
	public void show() {
		
		stage = new Stage();
		camera = new OrthographicCamera();
		tiledMap = new TmxMapLoader().load("assets/mapasTiled/fondoPrincipal2.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		aceptar = new TextButton("", aceptarStyle);
		salir = new TextButton("", salirStyle);

		listaPartidas = new List<Object>(skin2);

		try {
			listaPartidas.setItems(Partida.ListadoPartidas().toArray());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}

		lista = new ScrollPane(listaPartidas, skin2);
		lista.setSize(200, 200);
		lista.setPosition((super.getGameWidth() / 2) - (lista.getWidth() / 2), 150);
		
		lista.setFlickScroll(false);
		lista.setScrollingDisabled(true, false);
		

		lista.setForceScroll(false, true);
	
		aceptar.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {

				String nombrePartida=listaPartidas.getSelected().toString();
				
			
				
			//	System.out.println("lista escogida:" + listaPartidas.getSelected().toString());
				
				try {
				
					Partida partida= new Partida(nombrePartida);
					Dificultad dificultad= new Dificultad(partida.getDificultad());
					Configuracion configuracion= new Configuracion();
					//game1.mapGen.asignarPartida(partida,dificultad, configuracion,false);
					stopMusic();
					
					
					if (a!=null)
					{
						a.dispose();
						a=null;
						System.gc();
						System.out.println("entre a limpiar");
					}
					a = new ScreenVideo(game1);
					try {
						a.asignarPartida(configuracion.GetAtributo("batalla0",
								"Mapa", "mapa", "Video"), partida,"Minijuego1" ,dificultad,configuracion
								);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					

					game1.setScreen(a);
					a=null;
					System.gc();
					System.out.println("cambie screen");
					
//					game1.video.asignarPartida(configuracion.GetAtributo("batalla0",
//							"Mapa", "mapa", "Video"), partida,"Minijuego1" ,dificultad,configuracion);
//					game1.setScreen(game1.video);
				//	game1.setScreen(game1.mapGen);
				} catch (Exception e) {
					
					System.out.println(e.getMessage());
				}
				

			}
		});

		salir.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game1.setScreen(game1.inicio);

			}
		});

		camera = (OrthographicCamera) stage.getCamera();

		 stage.addActor(aceptar);
	       stage.addActor(lista);
		stage.addActor(salir);
		
		aceptar.setPosition(270, 55);
		salir.setPosition(450, 55);
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void hide() {
		stage.dispose();
//		skin.dispose();
//		skin2.dispose();
//		pack.dispose();
//		font.dispose();
		tiledMap.dispose();
		
		
		 stage=null;
		 camera=null;
		 aceptar=null;
		salir=null;
		
//		 skin=null;
//		 skin2=null;
		 lista=null;
//		 pack=null;
//		 font=null;
//		 salirStyle=null;
//aceptarStyle=null;
		 tiledMap=null;
		
		 tiledMapRenderer=null;
		 a=null;

		 listaPartidas=null;
		
		System.gc();
		System.gc();
		
		Gdx.input.setInputProcessor(null);

	}

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

	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.position.x = super.getGameWidth() / 2;
		camera.position.y = super.getGameHeight() / 2;
	}
}
