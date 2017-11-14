package com.jayktec.grafico.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.jayktec.grafico.Miniheroes;

public class ScreenCarga extends ScreenManager {
	
	public Miniheroes game;
	private Skin skin;
	private Stage stage;
	private Label carga;
	TiledMap tiledMap;
	OrthographicCamera camera;
	TiledMapRenderer tiledMapRenderer;
	public AssetManager manager;
	
	public ScreenCarga(final Miniheroes pGame) {
		super(pGame);
		game = pGame;
		//sManager = this;		
	}

	
	
	@Override
	public void show() {
		stage=new Stage();
		skin = new Skin(Gdx.files.internal("assets/skins/uiskin1.json"));
		carga=new Label("Cargando ..... ", skin);
		stage.addActor(carga);
		camera = new OrthographicCamera();
		camera = (OrthographicCamera) stage.getCamera();
		camera.setToOrtho(false,super.getGameWidth(),super.getGameHeight());
		tiledMap = new TmxMapLoader().load("assets/mapasTiled/fondoPrincipal3.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		camera = (OrthographicCamera) stage.getCamera();
		cargar();
		
	}
	
		@Override
	public void render(float delta) {
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			camera.update();
			tiledMapRenderer.setView(camera);
			tiledMapRenderer.render();
			
			
			if(game.getManager().update()){
			game.finalizarCarga();
			}
			else{
				int proceso=(int) (game.getManager().getProgress()*100);
			carga.setText("Cargando... "+proceso+"%");		
			}
			stage.act();
			stage.draw();
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}




	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

	public void cargar(){
		
	manager = new AssetManager();
		
		/**
		 * Carga de imagenes principales Ruta: assets/Texturas/
		*/
		manager.load("assets/Texturas/perJugarOver.png", Texture.class);
		manager.load("assets/Texturas/perJugarUp.png", Texture.class);
		manager.load("assets/Texturas/labelBlancas.png", Texture.class);
		manager.load("assets/Texturas/labelNegras.png", Texture.class);
		manager.load("assets/Texturas/relojAjedrez.png", Texture.class);
		manager.load("assets/Texturas/banderita.png", Texture.class);
		manager.load("assets/Texturas/heroeMasculino.png", Texture.class);
		manager.load("assets/Texturas/camila.png", Texture.class);
		manager.load("assets/Texturas/alejandro.png", Texture.class);
		manager.load("assets/Texturas/sebastian.png", Texture.class);
		manager.load("assets/Texturas/pared.png", Texture.class);
		manager.load("assets/Texturas/muro.png", Texture.class);
		manager.load("assets/Texturas/aguja.png", Texture.class);
		manager.load("assets/Texturas/puertaSalida.png", Texture.class);
		manager.load("assets/Texturas/moneda.png", Texture.class);
		manager.load("assets/Texturas/moneda2.png", Texture.class);
		manager.load("assets/Texturas/martillo.png", Texture.class);
		manager.load("assets/Texturas/ball.png", Texture.class);
		manager.load("assets/Texturas/bolsa.png", Texture.class);
		manager.load("assets/Texturas/FLECHA.png", Texture.class);
		manager.load("assets/Texturas/Magia.png", Texture.class);
		manager.load("assets/Texturas/cetro.png", Texture.class);
		manager.load("assets/Texturas/lanza.png", Texture.class);
		manager.load("assets/Texturas/MACHETE.png", Texture.class);
		manager.load("assets/Texturas/paja.png", Texture.class);
		manager.load("assets/Texturas/estacionAzul.png", Texture.class);
		manager.load("assets/Texturas/estacionAzul2.png", Texture.class);
		manager.load("assets/Texturas/estacionRoja.png", Texture.class);
		manager.load("assets/Texturas/barco.png", Texture.class);
		manager.load("assets/Texturas/venezuela.png", Texture.class);
		manager.load("assets/Texturas/dificultad.png", Texture.class);
		manager.load("assets/Texturas/personajes.png", Texture.class);
		manager.load("assets/Texturas/nombre.png", Texture.class);
		
		
		
		manager.load("assets/Texturas/PRUP.png", Texture.class);
		manager.load("assets/Texturas/PROVER.png", Texture.class);
		manager.load("assets/Texturas/PRDOWN.png", Texture.class);
		
		
		
		manager.load("assets/Texturas/contadores/alPaso.png", Texture.class);
		manager.load("assets/Texturas/contadores/capturadas.png", Texture.class);
		manager.load("assets/Texturas/contadores/bolsasCapturadas.png", Texture.class);
		manager.load("assets/Texturas/contadores/contPaja.png", Texture.class);
		manager.load("assets/Texturas/contadores/doble.png", Texture.class);
		manager.load("assets/Texturas/contadores/enroques.png", Texture.class);
		manager.load("assets/Texturas/contadores/incorrectos.png", Texture.class);
		manager.load("assets/Texturas/contadores/jugadasPeon.png", Texture.class);
		manager.load("assets/Texturas/contadores/movimientos.png", Texture.class);
		manager.load("assets/Texturas/contadores/noSeguidas.png", Texture.class);
		manager.load("assets/Texturas/contadores/seguidas.png", Texture.class);
		manager.load("assets/Texturas/contadores/promovidas.png", Texture.class);
		manager.load("assets/Texturas/contadores/pergaminoContador.png", Texture.class);
		manager.load("assets/Texturas/contadores/respCorrectas.png", Texture.class);
		manager.load("assets/Texturas/contadores/respIncorrectas.png", Texture.class);

		 manager.load("assets/Texturas/dificultades/1soldado.png", Texture.class);
		 manager.load("assets/Texturas/dificultades/2teniente.png", Texture.class);
		 manager.load("assets/Texturas/dificultades/3capitan.png", Texture.class);
		 manager.load("assets/Texturas/dificultades/4mayor.png", Texture.class);
		 manager.load("assets/Texturas/dificultades/5coronel.png", Texture.class);
		 manager.load("assets/Texturas/dificultades/6general.png", Texture.class);
		
		manager.load("assets/Texturas/estacionPass.png", Texture.class);
		manager.load("assets/Texturas/estacionPass2.png", Texture.class);
		manager.load("assets/Texturas/estacionRep.png", Texture.class);
		manager.load("assets/Texturas/cuadroOscuro.png", Texture.class);
		manager.load("assets/Texturas/cuadroBlanco.png", Texture.class);
		manager.load("assets/Texturas/cuadrotransparente.png", Texture.class);
		manager.load("assets/Texturas/cuadroRojo.png", Texture.class);
		manager.load("assets/Texturas/cuadroVerde.png", Texture.class);
		manager.load("assets/Texturas/pergamino.png", Texture.class);
		manager.load("assets/Texturas/explosion2.png", Texture.class);
	
		
		manager.load("assets/personajes/arismendi.png", Texture.class);
		manager.load("assets/personajes/boves.png", Texture.class);
		manager.load("assets/personajes/fernando.png", Texture.class);
		manager.load("assets/personajes/francisco.png", Texture.class);
		manager.load("assets/personajes/guaicaipuro.png", Texture.class);
		manager.load("assets/personajes/isabel.png", Texture.class);
		manager.load("assets/personajes/miguel.png", Texture.class);
		manager.load("assets/personajes/monteverde.png", Texture.class);
		manager.load("assets/personajes/morillo.png", Texture.class);
		manager.load("assets/personajes/paez.png", Texture.class);
		manager.load("assets/personajes/negroPrimero.png", Texture.class);
		manager.load("assets/personajes/simon.png", Texture.class);
		manager.load("assets/personajes/sucre.png", Texture.class);
		
		manager.load("assets/personajes/Aura/arismendi.png", Texture.class);
		manager.load("assets/personajes/Aura/boves.png", Texture.class);
		manager.load("assets/personajes/Aura/fernando.png", Texture.class);
		manager.load("assets/personajes/Aura/francisco.png", Texture.class);
		manager.load("assets/personajes/Aura/guaicaipuro.png", Texture.class);
		manager.load("assets/personajes/Aura/isabel.png", Texture.class);
		manager.load("assets/personajes/Aura/miguel.png", Texture.class);
		manager.load("assets/personajes/Aura/monteverde.png", Texture.class);
		manager.load("assets/personajes/Aura/morillo.png", Texture.class);
		manager.load("assets/personajes/Aura/paez.png", Texture.class);
		manager.load("assets/personajes/Aura/negroPrimero.png", Texture.class);
		manager.load("assets/personajes/Aura/simon.png", Texture.class);
		manager.load("assets/personajes/Aura/sucre.png", Texture.class);
		
		manager.load("assets/personajes/nombre/arismendi.png", Texture.class);
		manager.load("assets/personajes/nombre/boves.png", Texture.class);
		manager.load("assets/personajes/nombre/fernando.png", Texture.class);
		manager.load("assets/personajes/nombre/francisco.png", Texture.class);
		manager.load("assets/personajes/nombre/guaicaipuro.png", Texture.class);
		manager.load("assets/personajes/nombre/isabel.png", Texture.class);
		manager.load("assets/personajes/nombre/miguel.png", Texture.class);
		manager.load("assets/personajes/nombre/monteverde.png", Texture.class);
		manager.load("assets/personajes/nombre/morillo.png", Texture.class);
		manager.load("assets/personajes/nombre/paez.png", Texture.class);
		manager.load("assets/personajes/nombre/negroPrimero.png", Texture.class);
		manager.load("assets/personajes/nombre/simon.png", Texture.class);
		manager.load("assets/personajes/nombre/sucre.png", Texture.class);
		
		
		
		
		// manager.load("assets/Texturas/fva.jpg", Texture.class);
//		 manager.load("assets/Texturas/jayktec.png", Texture.class);
		
		
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/dama/DamaG.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/torre/TorreG.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/AlfilG.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoG.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/dama/DamaG.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/torre/TorreG.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/alfil/AlfilG.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoG.png", Texture.class);
		
		
		
		/**
		 * packs
		 */
		manager.load("assets/skins/packEstaciones.pack", TextureAtlas.class);
		manager.load("assets/skins/uiskin1.atlas", TextureAtlas.class);
		
		/**
		 *Mapa general 
		 */
		
		manager.load("assets/skins/ninos/sebastian/sebasizquierda.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/sebastian/sebasDerecha.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/sebastian/sebasEspalda.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/sebastian/sebasFrontal.pack", TextureAtlas.class);
		
		
		manager.load("assets/skins/ninos/camila/camilaIzquierda.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/camila/camilaDerecha.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/camila/camilaEspalda.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/camila/camilaFrontal.pack", TextureAtlas.class);
		
		
		manager.load("assets/skins/ninos/andres/andresIzquierda.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/andres/andresDerecha.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/andres/andresEspalda.pack", TextureAtlas.class);
		manager.load("assets/skins/ninos/andres/andresFrontal.pack", TextureAtlas.class);
		
		
		
		/**
		 * Carga de imagenes de las piezas clasicas Ruta:
		 * 
		 * assets/Texturas/PiezasClasicas //
		 */

		 manager.load("assets/Texturas/PiezasClasicas/Torre.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/TorreN.png",
		 Texture.class);
		 
		 manager.load("assets/Texturas/PiezasClasicas/Alfil.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/AlfilN.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/Caballo.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/CaballoN.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/Reina.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/ReinaN.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/Rey.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/ReyN.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/Peon.png",
		 Texture.class);
		 manager.load("assets/Texturas/PiezasClasicas/PeonN.png",
		 Texture.class);
		
		/**
		 * Carga de imagenes de las piezas MiniHeroes patriotas Ruta:
		 * assets/Texturas/PiezasMiniHeroes/patriotas/
		 */

		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/torre/Torre_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoDer.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/CaballoIzq.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/caballo/Caballo_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/Alfil.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/Alfil_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/izquierdaAbajo.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/derechaAbajo.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/izquierdaArriba.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/alfil/derechaArriba.png", Texture.class);
	
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/dama/Dama_selec.png", Texture.class);
		
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReySebas.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/rey/Rey_Sebas_selec.png", Texture.class);
		
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyCamila.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/rey/Rey_Camila_selec.png", Texture.class);
		
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/rey/ReyAndres.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/rey/Rey_Andres_selec.png", Texture.class);
		
		
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/patriotas/peon/Peon_mov.png", Texture.class);

		/**
		 * Carga de imagenes de las piezas MiniHeroes realistas Ruta:
		 * assets/Texturas/PiezasMiniHeroes/realistas/
		 */

		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/torre/Torre_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoDer.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/caballo/CaballoIzq.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/caballo/Caballo_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/alfil/Alfil.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/alfil/Alfil_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/dama/Dama_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/rey/Rey_selec.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon.png", Texture.class);
		manager.load("assets/Texturas/PiezasMiniHeroes/realistas/peon/Peon_selec.png", Texture.class);

		/**
		 * Carga de los Skins " Ruta:assets/skins/TexturasSkins/"
		 */

		manager.load("assets/skins/TexturasSkins/botones.png", Texture.class);
		manager.load("assets/skins/TexturasSkins/uiskin1.png", Texture.class);

		/**
		 * Carga de Sonidos del juego Ruta:"assets/Sonidos/
		 */
		manager.load("assets/Sonidos/step.ogg", Sound.class);
		manager.load("assets/Sonidos/paso.ogg", Sound.class);
		manager.load("assets/Sonidos/caballo.ogg", Sound.class);
		manager.load("assets/Sonidos/torre.ogg", Sound.class);
		manager.load("assets/Sonidos/alfil.ogg", Sound.class);
		manager.load("assets/Sonidos/alfil.ogg", Sound.class);
		manager.load("assets/Sonidos/cortinaFondo.ogg", Music.class);
		manager.load("assets/Sonidos/ALEGRE.ogg", Music.class);
		manager.load("assets/Sonidos/jingle.ogg", Music.class);
		manager.load("assets/Sonidos/lenta.ogg", Music.class);
		manager.load("assets/Sonidos/misterio.ogg", Music.class);
		manager.load("assets/Sonidos/triunfal.ogg", Music.class);
//		manager.load("assets/Sonidos/triunfal2.wav", Music.class);
		manager.load("assets/Sonidos/sonidoDerrota.mp3", Sound.class);
		manager.load("assets/Sonidos/movIncorrecto.ogg", Sound.class);
		manager.load("assets/Sonidos/campana.mp3", Sound.class);
		manager.load("assets/Sonidos/sonidoTriunfo.ogg", Sound.class);
		manager.load("assets/Sonidos/moneda.mp3", Sound.class);
		manager.load("assets/Sonidos/disparoSeco.mp3", Sound.class);
		manager.load("assets/Sonidos/sonidoMenu.mp3", Sound.class);

		manager.load("assets/Sonidos/moneda_gasta.wav", Sound.class);
		game.setManager(manager);
		
	}



	public AssetManager getManager() {
		return manager;
	}
	
	
	
}
