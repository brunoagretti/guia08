package frsf.isi.died.guia08.problema01;

import java.util.Comparator;
import java.util.List;
import frsf.isi.died.guia08.problema01.modelo.Empleado;

public class Main {

	public static void main(String[] args) {
		AppRRHH app = new AppRRHH(); 
		
		app.agregarEmpleadoContratado(1, "Juan", 130.0);
		app.agregarEmpleadoEfectivo(3, "Simnon", 300.0);
		app.agregarEmpleadoContratado(2, "Arnold", 120.0);
		app.agregarEmpleadoEfectivo(4, "Cacho", 200.0);
		
		Comparator<Empleado> comparador = (e1,e2) -> e1.getCuil().compareTo(e2.getCuil());
		List<Empleado> lista = app.getEmpleados();
		lista.sort(comparador);
		System.out.println(lista.toString());

		

	}

}
