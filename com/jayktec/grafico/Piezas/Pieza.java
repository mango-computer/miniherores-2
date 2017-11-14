package com.jayktec.grafico.Piezas;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import org.omg.PortableServer.THREAD_POLICY_ID;

import com.badlogic.gdx.Gdx;
import com.jayktec.grafico.Enums.*;
import com.jayktec.grafico.Principal.ScreenJuegoPrincipal;
import com.jayktec.grafico.Screen.ScreenManager;

public class Pieza extends Actor {
	//private static final Logger LOG = Logger.getLogger(Miniheroes.class.getName() );
	private int juego;
	public Texture _texture;
	public Casilla casilla = new Casilla();
    private Tablero tablero;
    public Pieza pieza;
    private eTipoPieza tipoPieza;
    private eColores eColor;
    private TextureAtlas pack;
    private Animation animation;
    private TextureRegion[] textureReg;
    public Texture texturaEsperando; 
    public Texture texturaSeleccionado;
    public Texture texturaMoviendo; 
    public Texture texturaCapturando;
    private ScreenManager ScreenFrom;
    public eEstadoPieza estadoPieza;
    private int Ind;
    
    private int size;
    private float frameDuration;
    private float elapsedTime = 0;
    private boolean ocupado = false;

    /**
     * Constructor por defecto de esta clase pieza el cual se encarga de dibujar en pantalla las distintas
     * piezas asi como verificar si el mouse esta haciendo click sobre una pieza
     **/
    
   public Pieza()
   {		
     	

     }

   
 /**
  * Metodo para dibujar este elemento en la pantalla
  */
    public void draw(Batch batch, float alpha){
    	try {
    		if(_texture!=null){
    	        batch.draw(_texture, getX(), getY(),casilla.GetAnchoCasilla(),casilla.GetAltoCasilla());
    	        }
    	    	else {
    	    		{
    	    			
    	    			size=getTextureReg().length;
    	    	     	frameDuration=0.12f; //0.12
    	    	     	for(int i=0;i<size;i++)
    	    	     	{
    	    	     		textureReg[i] = (pack.findRegion(Integer.toString(i+1)));
    	    	     		
    	    	     	}
    	    	        
    	    		     animation = new Animation(frameDuration,textureReg);
    	    			 elapsedTime += (Gdx.graphics.getDeltaTime()*0.01);
    	    			 batch.draw((TextureRegion)animation.getKeyFrame(elapsedTime, true), getX(), getY(),casilla.GetAnchoCasilla(),casilla.GetAltoCasilla());
    	    		}
    			}		
		} catch (Exception e) {
			// TODO: handle exception
		}
    
    }
    
    /**
     * Metodo para manejar la entrada del mouse en esta Pieza 
     **/
    public Actor hit(float x, float y, boolean touchable)
    { 
    
    //LOG.logp(Level.INFO,this.toString(), "Actor hit ","IN " + getName());
    	 if(estadoPieza==eEstadoPieza.Seleccionado )
      { 
    	//LOG.logp(Level.INFO,this.toString(), "Actor hit ","OUT ok1");    	  
    	  return this;
      }

      if(!this.isVisible() || this.getTouchable() == Touchable.disabled)
      {
    	  //LOG.logp(Level.INFO,this.toString(), "Actor hit ","OUT 1");
            return null;
      }
      
        float width=ScreenFrom.getGameWidth();// juego
		float height=ScreenFrom.getGameHeight(); // 
		float w=Gdx.graphics.getWidth();//pantalla completa
		float h=Gdx.graphics.getHeight();
		float wf=(w/2)-(width/2);
		float hf=(h/2)-(height/2);
    
		int x1 = Gdx.input.getX();
      	int y1 = Gdx.input.getY();
      	float sx= getX();
      	float sy=getY();
      	float sx1,sy1;

      	if (Gdx.graphics.isFullscreen()==true){
      	 	x1=x1-((int)wf);
      	 	 y1=Gdx.graphics.getHeight()-y1;
      	 	y1=y1-((int)hf);
       }
       else{
    	   y1=Gdx.graphics.getHeight()-y1; 
       }
    	 
  		sx1=sx+casilla.GetAnchoCasilla();
  		sy1=sy+casilla.GetAltoCasilla();

//System.out.println(getName()+" - "+sx+" - "+sy);

  		 if (tipoPieza!=null)


  		if(x1>=sx && x1<=sx1 )
  		{
  			if(y1>=sy && y1<=sy1 )
  			{
  				return this;			
  			}
  		}
  	
     return null;     
    }

