package com.gestionCursos.gestionCursos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestionCursos.gestionCursos.Models.Asignatura;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
}
