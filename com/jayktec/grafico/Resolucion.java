package com.jayktec.grafico;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Resolucion extends Actor {
	Texture _texture;

	public Resolucion() {
		// TODO Auto-generated constructor stub
	}

	public Resolucion(String pPathTexture) {
		_texture = new Texture(Gdx.files.internal(pPathTexture));
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(_texture, 0, 0);
	}

}
