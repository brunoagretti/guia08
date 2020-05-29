package isi.died2020.parcial01.ejercicio02.dominio;

import java.util.ArrayList;
import java.util.List;

public class Materia {
	private static Integer ID_GENERATOR=0;

	private Integer id;
	private String nombre;
	private List<Docente> docentes;
	private List<Inscripcion> inscripciones;
	private List<Examen> examenes;
	private List<Materia> correlativasCursada;
	private List<Materia> correlativasRendir;
	
	public Materia() {
		this.id = ID_GENERATOR++;
	}
	
	public Materia(String nombre) {
		this();
		this.docentes = new ArrayList<Docente>();
		this.inscripciones = new ArrayList<Inscripcion>();
		this.examenes = new ArrayList<Examen>();
		this.correlativasCursada = new ArrayList<Materia>();
		this.correlativasRendir = new ArrayList<Materia>();
		this.nombre = nombre;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Docente> getDocentes() {
		return docentes;
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public List<Materia> getCorrelativasCursada() {
		return correlativasCursada;
	}

	public List<Materia> getCorrelativasRendir() {
		return correlativasRendir;
	}
	
	public void addDocente(Docente d) {
		this.docentes.add(d);
	}

	public void addInscripcion(Inscripcion i) {
		this.inscripciones.add(i);
		i.setMateria(this);
	}
	
	public void addExamen(Examen e) {
		this.examenes.add(e);
		e.setMateria(this);
	}
	 
	public void addCorrelativaRendir(Materia m) {
		this.correlativasRendir.add(m);
	}
	
	public void addCorrelativaCursada(Materia m) {
		this.correlativasCursada.add(m);
	}
}
