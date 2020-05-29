package isi.died2020.parcial01.ejercicio02.dominio;

import java.util.ArrayList;
import java.util.List;

public class Docente {
	private static Integer ID_GENERATOR=0;

	public enum Cargo{PROFESOR,JTP,AYUDANTE};

	private Integer id;
	private String nombre;
	private Cargo cargo;
	private List<Materia> catedras;
	private List<Inscripcion> inscriptos;
	private List<Examen> examenes;
	
	public Docente() {
		this.id = ID_GENERATOR++;
		this.catedras = new ArrayList<Materia>();
		this.inscriptos = new ArrayList<Inscripcion>();
		this.examenes = new ArrayList<Examen>();
	}
	
	public Docente( String nombre, Cargo cargo) {
		this();
		this.nombre = nombre;
		this.cargo = cargo;
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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void asignarCatedra(Materia m) {
		this.catedras.add(m);
		m.addDocente(this);
	}

	public void agregarInscripcion(Inscripcion m) {
		this.inscriptos.add(m);
		m.setDocente(this);
	}
	
	public void agregarExamen(Examen m) {
		this.examenes.add(m);
		m.setDocente(this);
	}
	
}
