package isi.died2020.parcial01.ejercicio02.dominio;

import java.time.LocalDate;
import java.util.List;

public class Inscripcion {
	private static Integer ID_GENERATOR=0;

	public enum Estado {REGULAR,LIBRE,CURSANDO,PROMOCIONADO};
	private Integer id;
	private Alumno inscripto;
	private Materia materia;
	private Integer cicloLectivo;
	private Docente docente;
	private Estado estado;

	public Inscripcion() {
		this.id = ID_GENERATOR++;
	}
	
	public Inscripcion(Integer ciclo,Estado e) {
		this();
		this.cicloLectivo = ciclo;
		this.estado = e;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Alumno getInscripto() {
		return inscripto;
	}

	public void setInscripto(Alumno inscripto) {
		this.inscripto = inscripto;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Integer getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(Integer cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
s