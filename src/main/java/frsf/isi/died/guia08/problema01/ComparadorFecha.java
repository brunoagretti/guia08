package frsf.isi.died.guia08.problema01;

import java.util.Comparator;

import frsf.isi.died.guia08.problema01.modelo.FechaTareaException;
import frsf.isi.died.guia08.problema01.modelo.Tarea;

public abstract class ComparadorFecha implements Comparator<Tarea> {

	public abstract int compare(Tarea o1, Tarea o2) throws FechaTareaException;

}
