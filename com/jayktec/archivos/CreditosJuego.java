package com.jayktec.archivos;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.grafico.Screen.ScreenManager;

import net.indiespot.media.MoviePlayer;

public class CreditosJuego extends ScreenManager {
	
//	private Stage stage;
////	private TextButton cargar;
//	private TextButton salir;
//	public Miniheroes game1;
//	private Skin skin, skin2;
//	private ScrollPane lista;
//	private TextureAtlas buttonAtlas;
//	private BitmapFont font;
//	private TextButtonStyle textButtonStyle;
//	TiledMap tiledMap;
//	OrthographicCamera camera;
//	TiledMapRenderer tiledMapRenderer;
//	private static String ruta = "./Partidas/Creditos.txt";
//	 File creditos; 
//	 FileReader lectorCreditos;
//	 String stringCreditos;
//	 final Table scrollTable = new Table();
//	 final ScrollPane scroller = new ScrollPane(scrollTable);
//     final Table table = new Table();
//     ScrollPane.ScrollPaneStyle scrollStyle;
	
	private SpriteBatch batch;
	private Texture playerTexture;
	public Miniheroes game;
	private MoviePlayer player;
	private File mov;
	float delay = 0;
	boolean flag = true;
	private String video;
	private Boolean modificado = false;
	private String partida;
	private String minijuego;
	private int w = Gdx.graphics.getWidth();
	private int h= Gdx.graphics.getHeight();

