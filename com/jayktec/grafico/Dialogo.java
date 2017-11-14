//////////////////////////////////////////////////////////////
//www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                OpcionesScreen.java                       //
//                   Descripcion                            //
//             Pantalla para el menu de opciones            //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Vladimir Betancourt   05/06/2016     Version Inicial      //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
//import java.io.File;
//import java.io.IOException;
import java.util.ArrayList;
//import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap.Values;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.jayktec.grafico.Enums.*;
import com.jayktec.grafico.Screen.ScreenManager;

import net.indiespot.media.MoviePlayer;

//import net.indiespot.media.MoviePlayer;

public class Dialogo extends Dialog implements Disposable {

	private Preferencias preferencias;
	private CheckBox musica, sonido,pantallaCompleta;
	private ButtonGroup<Button> radioButtons = new ButtonGroup<Button>();
	private ArrayList<TextField> textFieldGroup = new ArrayList<TextField>();
	private eTipoDialogo tipoDialogo;
	

	private Object resultadosDialogo, valorDialogo;
	private Heroe_Dificultad vHeroe;
	private VideoPlayerActor player;	
	//private Texture playerTexture;
	private SpriteBatch batch1 = new SpriteBatch();
	private float videoX,videoY, margenVideoX,  margenVideoY;  
	private TextureAtlas atlas;		
	private static Skin skin=new Skin(Gdx.files.internal("assets/skins/uiskin1.json"));;
	private boolean flag = true;
	private eTipoVideo tipoVideo = null;
	private float volumenGeneral = 0.5f; 
	
