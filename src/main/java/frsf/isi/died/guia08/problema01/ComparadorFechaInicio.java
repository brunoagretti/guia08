package frsf.isi.died.guia08.problema01;

import frsf.isi.died.guia08.problema01.modelo.FechaTareaException;
import frsf.isi.died.guia08.problema01.modelo.Tarea;

public class ComparadorFechaInicio extends ComparadorFecha {

	@Override
	public int compare(Tarea o1, Tarea o2) throws FechaTareaException {
		return o1.getFechaInicio().compareTo(o2.getFechaInicio());
	}
	
}
