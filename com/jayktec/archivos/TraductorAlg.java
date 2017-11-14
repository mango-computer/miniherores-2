package com.jayktec.archivos;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.jayktec.grafico.Enums.eTipoPieza;
import com.jayktec.grafico.Piezas.Casilla;
import com.jayktec.grafico.Piezas.Tablero;
import com.jayktec.grafico.Screen.ScreenManager;


public class TraductorAlg {
	
	final private String[] filas = {"a","b","c","d","e","f","g","h"};
	final private String[] piezasEn= {"R","N","B","Q","K","O"};
	//final private String[] piezasEs= {"T","C","A","D","R","O"};
	final private String[] columnas = {"1","2","3","4","5","6","7","8"};
	
	private Tablero  tablero = new Tablero();
	private Casilla casilla = new Casilla(); 
	private int altoTablero = 0;

	private int anchoTablero  = 0;
	
	ScreenManager screenManager;
	//private boolean idiomaIngles = null;
	
	public TraductorAlg(ScreenManager pScreenManager) {
		screenManager = pScreenManager;	
		

		altoTablero = casilla.GetAltoCasilla();
		anchoTablero = casilla.GetAnchoCasilla();
	}
			
	public String[] Alg2Pgn(boolean pTurnoBlancas, String pMovAlg , Casilla[] pCasillas) {				
		pMovAlg = pMovAlg.substring(pMovAlg.indexOf(".") +1,pMovAlg.length()) ;		
		eTipoPieza vTipoPieza = tipoPieza(pMovAlg);
		

		if (vTipoPieza== eTipoPieza.Peon)
				return CalularPeon(pTurnoBlancas,pMovAlg ,pCasillas);
			else
				return CalularPgnI(pTurnoBlancas, pMovAlg, pCasillas, vTipoPieza);									
	}
	
	private eTipoPieza tipoPieza(String pMovAlg) {
		

//		if (System.getProperty("user.language").contains("es"))
		
//		for (String vPieza: piezasEs) {
//			if (vPieza.equals(pMovAlg.substring(0,1))){			
//				if (vPieza.equals("T")) 
//					return eTipoPieza.Torre;				
//				else if (vPieza.equals("A"))
//					return eTipoPieza.Alfil;							
//				else if (vPieza.equals("C"))
//					return eTipoPieza.Caballo;
//				else if (vPieza.equals("D"))
//					return eTipoPieza.Reina;
//				else if (vPieza.equals("R"))
//					return eTipoPieza.Rey;
//				else if (vPieza.equals("O"))
//					return eTipoPieza.Rey;; 									 
//			}
//		}
//		else

			for (String vPieza: piezasEn) {
			if (vPieza.equals(pMovAlg.substring(0,1))){			
				if (vPieza.equals("R")) 
					return eTipoPieza.Torre;				
				else if (vPieza.equals("B"))
					return eTipoPieza.Alfil;							
				else if (vPieza.equals("N"))
					return eTipoPieza.Caballo;
				else if (vPieza.equals("Q"))
					return eTipoPieza.Reina;
				else if (vPieza.equals("K"))
					return eTipoPieza.Rey;
				else if (vPieza.equals("O"))
					return eTipoPieza.Rey;; 									 
			}
		}							
		return eTipoPieza.Peon;
	}

