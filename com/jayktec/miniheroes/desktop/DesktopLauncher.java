package com.jayktec.miniheroes.desktop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.nio.channels.*;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jayktec.grafico.Miniheroes;


public class DesktopLauncher {
	FileChannel  channel;
	FileLock lock;
	File file ;
	
	public static void main (String[] arg) {

		if(!new DesktopLauncher().verificarActiva()){

			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.addIcon("assets/iconos/logo2-16X16.png", FileType.Internal);
			config.addIcon("assets/iconos/logo2-32X32.png", FileType.Internal);
			config.addIcon("assets/iconos/logo2-64X64.png", FileType.Internal);
			config.width=848;
			config.height=552;
			config.resizable=false;		
			config.title="miniHéroes";			
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");

			new LwjglApplication(new Miniheroes(), config);
			
			
			
	
		}			

	}
	
	boolean verificarActiva(){
		try{
			
			file = new File(System.getProperty("user.dir") + "/miniHeores.tmp");	
			System.out.println(file.getAbsolutePath());
			channel= new RandomAccessFile(file, "rw").getChannel();
			 
			try {
			//	System.out.println("1");
	                lock = channel.tryLock();
	            }
	            catch (OverlappingFileLockException e) {
	  //          	System.out.println("2");
	                closeLock();
	                return true;
	            }

	            if (lock == null) {
	//            	System.out.println("3");
	                closeLock();
	                return true;
	            }
						
		}
		catch(Exception e){
		//	return false;
			//System.out.println("5 e: " + e.getMessage());
		}
		
		
//		System.out.println("6");
		return false;
			
		}
	
    private void closeLock() {
        try { lock.release();  }
        catch (Exception e) {  }
        try { channel.close(); }
        catch (Exception e) {  }
    }

    private void deleteFile() {
        try { file.delete(); }
        catch (Exception e) { }
    }
    
   
   
}
