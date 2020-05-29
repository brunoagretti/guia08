package frsf.isi.died.guia08.problema01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDateTime;
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

	public void cargarEmpleadosContratadosCSV(String nombreArchivo) throws FileNotFoundException, IOException {
		
		
		try(Reader fileReader = new FileReader(nombreArchivo)) {
				try(BufferedReader in = new BufferedReader(fileReader)){
				String linea = null;
					while((linea = in.readLine())!=null) {
					String[] fila = linea.split(";");	
					agregarEmpleadoContratado(Integer.valueOf(fila[0]), fila[1],(double) Integer.valueOf(fila[2]));
					
				}
			}
		}
		
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado
	}

	public void cargarEmpleadosEfectivosCSV(String nombreArchivo) throws FileNotFoundException, IOException {
		
		try(Reader fileReader = new FileReader(nombreArchivo)) {
				try(BufferedReader in = new BufferedReader(fileReader)){
				String linea = null;
					while((linea = in.readLine())!=null) {
					String[] fila = linea.split(";");	
					agregarEmpleadoEfectivo(Integer.valueOf(fila[0]), fila[1],(double) Integer.valueOf(fila[2]));
					
				}
			}
		}
		
		// leer datos del archivo
		// por cada fila invocar a agregarEmpleadoContratado		
	}

	
	public void cargarTareasCSV(String nombreArchivo) throws TareaException, EmpleadoException, FileNotFoundException, IOException {
		
		Optional<Empleado> empleadoAasignar = null;
		try(Reader fileReader = new FileReader(nombreArchivo)) {
				try(BufferedReader in = new BufferedReader(fileReader)){
				String linea = null;
					while((linea = in.readLine())!=null) {
					String[] fila = linea.split(";");
					Tarea t = new Tarea(Integer.valueOf(fila[0]), Integer.valueOf(fila[2]), fila[1], null, null);
					empleadoAasignar = empleados.stream().filter(e -> e.getCuil().equals(Integer.valueOf(fila[3]))).findFirst();
					t.asignarEmpleado(empleadoAasignar.orElseThrow(()-> new EmpleadoException("")));
					
				}
			}
		}

	}
	
	public Double facturar() {
		this.guardarTareasTerminadasCSV();
		return this.empleados.stream()				
				.mapToDouble(e -> e.salario())
				.sum();
	}
	

	private void guardarTareasTerminadasCSV() {
		
		Tarea.tareas.stream()
					.filter(t -> !t.getFacturada() && t.finalizo())
					.forEach(t -> {
						try {
							this.guardarTarea(t);
						} catch (IOException | TareaException e) { 
							e.printStackTrace();
						}
					});
		
		// guarda una lista con los datos de la tarea que fueron terminadas
		// y todavía no fueron facturadas
		// y el nombre y cuil del empleado que la finalizó en formato CSV 
	}
	
	private void guardarTarea(Tarea t) throws IOException, TareaException {
		try(Writer fileWriter = new FileWriter("tareas_terminadas.csv", true)){
			try(BufferedWriter out = new BufferedWriter(fileWriter)){
				out.write(t.asCsv()+System.getProperty("line.separator"));
			}
		}
	}

	private Optional<Empleado> buscarEmpleado(Predicate<Empleado> p){
		return this.empleados.stream().filter(p).findFirst();
	}
	
	
}