    /**
     * Metodo ejecutar el movimiento de la pieza con la cual se esta interactuando en la pantalla
     * @param x posicion X hacia donde se va a mover la pieza
     * @param y posicion Y hacia donde se va a mover la pieza
     */
    public void move(float x,float y)
    {
    	ocupado = true;
    	cambiarEstadoPieza(eEstadoPieza.Moviendo);
    	float actualX=getX();
    	float actualY=getY();
    
    	double distancia=Math.sqrt((Math.pow(x-actualX, 2))+Math.pow(y-actualY, 2));
    	final float duracion=(float)distancia/((casilla.GetAnchoCasilla() + casilla.GetAltoCasilla())/2);	
    	

    	MoveToAction actionMove= new MoveToAction();
    
        actionMove.setPosition(x,y);
      
        actionMove.setDuration(0.2f*duracion);
        addAction(actionMove);
        
//        Thread t1 = new Thread(new Runnable() {
//	        public void run(){
	        	Timer.schedule(new Task() {
					@Override
					public void run() {
						 cambiarEstadoPieza(eEstadoPieza.Esperando);
						 ocupado = false;
					}
	        	}, 0.5f*duracion);
//	         }
//        });  
//        t1.start();
    }
    
     
     /**
      * Metodo cambiar el estado de esta pieza
      * @param pEstado nuevo estado de la pieza
      */
    public void cambiarEstadoPieza(eEstadoPieza pEstado)
    {	
    	//System.out.println("cambiando el estado de la pieza " + pEstado.toString() );
    	this.estadoPieza=pEstado; 
    	
    	if(estadoPieza==eEstadoPieza.Seleccionado)
    		{
    			this._texture = this.texturaSeleccionado;
    		}
    	else
    	if(estadoPieza==eEstadoPieza.Moviendo)
    		{
//    			this._texture = this.texturaMoviendo;
    		}
    	else
        if(estadoPieza==eEstadoPieza.Capturando)
        	{
        		this._texture = this.texturaCapturando;
        	}
        else
        	if(estadoPieza==eEstadoPieza.Esperando)
        	{
        	  this._texture = this.texturaEsperando;
        	}    	
    }
    /**
     * metodo para colocar las piezas en un tablero
     * @param pgn posicion pgn donde se va a colocar la pieza
     */
   public void setPosPgn(String pgn)
   {
	//   System.out.println(pgn);
	   int arr[]= new int[2];
	   arr=tablero.Pgn2XY(pgn);
	   setX(arr[0]);//+mitablero.GetInicioX()-casilla.GetAnchoCasilla());
	   setY(arr[1]);//+mitablero.GetInicioY()-casilla.GetAltoCasilla());
	  try {
		((ScreenJuegoPrincipal)  ScreenFrom ).setCasilla(pgn, this, this.getInd());
	  }
	  catch(Exception e){
		  
	  }
   }
   /**
    * Metodo para implementar listeners a esta pieza
    **/
 public void agregarListener(){
     this.addListener(new InputListener()
     {
    	

		@Override
         public boolean touchDown(InputEvent pEvent, float pX, float pY, int pPointer, int pButtons)
         {
    		
    		 float width=ScreenFrom.getGameWidth();
    			float height=ScreenFrom.getGameHeight();
    			float w=Gdx.graphics.getWidth();
    			float h=Gdx.graphics.getHeight();
    			float wf=(w/2)-(width/2);
    			float hf=(h/2)-(height/2);
    			
			int mouseX = Gdx.input.getX();
			int mouseY = Gdx.input.getY(); 
			
			float actorX= getX();
			float actorY= getY();
			
			float rangoX,rangoY,posX,posY; 

			rangoX=actorX+casilla.GetAnchoCasilla();
			rangoY=actorY+casilla.GetAltoCasilla();
			
			if (Gdx.graphics.isFullscreen()==true){
				mouseX=mouseX-((int)wf);
	      	 	mouseY=Gdx.graphics.getHeight()-mouseY;
	      	 	mouseY=mouseY-((int)hf);
	       }
	       else{
	    	   
	    	   mouseY=Gdx.graphics.getHeight()-mouseY;
	       }
			
			
			
			int enteroX=(mouseX-tablero.GetInicioX())/casilla.GetAnchoCasilla();
			int enteroY=(mouseY-tablero.GetInicioY())/casilla.GetAltoCasilla();      
			posX=((enteroX)*casilla.GetAnchoCasilla()) + tablero.GetInicioX();
			posY=((enteroY)*casilla.GetAltoCasilla()) + tablero.GetInicioY();
			
			
//			System.out.println("Touchdown pieza "+getName()+getInd()+" inicio:"+ tablero.XY2pgn((int)actorX , (int)actorY )+ "fin:"+ tablero.XY2pgn((int) mouseX, (int)mouseY) +" color" + getColor());
			if (ScreenFrom.ValidarMovimiento(pieza, juego ,(int)mouseX,(int)mouseY,(int)actorX, actorY, rangoX, rangoY,posX,posY)==true)
						   
			{
//				System.out.println("ValidarMovimiento true"); 
				move(posX,posY);
		
	            return true;
			 }
	         else
	         {

	      //  	System.out.println("Error, retorno falso en el listener"); 
            	return false;
	         }			 
         }
    
    }); // Fin del Listener touchDown      
    
 }
 

/**
 * Funcion que devuelve el tipo de pieza
 * @return tipo de pieza
 */
public eTipoPieza getTipoPieza(){
	
	return tipoPieza;
}
/**
 * seteo del tipo de pieza
 * @param pTipo tipo de pieza
 */

public void setTipoPieza(eTipoPieza pTipo){
	
	tipoPieza=pTipo;
}


public Texture get_texture() {
	return _texture;
}

public void set_texture(Texture _texture) {
	this._texture = _texture;
}

public TextureAtlas getPack() {
	return pack;
}

public void setPack(TextureAtlas pack) {
	this.pack = pack;
}

public TextureRegion[] getTextureReg() {
	return textureReg;
}

public void setTextureReg(TextureRegion[] textureReg) {
	this.textureReg = textureReg;
}

public eColores getEcolor(){
	
	return eColor;
}

public void setColor(eColores pColor){
	
	eColor=pColor;
}



public int getjuego(){
	
	return juego;
}

public void setjuego(int pJuego){
	
	juego=pJuego;
}

/**
 * Verificacion de cual pantalla se esta instanciando la pieza
 * @param pScreen pantalla de la cual se esta instanciando la pieza
 */


