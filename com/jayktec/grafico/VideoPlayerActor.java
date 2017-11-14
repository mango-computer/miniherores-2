package com.jayktec.grafico;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.brashmonkey.spriter.Player;
//import com.kotcrab.vis.ui.widget.tabbedpane.TabbedPaneAdapter;


//import net.indiespot.media.Movie;
import net.indiespot.media.MoviePlayer;
//import sun.java2d.DefaultDisposerRecord;

public class VideoPlayerActor extends Actor{
	public MoviePlayer player;
	

	public Texture playerTexture;	
	

	private FileHandle fh=null ;
	private String  path=null ;
	private File mov=null; 

	
	public VideoPlayerActor(String pPathVideo) {
		fh = Gdx.files.internal(pPathVideo);
		path = fh.file().getAbsolutePath();		
		mov = new File(path);
		try {
			player = new MoviePlayer(mov);
			
			player.audio().setVolume(1f);
			playerTexture = new Texture(player.movie.width(), player.movie.height(),

					Format.RGBA4444) {


				@Override
				public void bind() {

					Gdx.gl.glBindTexture(0, player.textureHandle);				
//					System.out.println("binding de video player"  );
				}					
			};								
			
			
			
			
		} catch (IOException e) {
			System.out.println("error en el  player " + e.getMessage());
			e.printStackTrace();
		}
}



//@Override
//public void draw(Batch batch, float alpha){	
//		//System.out.println("draw de video player" + batch.toString());
//        //batch.draw(playerTexture, 0, 0,200,200);		 
//    }

																																																																																																		

public  void tick(){
	this.player.tick();	
}

public  void syncTexture(int PSync){
	this.player.syncTexture(PSync);	
}

public boolean isPlaying(){
	return this.player.isPlaying();
}

public void stop(){
	System.out.println("stop de video actor");
	this.player.stop();		
	System.out.println("stopped de video video actor");
}

public void pause(){	
	System.out.println("paussingd de video actor");
	this.player.pause();
	System.out.println("paused de video video actor");
}

public void resume(){
	this.player.resume();
}

public boolean isPaused(){
	return this.player.isPaused();
	
}
public void restart(){
	
	int seg = (int)player.movie.getPlayingTime();
	seg= seg*-1;
try {
	player.relativeSeek(seg);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}

public void dispose(){

	try {
		Gdx.gl.glFinish();
		Gdx.gl.glFlush();
		
		player.stop();
		System.out.println("player.dipose 2" );
		player.movie.close();		
//		System.out.println("player.dipose 3" );
				player.close();
				player.audioRenderer=null;
				player.movie=null;
System.gc();
					System.out.println("player.dipose 4" );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("player.dipose close error:" + e.getMessage());
		e.printStackTrace();
	}	
	
	//System.out.println("player.dipose 5" );
	try {
		
		playerTexture.dispose();
		//System.out.println("player.dipose 6" );
		 
		this.clear();
		//System.out.println("player.dipose 7" );
		super.clear();
		//System.out.println("player.dipose 8" );
		this.finalize();
		//System.out.println("player.dipose 9" );	
		super.finalize();
		//System.out.println("player.dipose 10" );
		
	} catch (Throwable e) {
	System.out.println("player.dipose error:" + e.getMessage());
		e.printStackTrace();
	}
	//System.out.println("player.dipose 11" );
	this.remove();
	
	
	
	fh = null;	
	path = null;	
	mov = null;
	player.movie = null;	
	player = null;		
	playerTexture  = null;
	gc();
	//System.out.println("player.dipose 12" );

	
}

public void gc(){

//for (int i =0;i<=3;i++)
//
	System.gc();
}
protected void finalize() throws Throwable{
	  System.out.println("Finalizando el Objeto");
	  super.finalize();
	}

}
