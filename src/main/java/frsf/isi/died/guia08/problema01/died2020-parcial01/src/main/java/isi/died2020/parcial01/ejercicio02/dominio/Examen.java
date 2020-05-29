package isi.died2020.parcial01.ejercicio02.dominio;

import java.time.LocalDate;

public class Examen {
	private static Integer ID_GENERATOR=0;

	private Integer id;
	private Alumno alumno;
	private Materia materia;
	private Docente docente;
	private Integer nota;
	private LocalDate fecha;
	
	
	
	public Examen() {
		this.id = ID_GENERATOR++;
	}

	public Examen(Alumno alumno, Materia materia, Docente docente, Integer nota, LocalDate fecha) {
		this();
		this.alumno = alumno;
		this.materia = materia;
		this.docente = docente;
		this.nota = nota;
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public Docente getDocente() {
		return docente;
	}
	public void setDocente(Docente docente) {
		this.docente = docente;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	
}
