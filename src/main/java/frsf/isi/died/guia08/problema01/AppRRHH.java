package frsf.isi.died.guia08.problema01;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import frsf.isi.died.guia08.problema01.modelo.Empleado;
import frsf.isi.died.guia08.problema01.modelo.Tarea;
import frsf.isi.died.guia08.problema01.modelo.TareaException;
import frsf.isi.died.guia08.problema01.modelo.Empleado.Tipo;

public class AppRRHH {

	private ArrayList<Empleado> empleados;

	public AppRRHH() {
		this.empleados = new ArrayList<Empleado>();
		
	}

	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}

	public void agregarEmpleadoContratado(Integer cuil,String nombre,Double costoHora) {
		
		Empleado empleado = new Empleado(cuil, nombre, Tipo.CONTRATADO, costoHora);
		this.empleados.add(empleado);
		// crear un empleado
		// agregarlo a la lista
	}
	
	public void agregarEmpleadoEfectivo(Integer cuil,String nombre,Double costoHora) {
		
		Empleado empleado = new Empleado(cuil, nombre, Tipo.EFECTIVO, costoHora);
		empleados.add(empleado);
		// crear un empleado
		// agregarlo a la lista		
	}
	
	public void asignarTarea(Integer cuil,Integer idTarea,String descripcion,Integer duracionEstimada) {
		Tarea tarea = new Tarea(idTarea, duracionEstimada, descripcion, Optional.empty(), Optional.empty());
		try {
			buscarEmpleado(e -> e.getCuil().equals(cuil)).orElseThrow(() -> new EmpleadoException("Empleado missing")).asignarTarea(tarea);
		} catch (EmpleadoException e1) {
			System.out.println("Ha ocurrido un error: " + e1);
			e1.printStackTrace();
		} catch (TareaException e2) {
			System.out.println("Ha ocurrido un error: " + e2);
			e2.printStackTrace();
		}
		
		// busca el empleado por cuil en la lista de empleados
		// con el método buscarEmpleado() actual de esta clase
		// e invoca al método asignar tarea	
	}
	
	public void empezarTarea(Integer cuil,Integer idTarea) {
		
		try {
			buscarEmpleado(e -> e.getCuil().equals(cuil)).orElseThrow(() -> new EmpleadoException("Empleado missing")).comenzar(idTarea);
		} catch (EmpleadoException e1) {
			System.out.println("Ha ocurrido un error: " + e1);
			e1.printStackTrace();
		} catch (TareaException e2) {
			System.out.println("Ha ocurrido un error: " + e2);
			e2.printStackTrace();
		}
		
		// busca el empleado por cuil en la lista de empleados
		// con el método buscarEmpleado() actual de esta clase
		// e invoca al método comenzar tarea
	}
	
	public void terminarTarea(Integer cuil,Integer idTarea) {
		
		try {
			buscarEmpleado(e -> e.getCuil().equals(cuil)).orElseThrow(() -> new EmpleadoException("Empleado missing")).finalizar(idTarea);
		} catch (EmpleadoException e1) {
			System.out.println("Ha ocurrido un error: " + e1);
			e1.printStackTrace();
		} catch (TareaException e2) {
			System.out.println("Ha ocurrido un error: " + e2);
			e2.printStackTrace();
		}
		
	}

	public void cargarEmpleadosContratadosCSV(String nombreArchivo) {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado
	}

	public void cargarEmpleadosEfectivosCSV(String nombreArchivo) {
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado		
	}

	public void cargarTareasCSV(String nombreArchivo) {
		// leer datos del archivo
		// cada fila del archivo tendrá:
		// cuil del empleado asignado, numero de la taera, descripcion y duración estimada en horas.
	}
	
	public Double facturar() {
		this.guardarTareasTerminadasCSV();
		return this.empleados.stream()				
				.mapToDouble(e -> e.salario())
				.sum();
	}

	private void guardarTareasTerminadasCSV() {
		// guarda una lista con los datos de la tarea que fueron terminadas
		// y todavía no fueron facturadas
		// y el nombre y cuil del empleado que la finalizó en formato CSV 
	}

	private Optional<Empleado> buscarEmpleado(Predicate<Empleado> p){
		return this.empleados.stream().filter(p).findFirst();
	}
	
}

