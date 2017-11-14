
//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//               jayktecScreen.java                       //
//                   Descripcion                            //
//  		Pantalla para el logo de jayktec 				//
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             // 
//Wilmer Gonzalez      28/03/2016     Version Inicial       //
//////////////////////////////////////////////////////////////

package com.jayktec.grafico.Screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jayktec.grafico.Miniheroes;
import com.jayktec.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class jayktecScreen extends ScreenManager {
	private Miniheroes game;
	private SpriteBatch batch;
	private Texture ttrSplash;
	private Sprite splash;
	private TweenManager tweenManager;

	/**
	 * Constructor para la pantalla que muestra el logo de jayktec
	 * 
	 * @param pGame
	 *            Variable para que la clase Miniheroes.java genere la pantalla
	 */
	public jayktecScreen(final Miniheroes pGame) {
		super(pGame);
		game = pGame;

	}

	@Override
	public void show() {

		batch = new SpriteBatch();
		tweenManager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());

		ttrSplash = new Texture("assets/Texturas/jayktec.png");
		splash = new Sprite(ttrSplash);
		splash.setSize(Gdx.graphics.getWidth()/1.5f, Gdx.graphics.getHeight()/1.5f);
		splash.setPosition((Gdx.graphics.getWidth()/2)-(splash.getWidth()/2), (Gdx.graphics.getHeight() / 2)-(splash.getHeight()/2));
	
		// sentencias del motor tween para la transparencia de imagenes
		Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1, 3).setCallback(new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(game.fva);
			}

		}).start(tweenManager);

		Gdx.graphics.setContinuousRendering(true);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		tweenManager.update(delta);
		batch.begin();
		splash.draw(batch);
		batch.end();

	}

	@Override
	public void hide() {
		// manager.dispose();
		dispose();
	}

	@Override
	public void pause() {

		// manager.dispose();
	}

	@Override
	public void resume() {

		// manager.dispose();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void dispose() {
		ttrSplash.dispose();
		batch.dispose();
		splash.getTexture().dispose();

	}

}
