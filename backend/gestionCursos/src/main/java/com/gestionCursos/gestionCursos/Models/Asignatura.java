package com.gestionCursos.gestionCursos.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "asignatura")
public class Asignatura {

    public enum TipoAsignatura {
        OBLIGATORIA,
        OPCIONAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private int creditos;

    @Column(nullable = false)
    private int num_horas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAsignatura tipo;

    public Asignatura() {
    }

    public Asignatura(int id, String nombre, String descripcion, Curso curso, int creditos, int num_horas, TipoAsignatura tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.curso = curso;
        this.creditos = creditos;
        this.num_horas = num_horas;
        this.tipo = tipo;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public TipoAsignatura getTipo() {
        return tipo;
    }

    public void setTipo(TipoAsignatura tipo) {
        this.tipo = tipo;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getNum_horas() {
        return num_horas;
    }

    public void setNum_horas(int num_horas) {
        this.num_horas = num_horas;
    }
}