	private boolean ValidarPgnIPeon(String  pPgni, Casilla[] pCasillas , boolean pTurnoBlancas , eTipoPieza pTipoPieza) {
		
		Color vColor= null;		
		
		if (pTurnoBlancas)
			vColor = Color.WHITE;
		else 
			vColor = Color.BLACK;
					
		for (int i =0; i<64; i++)
			try {			
				if (pCasillas[i].getPieza().getTipoPieza().equals(pTipoPieza)) { 
					if(pCasillas[i].getPgn().contains(pPgni.toUpperCase())) {
						if (pCasillas[i].getPieza().getColor().equals(vColor)) {
							return true;							
						}
					}
				}
			} catch (Exception e) {
			}
		
		return false;						
	}

public String[] CalularPeon(boolean pTurnoBlancas, String pMovAlg , Casilla[] pCasillas) {	
		eTipoPieza vTipoPieza = eTipoPieza.Peon;
		String pgnI="";
		String pgnF="";
		String[] vRet= new String[2];

		if (pTurnoBlancas) {
			if (!pMovAlg.contains("x")) {
				pgnI = pMovAlg.substring(0,1) + (Integer.parseInt(pMovAlg.substring(1,2))- 1 );
				if (ValidarPgnIPeon(pgnI, pCasillas, pTurnoBlancas, vTipoPieza)){
					pgnF = pMovAlg;
				}
				else{
					pgnI = pMovAlg.substring(0,1) + ( Integer.parseInt(pMovAlg.substring(1,2)) - 2);
					if (ValidarPgnIPeon(pgnI, pCasillas, pTurnoBlancas,vTipoPieza)){
						pgnF = pMovAlg;
					}
					else{					
						pgnI = "";
						pgnF = "";
					}
				}
			}
			else { // peon capturando

				if (pMovAlg.substring(1,1) == pMovAlg.substring(3,4)) { //Captura al paso					
					pgnI = pMovAlg.substring(0,2);
					pgnF =  pMovAlg.substring(3,4) + (Integer.parseInt(pMovAlg.substring(4,5))+1);				
				}
				else {
					pgnF = pMovAlg.substring(2,4);
					if (esFila(pMovAlg.substring(0,1))){
						ArrayList<String>vPgnIs= new ArrayList<String>();
						vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, eTipoPieza.Peon, pMovAlg.substring(0,1));
						for(String vPgn: vPgnIs) {
								if (ValidarPgnI(vPgn, pgnF, eTipoPieza.Peon, Color.WHITE)) {
									pgnI =vPgn;
									break;
								}
						}				
					}
					else if (esColumna(pMovAlg.substring(0,1))) {
							ArrayList<String>vPgnIs= new ArrayList<String>();
							vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, eTipoPieza.Peon, pMovAlg.substring(1,2));
							for(String vPgn: vPgnIs) {
									if (ValidarPgnI(vPgn, pgnF, eTipoPieza.Peon, Color.WHITE)) {
										pgnI =vPgn;
									break;
									}
							}				
							pgnF = pMovAlg.substring(2,3);
					}					
				}
				if (!ValidarPgnIPeon(pgnI, pCasillas, pTurnoBlancas, vTipoPieza)){
					pgnI = "";
					pgnF = "";
				}
			}

		}
		else {
			if (!pMovAlg.contains("x")) {
				if (tipoPieza(pMovAlg) == eTipoPieza.Peon)								
					pgnI = pMovAlg.substring(0,1) + ( Integer.parseInt(pMovAlg.substring(1,2)) +1);
					if (ValidarPgnIPeon(pgnI, pCasillas, pTurnoBlancas, tipoPieza(pMovAlg))){
						pgnF = pMovAlg;	
					}
					else{
						pgnI = pMovAlg.substring(0,1) + ( Integer.parseInt(pMovAlg.substring(1,2)) +2);
						if (ValidarPgnIPeon(pgnI, pCasillas, pTurnoBlancas, tipoPieza(pMovAlg))){	
							pgnF = pMovAlg;
						}
						else{
							pgnI = "";
							pgnF = "";
						}
					}
			}
			else { // peon capturando
				if (pMovAlg.substring(1,1) == pMovAlg.substring(3,4)) { //Captura al paso
					pgnI = pMovAlg.substring(0,2);
					pgnF =  pMovAlg.substring(3,4) + (Integer.parseInt(pMovAlg.substring(4,5))+1);				
				}
				else {
					pgnF = pMovAlg.substring(2,4);
					if (esFila(pMovAlg.substring(0,1))){
						ArrayList<String>vPgnIs= new ArrayList<String>();
						vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, eTipoPieza.Peon, pMovAlg.substring(0,1));
						for(String vPgn: vPgnIs) {
								if (ValidarPgnI(vPgn, pgnF, eTipoPieza.Peon, Color.BLACK)) {
									pgnI =vPgn;
									break;
								}
						}				
					}
					else if (esColumna(pMovAlg.substring(0,1))) {
							ArrayList<String>vPgnIs= new ArrayList<String>();
							vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, eTipoPieza.Peon, pMovAlg.substring(1,2));
							for(String vPgn: vPgnIs) {
									if (ValidarPgnI(vPgn, pgnF, eTipoPieza.Peon, Color.BLACK)) {
										pgnI =vPgn;
									break;
									}
							}				
							pgnF = pMovAlg.substring(2,3);
					}					
				}
				if (!ValidarPgnIPeon(pgnI, pCasillas, pTurnoBlancas, vTipoPieza)){
					pgnI = "";
					pgnF = "";				
				}
			}
		}
	vRet[0] = pgnI.toUpperCase();
	vRet[1] = pgnF.toUpperCase();
	return vRet;
}

	public String[] CalularPgnI(boolean pTurnoBlancas, String pMovAlg , Casilla[] pCasillas, eTipoPieza pTipoPieza) {
		////system.out.println("tipo de pieza " + pTipoPieza);
	String pgnI="";
	String pgnF="";
	String[] vRet= new String[2];
	ArrayList<String>vPgnIs= new ArrayList<String>();
	
	//if (pTipoPieza.equals(eTipoPieza.Rey))
	
	if (pMovAlg.contains("O-O-O")) {
		if (pTurnoBlancas) {
			pgnI = "e1";
			pgnF = "c1";
		}
		else {
			pgnI = "e8";
			pgnF = "c8";
		}
		vRet[0] = pgnI.toUpperCase();
		vRet[1] = pgnF.toUpperCase();
		return vRet;		
		
	}else 
		if (pMovAlg.contains("O-O")) {			
			if (pTurnoBlancas) {
				pgnI = "e1";
				pgnF = "g1";
			}
			else {
				pgnI = "e8";
				pgnF = "g8";
			}
			
			vRet[0] = pgnI.toUpperCase();
			vRet[1] = pgnF.toUpperCase();
			return vRet;		
		}	
	//System.out.println("pMovAlg1 :"+pMovAlg);
	if (pMovAlg.contains("++")) {
		pMovAlg =pMovAlg.substring(0, pMovAlg.length() -2);
		
	}
	else
	if (pMovAlg.contains("+"))
		pMovAlg =pMovAlg.substring(0, pMovAlg.length() -1);

	
	//System.out.println("pMovAlg2 :"+pMovAlg);
	
	switch (pMovAlg.length()) {
	
	case 3: //movimiento normalito
		pgnF = pMovAlg.substring(1, 3);
		vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, pTipoPieza);
				
		break;
	case 4: // mover desde  o capturar
		pgnF = pMovAlg.substring(2, 4);
		if (pMovAlg.contains("x")) { //capturar

			vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, pTipoPieza);		
		}
		else { // mover desde fila o columna
			//vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, pTipoPieza);
			
			if (esFila(pMovAlg.substring(1, 2))){ //  se mueve desde una fila
				vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, pTipoPieza, pMovAlg.substring(1,2));
				
			}else if (esColumna(pMovAlg.substring(1, 2))){ // se mueve desde  una columna				
				vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, pTipoPieza, Integer.parseInt(pMovAlg.substring(1,2)));
			}												
		}							
		break;
		
	case 5: // mover desde y capturar
		pgnF = pMovAlg.substring(3, 5);
		
		if (esFila(pMovAlg.substring(1, 2))){ //  se mueve desde una fila
			vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, pTipoPieza, pMovAlg.substring(1,2));
			
		}else if (esColumna(pMovAlg.substring(1, 2))){ // se mueve desde  una columna		
			vPgnIs = BuscarPgnIs(pCasillas, pTurnoBlancas, pTipoPieza, Integer.parseInt(pMovAlg.substring(1,2)));
		}												
		
		break;
		
	default:
		break;
	}
	
	Color vColor= null;		
	
	if (pTurnoBlancas)
		vColor = Color.WHITE;			
	else 
		vColor = Color.BLACK;
	
	pgnI = "NO";
	for(String vPgn: vPgnIs) {
		//system.out.println("vpgnis: " + vPgn);
			if (ValidarPgnI(vPgn, pgnF, pTipoPieza, vColor)) {
				pgnI =vPgn;
			break;
			}
	}
	
	vRet[0] = pgnI.toUpperCase();
	vRet[1] = pgnF.toUpperCase();
	//system.out.println("pngi : " + pgnI + " pgnF:" + pgnF);
	return vRet;
	}

	private ArrayList<String> BuscarPgnIs(Casilla[] pCasillas , boolean pTurnoBlancas , eTipoPieza pTipoPieza) {
		ArrayList<String> vRet = new ArrayList<String>();		
		Color vColor= null;		
		
		if (pTurnoBlancas)
			vColor = Color.WHITE;
		else 
			vColor = Color.BLACK;
					
		for (int i =0; i<64; i++)
			try {			
				if (pCasillas[i].getPieza().getTipoPieza().equals(pTipoPieza)) { 
					if (pCasillas[i].getPieza().getColor().equals(vColor)) {
						vRet.add(pCasillas[i].getPgn().toLowerCase());
					}				
				}
			} catch (Exception e) {
				// TODO: handle exception
			}				
		return vRet;						
	}
	
	 private ArrayList<String> BuscarPgnIs(Casilla[] pCasillas, boolean pTurnoBlancas, eTipoPieza pTipoPieza, String pFila){
			ArrayList<String> vRet = new ArrayList<String>();		

			Color vColor= null;		
			
			if (pTurnoBlancas)
				vColor = Color.WHITE;
			else 
				vColor = Color.BLACK;

			//system.out.println("pFila:" + pFila + " color " + vColor);
			for (int i =0; i<64; i++)
				try {			
					if (pCasillas[i].getPieza().getTipoPieza().equals(pTipoPieza)) { 			
						if (pCasillas[i].getPieza().getColor().equals(vColor)) {
							//system.out.println("pCasillas[i].getPgn().toLowerCase().substring(0, 1) " + pCasillas[i].getPgn().toLowerCase().substring(0, 1) );
							if (pCasillas[i].getPgn().toLowerCase().substring(0, 1).contains(pFila)) {					
								vRet.add(pCasillas[i].getPgn().toLowerCase());
							}
						}				
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
					
			return vRet;								 
	 }
	 
	 private ArrayList<String> BuscarPgnIs(Casilla[] pCasillas, boolean pTurnoBlancas, eTipoPieza pTipoPieza, int pColumna){
//	System.out.println("buscando por la columna: " + pColumna + " tipo de pieza: " + pTipoPieza );
		 ArrayList<String> vRet = new ArrayList<String>();		
			Color vColor= null;		
			
			if (pTurnoBlancas)
				vColor = Color.WHITE;
			else 
				vColor = Color.BLACK;
						
			for (int i =0; i<64; i++)
				
				try {			
					if (pCasillas[i].getPieza().getTipoPieza().equals(pTipoPieza)) { 	
						if (pCasillas[i].getPieza().getColor().equals(vColor)) {
							if (pCasillas[i].getPgn().toLowerCase().substring(1, 2).contains(String.valueOf(pColumna))) {					
								vRet.add(pCasillas[i].getPgn().toLowerCase());
							}
						}				
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
					
			return vRet;						
		 
	 }

	
	private boolean esFila(String pFila) {
		
		for (String vFilas: filas) {
			if (vFilas.equals(pFila)){
			//	System.out.println("esfila" + pFila);
				return true;
				}
		}					
		//System.out.println("No esfila " + pFila);
		return false;
	}

	private boolean esColumna(String pColumna) {
		for (String vColumna: columnas) {
			if (vColumna.equals(pColumna)){
//				System.out.println("escolumna "+ pColumna);
				return true;
				}
		}					
	//	System.out.println("noescolumna "+ pColumna);
		return false;
	}
			
	private boolean  ValidarPgnIAlfil(String pPgnI, String pPgnF) {
		int pActorX = tablero.Pgn2XY(pPgnI)[0];
		int pActorY = tablero.Pgn2XY(pPgnI)[1];
		
		int pPosX =  tablero.Pgn2XY(pPgnF)[0];
		int pPosY =  tablero.Pgn2XY(pPgnF)[1];
		
		if (pPosY != pActorY) {
			float x = ((float) pPosX - (float) pActorX) / ((float) pPosY - (float) pActorY);
			if (Math.abs(x) == 1) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}
		}
		return false;
	}

	private boolean  ValidarPgnICaballo(String pPgnI, String pPgnF) {
		int pActorX = tablero.Pgn2XY(pPgnI)[0];
		int pActorY = tablero.Pgn2XY(pPgnI)[1];
		
		int pPosX =  tablero.Pgn2XY(pPgnF)[0];
		int pPosY =  tablero.Pgn2XY(pPgnF)[1];
		
		if (pPosX == pActorX + (anchoTablero * 2) && Math.abs(pPosY - pActorY) == altoTablero
				|| pPosY == pActorY + (altoTablero * 2) && Math.abs(pPosX - pActorX) == anchoTablero) {
			if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				return true;
		}

		if (pPosX == pActorX - (anchoTablero * 2) && Math.abs(pPosY - pActorY) == altoTablero
				|| pPosY == pActorY - (altoTablero * 2) && Math.abs(pPosX - pActorX) == anchoTablero) {
			if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				return true;
		}
		return false;		
	}

	private boolean  ValidarPgnIReina(String pPgnI, String pPgnF) {
		int pActorX = tablero.Pgn2XY(pPgnI)[0];
		int pActorY = tablero.Pgn2XY(pPgnI)[1];
		
		int pPosX =  tablero.Pgn2XY(pPgnF)[0];
		int pPosY =  tablero.Pgn2XY(pPgnF)[1];
		
		if (pPosX != pActorX) {
			if (Math.abs((pPosY - pActorY) / (pPosX - pActorX)) == 1.0f) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}
		}

		if (pPosY > pActorY && pPosX == pActorX) {
			if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				return true;
		}
		if (pPosY < pActorY && pPosX == pActorX) {
			if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				return true;
		}

		if (pPosX > pActorX && pPosY == pActorY) {
			if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				return true;
		}
		if (pPosX < pActorX && pPosY == pActorY) {
			if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				return true;
		}

		return false;
	} 

	private boolean  ValidarPgnIRey(String pPgnI, String pPgnF, Color pColor) {
		int pActorX = tablero.Pgn2XY(pPgnI)[0];
		int pActorY = tablero.Pgn2XY(pPgnI)[1];
		
		int pPosX =  tablero.Pgn2XY(pPgnF)[0];
		int pPosY =  tablero.Pgn2XY(pPgnF)[1];
		
		if (Math.abs(pPosX - pActorX) == anchoTablero && pPosY - pActorY == 0) {
			if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				return true;
		}

		if (Math.abs(pPosY - pActorY) == altoTablero && pPosX - pActorX == 0) {
			if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				return true;
		}

		if (pPosX != pActorX) {
			if (Math.abs((pPosY - pActorY) / (pPosX - pActorX)) == 1.0f && Math.abs(pPosX - pActorX) == anchoTablero
					&& Math.abs(pPosY - pActorY) == altoTablero) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}
			String vPgnI = tablero.XY2pgn(pActorX, pActorY);
			String vPgnf = tablero.XY2pgn(pPosX, pPosY);

			if (pColor.equals(Color.WHITE)) {
				if (vPgnI.contains("E1") && vPgnf.contains("C1")) {
					if (screenManager.movimientosPosibles(pPgnI, pPgnF))
						return true;
				}
				if (vPgnI.contains("E1") && vPgnf.contains("G1")) {
					if (screenManager.movimientosPosibles(pPgnI, pPgnF))
						return true;					
				}
			} else {
				if (vPgnI.contains("E8") && vPgnf.contains("C8")) {
					if (screenManager.movimientosPosibles(pPgnI, pPgnF))
						return true;				
				}
				if (vPgnI.contains("E8") && vPgnf.contains("G8")) {
					if (screenManager.movimientosPosibles(pPgnI, pPgnF))
						return true;
				}
			}
		}		
		return false;
	}
			
	private boolean ValidarPgnITorre(String pPgnI, String pPgnF) {
		int pActorX = tablero.Pgn2XY(pPgnI)[0];
		int pActorY = tablero.Pgn2XY(pPgnI)[1];
		
		int pPosX =  tablero.Pgn2XY(pPgnF)[0];
		int pPosY =  tablero.Pgn2XY(pPgnF)[1];
		
		 if(pPosY>pActorY && pPosX==pActorX ){
			 if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				 return true;
	     }
		 if(pPosY<pActorY && pPosX==pActorX ){
			 if (screenManager.movimientosPosibles(pPgnI, pPgnF))
				 return true;
	     }		 
		 
	     if( pPosX>pActorX && pPosY==pActorY){
	    	 if (screenManager.movimientosPosibles(pPgnI, pPgnF))
	    		 return true; 
	     }
	     if( pPosX<pActorX && pPosY==pActorY){
	    	 if (screenManager.movimientosPosibles(pPgnI, pPgnF))
	    		 return true; 
	     }
	     
    	 return false;	 
	}
	
	private boolean ValidarPgnIPeon(String pPgnI, String pPgnF, Color pColor) {
		
		int pActorX = tablero.Pgn2XY(pPgnI)[0];
		int pActorY = tablero.Pgn2XY(pPgnI)[1];
		
		int pPosX =  tablero.Pgn2XY(pPgnF)[0];
		int pPosY =  tablero.Pgn2XY(pPgnF)[1];
				
		int inicioTableroY = tablero.GetInicioY();
		int finTableroY = tablero.GetFinY();
		
		if (pColor.equals(Color.WHITE) && !tablero.getTableroRotado()
				|| pColor.equals(Color.BLACK) && tablero.getTableroRotado()) {
			if (pPosY == pActorY + altoTablero && pPosX == pActorX) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}

			if (pPosY == pActorY + (altoTablero * 2) && pActorY == inicioTableroY + altoTablero && pPosX == pActorX) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}

			if (pPosY == pActorY + (altoTablero) && Math.abs(pPosX - pActorX) == anchoTablero) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}
			return false;
		}

		if (pColor.equals(Color.BLACK) && !tablero.getTableroRotado()
				|| pColor.equals(Color.WHITE) && tablero.getTableroRotado()) {
			if (pPosY == pActorY - altoTablero && pPosX == pActorX) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}

			if (pPosY == pActorY - (altoTablero * 2) && pActorY == finTableroY - (altoTablero) && pPosX == pActorX) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}

			if (pPosY == pActorY - (altoTablero) && Math.abs(pPosX - pActorX) == anchoTablero) {
				if (screenManager.movimientosPosibles(pPgnI, pPgnF))
					return true;
			}
			return false;
		}

		return false;
	 
	}
	
	private boolean ValidarPgnI(String pPgnI, String pPgnF, eTipoPieza pTipoPieza, Color pColor) {
	
		if (pTipoPieza.equals(eTipoPieza.Alfil))
			return	ValidarPgnIAlfil(pPgnI, pPgnF);
		else if(pTipoPieza.equals(eTipoPieza.Caballo))
			return ValidarPgnICaballo(pPgnI, pPgnF);
		else if(pTipoPieza.equals(eTipoPieza.Reina))
			return ValidarPgnIReina(pPgnI, pPgnF);
		else if(pTipoPieza.equals(eTipoPieza.Rey))
			return ValidarPgnIRey(pPgnI,pPgnF, Color.WHITE);
		else if(pTipoPieza.equals(eTipoPieza.Torre))
			return ValidarPgnITorre(pPgnI, pPgnF);
		else if (pTipoPieza.equals(eTipoPieza.Peon))
			return ValidarPgnIPeon(pPgnI, pPgnF, pColor);
		
		return false;	
	}
	
	public String traducirLista(String pJugadas) {
		String[] vJugadas = pJugadas.split(" ");
		String vRetJugadas ="";
		boolean idiomaIngles = false;
		
		for (String vJugada: vJugadas){// saber cual es el idioma			
			for (String vPiezaEn:piezasEn) {
				////system.out.println("vJugada: " + vJugada + " vPiezaEn " + vPiezaEn);
				if (!vJugada.contains("O"))
					if (vJugada.contains(vPiezaEn)) {
						idiomaIngles = true;
						break;					
					}
			}	
			if (idiomaIngles)
				break;		
		}
			
			if (!idiomaIngles) { // traducir a Ingles

				for (String vJugada: vJugadas) {
					String[] traducir = new String[2];				
						if (vJugada.contains("T")) {
							traducir[0] = "T"; traducir[1] = "R";
						}
						else if (vJugada.contains("C")){
							traducir[0] = "C"; traducir[1] = "N";
						}
						else if (vJugada.contains("A")){
							traducir[0] = "A"; traducir[1] = "B";
						}
						else if (vJugada.contains("D")){
							traducir[0] = "D"; traducir[1] = "Q";
						}
						else if (vJugada.contains("R")){
							traducir[0] = "R"; traducir[1] = "K";
						}					
						else if (vJugada.contains("O")){
							traducir[0] = "O"; traducir[1]="O";
						}
						
						if (traducir[0]!=null && traducir[1]!=null)								
							vJugada = vJugada.replace(traducir[0], traducir[1]);
												
						vJugada = vJugada + " ";						
						vRetJugadas = vRetJugadas + vJugada;					
				//}					
				}				
				return vRetJugadas;				
			}				
			else
				return pJugadas;			
	}
	
}
