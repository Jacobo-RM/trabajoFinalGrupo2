package com.gestionCursos.gestionCursos.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionCursos.gestionCursos.Models.Curso;
import com.gestionCursos.gestionCursos.Service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("/all")
    public List<Curso> getCursos() {
        return cursoService.getCursos();
    }
    
}
