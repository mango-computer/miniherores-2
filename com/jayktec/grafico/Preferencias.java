package com.jayktec.grafico;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
public class Preferencias {

	   public static final String TAG = Preferencias.class.getName();
	   public static  Preferencias instance = null;

	   private boolean sonido, musica, pantallaCompleta, piezasClasicas;
	   private float volumen = 0.5f;

	   private Preferences prefs;

	   private Preferencias() {		   		  
	      prefs = Gdx.app.getPreferences("my app");	      
	   }
	   
	   public static Preferencias getInstance() {
		      if(instance == null) {
		         instance = new Preferencias();
		      }
		      return instance;
		   }
	   
	   public void load () {
		  sonido = prefs.getBoolean("sonido", false);
	      musica = prefs.getBoolean("musica", false);
	      pantallaCompleta = prefs.getBoolean("pantallacompleta",false);
	      piezasClasicas= prefs.getBoolean("piezasclasicas",false);
	      volumen = prefs.getFloat("volumen",0.5f);
	   }
	   public void save () {
	      prefs.putBoolean("sonido", sonido);
	      prefs.putBoolean("musica", musica);
	      prefs.putBoolean("pantallacompleta", pantallaCompleta);
	      prefs.putBoolean("piezasclasicas", piezasClasicas);
	      prefs.putFloat("volumen", volumen);
	      prefs.flush();
	   }
	   public boolean getPreferencia(String pPreferencia){
		   load();
		   if (pPreferencia.toLowerCase() == "musica")
			   return musica;
		   else
			   if (pPreferencia.toLowerCase() ==  "sonido")
				   return sonido;
			   else 
				   if (pPreferencia.toLowerCase() == "pantallacompleta")
					   return pantallaCompleta;
				   else 
					   if (pPreferencia.toLowerCase() == "piezasclasicas"){
//						   System.out.println("get_" + pPreferencia + " Valor"  + piezasClasicas);
						   return piezasClasicas;				}

		   return false;
	   }
	   
	   public void setPreferencia(String pPreferencia, boolean pValor){
//		   System.out.println("pPreferencia:" + pPreferencia  + " valor: " + pValor);
		   if (pPreferencia.toLowerCase() == "musica")
			   musica = pValor;
		   else
			   if (pPreferencia.toLowerCase() ==  "sonido")
				   sonido = pValor;
			   else 
				   if (pPreferencia.toLowerCase() == "pantallacompleta")
					  pantallaCompleta = pValor;
				   else 
					   if (pPreferencia.toLowerCase() == "piezasclasicas")
						   piezasClasicas = pValor;		
		   
		   save();
	   }

	   
	   public float getPreferenciav(String pPreferencia){
		   load();
		   if (pPreferencia.toLowerCase() == "volumen")
			   return volumen;
		   return 0.5f;
	   }

	   public void setPreferencia(String pPreferencia, float pValor){
//		   System.out.println("pPreferencia:" + pPreferencia  + " valor: " + pValor);
		   if (pPreferencia.toLowerCase() == "volumen")
			   volumen = pValor;
		   save();
	   }
}


