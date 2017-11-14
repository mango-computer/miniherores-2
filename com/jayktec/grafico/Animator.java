package com.jayktec.grafico;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Animator extends Actor {
	 private TextureRegion[] textani;
     private Animation animation;
     private float alto,ancho;
     private int size;
     private float frameDuration;
     public boolean touched=false;
     private float elapsedTime = 0;
  
 
     
     public Animator( TextureRegion[] pAnimacion,TextureAtlas atlas,float pAncho,float pAlto,float pFrameDuration){
     	
     	ancho=pAncho;
     	alto=pAlto;
     	size=pAnimacion.length;
     	textani=new TextureRegion[size];
     	frameDuration=pFrameDuration;
     	for(int i=0;i<size;i++)
     	{
     		pAnimacion[i] = (atlas.findRegion(Integer.toString(i+1)));
     		textani[i]=pAnimacion[i];
     	}
        
	      animation = new Animation(frameDuration,pAnimacion);

     }
     
     public void draw(Batch batch, float alpha){
     	  elapsedTime += Gdx.graphics.getDeltaTime();
     	if (touched==true){
     		 batch.draw((TextureRegion)animation.getKeyFrame(elapsedTime, true), getX(), getY(),ancho,alto);
     	}else{
     		 batch.draw( textani[0], getX(), getY(),ancho,alto);
     	}
     }



}
