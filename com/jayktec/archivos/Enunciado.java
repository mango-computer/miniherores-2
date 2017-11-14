//////////////////////////////////////////////////////////////
//                    www.jayktec.com.ve                    //
//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
//                   Enunciado.java                          //
//                   Descripcion                             //
//     clase encargada de loss Enunciados de las trivias     //
// es decir una pregunta con respuestas    ////////////////////
//      Autor            Fecha           Motivo             // 
//Yisheng León      06/06/2016     Version Inicial          //
/////////////////////////////////////////////////////////////

package com.jayktec.archivos;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.jayktec.grafico.Enums.eTipoTrivia;

public class Enunciado {

	private Pregunta pregunta;
	private ArrayList<Respuesta> respuestas;
	public int index = -1;// significa que la selección no fue aleatoria

	/**
	 * Creación de un enunciado con una pregunta, y un listado de respuestas
	 * posibles que debe contener la respuesta correcta
	 * 
	 * @param pregunta
	 * @param respuestas
	 */
	public Enunciado(Pregunta pregunta, ArrayList<Respuesta> respuestas) {
		super();
		this.pregunta = pregunta;
		this.respuestas = respuestas;
	}

	/**
	 * Construye un Enunciado a partir de la dificultad de una pregunta,
	 * obteniendo la pregunta , la respuesta correcta y otras opciones válidas,
	 * estas respuestas las resuelve de modo desordenado para que ya puedan ser
	 * mostradas, ofrece hasta 4 respuestas , si el numero de respuestas
	 * posibles es menor a 4 las muestras todas
	 * 
	 * 
	 * @param dificultad
	 *            valor de 1 a 5 siendo 1 lo mas facil y 5 lo mas dificil
	 * @throws Exception
	 * @author yisheng
	 */
	public Enunciado(int dificultad, eTipoTrivia tipoTrivia) throws Exception {
		super();
		ArrayList<Pregunta> preguntas = Trivia.GetPreguntas(dificultad, tipoTrivia);
		ThreadLocalRandom aleatorio = ThreadLocalRandom.current();
		this.pregunta = preguntas.get(aleatorio.nextInt(preguntas.size() - 1));
		String correcta = pregunta.getCorrecta();
		String grupo = pregunta.getGrupo();

		ArrayList<Respuesta> pRespuestas = new ArrayList<Respuesta>();
		ArrayList<Respuesta> temporal = Trivia.GetRespuesta(grupo, tipoTrivia);


		if (temporal.size() < 5) {
			pRespuestas = temporal;
		}

		else

		{
			pRespuestas.add(Trivia.GetRespuesta(grupo, correcta, tipoTrivia));
			for (int i = 0; i < temporal.size(); i++) {
				if (temporal.get(i).getOrden().equals(correcta)) {
					temporal.remove(i);
				}

			}

			for (int i = 0; i < 3; i++) {
				int any = aleatorio.nextInt(pRespuestas.size() - 1);
				pRespuestas.add(temporal.get(any));
				temporal.remove(any);
			}
		}

		
		this.respuestas = shufle(pRespuestas);
	}

	/**
	 * Construye un Enunciado a partir de un conjunto de preguntas, obteniendo
	 * la pregunta , la respuesta correcta y otras opciones válidas, estas
	 * respuestas las resuelve de modo desordenado para que ya puedan ser
	 * mostradas, ofrece hasta 4 respuestas , si el numero de respuestas
	 * posibles es menor a 4 las muestras todas
	 * 
	 * 
	 * @param valorDificultad
	 *            valor de 1 a 5 siendo 1 lo mas facil y 5 lo mas dificil
	 * @throws Exception
	 * @author yisheng
	 */
	public Enunciado(ArrayList<Pregunta> preguntas, eTipoTrivia tipoTrivia) throws Exception {
		super();
		// System.out.println("entre a creer respuesta");
		// preguntas=shufleP(preguntas);
		ThreadLocalRandom aleatorio = ThreadLocalRandom.current();
		// System.out.println("preguntas tamaño:" + preguntas.size());
		// System.out.println(this.index);
		this.index = aleatorio.nextInt(preguntas.size());
		// System.out.println("index" + this.index);
		this.pregunta = preguntas.get(index);

		String correcta = pregunta.getCorrecta();
		// System.out.println("correcta:" + correcta);
		String grupo = pregunta.getGrupo();
		// System.out.println("grupo:" + grupo);

		ArrayList<Respuesta> pRespuestas = new ArrayList<Respuesta>();
		ArrayList<Respuesta> temporal = Trivia.GetRespuesta(grupo, tipoTrivia);
		//

//		System.out.println("temporal:" + temporal.size());

		
		if (temporal.size() < 5) {
			pRespuestas = temporal;
		}

		else

		{
			pRespuestas.add(Trivia.GetRespuesta(grupo, correcta, tipoTrivia));
			for (int i = 0; i < temporal.size(); i++) {
				if (temporal.get(i).getOrden().equals(correcta)) {
//					System.out.println("entre a borrar la correcta");
//					System.out.println(correcta);

					temporal.remove(i);
				}

			}

			for (int i = 0; i < 3; i++) {
				int any = aleatorio.nextInt(pRespuestas.size());
				pRespuestas.add(temporal.get(any));
//				System.out.println("valor respuesta" + temporal.get(any).getValor());

				temporal.remove(any);
			}
		}

		this.respuestas = shufle(pRespuestas);

	}

