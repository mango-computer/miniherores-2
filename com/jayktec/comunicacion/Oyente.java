
//////////////////////////////////////////////////////////////
//www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//Oyente.java                            //
//Descripcion                            // 
//Pantalla para el juego                       //
//////////////////////////////////////////////////////////////
//Autor            Fecha           Motivo             //  
//Yisheng León     14/03/2016      Cambios en recibir()    //
//////////////////////////////////////////////////////////////



package com.jayktec.comunicacion;

/**
 * Tipos de oyentes
 * @author yisheng
 *
 */public enum Oyente{
	XMM ("XMM"),
	XBOARD ("XBOARD");
	
	private final String desc;
	
	Oyente(String descripcion){
		desc = descripcion;
	}
	
	public String Getdesc(){
		return desc;
	}

}





