package com.gestionCursos.gestionCursos.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gestionCursos.gestionCursos.Repository.AsignaturaRepository;
import com.gestionCursos.gestionCursos.Repository.CursoRepository;
import com.gestionCursos.gestionCursos.Models.Curso;
import com.gestionCursos.gestionCursos.Models.Asignatura;

@Service
@Transactional
public class CursoService {

    private final CursoRepository cursoRepository;
    private final AsignaturaRepository asignaturaRepository;

    public CursoService(CursoRepository cursoRepository, AsignaturaRepository asignaturaRepository) {
        this.cursoRepository = cursoRepository;
        this.asignaturaRepository = asignaturaRepository;
    }

    @Transactional (readOnly = true)
    public List<Curso> getCursos() {
        return cursoRepository.findAll();
    }
    


    
}
