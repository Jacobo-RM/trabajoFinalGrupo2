package com.gestionCursos.gestionCursos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionCursos.gestionCursos.Models.Asignatura;
import com.gestionCursos.gestionCursos.Models.Curso;
import com.gestionCursos.gestionCursos.Service.CursoService;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "http://localhost:5173")

public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/")
    public List<Curso> getCursos() {
        return cursoService.getCursos();
    }

    @GetMapping("/{id}")
    public Curso getCurso(@PathVariable int id) {
        return cursoService.getCurso(id);
    }

    @GetMapping("/{id}/asignaturas")
    public List<Asignatura> getAsignaturas(@PathVariable int id) {
        return cursoService.getAsignaturas(id);
    }

    @GetMapping("/asignaturas")
    public List<Asignatura> getAllAsignaturas() {
        return cursoService.getAsignaturas();
    }

    @GetMapping("/asignaturas/{id}")
    public Asignatura getAsignatura(@PathVariable int id) {
        return cursoService.getAsignatura(id);
    }

    @PostMapping("/agregarCurso")
    public Curso saveCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @PostMapping("/agregarAsignatura")
    public Asignatura saveAsignatura(@RequestBody Asignatura asignatura) {
        return cursoService.saveAsignatura(asignatura);
    }

    @PostMapping("/{idCurso}/agregarAsignatura")
    public void agregarAsignaturaACurso(@PathVariable int idCurso, Asignatura asignatura) {
        cursoService.agregarAsignaturaACurso(idCurso, asignatura);
    }

    @PutMapping("/actualizarAsignatura/{id}")
    public Asignatura updateAsignatura(@PathVariable int id, @RequestBody Asignatura asignatura) {
        asignatura.setId(id);
        return cursoService.updateAsignatura(asignatura);
    }

    @PutMapping("/actualizarCurso/{id}")
    public Curso updateCurso(@PathVariable int id, @RequestBody Curso curso) {
        curso.setId(id);
        return cursoService.updateCurso(curso);
    }

    @DeleteMapping("/borrarCurso/{id}")
    public void deleteCurso(@PathVariable("id") int id) {
        cursoService.deleteCurso(id);
    }

    @DeleteMapping("/borrarAsignatura/{id}")
    public void deleteAsignatura(@PathVariable int id) {
        cursoService.deleteAsignatura(id);
    }
}