	/**
	 * desordenar un listado de Respuestas
	 * 
	 * @param pRespuestas
	 * @return
	 * @author yisheng
	 */
	private ArrayList<Respuesta> shufle(ArrayList<Respuesta> pRespuestas) {
		ThreadLocalRandom aleatorio = ThreadLocalRandom.current();

		for (int i = 0; i < pRespuestas.size(); i++) {
			int randomPosition = aleatorio.nextInt(pRespuestas.size() - 1);
			Respuesta temp = pRespuestas.get(i);
			pRespuestas.set(i, pRespuestas.get(randomPosition));
			pRespuestas.set(randomPosition, temp);

		}

		return pRespuestas;

	}

	/**
	 * desordenar un listado de Preguntas
	 * 
	 * @param pPreguntas
	 * @return
	 * @author yisheng
	 */
//	private ArrayList<Pregunta> shufleP(ArrayList<Pregunta> pPreguntas) {
//		ThreadLocalRandom aleatorio = ThreadLocalRandom.current();
//
//		for (int i = 0; i < pPreguntas.size(); i++) {
//			int randomPosition = aleatorio.nextInt(pPreguntas.size() - 1);
//			Pregunta temp = pPreguntas.get(i);
//			pPreguntas.set(i, pPreguntas.get(randomPosition));
//			pPreguntas.set(randomPosition, temp);
//
//		}
//
//		return pPreguntas;
//
//	}

	/**
	 * verifica que el enunciado este bien hecho y que en el grupo de respuestas
	 * contenga la respuesta correcta de la pregunta
	 * 
	 * @return verdadero si esta bien hecho el enunciado falso lo contrario
	 * @author yisheng
	 */
	public boolean verificacion() {
		String correcta = this.pregunta.getCorrecta();
		String grupo = this.pregunta.getGrupo();
		for (int i = 0; i < this.respuestas.size(); i++) {
			if (this.respuestas.get(i).getOrden().equals(correcta)
					&& (this.respuestas.get(i).getGrupo().equals(grupo))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * verifica que el enunciado que se recibe de parametro este bien hecho y
	 * que en el grupo de respuestas contenga la respuesta correcta de la
	 * pregunta
	 * 
	 * @param enunciado
	 *            objeto a ser verificado
	 * @return verdadero si esta bien hecho el enunciado falso lo contrario
	 * @author yisheng
	 */
	public boolean verificacion(Enunciado enunciado) {
		String correcta = enunciado.getPregunta().getCorrecta();
		String grupo = enunciado.getPregunta().getGrupo();
		for (int i = 0; i < enunciado.getRespuestas().size(); i++) {
			if (enunciado.getRespuestas().get(i).getOrden().equals(correcta)
					&& (enunciado.getRespuestas().get(i).getGrupo().equals(grupo))) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return the pregunta
	 */
	public Pregunta getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta
	 *            the pregunta to set
	 */
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * @return the respuestas
	 */
	public ArrayList<Respuesta> getRespuestas() {
		return respuestas;
	}

	/**
	 * @param respuestas
	 *            the respuestas to set
	 */
	public void setRespuestas(ArrayList<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	

}
