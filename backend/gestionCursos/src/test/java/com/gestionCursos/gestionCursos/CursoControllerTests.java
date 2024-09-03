package com.gestionCursos.gestionCursos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionCursos.gestionCursos.Controller.CursoController;
import com.gestionCursos.gestionCursos.Models.Asignatura;
import com.gestionCursos.gestionCursos.Models.Curso;
import com.gestionCursos.gestionCursos.Service.CursoService;
import com.gestionCursos.gestionCursos.Repository.*;
import java.sql.Date;

@WebMvcTest(CursoController.class)
public class CursoControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CursoService cursoService;

    @MockBean
    private CursoRepository cursoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCursos() throws Exception {
        Curso curso1 = new Curso();
        curso1.setId(1);
        curso1.setNombre("Curso 1");

        Curso curso2 = new Curso();
        curso2.setId(2);
        curso2.setNombre("Curso 2");

        when(cursoService.getCursos()).thenReturn(Arrays.asList(curso1, curso2));

        mvc.perform(get("/cursos/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Curso 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Curso 2"));
    }

    @Test
    void testGetCursoById() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso 1");

        when(cursoService.getCurso(1)).thenReturn(curso);

        mvc.perform(get("/cursos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Curso 1"));
    }

    @Test
    void testGetAsignaturasByCursoId() throws Exception {
        Asignatura asignatura1 = new Asignatura();
        asignatura1.setId(1);
        asignatura1.setNombre("Mathematics");

        Asignatura asignatura2 = new Asignatura();
        asignatura2.setId(2);
        asignatura2.setNombre("Science");

        List<Asignatura> asignaturas = Arrays.asList(asignatura1, asignatura2);

        when(cursoService.getAsignaturas(1)).thenReturn(asignaturas);

        mvc.perform(get("/cursos/1/asignaturas")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Mathematics"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Science"));
    }
@Test
void testSaveCurso() throws Exception {
    String fixedDateString = "2024-09-01";
    java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(fixedDateString);
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    Curso curso = new Curso();
    curso.setId(1);
    curso.setNombre("Curso Test");
    curso.setDescripcion("Descripcion Test");
    curso.setFechaInicio(sqlDate);
    curso.setFechaFin(sqlDate);
    curso.setPrecio(100.0);

    when(cursoService.saveCurso(any(Curso.class))).thenReturn(curso);

    String cursoJson = objectMapper.writeValueAsString(curso);

    mvc.perform(post("/cursos/agregarCurso")
            .contentType(MediaType.APPLICATION_JSON)
            .content(cursoJson))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nombre").value("Curso Test"))
            .andExpect(jsonPath("$.descripcion").value("Descripcion Test"))
            .andExpect(jsonPath("$.precio").value(100.0))
            .andExpect(jsonPath("$.fechaInicio").value(fixedDateString))
            .andExpect(jsonPath("$.fechaFin").value(fixedDateString));
}

@Test
void testSaveAsignatura() throws Exception {
    Curso curso = new Curso();
    curso.setId(1);

    Asignatura asignatura = new Asignatura();
    asignatura.setId(1);
    asignatura.setNombre("Asignatura Test");
    asignatura.setDescripcion("Descripcion Asignatura Test");
    asignatura.setCurso(curso);

    when(cursoService.saveAsignatura(any(Asignatura.class))).thenReturn(asignatura);

    String asignaturaJson = objectMapper.writeValueAsString(asignatura);


    mvc.perform(post("/cursos/agregarAsignatura")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asignaturaJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.nombre").value("Asignatura Test"))
            .andExpect(jsonPath("$.descripcion").value("Descripcion Asignatura Test"))
            .andExpect(jsonPath("$.curso.id").value(1));
}


}