	public static  Dialogo instance = null; 
	public  static final int MaxCnt = 10;
	public static long m_cnt = 0;
	String result;
	List<String> fileList;
	Label fileListLabel;
	FileHandle  fileHandle ;
	File selected;
	ScrollPane scrollPane;
	TextField fileNameInput;
	FileHandle[] list;
	String[] fileArrayAux, fileArrayAux1;
	boolean playerPaused = false; 
	public TextButtonStyle vTextButtonStyle;
	TextButtonStyle repetirvideo = textButtonStyle("repetirvideo");	        			
	TextButton repetir= new TextButton("", repetirvideo);
	TextButtonStyle pausarvideo = textButtonStyle("pausa");
	TextButton pausa= new TextButton("", pausarvideo);
	TextButtonStyle aceptar = textButtonStyle("aceptar");	    
	TextButtonStyle salir = textButtonStyle("salir");	
	TextButtonStyle si = textButtonStyle("si");	
	TextButtonStyle no = textButtonStyle("no");	
	TextButtonStyle cancelar = textButtonStyle("cancelar");	
//	   public static Dialogo getInstance(String pPath, eTipoVideo pTipoVideo) {
//		   
//		      if(instance == null) {
//		         instance = new Dialogo("", pPath, pTipoVideo);
//		      }
//		      return instance;
//		   }

   
	private FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
        	String path = pathname.getPath();
            return path.matches(".*(?:pgn)")||pathname.isDirectory()&&!pathname.isHidden();           
        }
    };

	/**
	 * Muestra un dialogo para las opciones del juego
	 * 
	 * @param pTitulo
	 *            : titulo de dialogo
	 */

    
	public Dialogo(String pTitulo,ScreenManager pScreen) {
		
		super(pTitulo, skin, "dialog");
		
		
//		atlas = new TextureAtlas(Gdx.files.internal("assets/skins/uiskin1.atlas"));
//		skin.addRegions(atlas);
		
		super.getTitleTable().padTop(70);
		super.getTitleTable().padLeft(50);

		musica = new CheckBox(" Música de Fondo", skin);
		sonido = new CheckBox(" Efectos de Sonido", skin);
	
		pantallaCompleta = new CheckBox(" Pantalla Completa",skin);

		preferencias = Preferencias.getInstance();
		preferencias.load();

		float vVolumen = preferencias.getPreferenciav("volumen");
		sonido.setChecked(preferencias.getPreferencia("sonido"));
		musica.setChecked(preferencias.getPreferencia("musica"));
		pantallaCompleta.setChecked(preferencias.getPreferencia("pantallacompleta"));

		super.getContentTable().padTop(140);
		super.getContentTable().padLeft(50);
		super.getContentTable().align(Align.center | Align.top);
		super.getContentTable().add(musica).padLeft(50);
		super.getContentTable().row();
		
		final Music musica= pScreen.getMusic();
		final Slider volume = new Slider(0.1f, 1, 0.1f, false, skin);
		volume.setValue(vVolumen);
		volume.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				musica.setVolume(volume.getValue());
				volumenGeneral = volume.getValue();
				pScreen.setVolume(volumenGeneral);
			}
		});
		
		super.getContentTable().add(volume).padLeft(56);
		super.getContentTable().row();
		
		super.getContentTable().add(sonido).padLeft(56);
		super.getContentTable().row();
		
		//volume.setPosition(500, 500);
			
			
		
		//super.getContentTable().add(pantallaCompleta).padLeft(62);
		setValue(null);
		setValue(0);
		super.button("Si", true); // sends "true" as the result
		super.getButtonTable().add();
		super.button("No", false);// .padLeft(100); //sends "false" as the
									// result
		super.key(Keys.ENTER, true); // sends "true" when the ENTER key is
										// pressed
		super.key(Keys.ESCAPE, false);
		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.Preferencias;
		
	}

	/**
	 * Muestra un dialogo de informacion, con un boton para aceptar
	 * 
	 * @param pTitulo
	 *            : titulo de dialogo
	 * @param pMensaje
	 *            : mensaje, maximo de nueve lineas de 30 caracteres c/u siempre
	 *            aparece centrado
	 */
	public Dialogo(String pTitulo, ArrayList<String> pMensaje) {

		super(pTitulo,skin, "dialog");
	//	WindowStyle windowStyle;

	
		super.getTitleTable().padTop(70);
		super.getTitleTable().padLeft(50);

		super.getContentTable().padTop(40);
		super.getContentTable().padLeft(0);

		super.getContentTable().align(Align.center);
		// super.getContentTable().row();
		int i = 3;
		int vPadLeft = 0;
		
		for (String lineaMensaje : pMensaje) {
			super.getContentTable().add(lineaMensaje).padLeft(vPadLeft);
			super.getContentTable().row();
			vPadLeft = vPadLeft + i;
		}

		super.button("Aceptar", true); // sends "true" as the result

		super.getButtonTable().add();


		super.key(Keys.ENTER, true); // sends "true" when the ENTER key is pressed	
		// pressed
		super.key(Keys.ESCAPE, true); // send "truFe2 whenn the CANCEL key is pressed


		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.Aceptar;
		//super.setModal(false);
		setValue(null);
		//setValue(0);
	}
	
	/**
	 * Muestra un dialogo de informacion, con un boton para aceptar
	 * 
	 * @param pTitulo
	 *            : titulo de dialogo
	 * @param pTexture
	 *            : mensaje, maximo de nueve lineas de 30 caracteres c/u siempre
	 *            
	 */
	public Dialogo(String pTitulo, Texture pTexture) {

		super("", skin, "mensaje");
		super.setWidth(300);
		super.setHeight(300);
		
		Image imagenMensaje= new Image(pTexture); 
		
		super.getContentTable().add(imagenMensaje).align(Align.center|Align.center);

		
		
        
		
		super.button(" ", true , aceptar);
		
		super.getButtonTable().add();


		super.getButtonTable().add();

		super.key(Keys.ENTER, true); // sends "true" when the ENTER key is pressed	
		// pressed
		super.key(Keys.ESCAPE, true); // send "true2 whenn the CANCEL key is pressed

		super.getButtonTable().align(Align.center | Align.top);
		
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.Aceptar;
		//super.setModal(false);
		setValue(null);
		//setValue(0);
	}


	/**
	 * Muestra un dialogo de informacion, con un boton para aceptar
	 * 
	 * @param pTitulo
	 *            : titulo de dialogo
	 * @param pTexture
	 *            : mensaje, maximo de nueve lineas de 30 caracteres c/u siempre
	 *            
	 */
	public Dialogo(String pTitulo, Texture pTexture, boolean verfondo) {

		super("", skin, "mensaje2");
		super.setWidth(300);
		super.setHeight(300);
		
		Image imagenMensaje= new Image(pTexture); 
		
		super.getContentTable().add(imagenMensaje).align(Align.center|Align.center);

		
		
        
		
		super.button(" ", true , aceptar);
		
		super.getButtonTable().add();


		super.getButtonTable().add();

		super.key(Keys.ENTER, true); // sends "true" when the ENTER key is pressed	
		// pressed
		super.key(Keys.ESCAPE, true); // send "true2 whenn the CANCEL key is pressed

		super.getButtonTable().align(Align.center | Align.top);
		
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.Aceptar;
		//super.setModal(false);
		setValue(null);
		//setValue(0);
	}


	/**
	 * Muestra un dialogo con los botones si, no y opcionalmente cancelar
	 * 
	 * @param pTitulo:
	 *            titulo del dialogo
	 * @param pMensaje
	 *            mensaje, maximo de nueve lineas de 30 caracteres c/u siempre
	 *            aparece centrado
	 * @param pCancelar
	 *            boton opcional de cancelar
	 */

	public Dialogo(String pTitulo, ArrayList<String> pMensaje, String pCancelar) {

		super(pTitulo, skin, "dialog");
		super.getTitleTable().padTop(70);
		super.getTitleTable().padLeft(50);

		super.getContentTable().padTop(40);
		super.getContentTable().padLeft(0);

		super.getContentTable().align(Align.center);
		// super.getContentTable().row();
		int i = 3;
		int vPadLeft = 0;

		for (String lineaMensaje : pMensaje) {
			super.getContentTable().add(lineaMensaje).padLeft(vPadLeft);
			super.getContentTable().row();
			vPadLeft = vPadLeft + i;
		}

		setValue(null);
		setValue(0);
		super.button("Si", true);
		super.getButtonTable().add();
		super.button("No", false);

		if (pCancelar.length() > 0) {
			super.getButtonTable().add();
			super.button(pCancelar, false);
		}

		super.key(Keys.ENTER, true);
		super.key(Keys.ESCAPE, false);

		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.SiNoCancelar;
		setValue(null);
		
	}


	/**
	 * Muestra un dialogo con los botones si, no y opcionalmente cancelar
	 * 
	 * @param pTitulo:
	 *            titulo del dialogo
	 * @param pMensaje
	 *            mensaje, maximo de nueve lineas de 30 caracteres c/u siempre
	 *            aparece centrado
	 * @param pCancelar
	 *            boton opcional de cancelar
	 */

	public Dialogo(String pTitulo, Texture pTexture, String pCancelar) {
		super("", skin, "mensaje");
//		Skin skin = new Skin(Gdx.files.internal("assets/skins/uiskin1.json"));
//		skin.addRegions(atlas);
//		
		
		super.setWidth(300);
		super.setHeight(300);
		
		Image imagenMensaje= new Image(pTexture); 
		
		super.getContentTable().add(imagenMensaje).align(Align.center|Align.center);

		setValue(null);
		setValue(0);
		
        
		
		super.button(" ", true , si);
		
		super.getButtonTable().add();
		
		super.button("", false, no);

		if (pCancelar.length() > 0) {
			super.getButtonTable().add();
			super.button(pCancelar, false);
		}

		super.key(Keys.ENTER, true); 
		super.key(Keys.ESCAPE, false);

		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.SiNoCancelar;
		setValue(null);
		
	}

	public Dialogo(String pTitulo, Texture pTexture, String pCancelar, boolean cuadroNegro) {
		super("", skin, "mensaje2");
//		Skin skin = new Skin(Gdx.files.internal("assets/skins/uiskin1.json"));
//		skin.addRegions(atlas);
//		
		
		super.setWidth(300);
		super.setHeight(300);
		
		Image imagenMensaje= new Image(pTexture); 
		
		super.getContentTable().add(imagenMensaje).align(Align.center|Align.center);

		setValue(null);
		setValue(0);
		
        
		
		super.button(" ", true , si);
		
		super.getButtonTable().add();
		
		super.button("", false, no);

		if (pCancelar.length() > 0) {
			super.getButtonTable().add();
			super.button(pCancelar, false);
		}

		super.key(Keys.ENTER, true); 
		super.key(Keys.ESCAPE, false);

		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.SiNoCancelar;
		setValue(null);
		
	}

	public Dialogo(Texture[] pTexture) {
		super("", skin, "mensaje");
//		super.debug();

		super.setWidth(300);
		super.setHeight(300);
		
		Image imagenReina = new Image(pTexture[0]);
		imagenReina.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {				
				setValue(1);
				result(true);
			}
		});
		Image imagenTorre = new Image(pTexture[1]);
		imagenTorre.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {				
				setValue(2);
				result(true);
			}
		});
		
		
		Image imagenAlfil = new Image(pTexture[2]);
		imagenAlfil.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {				
				setValue(3);
				result(true);
			}
		});
		
		Image imagenCaballo =new Image(pTexture[3]);
		imagenCaballo.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {				
				setValue(4);
				result(true);
			}
		});
		super.getContentTable().row();
		super.getContentTable().add(imagenReina).align(Align.center|Align.center).width(100).height(100);
		//super.getContentTable().row();
		super.getContentTable().add(imagenTorre).align(Align.center|Align.center).width(100).height(100);
		
		super.getContentTable().row();
		super.getContentTable().add(imagenAlfil).align(Align.center|Align.center).width(100).height(100);
		
		super.getContentTable().add(imagenCaballo).align(Align.center|Align.center).width(100).height(100);
				
		tipoDialogo = eTipoDialogo.Promocion;
		setValue(0);


	}

	/**
	 * Muestra un dialogo radioButton
	 * 
	 * @param pTitulo:
	 *            titulo del dialogo
	 * @param pMensaje:
	 *            texto de las opciones, maximo de nueve lineas de 30 caracteres
	 *            c/u siempre aparece centrado
	 * @param pMultipleOpciones
	 *            permite escoger multiples opciones
	 */
	public Dialogo(String pTitulo, ArrayList<String> pMensaje, boolean pMultipleOpciones) {
		super(pTitulo, skin, "dialog");
		
		super.getTitleTable().padTop(70);
		super.getTitleTable().padLeft(50);
		super.getContentTable().padTop(40);
		super.getContentTable().padLeft(0);

		super.getContentTable().align(Align.center);
		// super.getContentTable().row();
		// int i = 3;
		int vPadLeft = 40;

		radioButtons.setMinCheckCount(1);
		
		if (pMultipleOpciones)
			radioButtons.setMaxCheckCount(9);

		ButtonStyle radioButtonStyle = new ButtonStyle();
		radioButtonStyle = new TextButtonStyle();
		radioButtonStyle.up = skin.getDrawable("check-off");
		radioButtonStyle.checked = skin.getDrawable("check-on");
		radioButtonStyle.down = skin.getDrawable("check-on");
		
		if (pMensaje.size()>14) 
		super.getContentTable().row().height(15);
		
		for (String lineaMensaje : pMensaje) {
			Button radioButton = new Button();
			// radioButton.setChecked(false);
			radioButton.setStyle(radioButtonStyle);
			radioButtons.add(radioButton);
			super.getContentTable().add(radioButton).padLeft(vPadLeft);
			super.getContentTable().add(lineaMensaje);
			
			if (pMensaje.size()>14)
				super.getContentTable().row().height(15);
			else
				super.getContentTable().row();
			
			// vPadLeft = vPadLeft + i;
		}

//        scrollPane = new ScrollPane(radioButtons, skin);		        		        
//        this.getContentTable().add(scrollPane).size(300, 300).padLeft(30);

		setValue(null);
		setValue(0);
		super.button("Aceptar", true);
		super.getButtonTable().add();
		super.button("Cancelar", false);

		super.key(Keys.ENTER, true);
		super.key(Keys.ESCAPE, false);

		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.Opciones;
	}

	/**
	 * Muestra un dialogo radioButton con imagenes
	 * 
	 * @param pTitulo:
	 *            titulo del dialogo
	 * @param pMensaje:
	 *            texto de las opciones, maximo de nueve lineas de 30 caracteres
	 *            c/u siempre aparece centrado
	 * @param pGame:
	 *            juego desde donde es instaciada esta clase
	 * @param
	 */
	public Dialogo(String pTitulo, ArrayList<String> pMensaje, Miniheroes pGame, final eTipoPersonaje pPersonaje) {
		super(pTitulo, skin, "dialog");
		
		super.getTitleTable().padTop(70);
		super.getTitleTable().padLeft(50);
		super.getContentTable().padTop(40);
		super.getContentTable().padLeft(0);

		super.getContentTable().align(Align.center);

		// super.getContentTable().row();
		// int i = 3;
		int vPadLeft = 40;

		radioButtons.setMinCheckCount(1);
		radioButtons.setMaxCheckCount(1);

		ButtonStyle radioButtonStyle = new ButtonStyle();
		radioButtonStyle = new TextButtonStyle();
		radioButtonStyle.up = skin.getDrawable("check-off");
		radioButtonStyle.checked = skin.getDrawable("check-on");
		radioButtonStyle.down = skin.getDrawable("check-on");

		vHeroe = new Heroe_Dificultad(pGame);
		vHeroe.setSize(130, 225);
		for (final String lineaMensaje : pMensaje) {
			Button radioButton = new Button();

			radioButton.addListener(new ChangeListener() {

				@Override
				public void changed(ChangeEvent event, Actor actor) {
						vHeroe.personaje(lineaMensaje, pPersonaje);
				}

			});

			// radioButton.setChecked(false);
			radioButton.setStyle(radioButtonStyle);
			radioButtons.add(radioButton);
			super.getContentTable().add(radioButton).padLeft(vPadLeft);
			super.getContentTable().add(lineaMensaje);
			super.getContentTable().row();
		}

//		System.out.println("columnas: " + super.getContentTable().getColumns());
		vHeroe.personaje(pMensaje.get(0), pPersonaje);
		super.getContentTable().add();
		super.getContentTable().add();
		super.getContentTable().add(vHeroe).align(Align.center | Align.right).padTop(15 * pMensaje.size());

		// super.getContentTable().add(vHeroe).padTop(100);
		super.getContentTable().row();
		setValue(null);
		setValue(0);
		super.button("Aceptar", true);
		super.getButtonTable().add();
		super.button("Cancelar", false);

		super.key(Keys.ENTER, true);
		super.key(Keys.ESCAPE, false);

		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.OpcionesImagenes;

	}

	/**
	 * Muestra un dialogo de input
	 * 
	 * @param pTitulo:
	 *            titulo del dialogo
	 * @param pMensaje:
	 *            texto de los entradas, maximo de nueve lineas de 30 caracteres
	 *            c/u siempre aparece centrado
	 * @param pCantidadEntradas:
	 *            textfields a mostrar en el dialogo
	 * @param
	 */

	public Dialogo(String pTitulo, ArrayList<String> pMensaje, int pCantidadEntradas) {

		super(pTitulo, skin, "mensaje");
		
		
		Skin skin2 = new Skin(Gdx.files.internal("uiskin.json"));
		TextureAtlas pack = new TextureAtlas(Gdx.files.internal("assets/skins/juegoPrincipal.pack"));
		skin2.addRegions(pack);

		super.getTitleTable().padTop(70);
		super.getTitleTable().padLeft(50);
		super.getContentTable().padTop(40);
		super.getContentTable().padLeft(0);

		super.setWidth(400);
		super.setHeight(350);
		
		super.getContentTable().align(Align.center);
		int vPadLeft = 40;

		for (int i = 0; i < pCantidadEntradas; i++) {
			TextField textField = new TextField("", skin, "default");
			textField.setWidth(300);
			super.getContentTable().add(pMensaje.get(i)).padLeft(vPadLeft);
			super.getContentTable().add(textField);
			super.getContentTable().row();
			textFieldGroup.add(textField);
		}
		
		setValue(null);
		        			
		super.button(" ", true , aceptar);			
		super.getButtonTable().add();
		
		        			
		super.button(" ", false , cancelar);									
		
		TextButtonStyle stycargarFen = new TextButtonStyle();
		stycargarFen.font=new BitmapFont();
		stycargarFen.up = skin2.getDrawable("cargarUp");
		stycargarFen.down = skin2.getDrawable("cargarDown");
		stycargarFen.over = skin2.getDrawable("cargarOver");

		
		//TextButtonStyle cargar = textButtonStyle("cancelar");	        														        			
		super.button(" ", "C" , stycargarFen);							
		
		super.key(Keys.ENTER, true);
		super.key(Keys.ESCAPE, false);

		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		// super.setColor(155, 155, 155, 0);
		tipoDialogo = eTipoDialogo.Entrada;
	}

	/**
	 * Muestra un dialogo para las respuesta de trivia incorrectas
	 * 
	 * @param pTitulo:
	 *            titulo de la ventana de dialogo
	 * @param pPregunta
	 *            : pregunta en la que falló el usuario
	 * @param pRespuestaCorrecta
	 *            : respuesta correcta a la pregunta
	 * @param pExplicacion
	 *            : explicación para la respuesta correcta.
	 */

	public Dialogo(String pTitulo, String pPathImagen) {

		super(pTitulo, skin, "dialog");

		super.getTitleTable().padTop(70);
		super.getTitleTable().padLeft(50);

		super.getContentTable().reset();

		super.getContentTable().align(Align.top);

//		int vPadLeft = 40;
		super.getContentTable().setLayoutEnabled(false);
		// super.getContentTable().padTop(10);
		// super.getContentTable().padLeft(20);

		Resolucion respuesta = new Resolucion(pPathImagen);

		super.getContentTable().clear();

		// super.getContentTable().padLeft(20);

		super.getContentTable().add(respuesta).align(Align.center | Align.top).padBottom(100);
		// super.getContentTable().add("").padTop(10).padLeft(40);

		super.button("Aceptar", true);
		// super.getButtonTable().add();
		// super.button("Cancelar", false);

		super.key(Keys.ENTER, true);
		super.key(Keys.ESCAPE, false);

		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		// super.setColor(155, 155, 155, 0);
		tipoDialogo = eTipoDialogo.Entrada;
	}