   public void setScreen(ScreenManager pScreen)
   { 
	   ScreenFrom = pScreen;

	  
   }

/**
 * Seteo de indicador de pieza 
 * @param pInd parametro de seteo de pieza
 */
   public void setInd(int pInd) 
   {
	Ind = pInd;
   }

 
   
   
   /**
    * funcion para obtener el indice de esta pieza
    * @return indice de la pieza
    */
   public int getInd(){
	   
	   return Ind;
   }
   
 
   
  /**
   * Metodo asignar el estado de esta pieza
   * @param pEstadoPieza estado de la pieza
   */
   public void setEstadoPieza(eEstadoPieza  pEstadoPieza){
	   estadoPieza = pEstadoPieza;
   }

   /**
    * funcion de obtener el estado de una pieza
    * @return estado de la pieza
    */
   public eEstadoPieza getEstadoPieza() {
	 return   estadoPieza;
   }

   
	public boolean  ocupado(){
		if (this.getActions().size >  0)
			return true;
				
		if (ocupado)
			return true;
				
	return false;
	}

	public void setTablero(Tablero pTablero){
		tablero = pTablero;
	}
	
	/**
	 * metodo para ser sobrescrito para cambiar las texturas por los tipos de piezas existentes
	 * @param color , oscuro o claro,  valor de la pieza
	 * @param tipoPieza , tipo de pieza a cambiar, por ejemplo miniheroes, ó clasica
	 * @author yisheng 
	 */
	public void setTipodePiezas(eColores pColor, String tipoPieza)
	{
		
	}

}
