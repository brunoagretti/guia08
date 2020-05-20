package frsf.isi.died.guia08.problema01.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Tarea {

	public void setEmpleadoAsignado(Empleado empleadoAsignado) {
		//this.empleadoAsignado = empleadoAsignado;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDuracionEstimada() {
		return duracionEstimada;
	}

	public void setDuracionEstimada(Integer duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	public LocalDateTime getFechaInicio() throws FechaTareaException {
		if(fechaInicio.isPresent()) {
			return fechaInicio.get();
		}else {
			throw new FechaTareaException("La tarea no tiene fecha de inicio inicializada");
		}
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = Optional.ofNullable(fechaInicio);
	}

	public LocalDateTime getFechaFin() throws FechaTareaException {
		if(fechaFin.isPresent()) {
			return fechaFin.get();
		}else {
			throw new FechaTareaException("La tarea no tiene fecha de finalizacion inicializada");
		}
	}
	public static Tarea getTarea(int idTarea) throws TareaException { //busca en la clase las instancias de tarea segun id
		return tareas.stream()
			  .filter(t -> t.getId()==idTarea)
			  .findFirst().orElseThrow(()-> new TareaException("No se ha encontrado la tarea con el id " + idTarea));
	}

	public void setFechaFin(LocalDateTime fechaFin) {		
		this.fechaFin = Optional.ofNullable(fechaFin);
	}

	public Boolean getFacturada() {
		return facturada;
	}

	public void setFacturada(Boolean facturada) {
		this.facturada = facturada;
	}

	public Optional<Empleado> getEmpleadoAsignado() {
		if(empleadoAsignado.isEmpty()) {
			return Optional.empty();
		}
		return empleadoAsignado;
	}

	private Integer id;
	private String descripcion;
	private Integer duracionEstimada;
	private Optional<Empleado> empleadoAsignado;
	private Optional<LocalDateTime> fechaInicio;
	private Optional<LocalDateTime> fechaFin;
	private Boolean facturada;
	private static ArrayList<Tarea> tareas;
	
	public void asignarEmpleado(Empleado e) throws TareaException {
		
		if(empleadoAsignado.isPresent()) {
			throw new TareaException("Ya hay un empleado asignado para esta tarea");
		}else {
			if(fechaFin.isPresent()) {
				throw new TareaException("La tarea ya ha sido finalizada");
			}
		}
		empleadoAsignado = Optional.of(e);
		
		// si la tarea ya tiene un empleado asignado
		// y tiene fecha de finalizado debe lanzar una excepcion
	}

	public Tarea(int id, int duracion, String desripcion, Optional<LocalDateTime> fi, Optional<LocalDateTime> ff) {
		this.id = id;
		this.descripcion = desripcion;
		duracionEstimada = duracion;
		fechaInicio = fi;
		fechaFin = ff;
		facturada = false;
		tareas.add(this);
	}
	
}