/**
 * Muestra un dialogo para el video de ayuda
 * @param pTitulo título del dialogo
 * @param pPathVideo path del video a mostrar en el dialogo 
 * @param pSiNo texto de los botones del dialogo {"repetir video","salir"}
 */
	public Dialogo() {
		//TODO
		super("   " + "", skin, "dialog");
	
System.out.println("termine de instanciar videoscreen");
	}	

	//TODO
	public void create(String pPathVideo ,eTipoVideo pTipoVideo, boolean noMostrarSalir){
		
		System.out.println("iniciando");
		super.getTitleTable().padTop(70);
		super.getTitleTable().padLeft(50);		
		tipoVideo = pTipoVideo;
		m_cnt++;

		System.out.println("path:"+pPathVideo);
		super.getContentTable().clear();		
		System.out.println("player : "+player);
		
		
		player=new VideoPlayerActor(pPathVideo);
		
		
		super.getContentTable().remove();
		
		//super.getContentTable().add(player);
		if (pTipoVideo.equals(eTipoVideo.VideoInfo)){ // no mostrar botones
			
		}else if (pTipoVideo.equals(eTipoVideo.Victoria)){ // sólo mostrar acpetar
			super.key(Keys.ENTER, true);
			super.key(Keys.ESCAPE, true);				
			    			
			super.button(" ", true , aceptar);			
			super.getButtonTable().add();
			
		}else if (pTipoVideo.equals(eTipoVideo.Historia) || pTipoVideo.equals(eTipoVideo.Biografia) || pTipoVideo.equals(eTipoVideo.Ayuda)){ // boton se repetir  y salir
			if (!noMostrarSalir){
				super.key(Keys.ENTER, false);			
				super.key(Keys.ESCAPE, false);
			}
			
			
			
			

			repetir.addCaptureListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					setResult("repetir");
					if (player!=null)
					{
						
						System.out.println("player lleno "+player.getZIndex()+ " "+ player.isVisible());
						playerPaused = true;
						player.setZIndex(300);
						player.restart();
						
						Timer.schedule(new Task() {
							@Override
							public void run() {
								playerPaused= false;
							}
						}, 1f);
					}
					else System.out.println("player nulo");
				}

			});
			
			super.getButtonTable().add(repetir);		
			
			
			

			pausa.addCaptureListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					setResult("pausa");
					if (player!=null)						
						if (player.isPlaying())
							pause();						
						else							
							resume();				
				}

			});
			
			super.getButtonTable().add(pausa);		
			if (!noMostrarSalir){
				      			
				super.button(" ", false , salir);			
				super.getButtonTable().add();	
			}
		}
		else  if (pTipoVideo.equals(eTipoVideo.Derrota)){ // si y no			
			super.key(Keys.ENTER, true);
			super.key(Keys.ESCAPE, false);
			
			        			
			super.button(" ", true , si).align(Align.bottom);			
			super.getButtonTable().add().align(Align.bottom);
			
			        			
			super.button(" ", false , no).align(Align.bottom);			
		}
		else {
			super.key(Keys.ENTER, false);
			super.key(Keys.ESCAPE, false);
			
			      			
			super.button(" ", false, cancelar);			
			super.getButtonTable().add();			
		}
		

		super.getButtonTable().align(Align.center | Align.top);
		super.getButtonTable().padBottom(19);
		tipoDialogo = eTipoDialogo.Video;
