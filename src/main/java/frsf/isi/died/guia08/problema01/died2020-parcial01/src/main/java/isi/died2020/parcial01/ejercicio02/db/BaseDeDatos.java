package isi.died2020.parcial01.ejercicio02.db;

import isi.died2020.parcial01.ejercicio02.dominio.Examen;
import isi.died2020.parcial01.ejercicio02.dominio.Inscripcion;

/**
 * Esta clase es falsa, tiene como objetivo 
 * simular la logica que empleariamos si enviamos datos 
 * a una base de datos pero en realidad no ejecuta nada.
 * Es util para poder entender como gestionar excepciones
 *  * @author marti
 *
 */
public class BaseDeDatos {

	public void guardar(Inscripcion i) throws BaseDeDatosExcepcion {
		if(i == null) throw new BaseDeDatosExcepcion(i);
		// simula una llamada a una base de datos
	}
	
	public void guardar(Examen i) throws BaseDeDatosExcepcion {
		if(i == null) throw new BaseDeDatosExcepcion(i);
		// simula una llamada a una base de datos

	}
}