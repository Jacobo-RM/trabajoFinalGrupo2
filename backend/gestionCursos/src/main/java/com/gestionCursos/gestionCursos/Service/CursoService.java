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

    @Transactional(readOnly = true)
    public List<Curso> getCursos() {
        return cursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Curso getCurso(int id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Asignatura> getAsignaturas(int id) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso != null) {
            return curso.getAsignaturas();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public List<Asignatura> getAsignaturas() {
        return asignaturaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Asignatura getAsignatura(int id) {
        return asignaturaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public Asignatura saveAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    @Transactional
    public void agregarAsignaturaACurso(int idCurso, Asignatura asignatura) {
        Curso curso = cursoRepository.findById(idCurso).orElse(null);
        if (curso != null) {
            if (!curso.getAsignaturas().contains(asignatura)) {
                curso.getAsignaturas().add(asignatura);
                cursoRepository.save(curso);
            }
        } else {
            throw new RuntimeException("Curso no encontrado");
        }
    }

    @Transactional
    public Asignatura updateAsignatura(Asignatura asignatura) {
        if (asignaturaRepository.existsById(asignatura.getId())) {
            return asignaturaRepository.save(asignatura);
        }
        return null;
    }

    @Transactional
    public void deleteCurso(int id) {
        cursoRepository.deleteById(id);

    }

    @Transactional
    public void deleteAsignatura(int id) {
        asignaturaRepository.deleteById(id);
    }

    @Transactional
    public Curso updateCurso(Curso curso) {
        if (cursoRepository.existsById(curso.getId())) {
            return cursoRepository.save(curso);
        }
        return null;
    }

    @Transactional
    public void eliminarAsignaturaDeCurso(int idCurso, int idAsignatura) {
        Curso curso = cursoRepository.findById(idCurso).orElse(null);
        if (curso != null) {
            curso.getAsignaturas().removeIf(asignatura -> asignatura.getId() == idAsignatura);
            cursoRepository.save(curso);
        }
    }

}
