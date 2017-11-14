//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                Protocolo.java                            //
//                   Descripcion                            // 
//             Pantalla para el juego                       //
//////////////////////////////////////////////////////////////
//      Autor            Fecha           Motivo             //  
//    Luis Diaz       09/03/2016      Version Inicial       //
//   Yisheng León     14/03/2016      Cambios en recibir()    //
//////////////////////////////////////////////////////////////
package com.jayktec.archivos;


	/**
	 * Un num de como el mango paola en el protocolo nos envia los errores por cualquier cosa 
	 * aun puede llegar a agregarse o modificar alguno
	 * @author luis
	 *
	 */

	
	public enum errores{
		COD900("Error desconocido"),
		COD901("Minijuego no soportado"),
		COD902("Movimiento no se encuentra en las posibles respuestas"),
		COD903("No existen movimientos posbiles"),
		COD904("Comando no soportado"),
		COD905("Comando Desconocido"),
		COD906("Posición Invalida (Fuera del Rango 0 - 63)"),
	    COD907("Color de Pieza Seleccionada Incorrecto"),
	    COD908("Escaque Vacío"),
	    COD909("Movimiento Crea Jaque"),
		COD910("Comando Necesita que Este Iniciado un Mini Juego"),
		COD911("No se Puede Agregar la Pieza"),	
		COD912("No se Puede Quitar una Pieza"),
		COD913("Color del Comando QPR No Reconocido, se esperaba [BLANCO] [NEGRO]"),
		Error("Error comando no reconocido"),
		Illegal("Movimiento Erroneo wwwwwwwwweeeeeeeeeeeey");
		
		private final String desc;
		
		errores(String descripcion){
			desc = descripcion;
		}
		
		public String Getdesc(){
			return desc;
		}

}



		
	
		

	


