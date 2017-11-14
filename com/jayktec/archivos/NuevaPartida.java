
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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.jayktec.grafico.Dialogo;
import com.jayktec.grafico.Enums.eTipoPersonaje;
import com.jayktec.grafico.Heroe_Dificultad;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Screen.ScreenManager;
import com.jayktec.grafico.Screen.ScreenVideo;

public class NuevaPartida extends ScreenManager {

	private Stage stage;
	// private TextButton crear;
	private TextButton salir;
	public Miniheroes game1;
	private Skin skin,skin2, skin3;
	private TextureAtlas pack,pack2;
	private BitmapFont font;
	private TextButtonStyle style;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	// private TextField partida;
	private String personaje;
	private String nivelDificultad;
	final Heroe_Dificultad myActor;
	private String NombrePartida="";
	private ScreenVideo a;
	
	
	public NuevaPartida(final Miniheroes game) {
		super(game);
		game1 = game;
		myActor = new Heroe_Dificultad(game1);
		myActor.setSize(130, 225);

	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		// camera.setToOrtho(false,w,h);
		// camera.update();

		tiledMap = new TmxMapLoader().load("assets/mapasTiled/fondoPrincipal4.tmx");

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		stage = new Stage();
		skin3 = new Skin();
		skin2 = new Skin(Gdx.files.internal("uiskin.json"));
		
		font = new BitmapFont();
		skin = new Skin();
		pack = new TextureAtlas(Gdx.files.internal("assets/skins/botones.pack"));
		pack2 = new TextureAtlas(Gdx.files.internal("assets/skins/nuevaPartida.pack"));
		skin.addRegions(pack);
		skin3.addRegions(pack2);
		style = new TextButtonStyle();
		style.font = font;
		style.up = skin.getDrawable("salirUp");
		style.down = skin.getDrawable("salirDown");
		style.over = skin.getDrawable("salirOver");

		salir = new TextButton("", style);

	
		salir.addCaptureListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game1.setScreen(game1.inicio);

			}
		});

		nivelDificultad = "1";

		myActor.setPosition(450, 100);
		myActor.personaje("Sebastian", eTipoPersonaje.Personaje);
		camera = (OrthographicCamera) stage.getCamera();

		// stage.addActor(partida);
		//
		// stage.addActor(crear);
		stage.addActor(salir);
		stage.addActor(myActor);

		// crear.setSize(170, 60);
	
		// partida.setSize(200, 30);
		// partida.setMaxLength(20);
		// crear.setPosition(500, 35);
		salir.setPosition(10, 10);
		// partida.setPosition(50, 250);
		Gdx.input.setInputProcessor(stage);

		//escoger(eTipoPersonaje.Personaje);
		escogerPersonajes();
		
		
		
	}

	@Override
	public void hide() {
		stage.dispose();
		skin.dispose();
		skin2.dispose();
		skin3.dispose();
		pack.dispose();
		pack2.dispose();
		font.dispose();
		tiledMap.dispose();
		Gdx.input.setInputProcessor(null);

	}

	@Override
	public void dispose() {
		
super.dispose();
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

	/**
	 * escoger Personajes
	 * @author yisheng
	 */
	private void escogerPersonajes()
	{
		
		Texture texturePersonajes=game1.getManager().get("assets/Texturas/personajes.png");
		myActor.personaje("Sebastian", eTipoPersonaje.Personaje);
		personaje="Sebastian";

		final Image image=new Image(texturePersonajes);
		TextButtonStyle stySebastian = new TextButtonStyle();
		TextButtonStyle styCamila = new TextButtonStyle();
		TextButtonStyle styAndres = new TextButtonStyle();
		TextButtonStyle styAceptar = new TextButtonStyle();
		
		
		styAndres.font=font;
		styCamila.font=font;
		stySebastian.font=font;
		styAceptar.font=font;
		
		styAceptar.up = skin.getDrawable("aceptarUp");
		styAceptar.down = skin.getDrawable("aceptarDown");
		styAceptar.over= skin.getDrawable("aceptarOver");
		
		styAndres.up = skin3.getDrawable("andresUp");
		styAndres.down = skin3.getDrawable("andresDown");
		styAndres.over = skin3.getDrawable("andresOver");
		
		styCamila.up = skin3.getDrawable("camilaUp");
		styCamila.down = skin3.getDrawable("camilaDown");
		styCamila.over = skin3.getDrawable("camilaOver");
		
		stySebastian.up = skin3.getDrawable("sebastianUp");
		stySebastian.down = skin3.getDrawable("sebastianDown");
		stySebastian.over = skin3.getDrawable("sebastianOver");
		
		final Button botonAndres= new Button(styAndres);	
		final Button botonCamila= new Button(styCamila);
		final Button botonSebastian= new Button(stySebastian);
		final Button Aceptar= new Button(styAceptar);
		
		botonAndres.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				personaje="Alejandro";
				myActor.personaje("Alejandro", eTipoPersonaje.Personaje);

			}
		});
		botonSebastian.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				myActor.personaje("Sebastian", eTipoPersonaje.Personaje);
				personaje="Sebastian";
			}
		});
		botonCamila.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				myActor.personaje("Camila", eTipoPersonaje.Personaje);
				personaje="Camila";
			}
		});

		
		Aceptar.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				botonAndres.remove();
				botonCamila.remove();
				botonSebastian.remove();
				Aceptar.remove();
				image.remove();
				myActor.personaje("Soldado", eTipoPersonaje.Dificultad);
				escogerDificultad();
			}
		});
		
		
			botonSebastian.setPosition(250, 230);
			   botonCamila.setPosition(250, 170);
			   botonAndres.setPosition(250, 110);
			Aceptar.setPosition(350, 40);
		
		image.setPosition(315, 300);
		image.setSize(220, 90);

		
			image.setSize(220, 90);
		botonAndres.setSize(180, 70);
		botonCamila.setSize(180, 70);
		botonSebastian.setSize(180, 70);
		
		stage.addActor(botonSebastian);
		stage.addActor(botonCamila);
		stage.addActor(botonAndres);
		stage.addActor(Aceptar);
		stage.addActor(image);
			
	}
	


	/**
	 * escoger Personajes
	 * @author yisheng
	 */
	private void escogerDificultad()
	{
		
Texture texturePersonajes=game1.getManager().get("assets/Texturas/dificultad.png");

		myActor.personaje("Soldado", eTipoPersonaje.Dificultad);
		nivelDificultad = "1";

		final Image image=new Image(texturePersonajes);
		TextButtonStyle stySoldado = new TextButtonStyle();
		TextButtonStyle styTeniente= new TextButtonStyle();
		TextButtonStyle styCapitan = new TextButtonStyle();
		TextButtonStyle styMayor = new TextButtonStyle();
		TextButtonStyle styCoronel = new TextButtonStyle();
		
		TextButtonStyle styGeneralJefe = new TextButtonStyle();
		TextButtonStyle styAceptar = new TextButtonStyle();
		
		
		
		stySoldado.font=font;
		styTeniente.font=font;
		styCapitan.font=font;
		styMayor.font=font;
		styCoronel.font=font;
		styGeneralJefe.font=font;
		
		styAceptar.font=font;
		
		
		stySoldado.up = skin3.getDrawable("soldadoUp");
		stySoldado.down = skin3.getDrawable("soldadoDown");
		stySoldado.over = skin3.getDrawable("soldadoOver");
		
		styTeniente.up = skin3.getDrawable("tenienteUp");
		styTeniente.down = skin3.getDrawable("tenienteDown");
		styTeniente.over = skin3.getDrawable("tenienteOver");
		
		styCapitan.up = skin3.getDrawable("capitanUp");
		styCapitan.down = skin3.getDrawable("capitanDown");
		styCapitan.over = skin3.getDrawable("capitanOver");
		
		styMayor.up = skin3.getDrawable("mayorUp");
		styMayor.down = skin3.getDrawable("mayorDown");
		styMayor.over = skin3.getDrawable("mayorOver");
		
		
		styCoronel.up = skin3.getDrawable("coronelUp");
		styCoronel.down = skin3.getDrawable("coronelDown");
		styCoronel.over = skin3.getDrawable("coronelOver");
		
		styGeneralJefe.up = skin3.getDrawable("generalUp");
		styGeneralJefe.down = skin3.getDrawable("generalDown");
		styGeneralJefe.over = skin3.getDrawable("generalOver");
		
		styAceptar.up = skin.getDrawable("aceptarUp");
		styAceptar.down = skin.getDrawable("aceptarDown");
		styAceptar.over = skin.getDrawable("aceptarOver");
		
		final Button botonSoldado= new Button(stySoldado);
		final Button botonTeniente= new Button(styTeniente);
		final Button botonCapitan= new Button(styCapitan);
		final Button botonMayor= new Button(styMayor);
		final Button botonCoronel= new Button(styCoronel);
		final Button botonGeneralJefe= new Button(styGeneralJefe);

		final Button Aceptar= new Button(styAceptar);
		
		botonSoldado.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				myActor.personaje("Soldado", eTipoPersonaje.Dificultad);
				nivelDificultad = "1";
			}
		});
		
		botonTeniente.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				myActor.personaje("Teniente", eTipoPersonaje.Dificultad);
				nivelDificultad = "2";
				
			}
		});
		
		
		botonCapitan.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				myActor.personaje("Capitan", eTipoPersonaje.Dificultad);
				nivelDificultad = "3";
				
			}
		});
		
		botonMayor.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				myActor.personaje("Mayor", eTipoPersonaje.Dificultad);
				nivelDificultad = "5";
				
			}
		});
		
		
		botonCoronel.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				myActor.personaje("Coronel", eTipoPersonaje.Dificultad);
				nivelDificultad = "7";
				
			}
		});
	
		botonGeneralJefe.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				myActor.personaje("General", eTipoPersonaje.Dificultad);
				nivelDificultad = "10";
				
			}
		});

		Aceptar.addCaptureListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				botonCoronel.remove();
				botonSoldado.remove();
				botonTeniente.remove();
				botonCapitan.remove();
				botonMayor.remove();
				botonGeneralJefe.remove();
				Aceptar.remove();
				image.remove();	
				myActor.remove();
				ingresarPartida();
			}
		});
		
				image.setPosition(312, 315);
				
		 botonSoldado.setPosition(250, 280);
		botonTeniente.setPosition(250, 235);
		 botonCapitan.setPosition(250, 197);
		   botonMayor.setPosition(250, 155); 
		 botonCoronel.setPosition(250, 111);
	 botonGeneralJefe.setPosition(250, 72);
		
		
		Aceptar.setPosition(350, 15);
		
		
		botonSoldado.setSize(180, 50);
		botonTeniente.setSize(180, 50);
		botonCoronel.setSize(180, 50);
		botonCapitan.setSize(180, 50);
		botonMayor.setSize(180, 50);
		botonGeneralJefe.setSize(180, 50);
		image.setSize(220, 90);
		
		
		stage.addActor(image);
		stage.addActor(Aceptar);
		stage.addActor(botonSoldado);
		stage.addActor(botonTeniente);
		stage.addActor(botonCapitan);
		stage.addActor(botonMayor);
		stage.addActor(botonCoronel);
		stage.addActor(botonGeneralJefe);	
		
		
		
	}

	/**
	 * ingresar eel nombre de partida
	 * @author yisheng
	 */
	private void ingresarPartida()
	{
		final TextField textfield;
Texture texturePersonajes=game1.getManager().get("assets/Texturas/nombre.png");
		
		final Image image=new Image(texturePersonajes);
		image.remove();	
		image.setPosition(312, 250);
		image.setSize(220, 90);
		stage.addActor(image);
			textfield = new TextField("", skin2);
			textfield.setWidth(300);
			textfield.setPosition(270, 200);
			stage.addActor(textfield);
		
		TextButtonStyle styAceptar = new TextButtonStyle();
		styAceptar.font=font;
		
		styAceptar.up = skin.getDrawable("aceptarUp");
		styAceptar.down = skin.getDrawable("aceptarDown");
		styAceptar.over = skin.getDrawable("aceptarOver");

		final Button Aceptar= new Button(styAceptar);
		Aceptar.setPosition(350, 40);
		
		Aceptar.addCaptureListener(new ChangeListener() {

			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				NombrePartida= textfield.getText();
				procesarIngresar();
			}
		});

		
		
		
		
		stage.addActor(Aceptar);
		
		
	}
	

	private void procesarIngresar() {

//		System.out.println("pPartida: " + NombrePartida );
		if (NombrePartida.length()>0)
		{
			
			try {
				String vPartida = NombrePartida;
				if (Partida.ExistePartida(vPartida)) {
//					System.out.println("Existe la partida:" + vPartida);
					// todo Dialogo de error
					// partida.setText(" Error Existe la partida:" + vPartida);
					Dialogo mensaje= new Dialogo("", "assets/Texturas/partidaExistente.png");
					
					mensaje.setWidth(360);
					mensaje.setHeight(400);
					mensaje.align(Align.center | Align.top);

					stage.addActor(mensaje);

					mensaje.setPosition(super.gameWidth / 2 - mensaje.getWidth() / 2,
							super.gameHeight / 2 - mensaje.getHeight() / 2);

					mensaje.addListener(new ChangeListener() {
						@Override
						public void changed(ChangeEvent event, Actor actor) {

							try {
						
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					});
					
					
				} else {
//					System.out.println("creando la partida:" + vPartida + " selec: " + personaje + " nivel: " + nivelDificultad);
					Partida.nuevaPartida(vPartida, personaje, nivelDificultad);
					Partida partida= new Partida(vPartida);
					Dificultad dificultad= new Dificultad(nivelDificultad);
					Configuracion configuracion= new Configuracion();
					// System.out.println(vPartida);
//					if (Boolean.parseBoolean(Configuracion.GetAtributo("Batalla0",
//							"Mapa", "mapa", "VideoPlayer"))) {
						stopMusic();
//						System.out.println("Video inicial:"+Configuracion.GetAtributo("batalla0",
//								"Mapa", "mapa", "Video"));
						
						
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
						
						
//						game1.video.asignarPartida(configuracion.GetAtributo("batalla0",
//								"Mapa", "mapa", "Video"), partida,"Minijuego1" ,dificultad,configuracion);
//						game1.setScreen(game1.video);
//					}
					
//					game1.mapGen.asignarPartida(vPartida,false);
//					game1.setScreen(game1.mapGen);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			ingresarPartida();
			
		}
	}

}
