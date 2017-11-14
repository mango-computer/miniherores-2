package com.jayktec.grafico.Screen;

import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.jayktec.grafico.BotonImagen;
import com.jayktec.grafico.Miniheroes;

public class ExtrasScreen extends ScreenManager {

	public Miniheroes game;	
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	private Stage stage;
	private TextButton creditos,material,volver;
	private BitmapFont font;
	private Skin skin;
	private TextureAtlas pack;
	int w = ((int) super.getGameWidth());
	int h = ((int) super.getGameHeight());
	private Texture fvaTexture;
	private BotonImagen fva;
	private Texture jayktecTexture;
	private BotonImagen jayktec;
	
	public ExtrasScreen(final Miniheroes pGame) {
		super(pGame);
		game = pGame;
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		tiledMap = new TmxMapLoader().load("assets/mapasTiled/fondoPrincipal5.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		stage = new Stage();
		camera = (OrthographicCamera) stage.getCamera();
		font = new BitmapFont();
		skin = new Skin();
        pack = new TextureAtlas(Gdx.files.internal("assets/skins/menuPrincipal.pack"));
		skin.addRegions(pack);
		botones();
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
		fvaTexture.dispose();
		jayktecTexture.dispose();
		stage.dispose();
		skin.dispose();
		pack.dispose();
		font.dispose();
		tiledMap.dispose();
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void pause() {
		
		super.pause();
	}

	@Override
	public void resume() {
		
		super.resume();
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
	 * dibuja los botones con sus respectivos listeners en la pantalla
	 */
	private void botones() {
		
		TextButtonStyle stycreditos = new TextButtonStyle();
		TextButtonStyle stysalir = new TextButtonStyle();
		TextButtonStyle stymaterial = new TextButtonStyle();
		stysalir.font=font;
		stycreditos.font=font;
		stymaterial.font=font;
		
		stysalir.up = skin.getDrawable("salirUp");
		stysalir.down = skin.getDrawable("salirDown");
		stysalir.over = skin.getDrawable("salirOver");
		
		stycreditos.up = skin.getDrawable("creditosUp");
		stycreditos.down = skin.getDrawable("creditosDown");
		stycreditos.over = skin.getDrawable("creditosOver");
		
		stymaterial.up = skin.getDrawable("materialUp");
		stymaterial.down = skin.getDrawable("materialDown");
		stymaterial.over = skin.getDrawable("materialOver");
		
		creditos = new TextButton("",stycreditos);
		volver = new TextButton("",stysalir);
		material = new TextButton("",stymaterial);
		fvaTexture = new Texture("assets/Texturas/fva.png");
		jayktecTexture = new Texture("assets/Texturas/jayana.png");
		fva = new BotonImagen(fvaTexture,fvaTexture,fvaTexture,fvaTexture);
		jayktec = new BotonImagen(jayktecTexture,jayktecTexture,jayktecTexture,jayktecTexture);
		 
		 
		 
		
		 
		 volver.addCaptureListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					game.setScreen(game.inicio);
				}
			});
		 
		 material.addCaptureListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {


			        try {
			        	FileHandle fh = Gdx.files.internal("material/principal.html");
						String path = fh.file().getAbsolutePath();
			            Desktop.getDesktop().browse(new URI("file://"+path));

			        } catch (URISyntaxException ex) {

			            System.out.println("Error: " +ex);

			        }catch(IOException e){

			            System.out.println("Error: " + e);

			        }
			      			}

			});
		 
		 jayktec.addCaptureListener(new ChangeListener() {

			 
				@Override
				public void changed(ChangeEvent event, Actor actor) {

			        try {

			            Desktop.getDesktop().browse(new URI("http://jayktec.com.ve/"));

			        } catch (URISyntaxException ex) {

			            System.out.println("Error: " +ex);

			        }catch(IOException e){

			            System.out.println("Error: " + e);

			        }
			      			}
			});
		 
			 
			 fva.addCaptureListener(new ChangeListener() {

				 
				@Override
				public void changed(ChangeEvent event, Actor actor) {

			        try {

			            Desktop.getDesktop().browse(new URI("http://www.fva.com.ve"));

			        } catch (URISyntaxException ex) {

			            System.out.println("Error: " +ex);

			        }catch(IOException e){

			            System.out.println("Error: " + e);

			        }
			      			}
			});
		 
			 creditos.addCaptureListener(new ChangeListener() {

				 
					@Override
					public void changed(ChangeEvent event, Actor actor) {
						game.setScreen(game.creditosJuego);
				   
				      			}
				});
			 
			 
		 stage.addActor(creditos);
		 stage.addActor(volver);
		 stage.addActor(material);
		 //stage.addActor(fva);
		 //stage.addActor(jayktec);
		
		 fva.setPosition(200, 450);
		 jayktec.setPosition(600, 450);
		
		 fva.setSize(80, 80);
		 jayktec.setSize(80, 80);
		
		
		 creditos.setPosition(223, 30);
		 material.setPosition((w/2)-(creditos.getWidth()/2), 30);
		  volver.setPosition(500, 30);
		 
		
		
		
		
	}
	
}
