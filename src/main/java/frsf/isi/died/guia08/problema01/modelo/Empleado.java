package frsf.isi.died.guia08.problema01.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Empleado {

	public Integer getCuil() {
		return cuil;
	}

	public void setCuil(Integer cuil) {
		this.cuil = cuil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Double getCostoHora() {
		return costoHora;
	}

	public void setCostoHora(Double costoHora) {
		this.costoHora = costoHora;
	}

	public List<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}

	public void setTareasAsignadas(List<Tarea> tareasAsignadas) {
		this.tareasAsignadas = tareasAsignadas;
	}

	public Function<Tarea, Double> getCalculoPagoPorTarea() {
		return calculoPagoPorTarea;
	}

	public void setCalculoPagoPorTarea(Function<Tarea, Double> calculoPagoPorTarea) {
		this.calculoPagoPorTarea = calculoPagoPorTarea;
	}

	public Predicate<Tarea> getPuedeAsignarTarea() {
		return puedeAsignarTarea;
	}

	public void setPuedeAsignarTarea(Predicate<Tarea> puedeAsignarTarea) {
		this.puedeAsignarTarea = puedeAsignarTarea;
	}

	public enum Tipo { CONTRATADO,EFECTIVO}; 
	
	private Integer cuil;
	private String nombre;
	private Tipo tipo;
	private Double costoHora;
	private List<Tarea> tareasAsignadas;
	private Function<Tarea, Double> calculoPagoPorTarea;		
	private Predicate<Tarea> puedeAsignarTarea; //este predicado es verdadero si la tarea no fue asiganada a otro empleado o si ya fue finalizada. Si no se cumple se lanzara excepcion
	private Predicate<Empleado> empleadoPuedeAsignarTareas; //este predicado es verdadero si el empleado puede asignar cualquier tarea (si tiene suficiente espacio)

	private int horasTrabajoPendiente() {
		return this.tareasAsignadas.stream()
				.mapToInt(t -> t.getDuracionEstimada())
				.sum();
	}

	public Empleado(Integer cuil, String nombre, Tipo tipo, Double costoHora) {

		this.cuil = cuil;
		this.nombre = nombre;
		this.tipo = tipo;
		this.costoHora = costoHora;
		this.calculoPagoPorTarea = (t) -> { //podria haber hecho el if afuera. quedo un choclo de largo y feo xd
			try {
				if(tipo==Tipo.CONTRATADO) {  // en teoria son 4 horas de trabajo diaria
					if(ChronoUnit.DAYS.between(t.getFechaInicio(), t.getFechaFin())*4>t.getDuracionEstimada()) {
						return t.getDuracionEstimada()*costoHora*1.3 + (4*ChronoUnit.DAYS.between(t.getFechaInicio(), t.getFechaFin()))*costoHora*1.3;
					}else {
						if(ChronoUnit.DAYS.between(t.getFechaInicio(), t.getFechaFin())*4 > t.getDuracionEstimada()+8) { //se demora mas de dos dias
							return t.getDuracionEstimada()*costoHora*0.75 + (4*ChronoUnit.DAYS.between(t.getFechaInicio(), t.getFechaFin()))*costoHora*0.75;	
						}
						return t.getDuracionEstimada()*costoHora + (4*ChronoUnit.DAYS.between(t.getFechaInicio(), t.getFechaFin()))*costoHora;
					}
				}else { //para los empleados efectivos
					if(ChronoUnit.DAYS.between(t.getFechaInicio(), t.getFechaFin())*4>t.getDuracionEstimada()) {
						return t.getDuracionEstimada()*costoHora*1.2 + (4*ChronoUnit.DAYS.between(t.getFechaInicio(), t.getFechaFin()))*costoHora*1.2;
					}else {
						return t.getDuracionEstimada()*costoHora + (4*ChronoUnit.DAYS.between(t.getFechaInicio(), t.getFechaFin()))*costoHora;					
					}
				}
			}
			catch(FechaTareaException e) {
				System.out.println("Ha ocurrido un error: " + e);
				e.printStackTrace();
				return 0.0; //no se si esto es valido pero el compilador me pedia que retornase un double
			}
		};
		this.puedeAsignarTarea = (t) -> {
			try {
			return t.getFechaFin().isAfter(LocalDateTime.now()) && t.getEmpleadoAsignado().isEmpty();
			}
			catch(FechaTareaException e) {
				System.out.println("Ha ocurrido un error: " + e);
				e.printStackTrace();
				return false; //no se si esto es valido pero el compilador me pedia que retornase un booleano
			}	
		};
		this.empleadoPuedeAsignarTareas = e ->  ((e.getTipo() == Tipo.CONTRATADO && e.tareasAsignadas.stream()
																									 .filter(t -> !t.getFacturada())
																									 .count()<=5)  
				                             || (e.getTipo() == Tipo.EFECTIVO && e.horasTrabajoPendiente()<=15));

				
	}
	
	public Double salario() {
		Double costo =  this.tareasAsignadas.stream()
					    	.filter(t -> !t.getFacturada())
							.mapToDouble(t -> calculoPagoPorTarea.apply(t))
							.sum();
		
		this.tareasAsignadas.stream()
							.filter(t -> !t.getFacturada())
							.forEach(t -> t.setFacturada(true));
		return costo;
							
		
		// cargar todas las tareas no facturadas
		// calcular el costo
		// marcarlas como facturadas.
	}
	
	/**
	 * Si la tarea ya fue terminada nos indica cuaal es el monto según el algoritmo de calculoPagoPorTarea
	 * Si la tarea no fue terminada simplemente calcula el costo en base a lo estimado.
	 * @param t
	 * @return
	 */
//	public Double costoTarea(Tarea t) {
//		return 0.0;
//	}
	@Override
	public String toString() {
		return this.nombre + " Cuil: " + this.cuil;
	}
	
	
	public Boolean asignarTarea(Tarea t) throws TareaException {
		if(puedeAsignarTarea.test(t)) {
			if(empleadoPuedeAsignarTareas.test(this)) {
				this.tareasAsignadas.add(t);
				return true;
			}
			return false;
		}
		throw new TareaException("Error: la tarea ya tiene otro empleado asignado o ya fue finalizada");
	}
	
	public void comenzar(Integer idTarea) throws TareaException {
		
		Optional<Tarea> tarea = this.tareasAsignadas.stream()
							.filter(t -> t.getId().equals(idTarea))
							.findFirst();
		if(tarea.isEmpty()) {
			throw new TareaException("Error: no se ha encontrado ninguna tarea con el id: " + idTarea);
		}else {
			tarea.get().setFechaInicio(LocalDateTime.now());
		}
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de inicio la fecha y hora actual
	}
	
	public void finalizar(Integer idTarea) throws TareaException {
		
		Optional<Tarea> tarea = this.tareasAsignadas.stream()
							.filter(t -> t.getId().equals(idTarea))
							.findFirst();
		if(tarea.isEmpty()) {
			throw new TareaException("Error: no se ha encontrado ninguna tarea con el id: " + idTarea);
		}else {
			tarea.get().setFechaFin(LocalDateTime.now());
		}		
		
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
	}

	public void comenzar(Integer idTarea,String fecha) throws TareaException { //fecha en formato DD-MM-YYYY HH:MM
		
		
		Optional<Tarea> tarea = this.tareasAsignadas.stream()
							.filter(t -> t.getId().equals(idTarea))
							.findFirst();
		if(tarea.isEmpty()) {
			throw new TareaException("Error: no se ha encontrado ninguna tarea con el id: " + idTarea);
		}else {
			tarea.get().setFechaInicio(LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("DD-MM-YYYY HH:MM")));
		}
		
		
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
	}
	
	public void finalizar(Integer idTarea,String fecha) throws TareaException {
		
		
		Optional<Tarea> tarea = this.tareasAsignadas.stream()
							.filter(t -> t.getId().equals(idTarea))
							.findFirst();
		if(tarea.isEmpty()) {
			throw new TareaException("Error: no se ha encontrado ninguna tarea con el id: " + idTarea);
		}else {
			tarea.get().setFechaFin(LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("DD-MM-YYYY HH:MM")));
		}
		
		
		// busca la tarea en la lista de tareas asignadas 
		// si la tarea no existe lanza una excepción
		// si la tarea existe indica como fecha de finalizacion la fecha y hora actual
	}
}
