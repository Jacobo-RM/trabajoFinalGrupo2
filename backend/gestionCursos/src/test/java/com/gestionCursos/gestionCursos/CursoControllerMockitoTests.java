package com.gestionCursos.gestionCursos;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.List;


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

@WebMvcTest(CursoController.class)
public class CursoControllerMockitoTests {

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
        curso1.setDescripcion("Descripcion Curso 1");
        curso1.setFecha_inicio("2024-09-01");
        curso1.setFecha_fin("2024-09-01");
        curso1.setPrecio(100.0);
        curso1.setAnio(2024);

        when(cursoService.getCursos()).thenReturn(Arrays.asList(curso1));

        mvc.perform(get("/cursos/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Curso 1"))
                .andExpect(jsonPath("$[0].descripcion").value("Descripcion Curso 1"))
                .andExpect(jsonPath("$[0].fecha_inicio").value("2024-09-01"))
                .andExpect(jsonPath("$[0].fecha_fin").value("2024-09-01"))
                .andExpect(jsonPath("$[0].precio").value(100.0));
    }

    @Test
    void testGetCursoById() throws Exception {

        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso 1");
        curso.setDescripcion("Descripcion Curso 1");
        curso.setFecha_inicio("2024-09-01");
        curso.setFecha_fin("2024-09-01");
        curso.setPrecio(100.0);
        curso.setAnio(2024);

        when(cursoService.getCurso(1)).thenReturn(curso);

        mvc.perform(get("/cursos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Curso 1"))
                .andExpect(jsonPath("$.descripcion").value("Descripcion Curso 1"))
                .andExpect(jsonPath("$.fecha_inicio").value("2024-09-01"))
                .andExpect(jsonPath("$.fecha_fin").value("2024-09-01"))
                .andExpect(jsonPath("$.precio").value(100.0));

    }

    @Test
    void testGetAsignaturasByCursoId() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso 1");
        curso.setDescripcion("Descripcion Curso 1");
        curso.setFecha_inicio("2024-09-01");
        curso.setFecha_fin("2024-09-01");
        curso.setPrecio(100.0);
        curso.setAnio(2024);

        Asignatura asignatura = new Asignatura();
        asignatura.setId(1);
        asignatura.setNombre("Asignatura Test");
        asignatura.setDescripcion("Descripcion Asignatura Test");
        asignatura.setCurso(curso);
        asignatura.setCreditos(3);
        asignatura.setNum_horas(45);
        asignatura.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);

        Asignatura asignatura2 = new Asignatura();
        asignatura2.setId(2);
        asignatura2.setNombre("Asignatura Test 2");
        asignatura2.setDescripcion("Descripcion Asignatura Test 2");
        asignatura2.setCurso(curso);
        asignatura2.setCreditos(3);
        asignatura2.setNum_horas(45);
        asignatura2.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);

        List<Asignatura> asignaturas = Arrays.asList(asignatura, asignatura2);

        when(cursoService.getAsignaturas(1)).thenReturn(asignaturas);

        mvc.perform(get("/cursos/1/asignaturas")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Asignatura Test"))
                .andExpect(jsonPath("$[0].descripcion").value("Descripcion Asignatura Test"))
                .andExpect(jsonPath("$[0].curso.id").value(1))
                .andExpect(jsonPath("$[0].curso.nombre").value("Curso 1"))
                .andExpect(jsonPath("$[0].curso.descripcion").value("Descripcion Curso 1"))
                .andExpect(jsonPath("$[0].curso.fecha_inicio").value("2024-09-01"))
                .andExpect(jsonPath("$[0].curso.fecha_fin").value("2024-09-01"))
                .andExpect(jsonPath("$[0].curso.precio").value(100.0))
                .andExpect(jsonPath("$[0].curso.anio").value(2024))
                .andExpect(jsonPath("$[0].creditos").value(3))
                .andExpect(jsonPath("$[0].num_horas").value(45))
                .andExpect(jsonPath("$[0].tipo").value("OBLIGATORIA"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Asignatura Test 2"))
                .andExpect(jsonPath("$[1].descripcion").value("Descripcion Asignatura Test 2"))
                .andExpect(jsonPath("$[1].curso.id").value(1))
                .andExpect(jsonPath("$[1].curso.nombre").value("Curso 1"))
                .andExpect(jsonPath("$[1].curso.descripcion").value("Descripcion Curso 1"))
                .andExpect(jsonPath("$[1].curso.fecha_inicio").value("2024-09-01"))
                .andExpect(jsonPath("$[1].curso.fecha_fin").value("2024-09-01"))
                .andExpect(jsonPath("$[1].curso.precio").value(100.0))
                .andExpect(jsonPath("$[1].curso.anio").value(2024))
                .andExpect(jsonPath("$[1].creditos").value(3))
                .andExpect(jsonPath("$[1].num_horas").value(45))
                .andExpect(jsonPath("$[1].tipo").value("OBLIGATORIA"));

    }

    @Test
    void testGetAsignaturas() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso 1");
        curso.setDescripcion("Descripcion Curso 1");
        curso.setFecha_inicio("2024-09-01");
        curso.setFecha_fin("2024-09-01");
        curso.setPrecio(100.0);
        curso.setAnio(2024);

        Asignatura asignatura1 = new Asignatura();
        asignatura1.setId(1);
        asignatura1.setNombre("Asignatura 1");
        asignatura1.setDescripcion("Descripcion Asignatura 1");
        asignatura1.setCreditos(3);
        asignatura1.setNum_horas(45);
        asignatura1.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);
        asignatura1.setCurso(curso);

        Asignatura asignatura2 = new Asignatura();
        asignatura2.setId(2);
        asignatura2.setNombre("Asignatura 2");
        asignatura2.setDescripcion("Descripcion Asignatura 2");
        asignatura2.setCreditos(3);
        asignatura2.setNum_horas(45);
        asignatura2.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);
        asignatura2.setCurso(curso);

        List<Asignatura> asignaturas = Arrays.asList(asignatura1, asignatura2);

        when(cursoService.getAsignaturas()).thenReturn(asignaturas);

        mvc.perform(get("/cursos/asignaturas")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].curso.id").value(1))
                .andExpect(jsonPath("$[0].curso.nombre").value("Curso 1"))
                .andExpect(jsonPath("$[0].curso.descripcion").value("Descripcion Curso 1"))
                .andExpect(jsonPath("$[0].curso.fecha_inicio").value("2024-09-01"))
                .andExpect(jsonPath("$[0].curso.fecha_fin").value("2024-09-01"))
                .andExpect(jsonPath("$[0].curso.precio").value(100.0))
                .andExpect(jsonPath("$[0].curso.anio").value(2024))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Asignatura 1"))
                .andExpect(jsonPath("$[0].descripcion").value("Descripcion Asignatura 1"))
                .andExpect(jsonPath("$[0].creditos").value(3))
                .andExpect(jsonPath("$[0].num_horas").value(45))
                .andExpect(jsonPath("$[0].tipo").value("OBLIGATORIA"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Asignatura 2"))
                .andExpect(jsonPath("$[1].descripcion").value("Descripcion Asignatura 2"))
                .andExpect(jsonPath("$[1].creditos").value(3))
                .andExpect(jsonPath("$[1].num_horas").value(45))
                .andExpect(jsonPath("$[1].tipo").value("OBLIGATORIA"));

    }

    @Test
    void testGetAsignaturaById() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso 1");
        curso.setDescripcion("Descripcion Curso 1");
        curso.setFecha_inicio("2024-09-01");
        curso.setFecha_fin("2024-09-01");
        curso.setPrecio(100.0);
        curso.setAnio(2024);

        Asignatura asignatura = new Asignatura();
        asignatura.setId(1);
        asignatura.setNombre("Asignatura Test");
        asignatura.setDescripcion("Descripcion Asignatura Test");
        asignatura.setCurso(curso);
        asignatura.setCreditos(3);
        asignatura.setNum_horas(45);
        asignatura.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);

        when(cursoService.getAsignatura(1)).thenReturn(asignatura);

        mvc.perform(get("/cursos/asignaturas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Asignatura Test"))
                .andExpect(jsonPath("$.descripcion").value("Descripcion Asignatura Test"))
                .andExpect(jsonPath("$.curso.id").value(1))
                .andExpect(jsonPath("$.creditos").value(3))
                .andExpect(jsonPath("$.num_horas").value(45))
                .andExpect(jsonPath("$.tipo").value("OBLIGATORIA"))
                .andExpect(jsonPath("$.curso.nombre").value("Curso 1"))
                .andExpect(jsonPath("$.curso.descripcion").value("Descripcion Curso 1"))
                .andExpect(jsonPath("$.curso.fecha_inicio").value("2024-09-01"))
                .andExpect(jsonPath("$.curso.fecha_fin").value("2024-09-01"))
                .andExpect(jsonPath("$.curso.precio").value(100.0))
                .andExpect(jsonPath("$.curso.anio").value(2024));

    }

    @Test
    void testSaveAsignatura() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso 1");
        curso.setDescripcion("Descripcion Curso 1");
        curso.setFecha_inicio("2024-09-01");
        curso.setFecha_fin("2024-09-01");
        curso.setPrecio(100.0);
        curso.setAnio(2024);

        Asignatura asignatura = new Asignatura();
        asignatura.setId(1);
        asignatura.setNombre("Asignatura Test");
        asignatura.setDescripcion("Descripcion Asignatura Test");
        asignatura.setCurso(curso);
        asignatura.setCreditos(3);
        asignatura.setNum_horas(45);
        asignatura.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);

        when(cursoService.saveAsignatura(any(Asignatura.class))).thenReturn(asignatura);

        String asignaturaJson = objectMapper.writeValueAsString(asignatura);

        mvc.perform(post("/cursos/agregarAsignatura")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asignaturaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Asignatura Test"))
                .andExpect(jsonPath("$.descripcion").value("Descripcion Asignatura Test"))
                .andExpect(jsonPath("$.curso.id").value(1))
                .andExpect(jsonPath("$.creditos").value(3))
                .andExpect(jsonPath("$.num_horas").value(45))
                .andExpect(jsonPath("$.tipo").value("OBLIGATORIA"))
                .andExpect(jsonPath("$.curso.nombre").value("Curso 1"))
                .andExpect(jsonPath("$.curso.descripcion").value("Descripcion Curso 1"))
                .andExpect(jsonPath("$.curso.fecha_inicio").value("2024-09-01"))
                .andExpect(jsonPath("$.curso.fecha_fin").value("2024-09-01"))
                .andExpect(jsonPath("$.curso.precio").value(100.0))
                .andExpect(jsonPath("$.curso.anio").value(2024));
    }

    @Test
    void testSaveCurso() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso 1");
        curso.setDescripcion("Descripcion Curso 1");
        curso.setFecha_inicio("2024-09-01");
        curso.setFecha_fin("2024-09-01");
        curso.setPrecio(100.0);
        curso.setAnio(2024);

        when(cursoService.saveCurso(any(Curso.class))).thenReturn(curso);

        String cursoJson = objectMapper.writeValueAsString(curso);

        mvc.perform(post("/cursos/agregarCurso")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cursoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Curso 1"))
                .andExpect(jsonPath("$.descripcion").value("Descripcion Curso 1"))
                .andExpect(jsonPath("$.fecha_inicio").value("2024-09-01"))
                .andExpect(jsonPath("$.fecha_fin").value("2024-09-01"))
                .andExpect(jsonPath("$.precio").value(100.0))
                .andExpect(jsonPath("$.anio").value(2024));
    }

    @Test
    void testDeleteAsignatura() throws Exception {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(1);
        asignatura.setNombre("Asignatura Test");
        asignatura.setDescripcion("Descripcion Asignatura Test");
        asignatura.setCreditos(3);
        asignatura.setNum_horas(45);
        asignatura.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);

        int asignaturaId = asignatura.getId();

        doNothing().when(cursoService).deleteAsignatura(asignaturaId);

        mvc.perform(delete("/cursos/borrarAsignatura/{id}", asignaturaId))
                .andExpect(status().isOk());


        verify(cursoService).deleteAsignatura(asignaturaId);
    }

    @Test
    void testDeleteCurso() throws Exception {
        Curso curso = new Curso();
        curso.setId(1);
        curso.setNombre("Curso 1");
        curso.setDescripcion("Descripcion Curso 1");
        curso.setFecha_inicio("2024-09-01");
        curso.setFecha_fin("2024-09-01");
        curso.setPrecio(100.0);
        curso.setAnio(2024);

        int cursoId = curso.getId();

        doNothing().when(cursoService).deleteCurso(cursoId);

        mvc.perform(delete("/cursos/borrarCurso/{id}", cursoId))
                .andExpect(status().isOk());

        verify(cursoService).deleteCurso(cursoId);
    }
    @Test
    void testUpdateAsignatura() throws Exception {
        Asignatura originalAsignatura = new Asignatura();
        originalAsignatura.setId(1);
        originalAsignatura.setNombre("Python");
        originalAsignatura.setDescripcion("Python");
        originalAsignatura.setCreditos(3);
        originalAsignatura.setNum_horas(45);
        originalAsignatura.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);

        Asignatura updatedAsignatura = new Asignatura();
        updatedAsignatura.setId(1);
        updatedAsignatura.setNombre("Java");
        updatedAsignatura.setDescripcion("Java");
        updatedAsignatura.setCreditos(4);
        updatedAsignatura.setNum_horas(50);
        updatedAsignatura.setTipo(Asignatura.TipoAsignatura.OBLIGATORIA);

        when(cursoService.updateAsignatura(any(Asignatura.class))).thenReturn(updatedAsignatura);

        ObjectMapper objectMapper = new ObjectMapper();
        String updatedAsignaturaJson = objectMapper.writeValueAsString(updatedAsignatura);

        mvc.perform(put("/cursos/actualizarAsignatura/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedAsignaturaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Java"))
                .andExpect(jsonPath("$.descripcion").value("Java"))
                .andExpect(jsonPath("$.creditos").value(4))
                .andExpect(jsonPath("$.num_horas").value(50))
                .andExpect(jsonPath("$.tipo").value("OBLIGATORIA"));

        verify(cursoService).updateAsignatura(any(Asignatura.class));
    }

}