System.out.println("termine de crear videoscreen");
		
		
	}
	
	
	
	
	
	    
	   public void setFilter (File pFile) {		  
		   filter.accept(pFile);
	        }
		    
		    
	
	 private void CambiarDirectorio(FileHandle pFileHandle) {
		// System.out.println("CambiarDirectorio:" + pFileHandle.path());
		 fileHandle  = pFileHandle;
		 
	        fileListLabel.setText(fileHandle.path());
	        
	         Array<String> items = new Array<String>();
	         list = fileHandle.list(filter);
	        
	        items.add("/..");
	        
	        for (final FileHandle handle : list) {
	            items.add(handle.name());
	        }
	        fileList.setSelected(null);
	        fileList.setItems(items);
	        
	    }

	/**
	 * dialogo de file chosser
	 * 
	 */	
	public Dialogo(String pTitulo, Boolean pBoolean) {

		super("   ", skin, "dialog");
//		super.setDebug(true);
		super.setWidth(500);
		super.setHeight(500);
			
		
		 
		//fileHandle = Gdx.files.internal(Gdx.files.getLocalStoragePath());
		fileHandle = Gdx.files.internal(System.getProperty("user.home"));
		
		//System.out.println("Gdx.files.getLocalStoragePath()" + Gdx.files.getLocalStoragePath());			
		
		fileArrayAux = new String[fileHandle.list().length+1];
		
		    list = fileHandle.list(filter);
		    int i=0;
		    fileArrayAux[i]  = "/..";
        	i++;
	        for (FileHandle handle : list) {
	       // 	System.out.println("extension" + handle.extension());
	        	//if ( !handle.file().isHidden()&& ( handle.isDirectory() || handle.extension().toLowerCase().contains("pgn"))) {	        		       
		        	fileArrayAux[i] = handle.file().getName();
		         	i++;
	        	//}	        		         
	        }
	       
	       fileArrayAux1  = new String[i];
	        for (int j=0; j<i;j++)
	        	fileArrayAux1[j]=fileArrayAux[j];
	        	        	        
//	        System.out.println("directorios: " + (i));
		        fileList = new List<String>(skin);
	            fileList.setItems(fileArrayAux1);
	            	                       
		        this.getContentTable().top().left();

		        fileListLabel = new Label(fileHandle.path(), skin);
		        fileListLabel.setAlignment(Align.left);
		        this.getContentTable().add(fileListLabel);		        		        
		        this.getContentTable().row();
		        
		        fileList.getSelection().setProgrammaticChangeEvents(false);
		        scrollPane = new ScrollPane(fileList, skin);		        		        
		        this.getContentTable().add(scrollPane).size(300, 300).padLeft(30);
		        //this.getContentTable().add();
		        this.getContentTable().row();
		        fileNameInput = new TextField("", skin);
		        		       
		        //this.getContentTable().add(fileNameLabel).padLeft(30);
		        
		        this.getContentTable().add(fileNameInput);
		        		        
		        fileNameInput.setTextFieldListener(new TextField.TextFieldListener() {
		            @Override
		            public void keyTyped(TextField textField, char c) {
		                result = textField.getText();
		            }
		        });

					        			
				super.button(" ", true , aceptar).align(Align.bottom);			
				super.getButtonTable().add().align(Align.bottom);
				
				        			
				super.button(" ", false , cancelar).align(Align.bottom);			

		        key(Keys.ENTER, true);
		        key(Keys.ESCAPE, false);

		        
		        fileList.addListener(new ClickListener() {
		            @Override
		            public void clicked(InputEvent event, float x, float y) {		            
		            	//System.out.println("evento 2 " + event.toString());
		            	//System.out.println("fileList.getSelectedIndex() == " + fileList.getSelectedIndex());
		            	if (fileList.getSelectedIndex() == 0) { // retroceder
		                    fileNameInput.setText("");
		            		CambiarDirectorio(fileHandle.parent());		            		
		            	}else {															            
			                 selected =  new File(fileHandle.path() + "/" + fileList.getSelected());
			               
				                if (!selected.isDirectory()) {
				                	  removeListener(this);
				                    result = selected.getName();
				                    fileNameInput.setText(result);		     
				                }
				                else{				              
				                	fileNameInput.setText("");
				                	CambiarDirectorio(new FileHandle(selected));
				                }
		            	}				            
		            }
		        });
		        setValue(null);
		        tipoDialogo = eTipoDialogo.AbrirArchivo;
	}		    

	/**
	 * 	 * dialogo para escoger entre mas de 20 opciones
	 * @param pTitulo
	 * @param pMultipleOpciones
	 * @param pOpciones
	 */

	public Dialogo(String pTitulo, Boolean pMultipleOpciones, ArrayList<String>pOpciones ) {

		super("   ", skin, "dialog");
		
		//System.out.println("entre al dialogo nuevo");
		
//		super.setDebug(true);
		super.setWidth(500);
		super.setHeight(500);
			
			
		 
		
	       fileArrayAux1  = new String[pOpciones.size()];
	       int j=0;
	        	for(String vOpcion: pOpciones){
	        		fileArrayAux1[j] = vOpcion;
	        		j++;
	        	}	    
	        	
		        fileList = new List<String>(skin);
	            fileList.setItems(fileArrayAux1);
	            	                       
		        this.getContentTable().top().left();

		        
		        fileList.getSelection().setProgrammaticChangeEvents(false);
		        scrollPane = new ScrollPane(fileList, skin);
		        scrollPane.setScrollingDisabled(true, false);
		        this.getContentTable().add(scrollPane).size(335, 450).padLeft(30).padTop(30);
		        this.getContentTable().add();
//		        this.getContentTable().row();
	//	        fileNameInput = new TextField("", skin);
		        		       
		        //this.getContentTable().add(fileNameLabel).padLeft(30);
		        
		//        this.getContentTable().add(fileNameInput);
		        		        
//		        fileNameInput.setTextFieldListener(new TextField.TextFieldListener() {
//		            @Override
//		            public void keyTyped(TextField textField, char c) {
//		                result = textField.getText();
//		            }
//		        });

					        			
				super.button(" ", true , aceptar).align(Align.bottom);			
				super.getButtonTable().add().align(Align.bottom);
				
					        			
				super.button(" ", false , cancelar).align(Align.bottom);			

		        key(Keys.ENTER, true);
		        key(Keys.ESCAPE, false);

		        
		        fileList.addListener(new ClickListener() {
		            @Override
		            public void clicked(InputEvent event, float x, float y) {		            
		            	setValue(fileList.getSelectedIndex());
		            }
		        });
		        setValue(0);
		        tipoDialogo = eTipoDialogo.Opciones25;
	}		    

	public Skin getSkin() {
	return skin;
}