	public CreditosJuego(Miniheroes Pgame) {
		super(Pgame);
		// TODO Auto-generated constructor stub
		game = Pgame;
	
	}	

/**
 * @return the video
 */
public String getVideo() {
	return video;
}



/**
 * @param video the video to set
 */
public void setVideo(String video) {
	this.video = video;
}



@Override
public void show() {

	super.stopMusic();
	batch = new SpriteBatch();
	video = "assets/video/JayktecPrueba.mp4";
	FileHandle fh = Gdx.files.internal(video);
	String path = fh.file().getAbsolutePath();
	mov = new File(path);
	flag = true;
	try {
		player = new MoviePlayer(mov);
		
		

	} catch (IOException e) {

		e.printStackTrace();
	}
	playerTexture = new Texture(player.movie.width(), player.movie.height(),

			Format.RGBA8888) {

		@Override
		public void bind() {
			Gdx.gl.glBindTexture(0, player.textureHandle);

		}
	};

	//Gdx.input.setInputProcessor();
	
}


@Override
public void hide() {

	player.stop();
	batch.dispose();	
	batch =null;
	player = null;

	playerTexture.dispose();
	playerTexture = null;
	video =null;
	System.gc();
	//Gdx.input.setInputProcessor(null);
	//dispose();
}


@Override
public void render(float delta) {
	Gdx.gl.glViewport(0, 0, w, h);
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
	Gdx.gl.glEnable(GL20.GL_BLEND);
	Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

	if (player != null) {
		player.tick();
		player.syncTexture(5);

		/**
		 * Renderizar el video
		 */
		batch.begin();
		float lh = player.movie.height() * w / player.movie.width();
		batch.draw(playerTexture, 0, (h - lh) / 2, w, lh);
		batch.end();
	} else {
		delay += Gdx.graphics.getDeltaTime();
	}
	
	
	if (flag == false) {

		if (player.isPlaying() == false|| Gdx.input.isKeyPressed(Keys.ESCAPE)) {
		
				game.setScreen(game.inicio);
			

		}
	}
	flag = false;

}


public void setWidth(int pWidth){		
	w = pWidth;
}
public void setHeight(int pHeight){		
	h = pHeight;
}
}
//	@Override
//	public void show() {
//		float w = Gdx.graphics.getWidth();
//		float h = Gdx.graphics.getHeight();
//		camera = new OrthographicCamera();
//		// camera.setToOrtho(false,w,h);
//		// camera.update();
//
//		tiledMap = new TmxMapLoader().load("assets/mapasTiled/fondoPrincipal1.tmx");
//
//		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
//
//		stage = new Stage();
//		skin2 = new Skin(Gdx.files.internal("uiskin.json"));
//		font = new BitmapFont();
//		skin = new Skin();
//		buttonAtlas = new TextureAtlas(Gdx.files.internal("assets/skins/botones.pack"));
//		skin.addRegions(buttonAtlas);
//
//		textButtonStyle = new TextButtonStyle();
//		textButtonStyle.font = font;
//		textButtonStyle.up = skin.getDrawable("boton");
//		textButtonStyle.down = skin.getDrawable("botonazul");
//
////		cargar = new TextButton("Cargar Partida", textButtonStyle);
//		salir = new TextButton("Salir", textButtonStyle);
//
//		
//		
//		
//
//		
//		
////Creditos Label
//
//	
//		this.stage = new Stage();
//        Gdx.input.setInputProcessor(this.stage);
//       // final String creditosFinal = leer(ruta);
//        final String creditosFinal = readFile(ruta);
//        final Label lista = new Label(creditosFinal, skin2);
//        lista.setAlignment(Align.center);
//        lista.setWrap(true);
////        final Table scrollTable = new Table();
//        scrollTable.add(lista);
// 
//        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
//        ScrollPane scrollPane=new ScrollPane(table, skin);
//   
////        final ScrollPane scroller = new ScrollPane(scrollTable);
////        final Table table = new Table();
//        table.setFillParent(true);
//        table.add(scroller).fill().expand();
//        this.table.add(lista).fill().expand();
//        this.stage.addActor(table);
//        this.stage.addActor(scrollTable);
//      //  this.stage.addActor(lista);
//        this.stage.addActor(scrollPane);
//        scrollPane.setPosition(300, 300);
//
//       
//			
//		
//		
//
//
//		salir.addCaptureListener(new ChangeListener() {
//			@Override
//			public void changed(ChangeEvent event, Actor actor) {
//				game1.setScreen(game1.inicio);
//
//			}
//		});
//
//		camera = (OrthographicCamera) stage.getCamera();
//
//		stage.addActor(salir);
//
//		
//		salir.setSize(50, 20);
//
//
//		salir.setPosition(10, 10);
//
//		Gdx.input.setInputProcessor(stage);
//	
//		
//
//	
//
//		
//	}
//	
//	public String leer (String ruta){
//	 
//	 try{
//		 creditos  = new File(ruta);
//		 lectorCreditos = new FileReader(creditos);
//		 BufferedReader br = new BufferedReader(lectorCreditos);
//		 stringCreditos = "";
//		 String aux = "";
//		 while(true)
//		 {
//			 aux = br.readLine();
//			 if(aux!=null){
//				 stringCreditos = stringCreditos+ "\f" +aux+"\n" ;
//			} else{
//				 break;
//			}
//			 }br.close();
//			 lectorCreditos.close();
//			 return stringCreditos;
//		 }catch(IOException e){
//			 System.out.println("Error:"+e.getMessage());
//		 }
//	 return null;
//	}
//	
//	@SuppressWarnings("resource")
//	public String readFile (String ruta) {
//		String aux = "";
//		stringCreditos = ""; 
//		try{    
//		
//		  new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta, true), "ISO-8859-1"));        
//		 
//		        BufferedReader  br = new BufferedReader (new InputStreamReader (new FileInputStream (ruta), "utf-8"));
//		                 
//		            while ((br.readLine())!=null) {
//		            	
//		            	 aux = br.readLine();
//		    			 if(aux!=null){
//		    				 stringCreditos = stringCreditos  +aux+"\n" ;
//		    			} else{
//		    				 break;
//		    			}
//		                   
//		            }br.close();
//		            return stringCreditos;
//		        }
//		        catch (Exception e){
//		 
//		        }
//		       return null;
//		   
//		 }
//		  
//	
//	
//	
//	@Override
//	public void hide() {
//		stage.dispose();
//		skin.dispose();
//		skin2.dispose();
//		buttonAtlas.dispose();
//		font.dispose();
//		tiledMap.dispose();
//		Gdx.input.setInputProcessor(null);
//	}
//	
//	@Override
//	public void dispose() {
//
//	}
//
//	@Override
//	public void render(float delta) {
//		
//		Gdx.gl.glClearColor(0, 0, 0, 0);
//		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		camera.update();
//		tiledMapRenderer.setView(camera);
//		tiledMapRenderer.render();
//		stage.act();
//		stage.draw();
//		Gdx.graphics.requestRendering();
//
//	}
//	
//	@Override
//	public void resize(int width, int height) {
//		
//		camera.setToOrtho(false, width, height);
//		camera.viewportWidth = width;
//		camera.viewportHeight = height;
//		camera.position.x = super.getGameWidth() / 2;
//		camera.position.y = super.getGameHeight() / 2;
//	}
//}
