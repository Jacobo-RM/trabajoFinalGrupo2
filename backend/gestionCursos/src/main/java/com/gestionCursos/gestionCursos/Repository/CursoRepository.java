package com.gestionCursos.gestionCursos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionCursos.gestionCursos.Models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