//public void setSkin(Skin skin) {
//	this.skin = skin;
//}

	public TextureAtlas getAtlas() {
	return atlas;
}

public void setAtlas(TextureAtlas atlas) {
	this.atlas = atlas;
}

	@Override
	protected void result(Object object) {
		if (tipoDialogo == eTipoDialogo.Preferencias) {
			if (object.equals(true)) {
//				System.out.println("result true");
				preferencias.setPreferencia("sonido", sonido.isChecked());
				preferencias.setPreferencia("musica", musica.isChecked());

				preferencias.setPreferencia("volumen",volumenGeneral);
				//preferencias.setPreferencia("pantallacompleta", pantallaCompleta.isChecked());

				
				preferencias.setPreferencia("pantallacompleta", pantallaCompleta.isChecked());

				//preferencias.save();
				setResult(true);
			}
		}
		if (tipoDialogo == eTipoDialogo.Opciones || tipoDialogo == eTipoDialogo.OpcionesImagenes) {
			if (object.equals(true)) {
//				System.out.println("seteando el valor: ");
				setValue(radioButtons.getCheckedIndex() + 1);
			}
		}
		if (tipoDialogo == eTipoDialogo.Entrada) {

			if (object.equals(true)) {
				ArrayList<String> vValue = new ArrayList<String>();			
				for (TextField vTextField : textFieldGroup) {					
					vValue.add(vTextField.getText());			
				}
				
				setValue(vValue);
			}
		}
		if (tipoDialogo == eTipoDialogo.Aceptar){
			if (object.equals(true)) {	
				setValue(eRespuestaDialgo.Aceptar);			
			}
		}
		if (tipoDialogo  == eTipoDialogo.SiNoCancelar){
			if (object.equals(true)) {
				setValue(eRespuestaDialgo.Si);
			}
			else {
				setValue(eRespuestaDialgo.No);
			}
		}
		if (tipoDialogo == eTipoDialogo.Promocion){
			ChangeEvent event= new ChangeEvent();
			setResult(object);
			this.fire(event);			
		}
		if (tipoDialogo == eTipoDialogo.AbrirArchivo) {
			setValue( fileHandle + "/" + fileNameInput.getText());		
		}
		
		if (tipoDialogo == eTipoDialogo.Opciones25){
			if (object.equals(true)) {
				if (fileList.getSelectedIndex()>=0)		
					setValue(fileList.getSelectedIndex());
				else
					setValue(0);
				}
	    	else
	    		setValue(null);
		}

		if (tipoDialogo == eTipoDialogo.Video){
			if (object.equals("pausa")) {
					setValue("pausa");
				}
	    	else
	    		setValue(null);
		}

		setResult(object);
	}

	/**
	 * devuelve el dialgo para ser mostrado en la pantalla
	 * 
	 * @return dialgo para ser mostrado en la pantalla
	 */
	public Dialog getDialog() {
		return this;
	}

	/**
	 * asigna el resultado de los botones del dialogo, si, no, acepta, cancelar,
	 * etc. *
	 */
	private void setResult(Object pResultadosDialogo) {
		// System.out.println("setresult" + pResultadosDialogo.toString());
		resultadosDialogo = pResultadosDialogo;
	}

	/**
	 * devuelve el resultado de los botones del dialogo
	 * 
	 * @return
	 */
	public Object getResult() {
		// if (resultadosDialogo != null)
		// System.out.println("getresult" + resultadosDialogo.toString());
		return resultadosDialogo;
	}

	/**
	 * asigna el valor de las opciones o textfields dependiendo de cada tipo de
	 * dialogo,
	 * 
	 * @param pValorDialogo
	 */
	private void setValue(Object pValorDialogo) {
		// System.out.println("setValue : " + pValorDialogo);
		valorDialogo = pValorDialogo;
	}

	/**
	 * devuelve los valores de las opciones , textFields o escogidas o
	 * ingresadas por el en el dialogo
	 * 
	 * @return Object valores escogidos o ingresados por el usuario.
	 */
	public Object getValue() {
		// System.out.println("getValue : " + valorDialogo);
		return valorDialogo;
	}
	
	@Override
	public void draw(Batch batch, float alpha){	
//		System.out.println("batch" + batch.toString());				
		super.draw(batch, alpha);
		//batch.dispose();
//		//if  (this.tipoDialogo == eTipoDialogo.Video && getResult()==null){						
		if  (this.tipoDialogo == eTipoDialogo.Video){						
				
		if (player != null ) {			
				player.tick();		
				player.syncTexture(5);				
				batch1.begin();				
				batch1.draw(player.playerTexture, videoX + (margenVideoX/(11/4)), videoY + margenVideoY, this.getWidth()-(margenVideoX), this.getHeight()-(7*(margenVideoY/4)));							
				batch1.end();
				
				if(player.isPlaying()|| playerPaused )
					flag = false;
				else
				if (!flag ) {					
					System.out.println("!flag && !playerPaused");
					ChangeEvent event= new ChangeEvent();
					if (tipoVideo ==  eTipoVideo.Victoria||tipoVideo ==  eTipoVideo.Derrota ) 
						setResult(true);						
					else
						setResult(false);
					
					this.fire(event);			
				}
			}
			else
				System.out.println("player = null");
		}			  
	}
	
		
	@Override
	public void setHeight(float pHeight){
			
		super.setHeight(pHeight);
		calcularMargen();		
	}

	@Override
	public void setWidth(float pWidth){
			
		super.setWidth(pWidth);
		calcularMargen();		
	}
	
	@Override
	public void setPosition(float pX, float pY){
		super.setPosition(pX, pY);
		calcularPosicion(pX, pY);
		calcularMargen();		
	}

	private void calcularMargen(){
				margenVideoX = this.getWidth()/7;
				margenVideoY = this.getHeight()/6;											
	}
	private void calcularPosicion(float pX , float pY){
		if (tipoDialogo==eTipoDialogo.Video){
			ScreenManager screenManager = new ScreenManager();
			
			int w = Gdx.graphics.getWidth();
			int h = Gdx.graphics.getHeight();
			float width = screenManager.getGameWidth();
			float height = screenManager.getGameHeight();
			float wf = (w / 2) - (width / 2);
			float hf = (h / 2) - (height / 2);

			videoX = pX;
			videoY = pY;
			if (Gdx.graphics.isFullscreen()) {
					videoX  = videoX + wf;
					videoY  = videoY + hf ;
				}			
			}				
	}

	public TextButtonStyle textButtonStyle(String pRegion){
		vTextButtonStyle=null;
		System.gc();
		vTextButtonStyle = new TextButtonStyle();
        vTextButtonStyle.font = skin.getFont("default-font");
        vTextButtonStyle.up = skin.getDrawable(pRegion + "up");
        vTextButtonStyle.down = skin.getDrawable(pRegion + "down");
        return vTextButtonStyle;		
	}
	
	public boolean isPlaying(){
			if (player!=null)
				return player.isPlaying();
			 				
				return false;
	}
			public boolean isPaused(){
				if (player!=null)
					return player.isPaused();
				
				return false;
		}

	public void pause(){
			if (player !=null)				
				if (player.isPlaying()){
					playerPaused = true;
//					flag=true;
					System.out.println("paussing");							
					player.pause();
					System.out.println("paussed");		
				}
		
	}
	
	public void resume(){
		if (player !=null)				
			if (player.isPaused()){
					System.out.println("resuming");
					player.resume();
					playerPaused = false;
					System.out.println("resumed");
			}					
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		//System.out.println("dialog dispose 1");	
		if (batch1!=null) {
			batch1.dispose();
			batch1 = null;
		}
			setValue(null);
			setAtlas(null);

			//System.out.println("dialog dispose 2");		
		
		if (player!=null){
			//System.out.println("el player no es igual a null");
			//System.out.println("dialog dispose 2.1");	
			
			player.stop();			
			//	System.out.println("dialog dispose 2.2");	
			
			player.remove();			
			System.out.println("dialog dispose 2.3");	
			
			player.dispose();
			System.out.println("dialog dispose 2.4");	
			
			//player = null;
			try {
				player.finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error");
			}
		}
		System.runFinalization();
					System.gc();
		//System.out.println("dialog dispose 3");	
		
//		if (skin !=null) {
//			skin.dispose();
//			skin = null;
//		}
		//System.out.println("dialog dispose 4");	
		
		if (atlas !=null) {
			atlas.dispose();
			atlas= null;
		}
				
		//System.out.println("dialog dispose 5");			
		result = null;
		
		//System.out.println("dialog dispose 6");	
		
		fileList = null;		
		//System.out.println("dialog dispose 7");	
		
		fileListLabel = null;
		//System.out.println("dialog dispose 8");	
		
		fileHandle = null ;
		//System.out.println("dialog dispose 9");	
		
		selected = null;
		//System.out.println("dialog dispose 10");	
		
		scrollPane = null;
		fileNameInput = null;
		list = null;
		fileArrayAux = null;
		fileArrayAux1= null;
	//System.out.println("dialog dispose 11");	
		
		//	System.out.println("7");							
			//System.out.println("8");
		//gc();
		
		this.clearChildren();
		super.clearChildren();
		super.remove();
		this.remove();
		
	//		System.out.println("dialog dispose 13");	
			System.out.println("dialog dispose 14.2");	
					
		
			try{				
				super.finalize();
				this.finalize();
					System.out.println("dialog dispose 14.2");	
							
		}
			catch(Exception e){
				System.out.println("error");
				System.out.println("error disposing the video " + e.getMessage());
				
			} catch (Throwable e) {
			// TODO Auto-generated catch block
				System.out.println("error");
				System.out.println("error eliminando el video " + e.getMessage());
			
			}
			
			
			System.gc();
			try {
				String sSistemaOperativo = System.getProperty("os.name");
			    if (sSistemaOperativo.toUpperCase().contains("LINUX")){	
					String Comando = "killall ffmpeg32";			
					Runtime.getRuntime().exec(Comando);
					Comando = "killall ffmpeg64";
					Runtime.getRuntime().exec(Comando);
			    }
			    else
			    {
					String Comando = "taskkill /IM ffmpeg32.exe /F";
					Runtime.getRuntime().exec(Comando);
					Comando = "taskkill /IM ffmpeg64.exe /F";
					Runtime.getRuntime().exec(Comando);		    			    
			    }
			
			} catch (IOException e1) {
				// .TODO Auto-generated catch block
				e1.printStackTrace();
			}

		//System.out.println("dialog dispose 15");	
			ObjectMap<Class, ObjectMap<String, Object>>  resources = new ObjectMap();
			for (ObjectMap<String, Object> entry : resources.values()) {
				for (Object resource : entry.values()){
					System.out.println("borrando:"+resource.toString());
					if (resource instanceof Disposable) ((Disposable)resource).dispose();
			}}
			preferencias=null;
			textFieldGroup=null;
			resultadosDialogo=null;
			 musica=null; sonido=null;pantallaCompleta=null;
			 radioButtons = null;
				textFieldGroup = null;
				tipoDialogo=null;
				

				resultadosDialogo= null; valorDialogo= null;
				vHeroe= null;
				player= null;;
				//private Texture playerTexture;
				batch1 =  null;  
				atlas= null;		
//				skin= null;;
				tipoVideo = null;
				System.gc();
				System.gc();
	}

	
		

}
