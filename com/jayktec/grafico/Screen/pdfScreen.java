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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class pdfScreen extends ScreenManager {
//
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
//	
	public pdfScreen(final Miniheroes pGame) {
		super(pGame);
		game = pGame;
	}
	public void getBuffer(){
	
		
	}

}
