package isi.died2020.parcial01.ejercicio02.app;

import java.util.ArrayList;
import java.util.List;

import isi.died2020.parcial01.ejercicio02.dominio.Alumno;
import isi.died2020.parcial01.ejercicio02.dominio.Docente;
import isi.died2020.parcial01.ejercicio02.dominio.Materia;

public class App {

	public static void main(String[] args) {
		
		MySysAcad sistema = new MySysAcadImpl();
		
		// MATERIAS
		Materia m1= new Materia("Materia 100");
		Materia m2= new Materia("Materia 200");
		m2.addCorrelativaCursada(m1);
		m2.addCorrelativaRendir(m1);
		
		Materia m3= new Materia("Materia 300");
		m2.addCorrelativaCursada(m1);
		m2.addCorrelativaCursada(m2);
		m2.addCorrelativaRendir(m1);
		
		sistema.registrarMateria(m1);
		sistema.registrarMateria(m2);
		
		
		// DOCENTES
		Docente prof1 = new Docente("Profesor 1", Docente.Cargo.PROFESOR);
		prof1.asignarCatedra(m1);
		prof1.asignarCatedra(m2);
		
		Docente prof2 = new Docente("Profesor 2", Docente.Cargo.PROFESOR);
		prof2.asignarCatedra(m3);
		
		Docente jtp1 = new Docente("JTP 1", Docente.Cargo.JTP);
		jtp1.asignarCatedra(m1);
		
		Docente jtp2= new Docente("JTP 2", Docente.Cargo.JTP);
		jtp2.asignarCatedra(m2);
		
		Docente ayud1= new Docente("Ayudante 1", Docente.Cargo.AYUDANTE);
		ayud1.asignarCatedra(m1);
		
		Docente ayud2= new Docente("AYudante 2", Docente.Cargo.AYUDANTE);
		ayud2.asignarCatedra(m3);

		sistema.registrarDocente(prof1);
		sistema.registrarDocente(prof2);
		sistema.registrarDocente(jtp1);
		sistema.registrarDocente(jtp2);
		sistema.registrarDocente(ayud1);
		sistema.registrarDocente(ayud2);

		// ALUMNOS
		Alumno a1 = new Alumno("Alumnno 1");
		Alumno a2 = new Alumno("Alumnno 2");
		Alumno a3 = new Alumno("Alumnno 3");
		Alumno a4 = new Alumno("Alumnno 4");
		Alumno a5 = new Alumno("Alumnno 5");
		Alumno a6 = new Alumno("Alumnno 6");
		Alumno a7 = new Alumno("Alumnno 7");
		Alumno a8 = new Alumno("Alumnno 8");
		Alumno a9 = new Alumno("Alumnno 9");
		Alumno a10 = new Alumno("Alumnno 10");
		Alumno a11 = new Alumno("Alumnno 11");
		Alumno a12 = new Alumno("Alumnno 12");
		Alumno a13 = new Alumno("Alumnno 13");
		Alumno a14 = new Alumno("Alumnno 14");
		Alumno a15 = new Alumno("Alumnno 15");
		sistema.registrarAlumnos(a1);
		sistema.registrarAlumnos(a2);
		sistema.registrarAlumnos(a3);
		sistema.registrarAlumnos(a4);
		sistema.registrarAlumnos(a5);
		sistema.registrarAlumnos(a6);
		sistema.registrarAlumnos(a7);
		sistema.registrarAlumnos(a8);
		sistema.registrarAlumnos(a9);
		sistema.registrarAlumnos(a10);
		sistema.registrarAlumnos(a11);
		sistema.registrarAlumnos(a12);
		sistema.registrarAlumnos(a13);
		sistema.registrarAlumnos(a14);
		sistema.registrarAlumnos(a15);


		
		sistema.inscribirAlumnoCursada(prof1, a1, m1,2020);

		sistema.inscribirAlumnoExamen(prof1, a1, m1);
	}
	

}
